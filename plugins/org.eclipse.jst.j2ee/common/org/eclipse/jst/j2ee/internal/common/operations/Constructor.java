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

import java.util.List;

/**
 * Represents a constructor declaration in a Java class.
 * 
 * <p>
 * This interface defines methods that are used by the JETEmitter template
 * model.
 * </p>
 * 
 * @author kraev
 */
public interface Constructor {

	/**
	 * Determines whether the constructor has any parameters
	 * 
	 * @return <code>true</code> if the constructor has no parameters,
	 *         <code>false</code> - otherwise.
	 */
	public boolean isParameterless();

	/**
	 * Determines whether the constructor has a <code>public</code> modifier.
	 * 
	 * @return <code>true</code> if the constructor is public,
	 *         <code>false</code> - otherwise.
	 */
	public boolean isPublic();

	/**
	 * Determines whether the constructor has a <code>protected</code>
	 * modifier.
	 * 
	 * @return <code>true</code> if the constructor is protected,
	 *         <code>false</code> - otherwise.
	 */
	public boolean isProtected();

	/**
	 * Returns the list of constructor's parameters in a format that is
	 * convenient for usage in a Javadoc <b>@see</b> annotation.
	 * 
	 * @return a String representation of the parameters list.
	 */
	public String getParamsForJavadoc();

	/**
	 * Returns the list of constructor's parameters in a format that is
	 * convenient for usage in a method declaration.
	 * 
	 * @return a String representation of the parameters list.
	 */
	public String getParamsForDeclaration();

	/**
	 * Returns the list of constructor's parameters in a format that is
	 * convenient for usage in a method call.
	 * 
	 * @return a String representation of the parameters list.
	 */
	public String getParamsForCall();

	/**
	 * Returns the list of all non-primitive constructor's parameter types.
	 * 
	 * @return a <code>List</code> of String representation of the parameter
	 *         types.
	 */
	public List<String> getNonPrimitiveParameterTypes();

}
