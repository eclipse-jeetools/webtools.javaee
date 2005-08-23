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
 * Created on Apr 2, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author administrator
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class MultiplicityTranslator extends Translator implements EjbDeploymentDescriptorXmlMapperI{
	
	public MultiplicityTranslator(){
		super(MULTIPLICITY,EjbPackage.eINSTANCE.getEJBRelationshipRole_Multiplicity());
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertStringToValue(java.lang.String, org.eclipse.emf.ecore.EObject)
	 */
	public Object convertStringToValue(String strValue, EObject owner) {
		String correct = strValue;
		if (strValue.toUpperCase().equals("ONE")) //$NON-NLS-1$
			correct = "One"; //$NON-NLS-1$
		else if (strValue.toUpperCase().equals("MANY")) //$NON-NLS-1$
			correct = "Many"; //$NON-NLS-1$
			
		return super.convertStringToValue(correct, owner);
	}

}
