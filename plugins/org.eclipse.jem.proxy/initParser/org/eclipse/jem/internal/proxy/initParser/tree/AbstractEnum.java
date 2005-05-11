/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: AbstractEnum.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/11 19:01:12 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;

/**
 * Base abstract implementation of the enum.
 * @since 1.1.0
 */
public abstract class AbstractEnum implements Enum {

	/**
	 * The name of the enumerator.
	 */
	private final String name;

	/**
	 * The <code>int</code> value of the enumerator.
	 */
	private final int value;

	/**
	 * Creates an initialized instance.
	 * 
	 * @param value
	 *            the <code>int</code> value of the enumerator.
	 * @param name
	 *            the name of the enumerator.
	 */
	protected AbstractEnum(int value, String name) {
		this.name = name;
		this.value = value;
	}

	/**
	 * Returns the name of the enumerator.
	 * 
	 * @return the name.
	 */
	public final String getName() {
		return name;
	}

	/**
	 * Returns the <code>int</code> value of the enumerator.
	 * 
	 * @return the value.
	 */
	public final int getValue() {
		return value;
	}

	/**
	 * Returns the name of the enumerator.
	 * 
	 * @return the name.
	 */
	public final String toString() {
		return name;
	}

}