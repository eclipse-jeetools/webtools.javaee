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
package org.eclipse.jst.j2ee.common;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.common.CommonPackage;

/**
 * The resource-ref element contains a declaration of application clients's reference to an external resource. It consists of an optional description, the resource factory reference name, the indication of the resource factory type expected by the application client's code, and the type of authentication (bean or container).
 * Example:
 * <resource-ref>
 * <res-ref-name>EmployeeAppDB<//res-ref-name>
 * <res-type>javax.sql.DataSource<//res-type>
 * <res-auth>Container<//res-auth>
 * <//resource-ref>
 */
public interface ResourceRef extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 * The description element is used by the ejb-jar file producer to provide text
	 * describing the parent element.  The description element should include any
	 * information that the ejb-jar file producer wants to provide to the consumer of
	 * the ejb-jar file (i.e. to the Deployer). Typically, the tools used by the
	 * ejb-jar file consumer will display the description when processing the parent
	 * element.
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Name attribute
	 * The res-ref-name element specifies the name of the resource factory reference
	 * name. The resource factory reference name is the name of the application
	 * client's environment entry whose value contains the JNDI name of the data
	 * source.
	 */
	String getName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Name attribute
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Type attribute
	 * The res-type element specifies the type of the data source. The type is
	 * specified by the Java interface (or class) expected to be implemented by the
	 * data source.
	 */
	String getType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Type attribute
	 */
	void setType(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Auth attribute
	 * The res-auth element specifies whether the enterprise bean code signs on
	 * programmatically to the resource manager, or whether the Container will sign on 
	 * to the resource manager on behalf of the bean. In the latter case, the
	 * Container uses information that is supplied by the Deployer.  The value of this 
	 * element must be one of the two following:
	 * <res-auth>Application<//res-auth>
	 * <res-auth>Container<//res-auth>
	 */
	ResAuthTypeBase getAuth();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.common.ResourceRef#getAuth <em>Auth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Auth</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.common.ResAuthTypeBase
	 * @see #isSetAuth()
	 * @see #unsetAuth()
	 * @see #getAuth()
	 * @generated
	 */
	void setAuth(ResAuthTypeBase value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.common.ResourceRef#getAuth <em>Auth</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetAuth()
	 * @see #getAuth()
	 * @see #setAuth(ResAuthTypeBase)
	 * @generated
	 */
	void unsetAuth();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.common.ResourceRef#getAuth <em>Auth</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Auth</em>' attribute is set.
	 * @see #unsetAuth()
	 * @see #getAuth()
	 * @see #setAuth(ResAuthTypeBase)
	 * @generated
	 */
	boolean isSetAuth();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Link attribute
	 */
	String getLink();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Link attribute
	 */
	void setLink(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ResSharingScope attribute
	 * The res-sharing-scope element specifies whether connections obtained
	 * through the given resource manager connection factory reference can be
	 * shared. The value of this element, if specified, must be one of the
	 * two following:  Shareable, Unshareable
	 * 
	 * The default value is Shareable.

	 */
	ResSharingScopeType getResSharingScope();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.common.ResourceRef#getResSharingScope <em>Res Sharing Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Res Sharing Scope</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.common.ResSharingScopeType
	 * @see #isSetResSharingScope()
	 * @see #unsetResSharingScope()
	 * @see #getResSharingScope()
	 * @generated
	 */
	void setResSharingScope(ResSharingScopeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.common.ResourceRef#getResSharingScope <em>Res Sharing Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResSharingScope()
	 * @see #getResSharingScope()
	 * @see #setResSharingScope(ResSharingScopeType)
	 * @generated
	 */
	void unsetResSharingScope();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.common.ResourceRef#getResSharingScope <em>Res Sharing Scope</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Res Sharing Scope</em>' attribute is set.
	 * @see #unsetResSharingScope()
	 * @see #getResSharingScope()
	 * @see #setResSharingScope(ResSharingScopeType)
	 * @generated
	 */
	boolean isSetResSharingScope();

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.common.CommonPackage#getResourceRef_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.internal.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





