/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.wst.web.internal.operation.IBaseWebNature;
import org.eclipse.wst.web.internal.operation.StaticWebNatureRuntime;

import com.ibm.wtp.emf.workbench.ProjectUtilities;


public class WebNatureRuntimeUtilities {
	/**
	 * WebNatureRuntimeHelper constructor comment.
	 */
	public WebNatureRuntimeUtilities() {
		super();
	}

	/**
	 * Adds a nauture to a project
	 */
	protected static void addNatureToProject(IProject proj, String natureId) throws CoreException {
		ProjectUtilities.addNatureToProject(proj, natureId);
	}

	/**
	 * Create a new nature runtime from the project info
	 */
	public static IBaseWebNature createRuntime(WebProjectInfo info) throws CoreException {
		IProject project = info.getProject();

		// refresh WebProjectInfo for existing runtime
		if (hasRuntime(project)) {
			// this is for when the web nature is being changed, such
			// as from converting from static to j2ee
			IBaseWebNature runtime = getRuntime(project);

			if (J2EEWebNatureRuntime.hasRuntime(project))
				((J2EEWebNatureRuntime) runtime).initializeFromInfo(info);


			return runtime;
		}
		if (info.getWebProjectType() == IWebNatureConstants.J2EE_WEB_PROJECT)
			addNatureToProject(project, IWebNatureConstants.J2EE_NATURE_ID);
		else
			addNatureToProject(project, IWebNatureConstants.STATIC_NATURE_ID);

		IBaseWebNature runtime = getRuntime(project);
		if (info.getWebProjectType() == IWebNatureConstants.J2EE_WEB_PROJECT)
			((J2EEWebNatureRuntime) runtime).initializeFromInfo(info);
		//No longer supporting for static projects
//		else
//			((StaticWebNatureRuntime) runtime).initializeFromInfo(info);

		return runtime;

	}

	/**
	 * Creation date: (10/22/2001 2:17:25 PM)
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.internal.web.operations.IBaseWebNature
	 */
	public static IBaseWebNature getRuntime(IProject project) {
		if (project == null)
			return null;
		try {
			IBaseWebNature nature;
			if (project.hasNature(IWebNatureConstants.J2EE_NATURE_ID))
				nature = (IBaseWebNature) project.getNature(IWebNatureConstants.J2EE_NATURE_ID);
			else
				nature = (IBaseWebNature) project.getNature(IWebNatureConstants.STATIC_NATURE_ID);
			return nature;
		} catch (CoreException e) {
			return null;
		}
	}


	/**
	 * Return the J2EE Web Nature for the given project. If the the project does not have a J2EE Web
	 * Nature, then return null.
	 * 
	 * @param project
	 *            The project to get the nature from
	 * @return IJ2EEWebNature The J2EE Web Nature
	 */
	public static J2EEWebNatureRuntime getJ2EERuntime(IProject project) {
		IBaseWebNature nature = getRuntime(project);
		if (nature != null) {
			if (nature.isJ2EE())
				return (J2EEWebNatureRuntime) nature;
		}
		return null;
	}

	/**
	 * Return the J2EE Web Nature for the given project. If the the project does not have a J2EE Web
	 * Nature, then return null.
	 * 
	 * @param project
	 *            The project to get the nature from
	 * @return IJ2EEWebNature The J2EE Web Nature
	 */
	public static StaticWebNatureRuntime getStaticRuntime(IProject project) {
		IBaseWebNature nature = getRuntime(project);
		if (nature != null) {
			if (nature.isStatic())
				return (StaticWebNatureRuntime) nature;
		}
		return null;
	}

	/**
	 * Return whether or not the project has a runtime created on it.
	 * 
	 * @return boolean
	 * @param project
	 *            com.ibm.itp.core.api.resources.IProject
	 * @deprecated
	 */
	public static boolean hasRuntime(IProject project) {
		try {
			return project.hasNature(IWebNatureConstants.J2EE_NATURE_ID);
		} catch (CoreException e) {
			return false;
		}
	}

	/**
	 * Return whether or not the project has a runtime created on it.
	 * 
	 * @return boolean
	 * @param project
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	public static boolean hasStaticRuntime(IProject project) {
		if (project == null || !project.exists()) {
			return false;
		}
		try {
			return project.hasNature(IWebNatureConstants.STATIC_NATURE_ID);
		} catch (CoreException e) {
			return false;
		}
	}

	/**
	 * Return whether or not the project has a runtime created on it.
	 * 
	 * @return boolean
	 * @param project
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	public static boolean hasJ2EERuntime(IProject project) {
		if (project == null || !project.exists()) {
			return false;
		}
		try {
			return project.hasNature(IWebNatureConstants.J2EE_NATURE_ID);
		} catch (CoreException e) {
			return false;
		}
	}

	public static String getDefaultJ2EEWebContentName() {
		return J2EEPlugin.getDefault().getJ2EEPreferences().getJ2EEWebContentFolderName();
	}

	public static String getDefaultStaticWebContentName() {
		return J2EEPlugin.getDefault().getJ2EEPreferences().getStaticWebContentFolderName();
	}

	public static IPath getDefaultJ2EEWebContentPath() {
		return new Path(getDefaultJ2EEWebContentName());
	}

	public static String getContextRootFromWebProject(IProject project) {

		// get uri from web app display name, and get context root from web nature
		IBaseWebNature nature = WebNatureRuntimeUtilities.getRuntime(project);
		String contextRoot = ""; //$NON-NLS-1$
		contextRoot = nature.getContextRoot();

		//	WebEditModel webEditModel = null;
		//	try {
		//		webEditModel = (nature.isStatic())
		//						? null
		//						:
		// ((J2EEWebNatureRuntime)nature).getWebAppEditModelForRead(WebNatureRuntimeUtilities.this);
		//		contextRoot = nature.getContextRoot();
		//	} catch (Exception e) {
		//		e.printStackTrace();
		//	}
		//	finally {
		//		if (webEditModel != null)
		//			webEditModel.releaseAccess(WebNatureRuntimeUtilities.this);
		//	}
		return contextRoot;
	}

	public static IPath getDefaultWEBINFPath() {
		return getDefaultJ2EEWebContentPath().append(IWebNatureConstants.INFO_DIRECTORY);
	}

	public static IPath getDefaultWebOutputFolderPath() {
		return getDefaultWEBINFPath().append(IWebNatureConstants.CLASSES_DIRECTORY);
	}

	public static IPath getWebOutputFolderPath(String webContentName) {
		return new Path(webContentName).append(IWebNatureConstants.INFO_DIRECTORY).append(IWebNatureConstants.CLASSES_DIRECTORY);
	}

	public static String getDefaultJavaSourceName() {
		return J2EEPlugin.getDefault().getJ2EEPreferences().getJavaSourceFolderName();
	}

}