/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Adapter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The resourceadapterType specifies information about the
 *         resource adapter. The information includes fully qualified
 *         resource adapter Java class name, configuration properties,
 *         information specific to the implementation of the resource
 *         adapter library as specified through the
 *         outbound-resourceadapter and inbound-resourceadapter
 *         elements, and an optional set of administered objects.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getResourceadapterClass <em>Resourceadapter Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getConfigProperty <em>Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getOutboundResourceadapter <em>Outbound Resourceadapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getInboundResourceadapter <em>Inbound Resourceadapter</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getAdminobject <em>Adminobject</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getSecurityPermission <em>Security Permission</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getResourceAdapter()
 * @extends JavaEEObject
 * @generated
 */
public interface ResourceAdapter extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Resourceadapter Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element resourceadapter-class specifies the
	 *             fully qualified name of a Java class that implements
	 *             the javax.resource.spi.ResourceAdapter
	 *             interface. This Java class is provided as part of
	 *             resource adapter's implementation of connector
	 *             architecture specified contracts. The implementation
	 *             of this class is required to be a JavaBean.
	 * 
	 * 			@since Java EE 6, Connector 1.6            
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resourceadapter Class</em>' attribute.
	 * @see #setResourceadapterClass(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getResourceAdapter_ResourceadapterClass()
	 * @generated
	 */
	String getResourceadapterClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getResourceadapterClass <em>Resourceadapter Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Resourceadapter Class</em>' attribute.
	 * @see #getResourceadapterClass()
	 * @generated
	 */
	void setResourceadapterClass(String value);

	/**
	 * Returns the value of the '<em><b>Config Property</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jca.ConfigProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Property</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config Property</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getResourceAdapter_ConfigProperty()
	 * @generated
	 */
	List<ConfigProperty> getConfigProperty();

	/**
	 * Returns the value of the '<em><b>Outbound Resourceadapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Outbound Resourceadapter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Outbound Resourceadapter</em>' containment reference.
	 * @see #setOutboundResourceadapter(OutboundResourceAdapter)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getResourceAdapter_OutboundResourceadapter()
	 * @generated
	 */
	OutboundResourceAdapter getOutboundResourceadapter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getOutboundResourceadapter <em>Outbound Resourceadapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Outbound Resourceadapter</em>' containment reference.
	 * @see #getOutboundResourceadapter()
	 * @generated
	 */
	void setOutboundResourceadapter(OutboundResourceAdapter value);

	/**
	 * Returns the value of the '<em><b>Inbound Resourceadapter</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inbound Resourceadapter</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inbound Resourceadapter</em>' containment reference.
	 * @see #setInboundResourceadapter(InboundResourceAdapter)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getResourceAdapter_InboundResourceadapter()
	 * @generated
	 */
	InboundResourceAdapter getInboundResourceadapter();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getInboundResourceadapter <em>Inbound Resourceadapter</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inbound Resourceadapter</em>' containment reference.
	 * @see #getInboundResourceadapter()
	 * @generated
	 */
	void setInboundResourceadapter(InboundResourceAdapter value);

	/**
	 * Returns the value of the '<em><b>Adminobject</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jca.AdminObject}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Adminobject</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Adminobject</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getResourceAdapter_Adminobject()
	 * @generated
	 */
	List<AdminObject> getAdminobject();

	/**
	 * Returns the value of the '<em><b>Security Permission</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.jca.SecurityPermission}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Security Permission</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Security Permission</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getResourceAdapter_SecurityPermission()
	 * @generated
	 */
	List<SecurityPermission> getSecurityPermission();

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getResourceAdapter_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ResourceAdapter#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ResourceAdapter
