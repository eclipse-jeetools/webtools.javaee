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
 * Base class for all Java interface generators.
 * 
 * <p>
 * Subclasses must implement these methods:
 * <ul>
 * <li>getName - returns the simple name of the interface
 * </ul>
 * <p>
 * Subclasses will typically override these methods:
 * <ul>
 * <li>getComment - returns the comment for the interface
 * <li>initialize - create and initialize child generators
 * </ul>
 * <p>
 * Subclasses will override these methods regularly:
 * <ul>
 * <li>deriveFlags - returns the modifier flags for the interface
 * <li>getSuperInterfaceNames - returns an array of super interface names (default is none)
 * <li>terminate - to null object references and release resources
 * <li>isInner - this must be override for inner interface generators
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
public abstract class JavaInterfaceGenerator extends JavaTypeGenerator {
	private static final String INTERFACE = "interface ";//$NON-NLS-1$

	/**
	 * JavaInterfaceGenerator default constructor.
	 */
	public JavaInterfaceGenerator() {
		super();
	}

	/**
	 * Creates the type descriptor. Subclasses generally do not need to override this unless doing
	 * so will save significant time.
	 */
	protected JavaTypeDescriptor createTypeDescriptor() throws GenerationException {
		JavaTypeDescriptor desc = new JavaInterfaceDescriptor();
		populateTypeDescriptor(desc);
		return desc;
	}

	/**
	 * @see JavaTypeGenerator
	 */
	public boolean isInterface() {
		return true;
	}

	/**
	 * Populates the generation buffer with the interface specific part of the type declaration.
	 */
	protected void primTypeKindDecl(JavaTypeDescriptor desc, IGenerationBuffer typeBuf) {
		typeBuf.append(INTERFACE);
		typeBuf.append(desc.getName());

		// Do the extends clause.
		String[] names = desc.getSuperInterfaceNames();
		if ((names != null) && (names.length > 0)) {
			typeBuf.append(EXTENDS);
			typeBuf.append(names);
		}
	}
}