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
 *  $RCSfile: MethodInvocation.java,v $
 *  $Revision: 1.1 $  $Date: 2004/01/19 22:50:15 $ 
 */
import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Method Invocation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * MethodInvocation expression, e.g. x.getY(3)
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.MethodInvocation#getReceiver <em>Receiver</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.MethodInvocation#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.instantiation.MethodInvocation#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getMethodInvocation()
 * @model 
 * @generated
 */
public interface MethodInvocation extends Expression {
	/**
	 * Returns the value of the '<em><b>Receiver</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Who the method is invoked against. This may be not set, which means "this". It may also be a Name, which would mean a class like "java.lang.String"
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Receiver</em>' containment reference.
	 * @see #setReceiver(Expression)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getMethodInvocation_Receiver()
	 * @model containment="true"
	 * @generated
	 */
	Expression getReceiver();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.MethodInvocation#getReceiver <em>Receiver</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Receiver</em>' containment reference.
	 * @see #getReceiver()
	 * @generated
	 */
	void setReceiver(Expression value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the method being invoked.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getMethodInvocation_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.MethodInvocation#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Arguments</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.instantiation.Expression}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The list of arguments, if any, to the method.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Arguments</em>' containment reference list.
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getMethodInvocation_Arguments()
	 * @model type="org.eclipse.jem.internal.instantiation.Expression" containment="true"
	 * @generated
	 */
	EList getArguments();

} // MethodInvocation
