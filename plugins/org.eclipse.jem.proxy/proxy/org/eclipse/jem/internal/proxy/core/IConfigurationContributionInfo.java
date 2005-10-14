/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
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
 *  $Revision: 1.4 $  $Date: 2005/10/14 17:45:02 $ 
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
	 * Hold the visible and hidden container paths. 
	 * A visible container path means it has been exported up to the top-level project. And also
	 * the array of those not visible. The paths are the String form (i.e. IPath.toString()).
	 * <p>
	 * These are the values of the {@link IConfigurationContributionInfo#getContainerIds()} map.
	 * 
	 * @since 1.2.0
	 */
	public static class ContainerPaths {
		
		private final String[] visiblePath;
		private final String[] hiddenPaths;
		private final String containerId;
	
		ContainerPaths(String containerId, String[] visiblePath, String[] hiddenPaths) {
			// Not meant to be subclassed or constructed by clients.
			this.containerId = containerId;
			this.visiblePath = visiblePath;
			this.hiddenPaths = hiddenPaths;
		}
		
		
		/**
		 * @return Returns the containerId.
		 * 
		 * @since 1.2.0
		 */
		public final String getContainerId() {
			return containerId;
		}
		
		/**
		 * Array of visible container paths. A path is visible if it is exported up to and can
		 * see by the top-level project.
		 * 
		 * @return
		 * 
		 * @since 1.2.0
		 */
		public final String[] getVisibleContainerPaths() {
			return visiblePath;
		}
		
		/**
		 * Array of hidden container paths. A path is hidden if it is not exported up to and cannot
		 * see by the top-level project.
		 * 
		 * @return
		 * 
		 * @since 1.2.0
		 */
		public final String[] getHiddenContainerPaths() {
			return hiddenPaths;
		}
		
		/**
		 * Get all of the paths together, both visible and hidden.
		 * @return
		 * 
		 * @since 1.2.0
		 */
		public String[] getAllPaths() {
			String[] allPaths = new String[visiblePath.length+hiddenPaths.length];
			System.arraycopy(visiblePath, 0, allPaths, 0, visiblePath.length);
			System.arraycopy(hiddenPaths, 0, allPaths, visiblePath.length, hiddenPaths.length);
			return allPaths;
		}
	}

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
	 * Value will be {@link ContainerPaths}
	 * 
	 * @return Map of containers. Keys of type String for container id (e.g. "SWT_CONTAINER" for a container classpath of "/SWT_CONTAINER/PDE/xyz".
	 * 	Values will be {@link ContainerPaths} Empty if launch not in a project.
	 * 
	 * @since 1.0.0
	 */
	public Map getContainers();
	
	/**
	 * Return the map of container ids that are found in the classpath.
	 * <p>
	 * Key will be String and it is the first segment of a container path. Value will {@link ContainerPaths}. This
	 * will contain all of the visible and hidden paths that the project can see. All of the paths will have
	 * the key (container id) as the first segment of the path.
	 * 
	 * @return map of container ids. Keys of type String. Values will be {@link ContainerPaths} Empty if launch not in a project.
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
