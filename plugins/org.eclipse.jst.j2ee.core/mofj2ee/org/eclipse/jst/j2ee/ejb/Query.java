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
package org.eclipse.jst.j2ee.ejb;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.internal.ejb.EjbPackage;

/**
 * The query element is used to specify a finder or select query. It contains an optional description of the query; the specification of the finder or select method it is used by; a specification of the return type mapping, if any, if the query is for a select method; and the EJB QL query string that defines the query. Queries that are expressible in EJB QL must use the ejb-ql element to specify the query. If a query
 * is not expressible in EJB QL, the description element should be used to describe the semantics of the query and the ejb-ql element should be empty.
 */
public interface Query extends EObject{

	public boolean isLocalResultMapping();
	public boolean isRemoteResultMapping();
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 * The description is used by the ejb-jar file producer to provide text describing 
	 * the query.
	 * 
	 * The description should include any information that the ejb-jar file producer
	 * wants to provide to the consumer of the ejb-jar file (i.e. to the Deployer).
	 * Typically, the tools used by the ejb-jar file consumer will display the
	 * description when processing the list of dependents.
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the EjbQL attribute
	 * Contains the EJB QL query string that defines a finder or select query. This
	 * element is defined within the scope of a query element whose contents specify
	 * the finder or the select method that uses the query. The content must be a
	 * valid EJB QL query string for the entity bean for which the query is specified. 
	 * The ejb-ql element must be specified for all queries that are expressible in
	 * EJB QL.
	 * 
	 * @invariant The value must be a valid EJB QL query string for the entity bean or 
	 * dependent object class for which the query is specified.
	 * @invariant The ejb-ql element must be specified for all queries that are
	 * expressible in EJB QL.
	 * 
	 * Example:
	 * <query>
	 * <query-method>
	 * <method-name>ejbSelectPendingLineitems<//method-name>
	 * <method-params//>
	 * <//query-method>
	 * <ejb-ql>SELECT ENTITY(l)
	 * FROM LineItems l
	 * WHERE l.shipped is FALSE
	 * <//ejb-ql>
	 * <//query>
	 */
	String getEjbQL();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the EjbQL attribute
	 */
	void setEjbQL(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ReturnTypeMapping attribute
	 * The return-type-mapping element is used in the query element to specify whether 
	 * an abstract schema type that is returned by a query for a select method is to
	 * be mapped to an EJBLocalObject or EJBObject type.  The value of the
	 * return-type-mapping element, if specified must be one of the following.
	 */
	ReturnTypeMapping getReturnTypeMapping();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Query#getReturnTypeMapping <em>Return Type Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Return Type Mapping</em>' attribute.
	 * @see org.eclipse.jst.j2ee.internal.ejb.ReturnTypeMapping
	 * @see #isSetReturnTypeMapping()
	 * @see #unsetReturnTypeMapping()
	 * @see #getReturnTypeMapping()
	 * @generated
	 */
	void setReturnTypeMapping(ReturnTypeMapping value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Query#getReturnTypeMapping <em>Return Type Mapping</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetReturnTypeMapping()
	 * @see #getReturnTypeMapping()
	 * @see #setReturnTypeMapping(ReturnTypeMapping)
	 * @generated
	 */
	void unsetReturnTypeMapping();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Query#getReturnTypeMapping <em>Return Type Mapping</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Return Type Mapping</em>' attribute is set.
	 * @see #unsetReturnTypeMapping()
	 * @see #getReturnTypeMapping()
	 * @see #setReturnTypeMapping(ReturnTypeMapping)
	 * @generated
	 */
	boolean isSetReturnTypeMapping();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The QueryMethod reference
	 * The query-method element is used to specify the method (DB//SR//CM-"on the
	 * home") for a finder or
	 * select query.
	 * 
	 * The method-name element specifies the name of a finder or select
	 * method in the entity bean's implementation class or a select method in
	 * the dependent object class.
	 * 
	 * Each method-param must be defined for a query-method using the
	 * method-params element.
	 * 
	 * Used in: query
	 * 
	 * Example:
	 * 
	 * Example:
	 * <query>
	 *   <description>Method finds large orders<//description>
	 *   <query-method>
	 *     <method-name>findLargeOrders<//method-name>
	 *     <method-params><//method-params>
	 *   <//query-method>
	 *   <ejb-ql>SELECT ENTITY(o) FROM Order o WHERE o.amount &gt; 1000<//ejb-ql>
	 * <//query>
	 */
	QueryMethod getQueryMethod();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the QueryMethod reference
	 */
	void setQueryMethod(QueryMethod value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The Entity reference
	 */
	ContainerManagedEntity getEntity();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.ejb.Query#getEntity <em>Entity</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Entity</em>' container reference.
	 * @see #getEntity()
	 * @generated
	 */
	void setEntity(ContainerManagedEntity value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.ejb.EjbPackage#getQuery_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.internal.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





