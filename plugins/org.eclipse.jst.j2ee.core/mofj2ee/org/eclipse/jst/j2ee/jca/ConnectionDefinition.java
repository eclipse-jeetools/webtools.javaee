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
import org.eclipse.jst.j2ee.internal.jca.JcaPackage;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Connection Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * @since J2EE1.4
 * The connection-definitionType defines a set of connection
 * interfaces and classes pertaining to a particular connection
 * type. This also includes configurable properties for
 * ManagedConnectionFactory instances that may be produced out
 * of this set.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getManagedConnectionFactoryClass <em>Managed Connection Factory Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionFactoryInterface <em>Connection Factory Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionFactoryImplClass <em>Connection Factory Impl Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionInterface <em>Connection Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionImplClass <em>Connection Impl Class</em>}</li>
 *   <li>{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConfigProperties <em>Config Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getConnectionDefinition()
 * @model 
 * @generated
 */
public interface ConnectionDefinition extends EObject{
	/**
	 * Returns the value of the '<em><b>Managed Connection Factory Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed Connection Factory Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element managedconnectionfactory-class specifies
	 *         the fully qualified name of the Java class that
	 *         implements the
	 *         javax.resource.spi.ManagedConnectionFactory interface.
	 *         This Java class is provided as part of resource
	 *         adapter's implementation of connector architecture
	 *         specified contracts. The implementation of this
	 *         class is required to be a JavaBean.
	 * 
	 *         Example:
	 *         <managedconnectionfactory-class>
	 *             com.wombat.ManagedConnectionFactoryImpl
	 *         </managedconnectionfactory-class>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Managed Connection Factory Class</em>' attribute.
	 * @see #setManagedConnectionFactoryClass(String)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getConnectionDefinition_ManagedConnectionFactoryClass()
	 * @model 
	 * @generated
	 */
	String getManagedConnectionFactoryClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getManagedConnectionFactoryClass <em>Managed Connection Factory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managed Connection Factory Class</em>' attribute.
	 * @see #getManagedConnectionFactoryClass()
	 * @generated
	 */
	void setManagedConnectionFactoryClass(String value);

	/**
	 * Returns the value of the '<em><b>Connection Factory Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Factory Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element connectionfactory-interface specifies
	 *         the fully qualified name of the ConnectionFactory
	 *         interface supported by the resource adapter.
	 * 
	 *         Example:
	 *         <connectionfactory-interface>com.wombat.ConnectionFactory
	 *         </connectionfactory-interface>
	 * 
	 *         OR
	 * 
	 *         <connectionfactory-interface>javax.resource.cci.ConnectionFactory
	 *         </connectionfactory-interface>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Factory Interface</em>' attribute.
	 * @see #setConnectionFactoryInterface(String)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getConnectionDefinition_ConnectionFactoryInterface()
	 * @model 
	 * @generated
	 */
	String getConnectionFactoryInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionFactoryInterface <em>Connection Factory Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Factory Interface</em>' attribute.
	 * @see #getConnectionFactoryInterface()
	 * @generated
	 */
	void setConnectionFactoryInterface(String value);

	/**
	 * Returns the value of the '<em><b>Connection Factory Impl Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Factory Impl Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The element connectionfactory-impl-class specifies
	 *         the fully qualified name of the ConnectionFactory
	 *         class that implements resource adapter
	 *         specific ConnectionFactory interface.
	 * 
	 *         Example:
	 * 
	 *         <connectionfactory-impl-class>com.wombat.ConnectionFactoryImpl
	 *         </connectionfactory-impl-class>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Factory Impl Class</em>' attribute.
	 * @see #setConnectionFactoryImplClass(String)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getConnectionDefinition_ConnectionFactoryImplClass()
	 * @model 
	 * @generated
	 */
	String getConnectionFactoryImplClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionFactoryImplClass <em>Connection Factory Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Factory Impl Class</em>' attribute.
	 * @see #getConnectionFactoryImplClass()
	 * @generated
	 */
	void setConnectionFactoryImplClass(String value);

	/**
	 * Returns the value of the '<em><b>Connection Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Interface</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The connection-interface element specifies the fully
	 *         qualified name of the Connection interface supported
	 *         by the resource adapter.
	 * 
	 *         Example:
	 * 
	 *             <connection-interface>javax.resource.cci.Connection
	 *             </connection-interface>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Interface</em>' attribute.
	 * @see #setConnectionInterface(String)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getConnectionDefinition_ConnectionInterface()
	 * @model 
	 * @generated
	 */
	String getConnectionInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionInterface <em>Connection Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Interface</em>' attribute.
	 * @see #getConnectionInterface()
	 * @generated
	 */
	void setConnectionInterface(String value);

	/**
	 * Returns the value of the '<em><b>Connection Impl Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Connection Impl Class</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The connection-impl-classType specifies the fully
	 *         qualified name of the Connection class that
	 *         implements resource adapter specific Connection
	 *         interface.  It is used by the connection-impl-class
	 *         elements.
	 * 
	 *         Example:
	 * 
	 *             <connection-impl-class>com.wombat.ConnectionImpl
	 *             </connection-impl-class>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Impl Class</em>' attribute.
	 * @see #setConnectionImplClass(String)
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getConnectionDefinition_ConnectionImplClass()
	 * @model 
	 * @generated
	 */
	String getConnectionImplClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.j2ee.internal.jca.ConnectionDefinition#getConnectionImplClass <em>Connection Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Impl Class</em>' attribute.
	 * @see #getConnectionImplClass()
	 * @generated
	 */
	void setConnectionImplClass(String value);

	/**
	 * Returns the value of the '<em><b>Config Properties</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.jca.ConfigProperty}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Properties</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config Properties</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getConnectionDefinition_ConfigProperties()
	 * @model type="org.eclipse.jst.j2ee.internal.jca.ConfigProperty" containment="true"
	 * @generated
	 */
	EList getConfigProperties();

} // ConnectionDefinition
