/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.application;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The moduleType defines a single Java EE module and contains a
 *         connector, ejb, java, or web element, which indicates the
 *         module type and contains a path to the module file, and an
 *         optional alt-dd element, which specifies an optional URI to
 *         the post-assembly version of the deployment descriptor.
 *         
 *         @since Java EE 5, Application 5
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.application.Module#getConnector <em>Connector</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Module#getEjb <em>Ejb</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Module#getJava <em>Java</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Module#getWeb <em>Web</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Module#getAltDd <em>Alt Dd</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.application.Module#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getModule()
 * @extends JavaEEObject
 * @generated
 */
public interface Module extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Connector</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               The connector element specifies the URI of a
	 *               resource adapter archive file, relative to the
	 *               top level of the application package.
	 *               
	 *               @since Java EE 5, Application 5
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connector</em>' attribute.
	 * @see #setConnector(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getModule_Connector()
	 * @generated
	 */
	String getConnector();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Module#getConnector <em>Connector</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connector</em>' attribute.
	 * @see #getConnector()
	 * @generated
	 */
	void setConnector(String value);

	/**
	 * Returns the value of the '<em><b>Ejb</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               The ejb element specifies the URI of an ejb-jar,
	 *               relative to the top level of the application
	 *               package.
	 *               
	 *               @since Java EE 5, Application 5
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ejb</em>' attribute.
	 * @see #setEjb(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getModule_Ejb()
	 * @generated
	 */
	String getEjb();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Module#getEjb <em>Ejb</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb</em>' attribute.
	 * @see #getEjb()
	 * @generated
	 */
	void setEjb(String value);

	/**
	 * Returns the value of the '<em><b>Java</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *               The java element specifies the URI of a java
	 *               application client module, relative to the top
	 *               level of the application package.
	 *               
	 *               @since Java EE 5, Application 5
	 *             
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Java</em>' attribute.
	 * @see #setJava(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getModule_Java()
	 * @generated
	 */
	String getJava();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Module#getJava <em>Java</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java</em>' attribute.
	 * @see #getJava()
	 * @generated
	 */
	void setJava(String value);

	/**
	 * Returns the value of the '<em><b>Web</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Web</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Web</em>' containment reference.
	 * @see #setWeb(Web)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getModule_Web()
	 * @generated
	 */
	Web getWeb();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Module#getWeb <em>Web</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Web</em>' containment reference.
	 * @see #getWeb()
	 * @generated
	 */
	void setWeb(Web value);

	/**
	 * Returns the value of the '<em><b>Alt Dd</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The alt-dd element specifies an optional URI to the
	 *             post-assembly version of the deployment descriptor
	 *             file for a particular Java EE module.  The URI must
	 *             specify the full pathname of the deployment
	 *             descriptor file relative to the application's root
	 *             directory. If alt-dd is not specified, the deployer
	 *             must read the deployment descriptor from the default
	 *             location and file name required by the respective
	 *             component specification.
	 *             
	 *             @since Java EE 5, Application 5
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Alt Dd</em>' attribute.
	 * @see #setAltDd(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getModule_AltDd()
	 * @generated
	 */
	String getAltDd();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Module#getAltDd <em>Alt Dd</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Alt Dd</em>' attribute.
	 * @see #getAltDd()
	 * @generated
	 */
	void setAltDd(String value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.javaee.application.internal.metadata.ApplicationPackage#getModule_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.application.Module#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	public String getUri();

} // Module