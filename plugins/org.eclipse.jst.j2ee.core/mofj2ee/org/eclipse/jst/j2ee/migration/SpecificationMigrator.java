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
package org.eclipse.jst.j2ee.migration;

import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.client.ApplicationClientResource;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.jca.ConnectorResource;
import org.eclipse.jst.j2ee.webapplication.WebAppResource;



/**
 * @author mdelder
 *
 */
public class SpecificationMigrator implements J2EEConstants, J2EESpecificationMigrationConstants {
	private boolean isComplex;
	private String version;
	private XMLResource xmlResource;
	
	/**
	 * Public constructor for migrating a single ejb
	 */
	public SpecificationMigrator(String aVersion, boolean complex) {
		version = aVersion;
		isComplex = complex;
	}
	
	/**
	 * Public constructor for migrating the entire deployment descriptor
	 */
	public SpecificationMigrator(XMLResource anXmlResource, String aVersion, boolean complex) {
		xmlResource	= anXmlResource;
		version = aVersion;
		isComplex = complex;
	}
	

	public J2EEMigrationStatus migrateTo13() {
		if (xmlResource != null && version != null) {
			switch (xmlResource.getType()) {
				case XMLResource.APP_CLIENT_TYPE :
					return migrateTo13((ApplicationClientResource)xmlResource);
				case XMLResource.APPLICATION_TYPE :
					return migrateTo13((ApplicationResource)xmlResource);
				case XMLResource.EJB_TYPE :
					return migrateTo13((EJBResource)xmlResource);
				case XMLResource.WEB_APP_TYPE :
					return migrateTo13((WebAppResource)xmlResource);
			}
		}	
		return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, xmlResource);
	}
	

	/**
     * @param resource
     * @return
     */
    protected J2EEMigrationStatus migrateTo13(WebAppResource resource) {
        return null;
    }

    /**
     * @param resource
     * @return
     */
    protected J2EEMigrationStatus migrateTo13(EJBResource resource) {
        return null;
    }

    /**
     * @param resource
     * @return
     */
    protected J2EEMigrationStatus migrateTo13(ApplicationResource resource) {
        return null;
    }

    /**
     * @param resource
     * @return
     */
    protected J2EEMigrationStatus migrateTo13(ApplicationClientResource resource) {
        return null;
    }

    public J2EEMigrationStatus migrateTo14() {
		if (xmlResource != null && version != null) {
			switch (xmlResource.getType()) {
				case XMLResource.APP_CLIENT_TYPE :
					return migrateTo14((ApplicationClientResource)xmlResource);
				case XMLResource.APPLICATION_TYPE :
					return migrateTo14((ApplicationResource)xmlResource);
				case XMLResource.EJB_TYPE :
					return migrateTo14((EJBResource)xmlResource);
				case XMLResource.WEB_APP_TYPE :
					return migrateTo14((WebAppResource)xmlResource);
				case XMLResource.RAR_TYPE :
					return migrateTo14 ((ConnectorResource)xmlResource);
			}
		}	
		return new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, xmlResource);
	}
	
	/**
     * @param resource
     * @return
     */
    protected J2EEMigrationStatus migrateTo14(ApplicationClientResource resource) {
        return null;
    }

    /**
     * @param resource
     * @return
     */
    protected J2EEMigrationStatus migrateTo14(ApplicationResource resource) {
        return null;
    }

    /**
     * @param resource
     * @return
     */
    protected J2EEMigrationStatus migrateTo14(EJBResource resource) {
        return null;
    }

    /**
     * @param resource
     * @return
     */
    protected J2EEMigrationStatus migrateTo14(WebAppResource resource) {
        return null;
    }

    /**
     * @param resource
     * @return
     */
    protected J2EEMigrationStatus migrateTo14(ConnectorResource resource) {
        return null;
    }

    protected boolean basicNeedsMigrationTo14() {
		boolean isResource1_4, isTarget1_4;
		isResource1_4 = xmlResource.getVersionID() == J2EEVersionConstants.J2EE_1_4_ID;
		isTarget1_4 = isVersion1_4();
		return (isResource1_4 && !isTarget1_4) || (!isResource1_4 && isTarget1_4);
	}
	
	protected boolean basicNeedsToMigrate() {
		boolean isResource1_3, isTarget1_3;
		isResource1_3 = xmlResource.getVersionID() == J2EEVersionConstants.J2EE_1_3_ID;
		isTarget1_3 = isVersion1_3();
		return (isResource1_3 && !isTarget1_3) || (!isResource1_3 && isTarget1_3);
	}
	
	protected J2EEMigrationStatus mergeStatuses(J2EEMigrationStatus status1, J2EEMigrationStatus status2) {
		if (status1 != null)
			return status1.append(status2);
		return status2;
	}
	
	
	protected boolean isVersion1_2() {
		return version == J2EE_VERSION_1_2;
	}

	protected boolean isVersion1_3() {
		return version == J2EE_VERSION_1_3;
	}
	
	protected boolean isVersion1_4() {
		return version == J2EE_VERSION_1_4;
	}

    /**
     * @return Returns the isComplex.
     */
    protected boolean isComplex() { 
        return isComplex;
    }
    /**
     * @return Returns the version.
     */
    public String getVersion() { 
        return version;
    }
    /**
     * @return Returns the xmlResource.
     */
    public XMLResource getXmlResource() { 
        return xmlResource;
    }
}
