/*******************************************************************************
 * Copyright (c) 2017 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.webservice;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceExtManager;
import org.eclipse.jst.j2ee.internal.webservices.WSDLServiceHelper;

@SuppressWarnings("restriction")
public class WebServicePropertyTester extends PropertyTester {

	private static final String PROPERTY_WSDL_RESOURCE = "isWsdlResource"; //$NON-NLS-1$
	private static final String PROPERTY_SERVICE = "isService"; //$NON-NLS-1$
	
	public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
		
		WSDLServiceHelper serviceHelper = WSDLServiceExtManager.getServiceHelper();
		if (serviceHelper == null) {
			return false;
		}
		if (receiver instanceof EObject) {
			if (PROPERTY_SERVICE.equals(property)) {
				return serviceHelper.isService(receiver);
			}
		} else if (receiver instanceof Resource) {
			if (PROPERTY_WSDL_RESOURCE.equals(property)) {
				return serviceHelper.isWSDLResource(receiver);
			}
		}
		return false;
	}

}
