/*
 * Created on Mar 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.internal.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ReferencedComponent;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.impl.ModuleCoreFactoryImpl;
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
        EARArtifactEdit edit = null;
        try {
        	EARComponentCreationDataModel dm = (EARComponentCreationDataModel)getOperationDataModel();
            moduleCore = ModuleCore.getModuleCoreForWrite(getProject());
            WorkbenchComponent earComp = moduleCore.findWorkbenchModuleByDeployName(
            		operationDataModel.getStringProperty(EARComponentCreationDataModel.MODULE_DEPLOY_NAME));
       		edit = EARArtifactEdit.getEARArtifactEditForWrite(earComp);
       		int versionId = ((J2EECreationDataModel)getOperationDataModel()).getIntProperty(J2EECreationDataModel.J2EE_MODULE_VERSION);
       		edit.createModelRoot(versionId);
       		// set version to WorkbenchComponent
       		String versionText = J2EEVersionUtil.getJ2EETextVersion(versionId);
       		earComp.getComponentType().setVersion(versionText);
			// specify module source folder, and deploy path
			IPath metaInfPath = new Path("META-INF"); //$NON-NLS-1$
		    IFolder metaInfFolder = moduleFolder.getFolder(metaInfPath); //$NON-NLS-1$
		    String metaInfFolderDeployPath = "/"; //$NON-NLS-1$
		    addResource(earComp, metaInfFolder, metaInfFolderDeployPath);
			// add components it contains to reference component list
			URI runtimeURI = URI.createURI(metaInfFolderDeployPath);
			List list = (List)dm.getProperty(EARComponentCreationDataModel.J2EE_COMPONENT_LIST);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ReferencedComponent rc = ModuleCoreFactoryImpl.eINSTANCE.createReferencedComponent();
					WorkbenchComponent wc = (WorkbenchComponent)list.get(i);
					rc.setHandle(wc.getHandle());
					rc.setRuntimePath(runtimeURI);
					earComp.getReferencedComponents().add(rc);
				}
			}
			// save
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
		J2EEComponentCreationDataModel dataModel = (J2EEComponentCreationDataModel) operationDataModel;
		createProjectStructure();
		createComponent(IModuleConstants.JST_EAR_MODULE, monitor);
		if (dataModel.getBooleanProperty(J2EEComponentCreationDataModel.CREATE_DEFAULT_FILES)) {
			createDeploymentDescriptor(monitor);
		}
	}
    
	protected  void addResources(WorkbenchComponent component ){
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation#createProjectStructure()
	 */
	protected void createProjectStructure() throws CoreException {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEComponentCreationOperation#getVersion()
	 */
	protected String getVersion() {
		int version = operationDataModel.getIntProperty(J2EEComponentCreationDataModel.J2EE_MODULE_VERSION);
		return J2EEVersionUtil.getJ2EETextVersion(version);
	}
}