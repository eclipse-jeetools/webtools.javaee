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



import org.eclipse.jdt.core.jdom.DOMFactory;
import org.eclipse.jdt.core.jdom.IDOMFactory;
import org.eclipse.jst.j2ee.internal.codegen.BaseGenerator;
import org.eclipse.jst.j2ee.internal.codegen.GenerationException;
import org.eclipse.jst.j2ee.internal.codegen.IBaseGenerator;
import org.eclipse.jst.j2ee.internal.codegen.IGenerationBuffer;
import org.eclipse.jst.j2ee.internal.codegen.ITargetContext;
import org.eclipse.wst.common.jdt.internal.integration.WorkingCopyProvider;


/**
 * Defines the methods all java generators must implement.
 */
public abstract class JavaElementGenerator extends BaseGenerator {
	private static org.eclipse.jdt.core.jdom.IDOMFactory fDOMFactory;
	private IGenerationBuffer fGenerationBuffer = null;

	/**
	 * Creates a new Java base generator.
	 */
	public JavaElementGenerator() {
		super();
	}

	/**
	 * Creates the target context implementation.
	 */
	protected ITargetContext createTargetContext() {
		return new JavaTargetContext();
	}

	/**
	 * Returns the depth of this member. Returns 0 for a top level type. Returns 1 for a member of a
	 * top level type. Returns 2 for a member of an inner type of a top level type. And so on.
	 */
	private int getDepth() {
		int depth = 0;
		IBaseGenerator ancestorGen = getParent();
		while (ancestorGen != null) {
			if (ancestorGen instanceof JavaTypeGenerator)
				depth++;
			ancestorGen = ancestorGen.getParent();
		}
		return depth;
	}

	/**
	 * Used by generators that need a JDOM scratch pad. Most notably, this is used for merging.
	 */
	public static IDOMFactory getDOMFactory() {
		if (fDOMFactory == null)
			fDOMFactory = new DOMFactory();
		return fDOMFactory;
	}

	/**
	 * Returns the member's generation buffer with proper initial indent.
	 */
	protected IGenerationBuffer getGenerationBuffer() {
		if (fGenerationBuffer == null) {
			fGenerationBuffer = getTargetContext().createGenerationBuffer();
			fGenerationBuffer.setIndent(getDepth());
		}
		return fGenerationBuffer;
	}

	/**
	 * Returns the working copy provider for this generation.
	 * 
	 * @see WorkingCopyProvider
	 */
	protected WorkingCopyProvider getWorkingCopyProvider() {
		return ((JavaTopLevelGenerationHelper) getTopLevelHelper()).getWorkingCopyProvider();
	}

	/**
	 * See immediate subclasses for more details. In general, this method returns true if the target
	 * has been obtained from a compilation unit working copy.
	 */
	public abstract boolean isPrepared() throws GenerationException;

	/**
	 * See immediate subclasses for more details. In general, this method ensures that the target
	 * has been obtained from a compilation unit working copy or will be so obtained the next time
	 * it is requested.
	 */
	public abstract void prepare() throws GenerationException;

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.internal.codegen.api.IBaseGenerator
	 */
	public void terminate() throws GenerationException {
		super.terminate();
		fGenerationBuffer = null;
	}
}