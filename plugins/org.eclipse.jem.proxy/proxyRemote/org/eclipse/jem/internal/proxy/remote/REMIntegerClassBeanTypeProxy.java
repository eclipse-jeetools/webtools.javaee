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
 *  $RCSfile: REMIntegerClassBeanTypeProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:06 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of INumberBeanProxy for Integer.class.
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMIntegerClassBeanTypeProxy extends REMAbstractNumberBeanTypeProxy {
// Some values are cache'd for speed
	private final IIntegerBeanProxy zeroProxy;
	private final IIntegerBeanProxy oneProxy;
	private final IIntegerBeanProxy twoProxy;
	private final IIntegerBeanProxy threeProxy;
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMIntegerClassBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy numberType) {
	super(aRegistry, new Integer(Commands.INTEGER_CLASS), Integer.class.getName(), numberType);
	
	// Create the cached values
	zeroProxy = new REMIntegerClassBeanProxy(aRegistry, new Integer(0));	
	oneProxy = new REMIntegerClassBeanProxy(aRegistry, new Integer(1));
	twoProxy = new REMIntegerClassBeanProxy(aRegistry, new Integer(2));
	threeProxy = new REMIntegerClassBeanProxy(aRegistry, new Integer(3));
		
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
IIntegerBeanProxy createIntegerBeanProxy(Integer anInteger) {
	switch(anInteger.intValue()) {
		case 0: return zeroProxy;
		case 1: return oneProxy ;
		case 2: return twoProxy ;
		case 3: return threeProxy ;
		default: return new REMIntegerClassBeanProxy(fRegistry, anInteger);
	}
}

/**
 * Generic way to create an Integer proxy, however, the Number must be an Integer.
 */
protected INumberBeanProxy createNumberBeanProxy(Number aNumber) {
	return createIntegerBeanProxy((Integer) aNumber);
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
	return createIntegerBeanProxy(new Integer(value.anInt));
}
}


