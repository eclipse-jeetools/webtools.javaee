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
import org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap.JaxrpcmapPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter Mode</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode#getParameterMode <em>Parameter Mode</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getParameterMode()
 * @model 
 * @generated
 */
public interface ParameterMode extends EObject {
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
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getParameterMode_Id()
   * @model 
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Parameter Mode</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter Mode</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter Mode</em>' attribute.
   * @see #setParameterMode(String)
   * @see org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.JaxrpcmapPackage#getParameterMode_ParameterMode()
   * @model 
   * @generated
   */
  String getParameterMode();

  /**
   * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.ParameterMode#getParameterMode <em>Parameter Mode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameter Mode</em>' attribute.
   * @see #getParameterMode()
   * @generated
   */
  void setParameterMode(String value);

} // ParameterMode
