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
package org.eclipse.jst.j2ee.commonarchivecore.exception;



/**
 * Exception which occurs when a mof resource's extent contains zero elements and an attempt is made
 * to access an elememt from the resource.
 */
public class EmptyResourceException extends ArchiveException {
	/**
	 * EmptyResourceException constructor comment.
	 */
	public EmptyResourceException() {
		super();
	}

	/**
	 * EmptyResourceException constructor comment.
	 * 
	 * @param s
	 *            java.lang.String
	 */
	public EmptyResourceException(String s) {
		super(s);
	}
}
