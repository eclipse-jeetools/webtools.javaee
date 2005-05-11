/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 *  $RCSfile: Enum.java,v $
 *  $Revision: 1.1 $  $Date: 2005/05/11 19:01:12 $ 
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