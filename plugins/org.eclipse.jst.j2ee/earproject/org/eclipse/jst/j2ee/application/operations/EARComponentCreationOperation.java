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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
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
        EARArtifactEdit edit = null;
        try {
            moduleCore = ModuleCore.getModuleCoreForWrite(getProject());
            component = moduleCore.findWorkbenchModuleByDeployName(
            		operationDataModel.getStringProperty(EARComponentCreationDataModel.MODULE_DEPLOY_NAME));
       		edit = EARArtifactEdit.getEARArtifactEditForWrite(component);
       		Object versionObj = ((J2EECreationDataModel)getOperationDataModel()).getProperty(J2EECreationDataModel.J2EE_MODULE_VERSION);
       		edit.createModelRoot(((Integer)versionObj).intValue());
			// specify module source folder, and deploy path
			IPath metaInfPath = new Path("META-INF"); //$NON-NLS-1$
		    IFolder metaInfFolder = moduleFolder.getFolder(metaInfPath); //$NON-NLS-1$
		    String metaInfFolderDeployPath = "/"; //$NON-NLS-1$
		    addResource(component, metaInfFolder, metaInfFolderDeployPath);
			moduleCore.saveIfNecessary(null); 
        } finally {
            if (null != moduleCore) {
                moduleCore.dispose();
            }
       		if(edit != null)
       			edit.dispose();
        }		
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.execute( IModuleConstants.JST_EAR_MODULE, monitor );
	}
    
	protected  void addResources(WorkbenchComponent component ){
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation#createProjectStructure()
	 */
	protected void createProjectStructure() throws CoreException {
		// TODO Auto-generated method stub
		
	}
}