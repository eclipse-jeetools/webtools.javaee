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
package org.eclipse.jem.internal.ui.proxy.remote;

import org.eclipse.osgi.util.NLS;

public final class ProxyRemoteUIMessages extends NLS {

	private static final String BUNDLE_NAME = "org.eclipse.jem.internal.ui.proxy.remote.messages";//$NON-NLS-1$

	private ProxyRemoteUIMessages() {
		// Do not instantiate
	}

	public static String LocalLaunchProjectTab_name;
	public static String LocalLaunchProjectTab_Project;
	public static String LocalLaunchProjectTab_Browse;
	public static String LocalLaunchProjectTab_Project_Selection;
	public static String LocalLaunchProjectTab_ChooseProject;
	public static String LocalLaunchProjectTab_ProjectNotExist_ERROR_;
	public static String LocalLaunchProjectTab_ProjectNotOpen_ERROR_;
	public static String LocalLaunchProjectTab_ProjectValidateError_ERROR_;

	static {
		NLS.initializeMessages(BUNDLE_NAME, ProxyRemoteUIMessages.class);
	}
}