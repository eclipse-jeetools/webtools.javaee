/*******************************************************************************
 * Copyright (c) 2005, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.taglib.internal;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.common.CommonPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.taglib.TaglibFactory
 * @model kind="package"
 * @generated
 */
public interface TaglibPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "taglib"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "taglib.xmi"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.taglib"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TaglibPackage eINSTANCE = org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.TagLibImpl <em>Tag Lib</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TagLibImpl
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getTagLib()
	 * @generated
	 */
	int TAG_LIB = 0;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__ICONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__DISPLAY_NAMES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__DESCRIPTIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__SMALL_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;

	/**
	 * The feature id for the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__LARGE_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__DESCRIPTION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__DISPLAY_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Tag Lib Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__TAG_LIB_VERSION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Jsp Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__JSP_VERSION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Short Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__SHORT_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__URI = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Tags</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__TAGS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Validator</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__VALIDATOR = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Listeners</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__LISTENERS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Functions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__FUNCTIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Taglib Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB__TAGLIB_EXTENSIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Tag Lib</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB_FEATURE_COUNT = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.JSPTagImpl <em>JSP Tag</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.JSPTagImpl
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getJSPTag()
	 * @generated
	 */
	int JSP_TAG = 1;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__ICONS = CommonPackage.DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__DISPLAY_NAMES = CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__DESCRIPTIONS = CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Body Content</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__BODY_CONTENT = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__EXAMPLE = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__NAME = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Dynamic Attributes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__DYNAMIC_ATTRIBUTES = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Attributes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__ATTRIBUTES = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Variables</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__VARIABLES = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Tag Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__TAG_CLASS = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Tei Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG__TEI_CLASS = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>JSP Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_FEATURE_COUNT = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 8;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.JSPTagAttributeImpl <em>JSP Tag Attribute</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.JSPTagAttributeImpl
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getJSPTagAttribute()
	 * @generated
	 */
	int JSP_TAG_ATTRIBUTE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE__NAME = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Required</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE__REQUIRED = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Rt Expr Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE__RT_EXPR_VALUE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Fragment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE__FRAGMENT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE__TYPE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>JSP Tag Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.ValidatorImpl <em>Validator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.ValidatorImpl
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getValidator()
	 * @generated
	 */
	int VALIDATOR = 3;

	/**
	 * The feature id for the '<em><b>Validator Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR__VALIDATOR_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Init Params</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR__INIT_PARAMS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Validator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.JSPVariableImpl <em>JSP Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.JSPVariableImpl
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getJSPVariable()
	 * @generated
	 */
	int JSP_VARIABLE = 4;

	/**
	 * The feature id for the '<em><b>Name Given</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_VARIABLE__NAME_GIVEN = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name From Attribute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_VARIABLE__NAME_FROM_ATTRIBUTE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Declare</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_VARIABLE__DECLARE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_VARIABLE__SCOPE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Variable Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_VARIABLE__VARIABLE_CLASS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_VARIABLE__DESCRIPTIONS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>JSP Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_VARIABLE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.FunctionImpl <em>Function</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.FunctionImpl
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getFunction()
	 * @generated
	 */
	int FUNCTION = 5;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__ICONS = CommonPackage.DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DISPLAY_NAMES = CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__DESCRIPTIONS = CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__NAME = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Signature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__SIGNATURE = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__EXAMPLE = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Function Class</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__FUNCTION_CLASS = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Function Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION__FUNCTION_EXTENSIONS = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Function</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FUNCTION_FEATURE_COUNT = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.TagFileImpl <em>Tag File</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TagFileImpl
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getTagFile()
	 * @generated
	 */
	int TAG_FILE = 6;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FILE__ICONS = CommonPackage.DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FILE__DISPLAY_NAMES = CommonPackage.DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FILE__DESCRIPTIONS = CommonPackage.DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FILE__NAME = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Path</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FILE__PATH = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Example</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FILE__EXAMPLE = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Tag Extensions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FILE__TAG_EXTENSIONS = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Tag File</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_FILE_FEATURE_COUNT = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.TldExtensionImpl <em>Tld Extension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TldExtensionImpl
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getTldExtension()
	 * @generated
	 */
	int TLD_EXTENSION = 7;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLD_EXTENSION__NAMESPACE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLD_EXTENSION__EXTENSION_ELEMENTS = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Tld Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLD_EXTENSION_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.ExtensibleTypeImpl <em>Extensible Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.ExtensibleTypeImpl
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getExtensibleType()
	 * @generated
	 */
	int EXTENSIBLE_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_TYPE__VALUE = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Extensible Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_TYPE_FEATURE_COUNT = CommonPackage.J2EEE_OBJECT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.BodyContentType <em>Body Content Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.BodyContentType
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getBodyContentType()
	 * @generated
	 */
	int BODY_CONTENT_TYPE = 9;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.taglib.internal.JSPScriptingVariableScope <em>JSP Scripting Variable Scope</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPScriptingVariableScope
	 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getJSPScriptingVariableScope()
	 * @generated
	 */
	int JSP_SCRIPTING_VARIABLE_SCOPE = 10;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib <em>Tag Lib</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Lib</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib
	 * @generated
	 */
	EClass getTagLib();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib#getTagLibVersion <em>Tag Lib Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Tag Lib Version</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib#getTagLibVersion()
	 * @see #getTagLib()
	 * @generated
	 */
	EAttribute getTagLib_TagLibVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib#getJspVersion <em>Jsp Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Jsp Version</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib#getJspVersion()
	 * @see #getTagLib()
	 * @generated
	 */
	EAttribute getTagLib_JspVersion();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib#getShortName <em>Short Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Short Name</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib#getShortName()
	 * @see #getTagLib()
	 * @generated
	 */
	EAttribute getTagLib_ShortName();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib#getUri <em>Uri</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Uri</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib#getUri()
	 * @see #getTagLib()
	 * @generated
	 */
	EAttribute getTagLib_Uri();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib#getTags <em>Tags</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tags</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib#getTags()
	 * @see #getTagLib()
	 * @generated
	 */
	EReference getTagLib_Tags();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib#getValidator <em>Validator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Validator</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib#getValidator()
	 * @see #getTagLib()
	 * @generated
	 */
	EReference getTagLib_Validator();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib#getListeners <em>Listeners</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Listeners</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib#getListeners()
	 * @see #getTagLib()
	 * @generated
	 */
	EReference getTagLib_Listeners();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib#getFunctions <em>Functions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Functions</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib#getFunctions()
	 * @see #getTagLib()
	 * @generated
	 */
	EReference getTagLib_Functions();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.TagLib#getTaglibExtensions <em>Taglib Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Taglib Extensions</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagLib#getTaglibExtensions()
	 * @see #getTagLib()
	 * @generated
	 */
	EReference getTagLib_TaglibExtensions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTag <em>JSP Tag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>JSP Tag</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTag
	 * @generated
	 */
	EClass getJSPTag();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTag#getBodyContent <em>Body Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body Content</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTag#getBodyContent()
	 * @see #getJSPTag()
	 * @generated
	 */
	EAttribute getJSPTag_BodyContent();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTag#getExample <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Example</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTag#getExample()
	 * @see #getJSPTag()
	 * @generated
	 */
	EAttribute getJSPTag_Example();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTag#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTag#getName()
	 * @see #getJSPTag()
	 * @generated
	 */
	EAttribute getJSPTag_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTag#isDynamicAttributes <em>Dynamic Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Dynamic Attributes</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTag#isDynamicAttributes()
	 * @see #getJSPTag()
	 * @generated
	 */
	EAttribute getJSPTag_DynamicAttributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTag#getAttributes <em>Attributes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Attributes</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTag#getAttributes()
	 * @see #getJSPTag()
	 * @generated
	 */
	EReference getJSPTag_Attributes();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTag#getVariables <em>Variables</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Variables</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTag#getVariables()
	 * @see #getJSPTag()
	 * @generated
	 */
	EReference getJSPTag_Variables();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTag#getTagClass <em>Tag Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Tag Class</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTag#getTagClass()
	 * @see #getJSPTag()
	 * @generated
	 */
	EReference getJSPTag_TagClass();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTag#getTeiClass <em>Tei Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Tei Class</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTag#getTeiClass()
	 * @see #getJSPTag()
	 * @generated
	 */
	EReference getJSPTag_TeiClass();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute <em>JSP Tag Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>JSP Tag Attribute</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute
	 * @generated
	 */
	EClass getJSPTagAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#getName()
	 * @see #getJSPTagAttribute()
	 * @generated
	 */
	EAttribute getJSPTagAttribute_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#isRequired <em>Required</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Required</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#isRequired()
	 * @see #getJSPTagAttribute()
	 * @generated
	 */
	EAttribute getJSPTagAttribute_Required();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#isRtExprValue <em>Rt Expr Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Rt Expr Value</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#isRtExprValue()
	 * @see #getJSPTagAttribute()
	 * @generated
	 */
	EAttribute getJSPTagAttribute_RtExprValue();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#isFragment <em>Fragment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Fragment</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#isFragment()
	 * @see #getJSPTagAttribute()
	 * @generated
	 */
	EAttribute getJSPTagAttribute_Fragment();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Type</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#getType()
	 * @see #getJSPTagAttribute()
	 * @generated
	 */
	EReference getJSPTagAttribute_Type();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPTagAttribute#getDescriptions()
	 * @see #getJSPTagAttribute()
	 * @generated
	 */
	EReference getJSPTagAttribute_Descriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.taglib.internal.Validator <em>Validator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Validator</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Validator
	 * @generated
	 */
	EClass getValidator();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.taglib.internal.Validator#getValidatorClass <em>Validator Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Validator Class</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Validator#getValidatorClass()
	 * @see #getValidator()
	 * @generated
	 */
	EReference getValidator_ValidatorClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.Validator#getInitParams <em>Init Params</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Init Params</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Validator#getInitParams()
	 * @see #getValidator()
	 * @generated
	 */
	EReference getValidator_InitParams();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.Validator#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Validator#getDescriptions()
	 * @see #getValidator()
	 * @generated
	 */
	EReference getValidator_Descriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.taglib.internal.JSPVariable <em>JSP Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>JSP Variable</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPVariable
	 * @generated
	 */
	EClass getJSPVariable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getNameGiven <em>Name Given</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name Given</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getNameGiven()
	 * @see #getJSPVariable()
	 * @generated
	 */
	EAttribute getJSPVariable_NameGiven();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getNameFromAttribute <em>Name From Attribute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name From Attribute</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getNameFromAttribute()
	 * @see #getJSPVariable()
	 * @generated
	 */
	EAttribute getJSPVariable_NameFromAttribute();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPVariable#isDeclare <em>Declare</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Declare</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPVariable#isDeclare()
	 * @see #getJSPVariable()
	 * @generated
	 */
	EAttribute getJSPVariable_Declare();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getScope <em>Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scope</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getScope()
	 * @see #getJSPVariable()
	 * @generated
	 */
	EAttribute getJSPVariable_Scope();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getVariableClass <em>Variable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Variable Class</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getVariableClass()
	 * @see #getJSPVariable()
	 * @generated
	 */
	EReference getJSPVariable_VariableClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getDescriptions <em>Descriptions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Descriptions</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPVariable#getDescriptions()
	 * @see #getJSPVariable()
	 * @generated
	 */
	EReference getJSPVariable_Descriptions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.taglib.internal.Function <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Function</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Function
	 * @generated
	 */
	EClass getFunction();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.Function#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Function#getName()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.Function#getSignature <em>Signature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Signature</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Function#getSignature()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_Signature();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.Function#getExample <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Example</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Function#getExample()
	 * @see #getFunction()
	 * @generated
	 */
	EAttribute getFunction_Example();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.jst.j2ee.taglib.internal.Function#getFunctionClass <em>Function Class</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Function Class</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Function#getFunctionClass()
	 * @see #getFunction()
	 * @generated
	 */
	EReference getFunction_FunctionClass();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.Function#getFunctionExtensions <em>Function Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Function Extensions</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.Function#getFunctionExtensions()
	 * @see #getFunction()
	 * @generated
	 */
	EReference getFunction_FunctionExtensions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.taglib.internal.TagFile <em>Tag File</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag File</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagFile
	 * @generated
	 */
	EClass getTagFile();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.TagFile#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagFile#getName()
	 * @see #getTagFile()
	 * @generated
	 */
	EAttribute getTagFile_Name();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.TagFile#getPath <em>Path</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Path</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagFile#getPath()
	 * @see #getTagFile()
	 * @generated
	 */
	EAttribute getTagFile_Path();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.TagFile#getExample <em>Example</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Example</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagFile#getExample()
	 * @see #getTagFile()
	 * @generated
	 */
	EAttribute getTagFile_Example();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.TagFile#getTagExtensions <em>Tag Extensions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag Extensions</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TagFile#getTagExtensions()
	 * @see #getTagFile()
	 * @generated
	 */
	EReference getTagFile_TagExtensions();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.taglib.internal.TldExtension <em>Tld Extension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tld Extension</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TldExtension
	 * @generated
	 */
	EClass getTldExtension();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.TldExtension#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Namespace</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TldExtension#getNamespace()
	 * @see #getTldExtension()
	 * @generated
	 */
	EAttribute getTldExtension_Namespace();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.taglib.internal.TldExtension#getExtensionElements <em>Extension Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Extension Elements</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.TldExtension#getExtensionElements()
	 * @see #getTldExtension()
	 * @generated
	 */
	EReference getTldExtension_ExtensionElements();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.taglib.internal.ExtensibleType <em>Extensible Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Extensible Type</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.ExtensibleType
	 * @generated
	 */
	EClass getExtensibleType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.taglib.internal.ExtensibleType#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Value</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.ExtensibleType#getValue()
	 * @see #getExtensibleType()
	 * @generated
	 */
	EAttribute getExtensibleType_Value();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.j2ee.taglib.internal.BodyContentType <em>Body Content Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Body Content Type</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.BodyContentType
	 * @generated
	 */
	EEnum getBodyContentType();

	/**
	 * Returns the meta object for enum '{@link org.eclipse.jst.j2ee.taglib.internal.JSPScriptingVariableScope <em>JSP Scripting Variable Scope</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>JSP Scripting Variable Scope</em>'.
	 * @see org.eclipse.jst.j2ee.taglib.internal.JSPScriptingVariableScope
	 * @generated
	 */
	EEnum getJSPScriptingVariableScope();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TaglibFactory getTaglibFactory();

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
	interface Literals {
		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.TagLibImpl <em>Tag Lib</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TagLibImpl
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getTagLib()
		 * @generated
		 */
		EClass TAG_LIB = eINSTANCE.getTagLib();

		/**
		 * The meta object literal for the '<em><b>Tag Lib Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_LIB__TAG_LIB_VERSION = eINSTANCE.getTagLib_TagLibVersion();

		/**
		 * The meta object literal for the '<em><b>Jsp Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_LIB__JSP_VERSION = eINSTANCE.getTagLib_JspVersion();

		/**
		 * The meta object literal for the '<em><b>Short Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_LIB__SHORT_NAME = eINSTANCE.getTagLib_ShortName();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_LIB__URI = eINSTANCE.getTagLib_Uri();

		/**
		 * The meta object literal for the '<em><b>Tags</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_LIB__TAGS = eINSTANCE.getTagLib_Tags();

		/**
		 * The meta object literal for the '<em><b>Validator</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_LIB__VALIDATOR = eINSTANCE.getTagLib_Validator();

		/**
		 * The meta object literal for the '<em><b>Listeners</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_LIB__LISTENERS = eINSTANCE.getTagLib_Listeners();

		/**
		 * The meta object literal for the '<em><b>Functions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_LIB__FUNCTIONS = eINSTANCE.getTagLib_Functions();

		/**
		 * The meta object literal for the '<em><b>Taglib Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_LIB__TAGLIB_EXTENSIONS = eINSTANCE.getTagLib_TaglibExtensions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.JSPTagImpl <em>JSP Tag</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.JSPTagImpl
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getJSPTag()
		 * @generated
		 */
		EClass JSP_TAG = eINSTANCE.getJSPTag();

		/**
		 * The meta object literal for the '<em><b>Body Content</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_TAG__BODY_CONTENT = eINSTANCE.getJSPTag_BodyContent();

		/**
		 * The meta object literal for the '<em><b>Example</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_TAG__EXAMPLE = eINSTANCE.getJSPTag_Example();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_TAG__NAME = eINSTANCE.getJSPTag_Name();

		/**
		 * The meta object literal for the '<em><b>Dynamic Attributes</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_TAG__DYNAMIC_ATTRIBUTES = eINSTANCE.getJSPTag_DynamicAttributes();

		/**
		 * The meta object literal for the '<em><b>Attributes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_TAG__ATTRIBUTES = eINSTANCE.getJSPTag_Attributes();

		/**
		 * The meta object literal for the '<em><b>Variables</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_TAG__VARIABLES = eINSTANCE.getJSPTag_Variables();

		/**
		 * The meta object literal for the '<em><b>Tag Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_TAG__TAG_CLASS = eINSTANCE.getJSPTag_TagClass();

		/**
		 * The meta object literal for the '<em><b>Tei Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_TAG__TEI_CLASS = eINSTANCE.getJSPTag_TeiClass();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.JSPTagAttributeImpl <em>JSP Tag Attribute</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.JSPTagAttributeImpl
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getJSPTagAttribute()
		 * @generated
		 */
		EClass JSP_TAG_ATTRIBUTE = eINSTANCE.getJSPTagAttribute();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_TAG_ATTRIBUTE__NAME = eINSTANCE.getJSPTagAttribute_Name();

		/**
		 * The meta object literal for the '<em><b>Required</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_TAG_ATTRIBUTE__REQUIRED = eINSTANCE.getJSPTagAttribute_Required();

		/**
		 * The meta object literal for the '<em><b>Rt Expr Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_TAG_ATTRIBUTE__RT_EXPR_VALUE = eINSTANCE.getJSPTagAttribute_RtExprValue();

		/**
		 * The meta object literal for the '<em><b>Fragment</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_TAG_ATTRIBUTE__FRAGMENT = eINSTANCE.getJSPTagAttribute_Fragment();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_TAG_ATTRIBUTE__TYPE = eINSTANCE.getJSPTagAttribute_Type();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_TAG_ATTRIBUTE__DESCRIPTIONS = eINSTANCE.getJSPTagAttribute_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.ValidatorImpl <em>Validator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.ValidatorImpl
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getValidator()
		 * @generated
		 */
		EClass VALIDATOR = eINSTANCE.getValidator();

		/**
		 * The meta object literal for the '<em><b>Validator Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALIDATOR__VALIDATOR_CLASS = eINSTANCE.getValidator_ValidatorClass();

		/**
		 * The meta object literal for the '<em><b>Init Params</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALIDATOR__INIT_PARAMS = eINSTANCE.getValidator_InitParams();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALIDATOR__DESCRIPTIONS = eINSTANCE.getValidator_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.JSPVariableImpl <em>JSP Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.JSPVariableImpl
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getJSPVariable()
		 * @generated
		 */
		EClass JSP_VARIABLE = eINSTANCE.getJSPVariable();

		/**
		 * The meta object literal for the '<em><b>Name Given</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_VARIABLE__NAME_GIVEN = eINSTANCE.getJSPVariable_NameGiven();

		/**
		 * The meta object literal for the '<em><b>Name From Attribute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_VARIABLE__NAME_FROM_ATTRIBUTE = eINSTANCE.getJSPVariable_NameFromAttribute();

		/**
		 * The meta object literal for the '<em><b>Declare</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_VARIABLE__DECLARE = eINSTANCE.getJSPVariable_Declare();

		/**
		 * The meta object literal for the '<em><b>Scope</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute JSP_VARIABLE__SCOPE = eINSTANCE.getJSPVariable_Scope();

		/**
		 * The meta object literal for the '<em><b>Variable Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_VARIABLE__VARIABLE_CLASS = eINSTANCE.getJSPVariable_VariableClass();

		/**
		 * The meta object literal for the '<em><b>Descriptions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference JSP_VARIABLE__DESCRIPTIONS = eINSTANCE.getJSPVariable_Descriptions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.FunctionImpl <em>Function</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.FunctionImpl
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getFunction()
		 * @generated
		 */
		EClass FUNCTION = eINSTANCE.getFunction();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__NAME = eINSTANCE.getFunction_Name();

		/**
		 * The meta object literal for the '<em><b>Signature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__SIGNATURE = eINSTANCE.getFunction_Signature();

		/**
		 * The meta object literal for the '<em><b>Example</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FUNCTION__EXAMPLE = eINSTANCE.getFunction_Example();

		/**
		 * The meta object literal for the '<em><b>Function Class</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION__FUNCTION_CLASS = eINSTANCE.getFunction_FunctionClass();

		/**
		 * The meta object literal for the '<em><b>Function Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FUNCTION__FUNCTION_EXTENSIONS = eINSTANCE.getFunction_FunctionExtensions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.TagFileImpl <em>Tag File</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TagFileImpl
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getTagFile()
		 * @generated
		 */
		EClass TAG_FILE = eINSTANCE.getTagFile();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_FILE__NAME = eINSTANCE.getTagFile_Name();

		/**
		 * The meta object literal for the '<em><b>Path</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_FILE__PATH = eINSTANCE.getTagFile_Path();

		/**
		 * The meta object literal for the '<em><b>Example</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TAG_FILE__EXAMPLE = eINSTANCE.getTagFile_Example();

		/**
		 * The meta object literal for the '<em><b>Tag Extensions</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TAG_FILE__TAG_EXTENSIONS = eINSTANCE.getTagFile_TagExtensions();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.TldExtensionImpl <em>Tld Extension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TldExtensionImpl
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getTldExtension()
		 * @generated
		 */
		EClass TLD_EXTENSION = eINSTANCE.getTldExtension();

		/**
		 * The meta object literal for the '<em><b>Namespace</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TLD_EXTENSION__NAMESPACE = eINSTANCE.getTldExtension_Namespace();

		/**
		 * The meta object literal for the '<em><b>Extension Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TLD_EXTENSION__EXTENSION_ELEMENTS = eINSTANCE.getTldExtension_ExtensionElements();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.impl.ExtensibleTypeImpl <em>Extensible Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.ExtensibleTypeImpl
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getExtensibleType()
		 * @generated
		 */
		EClass EXTENSIBLE_TYPE = eINSTANCE.getExtensibleType();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EXTENSIBLE_TYPE__VALUE = eINSTANCE.getExtensibleType_Value();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.BodyContentType <em>Body Content Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.BodyContentType
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getBodyContentType()
		 * @generated
		 */
		EEnum BODY_CONTENT_TYPE = eINSTANCE.getBodyContentType();

		/**
		 * The meta object literal for the '{@link org.eclipse.jst.j2ee.taglib.internal.JSPScriptingVariableScope <em>JSP Scripting Variable Scope</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.eclipse.jst.j2ee.taglib.internal.JSPScriptingVariableScope
		 * @see org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl#getJSPScriptingVariableScope()
		 * @generated
		 */
		EEnum JSP_SCRIPTING_VARIABLE_SCOPE = eINSTANCE.getJSPScriptingVariableScope();

	}

} //TaglibPackage
