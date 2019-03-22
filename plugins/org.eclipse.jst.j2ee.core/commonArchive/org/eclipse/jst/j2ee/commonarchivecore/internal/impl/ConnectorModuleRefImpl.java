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
import org.eclipse.jst.j2ee.commonarchivecore.internal.ConnectorModuleRef;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.ArchiveWrappedException;
import org.eclipse.jst.j2ee.jca.Connector;

/**
 * @generated
 */
public class ConnectorModuleRefImpl extends ModuleRefImpl implements ConnectorModuleRef {
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected ConnectorModuleRefImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CommonarchivePackage.Literals.CONNECTOR_MODULE_REF;
	}

	public Connector getConnector() throws ArchiveWrappedException {
		return (Connector) getDeploymentDescriptor();
	}

	/*
	 * @see ModuleRef#isConnector()
	 */
	@Override
	public boolean isConnector() {
		return true;
	}

} //ConnectorModuleRefImpl



