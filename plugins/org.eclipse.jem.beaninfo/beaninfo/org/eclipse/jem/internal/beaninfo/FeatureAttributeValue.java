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
 *  $RCSfile: FeatureAttributeValue.java,v $
 *  $Revision: 1.1.4.1 $  $Date: 2003/12/16 19:28:47 $ 
 */


import org.eclipse.emf.ecore.EObject;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Attribute Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueProxy <em>Value Proxy</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureAttributeValue()
 * @model 
 * @generated
 */


public interface FeatureAttributeValue extends EObject{

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
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureAttributeValue_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(EObject)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureAttributeValue_Value()
	 * @model containment="true"
	 * @generated
	 */
	EObject getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(EObject value);

	/**
	 * Returns the value of the '<em><b>Value Proxy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Proxy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Value Proxy</em>' attribute.
	 * @see #isSetValueProxy()
	 * @see #unsetValueProxy()
	 * @see #setValueProxy(Object)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureAttributeValue_ValueProxy()
	 * @model unsettable="true" transient="true"
	 * @generated
	 */
	Object getValueProxy();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueProxy <em>Value Proxy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Proxy</em>' attribute.
	 * @see #isSetValueProxy()
	 * @see #unsetValueProxy()
	 * @see #getValueProxy()
	 * @generated
	 */
	void setValueProxy(Object value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueProxy <em>Value Proxy</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetValueProxy()
	 * @see #getValueProxy()
	 * @see #setValueProxy(Object)
	 * @generated
	 */
	void unsetValueProxy();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueProxy <em>Value Proxy</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Value Proxy</em>' attribute is set.
	 * @see #unsetValueProxy()
	 * @see #getValueProxy()
	 * @see #setValueProxy(Object)
	 * @generated
	 */
	boolean isSetValueProxy();

}
