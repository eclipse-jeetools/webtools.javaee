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
 * Created on Nov 4, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.common.jdt.internal.integration;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.wst.common.frameworks.internal.WTPProjectUtilities;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationOperation;


/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JavaProjectCreationOperation extends ProjectCreationOperation {

	public JavaProjectCreationOperation(JavaProjectCreationDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		super.execute(monitor);
		createJavaProject(monitor);
	}

	private void createJavaProject(IProgressMonitor monitor) throws CoreException {
		JavaProjectCreationDataModel model = (JavaProjectCreationDataModel) operationDataModel;
		IProject project = model.getProject();
		WTPProjectUtilities.addNatureToProjectLast(project, JavaCore.NATURE_ID);
		IJavaProject javaProject = JavaCore.create(project);
		javaProject.setOutputLocation(model.getOutputPath(), monitor);
		javaProject.setRawClasspath(model.getClasspathEntries(), monitor);
		if (model.getBooleanProperty(JavaProjectCreationDataModel.CREATE_SOURCE_FOLDERS)) {
			String[] sourceFolders = (String[]) model.getProperty(JavaProjectCreationDataModel.SOURCE_FOLDERS);
			IFolder folder = null;
			for (int i = 0; i < sourceFolders.length; i++) {
				folder = project.getFolder(sourceFolders[i]);
				folder.create(true, true, monitor);
			}
		}
	}
}