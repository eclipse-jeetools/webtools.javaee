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
package org.eclipse.jst.j2ee.internal.common.exportmodel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.AddClasspathFoldersParticipant;
import org.eclipse.jst.common.internal.modulecore.AddClasspathLibReferencesParticipant;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.archive.ComponentArchiveLoadAdapter;
import org.eclipse.jst.j2ee.internal.archive.JavaEEArchiveUtilities;
import org.eclipse.jst.j2ee.internal.archive.operations.AppClientArchiveOpsResourceHandler;
import org.eclipse.jst.j2ee.internal.archive.operations.FlatComponentArchiver.ComponentExportCallback;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.jee.archive.ArchiveException;
import org.eclipse.jst.jee.archive.ArchiveSaveFailureException;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveLoadAdapter;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.jst.jee.archive.internal.ArchiveUtil;
import org.eclipse.wst.common.componentcore.internal.flat.AbstractFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent;
import org.eclipse.wst.common.componentcore.internal.flat.IChildModuleReference;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatResource;
import org.eclipse.wst.common.componentcore.internal.flat.IFlatVirtualComponent;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.VirtualComponentFlattenUtility;
import org.eclipse.wst.common.componentcore.internal.flat.FlatVirtualComponent.FlatComponentTaskModel;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class JavaEEComponentExportCallback implements ComponentExportCallback {

	private boolean isExportSource;
	private List<IPath> zipEntries;

	public JavaEEComponentExportCallback(boolean exportSource) {
		isExportSource = exportSource;
	}
	
	@Override
	public boolean canSave(IVirtualComponent component) {
		if (JavaEEProjectUtilities.isJCAComponent(component)) {
			return true;
		}
		return false;
	}

	@Override
	public IFlatVirtualComponent saveComponent(IVirtualComponent component, ZipOutputStream zipOutputStream, List<IPath> entries) throws ArchiveException {
		IArchive archiveToSave = null;
		zipEntries = entries;
		try {
			archiveToSave = JavaEEArchiveUtilities.INSTANCE.openArchive(component);
			IArchiveLoadAdapter loadAdapter = archiveToSave.getLoadAdapter();
			if (loadAdapter instanceof ComponentArchiveLoadAdapter) {
				ComponentArchiveLoadAdapter cLoadAdapter = (ComponentArchiveLoadAdapter)loadAdapter;
				cLoadAdapter.setExportSource(isExportSource());
			}
			List<IArchiveResource> files = archiveToSave.getArchiveResources();
			saveManifest(archiveToSave, zipOutputStream);
			for (IArchiveResource file : files) {
				if (!isManifest(file.getPath())) {
					addZipEntry(zipOutputStream, file);
				}
			}
			
		} catch (Exception e) {
			throw new ArchiveException(AppClientArchiveOpsResourceHandler.ARCHIVE_OPERATION_OpeningArchive, e);
		} finally {
			if (archiveToSave != null){
				JavaEEArchiveUtilities.INSTANCE.closeArchive(archiveToSave);
			}
		}
		return getFlatComponent(component);
	}

	private boolean isManifest(IPath path) {
		if (path.equals(new Path(J2EEConstants.MANIFEST_URI))) {
			return true;
		}
		return false;
	}
	
	private void saveManifest(IArchive archiveToSave, ZipOutputStream zipOutputStream) throws FileNotFoundException, ArchiveSaveFailureException {
		IArchiveResource manifest = null;
		
		if (archiveToSave.containsArchiveResource(new Path(J2EEConstants.MANIFEST_URI))) {
			manifest = archiveToSave.getArchiveResource(new Path(J2EEConstants.MANIFEST_URI));
			addZipEntry(zipOutputStream, manifest);
		}
	}
	
	protected void addZipEntry(ZipOutputStream zipOutputStream, IArchiveResource resource) throws ArchiveSaveFailureException {
		try {
			IPath path = resource.getPath();
			if (resource.getType() == IArchiveResource.DIRECTORY_TYPE && !path.hasTrailingSeparator()){
				path = path.addTrailingSeparator();
			}
			ZipEntry entry = new ZipEntry(path.toString());
			if (resource.getLastModified() > 0)
				entry.setTime(resource.getLastModified());
			zipOutputStream.putNextEntry(entry);
			zipEntries.add(path);
			if (resource.getType() != IArchiveResource.DIRECTORY_TYPE) {
				ArchiveUtil.copy(resource.getInputStream(), zipOutputStream);
			}
			zipOutputStream.closeEntry();
		} catch (IOException e) {
			throw new ArchiveSaveFailureException(e);
		}
	}
	
	protected IFlatVirtualComponent getFlatComponent(IVirtualComponent component) {
		FlatComponentTaskModel options = new FlatComponentTaskModel();
		options.put(FlatVirtualComponent.PARTICIPANT_LIST, getParticipants());
		return new ConnectorExportComponent(component, options);
	}
	
	protected IFlattenParticipant[] getParticipants() {
		return new IFlattenParticipant[]{
			createHierarchyParticipant(),
			new AddClasspathLibReferencesParticipant(),
			new AddClasspathFoldersParticipant()
		};
	}
	
	protected IFlattenParticipant createHierarchyParticipant() {
		return new AbstractFlattenParticipant() {
			@Override
			public boolean isChildModule(IVirtualComponent rootComponent, IVirtualReference reference, FlatComponentTaskModel dataModel) {
				if (!reference.getReferencedComponent().isBinary()) {
					return true;
				}
				return false;
			}
		};
	}

	private boolean isExportSource() {
		return isExportSource;
	}
	
	@Override
	public boolean createManifest() {
		return false;
	}

	public class ConnectorExportComponent extends FlatVirtualComponent {

		public ConnectorExportComponent(IVirtualComponent component, FlatComponentTaskModel dataModel) {
			super(component, dataModel);
		}
		
		@Override
		protected boolean canOptimize() {
			return true;
		}

		@Override
		protected void optimize(List<IFlatResource> resources, List<IChildModuleReference> children) {
			if (getComponent() != null) {
				VirtualComponentFlattenUtility util = new VirtualComponentFlattenUtility(resources, this);
				try {
					addConsumedReferences(util, getComponent(), new Path("")); 	//$NON-NLS-1$
					addUsedReferences(util, getComponent(), new Path("")); 		//$NON-NLS-1$
				} catch (CoreException e) {
					org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
				}
			}
		}
	}
}
