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
/*
 * Created on Sep 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.rename;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;
import org.eclipse.wst.common.framework.operation.WTPOperation;



/**
 * @author mdelder
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class UpdateWebContextRootMetadataOperation extends WTPOperation {

	private IProject newWebProject;
	private String newContextRoot;

	public UpdateWebContextRootMetadataOperation(IProject newWebProject, String newContextRoot) {
		this.newWebProject = newWebProject;
		this.newContextRoot = newContextRoot;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {

		if (this.newWebProject == null)
			return;

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		EAREditModel model = null;
		if (this.newWebProject.isAccessible()) {

			IProject[] allProjects = root.getProjects();
			for (int i = 0; i < allProjects.length; i++) {
				EARNatureRuntime earNature = EARNatureRuntime.getRuntime(allProjects[i]);
				if (earNature != null) {
					model = earNature.getEarEditModelForWrite(this);
					try {
						ModuleMapping mapping = model.getModuleMapping(this.newWebProject);
						if (mapping != null) {
							Module module = mapping.getModule();
							if (module != null && module.isWebModule()) // should always be true
								((WebModule) module).setContextRoot(this.newContextRoot);
						}
					} finally {
						if (model != null) {
							model.saveIfNecessary(monitor, this);
							model.releaseAccess(this);
							model = null;
						}
					}
				}
			}
		}
	}

}