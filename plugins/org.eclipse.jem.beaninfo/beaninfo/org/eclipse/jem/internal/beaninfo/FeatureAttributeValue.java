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
 *  $RCSfile: FeatureAttributeValue.java,v $
 *  $Revision: 1.3.4.1 $  $Date: 2004/06/24 18:19:38 $ 
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
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueJava <em>Value Java</em>}</li>
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
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the value as an EObject. This is used to return typically the IJavaInstance representing the value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #isSetValue()
	 * @see #unsetValue()
	 * @see #setValue(EObject)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureAttributeValue_Value()
	 * @model containment="true" unsettable="true"
	 * @generated
	 */
	EObject getValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #isSetValue()
	 * @see #unsetValue()
	 * @see #getValue()
	 * @generated
	 */
	void setValue(EObject value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetValue()
	 * @see #getValue()
	 * @see #setValue(EObject)
	 * @generated
	 */
	void unsetValue();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValue <em>Value</em>}' containment reference is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Value</em>' containment reference is set.
	 * @see #unsetValue()
	 * @see #getValue()
	 * @see #setValue(EObject)
	 * @generated
	 */
	boolean isSetValue();

	/**
	 * Returns the value of the '<em><b>Value Java</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is where the value is just a java object. Not an EObject. Sometimes it is easier to have this instead. This attribute is transient and won't be serialized.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value Java</em>' attribute.
	 * @see #isSetValueJava()
	 * @see #unsetValueJava()
	 * @see #setValueJava(Object)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureAttributeValue_ValueJava()
	 * @model unsettable="true" transient="true"
	 * @generated
	 */
	Object getValueJava();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueJava <em>Value Java</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value Java</em>' attribute.
	 * @see #isSetValueJava()
	 * @see #unsetValueJava()
	 * @see #getValueJava()
	 * @generated
	 */
	void setValueJava(Object value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueJava <em>Value Java</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetValueJava()
	 * @see #getValueJava()
	 * @see #setValueJava(Object)
	 * @generated
	 */
	void unsetValueJava();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue#getValueJava <em>Value Java</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Value Java</em>' attribute is set.
	 * @see #unsetValueJava()
	 * @see #getValueJava()
	 * @see #setValueJava(Object)
	 * @generated
	 */
	boolean isSetValueJava();

	/**
	 * Returns the value of the '<em><b>Value Proxy</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value Proxy</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the proxy of the value from a BeanInfo on the remote vm. It is typed to EJavaObject because we don't want to prereq the java instanctiation stuff. It is transient.
	 * <!-- end-model-doc -->
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
