/*******************************************************************************
 * Copyright (c) 2001, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.commonarchivecore.internal.impl;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchivePackage;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WebModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveWrappedException;
import org.eclipse.jst.j2ee.webapplication.WebApp;

/**
 * @generated
 */
public class WebModuleRefImpl extends ModuleRefImpl implements WebModuleRef {


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected WebModuleRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonarchivePackage.Literals.WEB_MODULE_REF;
	}

	/*
	 * @see WebModuleRef#getWebApp()
	 */
	public WebApp getWebApp() throws ArchiveWrappedException {
		return (WebApp) getDeploymentDescriptor();
	}

	/*
	 * @see ModuleRef#isWeb()
	 */
	@Override
	public boolean isWeb() {
		return true;
	}

} //WebModuleRefImpl



