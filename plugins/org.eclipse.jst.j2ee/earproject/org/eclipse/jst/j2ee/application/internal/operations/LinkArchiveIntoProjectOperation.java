/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.osgi.util.NLS;

public class LinkArchiveIntoProjectOperation extends J2EEUtilityJarImportAssistantOperation {


	private String linkedPathVariable;

	public LinkArchiveIntoProjectOperation(File utilityJar, String linkedPathVariable) {
		super(NLS.bind(EARCreationResourceHandler.LinkArchiveIntoProjectOperation_Linking_archive_into_selected_proje_, utilityJar.getName()), utilityJar);
		this.linkedPathVariable = linkedPathVariable;

	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		MultiStatus status = new MultiStatus(J2EEPlugin.PLUGIN_ID, 0, NLS.bind(EARCreationResourceHandler.LinkArchiveIntoProjectOperation_Linking_archive_into_selected_proje_, getUtilityJar().getName()), null);

		IProject project = getWorkspaceRoot().getProject(getAssociatedEARProjectName());

		try {
			IFile linkedJarFile = project.getFile(getUtilityJar().getName());
			if (linkedJarFile.exists()) {
				if (isOverwriteIfNecessary())
					linkedJarFile.delete(true, true, new SubProgressMonitor(monitor, 1));
				else {
					status.add(J2EEPlugin.createErrorStatus(0, NLS.bind(EARCreationResourceHandler.CreateProjectWithLinkedJarOperation_File_already_exists_0_, linkedJarFile.getFullPath()), null));
					return status;
				}
			}
			status.add(createLinkedArchive(project, getUtilityJar().getName(), getUtilityJar(), linkedPathVariable, monitor));

			addLibraryToClasspath(project, linkedJarFile, monitor);
			
			createVirtualArchiveComponent(project, getUtilityJar().getName(), linkedJarFile, monitor);

		} catch (CoreException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
		} catch (InvocationTargetException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
		} catch (InterruptedException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
		}
		return status;
	}

	public IStatus redo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}

	public IStatus undo(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		return Status.CANCEL_STATUS;
	}

}
