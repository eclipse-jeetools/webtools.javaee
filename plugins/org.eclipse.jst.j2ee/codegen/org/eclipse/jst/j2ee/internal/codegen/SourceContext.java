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
 * Holds the source context information for the current code generation.
 */
public class SourceContext implements ISourceContext {
	private Navigator fNavigator = null;

	/**
	 * @see ISourceContext
	 */
	public Navigator getNavigator() {
		if (fNavigator == null)
			fNavigator = new Navigator();
		return fNavigator;
	}
}