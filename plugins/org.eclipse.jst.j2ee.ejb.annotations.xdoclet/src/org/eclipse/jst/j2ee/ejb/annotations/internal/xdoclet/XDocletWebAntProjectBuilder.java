package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.IParent;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jst.j2ee.ejb.annotations.xdoclet.Logger;
import org.eclipse.jst.j2ee.ejb.annotations.xdoclet.XDocletAnnotationPlugin;
import org.eclipse.jst.j2ee.ejb.annotations.xdoclet.XDocletPreferenceStore;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.internal.web.operations.WebNatureRuntimeUtilities;

public class XDocletWebAntProjectBuilder extends XDocletAntProjectBuilder {

	public XDocletWebAntProjectBuilder() {
		super();
		templateUrl = Platform.getBundle(XDocletAnnotationPlugin.PLUGINID)
				.getEntry("/templates/builder/xdocletweb.xml");
	}

	protected String getTaskName() {
		return "webdoclet";
	}

	protected HashMap createTemplates(String beanPath) {
		HashMap templates = new HashMap();
		templates.put("@servlets@", beanPath);
		return templates;
	}

	protected Properties createAntBuildProperties(IResource resource,
			IJavaProject javaProject, IPackageFragmentRoot packageFragmentRoot,
			String beanPath) {
		Properties properties = new Properties();
		try {
			J2EEWebNatureRuntime runtime = WebNatureRuntimeUtilities
					.getJ2EERuntime(javaProject.getProject());

			properties.put("web.module.webinf", runtime.getWEBINFPath().toString());
			properties.put("web", runtime.getContextRoot());
			properties.put("web.project.dir", resource.getProject()
					.getLocation().toString());
			properties.put("web.project.classpath", asClassPath(javaProject));
			properties.put("web.module.src", packageFragmentRoot.getResource()
					.getProjectRelativePath().toString());
			properties.put("web.module.gen", packageFragmentRoot.getResource()
					.getProjectRelativePath().toString());
			properties.put("web.bin.dir", this.getJavaProjectOutputContainer(
					javaProject).toString());
			properties.put("xdoclet.home", XDocletPreferenceStore
					.getProperty(XDocletPreferenceStore.XDOCLETHOME));
			URL url = Platform.getBundle("org.apache.ant").getEntry("/");
			url = Platform.asLocalURL(url);
			File file = new File(url.getFile());
			properties.put("ant.home", file.getAbsolutePath());
			String servletLevel = J2EEConstants.VERSION_2_2_TEXT;
			if (J2EEWebNatureRuntime.SERVLETLEVEL_2_3.equals(runtime
					.getServletLevel()))
				servletLevel = J2EEConstants.VERSION_2_3_TEXT;
			else if (J2EEWebNatureRuntime.SERVLETLEVEL_2_4.equals(runtime
					.getServletLevel()))
				servletLevel = J2EEConstants.VERSION_2_4_TEXT;

			properties.put("servlet.spec.version", servletLevel);
			properties.put("java.class.path", "");
		} catch (Exception e) {
			Logger.logException(e);
		}
		return properties;
	}

	protected String constructAnnotatedClassList(IPackageFragmentRoot root,
			IResource changedBean) {

		List webClasses = new ArrayList();
		getAllAnnotatedWebClasses(root, webClasses);
		String beans = "";
		try {
			Iterator iterator = webClasses.iterator();
			while (iterator.hasNext()) {
				ICompilationUnit cu = (ICompilationUnit) iterator.next();
				IResource bean;
				bean = cu.getCorrespondingResource();
				IPath path = bean.getProjectRelativePath();
				path = makeRelativeTo(path, root);
				beans += "\t<include name=\"" + path.toString() + "\" />\n";

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

}
