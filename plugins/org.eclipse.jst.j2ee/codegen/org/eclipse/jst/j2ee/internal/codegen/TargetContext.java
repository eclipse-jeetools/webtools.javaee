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



import org.eclipse.core.runtime.IProgressMonitor;


/**
 * Holds the target context information for the current code generation.
 */
public class TargetContext implements ITargetContext {
	private Navigator fNavigator = null;
	private IProgressMonitor fProgressMonitor = null;

	/**
	 * This implementation returns an {@link GenerationBuffer}.
	 * 
	 * @see ITargetContext
	 */
	public IGenerationBuffer createGenerationBuffer() {
		return new GenerationBuffer();
	}

	/**
	 * @see ITargetContext
	 */
	public Navigator getNavigator() {
		if (fNavigator == null)
			fNavigator = new Navigator();
		return fNavigator;
	}

	/**
	 * @see ITargetContext
	 */
	public IProgressMonitor getProgressMonitor() {
		if (fProgressMonitor == null)
			fProgressMonitor = org.eclipse.core.internal.runtime.Policy.monitorFor(null);
		return fProgressMonitor;
	}

	/**
	 * @see ITargetContext
	 */
	public void setProgressMonitor(IProgressMonitor progressMonitor) {
		fProgressMonitor = progressMonitor;
	}
}