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



import org.eclipse.jst.j2ee.internal.codegen.AnalysisResult;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;


/**
 * A logical generator that groups a set of related member generators. For example, a property
 * generator that groups a field and 0, 1 or 2 method generators.
 * 
 * <p>
 * Subclasses must implement these methods:
 * <ul>
 * <li>getName - returns the name of the group
 * </ul>
 * <p>
 * Subclasses will typically override these methods:
 * <ul>
 * <li>initialize - create and initialize child generators
 * </ul>
 * <p>
 * Subclasses will override these methods regularly:
 * <ul>
 * <li>terminate - to null object references and release resources
 * </ul>
 */
public abstract class JavaMemberGroupGenerator extends JavaMemberGenerator {
	/**
	 * JavaMemberGroupGenerator default constructor.
	 */
	public JavaMemberGroupGenerator() {
		super();
	}

	/**
	 * For a group generator, we want the default implementation.
	 * 
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IBaseGenerator
	 */
	public AnalysisResult analyze() throws GenerationException {
		return primAnalyze();
	}

	/**
	 * Returns true if this is a group generator.
	 */
	public boolean isGroup() {
		return true;
	}

	/**
	 * For a group generator all we want to do is run the children.
	 * 
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IBaseGenerator
	 */
	public void run() throws GenerationException {
		runChildren();
	}
}