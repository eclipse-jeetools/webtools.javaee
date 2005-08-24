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
 *  $RCSfile: IOCommandException.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:39:08 $ 
 */

import java.io.IOException;
import org.eclipse.jem.internal.proxy.common.CommandException;

/**
 * A command exception occured while processing an io request
 * in a callback stream. This exception wrappers the command exception.
 */

public class IOCommandException extends IOException {
	/**
	 * Comment for <code>serialVersionUID</code>
	 * 
	 * @since 1.1.0
	 */
	private static final long serialVersionUID = -2308947195164382779L;
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


