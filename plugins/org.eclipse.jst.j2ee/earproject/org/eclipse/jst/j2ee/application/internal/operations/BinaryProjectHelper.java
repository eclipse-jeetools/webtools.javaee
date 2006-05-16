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
package org.eclipse.jst.j2ee.application.internal.operations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.emf.workbench.WorkbenchByteArrayOutputStream;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;

public class BinaryProjectHelper {

	public void importArchiveAsBinary(Archive archive, IProject project, IProgressMonitor monitor) {
		try {
					
			IJavaProject javaProject = JavaCore.create(project);
			
			IFile savedModuleFile = saveFile(archive, project);
			saveEnclosedFile(archive, project, ProjectUtilities.DOT_CLASSPATH);
			saveEnclosedFile(archive, project, ProjectUtilities.DOT_PROJECT);
			removeImportedClassesFromClasspathIfNecessary(project);
			
			ensureBinary(javaProject, monitor);
			IPath path = savedModuleFile.getFullPath();
			
			IClasspathEntry newEntry = JavaCore.newLibraryEntry(path, path, null, true);
						
			IClasspathEntry[] entries = javaProject.getRawClasspath();
			IClasspathEntry[] newEntries = new IClasspathEntry[entries.length + 1];
			System.arraycopy(entries, 0, newEntries, 1, entries.length);
			newEntries[0] = newEntry;			
			javaProject.setRawClasspath(newEntries, monitor);
								 
		} catch (FileNotFoundException e) {
			Logger.getLogger().log(e);
		} catch (IOException e) {
			Logger.getLogger().log(e);
		} catch (JavaModelException e) {
			Logger.getLogger().log(e);
		} 
	} 

	/**
	 * 
	 */
	public static void removeImportedClassesFromClasspathIfNecessary(IProject project) {
		IJavaProject javaProj = JavaCore.create(project);
		if (javaProj != null) {
			IClasspathEntry[] entries = javaProj.readRawClasspath();
			if (entries != null) {
				IClasspathEntry entryToRemove = null;
				for (int i = 0; i < entries.length; i++) {
					if (entries[i].getEntryKind() == IClasspathEntry.CPE_LIBRARY && entries[i].getPath().toString().endsWith("imported_classes") && !project.getFolder("imported_classes").exists()) {
						entryToRemove = entries[i];
						break;
					}
				}
				if (null != entryToRemove) {
					IClasspathEntry[] newEntries = new IClasspathEntry[entries.length - 1];
					for (int i = 0, j = 0; i < newEntries.length && j < entries.length; j++) {
						if (entryToRemove != entries[j]) {
							newEntries[i] = entries[j];
							i++;
						}
					}
					entries = newEntries;
					IPath output = javaProj.readOutputLocation();
					if (output != null)
						try {
							javaProj.setRawClasspath(entries, output, null);
						} catch (JavaModelException e) {
						}
				}

			}
		}
	}

	protected IFile saveFile(File aFile, IProject p) throws IOException {
		IFile iFile = p.getFile(aFile.getURI());
		WorkbenchByteArrayOutputStream out = new WorkbenchByteArrayOutputStream(iFile);
		ArchiveUtil.copy(aFile.getInputStream(), out);
		return iFile;
	}

	protected void saveEnclosedFile(Archive anArchive, IProject p, String uri) throws IOException {
		try {
			File aFile = anArchive.getFile(uri);
			saveFile(aFile, p);
		} catch (FileNotFoundException ignore) {
		}
	}

	protected void ensureBinary(IJavaProject javaProject, IProgressMonitor monitor) {
		
		if (javaProject == null)
			return;
		List newCp = new ArrayList();
		try {
			IClasspathEntry[] entries = javaProject.getRawClasspath();
			for (int i = 0; i < entries.length; i++) { 
				if (entries[i].getEntryKind() != IClasspathEntry.CPE_SOURCE)
					newCp.add(entries[i]);
			}
			entries = (IClasspathEntry[]) newCp.toArray(new IClasspathEntry[newCp.size()]);
			javaProject.setRawClasspath(entries, monitor);
		} catch (JavaModelException ex) {
			Logger.getLogger().log(ex);
		}
	}

}

