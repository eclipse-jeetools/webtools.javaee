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
 * Created on Jun 11, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.commonarchivecore.internal.helpers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMLResource;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window>Preferences>Java>Code
 * Generation>Code and Comments
 */
final class ResourceProxyValidator {
	static HashMap resourceURIMap = new HashMap();

	private static void resolveContainedProxies(EObject refObject) {
		List contained = refObject.eContents();
		EObject mofObject;
		for (int i = 0; i < contained.size(); i++) {
			mofObject = (EObject) contained.get(i);
			resolveProxies(mofObject);
		}
	}

	private static void resolveNonContainedProxies(EObject refObject) {
		List references = refObject.eClass().getEAllReferences();
		EReference reference;
		EObject proxyOrObject;
		for (int i = 0; i < references.size(); i++) {
			reference = (EReference) references.get(i);
			if (!reference.isContainment() && !reference.isTransient()) {
				if (reference.isMany()) {
					Iterator value = ((List) refObject.eGet(reference)).iterator();
					while (value.hasNext()) {
						proxyOrObject = (EObject) value.next();
						if (proxyOrObject.eIsProxy())
							value.remove();
					}
				} else {
					proxyOrObject = (EObject) refObject.eGet(reference, false);
					if (proxyOrObject != null && proxyOrObject.eIsProxy()) {
						URI resourceURI = ((EObjectImpl) proxyOrObject).eProxyURI().trimFragment();
						String protocol = resourceURI.scheme();
						if (protocol == null || !protocol.equals("java")) { //$NON-NLS-1$
							String id = ((EObjectImpl) proxyOrObject).eProxyURI().fragment();
							if (resourceURIMap.get(resourceURI) != null) {
								Resource cachedResource = (Resource) resourceURIMap.get(resourceURI);
								proxyOrObject = (EObject) ((XMLResource) cachedResource).getIDToEObjectMap().get(id);
							} else {
								proxyOrObject = (EObject) refObject.eGet(reference);
								resourceURIMap.put(resourceURI, proxyOrObject.eResource());
							}
							if (proxyOrObject == null || proxyOrObject.eIsProxy())
								refObject.eSet(reference, null);
						}
					}
				}
			}
		}
	}

	private static void resolveProxies(EObject refObject) {
		if (refObject != null) {
			resolveNonContainedProxies(refObject);
			resolveContainedProxies(refObject);
		}
	}

	/**
	 * Force all of the proxies with <code>resource</code> to be resolved.
	 */
	static void checkForUnresolvableProxies(Resource resource) {
		resourceURIMap = new HashMap(); //Reset hashmap on each call
		if (resource != null) {
			List topLevels = resource.getContents();
			EObject mofObject;
			for (int i = 0; i < topLevels.size(); i++) {
				mofObject = (EObject) topLevels.get(i);
				resolveProxies(mofObject);
			}
		}
	}

}
