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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.internal.common.impl.QNameImpl;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage;
import org.eclipse.jst.j2ee.webservice.jaxrpcmap.RootTypeQname;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root Type Qname</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class RootTypeQnameImpl extends QNameImpl implements RootTypeQname
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RootTypeQnameImpl()
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
    return JaxrpcmapPackage.eINSTANCE.getRootTypeQname();
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
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__NAMESPACE_URI:
        return getNamespaceURI();
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__LOCAL_PART:
        return getLocalPart();
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__COMBINED_QNAME:
        return getCombinedQName();
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__INTERNAL_PREFIX_OR_NS_URI:
        return getInternalPrefixOrNsURI();
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
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__NAMESPACE_URI:
        setNamespaceURI((String)newValue);
        return;
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__LOCAL_PART:
        setLocalPart((String)newValue);
        return;
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__COMBINED_QNAME:
        setCombinedQName((String)newValue);
        return;
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__INTERNAL_PREFIX_OR_NS_URI:
        setInternalPrefixOrNsURI((String)newValue);
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
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__NAMESPACE_URI:
        setNamespaceURI(NAMESPACE_URI_EDEFAULT);
        return;
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__LOCAL_PART:
        setLocalPart(LOCAL_PART_EDEFAULT);
        return;
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__COMBINED_QNAME:
        setCombinedQName(COMBINED_QNAME_EDEFAULT);
        return;
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__INTERNAL_PREFIX_OR_NS_URI:
        setInternalPrefixOrNsURI(INTERNAL_PREFIX_OR_NS_URI_EDEFAULT);
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
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__NAMESPACE_URI:
        return NAMESPACE_URI_EDEFAULT == null ? namespaceURI != null : !NAMESPACE_URI_EDEFAULT.equals(namespaceURI);
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__LOCAL_PART:
        return LOCAL_PART_EDEFAULT == null ? localPart != null : !LOCAL_PART_EDEFAULT.equals(localPart);
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__COMBINED_QNAME:
        return COMBINED_QNAME_EDEFAULT == null ? combinedQName != null : !COMBINED_QNAME_EDEFAULT.equals(combinedQName);
      case JaxrpcmapPackage.ROOT_TYPE_QNAME__INTERNAL_PREFIX_OR_NS_URI:
        return INTERNAL_PREFIX_OR_NS_URI_EDEFAULT == null ? internalPrefixOrNsURI != null : !INTERNAL_PREFIX_OR_NS_URI_EDEFAULT.equals(internalPrefixOrNsURI);
    }
    return eDynamicIsSet(eFeature);
  }

} //RootTypeQnameImpl
