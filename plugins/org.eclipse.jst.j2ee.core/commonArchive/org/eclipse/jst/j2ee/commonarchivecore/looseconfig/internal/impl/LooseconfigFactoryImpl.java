/*******************************************************************************
 * Copyright (c) 2001, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfiguration;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigFactory;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;


public class LooseconfigFactoryImpl extends EFactoryImpl implements LooseconfigFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LooseconfigFactory init() {
		try {
			LooseconfigFactory theLooseconfigFactory = (LooseconfigFactory)EPackage.Registry.INSTANCE.getEFactory("commonarchive.looseconfig.xmi");  //$NON-NLS-1$
			if (theLooseconfigFactory != null) {
				return theLooseconfigFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LooseconfigFactoryImpl();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseconfigFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LooseconfigPackage.LOOSE_APPLICATION: return createLooseApplication();
			case LooseconfigPackage.LOOSE_LIBRARY: return createLooseLibrary();
			case LooseconfigPackage.LOOSE_MODULE: return createLooseModule();
			case LooseconfigPackage.LOOSE_CONFIGURATION: return createLooseConfiguration();
			case LooseconfigPackage.LOOSE_WAR_FILE: return createLooseWARFile();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public LooseApplication createLooseApplication() {
		LooseApplicationImpl looseApplication = new LooseApplicationImpl();
		return looseApplication;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public LooseLibrary createLooseLibrary() {
		LooseLibraryImpl looseLibrary = new LooseLibraryImpl();
		return looseLibrary;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public LooseWARFile createLooseWARFile() {
		LooseWARFileImpl looseWARFile = new LooseWARFileImpl();
		return looseWARFile;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public LooseModule createLooseModule() {
		LooseModuleImpl looseModule = new LooseModuleImpl();
		return looseModule;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public LooseConfiguration createLooseConfiguration() {
		LooseConfigurationImpl looseConfiguration = new LooseConfigurationImpl();
		return looseConfiguration;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public LooseconfigPackage getLooseconfigPackage() {
		return (LooseconfigPackage)getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static LooseconfigPackage getPackage() {
		return LooseconfigPackage.eINSTANCE;
	}

} //LooseconfigFactoryImpl



