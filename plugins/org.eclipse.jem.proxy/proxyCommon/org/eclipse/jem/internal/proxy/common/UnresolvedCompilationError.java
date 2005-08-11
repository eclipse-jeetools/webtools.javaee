/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile$
 *  $Revision$  $Date$ 
 */
package org.eclipse.jem.internal.proxy.common;
 
/**
 * Used by the the registries to indicate "UnresolvedCompilationError". This is because the
 * normal java throws just an Error with a message. To make it easier in processing, it will
 * be turned into this error instead so that it can be explicitly caught.
 * <p>
 * This will only be used in certain explicit parts of the code. It will be detailed where
 * it can be thrown.
 * 
 * @since 1.1.0.1
 */
public class UnresolvedCompilationError extends Error {

	/**
	 * Comment for <code>serialVersionUID</code>
	 * 
	 * @since 1.1.0.1
	 */
	private static final long serialVersionUID = 7778842211073592790L;
	
	/**
	 * Construct from an Error.
	 * @param error The error that is the actual one. <b>This will not be the cause, the message and stacktrace will be copied into this new error.</b>
	 * 
	 * @since 1.1.0.1
	 */
	public UnresolvedCompilationError(Error error) {
		super(error.getMessage());
		setStackTrace(error.getStackTrace());
	}

}
