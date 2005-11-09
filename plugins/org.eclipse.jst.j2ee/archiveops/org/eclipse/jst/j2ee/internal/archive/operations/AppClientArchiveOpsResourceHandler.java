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

public final class AppClientArchiveOpsResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "appclientarchiveops";//$NON-NLS-1$

	private AppClientArchiveOpsResourceHandler() {
		// Do not instantiate
	}

	public static String ARCHIVE_OPERATION_SaveFile;
	public static String ARCHIVE_OPERATION_ProjectNature;
	public static String ARCHIVE_OPERATION_SaveManifest;
	public static String ARCHIVE_OPERATION_FileNotFound;
	public static String ARCHIVE_OPERATION_OpeningArchive;
	public static String ARCHIVE_OPERATION_ImportOperation;
	public static String IMPORT_MOFRESOURCE_STRING;
	public static String APPCLIENT_IMPORT_OPERATION_STRING;
	public static String APPCLIENT_IMPORT_FILE_STRING;
	public static String APPCLIENT_IMPORT_ERROR;
	public static String ARCHIVE_OPERATION_ErrorOccured;
	public static String Application_Client_File_UI_;
	public static String _c__Copyright_IBM_Corporation_2001_1;

	static {
		NLS.initializeMessages(BUNDLE_NAME, AppClientArchiveOpsResourceHandler.class);
	}
}