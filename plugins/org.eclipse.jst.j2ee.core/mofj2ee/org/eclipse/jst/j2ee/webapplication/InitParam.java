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

import org.eclipse.emf.ecore.EObject;
/**
 * The init-param element contains a name//value pair as an initialization param of the servlet
 */
public interface InitParam extends EObject{
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ParamName attribute
	 * 
	 * 

	 */
	String getParamName();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ParamName attribute
	 */
	void setParamName(String value);

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The value of the ParamValue attribute
	 */
	String getParamValue();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param value The new value of the ParamValue attribute
	 */
	void setParamValue(String value);

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

}














