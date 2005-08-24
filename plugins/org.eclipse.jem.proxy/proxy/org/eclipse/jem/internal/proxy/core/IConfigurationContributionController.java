/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.core;
/*
 *  $RCSfile: IConfigurationContributionController.java,v $
 *  $Revision: 1.9 $  $Date: 2005/08/24 20:39:05 $ 
 */

import java.net.URL;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.osgi.framework.Bundle;
;
/**
 * Controls contribution to the configuration. This allows the classpath
 * to be modified in an acceptable manner, so that duplicates aren't in
 * the path, or adding a project to the path in an acceptable way.
 * 
 * If only deleting or moving entries from the classpath, that can be done directly
 * to the classpath list passed in. Adding entries needs to go through this
 * controller.
 * 
 * This is meant to be implemented by developers of proxy launch configurations.
 * 
 * @version 	1.0
 * @author
 */
public interface IConfigurationContributionController {
	
	/**
	 * Append to the user classpath.
	 */
	public static final int APPEND_USER_CLASSPATH = 0;
	
	/**
	 * Prepend to the user classpath. This will stick it in position 0. If
	 * another prepend comes along, it will go in pos 0 and this one will move to 1.
	 */
	public static final int PREPEND_USER_CLASSPATH = 1;
	

	/**
	 * Append to the boot classpath.
	 */
	public static final int APPEND_BOOT_CLASSPATH = 2;

	/**
	 * Prepend to the boot classpath.
	 */
	public static final int PREPEND_BOOT_CLASSPATH = 3;
	
	/**
	 * Append a folder to the java.library.path (for things like
	 * dll's). This should result in a folder only.
	 * The nlsLocalize flag contribute via plugin will be
	 * ignored for this type flag. 
	 */
	public static final int APPEND_JAVA_LIBRARY_PATH = 4;
	
	/**
	 * Get the java project that we are contributing for.
	 * 
	 * @return javaproject, may be <code>null</code>
	 * 
	 * @since 1.0.0
	 */
	public IJavaProject getJavaProject();
		
	/**
	 * Add a project to the classpath. The type flag tells where to add it.
	 * 
	 * @param project
	 * @throws CoreException
	 * 
	 * @since 1.0.0
	 */
	public void contributeProject(IProject project) throws CoreException;
	
	/**
	 * Add an external file/folder to the classpath. The type flag tells where to add it.
	 * 
	 * @param classpath
	 * @param typeFlag One of <code>APPEND_USER_CLASSPATH</code>, <code>APPEND_BOOT_CLASSPATH</code>, <code>PREPEND_BOOT_CLASSPATH</code>, or <code>APPEND_JAVA_LIBRARY_PATH</code>.
	 * 
	 * @deprecated use contributeClasspath(URL, int) instead.
	 * @see #contributeClasspath(URL, int)
	 * @since 1.0.0
	 */
	public void contributeClasspath(String classpath, int typeFlag);
	
	/**
	 * Add an external file/folder to the classpath. The type flag tells where to add it.
	 * 
	 * @param classpathURL
	 * @param typeFlag One of <code>APPEND_USER_CLASSPATH</code>, <code>APPEND_BOOT_CLASSPATH</code>, <code>PREPEND_BOOT_CLASSPATH</code>, or <code>APPEND_JAVA_LIBRARY_PATH</code>.
	 * 
	 * @since 1.0.0
	 */
	public void contributeClasspath(URL classpathURL, int typeFlag);	
	
	/**
	 * Add an external files/folders to the classpath. The type flag tells where to add it.
	 * 
	 * @param classpaths
	 * @param typeFlag One of <code>APPEND_USER_CLASSPATH</code>, <code>APPEND_BOOT_CLASSPATH</code>, <code>PREPEND_BOOT_CLASSPATH</code>, or <code>APPEND_JAVA_LIBRARY_PATH</code>.
	 * 
	 * @deprecated use contributeClasspath(URL[], int) instead.
	 * @see #contributeClasspath(URL[], int)
	 * @since 1.0.0
	 */
	public void contributeClasspath(String[] classpaths, int typeFlag);	

	/**
	 * Add an external files/folders to the classpath. The type flag tells where to add it.
	 * 
	 * @param classpathURLs
	 * @param typeFlag One of <code>APPEND_USER_CLASSPATH</code>, <code>APPEND_BOOT_CLASSPATH</code>, <code>PREPEND_BOOT_CLASSPATH</code>, or <code>APPEND_JAVA_LIBRARY_PATH</code>.
	 * 
	 * @since 1.0.0
	 */
	public void contributeClasspath(URL[] classpathURLs, int typeFlag);	
	
	/**
	 * Add a file/folder from a plugin in the running Eclipse to the classpath. The type flag tells where to add it.
	 * In development mode it will find the bin class files instead if proxy.jars is setup correctly.
	 * <p>
	 * If nlsLocalize is <code>true</code>, then it will also search through the fragments in this manner:
	 * (Note: if <code>false</code> it will still search in the fragments, but it will only return the first file found that matches.
	 * <p>
	 * To find the files in the fragments that are in the runtime path (i.e. libraries), it will need to use a suffix,
	 * This is because the JDT will get confused if a runtime jar in a fragment has the same name
	 * as a runtime jar in the main plugin. So we will use the following search pattern:
	 * 
	 * 1) Find in the plugin and all of the fragments those that match the name exactly
	 * 2) Find in all of the fragments, in their runtime path (<library> stmt), those that match the name 
	 *    but have a suffix the same as the uniqueid of the fragment (preceeded by a period). This is so that it can be easily
	 *    found but yet be unique in the entire list of fragments. For example if looking for "runtime/xyz.jar"
	 *    and we have fragment "a.b.c.d.frag", then in the runtime path we will look for the file
	 *    "runtime/xyz.a.b.c.d.frag.jar".
	 * <p>
	 * If the files in the fragments are not in the fragments library path then it can have the same name as in the plugin.
	 * <p>
	 * This is useful for nls where the nls for the filename will be in one or more of the fragments of the plugin.	 	 
	 * 
	 * @param bundle The bundle it can be found in.
	 * @param relativePath Path to file/folder relative to bundle root. If it is <code>APPEND_JAVA_LIBRARY_PATH</code>, it should be folder and nlsLocalize will be ignored.
	 * @param typeFlag One of <code>APPEND_USER_CLASSPATH</code>, <code>APPEND_BOOT_CLASSPATH</code>, <code>PREPEND_BOOT_CLASSPATH</code>, or <code>APPEND_JAVA_LIBRARY_PATH</code>.
	 * @param nlsLocalize The usual value should be <code>false</code>. Use <code>true</code> if should look through fragments to gather them all as described in this methods description.
	 * 
	 * @since 1.0.0
	 */
	public void contributeClasspath(Bundle bundle, String relativePath, int typeFlag, boolean nlsLocalize);	
	
	/**
	 * Same as contributeClasspath(Bundle, String, int, boolean) except an IPath can be used as the relative path.
	 * 
	 * @param bundle
	 * @param relativePath
	 * @param typeFlag
	 * @param nlsLocalize
	 * 
	 * @see #contributeClasspath(Bundle, String, int, boolean)
	 * @since 1.0.0
	 */
	public void contributeClasspath(Bundle bundle, IPath relativePath, int typeFlag, boolean nlsLocalize);	
	
}
