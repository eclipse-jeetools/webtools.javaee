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
 * Created on Jan 12, 2004
 * 
 * To change the template for this generated file go to Window - Preferences -
 * Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.archive.operations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;

import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.WorkbenchByteArrayOutputStream;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class BinaryProjectHelper {

	public void importArchiveAsBinary(Archive archive, IProject project, IProgressMonitor monitor) {
		try {
			IFile savedModuleFile = null;
			savedModuleFile = saveFile(archive, project);
			saveEnclosedFile(archive, project, ProjectUtilities.DOT_CLASSPATH);
			saveEnclosedFile(archive, project, ProjectUtilities.DOT_PROJECT);
			removeImportedClassesFromClasspathIfNecessary(project);
			ProjectUtilities.forceClasspathReload(project);
			ensureBinary(project);
			IPath path = savedModuleFile.getFullPath();
			IClasspathEntry newEntry = JavaCore.newLibraryEntry(path, path, null, true);
			ProjectUtilities.appendJavaClassPath(project, newEntry);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//TODO
		// IJ2EENature nature =
		// (IJ2EENature)J2EENature.getRegisteredRuntime(aProject);
		//        if (nature != null)
		//            nature.recomputeBinaryProject();

	}

	/**
	 *  
	 */
	public static void removeImportedClassesFromClasspathIfNecessary(IProject project) {
		IJavaProject javaProj = ProjectUtilities.getJavaProject(project);
		if (javaProj != null) {
			IClasspathEntry[] entries = javaProj.readRawClasspath();
			if (entries != null) {
				IClasspathEntry entryToRemove = null;
				for (int i = 0; i < entries.length; i++) {
					if (entries[i].getEntryKind() == IClasspathEntry.CPE_LIBRARY && entries[i].getPath().toString().endsWith("imported_classes") && !project.getFolder("imported_classes").exists()) { //$NON-NLS-1$ //$NON-NLS-2$
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

	protected void ensureBinary(IProject p) {
		IJavaProject javaP = ProjectUtilities.getJavaProject(p);
		if (javaP == null)
			return;
		List newCp = new ArrayList();
		try {
			IClasspathEntry[] entries = javaP.getRawClasspath();
			for (int i = 0; i < entries.length; i++) {
				IClasspathEntry entry = entries[i];
				if (entry.getEntryKind() != IClasspathEntry.CPE_SOURCE)
					newCp.add(entry);
			}
			entries = (IClasspathEntry[]) newCp.toArray(new IClasspathEntry[newCp.size()]);
			javaP.setRawClasspath(entries, new NullProgressMonitor());
		} catch (JavaModelException ex) {
			ex.printStackTrace();
		}
	}

}