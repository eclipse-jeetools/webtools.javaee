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
 *  $RCSfile: REMShortClassBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of IBeanTypeProxy for Short.class.
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMShortClassBeanTypeProxy extends REMAbstractNumberBeanTypeProxy {
// Some values are cache'd for speed
	private final INumberBeanProxy zeroProxy;
	private final INumberBeanProxy oneProxy;
	private final INumberBeanProxy twoProxy;
	private final INumberBeanProxy threeProxy;
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMShortClassBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy numberType) {
	super(aRegistry, new Integer(Commands.SHORT_CLASS), Short.class.getName(), numberType);
	
	// Create the cached values
	zeroProxy = new REMShortClassBeanProxy(aRegistry, new Short((short)0));	
	oneProxy = new REMShortClassBeanProxy(aRegistry, new Short((short)1));
	twoProxy = new REMShortClassBeanProxy(aRegistry, new Short((short)2));
	threeProxy = new REMShortClassBeanProxy(aRegistry, new Short((short)3));		
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createShortBeanProxy(Short aShort) {
	switch(aShort.shortValue()) {
		case 0: return zeroProxy;
		case 1: return oneProxy ;
		case 2: return twoProxy ;
		case 3: return threeProxy ;
		default: return new REMShortClassBeanProxy(fRegistry, aShort);
	}
}

/**
 * Generic way to create a Short proxy, however, the Number must be a Short.
 */
protected INumberBeanProxy createNumberBeanProxy(Number aNumber) {
	return createShortBeanProxy((Short) aNumber);
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
	return createShortBeanProxy(new Short(value.aShort));
}
}


