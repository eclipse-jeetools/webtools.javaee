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
 *  $RCSfile: FeatureDecorator.java,v $
 *  $Revision: 1.5 $  $Date: 2005/02/04 23:11:53 $ 
 */


import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Equivalent to FeatureDescriptor in java.
 * <p>
 * Note: If any attribute is explicitly set then the BeanInfo/Reflection will not be merged into the decorator. This provides a way of overriding the BeanInfos. Also for any many-valued attribute, if it is desired to have it explicitly empty and not have BeanInfo fill it in, there will be another attribute named of the form "attibutueExplicitEmpty" If this is true then the BeanInfo will not merge in and will leave it empty.
 * <p>
 * These comments about merging apply to all subclasses of this decorator too. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getDisplayName <em>Display Name</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getShortDescription <em>Short Description</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isExpert <em>Expert</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isHidden <em>Hidden</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isPreferred <em>Preferred</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isMergeIntrospection <em>Merge Introspection</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isAttributesExplicitEmpty <em>Attributes Explicit Empty</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getImplicitlySetBits <em>Implicitly Set Bits</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getImplicitDecoratorFlag <em>Implicit Decorator Flag</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getAttributes <em>Attributes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator()
 * @model 
 * @generated
 */


public interface FeatureDecorator extends EAnnotation{
		
