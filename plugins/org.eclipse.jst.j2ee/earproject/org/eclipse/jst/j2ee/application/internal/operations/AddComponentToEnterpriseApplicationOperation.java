package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEditOperation;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreFactory;
import org.eclipse.wst.common.modulecore.ReferencedComponent;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;

public class AddComponentToEnterpriseApplicationOperation extends EARArtifactEditOperation {
	public static final String metaInfFolderDeployPath = "/"; //$NON-NLS-1$

	public AddComponentToEnterpriseApplicationOperation(AddComponentToEnterpriseApplicationDataModel operationDataModel) {
		super(operationDataModel);
	}
	
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
			addComponentToEnterpriseApplication(monitor);
	}

	private void addComponentToEnterpriseApplication(IProgressMonitor monitor) {
		ModuleCore moduleCore = null;
		try{
			String earProj = operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.EAR_PROJECT_NAME);
			IProject proj = ProjectUtilities.getProject(earProj);
			
			moduleCore = ModuleCore.getModuleCoreForWrite(proj);
			WorkbenchComponent earComp = moduleCore.findWorkbenchModuleByDeployName(operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME));
			
			AddComponentToEnterpriseApplicationDataModel dm = (AddComponentToEnterpriseApplicationDataModel)getOperationDataModel();
			URI runtimeURI = URI.createURI(metaInfFolderDeployPath);
			List list = (List)dm.getProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_LIST);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ReferencedComponent rc = ModuleCoreFactory.eINSTANCE.createReferencedComponent();
					WorkbenchComponent wc = (WorkbenchComponent)list.get(i);
					rc.setHandle(wc.getHandle());
					rc.setRuntimePath(runtimeURI);
					earComp.getReferencedComponents().add(rc);
				}
			}
			moduleCore.saveIfNecessary(monitor); 
		 }  finally {
		       if (null != moduleCore) {
		            moduleCore.dispose();
		       }
		 }		
	}
	public IProject getProject() {
		String projName = operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.PROJECT_NAME );
		return ProjectUtilities.getProject( projName );
	}

}
