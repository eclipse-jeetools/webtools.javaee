package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IEarComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.project.facet.IJavaProjectMigrationDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.JavaProjectMigrationDataModelProvider;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.datamodel.properties.ICreateReferenceComponentsDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.operation.ComponentCreationOperation;
import org.eclipse.wst.common.componentcore.internal.operation.CreateReferenceComponentsDataModelProvider;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentCreationOperation extends ComponentCreationOperation implements IEarComponentCreationDataModelProperties{

	public EARComponentCreationOperation(IDataModel model) {
		super(model); 
	}
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#createAndLinkJ2EEComponents()
     */
    protected void createAndLinkJ2EEComponentsForMultipleComponents() throws CoreException {
		IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        component.create(0, null);
		//create and link META-INF folder
		IVirtualFolder root = component.getRootFolder().getFolder(new Path("/")); //$NON-NLS-1$		
		root.createLink(new Path("/" + getModuleName()), 0, null); //$NON-NLS-1$
		
    	IVirtualFolder metaInfFolder = root.getFolder(J2EEConstants.META_INF);
    	metaInfFolder.create(IResource.FORCE, null);
    }
    
    protected void createAndLinkJ2EEComponentsForSingleComponent() throws CoreException {
        IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        component.create(0, null);
        //create and link META-INF folder
        IVirtualFolder root = component.getRootFolder().getFolder(new Path("/")); //$NON-NLS-1$     
        root.createLink(new Path("/"), 0, null); //$NON-NLS-1$
        
        IVirtualFolder metaInfFolder = root.getFolder(J2EEConstants.META_INF);
        metaInfFolder.create(IResource.FORCE, null);
    }
    
	public IProject getProject() {
		String projName = model.getStringProperty(PROJECT_NAME);
		return ProjectUtilities.getProject( projName );
	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        EARArtifactEdit earEdit = null;
        try {
            earEdit = EARArtifactEdit.getEARArtifactEditForWrite(getProject());
            Integer version = (Integer)getDataModel().getProperty(COMPONENT_VERSION);
       	 	earEdit.createModelRoot(version.intValue());
            earEdit.save(monitor);
        } finally {
       		if (earEdit != null)
       		    earEdit.dispose();
        }		
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) {
		try {
			super.execute(monitor, info);
			if (getDataModel().getBooleanProperty(CREATE_DEFAULT_FILES)) {
				createDeploymentDescriptor(monitor);
			}
			addModulesToEAR(monitor);
			addMetaResources();
		} catch (CoreException e) {
			Logger.getLogger().log(e.getMessage());
		} catch (InvocationTargetException e) {
            Logger.getLogger().log(e.getMessage());
		} catch (InterruptedException e) {
            Logger.getLogger().log(e.getMessage());
		}
		return OK_STATUS;
	}
	
	private IStatus addMetaResources() {
		IVirtualComponent component = ComponentCore.createComponent(getProject());
		if (component.getRootFolder().getUnderlyingResource() instanceof IProject) {
			IPath[] metaResources = new IPath[]{new Path("/.facets"), new Path("/.project"),
					new Path("/.runtime"), new Path("/"+IModuleConstants.COMPONENT_FILE_PATH.toString())};
			component.setMetaResources(metaResources);
		}
		return OK_STATUS;
	}
	
	private IStatus  addModulesToEAR(IProgressMonitor monitor) {
		IStatus stat = OK_STATUS;
		try{
			IDataModel dm = (IDataModel)model.getProperty(NESTED_ADD_COMPONENT_TO_EAR_DM);
            IVirtualComponent component = ComponentCore.createComponent(getProject(), model.getStringProperty(COMPONENT_DEPLOY_NAME));
			dm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, component);

			List moduleProjectsList = (List)model.getProperty(J2EE_PROJECTS_LIST);
			if (moduleProjectsList != null && !moduleProjectsList.isEmpty()) {
				List moduleComponentsList = new ArrayList(moduleProjectsList.size());
				for(int i=0;i<moduleProjectsList.size(); i++){
					moduleComponentsList.add(ComponentCore.createComponent((IProject)moduleProjectsList.get(i)));
				}
				dm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, moduleComponentsList);
				stat = dm.validateProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
				if( stat != OK_STATUS )
					return stat;
				dm.getDefaultOperation().execute(monitor, null);				
			}				
			List javaProjectsList = (List)model.getProperty(JAVA_PROJECT_LIST);
			if( !javaProjectsList.isEmpty()){
				
				for( int i=0; i< javaProjectsList.size(); i++){
					IProject proj = (IProject) javaProjectsList.get(i);
				    IDataModel migrationdm = DataModelFactory.createDataModel( new JavaProjectMigrationDataModelProvider());
					migrationdm.setProperty(IJavaProjectMigrationDataModelProperties.PROJECT_NAME, proj.getName());
					migrationdm.getDefaultOperation().execute(monitor, null);
					
					
					IDataModel refdm = DataModelFactory.createDataModel( new CreateReferenceComponentsDataModelProvider());
		            List targetCompList = (List) refdm.getProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST);
					
					IVirtualComponent targetcomponent = ComponentCore.createComponent(proj, proj.getName());
					targetCompList.add(targetcomponent);
					
					refdm.setProperty(ICreateReferenceComponentsDataModelProperties.SOURCE_COMPONENT, component);
					refdm.setProperty(ICreateReferenceComponentsDataModelProperties.TARGET_COMPONENT_LIST, targetCompList);
					refdm.getDefaultOperation().execute(monitor, null);
				}
			}

		 } catch(Exception e) {
			 Logger.getLogger().log(e);
		 }
		 return OK_STATUS;
	}
    
	protected  void addResources(WorkbenchComponent component ){
		//Default
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = getDataModel().getIntProperty(COMPONENT_VERSION);
		return J2EEVersionUtil.getJ2EETextVersion(version);
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return null;
	}
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return null;
	}	

/*    protected void setupComponentType(String typeID) {
		IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        ComponentType componentType = ComponentcoreFactory.eINSTANCE.createComponentType();
        componentType.setComponentTypeId(typeID);
        componentType.setVersion(getVersion());
        StructureEdit.setComponentType(component, componentType);
    }*/
	
	public String getModuleName() {
		return getDataModel().getStringProperty(COMPONENT_NAME);
	}
	
	public String getModuleDeployName() {
		return getDataModel().getStringProperty(COMPONENT_DEPLOY_NAME);
	}
    protected List getProperties() {
        // TODO Auto-generated method stub
        return null;
    }
}