/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;


public class LibModule implements ILibModule {
	protected static final IPath LIB_PATH = new Path(IWebNatureConstants.INFO_DIRECTORY).append(IWebNatureConstants.LIBRARY_DIRECTORY);

	private String jarName;
	private String projectName;

	public LibModule(String jarName, String projectName) {
		this.jarName = jarName;
		this.projectName = projectName;
	}

	/*
	 * @see ILibModule#getJarName()
	 */
	public String getJarName() {
		return jarName;
	}

	/*
	 * @see ILibModule#getProjectName()
	 */
	public String getProjectName() {
		return projectName;
	}

	/*
	 * @see ILibModule#getProject()
	 */
	public IProject getProject() {
		return (IProject) J2EEPlugin.getWorkspace().getRoot().findMember(projectName);
	}

	/**
	 * @see ILibModule#getURI()
	 */
	public String getURI() {
		return IPath.SEPARATOR + LIB_PATH.append(getJarName()).toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof ILibModule)) {
			return false;
		}
		ILibModule module = (ILibModule) obj;
		return getJarName().equals(module.getJarName()) && getProjectName().equals(module.getProjectName()) && getURI().equals(module.getURI()) && getProject().equals(module.getProject());
	}

}