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
 *  $RCSfile: REMIntegerTypeBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of IBeanTypeProxy for Integer.TYPE.
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMIntegerTypeBeanTypeProxy extends REMPrimitiveBeanTypeProxy {
// Some values are cache'd for speed
	private final IIntegerBeanProxy zeroProxy;
	private final IIntegerBeanProxy oneProxy;
	private final IIntegerBeanProxy twoProxy;
	private final IIntegerBeanProxy threeProxy;
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMIntegerTypeBeanTypeProxy(REMProxyFactoryRegistry aRegistry) {
	super(aRegistry, new Integer(Commands.INTEGER_TYPE), Integer.TYPE.getName());
	
	// Create the cached values
	zeroProxy = new REMIntegerTypeBeanProxy(aRegistry, 0);	
	oneProxy = new REMIntegerTypeBeanProxy(aRegistry, 1);
	twoProxy = new REMIntegerTypeBeanProxy(aRegistry, 2);
	threeProxy = new REMIntegerTypeBeanProxy(aRegistry, 3);
		
}
/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 * Use the cache to see if the value is being held before creating a new one
 */
IIntegerBeanProxy createIntegerBeanProxy(int anInt) {

	switch(anInt) {
		case 0: return zeroProxy;
		case 1: return oneProxy ;
		case 2: return twoProxy ;
		case 3: return threeProxy ;
		default: return new REMIntegerTypeBeanProxy(fRegistry, anInt);
	}
}

/**
 * newInstance method. integer is a primitive, so the standard way won't work.
 * Return the zeroProxy for this case because that is the default value.
 */
public IBeanProxy newInstance() {
	return zeroProxy;
}

/**
 * Create a new bean proxy the ValueObject passed in.
 * We will assume the object is correct type.
 */
public IBeanProxy newBeanProxy(Commands.ValueObject value) {
	return createIntegerBeanProxy(value.anInt);
}

/**
 * Short cut for newInstance where the string follows the
 * specific pattern we expect. That way it won't be necessary to ship over to the vm 
 * to process it.
 *
 * The pattern it can accept is:
 * 	1) (int) validNumber - number can be any number because (byte) explicitly casts it down.
 *	2) validNumber
 * It will use the decode function of int to determine the valid number.
 */
public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
	String trim = initializationString.trim();
	if (trim.length() == 0)
		return super.newInstance(initializationString);	
	int anInt;
	if (trim.startsWith("(int)")) { //$NON-NLS-1$
		Number v = parseString(trim.substring("(int)".length()).trim()); //$NON-NLS-1$
		if (v != null)
			anInt = v.intValue();
		else
			return super.newInstance(initializationString);	// Can't decode it, let full parser handle it.
	} else {
		try {
			anInt = Integer.decode(trim).intValue();	// See if valid int format.
		} catch (NumberFormatException e) {
			return super.newInstance(initializationString);	// Can't decode it, let full parser handle it.
		}
	}

	return getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(anInt);
}
}


