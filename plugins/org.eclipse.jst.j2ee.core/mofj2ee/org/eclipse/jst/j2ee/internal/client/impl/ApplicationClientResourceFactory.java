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
package org.eclipse.jst.j2ee.internal.client.impl;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.wst.common.internal.emf.resource.FileNameResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;


public class ApplicationClientResourceFactory extends TranslatorResourceFactory {

	/**
	 * Method registerDtds.
	 */
	public static void registerDtds() {
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.APP_CLIENT_SYSTEMID_1_2, "application-client_1_2.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.APP_CLIENT_SYSTEMID_1_3, "application-client_1_3.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.APP_CLIENT_SCHEMA_LOC_1_4, "application-client_1_4.xsd"); //$NON-NLS-1$
		
	}

	/**
	 * Constructor for ApplicationClientResourceFactory.
	 * @param aRendererFactory
	 */
	public ApplicationClientResourceFactory(RendererFactory aRendererFactory) {
		super(aRendererFactory);
	}

	/**
	 * @see com.ibm.etools.emf2xml.impl.TranslatorResourceFactory#createResource(URI, Renderer)
	 */
	protected TranslatorResource createResource(URI uri, Renderer aRenderer) {
		return new ApplicationClientResourceImpl(uri, aRenderer);
	}
	
	/**
	 * Register myself with the Resource.Factory.Registry
	 */
	public static void registerWith(RendererFactory aRendererFactory) {
		J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(J2EEConstants.APP_CLIENT_DD_SHORT_NAME, new ApplicationClientResourceFactory(aRendererFactory));
	}
	/**
	 * register using the default renderer factory.
	 * @see #registerWith(RendererFactory)
	 */
	public static void register() {
		registerWith(RendererFactory.getDefaultRendererFactory());
	}
	
	public static void register(FileNameResourceFactoryRegistry aRegistry) {
		aRegistry.registerLastFileSegment(J2EEConstants.APP_CLIENT_DD_SHORT_NAME, new ApplicationClientResourceFactory(RendererFactory.getDefaultRendererFactory()));
	}

	
	public static Resource.Factory getRegisteredFactory() {
		return J2EEResourceFactoryRegistry.INSTANCE.getFactory(J2EEConstants.APP_CLIENT_DD_URI_OBJ);
	}

}
