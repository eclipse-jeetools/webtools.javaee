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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XML Element Name</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName#getXmlElementName <em>Xml Element Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getXMLElementName()
 * @model 
 * @generated
 */
public interface XMLElementName extends EObject {
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
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getXMLElementName_Id()
   * @model 
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Xml Element Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xml Element Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Xml Element Name</em>' attribute.
   * @see #setXmlElementName(String)
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getXMLElementName_XmlElementName()
   * @model 
   * @generated
   */
  String getXmlElementName();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.XMLElementName#getXmlElementName <em>Xml Element Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Xml Element Name</em>' attribute.
   * @see #getXmlElementName()
   * @generated
   */
  void setXmlElementName(String value);

} // XMLElementName
