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
package org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ParamPosition;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Param Position</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ParamPositionImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ParamPositionImpl#getParamPosition <em>Param Position</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParamPositionImpl extends EObjectImpl implements ParamPosition
{
  /**
   * The default value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected static final String ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getId()
   * @generated
   * @ordered
   */
  protected String id = ID_EDEFAULT;

  /**
   * The default value of the '{@link #getParamPosition() <em>Param Position</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParamPosition()
   * @generated
   * @ordered
   */
  protected static final String PARAM_POSITION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getParamPosition() <em>Param Position</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParamPosition()
   * @generated
   * @ordered
   */
  protected String paramPosition = PARAM_POSITION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ParamPositionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return JaxrpcmapPackage.eINSTANCE.getParamPosition();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getId()
  {
    return id;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setId(String newId)
  {
    String oldId = id;
    id = newId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.PARAM_POSITION__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getParamPosition()
  {
    return paramPosition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParamPosition(String newParamPosition)
  {
    String oldParamPosition = paramPosition;
    paramPosition = newParamPosition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.PARAM_POSITION__PARAM_POSITION, oldParamPosition, paramPosition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case JaxrpcmapPackage.PARAM_POSITION__ID:
        return getId();
      case JaxrpcmapPackage.PARAM_POSITION__PARAM_POSITION:
        return getParamPosition();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case JaxrpcmapPackage.PARAM_POSITION__ID:
        setId((String)newValue);
        return;
      case JaxrpcmapPackage.PARAM_POSITION__PARAM_POSITION:
        setParamPosition((String)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case JaxrpcmapPackage.PARAM_POSITION__ID:
        setId(ID_EDEFAULT);
        return;
      case JaxrpcmapPackage.PARAM_POSITION__PARAM_POSITION:
        setParamPosition(PARAM_POSITION_EDEFAULT);
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case JaxrpcmapPackage.PARAM_POSITION__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case JaxrpcmapPackage.PARAM_POSITION__PARAM_POSITION:
        return PARAM_POSITION_EDEFAULT == null ? paramPosition != null : !PARAM_POSITION_EDEFAULT.equals(paramPosition);
    }
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (id: ");//$NON-NLS-1$
    result.append(id);
    result.append(", paramPosition: ");//$NON-NLS-1$
    result.append(paramPosition);
    result.append(')');
    return result.toString();
  }

} //ParamPositionImpl
