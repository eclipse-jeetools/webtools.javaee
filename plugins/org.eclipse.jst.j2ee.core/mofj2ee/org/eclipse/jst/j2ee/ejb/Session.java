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
import org.eclipse.jem.java.JavaClass;




/**
 * The session element declares an session bean. The declaration consists of: an optional description; optional display name; optional small icon file name; optional large icon file name; a name assigned
 * to the enterprise bean in the deployment description; the names of the session bean's home and remote interfaces, if any; the names of the session bean's local home and local interfaces, if any; the session bean's implementation class; the session bean's state management type; the session bean's transaction management type; an optional declaration of the bean's environment entries; an optional declaration of the bean's EJB references; an optional declaration of the bean's local EJB references; an optional declaration of the security role references; an optional declaration of the security identity to be used for the execution of the bean's methods; an optional declaration of the bean's resource manager connection factory references; and an optional declaration of the bean's resource environment references. The elements that are optional are "optional" in the sense that they are omitted when if lists represented by them are empty.
 */
public interface Session extends EnterpriseBean{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the TransactionType attribute
	 * The transaction-type element specifies an enterprise bean's transaction
	 * management type.
	 * 

	 */
	TransactionType getTransactionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Session#getTransactionType <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transaction Type</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.ejb.TransactionType
	 * @see #isSetTransactionType()
	 * @see #unsetTransactionType()
	 * @see #getTransactionType()
	 * @generated
	 */
	void setTransactionType(TransactionType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Session#getTransactionType <em>Transaction Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetTransactionType()
	 * @see #getTransactionType()
	 * @see #setTransactionType(TransactionType)
	 * @generated
	 */
	void unsetTransactionType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Session#getTransactionType <em>Transaction Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Transaction Type</em>' attribute is set.
	 * @see #unsetTransactionType()
	 * @see #getTransactionType()
	 * @see #setTransactionType(TransactionType)
	 * @generated
	 */
	boolean isSetTransactionType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the SessionType attribute
	 */
	SessionType getSessionType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Session#getSessionType <em>Session Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Session Type</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.ejb.SessionType
	 * @see #isSetSessionType()
	 * @see #unsetSessionType()
	 * @see #getSessionType()
	 * @generated
	 */
	void setSessionType(SessionType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Session#getSessionType <em>Session Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetSessionType()
	 * @see #getSessionType()
	 * @see #setSessionType(SessionType)
	 * @generated
	 */
	void unsetSessionType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Session#getSessionType <em>Session Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Session Type</em>' attribute is set.
	 * @see #unsetSessionType()
	 * @see #getSessionType()
	 * @see #setSessionType(SessionType)
	 * @generated
	 */
	boolean isSetSessionType();

	/**
	 * Returns the value of the '<em><b>Service Endpoint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Endpoint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @version J2EE1.4
	 * The service-endpoint element contains the
	 *         fully-qualified name of the enterprise bean's web
	 *         service endpoint interface. The service-endpoint
	 *         element may only be specified for a stateless
	 *         session bean. The specified interface must be a
	 *         valid JAX-RPC service endpoint interface.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Endpoint</em>' reference.
	 * @see #setServiceEndpoint(JavaClass)
	 * @see org.eclipse.jst.j2ee.internal.ejb.EjbPackage#getSession_ServiceEndpoint()
	 * @model 
	 * @generated
	 */
	JavaClass getServiceEndpoint();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Session#getServiceEndpoint <em>Service Endpoint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Service Endpoint</em>' reference.
	 * @see #getServiceEndpoint()
	 * @generated
	 */
	void setServiceEndpoint(JavaClass value);
	/**
	 * Helper method to set Java Class name via String
	 */
	public void setServiceEndpointName(String serviceEndpointName);
	/**
	 * Return true if this Session bean has a service endpoint interface
	 * interface.
	 */
	public boolean hasServiceEndpoint() ;
	/**
	 * Helper method to get Java Class name 
	 */
	public String getServiceEndpointName();
	
}





