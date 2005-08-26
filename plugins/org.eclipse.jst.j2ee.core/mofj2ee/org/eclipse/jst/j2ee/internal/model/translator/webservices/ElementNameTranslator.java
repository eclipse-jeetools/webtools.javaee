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
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapFactory;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class ElementNameTranslator extends Translator implements JaxrpcmapXmlMapperI {
	
	private static Translator[]   ELEMENT_NAME_XLATORS = new Translator[] {
		new Translator(TEXT_ATTRIBUTE_VALUE, JaxrpcmapPackage.eINSTANCE.getElementName_Text())
	};
	
	public ElementNameTranslator() {
		super(ELEMENT_NAME, JaxrpcmapPackage.eINSTANCE.getConstructorParameterOrder_ElementNames());
	}


	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		if (o == null)
			return CommonTranslators.EMPTY_CHILDREN;

		return ELEMENT_NAME_XLATORS;
	}
	
	
	public String getDOMName(Object value) {
	  return ELEMENT_NAME;
	}

		
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		JaxrpcmapFactory fact = JaxrpcmapPackage.eINSTANCE.getJaxrpcmapFactory();
		return fact.createElementName();

	}
	
	public boolean isManagedByParent() {
		return false;
	}

}

