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
public interface ITargetContext {
	/**
	 * Returns a new generation buffer.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.codegen.api.IGenerationBuffer
	 */
	IGenerationBuffer createGenerationBuffer();

	/**
	 * Returns the navigator for the target model.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.codegen.Navigator
	 */
	Navigator getNavigator();

	/**
	 * Returns the progress monitor for this generation. This never returns null. It may return the
	 * NullProgressMonitor singleton.
	 * 
	 * @return com.ibm.itp.common.IProgressMonitor
	 */
	IProgressMonitor getProgressMonitor();

	/**
	 * Sets the progress monitor for this generation.
	 * 
	 * @param progressMonitor
	 *            com.ibm.itp.common.IProgressMonitor
	 */
	void setProgressMonitor(IProgressMonitor progressMonitor);
}