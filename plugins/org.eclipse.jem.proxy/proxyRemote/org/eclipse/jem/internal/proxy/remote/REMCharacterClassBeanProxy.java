/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.proxy.remote;
/*
 * $RCSfile: REMCharacterClassBeanProxy.java,v $ $Revision: 1.3 $ $Date: 2004/08/27 15:35:20 $
 */

import org.eclipse.jem.internal.proxy.core.*;
import org.eclipse.jem.internal.proxy.common.remote.Commands;
/**
 * IDE Implementation of ICharacterBeanProxy for the Character class. Creation date: (2/6/00
 * 9:02:54 AM) @author: Joe Winchester
 */
final class REMCharacterClassBeanProxy extends REMConstantBeanProxy implements ICharacterBeanProxy {
	protected Character fCharValue;

	/**
	 * As well as storing the bean store the char value so that we can return the charValue faster
	 * without the need for repeated re-casting. It is package protected because it is created in
	 * special way, so no one else should create these.
	 * 
	 * @param aBean
	 *            java.lang.Object
	 */
	REMCharacterClassBeanProxy(REMProxyFactoryRegistry aRegistry, Character value) {
		super(aRegistry);
		fCharValue = value;
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
		if (anObject instanceof REMCharacterClassBeanProxy)
			return fCharValue.charValue() == ((REMCharacterClassBeanProxy) anObject).charValue();
		if (anObject instanceof Character)
			return fCharValue.charValue() == ((Character) anObject).charValue();
		return false;
	}

	/**
	 * Return the char value
	 */
	public char charValue() {
		return fCharValue.charValue();
	}
	/**
	 * Return the java.lang.Character value
	 */
	public Character characterValue() {
		return fCharValue;
	}

	/**
	 * Answer the toString() of the value.
	 */
	public String toBeanString() {
		return fCharValue.toString();
	}

	/**
	 * Get the beantype
	 */
	public IBeanTypeProxy getTypeProxy() {
		return ((REMStandardBeanTypeProxyFactory) fFactory.getBeanTypeProxyFactory()).characterClass;
	}

	/**
	 * Render the bean into value object.
	 */
	public void renderBean(Commands.ValueObject value) {
		value.set(fCharValue);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#byteValue()
	 */
	public byte byteValue() {
		return (byte) fCharValue.charValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#doubleValue()
	 */
	public double doubleValue() {
		return (double) fCharValue.charValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#floatValue()
	 */
	public float floatValue() {
		return (float) fCharValue.charValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#intValue()
	 */
	public int intValue() {
		return (int) fCharValue.charValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#longValue()
	 */
	public long longValue() {
		return (long) fCharValue.charValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#numberValue()
	 */
	public Number numberValue() {
		return new Integer(charValue()); // Kludge.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#shortValue()
	 */
	public short shortValue() {
		return (short) fCharValue.charValue();
	}

}
