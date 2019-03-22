/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.jdt.internal.integration;

import org.eclipse.core.runtime.IProgressMonitor;


/**
 * @author Administrator
 * 
 *  
 */
public interface WorkingCopyManager extends WorkingCopyProvider {

	void dispose();

	java.util.Set getAffectedFiles();

	/**
	 * This will save all of the referenced CompilationUnits to be saved.
	 */
	void saveCompilationUnits(IProgressMonitor monitor);

	/**
	 * This will save all of the new CompilationUnits to be saved.
	 */
	void saveOnlyNewCompilationUnits(IProgressMonitor monitor);

	/**
	 * Method hasWorkingCopies.
	 * 
	 * @return boolean
	 */
	boolean hasWorkingCopies();

	/**
	 * Revert all working copies.
	 */
	void revert();

}