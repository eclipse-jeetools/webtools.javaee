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
 *  $RCSfile: RemoteVMApplication.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


/**
 * The application to run to kick off the remote VM server side.
 *
 *
 * All this does is start the Server Thread and waits for it to finish.
 * This allows other mains to have a server thread, and some other
 * threads if they wished. They could start the server thread too
 * and some other thread, and then wait for both to finish.
 */

public class RemoteVMApplication {

public static void main(java.lang.String[] args) {
	String serverName = "Server Thread"; //$NON-NLS-1$
	if (System.getProperty("proxyvm.servername") != null) //$NON-NLS-1$
		serverName = serverName + "-" + System.getProperty("proxyvm.servername"); //$NON-NLS-1$ //$NON-NLS-2$
	Thread t = new RemoteVMServerThread(serverName);
	t.start();
	try {
		t.join();
	} catch (Exception e) {
	}
	System.exit(0);
}



}