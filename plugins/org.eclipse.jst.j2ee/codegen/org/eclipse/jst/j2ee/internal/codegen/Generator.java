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



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * Base class for all generators, base and dependent.
 * 
 * @see IGenerator
 */
public class Generator implements IGenerator {
	private GeneratorDictionary fXmlDictionary = null;
	private TopLevelGenerationHelper fTopLevelHelper = null;
	private ISourceContext fSourceContext = null;
	private Object fSourceElement = null;
	private List fDependentChildren = null;

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public void addDependentChild(IDependentGenerator child) {
		getDependentChildren().add(child);
	}

	/**
	 * Creates the source context implementation. Subframeworks may have a specialized context.
	 * Override this method to create it.
	 */
	protected ISourceContext createSourceContext() {
		return new SourceContext();
	}

	/**
	 * Creates the target context implementation. Subframeworks may have a specialized context.
	 * Override this method to create it.
	 */
	protected ITargetContext createTargetContext() {
		return new TargetContext();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public List getDependentChildren() {
		if (fDependentChildren == null)
			fDependentChildren = new ArrayList();
		return fDependentChildren;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public IDependentGenerator getDependentGenerator(String logicalName) throws GeneratorNotFoundException {
		DependentGenerator genImplInstance;
		try {
			genImplInstance = (DependentGenerator) getGeneratorImpl(logicalName, fXmlDictionary, getTopLevelHelper());

			// preset the relevant instance data
			this.addDependentChild(genImplInstance);
			genImplInstance.setSourceContext(this.getSourceContext());
		} catch (Exception x) {
			throw new GeneratorNotFoundException(logicalName);
		}
		return genImplInstance;
	}

	IGenerator getGeneratorImpl(String logicalName) throws GeneratorNotFoundException {
		Generator genImplInstance;
		try {
			genImplInstance = getGeneratorImpl(logicalName, fXmlDictionary, getTopLevelHelper());

			// preset the relevant instance data
			genImplInstance.fSourceContext = getSourceContext();
		} catch (Exception x) {
			throw new GeneratorNotFoundException(logicalName);
		}
		return genImplInstance;
	}

	static Generator getGeneratorImpl(String logicalName, GeneratorDictionary xmlDictionary, TopLevelGenerationHelper topLevelHelper) throws GeneratorNotFoundException {
		// Get the generator instance
		Generator genImplInstance = xmlDictionary.get(logicalName);

		// set the dictionary in the new generator
		genImplInstance.fXmlDictionary = xmlDictionary;
		genImplInstance.fTopLevelHelper = topLevelHelper;

		return genImplInstance;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public ISourceContext getSourceContext() {
		if (fSourceContext == null)
			fSourceContext = createSourceContext();
		return fSourceContext;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public Object getSourceElement() {
		return fSourceElement;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public TopLevelGenerationHelper getTopLevelHelper() {
		return fTopLevelHelper;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public void initialize(Object mofObject) throws GenerationException {
		setSourceElement(mofObject);
	}

	/**
	 * Used by the framework to make sure all related generators use the same source context.
	 */
	void setSourceContext(ISourceContext newSourceContext) {
		fSourceContext = newSourceContext;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public void setSourceElement(Object sourceElement) {
		fSourceElement = sourceElement;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public void terminate() throws GenerationException {
		if (fDependentChildren != null) {
			Iterator dependentChildGenIter = fDependentChildren.iterator();
			while (dependentChildGenIter.hasNext())
				((IDependentGenerator) dependentChildGenIter.next()).terminate();
			fDependentChildren.clear();
			fDependentChildren = null;
		}
		fXmlDictionary = null;
		fTopLevelHelper = null;
		fSourceContext = null;
		fSourceElement = null;
	}
}