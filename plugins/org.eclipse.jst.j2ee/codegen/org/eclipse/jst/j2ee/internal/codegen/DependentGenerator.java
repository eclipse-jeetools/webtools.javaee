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



import java.util.Iterator;


/**
 * A base class for dependent generators that provides the default implementations for the
 * {@link IDependentGenerator}interface.
 */
public class DependentGenerator extends Generator implements IDependentGenerator {
	private IBaseGenerator fBaseAncestorGenerator = null;
	private IDependentGenerator fParentGenerator = null;

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IDependentGenerator
	 */
	public IBaseGenerator getBaseAncestor() {
		// This will be null if we are not the root of the dependent generator tree
		// and nobody has asked for the base ancestor. Roots of dependent generator
		// trees get this set at creation time.
		if (fBaseAncestorGenerator == null)
			fBaseAncestorGenerator = getParent().getBaseAncestor();
		return fBaseAncestorGenerator;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerator
	 */
	public IDependentGenerator getDependentGenerator(String logicalName) throws GeneratorNotFoundException {
		DependentGenerator genImplInstance;
		try {
			genImplInstance = (DependentGenerator) super.getDependentGenerator(logicalName);

			// preset the relevant instance data
			genImplInstance.fParentGenerator = this;
			genImplInstance.setBaseAncestor(getBaseAncestor());
		} catch (Exception x) {
			throw new GeneratorNotFoundException(logicalName);
		}
		return genImplInstance;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IDependentGenerator
	 */
	public IDependentGenerator getParent() {
		return fParentGenerator;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IDependentGenerator
	 */
	public void run(IGenerationBuffer baseAncestorBuffer) throws GenerationException {
		runChildren(baseAncestorBuffer);
	}

	/**
	 * Runs the child generators.
	 */
	protected void runChildren(IGenerationBuffer baseAncestorBuffer) throws GenerationException {
		Iterator childGenIter = getDependentChildren().iterator();
		while (childGenIter.hasNext())
			((IDependentGenerator) childGenIter.next()).run(baseAncestorBuffer);
	}

	/**
	 * Sets the base ancestor generator.
	 */
	void setBaseAncestor(IBaseGenerator newBaseAncestor) {
		fBaseAncestorGenerator = newBaseAncestor;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.codegen.api.IGenerator
	 */
	public void terminate() throws GenerationException {
		fBaseAncestorGenerator = null;
		fParentGenerator = null;
	}
}