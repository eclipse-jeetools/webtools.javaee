/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 20, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.webapplication;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.xml.WarDeploymentDescriptorXmlMapperI;
import org.eclipse.jst.j2ee.webapplication.WebType;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public class WebTypeTranslator extends Translator implements WarDeploymentDescriptorXmlMapperI {
	
	private static Translator[] SERVLET_XLATORS = new Translator[] {
		new Translator(TEXT_ATTRIBUTE_VALUE, WebapplicationPackage.eINSTANCE.getServletType_ClassName())
	};
	private static Translator[] JSP_XLATORS = new Translator[] {
		new Translator(TEXT_ATTRIBUTE_VALUE, WebapplicationPackage.eINSTANCE.getJSPType_JspFile())
	};
	
	public WebTypeTranslator() {
		super(SERVLET_CLASS+','+JSP_FILE, WebapplicationPackage.eINSTANCE.getServlet_WebType());
	}


	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		if (o == null)
			return CommonTranslators.EMPTY_CHILDREN;
		if (((WebType)o).isJspType())
			return JSP_XLATORS;
		else if (((WebType)o).isServletType())
			return SERVLET_XLATORS;
		else
			throw new IllegalStateException("Internal error: Web Type expected"); //$NON-NLS-1$
	}
	
	
	public String getDOMName(Object value) {
		if (((WebType)value).isJspType())
			return JSP_FILE;
		else if (((WebType)value).isServletType())
			return SERVLET_CLASS;
		else 
			throw new IllegalStateException("Internal error: Web Type expected"); //$NON-NLS-1$
	}

		
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		WebapplicationFactory fact = WebapplicationPackage.eINSTANCE.getWebapplicationFactory();
		if (SERVLET_CLASS.equals(nodeName))
			return fact.createServletType();
		else if (JSP_FILE.equals(nodeName))
			return fact.createJSPType();
		else
			return null;
	}
	
	public boolean isManagedByParent() {
		return false;
	}

}
