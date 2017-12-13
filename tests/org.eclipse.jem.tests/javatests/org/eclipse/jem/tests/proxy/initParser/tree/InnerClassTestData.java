/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: InnerClassTestData.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:58:54 $ 
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

				/* (non-Javadoc)
				 * @see java.lang.Object#equals(java.lang.Object)
				 */
				public boolean equals(Object obj) {
					return super.equals(obj) || obj instanceof InnerInnerInnerInnerClass;
				}
			}
			
			/* (non-Javadoc)
			 * @see java.lang.Object#equals(java.lang.Object)
			 */
			public boolean equals(Object obj) {
				return super.equals(obj) || obj instanceof InnerInnerInnerClass;
			}
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object obj) {
			return super.equals(obj) || obj instanceof InnerInnerClass;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		return super.equals(obj) || obj instanceof InnerClassTestData;
	}
	
}
