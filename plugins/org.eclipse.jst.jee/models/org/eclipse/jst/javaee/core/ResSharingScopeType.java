/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResSharingScopeType.java,v 1.1 2007/03/20 18:04:35 jsholl Exp $
 */
package org.eclipse.jst.javaee.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Res Sharing Scope Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The res-sharing-scope type specifies whether connections
 * 	obtained through the given resource manager connection
 * 	factory reference can be shared. The value, if specified,
 * 	must be one of the two following:
 * 
 * 	    Shareable
 * 	    Unshareable
 * 
 * 	The default value is Shareable.
 * 
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResSharingScopeType()
 * @generated
 */
public final class ResSharingScopeType extends AbstractEnumerator {
	/**
	 * The '<em><b>Shareable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Shareable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SHAREABLE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int SHAREABLE = 0;

	/**
	 * The '<em><b>Unshareable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Unshareable</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #UNSHAREABLE_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int UNSHAREABLE = 1;

	/**
	 * The '<em><b>Shareable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SHAREABLE
	 * @generated
	 * @ordered
	 */
	public static final ResSharingScopeType SHAREABLE_LITERAL = new ResSharingScopeType(SHAREABLE, "Shareable", "Shareable"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Unshareable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #UNSHAREABLE
	 * @generated
	 * @ordered
	 */
	public static final ResSharingScopeType UNSHAREABLE_LITERAL = new ResSharingScopeType(UNSHAREABLE, "Unshareable", "Unshareable"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Res Sharing Scope Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ResSharingScopeType[] VALUES_ARRAY =
		new ResSharingScopeType[] {
			SHAREABLE_LITERAL,
			UNSHAREABLE_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Res Sharing Scope Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Res Sharing Scope Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResSharingScopeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResSharingScopeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Sharing Scope Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResSharingScopeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResSharingScopeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Sharing Scope Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResSharingScopeType get(int value) {
		switch (value) {
			case SHAREABLE: return SHAREABLE_LITERAL;
			case UNSHAREABLE: return UNSHAREABLE_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ResSharingScopeType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ResSharingScopeType
