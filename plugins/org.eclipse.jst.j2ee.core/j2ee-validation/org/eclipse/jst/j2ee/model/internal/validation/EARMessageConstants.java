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
 * Message key constants for WAR validation.
 */
public interface EARMessageConstants extends J2EEMessageConstants {
	public static final String EAR_VALIDATOR_ID = "EAR_VALIDATOR"; //$NON-NLS-1$
	public static final String EAR_MODEL_NAME = "EAR_VALIDATION"; //$NON-NLS-1$
	public static final String ERROR_EAR_VALIDATION_FAILED = "ERROR_EAR_VALIDATION_FAILED"; //$NON-NLS-1$
	public static final String ERROR_EAR_INVALID_EAR_FILE = "ERROR_EAR_INVALID_EAR_FILE"; //$NON-NLS-1$
	public static final String ERROR_EAR_DUPLICATE_ROLES = "ERROR_EAR_DUPLICATE_ROLES"; //$NON-NLS-1$
	public static final String MESSAGE_EAR_NO_MODULE_URI = "MESSAGE_EAR_NO_MODULE_URI"; //$NON-NLS-1$
	public static final String MESSAGE_EAR_MISSING_URI = "MESSAGE_EAR_MISSING_URI"; //$NON-NLS-1$
	public static final String EAR_DD_PARSE_LINECOL = "EAR_DD_PARSE_LINECOL"; //$NON-NLS-1$
	
	public static final String MODULE_DD_PARSE_LINECOL = "MODULE_DD_PARSE_LINECOL_ERROR_"; //$NON-NLS-1$
	public static final String MODULE_DD_PARSE_NOINFO = "MODULE_DD_PARSE_NOINFO_ERROR_"; //$NON-NLS-1$
	public static final String MODULE_DD_PARSE_LINE = "MODULE_DD_PARSE_LINE_ERROR_"; //$NON-NLS-1$
	public static final String EAR_DD_PARSE_LINE = "EAR_DD_PARSE_LINE"; //$NON-NLS-1$
	public static final String EAR_DD_CANNOT_OPEN_DD = "EAR_DD_CANNOT_OPEN_DD"; //$NON-NLS-1$
	public static final String EAR_DD_PARSE_NOINFO = "EAR_DD_PARSE_NOINFO"; //$NON-NLS-1$
	
	public static final String MESSAGE_EAR_DUPLICATE_URI_ERROR_ = "MESSAGE_EAR_DUPLICATE_URI_ERROR_"; //$NON-NLS-1$
	public static final String MESSAGE_INVALID_ALT_DD_WARN_ = "MESSAGE_INVALID_ALT_DD_WARN_"; //$NON-NLS-1$
	public static final String MESSAGE_EMPTY_ALT_DD_ERROR_ = "MESSAGE_EMPTY_ALT_DD_ERROR_"; //$NON-NLS-1$
	public static final String UNRESOLVED_EJB_REF_WARN_= "UNRESOLVED_EJB_REF_WARN_"; //$NON-NLS-1$
	public static final String ERROR_READING_MANIFEST_ERROR_ = "ERROR_READING_MANIFEST_ERROR_"; //$NON-NLS-1$
	public static final String MANIFEST_LINE_EXCEEDS_LENGTH_ERROR_ = "MANIFEST_LINE_EXCEEDS_LENGTH_ERROR_"; //$NON-NLS-1$
	public static final String MANIFEST_LINE_END_ERROR_ = "MANIFEST_LINE_END_ERROR_"; //$NON-NLS-1$
	public static final String MESSAGE_EAR_DUPICATE_ROOTCONTEXT_ERROR_ = "MESSAGE_EAR_DUPICATE_ROOTCONTEXT_ERROR_"; //$NON-NLS-1$
	public static final String URI_CONTAINS_SPACES_ERROR_ = "URI_CONTAINS_SPACES_ERROR_"; //$NON-NLS-1$
	public static final String MESSAGE_INCOMPATIBLE_SPEC_WARNING_ = "MESSAGE_INCOMPATIBLE_SPEC_WARNING_"; //$NON-NLS-1$
	public static final String EJB_BEAN_EJB_LINK_INTEFACE_MISMATCH_ERROR_ = "EJB_BEAN_EJB_LINK_INTEFACE_MISMATCH_ERROR_"; //$NON-NLS-1$
	public static final String EAR_VALIDATION_INTERNAL_ERROR_UI_ ="EAR_VALIDATION_INTERNAL_ERROR_UI_"; //$NON-NLS-1$
	
}
