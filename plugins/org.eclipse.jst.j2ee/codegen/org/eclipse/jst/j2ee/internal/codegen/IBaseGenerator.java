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
 * A base generator has an explicit target element in the target model. It creates, updates or
 * removes that element as needed. It may use other base generators and/or dependent generators to
 * complete the task.
 * <p>
 * It is a base generator that is initially obtained via
 * {@link BaseGenerator#getGenerator(String, String, Class, TopLevelGenerationHelper)}as the
 * starting point for a generation. The user of the framework is expected to call these methods in
 * this order on this starting point generator.
 * <ol>
 * <li>{@link IGenerator#initialize(Object)}
 * <li>{@link IBaseGenerator#analyze()}
 * <li>{@link IBaseGenerator#run()}
 * <li>{@link IGenerator#terminate()}
 * </ul>
 */
public interface IBaseGenerator extends IGenerator {
	/**
	 * Adds a child base generator to the list of child base generators.
	 */
	void addChild(IBaseGenerator child);

	/**
	 * Override to analyze the generation. The default implementation analyzes the child base
	 * generators, but does nothing with the dependent child generators.
	 * 
	 * @see AnalysisResult
	 */
	AnalysisResult analyze() throws GenerationException;

	/**
	 * Discards the dependent generators.
	 */
	void discardDependents() throws GenerationException;

	/**
	 * Returns the base child generators.
	 */
	List getChildren();

	/**
	 * Call this method to get a new base generator instance.
	 * 
	 * <p>
	 * The new base generator's ancestor generator is set to this base generator. The new base
	 * generator is added to this base generator's list of base children. The list of dependent
	 * children held by a base generator is only used by the base generator implementation during
	 * {@link IGenerator#terminate()}. Subclass base generators may create base generators during
	 * {@link IGenerator#initialize(Object)}or {@link IBaseGenerator#analyze()}.
	 */
	IBaseGenerator getGenerator(String logicalName) throws GeneratorNotFoundException;

	/**
	 * Returns the parent generator (or null for the root).
	 */
	IBaseGenerator getParent();

	/**
	 * Gets the target context. All base generators in a given generation have the same target
	 * context.
	 */
	ITargetContext getTargetContext();

	/**
	 * Gets the target element.
	 */
	Object getTargetElement();

	/**
	 * Override to run the generator, and generate the required code. The default implementation
	 * runs the child base generators, but does nothing with the dependent child generators.
	 */
	void run() throws GenerationException;

	/**
	 * Runs the dependent generators.
	 */
	void runDependents(IGenerationBuffer buffer) throws GenerationException;

	/**
	 * Sets the target element.
	 */
	void setTargetElement(Object targetElement);
}