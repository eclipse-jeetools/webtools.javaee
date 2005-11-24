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
package org.eclipse.jst.j2ee.webservice.wscommon;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Init Param</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getParamName <em>Param Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getParamValue <em>Param Value</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getDescriptionTypes <em>Description Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage#getInitParam()
 * @model
 * @generated
 * @since 1.0 */
public interface InitParam extends EObject{
	/**
	 * Returns the value of the '<em><b>Param Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Param Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Param Name</em>' attribute.
	 * @see #setParamName(String)
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage#getInitParam_ParamName()
	 * @model
	 * @generated
	 */
  String getParamName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getParamName <em>Param Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Param Name</em>' attribute.
	 * @see #getParamName()
	 * @generated
	 */
  void setParamName(String value);

	/**
	 * Returns the value of the '<em><b>Param Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Param Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Param Value</em>' attribute.
	 * @see #setParamValue(String)
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage#getInitParam_ParamValue()
	 * @model
	 * @generated
	 */
  String getParamValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getParamValue <em>Param Value</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Param Value</em>' attribute.
	 * @see #getParamValue()
	 * @generated
	 */
  void setParamValue(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage#getInitParam_Description()
	 * @model
	 * @generated
	 */
  String getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.wscommon.InitParam#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
  void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Description Types</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Description Types</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Description Types</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.wscommon.WscommonPackage#getInitParam_DescriptionTypes()
	 * @model type="org.eclipse.jst.j2ee.webservice.wscommon.DescriptionType" containment="true"
	 * @generated
	 */
  EList getDescriptionTypes();

} // InitParam
