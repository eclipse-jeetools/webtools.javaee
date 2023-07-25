/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The queryType defines a finder or select
 *         query. It contains
 *         - an optional description of the query
 *         - the specification of the finder or select
 *         method it is used by
 *         	- an optional specification of the result type 
 *         	  mapping, if the query is for a select method 
 *         	  and entity objects are returned.
 *         	- the EJB QL query string that defines the query.
 *         
 *         Queries that are expressible in EJB QL must use the ejb-ql
 *         element to specify the query. If a query is not expressible
 *         in EJB QL, the description element should be used to
 *         describe the semantics of the query and the ejb-ql element
 *         should be empty.
 *         
 *         The result-type-mapping is an optional element. It can only
 *         be present if the query-method specifies a select method
 *         that returns entity objects.  The default value for the
 *         result-type-mapping element is "Local".
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.Query#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.Query#getQueryMethod <em>Query Method</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.Query#getResultTypeMapping <em>Result Type Mapping</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.Query#getEjbQl <em>Ejb Ql</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.Query#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getQuery()
 * @extends JavaEEObject
 * @generated
 */
public interface Query extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference.
	 * @see #setDescription(Description)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getQuery_Description()
	 * @generated
	 */
	Description getDescription();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.Query#getDescription <em>Description</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' containment reference.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(Description value);

	/**
	 * Returns the value of the '<em><b>Query Method</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Query Method</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Query Method</em>' containment reference.
	 * @see #setQueryMethod(QueryMethod)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getQuery_QueryMethod()
	 * @generated
	 */
	QueryMethod getQueryMethod();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.Query#getQueryMethod <em>Query Method</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Query Method</em>' containment reference.
	 * @see #getQueryMethod()
	 * @generated
	 */
	void setQueryMethod(QueryMethod value);

	/**
	 * Returns the value of the '<em><b>Result Type Mapping</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.ejb.ResultTypeMappingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Result Type Mapping</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Result Type Mapping</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.ResultTypeMappingType
	 * @see #isSetResultTypeMapping()
	 * @see #unsetResultTypeMapping()
	 * @see #setResultTypeMapping(ResultTypeMappingType)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getQuery_ResultTypeMapping()
	 * @generated
	 */
	ResultTypeMappingType getResultTypeMapping();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.Query#getResultTypeMapping <em>Result Type Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Result Type Mapping</em>' attribute.
	 * @see org.eclipse.jst.javaee.ejb.ResultTypeMappingType
	 * @see #isSetResultTypeMapping()
	 * @see #unsetResultTypeMapping()
	 * @see #getResultTypeMapping()
	 * @generated
	 */
	void setResultTypeMapping(ResultTypeMappingType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.ejb.Query#getResultTypeMapping <em>Result Type Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetResultTypeMapping()
	 * @see #getResultTypeMapping()
	 * @see #setResultTypeMapping(ResultTypeMappingType)
	 * @generated
	 */
	void unsetResultTypeMapping();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.ejb.Query#getResultTypeMapping <em>Result Type Mapping</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Result Type Mapping</em>' attribute is set.
	 * @see #unsetResultTypeMapping()
	 * @see #getResultTypeMapping()
	 * @see #setResultTypeMapping(ResultTypeMappingType)
	 * @generated
	 */
	boolean isSetResultTypeMapping();

	/**
	 * Returns the value of the '<em><b>Ejb Ql</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ejb Ql</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ejb Ql</em>' attribute.
	 * @see #setEjbQl(String)
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getQuery_EjbQl()
	 * @generated
	 */
	String getEjbQl();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.Query#getEjbQl <em>Ejb Ql</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ejb Ql</em>' attribute.
	 * @see #getEjbQl()
	 * @generated
	 */
	void setEjbQl(String value);

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getQuery_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.Query#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // Query