/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.application.internal.util;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage;
import org.eclipse.jst.javaee.core.internal.util.JEEXMLHelperImpl;

public class EarXMLHelperImpl extends JEEXMLHelperImpl {

	public EarXMLHelperImpl(XMLResource resource) {
		super(resource);
	}

	
	@Override
	public String getURI(String prefix) {
		if (prefix != null && prefix.equals("")) //$NON-NLS-1$
			return ApplicationPackage.eNS_URI;
		else
			return super.getURI(prefix);
	}
	
	@Override
	protected String getQName(EPackage ePackage, String name, boolean mustHavePrefix)
	  {
	   String nsPrefix = getPrefix(ePackage, mustHavePrefix);
	// Not using EE5 package namespace - default jee5 namespace is used
	   if (nsPrefix.equals(ApplicationPackage.eNS_PREFIX)) 
	    {
	    	return name;
	    }
	    else return super.getQName(ePackage, name, mustHavePrefix);
	  }

	@Override
	public EStructuralFeature getFeature(EClass class1, String namespaceURI,
			String name, boolean isElement) {
		// If not found in current namespace package, try all of the known namespaces
		EStructuralFeature aFeature =  super.getFeature(class1, namespaceURI, name, isElement);
		if (aFeature == null && !ApplicationPackage.eNS_URI.equals(namespaceURI))
			aFeature =  super.getFeature(class1, ApplicationPackage.eNS_URI, name, isElement);
		if (aFeature == null && !ApplicationPackage.eNS_URI2.equals(namespaceURI))
			aFeature =  super.getFeature(class1, ApplicationPackage.eNS_URI2, name, isElement);
		if (aFeature == null && !ApplicationPackage.eNS_URI3.equals(namespaceURI))
			aFeature =  super.getFeature(class1, ApplicationPackage.eNS_URI3, name, isElement);
		return aFeature;
	}
}
