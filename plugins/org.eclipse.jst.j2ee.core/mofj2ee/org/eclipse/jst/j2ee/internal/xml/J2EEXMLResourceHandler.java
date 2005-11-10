/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.xml;

import org.eclipse.osgi.util.NLS;

public class J2EEXMLResourceHandler extends NLS {
	private static final String BUNDLE_NAME = "j2eexml";//$NON-NLS-1$

	private J2EEXMLResourceHandler() {
		// Do not instantiate
	}

	public static String Valid_values_are___EXC_;
	public static String RAR_file_support__IO_excep_EXC_;
	public static String Failure_occurred_reading_x_EXC_;
	public static String missing_req_field_EXC_;
	public static String An_IO_Exception_occurred_w_EXC_;
	public static String must_be_boolean_EXC_;
	public static String unsupported_encoding_EXC_;
	public static String rar_dtd_not_found_EXC_;
	public static String empty_collection_EXC_;
	public static String must_be_int_EXC_;
	public static String Invalid_value_for__EXC_;
	public static String An_Exception_occurred_whil_EXC_;
	public static String dtd_not_found_EXC_;
	public static String Not_supported_in_this_rele_EXC_;
	public static String io_ex_saving_EXC_;
	public static String could_not_create_file_EXC_;
	public static String unsupported_type_EXC_;
	public static String Key1;

	static {
		NLS.initializeMessages(BUNDLE_NAME, J2EEXMLResourceHandler.class);
	}

	public static String getString(String key, Object[] args) {
		return NLS.bind(key, args);
	}
}