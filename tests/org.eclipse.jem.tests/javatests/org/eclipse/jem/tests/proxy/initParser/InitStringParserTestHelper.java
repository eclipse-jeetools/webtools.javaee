/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests.proxy.initParser;
/*
 *  $RCSfile: InitStringParserTestHelper.java,v $
 *  $Revision: 1.6 $  $Date: 2005/08/24 20:58:54 $ 
 */
import java.lang.reflect.Array;

import junit.framework.Assert;
import org.eclipse.jem.internal.proxy.initParser.InitializationStringEvaluationException;
import org.eclipse.jem.internal.proxy.initParser.InitializationStringParser;

/**
 * @author jmyers
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class InitStringParserTestHelper extends AbstractInitStringParserTestHelper {

	private final ClassLoader classLoader;
	
	public InitStringParserTestHelper() {
		this.classLoader = null;	
	}
	
	public InitStringParserTestHelper(ClassLoader classLoader) {
		this.classLoader = classLoader;	
	}
	
	/**
	 * TestInitializationStringParser constructor comment.
	 */
	public void testInitString(String aString, Object expectedResult, boolean throwsException, boolean equalsOnly) throws Throwable {
		// Equals only means accept if == or equals(), don't try to go on and say maybe equal.

		try {
			InitializationStringParser parser = InitializationStringParser.createParser(aString, classLoader);
			Object result = parser.evaluate();
			if (throwsException) {
				Assert.fail("It should of thrown an exception. Instead result=" + (result != null ? result.toString() : "null"));
			} else {
				if (result == expectedResult) {
					return;
				} else if (
					(result == null && expectedResult != null) || (expectedResult == null && result != null)) {
						Assert.fail("ExpectedResult=" + expectedResult + " result=" + result);
				} else if (result.equals(expectedResult)) {
					return;
				} else {
					// It may be that the equals expression is bad.  If so use the toString() to do a partial comparison
					if (result.getClass() == expectedResult.getClass()) {
						if (result.toString().equals(expectedResult.toString())) {
							return;
						} else {
							// The toStrings do not match perfectly but the classes do.
							// Try and see how close the two strings are
							if ( expectedResult.getClass().isArray() && result.getClass().isArray()){
								Class expectedResultClass = expectedResult.getClass().getComponentType();
								Class resultClass = result.getClass().getComponentType();
								int resultLength = Array.getLength(result);
								int expectedLength = Array.getLength(expectedResult);
								if ( expectedLength == resultLength ){
									if ( resultClass == expectedResultClass ) {
										// TODO Should actually step in and check each element too.
										return;																		
									} else {
										Assert.fail( aString
												+ " ExpectedResult="
												+ expectedResult
												+ " ActualResult="
												+ result
												+ " ExpectedClass="
												+ expectedResult.getClass()
												+ " ActualClass="
												+ result.getClass());
									}
								} else {
									Assert.fail( aString
											+ " ExpectedResult="
											+ expectedResult
											+ " ActualResult="
											+ result
											+ " ExpectedClass="
											+ expectedResult.getClass()
											+ " ActualClass="
											+ result.getClass());		
								}								
							} else {
								if (equalsOnly)
									Assert.fail( aString
										+ " ExpectedResult="
										+ expectedResult
										+ " ActualResult="
										+ result
										+ " ExpectedClass="
										+ expectedResult.getClass()
										+ " ActualClass="
										+ result.getClass());
								return;
							}
						}
					} else {
						Assert.fail( aString
								+ " ExpectedResult="
								+ expectedResult
								+ " ActualResult="
								+ result
								+ " ExpectedClass="
								+ expectedResult.getClass()
								+ " ActualClass="
								+ result.getClass());
					}
				}
			}
		} catch (InitializationStringEvaluationException e) {
			if (throwsException) {
				return;
			} else {
				throw e.getOriginalException();
			}
		}
	}

}
