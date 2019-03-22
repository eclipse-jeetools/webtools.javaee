/*******************************************************************************
 * Copyright (c) 2009 IBM Corporation and others.
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

import org.eclipse.jst.javaee.core.Description;
import org.eclipse.jst.javaee.core.JavaEEObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Config Property</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 
 *         The config-propertyType contains a declaration of a single
 *         configuration property that may be used for providing
 *         configuration information.
 *         
 *         The declaration consists of an optional description, name,
 *         type and an optional value of the configuration property. If
 *         the resource adapter provider does not specify a value than
 *         the deployer is responsible for providing a valid value for
 *         a configuration property.
 *         
 *         Any bounds or well-defined values of properties should be
 *         described in the description element.
 *         
 *         @since Java EE 6, Connector 1.6
 *       
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConfigProperty#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyName <em>Config Property Name</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyType <em>Config Property Type</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyValue <em>Config Property Value</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyIgnore <em>Config Property Ignore</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertySupportsDynamicUpdates <em>Config Property Supports Dynamic Updates</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyConfidential <em>Config Property Confidential</em>}</li>
 *   <li>{@link org.eclipse.jst.javaee.jca.ConfigProperty#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigProperty()
 * @extends JavaEEObject
 * @generated
 */
public interface ConfigProperty extends JavaEEObject {
	/**
	 * Returns the value of the '<em><b>Description</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.javaee.core.Description}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' containment reference list.
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigProperty_Description()
	 * @generated
	 */
	List<Description> getDescription();

	/**
	 * Returns the value of the '<em><b>Config Property Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Property Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config Property Name</em>' attribute.
	 * @see #setConfigPropertyName(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigProperty_ConfigPropertyName()
	 * @generated
	 */
	String getConfigPropertyName();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyName <em>Config Property Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config Property Name</em>' attribute.
	 * @see #getConfigPropertyName()
	 * @generated
	 */
	void setConfigPropertyName(String value);

