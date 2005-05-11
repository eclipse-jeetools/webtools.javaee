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
 *  $RCSfile: InternalConditionalOperandType.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/11 19:01:12 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;

/**
 * Internal class for the Conditional operand type. Not meant to be used by customers.
 * 
 * @since 1.1.0
 */
public class InternalConditionalOperandType extends AbstractEnum {

	/**
	 * Used in Conditional processing. False operand on expression stack
	 */
	public final static int CONDITIONAL_FALSE_VALUE = 0;

	public final static InternalConditionalOperandType CONDITIONAL_FALSE = new InternalConditionalOperandType(CONDITIONAL_FALSE_VALUE,
			"Conditional False Operand Flag");

	/**
	 * Used in Conditional processing. True operand on expression stack
	 */
	public final static int CONDITIONAL_TRUE_VALUE = 1;

	public final static InternalConditionalOperandType CONDITIONAL_TRUE = new InternalConditionalOperandType(CONDITIONAL_TRUE_VALUE,
			"Conditional True Operand Flag");

	/**
	 * Used in Conditional processing. Test operand on expression stack
	 */
	public final static int CONDITIONAL_TEST_VALUE = 2;

	public final static InternalConditionalOperandType CONDITIONAL_TEST = new InternalConditionalOperandType(CONDITIONAL_TEST_VALUE,
			"Conditional Test Operand Flag");

	/**
	 * Return the enum for the given value.
	 * @param value
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public static InternalConditionalOperandType get(int value) {
		switch (value) {
			case CONDITIONAL_FALSE_VALUE:
				return CONDITIONAL_FALSE;
			case CONDITIONAL_TRUE_VALUE:
				return CONDITIONAL_TRUE;
			case CONDITIONAL_TEST_VALUE:
				return CONDITIONAL_TEST;
		}
		return null;
	}

	private InternalConditionalOperandType(int value, String name) {
		super(value, name);
	}
}