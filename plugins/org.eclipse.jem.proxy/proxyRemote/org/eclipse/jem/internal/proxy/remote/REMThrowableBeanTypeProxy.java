/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMThrowableBeanTypeProxy.java,v $
 *  $Revision: 1.3 $  $Date: 2005/08/24 20:39:06 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
/**
 * The Remote implementation of a Throwable bean type.
 */
public class REMThrowableBeanTypeProxy extends REMAbstractBeanTypeProxy implements IREMSpecialBeanTypeProxy {
	
	protected REMThrowableBeanTypeProxy(REMProxyFactoryRegistry aRegistry, Integer anID, String aClassname, IBeanTypeProxy aSuperType) {
		super(aRegistry, anID, aClassname, aSuperType);
	}	
	
	/**
	 * Helper to create a bean proxy
	 * Package protected because everyone should go through the factory API
	 * that is defined as part of IBeanProxyFactory
	 */
	REMThrowableBeanProxy createThrowableBeanProxy(Integer objectID) {
		return new REMThrowableBeanProxy(fRegistry, objectID, this);
	}
	
	/**
	 * newBeanTypeForClass: Create a new beantypeproxy of this kind for the given class.
	 * This is used by beantype proxy factory when creating a beantype proxy for a subclass.
	 * This is because subclasses, unless explicitly overridden, should use the same kind
	 * of beantype proxy. This way if anything special is needed for a specific type, then
	 * that can be done.
	 *
	 * NOTE: All subclasses must override this to return the correct type of BeanTypeProxy.
	 */
	public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
		return newBeanTypeForClass(anID, aClassname, anAbstract, this);
	}

	/**
	 * The special method to handle subclassing from an abstract).
	 */		
	public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract, IBeanTypeProxy superType) {
		// We can never have an interface of type Throwable since that doesn't exist.
		if (!anAbstract)
			return new REMThrowableBeanTypeProxy(fRegistry, anID, aClassname, superType);
		else
			return new REMAnAbstractBeanTypeProxy(fRegistry, anID, aClassname, superType, this);
	}
	
	/**
	 * Specialized from REMAbstractBeanTypeProxy to ensure ExceptionBeanProxies are created correctly.
	 */
	public IREMBeanProxy newBeanProxy(Integer objectID) {
		return createThrowableBeanProxy(objectID);
	}	

}
