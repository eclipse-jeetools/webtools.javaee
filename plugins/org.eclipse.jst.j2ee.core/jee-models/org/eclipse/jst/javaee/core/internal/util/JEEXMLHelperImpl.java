/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core.internal.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLHelperImpl;
import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

public class JEEXMLHelperImpl extends XMLHelperImpl {

	public JEEXMLHelperImpl(XMLResource resource) {
		super(resource);
	}



	@Override
	public EStructuralFeature getFeature(EClass class1, String namespaceURI,
			String name, boolean isElement) {
		// If not found in current namespace package, go to common package
		EStructuralFeature aFeature =  super.getFeature(class1, namespaceURI, name, isElement);
		if (aFeature == null)
			aFeature =  super.getFeature(class1, JavaeePackage.eNS_URI, name, isElement);
		if (aFeature == null)
			aFeature =  super.getFeature(class1, JavaeePackage.eNS_URI2, name, isElement);
	return aFeature;
	}

}
