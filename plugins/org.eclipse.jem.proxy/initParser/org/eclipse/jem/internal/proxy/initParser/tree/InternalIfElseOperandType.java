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
 *  $RCSfile: InternalIfElseOperandType.java,v $
 *  $Revision: 1.2 $  $Date: 2005/05/18 23:11:26 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;

/**
 * Internal class for the If/else operand type. Not meant to be used by customers.
 * 
 * @since 1.1.0
 */
public class InternalIfElseOperandType extends AbstractEnum {

	/**
	 * Used in If/else processing. False clause on expression stack
	 */
	public final static int ELSE_CLAUSE_VALUE = 0;

	public final static InternalIfElseOperandType ELSE_CLAUSE = new InternalIfElseOperandType(ELSE_CLAUSE_VALUE,
			"Else Clause Flag"); //$NON-NLS-1$

	/**
	 * Used in If/else processing. If operand on expression stack
	 */
	public final static int TRUE_CLAUSE_VALUE = 1;

	public final static InternalIfElseOperandType TRUE_CLAUSE = new InternalIfElseOperandType(TRUE_CLAUSE_VALUE,
			"If True Clause Flag"); //$NON-NLS-1$

	/**
	 * Return the enum for the given value.
	 * @param value
	 * @return
	 * 
	 * @since 1.1.0
	 */
	public static InternalIfElseOperandType get(int value) {
		switch (value) {
			case ELSE_CLAUSE_VALUE:
				return ELSE_CLAUSE;
			case TRUE_CLAUSE_VALUE:
				return TRUE_CLAUSE;
		}
		return null;
	}

	private InternalIfElseOperandType(int value, String name) {
		super(value, name);
	}
}