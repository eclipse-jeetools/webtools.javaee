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


package org.eclipse.jst.j2ee.webservice.internal;

import org.eclipse.jst.j2ee.internal.J2EEInit;
import org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap.impl.JaxrpcmapPackageImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapResourceFactory;



/**
* Initializer class to preregister packages, register resource factories, and dtds for the web services models.
*/
public class WebServiceInit
{
  protected static boolean initialized = false;

  public static void init()
  {

    if (initialized) return;
 

    // Initialize the Ecore package.
    org.eclipse.emf.ecore.impl.EcorePackageImpl.init();

    J2EEInit.init();    

    // Initialize packages
    JaxrpcmapPackageImpl.init();


    // Register resource factories for xml descriptors


    // Not calling JaxrpcmapResourceFactory.register() because it does nothing. Registration must be done for each filename as follows:
    // J2EEResourceFactoryRegistry.INSTANCE.registerLastFileSegment(filename, new JaxrpcmapResourceFactory(EMF2DOMRendererFactory.INSTANCE));  
    // where filename is a java.lang.String name of the mapping file

    //Register DTDs
    JaxrpcmapResourceFactory.registerDtds();


    initialized = true;


  }


}
