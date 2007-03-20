/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResultTypeMappingType.java,v 1.1 2007/03/20 18:04:36 jsholl Exp $
 */
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Result Type Mapping Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The result-type-mappingType is used in the query element to
 * 	specify whether an abstract schema type returned by a query
 * 	for a select method is to be mapped to an EJBLocalObject or
 * 	EJBObject type.
 * 
 * 	The value must be one of the following:
 * 
 * 	    Local
 * 	    Remote
 * 
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getResultTypeMappingType()
 * @generated
 */
public final class ResultTypeMappingType extends AbstractEnumerator {
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
	public static final int LOCAL = 0;

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
	 * The '<em><b>Local</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL
	 * @generated
	 * @ordered
	 */
	public static final ResultTypeMappingType LOCAL_LITERAL = new ResultTypeMappingType(LOCAL, "Local", "Local"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Remote</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMOTE
	 * @generated
	 * @ordered
	 */
	public static final ResultTypeMappingType REMOTE_LITERAL = new ResultTypeMappingType(REMOTE, "Remote", "Remote"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Result Type Mapping Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ResultTypeMappingType[] VALUES_ARRAY =
		new ResultTypeMappingType[] {
			LOCAL_LITERAL,
			REMOTE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Result Type Mapping Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Result Type Mapping Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResultTypeMappingType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResultTypeMappingType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Result Type Mapping Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResultTypeMappingType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResultTypeMappingType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Result Type Mapping Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResultTypeMappingType get(int value) {
		switch (value) {
			case LOCAL: return LOCAL_LITERAL;
			case REMOTE: return REMOTE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ResultTypeMappingType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ResultTypeMappingType
