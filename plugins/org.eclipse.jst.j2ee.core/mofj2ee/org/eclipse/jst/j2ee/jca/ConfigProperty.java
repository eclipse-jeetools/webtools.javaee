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
 * The config property contains a declaration of a single
 * configuration property for a ManagedConnectionFactory instance.
 * 
 * Each ManagedConnectionFactory instance creates connections to a specific EIS instance based on the properties configured on the ManagedConnectionFactory instance. The configurable properties are specified only once in the deployment descriptor, even though a resource adapter can be used to configure multiple ManagedConnnection-Factory instances (that create connections to different instances of the same EIS).
 * 
 * The declaration consists of an optional description, name, type
 * and an optional value of the configuration property. If the resource
 * adapter provider does not specify a value than the deployer is
 * responsible for providing a valid value for a configuration property.
 * 
 * Any bounds or well-defined values of properties should be described in the description.

 */
public interface ConfigProperty extends EObject{

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Description attribute
	 */
	String getDescription();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Description attribute
	 */
	void setDescription(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Name attribute
	 * Contains the name of a configuration property.
	 * 
	 * The connector architecture defines a set of well-defined properties all of type 
	 * java.lang.String. These are as follows:
	 * 
	 *      ServerName
	 *      PortNumber
	 *      UserName
	 *      Password
	 *      ConnectionURL
	 * 
	 * A resource adapter provider can extend this property set to include properties
	 * specific to the resource adapter and its underlying EIS.

	 */
	String getName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Name attribute
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Type attribute
	 * Contains the fully qualified Java type of a configuration property as required
	 * by ManagedConnection-Factory instance.
	 * 
	 * The following are the legal values of config-property-type:
	 *    java.lang.Boolean, java.lang.String, java.lang.Integer,
	 *    java.lang.Double, java.lang.Byte, java.lang.Short,
	 *    java.lang.Long, java.lang.Float, java.lang.Character
	 */
	String getType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Type attribute
	 */
	void setType(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Value attribute
	 * Contains the value of a configuration entry.
	 */
	String getValue();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Value attribute
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Descriptions</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Descriptions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @plannedfor J2EE1.4
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Descriptions</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.jca.JcaPackage#getConfigProperty_Descriptions()
	 * @model type="org.eclipse.jst.j2ee.internal.common.Description" containment="true"
	 * @generated
	 */
	EList getDescriptions();

}





