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


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ClientModuleRef;
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


public class CommonarchiveAdapterFactory extends AdapterFactoryImpl {
	protected static CommonarchivePackage modelPackage;

	public CommonarchiveAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = (CommonarchivePackage) EPackage.Registry.INSTANCE.getEPackage(CommonarchivePackage.eNS_URI);
		}
	}

	public boolean isFactoryForType(Object type) {
		if (type == modelPackage) {
			return true;
		}
		if (type instanceof EObject) {
			return ((EObject) type).eClass().eContainer() == modelPackage;
		}
		return false;
	}

	protected CommonarchiveSwitch sw = new CommonarchiveSwitch() {
		public Object caseContainer(Container object) {
			return createContainerAdapter();
		}

		public Object caseWARFile(WARFile object) {
			return createWARFileAdapter();
		}

		public Object caseModuleFile(ModuleFile object) {
			return createModuleFileAdapter();
		}

		public Object caseEARFile(EARFile object) {
			return createEARFileAdapter();
		}

		public Object caseApplicationClientFile(ApplicationClientFile object) {
			return createApplicationClientFileAdapter();
		}

		public Object caseEJBJarFile(EJBJarFile object) {
			return createEJBJarFileAdapter();
		}

		public Object caseRARFile(RARFile object) {
			return createRARFileAdapter();
		}

		public Object caseArchive(Archive object) {
			return createArchiveAdapter();
		}

		public Object caseFile(File object) {
			return createFileAdapter();
		}

		public Object caseReadOnlyDirectory(ReadOnlyDirectory object) {
			return createReadOnlyDirectoryAdapter();
		}
	};

	public Adapter createAdapter(Notifier target) {
		return (Adapter) sw.doSwitch((EObject) target);
	}

	/**
	 * By default create methods return null so that we can easily ignore cases. It's useful to
	 * ignore a case when inheritance will catch all the cases anyway.
	 */

	public Adapter createContainerAdapter() {
		return null;
	}

	public Adapter createEJBComponentAdapter() {
		return null;
	}

	public Adapter createModuleComponentAdapter() {
		return null;
	}

	public Adapter createServletComponentAdapter() {
		return null;
	}

	public Adapter createWARFileAdapter() {
		return null;
	}

	public Adapter createModuleFileAdapter() {
		return null;
	}

	public Adapter createEARFileAdapter() {
		return null;
	}

	public Adapter createApplicationClientFileAdapter() {
		return null;
	}

	public Adapter createEJBJarFileAdapter() {
		return null;
	}

	public Adapter createRARFileAdapter() {
		return null;
	}

	public Adapter createArchiveAdapter() {
		return null;
	}

	public Adapter createFileAdapter() {
		return null;
	}

	public Adapter createEJBClientJarFileAdapter() {
		return null;
	}

	public Adapter createReadOnlyDirectoryAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createModuleRefAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEJBModuleRefAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createWebModuleRefAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createClientModuleRefAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createConnectorModuleRefAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default
	 * implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean isFactoryForTypeGen(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch the delegates to the <code>createXXX</code> methods. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CommonarchiveSwitch modelSwitch = new CommonarchiveSwitch() {
		public Object caseFile(File object) {
			return createFileAdapter();
		}

		public Object caseArchive(Archive object) {
			return createArchiveAdapter();
		}

		public Object caseEJBJarFile(EJBJarFile object) {
			return createEJBJarFileAdapter();
		}

		public Object caseWARFile(WARFile object) {
			return createWARFileAdapter();
		}

		public Object caseEARFile(EARFile object) {
			return createEARFileAdapter();
		}

		public Object caseApplicationClientFile(ApplicationClientFile object) {
			return createApplicationClientFileAdapter();
		}

		public Object caseModuleFile(ModuleFile object) {
			return createModuleFileAdapter();
		}

		public Object caseContainer(Container object) {
			return createContainerAdapter();
		}

		public Object caseReadOnlyDirectory(ReadOnlyDirectory object) {
			return createReadOnlyDirectoryAdapter();
		}

		public Object caseRARFile(RARFile object) {
			return createRARFileAdapter();
		}

		public Object caseModuleRef(ModuleRef object) {
			return createModuleRefAdapter();
		}

		public Object caseEJBModuleRef(EJBModuleRef object) {
			return createEJBModuleRefAdapter();
		}

		public Object caseWebModuleRef(WebModuleRef object) {
			return createWebModuleRefAdapter();
		}

		public Object caseClientModuleRef(ClientModuleRef object) {
			return createClientModuleRefAdapter();
		}

		public Object caseConnectorModuleRef(ConnectorModuleRef object) {
			return createConnectorModuleRefAdapter();
		}

		public Object defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createAdapterGen(Notifier target) {
		return (Adapter) modelSwitch.doSwitch((EObject) target);
	}


	/**
	 * By default create methods return null so that we can easily ignore cases. It's useful to
	 * ignore a case when inheritance will catch all the cases anyway.
	 */

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createContainerAdapterGen() {

		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createWARFileAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createModuleFileAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createApplicationClientFileAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEARFileAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createEJBJarFileAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createRARFileAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createArchiveAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createFileAdapterGen() {
		return null;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public Adapter createReadOnlyDirectoryAdapterGen() {
		return null;
	}

} //CommonarchiveAdapterFactory



