/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;

public interface IJavaFacetInstallDataModelProperties extends IDataModelProperties {

	public static final String SOURCE_FOLDER_NAME = "IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME"; //$NON-NLS-1$
    public static final String DEFAULT_OUTPUT_FOLDER_NAME = "IJavaFacetInstallDataModelProperties.DEFAULT_OUTPUT_FOLDER_NAME"; //$NON-NLS-1$
    public static final String JAVA_FACET_INSTALL_CONFIG = "IJavaFacetInstallDataModelProperties.JAVA_FACET_INSTALL_CONFIG"; //$NON-NLS-1$
}
