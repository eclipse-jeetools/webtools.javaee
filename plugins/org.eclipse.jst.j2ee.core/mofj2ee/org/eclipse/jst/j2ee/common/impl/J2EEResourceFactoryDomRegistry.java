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
 * Created on May 20, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.common.impl;

import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.application.impl.ApplicationResourceFactory;
import org.eclipse.jst.j2ee.client.impl.ApplicationClientResourceFactory;
import org.eclipse.jst.j2ee.ejb.impl.EJBJarResourceFactory;
import org.eclipse.jst.j2ee.jca.impl.ConnectorResourceFactory;
import org.eclipse.jst.j2ee.webapplication.impl.WebAppResourceFactory;
import org.eclipse.jst.j2ee.webservice.WebServiceConstants;
import org.eclipse.jst.j2ee.webservice.wsclient.impl.WebServicesClientResourceFactory;
import org.eclipse.jst.j2ee.webservice.wsdd.WsddResourceFactory;
import org.eclipse.wst.common.internal.emf.resource.EMF2DOMRendererFactory;
import org.eclipse.wst.common.internal.emf.resource.EMF2SAXRendererFactory;



/**
 * @author mdelder
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class J2EEResourceFactoryDomRegistry
        extends J2EEResourceFactoryRegistry {

    /**
     * 
     */
    public J2EEResourceFactoryDomRegistry() {
        super();
        initRegistration();
    }
    
    private void initRegistration() {
    	EMF2SAXRendererFactory.INSTANCE.setValidating(false);
        registerLastFileSegment(J2EEConstants.EJBJAR_DD_SHORT_NAME, new EJBJarResourceFactory(EMF2DOMRendererFactory.INSTANCE));
        registerLastFileSegment(J2EEConstants.WEBAPP_DD_SHORT_NAME, new WebAppResourceFactory(EMF2DOMRendererFactory.INSTANCE));
        registerLastFileSegment(J2EEConstants.APP_CLIENT_DD_SHORT_NAME, new ApplicationClientResourceFactory(EMF2DOMRendererFactory.INSTANCE));
        registerLastFileSegment(J2EEConstants.APPLICATION_DD_SHORT_NAME, new ApplicationResourceFactory(EMF2DOMRendererFactory.INSTANCE));
        registerLastFileSegment(J2EEConstants.RAR_SHORT_NAME, new ConnectorResourceFactory(EMF2DOMRendererFactory.INSTANCE));
        registerLastFileSegment(J2EEConstants.WEB_SERVICES_CLIENT_SHORTNAME, new WebServicesClientResourceFactory(EMF2DOMRendererFactory.INSTANCE));
        registerLastFileSegment(WebServiceConstants.WEBSERVICE_DD_SHORT_NAME, new WsddResourceFactory(EMF2DOMRendererFactory.INSTANCE));
	}

}
