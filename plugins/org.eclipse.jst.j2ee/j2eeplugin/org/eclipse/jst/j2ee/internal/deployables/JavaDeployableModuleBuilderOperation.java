/*
 * Created on Feb 3, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.deployables;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.jem.util.emf.workbench.JavaProjectUtilities;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPluginResourceHandler;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.builder.DeployableModuleBuilder;
import org.eclipse.wst.common.modulecore.internal.builder.DeployableModuleBuilderDataModel;

/**
 * @author jialin
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JavaDeployableModuleBuilderOperation extends WTPOperation {
	
	public JavaDeployableModuleBuilderOperation(JavaDeployableModuleBuilderDataModel dataModel) {
		super(dataModel);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.frameworks.internal.operations.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		// preparation
		JavaDeployableModuleBuilderDataModel dataModel = (JavaDeployableModuleBuilderDataModel) operationDataModel;
		WorkbenchComponent workbenchModule = (WorkbenchComponent)dataModel.getProperty(DeployableModuleBuilderDataModel.WORKBENCH_MODULE);
		
		IProject project = (IProject)dataModel.getProperty(DeployableModuleBuilderDataModel.PROJECT);
		IPath projectPath = project.getFullPath();
		IJavaProject javaProj = JavaProjectUtilities.getJavaProject(project);
		List javaSourceFolderList = JavaProjectUtilities.getSourceContainers(project);
		
		// create output container folder if it does not exist
		IFolder outputContainer = (IFolder)dataModel.getProperty(DeployableModuleBuilderDataModel.OUTPUT_CONTAINER);
		if(!outputContainer.exists())
			createFolder(outputContainer);
		
		IPath outputContainerPath = outputContainer.getFullPath();

		// copy resources except the java source folder
		List resourceList = workbenchModule.getResources();
		List javaOutputPathList = new ArrayList();
		for (int i = 0; i < resourceList.size(); i++) {
			ComponentResource wmr = (ComponentResource)resourceList.get(i);
			URI sourceURI = wmr.getSourcePath();
			IPath sourcePath = new Path(sourceURI.toString());
			IResource sourceResource =  ModuleCore.getEclipseResource(wmr);
			if (sourceResource == null)
				continue;
			URI deployURI = wmr.getRuntimePath();
			IPath deployPath = outputContainerPath.append(deployURI.toString());
			// check if it is a java source folder
			if (javaSourceFolderList.contains(sourceResource)) {
				// check if there are nested java output paths. if so, abort.
				for (int j = 0; j < javaOutputPathList.size(); j++) {
					IPath path = (IPath)javaOutputPathList.get(j);
					if (path.isPrefixOf(deployPath) || deployPath.isPrefixOf(path)) {
						// add a problem marker
						IResource wtpmoduleFile = project.findMember(".wtpmodules"); //$NON-NLS-1$
						wtpmoduleFile.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);      
						IMarker m = wtpmoduleFile.createMarker(IMarker.PROBLEM);
						String msg = J2EEPluginResourceHandler.getString("NESTED_JAVA_OUTPUT_ERROR"); //$NON-NLS-1$
						m.setAttribute(IMarker.MESSAGE, msg);
						m.setAttribute(IMarker.SEVERITY,IMarker.SEVERITY_ERROR);
						return;
					}
				}
				// add deployPath to list
				javaOutputPathList.add(deployPath);
				continue;
			}
			// create parent folders for deploy folder if not exist
			IPath parentPath = deployPath.removeLastSegments(1);
			createFolder(parentPath);
			DeployableModuleBuilder.smartCopy(sourceResource, deployPath, new NullProgressMonitor());
		}

		// set Java specific output path, do it after resource copy
		IClasspathEntry[] cpe = javaProj.getRawClasspath();
		boolean classpathModified = false;
		for (int i = 0; i < resourceList.size(); i++) {
			ComponentResource wmr = (ComponentResource)resourceList.get(i);
			URI sourceURI = wmr.getSourcePath();
			IPath sourcePath = new Path(sourceURI.toString());
			IResource sourceResource = ModuleCore.getEclipseResource(wmr);
			// check if it is a java source folder
			if (javaSourceFolderList.contains(sourceResource)) {
				// get the classpath entry
				int index = -1;
				for (int j = 0; j < cpe.length; j++) {
					if (cpe[j].getPath().equals(sourcePath)) {
						index = j;
						break;
					}
				}
				URI deployURI = wmr.getRuntimePath();
				IPath classFilesPath = outputContainerPath.append(deployURI.toString());
				// check if the classpath is modified. Use relative path to avoid 
				// the problem that drive letter could be upper or lower case
				IPath relativeClassFilesPath = classFilesPath.makeRelative();
				IPath oldClassFilesPath = ((ClasspathEntry)cpe[index]).specificOutputLocation;
				IPath oldRelativeClassFilesPath = null;
				if (oldClassFilesPath != null)
				oldRelativeClassFilesPath = oldClassFilesPath.makeRelative();
				if (!relativeClassFilesPath.equals(oldRelativeClassFilesPath)) {
					((ClasspathEntry)cpe[index]).specificOutputLocation = classFilesPath;
					classpathModified = true;
				}
				createFolder(classFilesPath);
			}
		}
		// update classpath only when it is modified
		if (classpathModified)
			javaProj.setRawClasspath(cpe, new NullProgressMonitor());
	}

	/**
	 * @param outputContainer
	 */
	private void createFolder(IFolder outputContainer) {
		IContainer parentContainer = outputContainer.getParent();
		if(parentContainer != null && !parentContainer.exists() && parentContainer.getType() == IResource.FOLDER) {			
			createFolder((IFolder)outputContainer.getParent());
		}
		try {
			if(!outputContainer.exists())
				outputContainer.create(true, true, null);
		} catch (CoreException e) { 
			e.printStackTrace();
		}
		
	}

	/**
	 * Get resource for given absolute path
	 * 
	 * @exception com.ibm.itp.core.api.resources.CoreException
	 */
	private IResource getResource(IPath absolutePath) throws CoreException {
		IResource resource = null;
		if (absolutePath != null && !absolutePath.isEmpty()) 
			resource = ResourcesPlugin.getWorkspace().getRoot().findMember(absolutePath);
		return resource;
	}

	 
	/**
	 * Create a folder for given absolute path
	 * 
	 * @exception com.ibm.itp.core.api.resources.CoreException
	 */
	private IFolder createFolder(IPath absolutePath) throws CoreException {
		if (absolutePath == null || absolutePath.isEmpty())
			return null;
		IFolder folder = getWorkspace().getRoot().getFolder(absolutePath);
		// check if the parent is there
		IContainer parent = folder.getParent();
		if (parent != null && !parent.exists() && (parent instanceof IFolder))
			createFolder(parent.getFullPath());
		if (!folder.exists())
			folder.create(true, true, new NullProgressMonitor());
		return folder;
	}
}
