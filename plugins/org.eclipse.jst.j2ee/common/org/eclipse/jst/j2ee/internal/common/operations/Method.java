/*******************************************************************************
 * Copyright (c) 2007, 2008 SAP AG and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * Kaloyan Raev, kaloyan.raev@sap.com - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common.operations;

import java.util.Collection;

public interface Method {
	
	/**
	 * Returns the signature of the method.
	 * 
	 * @return a String representation of the method signature. 
	 */
	public String getSignature();

	/**
	 * Returns the name of the method. 
	 * 
	 * @return a String representation of the method name. 
	 */
	public String getName();
	
	/**
	 * Returns the unqualified name of the Java class that contains the method.
	 * 
	 * @return a String representation of the containing class name. 
	 */
	public String getContainingJavaClass();
	
	/**
	 * Returns the unqualified name of the method's return type. 
	 * 
	 * @return a String representation of the return type. 
	 */
	public String getReturnType();
	
	/**
	 * Returns the default return value for the method.
	 * 
	 * <p>
	 * If the method return type is <code>void</code>, then this method
	 * returns <code>null</code>.
	 * </p>
	 * 
	 * <p>
	 * If the method return type is <code>boolean</code>, then this method
	 * returns <code>"false"</code>.
	 * </p>
	 * 
	 * <p>
	 * For any other return type that is primitive this method returns
	 * <code>"0"</code>.
	 * </p>
	 * 
	 * <p>
	 * If the method return type is non-primitive, then this method returns
	 * <code>"null"</code>.
	 * </p>
	 * 
	 * @return a String representation of the default return type value.
	 */
	public String getDefaultReturnValue();
	
	/**
	 * Returns a collection of all types that should be imported so this method
	 * declaration can be resolved.
	 * 
	 * <p>
	 * The types are represented by their fully qualified names.
	 * </p>
	 * 
	 * @return a <code>Collection</code> of String representation of types to
	 *         be imported.
	 */
	public Collection<String> getReturnTypeImports();
	
	/**
	 * Returns the list of method's parameters in a format that is
	 * convenient for usage in a Javadoc <b>@see</b> annotation.
	 * 
	 * @return a String representation of the parameters list.
	 */
	public String getParamsForJavadoc();

	/**
	 * Returns the list of method's parameters in a format that is
	 * convenient for usage in a method declaration.
	 * 
	 * @return a String representation of the parameters list.
	 */
	public String getParamsForDeclaration();

	/**
	 * Returns the list of method's parameters in a format that is
	 * convenient for usage in a method call.
	 * 
	 * @return a String representation of the parameters list.
	 */
	public String getParamsForCall();

	/**
	 * Returns the list of all non-primitive method's parameter types.
	 * 
	 * @return a <code>List</code> of String representation of the parameter
	 *         types.
	 */
	public Collection<String> getParameterImports();
	
	
	/**
	 * Returns a list of exceptions thrown by the method
	 * @return a <code>List</code> of String representation of the exception
	 *         types.
	 */
	public String getExceptions();

}
