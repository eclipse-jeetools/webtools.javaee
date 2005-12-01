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
package org.eclipse.jst.j2ee.common.internal.util;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationFactoryImpl;
import org.eclipse.jst.j2ee.client.internal.impl.ClientFactoryImpl;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.internal.MOFJ2EEResourceHandler;
import org.eclipse.jst.j2ee.jca.internal.impl.JcaFactoryImpl;
import org.eclipse.jst.j2ee.webapplication.internal.impl.WebapplicationFactoryImpl;


public class CommonUtil {
public static boolean isDeploymentDescriptorRoot(EObject o) {
	return isDeploymentDescriptorRoot(o, true);
}
public static boolean isDeploymentDescriptorRoot(EObject o, boolean includeEars) {
	EObject meta = o.eClass();
	return (includeEars && meta == eClassApplication()) ||
		meta == eClassApplicationClient() ||
		meta == eClassEJBJar() ||
		meta == eClassWebApp() ||
		meta == eClassConnector();
}
public static boolean isDeploymentDescriptorRoot(Object o) {
	return isDeploymentDescriptorRoot(o, true);
}
public static boolean isDeploymentDescriptorRoot(Object o, boolean includeEARs) {
	if (o instanceof EObject)
		return isDeploymentDescriptorRoot((EObject)o, includeEARs);
	return false;
}
protected static EClass eClassApplication() {
	return ApplicationFactoryImpl.getPackage().getApplication();
}
protected static EClass eClassApplicationClient() {
	return ClientFactoryImpl.getPackage().getApplicationClient();
}
protected static EClass eClassEJBJar() {
	return EjbFactoryImpl.getPackage().getEJBJar();
}
protected static EClass eClassWebApp() {
	return WebapplicationFactoryImpl.getPackage().getWebApp();
}

protected static EClass eClassConnector() {
	return JcaFactoryImpl.getPackage().getConnector();
}
/**
 * Safety check method to ensure that the version of the dd object can be computed.
 */
public static void checkDDObjectForVersion(EObject dd) {
	if (dd.eIsProxy()) {
		String proxyUri = ((InternalEObject)dd).eProxyURI().toString();
		String message = MOFJ2EEResourceHandler.getString(MOFJ2EEResourceHandler.J2EE_VERSION_PROXY_ERROR, new Object[]{proxyUri}); //$NON-NLS-1$
		throw new IllegalStateException(message);
	} else if (dd.eResource() == null) {
		String message = MOFJ2EEResourceHandler.J2EE_VERSION_NULL_ERROR; //$NON-NLS-1$
		throw new IllegalStateException(message);
	}
}

}







