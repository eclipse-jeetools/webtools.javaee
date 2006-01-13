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
package org.eclipse.jst.j2ee.model.internal.validation;

/**
 * Message key constants for J2EE validators.
 */
public interface J2EEMessageConstants {
	public static final String EREF_CATEGORY = "erefvalidation"; //$NON-NLS-1$
	public static final String WAR_CATEGORY = "warvalidation"; //$NON-NLS-1$
	public static final String EAR_CATEGORY = "earvalidation"; //$NON-NLS-1$
	public static final String EJBJAR_CATEGORY = "ejbjarvalidation"; //$NON-NLS-1$
	public static final String APLICATIONCLIENT_CATEGORY = "applicationclientvalidation"; //$NON-NLS-1$
	public static final String VALIDATOR_NAME = "validator.name"; //$NON-NLS-1$
	public static final String ERROR_EAR_MISSING_EREFNAME = "ERROR_EAR_MISSING_EREFNAME"; //$NON-NLS-1$
	public static final String ERROR_EAR_MISSING_EREFTYPE = "ERROR_EAR_MISSING_EREFTYPE"; //$NON-NLS-1$
	public static final String ERROR_EAR_INVALID_EREFTYPE = "ERROR_EAR_INVALID_EREFTYPE"; //$NON-NLS-1$
	public static final String ERROR_EAR_MISSING_EREFHOME = "ERROR_EAR_MISSING_EREFHOME"; //$NON-NLS-1$
	public static final String ERROR_EAR_MISSING_EREFREMOTE = "ERROR_EAR_MISSING_EREFREMOTE"; //$NON-NLS-1$
	public static final String ERROR_EAR_MISSING_EJB_ROLE = "ERROR_EAR_MISSING_EJB_ROLE"; //$NON-NLS-1$
	public static final String ERROR_EAR_DUPLICATE_RESREF = "ERROR_EAR_DUPLICATE_RESREF"; //$NON-NLS-1$
	public static final String ERROR_EAR_DUPLICATE_SERVICEREF = "ERROR_EAR_DUPLICATE_SERVICEREF"; //$NON-NLS-1$
	public static final String ERROR_EAR_DUPLICATE_SECURITYROLEREF = "ERROR_EAR_DUPLICATE_SECURITYROLEREF"; //$NON-NLS-1$
	public static final String ERROR_EAR_DUPLICATE_EJBREF = "ERROR_EAR_DUPLICATE_EJBREF"; //$NON-NLS-1$
	public static final String ERROR_EAR_DUPLICATE_RESENVREF = "ERROR_EAR_DUPLICATE_RESENVREF"; //$NON-NLS-1$
	public static final String ERROR_EAR_DUPLICATE_MESSSAGEDESTINATIONREF = "ERROR_EAR_DUPLICATE_MESSSAGEDESTINATIONREF"; //$NON-NLS-1$
	public static final String ERROR_EAR_MISSING_MESSSAGEDESTINATION = "ERROR_EAR_MISSING_MESSSAGEDESTINATION"; //$NON-NLS-1$	
	public static final String ERROR_EAR_MISSING_EMPTY_MESSSAGEDESTINATION = "ERROR_EAR_MISSING_EMPTY_MESSSAGEDESTINATION"; //$NON-NLS-1$	
	public static final String ERROR_UNRESOLVED_MDB_MISSING_MESSAGE_DESTINATION = "ERROR_UNRESOLVED_MDB_MISSING_MESSAGE_DESTINATION";//$NON-NLS-1$	
}
