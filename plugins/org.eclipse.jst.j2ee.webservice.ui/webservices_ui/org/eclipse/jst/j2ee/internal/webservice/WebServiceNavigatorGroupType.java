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
/*
 * Created on Feb 25, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.webservice.helper.WebServicesManager;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsdd.PortComponent;
import org.eclipse.ui.IActionFilter;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServiceNavigatorGroupType implements IActionFilter {

	private static final Object[] NO_CHILDREN = new Object[0];

	public static final int SERVICES = 0;
	public static final int CLIENTS = 2;
	public static final int HANDLERS = 3;

	public static final String SERVICES_UI = WebServiceUIResourceHandler.WebServiceNavigatorGroupType_UI_0; 
	public static final String CLIENTS_UI = WebServiceUIResourceHandler.WebServiceNavigatorGroupType_UI_1; 
	public static final String HANDLERS_UI = WebServiceUIResourceHandler.WebServiceNavigatorGroupType_UI_2; 

	private int TYPE;
	private EObject wsdlService = null;
	private ServiceRef serviceRef = null;

	/**
	 * Create a specific type of web service navigator grouping
	 */
	public WebServiceNavigatorGroupType(int groupType) {
		super();
		TYPE = groupType;
	}

	/**
	 * Create a specific type of web service navigator grouping
	 */
	public WebServiceNavigatorGroupType(int groupType, EObject wsdlService) {
		super();
		TYPE = groupType;
		this.wsdlService = wsdlService;
	}

	/**
	 * Create a specific type of web service navigator grouping
	 */
	public WebServiceNavigatorGroupType(int groupType, ServiceRef serviceRef) {
		super();
		TYPE = groupType;
		this.serviceRef = serviceRef;
	}

	/**
	 * @return Returns the TYPE.
	 */
	public int getGroupType() {
		return TYPE;
	}

	public boolean isServices() {
		return getGroupType() == SERVICES;
	}

	public boolean isClients() {
		return getGroupType() == CLIENTS;
	}

	public boolean isHandlers() {
		return getGroupType() == HANDLERS;
	}

	public EObject getWsdlService() {
		return wsdlService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		if (isServices())
			return SERVICES_UI;
		else if (isClients())
			return CLIENTS_UI;
		else if (isHandlers())
			return HANDLERS_UI;
		else
			return super.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IActionFilter#testAttribute(java.lang.Object, java.lang.String,
	 *      java.lang.String)
	 */
	public boolean testAttribute(Object target, String name, String value) {
		if (target != null && target instanceof WebServiceNavigatorGroupType && value != null) {
			WebServiceNavigatorGroupType group = (WebServiceNavigatorGroupType) target;
			if (group.isClients() && value.equals(CLIENTS_UI))
				return true;
			else if (group.isServices() && value.equals(SERVICES_UI))
				return true;
			else if (group.isHandlers() && value.equals(HANDLERS_UI))
				return true;
		}
		return false;
	}

	/**
	 * @return Returns the serviceRef.
	 */
	public ServiceRef getServiceRef() {
		return serviceRef;
	}
	
	
	public Object[] getChildren() {
		
		switch(getGroupType()) {
			
			case CLIENTS: 
				return WebServicesManager.getInstance().getAllWorkspaceServiceRefs().toArray();
			case HANDLERS: {
				List result = new ArrayList();
				// handle web service handlers case
				if (getWsdlService() != null) {
					PortComponent port = WebServicesManager.getInstance().getPortComponent(getWsdlService());
					if (port != null && port.getHandlers() != null && !port.getHandlers().isEmpty())
						return port.getHandlers().toArray();
				}
				// handle service ref case
				else if (getServiceRef() != null) 
					return getServiceRef().getHandlers().toArray();
				else 
					return NO_CHILDREN;
			}
			case SERVICES: {
				List result = new ArrayList();
				result.addAll(WebServicesManager.getInstance().getInternalWSDLServices());
				result.addAll(WebServicesManager.getInstance().getExternalWSDLServices());
				return result.toArray();
			}
		}
		return NO_CHILDREN;
		
	}
	
}
