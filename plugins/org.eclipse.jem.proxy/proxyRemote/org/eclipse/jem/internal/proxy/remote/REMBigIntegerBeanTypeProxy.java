package org.eclipse.jem.internal.proxy.remote;
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
 *  $RCSfile: REMBigIntegerBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
import java.math.BigInteger;
/**
 * Remote Implementation of IBeanTypeProxy for BigInteger.
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMBigIntegerBeanTypeProxy extends REMAbstractNumberBeanTypeProxy {
// Some values are cache'd for speed
	private final INumberBeanProxy zeroProxy;
	private final static BigInteger zeroValue = new BigInteger("0"); //$NON-NLS-1$
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMBigIntegerBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy numberType) {
	super(aRegistry, new Integer(Commands.BIG_INTEGER_CLASS), BigInteger.class.getName(), numberType);
	
	// Create the cached values
	zeroProxy = new REMBigIntegerBeanProxy(aRegistry, zeroValue);	
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createBigIntegerBeanProxy(BigInteger aBigInteger) {
	return (!zeroValue.equals(aBigInteger)) ?
		new REMBigIntegerBeanProxy(fRegistry, aBigInteger) : zeroProxy;
}

/**
 * Generic way to create an BigInteger proxy, however, the Number must be a BigInteger.
 */
protected INumberBeanProxy createNumberBeanProxy(Number aNumber) {
	return createBigIntegerBeanProxy((BigInteger) aNumber);
}	

/**
 * newInstance method. Number's don't have a default ctor, so the standard way won't work.
 * Return the zero proxy for this case.
 */
public IBeanProxy newInstance() {
	return zeroProxy;
}

/**
 * Create a new bean proxy the ValueObject passed in.
 * We will assume the object is correct type.
 */
public IBeanProxy newBeanProxy(Commands.ValueObject value) {
	return createBigIntegerBeanProxy((BigInteger) value.anObject);
}
}


