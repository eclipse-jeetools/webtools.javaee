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

import java.util.ArrayList;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.WTPProjectUtilities;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationOp;

public class JavaProjectCreationOp extends ProjectCreationOp {

	public JavaProjectCreationOp(IDataModel dataModel) {
		super(dataModel);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		super.execute(monitor, info);
		try {
			createJavaProject(monitor);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return OK_STATUS;
	}

	private void createJavaProject(IProgressMonitor monitor) throws CoreException {
		IProject project = (IProject) model.getProperty(IJavaProjectCreationProperties.PROJECT);
		WTPProjectUtilities.addNatureToProjectLast(project, JavaCore.NATURE_ID);
		IJavaProject javaProject = JavaCore.create(project);
		javaProject.setOutputLocation(getOutputPath(project), monitor);
		javaProject.setRawClasspath(getClasspathEntries(project), monitor);
		if (model.getBooleanProperty(IJavaProjectCreationProperties.CREATE_SOURCE_FOLDERS)) {
			String[] sourceFolders = (String[]) model.getProperty(IJavaProjectCreationProperties.SOURCE_FOLDERS);
			IFolder folder = null;
			for (int i = 0; i < sourceFolders.length; i++) {
				folder = project.getFolder(sourceFolders[i]);
				folder.create(true, true, monitor);
			}
		}
	}

	private IPath getOutputPath(IProject project) {
		String outputLocation = model.getStringProperty(IJavaProjectCreationProperties.OUTPUT_LOCATION);
		return project.getFullPath().append(outputLocation);
	}

	private IClasspathEntry[] getClasspathEntries(IProject project) {
		IClasspathEntry[] entries = (IClasspathEntry[]) model.getProperty(IJavaProjectCreationProperties.CLASSPATH_ENTRIES);
		IClasspathEntry[] sourceEntries = null;
		if (model.getBooleanProperty(IJavaProjectCreationProperties.CREATE_SOURCE_FOLDERS))
			sourceEntries = getSourceClasspathEntries(project);
		return combineArrays(sourceEntries, entries);
	}

	private IClasspathEntry[] getSourceClasspathEntries(IProject project) {
		String[] sourceFolders = (String[]) model.getProperty(IJavaProjectCreationProperties.SOURCE_FOLDERS);
		ArrayList list = new ArrayList();
		for (int i = 0; i < sourceFolders.length; i++) {
			list.add(JavaCore.newSourceEntry(project.getFullPath().append(sourceFolders[i])));
		}
		IClasspathEntry[] classpath = new IClasspathEntry[list.size()];
		for (int i = 0; i < classpath.length; i++) {
			classpath[i] = (IClasspathEntry) list.get(i);
		}
		return classpath;
	}

	private IClasspathEntry[] combineArrays(IClasspathEntry[] sourceEntries, IClasspathEntry[] entries) {
		if (sourceEntries != null) {
			if (entries == null)
				return sourceEntries;
			return doCombineArrays(sourceEntries, entries);
		} else if (entries != null)
			return entries;
		return new IClasspathEntry[0];
	}

	private IClasspathEntry[] doCombineArrays(IClasspathEntry[] sourceEntries, IClasspathEntry[] entries) {
		IClasspathEntry[] result = new IClasspathEntry[sourceEntries.length + entries.length];
		System.arraycopy(sourceEntries, 0, result, 0, sourceEntries.length);
		System.arraycopy(entries, 0, result, sourceEntries.length, entries.length);
		return result;
	}

}