/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.rar.actions;

import org.eclipse.jst.j2ee.internal.plugin.J2EEUIPlugin;


/**
 * Defines constants for Connector Archive operations.
 */
public interface IConnectorArchiveConstants {

	//Actions
	public static final String CONNECTOR_EXPORT_ACTION_LABEL = "%connector.export.action.label_ui_"; //$NON-NLS-1$

	//General
	public static final String CONNECTOR_PROJECT_UI_ = RARArchiveUIResourceHandler.getString("Connector_Project_UI_"); //$NON-NLS-1$
	public static final String CONNECTOR_FILE_UI_ = RARArchiveUIResourceHandler.getString("Connector_File_UI_"); //$NON-NLS-1$
	public static final String STORE_CONNECTOR_FILE_NAMES_ID = "EJBImportWizardPage1.STORE_CONNECTOR_FILE_NAMES_ID"; //$NON-NLS-1$
	public static final String STORE_CONNECTOR_EXPORT_FILE_NAMES = "ConenctorExportWizardPage1.STORE_CONNECTOR_EXPORT_FILE_NAMES"; //$NON-NLS-1$
	public static final String WIZ_IMAGE = "connector_import_wiz"; //$NON-NLS-1$
	public static final String ID = "id"; //$NON-NLS-1$
	public static final String RAR_BROWSE_BUTTON_FILTER = "*.rar"; //$NON-NLS-1$
	public static final String TARGET_EXTENSION = "rar"; //$NON-NLS-1$
	public static final String YES_UI_ = RARArchiveUIResourceHandler.getString("Yes_UI_"); //$NON-NLS-1$
	public static final String NO_UI_ = RARArchiveUIResourceHandler.getString("No_UI_"); //$NON-NLS-1$
	public static final String QUESTION_UI_ = RARArchiveUIResourceHandler.getString("Question_UI_"); //$NON-NLS-1$
	public static final String RESOURCE__UI_ = "Resource_{0}_already_exists.__Would_you_like_to_overwrite_it_UI_"; //$NON-NLS-1$
	//Import
	public static final String IMPORT_UI_ = RARArchiveUIResourceHandler.getString("Import_UI_"); //$NON-NLS-1$
	public static final String IMPORT_A_CONNECTOR_UI_ = RARArchiveUIResourceHandler.getString("Import_a_Connector_project_from_the_file_system_UI_"); //$NON-NLS-1$
	public static final String IMPORT_INTO_NEW_OR_EXISTING_UI_ = RARArchiveUIResourceHandler.getString("Import_into_new_or_existing_Enterprise_Application_project_UI_"); //$NON-NLS-1$
	public static final String CONNECTOR_IMPORT_ID_ = "ConnectorProjectImportPage1"; //$NON-NLS-1$
	public static final String CONNECTOR_IMPORT_UI_ = RARArchiveUIResourceHandler.getString("Connector_Import_UI_"); //$NON-NLS-1$
	public static final String IRAR1000 = J2EEUIPlugin.PLUGIN_ID + ".irar1000"; //$NON-NLS-1$

	//Export
	public static final String CONNECTOR_EXPORT_UI_ = RARArchiveUIResourceHandler.getString("Connector_Export_UI_"); //$NON-NLS-1$
	public static final String EXPORT_CONNECTOR__UI_ = RARArchiveUIResourceHandler.getString("Export_Connector_Project_to_the_local_file_system_UI_"); //$NON-NLS-1$
	public static final String WORKBENCH_ID = "ConnectorExportPage1"; //$NON-NLS-1$
	public static final String EXPORT_UI_ = RARArchiveUIResourceHandler.getString("Export_UI_"); //$NON-NLS-1$
	public static final String EX_WIZ_IMAGE = "connector_export_wiz"; //$NON-NLS-1$
	public static final String ERAR1000 = J2EEUIPlugin.PLUGIN_ID + ".erar1000"; //$NON-NLS-1$

	//Errors
	public static final String UNABLE_TO_COMPLETE_THE_OPEN_ERROR_ = RARArchiveUIResourceHandler.getString("Unable_to_complete_the_operationdump_caused_by_this_UI_"); //$NON-NLS-1$
	public static final String IMPORT_PROBLEMS_UI_ = RARArchiveUIResourceHandler.getString("Import_Problems_UI_"); //$NON-NLS-1$
	public static final String CONNECTOR_IMPORT__UI_ = RARArchiveUIResourceHandler.getString("Connector_Import_Error_UI_"); //$NON-NLS-1$
	public static final String IMPORT_ERROR_UI_ = RARArchiveUIResourceHandler.getString("Import_Error_UI_"); //$NON-NLS-1$
	public static final String THE_JAR_FILE_DOES_NOT_EXIST_UI_ = RARArchiveUIResourceHandler.getString("The_jar_file_does_not_exist_or_cannot_be_opened,_UI_") + //$NON-NLS-1$
				RARArchiveUIResourceHandler.getString("Click_browse_to_select_a_valid_jar_or_enter_a_valid_jar_UI_"); //$NON-NLS-1$
	public static final String CONNECTOR_EXPORT_ERROR_UI_ = RARArchiveUIResourceHandler.getString("Connector_Export_Error_UI_"); //$NON-NLS-1$
	public static final String CANNOT_EXPORT_CLOSED_PROJECT_UI_ = RARArchiveUIResourceHandler.getString("Cannot_export_closed_projects_UI_"); //$NON-NLS-1$
	public static final String Rar_File_Name_Needed_UI_ = RARArchiveUIResourceHandler.getString("Rar_File_Name_Needed_UI_"); //$NON-NLS-1$

}// IConnectorArchiveConstants
