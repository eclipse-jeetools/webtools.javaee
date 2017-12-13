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
package org.eclipse.jem.tests.proxy;
/*
 *  $RCSfile: TestCallback.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:58:54 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
import java.io.InputStream;

import junit.framework.TestCase;
/**
 * This is the callback for the test case. This runs on the IDE side.
 */
public class TestCallback implements ICallback {
	
	Object tester;
	ProxyFactoryRegistry registry;
	
	int counter = 0;
	
	/**
	 * This will do assert tests to make sure test completed correctly.
	 */
	public void testCompleted() {
		if (badParmType != null)
			TestCase.assertEquals(IIntegerBeanProxy.class, badParmType);
		if (badParmValue != 0)
			TestCase.assertEquals(goodParmValue, badParmValue);
		if (badMsg != null)
			TestCase.fail(badMsg);
		TestCase.assertEquals(2, counter);
	}
	
	private boolean stoppedTest;
	private Class badParmType = null;
	private int badParmValue, goodParmValue;
	private String badMsg;
	
	// Normally the callback doesn't determine when to stop 
	// the callback proxy. But for this test it does.
	public TestCallback(Object t, ProxyFactoryRegistry registry) {
		tester = t;
		this.registry = registry;
	}
	
	public Object calledBack(int msgID, IBeanProxy parm){
		synchronized(tester) {
			if (stoppedTest)
				return null;	// We've already stopped, don't do anymore.
		}
		
		if (msgID != 0) {
			badMsg = "Msgid " + msgID + " is incorrect. It should be zero.";
			stopTest();
		} else if (parm instanceof IIntegerBeanProxy) {
			int pv = ((IIntegerBeanProxy) parm).intValue();
			if (++counter != pv) {
				badParmValue = pv;
				goodParmValue = counter;
				stopTest();
				return null;
			}
			if (counter == 2) {
				stopTest();
			}
		} else {
			// Bad parm type.
			badParmType = parm.getClass();
			stopTest();
		}
		return null;
	}
	
	public Object calledBack(int msgID, Object parm) {
		if (msgID != 1) {
			badMsg = "Msgid " + msgID + " is incorrect. It should be one.";
			stopTest();
		} else if (parm instanceof Integer){
			// We are going to use the registry to see what thread id is running when we call into it.
			IMethodProxy currentThreadMethod = registry.getMethodProxyFactory().getMethodProxy("java.lang.Thread", "currentThread", null);
			IBeanProxy thread = currentThreadMethod.invokeCatchThrowableExceptions(null);
			IMethodProxy hashcodeMethod = registry.getMethodProxyFactory().getMethodProxy("java.lang.Object", "hashCode", null);
			IBeanProxy threadID = hashcodeMethod.invokeCatchThrowableExceptions(thread);
			if (threadID instanceof INumberBeanProxy) {
				if (!parm.equals(((INumberBeanProxy) threadID).numberValue())) {
					badMsg = "ThreadID returned from call not match parm sent it. Means callback not on same thread.";
					stopTest();
				}
			} else {
				badMsg = "ThreadID returned from call is not valid.";
				stopTest();
			}
		} else {
			badMsg = "Parm is invalid for calledBack with Object";
			stopTest();
		}
		return null;
	}
	
	public Object calledBack(int msgID, Object[] parms){
		badMsg = "Failed Callback Test. Shouldn't have gotton to multi-parms"; //$NON-NLS-1$
		stopTest();
		return null;
	}
	
	public void calledBackStream(int msgID, InputStream in){
		badMsg = "Failed Callback Test. Shouldn't have gotton to input stream"; //$NON-NLS-1$
		stopTest();
	}	
	
	void stopTest() {
		synchronized(tester) {
			stoppedTest = true;
			tester.notify();
		}
	}

}
