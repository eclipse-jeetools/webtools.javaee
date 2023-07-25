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
package org.eclipse.jst.javaee.ejb.internal.impl;

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
import org.eclipse.jst.javaee.core.DisplayName;
import org.eclipse.jst.javaee.core.Icon;
import org.eclipse.jst.javaee.ejb.AssemblyDescriptor;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EnterpriseBeans;
import org.eclipse.jst.javaee.ejb.InterceptorsType;
import org.eclipse.jst.javaee.ejb.Relationships;
import org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EJB Jar</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getModuleName <em>Module Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getDescriptions <em>Descriptions</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getDisplayNames <em>Display Names</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getIcons <em>Icons</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getEnterpriseBeans <em>Enterprise Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getInterceptors <em>Interceptors</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getRelationships <em>Relationships</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getAssemblyDescriptor <em>Assembly Descriptor</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getEjbClientJar <em>Ejb Client Jar</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#isMetadataComplete <em>Metadata Complete</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.internal.impl.EJBJarImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EJBJarImpl extends EObjectImpl implements EJBJar {
	/**
	 * The default value of the '{@link #getModuleName() <em>Module Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleName()
	 * @generated
	 * @ordered
	 */
	protected static final String MODULE_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getModuleName() <em>Module Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModuleName()
	 * @generated
	 * @ordered
	 */
	protected String moduleName = MODULE_NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getEnterpriseBeans() <em>Enterprise Beans</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnterpriseBeans()
	 * @generated
	 * @ordered
	 */
	protected EnterpriseBeans enterpriseBeans;

	/**
	 * The cached value of the '{@link #getInterceptors() <em>Interceptors</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterceptors()
	 * @generated
	 * @ordered
	 */
	protected InterceptorsType interceptors;

	/**
	 * The cached value of the '{@link #getRelationships() <em>Relationships</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRelationships()
	 * @generated
	 * @ordered
	 */
	protected Relationships relationships;

	/**
	 * The cached value of the '{@link #getAssemblyDescriptor() <em>Assembly Descriptor</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssemblyDescriptor()
	 * @generated
	 * @ordered
	 */
	protected AssemblyDescriptor assemblyDescriptor;

	/**
	 * The default value of the '{@link #getEjbClientJar() <em>Ejb Client Jar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbClientJar()
	 * @generated
	 * @ordered
	 */
	protected static final String EJB_CLIENT_JAR_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEjbClientJar() <em>Ejb Client Jar</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEjbClientJar()
	 * @generated
	 * @ordered
	 */
	protected String ejbClientJar = EJB_CLIENT_JAR_EDEFAULT;

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
	 * The default value of the '{@link #isMetadataComplete() <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadataComplete()
	 * @generated
	 * @ordered
	 */
	protected static final boolean METADATA_COMPLETE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMetadataComplete() <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMetadataComplete()
	 * @generated
	 * @ordered
	 */
	protected boolean metadataComplete = METADATA_COMPLETE_EDEFAULT;

	/**
	 * This is true if the Metadata Complete attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean metadataCompleteESet;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "3.2"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * This is true if the Version attribute has been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected boolean versionESet;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EJBJarImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EjbPackage.Literals.EJB_JAR;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<Description> getDescriptions() {
		if (descriptions == null) {
			descriptions = new EObjectContainmentEList<Description>(Description.class, this, EjbPackage.EJB_JAR__DESCRIPTIONS);
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
			displayNames = new EObjectContainmentEList<DisplayName>(DisplayName.class, this, EjbPackage.EJB_JAR__DISPLAY_NAMES);
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
			icons = new EObjectContainmentEList<Icon>(Icon.class, this, EjbPackage.EJB_JAR__ICONS);
		}
		return icons;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getModuleName() {
		return moduleName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setModuleName(String newModuleName) {
		String oldModuleName = moduleName;
		moduleName = newModuleName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__MODULE_NAME, oldModuleName, moduleName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EnterpriseBeans getEnterpriseBeans() {
		return enterpriseBeans;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetEnterpriseBeans(EnterpriseBeans newEnterpriseBeans, NotificationChain msgs) {
		EnterpriseBeans oldEnterpriseBeans = enterpriseBeans;
		enterpriseBeans = newEnterpriseBeans;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__ENTERPRISE_BEANS, oldEnterpriseBeans, newEnterpriseBeans);
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
	public void setEnterpriseBeans(EnterpriseBeans newEnterpriseBeans) {
		if (newEnterpriseBeans != enterpriseBeans) {
			NotificationChain msgs = null;
			if (enterpriseBeans != null)
				msgs = ((InternalEObject)enterpriseBeans).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__ENTERPRISE_BEANS, null, msgs);
			if (newEnterpriseBeans != null)
				msgs = ((InternalEObject)newEnterpriseBeans).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__ENTERPRISE_BEANS, null, msgs);
			msgs = basicSetEnterpriseBeans(newEnterpriseBeans, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__ENTERPRISE_BEANS, newEnterpriseBeans, newEnterpriseBeans));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public InterceptorsType getInterceptors() {
		return interceptors;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInterceptors(InterceptorsType newInterceptors, NotificationChain msgs) {
		InterceptorsType oldInterceptors = interceptors;
		interceptors = newInterceptors;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__INTERCEPTORS, oldInterceptors, newInterceptors);
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
	public void setInterceptors(InterceptorsType newInterceptors) {
		if (newInterceptors != interceptors) {
			NotificationChain msgs = null;
			if (interceptors != null)
				msgs = ((InternalEObject)interceptors).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__INTERCEPTORS, null, msgs);
			if (newInterceptors != null)
				msgs = ((InternalEObject)newInterceptors).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__INTERCEPTORS, null, msgs);
			msgs = basicSetInterceptors(newInterceptors, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__INTERCEPTORS, newInterceptors, newInterceptors));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Relationships getRelationships() {
		return relationships;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelationships(Relationships newRelationships, NotificationChain msgs) {
		Relationships oldRelationships = relationships;
		relationships = newRelationships;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__RELATIONSHIPS, oldRelationships, newRelationships);
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
	public void setRelationships(Relationships newRelationships) {
		if (newRelationships != relationships) {
			NotificationChain msgs = null;
			if (relationships != null)
				msgs = ((InternalEObject)relationships).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__RELATIONSHIPS, null, msgs);
			if (newRelationships != null)
				msgs = ((InternalEObject)newRelationships).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__RELATIONSHIPS, null, msgs);
			msgs = basicSetRelationships(newRelationships, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__RELATIONSHIPS, newRelationships, newRelationships));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public AssemblyDescriptor getAssemblyDescriptor() {
		return assemblyDescriptor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssemblyDescriptor(AssemblyDescriptor newAssemblyDescriptor, NotificationChain msgs) {
		AssemblyDescriptor oldAssemblyDescriptor = assemblyDescriptor;
		assemblyDescriptor = newAssemblyDescriptor;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR, oldAssemblyDescriptor, newAssemblyDescriptor);
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
	public void setAssemblyDescriptor(AssemblyDescriptor newAssemblyDescriptor) {
		if (newAssemblyDescriptor != assemblyDescriptor) {
			NotificationChain msgs = null;
			if (assemblyDescriptor != null)
				msgs = ((InternalEObject)assemblyDescriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR, null, msgs);
			if (newAssemblyDescriptor != null)
				msgs = ((InternalEObject)newAssemblyDescriptor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR, null, msgs);
			msgs = basicSetAssemblyDescriptor(newAssemblyDescriptor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR, newAssemblyDescriptor, newAssemblyDescriptor));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getEjbClientJar() {
		return ejbClientJar;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setEjbClientJar(String newEjbClientJar) {
		String oldEjbClientJar = ejbClientJar;
		ejbClientJar = newEjbClientJar;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__EJB_CLIENT_JAR, oldEjbClientJar, ejbClientJar));
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
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isMetadataComplete() {
		return metadataComplete;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setMetadataComplete(boolean newMetadataComplete) {
		boolean oldMetadataComplete = metadataComplete;
		metadataComplete = newMetadataComplete;
		boolean oldMetadataCompleteESet = metadataCompleteESet;
		metadataCompleteESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__METADATA_COMPLETE, oldMetadataComplete, metadataComplete, !oldMetadataCompleteESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetMetadataComplete() {
		boolean oldMetadataComplete = metadataComplete;
		boolean oldMetadataCompleteESet = metadataCompleteESet;
		metadataComplete = METADATA_COMPLETE_EDEFAULT;
		metadataCompleteESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.EJB_JAR__METADATA_COMPLETE, oldMetadataComplete, METADATA_COMPLETE_EDEFAULT, oldMetadataCompleteESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetMetadataComplete() {
		return metadataCompleteESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		boolean oldVersionESet = versionESet;
		versionESet = true;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__VERSION, oldVersion, version, !oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void unsetVersion() {
		String oldVersion = version;
		boolean oldVersionESet = versionESet;
		version = VERSION_EDEFAULT;
		versionESet = false;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.UNSET, EjbPackage.EJB_JAR__VERSION, oldVersion, VERSION_EDEFAULT, oldVersionESet));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean isSetVersion() {
		return versionESet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.EJB_JAR__DESCRIPTIONS:
				return ((InternalEList<?>)getDescriptions()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_JAR__DISPLAY_NAMES:
				return ((InternalEList<?>)getDisplayNames()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_JAR__ICONS:
				return ((InternalEList<?>)getIcons()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				return basicSetEnterpriseBeans(null, msgs);
			case EjbPackage.EJB_JAR__INTERCEPTORS:
				return basicSetInterceptors(null, msgs);
			case EjbPackage.EJB_JAR__RELATIONSHIPS:
				return basicSetRelationships(null, msgs);
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				return basicSetAssemblyDescriptor(null, msgs);
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
			case EjbPackage.EJB_JAR__MODULE_NAME:
				return getModuleName();
			case EjbPackage.EJB_JAR__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.EJB_JAR__DISPLAY_NAMES:
				return getDisplayNames();
			case EjbPackage.EJB_JAR__ICONS:
				return getIcons();
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				return getEnterpriseBeans();
			case EjbPackage.EJB_JAR__INTERCEPTORS:
				return getInterceptors();
			case EjbPackage.EJB_JAR__RELATIONSHIPS:
				return getRelationships();
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				return getAssemblyDescriptor();
			case EjbPackage.EJB_JAR__EJB_CLIENT_JAR:
				return getEjbClientJar();
			case EjbPackage.EJB_JAR__ID:
				return getId();
			case EjbPackage.EJB_JAR__METADATA_COMPLETE:
				return isMetadataComplete();
			case EjbPackage.EJB_JAR__VERSION:
				return getVersion();
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
			case EjbPackage.EJB_JAR__MODULE_NAME:
				setModuleName((String)newValue);
				return;
			case EjbPackage.EJB_JAR__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection<? extends Description>)newValue);
				return;
			case EjbPackage.EJB_JAR__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection<? extends DisplayName>)newValue);
				return;
			case EjbPackage.EJB_JAR__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection<? extends Icon>)newValue);
				return;
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				setEnterpriseBeans((EnterpriseBeans)newValue);
				return;
			case EjbPackage.EJB_JAR__INTERCEPTORS:
				setInterceptors((InterceptorsType)newValue);
				return;
			case EjbPackage.EJB_JAR__RELATIONSHIPS:
				setRelationships((Relationships)newValue);
				return;
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				setAssemblyDescriptor((AssemblyDescriptor)newValue);
				return;
			case EjbPackage.EJB_JAR__EJB_CLIENT_JAR:
				setEjbClientJar((String)newValue);
				return;
			case EjbPackage.EJB_JAR__ID:
				setId((String)newValue);
				return;
			case EjbPackage.EJB_JAR__METADATA_COMPLETE:
				setMetadataComplete((Boolean)newValue);
				return;
			case EjbPackage.EJB_JAR__VERSION:
				setVersion((String)newValue);
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
			case EjbPackage.EJB_JAR__MODULE_NAME:
				setModuleName(MODULE_NAME_EDEFAULT);
				return;
			case EjbPackage.EJB_JAR__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.EJB_JAR__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case EjbPackage.EJB_JAR__ICONS:
				getIcons().clear();
				return;
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				setEnterpriseBeans((EnterpriseBeans)null);
				return;
			case EjbPackage.EJB_JAR__INTERCEPTORS:
				setInterceptors((InterceptorsType)null);
				return;
			case EjbPackage.EJB_JAR__RELATIONSHIPS:
				setRelationships((Relationships)null);
				return;
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				setAssemblyDescriptor((AssemblyDescriptor)null);
				return;
			case EjbPackage.EJB_JAR__EJB_CLIENT_JAR:
				setEjbClientJar(EJB_CLIENT_JAR_EDEFAULT);
				return;
			case EjbPackage.EJB_JAR__ID:
				setId(ID_EDEFAULT);
				return;
			case EjbPackage.EJB_JAR__METADATA_COMPLETE:
				unsetMetadataComplete();
				return;
			case EjbPackage.EJB_JAR__VERSION:
				unsetVersion();
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
			case EjbPackage.EJB_JAR__MODULE_NAME:
				return MODULE_NAME_EDEFAULT == null ? moduleName != null : !MODULE_NAME_EDEFAULT.equals(moduleName);
			case EjbPackage.EJB_JAR__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.EJB_JAR__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case EjbPackage.EJB_JAR__ICONS:
				return icons != null && !icons.isEmpty();
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				return enterpriseBeans != null;
			case EjbPackage.EJB_JAR__INTERCEPTORS:
				return interceptors != null;
			case EjbPackage.EJB_JAR__RELATIONSHIPS:
				return relationships != null;
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				return assemblyDescriptor != null;
			case EjbPackage.EJB_JAR__EJB_CLIENT_JAR:
				return EJB_CLIENT_JAR_EDEFAULT == null ? ejbClientJar != null : !EJB_CLIENT_JAR_EDEFAULT.equals(ejbClientJar);
			case EjbPackage.EJB_JAR__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case EjbPackage.EJB_JAR__METADATA_COMPLETE:
				return isSetMetadataComplete();
			case EjbPackage.EJB_JAR__VERSION:
				return isSetVersion();
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
		result.append(" (moduleName: "); //$NON-NLS-1$
		result.append(moduleName);
		result.append(", ejbClientJar: "); //$NON-NLS-1$
		result.append(ejbClientJar);
		result.append(", id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", metadataComplete: "); //$NON-NLS-1$
		if (metadataCompleteESet) result.append(metadataComplete); else result.append("<unset>"); //$NON-NLS-1$
		result.append(", version: "); //$NON-NLS-1$
		if (versionESet) result.append(version); else result.append("<unset>"); //$NON-NLS-1$
		result.append(')');
		return result.toString();
	}

} //EJBJarImpl