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
 *  $RCSfile: REMBooleanClassBeanProxy.java,v $
 *  $Revision: 1.2 $  $Date: 2005/02/15 22:56:10 $ 
 */


import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * Remote Implementation of IBooleanBeanProxy for Boolean.class.
 * Creation date: (2/6/00 9:02:54 AM)
 * @author: Joe Winchester
 */
final class REMBooleanClassBeanProxy extends REMConstantBeanProxy implements IBooleanBeanProxy {
	private final Boolean fBooleanValue;

/**
 * Store the boolean value so that we can return the booleanValue faster
 * without the need for going across the line. It is package protected because it is created in
 * special way, so no one else should create these.
 * @param aBean java.lang.Object
 */
REMBooleanClassBeanProxy(REMProxyFactoryRegistry aRegistry, Boolean value) {
	super(aRegistry);
	fBooleanValue = value;
}

/**
 * equals: Equal if:
 *         1) This proxy == (identity) to the other object
 *         2) Else if other is an IBeanProxy and not a constant one, then if
 *            equals on the server.
 *         3) If this is a constant proxy and the other is too or is a constant
 *            value (e.g. IStringBeanProxy.equals(String), then true if values are equals.
 */
public boolean equals(Object anObject) {
	if (this == anObject)
		return true;	// Identity
	if (anObject instanceof REMBooleanClassBeanProxy)
		return fBooleanValue.booleanValue() == ((REMBooleanClassBeanProxy) anObject).booleanValue();
	if (anObject instanceof Boolean)
		return fBooleanValue.booleanValue() == ((Boolean) anObject).booleanValue();
	return false;
}

/**
 * Return the boolean value
 */
public boolean booleanValue() {
	return fBooleanValue.booleanValue();
}
/**
 * Return the java.lang.Boolean value
 */
public Boolean getBooleanValue() {
	return fBooleanValue;
}

/**
 * Return the toString of the bean itself.
 */
public String toBeanString() {
	return fBooleanValue.toString();
}

/**
 * Get the beantype
 */
public IBeanTypeProxy getTypeProxy() {
	return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).booleanClass;
}

/**
 * Render the bean into value object.
 */
public void renderBean(Commands.ValueObject value) {
	value.set(fBooleanValue);
}
}






