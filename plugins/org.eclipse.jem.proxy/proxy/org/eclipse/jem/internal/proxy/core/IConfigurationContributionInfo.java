/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IConfigurationContributionInfo.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:53:46 $ 
 */
package org.eclipse.jem.internal.proxy.core;

import java.util.Map;

import org.eclipse.jdt.core.IJavaProject;
 
/**
 * This interface will be passed to the IConfigurationContributor initialize method. It has info
 * that may be needed by the contributor. It may be saved for later use, but if classpaths have changed,
 * it won't be kept up to date.
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see org.eclipse.jem.internal.proxy.core.IConfigurationContributor#initialize(IConfigurationContributionInfo)
 * @since 1.0.0
 */
public interface IConfigurationContributionInfo {
	/**
	 * Get the java project that we are contributing for.
	 * 
	 * @return javaproject, may be <code>null</code>
	 * 
	 * @since 1.0.0
	 */
	public IJavaProject getJavaProject();
	
	/**
	 * Return the map of containers (the key) that are found in the classpath. This is useful for determining
	 * if the container impliments a contributor interface.
	 * <p>
	 * Value will <code>Boolean</code> and will be true if container was visible to top-level project.
	 * 
	 * @return Map of containers. Keys of type IClasspathContainer. Empty if launch not in a project.
	 * 
	 * @see org.eclipse.jdt.core.IClasspathContainer
	 * @since 1.0.0
	 */
	public Map getContainers();
	
	/**
	 * Return the map of container ids (the key) that are found in the classpath.
	 * <p>
	 * Value will <code>Boolean</code> and will be true if container id was visible to top-level project.
	 * 
	 * @return map of container ids. Keys of type String. Empty if launch not in a project.
	 * 
	 * @since 1.0.0
	 */
	public Map getContainerIds();
	
	/**
	 * Return the map of plugin ids (the key) that are found in the classpath.
	 * <p>
	 * Value will <code>Boolean</code> and will be true if plugin was visible to top-level project.
	 * 
	 * @return map of plugin ids. Key of type String. Empty if launch not in a project.
	 * 
	 * @since 1.0.0
	 */
	public Map getPluginIds();	
	
	/**
	 * Return the map of project paths (the key) that are found in the classpath. The top-level project
	 * will NOT be in this list since it is assumed to be visible to itself.
	 * <p>
	 * Value will <code>Boolean</code> and will be true if project was visible to top-level project.
	 * 
	 * @return map of project paths. Key of type <code>IPath</code>. Empty if launch not in a project.
	 * 
	 * @see org.eclipse.core.runtime.IPath
	 * @since 1.0.0
	 */
	public Map getProjectPaths();	
	
}
