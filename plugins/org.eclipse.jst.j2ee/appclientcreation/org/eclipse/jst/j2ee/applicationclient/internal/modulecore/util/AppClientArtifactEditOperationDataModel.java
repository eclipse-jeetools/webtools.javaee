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
package org.eclipse.jst.j2ee.applicationclient.internal.modulecore.util;

import org.eclipse.jst.j2ee.applicationclient.componentcore.util.AppClientArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class AppClientArtifactEditOperationDataModel extends ArtifactEditOperationDataModel {

    /* (non-Javadoc)
     * @see org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel#getDefaultOperation()
     */
    public WTPOperation getDefaultOperation() {
        return new AppClientArtifactEditOperation(this);
    }
    
    public AppClientArtifactEdit getAppClientArtifactEditForRead() {
		ComponentHandle handle = ComponentHandle.create(StructureEdit.getContainingProject(getWorkbenchModule()),getWorkbenchModule().getName());
        return AppClientArtifactEdit.getAppClientArtifactEditForRead(handle);
    }
}
