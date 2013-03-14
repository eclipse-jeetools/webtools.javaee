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
 * A representation of the model object '<em><b>Connection Definition</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The connection-definitionType defines a set of connection
 *         interfaces and classes pertaining to a particular connection
 *         type. This also includes configurable properties for
 *         ManagedConnectionFactory instances that may be produced out
 *         of this set.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getManagedconnectionfactoryClass <em>Managedconnectionfactory Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConfigProperty <em>Config Property</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionfactoryInterface <em>Connectionfactory Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionfactoryImplClass <em>Connectionfactory Impl Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionInterface <em>Connection Interface</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionImplClass <em>Connection Impl Class</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnectionDefinition()
 * @extends JavaEEObject
 * @generated
 */
public interface ConnectionDefinition extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Managedconnectionfactory Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The element managedconnectionfactory-class specifies
	 *             the fully qualified name of the Java class that
	 *             implements the
	 *             javax.resource.spi.ManagedConnectionFactory interface.
	 *             This Java class is provided as part of resource
	 *             adapter's implementation of connector architecture
	 *             specified contracts. The implementation of this
	 *             class is required to be a JavaBean.
	 *             
	 *             Example:
	 *             <managedconnectionfactory-class>
	 *             	  com.wombat.ManagedConnectionFactoryImpl
	 *             </managedconnectionfactory-class>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Managedconnectionfactory Class</em>' attribute.
	 * @see #setManagedconnectionfactoryClass(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnectionDefinition_ManagedconnectionfactoryClass()
	 * @generated
	 */
	String getManagedconnectionfactoryClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getManagedconnectionfactoryClass <em>Managedconnectionfactory Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Managedconnectionfactory Class</em>' attribute.
	 * @see #getManagedconnectionfactoryClass()
	 * @generated
	 */
	void setManagedconnectionfactoryClass(String value);

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnectionDefinition_ConfigProperty()
	 * @generated
	 */
	List<ConfigProperty> getConfigProperty();

	/**
	 * Returns the value of the '<em><b>Connectionfactory Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The element connectionfactory-interface specifies
	 *             the fully qualified name of the ConnectionFactory
	 *             interface supported by the resource adapter.
	 *             
	 *             Example:
	 *             <connectionfactory-interface>com.wombat.ConnectionFactory
	 *             </connectionfactory-interface>
	 *             
	 *             OR
	 *             
	 *             <connectionfactory-interface>javax.resource.cci.ConnectionFactory
	 *             </connectionfactory-interface>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connectionfactory Interface</em>' attribute.
	 * @see #setConnectionfactoryInterface(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnectionDefinition_ConnectionfactoryInterface()
	 * @generated
	 */
	String getConnectionfactoryInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionfactoryInterface <em>Connectionfactory Interface</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connectionfactory Interface</em>' attribute.
	 * @see #getConnectionfactoryInterface()
	 * @generated
	 */
	void setConnectionfactoryInterface(String value);

	/**
	 * Returns the value of the '<em><b>Connectionfactory Impl Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The element connectionfactory-impl-class specifies
	 *             the fully qualified name of the ConnectionFactory
	 *             class that implements resource adapter
	 *             specific ConnectionFactory interface.
	 *             
	 *             Example:
	 *             
	 *             <connectionfactory-impl-class>com.wombat.ConnectionFactoryImpl
	 *             </connectionfactory-impl-class>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connectionfactory Impl Class</em>' attribute.
	 * @see #setConnectionfactoryImplClass(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnectionDefinition_ConnectionfactoryImplClass()
	 * @generated
	 */
	String getConnectionfactoryImplClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionfactoryImplClass <em>Connectionfactory Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connectionfactory Impl Class</em>' attribute.
	 * @see #getConnectionfactoryImplClass()
	 * @generated
	 */
	void setConnectionfactoryImplClass(String value);

	/**
	 * Returns the value of the '<em><b>Connection Interface</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The connection-interface element specifies the fully
	 *             qualified name of the Connection interface supported
	 *             by the resource adapter.
	 *             
	 *             Example:
	 *             
	 *             	  <connection-interface>javax.resource.cci.Connection
	 *             	  </connection-interface>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Interface</em>' attribute.
	 * @see #setConnectionInterface(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnectionDefinition_ConnectionInterface()
	 * @generated
	 */
	String getConnectionInterface();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionInterface <em>Connection Interface</em>}' attribute.
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
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The connection-impl-classType specifies the fully
	 *             qualified name of the Connection class that
	 *             implements resource adapter specific Connection
	 *             interface.  It is used by the connection-impl-class
	 *             elements.
	 *             
	 *             Example:
	 *             
	 *             	  <connection-impl-class>com.wombat.ConnectionImpl
	 *             	  </connection-impl-class>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Connection Impl Class</em>' attribute.
	 * @see #setConnectionImplClass(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnectionDefinition_ConnectionImplClass()
	 * @generated
	 */
	String getConnectionImplClass();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getConnectionImplClass <em>Connection Impl Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Connection Impl Class</em>' attribute.
	 * @see #getConnectionImplClass()
	 * @generated
	 */
	void setConnectionImplClass(String value);

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConnectionDefinition_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConnectionDefinition#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ConnectionDefinition
