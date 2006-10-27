/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.tests;


/**
 * Test case for testing the reflection of concepts
 * from JDK 5 when source is not available.
 * @since 1.2.2
 *
 */
public class JDK5BinReflectionTests extends JDK5ReflectionTests {

	/**
	 * 
	 */
	public JDK5BinReflectionTests() {
		isReflectingSource = false;
	}

	/**
	 * @param name
	 */
	public JDK5BinReflectionTests(String name) {
		super(name);
		isReflectingSource = false;
	}
	
}
