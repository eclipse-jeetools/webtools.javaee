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
package org.eclipse.jst.j2ee.internal.web.util;

import org.eclipse.jst.j2ee.web.componentcore.util.WebArtifactEdit;
import org.eclipse.wst.common.componentcore.internal.operation.ArtifactEditOperationDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class WebArtifactEditOperationDataModel extends ArtifactEditOperationDataModel {

    public WTPOperation getDefaultOperation() {
        return new WebArtifactEditOperation(this);
    }
    
    public WebArtifactEdit getWebArtifactEditForRead() {
        return WebArtifactEdit.getWebArtifactEditForRead(getComponent());
    }
}
