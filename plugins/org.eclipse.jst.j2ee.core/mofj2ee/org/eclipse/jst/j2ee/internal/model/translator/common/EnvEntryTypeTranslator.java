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
package org.eclipse.jst.j2ee.internal.model.translator.common;

import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.xml.DeploymentDescriptorXmlMapperI;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class EnvEntryTypeTranslator extends Translator implements DeploymentDescriptorXmlMapperI {
	private static final String JAVA_LANG= "java.lang."; //$NON-NLS-1$
	private static final int SUBSTRING_LENGTH = JAVA_LANG.length();
	public EnvEntryTypeTranslator() {
		super(ENV_ENTRY_TYPE, CommonPackage.eINSTANCE.getEnvEntry_Type());
	}
	
	/**
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertStringToValue(String)
	 */
	public Object convertStringToValue(String strValue, EObject owner) {		
		if (strValue != null && strValue.startsWith(JAVA_LANG)) 
			return super.convertStringToValue(strValue.substring(SUBSTRING_LENGTH), owner);
		
		return null;
	}
	
	/**
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertValueToString(Object)
	 */
	public String convertValueToString(Object value, EObject owner) {
		if (value != null) 
			return JAVA_LANG + ((Enumerator)value).getName(); //$NON-NLS-1$
		return null;
	}



}
