package org.eclipse.jem.internal.proxy.core;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IClasspathContributionController.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/14 18:37:14 $ 
 */

import java.net.URL;
import java.util.List;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
;
/**
 * Controls contribution to the class paths. This allows the classpath
 * to be modified in an acceptable manner, so that duplicates aren't in
 * the path, or adding a project to the path in an acceptable way.
 * 
 * If only deleting or moving entries from the classpath, that can be done directly
 * to the classpath list passed in. Adding entries needs to go through this
 * controller.
 * 
 * @version 	1.0
 * @author
 */
public interface IClasspathContributionController {
	
	/**
	 * Get the java project that we are contributing for.
	 * 
	 * @return javaproject, may be <code>null</code>
	 * 
	 * @since 1.0.0
	 */
	public IJavaProject getJavaProject();
	
	/**
	 * Contribute a project to the classpath at the specified index.
	 * The classpath list will be passed in. If index is -1, then add at the end.
	 */
	public void contributeProject(IProject project, List classpaths, int insertBeforeIndex) throws CoreException;
	
	/**
	 * Contribute a specific classpath at the specified index.
	 * The classpath list will be passed in. If index is -1, then add at the end.
	 */
	public void contributeClasspath(String classpath, List classpaths, int insertBeforeIndex);
	
	/**
	 * Contribute a set of specific classpaths at the specified index.
	 * The classpath list will be passed in. If index is -1, then add at the end.
	 */
	public void contributeClasspath(String[] classpathsToAdd, List classpaths, int insertBeforeIndex);	
	
	/**
	 * Contribute a set of specific classpaths URLs at the specified index.
	 * The classpath list will be passed in. If index is -1, then add at the end.
	 * This is a convienence method because localize can return an array urls.
	 */
	public void contributeClasspath(URL[] classpathsURLs, List classpaths, int insertBeforeIndex);	
	

}
