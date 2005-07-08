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
 *  $RCSfile: TestExpressionThreadTransfer.java,v $
 *  $Revision: 1.2 $  $Date: 2005/07/08 17:51:46 $ 
 */

import org.eclipse.jem.internal.proxy.common.*;
/**
 * This is a test for testing callbacks.
 */
public class TestExpressionThreadTransfer implements ICallback {
	
	IVMCallbackServer vmServer;
	int callbackID;
	boolean stop = false;

	/**
	 * When told to start, it will send a callback
	 * once every half second until told to stop.
	 */
	public void start() {
		// Need to put it into a new thread.
		// Then we'll just wait until the thread is done.
		// This will cause callback onto a different thread in IDE.
		Thread t = new Thread(new Runnable() {
			public void run() {
				// Calback to IDE on this thread. It won't return until complete on the other side.
				try {
					vmServer.doCallback(new ICallbackRunnable() {
						public Object run(ICallbackHandler handler) throws CommandException {
							return handler.callbackAsConstants(callbackID, 0, null);
						}
					});
					
				} catch (CommandException e) {
				}
			}
		});
		t.start();
		while (true) {
			try {
				t.join();
				break;
			} catch (InterruptedException e) {
			}
		}
		// Now we return and original thread on IDE resumes processing.
	}
		
	/**
	 * It is being initialized.
	 */
	public void initializeCallback(IVMCallbackServer server, int id) {
		vmServer = server;
		callbackID = id;
	}

}
