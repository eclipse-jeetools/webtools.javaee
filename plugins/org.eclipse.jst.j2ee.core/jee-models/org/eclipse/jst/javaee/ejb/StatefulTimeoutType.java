/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.math.BigInteger;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Stateful Timeout Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The stateful-timeoutType represents the amount of time
 *         a stateful session bean can be idle(not receive any client
 *         invocations) before it is eligible for removal by the container.
 *         
 *         A timeout value of 0 means the bean is immediately eligible for removal.
 *         
 *         A timeout value of -1 means the bean will never be removed due to timeout.
 *         
 *         @since Java EE 6, EJB 3.1
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.StatefulTimeoutType#getTimeout <em>Timeout</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.StatefulTimeoutType#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.StatefulTimeoutType#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getStatefulTimeoutType()
 * @extends JavaEEObject
 * @generated
 */
public interface StatefulTimeoutType extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Timeout</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeout</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Timeout</em>' attribute.
	 * @see #setTimeout(BigInteger)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getStatefulTimeoutType_Timeout()
	 * @generated
	 */
	BigInteger getTimeout();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.StatefulTimeoutType#getTimeout <em>Timeout</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Timeout</em>' attribute.
	 * @see #getTimeout()
	 * @generated
	 */
	void setTimeout(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.TimeUnitTypeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.TimeUnitTypeType
	 * @see #isSetUnit()
	 * @see #unsetUnit()
	 * @see #setUnit(TimeUnitTypeType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getStatefulTimeoutType_Unit()
	 * @generated
	 */
	TimeUnitTypeType getUnit();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.StatefulTimeoutType#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.TimeUnitTypeType
	 * @see #isSetUnit()
	 * @see #unsetUnit()
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(TimeUnitTypeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.StatefulTimeoutType#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnit()
	 * @see #getUnit()
	 * @see #setUnit(TimeUnitTypeType)
	 * @generated
	 */
	void unsetUnit();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.StatefulTimeoutType#getUnit <em>Unit</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Unit</em>' attribute is set.
	 * @see #unsetUnit()
	 * @see #getUnit()
	 * @see #setUnit(TimeUnitTypeType)
	 * @generated
	 */
	boolean isSetUnit();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getStatefulTimeoutType_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.StatefulTimeoutType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // StatefulTimeoutType
