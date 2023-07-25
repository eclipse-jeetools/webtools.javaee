/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ProjectScope;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathContainerUtils;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathUpdater;
import org.eclipse.jst.j2ee.internal.earcreation.EarFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.IEjbFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.jca.project.facet.ConnectorFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.AppClientFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IAppClientFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.UtilityFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.web.project.facet.WebFacetInstallDataModelProvider;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.INavigatorContentService;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.ComponentType;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.IComponentProjectMigrator;
import org.eclipse.wst.common.componentcore.internal.Property;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.SimpleValidateEdit;
import org.eclipse.wst.project.facet.SimpleWebFacetInstallDataModelProvider;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;

/**
 * This has been deprecated since WTP 3.1.2 and will be deleted post WTP 3.2.
 * See https://bugs.eclipse.org/bugs/show_bug.cgi?id=292934
 * @deprecated 
 * @author jsholl
 */
public class J2EEComponentProjectMigrator implements IComponentProjectMigrator {

	private static final String WEB_LIB_CONTAINER = "org.eclipse.jst.j2ee.internal.web.container"; //$NON-NLS-1$
	private static final String WEB_LIB_PATH = "/WEB-INF/lib";  //$NON-NLS-1$
	private static final String OLD_DEPLOYABLES_PATH = ".deployables"; //$NON-NLS-1$
	private IProject project;
	
	private static final String[] J2EE_CONTENT_EXTENSION_IDS = new String[] {
		"org.eclipse.jst.navigator.j2ee.ui.EARDDContent", //$NON-NLS-1$
		"org.eclipse.jst.navigator.j2ee.ui.WebDDContent", //$NON-NLS-1$
		"org.eclipse.jst.navigator.j2ee.ui.EJBDDContent", //$NON-NLS-1$
		"org.eclipse.jst.navigator.j2ee.ui.ConnectorDDContent" //$NON-NLS-1$
	};
	
	private static final String PROJECT_EXPLORER = "org.eclipse.ui.navigator.ProjectExplorer"; //$NON-NLS-1$
	
	public J2EEComponentProjectMigrator() {
		super();
	}

	@Override
	public void migrateProject(IProject aProject) {
		if (aProject.isAccessible() && (aProject.getFile(StructureEdit.MODULE_META_FILE_NAME).exists())) {
			// The file corresponding to StructureEdit.MODULE_META_FILE_NAME is crucial to migration.
			// If it does not exist, the project cannot be migrated. We should never fail the test for existence
			// of the file, if we do then something has gone badly wrong.
			Resource resource = WorkbenchResourceHelperBase.getResource(aProject.getFile(StructureEdit.MODULE_META_FILE_NAME), false);
			if(resource != null && resource.isLoaded()){
				// Unload the resource because the model inside the StructureEdit was cached when the 
				// the project was imported, and files may have moved due to migration (.wtpmodules for example).
				resource.unload();
			}
		
			final List files = new ArrayList();
			files.add(aProject.getFile(J2EEProjectUtilities.DOT_PROJECT));
			files.add(aProject.getFile(J2EEProjectUtilities.DOT_CLASSPATH));
			files.add(aProject.getFile(StructureEdit.MODULE_META_FILE_NAME));
			if(SimpleValidateEdit.validateEdit(files)){
				project = aProject;
				
				removeComponentBuilders(project);
				if (multipleComponentsDetected())
					createNewProjects();
				String facetid = getFacetFromProject(project);
				if (facetid.length() == 0)
					addFacets(project);
				J2EEComponentClasspathUpdater.getInstance().queueUpdate(project);
			}
		}
		ensureJ2EEContentExtensionsEnabled();
	}
	
