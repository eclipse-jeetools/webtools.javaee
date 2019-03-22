/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.common;

import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;

public interface CreationConstants {

	/**
	 * @deprecated, use the preferences directly
	 * @since 2.0
	 */
	String DEFAULT_WEB_SOURCE_FOLDER =	CommonFrameworksPlugin.getDefault().getPluginPreferences().getString(CommonFrameworksPlugin.DEFAULT_SOURCE_FOLDER); 
	/**
	 * @deprecated, use the preferences directly
	 * @since 2.0
	 */
	String DEFAULT_EJB_SOURCE_FOLDER = J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.EJB_CONTENT_FOLDER);
	
	/**
	 * @deprecated, use the preferences directly
	 * @since 2.0
	 */
	String DEFAULT_CONNECTOR_SOURCE_FOLDER = J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.JCA_CONTENT_FOLDER);
	/**
	 * @deprecated, use the preferences directly
	 * @since 2.0
	 */
	String DEFAULT_APPCLIENT_SOURCE_FOLDER = J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.APP_CLIENT_CONTENT_FOLDER); 
	String EJB_CLIENT_NAME ="ClientProject";//$NON-NLS-1$ 
	String CLIENT_JAR_URI="ClientJARURI";//$NON-NLS-1$ 
}
