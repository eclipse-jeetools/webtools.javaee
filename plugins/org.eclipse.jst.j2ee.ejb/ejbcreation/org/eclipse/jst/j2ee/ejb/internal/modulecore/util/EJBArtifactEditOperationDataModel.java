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
package org.eclipse.jst.j2ee.ejb.internal.modulecore.util;

import org.eclipse.jst.j2ee.ejb.modulecore.util.EJBArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class EJBArtifactEditOperationDataModel extends ArtifactEditOperationDataModel {

    /* (non-Javadoc)
     * @see org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel#getDefaultOperation()
     */
    public WTPOperation getDefaultOperation() {
        return new EJBArtifactEditOperation(this);
    }
    
    public EJBArtifactEdit getEJBArtifactEditForRead() {
        return EJBArtifactEdit.getEJBArtifactEditForRead(getWorkbenchModule());
    }
}
