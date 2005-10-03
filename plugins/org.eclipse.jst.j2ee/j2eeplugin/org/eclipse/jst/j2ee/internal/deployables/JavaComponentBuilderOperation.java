/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPluginResourceHandler;
import org.eclipse.wst.common.componentcore.datamodel.properties.IWorkbenchComponentBuilderDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.builder.ComponentStructuralBuilder;
import org.eclipse.wst.common.componentcore.internal.builder.WorkbenchComponentBuilderOperation;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class JavaComponentBuilderOperation extends WorkbenchComponentBuilderOperation implements IWorkbenchComponentBuilderDataModelProperties{
    /**
     * @param model
     */
    public JavaComponentBuilderOperation(IDataModel model) {
        super(model);
    }

    /* (non-Javadoc)
     * @see org.eclipse.core.commands.operations.IUndoableOperation#execute(org.eclipse.core.runtime.IProgressMonitor, org.eclipse.core.runtime.IAdaptable)
     */
    public IStatus execute(IProgressMonitor monitor, IAdaptable info) {
        StructureEdit sEdit = null;
		try {
            IVirtualComponent vComponent = (IVirtualComponent)model.getProperty(VIRTUAL_COMPONENT);
            sEdit = StructureEdit.getStructureEditForRead(vComponent.getProject());
            WorkbenchComponent wbComponent = sEdit.getComponent();
            
            IProject project = vComponent.getProject();
            //IPath projectPath = project.getFullPath();
            IJavaProject javaProj = JemProjectUtilities.getJavaProject(project);
            List javaSourceFolderList = JemProjectUtilities.getSourceContainers(project);
            
            
            // create output container folder if it does not exist
            IFolder outputContainer = (IFolder)model.getProperty(OUTPUT_CONTAINER);
            if(!outputContainer.exists())
            	createFolder(outputContainer);
            
            IPath outputContainerPath = outputContainer.getFullPath();

            // copy resources except the java source folder
            List resourceList = wbComponent.getResources();
            List javaOutputPathList = new ArrayList();
            for (int i = 0; i < resourceList.size(); i++) {
            	ComponentResource wmr = (ComponentResource)resourceList.get(i);  
            	IResource sourceResource =  StructureEdit.getEclipseResource(wmr);
            	if (sourceResource == null || sourceResource.getName().endsWith(".java") )
            		continue; 
            	IPath deployPath = outputContainerPath.append(wmr.getRuntimePath());
            	// check if it is a java source folder
            	if (javaSourceFolderList.contains(sourceResource)) {
            		// check if there are nested java output paths. if so, abort.
            		for (int j = 0; j < javaOutputPathList.size(); j++) {
            			IPath path = (IPath)javaOutputPathList.get(j);
            			if (!path.equals(deployPath) && (path.isPrefixOf(deployPath) || deployPath.isPrefixOf(path))) {

            				IResource wtpmoduleFile = project.findMember(".wtpmodules"); //$NON-NLS-1$ 
            				// add a problem marker    
            				IMarker m = wtpmoduleFile.createMarker(IMarker.PROBLEM);
            				String msg = J2EEPluginResourceHandler.getString("NESTED_JAVA_OUTPUT_ERROR"); //$NON-NLS-1$
            				m.setAttribute(IMarker.MESSAGE, msg);
            				m.setAttribute(IMarker.SEVERITY,IMarker.SEVERITY_ERROR);
            				return OK_STATUS;
            			}
            		}
            		// add deployPath to list
            		javaOutputPathList.add(deployPath);
            		continue;
            	}
            	// create parent folders for deploy folder if not exist
            	IPath parentPath = deployPath.removeLastSegments(1);
            	createFolder(parentPath);
            	ComponentStructuralBuilder.smartCopy(sourceResource, deployPath, new NullProgressMonitor());
            }

            // set Java specific output path, do it after resource copy
            IClasspathEntry[] cpe = javaProj.getRawClasspath();
            boolean classpathModified = false;
            for (int i = 0; i < resourceList.size(); i++) {
            	ComponentResource wmr = (ComponentResource)resourceList.get(i);  
            	IResource sourceResource = StructureEdit.getEclipseResource(wmr);
            	// check if it is a java source folder
            	if (javaSourceFolderList.contains(sourceResource)) {
            		// get the classpath entry
            		int index = -1;
            		for (int j = 0; j < cpe.length; j++) {
            			if (cpe[j].getPath().equals(sourceResource.getFullPath())) {
            				index = j;
            				break;
            			}
            		} 
            		IPath classFilesPath = outputContainerPath.append(wmr.getRuntimePath());
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
        } catch (JavaModelException e) {
            Logger.getLogger().log(e.getMessage());
        } catch (CoreException e) {
            Logger.getLogger().log(e.getMessage());
        } finally{
        	if(sEdit != null){
        		sEdit.dispose();
        	}
        }
		return OK_STATUS;
    }
}
