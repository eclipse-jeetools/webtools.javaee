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
import org.eclipse.wst.common.modulecore.ArtifactEdit;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperation;

public class EJBArtifactEditOperation extends ArtifactEditOperation {

    public EJBArtifactEditOperation(EJBArtifactEditOperationDataModel operationDataModel) {
        super(operationDataModel);
    }
    protected ArtifactEdit getArtifactEditForModule(WorkbenchComponent module) {
        return EJBArtifactEdit.getEJBArtifactEditForWrite(module);
    }
    
    protected EJBArtifactEdit getEJBArtifactEdit() {
        return (EJBArtifactEdit)getArtifactEdit();
    }
}
