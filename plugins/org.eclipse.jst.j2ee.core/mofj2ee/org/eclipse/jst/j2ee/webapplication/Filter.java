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
package org.eclipse.jst.j2ee.webapplication;

import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jst.j2ee.common.CompatibilityDescriptionGroup;


/**
 * Declares a filter in the web application application. The filter is mapped to either a servlet or a URL pattern in the filter-mapping element, using the filter-name value to reference. Filters can access the initialization parameters declared in the deployment descriptor at runtime via the FilterConfig interface.

 */
public interface Filter extends CompatibilityDescriptionGroup{

public String getFilterClassName();
public void setFilterClassName(String filterClassName);
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the Name attribute
	 * The logical name of the filter. This name is used to map the filter.
	 */
	String getName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the Name attribute
	 */
	void setName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The list of InitParams references
	 * The init-params contain a name//value pairs as initialization params for the
	 * filter

	 */
	EList getInitParams();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The FilterClass reference
	 * The fully qualified classname of the filter.
	 */
	JavaClass getFilterClass();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the FilterClass reference
	 */
	void setFilterClass(JavaClass value);

	/**
	 * Returns the value of the '<em><b>Init Param Values</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.j2ee.internal.common.ParamValue}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Init Param Values</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * @since J2EE1.4
	 * The init-param element contains a name/value pair as
	 *         an initialization param of a servlet filter
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Init Param Values</em>' containment reference list.
	 * @see org.eclipse.jst.j2ee.internal.webapplication.WebapplicationPackage#getFilter_InitParamValues()
	 * @model type="org.eclipse.jst.j2ee.internal.common.ParamValue" containment="true"
	 * @generated
	 */
	EList getInitParamValues();

}














