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
package org.eclipse.jst.j2ee.applicationclient.creation;


import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.ApplicationClientProjectLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleWorkbenchURIConverterImpl;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * Insert the type's description here. Creation date: (4/4/2001 9:59:29 AM)
 * 
 * @author: Administrator
 */
public class ApplicationClientNatureRuntime extends J2EEModuleNature implements IApplicationClientNatureConstants {

	private static final String CLIENT_PROJECT_12_OVERLAY = "1_2_ovr"; //$NON-NLS-1$
	private static final String CLIENT_PROJECT_13_OVERLAY = "1_3_ovr"; //$NON-NLS-1$
	private static final String CLIENT_PROJECT_14_OVERLAY = "1_4_ovr"; //$NON-NLS-1$

	/**
	 * ApplicationClientNatureRuntime constructor comment.
	 */
	public ApplicationClientNatureRuntime() {
		super();
	}

	public ApplicationClientFile asApplicationClientFile() throws OpenFailureException {
		return asApplicationClientFile(true);
	}

	public ApplicationClientFile asApplicationClientFile(boolean shouldExportSource) throws OpenFailureException {
		IProject proj = getProject();
		if (proj == null)
			return null;

		if (isBinaryProject()) {
			String location = ((J2EEModuleWorkbenchURIConverterImpl) getJ2EEWorkbenchURIConverter()).getInputJARLocation().toOSString();
			return getCommonArchiveFactory().openApplicationClientFile(location);
		}
		ApplicationClientProjectLoadStrategyImpl loader = new ApplicationClientProjectLoadStrategyImpl(proj);
		loader.setExportSource(shouldExportSource);
		loader.setResourceSet(this.getResourceSet());
		return getCommonArchiveFactory().openApplicationClientFile(loader, proj.getName());
	}

	/**
	 * Return a "virtual" archive on this nature's project; used for export
	 */
	public Archive asArchive() throws OpenFailureException {
		return asApplicationClientFile();
	}

	/**
	 * Return a "virtual" archive on this nature's project; used for export
	 */
	public Archive asArchive(boolean shouldExportSource) throws OpenFailureException {
		return asApplicationClientFile(shouldExportSource);
	}

	protected EditModel createCacheEditModel() {
		return getAppClientEditModelForRead(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEVersion()
	 */
	public int getJ2EEVersion() {
		// TODO Auto-generated method stub

		return getModuleVersion();
	}

	/**
	 * Method used for adding a j2ee project to an ear project; subclasses must override to create a
	 * new instance of the correct kind of Module
	 */
	public Module createNewModule() {
		return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createJavaClientModule();
	}

	/**
	 * Create a new nature runtime from the project info
	 */
	//	public static ApplicationClientNatureRuntime
	// createRuntime(ApplicationClientProjectInfo info) throws CoreException {
	//		IProject project = info.getProject();
	//		if (!hasRuntime(project)) {
	//			addNatureToProject(project, info.getNatureId());
	//			ApplicationClientNatureRuntime runtime = getRuntime(project);
	//			runtime.initializeFromInfo(info);
	//			return runtime;
	//		}
	//		return getRuntime(project);
	//	}
	public String getEditModelKey() {
		return EDIT_MODEL_ID;
	}

	public AppClientEditModel getAppClientEditModelForRead(Object accessorKey) {
		return (AppClientEditModel) getEditModelForRead(EDIT_MODEL_ID, accessorKey);
	}

	public AppClientEditModel getAppClientEditModelForWrite(Object accessorKey) {
		return (AppClientEditModel) getEditModelForWrite(EDIT_MODEL_ID, accessorKey);
	}

	/**
	 * Return the root object, the application, from the application.xml DD.
	 */
	public ApplicationClient getApplicationClient() {
		return ((AppClientEditModel) getCacheEditModel()).getApplicationClient();
	}

	public Resource getApplicationClientXmiResource() {
		return getResource(URI.createURI(ArchiveConstants.APP_CLIENT_DD_URI));
	}

	protected String getDefaultSourcePathString() {
		return IApplicationClientNatureConstants.DEFAULT_SOURCE_PATH;
	}

	/**
	 * @see IJ2EENature
	 */
	public IContainer getModuleServerRoot() {
		return ProjectUtilities.getJavaProjectOutputContainer(project);
	}

	/**
	 * Return the nature's ID.
	 */
	public String getNatureID() {
		return IApplicationClientNatureConstants.NATURE_ID;
	}

	/**
	 * Return the ID of the plugin that this nature is contained within.
	 */
	protected String getPluginID() {
		return J2EEPlugin.PLUGIN_ID;
	}

	/**
	 * Get a WebNatureRuntime that corresponds to the supplied project.
	 * 
	 * @return com.ibm.itp.wt.IWebNature
	 * @param project
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	public static ApplicationClientNatureRuntime getRuntime(IProject project) {
		return (ApplicationClientNatureRuntime) getRuntime(project, IApplicationClientNatureConstants.APPCLIENT_NATURE_IDS);
	}

	/**
	 * Return whether or not the project has a runtime created on it.
	 * 
	 * @return boolean
	 * @param project
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	public static boolean hasRuntime(IProject project) {
		return hasRuntime(project, IApplicationClientNatureConstants.APPCLIENT_NATURE_IDS);
	}

	/*
	 * @see J2EENature#canBeBinary()
	 */
	public boolean canBeBinary() {
		return true;
	}

	/**
	 * Get the module root folder.
	 * 
	 * @return IContainer
	 */
	public IContainer getModuleRoot() {
		return getSourceFolder();
	} // getModuleRoot

	public String getOverlayIconName() {
		switch (getJ2EEVersion()) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return CLIENT_PROJECT_12_OVERLAY;
			case J2EEVersionConstants.J2EE_1_3_ID :
				return CLIENT_PROJECT_13_OVERLAY;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				return CLIENT_PROJECT_14_OVERLAY;
		}
	}

	public int getDeploymentDescriptorType() {
		return XMLResource.APP_CLIENT_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getDeploymentDescriptorRoot()
	 */
	public EObject getDeploymentDescriptorRoot() {
		return getApplicationClient();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getVersionFromModuleFile()
	 */
	protected int getVersionFromModuleFile() {
		ApplicationClient ddRoot = getApplicationClient();
		if (ddRoot != null) {
			return ddRoot.getVersionID();
		}
		return J2EEVersionConstants.J2EE_1_4_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEEditModelForRead(java.lang.Object)
	 */
	public J2EEEditModel getJ2EEEditModelForRead(Object accessorKey) {
		return getAppClientEditModelForRead(accessorKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEEditModelForWrite(java.lang.Object)
	 */
	public J2EEEditModel getJ2EEEditModelForWrite(Object accessorKey) {
		return getAppClientEditModelForWrite(accessorKey);
	}

	/**
	 * Return an editing model used to read web service resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	// TODO WebServices for M3
//	public WebServiceEditModel getWebServiceEditModelForRead(Object accessorKey, Map params) {
//		return (WebServiceEditModel) getEditModelForRead(WEB_SERVICE_EDIT_MODEL_ID, accessorKey, params);
//	}


	/**
	 * Return an editing model used to edit web service resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	// TODO WebServices for M3
//	public WebServiceEditModel getWebServiceEditModelForWrite(Object accessorKey, Map params) {
//		return (WebServiceEditModel) getEditModelForWrite(WEB_SERVICE_EDIT_MODEL_ID, accessorKey, params);
//	}


}