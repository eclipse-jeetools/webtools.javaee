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
 *  $RCSfile: REMFloatClassBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of IBeanTypePRoxy for Float.class
 * Creation date: (2/6/00 9:01:40 AM)
 * @author: Joe Winchester
 */
final class REMFloatClassBeanTypeProxy extends REMAbstractNumberBeanTypeProxy {
// Some values are cache'd for speed
	private final INumberBeanProxy zeroProxy;
/**
 * REMIntegerBeanTypeProxy constructor comment.
 * @param aClass java.lang.Class
 */
REMFloatClassBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy numberType) {
	super(aRegistry, new Integer(Commands.FLOAT_CLASS), Float.class.getName(), numberType);
	
	// Create the cached values
	zeroProxy = new REMFloatClassBeanProxy(aRegistry, new Float(0F));	
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createFloatBeanProxy(Float aFloat) {
	return (aFloat.floatValue() != 0F) ?
		new REMFloatClassBeanProxy(fRegistry, aFloat) : zeroProxy;
}

/**
 * Generic way to create an Float proxy, however, the Number must be a Float.
 */
protected INumberBeanProxy createNumberBeanProxy(Number aNumber) {
	return createFloatBeanProxy((Float) aNumber);
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
	return createFloatBeanProxy(new Float(value.aFloat));
}	

}


