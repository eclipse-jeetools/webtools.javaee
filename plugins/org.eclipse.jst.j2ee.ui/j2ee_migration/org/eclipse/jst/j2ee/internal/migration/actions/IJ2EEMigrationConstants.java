/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.migration.actions;

import org.eclipse.jst.j2ee.migration.J2EESpecificationMigrationConstants;

public interface IJ2EEMigrationConstants {
	//Metadata version constants
	public static final String J2EE_VERSION_1_2 = J2EESpecificationMigrationConstants.J2EE_VERSION_1_2;
	public static final String J2EE_VERSION_1_3 = J2EESpecificationMigrationConstants.J2EE_VERSION_1_3;
	public static final String J2EE_VERSION_1_4 = J2EESpecificationMigrationConstants.J2EE_VERSION_1_4;

	//wizardTitle:
	public static String J2EE_PROJECT_MIGRATION_TITLE = J2EEMigrationUIResourceHandler.getString("J2EE_PROJECT_MIGRATION_TITLE_UI_"); //$NON-NLS-1$
	//bannerTitle:
	public static String ENTERPRISE_APPLICATIONS = J2EEMigrationUIResourceHandler.getString("ENTERPRISE_APPLICATIONS_UI_"); //$NON-NLS-1$
	//bannerDesc:
	public static String WIZ_BAN_DESC = J2EEMigrationUIResourceHandler.getString("WIZ_BAN_DESC_UI_"); //$NON-NLS-1$
	//listboxTitle:
	public static String ENTERPRISE_APP_PROJECTS = J2EEMigrationUIResourceHandler.getString("ENTERPRISE_APP_PROJECTS_UI_"); //$NON-NLS-1$
	//checkbox1Text:
	public static String MIGRATE_J2EE_SPEC = J2EEMigrationUIResourceHandler.getString("MIGRATE_J2EE_SPEC_UI_"); //$NON-NLS-1$
	//checkboxText:
	public static String MIGRATE_EJB_SPEC = J2EEMigrationUIResourceHandler.getString("MIGRATE_EJB_SPEC_UI_"); //$NON-NLS-1$
	//checkboxText:
	public static String MIGRATE_MODULE_PROJECTS = J2EEMigrationUIResourceHandler.getString("MIGRATE_MODULE_PROJECTS_UI_"); //$NON-NLS-1$

	//bannerTitle:
	public static String APPLICATION_CLIENTS = J2EEMigrationUIResourceHandler.getString("APPLICATION_CLIENTS_UI_"); //$NON-NLS-1$
	//listboxTitle:
	public static String APPLICATION_CLIENT_PROJECTS = J2EEMigrationUIResourceHandler.getString("APPLICATION_CLIENT_PROJECTS_UI_"); //$NON-NLS-1$
	//bannerTitle:
	public static String ENTERPRISE_BEANS_BAN = J2EEMigrationUIResourceHandler.getString("ENTERPRISE_BEANS_BAN_UI_"); //$NON-NLS-1$
	//listboxTitle:
	public static String EJB_PROJECTS = J2EEMigrationUIResourceHandler.getString("EJB_PROJECTS_UI_"); //$NON-NLS-1$


	//checkboxText:
	public static String ADD_LOCAL_CLIENT_VIEWS_CHECK = J2EEMigrationUIResourceHandler.getString("ADD_LOCAL_CLIENT_VIEWS_CHECK_UI_"); //$NON-NLS-1$
	//checkboxText:
	public static String MIGRATE_CMP_BEANS = J2EEMigrationUIResourceHandler.getString("MIGRATE_CMP_BEANS_UI_"); //$NON-NLS-1$

