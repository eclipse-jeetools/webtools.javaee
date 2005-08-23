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
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodElementKind;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class MethodElementKindTranslator extends Translator implements EjbDeploymentDescriptorXmlMapperI {
	private static final EjbPackage EJB_PKG = EjbPackage.eINSTANCE;
    /**
     * @param domNameAndPath
     * @param eClass
     */
    public MethodElementKindTranslator(){
		super(METHOD_INTF, EJB_PKG.getMethodElement_Type());
    }
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertStringToValue(java.lang.String, org.eclipse.emf.ecore.EObject)
	 */
	public Object convertStringToValue(String strValue, EObject owner) {
		Object obj = super.convertStringToValue(strValue, owner);
		if(obj != null)
			return obj;
		String correct = strValue;
		if (strValue.equalsIgnoreCase("HOME")) //$NON-NLS-1$
			correct = "Home"; //$NON-NLS-1$
		else if (strValue.equalsIgnoreCase("REMOTE")) //$NON-NLS-1$
			correct = "Remote"; //$NON-NLS-1$
		else if (strValue.equalsIgnoreCase("LOCALHOME")) //$NON-NLS-1$
			correct = "LocalHome"; //$NON-NLS-1$
		else if (strValue.equalsIgnoreCase("LOCAL")) //$NON-NLS-1$
			correct = "Local"; //$NON-NLS-1$
		return super.convertStringToValue(correct, owner);
	}
	
	/* (non-Javadoc)
     * @see com.ibm.etools.emf2xml.impl.Translator#isSetMOFValue(org.eclipse.emf.ecore.EObject)
     */
    public boolean isSetMOFValue(EObject emfObject) {
        if (((MethodElement)emfObject).getType() == MethodElementKind.UNSPECIFIED_LITERAL)
        	return false;
        return super.isSetMOFValue(emfObject);
    }
    
    /* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getMOFChildren(org.eclipse.emf.ecore.EObject)
	 */
	public List getMOFChildren(EObject mofObject) {
		List result = super.getMOFChildren(mofObject);
		if(result != null && result.size() > 0) {
			MethodElementKind methodIntf = (MethodElementKind) result.get(0);
			if(methodIntf.getValue() == MethodElementKind.UNSPECIFIED) 
				result = Collections.EMPTY_LIST;
		}
		return result;
	}


}
