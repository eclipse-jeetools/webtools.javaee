/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy.vm;
/*
 *  $RCSfile: TestCallback.java,v $
 *  $Revision: 1.5 $  $Date: 2005/07/08 17:51:46 $ 
 */

import org.eclipse.jem.internal.proxy.common.*;
/**
 * This is a test for testing callbacks.
 */
public class TestCallback implements ICallback {
	
	IVMCallbackServer vmServer;
	int callbackID;
	boolean stop = false;

	/**
	 * When told to start, it will send a callback
	 * once every half second until told to stop.
	 */
	public void start() {
		new Thread(new Runnable() {
			public void run() {
				// First send back test for IDE calling back on same thread. We do this by sending the thread id
				// as a constant and the other side will then call back to remote, ask for the thread id, and
				// see if it matches.
				try {
					vmServer.doCallback(new ICallbackRunnable() {
						public Object run(ICallbackHandler handler) throws CommandException {
							return handler.callbackAsConstants(callbackID, 1, new Integer(Thread.currentThread().hashCode()));
						}
					});
					
				} catch (CommandException e) {
				}
				
				// Now do the regular testing.
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
	public void initializeCallback(IVMCallbackServer server, int id) {
		vmServer = server;
		callbackID = id;
	}

}
