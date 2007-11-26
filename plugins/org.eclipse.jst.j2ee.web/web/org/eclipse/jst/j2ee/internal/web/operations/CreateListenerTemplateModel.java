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


import java.util.List;

import java.util.Collection;
import org.eclipse.jst.j2ee.internal.common.operations.INewJavaClassDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

public class CreateListenerTemplateModel extends CreateWebClassTemplateModel {
	
	public static final String QUALIFIED_SERVLET_CONTEXT_LISTENER = "javax.servlet.ServletContextListener"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_CONTEXT_EVENT = "javax.servlet.ServletContextEvent"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_LISTENER = "javax.servlet.ServletContextAttributeListener"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_CONTEXT_ATTRIBUTE_EVENT = "javax.servlet.ServletContextAttributeEvent"; //$NON-NLS-1$
	public static final String QUALIFIED_HTTP_SESSION_LISTENER = "javax.servlet.http.HttpSessionListener"; //$NON-NLS-1$
	public static final String QUALIFIED_HTTP_SESSION_EVENT = "javax.servlet.http.HttpSessionEvent"; //$NON-NLS-1$
	public static final String QUALIFIED_HTTP_SESSION_ATTRIBUTE_LISTENER = "javax.servlet.http.HttpSessionAttributeListener"; //$NON-NLS-1$
	public static final String QUALIFIED_HTTP_SESSION_BINDING_EVENT = "javax.servlet.http.HttpSessionBindingEvent"; //$NON-NLS-1$
	public static final String QUALIFIED_HTTP_SESSION_ACTIVATION_LISTENER = "javax.servlet.http.HttpSessionActivationListener"; //$NON-NLS-1$
	public static final String QUALIFIED_HTTP_SESSION_BINDING_LISTENER = "javax.servlet.http.HttpSessionBindingListener"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_REQUEST_LISTENER = "javax.servlet.ServletRequestListener"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_REQUEST_EVENT = "javax.servlet.ServletRequestEvent"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_LISTENER = "javax.servlet.ServletRequestAttributeListener"; //$NON-NLS-1$
	public static final String QUALIFIED_SERVLET_REQUEST_ATTRIBUTE_EVENT = "javax.servlet.ServletRequestAttributeEvent"; //$NON-NLS-1$
	
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
		
		return collection;
	}

	public CreateListenerTemplateModel(IDataModel dataModel) {
		super(dataModel);
	}

	public String getListenerName() {
		return getProperty(INewJavaClassDataModelProperties.CLASS_NAME).trim();
	}

	public boolean implementServletContextListener() {
		return implementInterface(NewListenerClassDataModelProvider.SERVLET_CONTEXT_LISTENER);
	}
	
	public boolean implementServletContextAttributeListener() {
		return implementInterface(NewListenerClassDataModelProvider.SERVLET_CONTEXT_ATTRIBUTE_LISTENER);
	}
	
	public boolean implementHttpSessionListener() {
		return implementInterface(NewListenerClassDataModelProvider.HTTP_SESSION_LISTENER);
	}
	
	public boolean implementHttpSessionAttributeListener() {
		return implementInterface(NewListenerClassDataModelProvider.HTTP_SESSION_ATTRIBUTE_LISTENER);
	}
	
	public boolean implementHttpSessionActivationListener() {
		return implementInterface(NewListenerClassDataModelProvider.HTTP_SESSION_ACTIVATION_LISTENER);
	}
	
	public boolean implementHttpSessionBindingListener() {
		return implementInterface(NewListenerClassDataModelProvider.HTTP_SESSION_BINDING_LISTENER);
	}
	
	public boolean implementServletRequestListener() {
		return implementInterface(NewListenerClassDataModelProvider.SERVLET_REQUEST_LISTENER);
	}
	
	public boolean implementServletRequestAttributeListener() {
		return implementInterface(NewListenerClassDataModelProvider.SERVLET_REQUEST_ATTRIBUTE_LISTENER);
	}
	
	private boolean implementInterface(String iface) {
		List interfaces = getQualifiedInterfaces();
		if (interfaces != null) {
			return interfaces.contains(iface);
		}
		return false;
	}

}
