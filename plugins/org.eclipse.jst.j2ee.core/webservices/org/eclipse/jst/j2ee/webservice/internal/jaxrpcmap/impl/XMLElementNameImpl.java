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
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.XMLElementName;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XML Element Name</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.XMLElementNameImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.webservice.jaxrpcmap.util.XMLElementNameImpl#getXmlElementName <em>Xml Element Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XMLElementNameImpl extends EObjectImpl implements XMLElementName
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
   * The default value of the '{@link #getXmlElementName() <em>Xml Element Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlElementName()
   * @generated
   * @ordered
   */
  protected static final String XML_ELEMENT_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getXmlElementName() <em>Xml Element Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXmlElementName()
   * @generated
   * @ordered
   */
  protected String xmlElementName = XML_ELEMENT_NAME_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XMLElementNameImpl()
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
    return JaxrpcmapPackage.eINSTANCE.getXMLElementName();
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
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.XML_ELEMENT_NAME__ID, oldId, id));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getXmlElementName()
  {
    return xmlElementName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setXmlElementName(String newXmlElementName)
  {
    String oldXmlElementName = xmlElementName;
    xmlElementName = newXmlElementName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JaxrpcmapPackage.XML_ELEMENT_NAME__XML_ELEMENT_NAME, oldXmlElementName, xmlElementName));
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
      case JaxrpcmapPackage.XML_ELEMENT_NAME__ID:
        return getId();
      case JaxrpcmapPackage.XML_ELEMENT_NAME__XML_ELEMENT_NAME:
        return getXmlElementName();
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
      case JaxrpcmapPackage.XML_ELEMENT_NAME__ID:
        setId((String)newValue);
        return;
      case JaxrpcmapPackage.XML_ELEMENT_NAME__XML_ELEMENT_NAME:
        setXmlElementName((String)newValue);
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
      case JaxrpcmapPackage.XML_ELEMENT_NAME__ID:
        setId(ID_EDEFAULT);
        return;
      case JaxrpcmapPackage.XML_ELEMENT_NAME__XML_ELEMENT_NAME:
        setXmlElementName(XML_ELEMENT_NAME_EDEFAULT);
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
      case JaxrpcmapPackage.XML_ELEMENT_NAME__ID:
        return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
      case JaxrpcmapPackage.XML_ELEMENT_NAME__XML_ELEMENT_NAME:
        return XML_ELEMENT_NAME_EDEFAULT == null ? xmlElementName != null : !XML_ELEMENT_NAME_EDEFAULT.equals(xmlElementName);
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
    result.append(", xmlElementName: ");//$NON-NLS-1$
    result.append(xmlElementName);
    result.append(')');
    return result.toString();
  }

} //XMLElementNameImpl
