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
package org.eclipse.jst.j2ee.jsp.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.jsp.JSPPropertyGroup;
import org.eclipse.jst.j2ee.jsp.JspPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JSP Property Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.impl.JSPPropertyGroupImpl#getUrlPattern <em>Url Pattern</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.impl.JSPPropertyGroupImpl#isElIgnored <em>El Ignored</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.impl.JSPPropertyGroupImpl#getPageEncoding <em>Page Encoding</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.impl.JSPPropertyGroupImpl#isScriptingInvalid <em>Scripting Invalid</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.impl.JSPPropertyGroupImpl#isIsXML <em>Is XML</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.impl.JSPPropertyGroupImpl#getIncludePreludes <em>Include Preludes</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jsp.impl.JSPPropertyGroupImpl#getIncludeCodas <em>Include Codas</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JSPPropertyGroupImpl extends CompatibilityDescriptionGroupImpl implements JSPPropertyGroup {
	/**
	 * The cached value of the '{@link #getUrlPattern() <em>Url Pattern</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUrlPattern()
	 * @generated
	 * @ordered
	 */
	protected EList urlPattern = null;

	/**
	 * The default value of the '{@link #isElIgnored() <em>El Ignored</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isElIgnored()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EL_IGNORED_EDEFAULT = true;

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
	protected static final boolean SCRIPTING_INVALID_EDEFAULT = true;

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
	 * The default value of the '{@link #isIsXML() <em>Is XML</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsXML()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_XML_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsXML() <em>Is XML</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsXML()
	 * @generated
	 * @ordered
	 */
	protected boolean isXML = IS_XML_EDEFAULT;

	/**
	 * This is true if the Is XML attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean isXMLESet = false;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected JSPPropertyGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JspPackage.eINSTANCE.getJSPPropertyGroup();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getUrlPattern() {
		if (urlPattern == null) {
			urlPattern = new EDataTypeUniqueEList(String.class, this, JspPackage.JSP_PROPERTY_GROUP__URL_PATTERN);
		}
		return urlPattern;
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
	public boolean isIsXML() {
		return isXML;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsXML(boolean newIsXML) {
		boolean oldIsXML = isXML;
		isXML = newIsXML;
		boolean oldIsXMLESet = isXMLESet;
		isXMLESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_PROPERTY_GROUP__IS_XML, oldIsXML, isXML, !oldIsXMLESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetIsXML() {
		boolean oldIsXML = isXML;
		boolean oldIsXMLESet = isXMLESet;
		isXML = IS_XML_EDEFAULT;
		isXMLESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JspPackage.JSP_PROPERTY_GROUP__IS_XML, oldIsXML, IS_XML_EDEFAULT, oldIsXMLESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetIsXML() {
		return isXMLESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncludePreludes() {
		if (includePreludes == null) {
			includePreludes = new EDataTypeUniqueEList(String.class, this, JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES);
		}
		return includePreludes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getIncludeCodas() {
		if (includeCodas == null) {
			includeCodas = new EDataTypeUniqueEList(String.class, this, JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS);
		}
		return includeCodas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case JspPackage.JSP_PROPERTY_GROUP__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				default:
					return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
			}
		}
		return eBasicSetContainer(null, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				return getIcons();
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				return getDisplayNames();
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				return getDescriptions();
			case JspPackage.JSP_PROPERTY_GROUP__SMALL_ICON:
				return getSmallIcon();
			case JspPackage.JSP_PROPERTY_GROUP__LARGE_ICON:
				return getLargeIcon();
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTION:
				return getDescription();
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAME:
				return getDisplayName();
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERN:
				return getUrlPattern();
			case JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED:
				return isElIgnored() ? Boolean.TRUE : Boolean.FALSE;
			case JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING:
				return getPageEncoding();
			case JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID:
				return isScriptingInvalid() ? Boolean.TRUE : Boolean.FALSE;
			case JspPackage.JSP_PROPERTY_GROUP__IS_XML:
				return isIsXML() ? Boolean.TRUE : Boolean.FALSE;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				return getIncludePreludes();
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				return getIncludeCodas();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERN:
				getUrlPattern().clear();
				getUrlPattern().addAll((Collection)newValue);
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
				setIsXML(((Boolean)newValue).booleanValue());
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				getIncludePreludes().clear();
				getIncludePreludes().addAll((Collection)newValue);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				getIncludeCodas().clear();
				getIncludeCodas().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				getIcons().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERN:
				getUrlPattern().clear();
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
				unsetIsXML();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				getIncludePreludes().clear();
				return;
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				getIncludeCodas().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case JspPackage.JSP_PROPERTY_GROUP__ICONS:
				return icons != null && !icons.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case JspPackage.JSP_PROPERTY_GROUP__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case JspPackage.JSP_PROPERTY_GROUP__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case JspPackage.JSP_PROPERTY_GROUP__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case JspPackage.JSP_PROPERTY_GROUP__URL_PATTERN:
				return urlPattern != null && !urlPattern.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__EL_IGNORED:
				return isSetElIgnored();
			case JspPackage.JSP_PROPERTY_GROUP__PAGE_ENCODING:
				return PAGE_ENCODING_EDEFAULT == null ? pageEncoding != null : !PAGE_ENCODING_EDEFAULT.equals(pageEncoding);
			case JspPackage.JSP_PROPERTY_GROUP__SCRIPTING_INVALID:
				return isSetScriptingInvalid();
			case JspPackage.JSP_PROPERTY_GROUP__IS_XML:
				return isSetIsXML();
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_PRELUDES:
				return includePreludes != null && !includePreludes.isEmpty();
			case JspPackage.JSP_PROPERTY_GROUP__INCLUDE_CODAS:
				return includeCodas != null && !includeCodas.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (urlPattern: ");//$NON-NLS-1$
		result.append(urlPattern);
		result.append(", elIgnored: ");//$NON-NLS-1$
		if (elIgnoredESet) result.append(elIgnored); else result.append("<unset>");//$NON-NLS-1$
		result.append(", pageEncoding: ");//$NON-NLS-1$
		result.append(pageEncoding);
		result.append(", scriptingInvalid: ");//$NON-NLS-1$
		if (scriptingInvalidESet) result.append(scriptingInvalid); else result.append("<unset>");//$NON-NLS-1$
		result.append(", isXML: ");//$NON-NLS-1$
		if (isXMLESet) result.append(isXML); else result.append("<unset>");//$NON-NLS-1$
		result.append(", includePreludes: ");//$NON-NLS-1$
		result.append(includePreludes);
		result.append(", includeCodas: ");//$NON-NLS-1$
		result.append(includeCodas);
		result.append(')');
		return result.toString();
	}

} //JSPPropertyGroupImpl
