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
/*
 * Created on Apr 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.migration;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.common.framework.operation.WTPOperation;
import org.eclipse.wst.common.migration.CompatibilityUtils;
import org.eclipse.wst.common.migration.TacitMigrationEngine;


/**
 * @author nagrawal
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */

public class RemoveCompatibilityOperation extends WTPOperation {

	IProject earProject = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public RemoveCompatibilityOperation(IProject project) {
		earProject = project;
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		IProject[] referencedProjects = earProject.getReferencedProjects();
		IProject[] projects = new IProject[referencedProjects.length + 1];

		System.arraycopy(referencedProjects, 0, projects, 0, referencedProjects.length);
		projects[referencedProjects.length] = earProject;

		TacitMigrationEngine migrationEngine = new TacitMigrationEngine();
		migrationEngine.migrateAllMappings(projects, true);

		for (int index = 0; index < projects.length; index++)
			CompatibilityUtils.setCompatibilityMode(projects[index], false);
		CompatibilityUtils.setCompatibilityMode(earProject, false);

	}
}

