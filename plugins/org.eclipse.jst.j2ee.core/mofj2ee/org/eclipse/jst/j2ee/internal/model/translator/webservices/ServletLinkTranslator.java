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

package org.eclipse.jst.j2ee.internal.model.translator.webservices;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.WsddPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddFactory;
import org.eclipse.wst.common.internal.emf.resource.Translator;



public class ServletLinkTranslator extends Translator implements WsddXmlMapperI {
	
	private static Translator[] SERVLET_LINK_XLATORS = new Translator[] {
		new Translator(TEXT_ATTRIBUTE_VALUE, WsddPackage.eINSTANCE.getServletLink_ServletLink())
	};
	
	public ServletLinkTranslator() {
		super(SERVLET_LINK, WsddPackage.eINSTANCE.getServiceImplBean_EServletLink());
	}


	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		if (o == null)
			return CommonTranslators.EMPTY_CHILDREN;

		return SERVLET_LINK_XLATORS;
	}
	
	
	public String getDOMName(Object value) {
	  return SERVLET_LINK;
	}

		
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		WsddFactory fact = WsddPackage.eINSTANCE.getWsddFactory();
		return fact.createServletLink();

	}
	
	public boolean isManagedByParent() {
		return false;
	}

}

