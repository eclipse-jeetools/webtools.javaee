package org.eclipse.jem.tests.proxy.vm;
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
 *  $RCSfile: TestCallback.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:25:46 $ 
 */

import org.eclipse.jem.internal.proxy.common.*;
/**
 * This is a test for testing callbacks.
 */
public class TestCallback implements ICallback {
	
	IVMServer vmServer;
	int callbackID;
	boolean stop = false;

	/**
	 * When told to start, it will send a callback
	 * once every half second until told to stop.
	 */
	public void start() {
		new Thread(new Runnable() {
			public void run() {
				final Integer[] p = new Integer[] {new Integer(0)};
				while(!stop) {
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						continue;
					}
					if (stop)
						break;
					// Now signal the callback.
					p[0] = new Integer(p[0].intValue()+1);
					try {
						vmServer.doCallback(new ICallbackRunnable() {
							public Object run(ICallbackHandler handler) throws CommandException {
								return handler.callbackWithParms(callbackID, 0, p);								
							}
						});
					} catch (CommandException e) {
					}						
				}
			}
		}).start();
	}
	
	/**
	 * Tell it stop.
	 */
	public void stop() {
		stop = true;
	}
	
	/**
	 * It is being initialized.
	 */
	public void initializeCallback(IVMServer server, int id) {
		vmServer = server;
		callbackID = id;
	}

}