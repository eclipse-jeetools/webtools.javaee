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
package org.eclipse.jst.j2ee.common.impl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.wst.common.internal.emf.resource.FileNameResourceFactoryRegistry;


public class J2EEResourceFactoryRegistry extends FileNameResourceFactoryRegistry {
	
	public static J2EEResourceFactoryRegistry INSTANCE = new J2EEResourceFactoryRegistry();

	public J2EEResourceFactoryRegistry() {
		super();
	}
	
	public Resource.Factory delegatedGetFactory(URI uri) {
		if (J2EEResourceFactoryRegistry.INSTANCE == this)
			return super.delegatedGetFactory(uri);
		return J2EEResourceFactoryRegistry.INSTANCE.getFactory(uri);	
	}
}
