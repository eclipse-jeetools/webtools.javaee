/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jem.internal.beaninfo;
/*
 *  $RCSfile: IndexedPropertyDecorator.java,v $
 *  $Revision: 1.3 $  $Date: 2004/08/27 15:33:31 $ 
 */


import org.eclipse.jem.java.Method;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indexed Property Decorator</b></em>'.
 * <!-- end-user-doc -->
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


public interface IndexedPropertyDecorator extends PropertyDecorator {
	/**
	 * Returns the value of the '<em><b>Indexed Read Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexed Read Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexed Read Method</em>' reference.
	 * @see #setIndexedReadMethod(Method)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getIndexedPropertyDecorator_IndexedReadMethod()
	 * @model 
	 * @generated
	 */
	Method getIndexedReadMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedReadMethod <em>Indexed Read Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indexed Read Method</em>' reference.
	 * @see #getIndexedReadMethod()
	 * @generated
	 */
	void setIndexedReadMethod(Method value);

	/**
	 * Returns the value of the '<em><b>Indexed Write Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Indexed Write Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Indexed Write Method</em>' reference.
	 * @see #setIndexedWriteMethod(Method)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getIndexedPropertyDecorator_IndexedWriteMethod()
	 * @model 
	 * @generated
	 */
	Method getIndexedWriteMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.IndexedPropertyDecorator#getIndexedWriteMethod <em>Indexed Write Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Indexed Write Method</em>' reference.
	 * @see #getIndexedWriteMethod()
	 * @generated
	 */
	void setIndexedWriteMethod(Method value);

}
