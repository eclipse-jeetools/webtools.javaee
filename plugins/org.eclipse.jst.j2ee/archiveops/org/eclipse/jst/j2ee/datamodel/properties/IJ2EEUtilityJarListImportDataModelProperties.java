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
package org.eclipse.jst.j2ee.datamodel.properties;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;

public interface IJ2EEUtilityJarListImportDataModelProperties extends IDataModelProperties{

	/**
	 * Required. The EAR Project to which Utility Jars should be added.
	 */
	public static final String EAR_PROJECT_NAME = IJavaUtilityJarImportDataModelProperties.EAR_PROJECT_NAME;

	/**
	 * Optional. Boolean Property. True indicates to create a Project Can be used with
	 * LINK_IMPORT="true|"false" or BINARY_IMPORT="true|false" The default value is true.
	 */
	public static final String CREATE_PROJECT = "J2EEUtilityJarListImportDataModel.CREATE_PROJECT"; //$NON-NLS-1$

	/**
	 * Optional. Boolean property. True indicates to import the jar as a linked project Can be
	 * specified with BINARY_IMPORT="true|false" and/or CREATE_PROJECT="true|false" The default
	 * value is false.
	 */
	public static final String LINK_IMPORT = "J2EEUtilityJarListImportDataModel.LINK_IMPORT"; //$NON-NLS-1$

	/**
	 * Optional. Boolean Property. True indicates to create a Project Can be used with
	 * LINK_IMPORT="true|"false" or BINARY_IMPORT="true|false" The default value is true.
	 */
	public static final String CREATE_LINKED_PROJECT = "J2EEUtilityJarListImportDataModel.CREATE_LINKED_PROJECT"; //$NON-NLS-1$

	/**
	 * Optional. Boolean property. True indicates to import the jar as a linked project Can be
	 * specified with BINARY_IMPORT="true|false" and/or CREATE_PROJECT="true|false" The default
	 * value is false.
	 */
	public static final String COPY = "J2EEUtilityJarListImportDataModel.COPY"; //$NON-NLS-1$

	/**
	 * Optional. Boolean property. True indicates to import the jar as a binary project Can be
	 * specified with LINK_IMPORT="true|false" and/or CREATE_PROJECT="true|false" The default value
	 * is false.
	 */
	public static final String BINARY_IMPORT = "J2EEUtilityJarListImportDataModel.BINARY_IMPORT"; //$NON-NLS-1$

	/**
	 * Required. A java.util.List of fully qualified file names for each Utility Jar that should be
	 * imported.
	 */
	public static final String UTILITY_JAR_LIST = "J2EEUtilityJarListImportDataModel.UTILITY_JAR_LIST"; //$NON-NLS-1$

	public static final String AVAILABLE_JARS_DIRECTORY = "J2EEUtilityJarListImportDataModel.AVAILABLE_JARS_DIRECTORY"; //$NON-NLS-1$

	public static final String PROJECT_ROOT = "J2EEUtilityJarListImportDataModel.PROJECT_ROOT"; //$NON-NLS-1$

	public static final String OVERRIDE_PROJECT_ROOT = "J2EEUtilityJarListImportDataModel.OVERRIDE_PROJECT_ROOT"; //$NON-NLS-1$

	public static final String OVERWRITE_IF_NECESSARY = "J2EEUtilityJarListImportDataModel.OVERWRITE_IF_NECESSARY"; //$NON-NLS-1$

	public static final String CREATE_LINKED_PATH_VARIABLE = "J2EEUtilityJarListImportDataModel.CREATE_LINKED_PATH"; //$NON-NLS-1$

	public static final String LINKED_PATH_VARIABLE = "J2EEUtilityJarListImportDataModel.LINKED_PATH_VARIABLE"; //$NON-NLS-1$
}
