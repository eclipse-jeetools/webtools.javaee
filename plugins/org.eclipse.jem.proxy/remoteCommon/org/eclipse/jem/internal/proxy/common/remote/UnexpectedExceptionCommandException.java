package org.eclipse.jem.internal.proxy.common.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: UnexpectedExceptionCommandException.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

/**
 * Wrapper an unexpected exception in a Command Exception.
 */
public class UnexpectedExceptionCommandException extends UnexpectedCommandException {

	public UnexpectedExceptionCommandException(boolean recoverable, Throwable data) {
		super(Commands.SOME_UNEXPECTED_EXCEPTION, recoverable, data);
	}
	
	public UnexpectedExceptionCommandException(boolean recoverable, String msg, Throwable data) {
		super(Commands.SOME_UNEXPECTED_EXCEPTION, recoverable, msg, data);
	}
	
	public Throwable getException() {
		return (Throwable) getExceptionData();
	}
	
	public String getMessage() {
		String localMsg = super.getMessage();
		if (getException() != null)
			if (localMsg == null || localMsg.length() == 0)
				return getException().getLocalizedMessage();
			else
				return localMsg + ": " + getException().getLocalizedMessage(); //$NON-NLS-1$
		return
			localMsg;
	}
	
	public void printExceptionStackTrace() {
		getException().printStackTrace();
	}
	
	public void printStackTrace() {
		printExceptionStackTrace();
	}
	
	public void printStackTrace(java.io.PrintStream p) {
		getException().printStackTrace(p);
	}

	public void printStackTrace(java.io.PrintWriter p) {
		getException().printStackTrace(p);
	}	
}