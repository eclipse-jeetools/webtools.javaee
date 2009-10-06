/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.jdt.internal.javalite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;

/**
 * <p>
 * Represents a scaled down version of an {@link IJavaProject}. This subset of
 * methods is guaranteed to not force a load of the underlying
 * {@link IJavaModel}. This enables safe access to a limited set of
 * {@link IJavaProject} API for multi-threaded clients to call during times of
 * extreme concurrency, e.g. workbench startup.
 * </p>
 * <p>
 * An instance of one of these handles can be created via
 * <code>JavaCoreLite.create(project)</code>.
 * </p>
 * 
 * @see JavaCore#create(org.eclipse.core.resources.IProject)
 * @see IJavaProject
 * @see IClasspathEntry
 */
public interface IJavaProjectLite {
	/**
	 * @see IJavaProject#readRawClasspath()
	 * 
	 * @return
	 */
	IClasspathEntry[] readRawClasspath();

	/**
	 * @see IJavaProject#readOutputLocation()
	 * 
	 * @return
	 */
	IPath readOutputLocation();

	/**
	 * @see IJavaProject#getProject()
	 * 
	 * @return
	 */
	IProject getProject();

	/**
	 * @see IJavaProject#exists()
	 */
	boolean exists();
	
	/**
	 * @see IJavaProject#isOpen()
	 * @return
	 */
	boolean isOpen();

	/**
	 * @see IJavaProject#hasBuildState()
	 * @return
	 */
	public boolean hasBuildState();
}
