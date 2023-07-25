/*******************************************************************************
 * Copyright (c) 2009, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class ConnectorCreateDeploymentFilesDataModelProvider extends
		CreateDeploymentFilesDataModelProvider  {
	@Override
	public IDataModelOperation getDefaultOperation() {
        return new ConnectorCreateDeploymentFilesFilesOperation(model);
    }

}
