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
 *  $RCSfile: REMClassBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * This is a bean type for the Class object. It is needed so that
 * when a new object id comes in that represents a class, it will
 * go through this bean type to create it. We need to do something
 * special so that the correct bean type object is created.
 */

final class REMClassBeanTypeProxy extends REMAbstractBeanTypeProxy {


REMClassBeanTypeProxy(REMProxyFactoryRegistry aRegistry, IBeanTypeProxy objectType) {
	super(aRegistry, new Integer(Commands.CLASS_CLASS), Class.class.getName(), objectType);
}

/**
 * Note: Since new instances of Class are never done through a default ctor,
 * this function is not supported.
 */
public IBeanProxy newInstance() { 
	throw new UnsupportedOperationException();
}

/**
 * Note: Since new instances of Class are never done through a initialization strings,
 * this function is not supported.
 */
public IBeanProxy newInstance(String initializationString) {
	throw new UnsupportedOperationException();
}		

/**
 * newBeanTypeForClass: Create a new beantypeproxy of this kind for the given class.
 * This is used by beantype proxy factory when creating a beantype proxy for a subclass.
 * This is because subclasses, unless explicitly overridden, should use the same kind
 * of beantype proxy. This way if anything special is needed for a specific type, then
 * that can be done.
 *
 * NOTE: Since the Class type is final, this function isn't supported.
 */
public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
	throw new UnsupportedOperationException();
}

/**
 * Create a new bean proxy with the specified id.
 *
 * In this case it means we need a new IBeanProxyType. We will let the 
 * Beantype factory handle this since it knows how to get the information
 * for a specific id.
 */
public IREMBeanProxy newBeanProxy(Integer anID) {
	return ((REMStandardBeanTypeProxyFactory) fRegistry.getBeanTypeProxyFactory()).createBeanTypeProxy(anID);
}


}