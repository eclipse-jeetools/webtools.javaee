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
/*
 * Created on Mar 18, 2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package org.eclipse.jst.j2ee.webapplication.internal.impl;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.common.internal.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.wst.common.internal.emf.resource.FileNameResourceFactoryRegistry;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;


/**
 * @author schacher
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class WebAppResourceFactory extends TranslatorResourceFactory {

	/**
	 * Method registerDtds.
	 */
	public static void registerDtds() {
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEBAPP_SYSTEMID_2_2, "web-app_2_2.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEBAPP_ALT_SYSTEMID_2_2, "web-app_2.2.dtd");	//$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEBAPP_SYSTEMID_2_3, "web-app_2_3.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEBAPP_ALT_SYSTEMID_2_3, "web-app_2.3.dtd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.WEB_APP_SCHEMA_LOC_2_4, "web-app_2_4.xsd"); //$NON-NLS-1$
		J2EEXmlDtDEntityResolver.registerDtD(J2EEConstants.JSP_SCHEMA_LOC_2_0, "jsp_2_0.xsd"); //$NON-NLS-1$
	}

	/**
	 * @param aRendererFactory
	 */
	public WebAppResourceFactory(RendererFactory aRendererFactory) {
		super(aRendererFactory);
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.TranslatorResourceFactory#createResource(org.eclipse.emf.common.util.URI, com.ibm.etools.emf2xml.Renderer)
	 */
	protected TranslatorResource createResource(URI uri, Renderer aRenderer) {
		return new WebAppResourceImpl(uri, aRenderer);
	}
	
	/**
	 * Register myself with the Resource.Factory.Registry
	 */
	public static void registerWith(RendererFactory aRendererFactory) {
		J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(J2EEConstants.WEBAPP_DD_SHORT_NAME, new WebAppResourceFactory(aRendererFactory));
	}
	
	/**
	 * register using the default renderer factory.
	 * @see #registerWith(RendererFactory)
	 */
	public static void register() {
		registerWith(RendererFactory.getDefaultRendererFactory());
	}

	/**
	 * register using the default renderer factory.
	 * @see #registerWith(RendererFactory)
	 */
	public static void register(FileNameResourceFactoryRegistry aRegistry) {
		aRegistry.registerLastFileSegment(J2EEConstants.WEBAPP_DD_SHORT_NAME, new WebAppResourceFactory(RendererFactory.getDefaultRendererFactory()));
	}
	
	public static Resource.Factory getRegisteredFactory() {
		return J2EEResourceFactoryRegistry.INSTANCE.getFactory(J2EEConstants.WEBAPP_DD_URI_OBJ);
	}

}
