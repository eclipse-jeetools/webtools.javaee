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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.modulecore.ModuleStructuralModel;
import org.eclipse.wst.common.modulecore.WorkbenchModule;
import org.eclipse.wst.common.modulecore.WorkbenchModuleResource;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WebAppDeployableModuleBuilderOperation extends WTPOperation {
	
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
		Path outputContainerPath = (Path)dataModel.getProperty(WebAppDeployableModuleBuilderDataModel.OUTPUT_CONTAINER);
		IProject parentProject = moduleModel.getProject();
		IPath projectPath = parentProject.getFullPath();
		IPath absoluteOCP = projectPath.append(outputContainerPath);
		IFolder outputContainerFolder = createFolder(absoluteOCP);

		// create deployed module folder
		IPath absoluteDMP = absoluteOCP.append(deployedName);
		IFolder deployedModuleFolder = createFolder(absoluteDMP);

		List resourceList = workbenchModule.getResources();
		for (int i = 0; i < resourceList.size(); i++) {
			WorkbenchModuleResource wmr = (WorkbenchModuleResource)resourceList.get(i);
			URI uri = wmr.getSourcePath();
			IPath path = projectPath.append(uri.toFileString());
			IResource resource = getWorkspace().getRoot().getFolder(path);
			if (resource == null) {
				resource =  getWorkspace().getRoot().getFile(path);
			}
			resource.copy(absoluteDMP, true, new NullProgressMonitor());
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
