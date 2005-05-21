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
 * Created on Sep 2, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyDataModel;
import org.eclipse.jst.j2ee.internal.common.operations.JARDependencyOperation;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

/**
 * @author schacher
 */
public class InvertClientJARDependencyCompoundOperation extends WTPOperation {

	protected EARNatureRuntime[] earNatures;
	protected IProject oldProject;
	protected IProject newProject;
	protected List childOperations = null;
	protected IProgressMonitor monitor;

	public InvertClientJARDependencyCompoundOperation(EARNatureRuntime[] referencingEARs, IProject anOldProject, IProject aNewProject) {
		earNatures = referencingEARs;
		oldProject = anOldProject;
		newProject = aNewProject;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.operations.HeadlessJ2EEOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor aMonitor) throws CoreException, InvocationTargetException, InterruptedException {
		monitor = aMonitor;
		createChildOperations();
		executeChildOperations();
	}

	private void createChildOperations() {
		childOperations = new ArrayList();
		for (int i = 0; i < earNatures.length; i++) {
			Iterator projects = earNatures[i].getAllMappedProjects().values().iterator();
			while (projects.hasNext()) {
				IProject project = (IProject) projects.next();
				if (project != null && !project.equals(oldProject) && !project.equals(newProject) && !JemProjectUtilities.isBinaryProject(project)) {
					JARDependencyDataModel model = new JARDependencyDataModel();
					model.setIntProperty(JARDependencyDataModel.JAR_MANIPULATION_TYPE, JARDependencyDataModel.JAR_MANIPULATION_INVERT);
					model.setProperty(JARDependencyDataModel.PROJECT_NAME, project.getName());
					model.setProperty(JARDependencyDataModel.OPPOSITE_PROJECT_NAME, newProject.getName());
					model.setProperty(JARDependencyDataModel.EAR_PROJECT_NAME, earNatures[i].getProject().getName());
					model.setProperty(JARDependencyDataModel.REFERENCED_PROJECT_NAME, oldProject.getName());
					childOperations.add(new JARDependencyOperation(model));
				}
			}
		}
	}

	private void executeChildOperations() throws InvocationTargetException, InterruptedException {
		monitor.beginTask("", childOperations.size()); //$NON-NLS-1$
		for (int i = 0; i < childOperations.size(); i++) {
			JARDependencyOperation op = (JARDependencyOperation) childOperations.get(i);
			op.run(new SubProgressMonitor(monitor, 1));
		}
	}
}