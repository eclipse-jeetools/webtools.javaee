/*******************************************************************************
 * Copyright (c) 2007 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class ServletSupertypesValidator extends AbstractSupertypesValidator {

	public static final String HTTP_SERVLET_CLASS_NAME = "javax.servlet.http.HttpServlet";
	public static final String GENERIC_SERVLET_CLASS_NAME = "javax.servlet.GenericServlet";
	public static final String SERVLET_INTERFACE_NAME = "javax.servlet.Servlet";
	
	public ServletSupertypesValidator(IDataModel dataModel) {
		super(dataModel);
	}
	
	public boolean isHttpServletSuperclass() {
		if (HTTP_SERVLET_CLASS_NAME.equals(getSuperclass()))
			return true;
		
		if (hasSuperclass(getSuperclass(), HTTP_SERVLET_CLASS_NAME))
			return true;
		
		return false;
	}

	public boolean isGenericServletSuperclass() {
		if (HTTP_SERVLET_CLASS_NAME.equals(getSuperclass()))
			return true;
		
		if (GENERIC_SERVLET_CLASS_NAME.equals(getSuperclass()))
			return true;
		
		if (hasSuperclass(getSuperclass(), GENERIC_SERVLET_CLASS_NAME))
			return true;
		
		return false;
	}
	
	public boolean isServletSuperclass() {
		if (HTTP_SERVLET_CLASS_NAME.equals(getSuperclass()))
			return true;
		
		if (GENERIC_SERVLET_CLASS_NAME.equals(getSuperclass()))
			return true;
		
		if (getInterfaces().contains(SERVLET_INTERFACE_NAME))
			return true;
		
		if (hasSuperInterface(getSuperclass(), SERVLET_INTERFACE_NAME))
			return true;
		
		for (Object iface : getInterfaces()) {
			if (hasSuperInterface((String) iface, SERVLET_INTERFACE_NAME)) 
				return true;
		}
		
		return false;
	}

}
