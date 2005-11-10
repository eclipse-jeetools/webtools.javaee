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
package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui;

import org.eclipse.osgi.util.NLS;

public final class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.ui.preferences";//$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}

	public static String label_set_xdoclet_runtime_preference;
	public static String label_enable_xdoclet_builder;
	public static String desc_enable_xdoclet_builder;
	public static String label_xdoclet_home;
	public static String desc_xdoclet_home;
	public static String label_browse;
	public static String label_xdoclet_version;
	public static String desc_xdoclet_version;
	public static String label_global_pref;
	public static String desc_global_pref;
	public static String label_set_ejbdoclet_preference;
	public static String desc_ejbdoclet_jboss;
	public static String desc_ejbdoclet_jonas;
	public static String desc_ejbdoclet_weblogic;
	public static String desc_ejbdoclet_websphere;
	public static String label_set_webdoclet_preference;
	public static String desc_webdoclet_jboss;
	public static String desc_webdoclet_jonas;
	public static String desc_webdoclet_weblogic;
	public static String desc_webdoclet_websphere;
	public static String label_generate_local;
	public static String label_generate_local_desc;
	public static String label_generate_remote;
	public static String label_generate_remote_desc;
	public static String label_generate_util;
	public static String label_generate_util_desc;
	public static String label_generate_dataobject;
	public static String label_generate_dataobject_desc;
	public static String label_generate_dao;
	public static String label_generate_dao_desc;
	public static String label_generate_valueobject;
	public static String label_generate_valueobject_desc;
	public static String label_generate_entitypk;
	public static String label_generate_entitypk_desc;
	public static String label_generate_entitycmp;
	public static String label_generate_entitycmp_desc;
	public static String label_generate_entitybmp;
	public static String label_generate_entitybmp_desc;
	public static String label_generate_session;
	public static String label_generate_session_desc;
	public static String label_generate_mdb;
	public static String label_generate_mdb_desc;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}