	//AddLocal:
	//bannerTitle:
	public static String ADD_LOCAL_CLIENT_VIEWS_BAN = J2EEMigrationUIResourceHandler.getString("ADD_LOCAL_CLIENT_VIEWS_BAN_UI_"); //$NON-NLS-1$
	//bannerDesc:
	public static String SELECT_EJB_CLIENT_VIEWS = J2EEMigrationUIResourceHandler.getString("SELECT_EJB_CLIENT_VIEWS_UI_"); //$NON-NLS-1$
	//listboxTitle:
	public static String ENTERPRISE_BEANS_LIST = J2EEMigrationUIResourceHandler.getString("ENTERPRISE_BEANS_LIST_UI_"); //$NON-NLS-1$
	//checkboxText:
	public static String DELETE_REM_CLIENT_VIEWS = J2EEMigrationUIResourceHandler.getString("DELETE_REM_CLIENT_VIEWS_UI_"); //$NON-NLS-1$
	//radiobuttonHeaderText:
	public static String LOCAL_CLIENT_VIEW_SCHEME = J2EEMigrationUIResourceHandler.getString("LOCAL_CLIENT_VIEW_SCHEME_UI_"); //$NON-NLS-1$
	//radiobuttonText:
	public static String REUSE_DELETED_CLIENT_VIEW_NAME = J2EEMigrationUIResourceHandler.getString("REUSE_DELETED_CLIENT_VIEW_NAME_UI_"); //$NON-NLS-1$
	//radiobuttonText:(needs colon as we want an entry field next to this text)
	public static String SPECIFY_SUFFIX = J2EEMigrationUIResourceHandler.getString("SPECIFY_SUFFIX_UI_"); //$NON-NLS-1$

	//suffixExampleText:
	public static String SUFFIX_EXAMPLE = J2EEMigrationUIResourceHandler.getString("SUFFIX_EXAMPLE_UI_"); //$NON-NLS-1$

