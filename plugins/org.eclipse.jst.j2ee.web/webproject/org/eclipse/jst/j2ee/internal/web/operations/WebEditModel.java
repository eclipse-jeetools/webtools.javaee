/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;


import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.operations.DefaultModuleProjectCreationOperation;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.web.util.WebArtifactEdit;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.jst.j2ee.webapplication.WelcomeFileList;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesResource;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;


public class WebEditModel extends org.eclipse.jst.j2ee.internal.J2EEEditModel {
	/**
	 * @param editModelID
	 * @param context
	 * @param readOnly
	 */
	public WebEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly) {
		super(editModelID, context, readOnly);
	}

	/**
	 * @param editModelID
	 * @param context
	 * @param readOnly
	 * @param knownResourceURIs
	 * @param shouldAccessUnkownURIsAsReadOnly
	 */
	public WebEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly, boolean accessUnknownResourcesAsReadOnly) {
		super(editModelID, context, readOnly, accessUnknownResourcesAsReadOnly);

	}

	// TODO -- mdelder isn't sure about this and needs to talk to jsholl
/*	public J2EEWebNatureRuntime getWebNature() {
		return J2EEWebNatureRuntime.getRuntime(getProject());
	}*/

	/**
	 * Return the root object, the web app, from the web.xml DD.
	 */
	public WebApp getWebApp() {
		Resource dd = getWebXmiResource();
		if (dd != null) {
			EList extent = dd.getContents();
			if (extent.size() > 0) {
				Object webApp = getRoot(dd);
				if (webApp instanceof WebApp)
					return (WebApp) webApp;
			}
		}
		return null;
	}


	public WebAppResource getWebXmiResource() {
		return (WebAppResource) getResource(J2EEConstants.WEBAPP_DD_URI_OBJ);
	}

	public XMLResource getDeploymentDescriptorResource() {
		return getWebXmiResource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel#getRootObject()
	 */
	public Object getRootObject() {
		return getWebApp();
	}

	public Resource makeDeploymentDescriptorWithRoot() {
		org.eclipse.jst.j2ee.common.XMLResource res = (org.eclipse.jst.j2ee.common.XMLResource) createResource(J2EEConstants.WEBAPP_DD_URI_OBJ);
		//TODO need to verify moduleVersion()
		res.setModuleVersionID(getJ2EEVersion());
		addWebAppIfNecessary(res);
		return res;
	}
	
	protected int getJ2EEVersion() {
		WebArtifactEdit webEdit = null;
		try {
			webEdit = (WebArtifactEdit) ModuleCore.getFirstArtifactEditForRead(project);
			if (webEdit != null)
				return webEdit.getJ2EEVersion();
		} finally {
			if (webEdit != null)
			webEdit.dispose();
		}
		return 0;
	}

	/**
	 * @param res
	 */
	private void addWebAppIfNecessary(XMLResource res) {
		if (res != null && res.getContents().isEmpty()) {
			WebApp webApp = WebapplicationFactory.eINSTANCE.createWebApp();
			res.getContents().add(webApp);
			webApp.setDisplayName(getProject().getName());
			res.setID(webApp, J2EEConstants.WEBAPP_ID);

			WelcomeFileList wList = WebapplicationFactory.eINSTANCE.createWelcomeFileList();
			webApp.setFileList(wList);
			List files = wList.getFile();
			WelcomeFile file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("index.html"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("index.htm"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("index.jsp"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("default.html"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("default.htm"); //$NON-NLS-1$
			files.add(file);
			file = WebapplicationFactory.eINSTANCE.createWelcomeFile();
			file.setWelcomeFile("default.jsp"); //$NON-NLS-1$
			files.add(file);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel#get13WebServicesClientResource()
	 */
	public WebServicesResource get13WebServicesClientResource() {
		return (WebServicesResource) getResource(J2EEConstants.WEB_SERVICES_CLIENT_WEB_INF_DD_URI_OBJ);
	}

	public String getDevelopmentAcivityID() {
		return DefaultModuleProjectCreationOperation.WEB_DEV_ACTIVITY_ID;
	}
}