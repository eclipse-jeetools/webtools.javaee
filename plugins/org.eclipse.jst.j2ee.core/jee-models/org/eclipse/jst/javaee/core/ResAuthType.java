/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResAuthType.java,v 1.1 2007/05/16 06:42:29 cbridgha Exp $
 */
package org.eclipse.jst.javaee.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Res Auth Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The res-authType specifies whether the Deployment Component
 * 	code signs on programmatically to the resource manager, or
 * 	whether the Container will sign on to the resource manager
 * 	on behalf of the Deployment Component. In the latter case,
 * 	the Container uses information that is supplied by the
 * 	Deployer.
 * 
 * 	The value must be one of the two following:
 * 
 * 	    Application
 * 	    Container
 * 
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage#getResAuthType()
 * @generated
 */
public final class ResAuthType extends AbstractEnumerator {
	/**
	 * The '<em><b>Application</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Application</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #APPLICATION_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int APPLICATION = 0;

	/**
	 * The '<em><b>Container</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Container</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #CONTAINER_LITERAL
	 * @generated
	 * @ordered
	 */
	public static final int CONTAINER = 1;

	/**
	 * The '<em><b>Application</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #APPLICATION
	 * @generated
	 * @ordered
	 */
	public static final ResAuthType APPLICATION_LITERAL = new ResAuthType(APPLICATION, "Application", "Application"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Container</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CONTAINER
	 * @generated
	 * @ordered
	 */
	public static final ResAuthType CONTAINER_LITERAL = new ResAuthType(CONTAINER, "Container", "Container"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * An array of all the '<em><b>Res Auth Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ResAuthType[] VALUES_ARRAY =
		new ResAuthType[] {
			APPLICATION_LITERAL,
			CONTAINER_LITERAL,
		};

	/**
	 * A public read-only list of all the '<em><b>Res Auth Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Res Auth Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResAuthType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Auth Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ResAuthType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Res Auth Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ResAuthType get(int value) {
		switch (value) {
			case APPLICATION: return APPLICATION_LITERAL;
			case CONTAINER: return CONTAINER_LITERAL;
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private ResAuthType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //ResAuthType
