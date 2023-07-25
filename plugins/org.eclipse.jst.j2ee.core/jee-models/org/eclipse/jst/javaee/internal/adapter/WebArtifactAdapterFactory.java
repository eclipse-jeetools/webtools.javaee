/*******************************************************************************
 * Copyright (c) 2009, 2019 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * SAP AG - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.internal.adapter;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.webapplication.WebType;

public class WebArtifactAdapterFactory extends JavaEEArtifactAdapterFactory {

	@Override
	protected String getFullyQualifiedClassName(Object adaptableObject) {
		if (adaptableObject instanceof org.eclipse.jst.javaee.web.Servlet) {
			org.eclipse.jst.javaee.web.Servlet servlet = (org.eclipse.jst.javaee.web.Servlet) adaptableObject;
			return servlet.getServletClass();
		} else if (adaptableObject instanceof org.eclipse.jst.javaee.web.Filter) {
			org.eclipse.jst.javaee.web.Filter filter = (org.eclipse.jst.javaee.web.Filter) adaptableObject;
			return filter.getFilterClass();
		} else if (adaptableObject instanceof org.eclipse.jst.javaee.core.Listener) {
			org.eclipse.jst.javaee.core.Listener listener = (org.eclipse.jst.javaee.core.Listener) adaptableObject;
			return listener.getListenerClass();
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.webapplication.Servlet) {
			org.eclipse.jst.j2ee.webapplication.Servlet servlet = (org.eclipse.jst.j2ee.webapplication.Servlet) adaptableObject;
			WebType webType = servlet.getWebType();
			if (webType != null && servlet.getWebType().isServletType()) {
				return getQualifiedName(servlet.getServletClass());
			}
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.webapplication.Filter) {
			org.eclipse.jst.j2ee.webapplication.Filter filter = (org.eclipse.jst.j2ee.webapplication.Filter) adaptableObject;
			return getQualifiedName(filter.getFilterClass());
		} else if (adaptableObject instanceof org.eclipse.jst.j2ee.common.Listener) {
			org.eclipse.jst.j2ee.common.Listener listener = (org.eclipse.jst.j2ee.common.Listener) adaptableObject;
			return getQualifiedName(listener.getListenerClass());
		}
		return null;
	}
	
	private String getQualifiedName(JavaClass javaClass) {
		return (javaClass == null) ? null : javaClass.getQualifiedName();
	}
	
}
