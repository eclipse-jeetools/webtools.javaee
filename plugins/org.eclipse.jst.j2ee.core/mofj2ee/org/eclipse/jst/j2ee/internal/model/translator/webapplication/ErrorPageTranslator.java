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
 * Created on Mar 21, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.model.translator.webapplication;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.model.translator.common.CommonTranslators;
import org.eclipse.jst.j2ee.internal.model.translator.common.JavaClassTranslator;
import org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.internal.xml.WarDeploymentDescriptorXmlMapperI;
import org.eclipse.jst.j2ee.webapplication.ErrorPage;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.wst.common.internal.emf.resource.ReadAheadHelper;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author schacher
 */
public class ErrorPageTranslator extends Translator implements WarDeploymentDescriptorXmlMapperI {
	private static Translator[] ERROR_CODE_XLATORS = new Translator[] {
		new Translator(ERROR_CODE, WebapplicationPackage.eINSTANCE.getErrorCodeErrorPage_ErrorCode()),
		new Translator(LOCATION, WebapplicationPackage.eINSTANCE.getErrorPage_Location())
	};
	private static Translator[] EXCEPTION_TYPE_XLATORS = new Translator[] {
		new JavaClassTranslator(EXCEPTION_TYPE, WebapplicationPackage.eINSTANCE.getExceptionTypeErrorPage_ExceptionType()),
		new Translator(LOCATION, WebapplicationPackage.eINSTANCE.getErrorPage_Location())
	};
	
	public ErrorPageTranslator() {
		super(ERROR_PAGE, WebapplicationPackage.eINSTANCE.getWebApp_ErrorPages());
		addReadAheadHelper(new ReadAheadHelper(ERROR_PAGE, new String[] {ERROR_CODE, EXCEPTION_TYPE}, null));
	
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#createEMFObject(java.lang.String, java.lang.String)
	 */
	public EObject createEMFObject(String nodeName, String readAheadName) {
		WebapplicationFactory fact = WebapplicationPackage.eINSTANCE.getWebapplicationFactory();
		if (ERROR_CODE.equals(readAheadName))
			return fact.createErrorCodeErrorPage();
		else if (EXCEPTION_TYPE.equals(readAheadName))
			return fact.createExceptionTypeErrorPage();
		else
			return null;
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.Translator#getChildren(java.lang.Object, int)
	 */
	public Translator[] getChildren(Object o, int versionID) {
		if (o == null)
			return CommonTranslators.EMPTY_CHILDREN;
		if (((ErrorPage)o).isErrorCode())
			return ERROR_CODE_XLATORS;
		else if (((ErrorPage)o).isExceptionType())
			return EXCEPTION_TYPE_XLATORS;
		else
			throw new IllegalStateException("Internal error: ErrorCode expected"); //$NON-NLS-1$
	}
		
	public boolean isManagedByParent() {
		return false;
	}
}
