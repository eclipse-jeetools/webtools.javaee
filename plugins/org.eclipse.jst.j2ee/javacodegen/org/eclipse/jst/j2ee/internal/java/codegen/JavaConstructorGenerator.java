/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.java.codegen;



/**
 * A Java constructor generator is used to create, remove or update a Java constructor.
 * 
 * <p>
 * Subclasses will typically override these methods:
 * <ul>
 * <li>getComment - returns the comment for the method
 * <li>getBody() - returns a simple method body. Alternatively, the subclass can create a set of
 * set of dependent generators (in initialize or analyze) and they will be run in
 * getBody(IGenerationBuffer) if getBody() has not been overridden.
 * </ul>
 * <p>
 * Subclasses will override these methods regularly:
 * <ul>
 * <li>getParameterDescriptors - override if parameter count is not 0
 * <li>deriveFlags - returns the modifier flags for the method
 * <li>getExceptions - to return a set of exceptions for the throws clause
 * </ul>
 * <p>
 * Subclasses may occasionally override these methods:
 * <ul>
 * <li>dispatchToMergeStrategy - when a hook is needed for pre and/or post merge processing
 * <li>terminate - to null object references and release resources
 * </ul>
 */
public abstract class JavaConstructorGenerator extends JavaMethodGenerator {
	private static final String WITHOUT_RETURN_TEMPLATE = "%1";//$NON-NLS-1$

	/**
	 * JavaConstructorGenerator default constructor.
	 */
	public JavaConstructorGenerator() {
		super();
	}

	/**
	 * The constructor generator overrides to use a template with no return value.
	 */
	String getDeclarationTemplate() {
		return WITHOUT_RETURN_TEMPLATE;
	}

	/**
	 * The name of a constructor is derived from it's enclosing type. Subclasses do not need to
	 * implement getName() in constructor generators.
	 */
	protected String getName() throws org.eclipse.jst.j2ee.internal.codegen.GenerationException {
		return getDeclaringTypeGenerator().getName();
	}
}