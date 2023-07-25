/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jsp;

import org.eclipse.emf.ecore.EFactory;

import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage
 * @generated
 */
public interface JspFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JspFactory eINSTANCE = org.eclipse.jst.javaee.jsp.internal.impl.JspFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Config</em>'.
	 * @generated
	 */
	JspConfig createJspConfig();

	/**
	 * Returns a new object of class '<em>Property Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Property Group</em>'.
	 * @generated
	 */
	JspPropertyGroup createJspPropertyGroup();

	/**
	 * Returns a new object of class '<em>Tag Lib</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Lib</em>'.
	 * @generated
	 */
	TagLib createTagLib();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	JspPackage getJspPackage();

} //JspFactory
