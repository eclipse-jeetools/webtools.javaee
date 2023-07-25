/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.impl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.internal.impl.ApplicationResourceFactory;
import org.eclipse.jst.j2ee.client.internal.impl.ApplicationClientResourceFactory;
import org.eclipse.jst.j2ee.ejb.internal.impl.EJBJarResourceFactory;
import org.eclipse.jst.j2ee.internal.common.J2EEXMIResourceFactory;
import org.eclipse.jst.j2ee.jca.internal.impl.ConnectorResourceFactory;
import org.eclipse.jst.j2ee.webapplication.internal.impl.WebAppResourceFactory;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.WsddResourceFactory;
import org.eclipse.jst.j2ee.webservice.wsclient.internal.impl.WebServicesClientResourceFactory;
import org.eclipse.wst.common.internal.emf.resource.FileNameResourceFactoryRegistry;


public class J2EEResourceFactoryRegistry extends FileNameResourceFactoryRegistry {
	
	public static J2EEResourceFactoryRegistry INSTANCE = new J2EEResourceFactoryRegistry();

	static {
		EJBJarResourceFactory.register();
		WebAppResourceFactory.register();
		ApplicationClientResourceFactory.register();
		ApplicationResourceFactory.register();
		ConnectorResourceFactory.register();
		WebServicesClientResourceFactory.register();
		WsddResourceFactory.register();
		//register() is not called on the JaxrpcmapResourceFactory because
		//the jaxprc-mapping descriptor does not have a standard short name.
		//The short names have to be registered once they are known.
		J2EEXMIResourceFactory.register();
	}
	
	public J2EEResourceFactoryRegistry() {
		super();
	}
	
	@Override
	public Resource.Factory delegatedGetFactory(URI uri) {
		if (J2EEResourceFactoryRegistry.INSTANCE == this)
			return super.delegatedGetFactory(uri);
		return J2EEResourceFactoryRegistry.INSTANCE.getFactory(uri);	
	}
}
