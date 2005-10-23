/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.action;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.ui.actions.ActionDelegate;

public class RemoveBuilderAction extends ActionDelegate {

	IJavaProject project;
	final static String BUILDERID="org.eclipse.jst.j2ee.ejb.annotations.xdoclet.xdocletbuilder";
	
	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, selection);
		if (selection == null)
			return;

		if (selection instanceof IStructuredSelection) {
			project = (IJavaProject) ((IStructuredSelection) selection).getFirstElement();
		}
	}

	public void run(IAction action) {

		if (project != null && (J2EEProjectUtilities.isEJBProject(project.getProject()) || J2EEProjectUtilities.isDynamicWebProject(project.getProject()))) {
			try {
				ProjectUtilities.removeFromBuildSpec(BUILDERID,project.getProject());
			} catch (CoreException e) {
			}
		}
	}


}
