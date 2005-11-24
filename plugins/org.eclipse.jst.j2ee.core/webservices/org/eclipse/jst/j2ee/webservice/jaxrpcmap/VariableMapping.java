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
 * A representation of the model object '<em><b>Variable Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The variable-mapping element defines the correlation between a Java class data member or JavaBeans property to an XML element name of an XML root type. If the data-member element is present, the Java variable name is a public data member.  If data-member
 * is not present, the Java variable name is a JavaBeans property.
 * 
 * Used in: java-xml-type-mapping
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#getJavaVariableName <em>Java Variable Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#getXmlElementName <em>Xml Element Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#isDataMember <em>Data Member</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#getXmlAttributeName <em>Xml Attribute Name</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#isXmlWildcard <em>Xml Wildcard</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getVariableMapping()
 * @model
 * @generated
 * @since 1.0 */
public interface VariableMapping extends EObject{
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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getVariableMapping_Id()
	 * @model
	 * @generated
	 */
  String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
  void setId(String value);

	/**
	 * Returns the value of the '<em><b>Java Variable Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Java Variable Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The java-variable-name defines the name of a public data member or  JavaBeans property within a Java class. 
	 * 
	 * Used in: variable-mapping
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Java Variable Name</em>' attribute.
	 * @see #setJavaVariableName(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getVariableMapping_JavaVariableName()
	 * @model
	 * @generated
	 */
  String getJavaVariableName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#getJavaVariableName <em>Java Variable Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Java Variable Name</em>' attribute.
	 * @see #getJavaVariableName()
	 * @generated
	 */
  void setJavaVariableName(String value);

	/**
	 * Returns the value of the '<em><b>Xml Element Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xml Element Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The xml-element-name element defines name attribute value of a WSDL element within a root type.
	 * 
	 * Used in: variable-mapping
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xml Element Name</em>' attribute.
	 * @see #setXmlElementName(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getVariableMapping_XmlElementName()
	 * @model
	 * @generated
	 */
  String getXmlElementName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#getXmlElementName <em>Xml Element Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml Element Name</em>' attribute.
	 * @see #getXmlElementName()
	 * @generated
	 */
  void setXmlElementName(String value);

	/**
	 * Returns the value of the '<em><b>Data Member</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Data Member</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Member</em>' attribute.
	 * @see #isSetDataMember()
	 * @see #unsetDataMember()
	 * @see #setDataMember(boolean)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getVariableMapping_DataMember()
	 * @model unsettable="true"
	 * @generated
	 */
  boolean isDataMember();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#isDataMember <em>Data Member</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Member</em>' attribute.
	 * @see #isSetDataMember()
	 * @see #unsetDataMember()
	 * @see #isDataMember()
	 * @generated
	 */
  void setDataMember(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#isDataMember <em>Data Member</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isSetDataMember()
	 * @see #isDataMember()
	 * @see #setDataMember(boolean)
	 * @generated
	 */
  void unsetDataMember();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#isDataMember <em>Data Member</em>}' attribute is set.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Data Member</em>' attribute is set.
	 * @see #unsetDataMember()
	 * @see #isDataMember()
	 * @see #setDataMember(boolean)
	 * @generated
	 */
  boolean isSetDataMember();

	/**
	 * Returns the value of the '<em><b>Xml Attribute Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xml Attribute Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Attribute Name</em>' attribute.
	 * @see #setXmlAttributeName(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getVariableMapping_XmlAttributeName()
	 * @model
	 * @generated
	 */
  String getXmlAttributeName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#getXmlAttributeName <em>Xml Attribute Name</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml Attribute Name</em>' attribute.
	 * @see #getXmlAttributeName()
	 * @generated
	 */
  void setXmlAttributeName(String value);

	/**
	 * Returns the value of the '<em><b>Xml Wildcard</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Xml Wildcard</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Xml Wildcard</em>' attribute.
	 * @see #isSetXmlWildcard()
	 * @see #unsetXmlWildcard()
	 * @see #setXmlWildcard(boolean)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getVariableMapping_XmlWildcard()
	 * @model unsettable="true"
	 * @generated
	 */
  boolean isXmlWildcard();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#isXmlWildcard <em>Xml Wildcard</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml Wildcard</em>' attribute.
	 * @see #isSetXmlWildcard()
	 * @see #unsetXmlWildcard()
	 * @see #isXmlWildcard()
	 * @generated
	 */
  void setXmlWildcard(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#isXmlWildcard <em>Xml Wildcard</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @see #isSetXmlWildcard()
	 * @see #isXmlWildcard()
	 * @see #setXmlWildcard(boolean)
	 * @generated
	 */
  void unsetXmlWildcard();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping#isXmlWildcard <em>Xml Wildcard</em>}' attribute is set.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Xml Wildcard</em>' attribute is set.
	 * @see #unsetXmlWildcard()
	 * @see #isXmlWildcard()
	 * @see #setXmlWildcard(boolean)
	 * @generated
	 */
  boolean isSetXmlWildcard();

} // VariableMapping
