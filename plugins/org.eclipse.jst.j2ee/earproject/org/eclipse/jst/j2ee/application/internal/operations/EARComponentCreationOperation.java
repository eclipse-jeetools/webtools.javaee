/*
 * Created on Mar 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;
import org.eclipse.wst.common.modulecore.resources.IVirtualContainer;
import org.eclipse.wst.common.modulecore.resources.IVirtualFolder;
import org.eclipse.emf.common.util.URI;

public class EARComponentCreationOperation extends J2EEComponentCreationOperation {
	public EARComponentCreationOperation(EARComponentCreationDataModel dataModel) {
		super(dataModel);
	}

	public EARComponentCreationOperation() {
		super();
	}
    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#createAndLinkJ2EEComponents()
     */
    protected void createAndLinkJ2EEComponents() throws CoreException {
        IVirtualContainer component = ModuleCore.create(getProject(), getModuleDeployName());
        component.commit();
		//create and link META-INF folder
		IVirtualFolder root = component.getFolder(new Path("/")); //$NON-NLS-1$		
		root.createLink(new Path("/" + getModuleName()), 0, null);
		
    	IVirtualFolder metaInfFolder = root.getFolder(J2EEConstants.META_INF);
    	metaInfFolder.create(IResource.FORCE, null);
    }
	
	public IProject getProject() {
		String projName = operationDataModel.getStringProperty(EARComponentCreationDataModel.PROJECT_NAME );
		return ProjectUtilities.getProject( projName );
	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        ModuleCore moduleCore = null;
        EARArtifactEdit earEdit = null;
        try {
        	EARComponentCreationDataModel dm = (EARComponentCreationDataModel)getOperationDataModel();
            moduleCore = ModuleCore.getModuleCoreForWrite(getProject());
            WorkbenchComponent earComp = moduleCore.findWorkbenchModuleByDeployName(operationDataModel.getStringProperty(EARComponentCreationDataModel.COMPONENT_DEPLOY_NAME));
            earEdit = EARArtifactEdit.getEARArtifactEditForWrite(earComp);
            Integer version = (Integer)operationDataModel.getProperty(AppClientComponentCreationDataModel.COMPONENT_VERSION);
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

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		J2EEComponentCreationDataModel dataModel = (J2EEComponentCreationDataModel) operationDataModel;
		createAndLinkJ2EEComponents();
		setupComponentType(IModuleConstants.JST_EAR_MODULE);
		if (dataModel.getBooleanProperty(J2EEComponentCreationDataModel.CREATE_DEFAULT_FILES)) {
			createDeploymentDescriptor(monitor);
		}
		addModulesToEAR(monitor);
	}
	
	private void addModulesToEAR(IProgressMonitor monitor) {
		try{
			AddComponentToEnterpriseApplicationDataModel dm = new AddComponentToEnterpriseApplicationDataModel();
			dm.setProperty(AddComponentToEnterpriseApplicationDataModel.PROJECT_NAME, 
					getOperationDataModel().getProperty(EARComponentCreationDataModel.PROJECT_NAME));
			dm.setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_PROJECT_NAME, 
					getOperationDataModel().getProperty(EARComponentCreationDataModel.PROJECT_NAME));
			dm.setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME, 
					getOperationDataModel().getProperty(EARComponentCreationDataModel.COMPONENT_NAME));
			List modulesList = (List)getOperationDataModel().getProperty(EARComponentCreationDataModel.J2EE_COMPONENT_LIST);
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
		int version = operationDataModel.getIntProperty(J2EEComponentCreationDataModel.COMPONENT_VERSION);
		return J2EEVersionUtil.getJ2EETextVersion(version);
	}
	public URI getComponentHandle(){
        ModuleCore moduleCore = null;

        try {
        	EARComponentCreationDataModel dm = (EARComponentCreationDataModel)getOperationDataModel();
            moduleCore = ModuleCore.getModuleCoreForRead(getProject());
            WorkbenchComponent earComp = moduleCore.findWorkbenchModuleByDeployName(operationDataModel.getStringProperty(EARComponentCreationDataModel.COMPONENT_DEPLOY_NAME));
            return earComp.getHandle();

        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }

        }		
	}	
}