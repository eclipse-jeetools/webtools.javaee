/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*


 */
package org.eclipse.jem.internal.proxy.initParser.tree;

/**
 * TypeSafe enumerations for the IExpression processing.
 * 
 * @since 1.1.0
 */
public interface Enum {

	/**
	 * Returns the name of the enumerator.
	 * 
	 * @return the name.
	 */
	String getName();

	/**
	 * Returns the <code>int</code> value of the enumerator.
	 * 
	 * @return the value.
	 */
	int getValue();
}