package org.eclipse.jem.internal.proxy.vm.remote;
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
 *  $RCSfile: ConnectionThread.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
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