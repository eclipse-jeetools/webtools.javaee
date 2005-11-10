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
package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

import org.eclipse.osgi.util.NLS;

public final class EJBArchiveOpsResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "ejbarchiveops";//$NON-NLS-1$

	private EJBArchiveOpsResourceHandler() {
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
	public static String ARCHIVE_OPERATION_InvalidEJBJar;
	public static String ARCHIVE_OPERATION_InvalidImportEJBJar;
	public static String ARCHIVE_OPERATION_ImportOperation;
	public static String EJB_IMPORT_OPERATION_STRING;
	public static String EJB_IMPORT_FILE_STRING;
	public static String IMPORT_MOFRESOURCE_STRING;
	public static String IMPORT_OPERATION_STRING;
	public static String ERROR_COPYING_FILES_FROM_ORIGINAL_JAR;
	public static String ERROR_IMPORTING_MODULE_FILE;
	public static String Yes_UI;
	public static String No_UI;
	public static String Yes_To_All_UI;
	public static String Cancel_UI_;
	public static String Error_exporting__UI_;
	public static String EJB_Jar_File_UI_;
	public static String Copy_Dep_JARS_UI;

	static {
		NLS.initializeMessages(BUNDLE_NAME, EJBArchiveOpsResourceHandler.class);
	}
}