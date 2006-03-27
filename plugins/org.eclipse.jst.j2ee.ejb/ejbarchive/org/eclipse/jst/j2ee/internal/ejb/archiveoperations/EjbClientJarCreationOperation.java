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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveManifest;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBJarImpl;
import org.eclipse.jst.j2ee.internal.common.CreationConstants;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IUtilityFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.UtilityProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.operation.FacetProjectCreationOperation;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
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

	public IStatus execute(IProgressMonitor aMonitor, IAdaptable info) throws ExecutionException {
		
		monitor = aMonitor;
		
		IStatus stat = OK_STATUS;

		IProject ejbproject = ProjectUtilities.getProject(model.getStringProperty( EJB_PROJECT_NAME ));
		if( ejbproject.exists() && ejbproject.isAccessible()){
			IFacetedProject facetedProject = null;
			org.eclipse.wst.common.project.facet.core.runtime.IRuntime runtime = null;
			String javaSourceFolder = ""; //$NON-NLS-1$
			
			boolean canContinue = true;
			try {
				facetedProject = ProjectFacetsManager.create(ejbproject);
			} catch (CoreException e) {
				Logger.getLogger().logError(e);
				canContinue = false;
			}
			if( canContinue ){
				runtime = facetedProject.getRuntime();

				IContainer container = J2EEProjectUtilities.getSourceFolderOrFirst(ejbproject, null);
				if( container.getType() == IResource.FOLDER ){
					javaSourceFolder = container.getName();
				}
				
			}
			IProject[] earprojects = J2EEProjectUtilities.getReferencingEARProjects( ejbproject );
			
			
			String projectName = model.getStringProperty( PROJECT_NAME );
			//String earProjectName = model.getStringProperty( IJavaUtilityProjectCreationDataModelProperties.EAR_PROJECT_NAME );

			
			IDataModel dm = DataModelFactory.createDataModel(new UtilityProjectCreationDataModelProvider());
			
			FacetDataModelMap map = (FacetDataModelMap) dm.getProperty(UtilityProjectCreationDataModelProvider.FACET_DM_MAP);
			
			IDataModel javadm = map.getFacetDataModel( IModuleConstants.JST_JAVA );
			IDataModel utildm = map.getFacetDataModel( J2EEProjectUtilities.UTILITY );
			
			javadm.setProperty( JavaFacetInstallDataModelProvider.FACET_PROJECT_NAME,
					projectName);
			
			
			javadm.setProperty( JavaFacetInstallDataModelProvider.SOURCE_FOLDER_NAME,
					javaSourceFolder);
			

			utildm.setProperty( IUtilityFacetInstallDataModelProperties.EAR_PROJECT_NAME, earprojects[0].getName());
			
			utildm.setProperty( IUtilityFacetInstallDataModelProperties.FACET_RUNTIME, runtime );
			dm.setProperty(UtilityProjectCreationDataModelProvider.FACET_RUNTIME, runtime);

			FacetProjectCreationOperation op = new FacetProjectCreationOperation(dm);
			try {
				stat = op.execute( monitor, null );
			} catch (ExecutionException e) {
				Logger.getLogger().logError(e);
			}

			final IVirtualComponent c = ComponentCore.createComponent( ejbproject );
			c.setMetaProperty(CreationConstants.EJB_CLIENT_NAME, projectName );

			String clientURI = model.getStringProperty(CLIENT_URI);
			c.setMetaProperty(CreationConstants.CLIENT_JAR_URI, clientURI);
			
			
			try{
				for( int i = 0;  i< earprojects.length; i++ ){
					runAddClientToEAROperation( earprojects[i].getName(), model, monitor);
				}
				runAddClientToEJBOperation(model, monitor);

				updateEJBDD(model, monitor);
	            moveOutgoingJARDependencies();
				EJBClientJarCreationHelper.copyOutgoingClasspathEntries( ejbproject,
								ProjectUtilities.getProject(projectName), true);
				modifyEJBModuleJarDependency(model, earprojects[0], monitor);
				IProject clientProject = ProjectUtilities.getProject( model.getStringProperty( PROJECT_NAME ) );
	            moveIncomingJARDependencies( ejbproject, clientProject );
	            
	            
			}catch (CoreException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
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
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_ARCHIVE_NAME, clientURI);
			
			try {
				dm.getDefaultOperation().execute(monitor, null);
			} catch (ExecutionException e) {
				Logger.getLogger().log(e);
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
			Logger.getLogger().log(e);
		}

	}

	private void modifyEJBModuleJarDependency(IDataModel model, IProject earProject,
				IProgressMonitor aMonitor) throws InvocationTargetException, InterruptedException {


		String ejbprojectName =  model.getStringProperty( EJB_PROJECT_NAME );
//		IProject ejbProj = ProjectUtilities.getProject(ejbprojectName);
//		IVirtualComponent ejbComponent = ComponentCore.createComponent(ejbProj);
//		IVirtualFile vf = ejbComponent.getRootFolder().getFile(new Path(J2EEConstants.MANIFEST_URI));
//		IFile manifestmf = vf.getUnderlyingFile();



		String clientProjectName = model.getStringProperty( PROJECT_NAME );

//		IDataModel updateManifestDataModel = DataModelFactory.createDataModel(UpdateManifestDataModelProvider.class);
//		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, ejbprojectName);
//		updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
//		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
//		updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST, UpdateManifestDataModelProvider.convertClasspathStringToList(clientProjectName + ".jar"));//$NON-NLS-1$
//
//
//		try {
//			updateManifestDataModel.getDefaultOperation().execute(aMonitor, null);
//		} catch (Exception e) {
//			Logger.getLogger().logError(e);
//		}

		if (!clientProjectName.equals(ejbprojectName)) {
			IDataModel dataModel = DataModelFactory.createDataModel(new JARDependencyDataModelProvider());
			dataModel.setProperty(JARDependencyDataModelProperties.PROJECT_NAME, ejbprojectName);
			dataModel.setProperty(JARDependencyDataModelProperties.REFERENCED_PROJECT_NAME, clientProjectName);
			dataModel.setProperty(JARDependencyDataModelProperties.EAR_PROJECT_NAME, earProject.getName());
			
			dataModel.setIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE, JARDependencyDataModelProperties.JAR_MANIPULATION_ADD);
			try {
				dataModel.getDefaultOperation().execute(aMonitor, null);
			} catch (Exception e) {
				Logger.getLogger().logError(e);
			}
		}
	}


	private void updateEJBDD(IDataModel model, IProgressMonitor monitor) {

		String ejbprojectName = model.getStringProperty( EJB_PROJECT_NAME );
		IProject ejbProj = ProjectUtilities.getProject(ejbprojectName);


		String clientProjectName = model.getStringProperty(PROJECT_NAME);

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
	
	//The referencing projects which were having Ejb will now have ejbclient
	private void moveIncomingJARDependencies(IProject ejbProject, IProject clientProject ) throws InvocationTargetException, InterruptedException {
		
		InvertClientJARDependencyCompoundOperation op = 
			new InvertClientJARDependencyCompoundOperation( J2EEProjectUtilities.getReferencingEARProjects( ejbProject ),
						ejbProject,
						clientProject );
	
		try {
			op.execute(createSubProgressMonitor(1), null);
		} catch (ExecutionException e) {
			Logger.getLogger().logError( e );
		}
	}
	
	protected IProgressMonitor createSubProgressMonitor(int ticks) {
		return new SubProgressMonitor(monitor, ticks);
	}	
	
	 //from the ejb project collect the entries in its manifest, and put it in the client project,
	 //remove these entries from the ejb project
	
    private void moveOutgoingJARDependencies() throws InvocationTargetException, InterruptedException {
    	
		IProject ejbproject = ProjectUtilities.getProject(model.getStringProperty( EJB_PROJECT_NAME ));
		String clientProjectName = model.getStringProperty( PROJECT_NAME );
		
		//from the ejb project collect the entries in its manifest
        ArchiveManifest ejbMf = J2EEProjectUtilities.readManifest( ejbproject );
        if (ejbMf == null)
            return;
        String[] mfEntries = ejbMf.getClassPathTokenized();
        if (mfEntries.length == 0)
            return;
        
        IProject[] earProjects = J2EEProjectUtilities.getReferencingEARProjects( ejbproject );
        
        IProgressMonitor sub = createSubProgressMonitor( earProjects.length * 2 );
        for (int i = 0; i < earProjects.length; i++) {
            List normalized = EJBClientJarCreationHelper.normalize(mfEntries, earProjects[i], ejbproject, true );
            
            //transfer the manifest entries from the ejb project to the client project
            IDataModel addDataModel = DataModelFactory.createDataModel( new JARDependencyDataModelProvider() );
            addDataModel.setIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE,
            			JARDependencyDataModelProperties.JAR_MANIPULATION_ADD);
            
            addDataModel.setProperty(JARDependencyDataModelProperties.PROJECT_NAME,
            			clientProjectName );
            
            addDataModel.setProperty(JARDependencyDataModelProperties.EAR_PROJECT_NAME, earProjects[i].getName());
            addDataModel.setProperty(JARDependencyDataModelProperties.JAR_LIST, normalized);
            

            //remove the manifest entries from the ejb project
            IDataModel removeDataModel = DataModelFactory.createDataModel( new JARDependencyDataModelProvider() );
            
            removeDataModel.setIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE,
            			JARDependencyDataModelProperties.JAR_MANIPULATION_REMOVE);
            removeDataModel.setProperty(JARDependencyDataModelProperties.PROJECT_NAME,
            			ejbproject.getName());
            
            removeDataModel.setProperty(JARDependencyDataModelProperties.EAR_PROJECT_NAME,
            			earProjects[i].getName());
            
            removeDataModel.setProperty(JARDependencyDataModelProperties.JAR_LIST, normalized);


            try {
				addDataModel.getDefaultOperation().execute( new SubProgressMonitor(sub, 1), null );
	            removeDataModel.getDefaultOperation().execute( new SubProgressMonitor(sub, 1), null );				
			} catch (ExecutionException e) {
				Logger.getLogger().logError( e );
			}

          

        }
    }
    
}
