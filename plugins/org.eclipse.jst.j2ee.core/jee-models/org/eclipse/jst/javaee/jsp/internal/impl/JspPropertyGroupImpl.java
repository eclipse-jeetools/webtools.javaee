/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jsp.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.core.UrlPatternType;

import org.eclipse.jst.javaee.jsp.JspPropertyGroup;

import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getUrlPatterns <em>Url Patterns</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#isElIgnored <em>El Ignored</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getPageEncoding <em>Page Encoding</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#isScriptingInvalid <em>Scripting Invalid</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#isIsXml <em>Is Xml</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getIncludePreludes <em>Include Preludes</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getIncludeCodas <em>Include Codas</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#isDeferredSyntaxAllowedAsLiteral <em>Deferred Syntax Allowed As Literal</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#isTrimDirectiveWhitespaces <em>Trim Directive Whitespaces</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getDefaultContentType <em>Default Content Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getBuffer <em>Buffer</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#isErrorOnUndeclaredNamespace <em>Error On Undeclared Namespace</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspPropertyGroupImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JspPropertyGroupImpl extends EObjectImpl implements JspPropertyGroup {
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList<Description> descriptions;

	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList<DisplayName> displayNames;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList<Icon> icons;

	/**
	 * The cached value of the '{@link #getUrlPatterns() <em>Url Patterns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrlPatterns()
	 * @generated
	 * @ordered
	 */
	protected EList<UrlPatternType> urlPatterns;

	/**
	 * The default value of the '{@link #isElIgnored() <em>El Ignored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isElIgnored()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EL_IGNORED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isElIgnored() <em>El Ignored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isElIgnored()
	 * @generated
	 * @ordered
	 */
	protected boolean elIgnored = EL_IGNORED_EDEFAULT;

	/**
	 * This is true if the El Ignored attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean elIgnoredESet;

	/**
	 * The default value of the '{@link #getPageEncoding() <em>Page Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageEncoding()
	 * @generated
	 * @ordered
	 */
	protected static final String PAGE_ENCODING_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPageEncoding() <em>Page Encoding</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPageEncoding()
	 * @generated
	 * @ordered
	 */
	protected String pageEncoding = PAGE_ENCODING_EDEFAULT;

	/**
	 * The default value of the '{@link #isScriptingInvalid() <em>Scripting Invalid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScriptingInvalid()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SCRIPTING_INVALID_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isScriptingInvalid() <em>Scripting Invalid</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isScriptingInvalid()
	 * @generated
	 * @ordered
	 */
	protected boolean scriptingInvalid = SCRIPTING_INVALID_EDEFAULT;

	/**
	 * This is true if the Scripting Invalid attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean scriptingInvalidESet;

	/**
	 * The default value of the '{@link #isIsXml() <em>Is Xml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsXml()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_XML_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsXml() <em>Is Xml</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsXml()
	 * @generated
	 * @ordered
	 */
	protected boolean isXml = IS_XML_EDEFAULT;

	/**
	 * This is true if the Is Xml attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isXmlESet;

	/**
	 * The cached value of the '{@link #getIncludePreludes() <em>Include Preludes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludePreludes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> includePreludes;

	/**
	 * The cached value of the '{@link #getIncludeCodas() <em>Include Codas</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludeCodas()
	 * @generated
	 * @ordered
	 */
	protected EList<String> includeCodas;

	/**
	 * The default value of the '{@link #isDeferredSyntaxAllowedAsLiteral() <em>Deferred Syntax Allowed As Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeferredSyntaxAllowedAsLiteral()
	 * @generated
	 * @ordered
	 */
	protected static final boolean DEFERRED_SYNTAX_ALLOWED_AS_LITERAL_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isDeferredSyntaxAllowedAsLiteral() <em>Deferred Syntax Allowed As Literal</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isDeferredSyntaxAllowedAsLiteral()
	 * @generated
	 * @ordered
	 */
	protected boolean deferredSyntaxAllowedAsLiteral = DEFERRED_SYNTAX_ALLOWED_AS_LITERAL_EDEFAULT;

	/**
	 * This is true if the Deferred Syntax Allowed As Literal attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean deferredSyntaxAllowedAsLiteralESet;

	/**
	 * The default value of the '{@link #isTrimDirectiveWhitespaces() <em>Trim Directive Whitespaces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTrimDirectiveWhitespaces()
	 * @generated
	 * @ordered
	 */
	protected static final boolean TRIM_DIRECTIVE_WHITESPACES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isTrimDirectiveWhitespaces() <em>Trim Directive Whitespaces</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isTrimDirectiveWhitespaces()
	 * @generated
	 * @ordered
	 */
	protected boolean trimDirectiveWhitespaces = TRIM_DIRECTIVE_WHITESPACES_EDEFAULT;

	/**
	 * This is true if the Trim Directive Whitespaces attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean trimDirectiveWhitespacesESet;

	/**
	 * The default value of the '{@link #getDefaultContentType() <em>Default Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultContentType()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_CONTENT_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultContentType() <em>Default Content Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultContentType()
	 * @generated
	 * @ordered
	 */
	protected String defaultContentType = DEFAULT_CONTENT_TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getBuffer() <em>Buffer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuffer()
	 * @generated
	 * @ordered
	 */
	protected static final String BUFFER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBuffer() <em>Buffer</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBuffer()
	 * @generated
	 * @ordered
	 */
	protected String buffer = BUFFER_EDEFAULT;

	/**
	 * The default value of the '{@link #isErrorOnUndeclaredNamespace() <em>Error On Undeclared Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isErrorOnUndeclaredNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ERROR_ON_UNDECLARED_NAMESPACE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isErrorOnUndeclaredNamespace() <em>Error On Undeclared Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isErrorOnUndeclaredNamespace()
	 * @generated
	 * @ordered
	 */
	protected boolean errorOnUndeclaredNamespace = ERROR_ON_UNDECLARED_NAMESPACE_EDEFAULT;

	/**
	 * This is true if the Error On Undeclared Namespace attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean errorOnUndeclaredNamespaceESet;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JspPropertyGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JspPackage.Literals.JSP_PROPERTY_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<DisplayName> getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Icon> getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, JspPackage.JSP_PROPERTY_GROUP__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<UrlPatternType> getUrlPatterns() {
		if (urlPatterns == null) {
			urlPatterns = new EObjectContainmentEList<UrlPatternType>(UrlPatternType.class, this, JspPackage.JSP_PROPERTY_GROUP__URL_PATTERNS);
		}
		return urlPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isElIgnored() {
		return elIgnored;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setElIgnored(boolean newElIgnored) {
		boolean oldElIgnored = elIgnored;
		elIgnored = newElIgnored;
		boolean oldElIgnoredESet = elIgnoredESet;
		elIgnoredESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED, oldElIgnored, elIgnored, !oldElIgnoredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetElIgnored() {
		boolean oldElIgnored = elIgnored;
		boolean oldElIgnoredESet = elIgnoredESet;
		elIgnored = EL_IGNORED_EDEFAULT;
		elIgnoredESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED, oldElIgnored, EL_IGNORED_EDEFAULT, oldElIgnoredESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetElIgnored() {
		return elIgnoredESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getPageEncoding() {
		return pageEncoding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPageEncoding(String newPageEncoding) {
		String oldPageEncoding = pageEncoding;
		pageEncoding = newPageEncoding;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING, oldPageEncoding, pageEncoding));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isScriptingInvalid() {
		return scriptingInvalid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setScriptingInvalid(boolean newScriptingInvalid) {
		boolean oldScriptingInvalid = scriptingInvalid;
		scriptingInvalid = newScriptingInvalid;
		boolean oldScriptingInvalidESet = scriptingInvalidESet;
		scriptingInvalidESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID, oldScriptingInvalid, scriptingInvalid, !oldScriptingInvalidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetScriptingInvalid() {
		boolean oldScriptingInvalid = scriptingInvalid;
		boolean oldScriptingInvalidESet = scriptingInvalidESet;
		scriptingInvalid = SCRIPTING_INVALID_EDEFAULT;
		scriptingInvalidESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID, oldScriptingInvalid, SCRIPTING_INVALID_EDEFAULT, oldScriptingInvalidESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetScriptingInvalid() {
		return scriptingInvalidESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isIsXml() {
		return isXml;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setIsXml(boolean newIsXml) {
		boolean oldIsXml = isXml;
		isXml = newIsXml;
		boolean oldIsXmlESet = isXmlESet;
		isXmlESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__IS_XML, oldIsXml, isXml, !oldIsXmlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetIsXml() {
		boolean oldIsXml = isXml;
		boolean oldIsXmlESet = isXmlESet;
		isXml = IS_XML_EDEFAULT;
		isXmlESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JspPackage.JSP_PROPERTY_GROUP__IS_XML, oldIsXml, IS_XML_EDEFAULT, oldIsXmlESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetIsXml() {
		return isXmlESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<String> getIncludePreludes() {
		if (includePreludes == null) {
			includePreludes = new EDataTypeEList<String>(String.class, this, JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES);
		}
		return includePreludes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<String> getIncludeCodas() {
		if (includeCodas == null) {
			includeCodas = new EDataTypeEList<String>(String.class, this, JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS);
		}
		return includeCodas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isDeferredSyntaxAllowedAsLiteral() {
		return deferredSyntaxAllowedAsLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDeferredSyntaxAllowedAsLiteral(boolean newDeferredSyntaxAllowedAsLiteral) {
		boolean oldDeferredSyntaxAllowedAsLiteral = deferredSyntaxAllowedAsLiteral;
		deferredSyntaxAllowedAsLiteral = newDeferredSyntaxAllowedAsLiteral;
		boolean oldDeferredSyntaxAllowedAsLiteralESet = deferredSyntaxAllowedAsLiteralESet;
		deferredSyntaxAllowedAsLiteralESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL, oldDeferredSyntaxAllowedAsLiteral, deferredSyntaxAllowedAsLiteral, !oldDeferredSyntaxAllowedAsLiteralESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetDeferredSyntaxAllowedAsLiteral() {
		boolean oldDeferredSyntaxAllowedAsLiteral = deferredSyntaxAllowedAsLiteral;
		boolean oldDeferredSyntaxAllowedAsLiteralESet = deferredSyntaxAllowedAsLiteralESet;
		deferredSyntaxAllowedAsLiteral = DEFERRED_SYNTAX_ALLOWED_AS_LITERAL_EDEFAULT;
		deferredSyntaxAllowedAsLiteralESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JspPackage.JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL, oldDeferredSyntaxAllowedAsLiteral, DEFERRED_SYNTAX_ALLOWED_AS_LITERAL_EDEFAULT, oldDeferredSyntaxAllowedAsLiteralESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetDeferredSyntaxAllowedAsLiteral() {
		return deferredSyntaxAllowedAsLiteralESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isTrimDirectiveWhitespaces() {
		return trimDirectiveWhitespaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setTrimDirectiveWhitespaces(boolean newTrimDirectiveWhitespaces) {
		boolean oldTrimDirectiveWhitespaces = trimDirectiveWhitespaces;
		trimDirectiveWhitespaces = newTrimDirectiveWhitespaces;
		boolean oldTrimDirectiveWhitespacesESet = trimDirectiveWhitespacesESet;
		trimDirectiveWhitespacesESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES, oldTrimDirectiveWhitespaces, trimDirectiveWhitespaces, !oldTrimDirectiveWhitespacesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetTrimDirectiveWhitespaces() {
		boolean oldTrimDirectiveWhitespaces = trimDirectiveWhitespaces;
		boolean oldTrimDirectiveWhitespacesESet = trimDirectiveWhitespacesESet;
		trimDirectiveWhitespaces = TRIM_DIRECTIVE_WHITESPACES_EDEFAULT;
		trimDirectiveWhitespacesESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JspPackage.JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES, oldTrimDirectiveWhitespaces, TRIM_DIRECTIVE_WHITESPACES_EDEFAULT, oldTrimDirectiveWhitespacesESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetTrimDirectiveWhitespaces() {
		return trimDirectiveWhitespacesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getDefaultContentType() {
		return defaultContentType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDefaultContentType(String newDefaultContentType) {
		String oldDefaultContentType = defaultContentType;
		defaultContentType = newDefaultContentType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__DEFAULT_CONTENT_TYPE, oldDefaultContentType, defaultContentType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getBuffer() {
		return buffer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setBuffer(String newBuffer) {
		String oldBuffer = buffer;
		buffer = newBuffer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__BUFFER, oldBuffer, buffer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isErrorOnUndeclaredNamespace() {
		return errorOnUndeclaredNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setErrorOnUndeclaredNamespace(boolean newErrorOnUndeclaredNamespace) {
		boolean oldErrorOnUndeclaredNamespace = errorOnUndeclaredNamespace;
		errorOnUndeclaredNamespace = newErrorOnUndeclaredNamespace;
		boolean oldErrorOnUndeclaredNamespaceESet = errorOnUndeclaredNamespaceESet;
		errorOnUndeclaredNamespaceESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__ERROR_ON_UNDECLARED_NAMESPACE, oldErrorOnUndeclaredNamespace, errorOnUndeclaredNamespace, !oldErrorOnUndeclaredNamespaceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetErrorOnUndeclaredNamespace() {
		boolean oldErrorOnUndeclaredNamespace = errorOnUndeclaredNamespace;
		boolean oldErrorOnUndeclaredNamespaceESet = errorOnUndeclaredNamespaceESet;
		errorOnUndeclaredNamespace = ERROR_ON_UNDECLARED_NAMESPACE_EDEFAULT;
		errorOnUndeclaredNamespaceESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JspPackage.JSP_PROPERTY_GROUP__ERROR_ON_UNDECLARED_NAMESPACE, oldErrorOnUndeclaredNamespace, ERROR_ON_UNDECLARED_NAMESPACE_EDEFAULT, oldErrorOnUndeclaredNamespaceESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetErrorOnUndeclaredNamespace() {
		return errorOnUndeclaredNamespaceESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERNS:
				return ((InternalEList<?>)getUrlPatterns()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				return getDescriptions();
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				return getDisplayNames();
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				return getIcons();
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERNS:
				return getUrlPatterns();
			case JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED:
				return isElIgnored();
			case JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING:
				return getPageEncoding();
			case JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID:
				return isScriptingInvalid();
			case JspPackage.JSP_PROPERTY_GROUP__IS_XML:
				return isIsXml();
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				return getIncludePreludes();
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				return getIncludeCodas();
			case JspPackage.JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL:
				return isDeferredSyntaxAllowedAsLiteral();
			case JspPackage.JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES:
				return isTrimDirectiveWhitespaces();
			case JspPackage.JSP_PROPERTY_GROUP__DEFAULT_CONTENT_TYPE:
				return getDefaultContentType();
			case JspPackage.JSP_PROPERTY_GROUP__BUFFER:
				return getBuffer();
			case JspPackage.JSP_PROPERTY_GROUP__ERROR_ON_UNDECLARED_NAMESPACE:
				return isErrorOnUndeclaredNamespace();
			case JspPackage.JSP_PROPERTY_GROUP__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERNS:
				getUrlPatterns().clear();
				getUrlPatterns().addAll((Collection<? extends UrlPatternType>)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED:
				setElIgnored((Boolean)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING:
				setPageEncoding((String)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID:
				setScriptingInvalid((Boolean)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__IS_XML:
				setIsXml((Boolean)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				getIncludePreludes().clear();
				getIncludePreludes().addAll((Collection<? extends String>)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				getIncludeCodas().clear();
				getIncludeCodas().addAll((Collection<? extends String>)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL:
				setDeferredSyntaxAllowedAsLiteral((Boolean)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES:
				setTrimDirectiveWhitespaces((Boolean)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DEFAULT_CONTENT_TYPE:
				setDefaultContentType((String)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__BUFFER:
				setBuffer((String)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__ERROR_ON_UNDECLARED_NAMESPACE:
				setErrorOnUndeclaredNamespace((Boolean)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__ID:
				setId((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				getIcons().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERNS:
				getUrlPatterns().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED:
				unsetElIgnored();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING:
				setPageEncoding(PAGE_ENCODING_EDEFAULT);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID:
				unsetScriptingInvalid();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__IS_XML:
				unsetIsXml();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				getIncludePreludes().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				getIncludeCodas().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL:
				unsetDeferredSyntaxAllowedAsLiteral();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES:
				unsetTrimDirectiveWhitespaces();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DEFAULT_CONTENT_TYPE:
				setDefaultContentType(DEFAULT_CONTENT_TYPE_EDEFAULT);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__BUFFER:
				setBuffer(BUFFER_EDEFAULT);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__ERROR_ON_UNDECLARED_NAMESPACE:
				unsetErrorOnUndeclaredNamespace();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__ID:
				setId(ID_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				return icons != null && !icons.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERNS:
				return urlPatterns != null && !urlPatterns.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED:
				return isSetElIgnored();
			case JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING:
				return PAGE_ENCODING_EDEFAULT == null ? pageEncoding != null : !PAGE_ENCODING_EDEFAULT.equals(pageEncoding);
			case JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID:
				return isSetScriptingInvalid();
			case JspPackage.JSP_PROPERTY_GROUP__IS_XML:
				return isSetIsXml();
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				return includePreludes != null && !includePreludes.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				return includeCodas != null && !includeCodas.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL:
				return isSetDeferredSyntaxAllowedAsLiteral();
			case JspPackage.JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES:
				return isSetTrimDirectiveWhitespaces();
			case JspPackage.JSP_PROPERTY_GROUP__DEFAULT_CONTENT_TYPE:
				return DEFAULT_CONTENT_TYPE_EDEFAULT == null ? defaultContentType != null : !DEFAULT_CONTENT_TYPE_EDEFAULT.equals(defaultContentType);
			case JspPackage.JSP_PROPERTY_GROUP__BUFFER:
				return BUFFER_EDEFAULT == null ? buffer != null : !BUFFER_EDEFAULT.equals(buffer);
			case JspPackage.JSP_PROPERTY_GROUP__ERROR_ON_UNDECLARED_NAMESPACE:
				return isSetErrorOnUndeclaredNamespace();
			case JspPackage.JSP_PROPERTY_GROUP__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (elIgnored: "); //$NON-NLS-1$
		if (elIgnoredESet) result.append(elIgnored); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", pageEncoding: "); //$NON-NLS-1$
		result.append(pageEncoding);
		result.append(", scriptingInvalid: "); //$NON-NLS-1$
		if (scriptingInvalidESet) result.append(scriptingInvalid); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", isXml: "); //$NON-NLS-1$
		if (isXmlESet) result.append(isXml); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", includePreludes: "); //$NON-NLS-1$
		result.append(includePreludes);
		result.append(", includeCodas: "); //$NON-NLS-1$
		result.append(includeCodas);
		result.append(", deferredSyntaxAllowedAsLiteral: "); //$NON-NLS-1$
		if (deferredSyntaxAllowedAsLiteralESet) result.append(deferredSyntaxAllowedAsLiteral); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", trimDirectiveWhitespaces: "); //$NON-NLS-1$
		if (trimDirectiveWhitespacesESet) result.append(trimDirectiveWhitespaces); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", defaultContentType: "); //$NON-NLS-1$
		result.append(defaultContentType);
		result.append(", buffer: "); //$NON-NLS-1$
		result.append(buffer);
		result.append(", errorOnUndeclaredNamespace: "); //$NON-NLS-1$
		if (errorOnUndeclaredNamespaceESet) result.append(errorOnUndeclaredNamespace); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //JspPropertyGroupImpl