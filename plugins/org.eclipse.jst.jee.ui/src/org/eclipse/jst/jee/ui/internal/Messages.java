/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal;

import org.eclipse.osgi.util.NLS;


public class Messages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jst.jee.ui.internal.messages"; //$NON-NLS-1$

	private Messages() {
		// Do not instantiate
	}
	
	public static String INVALID_DEP_DESC_SELECTION_TITLE;
	public static String INFORM_INVALID_DEP_DESC_SELECTION;
	public static String DEPLOYMENT_DESCRIPTOR;
	public static String SESSION_BEANS;
	public static String ENTITY_BEANS;
	public static String MESSAGE_DRIVEN_BEANS;
	public static String ACTIVATION_CONFIG_PROPERTIES;
	public static String ENTERPRISE_BEAN_CLASS_DESCRIPTION;
	
	public static String SERVLET_ITEM_PROVIDER;
	public static String LISTENER_ITEM_PROVIDER;
	public static String FILTERS_ITEM_PROVIDER;
	public static String FILTER_MAPPING_ITEM_PROVIDER;
	public static String SERVLET_MAPPING_ITEM_PROVIDER;
	
	
	public static String REFERENCES;
	public static String BUNDLED_LIBRARIES_NODE;
	public static String EJBLOCAL_REFERENCES;
	public static String EJB_REFERENCES;
	public static String ENVIROMENT_ENTRIES;
	public static String RESOURCE_ENVIROMENT_ENTRIES;
	public static String RESOURCE_REFERENCES;
	public static String SERVICE_REFERENCES;

	static {
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}
}
