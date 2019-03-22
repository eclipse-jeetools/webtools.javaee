/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Method Interface Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 *         The method-intf element allows a method element to
 *         differentiate between the methods with the same name and
 *         signature that are multiply defined across the home and
 *         component interfaces (e.g, in both an enterprise bean's
 *         remote and local interfaces or in both an enterprise bean's
 *         home and remote interfaces, etc.); the component and web
 *         service endpoint interfaces, and so on. 
 *         
 *         Local applies to the local component interface, local business 
 *         interfaces, and the no-interface view. 
 *         
 *         Remote applies to both remote component interface and the remote 
 *         business interfaces.  
 *         
 *         ServiceEndpoint refers to methods exposed through a web service
 *         endpoint.
 *         
 *         Timer refers to the bean's timeout callback methods.
 *         
 *         MessageEndpoint refers to the methods of a message-driven bean's
 *         message-listener interface.
 *         
 *         LifecycleCallback refers to the PostConstruct and PreDestroy
 *         lifecycle callback methods of a singleton session bean and
 *         to the PostConstruct, PreDestroy, PrePassivate, and PostActivate
 *         lifecycle callback methods of a stateful session bean.
 *         
 *         The method-intf element must be one of the following:
 *         
 *         Home
 *         Remote
 *         LocalHome
 *         Local
 *         ServiceEndpoint
 *         Timer
 *         MessageEndpoint
 *         LifecycleCallback
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMethodInterfaceType()
 * @generated
 */
public enum MethodInterfaceType implements Enumerator
{
	/**
	 * The '<em><b>Home</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HOME
	 * @generated
	 * @ordered
	 */
	HOME_LITERAL(0, "Home", "Home"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Remote</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMOTE
	 * @generated
	 * @ordered
	 */
	REMOTE_LITERAL(1, "Remote", "Remote"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Local Home</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL_HOME
	 * @generated
	 * @ordered
	 */
	LOCAL_HOME_LITERAL(2, "LocalHome", "LocalHome"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Local</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL
	 * @generated
	 * @ordered
	 */
	LOCAL_LITERAL(3, "Local", "Local"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Service Endpoint</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SERVICE_ENDPOINT
	 * @generated
	 * @ordered
	 */
	SERVICE_ENDPOINT_LITERAL(4, "ServiceEndpoint", "ServiceEndpoint"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Timer</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               @since Java EE 6, EJB 3.1
	 *             
	 * <!-- end-model-doc -->
	 * @see #TIMER
	 * @generated
	 * @ordered
	 */
	TIMER_LITERAL(5, "Timer", "Timer"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Message Endpoint</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               @since Java EE 6, EJB 3.1
	 *             
	 * <!-- end-model-doc -->
	 * @see #MESSAGE_ENDPOINT
	 * @generated
	 * @ordered
	 */
	MESSAGE_ENDPOINT_LITERAL(6, "MessageEndpoint", "MessageEndpoint"), //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Lifecycle Callback</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LIFECYCLE_CALLBACK_VALUE
	 * @generated
	 * @ordered
	 */
	LIFECYCLE_CALLBACK(7, "LifecycleCallback", "LifecycleCallback"); //$NON-NLS-1$ //$NON-NLS-2$
	/**
	 * The '<em><b>Home</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Home</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #HOME_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int HOME = 0;

	/**
	 * The '<em><b>Remote</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Remote</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REMOTE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int REMOTE = 1;

	/**
	 * The '<em><b>Local Home</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local Home</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL_HOME_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int LOCAL_HOME = 2;

	/**
	 * The '<em><b>Local</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Local</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #LOCAL_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int LOCAL = 3;

	/**
	 * The '<em><b>Service Endpoint</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Service Endpoint</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SERVICE_ENDPOINT_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int SERVICE_ENDPOINT = 4;

	/**
	 * The '<em><b>Timer</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               @since Java EE 6, EJB 3.1
	 *             
	 * <!-- end-model-doc -->
	 * @see #TIMER_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int TIMER = 5;

	/**
	 * The '<em><b>Message Endpoint</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               @since Java EE 6, EJB 3.1
	 *             
	 * <!-- end-model-doc -->
	 * @see #MESSAGE_ENDPOINT_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int MESSAGE_ENDPOINT = 6;

	/**
	 * The '<em><b>Lifecycle Callback</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 *               @since Java EE 7, EJB 3.2
	 *             
	 * <!-- end-model-doc -->
	 * @see #LIFECYCLE_CALLBACK
	 * @generated
	 * @ordered
	 */
	public static final int LIFECYCLE_CALLBACK_VALUE = 7;

	/**
	 * An array of all the '<em><b>Method Interface Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final MethodInterfaceType[] VALUES_ARRAY =
		new MethodInterfaceType[] {
			HOME_LITERAL,
			REMOTE_LITERAL,
			LOCAL_HOME_LITERAL,
			LOCAL_LITERAL,
			SERVICE_ENDPOINT_LITERAL,
			TIMER_LITERAL,
			MESSAGE_ENDPOINT_LITERAL,
			LIFECYCLE_CALLBACK,
		};

	/**
	 * A public read-only list of all the '<em><b>Method Interface Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<MethodInterfaceType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Method Interface Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MethodInterfaceType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MethodInterfaceType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Method Interface Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MethodInterfaceType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			MethodInterfaceType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Method Interface Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static MethodInterfaceType get(int value) {
		switch (value) {
			case HOME: return HOME_LITERAL;
			case REMOTE: return REMOTE_LITERAL;
			case LOCAL_HOME: return LOCAL_HOME_LITERAL;
			case LOCAL: return LOCAL_LITERAL;
			case SERVICE_ENDPOINT: return SERVICE_ENDPOINT_LITERAL;
			case TIMER: return TIMER_LITERAL;
			case MESSAGE_ENDPOINT: return MESSAGE_ENDPOINT_LITERAL;
			case LIFECYCLE_CALLBACK_VALUE: return LIFECYCLE_CALLBACK;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MethodInterfaceType(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
}
