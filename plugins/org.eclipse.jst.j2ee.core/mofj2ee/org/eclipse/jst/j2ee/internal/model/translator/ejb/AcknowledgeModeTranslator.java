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
 * Created on Mar 31, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.ejb;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.ejb.AcknowledgeMode;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.xml.EjbDeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public class AcknowledgeModeTranslator extends Translator implements EjbDeploymentDescriptorXmlMapperI {

	public static final String AUTO_ACKNOWLEDGE = "Auto-acknowledge"; //$NON-NLS-1$
	public static final String DUPS_OK_ACKNOWLEDGE = "Dups-ok-acknowledge"; //$NON-NLS-1$
	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public AcknowledgeModeTranslator() {
		super(ACKNOWLEDGE_MODE, EjbPackage.eINSTANCE.getMessageDriven_AcknowledgeMode());
	}
	
	
	public Object convertStringToValue(String strValue, EObject owner) {
		if (AUTO_ACKNOWLEDGE.equalsIgnoreCase(strValue))
			return AcknowledgeMode.AUTO_ACKNOWLEDGE_LITERAL;
		else if (DUPS_OK_ACKNOWLEDGE.equalsIgnoreCase(strValue))
			return AcknowledgeMode.DUPS_OK_ACKNOWLEDGE_LITERAL;
		else 
			return super.convertStringToValue(strValue, owner);
	}

	public String convertValueToString(Object value, EObject owner) {
		if (AcknowledgeMode.AUTO_ACKNOWLEDGE_LITERAL.equals(value)) 
			return AUTO_ACKNOWLEDGE;
		else if (AcknowledgeMode.DUPS_OK_ACKNOWLEDGE_LITERAL.equals(value))
			return DUPS_OK_ACKNOWLEDGE;
		else
			return super.convertValueToString(value, owner);
	}


}
