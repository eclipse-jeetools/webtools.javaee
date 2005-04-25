/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Dec 16, 2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEArtifactExportDataModel;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.SaveFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEArtifactExportOperation;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.web.internal.operation.ILibModule;


public final class WebModuleExportOperation extends J2EEArtifactExportOperation {
	/**
	 * flag which indicates whether nested projects should be incrementally built as part of export
	 */
	protected boolean buildIncremental = true;

	/**
	 * @param model
	 */
	public WebModuleExportOperation(J2EEArtifactExportDataModel model) {
		super(model);
		// TODO Auto-generated constructor stub
	}

	protected String archiveString() {
		return "War File";//$NON-NLS-1$
	}

	/**
	 * Insert the method's description here. Creation date: (1/9/2001 11:47:44 AM)
	 * 
	 * @exception java.lang.Exception
	 *                The exception description.
	 */
	public void createModuleFile() throws SaveFailureException {

		try {
			CommonarchiveFactory caf = ((CommonarchivePackage) EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI)).getCommonarchiveFactory();
			WTProjectLoadStrategyImpl ls = new WTProjectLoadStrategyImpl(project);
			moduleFile = caf.openWARFile(ls, getDestinationPath().toOSString());
			//    moduleFile.setSaveFilter(getFilter());
			if (isExportSource())
				ls.setExportSource(true);
			if (((WebModuleExportDataModel) operationDataModel).getBooleanProperty(WebModuleExportDataModel.EXCLUDE_COMPILE_JSP))
				ls.setExcludeCompiledJspFiles(true);
		} catch (Exception e) {
			throw new SaveFailureException(e);
		}
	}

	public void export() throws SaveFailureException {

		try {
			// Defect 227051
			buildProjectsIfNecessary(getProgressMonitor());

			exportWarProject();
		} catch (SaveFailureException ex) {
			throw ex;
		} catch (Exception e) {
			throw new SaveFailureException(e.getMessage(), e);//$NON-NLS-1$
		}

	}

	protected void exportWarProject() throws SaveFailureException {
		try {
			createModuleFile();
			getWarFile().saveAsNoReopen(getDestinationPath().toOSString());
		} catch (SaveFailureException ex) {
			throw ex;
		} catch (Exception e) {
			throw new SaveFailureException(e.getMessage(), e);//$NON-NLS-1$
		}

	}

	/**
	 * Creates a class loader to be used by the 1.0 dd generator for reflecting java fields;
	 * 
	 * @return an instance of URLClassLoader with the class path of the current project
	 */
	public ClassLoader getAlternateClassLoader() {
		IPath wbPath = Platform.getLocation();
		IJavaProject p = getJavaProject();
		IClasspathEntry[] cp = null;
		try {
			cp = p.getRawClasspath();
		} catch (Exception ex) {
			//Real hack for now; On the very first try, it bombs
			//On subsequent tries, it works
			try {
				cp = p.getRawClasspath();
			} catch (JavaModelException ex2) {
				//Okay; something is really wrong
				//TODO
				org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ex);
			}
		}
		List paths = new ArrayList();
		try {
			paths.add(getJavaProject().getOutputLocation().addTrailingSeparator());
		} catch (JavaModelException ex) {
			//TODO
			org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ex);
		}
		for (int i = 0; i < cp.length; i++) {
			if (cp[i].getEntryKind() == IClasspathEntry.CPE_SOURCE) {
				continue;
			}
			paths.add(cp[i].getPath());
		}
		java.net.URL[] pathUrls = new java.net.URL[paths.size()];
		for (int i = 0; i < paths.size(); i++) {
			IPath path = (IPath) paths.get(i);
			if (path.getDevice() == null) {
				path = wbPath.append(path);
			}
			try {
				pathUrls[i] = new java.net.URL("file:///" + path.toString());//$NON-NLS-1$
			} catch (java.net.MalformedURLException ex) {
				//TODO
				org.eclipse.jem.util.logger.proxy.Logger.getLogger().logError(ex);
			}
		}
		java.net.URLClassLoader parent = null; //
		// URL entries can be jar or directory.
		// this will use the system class loader as the parent
		// parent - null = bootstrap class loader.
		java.net.URLClassLoader cl = new java.net.URLClassLoader(pathUrls, parent);
		return cl;
	}

	public WARFile getWarFile() {
		return (WARFile) moduleFile;

	}

	protected void buildProjectsIfNecessary(IProgressMonitor monitor) throws CoreException {
		if (!buildIncremental || !operationDataModel.getBooleanProperty(J2EEArtifactExportDataModel.RUN_BUILD)) {
			return;
		}
		SubProgressMonitor subMonitor = new SubProgressMonitor(monitor, 1);
		try {
			Set projectsToBuild = new HashSet();
			ILibModule[] libModules = getLibModules();
			for (int i = 0; i < libModules.length; i++) {
				projectsToBuild.add(libModules[i].getProject());
			}

			List inOrderProjects = getProjectsInOrder(projectsToBuild);
			subMonitor.beginTask("", inOrderProjects.size());//$NON-NLS-1$
			//defect 240999
			project.refreshLocal(IResource.DEPTH_INFINITE, null);
			javac(project, monitor);
			for (int i = 0; i < inOrderProjects.size(); i++) {
				IProject moduleProject = (IProject) inOrderProjects.get(i);
				if (moduleProject.isAccessible()) {
					//defect 240999
					project.refreshLocal(IResource.DEPTH_INFINITE, null);
					javac(project, monitor);
				}
			}
		} finally {
			subMonitor.done();
		}
	}
	
	protected ILibModule[] getLibModules() {
		//TODO this will throw class cast exception, do we still use ILibModule?
		WebArtifactEdit webArtifactEdit = null;
		try {
			webArtifactEdit = (WebArtifactEdit)StructureEdit.getFirstArtifactEditForRead(project);
			if (webArtifactEdit!=null)
				return (ILibModule[]) webArtifactEdit.getLibModules();
		} finally {
			if (webArtifactEdit!=null)
				webArtifactEdit.dispose();
		}
		return new ILibModule[] {};
	}

	private List getProjectsInOrder(Set projectsToBuild) {
		List result = new ArrayList();
		IProject[] projects = (IProject[]) projectsToBuild.toArray(new IProject[projectsToBuild.size()]);

		IWorkspace.ProjectOrder projectOrder = ResourcesPlugin.getWorkspace().computeProjectOrder(projects);
		result.addAll(Arrays.asList(projectOrder.projects));
		if (projectOrder.hasCycles) {
			for (int i = 0; i < projectOrder.knots.length; i++) {
				result.addAll(Arrays.asList(projectOrder.knots[i]));
			}
		}
		return result;
	}

}