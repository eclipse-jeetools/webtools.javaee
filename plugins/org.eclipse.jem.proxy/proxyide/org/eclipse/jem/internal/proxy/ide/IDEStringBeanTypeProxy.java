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
 *  $RCSfile: IDEStringBeanTypeProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import org.eclipse.jem.internal.proxy.core.IStringBeanProxy;
import org.eclipse.jem.internal.proxy.core.ProxyFactoryRegistry;
/**
 * IDE Implementation of the BeanType proxy for java.lang.String
 */
final class IDEStringBeanTypeProxy extends IDEBeanTypeProxy {
	// Cache a proxy to an empty string for speed
	protected final IDEStringBeanProxy emptyStringProxy;

IDEStringBeanTypeProxy(ProxyFactoryRegistry aRegistry, Class aClass) {
	super(aRegistry, aClass);
	// Cache a proxy to an empty string for speed
	emptyStringProxy = new IDEStringBeanProxy(aRegistry, "",this);	//$NON-NLS-1$
}
/**
 * Helper to create string beans
 * Package protected because no-one else outside the package should use this, they
 * must go through the API where you look up a factory first.
 */
IStringBeanProxy createStringBeanProxy(String aString){
	if ( aString.equals("") ) {//$NON-NLS-1$
		return emptyStringProxy;
	} else {
		return new IDEStringBeanProxy(fProxyFactoryRegistry,aString,this);
	}
}
/* Specialized from IDEBeanTypeProxy to ensure IStringBeanProxies are created correctly
 */
protected IIDEBeanProxy newBeanProxy(Object anObject){

	return (IIDEBeanProxy)createStringBeanProxy( (String)anObject );

}
}
