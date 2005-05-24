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
package org.eclipse.jst.j2ee.internal.modulecore.util;

import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

//TODO delete
/**
 * @deprecated
 *
 */
public class EARArtifactEditOperationDataModel extends ArtifactEditOperationDataModel {

    public WTPOperation getDefaultOperation() {
        return new EARArtifactEditOperation(this);
    }
    
    public EARArtifactEdit getEARArtifactEditForRead() {
        return EARArtifactEdit.getEARArtifactEditForRead(getComponent());
    }
 
}
