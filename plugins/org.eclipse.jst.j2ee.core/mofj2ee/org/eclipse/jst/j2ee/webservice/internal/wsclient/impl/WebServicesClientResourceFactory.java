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
/*
 * Created on Aug 6, 2003
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.webservice.internal.wsclient.impl;

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


public class WebServicesClientResourceFactory
    extends TranslatorResourceFactory {
		/**
		 * Method registerDtds.
		 */
		public static void registerDtds() {
			J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEB_SERVICES_CLIENT_SYSTEMID_1_0, "j2ee_web_services_client_1_0.dtd"); //$NON-NLS-1$
			J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEB_SERVICES_CLIENT_SCHEMA_LOC_1_1, "j2ee_web_services_client_1_1.xsd"); //$NON-NLS-1$
		}
    /**
     * @param aRendererFactory
     */
    public WebServicesClientResourceFactory(RendererFactory aRendererFactory) {
        super(aRendererFactory);
    }

	protected TranslatorResource createResource(URI uri, Renderer aRenderer) {
		return new WebServicesResourceImpl(uri, aRenderer);
	}
	  public static void register(FileNameResourceFactoryRegistry aRegistry) {
			aRegistry.registerLastFileSegment(J2EEConstants.WEB_SERVICES_CLIENT_SHORTNAME, new WebServicesClientResourceFactory(RendererFactory.getDefaultRendererFactory()));
	  }
    
	/**
	 * Register myself with the Resource.Factory.Registry
	 */
	public static void registerWith(RendererFactory aRendererFactory) {
		J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(J2EEConstants.WEB_SERVICES_CLIENT_SHORTNAME, new WebServicesClientResourceFactory(aRendererFactory));
	}
	
	/**
	 * register using the default renderer factory.
	 * @see #registerWith(RendererFactory)
	 */
	public static void register() {
		registerWith(RendererFactory.getDefaultRendererFactory());
	}

	
	public static Resource.Factory getRegisteredFactory() {
		return J2EEResourceFactoryRegistry.INSTANCE.getFactory(J2EEConstants.WEB_SERVICES_CLIENT_DD_URI_OBJ);
	}
    

}
