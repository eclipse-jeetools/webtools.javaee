/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;


public class WebContentNameUpdateOperation implements IHeadlessRunnableWithProgress {

	public String fWebContentName;
	public IProject fProject;


	public WebContentNameUpdateOperation(IProject project, String webContentName) {
		super();
		fProject = project;
		fWebContentName = webContentName;
	}


	/**
	 * Runs this operation without forcing a UI dependency.
	 * 
	 * @param monitor
	 *            the progress monitor to use to display progress and receive requests for
	 *            cancelation
	 * @exception InvocationTargetException
	 *                if the run method must propagate a checked exception, it should wrap it inside
	 *                an <code>InvocationTargetException</code>; runtime exceptions are
	 *                automatically wrapped in an <code>InvocationTargetException</code> by the
	 *                calling context
	 * @exception InterruptedException
	 *                if the operation detects a request to cancel, using
	 *                <code>IProgressMonitor.isCanceled()</code>, it should exit by throwing
	 *                <code>InterruptedException</code>
	 * 
	 * @see IRunnableWithProgress
	 */
	public void run(org.eclipse.core.runtime.IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {

		try {
			WebPropertiesUtil.updateWebContentNameAndProperties(fProject, fWebContentName, monitor);
		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		}
	}

}