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
package org.eclipse.jem.internal.proxy.remote;
/*
 *  $RCSfile: REMFloatTypeBeanProxy.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:39:07 $ 
 */


import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * Remote Implementation of INumberBeanProxy for the primitive "float".
 * It can answer all of the primitive number types (int, long, short, byte, double, float).
 * Creation date: (2/6/00 9:02:54 AM)
 * @author: Joe Winchester
 */
class REMFloatTypeBeanProxy extends REMConstantBeanProxy implements INumberBeanProxy {
	private final float fFloat;

/**
 * It is package protected because they are created
 * in a special way and no one else should create them.
 * @param aBean java.lang.Object
 */
REMFloatTypeBeanProxy(REMProxyFactoryRegistry aRegistry, float aFloat) {
	super(aRegistry);
	fFloat = aFloat;
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
	if (anObject instanceof REMFloatTypeBeanProxy)
		return fFloat == ((REMFloatTypeBeanProxy) anObject).floatValue();
	if (anObject instanceof Double)
		return fFloat == ((Float) anObject).floatValue();
	return false;
}

/* (non-Javadoc)
 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
 */
public boolean sameAs(IBeanProxy aBeanProxy) {
	if (this == aBeanProxy)
		return true;	// Identity
	if (aBeanProxy instanceof REMFloatTypeBeanProxy)
		return fFloat == ((REMFloatTypeBeanProxy) aBeanProxy).floatValue();
	return false;
}
/**
 * byteValue method comment.
 */
public byte byteValue() {
	return (byte) fFloat;
}
/**
 * doubleValue method comment.
 */
public double doubleValue() {
	return fFloat;
}
/**
 * floatValue method comment.
 */
public float floatValue() {
	return fFloat;
}
/**
 * Return the int value
 */
public int intValue() {
	return (int) fFloat;
}
/**
 * longValue method comment.
 */
public long longValue() {
	return (long) fFloat;
}
/**
 * numberValue method comment.
 */
public Number numberValue() {
	return new Float(fFloat);
}
/**
 * shortValue method comment.
 */
public short shortValue() {
	return (short) fFloat;
}

/**
 * The bean's toString.
 */
public String toBeanString() {
	return String.valueOf(fFloat);
}

/**
 * Get the beantype
 */
public IBeanTypeProxy getTypeProxy() {
	return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).floatType;
}

/**
 * Render the bean into value object.
 */
public void renderBean(Commands.ValueObject value) {
	value.set(fFloat);
}
}


