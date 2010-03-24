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
package org.eclipse.jst.common.internal.modulecore;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.jst.common.jdt.internal.javalite.JavaLiteUtilities;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.Property;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

public class SingleRootUtil {
	public interface SingleRootCallback {
		public boolean canValidate(IVirtualComponent vc);
		public void validate(SingleRootUtil util, IVirtualComponent vc, IProject project, List resourceMaps);
	}
	
	/** 
	 * Used to return immediately after the first error code is found.
	 */
	public static final int INCLUDE_FIRST_ERROR = 0x08;
	/** 
	 * Used to capture all status codes (error, warning, info)
	 */
	public static final int INCLUDE_ALL = 0x07;
	/** 
	 * Used to capture all error and warning status codes only.
	 */
	public static final int INCLUDE_ERRORS_AND_WARNINGS = 0x06;
	/** 
	 * Used to capture all error status codes only.
	 */
	public static final int INCLUDE_ERRORS = 0x04;
	
	private static final int WARNINGS = 0x02;
	private static final int INFO = 0x01;
	private static final int CANCEL = 0x0;
	private static final int GET_SINGLE_ROOT_CONTAINER = 0x09;
	private static String USE_SINGLE_ROOT_PROPERTY = "useSingleRoot"; //$NON-NLS-1$
	private IVirtualComponent aComponent;
	private SingleRootCallback callback;
	private List<IContainer> cachedSourceContainers;
	private IContainer[] cachedOutputContainers;
	private MultiStatus wrapperStatus;
	private int VALIDATE_FLAG;
	
	public SingleRootUtil(IVirtualComponent component) {
		this(component, null);
	}

	public SingleRootUtil(IVirtualComponent component, SingleRootCallback callback) {
		this.aComponent = component;
		this.callback = callback;
	}
	

	/**
	 * Returns true if this module has a simple structure based on a
	 * single-root folder, and false otherwise.
     *
	 * In a single-root structure, all files that are contained within the root folder
	 * are part of the module, and are already in the correct module structure. No
	 * module resources exist outside of this single folder.
	 * 
	 * @return true if this module has a single-root structure, and
	 *         false otherwise
	 */
	public boolean isSingleRoot() {
		return validateSingleRoot(INCLUDE_FIRST_ERROR).getSeverity() != IStatus.ERROR;
	}
	
	/**
	 * Will attempt to return the IContainer that counts as the "single-root".
	 * If this module does not qualify as a "single-root" module, this
	 * method will return null. Otherwise it will return an IContainer
	 * that may be used as the single-root container. 
	 * 
	 * @return IContainer representing single-root container
	 */
	public IContainer getSingleRoot() {
		IStatus status = validateSingleRoot(GET_SINGLE_ROOT_CONTAINER);
		if (status.getSeverity() == IStatus.INFO) {
			IStatus[] children = ((MultiStatus) status).getChildren();
			ISingleRootStatus rootStatus = (ISingleRootStatus) children[0];
			return rootStatus.getSingleRoot();
		}
		return null;	
	}

