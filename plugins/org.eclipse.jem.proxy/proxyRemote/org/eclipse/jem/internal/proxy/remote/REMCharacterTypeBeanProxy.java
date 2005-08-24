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
 * $RCSfile: REMCharacterTypeBeanProxy.java,v $ $Revision: 1.6 $ $Date: 2005/08/24 20:39:07 $
 */

import org.eclipse.jem.internal.proxy.common.remote.Commands;
import org.eclipse.jem.internal.proxy.core.*;
/**
 * Remote implementation of ICharacterBeanProxy. Creation date: (2/6/00 9:02:54 AM) @author: Joe
 * Winchester
 */
final class REMCharacterTypeBeanProxy extends REMConstantBeanProxy implements ICharacterBeanProxy {
	protected char fChar;

	REMCharacterTypeBeanProxy(REMProxyFactoryRegistry aRegistry, char value) {
		super(aRegistry);
		fChar = value;
	}

	/**
	 * equals: Equal if: 1) This proxy == (identity) to the other object 2) Else if other is an
	 * IBeanProxy and not a constant one, then if equals on the server. 3) If this is a constant
	 * proxy and the other is too or is a constant value (e.g. IStringBeanProxy.equals(String),
	 * then true if values are equals.
	 */
	public boolean equals(Object anObject) {
		if (this == anObject)
			return true; // Identity
		if (anObject instanceof REMCharacterTypeBeanProxy)
			return fChar == ((REMCharacterTypeBeanProxy) anObject).charValue();
		if (anObject instanceof Character)
			return fChar == ((Character) anObject).charValue();
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IBeanProxy#sameAs(org.eclipse.jem.internal.proxy.core.IBeanProxy)
	 */
	public boolean sameAs(IBeanProxy aBeanProxy) {
		if (this == aBeanProxy)
			return true; // Identity
		if (aBeanProxy instanceof REMCharacterTypeBeanProxy)
			return fChar == ((REMCharacterTypeBeanProxy) aBeanProxy).charValue();
		return false;
	}

	/**
	 * Return the char value
	 */
	public char charValue() {
		return fChar;
	}
	/**
	 * Return the java.lang.Character value
	 */
	public Character characterValue() {
		return new Character(fChar);
	}

	/**
	 * Answer the toString() of the value.
	 */
	public String toBeanString() {
		return String.valueOf(fChar);
	}

	/**
	 * Get the beantype
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).charType;
	}

	/**
	 * Render the bean into value object.
	 */
	public void renderBean(Commands.ValueObject value) {
		value.set(fChar);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#byteValue()
	 */
	public byte byteValue() {
		return (byte) fChar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#doubleValue()
	 */
	public double doubleValue() {
		return fChar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#floatValue()
	 */
	public float floatValue() {
		return fChar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#intValue()
	 */
	public int intValue() {
		return fChar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#longValue()
	 */
	public long longValue() {
		return fChar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#numberValue()
	 */
	public Number numberValue() {
		return new Integer(fChar); // Kludge
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#shortValue()
	 */
	public short shortValue() {
		return (short) fChar;
	}

}
