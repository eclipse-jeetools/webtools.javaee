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


import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseApplication;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseArchive;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseConfiguration;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseLibrary;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseModule;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseWARFile;
import org.eclipse.jst.j2ee.commonarchivecore.looseconfig.internal.LooseconfigPackage;


/**
 * @lastgen class LooseconfigSwitch {}
 */
public class LooseconfigSwitch {
	/**
	 * The cached model package
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected static LooseconfigPackage modelPackage;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public LooseconfigSwitch() {
		if (modelPackage == null) {
			modelPackage = LooseconfigPackage.eINSTANCE;
		}
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object doSwitch(EObject theEObject) {
		return doSwitch(theEObject.eClass(), theEObject);
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(EClass theEClass, EObject theEObject) {
		if (theEClass.eContainer() == modelPackage) {
			return doSwitch(theEClass.getClassifierID(), theEObject);
		}
		else {
			List eSuperTypes = theEClass.getESuperTypes();
			return
				eSuperTypes.isEmpty() ?
					defaultCase(theEObject) :
					doSwitch((EClass)eSuperTypes.get(0), theEObject);
		}
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	protected Object doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case LooseconfigPackage.LOOSE_APPLICATION: {
				LooseApplication looseApplication = (LooseApplication)theEObject;
				Object result = caseLooseApplication(looseApplication);
				if (result == null) result = caseLooseArchive(looseApplication);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LooseconfigPackage.LOOSE_ARCHIVE: {
				LooseArchive looseArchive = (LooseArchive)theEObject;
				Object result = caseLooseArchive(looseArchive);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LooseconfigPackage.LOOSE_LIBRARY: {
				LooseLibrary looseLibrary = (LooseLibrary)theEObject;
				Object result = caseLooseLibrary(looseLibrary);
				if (result == null) result = caseLooseArchive(looseLibrary);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LooseconfigPackage.LOOSE_MODULE: {
				LooseModule looseModule = (LooseModule)theEObject;
				Object result = caseLooseModule(looseModule);
				if (result == null) result = caseLooseArchive(looseModule);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LooseconfigPackage.LOOSE_CONFIGURATION: {
				LooseConfiguration looseConfiguration = (LooseConfiguration)theEObject;
				Object result = caseLooseConfiguration(looseConfiguration);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case LooseconfigPackage.LOOSE_WAR_FILE: {
				LooseWARFile looseWARFile = (LooseWARFile)theEObject;
				Object result = caseLooseWARFile(looseWARFile);
				if (result == null) result = caseLooseModule(looseWARFile);
				if (result == null) result = caseLooseArchive(looseWARFile);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseLooseApplication(LooseApplication object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseLooseArchive(LooseArchive object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseLooseLibrary(LooseLibrary object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseLooseWARFile(LooseWARFile object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseLooseModule(LooseModule object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseLooseConfiguration(LooseConfiguration object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object defaultCase(EObject object) {
		return null;
	}

} //LooseconfigSwitch



