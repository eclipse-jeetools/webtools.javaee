/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.servertarget;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;

public class J2EEProjectServerTargetOperation extends WTPOperation {

    /**
     * @param operationDataModel
     */
    public J2EEProjectServerTargetOperation(J2EEProjectServerTargetDataModel operationDataModel) {
        super(operationDataModel);
        // TODO Auto-generated constructor stub
    }

	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
	    J2EEProjectServerTargetDataModel dataModel = (J2EEProjectServerTargetDataModel) operationDataModel;
		IRuntime runtime = dataModel.getRuntimeTarget();
		IProject project = dataModel.getProject();
		if (runtime != null) {
			ServerCore.getProjectProperties(project).setRuntimeTarget(runtime, monitor);
			if (dataModel.getBooleanProperty(J2EEProjectServerTargetDataModel.UPDATE_MODULES) && EARNatureRuntime.hasRuntime(project)) {
				ServerTargetHelper.setNewServerTargetForEARModules(runtime, project);
				ServerTargetHelper.setNewServerTargetForEARUtilityJars(runtime, project);
			}
		}
	}

}
