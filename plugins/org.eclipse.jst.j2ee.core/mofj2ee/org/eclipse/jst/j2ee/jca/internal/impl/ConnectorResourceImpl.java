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
package org.eclipse.jst.j2ee.jca.internal.impl;


import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.common.internal.impl.XMLResourceImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.model.translator.connector.ConnectorTranslator;
import org.eclipse.jst.j2ee.jca.Connector;
import org.eclipse.jst.j2ee.jca.ConnectorResource;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;


public class ConnectorResourceImpl extends XMLResourceImpl implements ConnectorResource{

	/**
	 * Constructor for ApplicationClientResourceImpl.
	 * @param uri
	 * @param aRenderer
	 */
	public ConnectorResourceImpl(URI uri, Renderer aRenderer) {
		super(uri, aRenderer);
	}
	/**
	 * Constructor for ApplicationClientResourceImpl.
	 * @param aRenderer
	 */
	public ConnectorResourceImpl(Renderer aRenderer) {
		super(aRenderer);
	}

	/**
	 * Return the first element in the EList.
	 */
	public Connector getConnector() {
		return (Connector) getRootObject();
	}

	
	public int getType() {
		return RAR_TYPE;
	}


	
	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_2_SystemID()
	 */
	public String getJ2EE_1_2_SystemID() {
		return J2EEConstants.CONNECTOR_SYSTEMID_1_0;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_3_SystemID()
	 */
	public String getJ2EE_1_3_SystemID() {
		return J2EEConstants.CONNECTOR_SYSTEMID_1_0;
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_2_PublicID()
	 */
	public String getJ2EE_1_2_PublicID() {
		return J2EEConstants.CONNECTOR_PUBLICID_1_0;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_3_PublicID()
	 */
	public String getJ2EE_1_3_PublicID() {
		return J2EEConstants.CONNECTOR_PUBLICID_1_0;
	}


	public String getDoctype() {
		switch (getJ2EEVersionID()) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :	
				return J2EEConstants.CONNECTOR_DOCTYPE;
			default :
				return null;
		}
	}
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.TranslatorResource#setDoctypeValues(java.lang.String, java.lang.String)
	 * This is setting the module version on the resource.
	 */
	public void setDoctypeValues(String publicId, String systemId) {
		int version = JCA_1_5_ID;
		if (systemId == null) 
			version = JCA_1_5_ID;
		else if (systemId.equals(getJ2EE_1_3_SystemID()))
			version = JCA_1_0_ID;
		super.setDoctypeValues(publicId, systemId);
		super.setVersionID(version);
	}
	/* 
	 * This directly sets the module version id
	 */
	public void setModuleVersionID(int id) {
		super.setVersionID(id);
		switch (id) {
				case (JCA_1_5_ID) :
					super.setDoctypeValues(null, null);
					break;
				case (JCA_1_0_ID) :
					super.setDoctypeValues(getJ2EE_1_3_PublicID(), getJ2EE_1_3_SystemID());
			}
		syncVersionOfRootObject();
	}
		/*
		 * Based on the J2EE version, this will set the module version
		 */
		public void setJ2EEVersionID(int id) {
		switch (id) {
			case (J2EE_1_4_ID) :
						primSetDoctypeValues(null, null);
						primSetVersionID(JCA_1_5_ID);
						break;
			case (J2EE_1_3_ID) :
						primSetDoctypeValues(getJ2EE_1_3_PublicID(), getJ2EE_1_3_SystemID());
						primSetVersionID(JCA_1_0_ID);
						break;
			case (J2EE_1_2_ID) :
						primSetDoctypeValues(getJ2EE_1_2_PublicID(), getJ2EE_1_2_SystemID());
						primSetVersionID(JCA_1_0_ID);
				}
			syncVersionOfRootObject();
		}
	/**
	 * @see com.ibm.etools.emf2xml.TranslatorResource#getRootTranslator()
	 */
	public Translator getRootTranslator() {
		return ConnectorTranslator.INSTANCE;
	}
	/* Return J2EE version based on module version
	 */
	public int getJ2EEVersionID() {
		switch (getModuleVersionID()) {
			case J2EEVersionConstants.JCA_1_0_ID :
				return J2EEVersionConstants.J2EE_1_3_ID;
			case J2EEVersionConstants.JCA_1_5_ID :
				return J2EEVersionConstants.J2EE_1_4_ID;
			default :
			return J2EEVersionConstants.J2EE_1_4_ID;
		}
	}
	/* (non-Javadoc)
	 * @see com.ibm.etools.emf2xml.impl.TranslatorResourceImpl#getDefaultVersionID()
	 */
	protected int getDefaultVersionID() {
		return JCA_1_5_ID;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.impl.XMLResourceImpl#syncVersionOfRootObject()
	 */
	protected void syncVersionOfRootObject() {
		Connector conn = getConnector();
		if (conn == null)
			return;
	
		String version = conn.getSpecVersion();
		String newVersion = getModuleVersionString();
		if (!newVersion.equals(version))
			conn.setSpecVersion(newVersion);
	}

}