	//Migration Status Report Messages:
	public static String MIGRATION_SUCCESS_REPORT = J2EEMigrationUIResourceHandler.getString("MIGRATION_SUCCESS_REPORT_UI_"); //$NON-NLS-1$
	public static String MIGRATION_WARNINGS_REPORT = J2EEMigrationUIResourceHandler.getString("MIGRATION_WARNINGS_REPORT_UI_"); //$NON-NLS-1$
	public static String MIGRATION_ERRORS_REPORT = J2EEMigrationUIResourceHandler.getString("MIGRATION_ERRORS_REPORT_UI_"); //$NON-NLS-1$
	public static String MIGRATION_NOT_NEEDED_REPORT = J2EEMigrationUIResourceHandler.getString("MIGRATION_NOT_NEEDED_REPORT"); //$NON-NLS-1$
	public static String MIGRATION_NOT_POSSIBLE_REPORT = J2EEMigrationUIResourceHandler.getString("MIGRATION_NOT_POSSIBLE_REPORT"); //$NON-NLS-1$
	public static String PROJECT_STRUCTURE_SUCCESS = J2EEMigrationUIResourceHandler.getString("PROJECT_STRUCTURE_SUCCESS_UI_"); //$NON-NLS-1$
	public static String PROJECT_STRUCTURE_FAILED = J2EEMigrationUIResourceHandler.getString("PROJECT_STRUCTURE_FAILED_UI_"); //$NON-NLS-1$
	public static String PROJECT_NOT_NEED_MIGRATION = J2EEMigrationUIResourceHandler.getString("PROJECT_NOT_NEED_MIGRATION_UI_"); //$NON-NLS-1$
	public static String J2EE_VERSION_SUCCESS = J2EEMigrationUIResourceHandler.getString("J2EE_VERSION_SUCCESS_UI_"); //$NON-NLS-1$
	public static String J2EE_VERSION_FAILED = J2EEMigrationUIResourceHandler.getString("J2EE_VERSION_FAILED_UI_"); //$NON-NLS-1$
	public static String J2EE_VERSION_NOT_NEED_MIGRATION = J2EEMigrationUIResourceHandler.getString("J2EE_VERSION_NOT_NEED_MIGRATION_UI_"); //$NON-NLS-1$
	public static String LOCAL_CLIENT_ADD_SUCCESS = J2EEMigrationUIResourceHandler.getString("LOCAL_CLIENT_ADD_SUCCESS_UI_"); //$NON-NLS-1$
	public static String LOCAL_CLIENT_ADD_FAILED = J2EEMigrationUIResourceHandler.getString("LOCAL_CLIENT_ADD_FAILED_UI_"); //$NON-NLS-1$
	public static String LOCAL_CLIENT_VIEWS_EXIST = J2EEMigrationUIResourceHandler.getString("LOCAL_CLIENT_VIEWS_EXIST_UI_"); //$NON-NLS-1$
	public static String REMOTE_CLIENT_DELETE_SUCCESS = J2EEMigrationUIResourceHandler.getString("REMOTE_CLIENT_DELETE_SUCCESS_UI_"); //$NON-NLS-1$
	public static String REMOTE_CLIENT_DELETE_FAILED = J2EEMigrationUIResourceHandler.getString("REMOTE_CLIENT_DELETE_FAILED_UI_"); //$NON-NLS-1$
	public static String REMOTE_CLIENT_VIEWS_NOT_EXIST = J2EEMigrationUIResourceHandler.getString("REMOTE_CLIENT_VIEWS_NOT_EXIST_UI_"); //$NON-NLS-1$
	public static String SOURCE_FOLDER_RENAME_FAILED = J2EEMigrationUIResourceHandler.getString("SOURCE_FOLDER_RENAME_FAILED"); //$NON-NLS-1$
	public static String SOURCE_FOLDER_RENAME_SKIPPED = J2EEMigrationUIResourceHandler.getString("SOURCE_FOLDER_RENAME_SKIPPED"); //$NON-NLS-1$
	public static String WEBCONTENT_FOLDER_RENAME_FAILED = J2EEMigrationUIResourceHandler.getString("WEBCONTENT_FOLDER_RENAME_FAILED"); //$NON-NLS-1$
	public static String WEBCONTENT_FOLDER_RENAME_SKIPPED = J2EEMigrationUIResourceHandler.getString("WEBCONTENT_FOLDER_RENAME_SKIPPED"); //$NON-NLS-1$
	public static String WEBPROJECT_VERSION_MIGRATE_FAILED = J2EEMigrationUIResourceHandler.getString("WEBPROJECT_VERSION_MIGRATE_FAILED"); //$NON-NLS-1$
	public static String WEBCONTENT_LIBPATH_UPDATE_FAILED = J2EEMigrationUIResourceHandler.getString("WEBCONTENT_LIBPATH_UPDATE_FAILED"); //$NON-NLS-1$
	public static String FAILED_DEPLOY_DELETE = J2EEMigrationUIResourceHandler.getString("FAILED_DEPLOY_DELETE"); //$NON-NLS-1$
	public static String COMPLETED_DEPLOY_DELETE = J2EEMigrationUIResourceHandler.getString("COMPLETED_DEPLOY_DELETE"); //$NON-NLS-1$
	public static String FAILED_LOCAL_CLIENT_CREATE = J2EEMigrationUIResourceHandler.getString("FAILED_LOCAL_CLIENT_CREATE"); //$NON-NLS-1$
	public static String COMPLETED_LOCAL_CLIENT_CREATE = J2EEMigrationUIResourceHandler.getString("COMPLETED_LOCAL_CLIENT_CREATE"); //$NON-NLS-1$
	public static String COMPLETED_CMP20_CODEGEN = J2EEMigrationUIResourceHandler.getString("COMPLETED_CMP20_CODEGEN"); //$NON-NLS-1$
	public static String LOCAL_CLIENT_NOT_NEEDED = J2EEMigrationUIResourceHandler.getString("LOCAL_CLIENT_NOT_NEEDED"); //$NON-NLS-1$
	public static String FILES_OUT_OF_SYNC = J2EEMigrationUIResourceHandler.getString("FILES_OUT_OF_SYNC"); //$NON-NLS-1$
	public static String SERVER_TARGET_SETTING_FAILED = J2EEMigrationUIResourceHandler.getString("SERVER_TARGET_SETTING_FAILED_UI_"); //$NON-NLS-1$