	/**
	 * Returns the value of the '<em><b>Config Property Type</b></em>' attribute.
	 * The literals are from the enumeration {@link org.eclipse.jst.javaee.jca.ConfigPropertyTypeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Config Property Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Config Property Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.jca.ConfigPropertyTypeType
	 * @see #isSetConfigPropertyType()
	 * @see #unsetConfigPropertyType()
	 * @see #setConfigPropertyType(ConfigPropertyTypeType)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigProperty_ConfigPropertyType()
	 * @generated
	 */
	ConfigPropertyTypeType getConfigPropertyType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyType <em>Config Property Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config Property Type</em>' attribute.
	 * @see org.eclipse.jst.javaee.jca.ConfigPropertyTypeType
	 * @see #isSetConfigPropertyType()
	 * @see #unsetConfigPropertyType()
	 * @see #getConfigPropertyType()
	 * @generated
	 */
	void setConfigPropertyType(ConfigPropertyTypeType value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyType <em>Config Property Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConfigPropertyType()
	 * @see #getConfigPropertyType()
	 * @see #setConfigPropertyType(ConfigPropertyTypeType)
	 * @generated
	 */
	void unsetConfigPropertyType();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyType <em>Config Property Type</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Config Property Type</em>' attribute is set.
	 * @see #unsetConfigPropertyType()
	 * @see #getConfigPropertyType()
	 * @see #setConfigPropertyType(ConfigPropertyTypeType)
	 * @generated
	 */
	boolean isSetConfigPropertyType();

	/**
	 * Returns the value of the '<em><b>Config Property Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * [
	 *             The element config-property-value contains the value
	 *             of a configuration entry. Note, it is possible for a
	 *             resource adapter deployer to override this
	 *             configuration information during deployment.
	 *             
	 *             Example:
	 *             <config-property-value>WombatServer</config-property-value>
	 *             
	 * 
	 * 			@since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Config Property Value</em>' attribute.
	 * @see #setConfigPropertyValue(String)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigProperty_ConfigPropertyValue()
	 * @generated
	 */
	String getConfigPropertyValue();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getConfigPropertyValue <em>Config Property Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config Property Value</em>' attribute.
	 * @see #getConfigPropertyValue()
	 * @generated
	 */
	void setConfigPropertyValue(String value);

	/**
	 * Returns the value of the '<em><b>Config Property Ignore</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element config-property-ignore is used to specify 
	 *             whether the configuration tools must ignore considering the 
	 *             configuration property during auto-discovery of
	 *             Configuration properties. See the Connector specification for
	 *             more details. If unspecified, the container must not ignore
	 *             the configuration property during auto-discovery.
	 *             This element must be one of the following, "true" or "false".
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Config Property Ignore</em>' attribute.
	 * @see #isSetConfigPropertyIgnore()
	 * @see #unsetConfigPropertyIgnore()
	 * @see #setConfigPropertyIgnore(boolean)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigProperty_ConfigPropertyIgnore()
	 * @generated
	 */
	boolean isConfigPropertyIgnore();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyIgnore <em>Config Property Ignore</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config Property Ignore</em>' attribute.
	 * @see #isSetConfigPropertyIgnore()
	 * @see #unsetConfigPropertyIgnore()
	 * @see #isConfigPropertyIgnore()
	 * @generated
	 */
	void setConfigPropertyIgnore(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyIgnore <em>Config Property Ignore</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConfigPropertyIgnore()
	 * @see #isConfigPropertyIgnore()
	 * @see #setConfigPropertyIgnore(boolean)
	 * @generated
	 */
	void unsetConfigPropertyIgnore();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyIgnore <em>Config Property Ignore</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Config Property Ignore</em>' attribute is set.
	 * @see #unsetConfigPropertyIgnore()
	 * @see #isConfigPropertyIgnore()
	 * @see #setConfigPropertyIgnore(boolean)
	 * @generated
	 */
	boolean isSetConfigPropertyIgnore();

	/**
	 * Returns the value of the '<em><b>Config Property Supports Dynamic Updates</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element config-property-supports-dynamic-updates is used to specify 
	 *             whether the configuration property allows its value to be updated, by
	 *             application server's configuration tools, during the lifetime of
	 *             the JavaBean instance. See the Connector specification for
	 *             more details. If unspecified, the container must not dynamically
	 *             reconfigure the property.
	 *             This element must be one of the following, "true" or "false".
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Config Property Supports Dynamic Updates</em>' attribute.
	 * @see #isSetConfigPropertySupportsDynamicUpdates()
	 * @see #unsetConfigPropertySupportsDynamicUpdates()
	 * @see #setConfigPropertySupportsDynamicUpdates(boolean)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigProperty_ConfigPropertySupportsDynamicUpdates()
	 * @generated
	 */
	boolean isConfigPropertySupportsDynamicUpdates();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertySupportsDynamicUpdates <em>Config Property Supports Dynamic Updates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config Property Supports Dynamic Updates</em>' attribute.
	 * @see #isSetConfigPropertySupportsDynamicUpdates()
	 * @see #unsetConfigPropertySupportsDynamicUpdates()
	 * @see #isConfigPropertySupportsDynamicUpdates()
	 * @generated
	 */
	void setConfigPropertySupportsDynamicUpdates(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertySupportsDynamicUpdates <em>Config Property Supports Dynamic Updates</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConfigPropertySupportsDynamicUpdates()
	 * @see #isConfigPropertySupportsDynamicUpdates()
	 * @see #setConfigPropertySupportsDynamicUpdates(boolean)
	 * @generated
	 */
	void unsetConfigPropertySupportsDynamicUpdates();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertySupportsDynamicUpdates <em>Config Property Supports Dynamic Updates</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Config Property Supports Dynamic Updates</em>' attribute is set.
	 * @see #unsetConfigPropertySupportsDynamicUpdates()
	 * @see #isConfigPropertySupportsDynamicUpdates()
	 * @see #setConfigPropertySupportsDynamicUpdates(boolean)
	 * @generated
	 */
	boolean isSetConfigPropertySupportsDynamicUpdates();

	/**
	 * Returns the value of the '<em><b>Config Property Confidential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * 
	 * 
	 *             The element config-property-confidential is used to specify 
	 *             whether the configuration property is confidential and
	 *             recommends application server's configuration tools to use special 
	 *             visual aids for editing them. See the Connector specification for
	 *             more details. If unspecified, the container must not treat the
	 *             property as confidential.
	 *             This element must be one of the following, "true" or "false".
	 *             
	 *             @since Java EE 6, Connector 1.6
	 *           
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Config Property Confidential</em>' attribute.
	 * @see #isSetConfigPropertyConfidential()
	 * @see #unsetConfigPropertyConfidential()
	 * @see #setConfigPropertyConfidential(boolean)
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigProperty_ConfigPropertyConfidential()
	 * @generated
	 */
	boolean isConfigPropertyConfidential();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyConfidential <em>Config Property Confidential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Config Property Confidential</em>' attribute.
	 * @see #isSetConfigPropertyConfidential()
	 * @see #unsetConfigPropertyConfidential()
	 * @see #isConfigPropertyConfidential()
	 * @generated
	 */
	void setConfigPropertyConfidential(boolean value);

	/**
	 * Unsets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyConfidential <em>Config Property Confidential</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetConfigPropertyConfidential()
	 * @see #isConfigPropertyConfidential()
	 * @see #setConfigPropertyConfidential(boolean)
	 * @generated
	 */
	void unsetConfigPropertyConfidential();

	/**
	 * Returns whether the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#isConfigPropertyConfidential <em>Config Property Confidential</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Config Property Confidential</em>' attribute is set.
	 * @see #unsetConfigPropertyConfidential()
	 * @see #isConfigPropertyConfidential()
	 * @see #setConfigPropertyConfidential(boolean)
	 * @generated
	 */
	boolean isSetConfigPropertyConfidential();

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
	 * @see org.eclipse.jst.javaee.jca.internal.metadata.JcaPackage#getConfigProperty_Id()
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.javaee.jca.ConfigProperty#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

} // ConfigProperty
