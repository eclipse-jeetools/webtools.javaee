/*******************************************************************************
 * Copyright (c) 2007, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class AppClientCreateDeploymentFilesDataModelProvider extends
		CreateDeploymentFilesDataModelProvider implements
		IWebCreateDeploymentFilesDataModelProperties {
	@Override
	public IDataModelOperation getDefaultOperation() {
        return new AppClientCreateDeploymentFilesOperation(model);
    }

}
