package org.eclipse.jst.j2ee.application.internal.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.modulecore.util.EARArtifactEditOperation;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;

public class AddComponentToEnterpriseApplicationOperation extends EARArtifactEditOperation {
	public static final String metaInfFolderDeployPath = "/"; //$NON-NLS-1$

	public AddComponentToEnterpriseApplicationOperation(AddComponentToEnterpriseApplicationDataModel operationDataModel) {
		super(operationDataModel);
	}
	
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
			addComponentToEnterpriseApplication(monitor);
	}

	private void addComponentToEnterpriseApplication(IProgressMonitor monitor) {
		StructureEdit moduleCore = null;
		try{
			String earProj = operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.EAR_PROJECT_NAME);
			IProject proj = ProjectUtilities.getProject(earProj);
			
			moduleCore = StructureEdit.getStructureEditForWrite(proj);
			WorkbenchComponent earComp = moduleCore.findComponentByName(operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME));
			
			AddComponentToEnterpriseApplicationDataModel dm = (AddComponentToEnterpriseApplicationDataModel)getOperationDataModel();
			IPath runtimePath = new Path(metaInfFolderDeployPath);
			List list = (List)dm.getProperty(AddComponentToEnterpriseApplicationDataModel.MODULE_LIST);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					ReferencedComponent rc = ComponentcoreFactory.eINSTANCE.createReferencedComponent();
					WorkbenchComponent wc = (WorkbenchComponent)list.get(i);
					rc.setHandle(normalize(earComp, wc));
					rc.setRuntimePath(runtimePath);
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
	private URI normalize(WorkbenchComponent aComponent, WorkbenchComponent aReferencedComponent) {   
		// same project doesn't matter
		if(StructureEdit.getContainingProject(aComponent) == StructureEdit.getContainingProject(aReferencedComponent)) 
			return aReferencedComponent.getHandle();
		// different project, fully qualify
		return ModuleURIUtil.fullyQualifyURI(aReferencedComponent);
							
	}

	public IProject getProject() {
		String projName = operationDataModel.getStringProperty(AddComponentToEnterpriseApplicationDataModel.PROJECT_NAME );
		return ProjectUtilities.getProject( projName );
	}

}
