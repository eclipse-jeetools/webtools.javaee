/*******************************************************************************
 * Copyright (c) 2009, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/**
 * <copyright>
 * </copyright>
 *
 * $Id: CookieConfigTypeImpl.java,v 1.1 2009/10/15 18:52:05 canderson Exp $
 */
package org.eclipse.jst.javaee.web.internal.impl;

import java.math.BigInteger;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.jst.javaee.web.CookieCommentType;
import org.eclipse.jst.javaee.web.CookieConfigType;
import org.eclipse.jst.javaee.web.CookieDomainType;
import org.eclipse.jst.javaee.web.CookieNameType;
import org.eclipse.jst.javaee.web.CookiePathType;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cookie Config Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl#getDomain <em>Domain</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl#getPath <em>Path</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl#isHttpOnly <em>Http Only</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl#isSecure <em>Secure</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl#getMaxAge <em>Max Age</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.CookieConfigTypeImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CookieConfigTypeImpl extends EObjectImpl implements CookieConfigType {
	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected CookieNameType name;

	/**
	 * The cached value of the '{@link #getDomain() <em>Domain</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDomain()
	 * @generated
	 * @ordered
	 */
	protected CookieDomainType domain;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected CookiePathType path;

	/**
	 * The cached value of the '{@link #getComment() <em>Comment</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComment()
	 * @generated
	 * @ordered
	 */
	protected CookieCommentType comment;

	/**
	 * The default value of the '{@link #isHttpOnly() <em>Http Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHttpOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean HTTP_ONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isHttpOnly() <em>Http Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isHttpOnly()
	 * @generated
	 * @ordered
	 */
	protected boolean httpOnly = HTTP_ONLY_EDEFAULT;

	/**
	 * This is true if the Http Only attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean httpOnlyESet;

	/**
	 * The default value of the '{@link #isSecure() <em>Secure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSecure()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SECURE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSecure() <em>Secure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSecure()
	 * @generated
	 * @ordered
	 */
	protected boolean secure = SECURE_EDEFAULT;

	/**
	 * This is true if the Secure attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean secureESet;

	/**
	 * The default value of the '{@link #getMaxAge() <em>Max Age</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxAge()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger MAX_AGE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMaxAge() <em>Max Age</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMaxAge()
	 * @generated
	 * @ordered
	 */
	protected BigInteger maxAge = MAX_AGE_EDEFAULT;

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
	protected CookieConfigTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return WebPackage.Literals.COOKIE_CONFIG_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CookieNameType getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetName(CookieNameType newName, NotificationChain msgs) {
		CookieNameType oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__NAME, oldName, newName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setName(CookieNameType newName) {
		if (newName != name) {
			NotificationChain msgs = null;
			if (name != null)
				msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.COOKIE_CONFIG_TYPE__NAME, null, msgs);
			if (newName != null)
				msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.COOKIE_CONFIG_TYPE__NAME, null, msgs);
			msgs = basicSetName(newName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__NAME, newName, newName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CookieDomainType getDomain() {
		return domain;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDomain(CookieDomainType newDomain, NotificationChain msgs) {
		CookieDomainType oldDomain = domain;
		domain = newDomain;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__DOMAIN, oldDomain, newDomain);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setDomain(CookieDomainType newDomain) {
		if (newDomain != domain) {
			NotificationChain msgs = null;
			if (domain != null)
				msgs = ((InternalEObject)domain).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.COOKIE_CONFIG_TYPE__DOMAIN, null, msgs);
			if (newDomain != null)
				msgs = ((InternalEObject)newDomain).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.COOKIE_CONFIG_TYPE__DOMAIN, null, msgs);
			msgs = basicSetDomain(newDomain, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__DOMAIN, newDomain, newDomain));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CookiePathType getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetPath(CookiePathType newPath, NotificationChain msgs) {
		CookiePathType oldPath = path;
		path = newPath;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__PATH, oldPath, newPath);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setPath(CookiePathType newPath) {
		if (newPath != path) {
			NotificationChain msgs = null;
			if (path != null)
				msgs = ((InternalEObject)path).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.COOKIE_CONFIG_TYPE__PATH, null, msgs);
			if (newPath != null)
				msgs = ((InternalEObject)newPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.COOKIE_CONFIG_TYPE__PATH, null, msgs);
			msgs = basicSetPath(newPath, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__PATH, newPath, newPath));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public CookieCommentType getComment() {
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetComment(CookieCommentType newComment, NotificationChain msgs) {
		CookieCommentType oldComment = comment;
		comment = newComment;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__COMMENT, oldComment, newComment);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setComment(CookieCommentType newComment) {
		if (newComment != comment) {
			NotificationChain msgs = null;
			if (comment != null)
				msgs = ((InternalEObject)comment).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.COOKIE_CONFIG_TYPE__COMMENT, null, msgs);
			if (newComment != null)
				msgs = ((InternalEObject)newComment).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.COOKIE_CONFIG_TYPE__COMMENT, null, msgs);
			msgs = basicSetComment(newComment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__COMMENT, newComment, newComment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isHttpOnly() {
		return httpOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setHttpOnly(boolean newHttpOnly) {
		boolean oldHttpOnly = httpOnly;
		httpOnly = newHttpOnly;
		boolean oldHttpOnlyESet = httpOnlyESet;
		httpOnlyESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__HTTP_ONLY, oldHttpOnly, httpOnly, !oldHttpOnlyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetHttpOnly() {
		boolean oldHttpOnly = httpOnly;
		boolean oldHttpOnlyESet = httpOnlyESet;
		httpOnly = HTTP_ONLY_EDEFAULT;
		httpOnlyESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.COOKIE_CONFIG_TYPE__HTTP_ONLY, oldHttpOnly, HTTP_ONLY_EDEFAULT, oldHttpOnlyESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetHttpOnly() {
		return httpOnlyESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSecure() {
		return secure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setSecure(boolean newSecure) {
		boolean oldSecure = secure;
		secure = newSecure;
		boolean oldSecureESet = secureESet;
		secureESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__SECURE, oldSecure, secure, !oldSecureESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetSecure() {
		boolean oldSecure = secure;
		boolean oldSecureESet = secureESet;
		secure = SECURE_EDEFAULT;
		secureESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, WebPackage.COOKIE_CONFIG_TYPE__SECURE, oldSecure, SECURE_EDEFAULT, oldSecureESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetSecure() {
		return secureESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public BigInteger getMaxAge() {
		return maxAge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMaxAge(BigInteger newMaxAge) {
		BigInteger oldMaxAge = maxAge;
		maxAge = newMaxAge;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__MAX_AGE, oldMaxAge, maxAge));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.COOKIE_CONFIG_TYPE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.COOKIE_CONFIG_TYPE__NAME:
				return basicSetName(null, msgs);
			case WebPackage.COOKIE_CONFIG_TYPE__DOMAIN:
				return basicSetDomain(null, msgs);
			case WebPackage.COOKIE_CONFIG_TYPE__PATH:
				return basicSetPath(null, msgs);
			case WebPackage.COOKIE_CONFIG_TYPE__COMMENT:
				return basicSetComment(null, msgs);
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
			case WebPackage.COOKIE_CONFIG_TYPE__NAME:
				return getName();
			case WebPackage.COOKIE_CONFIG_TYPE__DOMAIN:
				return getDomain();
			case WebPackage.COOKIE_CONFIG_TYPE__PATH:
				return getPath();
			case WebPackage.COOKIE_CONFIG_TYPE__COMMENT:
				return getComment();
			case WebPackage.COOKIE_CONFIG_TYPE__HTTP_ONLY:
				return isHttpOnly();
			case WebPackage.COOKIE_CONFIG_TYPE__SECURE:
				return isSecure();
			case WebPackage.COOKIE_CONFIG_TYPE__MAX_AGE:
				return getMaxAge();
			case WebPackage.COOKIE_CONFIG_TYPE__ID:
				return getId();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case WebPackage.COOKIE_CONFIG_TYPE__NAME:
				setName((CookieNameType)newValue);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__DOMAIN:
				setDomain((CookieDomainType)newValue);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__PATH:
				setPath((CookiePathType)newValue);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__COMMENT:
				setComment((CookieCommentType)newValue);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__HTTP_ONLY:
				setHttpOnly((Boolean)newValue);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__SECURE:
				setSecure((Boolean)newValue);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__MAX_AGE:
				setMaxAge((BigInteger)newValue);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__ID:
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
			case WebPackage.COOKIE_CONFIG_TYPE__NAME:
				setName((CookieNameType)null);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__DOMAIN:
				setDomain((CookieDomainType)null);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__PATH:
				setPath((CookiePathType)null);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__COMMENT:
				setComment((CookieCommentType)null);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__HTTP_ONLY:
				unsetHttpOnly();
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__SECURE:
				unsetSecure();
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__MAX_AGE:
				setMaxAge(MAX_AGE_EDEFAULT);
				return;
			case WebPackage.COOKIE_CONFIG_TYPE__ID:
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
			case WebPackage.COOKIE_CONFIG_TYPE__NAME:
				return name != null;
			case WebPackage.COOKIE_CONFIG_TYPE__DOMAIN:
				return domain != null;
			case WebPackage.COOKIE_CONFIG_TYPE__PATH:
				return path != null;
			case WebPackage.COOKIE_CONFIG_TYPE__COMMENT:
				return comment != null;
			case WebPackage.COOKIE_CONFIG_TYPE__HTTP_ONLY:
				return isSetHttpOnly();
			case WebPackage.COOKIE_CONFIG_TYPE__SECURE:
				return isSetSecure();
			case WebPackage.COOKIE_CONFIG_TYPE__MAX_AGE:
				return MAX_AGE_EDEFAULT == null ? maxAge != null : !MAX_AGE_EDEFAULT.equals(maxAge);
			case WebPackage.COOKIE_CONFIG_TYPE__ID:
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
		result.append(" (httpOnly: "); //$NON-NLS-1$
		if (httpOnlyESet) result.append(httpOnly); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", secure: "); //$NON-NLS-1$
		if (secureESet) result.append(secure); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", maxAge: "); //$NON-NLS-1$
		result.append(maxAge);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //CookieConfigTypeImpl
