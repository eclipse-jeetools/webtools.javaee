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
package org.eclipse.jst.j2ee.webservice.jaxrpcmap.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ConstructorParameterOrder;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ExceptionMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLMessage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Exception Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.util.ExceptionMappingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.util.ExceptionMappingImpl#getExceptionType <em>Exception Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.util.ExceptionMappingImpl#getWsdlMessagePartName <em>Wsdl Message Part Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.util.ExceptionMappingImpl#getWsdlMessage <em>Wsdl Message</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.util.ExceptionMappingImpl#getConstructorParameterOrder <em>Constructor Parameter Order</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExceptionMappingImpl extends EObjectImpl implements ExceptionMapping
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
   * The default value of the '{@link #getExceptionType() <em>Exception Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExceptionType()
   * @generated
   * @ordered
   */
  protected static final String EXCEPTION_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getExceptionType() <em>Exception Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExceptionType()
   * @generated
   * @ordered
   */
  protected String exceptionType = EXCEPTION_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getWsdlMessagePartName() <em>Wsdl Message Part Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWsdlMessagePartName()
   * @generated
   * @ordered
   */
  protected static final String WSDL_MESSAGE_PART_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWsdlMessagePartName() <em>Wsdl Message Part Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWsdlMessagePartName()
   * @generated
   * @ordered
   */
  protected String wsdlMessagePartName = WSDL_MESSAGE_PART_NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getWsdlMessage() <em>Wsdl Message</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWsdlMessage()
   * @generated
   * @ordered
   */
  protected WSDLMessage wsdlMessage = null;

  /**
   * The cached value of the '{@link #getConstructorParameterOrder() <em>Constructor Parameter Order</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstructorParameterOrder()
   * @generated
   * @ordered
   */
  protected ConstructorParameterOrder constructorParameterOrder = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExceptionMappingImpl()
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
    return JaxrpcmapPackage.eINSTANCE.getExceptionMapping();
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
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.EXCEPTION_MAPPING__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getExceptionType()
  {
    return exceptionType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setExceptionType(String newExceptionType)
  {
    String oldExceptionType = exceptionType;
    exceptionType = newExceptionType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.EXCEPTION_MAPPING__EXCEPTION_TYPE, oldExceptionType, exceptionType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getWsdlMessagePartName()
  {
    return wsdlMessagePartName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWsdlMessagePartName(String newWsdlMessagePartName)
  {
    String oldWsdlMessagePartName = wsdlMessagePartName;
    wsdlMessagePartName = newWsdlMessagePartName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE_PART_NAME, oldWsdlMessagePartName, wsdlMessagePartName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WSDLMessage getWsdlMessage()
  {
    return wsdlMessage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWsdlMessage(WSDLMessage newWsdlMessage, NotificationChain msgs)
  {
    WSDLMessage oldWsdlMessage = wsdlMessage;
    wsdlMessage = newWsdlMessage;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE, oldWsdlMessage, newWsdlMessage);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWsdlMessage(WSDLMessage newWsdlMessage)
  {
    if (newWsdlMessage != wsdlMessage)
    {
      NotificationChain msgs = null;
      if (wsdlMessage != null)
        msgs = ((InternalEObject)wsdlMessage).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE, null, msgs);
      if (newWsdlMessage != null)
        msgs = ((InternalEObject)newWsdlMessage).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE, null, msgs);
      msgs = basicSetWsdlMessage(newWsdlMessage, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE, newWsdlMessage, newWsdlMessage));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstructorParameterOrder getConstructorParameterOrder()
  {
    return constructorParameterOrder;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetConstructorParameterOrder(ConstructorParameterOrder newConstructorParameterOrder, NotificationChain msgs)
  {
    ConstructorParameterOrder oldConstructorParameterOrder = constructorParameterOrder;
    constructorParameterOrder = newConstructorParameterOrder;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER, oldConstructorParameterOrder, newConstructorParameterOrder);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstructorParameterOrder(ConstructorParameterOrder newConstructorParameterOrder)
  {
    if (newConstructorParameterOrder != constructorParameterOrder)
    {
      NotificationChain msgs = null;
      if (constructorParameterOrder != null)
        msgs = ((InternalEObject)constructorParameterOrder).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER, null, msgs);
      if (newConstructorParameterOrder != null)
        msgs = ((InternalEObject)newConstructorParameterOrder).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER, null, msgs);
      msgs = basicSetConstructorParameterOrder(newConstructorParameterOrder, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER, newConstructorParameterOrder, newConstructorParameterOrder));
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
        case JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE:
          return basicSetWsdlMessage(null, msgs);
        case JaxrpcmapPackage.EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER:
          return basicSetConstructorParameterOrder(null, msgs);
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
      case JaxrpcmapPackage.EXCEPTION_MAPPING__ID:
        return getId();
      case JaxrpcmapPackage.EXCEPTION_MAPPING__EXCEPTION_TYPE:
        return getExceptionType();
      case JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE_PART_NAME:
        return getWsdlMessagePartName();
      case JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE:
        return getWsdlMessage();
      case JaxrpcmapPackage.EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER:
        return getConstructorParameterOrder();
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
      case JaxrpcmapPackage.EXCEPTION_MAPPING__ID:
        setId((String)newValue);
        return;
      case JaxrpcmapPackage.EXCEPTION_MAPPING__EXCEPTION_TYPE:
        setExceptionType((String)newValue);
        return;
      case JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE_PART_NAME:
        setWsdlMessagePartName((String)newValue);
        return;
      case JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE:
        setWsdlMessage((WSDLMessage)newValue);
        return;
      case JaxrpcmapPackage.EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER:
        setConstructorParameterOrder((ConstructorParameterOrder)newValue);
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
      case JaxrpcmapPackage.EXCEPTION_MAPPING__ID:
        setId(ID_EDEFAULT);
        return;
      case JaxrpcmapPackage.EXCEPTION_MAPPING__EXCEPTION_TYPE:
        setExceptionType(EXCEPTION_TYPE_EDEFAULT);
        return;
      case JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE_PART_NAME:
        setWsdlMessagePartName(WSDL_MESSAGE_PART_NAME_EDEFAULT);
        return;
      case JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE:
        setWsdlMessage((WSDLMessage)null);
        return;
      case JaxrpcmapPackage.EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER:
        setConstructorParameterOrder((ConstructorParameterOrder)null);
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
      case JaxrpcmapPackage.EXCEPTION_MAPPING__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case JaxrpcmapPackage.EXCEPTION_MAPPING__EXCEPTION_TYPE:
        return EXCEPTION_TYPE_EDEFAULT == null ? exceptionType != null : !EXCEPTION_TYPE_EDEFAULT.equals(exceptionType);
      case JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE_PART_NAME:
        return WSDL_MESSAGE_PART_NAME_EDEFAULT == null ? wsdlMessagePartName != null : !WSDL_MESSAGE_PART_NAME_EDEFAULT.equals(wsdlMessagePartName);
      case JaxrpcmapPackage.EXCEPTION_MAPPING__WSDL_MESSAGE:
        return wsdlMessage != null;
      case JaxrpcmapPackage.EXCEPTION_MAPPING__CONSTRUCTOR_PARAMETER_ORDER:
        return constructorParameterOrder != null;
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
    result.append(", exceptionType: ");//$NON-NLS-1$
    result.append(exceptionType);
    result.append(", wsdlMessagePartName: ");//$NON-NLS-1$
    result.append(wsdlMessagePartName);
    result.append(')');
    return result.toString();
  }

} //ExceptionMappingImpl
