/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo;
/*
 *  $RCSfile: IndexedPropertyDecorator.java,v $
 *  $Revision: 1.6 $  $Date: 2005/04/14 19:05:36 $ 
 */


import org.eclipse.jem.java.Method;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indexed Property Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Equivalent to IndexedPropertyDecorator
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedReadMethod <em>Indexed Read Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedWriteMethod <em>Indexed Write Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getIndexedPropertyDecorator()
 * @model
 * @generated
 */


public interface IndexedPropertyDecorator extends PropertyDecorator{
	/**
	 * Returns the value of the '<em><b>Indexed Read Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexed Read Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexed Read Method</em>' reference.
	 * @see #isSetIndexedReadMethod()
	 * @see #unsetIndexedReadMethod()
	 * @see #setIndexedReadMethod(Method)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getIndexedPropertyDecorator_IndexedReadMethod()
	 * @model unsettable="true"
	 * @generated
	 */
	Method getIndexedReadMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedReadMethod <em>Indexed Read Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indexed Read Method</em>' reference.
	 * @see #isSetIndexedReadMethod()
	 * @see #unsetIndexedReadMethod()
	 * @see #getIndexedReadMethod()
	 * @generated
	 */
	void setIndexedReadMethod(Method value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedReadMethod <em>Indexed Read Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIndexedReadMethod()
	 * @see #getIndexedReadMethod()
	 * @see #setIndexedReadMethod(Method)
	 * @generated
	 */
	void unsetIndexedReadMethod();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedReadMethod <em>Indexed Read Method</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Indexed Read Method</em>' reference is set.
	 * @see #unsetIndexedReadMethod()
	 * @see #getIndexedReadMethod()
	 * @see #setIndexedReadMethod(Method)
	 * @generated
	 */
	boolean isSetIndexedReadMethod();

	/**
	 * Returns the value of the '<em><b>Indexed Write Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexed Write Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexed Write Method</em>' reference.
	 * @see #isSetIndexedWriteMethod()
	 * @see #unsetIndexedWriteMethod()
	 * @see #setIndexedWriteMethod(Method)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getIndexedPropertyDecorator_IndexedWriteMethod()
	 * @model unsettable="true"
	 * @generated
	 */
	Method getIndexedWriteMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedWriteMethod <em>Indexed Write Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indexed Write Method</em>' reference.
	 * @see #isSetIndexedWriteMethod()
	 * @see #unsetIndexedWriteMethod()
	 * @see #getIndexedWriteMethod()
	 * @generated
	 */
	void setIndexedWriteMethod(Method value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedWriteMethod <em>Indexed Write Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetIndexedWriteMethod()
	 * @see #getIndexedWriteMethod()
	 * @see #setIndexedWriteMethod(Method)
	 * @generated
	 */
	void unsetIndexedWriteMethod();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedWriteMethod <em>Indexed Write Method</em>}' reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Indexed Write Method</em>' reference is set.
	 * @see #unsetIndexedWriteMethod()
	 * @see #getIndexedWriteMethod()
	 * @see #setIndexedWriteMethod(Method)
	 * @generated
	 */
	boolean isSetIndexedWriteMethod();

}
