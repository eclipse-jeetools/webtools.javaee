/*******************************************************************************
 * Copyright (c) 2007, 2024 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.operations;

import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_BINDING_EVENT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_BINDING_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_EVENT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_HTTP_SESSION_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_EVENT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_CONTEXT_EVENT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_CONTEXT_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_EVENT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_EVENT;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_SERVLET_REQUEST_LISTENER;
import static org.eclipse.jst.j2ee.web.IServletConstants.QUALIFIED_WEB_LISTENER;

import java.util.Collection;
import java.util.List;

import org.eclipse.jst.j2ee.web.IServletConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateListenerTemplateModel extends CreateWebClassTemplateModel {
	
	@Override
	public Collection<String> getImports() {
		Collection<String> imports = super.getImports();
		
		float servletVersion = 3;
		boolean isJakartaEE = true;
		try {
			String eeVersion = getJavaEEVersion();
			isJakartaEE = (eeVersion == null || Double.parseDouble(eeVersion) >= 5);
			servletVersion = eeVersion != null ? Float.parseFloat(eeVersion) : 6;
		}
		catch (NumberFormatException e) {
			isJakartaEE = true;
		}
		if (implementServletContextListener()) {
			if (isJakartaEE) {
				imports.add(IServletConstants.QUALIFIED_JAKARTA_SERVLET_CONTEXT_LISTENER);
				imports.add(IServletConstants.QUALIFIED_JAKARTA_SERVLET_CONTEXT_EVENT);
			}
			else {
				imports.add(QUALIFIED_SERVLET_CONTEXT_LISTENER);
				imports.add(QUALIFIED_SERVLET_CONTEXT_EVENT);
			}
		}
		
		if (implementServletContextAttributeListener()) {
			if (isJakartaEE) {
				imports.add(IServletConstants.QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER);
				imports.add(IServletConstants.QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_EVENT);
			}
			else {
				imports.add(QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER);
				imports.add(QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_EVENT);
			}
		}
		
		if (implementHttpSessionListener()) {
			if (isJakartaEE) {
				imports.add(IServletConstants.QUALIFIED_HTTP_SESSION_LISTENER);
				imports.add(IServletConstants.QUALIFIED_HTTP_SESSION_EVENT);
			}
			else {
				imports.add(QUALIFIED_HTTP_SESSION_LISTENER);
				imports.add(QUALIFIED_HTTP_SESSION_EVENT);
			}
		}
		
		if (implementHttpSessionAttributeListener()) {
			if (isJakartaEE) {
				imports.add(IServletConstants.QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER);
				imports.add(IServletConstants.QUALIFIED_HTTP_SESSION_BINDING_EVENT);
			}
			else {
				imports.add(QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER);
				imports.add(QUALIFIED_HTTP_SESSION_BINDING_EVENT);
			}
		}
		
		if (implementHttpSessionActivationListener()) {
			if (isJakartaEE) {
				imports.add(IServletConstants.QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER);
				imports.add(IServletConstants.QUALIFIED_HTTP_SESSION_EVENT);
			}
			else {
				imports.add(QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER);
				imports.add(QUALIFIED_HTTP_SESSION_EVENT);
			}
		}
		
		if (implementHttpSessionBindingListener()) {
			if (isJakartaEE) {
				imports.add(IServletConstants.QUALIFIED_HTTP_SESSION_BINDING_LISTENER);
				imports.add(IServletConstants.QUALIFIED_HTTP_SESSION_BINDING_EVENT);
			}
			else {
				imports.add(QUALIFIED_HTTP_SESSION_BINDING_LISTENER);
				imports.add(QUALIFIED_HTTP_SESSION_BINDING_EVENT);
			}
		}
		
		if (implementServletRequestListener()) {
			if (isJakartaEE) {
				imports.add(IServletConstants.QUALIFIED_SERVLET_REQUEST_LISTENER);
				imports.add(IServletConstants.QUALIFIED_SERVLET_REQUEST_EVENT);
			}
			else {
				imports.add(QUALIFIED_SERVLET_REQUEST_LISTENER);
				imports.add(QUALIFIED_SERVLET_REQUEST_EVENT);
			}
		}
		
		if (implementServletRequestAttributeListener()) {
			if (isJakartaEE) {
				imports.add(IServletConstants.QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER);
				imports.add(IServletConstants.QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_EVENT);
			}
			else {
				imports.add(QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER);
				imports.add(QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_EVENT);
			}
		}
		
		if (isJakartaEE) {
			imports.add(IServletConstants.QUALIFIED_JAKARTA_WEB_LISTENER);
		}
		else if (servletVersion > 2){
			imports.add(QUALIFIED_WEB_LISTENER);
		}
		
		return imports;
	}

	public CreateListenerTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}

	public boolean implementServletContextListener() {
		return implementInterface(QUALIFIED_SERVLET_CONTEXT_LISTENER);
	}
	
	public boolean implementServletContextAttributeListener() {
		return implementInterface(QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER);
	}
	
	public boolean implementHttpSessionListener() {
		return implementInterface(QUALIFIED_HTTP_SESSION_LISTENER);
	}
	
	public boolean implementHttpSessionAttributeListener() {
		return implementInterface(QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER);
	}
	
	public boolean implementHttpSessionActivationListener() {
		return implementInterface(QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER);
	}
	
	public boolean implementHttpSessionBindingListener() {
		return implementInterface(QUALIFIED_HTTP_SESSION_BINDING_LISTENER);
	}
	
	public boolean implementServletRequestListener() {
		return implementInterface(QUALIFIED_SERVLET_REQUEST_LISTENER);
	}
	
	public boolean implementServletRequestAttributeListener() {
		return implementInterface(QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER);
	}
	
	private boolean implementInterface(String iface) {
		List<String> interfaces = getQualifiedInterfaces();
		if (interfaces != null) {
			return interfaces.contains(iface);
		}
		return false;
	}

}