	//Wizard Error and Warning Messages:
	public static String JAR_11_IMPORT_20 = J2EEMigrationUIResourceHandler.getString("JAR_11_IMPORT_20_UI_"); //$NON-NLS-1$
	public static String CMP_11_IMPORT_20 = J2EEMigrationUIResourceHandler.getString("CMP_11_IMPORT_20_UI_"); //$NON-NLS-1$
	public static String OLD_PROJECT_STRUCTURE = J2EEMigrationUIResourceHandler.getString("OLD_PROJECT_STRUCTURE_UI_"); //$NON-NLS-1$
	public static String PROJECT_MUST_BE_SELECTED = J2EEMigrationUIResourceHandler.getString("PROJECT_MUST_BE_SELECTED_UI_"); //{} =
																															  // //$NON-NLS-1$
	// (enterprise
	// application,
	// application
	// client
	// ,
	// EJB)
	// //$NON-NLS-1$
	public static String ENTERPRISE_APPLICATION = J2EEMigrationUIResourceHandler.getString("ENTERPRISE_APPLICATION_UI_"); //$NON-NLS-1$
	public static String APPLICATION_CLIENT = J2EEMigrationUIResourceHandler.getString("APPLICATION_CLIENT_UI_"); //$NON-NLS-1$
	public static String EJB = J2EEMigrationUIResourceHandler.getString("EJB_UI_"); //$NON-NLS-1$
	public static String WEB = J2EEMigrationUIResourceHandler.getString("WEB_UI_"); //$NON-NLS-1$
	public static String EJB_MUST_BE_SELECTED = J2EEMigrationUIResourceHandler.getString("EJB_MUST_BE_SELECTED_UI_"); //$NON-NLS-1$


	//Menus:
	public static String MIGRATE = J2EEMigrationUIResourceHandler.getString("MIGRATE_UI_"); //$NON-NLS-1$
	public static String MIGRATE_J2EE_PROJECTS = J2EEMigrationUIResourceHandler.getString("MIGRATE_J2EE_PROJECTS_UI_"); //$NON-NLS-1$
	public static String OPEN_J2EE_MIGRATION_WIZARD = J2EEMigrationUIResourceHandler.getString("OPEN_J2EE_MIGRATION_WIZARD_UI_"); //$NON-NLS-1$

	//Progress Indicator:
	public static String MIGRATING_PROJECT_STRUCTURES = J2EEMigrationUIResourceHandler.getString("MIGRATING_PROJECT_STRUCTURES_UI_"); //$NON-NLS-1$
	public static String MIGRATING_J2EE_VERSION = J2EEMigrationUIResourceHandler.getString("MIGRATING_J2EE_VERSION_UI_"); //$NON-NLS-1$
	public static String ADDING_LOCAL_CLIENT_VIEWS = J2EEMigrationUIResourceHandler.getString("ADDING_LOCAL_CLIENT_VIEWS_UI_"); //$NON-NLS-1$
	public static String DELETING_REMOTE_CLIENT_VIEWS = J2EEMigrationUIResourceHandler.getString("DELETING_REMOTE_CLIENT_VIEWS_UI_"); //$NON-NLS-1$

