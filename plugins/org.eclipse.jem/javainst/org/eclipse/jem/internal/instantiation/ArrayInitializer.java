package org.eclipse.jem.internal.instantiation;
/*******************************************************************************
 * Copyright (c)  2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ArrayInitializer.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Array Initializer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Array initialization expression. This is the entire expression, e.g. {2, 3}, or {{2,3}, 4}. In the second case, the {2,3} will be an ArrayInitializer contained within the expressions list for the top ArrayInitializer.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.ArrayInitializer#getExpressions <em>Expressions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getArrayInitializer()
 * @model 
 * @generated
 */
public interface ArrayInitializer extends Expression {
	/**
	 * Returns the value of the '<em><b>Expressions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.instantiation.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This isthe list of expressions within the initializer, e.g. {2, 3}, or {{2,3}, 4}. In the first case it will be two NumberLiterals. In the second case, the {2,3} will be an ArrayInitializer, followed by a NumberLiteral.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Expressions</em>' containment reference list.
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getArrayInitializer_Expressions()
	 * @model type="org.eclipse.jem.internal.instantiation.Expression" containment="true"
	 * @generated
	 */
	EList getExpressions();

} // ArrayInitializer
