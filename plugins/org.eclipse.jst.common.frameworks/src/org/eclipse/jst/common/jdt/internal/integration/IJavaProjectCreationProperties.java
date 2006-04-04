/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.jdt.internal.integration;

import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;
import org.eclipse.wst.common.frameworks.internal.operations.IProjectCreationProperties;

/**
 * This has been slated for removal post WTP 1.5. Do not use this class/interface
 * 
 * @deprecated
 * 
 * @see IJavaFacetInstallDataModelProperties
 */
public interface IJavaProjectCreationProperties extends IProjectCreationProperties, DoNotUseMeThisWillBeDeletedPost15 {

	/**
	 * Optional, type String []. These names are relative.
	 */
	public static final String SOURCE_FOLDERS = "JavaProjectCreationDataModel.SOURCE_FOLDERS"; //$NON-NLS-1$
	/**
	 * Optional, type Boolean default is True
	 */
	public static final String CREATE_SOURCE_FOLDERS = "JavaProjectCreationDataModel.CREATE_SOURCE_FOLDERS"; //$NON-NLS-1$


	/**
	 * Optional, type IClasspathEntry[]
	 */
	public static final String CLASSPATH_ENTRIES = "JavaProjectCreationDataModel.CLASSPATH_ENTRIES"; //$NON-NLS-1$

	/**
	 * Optional, type String
	 */
	public static final String OUTPUT_LOCATION = "JavaProjectCreationDataModel.OUTPUT_LOCATION"; //$NON-NLS-1$
	
}
