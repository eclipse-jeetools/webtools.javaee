/*******************************************************************************
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: TestJavaJDOMAdapterFactory.java,v $
 *  $Revision: 1.1 $  $Date: 2004/06/09 22:47:00 $ 
 */
package org.eclipse.jem.tests.modelListeners;

import java.util.Collections;
import java.util.List;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jdt.core.ICompilationUnit;

import org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory;
import org.eclipse.jem.internal.adapters.jdom.JavaReflectionSynchronizer;
 

/**
 * A test version of this so that it works with our test cases without having fullblown JEM model up and running.
 * @since 1.0.0
 */
public class TestJavaJDOMAdapterFactory extends JavaJDOMAdapterFactory implements IListenerTester {
	
	int callIndex = -1;	// Index of calls into control list.
	AssertionFailedError exception; // Set if exception occured.
	
	protected int[] callTypes;	// Order of permitted calltypes. If any come out of order, then error.
	protected Object[] callArgs;	// Corresponding args for each call types. Each type specific.
	
	public void setTestCases(int[] callTypes, Object[] callArgs) {
		this.callTypes = callTypes;
		this.callArgs = callArgs;
	}
	
	public void isException() throws AssertionFailedError {
		if (exception != null)
			throw exception;
	}
		
	public void isComplete() throws AssertionFailedError {
		Assert.assertEquals("Did not complete all notifcations. ", callTypes.length, callIndex+1);
	}

	public static final int FLUSH_ALL = 0, FLUSH_ALL_NO_NOTIFICATION = 1, FLUSH_PACKAGE = 2, FLUSH_PACKAGE_NO_NOTIFICATION = 3, 
		FLUSH_REFLECTION = 4, FLUSH_REFLECTION_NO_NOTIFICATION = 5, FLUSH_REFLECTION_PLUS_INNER_NO_NOTIFICATION = 6, NOTIFY_CONTENT_CHANGED = 7,
		DISASSOCIATE_SOURCE_NOTIFY = 8, DISASSOCIATE_SOURCE = 9, DISASSOCIATE_SOURCE_PLUS_INNER_NOTIFY = 10, DISASSOCIATE_SOURCE_PLUS_INNER = 11; 
	
	private static final String[] callTypeNames = new String[] {"FLUSH_ALL", "FLUSH_ALL_NO_NOTIFICATION", "FLUSH_PACKAGE", "FLUSH_PACKAGE_NO_NOTIFICATION", 
			"FLUSH_REFLECTION", "FLUSH_REFLECTION_NO_NOTIFICATION", "FLUSH_REFLECTION_PLUS_INNER_NO_NOTIFICATION", "NOTIFY_CONTENT_CHANGED",
			"DISASSOCIATE_SOURCE_NOTIFY", "DISASSOCIATE_SOURCE", "DISASSOCIATE_SOURCE_PLUS_INNER_NOTIFY", "DISASSOCIATE_SOURCE_PLUS_INNER"};	
	/*
	 * Test the next call type, if not valid, return false.
	 */
	protected boolean testCallType(int callType) {
		if (exception != null)
			return false;	// Already had an error
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
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdapterFactory#flushAll()
	 */
	public void flushAll() {
		testCallType(FLUSH_ALL);
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory#flushAllNoNotification()
	 */
	public List flushAllNoNotification() {
		testCallType(FLUSH_ALL_NO_NOTIFICATION);
		return Collections.EMPTY_LIST;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory#flushPackage(java.lang.String, boolean)
	 */
	public void flushPackage(String packageName, boolean noFlushIfSourceFound) {
		if (testCallType(FLUSH_PACKAGE)) {
			try {
				Assert.assertEquals((String) ((Object[]) callArgs[callIndex])[0], packageName);
				Assert.assertEquals(((Boolean) ((Object[]) callArgs[callIndex])[1]).booleanValue(), noFlushIfSourceFound); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory#flushPackageNoNotification(java.lang.String, boolean)
	 */
	public List flushPackageNoNotification(String packageName, boolean noFlushIfSourceFound) {
		if (testCallType(FLUSH_PACKAGE_NO_NOTIFICATION)) {
			try {
				Assert.assertEquals((String) ((Object[]) callArgs[callIndex])[0], packageName);
				Assert.assertEquals(((Boolean) ((Object[]) callArgs[callIndex])[1]).booleanValue(), noFlushIfSourceFound); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}		
		return Collections.EMPTY_LIST;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdapterFactory#flushReflection(java.lang.String)
	 */
	public void flushReflection(String source) {
		if (testCallType(FLUSH_REFLECTION)) {
			try {
				Assert.assertEquals((String) callArgs[callIndex], source); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}		
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory#flushReflectionNoNotification(java.lang.String)
	 */
	public Notification flushReflectionNoNotification(String source) {
		if (testCallType(FLUSH_REFLECTION_NO_NOTIFICATION)) {
			try {
				Assert.assertEquals((String) callArgs[callIndex], source); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}			
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory#flushReflectionPlusInnerNoNotification(java.lang.String)
	 */
	public Notification flushReflectionPlusInnerNoNotification(String source) {
		if (testCallType(FLUSH_REFLECTION_PLUS_INNER_NO_NOTIFICATION)) {
			try {
				Assert.assertEquals((String) callArgs[callIndex], source); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}
		return null;
	}
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.adapters.jdom.JavaJDOMAdapterFactory#notifyContentChanged(org.eclipse.jdt.core.ICompilationUnit)
	 */
	public void notifyContentChanged(ICompilationUnit targetCU) {
		if (testCallType(NOTIFY_CONTENT_CHANGED)) {
			try {
				Assert.assertEquals((String) callArgs[callIndex], targetCU.getElementName()); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}			
	}
	
	
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdapterFactory#disAssociateSource(java.lang.String, boolean)
	 */
	public Notification disAssociateSource(String source, boolean doNotify) {
		if (testCallType(DISASSOCIATE_SOURCE_NOTIFY)) {
			try {
				Assert.assertEquals((String) ((Object[]) callArgs[callIndex])[0], source);
				Assert.assertEquals(((Boolean) ((Object[]) callArgs[callIndex])[1]).booleanValue(), doNotify); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdapterFactory#disAssociateSource(java.lang.String)
	 */
	public Notification disAssociateSource(String source) {
		if (testCallType(DISASSOCIATE_SOURCE)) {
			try {
				Assert.assertEquals((String) callArgs[callIndex], source); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdapterFactory#disAssociateSourcePlusInner(java.lang.String, boolean)
	 */
	public Notification disAssociateSourcePlusInner(String source, boolean doNotify) {
		if (testCallType(DISASSOCIATE_SOURCE_PLUS_INNER_NOTIFY)) {
			try {
				Assert.assertEquals((String) ((Object[]) callArgs[callIndex])[0], source);
				Assert.assertEquals(((Boolean) ((Object[]) callArgs[callIndex])[1]).booleanValue(), doNotify); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.java.adapters.JavaReflectionAdapterFactory#disAssociateSourcePlusInner(java.lang.String)
	 */
	public void disAssociateSourcePlusInner(String source) {
		if (testCallType(DISASSOCIATE_SOURCE_PLUS_INNER)) {
			try {
				Assert.assertEquals((String) callArgs[callIndex], source); 
			} catch (AssertionFailedError e) {
				exception = e;
			}			
		}		
	}
	
	public JavaReflectionSynchronizer getSynchronizer() {
		return synchronizer;
	}
}
