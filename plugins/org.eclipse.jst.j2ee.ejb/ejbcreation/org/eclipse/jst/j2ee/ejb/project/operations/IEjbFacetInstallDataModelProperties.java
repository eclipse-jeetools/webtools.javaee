/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.ejb.project.operations;

import org.eclipse.jst.j2ee.internal.ejb.project.operations.EjbFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;

public interface IEjbFacetInstallDataModelProperties 
	extends IJ2EEModuleFacetInstallDataModelProperties {

	/**
	 * This field should not be used.  It is not part of the API and may be modified in the future.
	 */
	public static Class _provider_class = EjbFacetProjectCreationDataModelProvider.class;
	
    public static final String CREATE_CLIENT = "IEjbFacetInstallDataModelProperties.CREATE_CLIENT"; //$NON-NLS-1$	
	public static final String CLIENT_NAME = "IEjbFacetInstallDataModelProperties.CLIENT_NAME ";//$NON-NLS-1$	
	public static final String CLIENT_SOURCE_FOLDER = "IEjbFacetInstallDataModelProperties.CLIENT_SOURCE_FOLDER ";//$NON-NLS-1$
	public static final String CLIENT_URI = "IEjbFacetInstallDataModelProperties.CLIENT_URI ";//$NON-NLS-1$
	public static final String ALLOW_EJB_CLIENT = "IEjbFacetInstallDataModelProperties.ALLOW_EJB_CLIENT ";//$NON-NLS-1$
}
