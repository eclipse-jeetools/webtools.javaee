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
package org.eclipse.jst.j2ee.jca.impl;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;


public class ConnectorResourceFactory extends TranslatorResourceFactory {
	
	/**
	 * Method registerDtds.
	 */
	public static void registerDtds() {
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.CONNECTOR_SYSTEMID_1_0, "connector_1_0.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.CONNECTOR_SCHEMA_LOC_1_5, "connector_1_5.xsd"); //$NON-NLS-1$
	} 
 
	/**
	 * Constructor for ConnectorResourceFactory.
	 * @param aRendererFactory
	 */
	public ConnectorResourceFactory(RendererFactory aRendererFactory) {
		super(aRendererFactory);
	}
    /* (non-Javadoc)
     * @see com.ibm.etools.emf2xml.impl.TranslatorResourceFactory#createResource(org.eclipse.emf.common.util.URI, com.ibm.etools.emf2xml.Renderer)
     */
    protected TranslatorResource createResource(URI uri, Renderer aRenderer) {
		return new ConnectorResourceImpl(uri, aRenderer);
    }
    
	/**
	 * Register myself with the Resource.Factory.Registry
	 */
	public static void registerWith(RendererFactory aRendererFactory) {
		J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(J2EEConstants.RAR_SHORT_NAME, new ConnectorResourceFactory(aRendererFactory));
	}
	
	/**
	 * register using the default renderer factory.
	 * @see #registerWith(RendererFactory)
	 */
	public static void register() {
		registerWith(RendererFactory.getDefaultRendererFactory());
	}

	
	public static Resource.Factory getRegisteredFactory() {
		return J2EEResourceFactoryRegistry.INSTANCE.getFactory(J2EEConstants.RAR_DD_URI_OBJ);
	}

}
