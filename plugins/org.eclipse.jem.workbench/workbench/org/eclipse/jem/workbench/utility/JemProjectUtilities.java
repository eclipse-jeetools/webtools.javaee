package org.eclipse.jem.workbench.utility;
/***************************************************************************************************
 * Copyright (c) 2001, 2003 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * $RCSfile: JemProjectUtilities.java,v $ $Revision: 1.1 $ $Date: 2004/01/13 21:12:11 $
 */

import java.util.List;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.IPath;

import org.eclipse.wtp.emf.workbench.ProjectUtilities;
import org.eclipse.wtp.emf.workbench.WorkbenchResourceHelper;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;

public class JemProjectUtilities {

	public static JavaClass getJavaClass(IFile aFile) {
		if (aFile == null)
			return null;
		IProject project = aFile.getProject();
		List folders = ProjectUtilities.getSourceContainers(project);
		folders.addAll(ProjectUtilities.getLibraryContainers(project));
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
					return (JavaClass) JavaRefFactory.eINSTANCE.reflectType(qualifiedName, WorkbenchResourceHelper.getResourceSet(project));
				}
			}
		}
		return null;
	}
}