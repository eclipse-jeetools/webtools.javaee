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


import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.application.operations.DefaultModuleProjectCreationOperation;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesResource;
import org.eclipse.wst.common.emfworkbench.EMFWorkbenchContext;


/**
 * Insert the type's description here. Creation date: (7/8/2001 6:31:56 PM)
 * 
 * @author: Administrator
 */
public class AppClientEditModel extends org.eclipse.jst.j2ee.J2EEEditModel {
	/**
	 * AppClientEditModel constructor comment.
	 * 
	 * @param aKey
	 *            java.lang.Object
	 * @param aNature
	 *            org.eclipse.jst.j2ee.j2eeproject.J2EENature
	 */
	public AppClientEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly) {
		super(editModelID, context, readOnly);
	}

	public AppClientEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly, boolean accessUnkownResourcesAsReadOnly) {
		super(editModelID, context, readOnly, accessUnkownResourcesAsReadOnly);
	}

	public ApplicationClientNatureRuntime getAppClientNature() {
		return ApplicationClientNatureRuntime.getRuntime(getProject());
	}

	public Resource getAppClientXmiResource() {
		return getResource(J2EEConstants.APP_CLIENT_DD_URI_OBJ);
	}

	/**
	 * Return the root object, ApplicationClient, from the application-client.xml DD.
	 */
	public ApplicationClient getApplicationClient() {
		Resource dd = getAppClientXmiResource();
		if (dd != null) {
			Object rootObject = getRoot(dd);
			if (rootObject instanceof ApplicationClient)
				return (ApplicationClient) rootObject;
		}

		return null;
	}

	public Resource makeAppClientXmiResource() {
		return createResource(J2EEConstants.APP_CLIENT_DD_URI_OBJ);
	}

	public Resource makeDeploymentDescriptorWithRoot() {
		XMLResource res = (XMLResource) makeAppClientXmiResource();
		ApplicationClient client = ClientPackage.eINSTANCE.getClientFactory().createApplicationClient();
		res.getContents().add(client);
		res.setID(client, J2EEConstants.APP_CLIENT_ID);
		res.setModuleVersionID(getAppClientNature().getModuleVersion());
		client.setDisplayName(getProject().getName());
		return res;
	}

	public XMLResource getDeploymentDescriptorResource() {
		return (XMLResource) getAppClientXmiResource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.workbench.J2EEEditModel#getRootObject()
	 */
	public Object getRootObject() {
		return getApplicationClient();
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.workbench.J2EEEditModel#get13WebServicesClientResource()
	 */
	public WebServicesResource get13WebServicesClientResource() {
		return (WebServicesResource) getResource(J2EEConstants.WEB_SERVICES_CLIENT_META_INF_DD_URI_OBJ);
	}

	public String getDevelopmentAcivityID() {
		return DefaultModuleProjectCreationOperation.ENTERPRISE_JAVA;
	}
}