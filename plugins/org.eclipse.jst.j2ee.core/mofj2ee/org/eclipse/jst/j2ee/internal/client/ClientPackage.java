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
package org.eclipse.jst.j2ee.internal.client;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.client.ClientFactory;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;



public interface ClientPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "client"; //$NON-NLS-1$

	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT = 0;
	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__ICONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__DISPLAY_NAMES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__DESCRIPTIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT__SMALL_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT__LARGE_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT__DESCRIPTION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT__DISPLAY_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;
	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__VERSION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT__RESOURCE_REFS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT__ENVIRONMENT_PROPS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT__EJB_REFERENCES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT__RESOURCE_ENV_REFS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int APPLICATION_CLIENT__CALLBACK_HANDLER = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__SERVICE_REFS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__MESSAGE_DESTINATION_REFS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Message Destinations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT__MESSAGE_DESTINATIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the the '<em>Application Client</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int APPLICATION_CLIENT_FEATURE_COUNT = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.internal.client.ResAuthApplicationType <em>Res Auth Application Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.internal.client.ResAuthApplicationType
	 * @see org.eclipse.jst.j2ee.internal.client.impl.ClientPackageImpl#getResAuthApplicationType()
	 * @generated
	 */
	int RES_AUTH_APPLICATION_TYPE = 1;


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "client.xmi"; //$NON-NLS-1$
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.internal.client"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ClientPackage eINSTANCE = org.eclipse.jst.j2ee.internal.client.impl.ClientPackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ApplicationClient object
	 */
	EClass getApplicationClient();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.internal.client.ApplicationClient#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.jst.j2ee.internal.client.ApplicationClient#getVersion()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EAttribute getApplicationClient_Version();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getApplicationClient_ResourceRefs();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getApplicationClient_EnvironmentProps();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getApplicationClient_EjbReferences();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getApplicationClient_ResourceEnvRefs();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getApplicationClient_CallbackHandler();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.client.ApplicationClient#getServiceRefs <em>Service Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Refs</em>'.
	 * @see org.eclipse.jst.j2ee.internal.client.ApplicationClient#getServiceRefs()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_ServiceRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.client.ApplicationClient#getMessageDestinationRefs <em>Message Destination Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destination Refs</em>'.
	 * @see org.eclipse.jst.j2ee.internal.client.ApplicationClient#getMessageDestinationRefs()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_MessageDestinationRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.internal.client.ApplicationClient#getMessageDestinations <em>Message Destinations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destinations</em>'.
	 * @see org.eclipse.jst.j2ee.internal.client.ApplicationClient#getMessageDestinations()
	 * @see #getApplicationClient()
	 * @generated
	 */
	EReference getApplicationClient_MessageDestinations();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.j2ee.internal.client.ResAuthApplicationType <em>Res Auth Application Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Res Auth Application Type</em>'.
	 * @see org.eclipse.jst.j2ee.internal.client.ResAuthApplicationType
	 * @generated
	 */
	EEnum getResAuthApplicationType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	ClientFactory getClientFactory();

} //ClientPackage






