/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.action;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.XDocletAntProjectBuilder;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.ui.actions.ActionDelegate;

public class RunXDocletAction extends ActionDelegate {

	IJavaProject project;
	IFile aFile = null;
	
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
			XDocletAntProjectBuilder builder = XDocletAntProjectBuilder.Factory.newInstance(project.getProject());
			IFolder resource = (IFolder)J2EEProjectUtilities.getSourceFolderOrFirst(project.getProject(), null);
			aFile = null;
			try {
				resource.accept(new IResourceVisitor(){
					public boolean visit(IResource resource) throws org.eclipse.core.runtime.CoreException {
						if(aFile != null)
							return false;
						if (resource instanceof IFile) {
							aFile = (IFile) resource;
							return false;
						}
						return true;
					};
				});
			} catch (CoreException e) {
				
			}
			
			builder.buildUsingAnt(aFile, new NullProgressMonitor());
		}
	}

}
