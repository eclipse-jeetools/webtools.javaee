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
package org.eclipse.jst.j2ee.jca.internal.plugin;

import org.eclipse.osgi.util.NLS;

public final class JCAResourceHandler extends NLS {

	private static final String BUNDLE_NAME = "rar";//$NON-NLS-1$

	private JCAResourceHandler() {
		// Do not instantiate
	}

	public static String LicenseItemProvider_UI_0;
	public static String SecurityPermissionItemProvider_UI_0;
	public static String ConfigPropertyItemProvider_UI_0;
	public static String _UI_ResourceAdapter_type;
	public static String _UI_OutboundResourceAdapter_type;
	public static String _UI_ConnectionDefinition_type;
	public static String _UI_InboundResourceAdapter_type;
	public static String _UI_MessageAdapter_type;
	public static String _UI_MessageListener_type;
	public static String _UI_ActivationSpec_type;
	public static String _UI_RequiredConfigPropertyType_type;
	public static String _UI_AdminObject_type;

	static {
		NLS.initializeMessages(BUNDLE_NAME, JCAResourceHandler.class);
	}
}