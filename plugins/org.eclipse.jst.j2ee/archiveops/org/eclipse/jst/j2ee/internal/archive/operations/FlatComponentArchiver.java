/*******************************************************************************
 * Copyright (c) 2010, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.jee.archive.ArchiveException;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.internal.ArchiveUtil;
import org.eclipse.wst.common.componentcore.internal.flat.FlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent;
import org.eclipse.wst.common.componentcore.internal.flat.IChildModuleReference;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFile;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatFolder;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatVirtualComponent;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class FlatComponentArchiver {
	private IFlatVirtualComponent flatComponent;
	private OutputStream destinationStream;
	private ZipOutputStream zipOutputStream;
	private IVirtualComponent component;
	private List<IFlattenParticipant> participants;
	private ComponentExportCallback callbackHandler;
	private List<IPath> zipEntries = new ArrayList<IPath>();
	private List <IVirtualComponent> componentsArchived = new ArrayList<IVirtualComponent>();
	
	public interface ComponentExportCallback {
		public boolean canSave(IVirtualComponent component);
		public IFlatVirtualComponent saveComponent(IVirtualComponent component, ZipOutputStream zipOutputStream, List<IPath> zipEntries) throws ArchiveException;
		public boolean createManifest();
	}
	
	public static class UnderlyingFileNotFoundException extends ArchiveSaveFailureException {
		private static final long serialVersionUID = 1;

		public UnderlyingFileNotFoundException(String message) {
			super(message);
		}
		
	}

	public FlatComponentArchiver(IVirtualComponent aComponent, OutputStream out, List<IFlattenParticipant> fParticipants) {
		participants = fParticipants;
		component = aComponent;
		destinationStream = out;
		zipOutputStream = new ZipOutputStream(out);
		flatComponent = getFlatComponent(aComponent);
	}
	
	public FlatComponentArchiver(IVirtualComponent aComponent, OutputStream out, List<IFlattenParticipant> fParticipants, ComponentExportCallback callback) {
		participants = fParticipants;
		component = aComponent;
		destinationStream = out;
		callbackHandler = callback;
		zipOutputStream = new ZipOutputStream(out);
		flatComponent = getFlatComponent(aComponent);
	}
	
	public void close() throws IOException {
		getDestinationStream().close();
	}

	public void finish() throws IOException {
		getZipOutputStream().finish();
		//If this is not nested, close the stream to free up the resource
		//otherwise, don't close it because the parent may not be done
		if (!(getDestinationStream() instanceof ZipOutputStream))
			getDestinationStream().close();
	}
	
	/**
	 * Fetches the resources for the component using the FlatVirtualComponent
	 * and saves them to an archive.  
	 * 
	 * @throws ArchiveSaveFailureException
	 */
	protected void saveArchive() throws ArchiveSaveFailureException {
		Exception caughtException = null;
		boolean createManifest = true;
		try {
			if (callbackHandler != null && callbackHandler.canSave(getComponent())) {
				flatComponent = callbackHandler.saveComponent(getComponent(), getZipOutputStream(), zipEntries);
				createManifest = callbackHandler.createManifest();
			}
			if (flatComponent != null) {
				IFlatResource[] resources = flatComponent.fetchResources();
				saveManifest(Arrays.asList(resources), createManifest);
				saveChildModules(flatComponent.getChildModules());
				saveFlatResources(resources);
			}
		} catch (Exception e){
			caughtException = e;
		} finally {
			try {
				finish();
			} catch (IOException e) {
				throw new ArchiveSaveFailureException(e);
			} finally {
				if (caughtException != null){
					throw new ArchiveSaveFailureException(caughtException);
				}
			}
		}
	}
	
	/**
	 * Creates a nested archive for the component inside its parent archive
	 * 
	 * @param component
	 * @param entry
	 * @return FlatComponentArchiver
	 * @throws IOException
	 */
	protected FlatComponentArchiver saveNestedArchive(IVirtualComponent component, IPath entry) throws IOException {
		ZipEntry nest = new ZipEntry(entry.toString());
		getZipOutputStream().putNextEntry(nest);
		return new FlatComponentArchiver(component, getZipOutputStream(), getParticipants(), callbackHandler);
	}

	/**
	 * Adds the resources returned from the FlatVirtualComponent into the archive
	 * 
	 * @param resources
	 * @throws ArchiveSaveFailureException
	 */
	protected void saveFlatResources(IFlatResource[] resources) throws ArchiveSaveFailureException {
		for (int i = 0; i < resources.length; i++) {
			IFlatResource resource = resources[i];
			IPath entryPath = resource.getModuleRelativePath().append(resource.getName());
			if (resource instanceof IFlatFile) {
				if (shouldInclude(entryPath, true)) {
					addZipEntry(resource, entryPath);
					zipEntries.add(entryPath);
				}
			} else if (resource instanceof IFlatFolder) {
				if (shouldInclude(entryPath, false)) {
					addZipEntry(resource, entryPath);
					zipEntries.add(entryPath);
					saveFlatResources(((IFlatFolder)resource).members());
				}
			}
		}
	}

	/**
	 * @param entryPath
	 * @param isFile
	 * @return true or false - should resource be added to the archive
	 */
	protected boolean shouldInclude(IPath entryPath, boolean isFile) {
		if (zipEntries.contains(entryPath)) {
			return false;
		}
		if (isFile) {
			if (entryPath.equals(new Path(J2EEConstants.MANIFEST_URI))) {
				return false;
			}
		}
		else if (entryPath.equals(new Path(IModuleConstants.DOT_SETTINGS))) {
			return false;
		}
		return true;
	}

	/**
	 * Creates the nested jars from project references and saves them 
	 * to the archive.
	 * 
	 * @param childModules
	 * @throws ArchiveSaveFailureException
	 * @throws IOException
	 */
	protected void saveChildModules(IChildModuleReference[] childModules) throws ArchiveSaveFailureException, IOException {
		componentsArchived.add(component);
		for (int i = 0; i < childModules.length; i++) {
			IChildModuleReference childModule = childModules[i];
			IPath entryPath = childModule.getRelativeURI();
			
			//keep track of project references added.  we only want to include the 
			//project when both a binary module and its backing project exist
			zipEntries.add(entryPath);
			
			//prevent an infinite loop due to cycle dependencies
			if (componentsArchived.contains(childModule.getComponent()))
				continue;
			
			FlatComponentArchiver saver = saveNestedArchive(childModule.getComponent(), entryPath);
			saver.setArchivedComponents(componentsArchived);
			saver.saveArchive();
		}
	}

	/**
	 * Adds an entry and copies the resource into the archive
	 * 
	 * @param flatresource
	 * @param entryPath
	 * @throws ArchiveSaveFailureException
	 */
	protected void addZipEntry(IFlatResource f, IPath entryPath) throws ArchiveSaveFailureException {
		try {
			IPath path = entryPath;
			boolean isFolder = false;
			long lastModified = 0;
			
			if (f instanceof IFlatFolder) {
				isFolder = true;
				File folder = ((IFlatFolder)f).getAdapter(File.class);
				if (folder != null) {
					lastModified = folder.lastModified();
				}
				if (!path.hasTrailingSeparator())
					path = path.addTrailingSeparator();
			}
			else {
				lastModified = ((IFlatFile) f).getModificationStamp();
			}
			ZipEntry entry = new ZipEntry(path.toString());
			if (lastModified > 0)
				entry.setTime(lastModified);
			
			getZipOutputStream().putNextEntry(entry);
			if (!isFolder) {
				InputStream is = f.getAdapter(InputStream.class);
				if (is == null){
					File file = f.getAdapter(File.class);
					String msg = null;
					if (file!= null)
						msg = "Cannot find the file " + file.getAbsolutePath() + " in the file system. Make sure the file exists and try the operation again"; //$NON-NLS-1$ //$NON-NLS-2$
					else
						msg = "Cannot find the file " + f.getModuleRelativePath().append(f.getName()) + " in the file system. Make sure the file exists and try the operation again"; //$NON-NLS-1$ //$NON-NLS-2$
					throw new UnderlyingFileNotFoundException(msg);
				}
				ArchiveUtil.copy(is, getZipOutputStream());
			}
			getZipOutputStream().closeEntry();
		} catch (IOException e) {
			throw new ArchiveSaveFailureException(e);
		}
	}

	/**
	 * The FlatVirtualComponent is what does the bulk of the work
	 * 
	 * @param component
	 * @return IFlatVirtualComponent
	 */
	protected IFlatVirtualComponent getFlatComponent(IVirtualComponent component) {
		FlatComponentTaskModel options = new FlatComponentTaskModel();
		options.put(FlatVirtualComponent.PARTICIPANT_LIST, getParticipants());
		return new FlatVirtualComponent(component, options);
	}
	
	protected List<IFlattenParticipant> getParticipants() {
		return participants;
	}

	protected java.util.zip.ZipOutputStream getZipOutputStream() {
		return zipOutputStream;
	}

	/**
	 * This method adds the existing MANIFEST.MF as the first entry in the archive.
	 * This is necessary to support clients who use JarInputStream.getManifest().
	 * If no MANIFEST.MF is found, one is created if createManifest param is true
	 * 
	 * @param resources
	 * @param createManifest 
	 * @throws ArchiveSaveFailureException
	 */
	private void saveManifest(List<IFlatResource> resources, boolean createManifest) throws ArchiveSaveFailureException {
		IFlatFolder metainf = (FlatFolder)VirtualComponentFlattenUtility.getExistingModuleResource(resources, new Path(J2EEConstants.META_INF));
		IFlatFile manifest = null;
		
		if (metainf != null) {
			IFlatResource[] children = metainf.members();
			for (int i = 0; i < children.length; i++) {
				if (children[i].getName().equals(J2EEConstants.MANIFEST_SHORT_NAME)) {
					manifest = (IFlatFile) children[i];
					IPath entryPath = manifest.getModuleRelativePath().append(manifest.getName());
					addZipEntry(manifest, entryPath);
					break;
				}
			}
		}
		if (createManifest && manifest == null) {
			//manifest not found so create one for the archive
			createManifest();
		}
	}

	private void createManifest() throws ArchiveSaveFailureException {
		String manifestContents = "Manifest-Version: 1.0\r\n\r\n"; //$NON-NLS-1$
		try {
			ZipEntry entry = new ZipEntry(J2EEConstants.MANIFEST_URI);
			getZipOutputStream().putNextEntry(entry);
			ArchiveUtil.copy(new ByteArrayInputStream(manifestContents.getBytes()), getZipOutputStream());
		} catch (IOException e) {
			throw new ArchiveSaveFailureException(e);
		}
	}

	public void setArchivedComponents(List<IVirtualComponent> componentList) {
		componentsArchived.addAll(componentList);
	}

	public java.io.OutputStream getDestinationStream() {
		return destinationStream;
	}

	public IVirtualComponent getComponent() {
		return component;
	}

	public IFlatVirtualComponent getFlatComponent() {
		return flatComponent;
	}

	public List<IPath> getZipEntries() {
		return zipEntries;
	}


}