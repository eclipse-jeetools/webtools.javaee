package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.ComponentType;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class EARComponentCreationOp extends AbstractDataModelOperation {

	public EARComponentCreationOp() {
		super();
	}
	public EARComponentCreationOp(IDataModel model) {
		super(model); 
	}
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#createAndLinkJ2EEComponents()
     */
    protected void createAndLinkJ2EEComponents() throws CoreException {
		IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        component.create(0, null);
		//create and link META-INF folder
		IVirtualFolder root = component.getFolder(new Path("/")); //$NON-NLS-1$		
		root.createLink(new Path("/" + getModuleName()), 0, null);
		
    	IVirtualFolder metaInfFolder = root.getFolder(J2EEConstants.META_INF);
    	metaInfFolder.create(IResource.FORCE, null);
    }
	
	public IProject getProject() {
		String projName = getDataModel().getStringProperty(EARComponentCreationDataModel.PROJECT_NAME );
		return ProjectUtilities.getProject( projName );
	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        StructureEdit moduleCore = null;
        EARArtifactEdit earEdit = null;
        try {
        	EARComponentCreationDataModel dm = (EARComponentCreationDataModel)getDataModel();
            moduleCore = StructureEdit.getStructureEditForWrite(getProject());
            WorkbenchComponent earComp = moduleCore.findComponentByName(
					getDataModel().getStringProperty(EARComponentCreationDataModel.COMPONENT_DEPLOY_NAME));
            earEdit = EARArtifactEdit.getEARArtifactEditForWrite(earComp);
            Integer version = (Integer)getDataModel().getProperty(IComponentCreationDataModelProperties.COMPONENT_VERSION);
       	 	earEdit.createModelRoot(version.intValue());
            earEdit.save(monitor);
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
       		if (earEdit != null)
       		    earEdit.dispose();
        }		
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		try {
			createAndLinkJ2EEComponents();
			setupComponentType(IModuleConstants.JST_EAR_MODULE);
			if (getDataModel().getBooleanProperty(J2EEComponentCreationDataModel.CREATE_DEFAULT_FILES)) {
				createDeploymentDescriptor(monitor);
			}
			addModulesToEAR(monitor);
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
		return null;
	}
	
	private void addModulesToEAR(IProgressMonitor monitor) {
		try{
			AddComponentToEnterpriseApplicationDataModel dm = new AddComponentToEnterpriseApplicationDataModel();
			dm.setProperty(AddComponentToEnterpriseApplicationDataModel.PROJECT_NAME, 
					getDataModel().getProperty(EARComponentCreationDataModel.PROJECT_NAME));
			dm.setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_PROJECT_NAME, 
					getDataModel().getProperty(EARComponentCreationDataModel.PROJECT_NAME));
			dm.setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME, 
					getDataModel().getProperty(EARComponentCreationDataModel.COMPONENT_NAME));
			List modulesList = (List)getDataModel().getProperty(EARComponentCreationDataModel.J2EE_COMPONENT_LIST);
			if (modulesList != null && !modulesList.isEmpty()) {
				dm.setProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_LIST,modulesList);
				AddComponentToEnterpriseApplicationOperation addModuleOp = (AddComponentToEnterpriseApplicationOperation)dm.getDefaultOperation();
				addModuleOp.execute(monitor);
		   }
		 } catch(Exception e) {
			 Logger.getLogger().log(e);
		 }
	}
    
	protected  void addResources(WorkbenchComponent component ){
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = getDataModel().getIntProperty(IComponentCreationDataModelProperties.COMPONENT_VERSION);
		return J2EEVersionUtil.getJ2EETextVersion(version);
	}
	public URI getComponentHandle(){
        StructureEdit moduleCore = null;

        try {
            moduleCore = StructureEdit.getStructureEditForRead(getProject());
            WorkbenchComponent earComp = moduleCore.findComponentByName(getDataModel().getStringProperty(EARComponentCreationDataModel.COMPONENT_DEPLOY_NAME));
            return earComp.getHandle();

        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }

        }		
	}
	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}
	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}	

    protected void setupComponentType(String typeID) {
		IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleDeployName());
        ComponentType componentType = ComponentcoreFactory.eINSTANCE.createComponentType();
        componentType.setComponentTypeId(typeID);
        componentType.setVersion(getVersion());
        StructureEdit.setComponentType(component, componentType);
    }
	
	public String getModuleName() {
		return getDataModel().getStringProperty(J2EEComponentCreationDataModel.COMPONENT_NAME);
	}
	
	public String getModuleDeployName() {
		return getDataModel().getStringProperty(J2EEComponentCreationDataModel.COMPONENT_DEPLOY_NAME);
	}
}