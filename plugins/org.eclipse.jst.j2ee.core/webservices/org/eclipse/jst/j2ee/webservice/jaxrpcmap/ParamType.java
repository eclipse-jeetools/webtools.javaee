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
 * A representation of the model object '<em><b>Param Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType#getParamType <em>Param Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getParamType()
 * @model 
 * @generated
 */
public interface ParamType extends EObject {
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
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getParamType_Id()
   * @model 
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Param Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Param Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Param Type</em>' attribute.
   * @see #setParamType(String)
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getParamType_ParamType()
   * @model 
   * @generated
   */
  String getParamType();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParamType#getParamType <em>Param Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Param Type</em>' attribute.
   * @see #getParamType()
   * @generated
   */
  void setParamType(String value);

} // ParamType
