/*
 * Created on Feb 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.web.deployables;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.resources.IContainer;
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
		IPath projectPath = moduleModel.getProject().getFullPath();
		URI outputContainerURI = (URI)dataModel.getProperty(WebAppDeployableModuleBuilderDataModel.OUTPUT_CONTAINER);
		IPath absoluteOCP = projectPath.append(outputContainerURI.toString());
		IFolder outputContainerFolder = createFolder(absoluteOCP);

		// copy resources
		List resourceList = workbenchModule.getResources();
		for (int i = 0; i < resourceList.size(); i++) {
			WorkbenchModuleResource wmr = (WorkbenchModuleResource)resourceList.get(i);
			URI deployURI = wmr.getDeployedPath();
			IPath deployPath = absoluteOCP.append(deployURI.toString());
			URI sourceURI = wmr.getSourcePath();
			IPath sourcePath = new Path(sourceURI.toString());
			IResource resource = getWorkspace().getRoot().getFolder(sourcePath);
			if (resource == null || !(resource instanceof IFolder)) {
				resource = getWorkspace().getRoot().getFile(sourcePath);
			}
			if (resource == null)
				continue;
			IPath parentPath = deployPath.removeLastSegments(1);
			createFolder(parentPath);
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
			// check if the parent is there
			IContainer parent = folder.getParent();
			if (parent != null && !parent.exists() && (parent instanceof IFolder)) {
				((IFolder)parent).create(true, true, null);
			}
			if (!folder.exists())
				folder.create(true, true, null);
			return folder;
		}
		return null;
	}


}
