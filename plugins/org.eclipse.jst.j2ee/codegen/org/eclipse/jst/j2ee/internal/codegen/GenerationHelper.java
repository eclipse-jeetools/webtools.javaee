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
 * Abstract base class for code generation helpers.
 * 
 * Generation helpers are used to provide additional information to a set of generators. For
 * example, information about the target project may be provided. Another example would be
 * contextual information that goes beyond the metadata that is normally passed to the generators.
 */
public abstract class GenerationHelper {
	private GenerationHelper fParent = null;

	/**
	 * GenerationHelper default constructor.
	 */
	public GenerationHelper() {
		super();
	}

	/**
	 * Returns the helper this helper has been appended to. A top level helper returns null.
	 */
	public GenerationHelper getParent() {
		return fParent;
	}

	/**
	 * Sets the helper this helper has been appended to.
	 */
	public void setParent(GenerationHelper parentHelper) {
		fParent = parentHelper;
	}

	public boolean isTopLevelHelper() {
		return false;
	}

	public boolean isExtensionsHelper() {
		return false;
	}
}