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
 * Created on Oct 27, 2003
 * 
 * To change the template for this generated file go to Window&gt;Preferences&gt;Java&gt;Code
 * Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.servertarget;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

/**
 * @author jsholl
 * 
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ServerTargetOperation extends WTPOperation {
	public ServerTargetOperation(ServerTargetDataModel dataModel) {
		super(dataModel);
	}

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		ServerTargetDataModel dataModel = (ServerTargetDataModel) operationDataModel;
		IRuntime runtime = dataModel.getRuntimeTarget();
		IProject project = dataModel.getProject();
		if (runtime != null) {
			ServerCore.getProjectProperties(project).setRuntimeTarget(runtime, monitor);
			if (dataModel.getBooleanProperty(ServerTargetDataModel.UPDATE_MODULES) && EARNatureRuntime.hasRuntime(project)) {
				ServerTargetHelper.setNewServerTargetForEARModules(runtime, project);
				ServerTargetHelper.setNewServerTargetForEARUtilityJars(runtime, project);
			}
		}
	}
}