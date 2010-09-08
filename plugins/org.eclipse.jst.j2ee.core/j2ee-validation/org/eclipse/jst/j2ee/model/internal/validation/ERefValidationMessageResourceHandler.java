/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;


import org.eclipse.osgi.util.NLS;

public class ERefValidationMessageResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.j2ee.core.internal.plugin.erefvalidation";//$NON-NLS-1$

	private ERefValidationMessageResourceHandler() {
		// Do not instantiate
	}

	public static String ERROR_EAR_MISSING_EREFNAME;
	public static String ERROR_EAR_INVALID_EREFTYPE;
	public static String ERROR_EAR_MISSING_EREFTYPE;
	public static String ERROR_EAR_MISSING_EREFHOME;
	public static String ERROR_EAR_MISSING_EREFREMOTE;
	public static String ERROR_EAR_MISSING_EJB_ROLE;
	public static String ERROR_EAR_DUPLICATE_RESREF;
	public static String UNRESOLVED_EJB_REF_WARN_;
	public static String ERROR_EAR_DUPLICATE_SERVICEREF;
	public static String ERROR_EAR_DUPLICATE_SECURITYROLEREF;
	public static String ERROR_EAR_DUPLICATE_EJBREF;
	public static String ERROR_EAR_DUPLICATE_RESENVREF;
	public static String ERROR_EAR_DUPLICATE_MESSSAGEDESTINATIONREF;
	public static String ERROR_EAR_MISSING_MESSSAGEDESTINATION;
	public static String ERROR_UNRESOLVED_MDB_MISSING_MESSAGE_DESTINATION;
	public static String ERROR_EAR_MISSING_EMPTY_MESSSAGEDESTINATION;
	public static String ERROR_MODULE_DD_FILE_NOT_FOUND;

	

	static {
		NLS.initializeMessages(BUNDLE_NAME, ERefValidationMessageResourceHandler.class);
	}	

}
