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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jst.common.launcher.ant.AntLauncher;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.UnresolveableURIException;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;


public abstract class XDocletAntProjectBuilder {

	protected URL templateUrl;

	public static class Factory {

		public static XDocletAntProjectBuilder newInstance(IResource resource) {
			WebArtifactEdit webEdit = null;
			ModuleCore moduleCore = null;
			WorkbenchComponent wbModule = null;
			try {
				moduleCore = ModuleCore.getModuleCoreForRead(resource.getProject());
				URI sourcePath = URI.createPlatformResourceURI(resource.getFullPath().toString());
				ComponentResource[] moduleResources = moduleCore.findWorkbenchModuleResourcesBySourcePath(sourcePath);
				for (int i=0; i<moduleResources.length; i++) {
					ComponentResource moduleResource = moduleResources[i];
					if (moduleResource!=null)
						wbModule = moduleResource.getComponent();
					if (wbModule!=null)
						break;
				}
				String moduleType = wbModule.getComponentType().getModuleTypeId();
				if(moduleType.equals(IModuleConstants.JST_EJB_MODULE))
					return new XDocletEjbAntProjectBuilder();
				else if (moduleType.equals(IModuleConstants.JST_WEB_MODULE))
					return new XDocletWebAntProjectBuilder();
			} catch (UnresolveableURIException e) {
				e.printStackTrace();
			} finally {
				if (moduleCore!=null)
					moduleCore.dispose();
				if (webEdit != null)
					webEdit.dispose();
			}
			return null;
		}
		
	}
	public XDocletAntProjectBuilder() {
		super();
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

	protected String[] createClassPath(IJavaProject project) throws CoreException {
		String[] cp = JavaRuntime.computeDefaultRuntimeClassPath(project);
		return cp;
	}

	/**
	 * @param resource
	 * @param monitor
	 */
	public void buildUsingAnt(IResource beanClass, IProgressMonitor monitor) {
		IJavaProject javaProject = JavaCore.create(beanClass.getProject());
		ICompilationUnit compilationUnit = JavaCore
				.createCompilationUnitFrom((IFile) beanClass);
		try {
			IPackageFragmentRoot packageFragmentRoot = this
					.getPackageFragmentRoot(compilationUnit);
			String beanPath = constructAnnotatedClassList(packageFragmentRoot, beanClass);

			Properties properties = createAntBuildProperties(beanClass,
					javaProject, packageFragmentRoot, beanPath);

			HashMap templates = createTemplates(beanPath);

			AntLauncher antLauncher = new AntLauncher(templateUrl, beanClass
					.getParent().getLocation(), properties, templates);
			antLauncher.launch(getTaskName(), monitor);
		} catch (Exception e) {
			Logger.logException(e);
		}
	}



	protected abstract String getTaskName();
	protected abstract HashMap createTemplates(String beanPath); 
	protected abstract Properties createAntBuildProperties(IResource resource,
			IJavaProject javaProject, IPackageFragmentRoot packageFragmentRoot,
			String beanPath) ;

	protected abstract String constructAnnotatedClassList(IPackageFragmentRoot root,
			IResource changedBean); 


	/**
	 * @param path2
	 * @param root
	 * @return
	 */
	protected IPath makeRelativeTo(IPath path, IPackageFragmentRoot root) {
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

	protected IContainer getWebInfFolder(IJavaProject proj) {
		return WebPropertiesUtil.getJavaSourceFolder(proj.getProject());
	}
}
