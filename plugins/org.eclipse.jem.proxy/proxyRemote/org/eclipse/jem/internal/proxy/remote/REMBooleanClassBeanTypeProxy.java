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
 *  $RCSfile: REMBooleanClassBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Boolean BeanType Proxy for Boolean.class. 
 * Creation date: (2/23/00 1:59:02 PM)
 * @author: Richard Lee Kulp
 */
final class REMBooleanClassBeanTypeProxy extends REMAbstractBeanTypeProxy implements IREMConstantBeanTypeProxy {

	// Cache these instances. Since the BeanType proxies are cached also, only one instance
	// of true/false will be in system.
	protected final IBooleanBeanProxy trueProxy;
	protected final IBooleanBeanProxy falseProxy;
/**
 * REMBooleanBeanTypeProxy constructor comment.
 */
protected REMBooleanClassBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy objectType) {
	super(aRegistry, new Integer(Commands.BOOLEAN_CLASS), Boolean.class.getName(), objectType);
	
	trueProxy = new REMBooleanClassBeanProxy( fRegistry, Boolean.TRUE);
	falseProxy = new REMBooleanClassBeanProxy( fRegistry, Boolean.FALSE);	
}

/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 * Use the cache.
 */
IBooleanBeanProxy createBooleanBeanProxy(Boolean aBoolean) {
	return aBoolean.booleanValue() ? trueProxy : falseProxy;
}

/**
 * Create a new bean proxy with the specified id.
 *
 * For all of the primitive wrapper types, this is an invalid operation. They are created
 * explicitly through the appropriate create method.
 */
public IREMBeanProxy newBeanProxy(Integer anID) {
	throw new UnsupportedOperationException();
}

/**
 * newBeanTypeForClass: Create a new beantypeproxy of this kind for the given class.
 * This is used by beantype proxy factory when creating a beantype proxy for a subclass.
 * This is because subclasses, unless explicitly overridden, should use the same kind
 * of beantype proxy. This way if anything special is needed for a specific type, then
 * that can be done.
 *
 * For all of the primitive wrapper types, they are final classes, so you can't create a newBeanType 
 * for subclasses.
 */
public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
	throw new UnsupportedOperationException();
}

/**
 * newInstance method. Boolean doesn't have a default ctor, so the standard way won't work.
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
	return createBooleanBeanProxy(value.aBool ? Boolean.TRUE : Boolean.FALSE);
}

}


