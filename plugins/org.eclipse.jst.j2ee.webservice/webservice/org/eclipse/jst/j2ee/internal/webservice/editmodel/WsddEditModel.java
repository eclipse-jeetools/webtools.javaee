/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.webservice.editmodel;

import java.util.Iterator;

import org.eclipse.core.resources.IContainer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddResource;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;


public class WsddEditModel extends EditModel {

	public static final int WEB_PROJECT_WEBSERVICE = 0;
	public static final int EJB_PROJECT_WEBSERVICE = 1;
	public static final int APPCLIENT_PROJECT_WEBSERVICE = 2;

	private static final String WEB_SERVICES_FILE = "webservices.xml"; //$NON-NLS-1$
	private static final String WEB_SERVICES_CLIENT_FILE = "webservicesclient.xml"; //$NON-NLS-1$
	public static final String WS_META_INF_PATH = "META-INF/" + WEB_SERVICES_FILE; //$NON-NLS-1$
	public static final String WS_WEB_INF_PATH = "WEB-INF/" + WEB_SERVICES_FILE; //$NON-NLS-1$
	public static final String WS_CLIENT_META_INF_PATH = "META-INF/" + WEB_SERVICES_CLIENT_FILE; //$NON-NLS-1$
	public static final String WS_CLIENT_WEB_INF_PATH = "WEB-INF/" + WEB_SERVICES_CLIENT_FILE; //$NON-NLS-1$
	public static final String WSIL_FILE_EXT = "wsil"; //$NON-NLS-1$
	public static final String WSDL_FILE_EXT = "wsdl"; //$NON-NLS-1$

	private static final String SUPPORTED_DESCRIPTION_NAME = "webservices.xml"; //$NON-NLS-1$

	private WsddResource fWsddResource;

	public Resource getModelResource(String descriptorName) {
		try {
			if (descriptorName.equals(SUPPORTED_DESCRIPTION_NAME)) {
				if (fInputFile.exists()) {
					//URI uri = URI.createPlatformResourceURI(fInputFile.getFullPath().toString());
					fWsddResource = (WsddResource) fResourceSet.getResource(getWebServicesXmlResourceURI(), true);
					//fWsddResource = getWebServicesXmlResource();
					return fWsddResource;
				}
			}
			return null;
		} catch (RuntimeException re) {
			//Explicitly remove this resource from the resource set if an empty one has been left
			EList resources = fResourceSet.getResources();
			Iterator i = resources.iterator();
			while (i.hasNext()) {
				Resource res = (Resource) i.next();
				if (res instanceof WsddResource) {
					resources.remove(res);
					break;
				}
			}

			return null;
		}


	}

	public EObject getRootModelObject(String descriptorName) {
		if (fWsddResource == null)
			return null;

		return fWsddResource.getWebServices();
	}

	public EObject getRootModelObject() {
		return fWsddResource.getWebServices();
	}

	public Resource getRootModelResource() {
		return fWsddResource;
	}

	public WsddResource getWebServicesXmlResource() {
		return (WsddResource) WorkbenchResourceHelper.getOrCreateResource(getWebServicesXmlResourceURI(), fResourceSet);
	}

	public URI getWebServicesXmlResourceURI() {
		URI resourceURI = J2EEConstants.WEB_SERVICES_WEB_INF_DD_URI_OBJ;
		if (getProjectType() == APPCLIENT_PROJECT_WEBSERVICE)
			resourceURI = J2EEConstants.WEB_SERVICES_META_INF_DD_URI_OBJ;
		else if (getProjectType() == EJB_PROJECT_WEBSERVICE) {
			J2EENature aNature = J2EENature.getRegisteredRuntime(getProject());
			IContainer rootContainer = aNature.getEMFRoot();
			String folderName = rootContainer.getName();
			resourceURI = URI.createURI(folderName + "/" + J2EEConstants.WEB_SERVICES_META_INF_DD_URI); //$NON-NLS-1$
		} else if (getProjectType() == WEB_PROJECT_WEBSERVICE) {
			J2EENature aNature = J2EENature.getRegisteredRuntime(getProject());
			IContainer rootContainer = aNature.getEMFRoot();
			String folderName = rootContainer.getName();
			resourceURI = URI.createURI(folderName + "/" + J2EEConstants.WEB_SERVICES_WEB_INF_DD_URI_OBJ); //$NON-NLS-1$	
		}
		return resourceURI;
	}

	public int getProjectType() {
		int result = -1;
		try {
			if (getProject().hasNature(IEJBNatureConstants.NATURE_ID))
				result = EJB_PROJECT_WEBSERVICE;
			else if (getProject().hasNature(IApplicationClientNatureConstants.NATURE_ID))
				result = APPCLIENT_PROJECT_WEBSERVICE;
			else if (getProject().hasNature(IWebNatureConstants.J2EE_NATURE_ID))
				result = WEB_PROJECT_WEBSERVICE;
		} catch (Exception e) {
			//Ignore
		}

		return result;
	}
}