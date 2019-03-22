/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.instantiation;

import org.eclipse.jem.internal.instantiation.base.IJavaInstance;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>PT Instance Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jem.internal.instantiation.PTInstanceReference#getReference <em>Reference</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInstanceReference()
 * @model
 * @generated
 */
public interface PTInstanceReference extends PTExpression{
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reference</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reference</em>' reference.
	 * @see #setReference(IJavaInstance)
	 * @see org.eclipse.jem.internal.instantiation.InstantiationPackage#getPTInstanceReference_Reference()
	 * @model required="true"
	 * @generated
	 */
	IJavaInstance getReference();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.instantiation.PTInstanceReference#getReference <em>Reference</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' reference.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(IJavaInstance value);

} // PTInstanceReference
