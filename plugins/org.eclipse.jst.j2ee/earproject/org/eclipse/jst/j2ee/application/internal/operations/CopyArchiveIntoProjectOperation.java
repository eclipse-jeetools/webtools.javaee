/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;

public class CopyArchiveIntoProjectOperation extends J2EEUtilityJarImportAssistantOperation {

	public CopyArchiveIntoProjectOperation(File utilityJar) {
		super(NLS.bind(EARCreationResourceHandler.CopyArchiveIntoProjectOperation_Copying_archive_into_selected_proje_, utilityJar.getName()), utilityJar);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		MultiStatus status = new MultiStatus(J2EEPlugin.PLUGIN_ID, 0, NLS.bind(EARCreationResourceHandler.CopyArchiveIntoProjectOperation_Copying_archive_into_selected_proje_, getUtilityJar().getName()), null);
		 
		try {

			IFile copiedJarFile = null;
			
			IProject associatedEARProject = getWorkspaceRoot().getProject(getAssociatedEARProjectName()); 
			IVirtualComponent earComponent = ComponentCore.createComponent(associatedEARProject);
			
			IContainer underlyingFolder = earComponent.getRootFolder().getUnderlyingFolder();
			if(underlyingFolder.isAccessible()) {
				 copiedJarFile = underlyingFolder.getFile(new Path(getUtilityJar().getName()));
			} else {
				 copiedJarFile = associatedEARProject.getFile(getUtilityJar().getName());
			}		
 			if (copiedJarFile.exists()) {
				if (isOverwriteIfNecessary())
					copiedJarFile.delete(true, true, new SubProgressMonitor(monitor, 1));
				else {
					status.add(J2EEPlugin.createErrorStatus(0, NLS.bind(EARCreationResourceHandler.CopyArchiveIntoProjectOperation_Found_existing_file_0_, copiedJarFile), null));
					return status;
				}
			} 
			
			FileInputStream fileInputStream = null;
			ByteArrayOutputStream bos = null;
			ByteArrayInputStream jarFileInputStream = null;
			try {
				fileInputStream = new FileInputStream(getUtilityJar());
				bos = new ByteArrayOutputStream();
				byte[] data = new byte[4096];
				try {
					int bytesRead = 0;
					while ((bytesRead = fileInputStream.read(data)) > 0)
						bos.write(data, 0, bytesRead);
					// clear space for GC
					data = null;
				} finally {
					if(fileInputStream != null) 
						fileInputStream.close();
				}

				jarFileInputStream = new ByteArrayInputStream(bos.toByteArray());
				copiedJarFile.create(jarFileInputStream, 0, new SubProgressMonitor(monitor, 1));

				addLibraryToClasspath(associatedEARProject, copiedJarFile, monitor);
				
				createVirtualArchiveComponent(associatedEARProject, getUtilityJar().getName(), copiedJarFile, monitor);
				
				
			} catch (FileNotFoundException e) {
				status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
				J2EEPlugin.logError(0, e.getMessage(), e);
			} catch (InvocationTargetException e) {
				status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
				J2EEPlugin.logError(0, e.getMessage(), e);
			} catch (InterruptedException e) {
				status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
				J2EEPlugin.logError(0, e.getMessage(), e);
			} finally {
				if (bos != null)
					bos.close();
				if (jarFileInputStream != null)
					jarFileInputStream.close();
			} 
		} catch (IOException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
			J2EEPlugin.logError(0, e.getMessage(), e);  
		} catch (CoreException e) {
			status.add(J2EEPlugin.createErrorStatus(0, e.getMessage(), e));
			J2EEPlugin.logError(0, e.getMessage(), e);
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
