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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.common.launcher.ant.AntLauncher;
import org.eclipse.jst.j2ee.ejb.annotations.xdoclet.Logger;
import org.eclipse.jst.j2ee.ejb.annotations.xdoclet.XDocletAnnotationPlugin;
import org.eclipse.jst.j2ee.ejb.annotations.xdoclet.XDocletPreferenceStore;


public class XDocletAntProjectBuilder {
	private static final String XDOCLET_EJB_BEAN_TAG = "@ejb.bean";
	URL templateUrl;
	
	public XDocletAntProjectBuilder() {
		super();
		templateUrl= Platform.getBundle(XDocletAnnotationPlugin.PLUGINID).getEntry(
		"/templates/builder/xdoclet.xml");
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
			String beanPath = constructBeanPath(packageFragmentRoot, beanClass);

			Properties properties = createAntBuildProperties(beanClass,
					javaProject, packageFragmentRoot, beanPath);

			 HashMap templates = new HashMap();
			 templates.put("@beans@", beanPath);
			 templates.put("@jboss@", addJbossTask());
			 templates.put("@jonas@", addJonasTask());
			 templates.put("@weblogic@", addWeblogicTask());
			 templates.put("@websphere@", addWebSphereTask());

			AntLauncher antLauncher = new AntLauncher(templateUrl, beanClass.getParent()
					.getLocation(), properties, templates);
			antLauncher.launch("ejbdoclet", monitor);
		} catch (Exception e) {
			Logger.logException(e);
		}
	}

	/**
	 * @param resource
	 * @param javaProject
	 * @param packageFragmentRoot
	 * @param beanPath
	 * @return
	 * @throws JavaModelException
	 */
	private Properties createAntBuildProperties(IResource resource,
			IJavaProject javaProject, IPackageFragmentRoot packageFragmentRoot,
			String beanPath) throws JavaModelException {
		Properties properties = new Properties();
		properties.put("ejb", resource.getProject().getName());
		properties.put("ejb.project.dir", resource.getProject().getLocation()
				.toString());
		properties.put("ejb.module.src", packageFragmentRoot.getResource()
				.getProjectRelativePath().toString());
		properties.put("ejb.module.gen", packageFragmentRoot.getResource()
				.getProjectRelativePath().toString());
		properties.put("ejb.bin.dir", this.getJavaProjectOutputContainer(
				javaProject).toString());
		properties.put("xdoclet.home", XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETHOME));
		URL url = Platform.getBundle("org.apache.ant").getEntry("/");
		try {
			url = Platform.asLocalURL(url);
			
		} catch (IOException e) {
		}
		File file = new File(url.getFile());
		properties.put("ant.home",file.getAbsolutePath());
		properties.put("ejb.spec.version", "2.0");
		properties.put("java.class.path", "");
		properties.put("project.class.path", "");
		properties.put("project.path", "");
		return properties;
	}

	/**
	 * 
	 * XDoclet ejb task requires the names of the files in the fileset to be
	 * relative to the source folder (i.e. root of the classpath) Se wee need to
	 * find porvide them relative to the package fragment root
	 * 
	 * @param changedBean
	 * @return
	 */
	private String constructBeanPath(IPackageFragmentRoot root, IResource changedBean) {

		List ejbs = new ArrayList();
		getAllAnnotatedEjbs(root, ejbs);
		String beans = "";
		try {
			Iterator iterator = ejbs.iterator();
			while (iterator.hasNext()) {
				ICompilationUnit cu = (ICompilationUnit) iterator.next();
				IResource bean;
				bean = cu.getCorrespondingResource();
				IPath path = bean.getProjectRelativePath();
				path = makeRelativeTo(path, root);
				beans +="\t<include name=\""+ path.toString()+"\" />\n";

			}
		} catch (JavaModelException e) {
			Logger.logException(e);
		}
		return beans;
	}

	/**
	 * @param path2
	 * @param root
	 * @return
	 */
	private IPath makeRelativeTo(IPath path, IPackageFragmentRoot root) {
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

	/**
	 * @param root
	 * @return
	 */
	private void getAllAnnotatedEjbs(IParent root, List list) {
		IJavaElement[] elements = null;
		try {
			elements = root.getChildren();
			if (elements == null)
				return;
			for (int i = 0; i < elements.length; i++) {
				IJavaElement element = elements[i];

				if (XDoxletAnnotationUtil.isXDocletAnnotatedResource(element)){
					list.add(element);
				} else if (element.getElementType() == IJavaElement.PACKAGE_FRAGMENT) {
					getAllAnnotatedEjbs((IPackageFragment) element, list);
				}
			}
		} catch (JavaModelException e) {
			Logger.logException(e);
		}
	}

	private IPackageFragmentRoot getPackageFragmentRoot(ICompilationUnit res) {
		IJavaElement current = res;
		do {
			if (current instanceof IPackageFragmentRoot)
				return (IPackageFragmentRoot) current;
			current = current.getParent();
		} while (current != null);
		return null;
	}

	private IPath getJavaProjectOutputContainer(IJavaProject proj)
			throws JavaModelException {
		IPath path = proj.getOutputLocation();
		if (path == null)
			return null;
		if (path.segmentCount() == 1)
			return path;
		return ((IContainer) proj.getProject()).getFolder(
				path.removeFirstSegments(1)).getProjectRelativePath();
	}
	private String addWeblogicTask()
	{
	    if(!XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.EJB_WEBLOGIC))
	    	return "";
	    else
	    	return  "<weblogic version=\""+XDocletPreferenceStore.getProperty(XDocletPreferenceStore.EJB_WEBLOGIC+"_VERSION")+"\""+
	      "   xmlencoding=\"UTF-8\""+
	      "   destdir=\"\\${ejb.dd.dir}\""+
	      "   validatexml=\"false\""+
	      "   datasource=\"\\${data.source.name}\""+
	      "   mergedir=\"\\${ejb.dd.dir}\""+
	      "   persistence=\"weblogic\" />";
	      

	}
	
	private String addJbossTask()
	{
	    if(!XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.EJB_JBOSS))
	    	return "";
	    else
	    	return  
	    	"<jboss version=\""+XDocletPreferenceStore.getProperty(XDocletPreferenceStore.EJB_JBOSS+"_VERSION")+"\""+
	        "    unauthenticatedPrincipal=\"nobody\""+
	        "    xmlencoding=\"UTF-8\""+
	        "    destdir=\"\\${ejb.dd.dir}\""+
	        "    validatexml=\"false\""+
		    "    datasource=\"\\${data.source.name}\""+
	        "    datasourcemapping=\"PLEASE_MODIFY_THIS\""+
	        "    preferredrelationmapping=\"PLEASE_MODIFY_THIS\" />";
	      

	}
	
	private String addJonasTask()
	{
	    if(!XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.EJB_JONAS))
	    	return "";
	    else
	    	return  
	    	"<jonas version=\""+XDocletPreferenceStore.getProperty(XDocletPreferenceStore.EJB_JONAS+"_VERSION")+"\""+
	        "    xmlencoding=\"UTF-8\""+
	        "    destdir=\"\\${ejb.dd.dir}\""+
	        "    validatexml=\"false\""+
		    "    mergedir=\"\\${ejb.dd.dir}\" />";
	      

	}

	private String addWebSphereTask()
	{
	    if(!XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.EJB_WEBSPHERE))
	    	return "";
	    else
	    	return  "<webSphere destdir=\"\\${ejb.dd.dir}\"/>";
	}
		
}
