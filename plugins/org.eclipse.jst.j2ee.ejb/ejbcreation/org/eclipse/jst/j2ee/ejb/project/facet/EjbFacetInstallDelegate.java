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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.common.project.facet.core.ClasspathHelper;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationOp;
import org.eclipse.jst.j2ee.application.internal.operations.IAddComponentToEnterpriseApplicationDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBJarImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetInstallDelegate;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class EjbFacetInstallDelegate extends J2EEFacetInstallDelegate implements IDelegate {

	public void execute(IProject project, IProjectFacetVersion fv, Object config, IProgressMonitor monitor) throws CoreException {

		if (monitor != null) {
			monitor.beginTask("", 1);
		}

		try {
			IDataModel model = (IDataModel) config;

			final IJavaProject jproj = JavaCore.create(project);

			// Add WTP natures.

			WtpUtils.addNatures(project);

			// Create the directory structure.

			final IWorkspace ws = ResourcesPlugin.getWorkspace();
			final IPath pjpath = project.getFullPath();

			// Setup the flexible project structure.

			final IVirtualComponent c = ComponentCore.createComponent(project);

			c.create(0, null);

			c.setMetaProperty("java-output-path", "/build/classes/");


			final IVirtualFolder ejbroot = c.getRootFolder();

			final IClasspathEntry[] cp = jproj.getRawClasspath();

			for (int i = 0; i < cp.length; i++) {
				final IClasspathEntry cpe = cp[i];

				if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					ejbroot.createLink(cpe.getPath().removeFirstSegments(1), 0, null);
				}
			}

			IFolder ejbFolder = null;
			String configFolder = null;

			configFolder = model.getStringProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER);
			ejbroot.createLink(new Path("/" + configFolder), 0, null);

			String ejbFolderName = model.getStringProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER);
			IPath ejbFolderpath = pjpath.append(ejbFolderName);

			ejbFolder = ws.getRoot().getFolder(ejbFolderpath);


			if (!ejbFolder.getFile(J2EEConstants.EJBJAR_DD_URI).exists()) {
				String ver = model.getStringProperty(IFacetDataModelProperties.FACET_VERSION_STR);
				int nVer = J2EEVersionUtil.convertVersionStringToInt(ver);
				EJBArtifactEdit.createDeploymentDescriptor(project, nVer);
			}

			try {
				createManifest(project, ejbFolder, monitor);
			} catch (InvocationTargetException e) {
				Logger.getLogger().logError(e);
			} catch (InterruptedException e) {
				Logger.getLogger().logError(e);
			}

			// Setup the classpath.
			ClasspathHelper.removeClasspathEntries(project, fv);

			if (!ClasspathHelper.addClasspathEntries(project, fv)) {
				// TODO: Support the no runtime case.
				// ClasspathHelper.addClasspathEntries( project, fv, <something> );
			}

			// Associate with an EAR, if necessary.

			final String earProjectName = (String) model.getProperty(IEjbFacetInstallDataModelProperties.EAR_PROJECT_NAME);

			// Associate with an EAR, if necessary.
			if (model.getBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR)) {
				if (earProjectName != null && !earProjectName.equals("")) { //$NON-NLS-1$

					String ver = fv.getVersionString();
					String j2eeVersionText = J2EEVersionUtil.convertVersionIntToString
					(J2EEVersionUtil.convertEJBVersionStringToJ2EEVersionID(ver));
					
					IFacetedProject facetedProject = ProjectFacetsManager.create(project);
					installEARFacet(j2eeVersionText, earProjectName, facetedProject.getRuntime(), monitor);

					IProject earProject = ProjectUtilities.getProject(earProjectName);
					IVirtualComponent earComp = ComponentCore.createComponent(earProject);

					final IDataModel dataModel = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider() {
						public Object getDefaultProperty(String propertyName) {
							if (IAddComponentToEnterpriseApplicationDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP.equals(propertyName)) {
								Map map = new HashMap();
								List components = (List) getProperty(TARGET_COMPONENT_LIST);
								for (int i = 0; i < components.size(); i++) {
									IVirtualComponent component = (IVirtualComponent) components.get(i);
									String name = component.getName();
									name += ".jar"; //$NON-NLS-1$
									map.put(component, name);
								}
								return map;
							}
							return super.getDefaultProperty(propertyName);
						}

						public IDataModelOperation getDefaultOperation() {
							return new AddComponentToEnterpriseApplicationOp(model) {
								protected Module createNewModule(IVirtualComponent wc) {
									return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createEjbModule();
								}
							};
						}
					});

					dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp);
					List modList = (List) dataModel.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
					modList.add(c);
					dataModel.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
					try {
						dataModel.getDefaultOperation().execute(null, null);
					} catch (ExecutionException e) {
						Logger.getLogger().logError(e);
					}

				}


				// Create the Ejb Client View
				final boolean createClient = model.getBooleanProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT);
				String clientProjectName = (String) model.getProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME);
				if (createClient && clientProjectName != null && clientProjectName != "") {
					IProject ejbClientProject = ProjectUtilities.getProject(clientProjectName);
					if (ejbClientProject.exists())
						return;

					c.setMetaProperty(CreationConstants.EJB_CLIENT_NAME, clientProjectName);

					String clientURI = model.getStringProperty(IEjbFacetInstallDataModelProperties.CLIENT_URI);
					c.setMetaProperty(CreationConstants.CLIENT_JAR_URI, clientURI);


					org.eclipse.wst.common.project.facet.core.runtime.IRuntime rt = (IRuntime) model.getProperty(IEjbFacetInstallDataModelProperties.FACET_RUNTIME);
					try {

						IDataModel dm = DataModelFactory.createDataModel(new EjbClientProjectCreationDataModelProvider());

						dm.setStringProperty(EjbClientProjectCreationDataModelProvider.PROJECT_NAME, clientProjectName);

						dm.setStringProperty(EjbClientProjectCreationDataModelProvider.EJB_PROJECT_NAME, model.getStringProperty(IFacetDataModelProperties.FACET_PROJECT_NAME));

						dm.setStringProperty(EjbClientProjectCreationDataModelProvider.EAR_PROJECT_NAME, earProjectName);

						dm.setStringProperty(EjbClientProjectCreationDataModelProvider.SOURCE_FOLDER, (String) model.getProperty(IEjbFacetInstallDataModelProperties.CLIENT_SOURCE_FOLDER));

						dm.setProperty(EjbClientProjectCreationDataModelProvider.RUNTIME, rt);


						dm.getDefaultOperation().execute(monitor, null);


					} catch (Exception e) {
						Logger.getLogger().logError(e);
					}

					if (createClient && clientProjectName != null && clientProjectName != "") {
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

			try {
				((IDataModelOperation) model.getProperty(FacetDataModelProvider.NOTIFICATION_OPERATION)).execute(monitor, null);
			} catch (ExecutionException e) {
				Logger.getLogger().logError(e);
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

		final String earProjectName = (String) model.getProperty(IEjbFacetInstallDataModelProperties.EAR_PROJECT_NAME);
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
			IDataModel dataModel = DataModelFactory.createDataModel(new JARDependencyDataModelProvider());
			dataModel.setProperty(JARDependencyDataModelProperties.PROJECT_NAME, ejbprojectName);
			dataModel.setProperty(JARDependencyDataModelProperties.REFERENCED_PROJECT_NAME, clientProjectName);
			dataModel.setIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE, JARDependencyDataModelProperties.JAR_MANIPULATION_ADD);
			try {
				dataModel.getDefaultOperation().execute(aMonitor, null);
			} catch (Exception e) {
				Logger.getLogger().logError(e);
			}
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
				if (clienturi != null && !clienturi.equals("")) {
					ejbres.setEjbClientJar(clienturi);//$NON-NLS-1$
				} else
					ejbres.setEjbClientJar(clientProjectName + ".jar");//$NON-NLS-1$
				ejbres.setEjbClientJar(clienturi);//$NON-NLS-1$
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
