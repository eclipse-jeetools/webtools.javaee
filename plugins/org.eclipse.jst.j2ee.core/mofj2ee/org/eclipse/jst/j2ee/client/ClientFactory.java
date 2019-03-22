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
package org.eclipse.jst.j2ee.client;

import org.eclipse.emf.ecore.EFactory;
/**
 * @generated
 * @since 1.0 */
public interface ClientFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClientFactory eINSTANCE = org.eclipse.jst.j2ee.client.internal.impl.ClientFactoryImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ApplicationClient value
	 */
	ApplicationClient createApplicationClient();

	/**
	 * @generated This field/method will be replaced during code generation.
	 * @return ClientPackage value
	 */
	ClientPackage getClientPackage();

}





