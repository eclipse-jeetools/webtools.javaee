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
 *  $RCSfile: IDEConstructorProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */

import java.lang.reflect.Constructor;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

import org.eclipse.jem.internal.proxy.core.*;

/**
 * This is an implementation of IConstructorProxy where the target VM is the same as
 * the IDE.  The java.lang.reflect.Constructor instance is held in a field
 */
class IDEConstructorProxy extends IDEBeanProxy implements IConstructorProxy {	
	protected Constructor fConstructor;

/**
 * package protected constructor where the instance of java.lang.reflect.Constructor
 * can be passed in directly
 */
IDEConstructorProxy(ProxyFactoryRegistry aRegistry, Constructor aConstructor) {
	super(aRegistry);
	fConstructor = aConstructor;
}
/**
 * The type proxy is constant proxy out of the method factory.
 */
public IBeanTypeProxy getTypeProxy() {
	return ((IDEMethodProxyFactory) fProxyFactoryRegistry.getMethodProxyFactory()).constructorType;
}

/**
 * This is factored into a special method so that subclasses can override
 * if they wish.
 */
protected IBeanProxy getNewIDEBeanProxy(Object aBean){

	return IDEStandardBeanProxyFactory.createBeanProxy(fProxyFactoryRegistry, aBean);	// Use local helper method to do this.
}
/**
 * Invoke the constructor and return an IDEBeanProxy with the result
 */
public IBeanProxy newInstance() {

// Invoke the constructor
	try {
		Object result = fConstructor.newInstance(null);
		return getNewIDEBeanProxy(result);
	} catch ( Exception e ) {
		ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e));
		return null;
	}

}
public IBeanProxy newInstanceCatchThrowableExceptions(){
	return newInstance();
}
public IBeanProxy newInstanceCatchThrowableExceptions(IBeanProxy[] args){
	return newInstance(args);
}
/**
 * Invoke the constructor and return an IDEBeanProxy with the result
 */
public IBeanProxy newInstance(IBeanProxy[] creationArguments) {

// Get an array of bean objects from the bean proxy arguments
	Object result = null;
	Object[] creationObjects = new Object[creationArguments.length];
	for (int i = 0; i < creationArguments.length ; i++){
// It is possible the arguments are null which is perfectly valid
		if ( creationArguments[i] != null ) 
			creationObjects[i] = ((IIDEBeanProxy)creationArguments[i]).getBean();
	}

// Invoke the constructor
	try {
		result = fConstructor.newInstance(creationObjects);
	} catch ( Exception e ) {
		ProxyPlugin.getPlugin().getMsgLogger().log(new Status(IStatus.WARNING, ProxyPlugin.getPlugin().getDescriptor().getUniqueIdentifier(), 0, "", e));
	}

// If we have a non-null result create an IDEBeanProxy and return it
	if ( result != null ) {
		return getNewIDEBeanProxy(result);
	} else {
		return null;
	}
	
}
}


