/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.taglib.registry;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.web.locator.JavaProjectTaglibLocator;
import org.eclipse.jst.j2ee.web.taglib.ITaglibLocator;


public class JavaTaglibRegistry extends AbstractTaglibRegistry {

	protected IPath classpathFilePath;

	/**
	 * Constructor for JavaTaglibRegistry.
	 * 
	 * @param project
	 */
	public JavaTaglibRegistry(IProject project) {
		super(project);
	}

	protected void initialize() {
		this.locators = new ITaglibLocator[]{new JavaProjectTaglibLocator(this.project)};
	}

	protected boolean isWebXMLFile(IPath filePath) {
		return false;
	}

	protected IPath getClasspathFilePath() {
		if (this.classpathFilePath == null)
			this.classpathFilePath = getProject().getFullPath().append(".classpath"); //$NON-NLS-1$
		return this.classpathFilePath;
	}

	protected IJavaProject getJavaProject() {
		return JavaCore.create(getProject());
	}

	protected IResource getRefreshRoot() {
		try {
			return getProject().getWorkspace().getRoot().findMember(getJavaProject().getOutputLocation());
		} catch (JavaModelException e) {
			//Do nothing
		}
		return getProject();
	}

	/**
	 * @see AbstractTaglibRegistry#requiresFullUpdate(IResourceDelta)
	 */
	protected boolean requiresFullUpdate(IResourceDelta delta) {
		// If the websettings are updated, a full refresh is required.
		return delta.getResource().getFullPath().equals(getClasspathFilePath());
	}


}