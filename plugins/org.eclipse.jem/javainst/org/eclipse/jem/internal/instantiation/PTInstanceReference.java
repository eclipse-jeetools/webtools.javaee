/**
 * <copyright>
 * </copyright>
 *
 * %W%
 * @version %I% %H%
 */
package org.eclipse.jem.internal.instantiation;

import org.eclipse.jem.internal.instantiation.base.IJavaObjectInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PT Instance Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTInstanceReference#getObject <em>Object</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInstanceReference()
 * @model 
 * @generated
 */
public interface PTInstanceReference extends PTExpression {
	/**
	 * Returns the value of the '<em><b>Object</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Object</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Object</em>' reference.
	 * @see #setObject(IJavaObjectInstance)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInstanceReference_Object()
	 * @model required="true"
	 * @generated
	 */
	IJavaObjectInstance getObject();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTInstanceReference#getObject <em>Object</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object</em>' reference.
	 * @see #getObject()
	 * @generated
	 */
	void setObject(IJavaObjectInstance value);

} // PTInstanceReference
