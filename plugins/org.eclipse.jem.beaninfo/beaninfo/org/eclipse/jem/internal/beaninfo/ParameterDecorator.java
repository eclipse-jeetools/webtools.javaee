package org.eclipse.jem.internal.beaninfo;
/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: ParameterDecorator.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:17:59 $ 
 */


import org.eclipse.jem.internal.java.JavaParameter;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.ParameterDecorator#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.ParameterDecorator#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getParameterDecorator()
 * @model 
 * @generated
 */


public interface ParameterDecorator extends FeatureDecorator {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getParameterDecorator_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.ParameterDecorator#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parameter</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parameter</em>' reference.
	 * @see #setParameter(JavaParameter)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getParameterDecorator_Parameter()
	 * @model transient="true"
	 * @generated
	 */
	JavaParameter getParameter();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.ParameterDecorator#getParameter <em>Parameter</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter</em>' reference.
	 * @see #getParameter()
	 * @generated
	 */
	void setParameter(JavaParameter value);

}
