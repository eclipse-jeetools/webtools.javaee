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



import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;

/**
 * Base class for all Java class generators.
 * 
 * <p>
 * Subclasses must implement these methods:
 * <ul>
 * <li>getName - returns the simple name of the class
 * </ul>
 * <p>
 * Subclasses will typically override these methods:
 * <ul>
 * <li>getComment - returns the comment for the class
 * <li>initialize - create and initialize child generators
 * <li>getSuperclassName - returns the superclass name (default is none, i.e. java.lang.Object)
 * </ul>
 * <p>
 * Subclasses will override these methods regularly:
 * <ul>
 * <li>deriveFlags - returns the modifier flags for the class
 * <li>getSuperInterfaceNames - returns an array of super interface names (default is none)
 * <li>terminate - to null object references and release resources
 * <li>isInner - this must be override for inner class generators
 * </ul>
 * <p>
 * Subclasses may occasionally override these methods:
 * <ul>
 * <li>createHistoryDescriptor - to take specialized properties into account when looking for the
 * old member
 * <li>dispatchToMergeStrategy - when a hook is needed for pre and/or post merge processing
 * <li>getBody - see the method description for details
 * </ul>
 */
public abstract class JavaClassGenerator extends JavaTypeGenerator {
	private static final String CLASS = "class ";//$NON-NLS-1$
	private static final String IMPLEMENTS = " implements ";//$NON-NLS-1$

	/**
	 * JavaClassGenerator default constructor.
	 */
	public JavaClassGenerator() {
		super();
	}

	/**
	 * Creates the type descriptor. Subclasses generally do not need to override this unless doing
	 * so will save significant time.
	 */
	protected JavaTypeDescriptor createTypeDescriptor() throws GenerationException {
		JavaClassDescriptor desc = new JavaClassDescriptor();
		populateClassDescriptor(desc);
		return desc;
	}

	/**
	 * Subclasses must implement to get the superclass name. Default is none, i.e java.lang.Object.
	 */
	protected String getSuperclassName() throws GenerationException {
		return null;
	}

	/**
	 * @see JavaTypeGenerator
	 */
	public boolean isClass() {
		return true;
	}

	/**
	 * Populates the type descriptor. Subclasses generally do not need to override this unless doing
	 * so will save significant time.
	 */
	protected void populateClassDescriptor(JavaClassDescriptor desc) throws GenerationException {
		super.populateTypeDescriptor(desc);
		String temp = getSuperclassName();
		if (temp != null)
			temp = temp.trim();
		desc.setSuperclassName(temp);
	}

	/**
	 * Populates the generation buffer with the class specific part of the type declaration.
	 */
	protected void primTypeKindDecl(JavaTypeDescriptor desc, IGenerationBuffer typeBuf) {
		typeBuf.append(CLASS);
		typeBuf.append(desc.getName());

		// Do the extends clause.
		String temp = ((JavaClassDescriptor) desc).getSuperclassName();
		if ((temp != null) && (temp.length() > 0)) {
			typeBuf.append(EXTENDS);
			typeBuf.append(temp);
		}

		// Do the implements clause.
		String[] names = desc.getSuperInterfaceNames();
		if ((names != null) && (names.length > 0)) {
			typeBuf.append(IMPLEMENTS);
			typeBuf.append(names);
		}
	}
}