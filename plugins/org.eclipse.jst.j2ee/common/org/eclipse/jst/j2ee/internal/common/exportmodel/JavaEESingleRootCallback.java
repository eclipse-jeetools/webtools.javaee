/*******************************************************************************
 * Copyright (c) 2009, 2012 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *     Roberto Sanchez Herrera - [371907] Do not always treat EARs as non single root 
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.exportmodel;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.internal.modulecore.AddClasspathFoldersParticipant;
import org.eclipse.jst.common.internal.modulecore.AddClasspathLibReferencesParticipant;
import org.eclipse.jst.common.internal.modulecore.AddClasspathLibRefsProviderParticipant;
import org.eclipse.jst.common.internal.modulecore.ISingleRootStatus;
import org.eclipse.jst.common.internal.modulecore.ReplaceManifestExportParticipant;
import org.eclipse.jst.common.internal.modulecore.SingleRootUtil;
import org.eclipse.jst.common.internal.modulecore.SingleRootExportParticipant.SingleRootParticipantCallback;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.classpathdep.ClasspathDependencyEnablement;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.flat.FilterResourceParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.GlobalHeirarchyParticipant;
import org.eclipse.wst.common.componentcore.internal.flat.IFlattenParticipant;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class JavaEESingleRootCallback implements SingleRootParticipantCallback {
	//Warnings
	public static final int UNNECESSARY_RESOURCE_MAP = 100;
	
	//Errors
	public static final int EAR_PROJECT_FOUND = 10100;
	public static final int ATLEAST_1_RESOURCE_MAP_MISSING = 10101;
	public static final int JAVA_OUTPUT_NOT_WEBINF_CLASSES = 10102;
	public static final int RUNTIME_PATH_NOT_ROOT_OR_WEBINF_CLASSES = 10103;
	public static final int ONLY_1_CONTENT_ROOT_ALLOWED = 10104;
	public static final int ONE_CONTENT_ROOT_REQUIRED = 10105;
	public static final int ATLEAST_1_JAVA_SOURCE_REQUIRED = 10106;
	public static final int CLASSPATH_DEPENDENCIES_FOUND = 10107;
	public static final int SOURCE_PATH_OUTSIDE_CONTENT_ROOT = 10108;
	
	private static final int CANCEL = 0x0;
	private String[] filteredSuffixes = new String[]{}; 
	public JavaEESingleRootCallback() {
		// intentionally blank
	}
	
	public JavaEESingleRootCallback(String[] filtered) {
		this.filteredSuffixes = filtered;
	}
	
	public void setFilteredSuffixes(String[] filtered) {
		this.filteredSuffixes = filtered;
	}
	
	public boolean canValidate(IVirtualComponent vc) {
		return JavaEEProjectUtilities.usesJavaEEComponent(vc);
	}

	public void validate(SingleRootUtil util, IVirtualComponent vc, IProject project, List resourceMaps) {
	
		if (resourceMaps.size() == 1) {
			ComponentResource mapping = (ComponentResource)resourceMaps.get(0); 
			if (util.isRootMapping(mapping)) {
				IResource sourceResource = project.findMember(mapping.getSourcePath());
				if (sourceResource != null && sourceResource.exists()) {
					if (sourceResource instanceof IContainer && !util.isSourceContainer((IContainer) sourceResource)) {
						util.reportStatus(ISingleRootStatus.SINGLE_ROOT_CONTAINER_FOUND, (IContainer) sourceResource);
						util.setValidateFlag(CANCEL);
						return;
					}
				}
			}
		}
		
		if (JavaEEProjectUtilities.isEARProject(project)) { 
			validateEARProject(util, vc, resourceMaps);
			util.setValidateFlag(CANCEL);
			return;
		}
		
		//validate web projects for single root
		if (JavaEEProjectUtilities.isDynamicWebProject(project)) {
			validateWebProject(util, vc, resourceMaps);
			util.setValidateFlag(CANCEL);
		}

	}
	
	
	private void validateEARProject(SingleRootUtil util, IVirtualComponent vc, List resourceMaps) {
		/*
		 * If we are here, we know we have more than one resource mapping, so let's check if the EAR is single root.
		 * The algorithm is the following:
		 * 	Go through all the mappings, 
		 * 		If we find more than one mapping to root, then this EAR is not single root. 
		 * 		If we find only one mapping to root,
		 * 			Check if the other mappings' source path is part of the source path of the mapping to root. 
		 * 			If at least one mapping has a source path that is not in the mapping to root, then the EAR is not single root.
		 * 			else, report the only mapping found as the root container.  
		 */

		List<ComponentResource> rootMappings = new ArrayList<ComponentResource>();
		List<ComponentResource> nonRootMappings = new ArrayList<ComponentResource>();
		for (int i = 0; i < resourceMaps.size(); i++) {
			ComponentResource resourceMap = (ComponentResource) resourceMaps.get(i);
			// Verify if the map is for the content root
			if (util.isRootMapping(resourceMap)) {
				rootMappings.add(resourceMap);
			}
			else {
				nonRootMappings.add(resourceMap);
			}
		}

		if (rootMappings.size() > 1){
			util.reportStatus(ONLY_1_CONTENT_ROOT_ALLOWED);
			return;
		}
		if (rootMappings.size() < 1)
		{
			util.reportStatus(ONE_CONTENT_ROOT_REQUIRED);
			return;
		}

		// We have one mapping to root. Let's check if there are other mappings 

		ComponentResource rootMapping = rootMappings.get(0);
		boolean reportNonSingleRoot = false;
		for (ComponentResource otherMapping:nonRootMappings){
			IPath otherMappingSourcePath = otherMapping.getSourcePath();
			if (!rootMapping.getSourcePath().isPrefixOf(otherMappingSourcePath)){
				reportNonSingleRoot = true;
				break;	
			}			
		}
		if (reportNonSingleRoot){
			util.reportStatus(SOURCE_PATH_OUTSIDE_CONTENT_ROOT);
			return;			
		}
		// At this moment, we know there is only one mapping to root (and possibly one or more
		// other mappings that do not break the single root condition of the project), so let's see
		// if we can find the root container
		IResource sourceResource = util.getProject().findMember(rootMappings.get(0).getSourcePath());
		if (sourceResource != null && sourceResource.exists()) {
			if (sourceResource instanceof IContainer && !util.isSourceContainer((IContainer) sourceResource)) {
				util.reportStatus(ISingleRootStatus.SINGLE_ROOT_CONTAINER_FOUND, (IContainer) sourceResource);
				return;
			}
		}
		// If we get here, it means that we have only one mapping to root (and possibly one or more
		// other mappings that do not break the single root condition of the project), but the container for
		// the root mapping was not found. 
	}

	
	private void validateWebProject(SingleRootUtil util, IVirtualComponent vc, List resourceMaps) {
		// Ensure there are only basic component resource mappings -- one for the content folder 
		// and any for src folders mapped to WEB-INF/classes
		if (hasDefaultWebResourceMappings(util, resourceMaps)) {
			IContainer[] javaOutputFolders = util.getJavaOutputFolders();
			// Verify only one java output folder
			if (javaOutputFolders.length == 1) {
				// Verify the java output folder is to <content root>/WEB-INF/classes
				IPath javaOutputPath = util.getJavaOutputFolders()[0].getProjectRelativePath();
				IContainer rootContainer = vc.getRootFolder().getUnderlyingFolder();
				IPath compRootPath = rootContainer.getProjectRelativePath();
				if (compRootPath.append(J2EEConstants.WEB_INF_CLASSES).equals(javaOutputPath)) {
					util.reportStatus(ISingleRootStatus.SINGLE_ROOT_CONTAINER_FOUND, rootContainer);
					return;
				}
				util.reportStatus(JAVA_OUTPUT_NOT_WEBINF_CLASSES);
			}
			else {
				util.reportStatus(ISingleRootStatus.JAVA_OUTPUT_GREATER_THAN_1);
			}
		}
	}
	
	/**
	 * Ensure the default web setup is correct with one resource map and any number of java 
	 * resource maps to WEB-INF/classes
	 * 
	 * @param resourceMaps
	 * @return boolean
	 */
	private boolean hasDefaultWebResourceMappings(SingleRootUtil util, List resourceMaps) {
		int rootValidMaps = 0;
		IPath pathMappedToContentRoot = null;
		List<ComponentResource> tmpResources = new ArrayList<ComponentResource>();
		
		IPath webInfClasses = new Path(J2EEConstants.WEB_INF_CLASSES).makeAbsolute();
		for (int i = 0; i < resourceMaps.size(); i++) {
			ComponentResource resourceMap = (ComponentResource) resourceMaps.get(i);
			IPath sourcePath = resourceMap.getSourcePath();
			IPath runtimePath = resourceMap.getRuntimePath();
			IResource sourceResource = util.getProject().findMember(sourcePath);
			
			// Verify if the map is for the content root
			if (util.isRootMapping(resourceMap)) {
				rootValidMaps++;
				if (pathMappedToContentRoot == null)  //we are interested only if the first resource mapped to root
					pathMappedToContentRoot = sourcePath;
			} 
			// Verify if the map is for a java src folder and is mapped to "WEB-INF/classes"
			else if (runtimePath.equals(webInfClasses)) {
				if (sourceResource != null && sourceResource.exists()) {
					if (sourceResource instanceof IContainer && !util.isSourceContainer((IContainer) sourceResource)) {
						util.reportStatus(ISingleRootStatus.SOURCE_NOT_JAVA_CONTAINER, sourcePath);
					}
				}
				else {
					util.reportStatus(ISingleRootStatus.SOURCE_PATH_NOT_FOUND, sourcePath);
				}
			}
			else {
				// Do not report status yet. Below we do some extra validation
				tmpResources.add(resourceMap);
			}			
			if (util.getValidateFlag() == CANCEL) return false;
		}
		
		if (pathMappedToContentRoot != null){  
			for (ComponentResource res:tmpResources){
				IPath completePath = pathMappedToContentRoot.append(res.getRuntimePath());
				if (completePath.equals(res.getSourcePath())){
					// This mapping is redundant, because there is already a mapping that includes this resource			
					util.reportStatus(UNNECESSARY_RESOURCE_MAP, res.getSourcePath());
				}
				else{
					// Not root, not WEB-INF/classes and not redundant, report status
					util.reportStatus(RUNTIME_PATH_NOT_ROOT_OR_WEBINF_CLASSES, res.getRuntimePath());
				}
				if (util.getValidateFlag() == CANCEL) return false;
			}
			tmpResources = null;
		}
		
		// Make sure only one of the maps is the content root, and that at least one is for the java folder
		if (rootValidMaps != 1) {
			if (rootValidMaps < 1) {
				util.reportStatus(ONE_CONTENT_ROOT_REQUIRED);
			}
			else if (rootValidMaps > 1) {
				util.reportStatus(ONLY_1_CONTENT_ROOT_ALLOWED);
			}
		}
		return util.getValidateFlag() == CANCEL ? false : true;
	}

	public IFlattenParticipant[] getDelegateParticipants() {
		List<IFlattenParticipant> participants = new ArrayList<IFlattenParticipant>();

		participants.add(new GlobalHeirarchyParticipant());
		participants.add(FilterResourceParticipant.createSuffixFilterParticipant(filteredSuffixes));
		participants.add(new AddClasspathLibReferencesParticipant());
		participants.add(new AddClasspathLibRefsProviderParticipant());
		participants.add(new AddClasspathFoldersParticipant());
		if (ClasspathDependencyEnablement.isAllowClasspathComponentDependency()) {
			participants.add(new ReplaceManifestExportParticipant(new Path(J2EEConstants.MANIFEST_URI)));
		}
		
		return participants.toArray(new IFlattenParticipant[participants.size()]);
	}
	

}
