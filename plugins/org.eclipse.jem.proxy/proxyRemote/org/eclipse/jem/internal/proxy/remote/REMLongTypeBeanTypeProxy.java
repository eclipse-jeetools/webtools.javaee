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
 *  $RCSfile: REMLongTypeBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of IBeanTypeProxy for Long.TYPE/
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMLongTypeBeanTypeProxy extends REMPrimitiveBeanTypeProxy {
// Some values are cache'd for speed
	private final INumberBeanProxy zeroProxy;
	private final INumberBeanProxy oneProxy;
	private final INumberBeanProxy twoProxy;
	private final INumberBeanProxy threeProxy;
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMLongTypeBeanTypeProxy(REMProxyFactoryRegistry aRegistry) {
	super(aRegistry, new Integer(Commands.LONG_TYPE), Long.TYPE.getName());
	
	// Create the cached values
	zeroProxy = new REMLongTypeBeanProxy(aRegistry, 0);	
	oneProxy = new REMLongTypeBeanProxy(aRegistry, 1);
	twoProxy = new REMLongTypeBeanProxy(aRegistry, 2);
	threeProxy = new REMLongTypeBeanProxy(aRegistry, 3);		
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createLongBeanProxy(long aLong) {
	if (aLong >= 0 && aLong < 4) {
		// Switch statements don't work with longs.
		switch((int) aLong) {
			case 0: return zeroProxy;
			case 1: return oneProxy ;
			case 2: return twoProxy ;
			case 3: return threeProxy ;
		}
	} 
	return new REMLongTypeBeanProxy(fRegistry, aLong);
}

/**
 * newInstance method. long is a primitive, so the standard way won't work.
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
	return createLongBeanProxy(value.aLong);
}

/**
 * Short cut for newInstance where the string follows the
 * specific pattern we expect. That way it won't be necessary to ship over to the vm 
 * to process it.
 *
 * The pattern it can accept is:
 * 	1) (long) validNumber - number can be any number because (byte) explicitly casts it down.
 *	2) validNumber
 * It will use the decode function of long to determine the valid number.
 */
public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
	String trim = initializationString.trim();
	if (trim.length() == 0)
		return super.newInstance(initializationString);
	long aLong;
	if (trim.startsWith("(long)")) { //$NON-NLS-1$
		Number v = parseString(trim.substring("(long)".length()).trim()); //$NON-NLS-1$
		if (v != null)
			aLong = v.longValue();
		else
			return super.newInstance(initializationString);	// Can't decode it, let full parser handle it.
	} else {
		if (trim.charAt(trim.length()-1) == 'L' || trim.charAt(trim.length()-1) == 'l')
			trim = trim.substring(0, trim.length()-1);
		try {
			aLong = Long.decode(trim).longValue();	// See if valid long format.
		} catch (NumberFormatException e) {
			return super.newInstance(initializationString);	// Can't decode it, let full parser handle it.
		}
	}

	return getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(aLong);
}

}


