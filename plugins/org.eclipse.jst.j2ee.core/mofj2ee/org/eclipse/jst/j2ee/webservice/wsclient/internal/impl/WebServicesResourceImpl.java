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
package org.eclipse.jst.j2ee.webservice.wsclient.internal.impl;


import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.common.internal.impl.XMLResourceImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.model.translator.webservices.WebServicesTranslator;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesClient;
import org.eclipse.jst.j2ee.webservice.wsclient.WebServicesResource;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class WebServicesResourceImpl extends XMLResourceImpl implements WebServicesResource {

    /**
     * @param uri
     * @param aRenderer
     */
    public WebServicesResourceImpl(URI uri, Renderer aRenderer) {
        super(uri, aRenderer);
    }

    /**
     * @param aRenderer
     */
    public WebServicesResourceImpl(Renderer aRenderer) {
        super(aRenderer);
    }

		/**
	 * Return the first element in the EList.
	 */
	public WebServicesClient getWebServicesClient() {
		return (WebServicesClient) getRootObject();
	}
    
    public int getType() {
        return WEB_SERVICES_CLIENT_TYPE;
    }

	public String getJ2EE_1_2_SystemID() {
		return J2EEConstants.WEB_SERVICES_CLIENT_SYSTEMID_1_0;
	}
	public String getJ2EE_1_3_SystemID() {
		return J2EEConstants.WEB_SERVICES_CLIENT_SYSTEMID_1_0;
	}

	public String getJ2EE_1_2_PublicID() {
		return J2EEConstants.WEB_SERVICES_CLIENT_PUBLICID_1_0;
	}
	public String getJ2EE_1_3_PublicID() {
		return J2EEConstants.WEB_SERVICES_CLIENT_PUBLICID_1_0;
	}

	public String getDoctype() {
		switch (getJ2EEVersionID()) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :	
				return J2EEConstants.WEB_SERVICES_CLIENT_DOCTYPE;
			default :
				return null;
		}
	}
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.TranslatorResource#setDoctypeValues(java.lang.String, java.lang.String)
	 * This is setting the module version on the resource.
	 */
	public void setDoctypeValues(String publicId, String systemId) {
		int version = WEBSERVICES_1_0_ID;
		super.setDoctypeValues(publicId, systemId);
		super.setVersionID(version);
	}
		/* 
	 * This directly sets the module version id
	 */
	public void setModuleVersionID(int id) {
		super.setVersionID(id);
		switch (id) {
				case (WEBSERVICES_1_0_ID) :
					super.setDoctypeValues(getJ2EE_1_3_PublicID(), getJ2EE_1_3_SystemID());
			}
		syncVersionOfRootObject();
	}
	/*
	 * Based on the J2EE version, this will set the module version
	 */
	public void setJ2EEVersionID(int id) {
	switch (id) {
		case (J2EE_1_3_ID) :
					primSetDoctypeValues(getJ2EE_1_3_PublicID(), getJ2EE_1_3_SystemID());
					primSetVersionID(WEBSERVICES_1_0_ID);
					break;
		case (J2EE_1_2_ID) :
					primSetDoctypeValues(getJ2EE_1_2_PublicID(), getJ2EE_1_2_SystemID());
					primSetVersionID(WEBSERVICES_1_0_ID);
			}
		syncVersionOfRootObject();
	}
	/* Return J2EE version based on module version
	 */
	public int getJ2EEVersionID() {
		switch (getModuleVersionID()) {
			case J2EEVersionConstants.WEBSERVICES_1_0_ID :
				return J2EEVersionConstants.J2EE_1_3_ID;
			default :
				return J2EEVersionConstants.J2EE_1_3_ID;
		}
	}
	/**
	 * @see com.ibm.etools.emf2xml.TranslatorResource#getRootTranslator()
	 */
	public Translator getRootTranslator() {
		return WebServicesTranslator.INSTANCE;
	}
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.TranslatorResourceImpl#getDefaultVersionID()
	 */
	protected int getDefaultVersionID() {
		return WEBSERVICES_1_0_ID;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.impl.XMLResourceImpl#syncVersionOfRootObject()
	 */
	protected void syncVersionOfRootObject() {
		//not model for J2EE 1.4 so no operation required.
	}

}
