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



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * An abstract base class for base generators that provides the default implementations for much of
 * the {@link IBaseGenerator}interface. It also provides a static factory method to get the
 * starting point generator.
 */
public abstract class BaseGenerator extends Generator implements IBaseGenerator {
	private static Map generatorDictionaryMap;
	private IBaseGenerator fParentGenerator = null;
	private List fChildren = null;
	private ITargetContext fTargetContext = null;
	private Object fTargetElement = null;
	private AnalysisResult fAnalysisResult = null;

	/**
	 * BaseGenerator default constructor.
	 */
	public BaseGenerator() {
		super();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public void addChild(IBaseGenerator child) {
		getChildren().add(child);
	}

	/**
	 * This default implementation uses the existing result if it is set. It analyzes children and
	 * sets the result if the child results are not empty.
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public AnalysisResult analyze() throws GenerationException {
		return primAnalyze();
	}

	/**
	 * Does the analysis step on the child generators. The child results are added to the passed in
	 * result.
	 */
	protected void analyzeChildren(AnalysisResult result) throws GenerationException {
		Iterator childGenIter = getChildren().iterator();
		while (childGenIter.hasNext())
			result.addChildResult(((IBaseGenerator) childGenIter.next()).analyze());
	}

	private void buildDebugString(StringBuffer buffer, int indent) {
		try {
			for (int i = 0; i < indent; i++)
				buffer.append("  "); //$NON-NLS-1$
			buffer.append(getClass().getName());
			String name = getName();
			if (name != null) {
				buffer.append(" ("); //$NON-NLS-1$
				buffer.append(name);
				buffer.append(")"); //$NON-NLS-1$
			}
			buffer.append("\n"); //$NON-NLS-1$

			List children = getChildren();
			for (int i = 0; i < children.size(); i++) {
				BaseGenerator child = (BaseGenerator) children.get(i);
				child.buildDebugString(buffer, indent + 1);
			}
		} catch (GenerationException e) {
			// ignored
		}
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public void discardDependents() throws GenerationException {
		Iterator dependentChildGenIter = getDependentChildren().iterator();
		while (dependentChildGenIter.hasNext())
			((IDependentGenerator) dependentChildGenIter.next()).terminate();
		getDependentChildren().clear();
	}

	/**
	 * Returns this generator's analysis result. It may return null.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.internal.codegen.helpers.AnalysisResult
	 */
	protected AnalysisResult getAnalysisResult() {
		return fAnalysisResult;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public List getChildren() {
		if (fChildren == null)
			fChildren = new ArrayList();
		return fChildren;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public IDependentGenerator getDependentGenerator(String logicalName) throws GeneratorNotFoundException {
		DependentGenerator genImplInstance;
		try {
			genImplInstance = (DependentGenerator) super.getDependentGenerator(logicalName);

			// preset the relevant instance data
			genImplInstance.setBaseAncestor(this);
		} catch (Exception x) {
			throw new GeneratorNotFoundException(logicalName);
		}
		return genImplInstance;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public IBaseGenerator getGenerator(String logicalName) throws GeneratorNotFoundException {
		BaseGenerator genImplInstance;
		try {
			genImplInstance = (BaseGenerator) getGeneratorImpl(logicalName);

			// preset the relevant instance data
			genImplInstance.fParentGenerator = this;
			addChild(genImplInstance);
			genImplInstance.fTargetContext = getTargetContext();
		} catch (Exception x) {
			throw new GeneratorNotFoundException(logicalName);
		}
		return genImplInstance;
	}

	/**
	 * Temporary method, until we can re-factor the ejbdeploy generators.
	 * <p>
	 * The {@link BaseGenerator#getGenerator(String, String, Class, TopLevelGenerationHelper)}
	 * signature is the one that should be used. That may change due to design changes that are
	 * being considered. For example, the map of logical names to generator class names may be moved
	 * from a separate XML file into the plugin.xml file as an extension to the framework.
	 *  
	 */
	public static IBaseGenerator getGenerator(String xmlDictionaryName, String logicalName) throws GeneratorNotFoundException {
		return getGenerator(xmlDictionaryName, logicalName, BaseGenerator.class, null);
	}

	/**
	 * Find the top level generator by logical name. It will use the provided top level helper.
	 * NOTE: When reference deprecated method is removed, move its code in here.
	 * <p>
	 * The {@link BaseGenerator#getGenerator(String, String, Class, TopLevelGenerationHelper)}
	 * signature is the one that should be used. That may change due to design changes that are
	 * being considered. For example, the map of logical names to generator class names may be moved
	 * from a separate XML file into the plugin.xml file as an extension to the framework.
	 */
	public static IBaseGenerator getGenerator(String xmlDictionaryName, String logicalName, TopLevelGenerationHelper helper) throws GeneratorNotFoundException {
		return getGenerator(xmlDictionaryName, logicalName, BaseGenerator.class, helper);
	}

	/**
	 * Temporary method, until we can re-factor the ejbdeploy generators,
	 * <p>
	 * The {@link BaseGenerator#getGenerator(String, String, Class, TopLevelGenerationHelper)}
	 * signature is the one that should be used. That may change due to design changes that are
	 * being considered. For example, the map of logical names to generator class names may be moved
	 * from a separate XML file into the plugin.xml file as an extension to the framework.
	 */
	public static IBaseGenerator getGenerator(String xmlDictionaryName, String logicalName, Class cls) throws GeneratorNotFoundException {
		return getGenerator(xmlDictionaryName, logicalName, cls, null);
	}

	/**
	 * <p>
	 * This signature is the one that should be used. That may change due to design changes that are
	 * being considered. For example, the map of logical names to generator class names may be moved
	 * from a separate XML file into the plugin.xml file as an extension to the framework.
	 */
	public static IBaseGenerator getGenerator(String xmlDictionaryName, String logicalName, Class cls, TopLevelGenerationHelper helper) throws GeneratorNotFoundException {
		try {
			GeneratorDictionary xmlDictionary = getGeneratorDictionary(xmlDictionaryName, cls);
			return (IBaseGenerator) getGeneratorImpl(logicalName, xmlDictionary, helper);
		} catch (java.io.FileNotFoundException x) {
			throw new GeneratorNotFoundException(x.getMessage());
		}
	}

	protected static GeneratorDictionary getGeneratorDictionary(String xmlDictionaryName, Class cls) throws FileNotFoundException {
		GeneratorDictionary dictionary = null;
		if (xmlDictionaryName != null && cls != null) {
			ClassLoader loader = cls.getClassLoader();
			String key = loader.hashCode() + "#" + xmlDictionaryName; //$NON-NLS-1$
			if (generatorDictionaryMap == null)
				generatorDictionaryMap = new HashMap();
			dictionary = (GeneratorDictionary) generatorDictionaryMap.get(key);
			if (dictionary == null) {
				dictionary = DictionaryExtensionRegistry.generateDictionary(xmlDictionaryName, loader);
				generatorDictionaryMap.put(key, dictionary);
			}
		}
		return dictionary;
	}

	protected String getName() throws GenerationException {
		return null;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public IBaseGenerator getParent() {
		return fParentGenerator;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public ITargetContext getTargetContext() {
		if (fTargetContext == null)
			fTargetContext = createTargetContext();
		return fTargetContext;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public Object getTargetElement() {
		return fTargetElement;
	}

	/**
	 * Uses the existing result if it is set. It analyzes children and sets the result if the child
	 * results are not empty.
	 */
	protected AnalysisResult primAnalyze() throws GenerationException {
		AnalysisResult result = (fAnalysisResult != null) ? fAnalysisResult : new AnalysisResult();
		analyzeChildren(result);
		if (result.getChildResults().size() > 0)
			fAnalysisResult = result;
		return fAnalysisResult;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public void run() throws GenerationException {
		runChildren();
	}

	/**
	 * Runs the child generators.
	 */
	protected void runChildren() throws GenerationException {
		Iterator childGenIter = getChildren().iterator();
		while (childGenIter.hasNext())
			((IBaseGenerator) childGenIter.next()).run();
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public void runDependents(IGenerationBuffer buffer) throws GenerationException {
		Iterator dependentChildGenIter = getDependentChildren().iterator();
		while (dependentChildGenIter.hasNext())
			((IDependentGenerator) dependentChildGenIter.next()).run(buffer);
	}

	/**
	 * Sets this generator's analysis result.
	 * 
	 * @param newAnalysisResult
	 *            org.eclipse.jst.j2ee.internal.internal.internal.codegen.helpers.AnalysisResult
	 */
	protected void setAnalysisResult(AnalysisResult newAnalysisResult) {
		fAnalysisResult = newAnalysisResult;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public void setTargetElement(Object targetElement) {
		fTargetElement = targetElement;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IGenerator
	 */
	public void terminate() throws GenerationException {
		if (fChildren != null) {
			Iterator childGenIter = fChildren.iterator();
			while (childGenIter.hasNext())
				((IBaseGenerator) childGenIter.next()).terminate();
			fChildren.clear();
			fChildren = null;
		}
		fParentGenerator = null;
		fTargetContext = null;
		fTargetElement = null;
	}

	/**
	 * Returns a debug string containing identifying information for this generator and it's
	 * children.
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buildDebugString(buffer, 0);
		return buffer.toString();
	}
}