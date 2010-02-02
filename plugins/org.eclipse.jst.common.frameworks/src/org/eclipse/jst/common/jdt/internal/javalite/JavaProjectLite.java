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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;

/**
 * @see IJavaProjectLite
 */
public final class JavaProjectLite implements IJavaProjectLite {
	private final IJavaProject _javaProject;

	private Object lock = new Object();
	private boolean _javaProjectInitialized = false;
	private boolean _rawClasspathRead = false;
	private IClasspathEntry[] _rawClasspath;
	private boolean _rawOutputLocationRead = false;
	private IPath _rawOutputLocation;
	private long _dotClasspathModificationStamp = IFile.NULL_STAMP;

	JavaProjectLite(IJavaProject javaProject, boolean javaProjectInitialized) {
		this._javaProject = javaProject;
		this._javaProjectInitialized = javaProjectInitialized;
	}

	void markJavaProjectInitialized() {
		synchronized (lock) {
			if (!_javaProjectInitialized) {
				_javaProjectInitialized = true;
				flushClasspath();
			}
		}
	}

	private boolean isJavaProjectInitialized() {
		synchronized (lock) {
			return _javaProjectInitialized;
		}
	}

	void flushClasspath() {
		synchronized (lock) {
			_rawClasspathRead = false;
			_rawClasspath = null;
			_rawOutputLocationRead = false;
			_rawOutputLocation = null;
			_dotClasspathModificationStamp = IFile.NULL_STAMP;
		}
	}

	private void verifyDotClasspathModificationStamp() {
		long modificationStamp = IFile.NULL_STAMP;
		synchronized (lock) {
			modificationStamp = _dotClasspathModificationStamp;
		}
		if (modificationStamp == IFile.NULL_STAMP) {
			flushClasspath();
			return;
		}
		IFile dotClasspath = _javaProject.getProject().getFile(".classpath"); //$NON-NLS-1$
		if (!dotClasspath.exists()) {
			flushClasspath();
			return;
		}
		if (modificationStamp != dotClasspath.getModificationStamp()) {
			flushClasspath();
			return;
		}
	}

	private void updateDotClasspathModificationStamp() {
		IFile dotClasspath = _javaProject.getProject().getFile(".classpath"); //$NON-NLS-1$
		long modificationStamp = dotClasspath.getModificationStamp();
		synchronized (lock) {
			_dotClasspathModificationStamp = modificationStamp;
		}
	}

	public final IClasspathEntry[] readRawClasspath() {
		if (!isJavaProjectInitialized()) {
			if (JavaCoreLite.isInitialized(_javaProject)) {
				markJavaProjectInitialized();
			}
		}

		if (isJavaProjectInitialized()) {
			try {
				return _javaProject.getRawClasspath();
			} catch (JavaModelException e) {
				CommonFrameworksPlugin.log(e);
			}
		}

		verifyDotClasspathModificationStamp();

		boolean dataRead = false;
		synchronized (lock) {
			dataRead = _rawClasspathRead;
		}
		if (!dataRead) {
			updateDotClasspathModificationStamp();
			IClasspathEntry[] rawClasspath = _javaProject.readRawClasspath();
			synchronized (lock) {
				_rawClasspathRead = true;
				_rawClasspath = rawClasspath;
			}
		}
		return _rawClasspath;
	}

	public final IPath readOutputLocation() {
		if (!isJavaProjectInitialized()) {
			if (JavaCoreLite.isInitialized(_javaProject)) {
				markJavaProjectInitialized();
			}
		}

		if (isJavaProjectInitialized()) {
			try {
				return _javaProject.getOutputLocation();
			} catch (JavaModelException e) {
				CommonFrameworksPlugin.log(e);
			}
		}

		verifyDotClasspathModificationStamp();

		boolean dataRead = false;
		synchronized (lock) {
			dataRead = _rawOutputLocationRead;
		}
		if (!dataRead) {
			updateDotClasspathModificationStamp();
			IPath rawOutputLocation = _javaProject.readOutputLocation();
			if (rawOutputLocation == null) {
				// borrowed from JavaProject.getDefaultOutputLocation()
				rawOutputLocation = _javaProject.getProject().getFullPath().append("bin"); //$NON-NLS-1$
			}
			synchronized (lock) {
				_rawOutputLocationRead = true;
				_rawOutputLocation = rawOutputLocation;
			}
		}
		return _rawOutputLocation;
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
	public final boolean hasBuildState() {
		return _javaProject.hasBuildState();
	}

}