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
 *  $RCSfile: REMDoubleClassBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of IBeanTypeProxy for Double.class.
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMDoubleClassBeanTypeProxy extends REMAbstractNumberBeanTypeProxy {
// Some values are cache'd for speed
	private final INumberBeanProxy zeroProxy;
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMDoubleClassBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy numberType) {
	super(aRegistry, new Integer(Commands.DOUBLE_CLASS), Double.class.getName(), numberType);
	
	// Create the cached values
	zeroProxy = new REMDoubleClassBeanProxy(aRegistry, new Double(0D));	
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createDoubleBeanProxy(Double aDouble) {
	return (aDouble.doubleValue() != 0D) ?
		new REMDoubleClassBeanProxy(fRegistry, aDouble) : zeroProxy;
}

/**
 * Generic way to create an Double proxy, however, the Number must be a Double.
 */
protected INumberBeanProxy createNumberBeanProxy(Number aNumber) {
	return createDoubleBeanProxy((Double) aNumber);
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
	return createDoubleBeanProxy(new Double(value.aDouble));
}
}


