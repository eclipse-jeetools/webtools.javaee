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
 * Created on Sep 26, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.j2ee.internal.dialogs.J2EERenameUIConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.ltk.core.refactoring.Change;
import org.eclipse.ltk.core.refactoring.RefactoringStatus;
import org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext;
import org.eclipse.ltk.core.refactoring.participants.RenameParticipant;
import org.eclipse.wst.common.framework.AdaptabilityUtility;


/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class J2EERenameParticipant extends RenameParticipant {

	private static final Class IPROJECT_CLASS = IProject.class;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#initialize(java.lang.Object)
	 */
	protected boolean initialize(Object element) {
		if (element == null)
			return false;

		IProject project = (IProject) AdaptabilityUtility.getAdapter(element, IPROJECT_CLASS);
		if (project.isAccessible()) {

			if (J2EENature.getRegisteredRuntime(project) != null)
				return true;

			// only return true for utility jar projects
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			EAREditModel model = null;
			UtilityJARMapping mapping = null;
			IProject[] allProjects = root.getProjects();
			for (int i = 0; i < allProjects.length; i++) {
				EARNatureRuntime earNature = EARNatureRuntime.getRuntime(allProjects[i]);
				if (earNature != null) {
					try {
						model = earNature.getEarEditModelForRead(this);
						mapping = model.getUtilityJARMapping(project);

						if (mapping != null)
							return true;

					} finally {
						if (model != null) {
							model.releaseAccess(this);
							model = null;
						}
					}
				}
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#createChange(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public Change createChange(IProgressMonitor pm) throws CoreException, OperationCanceledException {
		Object[] targetElements = getProcessor().getElements();
		if (targetElements == null || targetElements.length != 1)
			return null;
		IProject project = (IProject) AdaptabilityUtility.getAdapter(targetElements[0], IPROJECT_CLASS);
		if (project != null)
			return new J2EEModuleRenameChange(project, getArguments().getNewName(), getArguments().getUpdateReferences());
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#getName()
	 */
	public String getName() {
		return J2EERenameUIConstants.RENAME;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ltk.core.refactoring.participants.RefactoringParticipant#checkConditions(org.eclipse.core.runtime.IProgressMonitor,
	 *      org.eclipse.ltk.core.refactoring.participants.CheckConditionsContext)
	 */
	public RefactoringStatus checkConditions(IProgressMonitor pm, CheckConditionsContext context) throws OperationCanceledException {
		return RefactoringStatus.create(Status.OK_STATUS);
	}
}