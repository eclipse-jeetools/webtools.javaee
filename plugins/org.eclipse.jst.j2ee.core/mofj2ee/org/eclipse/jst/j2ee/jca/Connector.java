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

import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;
/**
 * The connector element is the root element of the deployment descriptor for the resource adapter. This element includes general information - vendor name, version, specification version supported, icon -  about the resource adapter module. It also includes information specific to the implementation of the resource adapter library as specified through the element resourceadapter.

 */
public interface Connector extends CompatibilityDescriptionGroup{


	/**
	* This returns the module version id.  Compare with J2EEVersionConstants to determine module level
	*/
	public int getVersionID() throws IllegalStateException ;

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the VendorName attribute
	 * Specifies the name of resource adapter provider vendor.
	 * Example: Wombat Corp.
	 */
	String getVendorName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the VendorName attribute
	 */
	void setVendorName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the SpecVersion attribute
	 * Specifies the version of the connector architecture specification that is
	 * supported by this resource adapter. This information enables deployer to
	 * configure the resource adapter to support deployment and runtime requirements
	 * of the corresponding connector architecture specification.

	 */
	String getSpecVersion();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the SpecVersion attribute
	 */
	void setSpecVersion(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the EisType attribute
	 * Contains information about the type of the EIS. For example, the type of an EIS 
	 * can be product name of EIS independent of any version info.
	 * 
	 * This helps in identifying EIS instances that can be used with
	 * this resource adapter.

	 */
	String getEisType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the EisType attribute
	 */
	void setEisType(String value);

	/**
	 * These values are used in the deployment descriptor, use getVersionID() to determine module versions
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Version attribute
	 * Specifies a string-based version of the resource adapter from the resource
	 * adapter provider.
	 * 
	 * Example: 1.0
	 */
	String getVersion();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Version attribute
	 */
	void setVersion(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The License reference
	 * Specifies licensing requirements for the resource adapter module. This element
	 * specifies whether a license is required to deploy and use this resource
	 * adapter, and an optional description of the licensing terms (examples: duration 
	 * of license, number of
	 * connection restrictions).
	 */
	License getLicense();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the License reference
	 */
	void setLicense(License value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The ResourceAdapter reference
	 */
	ResourceAdapter getResourceAdapter();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the ResourceAdapter reference
	 */
	void setResourceAdapter(ResourceAdapter value);

}





