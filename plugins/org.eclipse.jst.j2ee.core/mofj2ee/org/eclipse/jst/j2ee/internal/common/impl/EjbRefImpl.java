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
package org.eclipse.jst.j2ee.internal.common.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.EjbRefType;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;

/**
 * The ejb-ref element is used for the declaration of a reference to an enterprise bean's home. The declaration consists of an optional description; the EJB reference name used in the code of the referencing application client; the expected type of the referenced enterprise bean; the expected home and remote interfaces of the referenced enterprise bean; and an optional ejb-link information. The optional  ejb-link element is used to specify the referenced enterprise bean.
 */
public class EjbRefImpl extends EObjectImpl implements EjbRef, EObject{

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String name = NAME_EDEFAULT;
	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final EjbRefType TYPE_EDEFAULT = EjbRefType.SESSION_LITERAL;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EjbRefType type = TYPE_EDEFAULT;
	/**
	 * This is true if the Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean typeESet = false;

	/**
	 * The default value of the '{@link #getHome() <em>Home</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHome()
	 * @generated
	 * @ordered
	 */
	protected static final String HOME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String home = HOME_EDEFAULT;
	/**
	 * The default value of the '{@link #getRemote() <em>Remote</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRemote()
	 * @generated
	 * @ordered
	 */
	protected static final String REMOTE_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String remote = REMOTE_EDEFAULT;
	/**
	 * The default value of the '{@link #getLink() <em>Link</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected static final String LINK_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String link = LINK_EDEFAULT;
	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String description = DESCRIPTION_EDEFAULT;
	/**
	 * The cached value of the '{@link #getDescriptions() <em>Descriptions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescriptions()
	 * @generated
	 * @ordered
	 */
	protected EList descriptions = null;

