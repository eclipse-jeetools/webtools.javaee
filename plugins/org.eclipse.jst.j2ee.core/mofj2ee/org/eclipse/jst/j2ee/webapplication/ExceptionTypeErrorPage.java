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

import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jem.java.JavaClass;
/**
 * @generated
 */
public interface ExceptionTypeErrorPage extends ErrorPage {




public String getExceptionTypeName() ;
void setExceptionTypeName(String exceptionTypeName) ;
/**
 * @deprecated Use @link{#setExceptionTypeName(String)}
 */
void setExceptionTypeName(String exceptionTypeName, ResourceSet context) ;
	/**
	 * @generated This field/method will be replaced during code generation 
	 * @return The ExceptionType reference
	 * The exception type contains a fully qualified class name of a Java exception
	 * type.
	 */
	JavaClass getExceptionType();

	/**
	 * @generated This field/method will be replaced during code generation 
	 * @param l The new value of the ExceptionType reference
	 */
	void setExceptionType(JavaClass value);

}