	/**
	 *  Validates whether the component module has a single-root structure.
	 *  An IStatus with a severity of OK is returned for a valid single-root 
	 *  structure.  A MultiStatus containing children of type ISingleRootStatus
	 *  is returned if any status codes were captured during the validation.
	 *  A MultiStatus with a severity of INFO or WARNING is returned for a valid 
	 *  single-root structure containing status codes with no severities of ERROR.
	 *  A MultiStatus with a severity of ERROR means the component does not have a
	 *  valid single-root structure.
	 *  
	 * @param flag - indicates the status codes (by severity) to capture during
	 *               the validation.  The INLCUDE_ALL flag will also capture the 
	 *               single-root container if a single-root structure was found.
	 *               Valid flags are: 
	 *               				  INCLUDE_ALL
	 *                                INCLUDE_ERRORS_AND_WARNINGS
	 *                                INCLUDE_ERRORS
	 *                                INCLUDE_FIRST_ERROR
	 *               
	 * @return IStatus
	 */
	public IStatus validateSingleRoot(int flag) {
		VALIDATE_FLAG = flag;
		wrapperStatus = null;
		StructureEdit edit = null;
		try {
			edit = StructureEdit.getStructureEditForRead(getProject());
			if (edit == null || edit.getComponent() == null) {
				reportStatus(ISingleRootStatus.NO_COMPONENT_FOUND);
				return getStatus();
			}	
			
			if (aComponent.isBinary()) {
				reportStatus(ISingleRootStatus.BINARY_COMPONENT_FOUND);
				return getStatus();
			}
			
			// 229650 - check to see if the property 'useSingleRoot' is defined. 
			Boolean useSingleRoot = getUseSingleRootProperty(edit);
			if (useSingleRoot != null) {
				//check if it was set to false
				if (!useSingleRoot.booleanValue()) {
					reportStatus(ISingleRootStatus.EXPLICITLY_DISABLED);
				}
				else {
					reportStatus(ISingleRootStatus.SINGLE_ROOT_FORCED, aComponent.getRootFolder().getUnderlyingFolder());
				}
				return getStatus();
			}
			
			// if there are any consumed references, this is not single-root
			if (hasConsumableReferences(aComponent)) {
				reportStatus(ISingleRootStatus.CONSUMABLE_REFERENCES_FOUND);
				if (VALIDATE_FLAG == CANCEL)
					return getStatus();
			}
			
			// if there are any linked resources then this is not a single-root module
			if (rootFoldersHaveLinkedContent()) {
				reportStatus(ISingleRootStatus.LINKED_RESOURCES_FOUND);
				if (VALIDATE_FLAG == CANCEL) 
					return getStatus();
			}
			
			List resourceMaps = edit.getComponent().getResources();
			
			// If the list is empty, return false
			if (resourceMaps.size() < 1) {
				reportStatus(ISingleRootStatus.NO_RESOURCE_MAPS_FOUND);
				return getStatus();
			}
			
			if (callback != null && callback.canValidate(aComponent)) { 
				callback.validate(this, aComponent, getProject(), resourceMaps);
			} 
			if (VALIDATE_FLAG != CANCEL) {
				validateProject(resourceMaps);
			}
			return getStatus();
		} finally {
			cachedOutputContainers = null;
			cachedSourceContainers = null;
			if (edit != null)
				edit.dispose();
		}
	}
	
	protected Boolean getUseSingleRootProperty(StructureEdit edit) {
		WorkbenchComponent wbComp = edit.getComponent();
		final List componentProperties = wbComp.getProperties();
		if (componentProperties != null) {
			final Iterator componentPropertiesIterator = componentProperties.iterator();
			while (componentPropertiesIterator.hasNext()) {
				Property wbProperty = (Property) componentPropertiesIterator.next();
				if (USE_SINGLE_ROOT_PROPERTY.equals(wbProperty.getName())) {
					return Boolean.valueOf(wbProperty.getValue());
				}
			}
		}
		return null;
	}
	
	protected boolean hasConsumableReferences(IVirtualComponent vc) {
		IVirtualReference[] refComponents = vc.getReferences();
    	for (int i = 0; i < refComponents.length; i++) {
    		IVirtualReference reference = refComponents[i];
    		if (reference != null && reference.getDependencyType()==IVirtualReference.DEPENDENCY_TYPE_CONSUMES) {
    			return true;
    		}
    	}
    	return false;
    }

	private void validateProject(List resourceMaps) {
		// Ensure there are only source folder component resource mappings to the root content folder
		if (isRootResourceMapping(resourceMaps)) {
			IContainer[] javaOutputFolders = getJavaOutputFolders();
			// Verify only one java outputfolder
			if (javaOutputFolders.length == 1) {
				// By the time we get here we know: for any folders defined as source in the 
				// .component file that they are also java source folders.
				if (!isSourceContainer(javaOutputFolders[0])) {
					// The single output folder is NOT a source folder so this is single-rooted. Since the
					// output folder (something like classes or bin) is not a source folder, JDT copies all files
					// (including non Java files) to this folder, so every resource needed at runtime is located 
					// in a single directory.
					reportStatus(ISingleRootStatus.SINGLE_ROOT_CONTAINER_FOUND, javaOutputFolders[0]);
					return;
				} 
				// Verify the java output folder is the same as one of the content roots
				IPath javaOutputPath = getJavaOutputFolders()[0].getProjectRelativePath();
				IContainer[] rootFolders = aComponent.getRootFolder().getUnderlyingFolders();
				for (int i=0; i < rootFolders.length; i++) {
					IPath compRootPath = rootFolders[i].getProjectRelativePath();
					if (javaOutputPath.equals(compRootPath)) {
						reportStatus(ISingleRootStatus.SINGLE_ROOT_CONTAINER_FOUND, aComponent.getRootFolder().getUnderlyingFolder());
						return;
					}
				}
				reportStatus(ISingleRootStatus.JAVA_OUTPUT_NOT_A_CONTENT_ROOT);
			}
			else {
				reportStatus(ISingleRootStatus.JAVA_OUTPUT_GREATER_THAN_1);
			}
		}
	}

	public IContainer[] getJavaOutputFolders() {
		if (cachedOutputContainers == null)
			cachedOutputContainers = getJavaOutputFolders(aComponent);
		return cachedOutputContainers;
	}
	
