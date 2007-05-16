/**
 * <copyright>
 * </copyright>
 *
 * $Id: EnvEntryImpl.java,v 1.1 2007/05/16 06:42:35 cbridgha Exp $
 */
package org.eclipse.jst.javaee.core.internal.impl;

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

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.EnvEntry;
import org.eclipse.jst.javaee.core.EnvEntryType;
import org.eclipse.jst.javaee.core.InjectionTarget;

import org.eclipse.jst.javaee.core.internal.metadata.JavaeePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Env Entry</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EnvEntryImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EnvEntryImpl#getEnvEntryName <em>Env Entry Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EnvEntryImpl#getEnvEntryType <em>Env Entry Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EnvEntryImpl#getEnvEntryValue <em>Env Entry Value</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EnvEntryImpl#getMappedName <em>Mapped Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EnvEntryImpl#getInjectionTargets <em>Injection Targets</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.core.internal.impl.EnvEntryImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EnvEntryImpl extends EObjectImpl implements EnvEntry {
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
	 * The default value of the '{@link #getEnvEntryName() <em>Env Entry Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntryName()
	 * @generated
	 * @ordered
	 */
	protected static final String ENV_ENTRY_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnvEntryName() <em>Env Entry Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntryName()
	 * @generated
	 * @ordered
	 */
	protected String envEntryName = ENV_ENTRY_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEnvEntryType() <em>Env Entry Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntryType()
	 * @generated
	 * @ordered
	 */
	protected static final EnvEntryType ENV_ENTRY_TYPE_EDEFAULT = EnvEntryType.JAVA_LANG_BOOLEAN_LITERAL;

	/**
	 * The cached value of the '{@link #getEnvEntryType() <em>Env Entry Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntryType()
	 * @generated
	 * @ordered
	 */
	protected EnvEntryType envEntryType = ENV_ENTRY_TYPE_EDEFAULT;

	/**
	 * This is true if the Env Entry Type attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean envEntryTypeESet = false;

	/**
	 * The default value of the '{@link #getEnvEntryValue() <em>Env Entry Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntryValue()
	 * @generated
	 * @ordered
	 */
	protected static final String ENV_ENTRY_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnvEntryValue() <em>Env Entry Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnvEntryValue()
	 * @generated
	 * @ordered
	 */
	protected String envEntryValue = ENV_ENTRY_VALUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected static final String MAPPED_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getMappedName() <em>Mapped Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappedName()
	 * @generated
	 * @ordered
	 */
	protected String mappedName = MAPPED_NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getInjectionTargets() <em>Injection Targets</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInjectionTargets()
	 * @generated
	 * @ordered
	 */
	protected EList injectionTargets = null;

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
	protected EnvEntryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return JavaeePackage.Literals.ENV_ENTRY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList(Description.class, this, JavaeePackage.ENV_ENTRY__DESCRIPTIONS);
		}
		return descriptions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEnvEntryName() {
		return envEntryName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnvEntryName(String newEnvEntryName) {
		String oldEnvEntryName = envEntryName;
		envEntryName = newEnvEntryName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.ENV_ENTRY__ENV_ENTRY_NAME, oldEnvEntryName, envEntryName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnvEntryType getEnvEntryType() {
		return envEntryType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnvEntryType(EnvEntryType newEnvEntryType) {
		EnvEntryType oldEnvEntryType = envEntryType;
		envEntryType = newEnvEntryType == null ? ENV_ENTRY_TYPE_EDEFAULT : newEnvEntryType;
		boolean oldEnvEntryTypeESet = envEntryTypeESet;
		envEntryTypeESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.ENV_ENTRY__ENV_ENTRY_TYPE, oldEnvEntryType, envEntryType, !oldEnvEntryTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void unsetEnvEntryType() {
		EnvEntryType oldEnvEntryType = envEntryType;
		boolean oldEnvEntryTypeESet = envEntryTypeESet;
		envEntryType = ENV_ENTRY_TYPE_EDEFAULT;
		envEntryTypeESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, JavaeePackage.ENV_ENTRY__ENV_ENTRY_TYPE, oldEnvEntryType, ENV_ENTRY_TYPE_EDEFAULT, oldEnvEntryTypeESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSetEnvEntryType() {
		return envEntryTypeESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getEnvEntryValue() {
		return envEntryValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnvEntryValue(String newEnvEntryValue) {
		String oldEnvEntryValue = envEntryValue;
		envEntryValue = newEnvEntryValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.ENV_ENTRY__ENV_ENTRY_VALUE, oldEnvEntryValue, envEntryValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getMappedName() {
		return mappedName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMappedName(String newMappedName) {
		String oldMappedName = mappedName;
		mappedName = newMappedName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.ENV_ENTRY__MAPPED_NAME, oldMappedName, mappedName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List getInjectionTargets() {
		if (injectionTargets == null) {
			injectionTargets = new EObjectContainmentEList(InjectionTarget.class, this, JavaeePackage.ENV_ENTRY__INJECTION_TARGETS);
		}
		return injectionTargets;
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
			eNotify(new ENotificationImpl(this, Notification.SET, JavaeePackage.ENV_ENTRY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case JavaeePackage.ENV_ENTRY__DESCRIPTIONS:
				return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
			case JavaeePackage.ENV_ENTRY__INJECTION_TARGETS:
				return ((InternalEList)getInjectionTargets()).basicRemove(otherEnd, msgs);
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
			case JavaeePackage.ENV_ENTRY__DESCRIPTIONS:
				return getDescriptions();
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_NAME:
				return getEnvEntryName();
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_TYPE:
				return getEnvEntryType();
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_VALUE:
				return getEnvEntryValue();
			case JavaeePackage.ENV_ENTRY__MAPPED_NAME:
				return getMappedName();
			case JavaeePackage.ENV_ENTRY__INJECTION_TARGETS:
				return getInjectionTargets();
			case JavaeePackage.ENV_ENTRY__ID:
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
			case JavaeePackage.ENV_ENTRY__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_NAME:
				setEnvEntryName((String)newValue);
				return;
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_TYPE:
				setEnvEntryType((EnvEntryType)newValue);
				return;
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_VALUE:
				setEnvEntryValue((String)newValue);
				return;
			case JavaeePackage.ENV_ENTRY__MAPPED_NAME:
				setMappedName((String)newValue);
				return;
			case JavaeePackage.ENV_ENTRY__INJECTION_TARGETS:
				getInjectionTargets().clear();
				getInjectionTargets().addAll((Collection)newValue);
				return;
			case JavaeePackage.ENV_ENTRY__ID:
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
			case JavaeePackage.ENV_ENTRY__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_NAME:
				setEnvEntryName(ENV_ENTRY_NAME_EDEFAULT);
				return;
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_TYPE:
				unsetEnvEntryType();
				return;
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_VALUE:
				setEnvEntryValue(ENV_ENTRY_VALUE_EDEFAULT);
				return;
			case JavaeePackage.ENV_ENTRY__MAPPED_NAME:
				setMappedName(MAPPED_NAME_EDEFAULT);
				return;
			case JavaeePackage.ENV_ENTRY__INJECTION_TARGETS:
				getInjectionTargets().clear();
				return;
			case JavaeePackage.ENV_ENTRY__ID:
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
			case JavaeePackage.ENV_ENTRY__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_NAME:
				return ENV_ENTRY_NAME_EDEFAULT == null ? envEntryName != null : !ENV_ENTRY_NAME_EDEFAULT.equals(envEntryName);
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_TYPE:
				return isSetEnvEntryType();
			case JavaeePackage.ENV_ENTRY__ENV_ENTRY_VALUE:
				return ENV_ENTRY_VALUE_EDEFAULT == null ? envEntryValue != null : !ENV_ENTRY_VALUE_EDEFAULT.equals(envEntryValue);
			case JavaeePackage.ENV_ENTRY__MAPPED_NAME:
				return MAPPED_NAME_EDEFAULT == null ? mappedName != null : !MAPPED_NAME_EDEFAULT.equals(mappedName);
			case JavaeePackage.ENV_ENTRY__INJECTION_TARGETS:
				return injectionTargets != null && !injectionTargets.isEmpty();
			case JavaeePackage.ENV_ENTRY__ID:
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
		result.append(" (envEntryName: "); //$NON-NLS-1$
		result.append(envEntryName);
		result.append(", envEntryType: "); //$NON-NLS-1$
		if (envEntryTypeESet) result.append(envEntryType); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", envEntryValue: "); //$NON-NLS-1$
		result.append(envEntryValue);
		result.append(", mappedName: "); //$NON-NLS-1$
		result.append(mappedName);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //EnvEntryImpl