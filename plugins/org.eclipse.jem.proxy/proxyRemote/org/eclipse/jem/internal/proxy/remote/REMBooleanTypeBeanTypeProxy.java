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
 *  $RCSfile: REMBooleanTypeBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Boolean BeanType Proxy for Boolean.TYPE
 * Creation date: (2/23/00 1:59:02 PM)
 * @author: Richard Lee Kulp
 */
final class REMBooleanTypeBeanTypeProxy extends REMPrimitiveBeanTypeProxy {

	// Cache these instances. Since the BeanType proxies are cached also, only one instance
	// of true/false will be in system.
	protected final IBooleanBeanProxy trueProxy;
	protected final IBooleanBeanProxy falseProxy;
/**
 * REMBooleanBeanTypeProxy constructor comment.
 */
protected REMBooleanTypeBeanTypeProxy(REMProxyFactoryRegistry aRegistry) {
	super(aRegistry, new Integer(Commands.BOOLEAN_TYPE), Boolean.TYPE.getName());
	
	trueProxy = new REMBooleanTypeBeanProxy( fRegistry, true);
	falseProxy = new REMBooleanTypeBeanProxy( fRegistry, false);	
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 * Use the cache.
 */
IBooleanBeanProxy createBooleanBeanProxy(boolean aBoolean) {
	return aBoolean ? trueProxy : falseProxy;
}


/**
 * newInstance method. boolean is a primitive, so the standard way won't work.
 * Return the falseProxy for this case because that is the default value.
 */
public IBeanProxy newInstance() {
	return falseProxy;
}

/**
 * Create a new bean proxy the ValueObject passed in.
 * We will assume the object is correct type.
 */
public IBeanProxy newBeanProxy(Commands.ValueObject value) {
	return createBooleanBeanProxy(value.aBool);
}
}


