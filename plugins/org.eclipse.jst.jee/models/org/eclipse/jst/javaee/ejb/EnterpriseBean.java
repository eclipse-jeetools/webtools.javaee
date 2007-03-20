/**
 * <copyright>
 * </copyright>
 *
 * $Id: EnterpriseBean.java,v 1.1 2007/03/20 18:04:36 jsholl Exp $
 */
package org.eclipse.jst.javaee.ejb;

import java.util.List;

import org.eclipse.emf.ecore.util.FeatureMap;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enterprise Bean</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 * 	The enterprise-beansType declares one or more enterprise
 * 	beans. Each bean can be a session, entity or message-driven
 * 	bean.
 * 
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBean#getGroup <em>Group</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBean#getSessionBeans <em>Session Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBean#getEntityBeans <em>Entity Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBean#getMessageDrivenBeans <em>Message Driven Beans</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.ejb.EnterpriseBean#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBean()
 * @extends JavaEEObject
 * @generated
 */
public interface EnterpriseBean extends JavaEEObject {
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBean_Group()
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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBean_SessionBeans()
	 * @generated
	 */
	List getSessionBeans();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBean_EntityBeans()
	 * @generated
	 */
	List getEntityBeans();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBean_MessageDrivenBeans()
	 * @generated
	 */
	List getMessageDrivenBeans();

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
	 * @see org.eclipse.jst.javaee.ejb.internal.metadata.EjbPackage#getEnterpriseBean_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.ejb.EnterpriseBean#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // EnterpriseBean