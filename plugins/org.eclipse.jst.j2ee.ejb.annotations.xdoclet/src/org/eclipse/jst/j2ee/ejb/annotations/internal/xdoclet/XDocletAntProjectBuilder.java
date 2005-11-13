/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.util.AntLauncherUtility;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;

public abstract class XDocletAntProjectBuilder {

	protected URL templateUrl;
	XDocletPreferenceStore preferenceStore = null;
	IProject project;
	IResource resource;
	Properties properties;

	public static class Factory {

		public static XDocletAntProjectBuilder newInstance(IResource resource) {
			IVirtualResource[] vResources = ComponentCore.createResources(resource);
			XDocletAntProjectBuilder antProjectBuilder = null;
			if( vResources.length == 0)
				return null;
			if (J2EEProjectUtilities.isEJBProject(vResources[0].getComponent().getProject()))
				antProjectBuilder =  new XDocletEjbAntProjectBuilder();
			
			else if (J2EEProjectUtilities.isDynamicWebProject(vResources[0].getComponent().getProject()))
				antProjectBuilder =  new XDocletWebAntProjectBuilder();
			antProjectBuilder.setProject(vResources[0].getComponent().getProject());
			antProjectBuilder.setResource(resource);
			return antProjectBuilder;
		}

	}

	public XDocletAntProjectBuilder() {
		super();
	}
	
	private XDocletPreferenceStore initPreferenceStore(IProject project)
	{
		if(preferenceStore == null)
		{
			preferenceStore = XDocletPreferenceStore.forProject(project);

		}
		return preferenceStore;
	}


	protected String asClassPath(IJavaProject project) throws CoreException {
		String[] cp = createClassPath(project);
		StringBuffer buf = new StringBuffer(256);
		for (int i = 0; i < cp.length; i++) {
			String string = cp[i];
			buf.append(string);
			if (i != cp.length - 1)
				buf.append(File.pathSeparatorChar);
		}
		return buf.toString();
	}

	protected String[] createClassPath(IJavaProject project)
			throws CoreException {
		String[] cp = JavaRuntime.computeDefaultRuntimeClassPath(project);
		return cp;
	}

	/**
	 * @param resource
	 * @param monitor
	 */
	public void buildUsingAnt(IResource beanClass, IProgressMonitor monitor) {
		initPreferenceStore(beanClass.getProject());
		IJavaProject javaProject = JavaCore.create(beanClass.getProject());
		try {
			IPackageFragmentRoot packageFragmentRoot = getPackageFragmentRootOrFirst(beanClass);
			String beanPath = constructAnnotatedClassList(packageFragmentRoot,beanClass);

			Properties properties = createAntBuildProperties(beanClass,
					javaProject, packageFragmentRoot, beanPath);
			this.setProperties(properties);
			HashMap templates = createTemplates(beanPath);

			AntLauncherUtility antLauncher = new AntLauncherUtility(templateUrl, beanClass
					.getParent().getLocation(), properties, templates);
			antLauncher.setUseLauncher(true);
			antLauncher.launch(getTaskName(), monitor);
			this.refreshProjects(beanClass.getProject(), monitor);
		} catch (Exception e) {
			Logger.logException(e);
		}
	}

	private IPackageFragmentRoot getPackageFragmentRootOrFirst(IResource beanClass) throws JavaModelException
	{
		try {
			ICompilationUnit compilationUnit = JavaCore.createCompilationUnitFrom((IFile) beanClass);
			return this.getPackageFragmentRoot(compilationUnit);
		} catch (RuntimeException e) {
		}
		
		IContainer container = J2EEProjectUtilities.getSourceFolderOrFirst(beanClass.getProject(),"ejbModule");
		return JavaCore.create(beanClass.getProject()).findPackageFragmentRoot(container.getFullPath());
	}
	protected abstract String getTaskName();

	protected abstract void refreshProjects(IProject project,
			IProgressMonitor monitor) throws CoreException;

	protected abstract HashMap createTemplates(String beanPath);

	protected abstract Properties createAntBuildProperties(IResource resource,
			IJavaProject javaProject, IPackageFragmentRoot packageFragmentRoot,
			String beanPath);

	protected abstract String constructAnnotatedClassList(
			IPackageFragmentRoot root, IResource changedBean);

	/**
	 * @param path2
	 * @param root
	 * @return
	 */
	protected IPath makeRelativeTo(IPath path, IPackageFragmentRoot root) {
		if(root == null)
		 return path;	
		try {
			IPath rpath = root.getCorrespondingResource()
					.getProjectRelativePath();
			if (rpath.isPrefixOf(path))
				return path.removeFirstSegments(rpath
						.matchingFirstSegments(path));
		} catch (JavaModelException e) {
			Logger.logException(e);
		}

		return path;
	}

	protected IPackageFragmentRoot getPackageFragmentRoot(ICompilationUnit res) {
		IJavaElement current = res;
		do {
			if (current instanceof IPackageFragmentRoot)
				return (IPackageFragmentRoot) current;
			current = current.getParent();
		} while (current != null);
		return null;
	}

	protected IPath getJavaProjectOutputContainer(IJavaProject proj)
			throws JavaModelException {
		IPath path = proj.getOutputLocation();
		if (path == null)
			return null;
		if (path.segmentCount() == 1)
			return path;
		return ((IContainer) proj.getProject()).getFolder(
				path.removeFirstSegments(1)).getProjectRelativePath();
	}

	public XDocletPreferenceStore getPreferenceStore() {
		return preferenceStore;
	}

	public IProject getProject() {
		return project;
	}

	public void setProject(IProject project) {
		this.project = project;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public IResource getResource() {
		return resource;
	}

	public void setResource(IResource resource) {
		this.resource = resource;
	}

}
