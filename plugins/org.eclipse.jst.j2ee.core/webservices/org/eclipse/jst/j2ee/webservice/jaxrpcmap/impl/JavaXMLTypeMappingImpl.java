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
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.RootTypeQname;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Java XML Type Mapping</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaXMLTypeMappingImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaXMLTypeMappingImpl#getClassType <em>Class Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaXMLTypeMappingImpl#getQnameScope <em>Qname Scope</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaXMLTypeMappingImpl#getAnonymousTypeQname <em>Anonymous Type Qname</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaXMLTypeMappingImpl#getRootTypeQname <em>Root Type Qname</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.JavaXMLTypeMappingImpl#getVariableMappings <em>Variable Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaXMLTypeMappingImpl extends EObjectImpl implements JavaXMLTypeMapping
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
   * The default value of the '{@link #getClassType() <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassType()
   * @generated
   * @ordered
   */
  protected static final String CLASS_TYPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getClassType() <em>Class Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getClassType()
   * @generated
   * @ordered
   */
  protected String classType = CLASS_TYPE_EDEFAULT;

  /**
   * The default value of the '{@link #getQnameScope() <em>Qname Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQnameScope()
   * @generated
   * @ordered
   */
  protected static final String QNAME_SCOPE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getQnameScope() <em>Qname Scope</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQnameScope()
   * @generated
   * @ordered
   */
  protected String qnameScope = QNAME_SCOPE_EDEFAULT;

  /**
   * The default value of the '{@link #getAnonymousTypeQname() <em>Anonymous Type Qname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnonymousTypeQname()
   * @generated
   * @ordered
   */
  protected static final String ANONYMOUS_TYPE_QNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAnonymousTypeQname() <em>Anonymous Type Qname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnonymousTypeQname()
   * @generated
   * @ordered
   */
  protected String anonymousTypeQname = ANONYMOUS_TYPE_QNAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getRootTypeQname() <em>Root Type Qname</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRootTypeQname()
   * @generated
   * @ordered
   */
  protected RootTypeQname rootTypeQname = null;

  /**
   * The cached value of the '{@link #getVariableMappings() <em>Variable Mappings</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariableMappings()
   * @generated
   * @ordered
   */
  protected EList variableMappings = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JavaXMLTypeMappingImpl()
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
    return JaxrpcmapPackage.eINSTANCE.getJavaXMLTypeMapping();
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
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getClassType()
  {
    return classType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setClassType(String newClassType)
  {
    String oldClassType = classType;
    classType = newClassType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__CLASS_TYPE, oldClassType, classType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getQnameScope()
  {
    return qnameScope;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setQnameScope(String newQnameScope)
  {
    String oldQnameScope = qnameScope;
    qnameScope = newQnameScope;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__QNAME_SCOPE, oldQnameScope, qnameScope));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAnonymousTypeQname()
  {
    return anonymousTypeQname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnonymousTypeQname(String newAnonymousTypeQname)
  {
    String oldAnonymousTypeQname = anonymousTypeQname;
    anonymousTypeQname = newAnonymousTypeQname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ANONYMOUS_TYPE_QNAME, oldAnonymousTypeQname, anonymousTypeQname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RootTypeQname getRootTypeQname()
  {
    return rootTypeQname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetRootTypeQname(RootTypeQname newRootTypeQname, NotificationChain msgs)
  {
    RootTypeQname oldRootTypeQname = rootTypeQname;
    rootTypeQname = newRootTypeQname;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME, oldRootTypeQname, newRootTypeQname);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRootTypeQname(RootTypeQname newRootTypeQname)
  {
    if (newRootTypeQname != rootTypeQname)
    {
      NotificationChain msgs = null;
      if (rootTypeQname != null)
        msgs = ((InternalEObject)rootTypeQname).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME, null, msgs);
      if (newRootTypeQname != null)
        msgs = ((InternalEObject)newRootTypeQname).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME, null, msgs);
      msgs = basicSetRootTypeQname(newRootTypeQname, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME, newRootTypeQname, newRootTypeQname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getVariableMappings()
  {
    if (variableMappings == null)
    {
      variableMappings = new EObjectContainmentEList(VariableMapping.class, this, JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__VARIABLE_MAPPINGS);
    }
    return variableMappings;
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
        case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME:
          return basicSetRootTypeQname(null, msgs);
        case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__VARIABLE_MAPPINGS:
          return ((InternalEList)getVariableMappings()).basicRemove(otherEnd, msgs);
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
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ID:
        return getId();
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__CLASS_TYPE:
        return getClassType();
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__QNAME_SCOPE:
        return getQnameScope();
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ANONYMOUS_TYPE_QNAME:
        return getAnonymousTypeQname();
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME:
        return getRootTypeQname();
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__VARIABLE_MAPPINGS:
        return getVariableMappings();
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
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ID:
        setId((String)newValue);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__CLASS_TYPE:
        setClassType((String)newValue);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__QNAME_SCOPE:
        setQnameScope((String)newValue);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ANONYMOUS_TYPE_QNAME:
        setAnonymousTypeQname((String)newValue);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME:
        setRootTypeQname((RootTypeQname)newValue);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__VARIABLE_MAPPINGS:
        getVariableMappings().clear();
        getVariableMappings().addAll((Collection)newValue);
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
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ID:
        setId(ID_EDEFAULT);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__CLASS_TYPE:
        setClassType(CLASS_TYPE_EDEFAULT);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__QNAME_SCOPE:
        setQnameScope(QNAME_SCOPE_EDEFAULT);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ANONYMOUS_TYPE_QNAME:
        setAnonymousTypeQname(ANONYMOUS_TYPE_QNAME_EDEFAULT);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME:
        setRootTypeQname((RootTypeQname)null);
        return;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__VARIABLE_MAPPINGS:
        getVariableMappings().clear();
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
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__CLASS_TYPE:
        return CLASS_TYPE_EDEFAULT == null ? classType != null : !CLASS_TYPE_EDEFAULT.equals(classType);
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__QNAME_SCOPE:
        return QNAME_SCOPE_EDEFAULT == null ? qnameScope != null : !QNAME_SCOPE_EDEFAULT.equals(qnameScope);
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ANONYMOUS_TYPE_QNAME:
        return ANONYMOUS_TYPE_QNAME_EDEFAULT == null ? anonymousTypeQname != null : !ANONYMOUS_TYPE_QNAME_EDEFAULT.equals(anonymousTypeQname);
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__ROOT_TYPE_QNAME:
        return rootTypeQname != null;
      case JaxrpcmapPackage.JAVA_XML_TYPE_MAPPING__VARIABLE_MAPPINGS:
        return variableMappings != null && !variableMappings.isEmpty();
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
    result.append(", classType: ");//$NON-NLS-1$
    result.append(classType);
    result.append(", qnameScope: ");//$NON-NLS-1$
    result.append(qnameScope);
    result.append(", anonymousTypeQname: ");//$NON-NLS-1$
    result.append(anonymousTypeQname);
    result.append(')');
    return result.toString();
  }

} //JavaXMLTypeMappingImpl
