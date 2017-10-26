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
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.io.File;
import java.util.List;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jst.j2ee.classpathdep.IClasspathDependencyConstants;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.archive.operations.ComponentLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.project.WebUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class WebComponentLoadStrategyImpl extends ComponentLoadStrategyImpl {

	public WebComponentLoadStrategyImpl(IVirtualComponent vComponent) {
		super(vComponent);
	}
	
	public WebComponentLoadStrategyImpl(IVirtualComponent vComponent, boolean includeClasspathComponents) {
		super(vComponent, includeClasspathComponents);
	}

	@Override
	public List getFiles() {
		super.getFiles();
		addLooseLibJARsToFiles();
		if (includeClasspathComponents) {
			addMappedClassFolders(IClasspathDependencyConstants.WEB_INF_CLASSES_PATH);
		}
		return filesHolder.getFiles();
	}

	public IVirtualReference[] getLibModules() {
		return WebUtilities.getLibModules(getComponent());
	}

	public void addLooseLibJARsToFiles() {
		IVirtualReference[] libModules = getLibModules();
		for (int i = 0; i < libModules.length; i++) {
			IVirtualReference iLibModule = libModules[i];
			IVirtualComponent looseComponent = iLibModule.getReferencedComponent();
			if (looseComponent.isBinary()) {
				java.io.File diskFile = looseComponent.getAdapter(java.io.File.class);
				String uri = iLibModule.getRuntimePath().makeRelative().toString() + "/" + diskFile.getName(); //$NON-NLS-1$
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
					Archive utilJAR = J2EEProjectUtilities.asArchive(uri, looseComponent.getProject(), isExportSource());
					if (utilJAR == null)
						continue;
					filesHolder.addFile(utilJAR);
				} catch (OpenFailureException oe) {
					String message = ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.UNABLE_TO_LOAD_MODULE_ERROR_, new Object[]{uri, getComponent().getProject().getName(), oe.getConcatenatedMessages()}); 
					WebPlugin.logError(message);
				}
			}
		}
	}
	@Override
	protected synchronized ArtifactEdit getArtifactEditForRead() {
		if(artifactEdit == null){
			artifactEdit = WebArtifactEdit.getWebArtifactEditForRead(vComponent);
		}
		return artifactEdit;
	}
	
	private void addClasspathComponentDependencies(final IVirtualComponent referencedComponent) {
		// retrieve all Java classpath component dependencies
		if (includeClasspathComponents && referencedComponent instanceof J2EEModuleVirtualComponent) {
			final IVirtualReference[] cpRefs = ((J2EEModuleVirtualComponent) referencedComponent).getJavaClasspathReferences();
			for (int j = 0; j < cpRefs.length; j++) {
				final IVirtualReference ref = cpRefs[j];
				final IPath runtimePath = ref.getRuntimePath();
				
				// only process ../ mappings
				if (ref.getReferencedComponent().isBinary()
						&& runtimePath.equals(IClasspathDependencyConstants.RUNTIME_MAPPING_INTO_CONTAINER_PATH)) {
					File cpEntryFile = ref.getReferencedComponent().getAdapter(java.io.File.class);
					addExternalFile("WEB-INF/lib/" + ref.getArchiveName(), cpEntryFile); //$NON-NLS-1$
				}
			}
		}
	}

}
