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
 *  $RCSfile: BeanDecorator.java,v $
 *  $Revision: 1.9 $  $Date: 2005/04/14 19:05:36 $ 
 */


import org.eclipse.emf.common.util.EList;

import org.eclipse.jem.java.JavaClass;
import java.net.URL;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Bean Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Equivalent to BeanDecorator in java.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties <em>Merge Super Properties</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperMethods <em>Merge Super Methods</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents <em>Merge Super Events</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectProperties <em>Introspect Properties</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectMethods <em>Introspect Methods</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectEvents <em>Introspect Events</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isDoBeaninfo <em>Do Beaninfo</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#getNotInheritedPropertyNames <em>Not Inherited Property Names</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#getNotInheritedMethodNames <em>Not Inherited Method Names</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#getNotInheritedEventNames <em>Not Inherited Event Names</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#getCustomizerClass <em>Customizer Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator()
 * @model
 * @generated
 */


public interface BeanDecorator extends FeatureDecorator{
	
	/**
	 * Returns the value of the '<em><b>Merge Super Properties</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merge Super Properties</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the properties of super types be merged when asking for eAllAttributes/eAllReferences.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Merge Super Properties</em>' attribute.
	 * @see #isSetMergeSuperProperties()
	 * @see #unsetMergeSuperProperties()
	 * @see #setMergeSuperProperties(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_MergeSuperProperties()
	 * @model default="true" unsettable="true"
	 * @generated
	 */
	boolean isMergeSuperProperties();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties <em>Merge Super Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge Super Properties</em>' attribute.
	 * @see #isSetMergeSuperProperties()
	 * @see #unsetMergeSuperProperties()
	 * @see #isMergeSuperProperties()
	 * @generated
	 */
	void setMergeSuperProperties(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties <em>Merge Super Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMergeSuperProperties()
	 * @see #isMergeSuperProperties()
	 * @see #setMergeSuperProperties(boolean)
	 * @generated
	 */
	void unsetMergeSuperProperties();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperProperties <em>Merge Super Properties</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Merge Super Properties</em>' attribute is set.
	 * @see #unsetMergeSuperProperties()
	 * @see #isMergeSuperProperties()
	 * @see #setMergeSuperProperties(boolean)
	 * @generated
	 */
	boolean isSetMergeSuperProperties();

	/**
	 * Returns the value of the '<em><b>Merge Super Methods</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merge Super Behaviors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the methods of super types be merged when asking for eAllBehaviors.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Merge Super Methods</em>' attribute.
	 * @see #isSetMergeSuperMethods()
	 * @see #unsetMergeSuperMethods()
	 * @see #setMergeSuperMethods(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_MergeSuperMethods()
	 * @model default="true" unsettable="true"
	 * @generated
	 */
	boolean isMergeSuperMethods();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperMethods <em>Merge Super Methods</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge Super Methods</em>' attribute.
	 * @see #isSetMergeSuperMethods()
	 * @see #unsetMergeSuperMethods()
	 * @see #isMergeSuperMethods()
	 * @generated
	 */
	void setMergeSuperMethods(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperMethods <em>Merge Super Methods</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMergeSuperMethods()
	 * @see #isMergeSuperMethods()
	 * @see #setMergeSuperMethods(boolean)
	 * @generated
	 */
	void unsetMergeSuperMethods();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperMethods <em>Merge Super Methods</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Merge Super Methods</em>' attribute is set.
	 * @see #unsetMergeSuperMethods()
	 * @see #isMergeSuperMethods()
	 * @see #setMergeSuperMethods(boolean)
	 * @generated
	 */
	boolean isSetMergeSuperMethods();

	/**
	 * Returns the value of the '<em><b>Merge Super Events</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Merge Super Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the events of super types be merged when asking for eAllEvents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Merge Super Events</em>' attribute.
	 * @see #isSetMergeSuperEvents()
	 * @see #unsetMergeSuperEvents()
	 * @see #setMergeSuperEvents(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_MergeSuperEvents()
	 * @model default="true" unsettable="true"
	 * @generated
	 */
	boolean isMergeSuperEvents();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents <em>Merge Super Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Merge Super Events</em>' attribute.
	 * @see #isSetMergeSuperEvents()
	 * @see #unsetMergeSuperEvents()
	 * @see #isMergeSuperEvents()
	 * @generated
	 */
	void setMergeSuperEvents(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents <em>Merge Super Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetMergeSuperEvents()
	 * @see #isMergeSuperEvents()
	 * @see #setMergeSuperEvents(boolean)
	 * @generated
	 */
	void unsetMergeSuperEvents();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isMergeSuperEvents <em>Merge Super Events</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Merge Super Events</em>' attribute is set.
	 * @see #unsetMergeSuperEvents()
	 * @see #isMergeSuperEvents()
	 * @see #setMergeSuperEvents(boolean)
	 * @generated
	 */
	boolean isSetMergeSuperEvents();

	/**
	 * Returns the value of the '<em><b>Introspect Properties</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Introspect Properties</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the properties from the introspection be added to the class. This allows properties to not be introspected and to use only what is defined explicitly in the JavaClass xmi file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Introspect Properties</em>' attribute.
	 * @see #setIntrospectProperties(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_IntrospectProperties()
	 * @model default="true"
	 * @generated
	 */
	boolean isIntrospectProperties();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectProperties <em>Introspect Properties</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Introspect Properties</em>' attribute.
	 * @see #isIntrospectProperties()
	 * @generated
	 */
	void setIntrospectProperties(boolean value);

	/**
	 * Returns the value of the '<em><b>Introspect Methods</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Introspect Behaviors</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the methods from the introspection be added to the class. This allows methods to not be introspected and to use only what is defined explicitly in the JavaClass xmi file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Introspect Methods</em>' attribute.
	 * @see #setIntrospectMethods(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_IntrospectMethods()
	 * @model default="true"
	 * @generated
	 */
	boolean isIntrospectMethods();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectMethods <em>Introspect Methods</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Introspect Methods</em>' attribute.
	 * @see #isIntrospectMethods()
	 * @generated
	 */
	void setIntrospectMethods(boolean value);

	/**
	 * Returns the value of the '<em><b>Introspect Events</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Introspect Events</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Should the events from the introspection be added to the class. This allows events to not be introspected and to use only what is defined explicitly in the JavaClass xmi file.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Introspect Events</em>' attribute.
	 * @see #setIntrospectEvents(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_IntrospectEvents()
	 * @model default="true"
	 * @generated
	 */
	boolean isIntrospectEvents();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isIntrospectEvents <em>Introspect Events</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Introspect Events</em>' attribute.
	 * @see #isIntrospectEvents()
	 * @generated
	 */
	void setIntrospectEvents(boolean value);

	/**
	 * Returns the value of the '<em><b>Customizer Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Customizer Class</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Customizer Class</em>' reference.
	 * @see #setCustomizerClass(JavaClass)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_CustomizerClass()
	 * @model
	 * @generated
	 */
	JavaClass getCustomizerClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#getCustomizerClass <em>Customizer Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Customizer Class</em>' reference.
	 * @see #getCustomizerClass()
	 * @generated
	 */
	void setCustomizerClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Do Beaninfo</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Do Beaninfo</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This means do we go and get the beaninfo from the remote vm. If false, then it will not try to get the beaninfo. This doesn't prevent introspection through reflection. That is controled by the separate introspect... attributes.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Do Beaninfo</em>' attribute.
	 * @see #setDoBeaninfo(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_DoBeaninfo()
	 * @model default="true"
	 * @generated
	 */
	boolean isDoBeaninfo();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.BeanDecorator#isDoBeaninfo <em>Do Beaninfo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Do Beaninfo</em>' attribute.
	 * @see #isDoBeaninfo()
	 * @generated
	 */
	void setDoBeaninfo(boolean value);

	/**
	 * Returns the value of the '<em><b>Not Inherited Property Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the list of inherited property names to not use in getAllProperties(). These names are properties that should not be inherited and should not show through. If the inherited property is not on the list then it will show in getAllProperties().
	 * <p>
	 * This list will be empty if all properties are inherited or if the mergeSuperProperties flag is false.
	 * <p>
	 * Note: This attribute is not meant to be changed by clients. It is an internal attribute.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Not Inherited Property Names</em>' attribute list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_NotInheritedPropertyNames()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getNotInheritedPropertyNames();

	/**
	 * Returns the value of the '<em><b>Not Inherited Method Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the list of inherited method names to not use in eAllOperations(). These names are operations that should not be inherited and should not show through. If the inherited operation is not on the list then it will show in getAllOperations().
	 * <p>
	 * This list will be empty if all operations are inherited or if the mergeSuperBehaviors flag is false.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Not Inherited Method Names</em>' attribute list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_NotInheritedMethodNames()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getNotInheritedMethodNames();

	/**
	 * Returns the value of the '<em><b>Not Inherited Event Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is the list of inherited event names to not use in getAllEvents(). These names are events that should not be inherited and should not show through. If the inherited event is not on the list then it will show in getAllEvents().
	 * <p>
	 * This list will be empty if all events are inherited or if the mergeSuperEvents flag is false.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Not Inherited Event Names</em>' attribute list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getBeanDecorator_NotInheritedEventNames()
	 * @model type="java.lang.String"
	 * @generated
	 */
	EList getNotInheritedEventNames();

	/**
	 * Return the URL of a 16x16 Color icon
	 */
	URL getIconURL();
	
}
