/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.plugin;


import org.eclipse.osgi.util.NLS;

public class J2EEPluginResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "j2eewtpplugin";//$NON-NLS-1$

	private J2EEPluginResourceHandler() {
		// Do not instantiate
	}

	public static String __Considering_delta_INFO_;
	public static String WARNING_METAFOLDER_MISSING_UI_;
	public static String Not_a_J2EE_project_UI_;
	public static String CoreException_could_not_initialize_Web_Service_class_path_variables_ERROR_;
	public static String Error_opening_archive_for_export_2;
	public static String J2EEDeployOperation_2_UI_;
	public static String No_library_folder_UI_;
	public static String J2EEDeployOperation_UI_Null_Status;
	public static String RAR_File;
	public static String NESTED_JAVA_OUTPUT_ERROR;
	public static String J2EEModulePostImportHelper_3;
	public static String J2EEModulePostImportHelper_2;
	public static String J2EEModulePostImportHelper_1;
	public static String J2EEModulePostImportHelper_0;
	public static String Some_source_types_have______in_their_name_ERROR_;
	public static String J2EEDeployOperation_UI_1;
	public static String J2EEDeployOperation_UI_0;
	public static String _UI_ParamValue_type;
	public static String J2EEDeployOperation_1_UI_;
	public static String An_error_occurred_while_sa_ERROR_;
	public static String WARNING_FILE_MISSING_UI_;
	public static String Cannot_load_EJBJar_because_model_is_null_UI_;
	public static String __Bad_builder_order_for_project_INFO_;
	public static String No_source_types_UI_;
	public static String EJBProjectResources_getEJBJar_EJBEditModel__UI_;
	public static String __No_imported_classes_folder__quitting_INFO_;
	public static String The_following_resources_ne_UI_;
	public static String J2EEDeployOperation_4_UI_;
	public static String __Full_first_build_INFO_;
	public static String Error_occurred_saving_the_manifest_7;
	public static String Cannot_load_EJBFile_because_ejbNature_asEJBJarFile___returns_null_UI_;
	public static String __Delta_build_INFO_;
	public static String __Start_build_project_INFO_;
	public static String Cannot_load_EJBFile_because_EJBNatureRuntime_is_null_UI_;
	public static String Minimizing_project_UI_;
	public static String Error_getting_project_nature_runtime_6;
	public static String Creating_Connector_Project_5;
	public static String Error_getting_project_nature_runtime_3;
	public static String J2EEDeployOperation_3_UI_;
	public static String Unable_to_save_read_only_f_ERROR_;
	public static String Could_not_find_root_object_ERROR_;
	public static String Cannot_run_J2EEUIWorkingCo_ERROR_;
	public static String Cannot_load_EJBFile_because_EJBEditModel_is_null_UI_;
	public static String Error_occurred_loading_ra_xml__4;

	static {
		NLS.initializeMessages(BUNDLE_NAME, J2EEPluginResourceHandler.class);
	}

	public static String getString(String key, Object[] args) {
		return NLS.bind(key, args);
	}
}