	/**
	 * Returns the value of the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Display Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Display Name</em>' attribute.
	 * @see #isSetDisplayName()
	 * @see #unsetDisplayName()
	 * @see #setDisplayName(String)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_DisplayName()
	 * @model unsettable="true"
	 * @generated
	 */
	String getDisplayName();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Display Name</em>' attribute.
	 * @see #isSetDisplayName()
	 * @see #unsetDisplayName()
	 * @see #getDisplayName()
	 * @generated
	 */
	void setDisplayName(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getDisplayName <em>Display Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetDisplayName()
	 * @see #getDisplayName()
	 * @see #setDisplayName(String)
	 * @generated
	 */
	void unsetDisplayName();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getDisplayName <em>Display Name</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Display Name</em>' attribute is set.
	 * @see #unsetDisplayName()
	 * @see #getDisplayName()
	 * @see #setDisplayName(String)
	 * @generated
	 */
	boolean isSetDisplayName();

	/**
	 * Returns the value of the '<em><b>Short Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Short Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Short Description</em>' attribute.
	 * @see #isSetShortDescription()
	 * @see #unsetShortDescription()
	 * @see #setShortDescription(String)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_ShortDescription()
	 * @model unsettable="true"
	 * @generated
	 */
	String getShortDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getShortDescription <em>Short Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Short Description</em>' attribute.
	 * @see #isSetShortDescription()
	 * @see #unsetShortDescription()
	 * @see #getShortDescription()
	 * @generated
	 */
	void setShortDescription(String value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getShortDescription <em>Short Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetShortDescription()
	 * @see #getShortDescription()
	 * @see #setShortDescription(String)
	 * @generated
	 */
	void unsetShortDescription();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getShortDescription <em>Short Description</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Short Description</em>' attribute is set.
	 * @see #unsetShortDescription()
	 * @see #getShortDescription()
	 * @see #setShortDescription(String)
	 * @generated
	 */
	boolean isSetShortDescription();

	/**
	 * Returns the value of the '<em><b>Category</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Category</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Category</em>' attribute.
	 * @see #setCategory(String)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_Category()
	 * @model 
	 * @generated
	 */
	String getCategory();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getCategory <em>Category</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Category</em>' attribute.
	 * @see #getCategory()
	 * @generated
	 */
	void setCategory(String value);

	/**
	 * Returns the value of the '<em><b>Expert</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Expert</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Expert</em>' attribute.
	 * @see #isSetExpert()
	 * @see #unsetExpert()
	 * @see #setExpert(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_Expert()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isExpert();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isExpert <em>Expert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Expert</em>' attribute.
	 * @see #isSetExpert()
	 * @see #unsetExpert()
	 * @see #isExpert()
	 * @generated
	 */
	void setExpert(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isExpert <em>Expert</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetExpert()
	 * @see #isExpert()
	 * @see #setExpert(boolean)
	 * @generated
	 */
	void unsetExpert();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isExpert <em>Expert</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Expert</em>' attribute is set.
	 * @see #unsetExpert()
	 * @see #isExpert()
	 * @see #setExpert(boolean)
	 * @generated
	 */
	boolean isSetExpert();

	/**
	 * Returns the value of the '<em><b>Hidden</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hidden</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hidden</em>' attribute.
	 * @see #isSetHidden()
	 * @see #unsetHidden()
	 * @see #setHidden(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_Hidden()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isHidden();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isHidden <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Hidden</em>' attribute.
	 * @see #isSetHidden()
	 * @see #unsetHidden()
	 * @see #isHidden()
	 * @generated
	 */
	void setHidden(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isHidden <em>Hidden</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetHidden()
	 * @see #isHidden()
	 * @see #setHidden(boolean)
	 * @generated
	 */
	void unsetHidden();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isHidden <em>Hidden</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Hidden</em>' attribute is set.
	 * @see #unsetHidden()
	 * @see #isHidden()
	 * @see #setHidden(boolean)
	 * @generated
	 */
	boolean isSetHidden();

	/**
	 * Returns the value of the '<em><b>Preferred</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Preferred</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Preferred</em>' attribute.
	 * @see #isSetPreferred()
	 * @see #unsetPreferred()
	 * @see #setPreferred(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_Preferred()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isPreferred();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isPreferred <em>Preferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Preferred</em>' attribute.
	 * @see #isSetPreferred()
	 * @see #unsetPreferred()
	 * @see #isPreferred()
	 * @generated
	 */
	void setPreferred(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isPreferred <em>Preferred</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetPreferred()
	 * @see #isPreferred()
	 * @see #setPreferred(boolean)
	 * @generated
	 */
	void unsetPreferred();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isPreferred <em>Preferred</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Preferred</em>' attribute is set.
	 * @see #unsetPreferred()
	 * @see #isPreferred()
	 * @see #setPreferred(boolean)
	 * @generated
	 */
	boolean isSetPreferred();

	/**
	 * Returns the value of the '<em><b>Merge Introspection</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merge Introspection</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the introspection results be merged into this decorator. If this is set to false, then the introspection results are ignored for this particular decorator. This is an internal feature simply to allow desired override capabilities. Customers would use it to prevent ANY introspection/reflection from occurring.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Merge Introspection</em>' attribute.
	 * @see #setMergeIntrospection(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_MergeIntrospection()
	 * @model default="true"
	 * @generated
	 */
	boolean isMergeIntrospection();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isMergeIntrospection <em>Merge Introspection</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge Introspection</em>' attribute.
	 * @see #isMergeIntrospection()
	 * @generated
	 */
	void setMergeIntrospection(boolean value);

	/**
	 * Returns the value of the '<em><b>Attributes Explicit Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The attributes are explicitly set as empty and not retrieved from the beaninfo/reflection. Customers should set this if they want the list of attributes to be empty and not merged with the BeanInfo results. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attributes Explicit Empty</em>' attribute.
	 * @see #setAttributesExplicitEmpty(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_AttributesExplicitEmpty()
	 * @model 
	 * @generated
	 */
	boolean isAttributesExplicitEmpty();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isAttributesExplicitEmpty <em>Attributes Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attributes Explicit Empty</em>' attribute.
	 * @see #isAttributesExplicitEmpty()
	 * @generated
	 */
	void setAttributesExplicitEmpty(boolean value);

	/**
	 * Returns the value of the '<em><b>Implicitly Set Bits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A bitflag for which attributes have been set by BeanInfo/Reflection.
	 * <p>
	 * This is an internal attribute that is used by the BeanInfo maintanance. It is not meant to be used by customers.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Implicitly Set Bits</em>' attribute.
	 * @see #setImplicitlySetBits(long)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_ImplicitlySetBits()
	 * @model 
	 * @generated
	 */
	long getImplicitlySetBits();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getImplicitlySetBits <em>Implicitly Set Bits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implicitly Set Bits</em>' attribute.
	 * @see #getImplicitlySetBits()
	 * @generated
	 */
	void setImplicitlySetBits(long value);

	/**
	 * Returns the value of the '<em><b>Implicit Decorator Flag</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jem.internal.beaninfo.ImplicitItem}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Is this decorator/feature implicit. This means created by Introspection/Reflection and not by customer.
	 * <p>
	 * This is an internal attribute that is used by the BeanInfo maintanance. It is not meant to be used by customers.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Implicit Decorator Flag</em>' attribute.
	 * @see org.eclipse.jem.internal.beaninfo.ImplicitItem
	 * @see #setImplicitDecoratorFlag(ImplicitItem)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_ImplicitDecoratorFlag()
	 * @model 
	 * @generated
	 */
	ImplicitItem getImplicitDecoratorFlag();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#getImplicitDecoratorFlag <em>Implicit Decorator Flag</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Implicit Decorator Flag</em>' attribute.
	 * @see org.eclipse.jem.internal.beaninfo.ImplicitItem
	 * @see #getImplicitDecoratorFlag()
	 * @generated
	 */
	void setImplicitDecoratorFlag(ImplicitItem value);

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.jem.internal.beaninfo.common.FeatureAttributeValue},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Feature attributes. Key/value pairs. If it is desired that the feature attributes is explicitly empty and not have BeanInfo/reflection set it, set attributesExplicitEmpty to true.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attributes</em>' map.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_Attributes()
	 * @model mapType="org.eclipse.jem.internal.beaninfo.FeatureAttributeMapEntry" keyType="java.lang.String" valueType="org.eclipse.jem.internal.beaninfo.common.FeatureAttributeValue"
	 * @generated
	 */
	EMap getAttributes();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model parameters=""
	 * @generated
	 */
	String getName();

}
