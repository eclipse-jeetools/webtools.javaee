/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy;
/*
 *  $RCSfile: TestCallbackStream.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:39 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
import java.io.InputStream;
import java.io.IOException;

import junit.framework.TestCase;
/**
 * This is the callback for the test case of the stream. This runs on the IDE side.
 */
public class TestCallbackStream implements ICallback {

	Object tester;	// Notified when the test is finished.
	
	private boolean testStopped;
	private String badMsg;
	private IOException exp;
	private boolean testSuccesful;
	
	public TestCallbackStream(Object o) {
		tester = o;
	}
	
	public void testComplete() throws IOException {
		if (badMsg != null)
			TestCase.fail(badMsg);
		if (exp != null)
			throw exp;
		TestCase.assertTrue(testSuccesful);
	}

	public Object calledBack(int msgID, Object  parm){
		badMsg = "Failed Callback Test. Shouldn't have gotton to object parm"; //$NON-NLS-1$
		stopTest();
		return null;
	}
	public Object calledBack(int msgID, IBeanProxy parm){
		badMsg = "Failed Callback Test. Shouldn't have gotton to parm"; //$NON-NLS-1$
		stopTest();
		return null;
	}
	public Object calledBack(int msgID, Object[] parms){
		badMsg = "Failed Callback Test. Shouldn't have gotton to multi-parms"; //$NON-NLS-1$
		stopTest();		
		return null;
	}
	public void calledBackStream(int msgID, InputStream in){
		// Read from the stream for 30000 bytes. Verify that
		// we get an incrementing stream, i.e. 0, 1, ..., 127, -127, ...
		
		synchronized (tester) {
			if (testStopped)
				return;
		}
		for (int i=0; i<30000; i++) {
			try {
				int next = in.read();
				if (next == -1) {
					badMsg = "Failed Callback Stream test. Stream ended too soon. On counter="+String.valueOf(i); //$NON-NLS-1$
					stopTest();
					return;
				}
				
				if (((byte) next) != ((byte) i)) {
					badMsg = "Failed Callback Stream test. Did not get correct next byte at:" + i; //$NON-NLS-1$
					stopTest();
					return;
				}
			} catch (IOException e) {
				exp = e;
				stopTest();				
				return;
			}
		}
		
		try {
			if (in.read() != -1) {
				badMsg = "Failed Callback Stream test. Too much data sent."; //$NON-NLS-1$
				stopTest();				
				return;
			}
		} catch (IOException e) {
			exp = e;
		}
		
		testSuccesful = true;
		stopTest();
	}
	
	void stopTest() {
		synchronized(tester) {
			testStopped = true;
			tester.notify();
		}
	}
		

}
