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
package org.eclipse.jst.common.jdt.internal.integration;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jdt.core.ICompilationUnit;


/**
 * The user of the Java code generation framework must supply an implementation of this interface.
 * The framework will obtain compilation working copies from this interface. The supplier of the
 * implementation is responsible for committing the working copies when appropriate for the user's
 * edit model.
 */
public interface WorkingCopyProvider {

	/**
	 * This will delete compilation unit from the workbench and fix the internal references for this
	 * working copy manager.
	 * 
	 * @param cu
	 *            the compilation unit to delete
	 * @param monitor
	 *            the progress monitor to use for the delete
	 */
	void delete(ICompilationUnit cu, IProgressMonitor monitor);

	/**
	 * Returns the working copy remembered for the compilation unit. That is, the manager already
	 * has a working copy for this unit, it does not create a new working copy. Does not connect the
	 * edit model to the working copy.
	 * 
	 * @param input
	 *            the compilation unit
	 * @return the working copy of the compilation unit, or <code>null</code> it there is no
	 *         remembered working copy for this compilation unit
	 */
	ICompilationUnit getExistingWorkingCopy(ICompilationUnit cu) throws CoreException;

	/**
	 * Returns the working copy remembered for the compilation unit or creates a new working copy
	 * for the compilation unit and returns it. If a working copy is passed in, it is returned.
	 * 
	 * @param input
	 *            the compilation unit
	 * @return the working copy of the compilation unit
	 * @exception CoreException
	 *                if the working copy can not be created
	 */
	ICompilationUnit getWorkingCopy(ICompilationUnit cu, boolean forNewCU) throws CoreException;
}