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
package org.eclipse.jst.j2ee.internal.jca.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.FlexibleJ2EEModuleCreationOperation;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;


public class FlexibleConnectorModuleCreationOperation extends FlexibleJ2EEModuleCreationOperation {

    /**
     * @param dataModel
     */
    public FlexibleConnectorModuleCreationOperation(FlexibleConnectorModuleCreationDataModel dataModel) {
        super(dataModel);
    }


    protected void createProjectStructure() throws CoreException {
        // TODO Auto-generated method stub

    }

    protected void createDeploymentDescriptor(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        // TODO Auto-generated method stub

    }

    protected void addResources(WorkbenchComponent component) {
        // TODO Auto-generated method stub

    }

    protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
        // TODO Auto-generated method stub
    }

}
