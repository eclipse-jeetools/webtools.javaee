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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.ui.actions.ActionDelegate;

public abstract class XDocletActionDelegate extends ActionDelegate {

	public static final String BUILDERID = "org.eclipse.jst.j2ee.ejb.annotations.xdoclet.xdocletbuilder";
	protected IProject project;
	protected IFile aFile = null;

	class SourceFinder implements IResourceVisitor {
		IFile sourceFile = null;

		public boolean visit(IResource resource) throws org.eclipse.core.runtime.CoreException {
			if (sourceFile != null)
				return false;
			if (resource instanceof IFile) {
				sourceFile = (IFile) resource;
				return false;
			}
			return true;
		}

		public IFile getSourceFile() {
			return sourceFile;
		}

	}

	public void selectionChanged(IAction action, ISelection selection) {
		super.selectionChanged(action, selection);
		if (selection == null)
			return;

		if (selection instanceof IStructuredSelection) {
			Object firstSelection = ((IStructuredSelection) selection).getFirstElement();
			if (firstSelection instanceof IProject)
				project = (IProject) firstSelection;
			if (firstSelection instanceof IFile) {
				aFile = (IFile) firstSelection;
				project = aFile.getProject();
			}
		}
	}

	public IProject getProject() {
		return project;
	}

	protected IFile getFirstSourceFile() {
		IFolder resource = (IFolder) J2EEProjectUtilities.getSourceFolderOrFirst(project.getProject(), null);
		SourceFinder finder = new SourceFinder();
		try {

			resource.accept(finder);
		} catch (CoreException e) {
		}
		return finder.getSourceFile();
	}

	public IFile getFile() {
		return aFile;
	}

}
