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
package org.eclipse.jem.internal.proxy.vm.remote;
/*
 *  $RCSfile: ConnectionThread.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:08 $ 
 */


import java.net.Socket;

import org.eclipse.jem.internal.proxy.common.CommandException;
/**
 * This is a thread to handle one connection.
 */

public class ConnectionThread extends Thread {	
		
	final protected ConnectionHandler connectionHandler;
	
	public ConnectionThread(Socket sc, RemoteVMServerThread svr, String name) {
		super(name);

		connectionHandler = new ConnectionHandler(sc, svr, this);
	}
	
	/**
	 * Request the thread to close down.
	 */
	public void close() {
		try {
			connectionHandler.close();
		} catch (Exception e) {
		}
	}
	
	public void run() {
		try {
			connectionHandler.run();
		} catch (CommandException e) {
			e.printStackTrace();
		}
	}
	
}
