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
 * This implementation tags members generated and deletes generated members that are not regenerated
 * each time.
 * <p>
 * During regeneration, members not marked generated are left alone. However, members that are
 * marked generated and are not regenerated each time are deleted.
 * <p>
 * This is the default merge strategy returned by
 * {@link JavaTopLevelGenerationHelper#createMergeStrategy()}.
 * 
 * @see IJavaMergeStrategy
 */
public class JavaTagBatchMergeStrategy extends JavaBatchMergeStrategy {
	/**
	 * JavaTagBatchMergeStrategy default constructor.
	 */
	public JavaTagBatchMergeStrategy() {
		super();
	}

	/**
	 * @see IJavaMergeStrategy
	 */
	public boolean isTagGenerated() {
		return true;
	}
}