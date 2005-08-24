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
 *  $RCSfile: IDENumberBeanProxy.java,v $
 *  $Revision: 1.6 $  $Date: 2005/08/24 20:39:06 $ 
 */

import org.eclipse.jem.internal.proxy.core.*;
/**
 * IDE Implementation of INumberBeanProxy for any Number (i.e. the non-primitives)
 * It can answer all of the primitive number types (int, long, short, byte, double, float).
 * Creation date: (2/6/00 9:02:54 AM)
 * @author: Joe Winchester
 */
public class IDENumberBeanProxy extends IDEBeanProxy implements INumberBeanProxy {
	private final Number fNumberValue;
	protected IBeanTypeProxy fBeanTypeProxy;
	
/**
 * It is package protected because they are created
 * in a special way and no one else should create them.
 * @param aBean java.lang.Object
 */
IDENumberBeanProxy(IDEProxyFactoryRegistry aRegistry, Number aNumber , IBeanTypeProxy aBeanTypeProxy) {
	super(aRegistry,aNumber);
	fNumberValue = aNumber;
	fBeanTypeProxy = aBeanTypeProxy;
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
	if (anObject instanceof IDENumberBeanProxy)
		return fNumberValue.equals(((IDENumberBeanProxy) anObject).numberValue());
	if (anObject instanceof Number)
		return fNumberValue.equals(anObject);
	return false;
}
/**
 * byteValue method comment.
 */
public byte byteValue() {
	return fNumberValue.byteValue();
}
/**
 * doubleValue method comment.
 */
public double doubleValue() {
	return fNumberValue.doubleValue();
}
/**
 * floatValue method comment.
 */
public float floatValue() {
	return fNumberValue.floatValue();
}
/**
 * Return the int value
 */
public int intValue() {
	return fNumberValue.intValue();
}
/**
 * longValue method comment.
 */
public long longValue() {
	return fNumberValue.longValue();
}
/**
 * numberValue method comment.
 */
public Number numberValue() {
	return fNumberValue;
}
/**
 * shortValue method comment.
 */
public short shortValue() {
	return fNumberValue.shortValue();
}

/**
 * The bean's toString.
 */
public String toBeanString() {
	return fNumberValue.toString();
}
public IBeanTypeProxy getTypeProxy(){
	return fBeanTypeProxy;
}
}
