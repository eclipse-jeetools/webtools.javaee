/**
 * <copyright>
 * </copyright>
 *
 * $Id: JspPropertyGroupImpl.java,v 1.1 2007/05/16 06:42:37 cbridgha Exp $
 */
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
	protected EList descriptions = null;

	/**
	 * The cached value of the '{@link #getDisplayNames() <em>Display Names</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayNames()
	 * @generated
	 * @ordered
	 */
	protected EList displayNames = null;

	/**
	 * The cached value of the '{@link #getIcons() <em>Icons</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIcons()
	 * @generated
	 * @ordered
	 */
	protected EList icons = null;

	/**
	 * The cached value of the '{@link #getUrlPatterns() <em>Url Patterns</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrlPatterns()
	 * @generated
	 * @ordered
	 */
	protected EList urlPatterns = null;

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
	protected boolean elIgnoredESet = false;

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
	protected boolean scriptingInvalidESet = false;

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
	protected boolean isXmlESet = false;

	/**
	 * The cached value of the '{@link #getIncludePreludes() <em>Include Preludes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludePreludes()
	 * @generated
	 * @ordered
	 */
	protected EList includePreludes = null;

	/**
	 * The cached value of the '{@link #getIncludeCodas() <em>Include Codas</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludeCodas()
	 * @generated
	 * @ordered
	 */
	protected EList includeCodas = null;

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
	protected boolean deferredSyntaxAllowedAsLiteralESet = false;

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
	protected boolean trimDirectiveWhitespacesESet = false;

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
	protected EClass eStaticClass() {
		return JspPackage.Literals.JSP_PROPERTY_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList(DisplayName.class, this, JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getIcons() {
		if (icons == null) {
			icons = new EObjectContainmentEList(Icon.class, this, JspPackage.JSP_PROPERTY_GROUP__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getUrlPatterns() {
		if (urlPatterns == null) {
			urlPatterns = new EObjectContainmentEList(UrlPatternType.class, this, JspPackage.JSP_PROPERTY_GROUP__URL_PATTERNS);
		}
		return urlPatterns;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isElIgnored() {
		return elIgnored;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public boolean isSetElIgnored() {
		return elIgnoredESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPageEncoding() {
		return pageEncoding;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public boolean isScriptingInvalid() {
		return scriptingInvalid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public boolean isSetScriptingInvalid() {
		return scriptingInvalidESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsXml() {
		return isXml;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public boolean isSetIsXml() {
		return isXmlESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getIncludePreludes() {
		if (includePreludes == null) {
			includePreludes = new EDataTypeEList(String.class, this, JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES);
		}
		return includePreludes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getIncludeCodas() {
		if (includeCodas == null) {
			includeCodas = new EDataTypeEList(String.class, this, JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS);
		}
		return includeCodas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isDeferredSyntaxAllowedAsLiteral() {
		return deferredSyntaxAllowedAsLiteral;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public boolean isSetDeferredSyntaxAllowedAsLiteral() {
		return deferredSyntaxAllowedAsLiteralESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isTrimDirectiveWhitespaces() {
		return trimDirectiveWhitespaces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public boolean isSetTrimDirectiveWhitespaces() {
		return trimDirectiveWhitespacesESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERNS:
				return ((InternalEList)getUrlPatterns()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
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
				return isElIgnored() ? Boolean.TRUE : Boolean.FALSE;
			case JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING:
				return getPageEncoding();
			case JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID:
				return isScriptingInvalid() ? Boolean.TRUE : Boolean.FALSE;
			case JspPackage.JSP_PROPERTY_GROUP__IS_XML:
				return isIsXml() ? Boolean.TRUE : Boolean.FALSE;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				return getIncludePreludes();
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				return getIncludeCodas();
			case JspPackage.JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL:
				return isDeferredSyntaxAllowedAsLiteral() ? Boolean.TRUE : Boolean.FALSE;
			case JspPackage.JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES:
				return isTrimDirectiveWhitespaces() ? Boolean.TRUE : Boolean.FALSE;
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERNS:
				getUrlPatterns().clear();
				getUrlPatterns().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED:
				setElIgnored(((Boolean)newValue).booleanValue());
				return;
			case JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING:
				setPageEncoding((String)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID:
				setScriptingInvalid(((Boolean)newValue).booleanValue());
				return;
			case JspPackage.JSP_PROPERTY_GROUP__IS_XML:
				setIsXml(((Boolean)newValue).booleanValue());
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				getIncludePreludes().clear();
				getIncludePreludes().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				getIncludeCodas().clear();
				getIncludeCodas().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DEFERRED_SYNTAX_ALLOWED_AS_LITERAL:
				setDeferredSyntaxAllowedAsLiteral(((Boolean)newValue).booleanValue());
				return;
			case JspPackage.JSP_PROPERTY_GROUP__TRIM_DIRECTIVE_WHITESPACES:
				setTrimDirectiveWhitespaces(((Boolean)newValue).booleanValue());
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
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //JspPropertyGroupImpl