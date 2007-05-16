/**
 * <copyright>
 * </copyright>
 *
 * $Id: NullCharType.java,v 1.1 2007/05/16 06:42:31 cbridgha Exp $
 */
package org.eclipse.jst.javaee.web;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Null Char Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.web.internal.metadata.WebPackage#getNullCharType()
 * @generated
 */
public final class NullCharType extends AbstractEnumerator {
	/**
	 * The '<em><b></b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b></b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #__LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int _ = 0;

	/**
	 * The '<em><b></b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #_
	 * @generated
	 * @ordered
	 */
	public static final NullCharType __LITERAL = new NullCharType(_, "_", ""); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Null Char Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final NullCharType[] VALUES_ARRAY =
		new NullCharType[] {
			__LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Null Char Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Null Char Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NullCharType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NullCharType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Null Char Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NullCharType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NullCharType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Null Char Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static NullCharType get(int value) {
		switch (value) {
			case _: return __LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private NullCharType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //NullCharType
