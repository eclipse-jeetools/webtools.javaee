/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enterprise Beans</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The enterprise-beansType declares one or more enterprise
 *         beans. Each bean can be a session, entity or message-driven
 *         bean.
 *         
 *         @since Java EE 5, EJB 3.0
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBeans#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBeans#getSessionBeans <em>Session Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBeans#getEntityBeans <em>Entity Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBeans#getMessageDrivenBeans <em>Message Driven Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBeans#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBeans()
 * @extends JavaEEObject
 * @generated
 */
public interface EnterpriseBeans extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list.
	 * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBeans_Group()
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Session Beans</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.SessionBean}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Session Beans</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Session Beans</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBeans_SessionBeans()
	 * @generated
	 */
	List<SessionBean> getSessionBeans();

	/**
	 * Returns the value of the '<em><b>Entity Beans</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.EntityBean}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entity Beans</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entity Beans</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBeans_EntityBeans()
	 * @generated
	 */
	List<EntityBean> getEntityBeans();

	/**
	 * Returns the value of the '<em><b>Message Driven Beans</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.ejb.MessageDrivenBean}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Message Driven Beans</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Message Driven Beans</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBeans_MessageDrivenBeans()
	 * @generated
	 */
	List<MessageDrivenBean> getMessageDrivenBeans();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBeans_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EnterpriseBeans#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // EnterpriseBeans