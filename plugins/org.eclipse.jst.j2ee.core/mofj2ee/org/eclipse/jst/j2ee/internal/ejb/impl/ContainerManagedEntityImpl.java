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
package org.eclipse.jst.j2ee.internal.ejb.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.Field;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.common.J2EEVersionResource;
import org.eclipse.jst.j2ee.common.SecurityIdentity;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CMRField;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBExtensionFilter;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EjbFactory;
import org.eclipse.jst.j2ee.ejb.MethodElementKind;
import org.eclipse.jst.j2ee.ejb.Query;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;
import org.eclipse.jst.j2ee.internal.ejb.util.CMPKeySynchronizationAdapter;


/**
 * @generated
 */
public class ContainerManagedEntityImpl extends EntityImpl implements ContainerManagedEntity {

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * 
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "1.x"; //$NON-NLS-1$

	protected EClassifier fGeneratedIdType = null;
	protected static final String PERS_FEATURE_CHNG_NOTIFIER = "PersistentFeatureChangeNotificationAdapter";	 //$NON-NLS-1$
	protected static final String QUERY_METHOD_NAME = "QueryMethod"; //$NON-NLS-1$
	protected static final String FIND_BY_PRIM_KEY_METHOD_NAME = "findByPrimaryKey"; //$NON-NLS-1$
	
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String version = VERSION_EDEFAULT;
	/**
	 * The default value of the '{@link #getAbstractSchemaName() <em>Abstract Schema Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAbstractSchemaName()
	 * @generated
	 * @ordered
	 */
	protected static final String ABSTRACT_SCHEMA_NAME_EDEFAULT = null;

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String abstractSchemaName = ABSTRACT_SCHEMA_NAME_EDEFAULT;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList persistentAttributes = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList keyAttributes = null;
	/**
	 * The cached value of the '{@link #getQueries() <em>Queries</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getQueries()
	 * @generated
	 * @ordered
	 */
	protected EList queries = null;

	/**
	 * The cached value of the '{@link #getCMPAttribute() <em>CMP Attribute</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCMPAttribute()
	 * @generated
	 * @ordered
	 */
	protected EList cmpAttribute = null;

	/**
	 * The cached value of the '{@link #getPrimKeyField() <em>Prim Key Field</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimKeyField()
	 * @generated
	 * @ordered
	 */
	protected CMPAttribute primKeyField = null;

