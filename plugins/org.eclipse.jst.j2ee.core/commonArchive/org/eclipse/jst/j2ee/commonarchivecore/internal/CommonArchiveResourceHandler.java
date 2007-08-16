package org.eclipse.jst.j2ee.commonarchivecore.internal;

/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

import org.eclipse.osgi.util.NLS;

public class CommonArchiveResourceHandler extends NLS {
	private static final String BUNDLE_NAME = "commonarchive";//$NON-NLS-1$

	private CommonArchiveResourceHandler() {
		// Do not instantiate
	}

	public static String not_a_dir_EXC_;
	public static String FileImpl__Error_0;
	public static String RepairArchiveCommand_usage1_ERROR_;
	public static String file_exist_as_dir_EXC_;
	public static String duplicate_entry_EXC_;
	public static String ser_not_dd_EXC_;
	public static String EAR_File;
	public static String RAR_File;
	public static String filename_exception_EXC_;
	public static String An_Application_Client_JAR_file;
	public static String no_sec_role_EXC_;
	public static String Original_archive_is_not_a__EXC_;
	public static String duplicate_module_EXC_;
	public static String reading_dd_EXC_;
	public static String Module_file_does_not_exist_2;
	public static String A_WAR_file;
	public static String IOException_occurred_while_EXC_;
	public static String io_ex_manifest_EXC_;
	public static String FixPrimKeyCommand_usage__s_EXC_;
	public static String FixPrimKeyCommand_usage___;
	public static String Parameter_should_not_be_nu_EXC_;
	public static String dd_in_ear_load_EXC_;
	public static String no_dds_10_EXC_;
	public static String list_components_war_EXC_;
	public static String An_EJB_JAR_file;
	public static String open_nested_EXC_;
	public static String Repair_command_failed___ex_EXC_;
	public static String nested_open_fail_EXC_;
	public static String Error_iterating_the_archiv_EXC_;
	public static String make_temp_file_WARN_;
	public static String missing_class_EXC_;
	public static String repair_usage_ERROR_;
	public static String Application_Client_Jar_Fil;
	public static String WAR_File;
	public static String could_not_find_dir_EXC_;
	public static String Converted;
	public static String Absolute_path_unknown_EXC_;
	public static String add_copy_lib_dir_EXC_;
	public static String removing_key_field_INFO_;
	public static String Module_file;
	public static String Stack_trace_of_nested_exce_EXC_;
	public static String tx_bean_mgd_WARN_;
	public static String manifest_dd_load_EXC_;
	public static String key_class_reflection_ERROR_;
	public static String uncontained_module_EXC_;
	public static String file_not_found_EXC_;
	public static String io_ex_reading_dd_EXC_;
	public static String invalid_cp_file_WARN_;
	public static String invalid_archive_EXC_;
	public static String load_resource_EXC_;
	public static String invalid_classpath_WARN_;
	public static String Extract_destination_is_the_EXC_;
	public static String subclass_responsibilty_EXC_;
	public static String Module_not_in_EAR;
	public static String File_not_correct_type;
	public static String io_ex_loading_EXC_;
	public static String could_not_open_EXC_;
	public static String manifest_dd_find_EXC_;
	public static String nested_jar_EXC_;
	public static String dup_resource_EXC_;
	public static String Repairs_all_entries_in_the;
	public static String add_copy_class_dir_EXC_;
	public static String io_ex_reopen_EXC_;
	public static String Error_occurred_iterating_f_EXC_;
	public static String dup_sec_role_module_EXC_;
	public static String duplicate_file_EXC_;
	public static String inferred_dds_EXC_;
	public static String unable_replace_EXC_;
	public static String EJB_Jar_File;
	public static String A_file_does_not_exist_for_module;
	public static String A_RAR_file;
	public static String dup_sec_role_EXC_;
	public static String _The_following_files_could_EXC_;
	public static String key_field_reflection_ERROR_;
	public static String End_of_list_reached_EXC_;
	public static String error_saving_EXC_;
	public static String Archive_is_not_a_valid_EJB_EXC_;
	public static String make_temp_dir_EXC_;
	public static String RepairArchiveCommand_usage;
	public static String FixPrimKeyCommand_failed___EXC_;
	public static String A_Application_file;
	public static String Null_uri_EXC_;
	public static String Internal_Error__Iterator_o_EXC_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, CommonArchiveResourceHandler.class);
	}

	public static String getString(String key, Object[] args) {
		return NLS.bind(key, args);
	}
}
