/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
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
 *  $RCSfile: EventSetDecorator.java,v $
 *  $Revision: 1.8 $  $Date: 2005/10/06 15:18:38 $ 
 */


import org.eclipse.emf.common.util.EList;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Set Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Equivalent to EventSetDecorator in java.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isInDefaultEventSet <em>In Default Event Set</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isUnicast <em>Unicast</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isListenerMethodsExplicitEmpty <em>Listener Methods Explicit Empty</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getAddListenerMethod <em>Add Listener Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getListenerMethods <em>Listener Methods</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getListenerType <em>Listener Type</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getRemoveListenerMethod <em>Remove Listener Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getEventAdapterClass <em>Event Adapter Class</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getSerListMthd <em>Ser List Mthd</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator()
 * @model
 * @generated
 */


public interface EventSetDecorator extends FeatureDecorator{
	
	/**
	 * Returns the value of the '<em><b>In Default Event Set</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>In Default Event Set</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>In Default Event Set</em>' attribute.
	 * @see #isSetInDefaultEventSet()
	 * @see #unsetInDefaultEventSet()
	 * @see #setInDefaultEventSet(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_InDefaultEventSet()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isInDefaultEventSet();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isInDefaultEventSet <em>In Default Event Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>In Default Event Set</em>' attribute.
	 * @see #isSetInDefaultEventSet()
	 * @see #unsetInDefaultEventSet()
	 * @see #isInDefaultEventSet()
	 * @generated
	 */
	void setInDefaultEventSet(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isInDefaultEventSet <em>In Default Event Set</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetInDefaultEventSet()
	 * @see #isInDefaultEventSet()
	 * @see #setInDefaultEventSet(boolean)
	 * @generated
	 */
	void unsetInDefaultEventSet();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isInDefaultEventSet <em>In Default Event Set</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>In Default Event Set</em>' attribute is set.
	 * @see #unsetInDefaultEventSet()
	 * @see #isInDefaultEventSet()
	 * @see #setInDefaultEventSet(boolean)
	 * @generated
	 */
	boolean isSetInDefaultEventSet();

	/**
	 * Returns the value of the '<em><b>Unicast</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unicast</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unicast</em>' attribute.
	 * @see #isSetUnicast()
	 * @see #unsetUnicast()
	 * @see #setUnicast(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_Unicast()
	 * @model unsettable="true"
	 * @generated
	 */
	boolean isUnicast();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isUnicast <em>Unicast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unicast</em>' attribute.
	 * @see #isSetUnicast()
	 * @see #unsetUnicast()
	 * @see #isUnicast()
	 * @generated
	 */
	void setUnicast(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isUnicast <em>Unicast</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetUnicast()
	 * @see #isUnicast()
	 * @see #setUnicast(boolean)
	 * @generated
	 */
	void unsetUnicast();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isUnicast <em>Unicast</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Unicast</em>' attribute is set.
	 * @see #unsetUnicast()
	 * @see #isUnicast()
	 * @see #setUnicast(boolean)
	 * @generated
	 */
	boolean isSetUnicast();

	/**
	 * Returns the value of the '<em><b>Listener Methods Explicit Empty</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Set true if the listenerMethods feature is explicitly set as empty and is not to have listener methods merged in from BeanInfo or reflection.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Listener Methods Explicit Empty</em>' attribute.
	 * @see #setListenerMethodsExplicitEmpty(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_ListenerMethodsExplicitEmpty()
	 * @model
	 * @generated
	 */
	boolean isListenerMethodsExplicitEmpty();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isListenerMethodsExplicitEmpty <em>Listener Methods Explicit Empty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listener Methods Explicit Empty</em>' attribute.
	 * @see #isListenerMethodsExplicitEmpty()
	 * @generated
	 */
	void setListenerMethodsExplicitEmpty(boolean value);

	/**
	 * Returns the value of the '<em><b>Add Listener Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Add Listener Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Add Listener Method</em>' reference.
	 * @see #setAddListenerMethod(Method)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_AddListenerMethod()
	 * @model required="true"
	 * @generated
	 */
	Method getAddListenerMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getAddListenerMethod <em>Add Listener Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Add Listener Method</em>' reference.
	 * @see #getAddListenerMethod()
	 * @generated
	 */
	void setAddListenerMethod(Method value);

	/**
	 * Returns the value of the '<em><b>Listener Methods</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.beaninfo.MethodProxy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listener Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * listener methods. If it is desired that the list be explicitly empty and not have BeanInfo set it, then set listenerMethodsExplicitEmpty to true.
	 * <p>
	 * ListenerMethods will be decorated with MethodDecorators.
	 * <p>
	 * Note: This is a derived setting, which means it will not notify out changes to it. To here changes to it, listen on "serListMthd" notifications instead.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Listener Methods</em>' reference list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_ListenerMethods()
	 * @model type="org.eclipse.jem.internal.beaninfo.MethodProxy" required="true" transient="true" volatile="true" derived="true"
	 * @generated
	 */
	EList getListenerMethods();

	/**
	 * Returns the value of the '<em><b>Listener Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listener Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listener Type</em>' reference.
	 * @see #setListenerType(JavaClass)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_ListenerType()
	 * @model required="true"
	 * @generated
	 */
	JavaClass getListenerType();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getListenerType <em>Listener Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listener Type</em>' reference.
	 * @see #getListenerType()
	 * @generated
	 */
	void setListenerType(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Remove Listener Method</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Remove Listener Method</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Remove Listener Method</em>' reference.
	 * @see #setRemoveListenerMethod(Method)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_RemoveListenerMethod()
	 * @model required="true"
	 * @generated
	 */
	Method getRemoveListenerMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getRemoveListenerMethod <em>Remove Listener Method</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Remove Listener Method</em>' reference.
	 * @see #getRemoveListenerMethod()
	 * @generated
	 */
	void setRemoveListenerMethod(Method value);

	/**
	 * Returns the value of the '<em><b>Event Adapter Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * For some listener interfaces an adapter class is provided that implements default no-op methods, e.g. java.awt.event.FocusEvent which has java.awt.event.FocusAdapter. The Adapter class is provided in a key/value pair on the java.beans.EventSetDescriptor with a key defined in a static final constants EVENTADAPTERCLASS = "eventAdapterClass".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event Adapter Class</em>' reference.
	 * @see #setEventAdapterClass(JavaClass)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_EventAdapterClass()
	 * @model
	 * @generated
	 */
	JavaClass getEventAdapterClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getEventAdapterClass <em>Event Adapter Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event Adapter Class</em>' reference.
	 * @see #getEventAdapterClass()
	 * @generated
	 */
	void setEventAdapterClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Ser List Mthd</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.beaninfo.MethodProxy}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This is a private feature. It is used internally only. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ser List Mthd</em>' containment reference list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_SerListMthd()
	 * @model type="org.eclipse.jem.internal.beaninfo.MethodProxy" containment="true" required="true"
	 * @generated
	 */
	EList getSerListMthd();

}
