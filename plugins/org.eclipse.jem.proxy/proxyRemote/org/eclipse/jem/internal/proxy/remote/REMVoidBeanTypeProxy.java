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
 *  $RCSfile: REMVoidBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * BeanTypeProxy for representing the "void" type value, and a value of this type is "null".
 * Creation date: (2/11/00 2:26:47 PM)
 * @author: Richard Lee Kulp
 */
final class REMVoidBeanTypeProxy extends REMAbstractBeanTypeProxy implements IREMConstantBeanTypeProxy {

	
protected REMVoidBeanTypeProxy(REMProxyFactoryRegistry aRegistry) {
	super(aRegistry, new Integer(Commands.VOID_TYPE), Void.TYPE.getName(), null);
}

/**
 * Return the supertype for us
 * null/void has no superclass.
 * This is required as part of the interface
 */
public IBeanTypeProxy getSuperBeanTypeProxy() {
	return null;
}

/**
 * Create a new bean proxy with the specified id.
 *
 * For void, this is an invalid operation. They are created
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
 * For void is a final class, so you can't create a newBeanType 
 * for subclasses.
 */
public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
	throw new UnsupportedOperationException();
}

/**
 * newInstance method. void represents "null",
 * so return the null for this case because that is the default value.
 */
public IBeanProxy newInstance() {
	return null;
}

/**
 * Create a new bean proxy the ValueObject passed in.
 * We will assume the object is correct type.
 */
public IBeanProxy newBeanProxy(Commands.ValueObject value) {
	return newInstance();
}

}


