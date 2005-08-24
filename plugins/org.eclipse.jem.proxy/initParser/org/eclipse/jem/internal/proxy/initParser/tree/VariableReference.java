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
 *  $RCSfile: VariableReference.java,v $
 *  $Revision: 1.2 $  $Date: 2005/08/24 20:39:07 $ 
 */
package org.eclipse.jem.internal.proxy.initParser.tree;
 

/**
 * This represents a Variable Reference value. A variable reference value can be on the left
 * side of an assignment (e.g. field or array access) or on any side
 * of any expression. When on the left side of an assignment, then the
 * value can assigned to. Such as <code>x[3] = 4</code>. Or it is value
 * that can be used in expressions, such as <code>x[3] + 2</code>.
 * <p>
 * When dereferenced, the value is given (see {@link VariableReference#dereference()}) as
 * the result or it is set with a value and then deferenced (see {@link VariableReference#set(Object, Class)}).
 * <p>
 * The type of the reference is the type stored in the corresponding expressionTypeStack entry. This is the type
 * for assignment, and the type for dereferenced.
 * @since 1.1.0
 */
public abstract class VariableReference {

	/**
	 * Dereference the value. 
	 * @return the dereferenced value. Such as the result of <code>x[3]</code>. The type of the reference 
	 * is the type stored in the corresponding expressionTypeStack entry for this reference.
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * 
	 * @since 1.1.0
	 */
	public abstract Object dereference();
	
	/**
	 * Set the value into the variable and dereference it. Once it is set it is
	 * no longer a reference and must be dereferenced.
	 * @param value value to set to.
	 * @param type type of the value being set. It may be of use to the reference or maybe not.
	 * @return the dereferenced value after being set. The type of the dereferenced value 
	 * is the type stored in the corresponding expressionTypeStack entry for this reference.
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * 
	 * @since 1.1.0
	 */
	public abstract Object set(Object value, Class type) throws IllegalArgumentException, IllegalAccessException;
}
