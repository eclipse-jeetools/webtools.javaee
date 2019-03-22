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
package org.eclipse.jst.j2ee.common;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.EcorePackage;

/**
 * <p>
 * <b>Note:</b> This class/interface is part of an interim API that is still under development and expected to
 * change significantly before reaching stability. It is being made available at this early stage to solicit feedback
 * from pioneering adopters on the understanding that any code that uses this API will almost certainly be broken
 * (repeatedly) as the API evolves.
 * </p>
 *	@since 1.0
 */
public interface CommonPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "common"; //$NON-NLS-1$

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_LOCAL_REF = 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE_REF = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RUN_AS_SPECIFIED_IDENTITY = 7;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_IDENTITY = 16;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int USE_CALLER_IDENTITY = 17;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int IDENTITY = 8;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ENV_REF = 5;
	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.IconTypeImpl <em>Icon Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.IconTypeImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getIconType()
	 * @generated
	 */
	int ICON_TYPE = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.DisplayNameImpl <em>Display Name</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.DisplayNameImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getDisplayName()
	 * @generated
	 */
	int DISPLAY_NAME = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationRefImpl <em>Message Destination Ref</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationRefImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getMessageDestinationRef()
	 * @generated
	 */
	int MESSAGE_DESTINATION_REF = 11;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationImpl <em>Message Destination</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getMessageDestination()
	 * @generated
	 */
	int MESSAGE_DESTINATION = 12;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.ParamValueImpl <em>Param Value</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.ParamValueImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getParamValue()
	 * @generated
	 */
	int PARAM_VALUE = 13;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.DescriptionGroupImpl <em>Description Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.DescriptionGroupImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getDescriptionGroup()
	 * @generated
	 */
	int DESCRIPTION_GROUP = 14;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl <em>Compatibility Description Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getCompatibilityDescriptionGroup()
	 * @generated
	 */
	int COMPATIBILITY_DESCRIPTION_GROUP = 21;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl <em>JNDI Env Refs Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getJNDIEnvRefsGroup()
	 * @generated
	 */
	int JNDI_ENV_REFS_GROUP = 15;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.DescriptionImpl <em>Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.DescriptionImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getDescription()
	 * @generated
	 */
	int DESCRIPTION = 18;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.QNameImpl <em>QName</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.QNameImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getQName()
	 * @generated
	 */
	int QNAME = 19;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.ListenerImpl <em>Listener</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.ListenerImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getListener()
	 * @generated
	 */
	int LISTENER = 20;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF_TYPE = 26;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY_TYPE = 24;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RES_AUTH_TYPE_BASE = 25;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RES_SHARING_SCOPE_TYPE = 27;
	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.MessageDestinationUsageType <em>Message Destination Usage Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationUsageType
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getMessageDestinationUsageType()
	 * @generated
	 */
	int MESSAGE_DESTINATION_USAGE_TYPE = 28;


	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "common.xmi"; //$NON-NLS-1$
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.common"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CommonPackage eINSTANCE = org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl <em>J2EEE Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getJ2EEEObject()
	 * @generated
	 */
	int J2EEE_OBJECT = 22;

	/**
	 * The number of structural features of the '<em>J2EEE Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_OBJECT_FEATURE_COUNT = 0;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__NAME = J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__TYPE = J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__HOME = J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__REMOTE = J2EEE_OBJECT_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__LINK = J2EEE_OBJECT_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__DESCRIPTION = J2EEE_OBJECT_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_REF__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Ejb Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_REF_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 7;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY__DESCRIPTION = J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY__NAME = J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY__VALUE = J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY__TYPE = J2EEE_OBJECT_FEATURE_COUNT + 3;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENV_ENTRY__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Env Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENV_ENTRY_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__DESCRIPTION = J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__NAME = J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__TYPE = J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__AUTH = J2EEE_OBJECT_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__LINK = J2EEE_OBJECT_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__RES_SHARING_SCOPE = J2EEE_OBJECT_FEATURE_COUNT + 5;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REF__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Resource Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REF_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 7;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE_REF__NAME = J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE_REF__DESCRIPTION = J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE_REF__LINK = J2EEE_OBJECT_FEATURE_COUNT + 2;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_ROLE_REF__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Security Role Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_ROLE_REF_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE__DESCRIPTION = J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE__ROLE_NAME = J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_ROLE__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Security Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_ROLE_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ENV_REF__DESCRIPTION = J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ENV_REF__NAME = J2EEE_OBJECT_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ENV_REF__TYPE = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ENV_REF__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Resource Env Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ENV_REF_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_LOCAL_REF__NAME = EJB_REF__NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_LOCAL_REF__TYPE = EJB_REF__TYPE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_LOCAL_REF__HOME = EJB_REF__HOME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_LOCAL_REF__REMOTE = EJB_REF__REMOTE;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_LOCAL_REF__LINK = EJB_REF__LINK;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_LOCAL_REF__DESCRIPTION = EJB_REF__DESCRIPTION;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_LOCAL_REF__DESCRIPTIONS = EJB_REF__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Local Home</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_LOCAL_REF__LOCAL_HOME = EJB_REF_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Local</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_LOCAL_REF__LOCAL = EJB_REF_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>EJB Local Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_LOCAL_REF_FEATURE_COUNT = EJB_REF_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_IDENTITY__DESCRIPTION = J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_IDENTITY__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Security Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_IDENTITY_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RUN_AS_SPECIFIED_IDENTITY__DESCRIPTION = SECURITY_IDENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_AS_SPECIFIED_IDENTITY__DESCRIPTIONS = SECURITY_IDENTITY__DESCRIPTIONS;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RUN_AS_SPECIFIED_IDENTITY__IDENTITY = SECURITY_IDENTITY_FEATURE_COUNT + 0;
	/**
	 * The number of structural features of the '<em>Run As Specified Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_AS_SPECIFIED_IDENTITY_FEATURE_COUNT = SECURITY_IDENTITY_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int IDENTITY__DESCRIPTION = J2EEE_OBJECT_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int IDENTITY__ROLE_NAME = J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__SMALL_ICON = J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__LARGE_ICON = J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__LANG = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Icon Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME__LANG = J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME__VALUE = J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Display Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__NAME = J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__TYPE = J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__USAGE = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__LINK = J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Message Destination Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_GROUP__ICONS = J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_GROUP__DISPLAY_NAMES = J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_GROUP__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Description Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_GROUP_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPATIBILITY_DESCRIPTION_GROUP__ICONS = DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES = DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS = DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON = DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON = DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION = DESCRIPTION_GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME = DESCRIPTION_GROUP_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Compatibility Description Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT = DESCRIPTION_GROUP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION__ICONS = COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION__DISPLAY_NAMES = COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION__DESCRIPTIONS = COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION__SMALL_ICON = COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;

	/**
	 * The feature id for the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION__LARGE_ICON = COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION__DESCRIPTION = COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION__DISPLAY_NAME = COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION__NAME = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Message Destination</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_FEATURE_COUNT = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_VALUE__NAME = J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_VALUE__VALUE = J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_VALUE__DESCRIPTION = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_VALUE__DESCRIPTIONS = J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Param Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_VALUE_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__ICONS = COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__DISPLAY_NAMES = COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__DESCRIPTIONS = COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__SMALL_ICON = COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;

	/**
	 * The feature id for the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__LARGE_ICON = COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__DESCRIPTION = COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__DISPLAY_NAME = COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Environment Properties</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Resource Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__RESOURCE_REFS = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ejb Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__EJB_REFS = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Resource Env Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Ejb Local Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Service Refs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP__SERVICE_REFS = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>JNDI Env Refs Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JNDI_ENV_REFS_GROUP_FEATURE_COUNT = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 7;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int USE_CALLER_IDENTITY__DESCRIPTION = SECURITY_IDENTITY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CALLER_IDENTITY__DESCRIPTIONS = SECURITY_IDENTITY__DESCRIPTIONS;

	/**
	 * The number of structural features of the '<em>Use Caller Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CALLER_IDENTITY_FEATURE_COUNT = SECURITY_IDENTITY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__LANG = J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__VALUE = J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME__NAMESPACE_URI = J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Local Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME__LOCAL_PART = J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Combined QName</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME__COMBINED_QNAME = J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME__INTERNAL_PREFIX_OR_NS_URI = J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>QName</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME_FEATURE_COUNT = J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER__ICONS = COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER__DISPLAY_NAMES = COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER__DESCRIPTIONS = COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER__SMALL_ICON = COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;

	/**
	 * The feature id for the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER__LARGE_ICON = COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER__DESCRIPTION = COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER__DISPLAY_NAME = COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Listener Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER__LISTENER_CLASS = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Listener</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER_FEATURE_COUNT = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.internal.impl.J2EEEAttributeImpl <em>J2EEE Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.internal.impl.J2EEEAttributeImpl
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getJ2EEEAttribute()
	 * @generated
	 */
	int J2EEE_ATTRIBUTE = 23;

	/**
	 * The feature id for the '<em><b>EAnnotations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__EANNOTATIONS = EcorePackage.EATTRIBUTE__EANNOTATIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__NAME = EcorePackage.EATTRIBUTE__NAME;

	/**
	 * The feature id for the '<em><b>Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__ORDERED = EcorePackage.EATTRIBUTE__ORDERED;

	/**
	 * The feature id for the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__UNIQUE = EcorePackage.EATTRIBUTE__UNIQUE;

	/**
	 * The feature id for the '<em><b>Lower Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__LOWER_BOUND = EcorePackage.EATTRIBUTE__LOWER_BOUND;

	/**
	 * The feature id for the '<em><b>Upper Bound</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__UPPER_BOUND = EcorePackage.EATTRIBUTE__UPPER_BOUND;

	/**
	 * The feature id for the '<em><b>Many</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__MANY = EcorePackage.EATTRIBUTE__MANY;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__REQUIRED = EcorePackage.EATTRIBUTE__REQUIRED;

	/**
	 * The feature id for the '<em><b>EType</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__ETYPE = EcorePackage.EATTRIBUTE__ETYPE;

	/**
	 * The feature id for the '<em><b>Changeable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__CHANGEABLE = EcorePackage.EATTRIBUTE__CHANGEABLE;

	/**
	 * The feature id for the '<em><b>Volatile</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__VOLATILE = EcorePackage.EATTRIBUTE__VOLATILE;

	/**
	 * The feature id for the '<em><b>Transient</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__TRANSIENT = EcorePackage.EATTRIBUTE__TRANSIENT;

	/**
	 * The feature id for the '<em><b>Default Value Literal</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__DEFAULT_VALUE_LITERAL = EcorePackage.EATTRIBUTE__DEFAULT_VALUE_LITERAL;

	/**
	 * The feature id for the '<em><b>Default Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__DEFAULT_VALUE = EcorePackage.EATTRIBUTE__DEFAULT_VALUE;

	/**
	 * The feature id for the '<em><b>Unsettable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__UNSETTABLE = EcorePackage.EATTRIBUTE__UNSETTABLE;

	/**
	 * The feature id for the '<em><b>Derived</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__DERIVED = EcorePackage.EATTRIBUTE__DERIVED;

	/**
	 * The feature id for the '<em><b>EContaining Class</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__ECONTAINING_CLASS = EcorePackage.EATTRIBUTE__ECONTAINING_CLASS;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__ID = EcorePackage.EATTRIBUTE__ID;

	/**
	 * The feature id for the '<em><b>EAttribute Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE__EATTRIBUTE_TYPE = EcorePackage.EATTRIBUTE__EATTRIBUTE_TYPE;

	/**
	 * The number of structural features of the '<em>J2EEE Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int J2EEE_ATTRIBUTE_FEATURE_COUNT = EcorePackage.EATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SecurityRole object
	 */
	EClass getSecurityRole();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSecurityRole_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSecurityRole_RoleName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.SecurityRole#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.SecurityRole#getDescriptions()
	 * @see #getSecurityRole()
	 * @generated
	 */
	EReference getSecurityRole_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ResourceRef object
	 */
	EClass getResourceRef();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceRef_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceRef_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceRef_Type();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceRef_Auth();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceRef_Link();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceRef_ResSharingScope();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.ResourceRef#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.ResourceRef#getDescriptions()
	 * @see #getResourceRef()
	 * @generated
	 */
	EReference getResourceRef_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EjbRef object
	 */
	EClass getEjbRef();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEjbRef_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEjbRef_Type();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEjbRef_Home();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEjbRef_Remote();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEjbRef_Link();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEjbRef_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.EjbRef#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.EjbRef#getDescriptions()
	 * @see #getEjbRef()
	 * @generated
	 */
	EReference getEjbRef_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EJBLocalRef object
	 */
	EClass getEJBLocalRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.EJBLocalRef#getLocalHome <em>Local Home</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local Home</em>'.
	 * @see org.eclipse.jst.j2ee.common.EJBLocalRef#getLocalHome()
	 * @see #getEJBLocalRef()
	 * @generated
	 */
	EAttribute getEJBLocalRef_LocalHome();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.EJBLocalRef#getLocal <em>Local</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local</em>'.
	 * @see org.eclipse.jst.j2ee.common.EJBLocalRef#getLocal()
	 * @see #getEJBLocalRef()
	 * @generated
	 */
	EAttribute getEJBLocalRef_Local();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EnvEntry object
	 */
	EClass getEnvEntry();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEnvEntry_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEnvEntry_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEnvEntry_Value();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getEnvEntry_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.EnvEntry#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.EnvEntry#getDescriptions()
	 * @see #getEnvEntry()
	 * @generated
	 */
	EReference getEnvEntry_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SecurityRoleRef object
	 */
	EClass getSecurityRoleRef();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSecurityRoleRef_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSecurityRoleRef_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSecurityRoleRef_Link();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.SecurityRoleRef#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.SecurityRoleRef#getDescriptions()
	 * @see #getSecurityRoleRef()
	 * @generated
	 */
	EReference getSecurityRoleRef_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return RunAsSpecifiedIdentity object
	 */
	EClass getRunAsSpecifiedIdentity();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getRunAsSpecifiedIdentity_Identity();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return SecurityIdentity object
	 */
	EClass getSecurityIdentity();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getSecurityIdentity_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.SecurityIdentity#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.SecurityIdentity#getDescriptions()
	 * @see #getSecurityIdentity()
	 * @generated
	 */
	EReference getSecurityIdentity_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return UseCallerIdentity object
	 */
	EClass getUseCallerIdentity();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.Description <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Description</em>'.
	 * @see org.eclipse.jst.j2ee.common.Description
	 * @generated
	 */
	EClass getDescription();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.Description#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.eclipse.jst.j2ee.common.Description#getLang()
	 * @see #getDescription()
	 * @generated
	 */
	EAttribute getDescription_Lang();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.Description#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.j2ee.common.Description#getValue()
	 * @see #getDescription()
	 * @generated
	 */
	EAttribute getDescription_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.QName <em>QName</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>QName</em>'.
	 * @see org.eclipse.jst.j2ee.common.QName
	 * @generated
	 */
	EClass getQName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.QName#getNamespaceURI <em>Namespace URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Namespace URI</em>'.
	 * @see org.eclipse.jst.j2ee.common.QName#getNamespaceURI()
	 * @see #getQName()
	 * @generated
	 */
	EAttribute getQName_NamespaceURI();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.QName#getLocalPart <em>Local Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Local Part</em>'.
	 * @see org.eclipse.jst.j2ee.common.QName#getLocalPart()
	 * @see #getQName()
	 * @generated
	 */
	EAttribute getQName_LocalPart();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.QName#getCombinedQName <em>Combined QName</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Combined QName</em>'.
	 * @see org.eclipse.jst.j2ee.common.QName#getCombinedQName()
	 * @see #getQName()
	 * @generated
	 */
	EAttribute getQName_CombinedQName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.QName#getInternalPrefixOrNsURI <em>Internal Prefix Or Ns URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Internal Prefix Or Ns URI</em>'.
	 * @see org.eclipse.jst.j2ee.common.QName#getInternalPrefixOrNsURI()
	 * @see #getQName()
	 * @generated
	 */
	EAttribute getQName_InternalPrefixOrNsURI();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.Listener <em>Listener</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Listener</em>'.
	 * @see org.eclipse.jst.j2ee.common.Listener
	 * @generated
	 */
	EClass getListener();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.common.Listener#getListenerClass <em>Listener Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Listener Class</em>'.
	 * @see org.eclipse.jst.j2ee.common.Listener#getListenerClass()
	 * @see #getListener()
	 * @generated
	 */
	EReference getListener_ListenerClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup <em>Compatibility Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Compatibility Description Group</em>'.
	 * @see org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup
	 * @generated
	 */
	EClass getCompatibilityDescriptionGroup();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup#getSmallIcon <em>Small Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Small Icon</em>'.
	 * @see org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup#getSmallIcon()
	 * @see #getCompatibilityDescriptionGroup()
	 * @generated
	 */
	EAttribute getCompatibilityDescriptionGroup_SmallIcon();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup#getLargeIcon <em>Large Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Large Icon</em>'.
	 * @see org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup#getLargeIcon()
	 * @see #getCompatibilityDescriptionGroup()
	 * @generated
	 */
	EAttribute getCompatibilityDescriptionGroup_LargeIcon();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup#getDescription()
	 * @see #getCompatibilityDescriptionGroup()
	 * @generated
	 */
	EAttribute getCompatibilityDescriptionGroup_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup#getDisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Display Name</em>'.
	 * @see org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup#getDisplayName()
	 * @see #getCompatibilityDescriptionGroup()
	 * @generated
	 */
	EAttribute getCompatibilityDescriptionGroup_DisplayName();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.J2EEEObject <em>J2EEE Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>J2EEE Object</em>'.
	 * @see org.eclipse.jst.j2ee.common.J2EEEObject
	 * @generated
	 */
	EClass getJ2EEEObject();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.J2EEEAttribute <em>J2EEE Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>J2EEE Attribute</em>'.
	 * @see org.eclipse.jst.j2ee.common.J2EEEAttribute
	 * @generated
	 */
	EClass getJ2EEEAttribute();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return Identity object
	 */
	EClass getIdentity();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getIdentity_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getIdentity_RoleName();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.Identity#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.Identity#getDescriptions()
	 * @see #getIdentity()
	 * @generated
	 */
	EReference getIdentity_Descriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.IconType <em>Icon Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Icon Type</em>'.
	 * @see org.eclipse.jst.j2ee.common.IconType
	 * @generated
	 */
	EClass getIconType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.IconType#getSmallIcon <em>Small Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Small Icon</em>'.
	 * @see org.eclipse.jst.j2ee.common.IconType#getSmallIcon()
	 * @see #getIconType()
	 * @generated
	 */
	EAttribute getIconType_SmallIcon();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.IconType#getLargeIcon <em>Large Icon</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Large Icon</em>'.
	 * @see org.eclipse.jst.j2ee.common.IconType#getLargeIcon()
	 * @see #getIconType()
	 * @generated
	 */
	EAttribute getIconType_LargeIcon();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.IconType#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.eclipse.jst.j2ee.common.IconType#getLang()
	 * @see #getIconType()
	 * @generated
	 */
	EAttribute getIconType_Lang();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.DisplayName <em>Display Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Display Name</em>'.
	 * @see org.eclipse.jst.j2ee.common.DisplayName
	 * @generated
	 */
	EClass getDisplayName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.DisplayName#getLang <em>Lang</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Lang</em>'.
	 * @see org.eclipse.jst.j2ee.common.DisplayName#getLang()
	 * @see #getDisplayName()
	 * @generated
	 */
	EAttribute getDisplayName_Lang();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.DisplayName#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.j2ee.common.DisplayName#getValue()
	 * @see #getDisplayName()
	 * @generated
	 */
	EAttribute getDisplayName_Value();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.MessageDestinationRef <em>Message Destination Ref</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Destination Ref</em>'.
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationRef
	 * @generated
	 */
	EClass getMessageDestinationRef();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.MessageDestinationRef#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationRef#getName()
	 * @see #getMessageDestinationRef()
	 * @generated
	 */
	EAttribute getMessageDestinationRef_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.MessageDestinationRef#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationRef#getType()
	 * @see #getMessageDestinationRef()
	 * @generated
	 */
	EAttribute getMessageDestinationRef_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.MessageDestinationRef#getUsage <em>Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Usage</em>'.
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationRef#getUsage()
	 * @see #getMessageDestinationRef()
	 * @generated
	 */
	EAttribute getMessageDestinationRef_Usage();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.MessageDestinationRef#getLink <em>Link</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Link</em>'.
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationRef#getLink()
	 * @see #getMessageDestinationRef()
	 * @generated
	 */
	EAttribute getMessageDestinationRef_Link();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.MessageDestinationRef#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationRef#getDescriptions()
	 * @see #getMessageDestinationRef()
	 * @generated
	 */
	EReference getMessageDestinationRef_Descriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.MessageDestination <em>Message Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Message Destination</em>'.
	 * @see org.eclipse.jst.j2ee.common.MessageDestination
	 * @generated
	 */
	EClass getMessageDestination();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.MessageDestination#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.common.MessageDestination#getName()
	 * @see #getMessageDestination()
	 * @generated
	 */
	EAttribute getMessageDestination_Name();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.ParamValue <em>Param Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Param Value</em>'.
	 * @see org.eclipse.jst.j2ee.common.ParamValue
	 * @generated
	 */
	EClass getParamValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.ParamValue#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.common.ParamValue#getName()
	 * @see #getParamValue()
	 * @generated
	 */
	EAttribute getParamValue_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.ParamValue#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.j2ee.common.ParamValue#getValue()
	 * @see #getParamValue()
	 * @generated
	 */
	EAttribute getParamValue_Value();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.common.ParamValue#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.jst.j2ee.common.ParamValue#getDescription()
	 * @see #getParamValue()
	 * @generated
	 */
	EAttribute getParamValue_Description();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.ParamValue#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.ParamValue#getDescriptions()
	 * @see #getParamValue()
	 * @generated
	 */
	EReference getParamValue_Descriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.DescriptionGroup <em>Description Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Description Group</em>'.
	 * @see org.eclipse.jst.j2ee.common.DescriptionGroup
	 * @generated
	 */
	EClass getDescriptionGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.DescriptionGroup#getIcons <em>Icons</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Icons</em>'.
	 * @see org.eclipse.jst.j2ee.common.DescriptionGroup#getIcons()
	 * @see #getDescriptionGroup()
	 * @generated
	 */
	EReference getDescriptionGroup_Icons();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.DescriptionGroup#getDisplayNames <em>Display Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Display Names</em>'.
	 * @see org.eclipse.jst.j2ee.common.DescriptionGroup#getDisplayNames()
	 * @see #getDescriptionGroup()
	 * @generated
	 */
	EReference getDescriptionGroup_DisplayNames();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.DescriptionGroup#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.DescriptionGroup#getDescriptions()
	 * @see #getDescriptionGroup()
	 * @generated
	 */
	EReference getDescriptionGroup_Descriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup <em>JNDI Env Refs Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>JNDI Env Refs Group</em>'.
	 * @see org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup
	 * @generated
	 */
	EClass getJNDIEnvRefsGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getEnvironmentProperties <em>Environment Properties</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Environment Properties</em>'.
	 * @see org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getEnvironmentProperties()
	 * @see #getJNDIEnvRefsGroup()
	 * @generated
	 */
	EReference getJNDIEnvRefsGroup_EnvironmentProperties();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getResourceRefs <em>Resource Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Refs</em>'.
	 * @see org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getResourceRefs()
	 * @see #getJNDIEnvRefsGroup()
	 * @generated
	 */
	EReference getJNDIEnvRefsGroup_ResourceRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getEjbRefs <em>Ejb Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ejb Refs</em>'.
	 * @see org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getEjbRefs()
	 * @see #getJNDIEnvRefsGroup()
	 * @generated
	 */
	EReference getJNDIEnvRefsGroup_EjbRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getResourceEnvRefs <em>Resource Env Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Env Refs</em>'.
	 * @see org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getResourceEnvRefs()
	 * @see #getJNDIEnvRefsGroup()
	 * @generated
	 */
	EReference getJNDIEnvRefsGroup_ResourceEnvRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getEjbLocalRefs <em>Ejb Local Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ejb Local Refs</em>'.
	 * @see org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getEjbLocalRefs()
	 * @see #getJNDIEnvRefsGroup()
	 * @generated
	 */
	EReference getJNDIEnvRefsGroup_EjbLocalRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getMessageDestinationRefs <em>Message Destination Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Message Destination Refs</em>'.
	 * @see org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getMessageDestinationRefs()
	 * @see #getJNDIEnvRefsGroup()
	 * @generated
	 */
	EReference getJNDIEnvRefsGroup_MessageDestinationRefs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getServiceRefs <em>Service Refs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Service Refs</em>'.
	 * @see org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup#getServiceRefs()
	 * @see #getJNDIEnvRefsGroup()
	 * @generated
	 */
	EReference getJNDIEnvRefsGroup_ServiceRefs();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ResourceEnvRef object
	 */
	EClass getResourceEnvRef();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceEnvRef_Description();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getResourceEnvRef_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getResourceEnvRef_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.common.ResourceEnvRef#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.common.ResourceEnvRef#getDescriptions()
	 * @see #getResourceEnvRef()
	 * @generated
	 */
	EReference getResourceEnvRef_Descriptions();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EjbRefType object
	 */
	EEnum getEjbRefType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return EnvEntryType object
	 */
	EEnum getEnvEntryType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ResAuthTypeBase object
	 */
	EEnum getResAuthTypeBase();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return ResSharingScopeType object
	 */
	EEnum getResSharingScopeType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.j2ee.common.MessageDestinationUsageType <em>Message Destination Usage Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Message Destination Usage Type</em>'.
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationUsageType
	 * @generated
	 */
	EEnum getMessageDestinationUsageType();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	CommonFactory getCommonFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals  {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.EjbRefImpl <em>Ejb Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.EjbRefImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getEjbRef()
		 * @generated
		 */
		EClass EJB_REF = eINSTANCE.getEjbRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_REF__NAME = eINSTANCE.getEjbRef_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_REF__TYPE = eINSTANCE.getEjbRef_Type();

		/**
		 * The meta object literal for the '<em><b>Home</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_REF__HOME = eINSTANCE.getEjbRef_Home();

		/**
		 * The meta object literal for the '<em><b>Remote</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_REF__REMOTE = eINSTANCE.getEjbRef_Remote();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_REF__LINK = eINSTANCE.getEjbRef_Link();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_REF__DESCRIPTION = eINSTANCE.getEjbRef_Description();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EJB_REF__DESCRIPTIONS = eINSTANCE.getEjbRef_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.EnvEntryImpl <em>Env Entry</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.EnvEntryImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getEnvEntry()
		 * @generated
		 */
		EClass ENV_ENTRY = eINSTANCE.getEnvEntry();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENV_ENTRY__DESCRIPTION = eINSTANCE.getEnvEntry_Description();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENV_ENTRY__NAME = eINSTANCE.getEnvEntry_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENV_ENTRY__VALUE = eINSTANCE.getEnvEntry_Value();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ENV_ENTRY__TYPE = eINSTANCE.getEnvEntry_Type();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ENV_ENTRY__DESCRIPTIONS = eINSTANCE.getEnvEntry_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.ResourceRefImpl <em>Resource Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.ResourceRefImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getResourceRef()
		 * @generated
		 */
		EClass RESOURCE_REF = eINSTANCE.getResourceRef();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_REF__DESCRIPTION = eINSTANCE.getResourceRef_Description();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_REF__NAME = eINSTANCE.getResourceRef_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_REF__TYPE = eINSTANCE.getResourceRef_Type();

		/**
		 * The meta object literal for the '<em><b>Auth</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_REF__AUTH = eINSTANCE.getResourceRef_Auth();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_REF__LINK = eINSTANCE.getResourceRef_Link();

		/**
		 * The meta object literal for the '<em><b>Res Sharing Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_REF__RES_SHARING_SCOPE = eINSTANCE.getResourceRef_ResSharingScope();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_REF__DESCRIPTIONS = eINSTANCE.getResourceRef_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.SecurityRoleRefImpl <em>Security Role Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.SecurityRoleRefImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getSecurityRoleRef()
		 * @generated
		 */
		EClass SECURITY_ROLE_REF = eINSTANCE.getSecurityRoleRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_ROLE_REF__NAME = eINSTANCE.getSecurityRoleRef_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_ROLE_REF__DESCRIPTION = eINSTANCE.getSecurityRoleRef_Description();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_ROLE_REF__LINK = eINSTANCE.getSecurityRoleRef_Link();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_ROLE_REF__DESCRIPTIONS = eINSTANCE.getSecurityRoleRef_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.SecurityRoleImpl <em>Security Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.SecurityRoleImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getSecurityRole()
		 * @generated
		 */
		EClass SECURITY_ROLE = eINSTANCE.getSecurityRole();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_ROLE__DESCRIPTION = eINSTANCE.getSecurityRole_Description();

		/**
		 * The meta object literal for the '<em><b>Role Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_ROLE__ROLE_NAME = eINSTANCE.getSecurityRole_RoleName();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_ROLE__DESCRIPTIONS = eINSTANCE.getSecurityRole_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.ResourceEnvRefImpl <em>Resource Env Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.ResourceEnvRefImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getResourceEnvRef()
		 * @generated
		 */
		EClass RESOURCE_ENV_REF = eINSTANCE.getResourceEnvRef();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ENV_REF__DESCRIPTION = eINSTANCE.getResourceEnvRef_Description();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_ENV_REF__NAME = eINSTANCE.getResourceEnvRef_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ENV_REF__TYPE = eINSTANCE.getResourceEnvRef_Type();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_ENV_REF__DESCRIPTIONS = eINSTANCE.getResourceEnvRef_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.EJBLocalRefImpl <em>EJB Local Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.EJBLocalRefImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getEJBLocalRef()
		 * @generated
		 */
		EClass EJB_LOCAL_REF = eINSTANCE.getEJBLocalRef();

		/**
		 * The meta object literal for the '<em><b>Local Home</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_LOCAL_REF__LOCAL_HOME = eINSTANCE.getEJBLocalRef_LocalHome();

		/**
		 * The meta object literal for the '<em><b>Local</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EJB_LOCAL_REF__LOCAL = eINSTANCE.getEJBLocalRef_Local();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.RunAsSpecifiedIdentityImpl <em>Run As Specified Identity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.RunAsSpecifiedIdentityImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getRunAsSpecifiedIdentity()
		 * @generated
		 */
		EClass RUN_AS_SPECIFIED_IDENTITY = eINSTANCE.getRunAsSpecifiedIdentity();

		/**
		 * The meta object literal for the '<em><b>Identity</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RUN_AS_SPECIFIED_IDENTITY__IDENTITY = eINSTANCE.getRunAsSpecifiedIdentity_Identity();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.IdentityImpl <em>Identity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.IdentityImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getIdentity()
		 * @generated
		 */
		EClass IDENTITY = eINSTANCE.getIdentity();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTITY__DESCRIPTION = eINSTANCE.getIdentity_Description();

		/**
		 * The meta object literal for the '<em><b>Role Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute IDENTITY__ROLE_NAME = eINSTANCE.getIdentity_RoleName();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference IDENTITY__DESCRIPTIONS = eINSTANCE.getIdentity_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.IconTypeImpl <em>Icon Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.IconTypeImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getIconType()
		 * @generated
		 */
		EClass ICON_TYPE = eINSTANCE.getIconType();

		/**
		 * The meta object literal for the '<em><b>Small Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ICON_TYPE__SMALL_ICON = eINSTANCE.getIconType_SmallIcon();

		/**
		 * The meta object literal for the '<em><b>Large Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ICON_TYPE__LARGE_ICON = eINSTANCE.getIconType_LargeIcon();

		/**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ICON_TYPE__LANG = eINSTANCE.getIconType_Lang();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.DisplayNameImpl <em>Display Name</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.DisplayNameImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getDisplayName()
		 * @generated
		 */
		EClass DISPLAY_NAME = eINSTANCE.getDisplayName();

		/**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISPLAY_NAME__LANG = eINSTANCE.getDisplayName_Lang();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DISPLAY_NAME__VALUE = eINSTANCE.getDisplayName_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationRefImpl <em>Message Destination Ref</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationRefImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getMessageDestinationRef()
		 * @generated
		 */
		EClass MESSAGE_DESTINATION_REF = eINSTANCE.getMessageDestinationRef();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_DESTINATION_REF__NAME = eINSTANCE.getMessageDestinationRef_Name();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_DESTINATION_REF__TYPE = eINSTANCE.getMessageDestinationRef_Type();

		/**
		 * The meta object literal for the '<em><b>Usage</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_DESTINATION_REF__USAGE = eINSTANCE.getMessageDestinationRef_Usage();

		/**
		 * The meta object literal for the '<em><b>Link</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_DESTINATION_REF__LINK = eINSTANCE.getMessageDestinationRef_Link();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MESSAGE_DESTINATION_REF__DESCRIPTIONS = eINSTANCE.getMessageDestinationRef_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationImpl <em>Message Destination</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.MessageDestinationImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getMessageDestination()
		 * @generated
		 */
		EClass MESSAGE_DESTINATION = eINSTANCE.getMessageDestination();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MESSAGE_DESTINATION__NAME = eINSTANCE.getMessageDestination_Name();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.ParamValueImpl <em>Param Value</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.ParamValueImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getParamValue()
		 * @generated
		 */
		EClass PARAM_VALUE = eINSTANCE.getParamValue();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAM_VALUE__NAME = eINSTANCE.getParamValue_Name();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAM_VALUE__VALUE = eINSTANCE.getParamValue_Value();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PARAM_VALUE__DESCRIPTION = eINSTANCE.getParamValue_Description();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PARAM_VALUE__DESCRIPTIONS = eINSTANCE.getParamValue_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.DescriptionGroupImpl <em>Description Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.DescriptionGroupImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getDescriptionGroup()
		 * @generated
		 */
		EClass DESCRIPTION_GROUP = eINSTANCE.getDescriptionGroup();

		/**
		 * The meta object literal for the '<em><b>Icons</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESCRIPTION_GROUP__ICONS = eINSTANCE.getDescriptionGroup_Icons();

		/**
		 * The meta object literal for the '<em><b>Display Names</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESCRIPTION_GROUP__DISPLAY_NAMES = eINSTANCE.getDescriptionGroup_DisplayNames();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESCRIPTION_GROUP__DESCRIPTIONS = eINSTANCE.getDescriptionGroup_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl <em>JNDI Env Refs Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.JNDIEnvRefsGroupImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getJNDIEnvRefsGroup()
		 * @generated
		 */
		EClass JNDI_ENV_REFS_GROUP = eINSTANCE.getJNDIEnvRefsGroup();

		/**
		 * The meta object literal for the '<em><b>Environment Properties</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JNDI_ENV_REFS_GROUP__ENVIRONMENT_PROPERTIES = eINSTANCE.getJNDIEnvRefsGroup_EnvironmentProperties();

		/**
		 * The meta object literal for the '<em><b>Resource Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JNDI_ENV_REFS_GROUP__RESOURCE_REFS = eINSTANCE.getJNDIEnvRefsGroup_ResourceRefs();

		/**
		 * The meta object literal for the '<em><b>Ejb Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JNDI_ENV_REFS_GROUP__EJB_REFS = eINSTANCE.getJNDIEnvRefsGroup_EjbRefs();

		/**
		 * The meta object literal for the '<em><b>Resource Env Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JNDI_ENV_REFS_GROUP__RESOURCE_ENV_REFS = eINSTANCE.getJNDIEnvRefsGroup_ResourceEnvRefs();

		/**
		 * The meta object literal for the '<em><b>Ejb Local Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JNDI_ENV_REFS_GROUP__EJB_LOCAL_REFS = eINSTANCE.getJNDIEnvRefsGroup_EjbLocalRefs();

		/**
		 * The meta object literal for the '<em><b>Message Destination Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JNDI_ENV_REFS_GROUP__MESSAGE_DESTINATION_REFS = eINSTANCE.getJNDIEnvRefsGroup_MessageDestinationRefs();

		/**
		 * The meta object literal for the '<em><b>Service Refs</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JNDI_ENV_REFS_GROUP__SERVICE_REFS = eINSTANCE.getJNDIEnvRefsGroup_ServiceRefs();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.SecurityIdentityImpl <em>Security Identity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.SecurityIdentityImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getSecurityIdentity()
		 * @generated
		 */
		EClass SECURITY_IDENTITY = eINSTANCE.getSecurityIdentity();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECURITY_IDENTITY__DESCRIPTION = eINSTANCE.getSecurityIdentity_Description();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SECURITY_IDENTITY__DESCRIPTIONS = eINSTANCE.getSecurityIdentity_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.UseCallerIdentityImpl <em>Use Caller Identity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.UseCallerIdentityImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getUseCallerIdentity()
		 * @generated
		 */
		EClass USE_CALLER_IDENTITY = eINSTANCE.getUseCallerIdentity();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.DescriptionImpl <em>Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.DescriptionImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getDescription()
		 * @generated
		 */
		EClass DESCRIPTION = eINSTANCE.getDescription();

		/**
		 * The meta object literal for the '<em><b>Lang</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTION__LANG = eINSTANCE.getDescription_Lang();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTION__VALUE = eINSTANCE.getDescription_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.QNameImpl <em>QName</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.QNameImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getQName()
		 * @generated
		 */
		EClass QNAME = eINSTANCE.getQName();

		/**
		 * The meta object literal for the '<em><b>Namespace URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QNAME__NAMESPACE_URI = eINSTANCE.getQName_NamespaceURI();

		/**
		 * The meta object literal for the '<em><b>Local Part</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QNAME__LOCAL_PART = eINSTANCE.getQName_LocalPart();

		/**
		 * The meta object literal for the '<em><b>Combined QName</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QNAME__COMBINED_QNAME = eINSTANCE.getQName_CombinedQName();

		/**
		 * The meta object literal for the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute QNAME__INTERNAL_PREFIX_OR_NS_URI = eINSTANCE.getQName_InternalPrefixOrNsURI();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.ListenerImpl <em>Listener</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.ListenerImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getListener()
		 * @generated
		 */
		EClass LISTENER = eINSTANCE.getListener();

		/**
		 * The meta object literal for the '<em><b>Listener Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LISTENER__LISTENER_CLASS = eINSTANCE.getListener_ListenerClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl <em>Compatibility Description Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getCompatibilityDescriptionGroup()
		 * @generated
		 */
		EClass COMPATIBILITY_DESCRIPTION_GROUP = eINSTANCE.getCompatibilityDescriptionGroup();

		/**
		 * The meta object literal for the '<em><b>Small Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON = eINSTANCE.getCompatibilityDescriptionGroup_SmallIcon();

		/**
		 * The meta object literal for the '<em><b>Large Icon</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON = eINSTANCE.getCompatibilityDescriptionGroup_LargeIcon();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION = eINSTANCE.getCompatibilityDescriptionGroup_Description();

		/**
		 * The meta object literal for the '<em><b>Display Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME = eINSTANCE.getCompatibilityDescriptionGroup_DisplayName();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl <em>J2EEE Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.J2EEEObjectImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getJ2EEEObject()
		 * @generated
		 */
		EClass J2EEE_OBJECT = eINSTANCE.getJ2EEEObject();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.internal.impl.J2EEEAttributeImpl <em>J2EEE Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.internal.impl.J2EEEAttributeImpl
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getJ2EEEAttribute()
		 * @generated
		 */
		EClass J2EEE_ATTRIBUTE = eINSTANCE.getJ2EEEAttribute();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.EnvEntryType <em>Env Entry Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.EnvEntryType
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getEnvEntryType()
		 * @generated
		 */
		EEnum ENV_ENTRY_TYPE = eINSTANCE.getEnvEntryType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.ResAuthTypeBase <em>Res Auth Type Base</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.ResAuthTypeBase
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getResAuthTypeBase()
		 * @generated
		 */
		EEnum RES_AUTH_TYPE_BASE = eINSTANCE.getResAuthTypeBase();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.EjbRefType <em>Ejb Ref Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.EjbRefType
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getEjbRefType()
		 * @generated
		 */
		EEnum EJB_REF_TYPE = eINSTANCE.getEjbRefType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.ResSharingScopeType <em>Res Sharing Scope Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.ResSharingScopeType
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getResSharingScopeType()
		 * @generated
		 */
		EEnum RES_SHARING_SCOPE_TYPE = eINSTANCE.getResSharingScopeType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.common.MessageDestinationUsageType <em>Message Destination Usage Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.common.MessageDestinationUsageType
		 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getMessageDestinationUsageType()
		 * @generated
		 */
		EEnum MESSAGE_DESTINATION_USAGE_TYPE = eINSTANCE.getMessageDestinationUsageType();

	}

} //CommonPackage






