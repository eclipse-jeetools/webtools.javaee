/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.delete;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;

/**
 * A simple structure that remembers for a given project, the manifest entries to be deleted and the
 * build path entries to be deleted
 */
public class ClasspathDeleteInfo {
	/**
	 * The project from which the dependencies will be removed
	 */
	protected IProject targetProject;
	/**
	 * The literal manifest entries to be removed; String
	 */
	protected List manifestEntries;
	/**
	 * The project references to be removed from the build path; IProject
	 */
	protected List referencedProjects;

	public ClasspathDeleteInfo(IProject project) {
		super();
		targetProject = project;
	}

	/**
	 * Gets the manifestEntries.
	 * 
	 * @return Returns a List
	 */
	public List getManifestEntries() {
		if (manifestEntries == null)
			manifestEntries = new ArrayList();
		return manifestEntries;
	}


	/**
	 * Gets the referencedProjects.
	 * 
	 * @return Returns a List
	 */
	public List getReferencedProjects() {
		if (referencedProjects == null)
			referencedProjects = new ArrayList();
		return referencedProjects;
	}


	/**
	 * Gets the targetProject.
	 * 
	 * @return Returns a IProject
	 */
	public IProject getTargetProject() {
		return targetProject;
	}


}