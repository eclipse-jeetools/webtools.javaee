/*
 * Created on Mar 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class EARComponentCreationOperation extends J2EEComponentCreationOperation {
	public EARComponentCreationOperation(EARComponentCreationDataModel dataModel) {
		super(dataModel);
	}

	public EARComponentCreationOperation() {
		super();
	}

	protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		String moduleName = (String)operationDataModel.getProperty(EARComponentCreationDataModel.MODULE_NAME);
		IFolder moduleFolder = getProject().getFolder(moduleName);
		if (!moduleFolder.exists()) {
			moduleFolder.create(true, true, null);
		}
		
		//should cache wbmodule when created instead of  searching ?
        ModuleCore moduleCore = null;
        WorkbenchComponent component = null;
        try {
            moduleCore = ModuleCore.getModuleCoreForRead(getProject());
            component = moduleCore.findWorkbenchModuleByDeployName(
            		operationDataModel.getStringProperty(EARComponentCreationDataModel.MODULE_DEPLOY_NAME));
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
        }		

        EARArtifactEdit edit = null;
       	try{
       		edit = EARArtifactEdit.getEARArtifactEditForWrite(component);
       		edit.createModelRoot();
       	}
       	catch(Exception e){
            e.printStackTrace();
       	} finally {
       		if(edit != null)
       			edit.dispose();
       	}					
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.execute( IModuleConstants.JST_EAR_MODULE, monitor );
	}
    
	protected  void addResources( WorkbenchComponent component ){
		addResource(component, getModuleRelativeFile(getWebContentSourcePath( getModuleName() ), getProject()), getWebContentDeployPath());
		addResource(component, getModuleRelativeFile(getJavaSourceSourcePath( getModuleName() ), getProject()), getJavaSourceDeployPath());		
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceSourcePath(String moduleName) {
		return "/" + moduleName +"/JavaSource"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getJavaSourceDeployPath() {
		return "/WEB-INF/classes"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getWebContentSourcePath(String moduleName) {
		return "/" + moduleName + "/WebContent"; //$NON-NLS-1$
	}
	
	/**
	 * @return
	 */
	public String getWebContentDeployPath() {
		return "/"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation#createProjectStructure()
	 */
	protected void createProjectStructure() throws CoreException {
		// TODO Auto-generated method stub
		
	}
}