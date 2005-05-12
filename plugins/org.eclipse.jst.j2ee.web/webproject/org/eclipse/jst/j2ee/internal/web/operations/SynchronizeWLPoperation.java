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
 * Created on Sep 15, 2003
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetHelper;
import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.frameworks.internal.operations.IHeadlessRunnableWithProgress;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.web.internal.operation.ILibModule;

/**
 * Given a Web Project, this operation will sychronize all its Web Library Projects (WLPs), i.e., it
 * will change the target server of all WLPs to that of the Web Project.
 * 
 * @author Pratik Shah
 */
public class SynchronizeWLPoperation implements IHeadlessRunnableWithProgress {
	private IProject prj;

	/**
	 * Constructor
	 * 
	 * @param webProject
	 *            A Web Project whose WLPs' target servers have to be changed
	 */
	public SynchronizeWLPoperation(IProject webProject) {
		prj = webProject;
	}

	/**
	 * Synchronizes all WLPs to have the same target server as the given Web project.
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.operations.IHeadlessRunnableWithProgress#run(org.eclipse.core.runtime.IProgressMonitor)
	 */
	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		IRuntime target = ServerTargetUtil.getServerTarget(prj.getName());
		ILibModule[] wlps = getLibModules();
		monitor.beginTask(ProjectSupportResourceHandler.getString("Sync_WLP_Op"), wlps.length); //$NON-NLS-1$
		for (int i = 0; i < wlps.length; i++) {
			IProject module = wlps[i].getProject();
			if (target != null) {
				ServerTargetHelper.cleanUpNonServerTargetClasspath(module);
				try {
					ServerCore.getProjectProperties(module).setRuntimeTarget(target, monitor);
				} catch (CoreException ce) {
					// TODO - handle exception
				}
			}
			monitor.worked(1);
		}
		monitor.done();
	}
	
	protected ILibModule[] getLibModules() {
		//TODO this will throw class cast exception, do we use ILibModule anymore?
		WebArtifactEdit webEdit = null;
		try {
			//TODO migrate to flex project
			//webEdit = (WebArtifactEdit) StructureEdit.getFirstArtifactEditForRead(prj);
			if (webEdit != null)
				return (ILibModule[]) webEdit.getLibModules();
		} finally {
			if (webEdit != null)
				webEdit.dispose();
		}
		return new ILibModule[] {};
	}
}