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
 *  $RCSfile: REMBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.IBeanTypeProxy;
/**
 * Default implementation for a BeanTypeProxy. This is what is used
 * for regular default beans and beantypes when no special beans or bean types are needed.
 */
public class REMBeanTypeProxy extends REMAbstractBeanTypeProxy implements IREMSpecialBeanTypeProxy {

	/**
	 * Create with a registry and a class. It is protected so that only subclasses
	 * and the factory with this REM package can call it.
	 */
	public REMBeanTypeProxy(REMProxyFactoryRegistry aRegistry, Integer anID, String aClassname, IBeanTypeProxy aSuperType) {
		super(aRegistry, anID, aClassname, aSuperType);
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
		if (!anAbstract) 
			return new REMBeanTypeProxy(fRegistry, anID, aClassname, superType);
		else
			return new REMAnAbstractBeanTypeProxy(fRegistry, anID, aClassname, superType, this);
	}
	
	/**
	 * Create a new bean proxy with the specified id.
	 */
	public IREMBeanProxy newBeanProxy(Integer anID){
	
		return new REMBeanProxy(fRegistry, anID, this);
	
	}


}