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
package org.eclipse.jst.j2ee.application.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;


public class ApplicationResourceFactory extends TranslatorResourceFactory {

	/**
	 * Method registerDtds.
	 */
	public static void registerDtds() {
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.APPLICATION_SYSTEMID_1_2, "application_1_2.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.APPLICATION_SYSTEMID_1_3, "application_1_3.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.APPLICATION_SCHEMA_LOC_1_4, "application_1_4.xsd"); //$NON-NLS-1$
		// moved to the web svcs J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEB_SERVICES_CLIENT_SCHEMA_LOC_1_1, "j2ee_web_services_client_1_1.xsd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.J2EE_1_4_XSD_SHORT_NAME, J2EEConstants.J2EE_1_4_XSD_SHORT_NAME); 
	}

    /**
     * @param aRendererFactory
     */
    public ApplicationResourceFactory(RendererFactory aRendererFactory) {
        super(aRendererFactory);
    }

    /* (non-Javadoc)
     * @see com.ibm.etools.emf2xml.impl.TranslatorResourceFactory#createResource(org.eclipse.emf.common.util.URI, com.ibm.etools.emf2xml.Renderer)
     */
    protected TranslatorResource createResource(URI uri, Renderer aRenderer) {
		return new ApplicationResourceImpl(uri, aRenderer);
    }
	/**
	 * Register myself with the Resource.Factory.Registry
	 */
	public static void registerWith(RendererFactory aRendererFactory) {
		J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(J2EEConstants.APPLICATION_DD_SHORT_NAME, new ApplicationResourceFactory(aRendererFactory));
	}
	
	/**
	 * register using the default renderer factory.
	 * @see #registerWith(RendererFactory)
	 */
	public static void register() {
		registerWith(RendererFactory.getDefaultRendererFactory());
	}

	
	public static Resource.Factory getRegisteredFactory() {
		return J2EEResourceFactoryRegistry.INSTANCE.getFactory(J2EEConstants.APPLICATION_DD_URI_OBJ);
	}

}
