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
 * Created on May 19, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.internal.plugin.LibCopyBuilder;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.web.internal.operation.ILibModule;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class RemoveWebLibraryProjectOperation extends WTPOperation {

	public RemoveWebLibraryProjectOperation(AddWebLibraryProjectDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		AddWebLibraryProjectDataModel model = (AddWebLibraryProjectDataModel) this.operationDataModel;
		ILibModule[] libModules = model.getLibModules();
		if (libModules == null || libModules.length == 0)
			return;
		List selectedModuleList = (List) model.getProperty(AddWebLibraryProjectDataModel.WEB_LIB_MODULE_LIST);
		//		WebLibModuleRepository repo =
		// (WebLibModuleRepository)model.getProperty(AddWebLibraryProjectDataModel.WEB_LIB_MODULE_REPO);


		IProject project = model.getTargetProject();
		J2EEWebNatureRuntime webNature = J2EEWebNatureRuntime.getRuntime(project);
		int size = selectedModuleList.size();
		// set up new lib module list
		ArrayList newLibModuleList = new ArrayList();
		for (int i = 0; i < libModules.length; i++) {
			ILibModule libModule = libModules[i];
			// check if the project of the module exists and open
			IProject libProj = libModule.getProject();
			if (libProj == null || !libProj.exists() || !libProj.isOpen())
				continue;
			if (selectedModuleList.contains(libModule))
				continue;
			newLibModuleList.add(libModule);
		}
		ILibModule[] newLibModules = new ILibModule[newLibModuleList.size()];
		newLibModuleList.toArray(newLibModules);
		webNature.setLibModules(newLibModules);
		// remove the lib modules in the removeLibModuleList
		try {
			// remove libProject from the .project file
			ArrayList foundLibClasspathList = new ArrayList();
			for (int i = 0; i < size; i++) {
				ILibModule libModule = (ILibModule) selectedModuleList.get(i);
				IProject libProject = libModule.getProject();
				if (libProject == null || !libProject.exists() || !libProject.isOpen())
					continue;
				ProjectUtilities.removeFromBuildSpec(LibCopyBuilder.BUILDER_ID, libProject);
				IClasspathEntry libClasspathEntry = JavaCore.newProjectEntry(libProject.getFullPath());
				foundLibClasspathList.add(libClasspathEntry.getPath());
			}
			IJavaProject javaProject = ProjectUtilities.getJavaProject(project);
			IClasspathEntry[] existingClasspath = javaProject.getRawClasspath();
			if (existingClasspath == null || existingClasspath.length == 0)
				return;
			int len = existingClasspath.length;
			List newClasspath = new ArrayList();
			/*
			 * By Default, the newEntry is created as an unexported entry. We need to make sure that
			 * the classpath doesn't contain this entry or else we get a Name Collision from the
			 * JavaModel. (Defect 210687) We cannot use the contains method on <code> newClasspath
			 * </code> because the equals method of the <code> ClasspathEntry </code> looks at the
			 * exported status to determine if they are similar. We are only interested in the PATHs
			 */
			for (int i = 0; i < len; i++) {
				IClasspathEntry classpathEntry = existingClasspath[i];
				IPath path = classpathEntry.getPath();
				int entryKind = classpathEntry.getEntryKind();
				// check project entries only
				if (entryKind == IClasspathEntry.CPE_PROJECT) {
					IProject moduleProj = ProjectUtilities.getProject(path.toString());
					if (moduleProj == null || !moduleProj.exists() || !moduleProj.isOpen())
						continue;
					if (foundLibClasspathList.contains(path))
						continue;
				}
				newClasspath.add(classpathEntry);
			}
			IClasspathEntry[] newEntries = new IClasspathEntry[newClasspath.size()];
			newClasspath.toArray(newEntries);
			javaProject.setRawClasspath(newEntries, monitor);
		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		}
	}
}