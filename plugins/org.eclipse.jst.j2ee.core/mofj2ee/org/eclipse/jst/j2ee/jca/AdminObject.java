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
package org.eclipse.jst.j2ee.jca;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Admin Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @version J2EE1.4
 * The adminobjectType specifies information about an
 * administered object.  Administered objects are specific to a
 * messaging style or message provider.  This contains
 * information on the Java type of the interface implemented by
 * an administered object, its Java class name and its
 * configuration properties.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.jca.AdminObject#getAdminObjectInterface <em>Admin Object Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.AdminObject#getAdminObjectClass <em>Admin Object Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.jca.AdminObject#getConfigProperties <em>Config Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getAdminObject()
 * @model
 * @generated
 */
public interface AdminObject extends EObject{
	/**
	 * Returns the value of the '<em><b>Admin Object Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Admin Object Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element adminobject-interface specifies the
	 *         fully qualified name of the Java type of the
	 *         interface implemented by an administered object.
	 * 
	 *         Example:
	 *           <adminobject-interface>javax.jms.Destination
	 *           </adminobject-interface>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Admin Object Interface</em>' attribute.
	 * @see #setAdminObjectInterface(String)
	 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getAdminObject_AdminObjectInterface()
	 * @model
	 * @generated
	 */
	String getAdminObjectInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.jca.AdminObject#getAdminObjectInterface <em>Admin Object Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Admin Object Interface</em>' attribute.
	 * @see #getAdminObjectInterface()
	 * @generated
	 */
	void setAdminObjectInterface(String value);

	/**
	 * Returns the value of the '<em><b>Admin Object Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Admin Object Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element adminobject-class specifies the fully
	 *         qualified Java class name of an administered object.
	 * 
	 *         Example:
	 *             <adminobject-class>com.wombat.DestinationImpl
	 *             </adminobject-class>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Admin Object Class</em>' attribute.
	 * @see #setAdminObjectClass(String)
	 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getAdminObject_AdminObjectClass()
	 * @model
	 * @generated
	 */
	String getAdminObjectClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.jca.AdminObject#getAdminObjectClass <em>Admin Object Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Admin Object Class</em>' attribute.
	 * @see #getAdminObjectClass()
	 * @generated
	 */
	void setAdminObjectClass(String value);

	/**
	 * Returns the value of the '<em><b>Config Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.jca.ConfigProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config Properties</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.jca.JcaPackage#getAdminObject_ConfigProperties()
	 * @model type="org.eclipse.jst.j2ee.jca.ConfigProperty" containment="true"
	 * @generated
	 */
	EList getConfigProperties();

} // AdminObject
