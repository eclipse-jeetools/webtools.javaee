package org.eclipse.jem.internal.java;

/*******************************************************************************
 * Copyright (c)  2001, 2003 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: JavaParameter.java,v $
 *  $Revision: 1.1 $  $Date: 2003/10/27 17:12:30 $ 
 */

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EParameter;
/**
 * @generated
 */
public interface JavaParameter extends EParameter, EModelElement{

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the IsFinal attribute
	 */
	boolean isFinal();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.JavaParameter#isFinal <em>Final</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Final</em>' attribute.
	 * @see #isFinal()
	 * @generated
	 */
	void setFinal(boolean value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ParameterKind attribute
	 */
	JavaParameterKind getParameterKind();

	/**
	 * Sets the value of the '{@link org.eclipse.jem.internal.java.JavaParameter#getParameterKind <em>Parameter Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parameter Kind</em>' attribute.
	 * @see org.eclipse.jem.internal.java.JavaParameterKind
	 * @see #getParameterKind()
	 * @generated
	 */
	void setParameterKind(JavaParameterKind value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Is this parameter type an array type.
	 */
	boolean isArray();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * Is this a return parameter.
	 */
	boolean isReturn();

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	JavaHelpers getJavaType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	String getQualifiedName();

}





