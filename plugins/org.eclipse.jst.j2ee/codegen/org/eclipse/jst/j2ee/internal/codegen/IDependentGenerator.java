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
package org.eclipse.jst.j2ee.internal.codegen;



/**
 * A dependent generator does not have an explicit target in the target model. It relies on a base
 * generator to manage the target. It also relies on a base generator to provide a generation buffer
 * to its run method.
 */
public interface IDependentGenerator extends IGenerator {
	/**
	 * Returns the base ancestor generator. That is the base generator that owns the tree of
	 * dependent generators that this dependent generator is in.
	 */
	IBaseGenerator getBaseAncestor();

	/**
	 * Returns the parent dependent generator (or null for the root of the dependent tree)
	 */
	IDependentGenerator getParent();

	/**
	 * Override to run the generator, and generate the required code. The default implementation
	 * runs the dependent child generators.
	 */
	void run(IGenerationBuffer baseAncestorBuffer) throws GenerationException;
}