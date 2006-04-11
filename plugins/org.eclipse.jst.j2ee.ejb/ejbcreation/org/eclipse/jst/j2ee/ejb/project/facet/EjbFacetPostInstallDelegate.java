/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.project.facet;



import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBJarImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.UpdateProjectClasspath;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.IEjbClientProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJavaUtilityProjectCreationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class EjbFacetPostInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor) throws CoreException {

		if (monitor != null) {
			monitor.beginTask("", 1); //$NON-NLS-1$
		}

		try {
			IDataModel model = (IDataModel) config;

			final IVirtualComponent c = ComponentCore.createComponent(project);
			
			final String earProjectName = (String) model.getProperty(
						IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME);

			// Associate with an EAR, if necessary.
			if (model.getBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR)) {
				if (earProjectName != null && !earProjectName.equals("")) { //$NON-NLS-1$

					String ver = fv.getVersionString();
					String j2eeVersionText = J2EEVersionUtil.convertVersionIntToString
					(J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(ver));
					
					final String moduleURI = model.getStringProperty(
								IJ2EEModuleFacetInstallDataModelProperties.MODULE_URI);

					installAndAddModuletoEAR( j2eeVersionText,
										earProjectName,
										(IRuntime) model.getProperty(IJ2EEFacetInstallDataModelProperties.FACET_RUNTIME),
										project,
										moduleURI,
										monitor );
			
				}


				// Create the Ejb Client View
				final boolean createClient = model.getBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT);
				String clientProjectName = (String) model.getProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME);
				if (createClient && clientProjectName != null && clientProjectName != "") { //$NON-NLS-1$
					IProject ejbClientProject = ProjectUtilities.getProject(clientProjectName);
					if (ejbClientProject.exists())
						return;

					c.setMetaProperty(CreationConstants.EJB_CLIENT_NAME, clientProjectName);

					String clientURI = model.getStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_URI);
					c.setMetaProperty(CreationConstants.CLIENT_JAR_URI, clientURI);


					org.eclipse.wst.common.project.facet.core.runtime.IRuntime rt = (IRuntime) model.getProperty(IJ2EEFacetInstallDataModelProperties.FACET_RUNTIME);
					try {

						IDataModel dm = DataModelFactory.createDataModel(new EjbClientProjectCreationDataModelProvider());

						dm.setStringProperty(IJavaUtilityProjectCreationDataModelProperties.PROJECT_NAME, clientProjectName);
						dm.setStringProperty(IEjbClientProjectCreationDataModelProperties.EJB_PROJECT_NAME, model.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME));
						dm.setStringProperty(IJavaUtilityProjectCreationDataModelProperties.EAR_PROJECT_NAME, earProjectName);
						dm.setStringProperty(IJavaUtilityProjectCreationDataModelProperties.SOURCE_FOLDER, (String) model.getProperty(IEjbFacetInstallDataModelProperties.CLIENT_SOURCE_FOLDER));
						dm.setProperty(IJavaUtilityProjectCreationDataModelProperties.RUNTIME, rt);

						dm.getDefaultOperation().execute(monitor, null);


					} catch (Exception e) {
						Logger.getLogger().logError(e);
					}

					if (createClient && clientProjectName != null && clientProjectName != "") { //$NON-NLS-1$
						try {
							if (model.getBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR))
								runAddClientToEAROperation(model, monitor);
							runAddClientToEJBOperation(model, monitor);
							modifyEJBModuleJarDependency(model, monitor);
							updateEJBDD(model, monitor);
						} catch (CoreException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}


			}

			if (monitor != null) {
				monitor.worked(1);
			}

		}

		finally {
			if (monitor != null) {
				monitor.done();
			}
		}
	}

	protected void runAddClientToEAROperation(IDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		final String earProjectName = (String) model.getProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME);
		IProject earproject = ProjectUtilities.getProject(earProjectName);

		IVirtualComponent earComp = ComponentCore.createComponent(earproject);


		String clientProjectName = model.getStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME);

		IProject clientProject = ProjectUtilities.getProject(clientProjectName);
		IVirtualComponent component = ComponentCore.createComponent(clientProject);

		if (earComp.exists() && component.exists()) {
			IDataModel dm = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp);

			List modList = (List) dm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			modList.add(component);
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
			
			String clientURI = model.getStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_URI);
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_ARCHIVE_NAME, clientURI);
			
			try {
				dm.getDefaultOperation().execute(monitor, null);
			} catch (ExecutionException e) {
				Logger.getLogger().log(e);
			}
		}
	}


	protected void runAddClientToEJBOperation(IDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		String ejbprojectName = model.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME);
		IProject ejbProj = ProjectUtilities.getProject(ejbprojectName);
		IVirtualComponent ejbcomponent = ComponentCore.createComponent(ejbProj);


		String clientProjectName = model.getStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME);
		IProject clientProject = ProjectUtilities.getProject(clientProjectName);
		IVirtualComponent ejbclientcomponent = ComponentCore.createComponent(clientProject);

		IDataModel dm = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, ejbcomponent);

		List modList = (List) dm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
		modList.add(ejbclientcomponent);
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
		try {
			dm.getDefaultOperation().execute(monitor, null);
		} catch (ExecutionException e) {
			Logger.getLogger().log(e);
		}

	}

	private void modifyEJBModuleJarDependency(IDataModel model, IProgressMonitor aMonitor) throws InvocationTargetException, InterruptedException {
		String ejbprojectName = model.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME);
		IProject ejbProj = ProjectUtilities.getProject(ejbprojectName);
		IVirtualComponent ejbComponent = ComponentCore.createComponent(ejbProj);
		IVirtualFile vf = ejbComponent.getRootFolder().getFile(new Path(J2EEConstants.MANIFEST_URI));
		IFile manifestmf = vf.getUnderlyingFile();
		if (manifestmf == null || !manifestmf.exists()) {
			try {
				createManifest(ejbProj, ejbComponent.getRootFolder().getUnderlyingFolder(), aMonitor);
			} catch (Exception e) {
				Logger.getLogger().logError(e);
			}
			String manifestFolder = IPath.SEPARATOR + model.getStringProperty(IJ2EEModuleFacetInstallDataModelProperties.CONFIG_FOLDER) + IPath.SEPARATOR + J2EEConstants.META_INF;
			IContainer container = ejbProj.getFolder(manifestFolder);
			manifestmf = container.getFile(new Path(J2EEConstants.MANIFEST_SHORT_NAME));
		}

		String clientProjectName = model.getStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME);

		IDataModel updateManifestDataModel = DataModelFactory.createDataModel(UpdateManifestDataModelProvider.class);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, ejbprojectName);
		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, UpdateManifestDataModelProvider.convertClasspathStringToList(clientProjectName + ".jar"));//$NON-NLS-1$


		try {
			updateManifestDataModel.getDefaultOperation().execute(aMonitor, null);
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		}

		if (!clientProjectName.equals(ejbprojectName)) {
			UpdateProjectClasspath.updateProjectDependency( ejbprojectName, clientProjectName, true );			
		}
	}


	private void updateEJBDD(IDataModel model, IProgressMonitor monitor) {

		String ejbprojectName = model.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME);
		IProject ejbProj = ProjectUtilities.getProject(ejbprojectName);


		String clientProjectName = model.getStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME);

		IVirtualComponent c = ComponentCore.createComponent(ejbProj);
		Properties props = c.getMetaProperties();

		String clienturi = props.getProperty(CreationConstants.CLIENT_JAR_URI);

		EJBArtifactEdit ejbEdit = null;
		try {
			ejbEdit = new EJBArtifactEdit(ejbProj, false, true);

			if (ejbEdit != null) {
				EJBJarImpl ejbres = (EJBJarImpl) ejbEdit.getDeploymentDescriptorRoot();
				if (clienturi != null && !clienturi.equals("")) { //$NON-NLS-1$
					ejbres.setEjbClientJar(clienturi);
				} else
					ejbres.setEjbClientJar(clientProjectName + ".jar");//$NON-NLS-1$
				ejbres.setEjbClientJar(clienturi);
				ejbEdit.saveIfNecessary(monitor);
			}
		} catch (Exception e) {
			Logger.getLogger().logError(e);
		} finally {
			if (ejbEdit != null)
				ejbEdit.dispose();
		}
	}	
}
