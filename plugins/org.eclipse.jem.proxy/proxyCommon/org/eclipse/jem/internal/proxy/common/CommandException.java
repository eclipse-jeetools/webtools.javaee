package org.eclipse.jem.internal.proxy.common;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: CommandException.java,v $
 *  $Revision: 1.3 $  $Date: 2005/06/16 17:46:14 $ 
 */
/**
 * An error occurred during command processing.
 *
 */

public class CommandException extends Exception {
	/**
	 * Comment for <code>serialVersionUID</code>
	 * 
	 * @since 1.1.0
	 */
	private static final long serialVersionUID = -2519238571145682514L;
	protected final Object fExceptionData;
	
	public CommandException() {
		fExceptionData = null;
	}
	
	public CommandException(Object data) {
		fExceptionData = data;
	}
	
	public CommandException(String msg, Object data) {
		super(msg);
		fExceptionData = data;
	}	
	
	public Object getExceptionData() {
		return fExceptionData;
	}
	
	public boolean isRecoverable() {
		return false;	// By default Command Exceptions are not recoverable.
	}	
}