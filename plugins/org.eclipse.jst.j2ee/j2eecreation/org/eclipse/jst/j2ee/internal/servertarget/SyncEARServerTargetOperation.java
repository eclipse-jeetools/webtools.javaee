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
 * Created on Feb 3, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.servertarget;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.wst.server.core.IRuntime;

/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class SyncEARServerTargetOperation extends ServerTargetOperation {
	/**
	 * @param dataModel
	 */
	public SyncEARServerTargetOperation(ServerTargetDataModel dataModel) {
		super(dataModel);

	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InterruptedException {
		ServerTargetDataModel dataModel = (ServerTargetDataModel) operationDataModel;
		IRuntime runtime = dataModel.getRuntimeTarget();
		IProject project = dataModel.getProject();
		if (runtime != null) {
			if (dataModel.getBooleanProperty(SyncEARServerTargetDataModel.SYNC_SERVER_TARGET_MODULES) && EARNatureRuntime.hasRuntime(project)) {
				ServerTargetHelper.setNewServerTargetForEARModules(runtime, project);
				ServerTargetHelper.setNewServerTargetForEARUtilityJars(runtime, project);
			}
		}
	}

}