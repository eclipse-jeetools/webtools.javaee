/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.project.facet;

import org.eclipse.jst.j2ee.internal.web.archive.operations.WebFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;

public interface IWebFacetInstallDataModelProperties extends IJ2EEModuleFacetInstallDataModelProperties {

	/**
	 * This field should not be used.  It is not part of the API and may be modified in the future.
	 */
	public static Class _provider_class = WebFacetProjectCreationDataModelProvider.class;
		
	public static final String CONTEXT_ROOT = "IWebFacetInstallDataModelProperties.CONTEXT_ROOT"; //$NON-NLS-1$
	
	public static final String SOURCE_FOLDER = "IWebFacetInstallDataModelProperties.SOURCE_FOLDER"; //$NON-NLS-1$
	
	public static final String INSTALL_WEB_LIBRARY = "IWebFacetInstallDataModelProperties.INSTALL_WEB_LIBRARY"; //$NON-NLS-1$


}
