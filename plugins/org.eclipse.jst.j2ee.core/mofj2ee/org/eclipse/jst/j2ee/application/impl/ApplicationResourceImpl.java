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
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.common.impl.XMLResourceImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.model.translator.application.ApplicationTranslator;
import org.eclipse.wst.common.internal.emf.resource.Renderer;
import org.eclipse.wst.common.internal.emf.resource.Translator;


public class ApplicationResourceImpl extends XMLResourceImpl implements ApplicationResource {
    /**
     * @param uri
     * @param aRenderer
     */
    public ApplicationResourceImpl(URI uri, Renderer aRenderer) {
        super(uri, aRenderer);
       
    }

    /**
     * @param aRenderer
     */
    public ApplicationResourceImpl(Renderer aRenderer) {
        super(aRenderer);
    }
	/**
	* Return the first element in the EList.	 */
	public Application getApplication() {
		return (Application) getRootObject();
	}


	public int getType() {
		return APPLICATION_TYPE;
	}


    public String getDoctype() {
    	switch (getJ2EEVersionID()) {
			case (J2EE_1_2_ID) :
			case (J2EE_1_3_ID) :	
				return J2EEConstants.APPLICATION_DOCTYPE;
			default :
				return null;
		}
		
    }
	/* App version is always the same as the J2EE version
	 */
	public int getJ2EEVersionID() {
		return getModuleVersionID();
	}
    public Translator getRootTranslator() {
		return ApplicationTranslator.INSTANCE;
    }
    
    /* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_2_SystemID()
	 */
	public String getJ2EE_1_2_SystemID() {
		return J2EEConstants.APPLICATION_SYSTEMID_1_2;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_3_SystemID()
	 */
	public String getJ2EE_1_3_SystemID() {
		return J2EEConstants.APPLICATION_SYSTEMID_1_3;
	}

	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_2_PublicID()
	 */
	public String getJ2EE_1_2_PublicID() {
		return J2EEConstants.APPLICATION_PUBLICID_1_2;
	}
	
	/* (non-Javadoc)
	 * @see com.ibm.etools.j2eexml.XMLResourceImpl#getJ2EE_1_3_PublicID()
	 */
	public String getJ2EE_1_3_PublicID() {
		return J2EEConstants.APPLICATION_PUBLICID_1_3;
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
		 * Based on the J2EE version, this will set the module version(Same as J2EE Version for Application)
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
		Application app = getApplication();
		if (app == null)
			return;
		
		String version = app.getVersion();
		String newVersion = getModuleVersionString();
		if (!newVersion.equals(version))
			app.setVersion(newVersion);
	}


}
