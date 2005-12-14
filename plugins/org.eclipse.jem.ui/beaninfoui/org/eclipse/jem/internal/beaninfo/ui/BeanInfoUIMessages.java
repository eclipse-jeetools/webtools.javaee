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
package org.eclipse.jem.internal.beaninfo.ui;

import org.eclipse.osgi.util.NLS;

public final class BeanInfoUIMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.beaninfo.ui.messages";//$NON-NLS-1$

	private BeanInfoUIMessages() {
		// Do not instantiate
	}

	public static String SearchPathDialog_ChoosePackages;
	public static String SearchPathDialog_Remove;
	public static String SearchPathDialog_Desc_Label;
	public static String SearchPathDialog_ModifySearchPaths;
	public static String SearchPathDialog_NotEditable_INFO_;
	public static String SearchPathDialog_PackagePresent_INFO_;
	public static String SearchPathDialog_InputDialog_Title;
	public static String SearchPathDialog_InputDialog_Message;
	public static String BeanInfoPathsBlock_ExportAll;
	public static String BeanInfoPathsBlock_UnexportAll;
	public static String BeanInfoPathsBlock_Page_Tab_Packages;
	public static String BeanInfoPathsBlock_Page_Tab_Classes;
	public static String BeanInfosWorkbookPage_AddFolders;
	public static String BeanInfosWorkbookPage_AddJARs;
	public static String BeanInfosWorkbookPage_AddExternalJAR;
	public static String BeanInfosWorkbookPage_AddVariable;
	public static String BeanInfosWorkbookPage_AddProjects;
	public static String BeanInfosWorkbookPage_ModifyPaths;
	public static String BeanInfosWorkbookPage_Remove;
	public static String BeanInfosWorkbookPage_List_Text;
	public static String BeanInfosWorkbookPage_SelectionDialog_Classes_Title;
	public static String BeanInfosWorkbookPage_SelectionDialog_JARs_Title;
	public static String BeanInfosWorkbookPage_SelectionDialog_JARs_Message;
	public static String BeanInfosWorkbookPage_SelectionDialog_ExtJARs_Text;
	public static String BeanInfosWorkbookPage_SelectionDialog_Projects_Title;
	public static String BeanInfosWorkbookPage_SelectionDialog_Classes_Prompt;
	public static String BeanInfosWorkbookPage_SelectionDialog_Projects_Prompt;
	public static String PackagesWorkbook_ChoosePackages;
	public static String PackagesWorkbook_ChooseDefinedPaths;
	public static String PackagesWorkbook_Remove;
	public static String PackagesWorkbook_LabelText;
	public static String PackagesWorkbook_SelectionDialog_DefinedPaths_Title;
	public static String PackagesWorkbook_SelectionDialog_DefinedPaths_Message;
	public static String LabelProvider_Library_Folder;
	public static String LabelProvider_Library__PathLastSegment_PathRelative_;
	public static String LabelProvider_Library__PathLastSegment_PathExceptLast_;
	public static String LabelProvider_Variable__name_PathOSString_;
	public static String LabelProvider__packageName_Path_;
	public static String VariableSelectionBlock_variable_label;
	public static String VariableSelectionBlock_variable_button;
	public static String VariableSelectionBlock_extension_label;
	public static String VariableSelectionBlock_extension_button;
	public static String VariableSelectionBlock_fullpath_label;
	public static String VariableSelectionBlock_error_entername_ERROR_;
	public static String VariableSelectionBlock_error_namenotexists_ERROR_;
	public static String VariableSelectionBlock_error_pathexists_ERROR_;
	public static String VariableSelectionBlock_error_invalidextension_ERROR_;
	public static String VariableSelectionBlock_warning_pathnotexists_WARN_;
	public static String VariableSelectionBlock_ExtJarDialog_title;
	public static String Beaninfo_UI__errortitle;
	public static String Beaninfo_UI__error;
	public static String BeaninfoPropertiesPage_INFO__nojavaproject;
	public static String BeaninfoPropertiesPage_INFO__closedproject;
	public static String BeaninfoPathsBlock_UI__enablebeaninfo;
	public static String BeaninfoPathsBlock_UI__searchpath_label;
	public static String BeaninfoPathsBlock_UI__searchpath_up_button;
	public static String BeaninfoPathsBlock_UI__searchpath_down_button;
	public static String BeaninfoPathsBlock_UI__searchpath_remove_button;
	public static String BeaninfoPathsBlock_UI__serachpath_tab_order;
	public static String BeaninfoPathsBlock_WARN__searchpath_missing_path_format;
	public static String BeaninfoPathsBlock_UI__searchpath_operationdescription;
	public static String BeaninfoPathsBlock_UI__addsearchpath_title;
	public static String BeaninfoPathsBlock_UI__addsearchpath_description;
	public static String BeaninfoPathsBlock_UI__warning_EntryMissing;
	public static String BeaninfoPathsBlock_UI__searchpath_add_button;

	static {
		NLS.initializeMessages(BUNDLE_NAME, BeanInfoUIMessages.class);
	}

	public static String ExceptionHandler_errordialog_msg_seelog;
}