/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.core;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.SAXXMIHandler;

public class JEESAXXMLHandler extends SAXXMIHandler {

	public JEESAXXMLHandler(XMLResource xmiResource, XMLHelper helper,
			Map<?, ?> options) {
		super(xmiResource, helper, options);
	}

	@Override
	protected EPackage getPackageForURI(String uriString) {
		EPackage defPackage;
		// Grab the schema location because all JEE DD files share a common namespace
		if (urisToLocations == null) {//uriMap not initialized yet
			defPackage = super.getPackageForURI(uriString);
			if (urisToLocations == null)// if still not initialized.. then just return defPackage
				return defPackage;
		}
		URI uri = urisToLocations.get(uriString);
		String locString = (uri == null) ? uriString : uri.toString();
		EPackage ePackage = packageRegistry.getEPackage(locString);
		if (ePackage == null)
			return super.getPackageForURI(locString);
		else return ePackage;
		
	}

}
