/*******************************************************************************
 * Copyright (c) 2001, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;


/**
 * This exception should be thrown if the user cancels validation.
 */
public class ValidationCancelledException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7065655347030103289L;

	public ValidationCancelledException() {
		super();
	}
}