	public static IContainer[] getJavaOutputFolders(IVirtualComponent component) {
		if (component == null)
			return new IContainer[0];
		
		List<IContainer> l = JavaLiteUtilities.getJavaOutputContainers(component);
		return l.toArray(new IContainer[l.size()]);
	}	
	
	/**
	 * Checks if the path argument is to a source container for the project.
	 * 
	 * @param a workspace relative full path
	 * @return is path a source container?
	 */
	public boolean isSourceContainer(IContainer sourceContainer) {
		if (cachedSourceContainers == null) {
			cachedSourceContainers = getSourceContainers(aComponent);
		}
		return cachedSourceContainers.contains(sourceContainer);
	}
	
	public static List<IContainer> getSourceContainers(IVirtualComponent component) {
		if (component == null)
			Collections.emptyList();
		return JavaLiteUtilities.getJavaSourceContainers(component);
	}	
	
	/*
     * This method returns true if the root folders of this component have any linked resources (folder or file);
     * Otherwise false is returned.
     */
    private boolean rootFoldersHaveLinkedContent() {
    	if (this.aComponent != null) {
    		final IContainer[] rootFolders = this.aComponent.getRootFolder().getUnderlyingFolders();
    		for (int i = 0; i < rootFolders.length; i++) {
    			try {
    				boolean hasLinkedContent = this.hasLinkedContent(rootFolders[i]);
    				if (hasLinkedContent) {
    					return true;
    				}
    			}
    			catch (CoreException coreEx) {
    				CommonFrameworksPlugin.logError(coreEx);
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
		for (int i=0; i < resourceMaps.size(); i++) {
			ComponentResource resourceMap = (ComponentResource) resourceMaps.get(i);
			// Verify it maps to "/" for the content root
			if (!isRootMapping(resourceMap)) {
				reportStatus(ISingleRootStatus.RUNTIME_PATH_NOT_ROOT, resourceMap.getRuntimePath());
				if (VALIDATE_FLAG == CANCEL) return false;
			}
			
			// verify it is also a src container
			IPath sourcePath = resourceMap.getSourcePath();
			IResource sourceResource = getProject().findMember(sourcePath);
			if (sourceResource != null && sourceResource.exists()) {
				if (sourceResource instanceof IContainer && !isSourceContainer((IContainer) sourceResource)) {
					reportStatus(ISingleRootStatus.SOURCE_NOT_JAVA_CONTAINER, sourcePath);
				}
			}
			else {
				reportStatus(ISingleRootStatus.SOURCE_PATH_NOT_FOUND, sourcePath);
			}
			if (VALIDATE_FLAG == CANCEL) return false;
		}
		return true;
	}
	
	public boolean isRootMapping(ComponentResource map) {
		// Verify it maps to "/" for the content root
		if (map.getRuntimePath().equals(Path.ROOT))
			return true;
		return false;
	}
		
	public IProject getProject() {
		return aComponent.getProject();
	}

	public void reportStatus(int code) {
		reportStatus(code, null, null);
	}
	
	public void reportStatus(int code, IContainer container) {
		reportStatus(code, null, container);
	}
	
	public void reportStatus(int code, IPath path) {
		reportStatus(code, path, null);
	}
	
	public void reportStatus(int code, IPath path, IContainer container) {
		ISingleRootStatus status = new SingleRootStatus(code, path, container);
		if (status.getSeverity() == IStatus.ERROR) {
			if ((VALIDATE_FLAG & INCLUDE_FIRST_ERROR) != 0) {
				VALIDATE_FLAG = CANCEL;
				addStatus(status);
			}
			else if ((VALIDATE_FLAG & INCLUDE_ERRORS) != 0) {
				addStatus(status);
			}
		}
		else if (status.getSeverity() == IStatus.WARNING && (VALIDATE_FLAG & WARNINGS) != 0) {
			addStatus(status);
		}
		else if (status.getSeverity() == IStatus.INFO && (VALIDATE_FLAG & INFO) != 0) { 
			VALIDATE_FLAG = CANCEL;
			addStatus(status);
		}
	}

	public int getValidateFlag() {
		return VALIDATE_FLAG;
	}

	public void setValidateFlag(int validateFlag) {
		VALIDATE_FLAG = validateFlag;
	}

	public IStatus getStatus() {
		if (wrapperStatus != null) {
			return wrapperStatus;
		}
		return Status.OK_STATUS;
	}

	private void addStatus(ISingleRootStatus status) {
		if (wrapperStatus == null) {
			wrapperStatus = new MultiStatus(CommonFrameworksPlugin.PLUGIN_ID, 0, new IStatus[] { status }, null, null);
		} else {
			wrapperStatus.add(status);
		}
	}
	
}
