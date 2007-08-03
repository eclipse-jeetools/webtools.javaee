/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web.internal.util;

import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;
import org.eclipse.jst.javaee.core.internal.util.JEEXMLHelperImpl;
import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

public class WebXMLHelperImpl extends JEEXMLHelperImpl {

	public WebXMLHelperImpl(XMLResource resource) {
		super(resource);
		// TODO Auto-generated constructor stub
	}

	public String getURI(String prefix) {
		if (prefix != null && prefix.equals(""))
			return WebPackage.eNS_URI;
		else
			return super.getURI(prefix);
	}

	protected String getQName(EPackage ePackage, String name, boolean mustHavePrefix) {
		String nsPrefix = getPrefix(ePackage, mustHavePrefix);
		// Not using EE5 package namespace - default jee5 namespace is used
		if (nsPrefix.equals(WebPackage.eNS_PREFIX) || nsPrefix.equals(JspPackage.eNS_PREFIX)) {
			return name;
		} else
			return super.getQName(ePackage, name, mustHavePrefix);
	}

}
