/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.jsp;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.internal.jsp.JspPackage
 * @generated
 */
public interface JspFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JspFactory eINSTANCE = new org.eclipse.jst.j2ee.jsp.impl.JspFactoryImpl();

	/**
	 * Returns a new object of class '<em>JSP Config</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>JSP Config</em>'.
	 * @generated
	 */
	JSPConfig createJSPConfig();

	/**
	 * Returns a new object of class '<em>JSP Property Group</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>JSP Property Group</em>'.
	 * @generated
	 */
	JSPPropertyGroup createJSPPropertyGroup();

	/**
	 * Returns a new object of class '<em>Tag Lib Ref Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Tag Lib Ref Type</em>'.
	 * @generated
	 */
	TagLibRefType createTagLibRefType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	JspPackage getJspPackage();

} //JspFactory
