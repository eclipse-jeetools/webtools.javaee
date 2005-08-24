/*******************************************************************************
 * Copyright (c) 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: PTInstanceReference.java,v $
 *  $Revision: 1.4 $  $Date: 2005/08/24 20:20:24 $ 
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
