package org.eclipse.jem.internal.proxy.core;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: IArrayBeanProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


/**
 * Proxy wrappering an array.
 */
public interface IArrayBeanProxy extends IBeanProxy {
	/**
	 * Get the object at the specified index.
	 */
	IBeanProxy get(int index) throws ThrowableProxy;

	IBeanProxy getCatchThrowableException(int index);
	
	/**
	 * Get the object at the specified multi-dimensional index.
	 * The array must be at least the number of dimensions specified,
	 * and each index along the way must exist.
	 * The number of dimensions can't be greater than the number
	 * of dimensions of the real object.
	 */
	IBeanProxy get(int [] indexes) throws ThrowableProxy; 
	
	
	/**
	 * Set the object at the specified index.
	 */
	void set(IBeanProxy value, int index) throws ThrowableProxy;
	
	/**
	 * Set the object at the specified multi-dimensional index.
	 * The array must be at least the number of dimensions specified,
	 * and each index along the way must exist.
	 * The number of dimensions can't be greater than the number
	 * of dimensions of the real object.
	 */
	void set(IBeanProxy value, int [] indexes) throws ThrowableProxy;	 
	
	/**
	 * Get the length of the first dimension of this array.
	 * If there are multi-dimensions, you must get the appropriate
	 * dimension from the get method to see the size of that dimension.
	 *
	 * e.g.
	 *    int [3] returns 3
	 *    int [3][2] returns 3
	 *
	 *    ((IArrayBeanProxy) get(1)).getLength() returns 2
	 *    Since arrays do not have to be homogenous, there could
	 *    be a different length array for each of the arrays 
	 *    returned from the first dimension, the returned length
	 *    from get(2) and get(3) could result in a different value
	 *    from get(1).
	 */
	int getLength();

	
}