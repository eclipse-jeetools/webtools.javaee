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
 * Created on Apr 30, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.common;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.common.ResAuthTypeBase;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;
import org.eclipse.jst.j2ee.internal.xml.DeploymentDescriptorXmlMapperI;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.internal.emf.resource.Translator;
/**
 * @author schacher
 */
public class ResAuthTranslator extends Translator implements DeploymentDescriptorXmlMapperI {

	private static final String CONTAINER_AUTH = "CONTAINER"; //$NON-NLS-1$
	private static final String SERVLET_AUTH = "SERVLET"; //$NON-NLS-1$
	public ResAuthTranslator() {
		super(RES_AUTH, CommonPackage.eINSTANCE.getResourceRef_Auth());
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertStringToValue(java.lang.String, org.eclipse.emf.ecore.EObject)
	 */
	public Object convertStringToValue(String strValue, EObject owner) {
		if (isWeb((ResourceRef)owner))
			return convertWebStringToValue(strValue, owner);
		
		return super.convertStringToValue(strValue, owner);
	}
	
	/**
	 * @param strValue
	 * @param owner
	 * @return
	 */
	private Object convertWebStringToValue(String strValue, EObject owner) {
		if (SERVLET_AUTH.equals(strValue)) 
			return ResAuthTypeBase.APPLICATION_LITERAL;
		else if (CONTAINER_AUTH.equals(strValue)) 
			return ResAuthTypeBase.CONTAINER_LITERAL;
		else 
			return super.convertStringToValue(strValue, owner);
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#convertValueToString(java.lang.Object, org.eclipse.emf.ecore.EObject)
	 */
	public String convertValueToString(Object value, EObject owner) {
		if (isWeb22(((ResourceRef)owner)))
			return convertWebValueToString(value, owner);
		 
		return super.convertValueToString(value, owner);
	}
	
	/**
	 * @param value
	 * @param owner
	 */
	private String convertWebValueToString(Object value, EObject owner) {
		if (ResAuthTypeBase.APPLICATION_LITERAL == value)
			return SERVLET_AUTH;
		else if (ResAuthTypeBase.CONTAINER_LITERAL == value)
			return CONTAINER_AUTH;
		else 
			return super.convertValueToString(value, owner);
	}

	protected boolean isWeb(ResourceRef ref) {
		EObject owner = ref.eContainer();
		return (owner instanceof WebApp);
	}
	protected boolean isWeb22(ResourceRef ref) { 
		return isWeb(ref) && ((WebApp)ref.eContainer()).getVersionID() <= J2EEVersionConstants.WEB_2_2_ID;
	}


}
