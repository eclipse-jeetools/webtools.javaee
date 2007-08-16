/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Apr 3, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;

import org.eclipse.emf.common.util.Enumerator;
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
public class MessageDrivenDestinationTypeTranslator extends Translator implements EjbDeploymentDescriptorXmlMapperI {
	protected static final EjbPackage EJB_PKG = EjbPackage.eINSTANCE;
	public static final String JAVAX_JMS = "javax.jms."; //$NON-NLS-1$


	public MessageDrivenDestinationTypeTranslator() {
		super(DESTINATION_TYPE, EJB_PKG.getMessageDrivenDestination_Type());
	}
	
	/**
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertValueToString(java.lang.Object, org.eclipse.emf.ecore.EObject)
	 */
	public String convertValueToString(Object value, EObject owner) {
		if (value != null){
			Enumerator enumerator = (Enumerator) value;
			if (enumerator != null)
				return JAVAX_JMS + enumerator.toString();
		}
		return super.convertValueToString(value, owner);
	}

	/**
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertStringToValue(java.lang.String, org.eclipse.emf.ecore.EObject)
	 */
	public Object convertStringToValue(String strValue, EObject owner) {
		String correct = strValue;
		if (strValue != null)
			correct = strValue.substring(JAVAX_JMS.length());
		return super.convertStringToValue(correct, owner);
	}

}
