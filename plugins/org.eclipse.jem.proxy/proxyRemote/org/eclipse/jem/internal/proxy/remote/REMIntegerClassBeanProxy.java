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
 *  $RCSfile: REMIntegerClassBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Proxy for Integers. Since Integers were first implemented
 * in the VCE, and because they are often referenced, we've created
 * a subclass to handle them. That way we wouldn't need to change
 * everyone that was using the IIntegerProxy interface.
 * Creation date: (08/31/00 11:28:48 AM)
 * @author: Administrator
 */
class REMIntegerClassBeanProxy extends REMNumberBeanProxy implements IIntegerBeanProxy {

REMIntegerClassBeanProxy(REMProxyFactoryRegistry aRegistry, Integer anInteger) {
	super(aRegistry, anInteger);
}

/**
 * Get the beantype
 */
public IBeanTypeProxy getTypeProxy() {
	return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).integerClass;
}

/**
 * Render the bean into value object.
 */
public void renderBean(Commands.ValueObject value) {
	value.set((Integer) numberValue());
}
}


