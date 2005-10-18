/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.project.facet;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.project.facet.WtpUtils;
import org.eclipse.jst.j2ee.application.ApplicationFactory;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.project.facet.EarUtil;
import org.eclipse.jst.j2ee.project.facet.IFacetDataModelPropeties;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.ComponentType;
import org.eclipse.wst.common.componentcore.internal.ComponentcoreFactory;
import org.eclipse.wst.common.componentcore.internal.Property;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.AbstractDataModelOperation;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.classpath.ClasspathHelper;

public class WebFacetInstallOperation extends AbstractDataModelOperation implements IDataModelOperation {

	private static final String WEB_LIB_CONTAINER = "org.eclipse.jst.j2ee.internal.web.container";


	public WebFacetInstallOperation(IDataModel model) {
		super(model);
	}

	public IStatus execute(IProgressMonitor monitor, IAdaptable info) throws ExecutionException {
		if (monitor != null) {
			monitor.beginTask("", 1);
		}

		try {
			IProject project = ProjectUtilities.getProject(model.getStringProperty(IFacetDataModelPropeties.FACET_PROJECT_NAME));
			IProjectFacetVersion fv = (IProjectFacetVersion) model.getProperty(IFacetDataModelPropeties.FACET_VERSION);
			
			final IJavaProject jproj = JavaCore.create(project);

			// Add WTP natures.

			WtpUtils.addNatures(project);

			// Create the directory structure.

			final IWorkspace ws = ResourcesPlugin.getWorkspace();
			final IPath pjpath = project.getFullPath();

			final IPath contentdir = pjpath.append(model.getStringProperty(IWebFacetInstallDataModelProperties.CONTENT_DIR));
			mkdirs(ws.getRoot().getFolder(contentdir));

			final IPath webinf = contentdir.append("WEB-INF");
			IFolder webinfFolder = ws.getRoot().getFolder(webinf);
			mkdirs(webinfFolder);

			final IPath webinflib = webinf.append("lib");
			mkdirs(ws.getRoot().getFolder(webinflib));

			// Setup WEB-INF/src, if necessary.

			if (model.getBooleanProperty(IWebFacetInstallDataModelProperties.CREATE_WEB_INF_SRC)) {
				final IPath webinfsrc = webinf.append("src");
				mkdirs(ws.getRoot().getFolder(webinfsrc));

				addToClasspath(jproj, JavaCore.newSourceEntry(webinfsrc));
			}

			// Setup the flexible project structure.

			final IVirtualComponent c = ComponentCore.createComponent(project);

			c.create(0, null);

			final ComponentType ctype = ComponentcoreFactory.eINSTANCE.createComponentType();

			ctype.setComponentTypeId("jst.web");
			ctype.setVersion(fv.getVersionString());

			Property prop;

			prop = ComponentcoreFactory.eINSTANCE.createProperty();
			prop.setName("context-root");
			prop.setValue(model.getStringProperty(IWebFacetInstallDataModelProperties.CONTEXT_ROOT));
			ctype.getProperties().add(prop);

			prop = ComponentcoreFactory.eINSTANCE.createProperty();
			prop.setName("java-output-path");
			prop.setValue("/build/classes/");
			ctype.getProperties().add(prop);

			final StructureEdit edit = StructureEdit.getStructureEditForWrite(project);

			try {
				StructureEdit.setComponentType(c, ctype);
				edit.saveIfNecessary(null);
			} finally {
				edit.dispose();
			}

			final IVirtualFolder jsrc = c.getRootFolder().getFolder("/WEB-INF/classes");
			final IClasspathEntry[] cp = jproj.getRawClasspath();

			for (int i = 0; i < cp.length; i++) {
				final IClasspathEntry cpe = cp[i];

				if (cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE) {
					jsrc.createLink(cpe.getPath().removeFirstSegments(1), 0, null);
				}
			}

			final IVirtualFolder webroot = c.getRootFolder();

			webroot.createLink(new Path("/" + model.getStringProperty(IWebFacetInstallDataModelProperties.CONTENT_DIR)), 0, null);

			// Create the deployment descriptor (web.xml) if one doesn't exist
			if (!webinfFolder.getFile("web.xml").exists()) {
				final WebArtifactEdit webEdit = WebArtifactEdit.getWebArtifactEditForWrite(project);

	    		String ver = model.getStringProperty(IFacetDataModelPropeties.FACET_VERSION_STR);
	    		int nVer = J2EEVersionUtil.convertVersionStringToInt(ver);
	    		
				try {
					webEdit.createModelRoot(nVer);
					webEdit.save(null);
				} finally {
					webEdit.dispose();
				}
			}

			// Setup the classpath.

			ClasspathHelper.removeClasspathEntries(project, fv);

			if (!ClasspathHelper.addClasspathEntries(project, fv)) {
				// TODO: Support the no runtime case.
				// ClasspathHelper.addClasspathEntries( project, fv, <something> );
			}

			// Add the web libraries container.

			final IPath cont = new Path(WEB_LIB_CONTAINER).append(project.getName());

			addToClasspath(jproj, JavaCore.newContainerEntry(cont));

			// Associate with an EAR, if necessary.

			final String earProjectName = model.getStringProperty(IWebFacetInstallDataModelProperties.EAR_PROJECT_NAME);

			if (earProjectName != null && !earProjectName.equals("")) {
				final IProject earproj = ws.getRoot().getProject(earProjectName);

				final EPackage.Registry registry = EPackage.Registry.INSTANCE;

				final EPackage pack = registry.getEPackage(ApplicationPackage.eNS_URI);

				final ApplicationFactory appfact = ((ApplicationPackage) pack).getApplicationFactory();

				final WebModule m = appfact.createWebModule();

				m.setUri(project.getName() + ".war");
				m.setContextRoot(model.getStringProperty(IWebFacetInstallDataModelProperties.CONTEXT_ROOT));

				EarUtil.addModuleReference(earproj, project, m);
			}

			if (monitor != null) {
				monitor.worked(1);
			}
		} catch (CoreException e) {
			throw new ExecutionException(e.getMessage(), e);
		}

		finally {
			if (monitor != null) {
				monitor.done();
			}
		}
		return OK_STATUS;
	}


	private static void addToClasspath(final IJavaProject jproj, final IClasspathEntry entry)

	throws CoreException

	{
		final IClasspathEntry[] current = jproj.getRawClasspath();
		final IClasspathEntry[] updated = new IClasspathEntry[current.length + 1];
		System.arraycopy(current, 0, updated, 0, current.length);
		updated[current.length] = entry;
		jproj.setRawClasspath(updated, null);
	}

	private static void mkdirs(final IFolder folder)

	throws CoreException

	{
		if (!folder.exists()) {
			if (folder.getParent() instanceof IFolder) {
				mkdirs((IFolder) folder.getParent());
			}

			folder.create(true, true, null);
		}
	}

}