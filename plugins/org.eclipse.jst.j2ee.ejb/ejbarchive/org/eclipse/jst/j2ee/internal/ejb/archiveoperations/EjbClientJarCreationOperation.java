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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestOperation;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.ejb.internal.plugin.EjbPlugin;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.EarUtilities;
import org.eclipse.jst.j2ee.project.facet.IUtilityFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.jst.jee.project.facet.ICreateDeploymentFilesDataModelProperties;
import org.eclipse.jst.jee.project.facet.IEJBCreateDeploymentFilesDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.operation.FacetProjectCreationOperation;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class EjbClientJarCreationOperation
	extends AbstractDataModelOperation
	implements IEjbClientJarCreationDataModelProperties{

	private IProgressMonitor monitor = null;
	
	public EjbClientJarCreationOperation(IDataModel model) {
		super(model);
	}

	@Override
	public IStatus execute(IProgressMonitor aMonitor, IAdaptable info) throws ExecutionException {
		
		monitor = aMonitor;
		
		IStatus stat = OK_STATUS;

		IProject ejbproject = ProjectUtilities.getProject(model.getStringProperty( EJB_PROJECT_NAME ));
		if( ejbproject.exists() && ejbproject.isAccessible()){
			IFacetedProject facetedProject = null;
			org.eclipse.wst.common.project.facet.core.runtime.IRuntime runtime = null;
			String javaSourceFolder = ""; //$NON-NLS-1$
			
			try {
				facetedProject = ProjectFacetsManager.create(ejbproject);
			} catch (CoreException e) {
				EjbPlugin.logError(e);
			}
			if( facetedProject != null ){
				runtime = facetedProject.getRuntime();

				IContainer container = J2EEProjectUtilities.getSourceFolderOrFirst(ejbproject, null);
				if( container.getType() == IResource.FOLDER ){
					javaSourceFolder = container.getName();
				}
				
			}
			IProject[] earprojects = EarUtilities.getReferencingEARProjects( ejbproject );
			
			
			String projectName = model.getStringProperty( PROJECT_NAME );
			//String earProjectName = model.getStringProperty( IJavaUtilityProjectCreationDataModelProperties.EAR_PROJECT_NAME );

			
			IDataModel dm = DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
			
			FacetDataModelMap map = (FacetDataModelMap) dm.getProperty(UtilityProjectCreationDataModelProvider.FACET_DM_MAP);
			
			IDataModel javadm = map.getFacetDataModel( IModuleConstants.JST_JAVA );
			IDataModel utildm = map.getFacetDataModel( J2EEProjectUtilities.UTILITY );
			
			// because we do not want utility->add to ear, as we are going to add it later to all referencing ear projects
			utildm.setBooleanProperty(IUtilityFacetInstallDataModelProperties.ADD_TO_EAR, false);
			
			javadm.setProperty( JavaFacetInstallDataModelProvider.FACET_PROJECT_NAME,
					projectName);
			
			
			javadm.setProperty( JavaFacetInstallDataModelProvider.SOURCE_FOLDER_NAME,
					javaSourceFolder);

			javadm.setProperty( JavaFacetInstallDataModelProvider.DEFAULT_OUTPUT_FOLDER_NAME,
					javaSourceFolder);

			if (earprojects.length > 0)
				utildm.setProperty( IUtilityFacetInstallDataModelProperties.EAR_PROJECT_NAME, earprojects[0].getName());
			
			utildm.setProperty( IUtilityFacetInstallDataModelProperties.FACET_RUNTIME, runtime );
			dm.setProperty(UtilityProjectCreationDataModelProvider.FACET_RUNTIME, runtime);

			FacetProjectCreationOperation op = new FacetProjectCreationOperation(dm);
			try {
				stat = op.execute( monitor, null );
			} catch (ExecutionException e) {
				EjbPlugin.logError(e);
			}

			final IVirtualComponent c = ComponentCore.createComponent( ejbproject );
			c.setMetaProperty(CreationConstants.EJB_CLIENT_NAME, projectName );

			String clientURI = model.getStringProperty(CLIENT_URI);
			c.setMetaProperty(CreationConstants.CLIENT_JAR_URI, clientURI);
			
			try{
				for( int i = 0;  i< earprojects.length; i++ ){
					runAddClientToEAROperation( earprojects[i].getName(), model, monitor);
				}

				updateEJBDD(model, monitor);
	            moveOutgoingJARDependencies();
				EJBClientJarCreationHelper.copyOutgoingClasspathEntries( ejbproject,
								ProjectUtilities.getProject(projectName), true);
				modifyEJBModuleJarDependency(model, monitor);
				IProject clientProject = ProjectUtilities.getProject( model.getStringProperty( PROJECT_NAME ) );
	            moveIncomingJARDependencies( ejbproject, clientProject );
	            
	            
			}catch (CoreException e) {
				EjbPlugin.logError(e);
			} catch (InvocationTargetException e) {
				EjbPlugin.logError(e);
			} catch (InterruptedException e) {
				EjbPlugin.logError(e);
			}
			
			EJBClientJarCreationHelper helper = new EJBClientJarCreationHelper( ejbproject );
			
			
	        IDataModel moveModel =  DataModelFactory.createDataModel( new EJBClientJarFileMoveDataModelProvider());
	        moveModel.setProperty(IEJBClientJarFileMoveDataModelProperties.EJB_PROJECT_NAME,
	        			ejbproject.getName() );
	        moveModel.setProperty(IEJBClientJarFileMoveDataModelProperties.EJB_CLIENTVIEW_PROJECT_NAME,
	        			 model.getStringProperty( PROJECT_NAME )); 

	        moveModel.setProperty( IEJBClientJarFileMoveDataModelProperties.FILES_TO_MOVE_MAP,
	        			helper.getFilesToMove() );
	        moveModel.getDefaultOperation().execute(monitor, null);
	        
		}
		

        
		return stat;
	}
	
	protected void runAddClientToEAROperation(String earProjectName, IDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {


		IProject earproject = ProjectUtilities.getProject(earProjectName);

		IVirtualComponent earComp = ComponentCore.createComponent(earproject);


		String clientProjectName = model.getStringProperty( PROJECT_NAME );

		IProject clientProject = ProjectUtilities.getProject(clientProjectName);
		IVirtualComponent component = ComponentCore.createComponent(clientProject);

		if (earComp.exists() && component.exists()) {
			IDataModel dm = DataModelFactory.createDataModel(new AddComponentToEnterpriseApplicationDataModelProvider());
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, earComp);

			List modList = (List) dm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
			modList.add(component);
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
			
			String clientURI = model.getStringProperty(CLIENT_URI);
			((Map)dm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENTS_TO_URI_MAP)).put(component, clientURI);

			try {
				dm.getDefaultOperation().execute(monitor, null);
			} catch (ExecutionException e) {
				EjbPlugin.logError(e);
			}
		}
	}


	protected void runAddClientToEJBOperation(IDataModel model, IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		String ejbprojectName = model.getStringProperty( EJB_PROJECT_NAME);
		IProject ejbProj = ProjectUtilities.getProject(ejbprojectName);
		IVirtualComponent ejbcomponent = ComponentCore.createComponent(ejbProj);


		String clientProjectName = model.getStringProperty( PROJECT_NAME);
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
			EjbPlugin.logError(e);
		}

	}

	private void modifyEJBModuleJarDependency(IDataModel model, 
				IProgressMonitor aMonitor) throws InvocationTargetException, InterruptedException {


		String ejbprojectName =  model.getStringProperty( EJB_PROJECT_NAME );
		IProject ejbProj = ProjectUtilities.getProject(ejbprojectName);
		IVirtualComponent ejbComponent = ComponentCore.createComponent(ejbProj);
		IVirtualFile vf = ejbComponent.getRootFolder().getFile(new Path(J2EEConstants.MANIFEST_URI));
		IFile manifestmf = vf.getUnderlyingFile();

		String clientURI = model.getStringProperty(CLIENT_URI);

		IDataModel updateManifestDataModel = DataModelFactory.createDataModel(UpdateManifestDataModelProvider.class);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, ejbprojectName);
		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, UpdateManifestDataModelProvider.convertClasspathStringToList(clientURI));


		try {
			updateManifestDataModel.getDefaultOperation().execute(aMonitor, null);
		} catch (Exception e) {
			EjbPlugin.logError(e);
		}

	}


	private void updateEJBDD(final IDataModel model, IProgressMonitor monitor) {

		String ejbprojectName = model.getStringProperty( EJB_PROJECT_NAME );
		final IProject ejbProj = ProjectUtilities.getProject(ejbprojectName);
		
		//if deployment descriptor does not exist then we need to generate one
		if (!hasDeploymentDescriptor(ejbProj)) {
			IDataModel dataModel = DataModelFactory.createDataModel(IEJBCreateDeploymentFilesDataModelProperties.class);
			dataModel.setProperty(ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT, ejbProj);
			try {
				dataModel.getDefaultOperation().execute(new NullProgressMonitor(), null);
			} catch (ExecutionException e) {
				EjbPlugin.logError( e );
			}
		}

		IModelProvider ejbModel = ModelProviderManager.getModelProvider(ejbProj);
        ejbModel.modify(new Runnable() {
            public void run() {
                IModelProvider writableEjbModel = ModelProviderManager.getModelProvider(ejbProj);
                Object modelObject = writableEjbModel.getModelObject();
                String clientProjectName = model.getStringProperty(PROJECT_NAME);
        		IVirtualComponent c = ComponentCore.createComponent(ejbProj);
        		Properties props = c.getMetaProperties();
        		String clienturi = props.getProperty(CreationConstants.CLIENT_JAR_URI);
        		
                if (modelObject instanceof org.eclipse.jst.javaee.ejb.EJBJar) {
                    org.eclipse.jst.javaee.ejb.EJBJar ejbres = (org.eclipse.jst.javaee.ejb.EJBJar) writableEjbModel.getModelObject();
                    if (clienturi != null && !clienturi.equals("")) { //$NON-NLS-1$
                    	ejbres.setEjbClientJar(clienturi);
                    } else
                    	ejbres.setEjbClientJar(clientProjectName + IJ2EEModuleConstants.JAR_EXT);
                }
                else {
                    org.eclipse.jst.j2ee.ejb.EJBJar ejbres = (org.eclipse.jst.j2ee.ejb.EJBJar) writableEjbModel.getModelObject();
                    if (clienturi != null && !clienturi.equals("")) { //$NON-NLS-1$
                        ejbres.setEjbClientJar(clienturi);
                    } else
                        ejbres.setEjbClientJar(clientProjectName + IJ2EEModuleConstants.JAR_EXT);
                }
            }
        },null);
	}	
	
	//The referencing projects which were having Ejb will now have ejbclient
	private void moveIncomingJARDependencies(IProject ejbProject, IProject clientProject ) throws InvocationTargetException, InterruptedException {
		
		InvertClientJARDependencyCompoundOperation op = 
			new InvertClientJARDependencyCompoundOperation( EarUtilities.getReferencingEARProjects( ejbProject ),
						ejbProject,
						clientProject );
	
		try {
			op.execute(createSubProgressMonitor(1), null);
		} catch (ExecutionException e) {
			EjbPlugin.logError( e );
		}
	}
	
	protected IProgressMonitor createSubProgressMonitor(int ticks) {
		return new SubProgressMonitor(monitor, ticks);
	}	
	
	 //from the ejb project collect the entries in its manifest, and put it in the client project,
	 //remove these entries from the ejb project
	
    private void moveOutgoingJARDependencies() throws InvocationTargetException, InterruptedException {
    	
		IProject ejbProject = ProjectUtilities.getProject(model.getStringProperty( EJB_PROJECT_NAME ));
		String clientProjectName = model.getStringProperty( PROJECT_NAME );
		
		//from the ejb project collect the entries in its manifest
        ArchiveManifest ejbMf = J2EEProjectUtilities.readManifest( ejbProject );
        if (ejbMf == null)
            return;
        String[] mfEntries = ejbMf.getClassPathTokenized();
        if (mfEntries.length == 0)
            return;
        
        IProject[] earProjects = EarUtilities.getReferencingEARProjects( ejbProject );
        IProject clientProject = ResourcesPlugin.getWorkspace().getRoot().getProject(clientProjectName);
        IProgressMonitor sub = createSubProgressMonitor( earProjects.length * 2 );
        for (int i = 0; i < earProjects.length; i++) {
            List normalized = EJBClientJarCreationHelper.normalize(mfEntries, earProjects[i], ejbProject, true );
            UpdateManifestOperation addOp = new EJBClientManifestUtility().getAddOperation(clientProject, earProjects[i], normalized);
            UpdateManifestOperation removeOp = new EJBClientManifestUtility().getRemoveOperation(ejbProject, earProjects[i], normalized, null);

            try {
				addOp.execute( new SubProgressMonitor(sub, 1), null );
	            removeOp.execute( new SubProgressMonitor(sub, 1), null );
			} catch (ExecutionException e) {
				EjbPlugin.logError( e );
			}
        }
    }
    
    private boolean hasDeploymentDescriptor(IProject project) {
    	boolean ret = true;
    	IPath ddFilePath = new Path(J2EEConstants.EJBJAR_DD_URI);
    	IVirtualComponent component = ComponentCore.createComponent(project);
    	if (component.getRootFolder() != null && component.getRootFolder().getUnderlyingFolder() != null) {
    		IFile ddXmlFile = component.getRootFolder().getUnderlyingFolder().getFile(ddFilePath);
    		ret = ddXmlFile.exists();
    	}
    	return ret;
    }

    
}
