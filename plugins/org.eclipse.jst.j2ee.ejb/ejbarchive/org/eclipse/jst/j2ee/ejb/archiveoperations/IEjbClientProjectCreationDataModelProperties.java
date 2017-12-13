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
package org.eclipse.jst.j2ee.ejb.archiveoperations;

import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbClientProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.IJavaUtilityProjectCreationDataModelProperties;

public interface IEjbClientProjectCreationDataModelProperties extends IJavaUtilityProjectCreationDataModelProperties{
	/**
	 * This field should not be used.  It is not part of the API and may be modified in the future.
	 */
	public static Class _provider_class = EjbClientProjectCreationDataModelProvider.class;

	public static final String EJB_PROJECT_NAME = "IEjbClientProjectCreationDataModelProperties.EJB_PROJECT_NAME"; //$NON-NLS-1$
	public static final String CLIENT_URI = "IEjbClientProjectCreationDataModelProperties.CLIENT_URI ";//$NON-NLS-1$	
	public static final String ADD_TO_EAR = IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR;
	public static final String DEFAULT_OUTPUT_FOLDER = "IEjbClientProjectCreationDataModelProperties.DEFAULT_OUTPUT_FOLDER";//$NON-NLS-1$
}
