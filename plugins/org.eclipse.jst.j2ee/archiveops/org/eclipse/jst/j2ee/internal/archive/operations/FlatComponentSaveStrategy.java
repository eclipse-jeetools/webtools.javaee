/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.ComponentArchiveLoadAdapter;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.jee.archive.ArchiveException;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.IArchiveResource;
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
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class FlatComponentSaveStrategy {
	private IFlatVirtualComponent flatComponent;
	private OutputStream destinationStream;
	private boolean isExportSource;
	private ZipOutputStream zipOutputStream;
	private IVirtualComponent component;
	private List<IFlattenParticipant> participants;
	private List<IPath> zipEntries = new ArrayList<IPath>();
	
	public FlatComponentSaveStrategy(IVirtualComponent aComponent, OutputStream out, boolean exportSource, List<IFlattenParticipant> fParticipants) {
		participants = fParticipants;
		component = aComponent;
		isExportSource = exportSource;
		destinationStream = out;
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
	
	protected void saveArchive() throws ArchiveSaveFailureException {
		Exception caughtException = null;
		try {
			if (JavaEEProjectUtilities.isJCAComponent(getComponent())) {
				saveJCAArchive();
			}
			else {	
				IFlatResource[] resources = getFlatComponent().fetchResources();
				saveManifest(Arrays.asList(resources));
				saveFlatResources(resources);
				saveChildModules(getFlatComponent().getChildModules());
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
	
	protected FlatComponentSaveStrategy saveNestedArchive(IVirtualComponent component, IPath entry) throws IOException {
		ZipEntry nest = new ZipEntry(entry.toString());
		getZipOutputStream().putNextEntry(nest);
		return new FlatComponentSaveStrategy(component, getZipOutputStream(), isExportSource(), getParticipants());
	}

	protected void saveJCAArchive() throws ArchiveException {
		IArchive archiveToSave = null;
		try {
			archiveToSave = JavaEEArchiveUtilities.INSTANCE.openArchive(getComponent());
			IArchiveLoadAdapter loadAdapter = archiveToSave.getLoadAdapter();
			if (loadAdapter instanceof ComponentArchiveLoadAdapter) {
				ComponentArchiveLoadAdapter cLoadAdapter = (ComponentArchiveLoadAdapter)loadAdapter;
				cLoadAdapter.setExportSource(isExportSource());
			}
			List<IArchiveResource> files = archiveToSave.getArchiveResources();
			saveManifest(archiveToSave);
			for (IArchiveResource file : files) {
				if (!isManifest(file.getPath())) {
					addZipEntry(file);
				}
			}
			
		} catch (Exception e) {
			throw new ArchiveException(AppClientArchiveOpsResourceHandler.ARCHIVE_OPERATION_OpeningArchive, e);
		} finally {
			if (archiveToSave != null){
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archiveToSave);
			}
		}
	}

	protected void saveFlatResources(IFlatResource[] resources) throws ArchiveSaveFailureException {
		for (int i = 0; i < resources.length; i++) {
			IFlatResource resource = resources[i];
			IPath entryPath = resource.getModuleRelativePath().append(resource.getName());
			if (resource instanceof IFlatFile) {
				if (!isManifest(entryPath)) {
					File f = (File)resource.getAdapter(File.class);
					addZipEntry(f, entryPath, false);
				}
			} else if (resource instanceof IFlatFolder) {
				if (shouldInclude(entryPath)) {
					File f = (File)((IFlatFolder)resource).getAdapter(File.class);
					addZipEntry(f, entryPath, true);
					saveFlatResources(((IFlatFolder)resource).members());
				}
			}
		}
	}

	protected boolean shouldInclude(IPath entryPath) {
		if (entryPath.segment(0).startsWith(".settings")) { //$NON-NLS-1$
			return false;
		}
		return true;
	}

	protected void saveChildModules(IChildModuleReference[] childModules) throws ArchiveSaveFailureException, IOException {
		for (int i = 0; i < childModules.length; i++) {
			IChildModuleReference childModule = childModules[i];
			IPath entry = childModule.getRelativeURI();
			if (!zipEntries.contains(entry)) {
				FlatComponentSaveStrategy saver = saveNestedArchive(childModule.getComponent(), entry);
				saver.saveArchive();
			}
		}
	}

	protected void addZipEntry(File f, IPath entryPath, boolean isFolder) throws ArchiveSaveFailureException {
		try {
			IPath path = entryPath;
			zipEntries.add(entryPath);
			if (isFolder && !path.hasTrailingSeparator()){
				path = path.addTrailingSeparator();
			}
			ZipEntry entry = new ZipEntry(path.toString());
			if (f.lastModified() > 0)
				entry.setTime(f.lastModified());
			getZipOutputStream().putNextEntry(entry);
			if (!isFolder) {
				ArchiveUtil.copy(new FileInputStream(f), getZipOutputStream());
			}
			getZipOutputStream().closeEntry();
		} catch (IOException e) {
			throw new ArchiveSaveFailureException(e);
		}
	}
	
	protected void addZipEntry(IArchiveResource resource) throws ArchiveSaveFailureException {
		try {
			IPath path = resource.getPath();
			if (resource.getType() == IArchiveResource.DIRECTORY_TYPE && !path.hasTrailingSeparator()){
				path = path.addTrailingSeparator();
			}
			ZipEntry entry = new ZipEntry(path.toString());
			if (resource.getLastModified() > 0)
				entry.setTime(resource.getLastModified());
			getZipOutputStream().putNextEntry(entry);
			if (resource.getType() != IArchiveResource.DIRECTORY_TYPE) {
				ArchiveUtil.copy(resource.getInputStream(), getZipOutputStream());
			}
			getZipOutputStream().closeEntry();
		} catch (IOException e) {
			throw new ArchiveSaveFailureException(e);
		}
	}

	/**
	 * The FlatVirtualComponent model is what does the grunt of the work
	 * @return
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

	private void saveManifest(IArchive archiveToSave) throws FileNotFoundException, ArchiveSaveFailureException {
		IArchiveResource manifest = null;
		
		if (archiveToSave.containsArchiveResource(new Path(J2EEConstants.MANIFEST_URI))) {
			manifest = archiveToSave.getArchiveResource(new Path(J2EEConstants.MANIFEST_URI));
			addZipEntry(manifest);
		}
	}

	private void saveManifest(List<IFlatResource> resources) throws ArchiveSaveFailureException {
		IFlatFolder metainf = (FlatFolder)VirtualComponentFlattenUtility.getExistingModuleResource(resources, new Path(J2EEConstants.META_INF));
		IFlatFile manifest = null;
		
		if (metainf != null) {
			IFlatResource[] children = metainf.members();
			for (int i = 0; i < children.length; i++) {
				if (children[i].getName().equals(J2EEConstants.MANIFEST_SHORT_NAME)) {
					manifest = (IFlatFile) children[i];
					File f = (File)manifest.getAdapter(File.class);
					IPath entryPath = manifest.getModuleRelativePath().append(manifest.getName());
					addZipEntry(f, entryPath, false);
					break;
				}
			}
		}
		if (manifest == null) {
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

	private boolean isManifest(IPath path) {
		if (path.equals(new Path(J2EEConstants.MANIFEST_URI))) {
			return true;
		}
		return false;
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

	public boolean isExportSource() {
		return isExportSource;
	}


}