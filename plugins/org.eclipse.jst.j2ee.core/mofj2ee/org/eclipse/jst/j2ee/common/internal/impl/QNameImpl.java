/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.common.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.wst.common.internal.emf.utilities.NamespaceAdapter;
import org.eclipse.wst.common.internal.emf.utilities.StringUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>QName</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.QNameImpl#getNamespaceURI <em>Namespace URI</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.QNameImpl#getLocalPart <em>Local Part</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.QNameImpl#getCombinedQName <em>Combined QName</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.common.internal.impl.QNameImpl#getInternalPrefixOrNsURI <em>Internal Prefix Or Ns URI</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class QNameImpl extends EObjectImpl implements QName {
	/**
	 * The default value of the '{@link #getNamespaceURI() <em>Namespace URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamespaceURI()
	 * @generated
	 * @ordered
	 */
	protected static final String NAMESPACE_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNamespaceURI() <em>Namespace URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamespaceURI()
	 * @generated
	 * @ordered
	 */
	protected String namespaceURI = NAMESPACE_URI_EDEFAULT;

	/**
	 * The default value of the '{@link #getLocalPart() <em>Local Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalPart()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCAL_PART_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocalPart() <em>Local Part</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocalPart()
	 * @generated
	 * @ordered
	 */
	protected String localPart = LOCAL_PART_EDEFAULT;

	/**
	 * The default value of the '{@link #getCombinedQName() <em>Combined QName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCombinedQName()
	 * @generated
	 * @ordered
	 */
	protected static final String COMBINED_QNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCombinedQName() <em>Combined QName</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCombinedQName()
	 * @generated
	 * @ordered
	 */
	protected String combinedQName = COMBINED_QNAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getInternalPrefixOrNsURI() <em>Internal Prefix Or Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalPrefixOrNsURI()
	 * @generated
	 * @ordered
	 */
	protected static final String INTERNAL_PREFIX_OR_NS_URI_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getInternalPrefixOrNsURI() <em>Internal Prefix Or Ns URI</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInternalPrefixOrNsURI()
	 * @generated
	 * @ordered
	 */
	protected String internalPrefixOrNsURI = INTERNAL_PREFIX_OR_NS_URI_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected QNameImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getQName();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNamespaceURIGen() {
		return namespaceURI;
	}

	public String getNamespaceURI() {
		if (namespaceURI != null)
			return namespaceURI;
		String resolved = NamespaceAdapter.getResolvedNamespaceURI(internalPrefixOrNsURI, this);
		return resolved == null ? internalPrefixOrNsURI : resolved;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNamespaceURIGen(String newNamespaceURI) {
		String oldNamespaceURI = namespaceURI;
		namespaceURI = newNamespaceURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.QNAME__NAMESPACE_URI, oldNamespaceURI, namespaceURI));
	}

	public void setNamespaceURI(String newNamespaceURI) {
	    String prefix = getInternalPrefixOrNsURI();
	    String oldNsURI = getInternalPrefixOrNsURI();
	    if (!StringUtil.stringsEqual(prefix, oldNsURI))
	        setValues(prefix, newNamespaceURI, getLocalPart());
	    else {
	        setNamespaceURIGen(newNamespaceURI);
	        setInternalPrefixOrNsURI(newNamespaceURI);
	    }
			
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocalPart() {
		return localPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocalPartGen(String newLocalPart) {
		String oldLocalPart = localPart;
		localPart = newLocalPart;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.QNAME__LOCAL_PART, oldLocalPart, localPart));
	}

	public void setLocalPart(String newLocalPart) {
		setLocalPartGen(newLocalPart);
		updateCombine();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCombinedQName() {
		return combinedQName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCombinedQNameGen(String newCombinedQName) {
		String oldCombinedQName = combinedQName;
		combinedQName = newCombinedQName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.QNAME__COMBINED_QNAME, oldCombinedQName, combinedQName));
	}

	public void setCombinedQName(String newCombinedQName) {
		setCombinedQNameGen(newCombinedQName);
		updateParse();
	}

	/**
	 * Parses the combined name into the components
	 */
	private void updateParse() {
		String ns = null;
		String lp = null;
		if (combinedQName != null || combinedQName.length() > 0) {
			int index = combinedQName.lastIndexOf(':');
			
			if (index < 0) { //No separator
				ns = combinedQName;
			} else if (index == 0) { //First char
				lp = combinedQName.substring(1); 
			} else if (index == combinedQName.length()-1) { //Last char
				ns = combinedQName.substring(0, index);
			} else { //In the middle, which is what it should be
				ns = combinedQName.substring(0, index);
				lp = combinedQName.substring(index+1, combinedQName.length());
			}
		}
		setInternalPrefixOrNsURIGen(ns);
		setLocalPartGen(lp);
	}
		
	/**
	 * updates the combined name from the components
	 */
	private void updateCombine() {
		String cn = null;
		if (internalPrefixOrNsURI != null || localPart != null) {
			String ns = internalPrefixOrNsURI == null ? "" : internalPrefixOrNsURI; //$NON-NLS-1$
			String lp = localPart == null ? "" : localPart; //$NON-NLS-1$
			cn = ns+':'+lp;
		}
		setCombinedQNameGen(cn);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getInternalPrefixOrNsURI() {
		return internalPrefixOrNsURI;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInternalPrefixOrNsURIGen(String newInternalPrefixOrNsURI) {
		String oldInternalPrefixOrNsURI = internalPrefixOrNsURI;
		internalPrefixOrNsURI = newInternalPrefixOrNsURI;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI, oldInternalPrefixOrNsURI, internalPrefixOrNsURI));
	}

	public void setInternalPrefixOrNsURI(String newInternalPrefixOrNsURI) {
		setInternalPrefixOrNsURIGen(newInternalPrefixOrNsURI);
		updateCombine();
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.QNAME__NAMESPACE_URI:
				return getNamespaceURI();
			case CommonPackage.QNAME__LOCAL_PART:
				return getLocalPart();
			case CommonPackage.QNAME__COMBINED_QNAME:
				return getCombinedQName();
			case CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI:
				return getInternalPrefixOrNsURI();
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
			case CommonPackage.QNAME__NAMESPACE_URI:
				setNamespaceURI((String)newValue);
				return;
			case CommonPackage.QNAME__LOCAL_PART:
				setLocalPart((String)newValue);
				return;
			case CommonPackage.QNAME__COMBINED_QNAME:
				setCombinedQName((String)newValue);
				return;
			case CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI:
				setInternalPrefixOrNsURI((String)newValue);
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
			case CommonPackage.QNAME__NAMESPACE_URI:
				setNamespaceURI(NAMESPACE_URI_EDEFAULT);
				return;
			case CommonPackage.QNAME__LOCAL_PART:
				setLocalPart(LOCAL_PART_EDEFAULT);
				return;
			case CommonPackage.QNAME__COMBINED_QNAME:
				setCombinedQName(COMBINED_QNAME_EDEFAULT);
				return;
			case CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI:
				setInternalPrefixOrNsURI(INTERNAL_PREFIX_OR_NS_URI_EDEFAULT);
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * eIsSet for namespace uri must be derived if null so namespaceURI ! = null and internalPrefixOrNsURI != null must both be check.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.QNAME__NAMESPACE_URI:
				return NAMESPACE_URI_EDEFAULT == null ? (namespaceURI != null || internalPrefixOrNsURI != null) : !NAMESPACE_URI_EDEFAULT.equals(namespaceURI);
			case CommonPackage.QNAME__LOCAL_PART:
				return LOCAL_PART_EDEFAULT == null ? localPart != null : !LOCAL_PART_EDEFAULT.equals(localPart);
			case CommonPackage.QNAME__COMBINED_QNAME:
				return COMBINED_QNAME_EDEFAULT == null ? combinedQName != null : !COMBINED_QNAME_EDEFAULT.equals(combinedQName);
			case CommonPackage.QNAME__INTERNAL_PREFIX_OR_NS_URI:
				return INTERNAL_PREFIX_OR_NS_URI_EDEFAULT == null ? internalPrefixOrNsURI != null : !INTERNAL_PREFIX_OR_NS_URI_EDEFAULT.equals(internalPrefixOrNsURI);
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
		result.append(" (namespaceURI: ");
		result.append(namespaceURI);
		result.append(", localPart: ");
		result.append(localPart);
		result.append(", combinedQName: ");
		result.append(combinedQName);
		result.append(", internalPrefixOrNsURI: ");
		result.append(internalPrefixOrNsURI);
		result.append(')');
		return result.toString();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.common.QName#setValues(java.lang.String, java.lang.String, java.lang.String)
	 */
	public void setValues(String prefix, String nsURI, String localPart) {
		setInternalPrefixOrNsURIGen(prefix);
		setLocalPartGen(localPart);
		String existingURI = NamespaceAdapter.getNamespaceURIAtThisLevel(prefix, this);
		boolean removed = false;
		if (existingURI != null && !existingURI.equals(nsURI)) {
			NamespaceAdapter.removeNamespace(prefix, this);
			removed = true;
		} 
		if (existingURI == null || removed)
			NamespaceAdapter.addNamespace(prefix, nsURI, this);
		updateCombine();
	}


} //QNameImpl
