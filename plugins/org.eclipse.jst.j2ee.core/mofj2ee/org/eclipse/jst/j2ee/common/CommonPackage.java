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
package org.eclipse.jst.j2ee.common;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <p>
 * <b>Note:</b> This class/interface is part of an interim API that is still under development and expected to
 * change significantly before reaching stability. It is being made available at this early stage to solicit feedback
 * from pioneering adopters on the understanding that any code that uses this API will almost certainly be broken
 * (repeatedly) as the API evolves.
 * </p>
 *	@since 1.0
 */
public interface CommonPackage extends EPackage{
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
	int EJB_REF__NAME = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__TYPE = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__HOME = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__REMOTE = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__LINK = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF__DESCRIPTION = 5;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_REF__DESCRIPTIONS = 6;

	/**
	 * The number of structural features of the the '<em>Ejb Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_REF_FEATURE_COUNT = 7;

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
	int ENV_ENTRY__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY__NAME = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY__VALUE = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY__TYPE = 3;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENV_ENTRY__DESCRIPTIONS = 4;

	/**
	 * The number of structural features of the the '<em>Env Entry</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ENV_ENTRY_FEATURE_COUNT = 5;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__NAME = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__TYPE = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__AUTH = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__LINK = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_REF__RES_SHARING_SCOPE = 5;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REF__DESCRIPTIONS = 6;

	/**
	 * The number of structural features of the the '<em>Resource Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_REF_FEATURE_COUNT = 7;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE_REF = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE_REF__NAME = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE_REF__DESCRIPTION = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE_REF__LINK = 2;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_ROLE_REF__DESCRIPTIONS = 3;

	/**
	 * The number of structural features of the the '<em>Security Role Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_ROLE_REF_FEATURE_COUNT = 4;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_ROLE__ROLE_NAME = 1;
	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_ROLE__DESCRIPTIONS = 2;

	/**
	 * The number of structural features of the the '<em>Security Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_ROLE_FEATURE_COUNT = 3;

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
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ENV_REF__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ENV_REF__NAME = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RESOURCE_ENV_REF__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ENV_REF__DESCRIPTIONS = 3;

	/**
	 * The number of structural features of the the '<em>Resource Env Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_ENV_REF_FEATURE_COUNT = 4;

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
	 * The number of structural features of the the '<em>EJB Local Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EJB_LOCAL_REF_FEATURE_COUNT = EJB_REF_FEATURE_COUNT + 2;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int SECURITY_IDENTITY__DESCRIPTION = 0;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_IDENTITY__DESCRIPTIONS = 1;

	/**
	 * The number of structural features of the the '<em>Security Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECURITY_IDENTITY_FEATURE_COUNT = 2;

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
	 * The number of structural features of the the '<em>Run As Specified Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RUN_AS_SPECIFIED_IDENTITY_FEATURE_COUNT = SECURITY_IDENTITY_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int IDENTITY__DESCRIPTION = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int IDENTITY__ROLE_NAME = 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY__DESCRIPTIONS = 2;

	/**
	 * The number of structural features of the the '<em>Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int IDENTITY_FEATURE_COUNT = 3;

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
	 * The feature id for the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__SMALL_ICON = 0;

	/**
	 * The feature id for the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__LARGE_ICON = 1;

	/**
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE__LANG = 2;

	/**
	 * The number of structural features of the the '<em>Icon Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ICON_TYPE_FEATURE_COUNT = 3;

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
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME__LANG = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME__VALUE = 1;

	/**
	 * The number of structural features of the the '<em>Display Name</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISPLAY_NAME_FEATURE_COUNT = 2;

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
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__NAME = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Usage</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__USAGE = 2;

	/**
	 * The feature id for the '<em><b>Link</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__LINK = 3;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF__DESCRIPTIONS = 4;

	/**
	 * The number of structural features of the the '<em>Message Destination Ref</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MESSAGE_DESTINATION_REF_FEATURE_COUNT = 5;

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
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_GROUP__ICONS = 0;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_GROUP__DISPLAY_NAMES = 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_GROUP__DESCRIPTIONS = 2;

	/**
	 * The number of structural features of the the '<em>Description Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_GROUP_FEATURE_COUNT = 3;

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
	 * The number of structural features of the the '<em>Compatibility Description Group</em>' class.
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
	 * The number of structural features of the the '<em>Message Destination</em>' class.
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
	int PARAM_VALUE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_VALUE__VALUE = 1;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_VALUE__DESCRIPTION = 2;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_VALUE__DESCRIPTIONS = 3;

	/**
	 * The number of structural features of the the '<em>Param Value</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PARAM_VALUE_FEATURE_COUNT = 4;

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
	 * The number of structural features of the the '<em>JNDI Env Refs Group</em>' class.
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
	 * The number of structural features of the the '<em>Use Caller Identity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USE_CALLER_IDENTITY_FEATURE_COUNT = SECURITY_IDENTITY_FEATURE_COUNT + 0;

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
	 * The feature id for the '<em><b>Lang</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__LANG = 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__VALUE = 1;

	/**
	 * The number of structural features of the the '<em>Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_FEATURE_COUNT = 2;

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
	 * The feature id for the '<em><b>Namespace URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME__NAMESPACE_URI = 0;

	/**
	 * The feature id for the '<em><b>Local Part</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME__LOCAL_PART = 1;

	/**
	 * The feature id for the '<em><b>Combined QName</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME__COMBINED_QNAME = 2;

	/**
	 * The feature id for the '<em><b>Internal Prefix Or Ns URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME__INTERNAL_PREFIX_OR_NS_URI = 3;

	/**
	 * The number of structural features of the the '<em>QName</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int QNAME_FEATURE_COUNT = 4;

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
	 * The number of structural features of the the '<em>Listener</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LISTENER_FEATURE_COUNT = COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int EJB_REF_TYPE = 24;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int ENV_ENTRY_TYPE = 22;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RES_AUTH_TYPE_BASE = 23;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int RES_SHARING_SCOPE_TYPE = 25;
	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.common.MessageDestinationUsageType <em>Message Destination Usage Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.common.MessageDestinationUsageType
	 * @see org.eclipse.jst.j2ee.common.internal.impl.CommonPackageImpl#getMessageDestinationUsageType()
	 * @generated
	 */
	int MESSAGE_DESTINATION_USAGE_TYPE = 26;


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

} //CommonPackage






