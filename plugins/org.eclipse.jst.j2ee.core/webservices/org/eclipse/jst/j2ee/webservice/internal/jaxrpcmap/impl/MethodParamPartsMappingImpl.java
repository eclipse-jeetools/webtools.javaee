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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessageMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Method Param Parts Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.MethodParamPartsMappingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.MethodParamPartsMappingImpl#getParamPosition <em>Param Position</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.MethodParamPartsMappingImpl#getParamType <em>Param Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.MethodParamPartsMappingImpl#getWsdlMessageMapping <em>Wsdl Message Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MethodParamPartsMappingImpl extends EObjectImpl implements MethodParamPartsMapping
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
   * The default value of the '{@link #getParamType() <em>Param Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParamType()
   * @generated
   * @ordered
   */
  protected static final String PARAM_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getParamType() <em>Param Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParamType()
   * @generated
   * @ordered
   */
  protected String paramType = PARAM_TYPE_EDEFAULT;

  /**
   * The cached value of the '{@link #getWsdlMessageMapping() <em>Wsdl Message Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWsdlMessageMapping()
   * @generated
   * @ordered
   */
  protected WSDLMessageMapping wsdlMessageMapping = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected MethodParamPartsMappingImpl()
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
    return JaxrpcmapPackage.eINSTANCE.getMethodParamPartsMapping();
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
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__ID, oldId, id));
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
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_POSITION, oldParamPosition, paramPosition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getParamType()
  {
    return paramType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParamType(String newParamType)
  {
    String oldParamType = paramType;
    paramType = newParamType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_TYPE, oldParamType, paramType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WSDLMessageMapping getWsdlMessageMapping()
  {
    return wsdlMessageMapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWsdlMessageMapping(WSDLMessageMapping newWsdlMessageMapping, NotificationChain msgs)
  {
    WSDLMessageMapping oldWsdlMessageMapping = wsdlMessageMapping;
    wsdlMessageMapping = newWsdlMessageMapping;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING, oldWsdlMessageMapping, newWsdlMessageMapping);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWsdlMessageMapping(WSDLMessageMapping newWsdlMessageMapping)
  {
    if (newWsdlMessageMapping != wsdlMessageMapping)
    {
      NotificationChain msgs = null;
      if (wsdlMessageMapping != null)
        msgs = ((InternalEObject)wsdlMessageMapping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING, null, msgs);
      if (newWsdlMessageMapping != null)
        msgs = ((InternalEObject)newWsdlMessageMapping).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING, null, msgs);
      msgs = basicSetWsdlMessageMapping(newWsdlMessageMapping, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING, newWsdlMessageMapping, newWsdlMessageMapping));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING:
          return basicSetWsdlMessageMapping(null, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
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
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__ID:
        return getId();
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_POSITION:
        return getParamPosition();
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_TYPE:
        return getParamType();
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING:
        return getWsdlMessageMapping();
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
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__ID:
        setId((String)newValue);
        return;
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_POSITION:
        setParamPosition((String)newValue);
        return;
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_TYPE:
        setParamType((String)newValue);
        return;
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING:
        setWsdlMessageMapping((WSDLMessageMapping)newValue);
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
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__ID:
        setId(ID_EDEFAULT);
        return;
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_POSITION:
        setParamPosition(PARAM_POSITION_EDEFAULT);
        return;
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_TYPE:
        setParamType(PARAM_TYPE_EDEFAULT);
        return;
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING:
        setWsdlMessageMapping((WSDLMessageMapping)null);
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
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_POSITION:
        return PARAM_POSITION_EDEFAULT == null ? paramPosition != null : !PARAM_POSITION_EDEFAULT.equals(paramPosition);
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__PARAM_TYPE:
        return PARAM_TYPE_EDEFAULT == null ? paramType != null : !PARAM_TYPE_EDEFAULT.equals(paramType);
      case JaxrpcmapPackage.METHOD_PARAM_PARTS_MAPPING__WSDL_MESSAGE_MAPPING:
        return wsdlMessageMapping != null;
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
    result.append(", paramType: ");//$NON-NLS-1$
    result.append(paramType);
    result.append(')');
    return result.toString();
  }

} //MethodParamPartsMappingImpl
