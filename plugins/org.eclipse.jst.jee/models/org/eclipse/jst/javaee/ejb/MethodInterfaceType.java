/**
 * <copyright>
 * </copyright>
 *
 * $Id: MethodInterfaceType.java,v 1.1 2007/03/20 18:04:36 jsholl Exp $
 */
package org.eclipse.jst.javaee.ejb;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Method Interface Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The method-intf element allows a method element to
 * 	differentiate between the methods with the same name and
 * 	signature that are multiply defined across the home and
 * 	component interfaces (e.g, in both an enterprise bean's
 * 	remote and local interfaces or in both an enterprise bean's
 * 	home and remote interfaces, etc.); the component and web
 * 	service endpoint interfaces, and so on. The Local applies to
 *         both local component interface and local business interface.
 *         Similarly, Remote applies to both remote component interface
 *         and the remote business interface.
 * 
 * 	The method-intf element must be one of the following:
 * 
 * 	    Home
 * 	    Remote
 * 	    LocalHome
 * 	    Local
 * 	    ServiceEndpoint
 * 
 *       
 * <!-- end-model-doc -->
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getMethodInterfaceType()
 * @generated
 */
public final class MethodInterfaceType extends AbstractEnumerator {
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
	 * The '<em><b>Home</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #HOME
	 * @generated
	 * @ordered
	 */
	public static final MethodInterfaceType HOME_LITERAL = new MethodInterfaceType(HOME, "Home", "Home"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Remote</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #REMOTE
	 * @generated
	 * @ordered
	 */
	public static final MethodInterfaceType REMOTE_LITERAL = new MethodInterfaceType(REMOTE, "Remote", "Remote"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Local Home</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL_HOME
	 * @generated
	 * @ordered
	 */
	public static final MethodInterfaceType LOCAL_HOME_LITERAL = new MethodInterfaceType(LOCAL_HOME, "LocalHome", "LocalHome"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Local</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #LOCAL
	 * @generated
	 * @ordered
	 */
	public static final MethodInterfaceType LOCAL_LITERAL = new MethodInterfaceType(LOCAL, "Local", "Local"); //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * The '<em><b>Service Endpoint</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SERVICE_ENDPOINT
	 * @generated
	 * @ordered
	 */
	public static final MethodInterfaceType SERVICE_ENDPOINT_LITERAL = new MethodInterfaceType(SERVICE_ENDPOINT, "ServiceEndpoint", "ServiceEndpoint"); //$NON-NLS-1$ //$NON-NLS-2$

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
		};

	/**
	 * A public read-only list of all the '<em><b>Method Interface Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

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
		}
		return null;	
	}

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private MethodInterfaceType(int value, String name, String literal) {
		super(value, name, literal);
	}

} //MethodInterfaceType
