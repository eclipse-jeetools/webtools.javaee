/**
 * <copyright>
 * </copyright>
 *
 * $Id: SecurityConstraintImpl.java,v 1.1 2007/03/20 18:04:38 jsholl Exp $
 */
package org.eclipse.jst.javaee.web.internal.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.core.DisplayName;

import org.eclipse.jst.javaee.web.AuthConstraint;
import org.eclipse.jst.javaee.web.SecurityConstraint;
import org.eclipse.jst.javaee.web.UserDataConstraint;
import org.eclipse.jst.javaee.web.WebResourceCollection;

import org.eclipse.jst.javaee.web.internal.metadata.WebPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Security Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.SecurityConstraintImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.SecurityConstraintImpl#getWebResourceCollections <em>Web Resource Collections</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.SecurityConstraintImpl#getAuthConstraint <em>Auth Constraint</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.SecurityConstraintImpl#getUserDataConstraint <em>User Data Constraint</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.web.internal.impl.SecurityConstraintImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SecurityConstraintImpl extends EObjectImpl implements SecurityConstraint {
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
	 * The cached value of the '{@link #getWebResourceCollections() <em>Web Resource Collections</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWebResourceCollections()
	 * @generated
	 * @ordered
	 */
	protected EList webResourceCollections = null;

	/**
	 * The cached value of the '{@link #getAuthConstraint() <em>Auth Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAuthConstraint()
	 * @generated
	 * @ordered
	 */
	protected AuthConstraint authConstraint = null;

	/**
	 * The cached value of the '{@link #getUserDataConstraint() <em>User Data Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserDataConstraint()
	 * @generated
	 * @ordered
	 */
	protected UserDataConstraint userDataConstraint = null;

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
	protected SecurityConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return WebPackage.Literals.SECURITY_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDisplayNames() {
		if (displayNames == null) {
			displayNames = new EObjectContainmentEList(DisplayName.class, this, WebPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES);
		}
		return displayNames;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getWebResourceCollections() {
		if (webResourceCollections == null) {
			webResourceCollections = new EObjectContainmentEList(WebResourceCollection.class, this, WebPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS);
		}
		return webResourceCollections;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AuthConstraint getAuthConstraint() {
		return authConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAuthConstraint(AuthConstraint newAuthConstraint, NotificationChain msgs) {
		AuthConstraint oldAuthConstraint = authConstraint;
		authConstraint = newAuthConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT, oldAuthConstraint, newAuthConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAuthConstraint(AuthConstraint newAuthConstraint) {
		if (newAuthConstraint != authConstraint) {
			NotificationChain msgs = null;
			if (authConstraint != null)
				msgs = ((InternalEObject)authConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT, null, msgs);
			if (newAuthConstraint != null)
				msgs = ((InternalEObject)newAuthConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT, null, msgs);
			msgs = basicSetAuthConstraint(newAuthConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT, newAuthConstraint, newAuthConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UserDataConstraint getUserDataConstraint() {
		return userDataConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUserDataConstraint(UserDataConstraint newUserDataConstraint, NotificationChain msgs) {
		UserDataConstraint oldUserDataConstraint = userDataConstraint;
		userDataConstraint = newUserDataConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, WebPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT, oldUserDataConstraint, newUserDataConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUserDataConstraint(UserDataConstraint newUserDataConstraint) {
		if (newUserDataConstraint != userDataConstraint) {
			NotificationChain msgs = null;
			if (userDataConstraint != null)
				msgs = ((InternalEObject)userDataConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - WebPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT, null, msgs);
			if (newUserDataConstraint != null)
				msgs = ((InternalEObject)newUserDataConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - WebPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT, null, msgs);
			msgs = basicSetUserDataConstraint(newUserDataConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT, newUserDataConstraint, newUserDataConstraint));
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
			eNotify(new ENotificationImpl(this, Notification.SET, WebPackage.SECURITY_CONSTRAINT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case WebPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
			case WebPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				return ((InternalEList)getWebResourceCollections()).basicRemove(otherEnd, msgs);
			case WebPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				return basicSetAuthConstraint(null, msgs);
			case WebPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				return basicSetUserDataConstraint(null, msgs);
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
			case WebPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				return getDisplayNames();
			case WebPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				return getWebResourceCollections();
			case WebPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				return getAuthConstraint();
			case WebPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				return getUserDataConstraint();
			case WebPackage.SECURITY_CONSTRAINT__ID:
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
			case WebPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case WebPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				getWebResourceCollections().clear();
				getWebResourceCollections().addAll((Collection)newValue);
				return;
			case WebPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				setAuthConstraint((AuthConstraint)newValue);
				return;
			case WebPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				setUserDataConstraint((UserDataConstraint)newValue);
				return;
			case WebPackage.SECURITY_CONSTRAINT__ID:
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
			case WebPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case WebPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				getWebResourceCollections().clear();
				return;
			case WebPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				setAuthConstraint((AuthConstraint)null);
				return;
			case WebPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				setUserDataConstraint((UserDataConstraint)null);
				return;
			case WebPackage.SECURITY_CONSTRAINT__ID:
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
			case WebPackage.SECURITY_CONSTRAINT__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case WebPackage.SECURITY_CONSTRAINT__WEB_RESOURCE_COLLECTIONS:
				return webResourceCollections != null && !webResourceCollections.isEmpty();
			case WebPackage.SECURITY_CONSTRAINT__AUTH_CONSTRAINT:
				return authConstraint != null;
			case WebPackage.SECURITY_CONSTRAINT__USER_DATA_CONSTRAINT:
				return userDataConstraint != null;
			case WebPackage.SECURITY_CONSTRAINT__ID:
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //SecurityConstraintImpl