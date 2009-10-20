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
import org.eclipse.jdt.core.IJavaProject;

/**
 * @see IJavaProjectLite
 */
public final class JavaProjectLite implements IJavaProjectLite {
	private final IJavaProject _javaProject;

	JavaProjectLite(IJavaProject javaProject) {
		this._javaProject = javaProject;
	}

	/**
	 * @see IJavaProjectLite#readRawClasspath()
	 */
	public final IClasspathEntry[] readRawClasspath() {
		return _javaProject.readRawClasspath();
	}

	/**
	 * @see IJavaProjectLite#readOutputLocation()
	 */
	public final IPath readOutputLocation() {
		return _javaProject.readOutputLocation();
	}

	/**
	 * @see IJavaProjectLite#getProject()
	 */
	public final IProject getProject() {
		return _javaProject.getProject();
	}

	public final boolean exists() {
		return _javaProject.exists();
	}
	
	/**
	 * @see IJavaProjectLite#isOpen()
	 */
	public final boolean isOpen() {
		return _javaProject.isOpen();
	}
	
	/**
	 * @see IJavaProjectLite#hasBuildState()
	 */
	public final boolean hasBuildState(){
		return _javaProject.hasBuildState();
	}
}
