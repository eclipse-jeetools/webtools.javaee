/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: TestBeaninfoAdapterFactory.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 23:00:16 $ 
 */
package org.eclipse.jem.tests.modelListeners;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.eclipse.jem.internal.beaninfo.adapters.BeaninfoAdapterFactory;
import org.eclipse.jem.internal.beaninfo.core.IBeaninfoSupplier;

/**
 * Test factory to handle the calls from the listener during our tests.
 * 
 * @since 1.0.0
 */
public class TestBeaninfoAdapterFactory extends BeaninfoAdapterFactory implements IListenerTester {

	/**
	 * @param supplier
	 * 
	 * @since 1.0.0
	 */
	public TestBeaninfoAdapterFactory(IBeaninfoSupplier supplier) {
		super(supplier);
	}
	
	int callIndex = -1; // Index of calls into control list.

	AssertionFailedError exception; // Set if exception occured.

	protected int[] callTypes; // Order of permitted calltypes. If any come out of order, then error.

	protected Object[] callArgs; // Corresponding args for each call types. Each type specific.

	public void setTestCases(int[] callTypes, Object[] callArgs) {
		this.callTypes = callTypes;
		this.callArgs = callArgs;
	}

	public void isException() throws AssertionFailedError {
		if (exception != null)
			throw exception;
	}

	public void isComplete() throws AssertionFailedError {
		Assert.assertEquals("Did not complete all notifcations. ", callTypes.length, callIndex + 1);
	}

	public static final int MARK_ALL_STALE = 0, MARK_STALE_INTROSPECTION = 1, MARK_STALE_INTROSPECTION_PLUS_INNER = 2;

	private static final String[] callTypeNames = new String[] { "MARK_ALL_STALE", "MARK_STALE_INTROSPECTION", "MARK_STALE_INTROSPECTION_PLUS_INNER",
			"UNREGISTER_INTROSPECTION", "UNREGISTER_INTROSPECTION_PLUS_INNER"};

	/*
	 * Test the next call type, if not valid, return false.
	 */
	protected boolean testCallType(int callType) {
		if (exception != null)
			return false; // Already had an error
		try {
			if (++callIndex >= callTypes.length)
				Assert.fail("An extra notification of type " + callTypeNames[callType] + " received.");
			if (callTypes[callIndex] != callType)
				Assert.assertEquals(callTypeNames[callTypes[callIndex]], callTypeNames[callType]);
		} catch (AssertionFailedError e) {
			exception = e;
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.beaninfo.adapters.BeaninfoAdapterFactory#markAllStale()
	 */
	public void markAllStale() {
		testCallType(MARK_ALL_STALE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.beaninfo.adapters.BeaninfoAdapterFactory#markStaleIntrospection(java.lang.String, boolean)
	 */
	public void markStaleIntrospection(String sourceName, boolean clearResults) {
		if (testCallType(MARK_STALE_INTROSPECTION)) {
			try {
				Assert.assertEquals((String) ((Object[]) callArgs[callIndex])[0], sourceName);
				Assert.assertEquals(((Boolean) ((Object[]) callArgs[callIndex])[1]).booleanValue(), clearResults); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.beaninfo.adapters.BeaninfoAdapterFactory#markStaleIntrospectionPlusInner(java.lang.String, boolean)
	 */
	public void markStaleIntrospectionPlusInner(String sourceName, boolean clearResults) {
		if (testCallType(MARK_STALE_INTROSPECTION_PLUS_INNER)) {
			try {
				Assert.assertEquals((String) ((Object[]) callArgs[callIndex])[0], sourceName);
				Assert.assertEquals(((Boolean) ((Object[]) callArgs[callIndex])[1]).booleanValue(), clearResults); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}		
	}
}