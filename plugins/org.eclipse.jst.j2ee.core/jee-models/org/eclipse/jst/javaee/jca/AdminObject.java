/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.javaee.jca;

import java.util.List;

import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Admin Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The adminobjectType specifies information about an
 *         administered object.  Administered objects are specific to a
 *         messaging style or message provider.  This contains
 *         information on the Java type of the interface implemented by
 *         an administered object, its Java class name and its
 *         configuration properties.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.AdminObject#getAdminobjectInterface <em>Adminobject Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.AdminObject#getAdminobjectClass <em>Adminobject Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.AdminObject#getConfigProperty <em>Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.AdminObject#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAdminObject()
 * @extends JavaEEObject
 * @generated
 */
public interface AdminObject extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Adminobject Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The element adminobject-interface specifies the
	 *             fully qualified name of the Java type of the
	 *             interface implemented by an administered object.
	 *             
	 *             Example:
	 *             	<adminobject-interface>javax.jms.Destination
	 *             	</adminobject-interface>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Adminobject Interface</em>' attribute.
	 * @see #setAdminobjectInterface(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAdminObject_AdminobjectInterface()
	 * @generated
	 */
	String getAdminobjectInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.AdminObject#getAdminobjectInterface <em>Adminobject Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adminobject Interface</em>' attribute.
	 * @see #getAdminobjectInterface()
	 * @generated
	 */
	void setAdminobjectInterface(String value);

	/**
	 * Returns the value of the '<em><b>Adminobject Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The element adminobject-class specifies the fully
	 *             qualified Java class name of an administered object.
	 *             
	 *             Example:
	 *             	  <adminobject-class>com.wombat.DestinationImpl
	 *             	  </adminobject-class>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Adminobject Class</em>' attribute.
	 * @see #setAdminobjectClass(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAdminObject_AdminobjectClass()
	 * @generated
	 */
	String getAdminobjectClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.AdminObject#getAdminobjectClass <em>Adminobject Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Adminobject Class</em>' attribute.
	 * @see #getAdminobjectClass()
	 * @generated
	 */
	void setAdminobjectClass(String value);

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAdminObject_ConfigProperty()
	 * @generated
	 */
	List<ConfigProperty> getConfigProperty();

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getAdminObject_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.AdminObject#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // AdminObject
