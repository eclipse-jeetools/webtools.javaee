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
package org.eclipse.jst.j2ee.internal.web.archive.operations;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.web.operations.WebPropertiesUtil;
import org.eclipse.wst.common.frameworks.operations.IHeadlessRunnableWithProgress;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * Insert the type's description here. Creation date: (10/31/2001 6:45:07 PM)
 * 
 * @author: Administrator
 */
public class ContextRootUpdateOperation implements IHeadlessRunnableWithProgress {
	public String fContextRoot;
	public org.eclipse.core.resources.IProject fProject;

	/**
	 * ContextRootUpdateOperation constructor comment.
	 */
	public ContextRootUpdateOperation(IProject project, String contextRoot) {
		super();
		fProject = project;
		fContextRoot = contextRoot;
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

		// update context root in web project
		try {
			WebPropertiesUtil.updateContextRoot(fProject, fContextRoot);

			// update context root in ear project
			updateContextRootInEAR(fProject, fContextRoot);
		} catch (CoreException e) {
			throw new InvocationTargetException(e);
		}
	}

	protected void updateContextRootInEAR(org.eclipse.core.resources.IProject project, String contextRoot) {
		
//		J2EEWebNatureRuntime runtime = J2EEWebNatureRuntime.getRuntime(project);
//		if (runtime == null)
//			return;

//		EARNatureRuntime earNatureRuntime[] = runtime.getReferencingEARProjects();
		EARNatureRuntime earNatureRuntime[] = new EARNatureRuntime[0];
		EAREditModel editModel = null;
		for (int i = 0; i < earNatureRuntime.length; i++) {
			// hold the model and update the context root & release the model
			try {
				editModel = earNatureRuntime[i].getEarEditModelForWrite(this);
				if (editModel != null) {
					Module module = null;
					if ((module = earNatureRuntime[i].getModule(project)) != null) {

						// only if the module is a WebModule
						if (module instanceof WebModule) {
							//if(((WebModule)module).isSetContextRoot())
							((WebModule) module).setContextRoot(contextRoot);
						}
					}
					editModel.saveIfNecessary(this);
				}
			} catch (Throwable e) {
				Logger.getLogger().logError(e);
			} finally {
				if (editModel != null)
					editModel.releaseAccess(this);
				editModel = null;
			}

		}
	}
}