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

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Java XML Type Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The java-xml-type-mapping element contains a class-type that is the fully qualified name of the Java class, QName of the XML root type, the WSDL type scope the QName applies to and the set of variable 
 * mappings for each public variable within the Java class.
 * 
 * Used in: java-wsdl-mapping
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getClassType <em>Class Type</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getQnameScope <em>Qname Scope</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getAnonymousTypeQname <em>Anonymous Type Qname</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getRootTypeQname <em>Root Type Qname</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getVariableMappings <em>Variable Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getJavaXMLTypeMapping()
 * @model
 * @generated
 * @since 1.0 */
public interface JavaXMLTypeMapping extends EObject{
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
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getJavaXMLTypeMapping_Id()
	 * @model
	 * @generated
	 */
  String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
  void setId(String value);

	/**
	 * Returns the value of the '<em><b>Class Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Class Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The class-type element is the fully qualified class name of a Java class.
	 * 
	 * Used in: java-xml-type-mapping
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Class Type</em>' attribute.
	 * @see #setClassType(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getJavaXMLTypeMapping_ClassType()
	 * @model
	 * @generated
	 */
  String getClassType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getClassType <em>Class Type</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Class Type</em>' attribute.
	 * @see #getClassType()
	 * @generated
	 */
  void setClassType(String value);

	/**
	 * Returns the value of the '<em><b>Qname Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Qname Scope</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The qname-scope elements scopes the reference of a QName to the WSDL element type it applies to. The value of qname-scope may be simpleType, complexType, or element.
	 * 
	 * Used in: java-xml-type-mapping
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Qname Scope</em>' attribute.
	 * @see #setQnameScope(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getJavaXMLTypeMapping_QnameScope()
	 * @model
	 * @generated
	 */
  String getQnameScope();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getQnameScope <em>Qname Scope</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Qname Scope</em>' attribute.
	 * @see #getQnameScope()
	 * @generated
	 */
  void setQnameScope(String value);

	/**
	 * Returns the value of the '<em><b>Anonymous Type Qname</b></em>' attribute.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Anonymous Type Qname</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Anonymous Type Qname</em>' attribute.
	 * @see #setAnonymousTypeQname(String)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getJavaXMLTypeMapping_AnonymousTypeQname()
	 * @model
	 * @generated
	 */
  String getAnonymousTypeQname();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getAnonymousTypeQname <em>Anonymous Type Qname</em>}' attribute.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Anonymous Type Qname</em>' attribute.
	 * @see #getAnonymousTypeQname()
	 * @generated
	 */
  void setAnonymousTypeQname(String value);

	/**
	 * Returns the value of the '<em><b>Root Type Qname</b></em>' containment reference.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Root Type Qname</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Root Type Qname</em>' containment reference.
	 * @see #setRootTypeQname(RootTypeQname)
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getJavaXMLTypeMapping_RootTypeQname()
	 * @model containment="true" required="true"
	 * @generated
	 */
  RootTypeQname getRootTypeQname();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.JavaXMLTypeMapping#getRootTypeQname <em>Root Type Qname</em>}' containment reference.
	 * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Root Type Qname</em>' containment reference.
	 * @see #getRootTypeQname()
	 * @generated
	 */
  void setRootTypeQname(RootTypeQname value);

	/**
	 * Returns the value of the '<em><b>Variable Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping}.
	 * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Variable Mappings</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
	 * @return the value of the '<em>Variable Mappings</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.webservice.jaxrpcmap.JaxrpcmapPackage#getJavaXMLTypeMapping_VariableMappings()
	 * @model type="org.eclipse.jst.j2ee.webservice.jaxrpcmap.VariableMapping" containment="true"
	 * @generated
	 */
  EList getVariableMappings();

} // JavaXMLTypeMapping
