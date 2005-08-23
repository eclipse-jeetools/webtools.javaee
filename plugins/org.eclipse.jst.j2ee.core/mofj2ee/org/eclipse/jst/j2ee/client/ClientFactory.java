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
package org.eclipse.jst.j2ee.client;

import org.eclipse.emf.ecore.EFactory;
/**
 * @generated
 */
public interface ClientFactory extends EFactory{
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClientFactory eINSTANCE = new org.eclipse.jst.j2ee.client.internal.impl.ClientFactoryImpl();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ApplicationClient value
	 */
	ApplicationClient createApplicationClient();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	ClientPackage getClientPackage();

}





