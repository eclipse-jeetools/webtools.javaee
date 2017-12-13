/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.workbench.validation;


/**
 * This class declares the string constants which are used only in WSAD.
 *  
 */
public interface EJBHelperConstants {
	//
	// Location constants
	//
	// Used when a line number cannot be calculated, and thus the target of the message
	// has to be described in words.
	/* package */static final String LOC_CLASS = "LOC_CLASS"; //$NON-NLS-1$
	/* package */static final String LOC_FIELD = "LOC_FIELD"; //$NON-NLS-1$
	/* package */static final String LOC_METHOD = "LOC_METHOD"; //$NON-NLS-1$
	/* package */static final String LOC_BEAN = "LOC_BEAN"; //$NON-NLS-1$
	/* package */static final String LOC_ROLE = "LOC_ROLE"; //$NON-NLS-1$
	/* package */static final String LOC_ROLEREF = "LOC_ROLEREF"; //$NON-NLS-1$
	/* package */static final String LOC_METHODELEMENT = "LOC_METHODELEMENT"; //$NON-NLS-1$
	/* package */static final String LOC_METHODPERMISSION = "LOC_METHODPERMISSION"; //$NON-NLS-1$
	/* package */static final String LOC_METHODTRANSACTION = "LOC_METHODTRANSACTION"; //$NON-NLS-1$
	/* package */static final String LOC_EJBRELATION = "LOC_EJBRELATION"; //$NON-NLS-1$
	/* package */static final String LOC_EJBRELATIONSHIPROLE = "LOC_EJBRELATIONSHIPROLE"; //$NON-NLS-1$

}