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
package org.eclipse.jst.j2ee.common;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.common.J2EEXMIResource;
import org.eclipse.jst.j2ee.internal.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emf.resource.CompatibilityResourceFactory;




/**
 * @author nssheth
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class J2EEXMIResourceFactory extends CompatibilityResourceFactory {

	public static J2EEXMIResourceFactory INSTANCE = new J2EEXMIResourceFactory();
	
	public static void register() {
		J2EEResourceFactoryRegistry.INSTANCE.getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, INSTANCE);
	}

	/**
	 * Constructor for J2EEXMIResourceFactory.
	 */
	public J2EEXMIResourceFactory() {
		super();
	}

	/**
	 * @see org.eclipse.emf.ecore.resource.Resource.Factory#createResource(URI)
	 */
	public Resource createResource(URI uri) {
		J2EEXMIResource resource = new J2EEXMIResource(uri);
		return resource;
	}
}
