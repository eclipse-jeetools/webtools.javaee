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



import org.eclipse.jst.j2ee.internal.codegen.GenerationBuffer;

/**
 * This is a specialized generation buffer for Java generation.
 */
public class JavaGenerationBuffer extends GenerationBuffer {
	/**
	 * JavaGenerationBuffer default constructor.
	 */
	public JavaGenerationBuffer() {
		super();
	}

	/**
	 * Adds a user code point to the buffer using the given name and content. The content can be
	 * null. If there is content, it must not have margins and must have a line separator at the
	 * end.
	 * <p>
	 * Note that these VAJ style user code points are not encouraged. They are supported by the
	 * framework only for compatibility reasons. Use intelligent merge strategies and merglets
	 * instead whenever possible.
	 */
	protected void addUserCodePoint(String codePointName, String content, IJavaMergeStrategy strategy) {
		formatWithMargin(strategy.getUserCodeBeginTemplate(), new String[]{codePointName});
		append(IJavaGenConstants.LINE_SEPARATOR);
		if (content != null)
			appendWithMargin(content);
		appendWithMargin(strategy.getUserCodeEnd());
		append(org.eclipse.jst.j2ee.internal.codegen.IBaseGenConstants.LINE_SEPARATOR);
	}
}