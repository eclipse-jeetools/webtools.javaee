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
 *  $RCSfile: REMBooleanTypeBeanProxy.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:22:23 $ 
 */


import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * Remote Implementation of IBooleanBeanProxy for Boolean.TYPE.
 * Creation date: (2/6/00 9:02:54 AM)
 * @author: Joe Winchester
 */
final class REMBooleanTypeBeanProxy extends REMConstantBeanProxy implements IBooleanBeanProxy {
	private final boolean fBoolean;

/**
 * Store the boolean value so that we can return the booleanValue faster
 * without the need for going across the line. It is package protected because it is created in
 * special way, so no one else should create these.
 * @param aBean java.lang.Object
 */
REMBooleanTypeBeanProxy(REMProxyFactoryRegistry aRegistry, boolean value) {
	super(aRegistry);
	fBoolean = value;
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
	if (anObject instanceof REMBooleanTypeBeanProxy)
		return fBoolean == ((REMBooleanTypeBeanProxy) anObject).booleanValue();
	if (anObject instanceof Boolean)
		return fBoolean == ((Boolean) anObject).booleanValue();
	return false;
}

/* (non-Javadoc)
 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
 */
public boolean sameAs(IBeanProxy aBeanProxy) {
	if (this == aBeanProxy)
		return true;	// Identity
	if (aBeanProxy instanceof REMBooleanTypeBeanProxy)
		return fBoolean == ((REMBooleanTypeBeanProxy) aBeanProxy).booleanValue();
	return false;
}

/**
 * Return the boolean value
 */
public boolean booleanValue() {
	return fBoolean;
}
/**
 * Return the java.lang.Boolean value
 */
public Boolean getBooleanValue() {
	return fBoolean ? Boolean.TRUE : Boolean.FALSE;
}

/**
 * Return the toString of the bean itself.
 */
public String toBeanString() {
	return fBoolean ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
}

/**
 * Get the beantype
 */
public IBeanTypeProxy getTypeProxy() {
	return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).booleanType;
}

/**
 * Render the bean into value object.
 */
public void renderBean(Commands.ValueObject value) {
	value.set(fBoolean);
}

}


