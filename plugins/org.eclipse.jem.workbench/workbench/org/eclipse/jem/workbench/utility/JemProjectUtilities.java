/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.workbench.utility;
/*
 * $RCSfile: JemProjectUtilities.java,v $ $Revision: 1.6 $ $Date: 2005/02/15 23:09:27 $
 */

import java.util.*;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.*;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jem.util.emf.workbench.WorkbenchResourceHelperBase;
import org.eclipse.jem.util.plugin.JEMUtilPlugin;


public class JemProjectUtilities {

	public static JavaClass getJavaClass(IFile aFile) {
		if (aFile == null)
			return null;
		IProject project = aFile.getProject();
		List folders = getSourceContainers(project);
		folders.addAll(getLibraryContainers(project));
		IContainer folder = null;
		IPath folderPath, filePath, javaPath;
		filePath = aFile.getProjectRelativePath();
		if (folders != null) {
			for (int i = 0; i < folders.size(); i++) {
				folder = (IContainer) folders.get(i);
				folderPath = folder.getProjectRelativePath();
				int segments = filePath.matchingFirstSegments(folderPath);
				if (segments > 0) {
					javaPath = filePath.removeFirstSegments(segments);
					javaPath = javaPath.removeFileExtension();
					String qualifiedName = javaPath.toString().replace('/', '.');
					return (JavaClass) JavaRefFactory.eINSTANCE.reflectType(qualifiedName, WorkbenchResourceHelperBase.getResourceSet(project));
				}
			}
		}
		return null;
	}
	
	public static List getSourceContainers(IProject p) {
		try {
			List sourceContainers = new ArrayList();
			List sourcePaths = getSourcePaths(p);
			if (sourcePaths != null && !sourcePaths.isEmpty()) {
				for (int i = 0; i < sourcePaths.size(); i++) {
					IPath path = (IPath) sourcePaths.get(i);
					if (path.isEmpty())
						sourceContainers.add(p);
					else
						sourceContainers.add(p.getFolder(path));
				}
			}
			return sourceContainers;
		} catch (IllegalArgumentException ex) {
			return Collections.EMPTY_LIST;
		}
	}
	protected static List getSourcePaths(IProject p) {
		IJavaProject javaProj = getJavaProject(p);
		if (javaProj == null)
			return null;
		IClasspathEntry[] cp = null;
		try {
			cp = javaProj.getRawClasspath();
		} catch (JavaModelException ex) {
			JEMUtilPlugin.getLogger().logError(ex);
			return null;
		}
		List sourcePaths = new ArrayList();
		for (int i = 0; i < cp.length; i++) {
			if (cp[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				sourcePaths.add(cp[i].getPath().removeFirstSegments(1));
			}
		}
		return sourcePaths;
	}
	
	/**
	 * return list of path that contain classes
	 */
	public static List getLibraryContainers(IProject p) {
		try {
			List libraryContainers = new ArrayList();
			List libraryPaths = getlibraryPaths(p);
			if (libraryPaths != null && !libraryPaths.isEmpty()) {
				for (int i = 0; i < libraryPaths.size(); i++) {
					IPath path = (IPath) libraryPaths.get(i);
					if (path.isEmpty())
						libraryContainers.add(p);
					else
						libraryContainers.add(p.getFolder(path));
				}
			}
			return libraryContainers;
		} catch (IllegalArgumentException ex) {
			return Collections.EMPTY_LIST;
		}
	}
	
	/**
	 * return list of path that may contain classes
	 */
	protected static List getlibraryPaths(IProject p) {
		IJavaProject javaProj = getJavaProject(p);
		if (javaProj == null)
			return null;
		IClasspathEntry[] cp = null;
		try {
			cp = javaProj.getRawClasspath();
		} catch (JavaModelException ex) {
			JEMUtilPlugin.getLogger().logError(ex);
			return null;
		}
		List libraryPaths = new ArrayList();
		for (int i = 0; i < cp.length; i++) {
			if (cp[i].getEntryKind() == IClasspathEntry.CPE_LIBRARY) {
				libraryPaths.add(cp[i].getPath().removeFirstSegments(1));
			}
		}
		return libraryPaths;
	}
	
	public static IJavaProject getJavaProject(IProject p) {
		try {
			return (IJavaProject) p.getNature(JavaCore.NATURE_ID);
		} catch (CoreException ignore) {
			return null;
		}
	}
	
}
