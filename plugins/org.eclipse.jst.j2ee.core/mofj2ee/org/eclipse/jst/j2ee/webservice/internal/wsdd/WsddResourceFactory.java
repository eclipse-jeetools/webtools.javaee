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
package org.eclipse.jst.j2ee.webservice.internal.wsdd;


import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.internal.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.jst.j2ee.webservice.internal.WebServiceConstants;
import org.eclipse.jst.j2ee.webservice.internal.wsdd.impl.WsddResourceImpl;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;


public class WsddResourceFactory extends TranslatorResourceFactory
{
  /**
  * WsddResourceFactory constructor comment.
  */
  public WsddResourceFactory(RendererFactory aRendererFactory) 
  {
	  super(aRendererFactory);
  }
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.TranslatorResourceFactory#createResource(org.eclipse.emf.common.util.URI, com.ibm.etools.emf2xml.Renderer)
	 */
	protected TranslatorResource createResource(URI uri, Renderer aRenderer) {
		return new WsddResourceImpl(uri, aRenderer);
	}

  /**
   * Register myself with the Resource.Factory.Registry
   */
  public static void registerWith(RendererFactory aRendererFactory) {
  	J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(WebServiceConstants.WEBSERVICE_DD_SHORT_NAME, new WsddResourceFactory(aRendererFactory));
  	J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(WebServiceConstants.WEBSERVICE_TEMP_DD_SHORT_NAME, new WsddResourceFactory(aRendererFactory));
  }
	
  /**
   * register using the default renderer factory.
   * @see #registerWith(RendererFactory)
   */
  public static void register() {
  	registerWith(RendererFactory.getDefaultRendererFactory());
  }

  /**
   * Method registerDtds.
  */
  public static void registerDtds() 
  {
    //http://www.ibm.com/webservices/dtd/j2ee_web_services_1_0.dtd
  	J2EEXmlDtDEntityResolver.registerDtD(WebServiceConstants.WEBSERVICE_SYSTEMID, "j2ee_web_services_1_0.dtd");//$NON-NLS-1$
	J2EEXmlDtDEntityResolver.registerDtD(WebServiceConstants.WEBSERVICE_SCHEMA_LOC, "j2ee_web_services_1_1.xsd"); //$NON-NLS-1$
    J2EEXmlDtDEntityResolver.registerDtD(WebServiceConstants.WEBSERVICE_SCHEMA_LOC_SUN, "j2ee_web_services_1_1.xsd"); //$NON-NLS-1$	
  }
}
