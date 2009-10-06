/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.SingleRootStatus;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.Property;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class SingleRootUtil {
	
	/** 
	 * Used to return immediately after the first error code is found.
	 */
	public static final int INCLUDE_FIRST_ERROR = 0x08;
	/** 
	 * Used to capture all status codes (error, info, warning).
	 */
	public static final int INCLUDE_ALL = 0x07;
	/** 
	 * Used to capture all error and warning status codes.
	 */
	public static final int INCLUDE_ERRORS_AND_WARNINGS = 0x06;
	/** 
	 * Used to capture all error status codes.
	 */
	public static final int INCLUDE_ERRORS = 0x04;
	
	private static final int WARNINGS = 0x02;
	private static final int INFO = 0x01;
	private static final int NONE = 0x0;
	private static String USE_SINGLE_ROOT_PROPERTY = "useSingleRoot"; //$NON-NLS-1$
	private IVirtualComponent component;
	private IPackageFragmentRoot[] cachedSourceContainers;
	private IContainer[] cachedOutputContainers;
	private boolean isSingleJavaOutputNonSource;
	private MultiStatus wrapperStatus;
	private int INCLUDE_FLAG;
	
	public SingleRootUtil(IVirtualComponent component) {
		this.component = component;
	}
	
	/**
	 * Returns true if this module has a simple structure based on a
	 * single root folder, and false otherwise.
     *
	 * In a single root structure, all files that are contained within the root folder
	 * are part of the module, and are already in the correct module structure. No
	 * module resources exist outside of this single folder.
	 * 
	 * @return true if this module has a single root structure, and
	 *         false otherwise
	 */
	public boolean isSingleRoot() {
		return validateSingleRoot(INCLUDE_FIRST_ERROR).getSeverity() != IStatus.ERROR;
	}

	/**
	 *  Validates whether the component module has a single root structure.
	 *  An IStatus with a severity of OK is returned for a valid single root 
	 *  structure.  A MultiStatus containing children of type ISingleRootStatus
	 *  is returned if any status codes were captured during the validation.
	 *  A MultiStatus with a severity of INFO or WARNING is returned for a valid 
	 *  single root structure containing status codes with no severities of ERROR.
	 *  A MultiStatus with a severity of ERROR means the component does not have a
	 *  valid single root structure.
	 *  
	 * @param flag - indicates the status codes (by severity) to capture 
	 *               during the validation.  
	 *               Valid flags are: INCLUDE_FIRST_ERROR
	 *               				  INCLUDE_ALL
	 *                                INCLUDE_ERRORS_AND_WARNINGS
	 *                                INCLUDE_ERRORS
	 *               
	 * @return IStatus
	 */
	public IStatus validateSingleRoot(int flag) {
		INCLUDE_FLAG = flag;
		isSingleJavaOutputNonSource = false;
		wrapperStatus = null;
		StructureEdit edit = null;
		try {
			edit = StructureEdit.getStructureEditForRead(getProject());
			if (edit == null || edit.getComponent() == null) {
				reportStatus(ISingleRootStatus.NO_COMPONENT_FOUND);
				return getStatus();
			}
		
			WorkbenchComponent wbComp = edit.getComponent();
			List resourceMaps = wbComp.getResources();
			
			// 229650 - check to see if the property 'useSingleRoot' is defined. If it is set and
			// the value of the property is true then it will override the logic checks below
			final List componentProperties = wbComp.getProperties();
			if (componentProperties != null) {
				final Iterator componentPropertiesIterator = componentProperties.iterator();
				while (componentPropertiesIterator.hasNext()) {
					Property wbProperty = (Property) componentPropertiesIterator.next();
					if (USE_SINGLE_ROOT_PROPERTY.equals(wbProperty.getName())) {
						boolean useSingleRoot = Boolean.valueOf(wbProperty.getValue()).booleanValue();
						if (useSingleRoot) {
							return Status.OK_STATUS;
						}
					}
				}
			}
			
			if (JavaEEProjectUtilities.isEARProject(getProject())) {
				// Always return false for EARs so that members for EAR are always calculated and j2ee modules are filtered out
				reportStatus(ISingleRootStatus.EAR_PROJECT_FOUND);
			} else if (JavaEEProjectUtilities.isDynamicWebProject(getProject())) {
				//validate web projects for single root
				validateWebProject(resourceMaps);
			} 
			else if (JavaEEProjectUtilities.isEJBProject(getProject()) || JavaEEProjectUtilities.isJCAProject(getProject())
					|| JavaEEProjectUtilities.isApplicationClientProject(getProject()) || JavaEEProjectUtilities.isUtilityProject(getProject())) {
				
				validateProject(resourceMaps);
			}
			//return the current status
			return getStatus();
		} finally {
			cachedOutputContainers = null;
			cachedSourceContainers = null;
			if (edit != null)
				edit.dispose();
		}
	}

	private void validateProject(List resourceMaps) {
		// if there are any linked resources then this is not a singleroot module
		if (this.rootFoldersHaveLinkedContent()) {
			reportStatus(ISingleRootStatus.LINKED_RESOURCES_FOUND);
			if (INCLUDE_FLAG == NONE) return;
		}
		
		// Ensure there are only source folder component resource mappings to the root content folder
		if (isRootResourceMapping(resourceMaps)) {
			IContainer[] javaOutputFolders = getJavaOutputFolders();
			// Verify only one java outputfolder
			if (javaOutputFolders.length==1) {
				// By the time we get here we know: for any folders defined as source in the 
				// .component file that they are also java source folders.
				if (JavaEEProjectUtilities.isUtilityProject(getProject()) || 
						JavaEEProjectUtilities.isEJBProject(getProject()) || 
						JavaEEProjectUtilities.isApplicationClientProject(getProject())) {
					
					if (!isSourceContainer(javaOutputFolders[0].getFullPath())) {
						// The single output folder is NOT a source folder so this is single-rooted. Since the
						// output folder (something like classes or bin) is not a source folder, JDT copies all files
						// (including non Java files) to this folder, so every resource needed at runtime is located 
						// in a single directory.
						isSingleJavaOutputNonSource  = true;
						return;
					} 
				}
				// Verify the java output folder is the same as one of the content roots
				IPath javaOutputPath = getJavaOutputFolders()[0].getProjectRelativePath();
				IContainer[] rootFolders = component.getRootFolder().getUnderlyingFolders();
				for (int i=0; i<rootFolders.length; i++) {
					IPath compRootPath = rootFolders[i].getProjectRelativePath();
					if (javaOutputPath.equals(compRootPath))
						return;
				}
				reportStatus(ISingleRootStatus.JAVA_OUTPUT_NOT_A_CONTENT_ROOT);
			}
			else {
				reportStatus(ISingleRootStatus.JAVA_OUTPUT_GREATER_THAN_1);
			}
		}
	}

	private void validateWebProject(List resourceMaps) {
		// if there are any linked resources then this is not a singleroot module
		if (this.rootFoldersHaveLinkedContent()) {
			reportStatus(ISingleRootStatus.LINKED_RESOURCES_FOUND);
			if (INCLUDE_FLAG == NONE) return;
		}
		
		// Ensure there are only basic component resource mappings -- one for the content folder 
		// and any for src folders mapped to WEB-INF/classes
		if (hasDefaultWebResourceMappings(resourceMaps)) {
			// Verify only one java output folder
			if (getJavaOutputFolders().length==1) {
				// Verify the java output folder is to <content root>/WEB-INF/classes
				IPath javaOutputPath = getJavaOutputFolders()[0].getProjectRelativePath();
				IPath compRootPath = component.getRootFolder().getUnderlyingFolder().getProjectRelativePath();
				if (compRootPath.append(J2EEConstants.WEB_INF_CLASSES).equals(javaOutputPath)) {
					return;
				}
				else {
					reportStatus(ISingleRootStatus.JAVA_OUTPUT_NOT_WEBINF_CLASSES);
				}
			}
			else {
				reportStatus(ISingleRootStatus.JAVA_OUTPUT_GREATER_THAN_1);
			}
		}
	}

	/**
	 * Returns the root folders containing Java output in this module.
	 * 
	 * @return a possibly-empty array of Java output folders
	 */
	public IContainer[] getJavaOutputFolders() {
		if (cachedOutputContainers == null)
			cachedOutputContainers = getJavaOutputFolders(getProject());
		return cachedOutputContainers;
	}
	
	public IContainer[] getJavaOutputFolders(IProject project) {
		if (project == null)
			return new IContainer[0];
		return J2EEProjectUtilities.getOutputContainers(project);
	}
	
	/**
	 * Checks if the path argument is to a source container for the project.
	 * 
	 * @param a workspace relative full path
	 * @return is path a source container?
	 */
	private boolean isSourceContainer(IPath path) {
		IPackageFragmentRoot[] srcContainers = getSourceContainers();
		for (int i = 0; i < srcContainers.length; i++) {
			if (srcContainers[i].getPath().equals(path))
				return true;
		}
		return false;
	}
	
	private IPackageFragmentRoot[] getSourceContainers() {
		if (cachedSourceContainers == null)
			cachedSourceContainers = J2EEProjectUtilities.getSourceContainers(getProject());
		return cachedSourceContainers;
	}
	
	/*
     * This method returns true if the root folders of this component have any linked resources (folder or file);
     * Otherwise false is returned.
     */
    private boolean rootFoldersHaveLinkedContent() {
    	if (this.component != null) {
    		final IContainer[] rootFolders = this.component.getRootFolder().getUnderlyingFolders();
    		for (int i = 0; i < rootFolders.length; i++) {
    			try {
    				boolean hasLinkedContent = this.hasLinkedContent(rootFolders[i]);
    				if (hasLinkedContent) {
    					return true;
    				}
    			}
    			catch (CoreException coreEx) {
    				J2EEPlugin.logError(coreEx);
    			}
    		}
    	}
    	return false;
    }
    
    /*
     * If the resource to check is a file then this method will return true if the file is linked. If the resource to
     * check is a folder then this method will return true if it, any of its sub directories, or any file contained
     * with-in this directory of any of it's sub directories are linked. Otherwise false is returned.
     */
    private boolean hasLinkedContent(final IResource resourceToCheck) throws CoreException {
    	if ((resourceToCheck != null) && resourceToCheck.isAccessible()) {
    		// skip non-accessible files
    		if (resourceToCheck.isLinked()) {
    			return true;
    		}
    		else {
    			switch (resourceToCheck.getType()) {
    				case IResource.FOLDER:
    					// recursively check sub directory contents
    					final IResource[] subDirContents = ((IFolder) resourceToCheck).members();
    					for (int i = 0; i < subDirContents.length; i++) {
    						if (hasLinkedContent(subDirContents[i])) {
    							return true;
    						}
    					}
    					break;
    				case IResource.FILE:
    					return resourceToCheck.isLinked();
    				default:
    					// skip as we only care about files and folders
    					break;
    			}
    		}
    	}
    	return false;
    }
    
    /**
	 * Ensure that any component resource mappings are for source folders and 
	 * that they map to the root content folder
	 * 
	 * @param resourceMaps
	 * @return boolean
	 */
	private boolean isRootResourceMapping(List resourceMaps) {
		// If the list is empty, return false
		if (resourceMaps.size() < 1) {
			reportStatus(ISingleRootStatus.NO_RESOURCE_MAPS_FOUND);
			return false;
		}
		
		for (int i=0; i < resourceMaps.size(); i++) {
			ComponentResource resourceMap = (ComponentResource) resourceMaps.get(i);
			// Verify it maps to "/" for the content root
			if (!resourceMap.getRuntimePath().equals(Path.ROOT)) {
				reportStatus(ISingleRootStatus.RUNTIME_PATH_NOT_ROOT, resourceMap);
				if (INCLUDE_FLAG == NONE) return false;
			}
			
			// verify it is also a src container
			IResource sourceResource = getProject().findMember(resourceMap.getSourcePath());
			if (sourceResource != null && sourceResource.exists()){
				IPath sourcePath = getProject().getFullPath().append(resourceMap.getSourcePath());
				if (!isSourceContainer(sourcePath))
					reportStatus(ISingleRootStatus.SOURCE_NOT_JAVA_CONTAINER, resourceMap);
			}
			else {
				reportStatus(ISingleRootStatus.SOURCE_PATH_NOT_FOUND, resourceMap);
			}
			if (INCLUDE_FLAG == NONE) return false;
		}
		return true;
	}
	
	/**
	 * Ensure the default web setup is correct with one resource map and any number of java 
	 * resource maps to WEB-INF/classes
	 * 
	 * @param resourceMaps
	 * @return boolean
	 */
	private boolean hasDefaultWebResourceMappings(List resourceMaps) {
		int rootValidMaps = 0;
		int javaValidRoots = 0;
		
		// If there aren't at least 2 maps, return false
		if (INCLUDE_FLAG == INCLUDE_FIRST_ERROR && resourceMaps.size() < 2) {
			reportStatus(ISingleRootStatus.ATLEAST_1_RESOURCE_MAP_MISSING);
			return false;
		}
		
		IPath webInfClasses = new Path(J2EEConstants.WEB_INF_CLASSES).makeAbsolute();
		for (int i = 0; i < resourceMaps.size(); i++) {
			ComponentResource resourceMap = (ComponentResource) resourceMaps.get(i);
			IPath sourcePath = getProject().getFullPath().append(resourceMap.getSourcePath());

			// Verify if the map is for the content root
			if (resourceMap.getRuntimePath().equals(Path.ROOT)) {
				rootValidMaps++;
			} 
			// Verify if the map is for a java src folder and is mapped to "WEB-INF/classes"
			else if (resourceMap.getRuntimePath().equals(webInfClasses)) {
				if (exists(sourcePath)) {
					if (isSourceContainer(sourcePath)) {
						javaValidRoots++;
					}
					else {
						reportStatus(ISingleRootStatus.SOURCE_NOT_JAVA_CONTAINER, resourceMap);
					}
				}
				else {
					reportStatus(ISingleRootStatus.SOURCE_PATH_NOT_FOUND, resourceMap);
				}
			}
			else {
				reportStatus(ISingleRootStatus.RUNTIME_PATH_NOT_ROOT_OR_WEBINF_CLASSES, resourceMap);
			}
			if (INCLUDE_FLAG == NONE) return false;
		}
		// Make sure only one of the maps is the content root, and that at least one is for the java folder
		if (rootValidMaps != 1) {
			if (rootValidMaps < 1) {
				reportStatus(ISingleRootStatus.ONE_CONTENT_ROOT_REQUIRED);
			}
			else if (rootValidMaps > 1) {
				reportStatus(ISingleRootStatus.ONLY_1_CONTENT_ROOT_ALLOWED);
			}
			if (INCLUDE_FLAG == NONE) return false;
		}
		if (javaValidRoots < 1) {
			reportStatus(ISingleRootStatus.ATLEAST_1_JAVA_SOURCE_REQUIRED);
		}		
		return INCLUDE_FLAG == NONE ? false : true;
	}
	
	/**
	 * Checks if the path argument exists relative to this workspace root.
	 * 
	 * @param a workspace relative full path
	 * @return is path in the workspace?
	 */
	private boolean exists(IPath path) {
		IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		return workspaceRoot.exists(path);
	}

	private IProject getProject() {
		return component.getProject();
	}

	public boolean isSingleJavaOutputNonSource() {
		return isSingleJavaOutputNonSource;
	}
	
	public void reportStatus(int code) {
		reportStatus(code, null, null);
	}
	
	public void reportStatus(int code, ComponentResource resource) {
		reportStatus(code, resource, null);
	}
	
	public void reportStatus(int code, ComponentResource resource, String message) {
		ISingleRootStatus status = new SingleRootStatus(code, resource, message);
		if (status.getSeverity() == IStatus.ERROR) {
			if ((INCLUDE_FLAG & INCLUDE_FIRST_ERROR) != 0) {
				INCLUDE_FLAG = NONE;
				addStatus(status);
			}
			else if ((INCLUDE_FLAG & INCLUDE_ERRORS) != 0) {
				addStatus(status);
			}
		}
		else if (status.getSeverity() == IStatus.WARNING && (INCLUDE_FLAG & WARNINGS) != 0) {
			addStatus(status);
		}
		else if (status.getSeverity() == IStatus.INFO && (INCLUDE_FLAG & INFO) != 0) { 
			addStatus(status);
		}
	}

	public IStatus getStatus() {
		if (wrapperStatus != null) {
			return wrapperStatus;
		}
		return Status.OK_STATUS;
	}

	private void addStatus(ISingleRootStatus status) {
		if (wrapperStatus == null) {
			wrapperStatus = new MultiStatus(J2EEPlugin.PLUGIN_ID, 0, new IStatus[] { status }, null, null);
		}
		else {
			wrapperStatus.add(status);
		}
	}
	
}