	/**
	 * Ensure the J2EE content extension ids are enabled on the project explorer 
	 * for the projects being migrated.
	 */
	private void ensureJ2EEContentExtensionsEnabled() {
		IViewPart view = null;
		try {
			view = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getActivePage().findView(PROJECT_EXPLORER);
		} catch (Exception e) { 
			//Just bail and return if there is no view
		}
		if (view == null)
			return;
		
		INavigatorContentService contentService =  view.getAdapter(INavigatorContentService.class);
		CommonViewer viewer = view.getAdapter(CommonViewer.class);
		
		// Set the J2EE content extensions as enabled now that we have set the J2EE facets
		if (contentService != null)
			contentService.getActivationService().activateExtensions(J2EE_CONTENT_EXTENSION_IDS, false);
		
		// Update the viewer if we are in the current UI thread
		if (viewer != null) {
			Display display = viewer.getControl().getDisplay();
			if (display!=null && Thread.currentThread().equals(display.getThread()))
				viewer.refresh();
		}
	}

		private void createNewProjects() {

			StructureEdit se = null;
			try {
				se = StructureEdit.getStructureEditForWrite(project);
				List comps = se.getComponentModelRoot().getComponents();
				List removedComps = new ArrayList();
				for (int i = 1;i<comps.size();i++) {
					WorkbenchComponent comp = (WorkbenchComponent) comps.get(i);
					IWorkspace ws = ResourcesPlugin.getWorkspace();
					IProject newProj = ws.getRoot().getProject(comp.getName());
					if (!newProj.exists()) {
						try {
							createProj(newProj,(!comp.getComponentType().getComponentTypeId().equals(J2EEProjectUtilities.ENTERPRISE_APPLICATION)));
							WtpUtils.addNatures(newProj);
						} catch (CoreException e) {
							J2EEUIPlugin.logError(e);
						}
					}
					if (comp.getComponentType()!=null)
						addFacetsToProject(newProj,comp.getComponentType().getComponentTypeId(),comp.getComponentType().getVersion(),false);
					removedComps.add(comp);
					IFolder compFolder = project.getFolder(comp.getName());
					if (compFolder.exists())
						try {
							compFolder.delete(true,null);
						} catch (CoreException e) {
							J2EEUIPlugin.logError(e);
						}
				}
				se.getComponentModelRoot().getComponents().removeAll(removedComps);
				se.save(null);
			
			} finally {
				if (se != null)
					se.dispose();
			}
	
		
	}

		private void createProj(IProject newProj, boolean isJavaProject) throws CoreException {
			newProj.create(null);
			IProjectDescription description = ResourcesPlugin.getWorkspace().newProjectDescription(newProj.getName());
//			if (isJavaProject)
//				description.setNatureIds(new String[]{JavaCore.NATURE_ID});
			description.setLocation(null);
			newProj.open(null);
			newProj.setDescription(description, null);
		}

		private boolean multipleComponentsDetected() {
			StructureEdit se = null;
			try {
				se = StructureEdit.getStructureEditForRead(project);
				if (se == null) return false;
				if (se.getComponentModelRoot() == null) return false;
				return se.getComponentModelRoot().getComponents().size() > 1;
			} finally {
				if (se != null)
					se.dispose();
			}
	}

		private void removeComponentBuilders(IProject aProject) {
		try {
			aProject.refreshLocal(IResource.DEPTH_INFINITE,null);
		} catch (CoreException e1) {
			J2EEUIPlugin.logError(e1);
		}
		//IJavaProject javaP = JemProjectUtilities.getJavaProject(aProject);
		List oldBuilders = new ArrayList();
		oldBuilders.add("org.eclipse.wst.common.modulecore.ComponentStructuralBuilder"); //$NON-NLS-1$
		oldBuilders.add("org.eclipse.wst.common.modulecore.ComponentStructuralBuilderDependencyResolver"); //$NON-NLS-1$
		oldBuilders.add("org.eclipse.wst.common.modulecore.DependencyGraphBuilder"); //$NON-NLS-1$
		try {
			J2EEProjectUtilities.removeBuilders(aProject,oldBuilders);
		} catch (CoreException e) {
			J2EEUIPlugin.logError(e);
		}
	}

