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

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.jst.javaee.jsp.JspConfig;
import org.eclipse.jst.javaee.jsp.JspPropertyGroup;
import org.eclipse.jst.javaee.jsp.TagLib;

import org.eclipse.jst.javaee.jsp.internal.metadata.JspPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Config</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspConfigImpl#getTagLibs <em>Tag Libs</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspConfigImpl#getJspPropertyGroups <em>Jsp Property Groups</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jsp.internal.impl.JspConfigImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JspConfigImpl extends EObjectImpl implements JspConfig {
	/**
	 * The cached value of the '{@link #getTagLibs() <em>Tag Libs</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTagLibs()
	 * @generated
	 * @ordered
	 */
	protected EList<TagLib> tagLibs;

	/**
	 * The cached value of the '{@link #getJspPropertyGroups() <em>Jsp Property Groups</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getJspPropertyGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<JspPropertyGroup> jspPropertyGroups;

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
	protected JspConfigImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return JspPackage.Literals.JSP_CONFIG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<TagLib> getTagLibs() {
		if (tagLibs == null) {
			tagLibs = new EObjectContainmentEList<TagLib>(TagLib.class, this, JspPackage.JSP_CONFIG__TAG_LIBS);
		}
		return tagLibs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<JspPropertyGroup> getJspPropertyGroups() {
		if (jspPropertyGroups == null) {
			jspPropertyGroups = new EObjectContainmentEList<JspPropertyGroup>(JspPropertyGroup.class, this, JspPackage.JSP_CONFIG__JSP_PROPERTY_GROUPS);
		}
		return jspPropertyGroups;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JspPackage.JSP_CONFIG__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JspPackage.JSP_CONFIG__TAG_LIBS:
				return ((InternalEList<?>)getTagLibs()).basicRemove(otherEnd, msgs);
			case JspPackage.JSP_CONFIG__JSP_PROPERTY_GROUPS:
				return ((InternalEList<?>)getJspPropertyGroups()).basicRemove(otherEnd, msgs);
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
			case JspPackage.JSP_CONFIG__TAG_LIBS:
				return getTagLibs();
			case JspPackage.JSP_CONFIG__JSP_PROPERTY_GROUPS:
				return getJspPropertyGroups();
			case JspPackage.JSP_CONFIG__ID:
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
			case JspPackage.JSP_CONFIG__TAG_LIBS:
				getTagLibs().clear();
				getTagLibs().addAll((Collection<? extends TagLib>)newValue);
				return;
			case JspPackage.JSP_CONFIG__JSP_PROPERTY_GROUPS:
				getJspPropertyGroups().clear();
				getJspPropertyGroups().addAll((Collection<? extends JspPropertyGroup>)newValue);
				return;
			case JspPackage.JSP_CONFIG__ID:
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
			case JspPackage.JSP_CONFIG__TAG_LIBS:
				getTagLibs().clear();
				return;
			case JspPackage.JSP_CONFIG__JSP_PROPERTY_GROUPS:
				getJspPropertyGroups().clear();
				return;
			case JspPackage.JSP_CONFIG__ID:
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
			case JspPackage.JSP_CONFIG__TAG_LIBS:
				return tagLibs != null && !tagLibs.isEmpty();
			case JspPackage.JSP_CONFIG__JSP_PROPERTY_GROUPS:
				return jspPropertyGroups != null && !jspPropertyGroups.isEmpty();
			case JspPackage.JSP_CONFIG__ID:
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
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //JspConfigImpl