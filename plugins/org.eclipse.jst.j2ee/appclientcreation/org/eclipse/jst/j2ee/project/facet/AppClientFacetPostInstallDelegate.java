/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import static org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties.FACETED_PROJECT_WORKING_COPY;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModelProvider;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.operation.IArtifactEditOperationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class AppClientFacetPostInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	@Override
	public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor) throws CoreException {
		if (monitor != null)
			monitor.beginTask("", 1); //$NON-NLS-1$
		try {
			IDataModel model = (IDataModel) config;
			
			// Add main class if necessary
			if (model.getBooleanProperty(IAppClientFacetInstallDataModelProperties.CREATE_DEFAULT_MAIN_CLASS)){
				addMainClass(monitor, model, project);			
			} else {
				createManifestEntryForMainClass(monitor, model, project);
			}

			// Associate with an EAR, if necessary.
			final String earProjectName = (String) model.getProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME);
			if (model.getBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR)) {
				if (earProjectName != null && !earProjectName.equals("")) { //$NON-NLS-1$
					String ver = fv.getVersionString();
					
					String j2eeVersionText = ver;
					installEARFacet(j2eeVersionText, earProjectName, (IRuntime) model.getProperty(IJ2EEFacetInstallDataModelProperties.FACET_RUNTIME), monitor);
					final String moduleURI = model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.MODULE_URI);
					
                    final IFacetedProjectWorkingCopy fpjwc
                        = (IFacetedProjectWorkingCopy) model.getProperty( FACETED_PROJECT_WORKING_COPY );
					
					installAndAddModuletoEAR( j2eeVersionText,
								earProjectName,
								fpjwc.getPrimaryRuntime(),
								project,
								moduleURI,
								monitor );

				}
			}

			if (monitor != null)
				monitor.worked(1);
		} catch (Exception e) {
			J2EEPlugin.logError(e);
		} finally {
			if (monitor != null)
				monitor.done();
		}
	}
	
	private void addMainClass(IProgressMonitor monitor, IDataModel model, IProject project) {
		try {
			IDataModel mainClassDataModel = DataModelFactory.createDataModel(NewJavaClassDataModelProvider.class);
			mainClassDataModel.setProperty(IArtifactEditOperationDataModelProperties.PROJECT_NAME, project.getName());
			mainClassDataModel.setProperty(INewJavaClassDataModelProperties.CLASS_NAME, "Main"); //$NON-NLS-1$
			mainClassDataModel.setBooleanProperty(INewJavaClassDataModelProperties.MAIN_METHOD, true);

			IContainer container = J2EEProjectUtilities.getSourceFolderOrFirst(project, null);
			if( container != null ){
				String projRelativeSourcePath = container.getFullPath().toPortableString();
				mainClassDataModel.setProperty(INewJavaClassDataModelProperties.SOURCE_FOLDER, projRelativeSourcePath);
				mainClassDataModel.getDefaultOperation().execute(monitor, null);
				createManifestEntryForMainClass(monitor, model, project);
			}
		} catch (Exception e) {
			J2EEPlugin.logError(e);

		}
	}
	
	protected void createManifestEntryForMainClass(IProgressMonitor monitor, IDataModel model, IProject project) throws CoreException, InvocationTargetException, InterruptedException {
		IVirtualComponent appClientComponent = ComponentCore.createComponent(project);
		IVirtualFile vf = appClientComponent.getRootFolder().getFile(new Path(J2EEConstants.MANIFEST_URI));
		IFile manifestmf = vf.getUnderlyingFile();
		if (manifestmf == null || !manifestmf.exists()) {
			try {
				createManifest(project, appClientComponent.getRootFolder().getUnderlyingFolder(), monitor);
			} catch (Exception e) {
				J2EEPlugin.logError(e);
			}
		}
		if (model.getBooleanProperty(IAppClientFacetInstallDataModelProperties.CREATE_DEFAULT_MAIN_CLASS)) {
			String manifestFolder = IPath.SEPARATOR + model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER) + IPath.SEPARATOR + J2EEConstants.META_INF;
			IContainer container = project.getFolder(manifestFolder);
			manifestmf = container.getFile(new Path(J2EEConstants.MANIFEST_SHORT_NAME));
			IDataModel dm = DataModelFactory.createDataModel(UpdateManifestDataModelProvider.class);
			dm.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, project.getName());
			dm.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
			dm.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
			dm.setProperty(UpdateManifestDataModelProperties.MAIN_CLASS, "Main"); //$NON-NLS-1$
			try {
				dm.getDefaultOperation().execute(monitor, null);
			} catch (Exception e) {
				// Ignore
			}
		}
	}	
}
