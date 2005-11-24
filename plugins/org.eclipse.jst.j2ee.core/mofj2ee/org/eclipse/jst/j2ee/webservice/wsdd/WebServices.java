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
package org.eclipse.jst.j2ee.webservice.wsdd;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Web Services</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The webservices element is the root element for the web services deployment descriptor.  It specifies the set of Web service descriptions that are to be deployed into the J2EE Application Server and the dependencies they have on container resources and services.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wsdd.WebServices#getWebServiceDescriptions <em>Web Service Descriptions</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServices()
 * @model
 * @generated
 * @since 1.0 */
public interface WebServices extends CompatibilityDescriptionGroup{
	/**
	 * Returns the value of the '<em><b>Web Service Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Web Service Descriptions</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Web Service Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wsdd.WsddPackage#getWebServices_WebServiceDescriptions()
	 * @model type="org.eclipse.jst.j2ee.webservice.wsdd.WebServiceDescription" containment="true" required="true"
	 * @generated
	 */
  EList getWebServiceDescriptions();

} // WebServices
