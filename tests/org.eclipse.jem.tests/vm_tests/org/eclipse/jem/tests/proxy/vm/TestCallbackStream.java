package org.eclipse.jem.tests.proxy.vm;
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
 *  $RCSfile: TestCallbackStream.java,v $
 *  $Revision: 1.3 $  $Date: 2005/07/08 17:51:46 $ 
 */

import java.io.IOException;
import java.io.OutputStream;

import org.eclipse.jem.internal.proxy.common.ICallback;
import org.eclipse.jem.internal.proxy.common.IVMCallbackServer;
/**
 * This is a test for testing callback streams.
 */
public class TestCallbackStream implements ICallback {
	
	IVMCallbackServer vmServer;
	int callbackID;
	boolean stop = false;

	/**
	 * When told to start, it will send a stream of 30000 bytes of incrementing
	 * value (i.e. 0, 1, 2, ..., 127, -127, ...)
	 */
	public void start() {
		new Thread(new Runnable() {
			public void run() {
				OutputStream os = null;
				try {
					os = vmServer.requestStream(callbackID, 0);
					if (os != null)
						for (int i=0; i<30000; i++)
							os.write(i);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					if (os != null)
						os.close();
				} catch (IOException e) {
				}
		}}).start();
	}
	
	/**
	 * It is being initialized.
	 */
	public void initializeCallback(IVMCallbackServer server, int id) {
		vmServer = server;
		callbackID = id;
	}

}