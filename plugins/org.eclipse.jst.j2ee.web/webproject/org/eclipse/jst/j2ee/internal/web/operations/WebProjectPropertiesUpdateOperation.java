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
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.core.ClasspathEntry;
import org.eclipse.jst.j2ee.internal.web.archive.operations.ContextRootUpdateOperation;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebContentNameUpdateOperation;
import org.eclipse.wst.common.frameworks.operations.IHeadlessRunnableWithProgress;

/**
 * @version 1.0
 * @author
 */
public class WebProjectPropertiesUpdateOperation implements IHeadlessRunnableWithProgress {

	protected WebProjectInfo webProjectInfo;
//	protected J2EEWebNatureRuntime nature;


	public WebProjectPropertiesUpdateOperation(WebProjectInfo projectInfo) {
		webProjectInfo = projectInfo;
		IProject project = webProjectInfo.getProject();
		//nature = (J2EEWebNatureRuntime) J2EEWebNatureRuntimeUtilities.getRuntime(project);
	}

	/*
	 * @see IHeadlessRunnableWithProgress#run(IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

		IProject project = webProjectInfo.getProject();

		// Update the context root
		String contextRoot = webProjectInfo.getContextRoot();
		if (contextRoot != null) {
			new ContextRootUpdateOperation(project, contextRoot).run(monitor);
		}

		// Update the web content name
		String webContentName = webProjectInfo.getWebContentName();
		if (webContentName != null) {
			new WebContentNameUpdateOperation(project, webContentName).run(monitor);
		}

		// Update the Servlet and JSP Levels
		int servletLevel = webProjectInfo.getServletLevel();
		int jspLevel = webProjectInfo.getJSPLevel();

		try {
			updateClassPathEntries(monitor);
       //TODO setOn Module forwrite
		/*	if (servletLevel != null)
				nature.setServletLevel(servletLevel);
			if (jspLevel != null)
				nature.setJSPLevel(jspLevel);

*/
		} catch (Exception e) {
			throw new InvocationTargetException(e);
		}

	}

	protected IClasspathEntry[] getClasspathsFromWebProjectInfo(WebProjectInfo wpInfo) {
		IClasspathEntry[] wasClasspath = wpInfo.getWASClasspathEntries();
		IClasspathEntry[] serverJdkClasspath = wpInfo.getServerJDKClasspathEntries();
		List list = new ArrayList(wasClasspath.length + serverJdkClasspath.length);
		list.addAll(Arrays.asList(wasClasspath));
		list.addAll(Arrays.asList(serverJdkClasspath));

		ClasspathEntry[] ret = new ClasspathEntry[list.size()];
		return (IClasspathEntry[]) list.toArray(ret);
	}


	protected IClasspathEntry[] getOldDefaultClasspath() {
		WebProjectInfo wpInfo = new WebProjectInfo();
		//TODO switch to module level
/*		wpInfo.setProject(nature.getProject());
		wpInfo.setJSPLevel(nature.getJSPLevel());
		wpInfo.setServletLevel(nature.getServletLevel());*/
		return getClasspathsFromWebProjectInfo(wpInfo);
	}

	protected IClasspathEntry[] getDefaultClasspath() {
		WebProjectInfo wpInfo = new WebProjectInfo();
		//TODO switch to Module level
	/*	wpInfo.setProject(nature.getProject());
		wpInfo.setJSPLevel(webProjectInfo.getJSPLevel());
		wpInfo.setServletLevel(webProjectInfo.getServletLevel());*/
		return getClasspathsFromWebProjectInfo(wpInfo);
	}



	protected void updateClassPathEntries(IProgressMonitor monitor) throws InvocationTargetException {
		IProject project = webProjectInfo.getProject();
		try {
			if (project.hasNature(JavaCore.NATURE_ID)) {
				IJavaProject javaProject = JavaCore.create(project);
				IClasspathEntry[] existingClasspath = javaProject.getRawClasspath();
				IClasspathEntry[] oldDefaultClasspath = getOldDefaultClasspath();
				IClasspathEntry[] defaultClasspath = getDefaultClasspath();
				IClasspathEntry[] newClasspath = ClasspathUtilities.removeClasspathEntries(existingClasspath, oldDefaultClasspath);
				newClasspath = ClasspathUtilities.addClasspathEntries(newClasspath, defaultClasspath);
				javaProject.setRawClasspath(newClasspath, monitor);
			}
		} catch (JavaModelException e) {
			throw new InvocationTargetException(e);
		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		}
	}

}