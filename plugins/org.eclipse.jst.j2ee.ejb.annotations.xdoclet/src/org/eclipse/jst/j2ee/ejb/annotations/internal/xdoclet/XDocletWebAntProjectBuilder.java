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
import org.eclipse.emf.common.util.URI;
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
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.wst.common.modulecore.ComponentResource;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;

public class XDocletWebAntProjectBuilder extends XDocletAntProjectBuilder {

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
		return templates;
	}

	protected Properties createAntBuildProperties(IResource resource, IJavaProject javaProject, IPackageFragmentRoot packageFragmentRoot, String beanPath) {
		Properties properties = new Properties();
		ModuleCore moduleCore = null;
		WebArtifactEdit webEdit = null;
		try {
			moduleCore = ModuleCore.getModuleCoreForRead(javaProject.getProject());
			WorkbenchComponent wbModule = null;
			URI sourcePath = URI.createURI(resource.getFullPath().toString());
			ComponentResource[] moduleResources = moduleCore.findWorkbenchModuleResourcesBySourcePath(sourcePath);
			for (int i = 0; i < moduleResources.length; i++) {
				ComponentResource moduleResource = moduleResources[i];
				if (moduleResource != null)
					wbModule = moduleResource.getComponent();
				if (wbModule != null)
					break;
			}
			webEdit = WebArtifactEdit.getWebArtifactEditForRead(wbModule);
			int j2eeVersion = 0;
			String contextRoot = ""; //$NON-NLS-1$
			if (webEdit != null) {
				j2eeVersion = webEdit.getJ2EEVersion();
				contextRoot = webEdit.getServerContextRoot();
			}

			properties.put("web.module.webinf", getWebInfFolder(javaProject).getProjectRelativePath().toString()); //$NON-NLS-1$
			properties.put("web", contextRoot); //$NON-NLS-1$
			properties.put("web.project.dir", resource.getProject().getLocation().toString()); //$NON-NLS-1$
			properties.put("web.project.classpath", asClassPath(javaProject)); //$NON-NLS-1$
			properties.put("web.module.src", packageFragmentRoot.getResource().getProjectRelativePath().toString()); //$NON-NLS-1$
			properties.put("web.module.gen", packageFragmentRoot.getResource().getProjectRelativePath().toString()); //$NON-NLS-1$
			properties.put("web.bin.dir", this.getJavaProjectOutputContainer(javaProject).toString()); //$NON-NLS-1$
			properties.put("xdoclet.home", XDocletPreferenceStore.getProperty(XDocletPreferenceStore.XDOCLETHOME)); //$NON-NLS-1$
			URL url = Platform.getBundle("org.apache.ant").getEntry("/"); //$NON-NLS-1$ //$NON-NLS-2$
			url = Platform.asLocalURL(url);
			File file = new File(url.getFile());
			properties.put("ant.home", file.getAbsolutePath()); //$NON-NLS-1$

			String servletLevel = J2EEVersionConstants.VERSION_2_2_TEXT;
			if (j2eeVersion == J2EEVersionConstants.J2EE_1_3_ID)
				servletLevel = J2EEVersionConstants.VERSION_2_3_TEXT;
			else if (j2eeVersion == J2EEVersionConstants.J2EE_1_4_ID)
				servletLevel = J2EEVersionConstants.VERSION_2_4_TEXT;

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
}