	/**
	 * @generated
	 */
	protected ContainerManagedEntityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EClass eStaticClass() {
		return EjbPackage.eINSTANCE.getContainerManagedEntity();
	}

/**
 * Create a new instance of AttributeExtn with aName and add it to the keyFeatures.
 * Creation date: (8/2/2000 9:11:13 PM)
 * @param aName java.lang.String
 * @return CMPAttribute
 */
public CMPAttribute addKeyAttributeName(String aName) {
	CMPAttribute attribute = getPersistentAttribute(aName);
	if (attribute != null) {
		List keys = getKeyAttributes();
		CMPAttribute key;
		for (int i = 0; i < keys.size(); i++) {
			key = (CMPAttribute) keys.get(i);
			if (aName.equals(key.getName()))
				return attribute;
		}
		keys.add(attribute);
	}
	return attribute;
}
/**
 * Return a List of one of the properties using aFilter.
*/
public List getFilteredFeatures(EJBExtensionFilter aFilter) {
		return aFilter.filter(this);
	}
/**
 * Add a persistent attribute with the same name as the name for aField.
 * Creation date: (8/3/2000 8:30:42 AM)
 * @param aField org.eclipse.jem.internal.java.Field
 * @return CMPAttribute
 */
public CMPAttribute addPersistentAttribute(Field aField) {
	if (aField == null)
		return null;
	return addPersistentAttributeName(aField.getName());
}
/**
 * Create a new instance of AttributeExtn with aName and add it to the persistentFeatures.
 * Creation date: (8/2/2000 9:11:13 PM)
 * @param aName java.lang.String
 * @return CMPAttribute
 */
public CMPAttribute addPersistentAttributeName(String aName) {
	CMPAttribute attribute = getPersistentAttribute(aName);
	if (attribute == null) {
		attribute = createPersistentAttribute(aName);
		getPersistentAttributes().add(attribute);
	}
	return attribute;
}
protected CMPAttribute createPersistentAttribute(String aName) {
	CMPAttribute attribute = ((EjbPackage)EPackage.Registry.INSTANCE.getEPackage(EjbPackage.eNS_URI)).getEjbFactory().createCMPAttribute();
	attribute.setName(aName);
	return attribute;
}
public List getAllAvailableQueryMethods() {
	Set sigs = new HashSet();
	List javaMethods = getAvailableSelectQueryJavaMethods();
	sigs.addAll(getMethodElementSignatures(javaMethods));
	javaMethods = getAvailableLocalQueryJavaMethods();
	sigs.addAll(getMethodElementSignatures(javaMethods));
	javaMethods = getAvailableRemoteQueryJavaMethods();
	sigs.addAll(getMethodElementSignatures(javaMethods));
	List signatures = new ArrayList(sigs);
	return createQueryMethods(signatures);
}
protected List createQueryMethods(List signatures) {
	Collections.sort(signatures);
	Enumerator enumerator = MethodElementKind.UNSPECIFIED_LITERAL;
	return createMethodElements(QUERY_METHOD_NAME, signatures, enumerator);
}

public List getAvailableSelectQueryMethods() {
	List methods = getAvailableSelectQueryJavaMethods();
	List sigs = getMethodElementSignatures(methods);
	return createQueryMethods(sigs);
}
protected List getAvailableSelectQueryJavaMethods() {
	JavaClass bean = getEjbClass();
	if (bean == null)
		return Collections.EMPTY_LIST;
	return bean.getOnlySpecificMethods("ejbSelect", Collections.EMPTY_LIST);	 //$NON-NLS-1$
}

public List getAvailableFindQueryMethods() {
	Set sigs = new HashSet();
	List javaMethods = getAvailableRemoteQueryJavaMethods();
	sigs.addAll(getMethodElementSignatures(javaMethods));
	javaMethods = getAvailableLocalQueryJavaMethods();
	sigs.addAll(getMethodElementSignatures(javaMethods));
	List signatures = new ArrayList(sigs);
	return createQueryMethods(signatures);
}
protected List getAvailableLocalQueryJavaMethods() {
	return getAvailableQueryJavaMethods(getLocalHomeInterface());
}
/**
* This returns the CMP version id by using the module id and the <cmp-version>. 
* Compare with J2EEVersionConstants to determine CMP level
* This will help differentiate between 2.0,2.1,...   rather than "2.x"
*/
public int getCMPVersionID() throws IllegalStateException {
	J2EEVersionResource res = (J2EEVersionResource) eResource();
	/* If resource is null we will return based on CMP version....*/
	if (res == null) {
		if (getVersion().equals(ContainerManagedEntity.VERSION_1_X))
			return J2EEVersionConstants.EJB_1_1_ID;
		return J2EEVersionConstants.EJB_2_0_ID;
	}
	int cmpVersion;
	switch (res.getModuleVersionID()) {
		case J2EEVersionConstants.EJB_2_0_ID :
			if (getVersion().equalsIgnoreCase(VERSION_2_X))
				cmpVersion = J2EEVersionConstants.EJB_2_0_ID;
			else
				cmpVersion = J2EEVersionConstants.EJB_1_1_ID;
			break;
		case J2EEVersionConstants.EJB_2_1_ID :
					if (getVersion().equalsIgnoreCase(VERSION_2_X))
						cmpVersion = J2EEVersionConstants.EJB_2_1_ID;
					else
						cmpVersion = J2EEVersionConstants.EJB_1_1_ID;
					break;

		default :
			cmpVersion = res.getModuleVersionID();
	}
	return cmpVersion;
}

/* (non-Javadoc)
 * @see org.eclipse.jst.j2ee.internal.ejb.EnterpriseBean#getVersionID()
 */
public int getVersionID() {
	return getCMPVersionID();
}protected List getAvailableRemoteQueryJavaMethods() {
	return getAvailableQueryJavaMethods(getHomeInterface());
}

protected List getAvailableQueryJavaMethods(JavaClass home) {
	if (home == null) return Collections.EMPTY_LIST;
	List excluded = new ArrayList(1);
	excluded.add("findByPrimaryKey"); //$NON-NLS-1$
	return home.getOnlySpecificMethods("find", excluded); //$NON-NLS-1$
}

protected EClassifier getGeneratedIdType() {
	if (fGeneratedIdType == null) {
		fGeneratedIdType = JavaRefFactory.eINSTANCE.reflectType(JAVA_LANG_STRING, this);
	}
	return fGeneratedIdType;
}
/**
 * Return the keyAttribute with aName.
 * Creation date: (8/8/2000 9:25:27 PM)
 * @return EStructuralFeature
 * @param aName java.lang.String
 */
public CMPAttribute getKeyAttribute(String aName) {
	if (aName != null) {
		Iterator it = getKeyAttributes().iterator();
		CMPAttribute next;
		while (it.hasNext()) {
			next = (CMPAttribute) it.next();
			if (aName.equals(next.getName()))
				return next;
		}
	}
	return null;
}
/**
 * Return the persistentAttribute with aName.
 * Creation date: (8/8/2000 9:25:27 PM)
 * @return EStructuralFeature
 * @param aName java.lang.String
 */
public CMPAttribute getPersistentAttribute(String aName) {
	if (aName != null) {
		Iterator it = getPersistentAttributes().iterator();
		CMPAttribute next;
		while (it.hasNext()) {
			next = (CMPAttribute) it.next();
			if (aName.equals(next.getName()))
				return next;
		}
	}
	return null;
}

/**
 * If a primary key exists, return the persistent attribute; the primary key is defined as one and only one
 * key attribute, whose type is the same as the key class
 * @deprecated use getPrimKeyField()
 */
public CMPAttribute getPrimaryKeyAttribute() {
	return getPrimKeyField();
}


/**
 * isContainerManagedEntity method comment.
 */
public boolean isContainerManagedEntity() {
	return true;
}
public boolean isKeyAttribute(CMPAttribute anAttribute) {
	java.util.List attributes = getKeyAttributes();
	for (int i = 0; i < attributes.size(); i++){
		if (attributes.get(i) == anAttribute)
			return true;
	}
	return false;
}
/*
 * Return whether this bean represents an instance of the "unknown primary key" case
 * described in the specification.  This is identified by the primary key class
 * being specified as "java.lang.Object".
 * In order to deploy an unknown primary key bean, we must substitute a real key,
 * and add a corresponding field.
 */
public boolean isUnknownPrimaryKey() {
	String keyName = getPrimaryKeyName();
	if (keyName == null) return false;
	//test whether it is unknown or we have the WAS generated key for the unknown prim key
	boolean genTest = keyName.equals(UNKNOWN_PRIMARY_KEY) || keyName.equals(WAS_GENERATED_STRING_KEY);
	if (genTest) return true;
	//Lastly, test for the findByPrimaryKey(Object) method on the home.
	JavaClass home = getHomeInterface();
	if (home == null)
		home = getLocalHomeInterface();
	if (home == null) return false;
	List parmType = new ArrayList(1);
    parmType.add(UNKNOWN_PRIMARY_KEY);
   	return home.getMethodExtended(FIND_BY_PRIM_KEY_METHOD_NAME, parmType) != null;
}
/**
 * isVersion1_X method comment.
 */
public boolean isVersion1_X() {
	return getVersion().equals(ContainerManagedEntity.VERSION_1_X);
}
/**
 * isVersion2_X method comment.
 */
public boolean isVersion2_X() {
	return getVersion().equals(ContainerManagedEntity.VERSION_2_X);
}
/* 
 * listPersistentFieldNames - return the names of our CMP fields. derived from persistentFeatures
 */
public String[] listPersistentFieldNames() {
	java.util.List attributes = getPersistentAttributes();
	String[] names = new String[attributes.size()];
	for (int i = 0; i < attributes.size(); i++) {
		names[i] = ((CMPAttribute) attributes.get(i)).getName();
	}
	return names;
}

/**
 * Remove the persistentAttribute with the same name as the Field's name.
 * Creation date: (8/3/2000 8:33:01 AM)
 * @param aField org.eclipse.jem.internal.java.Field
 */
public CMPAttribute removePersistentAttribute(Field aField) {
	if (aField != null)
		return removePersistentAttribute(aField.getName());
	return null;
}
/**
 * Remove the persistentAttribute named aName.
 * Creation date: (8/2/2000 8:59:32 PM)
 * @param aName java.lang.String
 */
public CMPAttribute removePersistentAttribute(String aName) {
	if (aName != null) {
		Iterator it = getPersistentAttributes().iterator();
		CMPAttribute next;
		while (it.hasNext()) {
			next = (CMPAttribute) it.next();
			if (aName.equals(next.getName())) {
				getPersistentAttributes().remove(next);
				return next;
			}
		}
	}
	return null;
}
/**
	 * @generated This field/method will be replaced during code generation 
	 * Specifies the version of an entity bean with container-managed persistence.
	 * 
	 * @invariant The cmp-version element must be one of the two following: 1.x , 2.x
	 * @default 2.x
	 * Returns "1.x" if in a 1.1 Jar (For compatibility)
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CONTAINER_MANAGED_ENTITY__VERSION, oldVersion, version));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The abstract-schema-name element specifies the name of the abstract schema type of an entity bean with cmp-version 2.x. It is used in EJB QL queries.
	 * 
	 * For example, the abstract-schema-name for an entity bean whose local interface is com.acme.commerce.Order might be Order.
	 * 

	 */
	public String getAbstractSchemaName() {
		return abstractSchemaName;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void setAbstractSchemaName(String newAbstractSchemaName) {
		String oldAbstractSchemaName = abstractSchemaName;
		abstractSchemaName = newAbstractSchemaName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CONTAINER_MANAGED_ENTITY__ABSTRACT_SCHEMA_NAME, oldAbstractSchemaName, abstractSchemaName));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getPersistentAttributes() {
		if (persistentAttributes == null) {
			persistentAttributes = new EObjectContainmentEList(CMPAttribute.class, this, EjbPackage.CONTAINER_MANAGED_ENTITY__PERSISTENT_ATTRIBUTES);
		}
		return persistentAttributes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_JAR:
					if (eContainer != null)
						msgs = eBasicRemoveFromContainer(msgs);
					return eBasicSetContainer(otherEnd, EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_JAR, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES:
					return ((InternalEList)getQueries()).basicAdd(otherEnd, msgs);
				default:
					return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
			}
		}
		if (eContainer != null)
			msgs = eBasicRemoveFromContainer(msgs);
		return eBasicSetContainer(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs) {
		if (featureID >= 0) {
			switch (eDerivedStructuralFeatureID(featureID, baseClass)) {
				case EjbPackage.CONTAINER_MANAGED_ENTITY__ICONS:
					return ((InternalEList)getIcons()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__DISPLAY_NAMES:
					return ((InternalEList)getDisplayNames()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__DESCRIPTIONS:
					return ((InternalEList)getDescriptions()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__ENVIRONMENT_PROPERTIES:
					return ((InternalEList)getEnvironmentProperties()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_REFS:
					return ((InternalEList)getResourceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_REFS:
					return ((InternalEList)getEjbRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_ENV_REFS:
					return ((InternalEList)getResourceEnvRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_LOCAL_REFS:
					return ((InternalEList)getEjbLocalRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__MESSAGE_DESTINATION_REFS:
					return ((InternalEList)getMessageDestinationRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__SERVICE_REFS:
					return ((InternalEList)getServiceRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_ROLE_REFS:
					return ((InternalEList)getSecurityRoleRefs()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_JAR:
					return eBasicSetContainer(null, EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_JAR, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_IDENTITY:
					return basicSetSecurityIdentity(null, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__PERSISTENT_ATTRIBUTES:
					return ((InternalEList)getPersistentAttributes()).basicRemove(otherEnd, msgs);
				case EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES:
					return ((InternalEList)getQueries()).basicRemove(otherEnd, msgs);
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
	public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs) {
		if (eContainerFeatureID >= 0) {
			switch (eContainerFeatureID) {
				case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_JAR:
					return eContainer.eInverseRemove(this, EjbPackage.EJB_JAR__ENTERPRISE_BEANS, EJBJar.class, msgs);
				default:
					return eDynamicBasicRemoveFromContainer(msgs);
			}
		}
		return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object eGet(EStructuralFeature eFeature, boolean resolve) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ICONS:
				return getIcons();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DISPLAY_NAMES:
				return getDisplayNames();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DESCRIPTIONS:
				return getDescriptions();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SMALL_ICON:
				return getSmallIcon();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LARGE_ICON:
				return getLargeIcon();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DESCRIPTION:
				return getDescription();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DISPLAY_NAME:
				return getDisplayName();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ENVIRONMENT_PROPERTIES:
				return getEnvironmentProperties();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_REFS:
				return getResourceRefs();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_REFS:
				return getEjbRefs();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_ENV_REFS:
				return getResourceEnvRefs();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_LOCAL_REFS:
				return getEjbLocalRefs();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__MESSAGE_DESTINATION_REFS:
				return getMessageDestinationRefs();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SERVICE_REFS:
				return getServiceRefs();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__NAME:
				return getName();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_ROLE_REFS:
				return getSecurityRoleRefs();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_CLASS:
				if (resolve) return getEjbClass();
				return basicGetEjbClass();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__HOME_INTERFACE:
				if (resolve) return getHomeInterface();
				return basicGetHomeInterface();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__REMOTE_INTERFACE:
				if (resolve) return getRemoteInterface();
				return basicGetRemoteInterface();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_JAR:
				return getEjbJar();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_IDENTITY:
				return getSecurityIdentity();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LOCAL_HOME_INTERFACE:
				if (resolve) return getLocalHomeInterface();
				return basicGetLocalHomeInterface();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LOCAL_INTERFACE:
				if (resolve) return getLocalInterface();
				return basicGetLocalInterface();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__REENTRANT:
				return isReentrant() ? Boolean.TRUE : Boolean.FALSE;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIMARY_KEY:
				if (resolve) return getPrimaryKey();
				return basicGetPrimaryKey();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__VERSION:
				return getVersion();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ABSTRACT_SCHEMA_NAME:
				return getAbstractSchemaName();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PERSISTENT_ATTRIBUTES:
				return getPersistentAttributes();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__KEY_ATTRIBUTES:
				return getKeyAttributes();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES:
				return getQueries();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__CMP_ATTRIBUTE:
				return getCMPAttribute();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIM_KEY_FIELD:
				if (resolve) return getPrimKeyField();
				return basicGetPrimKeyField();
		}
		return eDynamicGet(eFeature, resolve);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public boolean eIsSet(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ICONS:
				return icons != null && !icons.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DISPLAY_NAMES:
				return displayNames != null && !displayNames.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DESCRIPTIONS:
				return descriptions != null && !descriptions.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SMALL_ICON:
				return SMALL_ICON_EDEFAULT == null ? smallIcon != null : !SMALL_ICON_EDEFAULT.equals(smallIcon);
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LARGE_ICON:
				return LARGE_ICON_EDEFAULT == null ? largeIcon != null : !LARGE_ICON_EDEFAULT.equals(largeIcon);
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DISPLAY_NAME:
				return DISPLAY_NAME_EDEFAULT == null ? displayName != null : !DISPLAY_NAME_EDEFAULT.equals(displayName);
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ENVIRONMENT_PROPERTIES:
				return environmentProperties != null && !environmentProperties.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_REFS:
				return resourceRefs != null && !resourceRefs.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_REFS:
				return ejbRefs != null && !ejbRefs.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_ENV_REFS:
				return resourceEnvRefs != null && !resourceEnvRefs.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_LOCAL_REFS:
				return ejbLocalRefs != null && !ejbLocalRefs.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__MESSAGE_DESTINATION_REFS:
				return messageDestinationRefs != null && !messageDestinationRefs.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SERVICE_REFS:
				return serviceRefs != null && !serviceRefs.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_ROLE_REFS:
				return securityRoleRefs != null && !securityRoleRefs.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_CLASS:
				return ejbClass != null;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__HOME_INTERFACE:
				return homeInterface != null;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__REMOTE_INTERFACE:
				return remoteInterface != null;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_JAR:
				return getEjbJar() != null;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_IDENTITY:
				return securityIdentity != null;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LOCAL_HOME_INTERFACE:
				return localHomeInterface != null;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LOCAL_INTERFACE:
				return localInterface != null;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__REENTRANT:
				return isSetReentrant();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIMARY_KEY:
				return primaryKey != null;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ABSTRACT_SCHEMA_NAME:
				return ABSTRACT_SCHEMA_NAME_EDEFAULT == null ? abstractSchemaName != null : !ABSTRACT_SCHEMA_NAME_EDEFAULT.equals(abstractSchemaName);
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PERSISTENT_ATTRIBUTES:
				return persistentAttributes != null && !persistentAttributes.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__KEY_ATTRIBUTES:
				return keyAttributes != null && !keyAttributes.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES:
				return queries != null && !queries.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__CMP_ATTRIBUTE:
				return cmpAttribute != null && !cmpAttribute.isEmpty();
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIM_KEY_FIELD:
				return primKeyField != null;
		}
		return eDynamicIsSet(eFeature);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eSet(EStructuralFeature eFeature, Object newValue) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ICONS:
				getIcons().clear();
				getIcons().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DISPLAY_NAMES:
				getDisplayNames().clear();
				getDisplayNames().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DESCRIPTIONS:
				getDescriptions().clear();
				getDescriptions().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SMALL_ICON:
				setSmallIcon((String)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LARGE_ICON:
				setLargeIcon((String)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DISPLAY_NAME:
				setDisplayName((String)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				getEnvironmentProperties().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_REFS:
				getResourceRefs().clear();
				getResourceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_REFS:
				getEjbRefs().clear();
				getEjbRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				getResourceEnvRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				getEjbLocalRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				getMessageDestinationRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SERVICE_REFS:
				getServiceRefs().clear();
				getServiceRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__NAME:
				setName((String)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				getSecurityRoleRefs().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_CLASS:
				setEjbClass((JavaClass)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__HOME_INTERFACE:
				setHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_JAR:
				setEjbJar((EJBJar)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__REENTRANT:
				setReentrant(((Boolean)newValue).booleanValue());
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIMARY_KEY:
				setPrimaryKey((JavaClass)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__VERSION:
				setVersion((String)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ABSTRACT_SCHEMA_NAME:
				setAbstractSchemaName((String)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PERSISTENT_ATTRIBUTES:
				getPersistentAttributes().clear();
				getPersistentAttributes().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__KEY_ATTRIBUTES:
				getKeyAttributes().clear();
				getKeyAttributes().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES:
				getQueries().clear();
				getQueries().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__CMP_ATTRIBUTE:
				getCMPAttribute().clear();
				getCMPAttribute().addAll((Collection)newValue);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIM_KEY_FIELD:
				setPrimKeyField((CMPAttribute)newValue);
				return;
		}
		eDynamicSet(eFeature, newValue);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	public void eUnset(EStructuralFeature eFeature) {
		switch (eDerivedStructuralFeatureID(eFeature)) {
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ICONS:
				getIcons().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DISPLAY_NAMES:
				getDisplayNames().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DESCRIPTIONS:
				getDescriptions().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SMALL_ICON:
				setSmallIcon(SMALL_ICON_EDEFAULT);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LARGE_ICON:
				setLargeIcon(LARGE_ICON_EDEFAULT);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__DISPLAY_NAME:
				setDisplayName(DISPLAY_NAME_EDEFAULT);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ENVIRONMENT_PROPERTIES:
				getEnvironmentProperties().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_REFS:
				getResourceRefs().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_REFS:
				getEjbRefs().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__RESOURCE_ENV_REFS:
				getResourceEnvRefs().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_LOCAL_REFS:
				getEjbLocalRefs().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__MESSAGE_DESTINATION_REFS:
				getMessageDestinationRefs().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SERVICE_REFS:
				getServiceRefs().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_ROLE_REFS:
				getSecurityRoleRefs().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_CLASS:
				setEjbClass((JavaClass)null);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__HOME_INTERFACE:
				setHomeInterface((JavaClass)null);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__REMOTE_INTERFACE:
				setRemoteInterface((JavaClass)null);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__EJB_JAR:
				setEjbJar((EJBJar)null);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__SECURITY_IDENTITY:
				setSecurityIdentity((SecurityIdentity)null);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LOCAL_HOME_INTERFACE:
				setLocalHomeInterface((JavaClass)null);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__LOCAL_INTERFACE:
				setLocalInterface((JavaClass)null);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__REENTRANT:
				unsetReentrant();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIMARY_KEY:
				setPrimaryKey((JavaClass)null);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__ABSTRACT_SCHEMA_NAME:
				setAbstractSchemaName(ABSTRACT_SCHEMA_NAME_EDEFAULT);
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PERSISTENT_ATTRIBUTES:
				getPersistentAttributes().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__KEY_ATTRIBUTES:
				getKeyAttributes().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES:
				getQueries().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__CMP_ATTRIBUTE:
				getCMPAttribute().clear();
				return;
			case EjbPackage.CONTAINER_MANAGED_ENTITY__PRIM_KEY_FIELD:
				setPrimKeyField((CMPAttribute)null);
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
		result.append(" (version: "); //$NON-NLS-1$
		result.append(version);
		result.append(", abstractSchemaName: "); //$NON-NLS-1$
		result.append(abstractSchemaName);
		result.append(')');
		return result.toString();
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 */
	public EList getKeyAttributesGen() {	    
		if (keyAttributes == null) {
			keyAttributes = new EObjectResolvingEList(CMPAttribute.class, this, EjbPackage.CONTAINER_MANAGED_ENTITY__KEY_ATTRIBUTES);			
		}
		return keyAttributes;
	}
	
	public EList getKeyAttributes() {
	    if(keyAttributes == null) {
		    CMPKeySynchronizationAdapter syncAdapter = (CMPKeySynchronizationAdapter)  EcoreUtil.getExistingAdapter(this, CMPKeySynchronizationAdapter.ADAPTER_TYPE);
			if(syncAdapter != null) 
			    syncAdapter.initializeKeyAttributes(); 
	    }
		return getKeyAttributesGen();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getQueries() {
		if (queries == null) {
			queries = new EObjectContainmentWithInverseEList(Query.class, this, EjbPackage.CONTAINER_MANAGED_ENTITY__QUERIES, EjbPackage.QUERY__ENTITY);
		}
		return queries;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList getCMPAttribute() {
		if (cmpAttribute == null) {
			cmpAttribute = new EObjectResolvingEList(CMPAttribute.class, this, EjbPackage.CONTAINER_MANAGED_ENTITY__CMP_ATTRIBUTE);
		}
		return cmpAttribute;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CMPAttribute getPrimKeyField() { 	    
	    
		if (primKeyField != null && primKeyField.eIsProxy()) {
			CMPAttribute oldPrimKeyField = primKeyField;
			primKeyField = (CMPAttribute)eResolveProxy((InternalEObject)primKeyField);
			if (primKeyField != oldPrimKeyField) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EjbPackage.CONTAINER_MANAGED_ENTITY__PRIM_KEY_FIELD, oldPrimKeyField, primKeyField));
			}
		}
		return primKeyField;
	}

	public void makePrimKeyFieldProxy() {
		if (primKeyField == null) return;
		String fieldName = primKeyField.getName();
		if (fieldName == null) fieldName = ""; //$NON-NLS-1$
		primKeyField = EjbFactory.eINSTANCE.createCMPAttribute();
		((InternalEObject)primKeyField).eSetProxyURI(URI.createURI(fieldName));
		
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CMPAttribute basicGetPrimKeyField() {
		return primKeyField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPrimKeyField(CMPAttribute newPrimKeyField) {
		CMPAttribute oldPrimKeyField = primKeyField;
		primKeyField = newPrimKeyField;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.CONTAINER_MANAGED_ENTITY__PRIM_KEY_FIELD, oldPrimKeyField, primKeyField));
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.ejb.ContainerManagedEntity#getCMRFields()
	 */
	public List getCMRFields() {
		List myRoles = getRoles();
		List cmrFields = Collections.EMPTY_LIST;
		if (myRoles != null && !myRoles.isEmpty()) {
			cmrFields = new ArrayList();
			Iterator i = myRoles.iterator();
			while (i.hasNext()) {
				EJBRelationshipRole role = (EJBRelationshipRole) i.next();
				CMRField field = role.getCmrField();
				if (field != null)
					cmrFields.add(field);
			}
		}
		return cmrFields;
	}

	public boolean isSelfReferencing(EJBRelation rel) {
		if (rel.getSecondRole() == null)
			return false;
		return (rel.getFirstRole().getSourceEntity() == this) &&
			(rel.getSecondRole().getSourceEntity() == this);
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.ejb.ContainerManagedEntity#getRoles()
	 */
	public List getRoles() {
		if (getEjbJar() == null)
			return Collections.EMPTY_LIST;
		List allRoles = getEjbJar().getEjbRelations();
		List myRoles = Collections.EMPTY_LIST;
		if (allRoles != null && !allRoles.isEmpty()) {
			myRoles = new ArrayList();
			Iterator i = allRoles.iterator();
			while (i.hasNext()) {
				EJBRelation rel = (EJBRelation) i.next();
				EJBRelationshipRole role = rel.getFirstRole();
				if (isSelfReferencing(rel)) {
					myRoles.add(rel.getFirstRole());
					myRoles.add(rel.getSecondRole());
				}
				else if ((role != null) && role.getSourceEntity() == this) {
					myRoles.add(role);
				} else {
					role = rel.getSecondRole();
					if ((role != null) && (role.getSourceEntity() == this))
						myRoles.add(role);
				}
			}
		}
		return myRoles;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.j2ee.internal.ejb.ContainerManagedEntity#getRole(java.lang.String)
	 */
	public CommonRelationshipRole getRole(String roleName) {
		List roles = getRoles();
		for (int i=0; i<roles.size(); i++) {
			CommonRelationshipRole role = (CommonRelationshipRole)roles.get(i);
			if (role.getName().equals(roleName))
				return role;
		}
		return null;
	}

}






