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



import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.codegen.ITargetContext;
import org.eclipse.jst.j2ee.internal.codegen.TargetContext;


/**
 * Holds the target context information for the current code generation.
 */
public class JavaTargetContext extends TargetContext {
	/**
	 * This implementation returns an {@link JavaGenerationBuffer}.
	 * 
	 * @see ITargetContext
	 */
	public IGenerationBuffer createGenerationBuffer() {
		return new JavaGenerationBuffer();
	}
}