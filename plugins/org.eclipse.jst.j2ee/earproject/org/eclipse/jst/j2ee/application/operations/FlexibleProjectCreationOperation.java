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
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.internal.servertarget.J2EEProjectServerTargetDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.J2EEProjectServerTargetOperation;
import org.eclipse.wst.common.frameworks.internal.WTPProjectUtilities;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ProjectComponents;
import org.eclipse.wst.common.modulecore.internal.util.IModuleConstants;

public class FlexibleProjectCreationOperation extends WTPOperation {

    public FlexibleProjectCreationOperation(flexibleProjectCreationDataModel operationDataModel) {
        super(operationDataModel);
        // TODO Auto-generated constructor stub
    }

    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        createProject(monitor);
        addServerTarget(monitor);
	    WTPProjectUtilities.addNatureToProjectLast(getProject(), IModuleConstants.MODULE_NATURE_ID);
        createInitialWTPModulesFile();
    }

    private void addServerTarget(IProgressMonitor monitor)  throws CoreException, InvocationTargetException, InterruptedException{
        J2EEProjectServerTargetDataModel serverModel = ((flexibleProjectCreationDataModel) operationDataModel).getServerTargetDataModel();
        J2EEProjectServerTargetOperation serverTargetOperation = (J2EEProjectServerTargetOperation)serverModel.getDefaultOperation();
        serverTargetOperation.doRun(monitor);
    }

    private void createProject(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        ProjectCreationDataModel projModel = ((flexibleProjectCreationDataModel) operationDataModel).getProjectDataModel();
        WTPOperation op = projModel.getDefaultOperation();
        op.doRun(monitor);
    }
    
    private void createInitialWTPModulesFile() {
    	ModuleCore moduleCore = null;
		try {
			IProject containingProject = getProject();
			moduleCore = ModuleCore.getModuleCoreForWrite(containingProject);
			moduleCore.prepareProjectModulesIfNecessary(); 
			ProjectComponents projectModules = moduleCore.getModuleModelRoot();
			moduleCore.saveIfNecessary(null); 
		} finally {
			if(moduleCore != null)
				moduleCore.dispose();
		}     
    }
    
    protected IProject getProject() {
        String name = (String) ((flexibleProjectCreationDataModel) operationDataModel).getStringProperty(flexibleProjectCreationDataModel.PROJECT_NAME);
        if (name != null && name.length() > 0)
            return ResourcesPlugin.getWorkspace().getRoot().getProject(name);
        return null;
    }
}
