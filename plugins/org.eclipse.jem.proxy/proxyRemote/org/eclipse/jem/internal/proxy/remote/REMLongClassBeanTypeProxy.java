package org.eclipse.jem.internal.proxy.remote;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: REMLongClassBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of IBeanTypeProxy Long.class.
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMLongClassBeanTypeProxy extends REMAbstractNumberBeanTypeProxy {
// Some values are cache'd for speed
	private final INumberBeanProxy zeroProxy;
	private final INumberBeanProxy oneProxy;
	private final INumberBeanProxy twoProxy;
	private final INumberBeanProxy threeProxy;
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMLongClassBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy numberType) {
	super(aRegistry, new Integer(Commands.LONG_CLASS), Long.class.getName(), numberType);
	
	// Create the cached values
	zeroProxy = new REMLongClassBeanProxy(aRegistry, new Long(0));	
	oneProxy = new REMLongClassBeanProxy(aRegistry, new Long(1));
	twoProxy = new REMLongClassBeanProxy(aRegistry, new Long(2));
	threeProxy = new REMLongClassBeanProxy(aRegistry, new Long(3));		
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createLongBeanProxy(Long aLong) {
	if (aLong.longValue() >= 0 && aLong.longValue() < 4) {
		// Switch statements don't work with longs.
		switch(aLong.intValue()) {
			case 0: return zeroProxy;
			case 1: return oneProxy ;
			case 2: return twoProxy ;
			case 3: return threeProxy ;
		}
	} 
	return new REMLongClassBeanProxy(fRegistry, aLong);
}

/**
 * Generic way to create a Long proxy, however, the Number must be a Long.
 */
protected INumberBeanProxy createNumberBeanProxy(Number aNumber) {
	return createLongBeanProxy((Long) aNumber);
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
	return createLongBeanProxy(new Long(value.aLong));
}
}


