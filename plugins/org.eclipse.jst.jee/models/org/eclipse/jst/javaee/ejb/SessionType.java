/**
 * <copyright>
 * </copyright>
 *
 * $Id: SessionType.java,v 1.1 2007/03/20 18:04:36 jsholl Exp $
 */
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Session Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The session-typeType describes whether the session bean is a
 * 	stateful session or stateless session. It is used by
 * 	session-type elements.
 * 
 * 	The value must be one of the two following:
 * 
 * 	    Stateful
 * 	    Stateless
 * 
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getSessionType()
 * @generated
 */
public final class SessionType extends AbstractEnumerator {
	/**
	 * The '<em><b>Stateful</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stateful</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATEFUL_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int STATEFUL = 0;

	/**
	 * The '<em><b>Stateless</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Stateless</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #STATELESS_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int STATELESS = 1;

	/**
	 * The '<em><b>Stateful</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATEFUL
	 * @generated
	 * @ordered
	 */
	public static final SessionType STATEFUL_LITERAL = new SessionType(STATEFUL, "Stateful", "Stateful"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Stateless</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #STATELESS
	 * @generated
	 * @ordered
	 */
	public static final SessionType STATELESS_LITERAL = new SessionType(STATELESS, "Stateless", "Stateless"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Session Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final SessionType[] VALUES_ARRAY =
		new SessionType[] {
			STATEFUL_LITERAL,
			STATELESS_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Session Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Session Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SessionType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SessionType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Session Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SessionType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			SessionType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Session Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SessionType get(int value) {
		switch (value) {
			case STATEFUL: return STATEFUL_LITERAL;
			case STATELESS: return STATELESS_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private SessionType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //SessionType
