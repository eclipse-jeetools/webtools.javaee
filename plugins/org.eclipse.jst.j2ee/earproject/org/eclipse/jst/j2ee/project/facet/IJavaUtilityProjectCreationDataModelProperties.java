/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;

public interface IJavaUtilityProjectCreationDataModelProperties extends IDataModelProperties {
	/**
	 * This field should not be used.  It is not part of the API and may be modified in the future.
	 */
	public static Class _provider_class = JavaUtilityProjectCreationDataModelProvider.class;

	public static final String PROJECT_NAME = "IJavaUtilityProjectCreationDataModelProperties.PROJECT_NAME"; //$NON-NLS-1$
	
//	public static final String PROJECT_LOCATION = "IJavaUtilityProjectCreationDataModelProperties.PROJECT_LOCATION"; //$NON-NLS-1$	
	
	public static final String EAR_PROJECT_NAME = "IJavaUtilityProjectCreationDataModelProperties.EAR_PROJECT_NAME"; //$NON-NLS-1$

	public static final String RUNTIME = "IJavaUtilityProjectCreationDataModelProperties.RUNTIME";	//$NON-NLS-1$
	
	public static final String SOURCE_FOLDER = "IJavaUtilityProjectCreationDataModelProperties.SOURCE_FOLDER";	//$NON-NLS-1$
}