	public EjbRefImpl() {
		super();
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return CommonPackage.eINSTANCE.getEjbRef();
	}

/**
 * Return an enterprise bean referenced by the EjbRef, if one exists, within the scope of the parameter.
 * The ejb-link value of the ref must equate to a named enterprise bean contained in the jar; otherwise 
 * return null
 */
public EnterpriseBean getLinkedEjb(EJBJar ejbJar) {
	if (getLink() == null) {
		return null;
	}
	return ejbJar.getEnterpiseBeanFromRef(this);
}/**
 * Return an enterprise bean referenced by the EjbRef, if one exists, within the scope of the parameter.
 * The ejb-link value of the ref must equate to a named enterprise bean contained in the jar; otherwise 
 * return null
 */  
public boolean isEquivalent(EjbRef anEjbRef) {
	if (anEjbRef == null) return false;
	boolean equiv = true;
	equiv = getName() == anEjbRef.getName() || (getName() != null && getName().equals(anEjbRef.getName()));
	if (equiv)
		equiv = getHome() == anEjbRef.getHome() || (getHome() != null && getHome().equals(anEjbRef.getHome()));
	if (equiv)
		equiv = getRemote() == anEjbRef.getRemote() || (getRemote() != null && getRemote().equals(anEjbRef.getRemote()));
	if (equiv)
		equiv = getLink() == anEjbRef.getLink() || (getLink() != null && getLink().equals(anEjbRef.getLink()));
	return equiv;
}
public boolean isLinked(EnterpriseBean anEJB) {
	if (getLink() == null)
		return false;
	return getLink().equals(anEJB.getName());
}
/**
 * Return true if the ejbref is for a local ejb interface.
 */
public boolean isLocal(){
	return false;
}
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The ejb-ref-name element contains the name of an EJB reference. This is the JNDI name that the servlet code uses to get a reference to the enterprise bean.
	 * Example: <ejb-ref-name>ejb//Payroll<//ejb-ref-name>
	 */
	public String getName() {
		return name;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.EJB_REF__NAME, oldName, name));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The ejb-ref-type element contains the expected type of the referenced enterprise bean. The ejb-ref-type element must be one of the following:
	 * <ejb-ref-type>Entity<//ejb-ref-type>
	 * <ejb-ref-type>Session<//ejb-ref-type>
	 */
	public EjbRefType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(EjbRefType newType) {
		EjbRefType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		boolean oldTypeESet = typeESet;
		typeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.EJB_REF__TYPE, oldType, type, !oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetType() {
		EjbRefType oldType = type;
		boolean oldTypeESet = typeESet;
		type = TYPE_EDEFAULT;
		typeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, CommonPackage.EJB_REF__TYPE, oldType, TYPE_EDEFAULT, oldTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetType() {
		return typeESet;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The home element contains the fully-qualified name of the enterprise bean's home interface.
	 * Example: <home>com.aardvark.payroll.PayrollHome<//home>
	 */
	public String getHome() {
		return home;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setHome(String newHome) {
		String oldHome = home;
		home = newHome;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.EJB_REF__HOME, oldHome, home));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The remote element contains the fully-qualified name of the enterprise bean's remote interface.
	 */
	public String getRemote() {
		return remote;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setRemote(String newRemote) {
		String oldRemote = remote;
		remote = newRemote;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.EJB_REF__REMOTE, oldRemote, remote));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The ejb-link element is used in the ejb-ref element to specify that an EJB reference is linked to an enterprise bean in the encompassing J2EE Application package. The value of the ejb-link element must be the ejb-name of an enterprise bean in the same J2EE Application package. 
	 * Example: <ejb-link>EmployeeRecord<//ejb-link>
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setLink(String newLink) {
		String oldLink = link;
		link = newLink;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.EJB_REF__LINK, oldLink, link));
	}

	/**
	 * @generated This field/method will be replaced during code generation The
	 *            description element is used by the ejb-jar file producer to
	 *            provide text describing the parent element. The description
	 *            element should include any information that the ejb-jar file
	 *            producer wants to provide to the consumer of the ejb-jar file
	 *            (i.e. to the Deployer). Typically, the tools used by the
	 *            ejb-jar file consumer will display the description when
	 *            processing the parent element.
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (getDescriptions() != null && !getDescriptions().isEmpty()) {
			Description d = (Description) getDescriptions().get(0);
			if (d != null)
				d.setValue(newDescription);
		}
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.EJB_REF__DESCRIPTION, oldDescription, description));
	}

	public String getDescription() {
		if (getDescriptions() != null && !getDescriptions().isEmpty()) {
			Description d = (Description) getDescriptions().get(0);
			if (d != null)
				description = d.getValue();
		}
		return description;
	}


	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, CommonPackage.EJB_REF__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case CommonPackage.EJB_REF__DESCRIPTIONS:
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
			case CommonPackage.EJB_REF__NAME:
				return getName();
			case CommonPackage.EJB_REF__TYPE:
				return getType();
			case CommonPackage.EJB_REF__HOME:
				return getHome();
			case CommonPackage.EJB_REF__REMOTE:
				return getRemote();
			case CommonPackage.EJB_REF__LINK:
				return getLink();
			case CommonPackage.EJB_REF__DESCRIPTION:
				return getDescription();
			case CommonPackage.EJB_REF__DESCRIPTIONS:
				return getDescriptions();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.EJB_REF__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CommonPackage.EJB_REF__TYPE:
				return isSetType();
			case CommonPackage.EJB_REF__HOME:
				return HOME_EDEFAULT == null ? home != null : !HOME_EDEFAULT.equals(home);
			case CommonPackage.EJB_REF__REMOTE:
				return REMOTE_EDEFAULT == null ? remote != null : !REMOTE_EDEFAULT.equals(remote);
			case CommonPackage.EJB_REF__LINK:
				return LINK_EDEFAULT == null ? link != null : !LINK_EDEFAULT.equals(link);
			case CommonPackage.EJB_REF__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case CommonPackage.EJB_REF__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.EJB_REF__NAME:
				setName((String)newValue);
				return;
			case CommonPackage.EJB_REF__TYPE:
				setType((EjbRefType)newValue);
				return;
			case CommonPackage.EJB_REF__HOME:
				setHome((String)newValue);
				return;
			case CommonPackage.EJB_REF__REMOTE:
				setRemote((String)newValue);
				return;
			case CommonPackage.EJB_REF__LINK:
				setLink((String)newValue);
				return;
			case CommonPackage.EJB_REF__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case CommonPackage.EJB_REF__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case CommonPackage.EJB_REF__NAME:
				setName(NAME_EDEFAULT);
				return;
			case CommonPackage.EJB_REF__TYPE:
				unsetType();
				return;
			case CommonPackage.EJB_REF__HOME:
				setHome(HOME_EDEFAULT);
				return;
			case CommonPackage.EJB_REF__REMOTE:
				setRemote(REMOTE_EDEFAULT);
				return;
			case CommonPackage.EJB_REF__LINK:
				setLink(LINK_EDEFAULT);
				return;
			case CommonPackage.EJB_REF__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case CommonPackage.EJB_REF__DESCRIPTIONS:
				getDescriptions().clear();
				return;
		}
		eDynamicUnset(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: "); //$NON-NLS-1$
		result.append(name);
		result.append(", type: "); //$NON-NLS-1$
		if (typeESet) result.append(type); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", home: "); //$NON-NLS-1$
		result.append(home);
		result.append(", remote: "); //$NON-NLS-1$
		result.append(remote);
		result.append(", link: "); //$NON-NLS-1$
		result.append(link);
		result.append(", description: "); //$NON-NLS-1$
		result.append(description);
		result.append(')');
		return result.toString();
	}

}





