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
package org.eclipse.jst.j2ee.client;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;


/**
 * The application-client element is the root element of an application client deployment descriptor.  The application client deployment descriptor describes the EJB components and external resources referenced by the application client.

 */
public interface ApplicationClient extends CompatibilityDescriptionGroup{

public String getCallbackHandlerClassName();
/**
 * Return boolean indicating if this Application Client was populated from an Application Client 1.2 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
public boolean isVersion1_2Descriptor();
/**
 * Return boolean indicating if this Application client was populated from an Application Client 1.3 compliant descriptor
 * @return boolean
 * @deprecated Use getVersionID() to determine module level
 */
public boolean isVersion1_3Descriptor();
/**
 * @deprecated Use getVersionID() to determine module level
 * */
public boolean isVersion1_4Descriptor();
public void setCallbackHandlerClassName(String callbackHandlerClassName);
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
	 * The required value for the version is 1.4.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see org.eclipse.jst.j2ee.internal.client.ClientPackage#getApplicationClient_Version()
	 * @model 
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.client.ApplicationClient#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of ResourceRefs references
	 * Contains declarations of the application clients's references to an external
	 * resources.
	 */
	EList getResourceRefs();

	/**
	 This returns the module version id. Compare with J2EEVersionConstants to determine module level
	 */
	public int getVersionID() throws IllegalStateException ;
	/**
	 *This returns the j2ee version id. Compare with J2EEVersionConstants to determine j2ee level
	 */
	public int getJ2EEVersionID() throws IllegalStateException ;

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of EnvironmentProps references
	 * The env-entry element contains the declaration of an application client's
	 * environment entries. Each declaration consists of an optional description, the
	 * name of the environment entry, and an optional value.
	 */
	EList getEnvironmentProps();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of EjbReferences references
	 * Contains a list of ejb-ref elements used for the declaration of a reference to
	 * an enterprise
	 * bean's home. 
	 */
	EList getEjbReferences();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of ResourceEnvRefs references
	 * The resource-env-ref element contains a declaration of an application client's
	 * reference to an administered object associated with a resource in the
	 * application client's environment.  It consists of an optional
	 * description, the resource environment reference name, and an indication of the
	 * resource environment reference type expected by the application client code.
	 * 
	 * Used in: application-client
	 * 
	 * Example:
	 * 
	 * <resource-env-ref>
	 *     <resource-env-ref-name>jms//StockQueue<//resource-env-ref-name>
	 *     <resource-env-ref-type>javax.jms.Queue<//resource-env-ref-type>
	 * <//resource-env-ref>

	 */
	EList getResourceEnvRefs();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The CallbackHandler reference
	 * A class provided by the application.  The class must have a no args constructor 
	 * and must implement the javax.security.auth.callback.CallbackHandler interface.  
	 * The class will be instantiated by the application client container and used by
	 * the container to collect authentication information from the user.
	 */
	JavaClass getCallbackHandler();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the CallbackHandler reference
	 */
	void setCallbackHandler(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Service Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Service Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Service Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.client.ClientPackage#getApplicationClient_ServiceRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.webservice.wsclient.ServiceRef" containment="true"
	 * @generated
	 */
	EList getServiceRefs();

	/**
	 * Returns the value of the '<em><b>Message Destination Refs</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.MessageDestinationRef}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destination Refs</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * The message-destination-ref-name element specifies the
	 *     name of a message destination reference; its value is
	 *     the message destination reference name used in the
	 *     application client code. The name is a JNDI name
	 *     relative to the java:comp/env context and must be unique
	 *     within an application client.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Destination Refs</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.client.ClientPackage#getApplicationClient_MessageDestinationRefs()
	 * @model type="org.eclipse.jst.j2ee.internal.common.MessageDestinationRef" containment="true"
	 * @generated
	 */
	EList getMessageDestinationRefs();

	/**
	 * Returns the value of the '<em><b>Message Destinations</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.MessageDestination}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Destinations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Message Destinations</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.client.ClientPackage#getApplicationClient_MessageDestinations()
	 * @model type="org.eclipse.jst.j2ee.internal.common.MessageDestination" containment="true"
	 * @generated
	 */
	EList getMessageDestinations();

}





