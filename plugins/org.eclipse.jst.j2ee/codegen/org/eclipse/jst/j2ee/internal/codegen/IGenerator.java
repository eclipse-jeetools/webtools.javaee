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



import java.util.List;


/**
 * Defines the interface for all generators, base and dependent.
 * 
 * @see IBaseGenerator
 * @see IDependentGenerator
 */
public interface IGenerator {
	/**
	 * Adds a dependent child generator to the list of dependent child generators. Both base and
	 * dependent generators can have dependent generators. However, a subtree of dependent
	 * generators must descend from one base generator.
	 * 
	 * @param child
	 *            the dependent generator to add as a child
	 * @see IDependentGenerator
	 */
	void addDependentChild(IDependentGenerator child);

	/**
	 * Returns the dependent child generators.
	 */
	List getDependentChildren();

	/**
	 * Call this method to get a new dependent generator instance.
	 * 
	 * <p>
	 * If this method is called on a base generator, the new dependent generator's base ancestor
	 * generator is set to this base generator. The new dependent generator is added to this base
	 * generator's list of dependent children.
	 * <p>
	 * In the base framework, the list of dependent children held by a base generator is only used
	 * by the base generator implementation during {@link IGenerator#terminate()}. Subclass base
	 * generators may create dependent generators during {@link IGenerator#initialize(Object)}or
	 * {@link IBaseGenerator#analyze()}. It is up to the subclass base generator to run the
	 * dependent generators during {@link IBaseGenerator#run()}. It may or may not use the list of
	 * dependent child generators (see {@link IBaseGenerator#runDependents(IGenerationBuffer)}and
	 * {@link IBaseGenerator#discardDependents()}).
	 * 
	 * <p>
	 * Examples of a base generator running dependent generators can be found in the Java code
	 * generation extension of this abstract framework. Look for references to the
	 * {@link IBaseGenerator#runDependents(IGenerationBuffer)}method.
	 * 
	 * <p>
	 * If this method is called on a dependent generator, the new dependent generator's parent
	 * generator is set to this dependent generator. The base ancestor generator setting is also
	 * passed down the tree. The new dependent generator is added to this dependent generator's list
	 * of children. A dependent generator runs its dependent children during
	 * {@link IDependentGenerator#run(IGenerationBuffer)}.
	 */
	IDependentGenerator getDependentGenerator(String logicalName) throws GeneratorNotFoundException;

	/**
	 * Gets the source context. All generators in a given generation have the same source context.
	 * 
	 * @see ISourceContext
	 */
	ISourceContext getSourceContext();

	/**
	 * Gets the source element. The source element is the object that was passed to the
	 * {@link IGenerator#initialize(Object)}method.
	 */
	Object getSourceElement();

	/**
	 * Returns the top level helper for this generation run.
	 * 
	 * @see TopLevelGenerationHelper
	 */
	TopLevelGenerationHelper getTopLevelHelper();

	/**
	 * Override to initialize the generator, and create all required children
	 */
	void initialize(Object mofObject) throws GenerationException;

	/**
	 * Sets the source element.
	 */
	void setSourceElement(Object sourceElement);

	/**
	 * The graph of objects created during generation can be quite large and complex. Such object
	 * graphs can be difficult to garbage collect. The intention of this method is to provide a way
	 * to break up the graph after generation to make garbage collection easier and overall system
	 * performance better. In addition it provides a good place to make sure critical resources are
	 * released after generation. The call to terminate should be placed in a finally block in case
	 * an exception causes abnormal termination of generation.
	 */
	void terminate() throws GenerationException;
}