/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.proxy.initParser.tree;
 

/**
 * Internal class for the Prefix operand type. Not meant to be used by customers.
 * @since 1.1.0
 */
public class InternalInfixOperandType extends AbstractEnum {

	/**
	 * Used in Infix processing. Left operand on expression stack
	 */
	public final static int INFIX_LEFT_OPERAND_VALUE = 0;
	public final static InternalInfixOperandType INFIX_LEFT_OPERAND = new InternalInfixOperandType(INFIX_LEFT_OPERAND_VALUE, "Infix Left Operand Flag"); //$NON-NLS-1$
	/**
	 * Used in Infix processing. Other operand (but not last) on expression stack
	 */
	public final static int INFIX_OTHER_OPERAND_VALUE = 1;
	public final static InternalInfixOperandType INFIX_OTHER_OPERAND = new InternalInfixOperandType(INFIX_OTHER_OPERAND_VALUE,
	"Infix Other Operand Flag"); //$NON-NLS-1$
	/**
	 * Used in Infix processing. Rightmost (last) operand on expression stack
	 */
	public final static int INFIX_LAST_OPERAND_VALUE = 2;
	public final static InternalInfixOperandType INFIX_LAST_OPERAND = new InternalInfixOperandType(INFIX_LAST_OPERAND_VALUE, "Infix Last Operand Flag"); //$NON-NLS-1$

	/**
	 * Return the enum for the given value.
	 * @param value
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public static InternalInfixOperandType get(int value) {
		switch (value) {
			case INFIX_LAST_OPERAND_VALUE:
				return INFIX_LAST_OPERAND;
			case INFIX_LEFT_OPERAND_VALUE:
				return INFIX_LEFT_OPERAND;
			case INFIX_OTHER_OPERAND_VALUE:
				return INFIX_OTHER_OPERAND;
		}
		return null;
	}
	
	private InternalInfixOperandType(int value, String name) {
		super(value, name);
	}
}
