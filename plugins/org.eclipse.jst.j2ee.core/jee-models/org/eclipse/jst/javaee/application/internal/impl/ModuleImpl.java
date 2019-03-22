/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.application.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.internal.ICommonEMFModule;
import org.eclipse.jst.javaee.application.Module;
import org.eclipse.jst.javaee.application.Web;
import org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage;
import org.eclipse.jst.jee.application.ICommonModule;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ModuleImpl#getConnector <em>Connector</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ModuleImpl#getEjb <em>Ejb</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ModuleImpl#getJava <em>Java</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ModuleImpl#getWeb <em>Web</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ModuleImpl#getAltDd <em>Alt Dd</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.internal.impl.ModuleImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ModuleImpl extends EObjectImpl implements Module, ICommonModule, ICommonEMFModule {
	/**
	 * The default value of the '{@link #getConnector() <em>Connector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnector()
	 * @generated
	 * @ordered
	 */
	protected static final String CONNECTOR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getConnector() <em>Connector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConnector()
	 * @generated
	 * @ordered
	 */
	protected String connector = CONNECTOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getEjb() <em>Ejb</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjb()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjb() <em>Ejb</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjb()
	 * @generated
	 * @ordered
	 */
	protected String ejb = EJB_EDEFAULT;

	/**
	 * The default value of the '{@link #getJava() <em>Java</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJava()
	 * @generated
	 * @ordered
	 */
	protected static final String JAVA_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getJava() <em>Java</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJava()
	 * @generated
	 * @ordered
	 */
	protected String java = JAVA_EDEFAULT;

	/**
	 * The cached value of the '{@link #getWeb() <em>Web</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWeb()
	 * @generated
	 * @ordered
	 */
	protected Web web;

	/**
	 * The default value of the '{@link #getAltDd() <em>Alt Dd</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAltDd()
	 * @generated
	 * @ordered
	 */
	protected static final String ALT_DD_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAltDd() <em>Alt Dd</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAltDd()
	 * @generated
	 * @ordered
	 */
	protected String altDd = ALT_DD_EDEFAULT;

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
	protected ModuleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ApplicationPackage.Literals.MODULE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getConnector() {
		return connector;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setConnector(String newConnector) {
		String oldConnector = connector;
		connector = newConnector;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__CONNECTOR, oldConnector, connector));
	}
	// Returns the uri of the given module type
	public String getUri() {
		if (eIsSet(ApplicationPackage.MODULE__EJB))
			return getEjb();
		else if (eIsSet(ApplicationPackage.MODULE__CONNECTOR))
			return getConnector();
		else if (eIsSet(ApplicationPackage.MODULE__JAVA))
			return getJava();
		else if (eIsSet(ApplicationPackage.MODULE__WEB))
			return getWeb().getWebUri();
		return null;
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEjb() {
		return ejb;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEjb(String newEjb) {
		String oldEjb = ejb;
		ejb = newEjb;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__EJB, oldEjb, ejb));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getJava() {
		return java;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setJava(String newJava) {
		String oldJava = java;
		java = newJava;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__JAVA, oldJava, java));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Web getWeb() {
		return web;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWeb(Web newWeb, NotificationChain msgs) {
		Web oldWeb = web;
		web = newWeb;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__WEB, oldWeb, newWeb);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWeb(Web newWeb) {
		if (newWeb != web) {
			NotificationChain msgs = null;
			if (web != null)
				msgs = ((InternalEObject)web).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ApplicationPackage.MODULE__WEB, null, msgs);
			if (newWeb != null)
				msgs = ((InternalEObject)newWeb).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ApplicationPackage.MODULE__WEB, null, msgs);
			msgs = basicSetWeb(newWeb, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__WEB, newWeb, newWeb));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getAltDd() {
		return altDd;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAltDd(String newAltDd) {
		String oldAltDd = altDd;
		altDd = newAltDd;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__ALT_DD, oldAltDd, altDd));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ApplicationPackage.MODULE__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ApplicationPackage.MODULE__WEB:
				return basicSetWeb(null, msgs);
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
			case ApplicationPackage.MODULE__CONNECTOR:
				return getConnector();
			case ApplicationPackage.MODULE__EJB:
				return getEjb();
			case ApplicationPackage.MODULE__JAVA:
				return getJava();
			case ApplicationPackage.MODULE__WEB:
				return getWeb();
			case ApplicationPackage.MODULE__ALT_DD:
				return getAltDd();
			case ApplicationPackage.MODULE__ID:
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
			case ApplicationPackage.MODULE__CONNECTOR:
				setConnector((String)newValue);
				return;
			case ApplicationPackage.MODULE__EJB:
				setEjb((String)newValue);
				return;
			case ApplicationPackage.MODULE__JAVA:
				setJava((String)newValue);
				return;
			case ApplicationPackage.MODULE__WEB:
				setWeb((Web)newValue);
				return;
			case ApplicationPackage.MODULE__ALT_DD:
				setAltDd((String)newValue);
				return;
			case ApplicationPackage.MODULE__ID:
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
			case ApplicationPackage.MODULE__CONNECTOR:
				setConnector(CONNECTOR_EDEFAULT);
				return;
			case ApplicationPackage.MODULE__EJB:
				setEjb(EJB_EDEFAULT);
				return;
			case ApplicationPackage.MODULE__JAVA:
				setJava(JAVA_EDEFAULT);
				return;
			case ApplicationPackage.MODULE__WEB:
				setWeb((Web)null);
				return;
			case ApplicationPackage.MODULE__ALT_DD:
				setAltDd(ALT_DD_EDEFAULT);
				return;
			case ApplicationPackage.MODULE__ID:
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
			case ApplicationPackage.MODULE__CONNECTOR:
				return CONNECTOR_EDEFAULT == null ? connector != null : !CONNECTOR_EDEFAULT.equals(connector);
			case ApplicationPackage.MODULE__EJB:
				return EJB_EDEFAULT == null ? ejb != null : !EJB_EDEFAULT.equals(ejb);
			case ApplicationPackage.MODULE__JAVA:
				return JAVA_EDEFAULT == null ? java != null : !JAVA_EDEFAULT.equals(java);
			case ApplicationPackage.MODULE__WEB:
				return web != null;
			case ApplicationPackage.MODULE__ALT_DD:
				return ALT_DD_EDEFAULT == null ? altDd != null : !ALT_DD_EDEFAULT.equals(altDd);
			case ApplicationPackage.MODULE__ID:
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
		result.append(" (connector: "); //$NON-NLS-1$
		result.append(connector);
		result.append(", ejb: "); //$NON-NLS-1$
		result.append(ejb);
		result.append(", java: "); //$NON-NLS-1$
		result.append(java);
		result.append(", altDd: "); //$NON-NLS-1$
		result.append(altDd);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

	public void setUri(String value) {
	}
	public boolean isConnectorModule() {
		if (eIsSet(ApplicationPackage.MODULE__CONNECTOR))
			return true;
		return false;
	}
	/* Returns false by default
	 *
	 */
	public boolean isEjbModule() {
		if (eIsSet(ApplicationPackage.MODULE__EJB) )
			return true;
		return false;
	}
	/* 
	 * Returns false by default
	 */
	public boolean isJavaModule() {
		if (eIsSet(ApplicationPackage.MODULE__JAVA))
			return true;
		return false;
	}
	/* 
	 * Returns false by default
	 */
	public boolean isWebModule() {
		if (eIsSet(ApplicationPackage.MODULE__WEB))
			return true;
		return false;
	}	

} //ModuleImpl