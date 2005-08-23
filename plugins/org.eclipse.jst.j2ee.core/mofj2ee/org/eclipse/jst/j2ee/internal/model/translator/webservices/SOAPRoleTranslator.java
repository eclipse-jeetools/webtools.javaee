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
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;



public class SOAPRoleTranslator extends Translator implements WsddXmlMapperI {
	
	private static Translator[] SOAP_ROLE_XLATORS = new Translator[] {
		new Translator(TEXT_ATTRIBUTE_VALUE, WscommonPackage.eINSTANCE.getSOAPRole_SoapRole())
	};
	
	public SOAPRoleTranslator() {
		super(SOAP_ROLE, WsddPackage.eINSTANCE.getHandler_SoapRoles());
	}


	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		if (o == null)
			return CommonTranslators.EMPTY_CHILDREN;

		return SOAP_ROLE_XLATORS;
	}
	
	
	public String getDOMName(Object value) {
	  return SOAP_ROLE;
	}

		
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		WscommonFactory fact = WscommonPackage.eINSTANCE.getWscommonFactory();
		return fact.createSOAPRole();

	}
	
	public boolean isManagedByParent() {
		return false;
	}

}

