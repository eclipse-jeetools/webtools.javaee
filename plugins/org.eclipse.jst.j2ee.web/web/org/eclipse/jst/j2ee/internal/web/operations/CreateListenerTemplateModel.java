/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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

import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateListenerTemplateModel extends CreateWebClassTemplateModel {
	
	@Override
	public Collection<String> getImports() {
		Collection<String> collection = super.getImports();
		
		if (implementServletContextListener()) {
			collection.add(QUALIFIED_SERVLET_CONTEXT_LISTENER);
			collection.add(QUALIFIED_SERVLET_CONTEXT_EVENT);
		}
		
		if (implementServletContextAttributeListener()) {
			collection.add(QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER);
			collection.add(QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_EVENT);
		}
		
		if (implementHttpSessionListener()) {
			collection.add(QUALIFIED_HTTP_SESSION_LISTENER);
			collection.add(QUALIFIED_HTTP_SESSION_EVENT);
		}
		
		if (implementHttpSessionAttributeListener()) {
			collection.add(QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER);
			collection.add(QUALIFIED_HTTP_SESSION_BINDING_EVENT);
		}
		
		if (implementHttpSessionActivationListener()) {
			collection.add(QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER);
			collection.add(QUALIFIED_HTTP_SESSION_EVENT);
		}
		
		if (implementHttpSessionBindingListener()) {
			collection.add(QUALIFIED_HTTP_SESSION_BINDING_LISTENER);
			collection.add(QUALIFIED_HTTP_SESSION_BINDING_EVENT);
		}
		
		if (implementServletRequestListener()) {
			collection.add(QUALIFIED_SERVLET_REQUEST_LISTENER);
			collection.add(QUALIFIED_SERVLET_REQUEST_EVENT);
		}
		
		if (implementServletRequestAttributeListener()) {
			collection.add(QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER);
			collection.add(QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_EVENT);
		}
		
		if (SERVLET_3.equals(getJavaEEVersion()) || SERVLET_3_1.equals(getJavaEEVersion()) || SERVLET_4_0.equals(getJavaEEVersion())){
			collection.add(QUALIFIED_WEB_LISTENER);
		}
		
		return collection;
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
