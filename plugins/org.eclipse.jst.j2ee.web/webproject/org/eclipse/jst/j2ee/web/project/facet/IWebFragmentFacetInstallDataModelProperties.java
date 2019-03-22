/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.project.facet;

import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;

public interface IWebFragmentFacetInstallDataModelProperties extends IJ2EEModuleFacetInstallDataModelProperties {
	/**
	 * This field should not be used.  It is not part of the API and may be modified in the future.
	 */
	public static Class _provider_class = WebFragmentFacetInstallDataModelProvider.class;

	public static final String CONTENT_DIR = "IWebFragmentFacetInstallDataModelProperties.CONTENT_DIR"; //$NON-NLS-1$
	
	public static final String ADD_TO_WAR = "IWebFragmentFacetInstallDataModelProperties.ADD_TO_WAR"; //$NON-NLS-1$
	public static final String WAR_PROJECT_NAME = "IWebFragmentFacetInstallDataModelProperties.WAR_PROJECT_NAME"; //$NON-NLS-1$
	public static final String LAST_WAR_NAME = "IWebFragmentFacetInstallDataModelProperties.LAST_WAR_NAME";	//$NON-NLS-1$
	
	}
