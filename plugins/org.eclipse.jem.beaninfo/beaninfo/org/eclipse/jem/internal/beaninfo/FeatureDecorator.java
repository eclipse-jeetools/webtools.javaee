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
 *  $RCSfile: FeatureDecorator.java,v $
 *  $Revision: 1.3 $  $Date: 2004/03/08 21:25:33 $ 
 */


import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;

import org.eclipse.jem.internal.proxy.core.IBeanProxy;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Equivalent to FeatureDescriptor in java.
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
 *   <li>{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isAttributesExplicit <em>Attributes Explicit</em>}</li>
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
	 * Enumeration of what was implicitly created.
	 */
	public static final int
		NOT_IMPLICIT = 0,
		IMPLICIT_DECORATOR = 1,
		IMPLICIT_DECORATOR_AND_FEATURE = 3;
		// Can't have explicit decorator but implicit feature.


	/**
	 * Was this decorator implicitly created by introspection?
	 * The default should be false.
	 * This is here for linkage with the introspection process
	 * and is not really a MOF property. The introspection needs
	 * to know which features it created and which were explicitly
	 * created by other means. Implicitly created ones may be 
	 * deleted at any time when the introspection determines it
	 * needs to.
	 */
	public int isImplicitlyCreated();
	public void setImplicitlyCreated(int implicit);
	
	/**
	 * Answer whether this has introspection results merged into it.
	 * This is important because if it doesn't have a proxy,
	 * then this is an explicit decorator. Explicit decorators
	 * will show up in the eAllAttribute/eAllBehaviors/eAllEvents
	 * when beaninfo says don't do merge. This is because the
	 * introspection only knows about beaninfo features and so
	 * it can only specify that beaninfo features are not to
	 * be inherited.
	 */
	public boolean isIntrospected();
	
	/**
	 * Return the descriptor proxy used gather implicit information
	 * not explicitly set on the decorator. Each decorator
	 * knows what to retrieve out of the proxy. 
	 */
	public IBeanProxy getDescriptorProxy();
		 
	/**
	 * Set the descriptor proxy to gather implicit information
	 * not explicitly set on the decorator. Each decorator
	 * knows what to retrieve out of the proxy. If the proxy
	 * is null, then it there is no beaninfo to info from. When
	 * this method is called, any cached values are cleared out.
	 */
	public void setDescriptorProxy(IBeanProxy descriptor);
	
	/**
	 * Set the decorator to use for querying reflected properties.
	 * On reflection we need to return the results, but we don't
	 * want to explicitly set anything that could override the
	 * explicit settings by the user. So we pass in a decorator
	 * that has the reflected settings. It act's like the descriptor proxy
	 * above for when it comes from beaninfo's. Each decorator knows what
	 * to retrieve from implicit decorator.
	 */
	public void setDecoratorProxy(FeatureDecorator decorator);
	
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
	 * Should the introspection results be merged into this decorator. If this is set to false, then the introspection results are ignored for this particular decorator. This is an internal feature simply to allow desired override capabilities.
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
	 * Returns the value of the '<em><b>Attributes</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link org.eclipse.jem.internal.beaninfo.FeatureAttributeValue},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Attributes</em>' map.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_Attributes()
	 * @model mapType="org.eclipse.jem.internal.beaninfo.FeatureAttributeMapEntry" keyType="java.lang.String" valueType="org.eclipse.jem.internal.beaninfo.FeatureAttributeValue"
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

	/**
	 * Returns the value of the '<em><b>Attributes Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes Explicit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The attributes are explicitly set and not retrieved from the beaninfo.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Attributes Explicit</em>' attribute.
	 * @see #setAttributesExplicit(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getFeatureDecorator_AttributesExplicit()
	 * @model 
	 * @generated
	 */
	boolean isAttributesExplicit();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.FeatureDecorator#isAttributesExplicit <em>Attributes Explicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Attributes Explicit</em>' attribute.
	 * @see #isAttributesExplicit()
	 * @generated
	 */
	void setAttributesExplicit(boolean value);

	/**
	 * Answer if this decorator needs to be re-introspected. This would occur
	 * because the BeanInfo is now invalid.
	 * @return Re-introspection required.
	 */
	boolean needIntrospection();
	

}
