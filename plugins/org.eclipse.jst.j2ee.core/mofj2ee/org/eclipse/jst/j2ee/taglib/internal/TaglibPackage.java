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
package org.eclipse.jst.j2ee.taglib.internal;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.common.CommonPackage;



public interface TaglibPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "taglib";//$NON-NLS-1$

	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__SMALL_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__LARGE_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__DESCRIPTION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__DISPLAY_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__TAG_LIB_VERSION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__JSP_VERSION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__SHORT_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__URI = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__TAGS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int TAG_LIB__VALIDATOR = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 5;
	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * The number of structural features of the the '<em>Tag Lib</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB_FEATURE_COUNT = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 9;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int VALIDATOR = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */


	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG__BODY_CONTENT = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 0;
	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG__ATTRIBUTES = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG__VARIABLES = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 5;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG__TAG_CLASS = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 6;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG__TEI_CLASS = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the the '<em>JSP Tag</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_FEATURE_COUNT = CommonPackage.DESCRIPTION_GROUP_FEATURE_COUNT + 8;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG_ATTRIBUTE = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG_ATTRIBUTE__NAME = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG_ATTRIBUTE__REQUIRED = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG_ATTRIBUTE__RT_EXPR_VALUE = 2;
	/**
	 * The feature id for the '<em><b>Fragment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE__FRAGMENT = 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_TAG_ATTRIBUTE__TYPE = 4;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE__DESCRIPTIONS = 5;

	/**
	 * The number of structural features of the the '<em>JSP Tag Attribute</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_TAG_ATTRIBUTE_FEATURE_COUNT = 6;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int VALIDATOR__VALIDATOR_CLASS = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int VALIDATOR__INIT_PARAMS = 1;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR__DESCRIPTIONS = 2;

	/**
	 * The number of structural features of the the '<em>Validator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALIDATOR_FEATURE_COUNT = 3;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_VARIABLE = 4;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_VARIABLE__NAME_GIVEN = 0;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_VARIABLE__NAME_FROM_ATTRIBUTE = 1;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_VARIABLE__DECLARE = 2;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_VARIABLE__SCOPE = 3;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_VARIABLE__VARIABLE_CLASS = 4;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_VARIABLE__DESCRIPTIONS = 5;

	/**
	 * The number of structural features of the the '<em>JSP Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_VARIABLE_FEATURE_COUNT = 6;

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
	 * The number of structural features of the the '<em>Function</em>' class.
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
	 * The number of structural features of the the '<em>Tag File</em>' class.
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
	int TLD_EXTENSION__NAMESPACE = 0;

	/**
	 * The feature id for the '<em><b>Extension Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLD_EXTENSION__EXTENSION_ELEMENTS = 1;

	/**
	 * The number of structural features of the the '<em>Tld Extension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TLD_EXTENSION_FEATURE_COUNT = 2;

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
	int EXTENSIBLE_TYPE__VALUE = 0;

	/**
	 * The number of structural features of the the '<em>Extensible Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTENSIBLE_TYPE_FEATURE_COUNT = 1;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int BODY_CONTENT_TYPE = 9;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	int JSP_SCRIPTING_VARIABLE_SCOPE = 10;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	String eNS_URI = "taglib.xmi";//$NON-NLS-1$
	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.taglib";//$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TaglibPackage eINSTANCE = org.eclipse.jst.j2ee.taglib.internal.impl.TaglibPackageImpl.init();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return TagLib object
	 */
	EClass getTagLib();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getTagLib_TagLibVersion();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getTagLib_JspVersion();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getTagLib_ShortName();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getTagLib_Uri();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getTagLib_Tags();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getTagLib_Validator();

	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation 
	 * @return Validator object
	 */
	EClass getValidator();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getValidator_ValidatorClass();

	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation 
	 * @return JSPTag object
	 */
	EClass getJSPTag();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJSPTag_BodyContent();

	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJSPTag_Attributes();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJSPTag_Variables();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJSPTag_TagClass();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EReference getJSPTag_TeiClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JSPTagAttribute object
	 */
	EClass getJSPTagAttribute();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJSPTagAttribute_Name();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJSPTagAttribute_Required();

	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation 
	 * @return JSPVariable object
	 */
	EClass getJSPVariable();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJSPVariable_NameGiven();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJSPVariable_NameFromAttribute();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJSPVariable_Declare();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	EAttribute getJSPVariable_Scope();

	/**
	 * @generated This field/method will be replaced during code generation.
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
	 * @generated This field/method will be replaced during code generation 
	 * @return BodyContentType object
	 */
	EEnum getBodyContentType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return JSPScriptingVariableScope object
	 */
	EEnum getJSPScriptingVariableScope();

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	TaglibFactory getTaglibFactory();

} //TaglibPackage















