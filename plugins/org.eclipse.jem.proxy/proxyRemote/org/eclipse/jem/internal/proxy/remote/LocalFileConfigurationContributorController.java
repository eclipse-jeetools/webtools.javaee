/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: LocalFileConfigurationContributorController.java,v $
 *  $Revision: 1.3 $  $Date: 2004/03/22 23:49:02 $ 
 */
package org.eclipse.jem.internal.proxy.remote;

import java.util.*;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;
import org.eclipse.jdt.core.*;
import org.eclipse.jdt.launching.IRuntimeClasspathEntry;
import org.eclipse.jdt.launching.JavaRuntime;

import org.eclipse.jem.internal.proxy.core.*;
 
/**
 * This version works with local files being added to the classpath, and the classpath is strings.
 * 
 * @since 1.0.0
 */
public class LocalFileConfigurationContributorController implements IConfigurationContributionController {

	private String[] classpathInfo;
	private String[][] bootpathInfo;
	private List classpath;
	private List prependBootpath;
	private List appendBootpath;
	private List javaLibraryPath;
	private ProxyLaunchSupport.LaunchInfo launchInfo;
	
	public LocalFileConfigurationContributorController(String[] classpathInfo, String[][] bootpathInfo, ProxyLaunchSupport.LaunchInfo launchInfo) {
		this.classpathInfo = classpathInfo;
		this.bootpathInfo = bootpathInfo;
		this.launchInfo = launchInfo;
	}
	
	public String[] getFinalClasspath() {
		if (classpath == null)
			return classpathInfo;
		else
			return (String[]) classpath.toArray(new String[classpath.size()]);
	}

	public String[] getFinalPrependBootpath() {
		if (prependBootpath == null)
			return bootpathInfo[0];
		else
			return (String[]) prependBootpath.toArray(new String[prependBootpath.size()]);
	}
	
	public String[] getFinalAppendBootpath() {
		if (appendBootpath == null)
			return bootpathInfo[2];
		else
			return (String[]) prependBootpath.toArray(new String[appendBootpath.size()]);
	}	

	public List getFinalJavaLibraryPath() {
		if (javaLibraryPath == null)
			return Collections.EMPTY_LIST;
		else
			return javaLibraryPath;
	}
	
	protected List getClasspath() {
		if (classpath == null) {
			classpath = new ArrayList(classpathInfo.length);
			addAll(classpath, classpathInfo, -1);
		}
		return classpath;
	}
	
	protected List getPrependBootpath() {
		if (prependBootpath == null) {
			if (bootpathInfo[0] != null) {
				prependBootpath = new ArrayList(bootpathInfo[0].length);
				addAll(prependBootpath, bootpathInfo[0], -1);
			} else
				prependBootpath = new ArrayList(1);
		}
		return prependBootpath;
	}
	
	protected List getAppendBootpath() {
		if (appendBootpath == null) {
			if (bootpathInfo[2] != null) {
				appendBootpath = new ArrayList(bootpathInfo[2].length);
				addAll(appendBootpath, bootpathInfo[2], -1);
			} else
				appendBootpath = new ArrayList(1);
		}
		return appendBootpath;
	}	
	
	protected List getJavaLibraryPath() {
		if (javaLibraryPath == null) {
			javaLibraryPath = new ArrayList(3);
		}
		return javaLibraryPath;
	}
	
	protected void addAll(List toList, Object[] array, int loc) {
		for (int i = 0; i < array.length; i++) {
			// Need a dup check, sometimes dups come in especially during development
			if (toList.contains(array[i]))
				continue;
			if (loc == -1)
				toList.add(array[i]);
			else
				toList.add(loc++, array[i]);
		}
	}
	