		public String getFacetFromProject(IProject aProject) {
			return J2EEProjectUtilities.getJ2EEProjectType(aProject);
		}

		
		protected IDataModel setupJavaInstallAction(IProject aProject, boolean existing,String srcFolder) {
			IDataModel dm = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
			dm.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			String jVersion = "1.4"; //$NON-NLS-1$
			IScopeContext context = new ProjectScope( project );
		    IEclipsePreferences prefs 
		            = context.getNode( JavaCore.PLUGIN_ID );
			if (JavaCore.VERSION_1_5.equals(prefs.get(JavaCore.COMPILER_COMPLIANCE,JavaCore.VERSION_1_4))) {
				jVersion = "5.0"; //$NON-NLS-1$
			}
			dm.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, jVersion);
			if (!existing)
				dm.setStringProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, srcFolder);
			return dm;
		}
		
		protected IDataModel setupUtilInstallAction(IProject aProject,String specVersion) {
			IDataModel aFacetInstallDataModel = DataModelFactory.createDataModel(new UtilityFacetInstallDataModelProvider());
			aFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			aFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, specVersion);
			aFacetInstallDataModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR,false);
			aFacetInstallDataModel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME,null);
			return aFacetInstallDataModel;
		}
		protected IDataModel setupEarInstallAction(IProject aProject,String specVersion) {
			IDataModel earFacetInstallDataModel = DataModelFactory.createDataModel(new EarFacetInstallDataModelProvider());
			earFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			earFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, specVersion);
			
			return earFacetInstallDataModel;
		}
		protected IDataModel setupAppClientInstallAction(IProject aProject,String specVersion) {
			IDataModel aFacetInstallDataModel = DataModelFactory.createDataModel(new AppClientFacetInstallDataModelProvider());
			aFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			aFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, specVersion);
			aFacetInstallDataModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR,false);
			aFacetInstallDataModel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME,null);
			aFacetInstallDataModel.setBooleanProperty(IAppClientFacetInstallDataModelProperties.CREATE_DEFAULT_MAIN_CLASS,false);
			return aFacetInstallDataModel;
		}
		protected IDataModel setupConnectorInstallAction(IProject aProject,String specVersion) {
			IDataModel aFacetInstallDataModel = DataModelFactory.createDataModel(new ConnectorFacetInstallDataModelProvider());
			aFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			aFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, specVersion);
			aFacetInstallDataModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR,false);
			aFacetInstallDataModel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME,null);
			return aFacetInstallDataModel;
		}

		private void addFacets(IProject aProject) {
			StructureEdit edit = null;
			try {
				edit = StructureEdit.getStructureEditForWrite(aProject);
				if (edit == null) return;  // Not a component project....
				edit.getModuleStructuralModel().setUseOldFormat(true);
				if (edit.getComponent() == null) return; // Can't migrate
				ComponentType type = edit.getComponent().getComponentType();
				if (type == null) return;  // Can't migrate
				String compId = type.getComponentTypeId();
				String specVersion = edit.getComponent().getComponentType().getVersion();
				moveMetaProperties(edit.getComponent(),type);
				addFacetsToProject(aProject, compId, specVersion,true);
			}
			finally {
				if (edit != null) {
					edit.save(null);
					edit.getModuleStructuralModel().setUseOldFormat(false);
					edit.dispose();
				}
			}
			
		}

		private void moveMetaProperties(WorkbenchComponent component, ComponentType type) {
			List props = type.getProperties();
			List compProps = component.getProperties();
			for (Iterator iter = props.iterator(); iter.hasNext();) {
				Property element = (Property) iter.next();
				Property newProp = ComponentcoreFactory.eINSTANCE.createProperty();
				newProp.setName(element.getName());
				newProp.setValue(element.getValue());
				compProps.add(newProp);
			}
			props.clear();
		}

		private void addFacetsToProject(IProject aProject, String compId, String specVersion,boolean existing) {
			if (compId.equals(J2EEProjectUtilities.DYNAMIC_WEB))
				installWEBFacets(aProject,specVersion,existing);
			else if (compId.equals(J2EEProjectUtilities.EJB))
				installEJBFacets(aProject,specVersion,existing);
			else if (compId.equals(J2EEProjectUtilities.APPLICATION_CLIENT))
				installAppClientFacets(aProject,specVersion,existing);
			else if (compId.equals(J2EEProjectUtilities.ENTERPRISE_APPLICATION))
				installEARFacets(aProject,specVersion,existing);
			else if (compId.equals(J2EEProjectUtilities.JCA))
				installConnectorFacets(aProject,specVersion,existing);
			else if (compId.equals(J2EEProjectUtilities.UTILITY))
				installUtilityFacets(aProject,specVersion,existing);
			else if (compId.equals(J2EEProjectUtilities.STATIC_WEB))
				installStaticWebFacets(aProject,specVersion,existing);
		}

		private void installStaticWebFacets(IProject project2, String specVersion, boolean existing) {
			IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
			dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, project2.getName());
			FacetDataModelMap facetDMs = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			//facetDMs.add(setupJavaInstallAction(webProj,existing,CreationConstants.DEFAULT_WEB_SOURCE_FOLDER));
			IDataModel newModel = setupStaticWebInstallAction(project2);
			facetDMs.add(newModel);
			//setRuntime(webProj,dm); //Setting runtime property
			try {
				/**
				 * Warning cleanup 12/07/2005
				 */
				//IStatus stat =  dm.getDefaultOperation().execute(null,null);
				dm.getDefaultOperation().execute(null,null);
			} catch (ExecutionException e) {
				Throwable realException = e.getCause();
				if (realException != null && realException instanceof CoreException) {
					IStatus st = ((CoreException)realException).getStatus();
					String message = st != null ? st.toString() : ""; //$NON-NLS-1$
					J2EEUIPlugin.logError(message, realException);
				}
			}
		}

		private IDataModel setupStaticWebInstallAction(IProject project2) {
			IDataModel webFacetInstallDataModel = DataModelFactory.createDataModel(new SimpleWebFacetInstallDataModelProvider());
			webFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, project2.getName());
			webFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, "1.0"); //$NON-NLS-1$
			
			return webFacetInstallDataModel;
		}

		private void installUtilityFacets(IProject aProject, String specVersion, boolean existing) {
			replaceDeployablesOutputIfNecessary(project);
			IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
			dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			FacetDataModelMap facetDMs = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			facetDMs.add(setupJavaInstallAction(aProject,existing,"src")); //$NON-NLS-1$
			IDataModel newModel = setupUtilInstallAction(aProject,specVersion);
			facetDMs.add(newModel);
			try {
				/**
				 * Warning cleanup 12/07/2005
				 */
				//IStatus stat =  dm.getDefaultOperation().execute(null,null);
				dm.getDefaultOperation().execute(null,null);
			} catch (ExecutionException e) {
				Throwable realException = e.getCause();
				if (realException != null && realException instanceof CoreException) {
					IStatus st = ((CoreException)realException).getStatus();
					String message = st != null ? st.toString() : ""; //$NON-NLS-1$
					J2EEUIPlugin.logError(message, realException);
				}
			}
			
		}

		private void installConnectorFacets(IProject aProject, String specVersion, boolean existing) {
			replaceDeployablesOutputIfNecessary(project);
			IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
			dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			FacetDataModelMap facetDMs = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			facetDMs.add(setupJavaInstallAction(aProject,existing,J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.JCA_CONTENT_FOLDER)));
			IDataModel newModel = setupConnectorInstallAction(aProject,specVersion);
			facetDMs.add(newModel);
			try {
				/**
				 * Warning cleanup 12/07/2005
				 */
				//IStatus stat =  dm.getDefaultOperation().execute(null,null);
				dm.getDefaultOperation().execute(null,null);
			} catch (ExecutionException e) {
				Throwable realException = e.getCause();
				if (realException != null && realException instanceof CoreException) {
					IStatus st = ((CoreException)realException).getStatus();
					String message = st != null ? st.toString() : ""; //$NON-NLS-1$
					J2EEUIPlugin.logError(message, realException);
				}
			}
			
		}

		private void installEARFacets(IProject aProject, String specVersion, boolean existing) {
			IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
			dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			FacetDataModelMap facetDMs = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			IDataModel newModel = setupEarInstallAction(aProject,specVersion);
			facetDMs.add(newModel);
			try {
				/**
				 * Warning cleanup 12/07/2005
				 */
				//IStatus stat =  dm.getDefaultOperation().execute(null,null);
				dm.getDefaultOperation().execute(null,null);
			} catch (ExecutionException e) {
				Throwable realException = e.getCause();
				if (realException != null && realException instanceof CoreException) {
					IStatus st = ((CoreException)realException).getStatus();
					String message = st != null ? st.toString() : ""; //$NON-NLS-1$
					J2EEUIPlugin.logError(message, realException);
				}
			}
			
		}

		private void installAppClientFacets(IProject aProject, String specVersion, boolean existing) {
			replaceDeployablesOutputIfNecessary(project);
			IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
			dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			FacetDataModelMap facetDMs = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			facetDMs.add(setupJavaInstallAction(aProject,existing,J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APP_CLIENT_CONTENT_FOLDER)));
			IDataModel newModel = setupAppClientInstallAction(aProject,specVersion);
			facetDMs.add(newModel);
			try {
				/**
				 * Warning cleanup 12/07/2005
				 */
				//IStatus stat =  dm.getDefaultOperation().execute(null,null);
				dm.getDefaultOperation().execute(null,null);
			} catch (ExecutionException e) {
				Throwable realException = e.getCause();
				if (realException != null && realException instanceof CoreException) {
					IStatus st = ((CoreException)realException).getStatus();
					String message = st != null ? st.toString() : ""; //$NON-NLS-1$
					J2EEUIPlugin.logError(message, realException);
				}
			}
			
		}

		private void installEJBFacets(IProject ejbProject2,String ejbVersion, boolean existing) {
			replaceDeployablesOutputIfNecessary(project);
			IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
			dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, ejbProject2.getName());
			FacetDataModelMap facetDMs = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			facetDMs.add(setupJavaInstallAction(ejbProject2,existing,J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER)));
			IDataModel newModel = setupEjbInstallAction(ejbProject2,ejbVersion,existing);
			facetDMs.add(newModel);
			//setRuntime(ejbProject2,dm); //Setting runtime property
			try {
				/**
				 * Warning cleanup 12/07/2005
				 */
				//IStatus stat =  dm.getDefaultOperation().execute(null,null);
				dm.getDefaultOperation().execute(null,null);
			} catch (ExecutionException e) {
				Throwable realException = e.getCause();
				if (realException != null && realException instanceof CoreException) {
					IStatus st = ((CoreException)realException).getStatus();
					String message = st != null ? st.toString() : ""; //$NON-NLS-1$
					J2EEUIPlugin.logError(message, realException);
				}
			}
			
		}
		private void installWEBFacets(IProject webProj,String specVersion, boolean existing) {
			removeOldWebContainerIfNecessary(project);
			replaceDeployablesOutputIfNecessary(project);
			
			IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
			dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, webProj.getName());
			FacetDataModelMap facetDMs = (FacetDataModelMap) dm.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
			facetDMs.add(setupJavaInstallAction(webProj,existing, CommonFrameworksPlugin.getDefault().getPluginPreferences().getString(CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER)));
			IDataModel newModel = setupWebInstallAction(webProj,specVersion);
			facetDMs.add(newModel);
			//setRuntime(webProj,dm); //Setting runtime property
			try {
				/**
				 * Warning cleanup 12/07/2005
				 */
				//IStatus stat =  dm.getDefaultOperation().execute(null,null);
				dm.getDefaultOperation().execute(null,null);
			} catch (ExecutionException e) {
				Throwable realException = e.getCause();
				if (realException != null && realException instanceof CoreException) {
					IStatus st = ((CoreException)realException).getStatus();
					String message = st != null ? st.toString() : ""; //$NON-NLS-1$
					J2EEUIPlugin.logError(message, realException);
				}
			} catch (Exception ex) {
				if (ex instanceof CoreException) {
					IStatus st = ((CoreException)ex).getStatus();
					String message = st != null ? st.toString() : ""; //$NON-NLS-1$
					J2EEUIPlugin.logError(message, ex);
				}
			}
			
			
		}
		private void replaceDeployablesOutputIfNecessary(IProject proj) {


			IJavaProject jproj = JemProjectUtilities.getJavaProject(proj);
			final IClasspathEntry[] current;
			boolean deployablesFound = false;
			try {
				current = jproj.getRawClasspath();
				List updatedList = new ArrayList();
				IPath sourcePath = null;
				for (int i = 0; i < current.length; i++) {
					IClasspathEntry entry = current[i];
					if ((entry.getEntryKind() == IClasspathEntry.CPE_SOURCE) && (entry.getOutputLocation() != null && entry.getOutputLocation().toString().indexOf(OLD_DEPLOYABLES_PATH) != -1)) {
						sourcePath = entry.getPath();
						updatedList.add(JavaCore.newSourceEntry(sourcePath));
						deployablesFound = true;
					}
					else
						updatedList.add(entry);
				}
				if (deployablesFound) {
					IClasspathEntry[] updated = (IClasspathEntry[])updatedList.toArray(new IClasspathEntry[updatedList.size()]);
					jproj.setRawClasspath(updated, null);
					jproj.save(null, true);
				}
			} catch (JavaModelException e) {
				J2EEUIPlugin.logError(e);
			}
			
		
			
		}

		private void removeOldWebContainerIfNecessary(IProject webProj) {

			IJavaProject jproj = JemProjectUtilities.getJavaProject(webProj);
			final IClasspathEntry[] current;
			try {
				current = jproj.getRawClasspath();
				List updatedList = new ArrayList();
				boolean useDefaultWebAppLibraries = J2EEComponentClasspathContainerUtils.getDefaultUseWebAppLibraries();
				for (int i = 0; i < current.length; i++) {
					IClasspathEntry entry = current[i];
					// the web container is added to the classpath if:
					// 1. they don't have an entry for WEB_LIB_CONTAINER AND
					// 2. they have an entry for WEB_LIB_PATH BUT
					// they do not have the preference checked to use the Web App classpath container
					if ((entry.getPath().toString().indexOf(WEB_LIB_CONTAINER) == -1) && 
							((entry.getPath().toString().indexOf(WEB_LIB_PATH) == -1) || !useDefaultWebAppLibraries))
						updatedList.add(entry);
				}
				IClasspathEntry[] updated = (IClasspathEntry[])updatedList.toArray(new IClasspathEntry[updatedList.size()]);
				jproj.setRawClasspath(updated, null);
				jproj.save(null, true);
			} catch (JavaModelException e) {
				J2EEUIPlugin.logError(e);
			}
			
		}

		protected IRuntime getRuntimeByID(String id) {
			IRuntime[] targets = ServerUtil.getRuntimes("", ""); //$NON-NLS-1$ //$NON-NLS-2$
			for (int i = 0; i < targets.length; i++) {
				IRuntime target = targets[i];
				if (id.equals(target.getId()))
					return target;
			}
			return null;
		}

		protected IDataModel setupEjbInstallAction(IProject aProject,String ejbVersion, boolean existing) {
			IDataModel ejbFacetInstallDataModel = DataModelFactory.createDataModel(new EjbFacetInstallDataModelProvider());
			ejbFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			ejbFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, ejbVersion);
			ejbFacetInstallDataModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR,false);
			ejbFacetInstallDataModel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME,null);
			if (!existing)
				ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER, J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER));
			return ejbFacetInstallDataModel;
		}

		protected IDataModel setupWebInstallAction(IProject aProject,String specVersion) {
			IDataModel webFacetInstallDataModel = DataModelFactory.createDataModel(new WebFacetInstallDataModelProvider());
			webFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, aProject.getName());
			webFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, specVersion);
			webFacetInstallDataModel.setBooleanProperty(IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR,false);
			webFacetInstallDataModel.setStringProperty(IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME,null);
			return webFacetInstallDataModel;
		}


}
