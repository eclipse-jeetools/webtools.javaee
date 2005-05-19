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
 * Created on Dec 5, 2003
 * 
 * To change the template for this generated file go to Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperationNew;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.web.internal.operation.ILibModule;

public class WebModuleImportOperationNew extends J2EEArtifactImportOperationNew {
	/**
	 * @param model
	 */
	public WebModuleImportOperationNew(IDataModel model) {
		super(model);
	}

	protected void doExecute(IProgressMonitor monitor) throws ExecutionException {
		super.doExecute(monitor);
		IFolder folder = WebPropertiesUtil.getWebLibFolder(virtualComponent.getProject());
		if (!folder.exists()) {
			try {
				folder.create(true, true, new NullProgressMonitor());
			} catch (CoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			addExtraClasspathEntries(monitor);
		} catch (JavaModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addExtraClasspathEntries(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, CoreException, JavaModelException {
		List extraEntries = null;
		IJavaProject javaProject = JavaCore.create(virtualComponent.getProject());
		extraEntries = new ArrayList();
		importWebLibraryProjects(monitor, extraEntries, javaProject);

		IResource[] libs = WebPropertiesUtil.getWebLibFolder(virtualComponent.getProject()).members();
		for (int i = 0; i < libs.length; i++) {
			if (!javaProject.isOnClasspath(libs[i]))
				extraEntries.add(JavaCore.newLibraryEntry(libs[i].getFullPath(), libs[i].getFullPath(), null));
		}
		// addToClasspath(extraEntries);
	}

	private void importWebLibraryProjects(IProgressMonitor monitor, List extraEntries, IJavaProject javaProject) throws InvocationTargetException, InterruptedException {
		// List libProjects = (List)
		// operationDataModel.getProperty(WebModuleImportDataModel.HANDLED_ARCHIVES);
		// J2EEUtilityJarImportDataModel importModel = null;
		// WTPOperation importOperation = null;
		// ArrayList libModules = new ArrayList();
		// for (int i = 0; null != libProjects && i < libProjects.size(); i++) {
		// importModel = (J2EEUtilityJarImportDataModel) libProjects.get(i);
		// libModules.add(new LibModule(importModel.getArchiveFile().getName(),
		// importModel.getProject().getName()));
		// importOperation = importModel.getDefaultOperation();
		// importOperation.run(monitor);
		// if (extraEntries != null) {
		// if (!javaProject.isOnClasspath(importModel.getProject())) {
		// extraEntries.add(JavaCore.newProjectEntry(importModel.getProject().getFullPath()));
		// }
		// }
		// }
		// LibModule[] libModulesArray = new LibModule[libModules.size()];
		// for (int i = 0; i < libModules.size(); i++) {
		// libModulesArray[i] = (LibModule) libModules.get(i);
		// }
		// setLibModules(javaProject.getProject(), libModulesArray);
	}

	protected void setLibModules(IProject project, ILibModule[] modules) {
		// TODO this will throw class cast exception, do we still use ILibModule?
		WebArtifactEdit webArtifactEdit = null;
		try {
			// TODO migrate to flex projects
			// webArtifactEdit =
			// (WebArtifactEdit)StructureEdit.getFirstArtifactEditForRead(project);
			if (webArtifactEdit != null)
				webArtifactEdit.addLibModules((ReferencedComponent[]) modules);
		} finally {
			if (webArtifactEdit != null)
				webArtifactEdit.dispose();
		}
	}

	protected SaveStrategy createSaveStrategy(IVirtualComponent virtualComponent) {
		return new FlexibleJ2EEWebSaveStrategyImpl(virtualComponent);
	}
}