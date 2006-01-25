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

import org.eclipse.core.resources.IFolder;
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
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.ComponentResource;
import org.eclipse.wst.common.componentcore.internal.ReferencedComponent;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;

public class XDocletEjbAntProjectBuilder extends XDocletAntProjectBuilder {
	IProject clientProject;

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

		templates.put("@docletTasks@", createDocletTasks()); //$NON-NLS-1$

		return templates;
	}

	private String createDocletTasks() {
		IExtension[] extensions = Platform.getExtensionRegistry().getExtensionPoint(
				"org.eclipse.jst.j2ee.ejb.annotations.xdoclet.ejbDocletTaskProvider").getExtensions();
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

					docletTaskProvider.setClientProject(clientProject);
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

	/**
	 * @param resource
	 * @param javaProject
	 * @param packageFragmentRoot
	 * @param beanPath
	 * @return
	 * @throws JavaModelException
	 */
	public Properties createAntBuildProperties(IResource resource, IJavaProject javaProject, IPackageFragmentRoot packageFragmentRoot,
			String beanPath) {
		Properties properties = new Properties();
		StructureEdit core = null;
		EJBArtifactEdit ejbEdit = null;
		try {
			core = StructureEdit.getStructureEditForRead(javaProject.getProject());
			List ejbs = new ArrayList();
			getAllAnnotatedEjbs(packageFragmentRoot, ejbs);
			
			String projectDir = resource.getProject().getLocation().toString() ;
			String moduleDir = packageFragmentRoot.getResource().getProjectRelativePath().toString();

			properties.put("ejb", resource.getProject().getName()); //$NON-NLS-1$
			properties.put("ejb.project.dir", projectDir); //$NON-NLS-1$
			properties.put("ejb.project.classpath", asClassPath(javaProject)); //$NON-NLS-1$
			properties.put("ejb.module.src", moduleDir); //$NON-NLS-1$
			properties.put("ejb.module.gen", packageFragmentRoot.getResource().getProjectRelativePath().toString()); //$NON-NLS-1$
			properties.put("ejb.bin.dir", this.getJavaProjectOutputContainer(javaProject).toString()); //$NON-NLS-1$
			properties.put("ejb.bin.dir", this.getJavaProjectOutputContainer(javaProject).toString()); //$NON-NLS-1$
			properties.put("xdoclet.home", getPreferenceStore().getProperty(XDocletPreferenceStore.XDOCLETHOME)); //$NON-NLS-1$
			URL url = Platform.getBundle("org.apache.ant").getEntry("/"); //$NON-NLS-1$ //$NON-NLS-2$
			url = Platform.asLocalURL(url);
			File file = new File(url.getFile());
			properties.put("ant.home", file.getAbsolutePath()); //$NON-NLS-1$
			WorkbenchComponent ejbModule = core.getComponent();
			// For deleted resources we cannot find the module
			// So assume the first module in the project (currently)
			// there is only one anyway...
			IProject proj = javaProject.getProject();
		
			ejbEdit = EJBArtifactEdit.getEJBArtifactEditForRead(proj);

			int ejbLevelI = ejbEdit.getEJBJarXmiResource().getModuleVersionID();
			String ejbLevel = J2EEProjectUtilities.getJ2EEProjectVersion(proj);
			if ((ejbLevel == null || ejbLevel.length()==0) && ejbLevelI == J2EEVersionConstants.EJB_2_0_ID)
				ejbLevel = J2EEVersionConstants.VERSION_2_0_TEXT;
			else if ((ejbLevel == null || ejbLevel.length()==0) && ejbLevelI == J2EEVersionConstants.EJB_2_1_ID)
				ejbLevel = J2EEVersionConstants.VERSION_2_1_TEXT;

			setEjbClientJarProperties(properties, core, ejbModule);
			IPath metaInfPath = getMetaInfFolder(ejbModule);
			String metaInf = projectDir+"/"+ moduleDir+"/META-INF";
			if(metaInfPath != null)
				metaInf = projectDir+"/"+metaInfPath.toString();
			
			properties.put("ejb.spec.version", ejbLevel); //$NON-NLS-1$
			properties.put("ejb.metainf.dir", metaInf); //$NON-NLS-1$
			properties.put("java.class.path", ""); //$NON-NLS-1$ //$NON-NLS-2$
			properties.put("project.class.path", ""); //$NON-NLS-1$ //$NON-NLS-2$
			properties.put("project.path", ""); //$NON-NLS-1$ //$NON-NLS-2$

			properties.put("ejb.dd.displayname", ejbModule.getName()); //$NON-NLS-1$
			properties.put("ejb.dd.description", ejbModule.getName() + " generated by eclipse wtp xdoclet extension."); //$NON-NLS-1$

		} catch (Exception e) {
			Logger.logException(e);
		} finally {
			if (core != null)
				core.dispose();
			if (ejbEdit != null)
				ejbEdit.dispose();
		}
		return properties;
	}

	private void setEjbClientJarProperties(Properties properties, StructureEdit core, WorkbenchComponent ejbModule)
			throws UnresolveableURIException {
		clientProject = null;
		Iterator refComps = ejbModule.getReferencedComponents().iterator();
		if (refComps.hasNext()) {
			ReferencedComponent refedComp = (ReferencedComponent) refComps.next();
			WorkbenchComponent clientEjbJarComp = core.findComponentByURI(refedComp.getHandle());
			IProject project = StructureEdit.getContainingProject(clientEjbJarComp);
			if (J2EEProjectUtilities.isUtilityProject(project)) {
				properties.put("ejb.dd.clientjar", clientEjbJarComp.getName() + ".jar"); //$NON-NLS-1$
				setClientJarSourcepath(properties, ejbModule, clientEjbJarComp);
			}

		}
	}

	private void setClientJarSourcepath(Properties properties, WorkbenchComponent ejbModule, WorkbenchComponent ejbClientJarComp) {
		// TODO: THIS API DOES NOT WORK YET
		// IProject clientProj = StructureEdit.getContainingProject(component);
		// ComponentResource[] sourceContainers =
		// core.getSourceContainers(component);

		Iterator clientHarResources = ejbClientJarComp.getResources().iterator();
		clientProject = StructureEdit.getContainingProject(ejbClientJarComp);
		List sourcePaths = JemProjectUtilities.getSourceContainers(clientProject);
		while (clientHarResources.hasNext()) {
			ComponentResource res = (ComponentResource) clientHarResources.next();
			IPath sPath = res.getSourcePath();
			Iterator projSPaths = sourcePaths.iterator();
			while (projSPaths.hasNext()) {
				IFolder pSPath = (IFolder) projSPaths.next();
				if (sPath.makeRelative().equals(pSPath.getProjectRelativePath())) {
					properties.put("ejb.client.module.src", pSPath.getLocation().toString()); //$NON-NLS-1$
					return;
				}
			}
		}
	}

	/**
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
			if (root == null)
				return;
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

	protected void refreshProjects(IProject project, IProgressMonitor monitor) throws CoreException {
		project.refreshLocal(IResource.DEPTH_INFINITE, monitor);

		if (clientProject != null)
			clientProject.refreshLocal(IResource.DEPTH_INFINITE, monitor);

	}
	
	protected IPath getMetaInfFolder(WorkbenchComponent ejbModule) {
		ComponentResource[] METAINF = ejbModule.findResourcesByRuntimePath(new Path("/META-INF"));
		for (int i = 0; i < METAINF.length; i++) {
			ComponentResource resource = METAINF[i];
			if(resource.getRuntimePath().toString().equals("/META-INF"))
				return resource.getSourcePath();
		}		
		if (METAINF.length > 0)
			return METAINF[0].getSourcePath();
		return null;
	}

}
