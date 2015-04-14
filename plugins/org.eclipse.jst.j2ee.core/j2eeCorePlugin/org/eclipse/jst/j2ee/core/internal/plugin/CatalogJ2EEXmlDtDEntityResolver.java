/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.core.internal.plugin;

import java.io.IOException;

import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.wst.common.uriresolver.internal.provisional.URIResolver;
import org.eclipse.wst.common.uriresolver.internal.provisional.URIResolverPlugin;
import org.xml.sax.InputSource;

public class CatalogJ2EEXmlDtDEntityResolver extends J2EEXmlDtDEntityResolver {

	private URIResolver uriResolver;

	public CatalogJ2EEXmlDtDEntityResolver() {
	}

	@Override
	public org.xml.sax.InputSource resolveEntity(String publicId, String systemId) throws IOException, org.xml.sax.SAXException {
		// The following flag and all its usage is to debug bugzilla 459564. Needs to be removed. 
		boolean debug = 
				"-//Sun Microsystems, Inc.//DTD J2EE Application 1.3//EN".equals(publicId)  //$NON-NLS-1$
				&& "http://java.sun.com/dtd/application_1_3.dtd".equals(systemId); //$NON-NLS-1$

		if (uriResolver == null) {
			uriResolver = URIResolverPlugin.createResolver();
		}
		
		if (debug){
			System.out.println("CatalogJ2EEXmlDtDEntityResolver - URI resolver for publicId and systemId: " + publicId + ", " + systemId + ": " + uriResolver); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		
		String uri = uriResolver.resolve(null, publicId, systemId);
		
		
		if (debug){
			System.out.println("CatalogJ2EEXmlDtDEntityResolver - URI resolved by " + uriResolver + " for publicId and systemId " + publicId + ", " + systemId + ": " + uri); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
		}
		
		if (null != uri) {
			InputSource result = new InputSource(uri);
			result.setPublicId(publicId);
			// force the encoding to be UTF8
			result.setEncoding("UTF-8"); //$NON-NLS-1$
			return result;
		}
		
		if (debug){
			System.out.println("CatalogJ2EEXmlDtDEntityResolver - Delegating URI resolution to parent for publicId and systemId: " + publicId + ", " + systemId); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		return super.resolveEntity(publicId, systemId);
	}

}
