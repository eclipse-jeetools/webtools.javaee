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
 *  $RCSfile: REMDoubleTypeBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of IBeanTypeProxy for Double.TYPE
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMDoubleTypeBeanTypeProxy extends REMPrimitiveBeanTypeProxy {
// Some values are cache'd for speed
	private final INumberBeanProxy zeroProxy;
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMDoubleTypeBeanTypeProxy(REMProxyFactoryRegistry aRegistry) {
	super(aRegistry, new Integer(Commands.DOUBLE_TYPE), Double.TYPE.getName());
	
	// Create the cached values
	zeroProxy = new REMDoubleTypeBeanProxy(aRegistry, 0D);		
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createDoubleBeanProxy(double aDouble) {
	return (aDouble != 0D) ?
		new REMDoubleTypeBeanProxy(fRegistry, aDouble) : zeroProxy;
}

/**
 * newInstance method. double is a primitive, so the standard way won't work.
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
	return createDoubleBeanProxy(value.aDouble);
}

/**
 * Short cut for newInstance where the string follows the
 * specific pattern we expect. That way it won't be necessary to ship over to the vm 
 * to process it.
 *
 * The pattern it can accept is:
 * 	1) (double) validNumber - number can be any number because (double) explicitly casts it down.
 *	2) validNumber
 * It will use the decode function of double to determine the valid number.
 */
public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
	String trim = initializationString.trim();
	if (trim.length() == 0)
		return super.newInstance(initializationString);	
	double aDouble;
	if (trim.startsWith("(double)")) { //$NON-NLS-1$
		Number v = parseString(trim.substring("(double)".length()).trim()); //$NON-NLS-1$
		if (v != null)
			aDouble = v.doubleValue();
		else
			return super.newInstance(initializationString);	// Can't decode it, let full parser handle it.
	} else {
		try {
			aDouble = Double.valueOf(initializationString).doubleValue();	// See if valid double format.
		} catch (NumberFormatException e) {
			return super.newInstance(initializationString);	// Can't decode it, let full parser handle it.
		}
	}

	return getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(aDouble);
}
}


