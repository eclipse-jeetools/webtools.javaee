/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JDK5ReflectionSuite.java,v $
 *  $Revision: 1.1 $  $Date: 2006/10/27 19:36:33 $ 
 */
package org.eclipse.jem.tests;

import junit.extensions.TestSetup;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * This is basic JEM JDK5 toleration mode tests.
 * 
 * @author richkulp
 *
 */
public class JDK5ReflectionSuite extends TestSetup {

	// Test cases to be include in the suite
	private static final Class testsList[] = { JDK5ReflectionTests.class, JDK5BinReflectionTests.class};

	/**
	 * 
	 */
	public JDK5ReflectionSuite() {
		this("Test Basic JEM JDK5 toleration mode Suite");
	}

	/**
	 * @param name
	 */
	public JDK5ReflectionSuite(String name) {
		super(new TestSuite(name) {
			{
				for (int i = 0; i < testsList.length; i++) {
					addTestSuite(testsList[i]);
				}

			}
		});
	}

	public static Test suite() {
		return new JDK5ReflectionSuite();
	}
}
