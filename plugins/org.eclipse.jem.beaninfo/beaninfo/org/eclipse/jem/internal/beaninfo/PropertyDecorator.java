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
 *  $RCSfile: PropertyDecorator.java,v $
 *  $Revision: 1.2 $  $Date: 2004/01/13 16:17:00 $ 
 */


import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClassifier;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Property Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isBound <em>Bound</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isConstrained <em>Constrained</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isDesignTime <em>Design Time</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isAlwaysIncompatible <em>Always Incompatible</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getFilterFlags <em>Filter Flags</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getPropertyEditorClass <em>Property Editor Class</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getReadMethod <em>Read Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getWriteMethod <em>Write Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getPropertyDecorator()
 * @model 
 * @generated
 */


public interface PropertyDecorator extends FeatureDecorator{
	/**
	 * Returns the value of the '<em><b>Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bound</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bound</em>' attribute.
	 * @see #isSetBound()
	 * @see #unsetBound()
	 * @see #setBound(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getPropertyDecorator_Bound()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isBound();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isBound <em>Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bound</em>' attribute.
	 * @see #isSetBound()
	 * @see #unsetBound()
	 * @see #isBound()
	 * @generated
	 */
	void setBound(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isBound <em>Bound</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetBound()
	 * @see #isBound()
	 * @see #setBound(boolean)
	 * @generated
	 */
	void unsetBound();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isBound <em>Bound</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Bound</em>' attribute is set.
	 * @see #unsetBound()
	 * @see #isBound()
	 * @see #setBound(boolean)
	 * @generated
	 */
	boolean isSetBound();

	/**
	 * Returns the value of the '<em><b>Constrained</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constrained</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constrained</em>' attribute.
	 * @see #isSetConstrained()
	 * @see #unsetConstrained()
	 * @see #setConstrained(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getPropertyDecorator_Constrained()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isConstrained();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isConstrained <em>Constrained</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constrained</em>' attribute.
	 * @see #isSetConstrained()
	 * @see #unsetConstrained()
	 * @see #isConstrained()
	 * @generated
	 */
	void setConstrained(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isConstrained <em>Constrained</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConstrained()
	 * @see #isConstrained()
	 * @see #setConstrained(boolean)
	 * @generated
	 */
	void unsetConstrained();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isConstrained <em>Constrained</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Constrained</em>' attribute is set.
	 * @see #unsetConstrained()
	 * @see #isConstrained()
	 * @see #setConstrained(boolean)
	 * @generated
	 */
	boolean isSetConstrained();

	/**
	 * Returns the value of the '<em><b>Design Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Design Time</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If not set, then normal default processing.
	 * 
	 * If set true, then this property is a design time property. This means it will show up in the property sheet, but it won't be able to be connected to at runtime. It may not even be a true bean property but instead the builder will know how to handle it.
	 * 
	 * If set false, then this property will not show up on the property sheet, but will be able to be connected to for runtime.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Design Time</em>' attribute.
	 * @see #isSetDesignTime()
	 * @see #unsetDesignTime()
	 * @see #setDesignTime(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getPropertyDecorator_DesignTime()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isDesignTime();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isDesignTime <em>Design Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Design Time</em>' attribute.
	 * @see #isSetDesignTime()
	 * @see #unsetDesignTime()
	 * @see #isDesignTime()
	 * @generated
	 */
	void setDesignTime(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isDesignTime <em>Design Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDesignTime()
	 * @see #isDesignTime()
	 * @see #setDesignTime(boolean)
	 * @generated
	 */
	void unsetDesignTime();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isDesignTime <em>Design Time</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Design Time</em>' attribute is set.
	 * @see #unsetDesignTime()
	 * @see #isDesignTime()
	 * @see #setDesignTime(boolean)
	 * @generated
	 */
	boolean isSetDesignTime();

	/**
	 * Returns the value of the '<em><b>Always Incompatible</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Always Incompatible</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If set true, then when multiple objects are selected, this property is always incompatible with each other. So in this case the property will not show up on the property sheet if more than one object has been selected.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Always Incompatible</em>' attribute.
	 * @see #setAlwaysIncompatible(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getPropertyDecorator_AlwaysIncompatible()
	 * @model 
	 * @generated
	 */
	boolean isAlwaysIncompatible();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#isAlwaysIncompatible <em>Always Incompatible</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Always Incompatible</em>' attribute.
	 * @see #isAlwaysIncompatible()
	 * @generated
	 */
	void setAlwaysIncompatible(boolean value);

	/**
	 * Returns the value of the '<em><b>Filter Flags</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filter Flags</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filter Flags</em>' attribute list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getPropertyDecorator_FilterFlags()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getFilterFlags();

	/**
	 * Returns the value of the '<em><b>Property Editor Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Property Editor Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Property Editor Class</em>' reference.
	 * @see #setPropertyEditorClass(JavaClass)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getPropertyDecorator_PropertyEditorClass()
	 * @model 
	 * @generated
	 */
	JavaClass getPropertyEditorClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getPropertyEditorClass <em>Property Editor Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Property Editor Class</em>' reference.
	 * @see #getPropertyEditorClass()
	 * @generated
	 */
	void setPropertyEditorClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Read Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Read Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Read Method</em>' reference.
	 * @see #setReadMethod(Method)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getPropertyDecorator_ReadMethod()
	 * @model 
	 * @generated
	 */
	Method getReadMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getReadMethod <em>Read Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Read Method</em>' reference.
	 * @see #getReadMethod()
	 * @generated
	 */
	void setReadMethod(Method value);

	/**
	 * Returns the value of the '<em><b>Write Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Write Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Write Method</em>' reference.
	 * @see #setWriteMethod(Method)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getPropertyDecorator_WriteMethod()
	 * @model 
	 * @generated
	 */
	Method getWriteMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.PropertyDecorator#getWriteMethod <em>Write Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Write Method</em>' reference.
	 * @see #getWriteMethod()
	 * @generated
	 */
	void setWriteMethod(Method value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model parameters=""
	 * @generated
	 */
	EClassifier getPropertyType();

}
