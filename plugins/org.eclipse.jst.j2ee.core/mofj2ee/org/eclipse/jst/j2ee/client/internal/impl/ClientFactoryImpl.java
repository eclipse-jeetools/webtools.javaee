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
package org.eclipse.jst.j2ee.client.internal.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.client.ClientFactory;
import org.eclipse.jst.j2ee.client.ClientPackage;
import org.eclipse.jst.j2ee.client.ResAuthApplicationType;

/**
 * @generated
 */
public class ClientFactoryImpl extends EFactoryImpl implements ClientFactory {
 
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ClientFactory init() {
		try {
			ClientFactory theClientFactory = (ClientFactory)EPackage.Registry.INSTANCE.getEFactory("client.xmi");  //$NON-NLS-1$
			if (theClientFactory != null) {
				return theClientFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ClientFactoryImpl();
	}

	public ClientFactoryImpl() {
		super(); 		
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ClientPackage.APPLICATION_CLIENT: return createApplicationClient();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ClientPackage.RES_AUTH_APPLICATION_TYPE:
				return createResAuthApplicationTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ClientPackage.RES_AUTH_APPLICATION_TYPE:
				return convertResAuthApplicationTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

public static ClientFactory getActiveFactory() {
	return (ClientFactory) getPackage().getEFactoryInstance();
}
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public ApplicationClient createApplicationClient() {
		ApplicationClientImpl applicationClient = new ApplicationClientImpl();
		return applicationClient;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ResAuthApplicationType createResAuthApplicationTypeFromString(EDataType eDataType, String initialValue) {
		ResAuthApplicationType result = ResAuthApplicationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertResAuthApplicationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public ClientPackage getClientPackage() {
		return (ClientPackage)getEPackage();
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public static ClientPackage getPackage() {
		return ClientPackage.eINSTANCE;
	}

}






