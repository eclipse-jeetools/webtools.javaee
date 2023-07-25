/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.web.internal.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;
import org.eclipse.jst.javaee.core.internal.util.JEEXMLHelperImpl;
import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

public class WebXMLHelperImpl extends JEEXMLHelperImpl {

	public WebXMLHelperImpl(XMLResource resource) {
		super(resource);
	}

	@Override
	public String getURI(String prefix) {
		if (prefix != null && prefix.equals("")) //$NON-NLS-1$
			return WebPackage.eNS_URI;
		else
			return super.getURI(prefix);
	}

	@Override
	protected String getQName(EPackage ePackage, String name, boolean mustHavePrefix) {
		String nsPrefix = getPrefix(ePackage, mustHavePrefix);
		// Not using EE5 package namespace - default jee5 namespace is used
		if (nsPrefix.equals(WebPackage.eNS_PREFIX) || nsPrefix.equals(JspPackage.eNS_PREFIX)) {
			return name;
		} else
			return super.getQName(ePackage, name, mustHavePrefix);
	}

	@Override
	public EStructuralFeature getFeature(EClass class1, String namespaceURI, String name, boolean isElement) {
		
		EStructuralFeature feat = super.getFeature(class1, namespaceURI, name, isElement);
		if (feat == null && !WebPackage.eNS_URI.equals(namespaceURI))
			feat =  super.getFeature(class1, WebPackage.eNS_URI, name, isElement);
		if (feat == null && !WebPackage.eNS_URI2.equals(namespaceURI))
			feat =  super.getFeature(class1, WebPackage.eNS_URI2, name, isElement);
		if (feat == null && !WebPackage.eNS_URI3.equals(namespaceURI))
			feat =  super.getFeature(class1, WebPackage.eNS_URI3, name, isElement);
		if (feat == null)
			//Try to use JSP namespace
			feat = super.getFeature(class1, JspPackage.eNS_URI, name, isElement);
		return feat;
		
	}

}
