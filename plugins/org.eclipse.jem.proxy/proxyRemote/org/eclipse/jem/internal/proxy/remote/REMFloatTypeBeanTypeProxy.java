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
 *  $RCSfile: REMFloatTypeBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of IBeanTypeProxy for Float.TYPE.
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMFloatTypeBeanTypeProxy extends REMPrimitiveBeanTypeProxy {
// Some values are cache'd for speed
	private final INumberBeanProxy zeroProxy;
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMFloatTypeBeanTypeProxy(REMProxyFactoryRegistry aRegistry) {
	super(aRegistry, new Integer(Commands.FLOAT_TYPE), Float.TYPE.getName());
	
	// Create the cached values
	zeroProxy = new REMFloatTypeBeanProxy(aRegistry, 0F);		
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createFloatBeanProxy(float aFloat) {
	return (aFloat != 0F) ?
		new REMFloatTypeBeanProxy(fRegistry, aFloat) : zeroProxy;
}

/**
 * newInstance method. float is a primitive, so the standard way won't work.
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
	return createFloatBeanProxy(value.aFloat);
}

/**
 * Short cut for newInstance where the string follows the
 * specific pattern we expect. That way it won't be necessary to ship over to the vm 
 * to process it.
 *
 * The pattern it can accept is:
 * 	1) (float) validNumber - number can be any number because (float) explicitly casts it down.
 *	2) validNumber
 * It will use the decode function of float to determine the valid number.
 */
public IBeanProxy newInstance(String initializationString) throws ThrowableProxy, ClassCastException, InstantiationException {
	String trim = initializationString.trim();
	if (trim.length() == 0)
		return super.newInstance(initializationString);	
	float aFloat;
	if (trim.startsWith("(float)")) { //$NON-NLS-1$
		Number v = parseString(trim.substring("(float)".length()).trim()); //$NON-NLS-1$
		if (v != null)
			aFloat = v.floatValue();
		else
			return super.newInstance(initializationString);	// Can't decode it, let full parser handle it.
	} else {
		try {
			aFloat = Float.valueOf(initializationString).floatValue();	// See if valid float format.
		} catch (NumberFormatException e) {
			return super.newInstance(initializationString);	// Can't decode it, let full parser handle it.
		}
	}

	return getProxyFactoryRegistry().getBeanProxyFactory().createBeanProxyWith(aFloat);
}
}