	public static String ABS_PATHS_APP_EXT_REMOVED = J2EEMigrationUIResourceHandler.getString("ABS_PATHS_APP_EXT_REMOVED"); //$NON-NLS-1$
	public static String PROJECT_REFERENCES_UPDATED = J2EEMigrationUIResourceHandler.getString("PROJECT_REFERENCES_UPDATED"); //$NON-NLS-1$
	public static String PROJECT_CLASSPATH_UPDATED = J2EEMigrationUIResourceHandler.getString("PROJECT_CLASSPATH_UPDATED"); //$NON-NLS-1$
	public static String FAILED_MIGRATING_IMPORTED_CLASSES = J2EEMigrationUIResourceHandler.getString("FAILED_MIGRATING_IMPORTED_CLASSES"); //$NON-NLS-1$
	public static String EXTRACTED_IMPORTED_CLASSES = J2EEMigrationUIResourceHandler.getString("EXTRACTED_IMPORTED_CLASSES"); //$NON-NLS-1$
	public static String IMPORTED_CLASSES_FILE_DELETED = J2EEMigrationUIResourceHandler.getString("IMPORTED_CLASSES_FILE_DELETED"); //$NON-NLS-1$
	public static String BACKEND_MIGRATION_FAILED = J2EEMigrationUIResourceHandler.getString("BACKEND_MIGRATION_FAILED"); //$NON-NLS-1$
	public static String BINARY_MIGRATION_FAILED = J2EEMigrationUIResourceHandler.getString("BINARY_MIGRATION_FAILED"); //$NON-NLS-1$

	//Metadata Operation messages
	String COMPLETED_BACKEND_MIG = J2EEMigrationUIResourceHandler.getString("COMPLETED_BACKEND_MIG"); //$NON-NLS-1$
	String NOT_NEEDED_DEPLOYMENT_DESC_MIG = J2EEMigrationUIResourceHandler.getString("NOT_NEEDED_DEPLOYMENT_DESC_MIG"); //$NON-NLS-1$
	String NOT_NEEDED_BACKEND_MIG = J2EEMigrationUIResourceHandler.getString("NOT_NEEDED_BACKEND_MIG"); //$NON-NLS-1$

	//Dialog titles
	String MIGRATION_COMPLETE = J2EEMigrationUIResourceHandler.getString("MIGRATION_COMPLETE"); //$NON-NLS-1$
	String MIGRATION_ERRORS = J2EEMigrationUIResourceHandler.getString("MIGRATION_ERRORS"); //$NON-NLS-1$
	String MIGRATION_NOT_NEEDED = J2EEMigrationUIResourceHandler.getString("MIGRATION_NOT_NEEDED"); //$NON-NLS-1$
	String CONFIRMATION_TITLE = J2EEMigrationUIResourceHandler.getString("CONFIRMATION_TITLE"); //$NON-NLS-1$
	String OPEN_EDITORS_TITLE = J2EEMigrationUIResourceHandler.getString("OPEN_EDITORS_TITLE"); //$NON-NLS-1$
	String INVALID_SELECTION_TITLE = J2EEMigrationUIResourceHandler.getString("INVALID_SELECTION_TITLE"); //$NON-NLS-1$
	String INFORM_INVALID_J2EE_SELECTION = J2EEMigrationUIResourceHandler.getString("INFORM_INVALID_J2EE_SELECTION"); //$NON-NLS-1$
	String REQUIRED_CLIENT_VIEW_TITLE = J2EEMigrationUIResourceHandler.getString("REQUIRED_CLIENT_VIEW_TITLE"); //$NON-NLS-1$

	//Confirmation messages
	String CONFIRM_MIGRATE_PROJECT_STRUCTURE = J2EEMigrationUIResourceHandler.getString("CONFIRM_MIGRATE_PROJECT_STRUCTURE"); //$NON-NLS-1$
	String CONFIRM_MIGRATE_J2EE_13 = J2EEMigrationUIResourceHandler.getString("CONFIRM_MIGRATE_J2EE_13"); //$NON-NLS-1$
	String INFORM_OPEN_EDITORS = J2EEMigrationUIResourceHandler.getString("INFORM_OPEN_EDITORS"); //$NON-NLS-1$
	String INFORM_PROPER_SELECTION = J2EEMigrationUIResourceHandler.getString("INFORM_PROPER_SELECTION"); //$NON-NLS-1$
	String CONFIRM_CLIENT_VIEW_REQUIRED = J2EEMigrationUIResourceHandler.getString("CONFIRM_CLIENT_VIEW_REQUIRED"); //$NON-NLS-1$


	//Migration Adapter Type Tag
	String J2EE_PROJ_MIGRATION_ADAPTER = "J2EEProjectMigration"; //$NON-NLS-1$
}

