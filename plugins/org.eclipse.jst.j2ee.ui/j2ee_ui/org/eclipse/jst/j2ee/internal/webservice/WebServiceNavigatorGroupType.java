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
 * Created on Feb 25, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice;

import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.ui.IActionFilter;
import org.eclipse.wst.wsdl.Service;

/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class WebServiceNavigatorGroupType implements IActionFilter {

	public static final int SERVICES = 0;
	public static final int CLIENTS = 2;
	public static final int HANDLERS = 3;

	public static final String SERVICES_UI = WebServiceUIResourceHandler.getString("WebServiceNavigatorGroupType_UI_0"); //$NON-NLS-1$
	public static final String CLIENTS_UI = WebServiceUIResourceHandler.getString("WebServiceNavigatorGroupType_UI_1"); //$NON-NLS-1$
	public static final String HANDLERS_UI = WebServiceUIResourceHandler.getString("WebServiceNavigatorGroupType_UI_2"); //$NON-NLS-1$

	private int TYPE;
	private Service wsdlService = null;
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
	public WebServiceNavigatorGroupType(int groupType, Service wsdlService) {
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

	public Service getWsdlService() {
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
}