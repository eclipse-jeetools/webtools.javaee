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

import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.common.EJBLocalRef;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.JNDIEnvRefsGroup;
import org.eclipse.jst.j2ee.common.SecurityIdentity;


/**
 * @generated
 * EnterpriseJavaBean is a class.  It can have instances, someone could write an instance document containing Departments and Employees.  It also has attributes, operations, and associations.  These are actually derived//filtered from its implementation classes and interfaces.  For mapping and browsing purposes, though, you would like the EJB to appear as a class.  
 * 
 * In this light, even Session Beans can have associations and properties implemented by their bean.  For example, it would be meaningful to describe associations from a Session to the Entities which it uses to perform its work.
 * @extends ENamedElement
 *
 **/
public interface EnterpriseBean extends JNDIEnvRefsGroup, ENamedElement{

/**
 * Creates a List of MethodElements that represent the
 * Methods from @someMethods pointing to this EJB.  It is
 * assumed that all of the methods belong to @anInterface.
 */
List createMethodElements(List someMethods, JavaClass anInterface) ;
/**
 * This method will return a List of newly created MethodElements
 * that represent all the possible MethodElements that can be created
 * and are common to both the Home and Remote interfaces.  These MethodElements are not contained and will
 * not be serialized and should not be added to the model.  Their intention is
 * solely for comparing with actual MethodElements contained in the model.
 * @deprecated used getAvailableUnspecifiedMethodElements
 */
List getAvailableCommonMethodElements() ;

/**
 * This method will return a List of newly created MethodElements
 * that represent all the possible MethodElements that can be created
 * and are common to both the Home and Remote interfaces.  These MethodElements are not contained and will
 * not be serialized and should not be added to the model.  Their intention is
 * solely for comparing with actual MethodElements contained in the model.
 */
List getAvailableUnspecifiedMethodElements() ;
/**
 * This method will return a List of MethodElements signatures
 * that represent all the possible MethodElements that can be created
 * and are common to both the Home and Remote interfaces.
 * @deprecated used getAvailableUnspecifiedMethodElementSignatures
 */
List getAvailableCommonMethodElementSignatures() ;
/**
 * This method will return a List of MethodElements signatures
 * that represent all the possible MethodElements that can be created
 * and are common to both the Home and Remote interfaces.
 */
List getAvailableUnspecifiedMethodElementSignatures() ;
/**
 * This method will return a List of newly created MethodElements
 * that represent all the possible MethodElements that can be created
 * for the Home interface.  These MethodElements are not contained and will
 * not be serialized and should not be added to the model.  Their intention is
 * solely for comparing with actual MethodElements contained in the model.
 */
List getAvailableHomeMethodElements() ;
/**
 * This method will return a List of newly created MethodElements
 * that represent all the possible MethodElements that can be created
 * for the Local Home interface.  These MethodElements are not contained and will
 * not be serialized and should not be added to the model.  Their intention is
 * solely for comparing with actual MethodElements contained in the model.
 */
List getAvailableLocalHomeMethodElements() ;
/**
 * This method will return a List of newly created MethodElements
 * that represent all the possible MethodElements that can be created
 * for the Local interface.  These MethodElements are not contained and will
 * not be serialized and should not be added to the model.  Their intention is
 * solely for comparing with actual MethodElements contained in the model.
 */
List getAvailableLocalMethodElements() ;
/**
 * This method will return a List of newly created MethodElements
 * that represent all the possible MethodElements that can be created
 * for the Remote interface.  These MethodElements are not contained and will
 * not be serialized and should not be added to the model.  Their intention is
 * solely for comparing with actual MethodElements contained in the model.
 */
List getAvailableRemoteMethodElements() ;
/**
 * Return a List of all available MethodElements for all types as well as the
 * existing MethodElements from the <code>refObject</code>.  The parameter
 * will be either a MethodTransaction or a MethodPermission.  The List will be
 * sorted based on the passed EjbMethodElementComparator
 */
public List getExistingOrAvailableMethodElements(EObject refObject, Comparator comparator) ;
/**
 * Return a List of all available MethodElements for all types as well as the
 * existing MethodElements from the <code>refObject</code>.  The parameter
 * will be either a MethodTransaction or a MethodPermission.  The List will be
 * sorted based on the EjbMethodElementComparator
 */
public List getExistingOrAvailableMethodElements(EObject refObject) ;
public String getEjbClassName();
/**
 * Return an EjbRef contained by this bean which is
 * equivalent to @anEjbRef.
 */
EjbRef getEquivalentEjbRef(EjbRef anEjbRef) ;
/**
 * Method getEquivalentEJBLocalRef.
 * @param anEjbLocalRef
 * @return EJBLocalRef
 */
EJBLocalRef getEquivalentEJBLocalRef(EJBLocalRef anEjbLocalRef);

public String getHomeInterfaceName();
public Method[] getHomeMethodsForDeployment();
/**
 * Return an EjbRef that is physically linked to @anEJB.
 */
EjbRef getLinkedEJBReference(EnterpriseBean anEJB) ;
/**
 * Return an EJBLocalRef that is physically linked to @anEJB.
 */
EJBLocalRef getLinkedEJBLocalReference(EnterpriseBean anEJB) ;
public String getLocalHomeInterfaceName();
public Method[] getLocalHomeMethodsForDeployment();
public String getLocalInterfaceName();
public String getRemoteInterfaceName();
public Method[] getRemoteMethodsForDeployment();
/**
 * Return true if @aJavaClass as equal to the
 * home, remote, bean class, or key class.
 */
boolean hasJavaReference(JavaClass aJavaClass) ;
/**
 * Return true if this ejb has BeanManagedPersistance.
 */
boolean isBeanManagedEntity() ;
public boolean isContainerManagedEntity();
public boolean isEntity();
public boolean isMessageDriven();
public boolean isSession();

/**
 * returns an integer representing the version of the bean in the EJBJar
 * @see org.eclipse.jst.j2ee.J2EEVersionConstants
 */
public int getVersionID();

/**
 * @deprecated - Use {@link #getVersionID()}
 * @see org.eclipse.jst.j2ee.J2EEVersionConstants
 */
public boolean isVersion1_X() ;

/**
 * @deprecated - Use {@link #getVersionID()}
 * @see org.eclipse.jst.j2ee.J2EEVersionConstants
 */
public boolean isVersion2_X() ;

/**
 * Return true if this EJB has a local interface and a local home
 * interface.
 */
public boolean hasLocalClient();

/**
 * Return true if this EJB has a remote interface and a home
 * interface.
 */
public boolean hasRemoteClient();

/**
 * Fix the role-link on any contained role refs
 */
public void reSyncSecurityRoleRef(String existingRoleName, String newRoleName);
public void setEjbClassName(String ejbClassName);
public void setHomeInterfaceName(String homeInterfaceName);
public void setLocalHomeInterfaceName(String localHomeInterfaceName);
public void setLocalInterfaceName(String localInterfaceName);
public void setRemoteInterfaceName(String remoteInterfaceName);
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.eclipse.jst.j2ee.ejb.EjbPackage#getEnterpriseBean_Name()
	 * @model 
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.ejb.EnterpriseBean#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of SecurityRoleRefs references
	 */
	EList getSecurityRoleRefs();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The EjbClass reference
	 */
	JavaClass getEjbClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the EjbClass reference
	 */
	void setEjbClass(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The HomeInterface reference
	 */
	JavaClass getHomeInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the HomeInterface reference
	 */
	void setHomeInterface(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The RemoteInterface reference
	 */
	JavaClass getRemoteInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the RemoteInterface reference
	 */
	void setRemoteInterface(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The EjbJar reference
	 */
	EJBJar getEjbJar();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the EjbJar reference
	 */
	void setEjbJar(EJBJar value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The SecurityIdentity reference
	 * The security-identity element specifies whether the caller's security identity
	 * is to be used for the execution of the methods of the enterprise bean or
	 * whether a specific run-as identity is to be used. It
	 * contains an optional description and a specification of the security identity
	 * to be used.
	 */
	SecurityIdentity getSecurityIdentity();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the SecurityIdentity reference
	 */
	void setSecurityIdentity(SecurityIdentity value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The LocalHomeInterface reference
	 */
	JavaClass getLocalHomeInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the LocalHomeInterface reference
	 */
	void setLocalHomeInterface(JavaClass value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The LocalInterface reference
	 */
	JavaClass getLocalInterface();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the LocalInterface reference
	 */
	void setLocalInterface(JavaClass value);

}






