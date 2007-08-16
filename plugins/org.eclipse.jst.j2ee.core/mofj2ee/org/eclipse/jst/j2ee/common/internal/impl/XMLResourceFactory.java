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
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.jst.j2ee.internal.common.XMLResource;


/**
 * @deprecated Since 4/22/2003
 * This is replaced with concrete implementers of {@link com.ibm.etools.emf2xml.impl.TranslatorResourceFactory}
 */

public abstract class XMLResourceFactory extends ResourceFactoryImpl {
/**
 * EJBJarResourceFactory constructor comment.
 */
public XMLResourceFactory() {
	//TODO: Delete this class
	super();
}

/**
 * @see org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl#createResource(URI)
 */
public final Resource createResource(URI uri) {
	XMLResource resource = primCreateResource(uri);
	resource.getDefaultSaveOptions().put(org.eclipse.emf.ecore.xmi.XMLResource.OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);
    resource.getDefaultSaveOptions().put(org.eclipse.emf.ecore.xmi.XMLResource.OPTION_LINE_WIDTH, new Integer(80));
    return resource;
}

/**
 * Method primCreateResource.
 * @param uri
 * @return XMLResource
 */
protected abstract XMLResource primCreateResource(URI uri) ;

}



