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
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;

public class XDocletEjbAntProjectBuilder extends XDocletAntProjectBuilder {

	public XDocletEjbAntProjectBuilder() {
		super();
		templateUrl = Platform.getBundle(XDocletAnnotationPlugin.PLUGINID).getEntry("/templates/builder/xdoclet.xml"); //$NON-NLS-1$
	}

	/**
	 * @return
	 */
	protected String getTaskName() {
		return "ejbdoclet"; //$NON-NLS-1$
	}

	/**
	 * @param beanPath
	 * @return
	 */
	protected HashMap createTemplates(String beanPath) {
		HashMap templates = new HashMap();
		templates.put("@beans@", beanPath); //$NON-NLS-1$
		templates.put("@jboss@", addJbossTask()); //$NON-NLS-1$
		templates.put("@jonas@", addJonasTask()); //$NON-NLS-1$
		templates.put("@weblogic@", addWeblogicTask()); //$NON-NLS-1$
		templates.put("@websphere@", addWebSphereTask()); //$NON-NLS-1$
		return templates;
	}

	/**
	 * @param resource
	 * @param javaProject
	 * @param packageFragmentRoot
	 * @param beanPath
	 * @return
	 * @throws JavaModelException
	 */
	public Properties createAntBuildProperties(IResource resource, IJavaProject javaProject, IPackageFragmentRoot packageFragmentRoot, String beanPath) {
		Properties properties = new Properties();
		try {
			properties.put("ejb", resource.getProject().getName()); //$NON-NLS-1$
			properties.put("ejb.project.dir", resource.getProject().getLocation().toString()); //$NON-NLS-1$
			properties.put("ejb.project.classpath", asClassPath(javaProject)); //$NON-NLS-1$
			properties.put("ejb.module.src", packageFragmentRoot.getResource().getProjectRelativePath().toString()); //$NON-NLS-1$
			properties.put("ejb.module.gen", packageFragmentRoot.getResource().getProjectRelativePath().toString()); //$NON-NLS-1$
			properties.put("ejb.bin.dir", this.getJavaProjectOutputContainer(javaProject).toString()); //$NON-NLS-1$
			properties.put("xdoclet.home", XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETHOME)); //$NON-NLS-1$
			URL url = Platform.getBundle("org.apache.ant").getEntry("/"); //$NON-NLS-1$ //$NON-NLS-2$
			url = Platform.asLocalURL(url);
			File file = new File(url.getFile());
			properties.put("ant.home", file.getAbsolutePath()); //$NON-NLS-1$
			EJBNatureRuntime runtime = EJBNatureRuntime.getRuntime(javaProject.getProject());
			properties.put("ejb.spec.version", runtime.getModuleVersionText()); //$NON-NLS-1$
			properties.put("java.class.path", ""); //$NON-NLS-1$ //$NON-NLS-2$
			properties.put("project.class.path", ""); //$NON-NLS-1$ //$NON-NLS-2$
			properties.put("project.path", ""); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (Exception e) {
			Logger.logException(e);
		}
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
	protected String constructAnnotatedClassList(IPackageFragmentRoot root, IResource changedBean) {

		List ejbs = new ArrayList();
		getAllAnnotatedEjbs(root, ejbs);
		String beans = ""; //$NON-NLS-1$
		try {
			Iterator iterator = ejbs.iterator();
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
	private void getAllAnnotatedEjbs(IParent root, List list) {
		IJavaElement[] elements = null;
		try {
			elements = root.getChildren();
			if (elements == null)
				return;
			for (int i = 0; i < elements.length; i++) {
				IJavaElement element = elements[i];

				if (XDoxletAnnotationUtil.isXDocletAnnotatedEjbClass(element)) {
					list.add(element);
				} else if (element.getElementType() == IJavaElement.PACKAGE_FRAGMENT) {
					getAllAnnotatedEjbs((IPackageFragment) element, list);
				}
			}
		} catch (JavaModelException e) {
			Logger.logException(e);
		}
	}

	private String addWeblogicTask() {
		if (!XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.EJB_WEBLOGIC))
			return ""; //$NON-NLS-1$
		return "<weblogic version=\"" + XDocletPreferenceStore.getProperty(XDocletPreferenceStore.EJB_WEBLOGIC + "_VERSION") + "\"" + "   xmlencoding=\"UTF-8\"" + "   destdir=\"\\${ejb.dd.dir}\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
					+ "   validatexml=\"false\"" + "   datasource=\"\\${data.source.name}\"" + "   mergedir=\"\\${ejb.dd.dir}\"" + "   persistence=\"weblogic\" />"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

	}

	private String addJbossTask() {
		if (!XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.EJB_JBOSS))
			return ""; //$NON-NLS-1$
		return "<jboss version=\"" + XDocletPreferenceStore.getProperty(XDocletPreferenceStore.EJB_JBOSS + "_VERSION") + "\"" + "    unauthenticatedPrincipal=\"nobody\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					+ "    xmlencoding=\"UTF-8\"" + "    destdir=\"\\${ejb.dd.dir}\"" + "    validatexml=\"false\"" + "    datasource=\"\\${data.source.name}\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
					+ "    datasourcemapping=\"PLEASE_MODIFY_THIS\"" + "    preferredrelationmapping=\"PLEASE_MODIFY_THIS\" />"; //$NON-NLS-1$ //$NON-NLS-2$

	}

	private String addJonasTask() {
		if (!XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.EJB_JONAS))
			return ""; //$NON-NLS-1$
		return "<jonas version=\"" + XDocletPreferenceStore.getProperty(XDocletPreferenceStore.EJB_JONAS + "_VERSION") + "\"" + "    xmlencoding=\"UTF-8\"" + "    destdir=\"\\${ejb.dd.dir}\"" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
					+ "    validatexml=\"false\"" + "    mergedir=\"\\${ejb.dd.dir}\" />"; //$NON-NLS-1$ //$NON-NLS-2$

	}

	private String addWebSphereTask() {
		if (!XDocletPreferenceStore.isPropertyActive(XDocletPreferenceStore.EJB_WEBSPHERE))
			return ""; //$NON-NLS-1$
		return "<webSphere destdir=\"\\${ejb.dd.dir}\"/>"; //$NON-NLS-1$
	}

}
