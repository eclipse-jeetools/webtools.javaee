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
package org.eclipse.jst.j2ee.commonarchivecore.internal.util;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ClientModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ConnectorModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Container;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.File;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.RARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ReadOnlyDirectory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WebModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.CommonarchiveFactoryImpl;


public class CommonarchiveSwitch {
	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static CommonarchivePackage modelPackage;

	protected static CommonarchiveFactory factory;

	public CommonarchiveSwitch() {
		factory = (CommonarchiveFactory) CommonarchiveFactoryImpl.getPackage().getEFactoryInstance();
		modelPackage = CommonarchiveFactoryImpl.getPackage();
	}

	public Object doSwitch(EObject theEObject) {
		EClass theEClass = theEObject.eClass();
		if (theEClass.eContainer() != modelPackage)
			return defaultCase(theEObject);

		switch (theEClass.getClassifierID()) {
			case CommonarchivePackage.WAR_FILE : {
				WARFile castedObject = (WARFile) theEObject;
				Object result = caseWARFile(castedObject);
				if (result == null)
					result = caseModuleFile(castedObject);
				if (result == null)
					result = caseArchive(castedObject);
				if (result == null)
					result = caseContainer(castedObject);
				if (result == null)
					result = caseFile(castedObject);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CommonarchivePackage.EAR_FILE : {
				EARFile castedObject = (EARFile) theEObject;
				Object result = caseEARFile(castedObject);
				if (result == null)
					result = caseModuleFile(castedObject);
				if (result == null)
					result = caseArchive(castedObject);
				if (result == null)
					result = caseContainer(castedObject);
				if (result == null)
					result = caseFile(castedObject);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CommonarchivePackage.APPLICATION_CLIENT_FILE : {
				ApplicationClientFile castedObject = (ApplicationClientFile) theEObject;
				Object result = caseApplicationClientFile(castedObject);
				if (result == null)
					result = caseModuleFile(castedObject);
				if (result == null)
					result = caseArchive(castedObject);
				if (result == null)
					result = caseContainer(castedObject);
				if (result == null)
					result = caseFile(castedObject);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CommonarchivePackage.EJB_JAR_FILE : {
				EJBJarFile castedObject = (EJBJarFile) theEObject;
				Object result = caseEJBJarFile(castedObject);
				if (result == null)
					result = caseModuleFile(castedObject);
				if (result == null)
					result = caseArchive(castedObject);
				if (result == null)
					result = caseContainer(castedObject);
				if (result == null)
					result = caseFile(castedObject);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CommonarchivePackage.RAR_FILE : {
				RARFile castedObject = (RARFile) theEObject;
				Object result = caseRARFile(castedObject);
				if (result == null)
					result = caseModuleFile(castedObject);
				if (result == null)
					result = caseArchive(castedObject);
				if (result == null)
					result = caseContainer(castedObject);
				if (result == null)
					result = caseFile(castedObject);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CommonarchivePackage.ARCHIVE : {
				Archive castedObject = (Archive) theEObject;
				Object result = caseArchive(castedObject);
				if (result == null)
					result = caseContainer(castedObject);
				if (result == null)
					result = caseFile(castedObject);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CommonarchivePackage.FILE : {
				File castedObject = (File) theEObject;
				Object result = caseFile(castedObject);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			case CommonarchivePackage.READ_ONLY_DIRECTORY : {
				ReadOnlyDirectory castedObject = (ReadOnlyDirectory) theEObject;
				Object result = caseReadOnlyDirectory(castedObject);
				if (result == null)
					result = caseContainer(castedObject);
				if (result == null)
					result = caseFile(castedObject);
				if (result == null)
					result = defaultCase(theEObject);
				return result;
			}
			default :
				return defaultCase(theEObject);
		}
	} // doSwitch

	public Object caseContainer(Container object) {
		return null;
	}

	public Object caseWARFile(WARFile object) {
		return null;
	}

	public Object caseModuleFile(ModuleFile object) {
		return null;
	}

	public Object caseEARFile(EARFile object) {
		return null;
	}

	public Object caseApplicationClientFile(ApplicationClientFile object) {
		return null;
	}

	public Object caseEJBJarFile(EJBJarFile object) {
		return null;
	}

	public Object caseRARFile(RARFile object) {
		return null;
	}

	public Object caseArchive(Archive object) {
		return null;
	}

	public Object caseFile(File object) {
		return null;
	}

	public Object caseReadOnlyDirectory(ReadOnlyDirectory object) {
		return null;
	}

	public Object defaultCase(EObject object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseModuleRef(ModuleRef object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseEJBModuleRef(EJBModuleRef object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseWebModuleRef(WebModuleRef object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseClientModuleRef(ClientModuleRef object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseConnectorModuleRef(ConnectorModuleRef object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object doSwitchGen(EObject theEObject) {
		EClass theEClass = theEObject.eClass();
		if (theEClass.eContainer() == modelPackage) {
			switch (theEClass.getClassifierID()) {
				case CommonarchivePackage.FILE : {
					File file = (File) theEObject;
					Object result = caseFile(file);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.ARCHIVE : {
					Archive archive = (Archive) theEObject;
					Object result = caseArchive(archive);
					if (result == null)
						result = caseContainer(archive);
					if (result == null)
						result = caseFile(archive);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.EJB_JAR_FILE : {
					EJBJarFile ejbJarFile = (EJBJarFile) theEObject;
					Object result = caseEJBJarFile(ejbJarFile);
					if (result == null)
						result = caseModuleFile(ejbJarFile);
					if (result == null)
						result = caseArchive(ejbJarFile);
					if (result == null)
						result = caseContainer(ejbJarFile);
					if (result == null)
						result = caseFile(ejbJarFile);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.WAR_FILE : {
					WARFile warFile = (WARFile) theEObject;
					Object result = caseWARFile(warFile);
					if (result == null)
						result = caseModuleFile(warFile);
					if (result == null)
						result = caseArchive(warFile);
					if (result == null)
						result = caseContainer(warFile);
					if (result == null)
						result = caseFile(warFile);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.EAR_FILE : {
					EARFile earFile = (EARFile) theEObject;
					Object result = caseEARFile(earFile);
					if (result == null)
						result = caseModuleFile(earFile);
					if (result == null)
						result = caseArchive(earFile);
					if (result == null)
						result = caseContainer(earFile);
					if (result == null)
						result = caseFile(earFile);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.APPLICATION_CLIENT_FILE : {
					ApplicationClientFile applicationClientFile = (ApplicationClientFile) theEObject;
					Object result = caseApplicationClientFile(applicationClientFile);
					if (result == null)
						result = caseModuleFile(applicationClientFile);
					if (result == null)
						result = caseArchive(applicationClientFile);
					if (result == null)
						result = caseContainer(applicationClientFile);
					if (result == null)
						result = caseFile(applicationClientFile);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.READ_ONLY_DIRECTORY : {
					ReadOnlyDirectory readOnlyDirectory = (ReadOnlyDirectory) theEObject;
					Object result = caseReadOnlyDirectory(readOnlyDirectory);
					if (result == null)
						result = caseContainer(readOnlyDirectory);
					if (result == null)
						result = caseFile(readOnlyDirectory);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.RAR_FILE : {
					RARFile rarFile = (RARFile) theEObject;
					Object result = caseRARFile(rarFile);
					if (result == null)
						result = caseModuleFile(rarFile);
					if (result == null)
						result = caseArchive(rarFile);
					if (result == null)
						result = caseContainer(rarFile);
					if (result == null)
						result = caseFile(rarFile);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.EJB_MODULE_REF : {
					EJBModuleRef ejbModuleRef = (EJBModuleRef) theEObject;
					Object result = caseEJBModuleRef(ejbModuleRef);
					if (result == null)
						result = caseModuleRef(ejbModuleRef);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.WEB_MODULE_REF : {
					WebModuleRef webModuleRef = (WebModuleRef) theEObject;
					Object result = caseWebModuleRef(webModuleRef);
					if (result == null)
						result = caseModuleRef(webModuleRef);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.CLIENT_MODULE_REF : {
					ClientModuleRef clientModuleRef = (ClientModuleRef) theEObject;
					Object result = caseClientModuleRef(clientModuleRef);
					if (result == null)
						result = caseModuleRef(clientModuleRef);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				case CommonarchivePackage.CONNECTOR_MODULE_REF : {
					ConnectorModuleRef connectorModuleRef = (ConnectorModuleRef) theEObject;
					Object result = caseConnectorModuleRef(connectorModuleRef);
					if (result == null)
						result = caseModuleRef(connectorModuleRef);
					if (result == null)
						result = defaultCase(theEObject);
					return result;
				}
				default :
					return defaultCase(theEObject);
			}
		}
		return defaultCase(theEObject);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseContainerGen(Container object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseWARFileGen(WARFile object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseModuleFileGen(ModuleFile object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseApplicationClientFileGen(ApplicationClientFile object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseEARFileGen(EARFile object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseEJBJarFileGen(EJBJarFile object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseRARFileGen(RARFile object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseArchiveGen(Archive object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseFileGen(File object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object caseReadOnlyDirectoryGen(ReadOnlyDirectory object) {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Object defaultCaseGen(EObject object) {
		return null;
	}

} //CommonarchiveSwitch



