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
 *  $RCSfile: PrefixOperator.java,v $
 *  $Revision: 1.2 $  $Date: 2005/05/18 23:11:26 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;

/**
 * Enum for prefix operator.
 * 
 * @since 1.1.0
 */
public class PrefixOperator extends AbstractEnum {

	/**
	 * Prefix plus "+"
	 */
	public static final int PRE_PLUS_VALUE = 0;

	public static final PrefixOperator PRE_PLUS = new PrefixOperator(PRE_PLUS_VALUE, "Prefix +"); //$NON-NLS-1$

	/**
	 * Prefix minus "-"
	 */
	public static final int PRE_MINUS_VALUE = 1;

	public static final PrefixOperator PRE_MINUS = new PrefixOperator(PRE_MINUS_VALUE, "Prefix -"); //$NON-NLS-1$

	/**
	 * Prefix bitwise complement "~"
	 */
	public static final int PRE_COMPLEMENT_VALUE = 2;

	public static final PrefixOperator PRE_COMPLEMENT = new PrefixOperator(PRE_COMPLEMENT_VALUE, "Prefix ~"); //$NON-NLS-1$

	/**
	 * Prefix logical not "!"
	 */
	public static final int PRE_NOT_VALUE = 3;

	public static final PrefixOperator PRE_NOT = new PrefixOperator(PRE_NOT_VALUE, "Prefix !"); //$NON-NLS-1$

	/**
	 * Return the prefix operator for the given enum value.
	 * @param value
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public static PrefixOperator get(int value) {
		switch (value) {
			case PRE_PLUS_VALUE:
				return PRE_PLUS;
			case PRE_MINUS_VALUE:
				return PRE_MINUS;
			case PRE_COMPLEMENT_VALUE:
				return PRE_COMPLEMENT;
			case PRE_NOT_VALUE:
				return PRE_NOT;
		}
		return null;
	}

	private PrefixOperator(int value, String name) {
		super(value, name);
	}

}