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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;

public class XDocletWebAntProjectBuilder extends XDocletAntProjectBuilder {

	String contextRoot = ""; //$NON-NLS-1$

	public XDocletWebAntProjectBuilder() {
		super();
		templateUrl = Platform.getBundle(XDocletAnnotationPlugin.PLUGINID).getEntry("/templates/builder/xdocletweb.xml"); //$NON-NLS-1$
	}

	protected String getTaskName() {
		return "webdoclet"; //$NON-NLS-1$
	}

	protected HashMap createTemplates(String beanPath) {
		HashMap templates = new HashMap();
		templates.put("@servlets@", beanPath); //$NON-NLS-1$

		templates.put("@webDoclet@", createDocletTasks()); //$NON-NLS-1$
		return templates;
	}

	private String createDocletTasks() {
		IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
				"org.eclipse.jst.j2ee.ejb.annotations.xdoclet.webdocletTaskProvider").getExtensions();
		StringBuffer tasks = new StringBuffer(512);
		for (int i = 0; extensions != null && i < extensions.length; i++) {
			IExtension extension = extensions[i];
			IConfigurationElement[] elements = extension.getConfigurationElements();
			if (elements == null)
				continue;
			try {
				String pluginDescriptor = elements[0].getDeclaringExtension().getNamespace();

				org.osgi.framework.Bundle bundle = Platform.getBundle(pluginDescriptor);
				Class c = bundle.loadClass(elements[0].getAttribute("class"));
				if (c != null) {
					XDocletTaskProvider docletTaskProvider = (XDocletTaskProvider) c.newInstance();

					docletTaskProvider.setClientProject(null);
					docletTaskProvider.setPreferenceStore(this.preferenceStore);
					docletTaskProvider.setExtension(extension);
					docletTaskProvider.setProperties(this.getProperties());
					docletTaskProvider.setProject(this.getProject());
					if (preferenceStore.getBooleanProperty(elements[0].getAttribute("id") + ".defaultSelection")) {
						tasks.append("\n");
						tasks.append(docletTaskProvider.getTask());
						tasks.append("\n");
					}
				}
			} catch (Exception e) {
				Logger.logException(e);
			}
		}
		return tasks.toString();
	}

	protected Properties createAntBuildProperties(IResource resource, IJavaProject javaProject,
			IPackageFragmentRoot packageFragmentRoot, String beanPath) {
		Properties properties = new Properties();
		StructureEdit moduleCore = null;
		WebArtifactEdit webEdit = null;
		try {
			moduleCore = StructureEdit.getStructureEditForRead(javaProject.getProject());
			
			WorkbenchComponent wbModule = moduleCore.getComponent();
			IProject proj = javaProject.getProject();
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(proj);

			if (webEdit != null) {
				if((contextRoot = webEdit.getServerContextRoot()) == null)
					contextRoot = "";//$NON-NLS-1$
			}
			String projectDir = resource.getProject().getLocation().toString();
			IPath webInf =  getWebInfFolder(wbModule);
			properties.put("web.module.webinf", projectDir +"/"+webInf.toString()); //$NON-NLS-1$
			properties.put("web", contextRoot); //$NON-NLS-1$
			properties.put("web.project.dir", projectDir); //$NON-NLS-1$
			properties.put("web.project.classpath", asClassPath(javaProject)); //$NON-NLS-1$
			properties.put("web.module.src", packageFragmentRoot.getResource().getProjectRelativePath().toString()); //$NON-NLS-1$
			properties.put("web.module.gen", packageFragmentRoot.getResource().getProjectRelativePath().toString()); //$NON-NLS-1$
			properties.put("web.bin.dir", this.getJavaProjectOutputContainer(javaProject).toString()); //$NON-NLS-1$
			properties.put("xdoclet.home", getPreferenceStore().getProperty(XDocletPreferenceStore.XDOCLETHOME)); //$NON-NLS-1$
			URL url = Platform.getBundle("org.apache.ant").getEntry("/"); //$NON-NLS-1$ //$NON-NLS-2$
			url = Platform.asLocalURL(url);
			File file = new File(url.getFile());
			properties.put("ant.home", file.getAbsolutePath()); //$NON-NLS-1$

			String servletLevel = J2EEProjectUtilities.getJ2EEProjectVersion(proj);

			properties.put("servlet.spec.version", servletLevel); //$NON-NLS-1$
			properties.put("java.class.path", ""); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
			Logger.logException(e);
		} finally {
			if (moduleCore != null)
				moduleCore.dispose();
			if (webEdit != null)
				webEdit.dispose();
		}
		return properties;
	}

	protected String constructAnnotatedClassList(IPackageFragmentRoot root, IResource changedBean) {

		List webClasses = new ArrayList();
		getAllAnnotatedWebClasses(root, webClasses);
		String beans = ""; //$NON-NLS-1$
		try {
			Iterator iterator = webClasses.iterator();
			while (iterator.hasNext()) {
				ICompilationUnit cu = (ICompilationUnit) iterator.next();
				IResource bean;
				bean = cu.getCorrespondingResource();
				IPath path = bean.getProjectRelativePath();
				path = makeRelativeTo(path, root);
				beans += "\t<include name=\"" + path.toString() + "\" />\n"; //$NON-NLS-1$ //$NON-NLS-2$

			}
		} catch (JavaModelException e) {
			Logger.logException(e);
		}
		return beans;
	}

	/**
	 * @param root
	 * @return
	 */
	private void getAllAnnotatedWebClasses(IParent root, List list) {
		IJavaElement[] elements = null;
		try {
			elements = root.getChildren();
			if (elements == null)
				return;
			for (int i = 0; i < elements.length; i++) {
				IJavaElement element = elements[i];

				if (XDoxletAnnotationUtil.isXDocletAnnotatedWebClass(element)) {
					list.add(element);
				} else if (element.getElementType() == IJavaElement.PACKAGE_FRAGMENT) {
					getAllAnnotatedWebClasses((IPackageFragment) element, list);
				}
			}
		} catch (JavaModelException e) {
			Logger.logException(e);
		}
	}

	protected void refreshProjects(IProject project, IProgressMonitor monitor) throws CoreException {
		if (project != null)
			project.refreshLocal(IResource.DEPTH_INFINITE, monitor);

	}

	protected IPath getWebInfFolder(WorkbenchComponent webModule) {
		ComponentResource[] webXML = webModule.findResourcesByRuntimePath(new Path("/WEB-INF"));
		if (webXML.length > 0)
			return webXML[0].getSourcePath();
		return null;
	}

}
