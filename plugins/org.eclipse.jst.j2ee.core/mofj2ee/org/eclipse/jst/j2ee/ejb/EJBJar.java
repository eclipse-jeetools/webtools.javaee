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
package org.eclipse.jst.j2ee.ejb;

import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;

/**
 * The root element of the EJB deployment descriptor. It contains an optional description of the ejb-jar file; optional display name; optional small icon file name; optional large icon file
 * name; mandatory structural information about all included enterprise beans; a descriptor for container managed relationships, if any; an optional application-assembly descriptor; and an optional name of an ejb-client-jar file for the ejb-jar.
 */
public interface EJBJar extends CompatibilityDescriptionGroup{

/**
 * Return true if there are any ContainerManagedEntity beans in this jar.
 */
boolean containsContainerManagedBeans() ;
public boolean containsSecurityRole(String name);
/**
 * Return List of BeanManaged beans in this jar.
 * @return java.util.List
 */
List getBeanManagedBeans() ;
/**
 * Return List of ContainerManagedEntity beans in this jar.
 * @return java.util.List
 */
List getContainerManagedBeans() ;
/**
 * Return List of EJB 1.1 ContainerManagedEntity beans in this jar.
 * @return java.util.List
 */
List getEJB11ContainerManagedBeans() ;
/**
 * Return List of EJB 2.0 ContainerManagedEntity beans in this jar.
 * @return java.util.List
 */
List getEJB20ContainerManagedBeans() ;
/**
 * Return List of MessageDriven beans in this jar.
 * @return java.util.List
 */
List getMessageDrivenBeans() ;
	/**
	 * @return The list of EjbRelations references
	 * A list of ejb-relation elements, which specify the container managed relationships.
	 */
	public List getEjbRelations();
/**
 * Return an enterprise bean referenced by the EjbRef, if one exists.  The ejb-link value
 * of the ref must equate to a named enterprise bean contained in the jar; otherwise return
 * null
 */
public EnterpriseBean getEnterpiseBeanFromRef(EjbRef ref);
	public EnterpriseBean getEnterpriseBeanNamed(String ejbName);
/**
 * Return ALL EnterpriseBean(s) that are referencing @aJavaClass as a
 * home, remote, bean class, or key class.
 */
List getEnterpriseBeansWithReference(JavaClass aJavaClass) ;
/**
 * Return the *FIRST* EnterpriseBean that is referencing @aJavaClass as its
 * home, remote, bean class, or key class.
 */
EnterpriseBean getEnterpriseBeanWithReference(JavaClass aJavaClass) ;
/**
 * Return List of Session beans in this jar.
 * @return java.util.List
 */
List getSessionBeans() ;
/**
 * Return boolean indicating if this EJB JAR was populated from an EJB 1.1 descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
boolean isVersion1_1Descriptor() ;
/**
 * Return boolean indicating if this EJB JAR was populated from an EJB 2.0 descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
boolean isVersion2_0Descriptor() ;
/**
 * Rename the security role contained in the assembly descriptor; for each enterprise bean, fix the role-link on any contained role
 * refs
 */
public void renameSecurityRole(String existingRoleName, String newRoleName);
	/**
	 *This returns the j2ee version id. Compare with J2EEVersionConstants to determine j2ee level
	 */
	public int getJ2EEVersionID() throws IllegalStateException ;

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the EjbClientJar attribute
	 * The optional ejb-client-jar element specifies a JAR file that contains the
	 * class files necessary for a client program to access the enterprise beans in
	 * the ejb-jar file. The Deployer should make the ejb-client JAR file accessible
	 * to the client's class-loader. 
	 * Example:<ejb-client-jar>employee_service_client.jar<//ejb-client-jar>

	 */
	String getEjbClientJar();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the EjbClientJar attribute
	 */
	void setEjbClientJar(String value);

	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Version</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * The version specifies the version of the
	 *     EJB specification that the instance document must
	 *     comply with. This information enables deployment tools
	 *     to validate a particular EJB Deployment
	 *     Descriptor with respect to a specific version of the EJB
	 *     schema.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.jst.j2ee.internal.ejb.EjbPackage#getEJBJar_Version()
	 * @model 
	 * @generated
	 */
	String getVersion();

	/**
	 * This returns the module version id.  Compare with J2EEVersionConstants to determine module level
	 */
	public int getVersionID() throws IllegalStateException ;

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.EJBJar#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The AssemblyDescriptor reference
	 */
	AssemblyDescriptor getAssemblyDescriptor();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the AssemblyDescriptor reference
	 */
	void setAssemblyDescriptor(AssemblyDescriptor value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of EnterpriseBeans references
	 * The enterprise-beans element contains the declarations of one or more
	 * enterprise beans.
	 */
	EList getEnterpriseBeans();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The RelationshipList reference
	 * The relationships collection describes the relationships in which container
	 * managed persistence entity beans and dependent objects participate. The
	 * relationships element contains an optional description; a list of
	 * ejb-entity-ref elements (references to entity beans that participate in
	 * container managed relationships but whose abstract persistence schemas are not
	 * included in the ejb-jar file);
	 * and a list of ejb-relation elements, which specify the container managed
	 * relationships.
	 */
	Relationships getRelationshipList();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the RelationshipList reference
	 */
	void setRelationshipList(Relationships value);

	/**
	 * Return the EJBRelationshipRole with the same name as <code>aRoleName</code>
	 * that has <code>sourceCMP</code> as its source bean.
	 */
	EJBRelationshipRole getRelationshipRole(String aRoleName, ContainerManagedEntity sourceCMP) ;
	
	/**
	 * Return the first EJBRelation with the same name as <code>aRelationName</code>.
	 */
	EJBRelation getEJBRelation(String aRelationName) ;
	
	/**
	 * Return all EJBRelation objects that have an EJBRelationshipRole with a
	 * source EJB of <code>cmp</code>.
	 */
	List getEJBRelationsForSource(ContainerManagedEntity cmp);
	
	/**
	 * Return all EJBRelationshipRole objects that have an EJBRelationshipRole with a
	 * type EJB of <code>cmp</code>.
	 */
	List getEJBRelationshipRolesForType(ContainerManagedEntity cmp) ;
}






