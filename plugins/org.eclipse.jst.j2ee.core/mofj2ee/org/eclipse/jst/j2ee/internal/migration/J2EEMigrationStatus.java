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
package org.eclipse.jst.j2ee.internal.migration;

import java.text.MessageFormat;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.common.XMLResource;


/**
 * @author DABERG
 *
 */
public class J2EEMigrationStatus implements J2EESpecificationMigrationConstants { 
    
	public static final int COMPLETED_OK = 0;
	public static final int NOT_NEEDED = 1;
	public static final int NOT_POSSIBLE = 2;
	public static final int WARNING = 3;
	public static final int ERROR = 4; 

    public static final J2EEMigrationStatus OK_STATUS = new J2EEMigrationStatus(COMPLETED_OK, "");

	private static final J2EEMigrationStatus[] EmptyStatusArray = new J2EEMigrationStatus[0];

	private int severity;
	private String message;
	private XMLResource resource;
	private EObject targetObject;
	
	J2EEMigrationStatus() {
	}

	public J2EEMigrationStatus(int aSeverity, String aMessage) {
		this(aSeverity, (EObject)null, aMessage);
	}
	public J2EEMigrationStatus(int aSeverity, XMLResource xmlResource) {
		this(aSeverity, xmlResource, null);
	}
	
	public J2EEMigrationStatus(int aSeverity, EObject anObject) {
		this(aSeverity, anObject, null);
	}

	public J2EEMigrationStatus(int aSeverity, XMLResource xmlResource, String aMessage) {
		severity = aSeverity;
		resource = xmlResource;
		message = aMessage;
	}
	
	public J2EEMigrationStatus(int aSeverity, EObject anObject, String aMessage) {
		severity = aSeverity;
		targetObject = anObject;
		message = aMessage;
	}
	
	protected String format(String aPattern, String arg1) {
		return MessageFormat.format(aPattern, new String[]{arg1});
	}
	
	/**
	 * Returns the message.
	 * @return String
	 */
	public String getMessage() {
		if (message == null)
			message = createDefaultMessage();
		return message;
	}
	
	public String getMessageForDisplay() {
		return getSeverityText()+" "+getMessage(); //$NON-NLS-1$
	}
	
	protected String getSeverityText() {
		switch (severity) {
			case ERROR:
				return ERROR_TEXT;
			case WARNING:
				return WARNING_TEXT;
			case NOT_NEEDED:
			case NOT_POSSIBLE:
				return INFO_TEXT;
			default:
				return ""; //$NON-NLS-1$
		}
	}

	/**
	 * Method createDefaultMessage.
	 * @return String
	 */
	private String createDefaultMessage() {
		String objDesc;
		if (getResource() != null)
			objDesc = getResource().getURI().toString();
		else if (getTargetObject() != null && getTargetObject() instanceof ENamedElement)
			objDesc = ((ENamedElement) getTargetObject()).getName();
		else
			return null;
		switch (getSeverity()) {
			case COMPLETED_OK :
				return format(DEFAULT_COMPLETED_STATUS_MSG, objDesc);
			case NOT_NEEDED :
				return format(DEFAULT_NOT_NEEDED_STATUS_MSG, objDesc);
			case NOT_POSSIBLE :
				return format(DEFAULT_NOT_POSSIBLE_STATUS_MSG, objDesc);
			case ERROR :
				return format(DEFAULT_ERROR_STATUS_MSG, objDesc);
		}
		return null;
	}


	/**
	 * Sets the message.
	 * @param message The message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * The migration was completed fine or was not needed.
	 */
	public boolean isOK() {
		return severity == COMPLETED_OK || severity == NOT_NEEDED;
	}

	/**
	 * An error ocurred during migration.
	 */
	public boolean isError() {
		return severity == ERROR;
	}
	
	/**
	 * A warning ocurred during migration.
	 */
	public boolean isWarning() {
		return severity == WARNING;
	}

	/**
	 * The migration was not required.
	 */
	public boolean isNotNeeded() {
		return severity == NOT_NEEDED;
	}
	/**
	 * The migration was not possible.
	 */
	public boolean isNotPossible() {
		return severity == NOT_POSSIBLE;
	}
	/**
	 * Returns the severity.
	 * @return int
	 */
	public int getSeverity() {
		return severity;
	}

	/**
	 * Sets the severity.
	 * @param severity The severity to set
	 */
	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public J2EEMigrationStatus[] getChildren() {
		return EmptyStatusArray;
	}

	public boolean isMultiStatus() {
		return false;
	}
	public XMLResource getResource() {
		return resource;
	}
	public EObject getTargetObject() {
		return targetObject;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("MigrationStatus "); //$NON-NLS-1$
		if (severity == COMPLETED_OK) {
			buf.append("Completed OK"); //$NON-NLS-1$
		} else if (severity == ERROR) {
			buf.append("ERROR"); //$NON-NLS-1$
		} else if (severity == WARNING) {
			buf.append("WARNING"); //$NON-NLS-1$
		} else if (severity == NOT_NEEDED) {
			buf.append("Not Needed"); //$NON-NLS-1$
		} else if (severity == NOT_POSSIBLE) {
			buf.append("Not Possible"); //$NON-NLS-1$
		} else {
			buf.append("severity="); //$NON-NLS-1$
			buf.append(severity);
		}
		buf.append(' ');
		buf.append(message);
		return buf.toString();
	}
	
	/**
	 * Append the paramater to this status, and return the resultant status;
	 * clients must be careful to set their cached status to the return value, 
	 * as it may be a new instance.
	 */
	public J2EEMigrationStatus append(J2EEMigrationStatus aStatus) {
		if (aStatus == null)
			return this;
		J2EEMigrationMultiStatus multi = new J2EEMigrationMultiStatus();
		multi.merge(this);
		multi.merge(aStatus);
		return multi;
	}
}

