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
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.strategy.SaveStrategy;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactImportOperationNew;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.web.internal.operation.ILibModule;
import org.eclipse.wst.web.internal.operation.LibModule;

public class WebModuleImportOperationNew extends J2EEArtifactImportOperationNew {
	/**
	 * @param model
	 */
	public WebModuleImportOperationNew(IDataModel model) {
		super(model);
	}

	protected void doExecute(IProgressMonitor monitor) throws ExecutionException {
		super.doExecute(monitor);
		IVirtualFolder libFolder = WebPropertiesUtil.getWebLibFolder(virtualComponent);
		if (!libFolder.exists()) {
			try {
				libFolder.create(IFolder.FORCE, new NullProgressMonitor());
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

	private void addExtraClasspathEntries(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException, CoreException, JavaModelException, ExecutionException {
		List extraEntries = null;
		IJavaProject javaProject = JavaCore.create(virtualComponent.getProject());
		extraEntries = new ArrayList();
		importWebLibraryProjects(monitor, extraEntries, javaProject);
		IVirtualFolder libFolder = WebPropertiesUtil.getWebLibFolder(virtualComponent);
		IVirtualResource[] libs = libFolder.members();
		IResource lib = null;
		for (int i = 0; i < libs.length; i++) {
			lib = libs[i].getUnderlyingResource();
			if (!javaProject.isOnClasspath(lib))
				extraEntries.add(JavaCore.newLibraryEntry(lib.getFullPath(), lib.getFullPath(), null));
		}
		addToClasspath(model, extraEntries);
	}

	private void importWebLibraryProjects(IProgressMonitor monitor, List extraEntries, IJavaProject javaProject) throws InvocationTargetException, InterruptedException, ExecutionException {
		List libProjects = (List) model.getProperty(IWebComponentImportDataModelProperties.WEB_LIB_COMPONENTS);
		IDataModel importModel = null;
		IVirtualComponent nestedComponent = null;
		ArrayList libModules = new ArrayList();
		String jarName = null;
		for (int i = 0; null != libProjects && i < libProjects.size(); i++) {
			importModel = (IDataModel) libProjects.get(i);
			nestedComponent = (IVirtualComponent) importModel.getProperty(IWebComponentImportDataModelProperties.COMPONENT);
			jarName = ((Archive) importModel.getProperty(IWebComponentImportDataModelProperties.FILE)).getName();
			libModules.add(new LibModule(jarName, nestedComponent.getProject().getName()));
			importModel.getDefaultOperation().execute(monitor, info);
			if (extraEntries != null) {
				if (!javaProject.isOnClasspath(nestedComponent.getProject())) {
					extraEntries.add(JavaCore.newProjectEntry(nestedComponent.getProject().getFullPath()));
				}
			}
			ComponentCore.createReference(virtualComponent, nestedComponent, new Path("/WEB-INF/lib/")).create(0, monitor);
		}


		LibModule[] libModulesArray = new LibModule[libModules.size()];
		for (int i = 0; i < libModules.size(); i++) {
			libModulesArray[i] = (LibModule) libModules.get(i);
		}
		setLibModules(javaProject.getProject(), libModulesArray);
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
		return new WebComponentSaveStrategyImpl(virtualComponent);
	}
}