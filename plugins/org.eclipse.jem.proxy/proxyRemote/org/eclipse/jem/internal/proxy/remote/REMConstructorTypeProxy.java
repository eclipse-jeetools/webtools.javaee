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
 *  $RCSfile: REMConstructorTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * BeanType Proxy for Method class.
 */

final class REMConstructorTypeProxy extends REMAbstractBeanTypeProxy {

REMConstructorTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy accessibleTypeProxy) {
	super(aRegistry, new Integer(Commands.CONSTRUCTOR_CLASS), java.lang.reflect.Constructor.class.getName(), accessibleTypeProxy);
}

/**
 * newBeanTypeForClass: Create a new beantypeproxy of this kind for the given class.
 * This is used by beantype proxy factory when creating a beantype proxy for a subclass.
 * This is because subclasses, unless explicitly overridden, should use the same kind
 * of beantype proxy. This way if anything special is needed for a specific type, then
 * that can be done.
 *
 * For all of the accessible object types, they are final classes, so you can't create a newBeanType 
 * for subclasses.
 */
public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
	throw new UnsupportedOperationException();
}

/**
 * Specialized from REMAbstractBeanTypeProxy to ensure Constructor proxies are created correctly.
 */
public IREMBeanProxy newBeanProxy(Integer objectID) {
	return new REMConstructorProxy(fRegistry, objectID);
}

/**
 * Note: Since new instances of Constructor are never done through a default ctor,
 * this function is not supported.
 */
public IBeanProxy newInstance() { 
	throw new UnsupportedOperationException();
}

/**
 * Note: Since new instances of Constructor are never done through an initialization string,
 * this function is not supported.
 */
public IBeanProxy newInstance(String initializationString) {
	throw new UnsupportedOperationException();
}		

}
