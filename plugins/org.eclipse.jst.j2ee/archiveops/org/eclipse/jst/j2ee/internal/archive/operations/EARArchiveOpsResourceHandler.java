/*******************************************************************************
 * Copyright (c) 2000, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.archive.operations;

import org.eclipse.osgi.util.NLS;

public final class EARArchiveOpsResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "eararchiveops";//$NON-NLS-1$

	private EARArchiveOpsResourceHandler() {
		// Do not instantiate
	}

	public static String ARCHIVE_OPERATION_SaveMofResources;
	public static String ARCHIVE_OPERATION_SaveFile;
	public static String ARCHIVE_OPERATION_ProjectNature;
	public static String ARCHIVE_OPERATION_SaveManifest;
	public static String ARCHIVE_OPERATION_FileNotFound;
	public static String ARCHIVE_OPERATION_FileContents;
	public static String ARCHIVE_OPERATION_ErroOccured;
	public static String ARCHIVE_OPERATION_FilesFromProject;
	public static String ARCHIVE_OPERATION_OpeningArchive;
	public static String ARCHIVE_OPERATION_ImportOperation;
	public static String IMPORT_MOFRESOURCE_STRING;
	public static String EAR_IMPORT_FILE_STRING;
	public static String ERROR_IMPORTING_EAR_FILE;
	public static String ERROR_EXPORTING_EAR_FILE;
	public static String IMPORTING_EAR_FILE_UI_;
	public static String Updating_project_classpath_UI_;
	public static String UNABLE_TO_LOAD_MODULE_ERROR_;
	public static String _jar_UI_;
	public static String Updating_manifest_Class_Path__attributes_UI_;
	public static String _preDeploy_ERROR_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, EARArchiveOpsResourceHandler.class);
	}
}