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
 *  $Revision: 1.2 $  $Date: 2005/02/10 22:38:30 $ 
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
	 * Get a snapshot of the array. It will return an array of proxies of the
	 * complete first dimension of the array. This is useful if you need
	 * to be working with more than one entry of the array. You can save
	 * accesses to the vm by doing this. 
	 * <p>
	 * NOTE: This is a snapshot. It will not see changes to the array.
	 * If an entry is set into the returned array of proxies, this will
	 * not be reflected into the real array. Also any changes in the
	 * contents of the first dimension of the real array will not
	 * be reflected into this returned array of proxies. The proxies
	 * returned are real proxies, so any changes to them will be reflected
	 * back and forth. Treat this as an array copy to a new array.
	 * 
	 * @return array of proxies of the first dimension of the array.
	 * @throws ThrowableProxy
	 * 
	 * @since 1.1.0
	 */
	IBeanProxy[] getSnapshot() throws ThrowableProxy;
	
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