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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.internal.xml.J2EEXmlDtDEntityResolver;
import org.eclipse.jst.j2ee.webservice.WebServiceConstants;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.impl.JaxrpcmapResourceImpl;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.RendererFactory;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResource;
import org.eclipse.wst.common.internal.emf.resource.TranslatorResourceFactory;


public class JaxrpcmapResourceFactory extends TranslatorResourceFactory
{
  /**
  * JaxrpcmapResourceFactory constructor comment.
  */
  public JaxrpcmapResourceFactory(RendererFactory aRendererFactory) 
  {
	  super(aRendererFactory);
  }

	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.TranslatorResourceFactory#createResource(org.eclipse.emf.common.util.URI, com.ibm.etools.emf2xml.Renderer)
	 */
	protected TranslatorResource createResource(URI uri, Renderer aRenderer) {
		return new JaxrpcmapResourceImpl(uri, aRenderer);
	}

  /**
   * Register myself with the Resource.Factory.Registry
   */
  public static void registerWith(RendererFactory aRendererFactory) {
    //Do nothing since the jaxrpc mapping file has no standard name.
  	//J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(WebServiceConstants.WEBSERVICE_DD_SHORT_NAME, new WsddResourceFactory(aRendererFactory));
  }
	
	/**
	 * register using the default renderer factory.
	 * @see #registerWith(RendererFactory)
	 */
	public static void register() {
	    //Do nothing since the jaxrpc mapping file has no standard name.
		//registerWith(EMF2DOMRendererFactory.INSTANCE);
	}

  /**
   * Method registerDtds.
  */
  public static void registerDtds() 
  {
  	J2EEXmlDtDEntityResolver.registerDtD(WebServiceConstants.JAXRPCMAP_SYSTEMID, "j2ee_jaxrpc_mapping_1_0.dtd");//$NON-NLS-1$
	J2EEXmlDtDEntityResolver.registerDtD(WebServiceConstants.JAXRPCMAP_SCHEMA_LOC, "j2ee_jaxrpc_mapping_1_1.xsd"); //$NON-NLS-1$  	
	J2EEXmlDtDEntityResolver.registerDtD(WebServiceConstants.JAXRPCMAP_SCHEMA_LOC_SUN, "j2ee_jaxrpc_mapping_1_1.xsd"); //$NON-NLS-1$  			
  }

}
