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
 *  $RCSfile: REMAnAbstractBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;

/**
 * This is a proxy for an abstract bean type. I.e. you can't create a new instance of it.
 * It can be used as a general abstract type. It will pass newBeanTypeForClass unto
 * the main super type passed in.
 */
 
public class REMAnAbstractBeanTypeProxy extends REMAbstractBeanTypeProxy {

	protected IREMSpecialBeanTypeProxy fMainSuperType;	// The type that this was created from so that
							// any non-abstract subclasses can create the correct
							// type of beantype proxy.
					
	public REMAnAbstractBeanTypeProxy(REMProxyFactoryRegistry aRegistry, Integer anID, String aClassname, IBeanTypeProxy aSuperType, IREMSpecialBeanTypeProxy aType) {
		super(aRegistry, anID, aClassname, aSuperType);
		fMainSuperType = aType;
	}
	
	/**
	 * Create a new bean proxy with the specified id.
	 *
	 * For all of the abstract types, this is an invalid operation. 
	 */
	public IREMBeanProxy newBeanProxy(Integer anID) {
		throw new UnsupportedOperationException();
	}	
	
	/**
	 * Note: Since new instances of abstract classes can never be done,
	 * this function is not supported.
	 */
	public IBeanProxy newInstance() { 
		throw new UnsupportedOperationException();
	}
	
	/**
	 * newBeanTypeForClass: Create a new beantypeproxy of this kind for the given class.
	 * This is used by beantype proxy factory when creating a beantype proxy for a subclass.
	 * This is because subclasses, unless explicitly overridden, should use the same kind
	 * of beantype proxy. This way if anything special is needed for a specific type, then
	 * that can be done.
	 */
	public IREMBeanTypeProxy newBeanTypeForClass(Integer anID, String aClassname, boolean anAbstract) {
		return fMainSuperType.newBeanTypeForClass(anID, aClassname, anAbstract, this);
	}
	
	/**
	 * release - BeanType is about to be released.
	 * Clear the fMainSupertype (supertype is cleared so that
	 * it won't hold onto it and will let the supertype be GC'd
	 * if necessary.
	 */
	public void release() {
		fMainSuperType = null;
		super.release();
	}					
}