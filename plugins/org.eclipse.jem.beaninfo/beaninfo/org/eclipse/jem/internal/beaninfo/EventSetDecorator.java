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
 *  $RCSfile: EventSetDecorator.java,v $
 *  $Revision: 1.1.4.1 $  $Date: 2003/12/16 19:28:47 $ 
 */


import org.eclipse.emf.common.util.EList;

import org.eclipse.jem.internal.java.JavaClass;
import org.eclipse.jem.internal.java.Method;
/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event Set Decorator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isInDefaultEventSet <em>In Default Event Set</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isUnicast <em>Unicast</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isListenerMethodsExplicit <em>Listener Methods Explicit</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getAddListenerMethod <em>Add Listener Method</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getListenerMethods <em>Listener Methods</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getListenerType <em>Listener Type</em>}</li>
 *   <li>{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#getRemoveListenerMethod <em>Remove Listener Method</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator()
 * @model 
 * @generated
 */


public interface EventSetDecorator extends FeatureDecorator{
	public static final String EVENTADAPTERCLASS = "eventAdapterClass"; //$NON-NLS-1$
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
	 * Returns the value of the '<em><b>Listener Methods</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jem.internal.beaninfo.MethodProxy}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listener Methods</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Listener Methods</em>' containment reference list.
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_ListenerMethods()
	 * @model type="org.eclipse.jem.internal.beaninfo.MethodProxy" containment="true" required="true"
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
	 * Returns the value of the '<em><b>Listener Methods Explicit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Listener Methods Explicit</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If the listenerMethods feature is explicitly set, ie. not through the event set descriptor proxy, then this flag must be set true. If it is true, then the listenerMethods will not be brought over from the descriptor proxy, nor will default ones be created if there aren't any specified.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Listener Methods Explicit</em>' attribute.
	 * @see #setListenerMethodsExplicit(boolean)
	 * @see org.eclipse.jem.internal.beaninfo.BeaninfoPackage#getEventSetDecorator_ListenerMethodsExplicit()
	 * @model 
	 * @generated
	 */
	boolean isListenerMethodsExplicit();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.beaninfo.EventSetDecorator#isListenerMethodsExplicit <em>Listener Methods Explicit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Listener Methods Explicit</em>' attribute.
	 * @see #isListenerMethodsExplicit()
	 * @generated
	 */
	void setListenerMethodsExplicit(boolean value);

	/**
	 * For some listener interfaces an adapter class is provided that implements default no-op methods, e.g.
	 * java.awt.event.FocusEvent which has java.awt.event.FocusAdapter.
	 * The Adapter class is provided in a key/value pair on the java.beans.EventSetDescriptor with a key 
	 * defined in a static final constants EVENTADAPTERCLASS = "eventAdapterClass";
	 */	
	JavaClass getEventAdapterClass();

}
