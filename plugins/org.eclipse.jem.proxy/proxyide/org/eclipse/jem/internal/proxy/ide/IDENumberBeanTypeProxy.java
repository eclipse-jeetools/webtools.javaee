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
package org.eclipse.jem.internal.proxy.ide;
/*
 *  $RCSfile: IDENumberBeanTypeProxy.java,v $
 *  $Revision: 1.5 $  $Date: 2005/08/24 20:39:06 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
/**
 * Number BeanType Proxy.
 */
public abstract class IDENumberBeanTypeProxy extends IDEBeanTypeProxy {
	protected final IDENumberBeanProxy zeroProxy;
/**
 * IDENumberBeanTypeProxy constructor comment.
 * @param aRegistry com.ibm.etools.vce.proxy.ProxyFactoryRegistry
 * @param aClass java.lang.Class
 */
protected IDENumberBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass, Number zeroNumber) {
	super(aRegistry, aClass);
	// Cache a zero proxy of this type (though the number will be Long).
	zeroProxy = new IDENumberBeanProxy(aRegistry, zeroNumber , this);
}
/**
 * Helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
INumberBeanProxy createNumberBeanProxy(Number aNumber) {
	if (aNumber == null || zeroProxy.fBean.equals(aNumber))
		return zeroProxy;
	return new IDENumberBeanProxy(fProxyFactoryRegistry, aNumber, this);
}
/**
 * Specialized from IDEBeanTypeProxy to ensure INumberBeanProxies are created correctly.
 */
protected IIDEBeanProxy newBeanProxy(Object anObject){

	return (IIDEBeanProxy)createNumberBeanProxy((Number) anObject);

}
}
