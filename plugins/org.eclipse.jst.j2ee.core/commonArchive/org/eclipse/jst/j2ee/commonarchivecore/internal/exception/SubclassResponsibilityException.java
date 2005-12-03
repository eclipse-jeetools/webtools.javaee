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
package org.eclipse.jst.j2ee.commonarchivecore.internal.exception;



import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonArchiveResourceHandler;

/**
 * Runtime exception used as a way to enforce abstract behavior without declaring the methods
 * abstract. Necessary because impl classes in the etools generated hierarchy cannot be abstract if
 * they have subtypes.
 */
public class SubclassResponsibilityException extends ArchiveRuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6815673671775564354L;

	/**
	 * SubclassResponsibilityException constructor comment.
	 */
	public SubclassResponsibilityException() {
		super();
	}

	/**
	 * SubclassResponsibilityException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public SubclassResponsibilityException(String methodName) {
		super(CommonArchiveResourceHandler.getString(CommonArchiveResourceHandler.subclass_responsibilty_EXC_, (new Object[]{methodName}))); // = " must be implemented in subclass"
	}
}
