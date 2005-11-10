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
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.eclipse.osgi.util.NLS;

public final class EJBCreationResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "ejbcreation";//$NON-NLS-1$

	private EJBCreationResourceHandler() {
		// Do not instantiate
	}

	public static String Error_occured_getting_ejb__ERROR_;
	public static String Loading_examples_____UI_;
	public static String Traversing_file_system____UI_;
	public static String Importing_files____UI_;
	public static String Creating_EJB_Project____UI_;
	public static String Not_an_ejb_project_UI_;
	public static String An_ejb_project_must_be_open_and_must_exist_for_properties_to_be_edited_UI_;
	public static String CANCEL_UI_;
	public static String NO_UI_;
	public static String YES_UI_;
	public static String ALL_UI_;
	public static String None_UI_;
	public static String Enter_bean_name_UI_;
	public static String Message_invalid_bean_name_UI_;
	public static String Bean_name_is_already_used_UI_;
	public static String Bean_name_UI_;
	public static String Default_pack_not_be_null_UI_;
	public static String The_project_does_not_conta_UI_;
	public static String Select_a_source_folder__UI_;
	public static String The_selected_source_folder_UI_;
	public static String The_selected_source_folder1_UI_;
	public static String Package_root_must_be_direc_UI_;
	public static String Bean_class_UI_;
	public static String Bean_superclass_UI_;
	public static String Message_Enter_bean_class_name_UI_;
	public static String Bean_Class_Cannot_Be_In_UI_;
	public static String Class_implements;
	public static String Message_Enter_home_interface_name_UI_;
	public static String Message_Home_Interface_UI_;
	public static String Message_Enter_remote_interface_name_UI_;
	public static String Message_Remote_Interface_UI_;
	public static String Remote_interface_cannot_be_i_UI_;
	public static String Home_interface_cannot_be_i_UI_;
	public static String Interface_extends;
	public static String Local_Home_not_in_a_default_pkg_UI_;
	public static String Enter_local_home_name_UI_;
	public static String Enter_local_int_name_UI_;
	public static String Local_home_interface_UI_;
	public static String Local_interface_UI_;
	public static String Local_not_in_a_default_pkg_UI_;
	public static String Enter_service_endpoint_name_UI_;
	public static String Service_endpoint_interface_UI_;
	public static String Service_endpoint_not_in_a_default_pkg_UI_;
	public static String Atleast_one_client;
	public static String Atleast_one_client_with_service;
	public static String Name_collision_pattern;
	public static String Creating_source_folder__1;
	public static String Select_valid_EJB_project;
	public static String BEAN_SUPERCLASS_NOT_EXIST;
	public static String Super_EJB_has_no_local_interface;
	public static String Super_EJB_has_no_remote_interface;
	public static String Key_class_UI_;
	public static String Entity_enter_primary_key;
	public static String Entyty_primary_key_not_default;
	public static String EJB_CLIENT_JAR_CREATION_ERROR_;
	public static String EJB_CLIENT_JAR_CREATION_MESSAGE_;
	public static String CREATING_CLIENT_JAR_UI_;
	public static String FILES_OUT_OF_SYNC_UI_;
	public static String EJB_Client_JAR_name_exists_;
	public static String EJB_JAR_already_has_client_Jar_;
	public static String CLIENT_JAR_URI_NOT_RESOLVE_UI_;
	public static String ClientJAR_8;
	public static String ClientJAR_9;
	public static String ClientJAR_10;
	public static String ClientJAR_11;
	public static String ClientJAR_12;
	public static String ClientJAR_13;
	public static String ClientJAR_14;
	public static String ClientJAR_15;
	public static String remove_client_jar_binary;
	public static String CANNOT_RESOLVE_INTERFACE_UI_;
	public static String _1;
	public static String _2;
	public static String Neither_Bean_A_nor_Bean_B_have_a_local_client_view_UI_;
	public static String Relationship_name_cannot_be_empty_UI_;
	public static String Relationship_name_already_exist_198_UI_;
	public static String Role_name_for_Bean_A_cannot_be_empty_UI_;
	public static String Role_name_for_Bean_A_must_be_java_valid_field_UI_;
	public static String Role_name_for_Bean_B_cannot_be_empty_UI_;
	public static String Role_name_for_Bean_B_must_be_java_valid_field_UI_;
	public static String Multiplicity_for_Bean_A_cannot_be_empty_UI_;
	public static String Multiplicity_for_Bean_B_cannot_be_empty_UI_;
	public static String CMR_field_name_for_Bean_A_cannot_be_empty_UI_;
	public static String CMR_field_name_for_Bean_B_cannot_be_empty_UI_;
	public static String CMR_field_type_for_Bean_A_cannot_be_empty_UI_;
	public static String CMR_field_type_for_Bean_B_cannot_be_empty_UI_;
	public static String Relationship_must_be_navigable_atleast_in_one_direction_UI_;
	public static String Role_name_cannot_be_the_same_UI_;
	public static String Cannot_create_many_to_many_relationship_UI_;
	public static String is_not_an_unique_CMR_;
	public static String is_not_an_unique_role_name_;
	public static String CMR_fields_must_be_unique_;
	public static String Bean_A_does_not_match_Bean_B_client_view_;
	public static String Bean_A_CMR_field_name_is_not_valid_java_field_UI_;
	public static String Bean_B_CMR_field_name_is_not_valid_java_field_UI_;
	public static String Bean_empty_;
	public static String Selected_bean_a_must_be_a_cmp_;
	public static String Selected_bean_b_must_be_a_cmp_;
	public static String Selected_bean_a_must_be_2_x_cmp_;
	public static String Selected_bean_b_must_be_2_x_cmp_;
	public static String FIELD_CANNOT_BE_EMPTY;
	public static String AN_OPERATION_MUST_BE_SELECTED;
	public static String CLIENT_VIEW_ERR_DELETE_SUPERCLASS;
	public static String CANNOT_REMOVE_BOTH_LOCAL_AND_REMOTE;
	public static String SELECT_ONE_OR_MORE_VIEWS;
	public static String ONLY_ONE_VIEW_CREATE;
	public static String ERR_CMP_FIELD_LIST_EMPTY;
	public static String ERR_CMP_FIELD_LIST_KEY_FIELD_EMPTY;
	public static String ERR_PLEASE_SELECT_A_FOREIGN_KEY;
	public static String ERR_FOREIGN_KEY_SELECTED;
	public static String ServiceEndpoint_interface_cannot_be_UI_;
	public static String NON_INTERFACE_NAME_CANNOT_BE_NULL_UI_;
	public static String ONE_END_MUST_BE_NAV;
	public static String CANT_BE_BINARY_PROJECT_UI_;
	public static String EJB_EXPORT_PROJECT_CONTAINS_CLEINT_WARNING;
	public static String remove_client_jar_client_binary;

	static {
		NLS.initializeMessages(BUNDLE_NAME, EJBCreationResourceHandler.class);
	}

	public static final String CLIENT_SAME_NAME_AS_EJB = _1; //$NON-NLS-1$
	public static final String CLIENT_SAME_NAME_AS_EAR = _2; //$NON-NLS-1$

	private static ResourceBundle fgResourceBundle;

	/**
	 * Returns the resource bundle used by all classes in this Project
	 */
	public static ResourceBundle getResourceBundle() {
		try {
			return ResourceBundle.getBundle("ejbcreation");//$NON-NLS-1$
		} catch (MissingResourceException e) {
			// does nothing - this method will return null and
			// getString(String, String) will return the key
			// it was called with
		}
		return null;
	}

	public static String getString(String key) {
		if (fgResourceBundle == null) {
			fgResourceBundle = getResourceBundle();
		}

		if (fgResourceBundle != null) {
			try {
				return fgResourceBundle.getString(key);
			} catch (MissingResourceException e) {
				return "!" + key + "!";//$NON-NLS-2$//$NON-NLS-1$
			}
		}
		return "!" + key + "!";//$NON-NLS-2$//$NON-NLS-1$
	}
}