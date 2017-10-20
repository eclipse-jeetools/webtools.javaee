/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.IClasspathDependencyComponent;
import org.eclipse.jst.common.internal.modulecore.IClasspathDependencyProvider;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.jee.archive.ArchiveOpenFailureException;
import org.eclipse.jst.jee.archive.ArchiveOptions;
import org.eclipse.jst.jee.archive.IArchive;
import org.eclipse.jst.jee.archive.IArchiveResource;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class WebComponentArchiveLoadAdapter extends ComponentArchiveLoadAdapter {

	public static IPath WEBLIB = new Path("/WEB-INF/lib"); //$NON-NLS-1$
	
	private Map<IPath, IResource> pathsToWorkbenchLibJars = new HashMap<IPath, IResource>();
	
	public WebComponentArchiveLoadAdapter(IVirtualComponent vComponent) {
		super(vComponent);
	}
	
	public WebComponentArchiveLoadAdapter(IVirtualComponent vComponent, boolean includeClasspathComponents) {
		super(vComponent, includeClasspathComponents);
	}

	@Override
	public List <IArchiveResource> getArchiveResources() {
		super.getArchiveResources();
		addLooseLibJARsToFiles();
		if (includeClasspathComponents) {
			addMappedClassFolders(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_COMPONENT_PATH);		
		}
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

		return result.toArray(new IVirtualReference[result.size()]);

	}

	public void addLooseLibJARsToFiles() {
		IVirtualReference[] libModules = getLibModules();
		for (int i = 0; i < libModules.length; i++) {
			IVirtualReference iLibModule = libModules[i];
			IVirtualComponent looseComponent = iLibModule.getReferencedComponent();
			if (looseComponent.isBinary()) {
				java.io.File diskFile = looseComponent.getAdapter(java.io.File.class);
				IPath uri = iLibModule.getRuntimePath().makeRelative().append("/" + diskFile.getName()); //$NON-NLS-1$
				addExternalFile(uri, diskFile);				
			} else {
				String name = null;
				String archiveName = iLibModule.getArchiveName();
				if (archiveName != null && archiveName.length() > 0) {
					name = archiveName;
				} else {
					name = looseComponent.getName() + IJ2EEModuleConstants.JAR_EXT;
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
					//save jar file since it is later removed from filesHolder
					addFile(webLibArchive.getPath());
					//this is in case there is a jar in the WEB-INF/lib folder which is also
					//mapped as a project.  If it is not removed here, then export will include
					//the jar version instead of the project version.
					filesHolder.remove(webLibArchive.getPath());
					filesHolder.addFile(webLibArchive);
				} catch (ArchiveOpenFailureException e) {
					String message = ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.UNABLE_TO_LOAD_MODULE_ERROR_, new Object[]{uri, getComponent().getProject().getName(), e.getMessage()});
					J2EEPlugin.logError(message);
				}
			}
		}
	}

	private void addFile(IPath deployPath) {
		IResource weblibJar = getWorkbenchResources(deployPath);
		if (weblibJar != null) {
			pathsToWorkbenchLibJars.put(deployPath, weblibJar);
		}
	}
	
	public IResource getWorkbenchLibJars(IPath deployPath) {
		return pathsToWorkbenchLibJars.get(deployPath);
	}
	
	private void addClasspathComponentDependencies(final IVirtualComponent referencedComponent) {
		// retrieve all Java classpath component dependencies
		if (includeClasspathComponents && referencedComponent instanceof IClasspathDependencyProvider) {
			final IVirtualReference[] cpRefs = ((IClasspathDependencyProvider) referencedComponent).getJavaClasspathReferences();
			for (int j = 0; j < cpRefs.length; j++) {
				final IVirtualReference ref = cpRefs[j];
				final IPath runtimePath = ref.getRuntimePath();
				
				// only process ../ mappings
				if (ref.getReferencedComponent() instanceof IClasspathDependencyComponent
						&& runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
					File cpEntryFile = ref.getReferencedComponent().getAdapter(java.io.File.class);
					addExternalFile(new Path("WEB-INF/lib/" + ref.getArchiveName()), cpEntryFile); //$NON-NLS-1$
				}
			}
		}
	}
	
	@Override
	protected IPath getDefaultModelObjectPath() {
		return new Path(J2EEConstants.WEBAPP_DD_URI);
	}
}
