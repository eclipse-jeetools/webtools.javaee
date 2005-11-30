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
package org.eclipse.jst.j2ee.jsp;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
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
 * /**
 * <p>
 * <b>Note:</b> This class/interface is part of an interim API that is still under development and expected to
 * change significantly before reaching stability. It is being made available at this early stage to solicit feedback
 * from pioneering adopters on the understanding that any code that uses this API will almost certainly be broken
 * (repeatedly) as the API evolves.
 * </p>
 *
 * <!-- end-user-doc -->
 * @see org.eclipse.jst.j2ee.jsp.JspFactory
 * @model kind="package"
 * @generated
 * @since 1.0 */
public interface JspPackage extends EPackage{
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "jsp";//$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "jsp.xmi";//$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.eclipse.jst.j2ee.jsp";//$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	JspPackage eINSTANCE = org.eclipse.jst.j2ee.jsp.internal.impl.JspPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jsp.internal.impl.JSPConfigImpl <em>JSP Config</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jsp.internal.impl.JSPConfigImpl
	 * @see org.eclipse.jst.j2ee.jsp.internal.impl.JspPackageImpl#getJSPConfig()
	 * @generated
	 */
	int JSP_CONFIG = 0;

	/**
	 * The feature id for the '<em><b>Tag Libs</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_CONFIG__TAG_LIBS = 0;

	/**
	 * The feature id for the '<em><b>Property Groups</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_CONFIG__PROPERTY_GROUPS = 1;

	/**
	 * The number of structural features of the the '<em>JSP Config</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_CONFIG_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jsp.internal.impl.JSPPropertyGroupImpl <em>JSP Property Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jsp.internal.impl.JSPPropertyGroupImpl
	 * @see org.eclipse.jst.j2ee.jsp.internal.impl.JspPackageImpl#getJSPPropertyGroup()
	 * @generated
	 */
	int JSP_PROPERTY_GROUP = 1;

	/**
	 * The feature id for the '<em><b>Icons</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__ICONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__ICONS;

	/**
	 * The feature id for the '<em><b>Display Names</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__DISPLAY_NAMES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAMES;

	/**
	 * The feature id for the '<em><b>Descriptions</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__DESCRIPTIONS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTIONS;

	/**
	 * The feature id for the '<em><b>Small Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__SMALL_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__SMALL_ICON;

	/**
	 * The feature id for the '<em><b>Large Icon</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__LARGE_ICON = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__LARGE_ICON;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__DESCRIPTION = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Display Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__DISPLAY_NAME = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP__DISPLAY_NAME;

	/**
	 * The feature id for the '<em><b>Url Pattern</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__URL_PATTERN = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>El Ignored</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__EL_IGNORED = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Page Encoding</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__PAGE_ENCODING = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Scripting Invalid</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__SCRIPTING_INVALID = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Is XML</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__IS_XML = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Include Preludes</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__INCLUDE_PRELUDES = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Include Codas</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP__INCLUDE_CODAS = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the the '<em>JSP Property Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int JSP_PROPERTY_GROUP_FEATURE_COUNT = CommonPackage.COMPATIBILITY_DESCRIPTION_GROUP_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link org.eclipse.jst.j2ee.jsp.internal.impl.TagLibRefTypeImpl <em>Tag Lib Ref Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.jst.j2ee.jsp.internal.impl.TagLibRefTypeImpl
	 * @see org.eclipse.jst.j2ee.jsp.internal.impl.JspPackageImpl#getTagLibRefType()
	 * @generated
	 */
	int TAG_LIB_REF_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Taglib URI</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB_REF_TYPE__TAGLIB_URI = 0;

	/**
	 * The feature id for the '<em><b>Taglib Location</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB_REF_TYPE__TAGLIB_LOCATION = 1;

	/**
	 * The number of structural features of the the '<em>Tag Lib Ref Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TAG_LIB_REF_TYPE_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jsp.JSPConfig <em>JSP Config</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>JSP Config</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPConfig
	 * @generated
	 */
	EClass getJSPConfig();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jsp.JSPConfig#getTagLibs <em>Tag Libs</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Tag Libs</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPConfig#getTagLibs()
	 * @see #getJSPConfig()
	 * @generated
	 */
	EReference getJSPConfig_TagLibs();

	/**
	 * Returns the meta object for the containment reference list '{@link org.eclipse.jst.j2ee.jsp.JSPConfig#getPropertyGroups <em>Property Groups</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Property Groups</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPConfig#getPropertyGroups()
	 * @see #getJSPConfig()
	 * @generated
	 */
	EReference getJSPConfig_PropertyGroups();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jsp.JSPPropertyGroup <em>JSP Property Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>JSP Property Group</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPPropertyGroup
	 * @generated
	 */
	EClass getJSPPropertyGroup();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#getUrlPattern <em>Url Pattern</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Url Pattern</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#getUrlPattern()
	 * @see #getJSPPropertyGroup()
	 * @generated
	 */
	EAttribute getJSPPropertyGroup_UrlPattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#isElIgnored <em>El Ignored</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>El Ignored</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#isElIgnored()
	 * @see #getJSPPropertyGroup()
	 * @generated
	 */
	EAttribute getJSPPropertyGroup_ElIgnored();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#getPageEncoding <em>Page Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Page Encoding</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#getPageEncoding()
	 * @see #getJSPPropertyGroup()
	 * @generated
	 */
	EAttribute getJSPPropertyGroup_PageEncoding();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#isScriptingInvalid <em>Scripting Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Scripting Invalid</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#isScriptingInvalid()
	 * @see #getJSPPropertyGroup()
	 * @generated
	 */
	EAttribute getJSPPropertyGroup_ScriptingInvalid();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#isIsXML <em>Is XML</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is XML</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#isIsXML()
	 * @see #getJSPPropertyGroup()
	 * @generated
	 */
	EAttribute getJSPPropertyGroup_IsXML();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#getIncludePreludes <em>Include Preludes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Include Preludes</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#getIncludePreludes()
	 * @see #getJSPPropertyGroup()
	 * @generated
	 */
	EAttribute getJSPPropertyGroup_IncludePreludes();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#getIncludeCodas <em>Include Codas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Include Codas</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.JSPPropertyGroup#getIncludeCodas()
	 * @see #getJSPPropertyGroup()
	 * @generated
	 */
	EAttribute getJSPPropertyGroup_IncludeCodas();

	/**
	 * Returns the meta object for class '{@link org.eclipse.jst.j2ee.jsp.TagLibRefType <em>Tag Lib Ref Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tag Lib Ref Type</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.TagLibRefType
	 * @generated
	 */
	EClass getTagLibRefType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jsp.TagLibRefType#getTaglibURI <em>Taglib URI</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Taglib URI</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.TagLibRefType#getTaglibURI()
	 * @see #getTagLibRefType()
	 * @generated
	 */
	EAttribute getTagLibRefType_TaglibURI();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.jst.j2ee.jsp.TagLibRefType#getTaglibLocation <em>Taglib Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Taglib Location</em>'.
	 * @see org.eclipse.jst.j2ee.jsp.TagLibRefType#getTaglibLocation()
	 * @see #getTagLibRefType()
	 * @generated
	 */
	EAttribute getTagLibRefType_TaglibLocation();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	JspFactory getJspFactory();

} //JspPackage
