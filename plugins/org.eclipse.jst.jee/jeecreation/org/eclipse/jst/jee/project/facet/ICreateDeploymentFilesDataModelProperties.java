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

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;

public interface ICreateDeploymentFilesDataModelProperties extends
		IDataModelProperties {

	public static final String TARGET_PROJECT = "ICreateDeploymentFilesDataModelProperties.TARGET_PROJECT"; //$NON-NLS-1$
	/**
	 *  boolean property for Java EE 5 projects, to create/not create a deployment descriptor, 
	 *  The default value is false
	 */
	public static final String GENERATE_DD = "ICreateDeploymentFilesDataModelProperties.GENERATE_DD"; //$NON-NLS-1$ 

}
