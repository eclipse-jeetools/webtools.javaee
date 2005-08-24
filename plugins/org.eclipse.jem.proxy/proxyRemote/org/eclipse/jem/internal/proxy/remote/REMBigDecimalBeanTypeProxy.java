/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMBigDecimalBeanTypeProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:06 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
import java.math.BigDecimal;
/**
 * Remote Implementation of IBeanTypeProxy for BigDecimal.
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMBigDecimalBeanTypeProxy extends REMAbstractNumberBeanTypeProxy {
// Some values are cache'd for speed
	private final INumberBeanProxy zeroProxy;
	private final static BigDecimal zeroValue = new BigDecimal(0D);

REMBigDecimalBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy numberType) {
	super(aRegistry, new Integer(Commands.BIG_DECIMAL_CLASS), BigDecimal.class.getName(), numberType);
	
	// Create the cached values
	zeroProxy = new REMBigDecimalBeanProxy(aRegistry, zeroValue);	
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createBigDecimalBeanProxy(BigDecimal aBigDecimal) {
	return (!zeroValue.equals(aBigDecimal)) ?
		new REMBigDecimalBeanProxy(fRegistry, aBigDecimal) : zeroProxy;
}

/**
 * Generic way to create an BigDecimal proxy, however, the Number must be a BigDecimal.
 */
protected INumberBeanProxy createNumberBeanProxy(Number aNumber) {
	return createBigDecimalBeanProxy((BigDecimal) aNumber);
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
	return createBigDecimalBeanProxy((BigDecimal) value.anObject);
}	

}


