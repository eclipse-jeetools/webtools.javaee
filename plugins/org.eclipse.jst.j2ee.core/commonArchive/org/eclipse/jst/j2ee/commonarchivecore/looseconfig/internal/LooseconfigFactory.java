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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal;


import org.eclipse.emf.ecore.EFactory;


public interface LooseconfigFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	LooseconfigFactory eINSTANCE = new org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl.LooseconfigFactoryImpl();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseApplication value
	 */
	LooseApplication createLooseApplication();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseLibrary value
	 */
	LooseLibrary createLooseLibrary();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseWARFile value
	 */
	LooseWARFile createLooseWARFile();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseModule value
	 */
	LooseModule createLooseModule();

	/**
	 * @generated This field/method will be replaced during code generation
	 * @return LooseConfiguration value
	 */
	LooseConfiguration createLooseConfiguration();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	LooseconfigPackage getLooseconfigPackage();

} //LooseconfigFactory



