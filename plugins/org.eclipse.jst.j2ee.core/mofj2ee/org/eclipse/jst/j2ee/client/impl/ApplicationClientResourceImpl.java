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
package org.eclipse.jst.j2ee.client.impl;



import org.eclipse.emf.common.util.URI;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.common.impl.XMLResourceImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.model.translator.client.ApplicationClientTranslator;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;

public class ApplicationClientResourceImpl extends XMLResourceImpl implements ApplicationClientResource {
	/**
	 * Constructor for ApplicationClientResourceImpl.
	 * @param uri
	 * @param aRenderer
	 */
	public ApplicationClientResourceImpl(URI uri, Renderer aRenderer) {
		super(uri, aRenderer);
	}

	/**
	 * Constructor for ApplicationClientResourceImpl.
	 * @param aRenderer
	 */
	public ApplicationClientResourceImpl(Renderer aRenderer) {
		super(aRenderer);
	}

	/**
	 * Return the first element in the EList.
	 */
	public ApplicationClient getApplicationClient() {
		return (ApplicationClient) getRootObject();
	}

	
	public int getType() {
		return APP_CLIENT_TYPE;
	}


	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_2_SystemID()
	 */
	public String getJ2EE_1_2_SystemID() {
		return J2EEConstants.APP_CLIENT_SYSTEMID_1_2;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_3_SystemID()
	 */
	public String getJ2EE_1_3_SystemID() {
		return J2EEConstants.APP_CLIENT_SYSTEMID_1_3;
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_2_PublicID()
	 */
	public String getJ2EE_1_2_PublicID() {
		return J2EEConstants.APP_CLIENT_PUBLICID_1_2;
	}
	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_3_PublicID()
	 */
	public String getJ2EE_1_3_PublicID() {
		return J2EEConstants.APP_CLIENT_PUBLICID_1_3;
	}


	public String getDoctype() {
		switch (getJ2EEVersionID()) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :	
				return J2EEConstants.APP_CLIENT_DOCTYPE;
			default :
				return null;
		}
	}
	
	/**
	 * @see com.ibm.etools.emf2xml.TranslatorResource#getRootTranslator()
	 */
	public Translator getRootTranslator() {
		return ApplicationClientTranslator.INSTANCE;
	}

	/* App client version is always the same as the J2EE version
	 */
	public int getJ2EEVersionID() {
		return getModuleVersionID();
	}
	/* 
	 * This directly sets the module version id
	 */
	public void setModuleVersionID(int id) {
		super.setVersionID(id);
		switch (id) {
				case (J2EE_1_4_ID) :
					super.setDoctypeValues(null, null);
					break;
				case (J2EE_1_3_ID) :
					super.setDoctypeValues(getJ2EE_1_3_PublicID(), getJ2EE_1_3_SystemID());
					break;
				case (J2EE_1_2_ID) :
					super.setDoctypeValues(getJ2EE_1_2_PublicID(), getJ2EE_1_2_SystemID());
			}
		syncVersionOfRootObject();
	}
		/*
		 * Based on the J2EE version, this will set the module version(Same as J2EE Version for App client)
		 */
		public void setJ2EEVersionID(int id) {
		primSetVersionID(id);
		switch (id) {
					case (J2EE_1_4_ID) :
						primSetDoctypeValues(null, null);
						break;
					case (J2EE_1_3_ID) :
						primSetDoctypeValues(getJ2EE_1_3_PublicID(), getJ2EE_1_3_SystemID());
						break;
					case (J2EE_1_2_ID) :
						primSetDoctypeValues(getJ2EE_1_2_PublicID(), getJ2EE_1_2_SystemID());
				}
			syncVersionOfRootObject();
		}
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.impl.XMLResourceImpl#syncVersionOfRootObject()
	 */
	protected void syncVersionOfRootObject() {
		ApplicationClient clt = getApplicationClient();
		if (clt == null)
			return;
	
		String version = clt.getVersion();
		String newVersion = getModuleVersionString();
		if (!newVersion.equals(version))
			clt.setVersion(newVersion);
	}


}
