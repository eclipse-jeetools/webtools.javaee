/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.tests.modulecore;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.web.archive.operations.WebModuleCreationDataModel;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.ModuleEditModel;
import org.eclipse.wst.common.modulecore.ModuleEditModelFactory;
import org.eclipse.wst.common.modulecore.ModuleStructuralModel;
import org.eclipse.wst.common.modulecore.ProjectModules;
import org.eclipse.wst.common.modulecore.WorkbenchModule;
import org.eclipse.wst.common.modulecore.impl.PlatformURLModuleConnection;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p>
 */
public class ModuleEditModelTest extends TestCase {

	private IProject project;
	private EMFWorkbenchContext emfContext;

	public interface IModuleTypesConstants {
		String MODULE_TYPE_WEB = "org.eclipse.jst.modules.web"; //$NON-NLS-1$
	}


	public ModuleEditModelTest(String name) {
		super(name);
	}

	public static Test suite() {
		// return new TestSuite(ModuleEditModelTest.class);
		TestSuite suite = new TestSuite();
		suite.addTest(new ModuleEditModelTest("testURIAPI"));
		return suite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		IProject project = getProject();
		super.setUp();
	}

	public void testURIAPI() throws Exception {
		URI uri = URI.createURI("module:/resource/ProjectName/moduleIdentifier/META-INF/ejb-jar.xml");
		System.out.println("URI : \"" + uri.toString() + "\"" + " with scheme \"" + uri.scheme() + "\" has " + uri.segmentCount() + " segments. They are ...");
		String[] segments = uri.segments();
		for (int i = 0; i < segments.length; i++)
			System.out.println("[" + i + "]: " + segments[i]);

		// check for at least 3 segments

		URI moduleURI = uri.trimSegments(uri.segmentCount() - 3);
		/* Determine if the URI is for a resource or binary module */

		if (PlatformURLModuleConnection.RESOURCE_MODULE.equals(segments[0])) {
			ModuleStructuralModel structuralModel = null;
			try {
				/* We need to find the project */
				IProject containingProject = getProject(segments[1]);
				ModuleCoreNature moduleCoreNature = getNature(containingProject);
				structuralModel = moduleCoreNature.getModuleStructuralModelForRead(this);
				ProjectModules modules = (ProjectModules) structuralModel.getPrimaryRootObject();
				EList workbenchModules = modules.getWorkbenchModules();
				for (Iterator iter = workbenchModules.iterator(); iter.hasNext();) {
					WorkbenchModule element = (WorkbenchModule) iter.next();

					// if(element.getHandle())

				}
			} finally {
				if (structuralModel != null)
					structuralModel.releaseAccess(this);
			}
		} else if (PlatformURLModuleConnection.BINARY_MODULE.equals(segments[0])) {

		}
	}

	/**
	 * @param containingProject
	 * @return
	 */
	private ModuleCoreNature getNature(IProject aProject) {
		try {
			return (ModuleCoreNature) aProject.getNature(ModuleCoreNature.MODULE_NATURE_ID);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param segments
	 * @return
	 */
	private IProject getProject(String aProjectName) {
		return ResourcesPlugin.getWorkspace().getRoot().getProject(aProjectName);
	}

	/**
	 * 
	 */
	public void testLoadingDocument() {
		ModuleEditModelFactory factory = new ModuleEditModelFactory();

		URI moduleURI = URI.createURI(PlatformURLModuleConnection.MODULE_PROTOCOL + IPath.SEPARATOR + getProjectName() + IPath.SEPARATOR + getModuleName());
		URI ddURI = URI.createURI(IPath.SEPARATOR + "WEB-INF" + IPath.SEPARATOR + "web.xml");

		Map params = new HashMap();
		params.put(ModuleEditModelFactory.PARAM_MODULE_URI, moduleURI);
		EMFWorkbenchContext context = createEMFWorkbenchContext();
		ModuleEditModel editModel = (ModuleEditModel) factory.createEditModelForWrite(IModuleTypesConstants.MODULE_TYPE_WEB, context, params);
		Resource ddResource = editModel.getResource(ddURI);
		EObject rootObject = (EObject) ddResource.getContents().get(0);
		if (rootObject == null)
			System.out.println("Read failed.");
		else
			System.out.println("Found WebApp: " + ((WebApp) rootObject).getDisplayName());

	}

	public EMFWorkbenchContext createEMFWorkbenchContext() {
		if (emfContext == null)
			emfContext = new EMFWorkbenchContext(getProject());
		return emfContext;
	}

	public IProject getProject() {
		if (project == null) {
			project = ResourcesPlugin.getWorkspace().getRoot().getProject(getProjectName());
			if (!project.exists()) {
				try {
					WebModuleCreationDataModel dataModel = new WebModuleCreationDataModel();
					dataModel.setProperty(WebModuleCreationDataModel.PROJECT_NAME, getProjectName());
					dataModel.getDefaultOperation().run(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return project;
	}

	public String getProjectName() {
		return "BobDolesModules";
	}

	public String getModuleName() {
		return "MyWebModule";
	}



}
