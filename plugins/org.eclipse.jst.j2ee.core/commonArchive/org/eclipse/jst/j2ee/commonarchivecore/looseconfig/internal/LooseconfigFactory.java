/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseconfigPackage
 * @generated
 */
public interface LooseconfigFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LooseconfigFactory eINSTANCE = org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Loose Application</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Loose Application</em>'.
	 * @generated
	 */
	LooseApplication createLooseApplication();

	/**
	 * Returns a new object of class '<em>Loose Library</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Loose Library</em>'.
	 * @generated
	 */
	LooseLibrary createLooseLibrary();

	/**
	 * Returns a new object of class '<em>Loose Module</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Loose Module</em>'.
	 * @generated
	 */
	LooseModule createLooseModule();

	/**
	 * Returns a new object of class '<em>Loose Configuration</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Loose Configuration</em>'.
	 * @generated
	 */
	LooseConfiguration createLooseConfiguration();

	/**
	 * Returns a new object of class '<em>Loose WAR File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Loose WAR File</em>'.
	 * @generated
	 */
	LooseWARFile createLooseWARFile();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	LooseconfigPackage getLooseconfigPackage();

} //LooseconfigFactory
