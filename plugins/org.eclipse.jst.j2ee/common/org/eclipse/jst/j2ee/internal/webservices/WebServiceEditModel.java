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
/*
 * Created on Feb 3, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservices;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesResource;
import org.eclipse.jst.j2ee.webservice.wsdd.WebServices;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddResource;
import org.eclipse.wst.common.internal.emfworkbench.EMFWorkbenchContext;

import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.WorkbenchResourceHelperBase;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServiceEditModel extends J2EEEditModel {

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

	protected int projectType;

	/**
	 * constructor
	 */
	public WebServiceEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly, int projectType) {
		super(editModelID, context, readOnly);
		this.projectType = projectType;
	}

	/**
	 * constructor
	 */
	public WebServiceEditModel(String editModelID, EMFWorkbenchContext context, boolean readOnly, boolean accessUnkownResourcesAsReadOnly, int projectType) {
		super(editModelID, context, readOnly, accessUnkownResourcesAsReadOnly);
		this.projectType = projectType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel#getRootObject()
	 */
	public Object getRootObject() {
		return getWebServices();
	}

	public WebServices getWebServices() {
		Resource dd = getWebServicesXmlResource();
		if (dd != null) {
			Object webServices = getRoot(dd);
			if (webServices instanceof WebServices)
				return (WebServices) webServices;
		}
		return null;
	}

	public List getWSILResources() {
		return getResources(WSIL_FILE_EXT);
	}

	public List getWSDLResources() {
		return getResources(WSDL_FILE_EXT);
	}

	public List getResources(String ext) {
		List files = ProjectUtilities.getAllProjectFiles(getProject());
		List result = new ArrayList();
		for (int i = 0; i < files.size(); i++) {
			IFile file = (IFile) files.get(i);
			if (file != null && file.getFileExtension() != null && file.getFileExtension().equals(ext)) {
				Resource res = WorkbenchResourceHelperBase.getResource(file, true);
				if (res != null)
					result.add(res);
			}
		}
		return result;
	}

	public WsddResource getWebServicesXmlResource() {
		Resource aResource = getResource(getWebServicesXmlResourceURI());
		if (aResource.isLoaded())
			return (WsddResource) aResource;
		else return null;
	}

	public URI getWebServicesXmlResourceURI() {
		URI resourceURI = J2EEConstants.WEB_SERVICES_WEB_INF_DD_URI_OBJ;
		if (projectType == APPCLIENT_PROJECT_WEBSERVICE || projectType == EJB_PROJECT_WEBSERVICE)
			resourceURI = J2EEConstants.WEB_SERVICES_META_INF_DD_URI_OBJ;
		return resourceURI;
	}

	/**
	 * return the WSDLResource if it exists, otherwise return null
	 */
	public ResourceImpl getWsdlResource(String path) {
		if (path == null || path.equals(""))return null; //$NON-NLS-1$
		Resource res = null;
		try {
			res = getResource(URI.createURI(path));
		} catch (Exception e) {
			//Ignore
		}
		if (res != null && res.isLoaded() && res.getClass().getName().equals("org.eclipse.wst.wsdl.internal.util.WSDLResourceImpl"))
			return (ResourceImpl) res;
		return null;

	}

	public J2EEEditModel getJ2EEEditModel(Object accessorKey) {
		J2EEEditModel editModel = null;
		switch (projectType) {
			case WEB_PROJECT_WEBSERVICE :
				editModel = J2EENature.getRuntime(getProject(), IWebNatureConstants.J2EE_NATURE_ID).getJ2EEEditModelForRead(accessorKey);
				break;
			case EJB_PROJECT_WEBSERVICE :
				editModel = J2EENature.getRuntime(getProject(), IEJBNatureConstants.NATURE_ID).getJ2EEEditModelForRead(accessorKey);
				break;
			case APPCLIENT_PROJECT_WEBSERVICE :
				editModel = J2EENature.getRuntime(getProject(), IApplicationClientNatureConstants.NATURE_ID).getJ2EEEditModelForRead(accessorKey);
				break;
		}
		return editModel;
	}

	public WebServicesResource get13WebServicesClientResource() {
		String resourcePath = WS_CLIENT_WEB_INF_PATH;
		if (projectType == APPCLIENT_PROJECT_WEBSERVICE || projectType == EJB_PROJECT_WEBSERVICE)
			resourcePath = WS_CLIENT_META_INF_PATH;
		Resource webServiceResource = getResource(URI.createURI(resourcePath));
		if(webServiceResource != null && webServiceResource instanceof WebServicesResource)
			return (WebServicesResource) webServiceResource;
		return null;
	}
}