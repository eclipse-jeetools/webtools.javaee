/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.componentcore.internal.resources.VirtualArchiveComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class WebComponentArchiveLoadAdapter extends ComponentArchiveLoadAdapter {

	public static IPath WEBLIB = new Path("/WEB-INF/lib"); //$NON-NLS-1$
	
	public WebComponentArchiveLoadAdapter(IVirtualComponent vComponent) {
		super(vComponent);
	}
	
	public WebComponentArchiveLoadAdapter(IVirtualComponent vComponent, boolean includeClasspathComponents) {
		super(vComponent, includeClasspathComponents);
	}

	public List <IArchiveResource> getArchiveResources() {
		super.getArchiveResources();
		addLooseLibJARsToFiles();
		return filesHolder.getFiles();
	}

	public IVirtualReference[] getLibModules() {
		List <IVirtualReference> result = new ArrayList<IVirtualReference>();
		IVirtualReference[] refComponents = null;
		if (!vComponent.isBinary())
			refComponents = ((J2EEModuleVirtualComponent)vComponent).getNonManifestReferences();
		else
			refComponents = vComponent.getReferences();
		// Check the deployed path to make sure it has a lib parent folder and matchs the web.xml
		// base path
		for (int i = 0; i < refComponents.length; i++) {
			if (refComponents[i].getRuntimePath().equals(WEBLIB))
				result.add(refComponents[i]);
		}

		return (IVirtualReference[]) result.toArray(new IVirtualReference[result.size()]);

	}

	public void addLooseLibJARsToFiles() {
		IVirtualReference[] libModules = getLibModules();
		for (int i = 0; i < libModules.length; i++) {
			IVirtualReference iLibModule = libModules[i];
			IVirtualComponent looseComponent = iLibModule.getReferencedComponent();
			if (looseComponent.isBinary()) {
				VirtualArchiveComponent archiveComp = (VirtualArchiveComponent) looseComponent;
				java.io.File diskFile = archiveComp.getUnderlyingDiskFile();
				if (!diskFile.exists()) {
					IFile wbFile = archiveComp.getUnderlyingWorkbenchFile();
					diskFile = new File(wbFile.getLocation().toOSString());
				}
				IPath uri = iLibModule.getRuntimePath().makeRelative().append("/" + diskFile.getName()); //$NON-NLS-1$
				addExternalFile(uri, diskFile);				
			} else {
				String name = null;
				String archiveName = iLibModule.getArchiveName();
				if (archiveName != null && archiveName.length() > 0) {
					name = archiveName;
				} else {
					name = looseComponent.getName() + ".jar"; //$NON-NLS-1$
				}
				String prefix = iLibModule.getRuntimePath().makeRelative().toString();
				if (prefix.length() > 0 && prefix.charAt(prefix.length() - 1)!= '/') {
					prefix += "/"; //$NON-NLS-1$
				}

				addClasspathComponentDependencies(looseComponent);
				String uri = prefix + name;
				
				try {
					JavaComponentArchiveLoadAdapter archiveLoadAdapter = new JavaComponentArchiveLoadAdapter(looseComponent);
					archiveLoadAdapter.setExportSource(isExportSource());
					ArchiveOptions webLibOptions = new ArchiveOptions();
					webLibOptions.setOption(ArchiveOptions.LOAD_ADAPTER, archiveLoadAdapter);
					IArchive webLibArchive;
					webLibArchive = JavaEEArchiveUtilities.INSTANCE.openArchive(webLibOptions);
					webLibArchive.setPath(new Path(uri));
					webLibArchive.setArchive(archive);
					filesHolder.addFile(webLibArchive);
				} catch (ArchiveOpenFailureException e) {
					String message = ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.UNABLE_TO_LOAD_MODULE_ERROR_, new Object[]{uri, getComponent().getProject().getName(), e.getMessage()});
					org.eclipse.jem.util.logger.proxy.Logger.getLogger().logTrace(message);
				}
			}
		}
	}
	
	private void addClasspathComponentDependencies(final IVirtualComponent referencedComponent) {
		// retrieve all Java classpath component dependencies
		if (includeClasspathComponents && referencedComponent instanceof J2EEModuleVirtualComponent) {
			final IVirtualReference[] cpRefs = ((J2EEModuleVirtualComponent) referencedComponent).getJavaClasspathReferences();
			for (int j = 0; j < cpRefs.length; j++) {
				final IVirtualReference ref = cpRefs[j];
				final IPath runtimePath = ref.getRuntimePath();
				
				// only process ../ mappings
				if (ref.getReferencedComponent() instanceof VirtualArchiveComponent
						&& runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
					final VirtualArchiveComponent comp = (VirtualArchiveComponent) ref.getReferencedComponent();
					File cpEntryFile = comp.getUnderlyingDiskFile();
					if (!cpEntryFile.exists()) {
						final IFile wbFile = comp.getUnderlyingWorkbenchFile();
						cpEntryFile = new File(wbFile.getLocation().toOSString());
					}
					addExternalFile(new Path("WEB-INF/lib/" + ref.getArchiveName()), cpEntryFile);
				}
			}
		}
	}

}