	protected void addLocations(List toList, IRuntimeClasspathEntry[] entries) {
		for (int i = 0; i < entries.length; i++) {
			IRuntimeClasspathEntry entry = entries[i];
			String location = entry.getLocation();
			if (location != null && !toList.contains(location))
				toList.add(location);
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IConfigurationContributionController#getJavaProject()
	 */
	public IJavaProject getJavaProject() {
		return launchInfo.getJavaProject();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IConfigurationContributionController#contributeProject(org.eclipse.core.resources.IProject)
	 */
	public void contributeProject(IProject project) throws CoreException {
		IJavaProject jproject = JavaCore.create(project);
		IRuntimeClasspathEntry[] projPath = JavaRuntime.computeUnresolvedRuntimeClasspath(jproject);
		boolean jreContainerFound= false;
		for (int i = 0; i < projPath.length; i++) {
			IRuntimeClasspathEntry entry= projPath[i];
			if (entry.getClasspathProperty() == IRuntimeClasspathEntry.BOOTSTRAP_CLASSES || entry.getClasspathProperty() == IRuntimeClasspathEntry.STANDARD_CLASSES) {
				int entryKind= entry.getClasspathEntry().getEntryKind();
				String segment0= entry.getPath().segment(0);
				if (entryKind == IClasspathEntry.CPE_CONTAINER && JavaRuntime.JRE_CONTAINER.equals(segment0)
						|| entryKind == IClasspathEntry.CPE_VARIABLE && JavaRuntime.JRELIB_VARIABLE.equals(segment0)) {
					jreContainerFound= true;
				} else {
					if (jreContainerFound)
						addLocations(getAppendBootpath(), JavaRuntime.resolveRuntimeClasspathEntry(entry, jproject));
					else
						addLocations(getPrependBootpath(), JavaRuntime.resolveRuntimeClasspathEntry(entry, jproject));
				}
			} else {
				addLocations(getClasspath(), JavaRuntime.resolveRuntimeClasspathEntry(entry, jproject));
			}
		}			
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IConfigurationContributionController#contributeClasspath(java.lang.String, int)
	 */
	public void contributeClasspath(String classpath, int typeFlag) {
		switch (typeFlag) {
			case PREPEND_BOOT_CLASSPATH:
				if (!getPrependBootpath().contains(classpath))
					getPrependBootpath().add(classpath);
				break;
			case PREPEND_USER_CLASSPATH:
				if (!getClasspath().contains(classpath))
					getClasspath().add(0, classpath);
				break;
			case APPEND_USER_CLASSPATH:
				if (!getClasspath().contains(classpath))					
					getClasspath().add(classpath);
				break;				
			case APPEND_BOOT_CLASSPATH:
				if (!getAppendBootpath().contains(classpath))					
					getAppendBootpath().add(classpath);
				break;
			case APPEND_JAVA_LIBRARY_PATH:
				if (!getJavaLibraryPath().contains(classpath))					
					getJavaLibraryPath().add(classpath);
				break;
		}
		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IConfigurationContributionController#contributeClasspath(org.eclipse.core.runtime.Plugin, java.lang.String, int, boolean)
	 */
	public void contributeClasspath(IPluginDescriptor pluginDescriptor, String relativePath, int typeFlag, boolean nlsLocalize) {
		// If not nls localize, or if it is java library path, then just find the one in the plugin/fragment and add it.
		if (!nlsLocalize || typeFlag == IConfigurationContributionController.APPEND_JAVA_LIBRARY_PATH)
			contributeClasspath(ProxyPlugin.getPlugin().localizeFromPluginDescriptor(pluginDescriptor, relativePath), typeFlag);
		else
			contributeClasspath(ProxyPlugin.getPlugin().localizeFromPluginDescriptorAndFragments(pluginDescriptor, relativePath), typeFlag);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.IConfigurationContributionController#contributeClasspath(java.lang.String[], int)
	 */
	public void contributeClasspath(String[] classpaths, int typeFlag) {
		switch (typeFlag) {
			case PREPEND_BOOT_CLASSPATH:
				addAll(getPrependBootpath(), classpaths, -1);
				break;
			case PREPEND_USER_CLASSPATH:
				addAll(getClasspath(), classpaths, 0);
				break;
			case APPEND_USER_CLASSPATH:
				addAll(getClasspath(), classpaths, -1);
				break;				
			case APPEND_BOOT_CLASSPATH:
				addAll(getAppendBootpath(), classpaths, -1);
				break;
			case APPEND_JAVA_LIBRARY_PATH:
				addAll(getJavaLibraryPath(), classpaths, -1);
				break;
		}
	}

}
