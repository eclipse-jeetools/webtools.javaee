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
 *  $RCSfile: AbstractInitStringParserTestHelper.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:22 $ 
 */
package org.eclipse.jem.tests.proxy.initParser;

/**
 * Interface for init string parser helper. Allows "InitString Parser" or "AST Parser" or some other kind of testing.
 * 
 * @since 1.0.0
 */
public abstract class AbstractInitStringParserTestHelper {
	/**
	 * Test the given string against the given result. equalsOnly determines how exactly it matches.
	 * If allowed to throw exceptions, then the exception as the result of evaluation will be squashed.
	 * 
	 * @param aString
	 * @param expectedResult
	 * @param throwsException <code>true</code> if this is expected to throw an exception and such exception will not be passed on out and will return normal.
	 * @param equalsOnly <code>true</code> means if must match either ==, equals(), or toString()'s match.
	 * @throws Throwable
	 * 
	 * @since 1.0.0
	 */
	public abstract void testInitString(String aString, Object expectedResult, boolean throwsException, boolean equalsOnly)
		throws Throwable;

	/**
	 * Test the given string against the given result. It must match either ==, equals(), or toString()'s match.
	 * It should not throw exceptions. If it does, the exception will be passed out.
	 * 
	 * @param aString
	 * @param expectedResult
	 * @throws Throwable 
	 * 
	 * @since 1.0.0
	 */
	public void testInitString(String aString, Object expectedResult) throws Throwable {
		testInitString(aString, expectedResult, false, true);
	}

	/**
	 * Test the given string against the given result. equalsOnly determines how exactly it matches
	 * It should not throw exceptions. If it does, the exception will be passed out.
	 * 
	 * @param aString
	 * @param equalsOnly <code>true</code> means if must match either ==, equals(), or toString()'s match.
	 * @param expectedResult
	 * @throws Throwable
	 * 
	 * @since 1.0.0
	 */
	public void testInitString(String aString, boolean equalsOnly, Object expectedResult) throws Throwable {
		testInitString(aString, expectedResult, false, equalsOnly);
	}
}