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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.j2ee.webservice.internal.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.MethodParamPartsMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.ServiceEndpointMethodMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.WSDLReturnValueMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service Endpoint Method Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointMethodMappingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointMethodMappingImpl#getJavaMethodName <em>Java Method Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointMethodMappingImpl#getWsdlOperation <em>Wsdl Operation</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointMethodMappingImpl#isWrappedElement <em>Wrapped Element</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointMethodMappingImpl#getMethodParamPartsMappings <em>Method Param Parts Mappings</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.ServiceEndpointMethodMappingImpl#getWsdlReturnValueMapping <em>Wsdl Return Value Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceEndpointMethodMappingImpl extends EObjectImpl implements ServiceEndpointMethodMapping
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
   * The default value of the '{@link #getJavaMethodName() <em>Java Method Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaMethodName()
   * @generated
   * @ordered
   */
  protected static final String JAVA_METHOD_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getJavaMethodName() <em>Java Method Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJavaMethodName()
   * @generated
   * @ordered
   */
  protected String javaMethodName = JAVA_METHOD_NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getWsdlOperation() <em>Wsdl Operation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWsdlOperation()
   * @generated
   * @ordered
   */
  protected static final String WSDL_OPERATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWsdlOperation() <em>Wsdl Operation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWsdlOperation()
   * @generated
   * @ordered
   */
  protected String wsdlOperation = WSDL_OPERATION_EDEFAULT;

  /**
   * The default value of the '{@link #isWrappedElement() <em>Wrapped Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isWrappedElement()
   * @generated
   * @ordered
   */
  protected static final boolean WRAPPED_ELEMENT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isWrappedElement() <em>Wrapped Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isWrappedElement()
   * @generated
   * @ordered
   */
  protected boolean wrappedElement = WRAPPED_ELEMENT_EDEFAULT;

  /**
   * This is true if the Wrapped Element attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean wrappedElementESet = false;

  /**
   * The cached value of the '{@link #getMethodParamPartsMappings() <em>Method Param Parts Mappings</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMethodParamPartsMappings()
   * @generated
   * @ordered
   */
  protected EList methodParamPartsMappings = null;

  /**
   * The cached value of the '{@link #getWsdlReturnValueMapping() <em>Wsdl Return Value Mapping</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWsdlReturnValueMapping()
   * @generated
   * @ordered
   */
  protected WSDLReturnValueMapping wsdlReturnValueMapping = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ServiceEndpointMethodMappingImpl()
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
    return JaxrpcmapPackage.eINSTANCE.getServiceEndpointMethodMapping();
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
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getJavaMethodName()
  {
    return javaMethodName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJavaMethodName(String newJavaMethodName)
  {
    String oldJavaMethodName = javaMethodName;
    javaMethodName = newJavaMethodName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__JAVA_METHOD_NAME, oldJavaMethodName, javaMethodName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getWsdlOperation()
  {
    return wsdlOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWsdlOperation(String newWsdlOperation)
  {
    String oldWsdlOperation = wsdlOperation;
    wsdlOperation = newWsdlOperation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_OPERATION, oldWsdlOperation, wsdlOperation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isWrappedElement()
  {
    return wrappedElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWrappedElement(boolean newWrappedElement)
  {
    boolean oldWrappedElement = wrappedElement;
    wrappedElement = newWrappedElement;
    boolean oldWrappedElementESet = wrappedElementESet;
    wrappedElementESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WRAPPED_ELEMENT, oldWrappedElement, wrappedElement, !oldWrappedElementESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetWrappedElement()
  {
    boolean oldWrappedElement = wrappedElement;
    boolean oldWrappedElementESet = wrappedElementESet;
    wrappedElement = WRAPPED_ELEMENT_EDEFAULT;
    wrappedElementESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WRAPPED_ELEMENT, oldWrappedElement, WRAPPED_ELEMENT_EDEFAULT, oldWrappedElementESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetWrappedElement()
  {
    return wrappedElementESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getMethodParamPartsMappings()
  {
    if (methodParamPartsMappings == null)
    {
      methodParamPartsMappings = new EObjectContainmentEList(MethodParamPartsMapping.class, this, JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__METHOD_PARAM_PARTS_MAPPINGS);
    }
    return methodParamPartsMappings;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WSDLReturnValueMapping getWsdlReturnValueMapping()
  {
    return wsdlReturnValueMapping;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetWsdlReturnValueMapping(WSDLReturnValueMapping newWsdlReturnValueMapping, NotificationChain msgs)
  {
    WSDLReturnValueMapping oldWsdlReturnValueMapping = wsdlReturnValueMapping;
    wsdlReturnValueMapping = newWsdlReturnValueMapping;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING, oldWsdlReturnValueMapping, newWsdlReturnValueMapping);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWsdlReturnValueMapping(WSDLReturnValueMapping newWsdlReturnValueMapping)
  {
    if (newWsdlReturnValueMapping != wsdlReturnValueMapping)
    {
      NotificationChain msgs = null;
      if (wsdlReturnValueMapping != null)
        msgs = ((InternalEObject)wsdlReturnValueMapping).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING, null, msgs);
      if (newWsdlReturnValueMapping != null)
        msgs = ((InternalEObject)newWsdlReturnValueMapping).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING, null, msgs);
      msgs = basicSetWsdlReturnValueMapping(newWsdlReturnValueMapping, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING, newWsdlReturnValueMapping, newWsdlReturnValueMapping));
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
        case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__METHOD_PARAM_PARTS_MAPPINGS:
          return ((InternalEList)getMethodParamPartsMappings()).basicRemove(otherEnd, msgs);
        case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING:
          return basicSetWsdlReturnValueMapping(null, msgs);
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
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__ID:
        return getId();
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__JAVA_METHOD_NAME:
        return getJavaMethodName();
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_OPERATION:
        return getWsdlOperation();
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WRAPPED_ELEMENT:
        return isWrappedElement() ? Boolean.TRUE : Boolean.FALSE;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__METHOD_PARAM_PARTS_MAPPINGS:
        return getMethodParamPartsMappings();
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING:
        return getWsdlReturnValueMapping();
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
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__ID:
        setId((String)newValue);
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__JAVA_METHOD_NAME:
        setJavaMethodName((String)newValue);
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_OPERATION:
        setWsdlOperation((String)newValue);
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WRAPPED_ELEMENT:
        setWrappedElement(((Boolean)newValue).booleanValue());
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__METHOD_PARAM_PARTS_MAPPINGS:
        getMethodParamPartsMappings().clear();
        getMethodParamPartsMappings().addAll((Collection)newValue);
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING:
        setWsdlReturnValueMapping((WSDLReturnValueMapping)newValue);
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
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__ID:
        setId(ID_EDEFAULT);
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__JAVA_METHOD_NAME:
        setJavaMethodName(JAVA_METHOD_NAME_EDEFAULT);
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_OPERATION:
        setWsdlOperation(WSDL_OPERATION_EDEFAULT);
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WRAPPED_ELEMENT:
        unsetWrappedElement();
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__METHOD_PARAM_PARTS_MAPPINGS:
        getMethodParamPartsMappings().clear();
        return;
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING:
        setWsdlReturnValueMapping((WSDLReturnValueMapping)null);
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
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__JAVA_METHOD_NAME:
        return JAVA_METHOD_NAME_EDEFAULT == null ? javaMethodName != null : !JAVA_METHOD_NAME_EDEFAULT.equals(javaMethodName);
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_OPERATION:
        return WSDL_OPERATION_EDEFAULT == null ? wsdlOperation != null : !WSDL_OPERATION_EDEFAULT.equals(wsdlOperation);
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WRAPPED_ELEMENT:
        return isSetWrappedElement();
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__METHOD_PARAM_PARTS_MAPPINGS:
        return methodParamPartsMappings != null && !methodParamPartsMappings.isEmpty();
      case JaxrpcmapPackage.SERVICE_ENDPOINT_METHOD_MAPPING__WSDL_RETURN_VALUE_MAPPING:
        return wsdlReturnValueMapping != null;
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
    result.append(", javaMethodName: ");//$NON-NLS-1$
    result.append(javaMethodName);
    result.append(", wsdlOperation: ");//$NON-NLS-1$
    result.append(wsdlOperation);
    result.append(", wrappedElement: ");//$NON-NLS-1$
    if (wrappedElementESet) result.append(wrappedElement); else result.append("<unset>");//$NON-NLS-1$
    result.append(')');
    return result.toString();
  }

} //ServiceEndpointMethodMappingImpl
