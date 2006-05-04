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
	
	public static String label_attributes;
	public static String label_include;
	public static String label_tasks;
	public static String label_task;
	public static String label_edit;
	public static String error_message_no_lib;
	

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}