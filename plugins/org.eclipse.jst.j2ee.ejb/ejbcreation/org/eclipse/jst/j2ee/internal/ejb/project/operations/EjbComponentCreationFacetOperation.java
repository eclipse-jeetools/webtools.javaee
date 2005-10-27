package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.AddComponentToEnterpriseApplicationDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProperties;
import org.eclipse.jst.j2ee.application.internal.operations.UpdateManifestDataModelProvider;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.ejb.datamodel.properties.IEjbComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBJarImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProperties;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEComponentCreationFacetOperation;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EjbComponentCreationFacetOperation extends J2EEComponentCreationFacetOperation {

	
	public EjbComponentCreationFacetOperation(IDataModel model) {
		super(model);
	}
	
	
	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		IDataModel dm = DataModelFactory.createDataModel(new FacetProjectCreationDataModelProvider());
		String projectName = model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME, projectName);
		List facetDMs = new ArrayList();
		facetDMs.add(setupJavaInstallAction());
		facetDMs.add(setupEjbInstallAction());
		dm.setProperty(IFacetProjectCreationDataModelProperties.FACET_DM_LIST, facetDMs);
		IStatus stat =  dm.getDefaultOperation().execute(monitor, info);

		if( stat.isOK()){
			String earProjectName = (String) model.getProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME);
			IProject earProject = ProjectUtilities.getProject( earProjectName );
			if (earProject != null && earProject.exists())
				stat = addtoEar(projectName, earProjectName);
		}

		final boolean createClient = model.getBooleanProperty(IEjbComponentCreationDataModelProperties.CREATE_CLIENT);
		IDataModel ejbClientComponentDataModel = (IDataModel) model.getProperty(IEjbComponentCreationDataModelProperties.NESTED_MODEL_EJB_CLIENT_CREATION);
		String clientProjectName = ejbClientComponentDataModel.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
		

		if( createClient && clientProjectName != null && clientProjectName != ""){
			try {
				runAddClientToEAROperation(monitor);
		        runAddClientToEJBOperation(monitor);
		        modifyEJBModuleJarDependency(monitor);
		        updateEJBDD(monitor);				
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

        
		return stat;
	}

	protected IDataModel setupEjbInstallAction() {
		String versionStr = model.getPropertyDescriptor(IJ2EEComponentCreationDataModelProperties.COMPONENT_VERSION).getPropertyDescription();
		IDataModel ejbFacetInstallDataModel = DataModelFactory.createDataModel(new EjbFacetInstallDataModelProvider());
		ejbFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_PROJECT_NAME, model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME));
		ejbFacetInstallDataModel.setProperty(IFacetDataModelProperties.FACET_VERSION_STR, versionStr);
		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.CONFIG_FOLDER,
		model.getStringProperty(IJavaComponentCreationDataModelProperties.JAVASOURCE_FOLDER));
		if (model.getBooleanProperty(IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR))
			ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.EAR_PROJECT_NAME, model.getProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME));

		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.CREATE_CLIENT, model.getProperty(IEjbComponentCreationDataModelProperties.CREATE_CLIENT));
		
		IDataModel ejbClientComponentDataModel = (IDataModel) model.getProperty(IEjbComponentCreationDataModelProperties.NESTED_MODEL_EJB_CLIENT_CREATION);
		
		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.CLIENT_NAME,
				ejbClientComponentDataModel.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME));
		
		ejbFacetInstallDataModel.setProperty(IEjbFacetInstallDataModelProperties.CLIENT_SOURCE_FOLDER,
				model.getStringProperty(IJavaComponentCreationDataModelProperties.JAVASOURCE_FOLDER));
		ejbFacetInstallDataModel.setProperty(IJ2EEFacetInstallDataModelProperties.RUNTIME_TARGET_ID, model.getProperty(IJ2EEComponentCreationDataModelProperties.RUNTIME_TARGET_ID));
		
		return ejbFacetInstallDataModel;
	}
	
    protected void runAddClientToEAROperation(IProgressMonitor monitor) throws
    		CoreException, InvocationTargetException, InterruptedException {
 	
   
        String earProjectName = (String) model.getProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME);
		IProject earproject = ProjectUtilities.getProject( earProjectName );
		
		IVirtualComponent earComp = ComponentCore.createComponent( earproject );
				

		IDataModel ejbClientComponentDataModel = (IDataModel) model.getProperty(IEjbComponentCreationDataModelProperties.NESTED_MODEL_EJB_CLIENT_CREATION);
		String clientProjectName = ejbClientComponentDataModel.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
		IProject clientProject = ProjectUtilities.getProject( clientProjectName );
		IVirtualComponent component = ComponentCore.createComponent( clientProject );
		
		if( earComp.exists() && component.exists() ){
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


    protected void runAddClientToEJBOperation(IProgressMonitor monitor)throws CoreException, InvocationTargetException, InterruptedException{
		
		String projectName = model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
        IProject ejbProj = ProjectUtilities.getProject( projectName );
		IVirtualComponent ejbcomponent = ComponentCore.createComponent(ejbProj);
		
		IDataModel ejbClientComponentDataModel = (IDataModel) model.getProperty(IEjbComponentCreationDataModelProperties.NESTED_MODEL_EJB_CLIENT_CREATION);
		String clientProjectName = ejbClientComponentDataModel.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
		IProject clientProject = ProjectUtilities.getProject( clientProjectName );
		IVirtualComponent ejbclientcomponent = ComponentCore.createComponent( clientProject );
		
        IDataModel dm = DataModelFactory.createDataModel(new CreateReferenceComponentsDataModelProvider());
		dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, ejbcomponent);
		
        List modList = (List) dm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
        modList.add( ejbclientcomponent );
        dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, modList);
		try {
			dm.getDefaultOperation().execute(monitor, null);
		} catch (ExecutionException e) {
			Logger.getLogger().log(e);
		}
            
    }

    private void modifyEJBModuleJarDependency(IProgressMonitor aMonitor) throws InvocationTargetException, InterruptedException {
   
        
		String projectName = model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
        IProject ejbProj = ProjectUtilities.getProject( projectName );
		IVirtualComponent ejbComponent = ComponentCore.createComponent(ejbProj);
		IVirtualFile vf = ejbComponent.getRootFolder().getFile( new Path(J2EEConstants.MANIFEST_URI) );
        IFile manifestmf = vf.getUnderlyingFile();
		
        
		IDataModel ejbClientComponentDataModel = (IDataModel) model.getProperty(IEjbComponentCreationDataModelProperties.NESTED_MODEL_EJB_CLIENT_CREATION);
		String clientProjectName = ejbClientComponentDataModel.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
		
		
        IDataModel updateManifestDataModel = DataModelFactory.createDataModel(UpdateManifestDataModelProvider.class);
        updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.PROJECT_NAME, projectName);
        updateManifestDataModel.setBooleanProperty(UpdateManifestDataModelProperties.MERGE, false);
        updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.MANIFEST_FILE, manifestmf);
        updateManifestDataModel.setProperty(UpdateManifestDataModelProperties.JAR_LIST,
        	UpdateManifestDataModelProvider.convertClasspathStringToList(clientProjectName + ".jar") );//$NON-NLS-1$
        
 
        try {
        	updateManifestDataModel.getDefaultOperation().execute( aMonitor, null );
        } catch (Exception e) {
        	Logger.getLogger().logError(e);
        }
        
        if( !clientProjectName.equals( projectName )){
            IDataModel dataModel = DataModelFactory.createDataModel(new JARDependencyDataModelProvider());
            dataModel.setProperty(JARDependencyDataModelProperties.PROJECT_NAME, projectName);
            dataModel.setProperty(JARDependencyDataModelProperties.REFERENCED_PROJECT_NAME, clientProjectName);
            dataModel.setIntProperty(JARDependencyDataModelProperties.JAR_MANIPULATION_TYPE, JARDependencyDataModelProperties.JAR_MANIPULATION_ADD);
            try {
            	dataModel.getDefaultOperation().execute( aMonitor, null );
	        } catch (Exception e) {
	        	Logger.getLogger().logError(e);
	        }
        }   
    }


    private void updateEJBDD(IProgressMonitor monitor) {        
        
		String projectName = model.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
        IProject ejbProj = ProjectUtilities.getProject( projectName );
		
		IDataModel ejbClientComponentDataModel = (IDataModel) model.getProperty(IEjbComponentCreationDataModelProperties.NESTED_MODEL_EJB_CLIENT_CREATION);
		String clientProjectName = ejbClientComponentDataModel.getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
		
		
        
        EJBArtifactEdit ejbEdit = null;
        try{
            ejbEdit = EJBArtifactEdit.getEJBArtifactEditForWrite( ejbProj );
            if(ejbEdit != null) {
                EJBJarImpl ejbres = (EJBJarImpl)ejbEdit.getDeploymentDescriptorRoot();
                ejbres.setEjbClientJar(clientProjectName + ".jar");//$NON-NLS-1$
                ejbEdit.saveIfNecessary(monitor);
            }
        }
        catch(Exception e){
            Logger.getLogger().logError(e);
        } finally {
            if(ejbEdit != null)
                ejbEdit.dispose();
        }  
    }   
}


