package org.eclipse.jem.internal.proxy.ide;
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
 *  $RCSfile: IDEIntegerClassBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.IIntegerBeanProxy;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;

/**
 * IDE Implementation of IIntegerBeanTypeProxy
 */
final class IDEIntegerClassBeanTypeProxy extends IDENumberBeanTypeProxy {
// Some values are cache'd for speed
	private final IDEIntegerBeanProxy zeroIntProxy;
	private final IDEIntegerBeanProxy oneIntProxy;
	private final IDEIntegerBeanProxy twoIntProxy;
	private final IDEIntegerBeanProxy threeIntProxy;
IDEIntegerClassBeanTypeProxy(ProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass, new Integer(0));
	// Create the cached values
	zeroIntProxy = new IDEIntegerBeanProxy(aRegistry, new Integer(0), this );	
	oneIntProxy = new IDEIntegerBeanProxy(aRegistry, new Integer(1), this );
	twoIntProxy = new IDEIntegerBeanProxy(aRegistry, new Integer(2), this );
	threeIntProxy = new IDEIntegerBeanProxy(aRegistry, new Integer(3), this );
		
}
/**
 * Static helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
IIntegerBeanProxy createIntegerBeanProxy(Integer anInteger) {
	switch(anInteger.intValue()){
		case 0: return zeroIntProxy ;
		case 1: return oneIntProxy ;
		case 2: return twoIntProxy ;
		case 3: return threeIntProxy ;
		default: return new IDEIntegerBeanProxy(fProxyFactoryRegistry,anInteger,this);
	}
}
protected IIDEBeanProxy newBeanProxy(Object anObject){

	return (IIDEBeanProxy)createIntegerBeanProxy((Integer)anObject);

}
}

