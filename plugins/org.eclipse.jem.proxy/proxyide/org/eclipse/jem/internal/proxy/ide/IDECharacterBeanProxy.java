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
package org.eclipse.jem.internal.proxy.ide;
/*
 * $RCSfile: IDECharacterBeanProxy.java,v $ $Revision: 1.3 $ $Date: 2004/08/27 15:35:20 $
 */

import org.eclipse.jem.internal.proxy.core.*;
/**
 * IDE Implementation of ICharacterBeanBeanProxy..
 */
class IDECharacterBeanProxy extends IDEObjectBeanProxy implements ICharacterBeanProxy {
	protected Character fCharacterValue;
	IDECharacterBeanProxy(IDEProxyFactoryRegistry aRegistry, Object aBean, IBeanTypeProxy aBeanTypeProxy) {
		super(aRegistry, aBean, aBeanTypeProxy);
		fCharacterValue = (Character) aBean;
	}
	public char charValue() {
		return fCharacterValue.charValue();
	}
	public Character characterValue() {
		return fCharacterValue;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jem.internal.proxy.core.IIntegerBeanProxy#intValue()
	 */
	public int intValue() {
		return (int) charValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#byteValue()
	 */
	public byte byteValue() {
		return (byte) charValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#doubleValue()
	 */
	public double doubleValue() {
		return (double) charValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#floatValue()
	 */
	public float floatValue() {
		return (float) charValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#longValue()
	 */
	public long longValue() {
		return (long) charValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#numberValue()
	 */
	public Number numberValue() {
		// This is a kludge. Need to new one up.
		return new Integer(charValue());
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jem.internal.proxy.core.INumberBeanProxy#shortValue()
	 */
	public short shortValue() {
		return (short) charValue();
	}

}
