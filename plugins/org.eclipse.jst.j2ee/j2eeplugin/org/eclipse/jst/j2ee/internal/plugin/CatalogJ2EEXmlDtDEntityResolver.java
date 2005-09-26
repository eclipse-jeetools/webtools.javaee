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
package org.eclipse.jst.j2ee.internal.plugin;

import java.io.IOException;

import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.wst.common.uriresolver.internal.provisional.URIResolver;
import org.eclipse.wst.common.uriresolver.internal.provisional.URIResolverPlugin;
import org.xml.sax.InputSource;

public class CatalogJ2EEXmlDtDEntityResolver extends J2EEXmlDtDEntityResolver {

	private URIResolver uriResolver;

	public CatalogJ2EEXmlDtDEntityResolver() {
	}

	public org.xml.sax.InputSource resolveEntity(String publicId, String systemId) throws IOException, org.xml.sax.SAXException {
		if (uriResolver == null) {
			uriResolver = URIResolverPlugin.createResolver();
		}
		String uri = uriResolver.resolve(null, publicId, systemId);
		if (null != uri) {
			InputSource result = new InputSource(uri);
			result.setPublicId(publicId);
			// force the encoding to be UTF8
			result.setEncoding("UTF-8"); //$NON-NLS-1$
			return result;
		}
		return super.resolveEntity(publicId, systemId);
	}

}
