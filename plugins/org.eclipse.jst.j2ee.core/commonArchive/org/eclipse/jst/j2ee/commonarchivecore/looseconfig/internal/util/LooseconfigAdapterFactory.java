/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.util;


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfiguration;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;


/**
 * @lastgen class LooseconfigAdapterFactory extends AdapterFactoryImpl {}
 */
public class LooseconfigAdapterFactory extends AdapterFactoryImpl {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected static LooseconfigPackage modelPackage;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseconfigAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = LooseconfigPackage.eINSTANCE;
		}
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected LooseconfigSwitch modelSwitch =
		new LooseconfigSwitch() {
			public Object caseLooseApplication(LooseApplication object) {
				return createLooseApplicationAdapter();
			}
			public Object caseLooseArchive(LooseArchive object) {
				return createLooseArchiveAdapter();
			}
			public Object caseLooseLibrary(LooseLibrary object) {
				return createLooseLibraryAdapter();
			}
			public Object caseLooseModule(LooseModule object) {
				return createLooseModuleAdapter();
			}
			public Object caseLooseConfiguration(LooseConfiguration object) {
				return createLooseConfigurationAdapter();
			}
			public Object caseLooseWARFile(LooseWARFile object) {
				return createLooseWARFileAdapter();
			}
			public Object defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAdapter(Notifier target) {
		return (Adapter)modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * By default create methods return null so that we can easily ignore cases. It's useful to
	 * ignore a case when inheritance will catch all the cases anyway.
	 */

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createLooseApplicationAdapter() {

		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createLooseArchiveAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createLooseLibraryAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createLooseWARFileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc --> This default
	 * implementation returns null. <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createLooseModuleAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createLooseConfigurationAdapter() {
		return null;
	}

} //LooseconfigAdapterFactory



