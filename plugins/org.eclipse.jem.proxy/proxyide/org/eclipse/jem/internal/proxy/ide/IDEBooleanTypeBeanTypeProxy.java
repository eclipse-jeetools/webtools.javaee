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
 *  $RCSfile: IDEBooleanTypeBeanTypeProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2004/02/03 23:18:36 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;

final public class IDEBooleanTypeBeanTypeProxy extends IDEPrimitiveBeanTypeProxy {

	// Cache these instances. Since the BeanType proxies are cached also, only one instance
	// of true/false will be in system.
	protected final IDEBooleanBeanProxy trueProxy;
	protected final IDEBooleanBeanProxy falseProxy;

IDEBooleanTypeBeanTypeProxy(IDEProxyFactoryRegistry aRegistry, Class aClass) {

	super(aRegistry, aClass);
	trueProxy = new IDEBooleanBeanTypeProxy( fProxyFactoryRegistry, Boolean.TRUE, this );
	falseProxy = new IDEBooleanBeanTypeProxy( fProxyFactoryRegistry, Boolean.FALSE, this );
	
	// TODO Need to go through all of the primitives, separate them out into a separate classes so
	// that sameAs can work with them correctly. Currently only boolean type will do sameAs correctly.
	// Should also look into storing the primitive in the proxy instead of creating a new wrapper object
	// for each for the non-boolean primitives.	
}
/**
 * Static helper to create a bean proxy
 * Package protected because everyone should go through the factory API
 * that is defined as part of IBeanProxyFactory
 */
IBooleanBeanProxy createBooleanBeanProxy(boolean aBoolean) {
	if ( aBoolean ){
		return trueProxy;
	} else {
		return falseProxy;
	}
}
/* Specialized from IDEBeanTypeProxy to ensure IBooleanBeanProxies are created correctly
 */
protected IIDEBeanProxy newBeanProxy(Object anObject){

	return (IIDEBeanProxy)createBooleanBeanProxy( anObject != null ? ((Boolean)anObject).booleanValue() : false );

}
int getPrimitiveType(){
	return BOOLEAN;
}
}
