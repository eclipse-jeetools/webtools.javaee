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
 *  $RCSfile: REMInterfaceBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
/**
 * Default beantype proxy for an interface.
 */

public class REMInterfaceBeanTypeProxy extends REMAbstractBeanTypeProxy {
	
	protected REMInterfaceBeanTypeProxy(REMProxyFactoryRegistry aRegistry, Integer anID, String aClassname) {
		super(aRegistry, anID, aClassname, null);
	}
	
	/** 
	 * Interfaces are interfaces.
	 */
	public boolean isInterface() {
		return true;
	}
	
	/**
	 * Create a new bean proxy with the specified id.
	 *
	 * For all of the interface types, this is an invalid operation. 
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
	 * For all of the interface types, you can't create a newBeanType for subclasses since
	 * interface aren't subclassable, only extendable.
	 */
	public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstact) {
		throw new UnsupportedOperationException();
	}
	
	/**
	 * Note: Since new instances of interfaces can never be done,
	 * this function is not supported.
	 */
	public IBeanProxy newInstance() { 
		throw new UnsupportedOperationException();
	}
	
	/**
	 * newInstance(initializationString) is valid because it can be used to create
	 * an instance of an implementation of the interface.
	 */

}