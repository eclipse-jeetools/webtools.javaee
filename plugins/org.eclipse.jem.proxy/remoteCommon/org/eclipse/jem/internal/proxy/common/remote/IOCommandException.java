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
 *  $RCSfile: IOCommandException.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.io.IOException;
import org.eclipse.jem.internal.proxy.common.CommandException;

/**
 * A command exception occured while processing an io request
 * in a callback stream. This exception wrappers the command exception.
 */

public class IOCommandException extends IOException {
	protected CommandException fException;
	
	public IOCommandException(CommandException e) {
		fException = e;
	}
	
	public CommandException getException() {
		return fException;
	}
	
	public String getMessage() {
		return fException.getMessage();
	}
	
	public void printStackTrace() {
		fException.printStackTrace();
	}
	
	public void printStackTrace(java.io.PrintStream p) {
		fException.printStackTrace(p);
	}

	public void printStackTrace(java.io.PrintWriter p) {
		fException.printStackTrace(p);
	}

}


