/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.core;
/*


 */


/**
 * A Proxy for an array type.
 */

public interface IArrayBeanTypeProxy extends IBeanTypeProxy {
	/**
	 * getComponentType: Get the component type of this array.
	 * e.g. (new Object[3]).getClass().getComponentType() will
	 * return "java.lang.Object".
	 * and (new Object[3][2][1]).getClass().getComponentType() will
	 * return "Object[][]";
	 */
	public IBeanTypeProxy getComponentType();
	/**
	 * Return the final type, i.e. the final non-array type.
	 * i.e. int[][] will return int.
	 */
	public IBeanTypeProxy getFinalComponentType();	
	/**
	 * Return the number of dimensions for this type.
	 * i.e. int [][] will return 2.
	 */
	public int getDimensions();
}
