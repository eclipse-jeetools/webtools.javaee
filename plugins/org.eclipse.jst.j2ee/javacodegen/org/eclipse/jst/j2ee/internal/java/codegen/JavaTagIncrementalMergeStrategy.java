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



/**
 * This implementation tags members generated. Since incremental merge strategies do not ever
 * members just because they were not regenerated, this strategy is not commonly used.
 * 
 * @see IJavaMergeStrategy
 */
public class JavaTagIncrementalMergeStrategy extends JavaIncrementalMergeStrategy {
	/**
	 * JavaTagIncrementalMergeStrategy default constructor.
	 */
	public JavaTagIncrementalMergeStrategy() {
		super();
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean isTagGenerated() {
		return true;
	}
}