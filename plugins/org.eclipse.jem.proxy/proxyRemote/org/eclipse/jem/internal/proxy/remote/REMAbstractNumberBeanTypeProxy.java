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
 *  $RCSfile: REMAbstractNumberBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;

/**
 * Abstract class for any of the number bean type subclasses.
 * NOTE: This does not represent the beantype Number. It is a
 * class that provides functions for Number subclass beantypes.
 * The Number beantype itself is simply a REMBeanTypeProxy since
 * Number is abstract and has no necessary extra function.
 * Creation date: (08/31/00 11:49:12 AM)
 * @author: Administrator
 */
public abstract class REMAbstractNumberBeanTypeProxy extends REMAbstractBeanTypeProxy implements IREMConstantBeanTypeProxy {

protected REMAbstractNumberBeanTypeProxy(REMProxyFactoryRegistry aRegistry, Integer anID, String aClassname, IBeanTypeProxy aSuperType) {
	super(aRegistry, anID, aClassname, aSuperType);
}
/**
 * Helper to create a bean proxy
 * protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
protected abstract INumberBeanProxy createNumberBeanProxy(Number aNumber);

/**
 * Create a new bean proxy with the specified id.
 *
 * For all of the Number types, this is an invalid operation. They are created
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
 * For all of the Number types, they are final classes, so you can't create a newBeanType 
 * for subclasses.
 */
public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
	throw new UnsupportedOperationException();
}

/**
 * newInstance method. Number's don't have a default ctor, so the standard way won't work.
 * Each beantype will return a default value (usually a value of 0).
 */
public abstract IBeanProxy newInstance() throws ThrowableProxy;
}


