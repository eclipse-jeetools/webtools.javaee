/*
 * Created on Feb 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.deployables;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.wst.common.modulecore.ModuleStructuralModel;
import org.eclipse.wst.common.modulecore.WorkbenchModule;
import org.eclipse.wst.common.modulecore.WorkbenchModuleResource;
import org.eclipse.wst.common.modulecore.builder.DeployableModuleBuilderOperation;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WebAppDeployableModuleBuilderOperation extends DeployableModuleBuilderOperation {
	
	public WebAppDeployableModuleBuilderOperation(WebAppDeployableModuleBuilderDataModel dataModel) {
		super(dataModel);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		WebAppDeployableModuleBuilderDataModel dataModel = (WebAppDeployableModuleBuilderDataModel) operationDataModel;
		ModuleStructuralModel moduleModel = (ModuleStructuralModel)dataModel.getProperty(WebAppDeployableModuleBuilderDataModel.MODULE_STRUCTURAL_MODEL);
		WorkbenchModule workbenchModule = (WorkbenchModule)dataModel.getProperty(WebAppDeployableModuleBuilderDataModel.WORKBENCH_MODULE);
		String deployedName = workbenchModule.getDeployedName();

		// create output container folder if it does not exist
		Path absoluteOCP = (Path)dataModel.getProperty(WebAppDeployableModuleBuilderDataModel.OUTPUT_CONTAINER);
		IFolder outputContainerFolder = createFolder(absoluteOCP);

		// create deployed module folder
		IPath absoluteDMP = absoluteOCP.append(deployedName);
		IFolder deployedModuleFolder = createFolder(absoluteDMP);

		// copy resources
		IPath projectPath = moduleModel.getProject().getFullPath();
		List resourceList = workbenchModule.getResources();
		for (int i = 0; i < resourceList.size(); i++) {
			WorkbenchModuleResource wmr = (WorkbenchModuleResource)resourceList.get(i);
			URI sourceURI = wmr.getSourcePath();
			IPath sourcePath = projectPath.append(sourceURI.toFileString());
			IResource resource = getWorkspace().getRoot().getFolder(sourcePath);
			if (resource == null) {
				resource =  getWorkspace().getRoot().getFile(sourcePath);
			}
			URI deployURI = wmr.getDeployedPath();
			IPath deployPath = absoluteDMP.append(deployURI.toFileString());
			resource.copy(deployPath, true, new NullProgressMonitor());
		}
	}

	/**
	 * Create a folder relative to the project based on aProjectRelativePath
	 * 
	 * @exception com.ibm.itp.core.api.resources.CoreException
	 */
	public IFolder createFolder(IPath absolutePath) throws CoreException {
		if (absolutePath != null && !absolutePath.isEmpty()) {
			IFolder folder = getWorkspace().getRoot().getFolder(absolutePath);
			if (!folder.exists())
				folder.create(true, true, null);
			return folder;
		}
		return null;
	}


}
