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
 * This strategy and subclass strategies leave members that are not regenerated each time alone.
 * <p>
 * This is the more common of the incremental strategies. It is use when regular user modification
 * of a generated class is expected and collisions between user code and generated code has to be
 * handled in detail.
 * 
 * @see IJavaMergeStrategy
 */
public class JavaIncrementalMergeStrategy extends JavaMergeStrategy {
	/**
	 * JavaIncrementalMergeStrategy default constructor.
	 */
	public JavaIncrementalMergeStrategy() {
		super();
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean isBatchGeneration() {
		return false;
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean isTagGenerated() {
		return false;
	}

	/**
	 * @see IJavaMergeStrategy This incremental strategy does not do anything.
	 */
	public void postProcess(JavaTypeGenerator typeGen) throws MergeException {
	}
}