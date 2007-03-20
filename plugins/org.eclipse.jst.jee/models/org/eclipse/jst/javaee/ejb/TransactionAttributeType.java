/**
 * <copyright>
 * </copyright>
 *
 * $Id: TransactionAttributeType.java,v 1.1 2007/03/20 18:04:36 jsholl Exp $
 */
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Transaction Attribute Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The trans-attributeType specifies how the container must
 * 	manage the transaction boundaries when delegating a method
 * 	invocation to an enterprise bean's business method.
 * 
 * 	The value must be one of the following:
 * 
 * 	    NotSupported
 * 	    Supports
 * 	    Required
 * 	    RequiresNew
 * 	    Mandatory
 * 	    Never
 * 
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getTransactionAttributeType()
 * @generated
 */
public final class TransactionAttributeType extends AbstractEnumerator {
	/**
	 * The '<em><b>Not Supported</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Not Supported</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NOT_SUPPORTED_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int NOT_SUPPORTED = 0;

	/**
	 * The '<em><b>Supports</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Supports</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUPPORTS_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int SUPPORTS = 1;

	/**
	 * The '<em><b>Required</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Required</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUIRED_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int REQUIRED = 2;

	/**
	 * The '<em><b>Requires New</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Requires New</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #REQUIRES_NEW_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int REQUIRES_NEW = 3;

	/**
	 * The '<em><b>Mandatory</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Mandatory</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #MANDATORY_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int MANDATORY = 4;

	/**
	 * The '<em><b>Never</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Never</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NEVER_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int NEVER = 5;

	/**
	 * The '<em><b>Not Supported</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOT_SUPPORTED
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType NOT_SUPPORTED_LITERAL = new TransactionAttributeType(NOT_SUPPORTED, "NotSupported", "NotSupported"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Supports</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUPPORTS
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType SUPPORTS_LITERAL = new TransactionAttributeType(SUPPORTS, "Supports", "Supports"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Required</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUIRED
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType REQUIRED_LITERAL = new TransactionAttributeType(REQUIRED, "Required", "Required"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Requires New</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REQUIRES_NEW
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType REQUIRES_NEW_LITERAL = new TransactionAttributeType(REQUIRES_NEW, "RequiresNew", "RequiresNew"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Mandatory</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #MANDATORY
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType MANDATORY_LITERAL = new TransactionAttributeType(MANDATORY, "Mandatory", "Mandatory"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Never</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NEVER
	 * @generated
	 * @ordered
	 */
	public static final TransactionAttributeType NEVER_LITERAL = new TransactionAttributeType(NEVER, "Never", "Never"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Transaction Attribute Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TransactionAttributeType[] VALUES_ARRAY =
		new TransactionAttributeType[] {
			NOT_SUPPORTED_LITERAL,
			SUPPORTS_LITERAL,
			REQUIRED_LITERAL,
			REQUIRES_NEW_LITERAL,
			MANDATORY_LITERAL,
			NEVER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Transaction Attribute Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Transaction Attribute Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionAttributeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionAttributeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transaction Attribute Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionAttributeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TransactionAttributeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Transaction Attribute Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransactionAttributeType get(int value) {
		switch (value) {
			case NOT_SUPPORTED: return NOT_SUPPORTED_LITERAL;
			case SUPPORTS: return SUPPORTS_LITERAL;
			case REQUIRED: return REQUIRED_LITERAL;
			case REQUIRES_NEW: return REQUIRES_NEW_LITERAL;
			case MANDATORY: return MANDATORY_LITERAL;
			case NEVER: return NEVER_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TransactionAttributeType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //TransactionAttributeType
