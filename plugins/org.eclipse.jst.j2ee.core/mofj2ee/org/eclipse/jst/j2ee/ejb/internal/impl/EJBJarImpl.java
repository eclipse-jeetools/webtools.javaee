/*******************************************************************************
 * Copyright (c) 2001, 2025 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.internal.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.internal.impl.CompatibilityDescriptionGroupImpl;
import org.eclipse.jst.j2ee.common.internal.util.CommonUtil;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EJBRelation;
import org.eclipse.jst.j2ee.ejb.EJBRelationshipRole;
import org.eclipse.jst.j2ee.ejb.EJBResource;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.Entity;
import org.eclipse.jst.j2ee.ejb.Relationships;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionResource;

/**
 * The root element of the EJB deployment descriptor. It contains an optional description of the ejb-jar file; optional display name; optional small icon file name; optional large icon file
 * name; mandatory structural information about all included enterprise beans; a descriptor for container managed relationships, if any; an optional application-assembly descriptor; and an optional name of an ejb-client-jar file for the ejb-jar.
 */
public class EJBJarImpl extends CompatibilityDescriptionGroupImpl implements EJBJar {

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
	 * @generated This field/method will be replaced during code generation.
	 */
	protected String ejbClientJar = EJB_CLIENT_JAR_EDEFAULT;
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

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
	 * @generated This field/method will be replaced during code generation.
	 */
	protected AssemblyDescriptor assemblyDescriptor = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected EList enterpriseBeans = null;
	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	protected Relationships relationshipList = null;
	public EJBJarImpl() {
		super();
//		setRefId(com.ibm.etools.archive.ArchiveConstants.EJBJAR_ID);
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
 * Return true if there are any ContainerManagedEntity beans in this jar.
 */
@Override
public boolean containsContainerManagedBeans() {
	Iterator it = getEnterpriseBeans().iterator();
	EnterpriseBean ejb;
	while (it.hasNext()) {
		ejb = (EnterpriseBean)it.next();
		if (ejb.isEntity() && ((Entity)ejb).isContainerManagedEntity())
			return true;
	}
	return false;
}
/**
 * @see org.eclipse.jst.j2ee.internal.ejb.EJBJar
 */
@Override
public boolean containsSecurityRole(java.lang.String name) {
	AssemblyDescriptor ad = getAssemblyDescriptor();
	return (ad != null) && (ad.getSecurityRoleNamed(name) != null);
}
/**
 * Return List of BMP beans in this jar.
 * @return java.util.List
 */
@Override
public List getBeanManagedBeans() {
	List allBeans, beans;
	allBeans = getEnterpriseBeans();
	int size = allBeans.size();
	beans = new ArrayList(size);
	EnterpriseBean ejb;
	for (int i = 0; i < size; i++) {
		ejb = (EnterpriseBean) allBeans.get(i);
		if (ejb.isBeanManagedEntity())
			beans.add(ejb);
	}
	return beans;
}
/**
 * Return List of ContainerManagedEntity beans in this jar.
 * @return java.util.List
 */
@Override
public List getContainerManagedBeans() {
	List cmps = new ArrayList(getEnterpriseBeans().size());
	Iterator it = getEnterpriseBeans().iterator();
	EnterpriseBean ejb;
	while (it.hasNext()) {
		ejb = (EnterpriseBean)it.next();
		if (ejb.isEntity() && ((Entity)ejb).isContainerManagedEntity())
			cmps.add(ejb);
	}
	return cmps;
}

/**
 * Return List of EJB 1.1 ContainerManagedEntity beans in this jar.
 * @return java.util.List
 */
@Override
public List getEJB11ContainerManagedBeans() {
	List cmps = new ArrayList(getEnterpriseBeans().size());
	Iterator it = getEnterpriseBeans().iterator();
	EnterpriseBean ejb;
	while (it.hasNext()) {
		ejb = (EnterpriseBean)it.next();
		if (ejb.isEntity() && ((Entity)ejb).isContainerManagedEntity() && ejb.getVersionID() <= J2EEVersionConstants.EJB_1_1_ID)
			cmps.add(ejb);
	}
	return cmps;
}

/**
 * Return List of EJB 2.0 ContainerManagedEntity beans in this jar.
 * @return java.util.List
 */
@Override
public List getEJB20ContainerManagedBeans() {
	List cmps = new ArrayList(getEnterpriseBeans().size());
	Iterator it = getEnterpriseBeans().iterator();
	EnterpriseBean ejb;
	while (it.hasNext()) {
		ejb = (EnterpriseBean)it.next();
		if (ejb.isEntity() && ((Entity)ejb).isContainerManagedEntity() && ejb.getVersionID() >= J2EEVersionConstants.EJB_2_0_ID)
			cmps.add(ejb);
	}
	return cmps;
}
	/*
	 * @see EJBJar#getMessageDrivenBeans()
	 */
	@Override
	public List getMessageDrivenBeans() {
		List ejbs = getEnterpriseBeans();
		int size = ejbs.size();
		List mdbs = new ArrayList(size);
		EnterpriseBean ejb;
		for (int i = 0; i < size; i++) {
			ejb = (EnterpriseBean)ejbs.get(i);
			if (ejb.isMessageDriven())
				mdbs.add(ejb);
		}
		return mdbs;
	}
/**
 * @return The list of EjbRelations references
 * A list of ejb-relation elements, which specify the container managed relationships.
 */
@Override
public List getEjbRelations() {
	return getRelationshipList() == null ? null : getRelationshipList().getEjbRelations();
}
/**
 * Return an enterprise bean referenced by the EjbRef, if one exists.  The ejb-link value
 * of the ref must equate to a named enterprise bean contained in the jar; otherwise return
 * null
 */
@Override
public EnterpriseBean getEnterpiseBeanFromRef(EjbRef ref) {
	String link = ref.getLink();
	if (link == null) {
		return null;
	}
	return getEnterpriseBeanNamed(link);
}
@Override
public EnterpriseBean getEnterpriseBeanNamed(String ejbName) {
	if (ejbName == null)
		return null;
	EList beans = getEnterpriseBeans();
	EnterpriseBean bean;
	for (int i = 0; i < beans.size(); i++) {
		bean = (EnterpriseBean) beans.get(i);
		if (ejbName.equals(bean.getName()))
			return bean;
	}
	return null;
}
/**
 * Return ALL EnterpriseBean(s) that are referencing @aJavaClass as a
 * home, remote, bean class, or key class.
 */
@Override
public java.util.List getEnterpriseBeansWithReference(JavaClass aJavaClass) {
	List beans = getEnterpriseBeans();
	List result = new ArrayList();
	EnterpriseBean bean = null;
	for (int i = 0; i < beans.size(); i++){
		bean = (EnterpriseBean) beans.get(i);
		if (bean.hasJavaReference(aJavaClass))
			result.add(bean);
	}
	return result;
}
/**
 * Return the *FIRST* EnterpriseBean that is referencing @aJavaClass as its
 * home, remote, bean class, or key class.
 */
@Override
public EnterpriseBean getEnterpriseBeanWithReference(JavaClass aJavaClass) {
	List beans = getEnterpriseBeans();
	EnterpriseBean bean = null;
	for (int i = 0; i < beans.size(); i++){
		bean = (EnterpriseBean) beans.get(i);
		if (bean.hasJavaReference(aJavaClass))
			return bean;
	}
	return null;
}
/**
 * Return List of Session beans in this jar.
 * @return java.util.List
 */
@Override
public List getSessionBeans() {
	List allBeans, beans;
	allBeans = getEnterpriseBeans();
	int size = allBeans.size();
	beans = new ArrayList(size);
	EnterpriseBean ejb;
	for (int i = 0; i < size; i++) {
		ejb = (EnterpriseBean) allBeans.get(i);
		if (ejb.isSession())
			beans.add(ejb);
	}
	return beans;
}
/**
 * Return boolean indicating if this EJB JAR was populated from an EJB 1.1 descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
@Override
public boolean isVersion1_1Descriptor() {
	CommonUtil.checkDDObjectForVersion(this);
	EJBResource ejbRes = (EJBResource) eResource();
	return ejbRes.isEJB1_1();
}
/**
 * Return boolean indicating if this EJB JAR was populated from an EJB 2.0 descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
@Override
public boolean isVersion2_0Descriptor()  {
	CommonUtil.checkDDObjectForVersion(this);
	EJBResource ejbRes = (EJBResource) eResource();
	return ejbRes.isEJB2_0();
}
/**
 * @see org.eclipse.jst.j2ee.internal.ejb.EJBJar
 */
@Override
public void renameSecurityRole(java.lang.String existingRoleName, java.lang.String newRoleName) {
	AssemblyDescriptor ad = getAssemblyDescriptor();
	if (ad != null)
		ad.renameSecurityRole(existingRoleName, newRoleName);
	List ejbs = getEnterpriseBeans();
	for (int i = 0; i < ejbs.size(); i++) {
		EnterpriseBean ejb = (EnterpriseBean) ejbs.get(i);
		ejb.reSyncSecurityRoleRef(existingRoleName, newRoleName);
	}
}
	/**
	 *
	 */
	@Override
	public void setDisplayName(String newDisplayName) {
		super.setDisplayName(newDisplayName);
	}
	
	/**
	 * @generated This field/method will be replaced during code generation 
	 * The optional ejb-client-jar element specifies a JAR file that contains the class files necessary for a client program to access the enterprise beans in the ejb-jar file. The Deployer should make the ejb-client JAR file accessible to the client's class-loader.  Example:<ejb-client-jar>employee_service_client.jar<//ejb-client-jar>

	 */
	@Override
	public String getEjbClientJar() {
		return ejbClientJar;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
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
	public String getVersion() {
		return version;
	}

	/**
	This returns the module version id.  Compare with J2EEVersionConstants to determine module level
	*/
	@Override
	public int getVersionID() throws IllegalStateException {
		J2EEVersionResource ejbRes = (J2EEVersionResource) eResource();
		if (ejbRes == null) {
			// fix defect 3276, when resource is unloaded
			if (version == null) 
				throw new IllegalStateException();
			if (version.equals("2.1")) //$NON-NLS-1$
				return J2EEVersionConstants.EJB_2_1_ID;
			if (version.equals("2.0")) //$NON-NLS-1$
				return J2EEVersionConstants.EJB_2_0_ID;
			if (version.equals("1.1")) //$NON-NLS-1$
				return J2EEVersionConstants.EJB_1_1_ID;
			if (version.equals("1.0")) //$NON-NLS-1$
				return J2EEVersionConstants.EJB_1_0_ID;
			throw new IllegalStateException();
		}
		return ejbRes.getModuleVersionID();
	}
	/**
	 *This returns the j2ee version id. Compare with J2EEVersionConstants to determine j2ee level
	 */
	@Override
	public int getJ2EEVersionID() throws IllegalStateException {
		J2EEVersionResource res = (J2EEVersionResource) eResource();
		if (res == null) throw new IllegalStateException("XMLResource is null"); //$NON-NLS-1$
		return res.getJ2EEVersionID();
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
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__VERSION, oldVersion, version));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
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
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setAssemblyDescriptor(AssemblyDescriptor newAssemblyDescriptor) {
		if (newAssemblyDescriptor != assemblyDescriptor) {
			NotificationChain msgs = null;
			if (assemblyDescriptor != null)
				msgs = ((InternalEObject)assemblyDescriptor).eInverseRemove(this, EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR, AssemblyDescriptor.class, msgs);
			if (newAssemblyDescriptor != null)
				msgs = ((InternalEObject)newAssemblyDescriptor).eInverseAdd(this, EjbPackage.ASSEMBLY_DESCRIPTOR__EJB_JAR, AssemblyDescriptor.class, msgs);
			msgs = basicSetAssemblyDescriptor(newAssemblyDescriptor, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR, newAssemblyDescriptor, newAssemblyDescriptor));
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The enterprise-beans element contains the declarations of one or more
	 * enterprise beans.
	 */
	@Override
	public EList getEnterpriseBeans() {
		if (enterpriseBeans == null) {
			enterpriseBeans = new EObjectContainmentWithInverseEList(EnterpriseBean.class, this, EjbPackage.EJB_JAR__ENTERPRISE_BEANS, EjbPackage.ENTERPRISE_BEAN__EJB_JAR);
		}
		return enterpriseBeans;
	}

	/**
	 * @generated This field/method will be replaced during code generation 
	 * The relationships collection describes the relationships in which container managed persistence entity beans and dependent objects participate. The relationships element contains an optional description; a list of ejb-entity-ref elements (references to entity beans that participate in container managed relationships but whose abstract persistence schemas are not included in the ejb-jar file);
	 * and a list of ejb-relation elements, which specify the container managed relationships.
	 */
	@Override
	public Relationships getRelationshipList() {
		return relationshipList;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetRelationshipList(Relationships newRelationshipList, NotificationChain msgs) {
		Relationships oldRelationshipList = relationshipList;
		relationshipList = newRelationshipList;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__RELATIONSHIP_LIST, oldRelationshipList, newRelationshipList);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public void setRelationshipList(Relationships newRelationshipList) {
		if (newRelationshipList != relationshipList) {
			NotificationChain msgs = null;
			if (relationshipList != null)
				msgs = ((InternalEObject)relationshipList).eInverseRemove(this, EjbPackage.RELATIONSHIPS__EJB_JAR, Relationships.class, msgs);
			if (newRelationshipList != null)
				msgs = ((InternalEObject)newRelationshipList).eInverseAdd(this, EjbPackage.RELATIONSHIPS__EJB_JAR, Relationships.class, msgs);
			msgs = basicSetRelationshipList(newRelationshipList, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EjbPackage.EJB_JAR__RELATIONSHIP_LIST, newRelationshipList, newRelationshipList));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				if (assemblyDescriptor != null)
					msgs = ((InternalEObject)assemblyDescriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR, null, msgs);
				return basicSetAssemblyDescriptor((AssemblyDescriptor)otherEnd, msgs);
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				return ((InternalEList)getEnterpriseBeans()).basicAdd(otherEnd, msgs);
			case EjbPackage.EJB_JAR__RELATIONSHIP_LIST:
				if (relationshipList != null)
					msgs = ((InternalEObject)relationshipList).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - EjbPackage.EJB_JAR__RELATIONSHIP_LIST, null, msgs);
				return basicSetRelationshipList((Relationships)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				return basicSetAssemblyDescriptor(null, msgs);
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				return ((InternalEList)getEnterpriseBeans()).basicRemove(otherEnd, msgs);
			case EjbPackage.EJB_JAR__RELATIONSHIP_LIST:
				return basicSetRelationshipList(null, msgs);
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
			case EjbPackage.EJB_JAR__EJB_CLIENT_JAR:
				return getEjbClientJar();
			case EjbPackage.EJB_JAR__VERSION:
				return getVersion();
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				return getAssemblyDescriptor();
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				return getEnterpriseBeans();
			case EjbPackage.EJB_JAR__RELATIONSHIP_LIST:
				return getRelationshipList();
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
			case EjbPackage.EJB_JAR__EJB_CLIENT_JAR:
				setEjbClientJar((String)newValue);
				return;
			case EjbPackage.EJB_JAR__VERSION:
				setVersion((String)newValue);
				return;
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				setAssemblyDescriptor((AssemblyDescriptor)newValue);
				return;
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				getEnterpriseBeans().clear();
				getEnterpriseBeans().addAll((Collection)newValue);
				return;
			case EjbPackage.EJB_JAR__RELATIONSHIP_LIST:
				setRelationshipList((Relationships)newValue);
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
			case EjbPackage.EJB_JAR__EJB_CLIENT_JAR:
				setEjbClientJar(EJB_CLIENT_JAR_EDEFAULT);
				return;
			case EjbPackage.EJB_JAR__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				setAssemblyDescriptor((AssemblyDescriptor)null);
				return;
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				getEnterpriseBeans().clear();
				return;
			case EjbPackage.EJB_JAR__RELATIONSHIP_LIST:
				setRelationshipList((Relationships)null);
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
			case EjbPackage.EJB_JAR__EJB_CLIENT_JAR:
				return EJB_CLIENT_JAR_EDEFAULT == null ? ejbClientJar != null : !EJB_CLIENT_JAR_EDEFAULT.equals(ejbClientJar);
			case EjbPackage.EJB_JAR__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case EjbPackage.EJB_JAR__ASSEMBLY_DESCRIPTOR:
				return assemblyDescriptor != null;
			case EjbPackage.EJB_JAR__ENTERPRISE_BEANS:
				return enterpriseBeans != null && !enterpriseBeans.isEmpty();
			case EjbPackage.EJB_JAR__RELATIONSHIP_LIST:
				return relationshipList != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * @generated This field/method will be replaced during code generation.
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (ejbClientJar: "); //$NON-NLS-1$
		result.append(ejbClientJar);
		result.append(", version: "); //$NON-NLS-1$
		result.append(version);
		result.append(')');
		return result.toString();
	}

	@Override
	public EJBRelationshipRole getRelationshipRole(String aRoleName, ContainerManagedEntity sourceCMP) {
		if (aRoleName == null || sourceCMP == null) return null;
		EJBRelationshipRole role = null;
		if (getRelationshipList() != null) {
			List relations = getRelationshipList().getEjbRelations();
			int size = relations.size();
			EJBRelation rel = null;
			for (int i = 0; i < size; i++) {
				rel = (EJBRelation) relations.get(i);
				role = rel.getRelationshipRole(aRoleName);
				if (role != null && role.getSourceEntity() == sourceCMP)
					return role;				
			}
		}
		return null;
	}
	
	@Override
	public EJBRelation getEJBRelation(String aRelationName) {
		Relationships relList = getRelationshipList();
		if (relList != null) {
			List rels = relList.getEjbRelations();
			int size = rels.size();
			EJBRelation rel = null;
			for (int i = 0; i < size; i++) {
				rel = (EJBRelation) rels.get(i);
				if (rel == null) continue;
				if (aRelationName.equals(rel.getName()))
					return rel;
			}
		}
		return null;
	}
	/**
	 * @see EJBJar#getEJBRelationsForSource(ContainerManagedEntity)
	 */
	@Override
	public List getEJBRelationsForSource(ContainerManagedEntity cmp) {
		Relationships relList = getRelationshipList();
		List result = null;
		if (relList != null) {
			List rels = relList.getEjbRelations();
			int size = rels.size();
			EJBRelation rel = null;
			for (int i = 0; i < size; i++) {
				rel = (EJBRelation) rels.get(i);
				if (rel == null) continue;
				List roles = rel.getRelationshipRoles();
				EJBRelationshipRole role;
				for (int j = 0; j < roles.size(); j++) {
					role = (EJBRelationshipRole) roles.get(j);
					if (role.getSourceEntity() == cmp) {
						if (result == null)
							result = new ArrayList();
						result.add(rel);
						break;
					}
				}
			}
		}
		if (result == null)
			result = Collections.EMPTY_LIST;
		return result;
	}
	
	@Override
	public List getEJBRelationshipRolesForType(ContainerManagedEntity cmp) {
		Relationships relList = getRelationshipList();
		List result = null;
		if (relList != null) {
			List rels = relList.getEjbRelations();
			int size = rels.size();
			EJBRelation rel = null;
			for (int i = 0; i < size; i++) {
				rel = (EJBRelation) rels.get(i);
				if (rel == null) continue;
				List roles = rel.getRelationshipRoles();
				EJBRelationshipRole role;
				for (int j = 0; j < roles.size(); j++) {
					role = (EJBRelationshipRole) roles.get(j);
					if (cmp.equals(role.getTypeEntity())) {
						if (result == null)
							result = new ArrayList();
						result.add(role);
						break;
					}
				}
			}
		}
		if (result == null)
			result = Collections.EMPTY_LIST;
		return result;
	}


}






