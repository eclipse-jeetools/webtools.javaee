/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.common.remote;
/*
 *  $RCSfile: CommandErrorException.java,v $
 *  $Revision: 1.8 $  $Date: 2005/08/24 20:39:08 $ 
 */

import org.eclipse.jem.internal.proxy.common.CommandException;
/**
 * An error return from a command. This is clean return in that
 * the connection is still valid and alive.
 * After it has been processed the data will be the value retrieved
 * out of the valueobject and made into a proxy.
 */
public class CommandErrorException extends CommandException {
	/**
	 * Comment for <code>serialVersionUID</code>
	 * 
	 * @since 1.1.0
	 */
	private static final long serialVersionUID = 1701752035606593305L;
	private final Object fErrorObject;
	private final int fErrorCode;
	
	public CommandErrorException(int errorCode, Commands.ValueObject errorData) {
		super(errorData.clone());	// Clone it because typically these get reused and cleared out. This way we have our own.
		fErrorObject = null;
		fErrorCode = errorCode;
	}
	
	public CommandErrorException(String msg, int errorCode, Commands.ValueObject errorData, Object errorObject) {
		super(msg, errorData.clone());
		fErrorObject = errorObject;
		fErrorCode = errorCode;		
	}
	
	public Commands.ValueObject getValue() {
		return (Commands.ValueObject) getExceptionData();
	}
		
	public boolean isRecoverable() {
		return true;	// Command errors are recoverable.
	}
	
	/**
	 * Return the error code.
	 */
	public int getErrorCode() {
		return fErrorCode;
	}
	
	/**
	 * Return the error object after conversion to proxy format.
	 */
	public Object getErrorObject() {
		return fErrorObject;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return super.toString() + "-- Error code:"+getErrorCode() + (getValue() != null ? " Value data:\"" + getValue().getAsObject() + "\"" : " ") + (fErrorObject != null ? " Error object:\""+fErrorObject.toString()+"\"" : " "); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
	}

}
