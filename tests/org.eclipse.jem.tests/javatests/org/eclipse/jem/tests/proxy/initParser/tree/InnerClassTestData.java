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
 *  $RCSfile: InnerClassTestData.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/23 22:53:36 $ 
 */
package org.eclipse.jem.tests.proxy.initParser.tree;

import java.awt.Color;
 
/**
 * This is for testing inner class access.
 * @since 1.0.0
 */
public class InnerClassTestData {

	/**
	 * 
	 * @since 1.0.0
	 */
	public static class InnerInnerClass extends Object {
		public final static Color GREEN = Color.green;

		/**
		 * 
		 * @since 1.0.0
		 */
		public static class InnerInnerInnerClass extends Object {
			public final static Color RED = Color.red; 

			/**
			 * 
			 * @since 1.0.0
			 */
			public class InnerInnerInnerInnerClass {

			}
		}
	}
}