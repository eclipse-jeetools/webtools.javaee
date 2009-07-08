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
