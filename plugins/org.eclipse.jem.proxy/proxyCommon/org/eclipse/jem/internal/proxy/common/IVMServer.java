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
 *  $RCSfile: IVMServer.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:54:34 $ 
 */

import java.io.OutputStream;
/**
 * This is the interface for accessing the 
 * VM  Server. It will be given to an
 * ICallback so that the callback can access
 * the server to request a callback.
 */
public interface IVMServer {
	
	
	/**
	 * Process a callback. The runnable contains the
	 * actual code.
	 */
	public Object doCallback(ICallbackRunnable runnable) throws CommandException;
	
	/**
	 * Request a stream for writing a lot of data (suggested for larger
	 * than several thousand bytes). The connection will be assigned
	 * to this stream until the stream is closed.
	 */
	public OutputStream requestStream(int callbackID, int msgID) throws CommandException;

	

}