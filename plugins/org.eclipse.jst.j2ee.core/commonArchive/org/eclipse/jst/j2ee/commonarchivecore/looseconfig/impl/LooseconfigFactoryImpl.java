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
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseConfiguration;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseconfigFactory;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.LooseconfigPackage;


public class LooseconfigFactoryImpl extends EFactoryImpl implements LooseconfigFactory, EFactory {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseconfigFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LooseconfigPackage.LOOSE_APPLICATION :
				return createLooseApplication();
			case LooseconfigPackage.LOOSE_LIBRARY :
				return createLooseLibrary();
			case LooseconfigPackage.LOOSE_MODULE :
				return createLooseModule();
			case LooseconfigPackage.LOOSE_CONFIGURATION :
				return createLooseConfiguration();
			case LooseconfigPackage.LOOSE_WAR_FILE :
				return createLooseWARFile();
			default :
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseApplication createLooseApplication() {
		LooseApplicationImpl looseApplication = new LooseApplicationImpl();
		return looseApplication;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseLibrary createLooseLibrary() {
		LooseLibraryImpl looseLibrary = new LooseLibraryImpl();
		return looseLibrary;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseWARFile createLooseWARFile() {
		LooseWARFileImpl looseWARFile = new LooseWARFileImpl();
		return looseWARFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseModule createLooseModule() {
		LooseModuleImpl looseModule = new LooseModuleImpl();
		return looseModule;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseConfiguration createLooseConfiguration() {
		LooseConfigurationImpl looseConfiguration = new LooseConfigurationImpl();
		return looseConfiguration;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseconfigPackage getLooseconfigPackage() {
		return (LooseconfigPackage) getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static LooseconfigPackage getPackage() {
		return LooseconfigPackage.eINSTANCE;
	}

} //LooseconfigFactoryImpl



