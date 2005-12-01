/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.plugin;

import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;

public interface IJ2EEModuleConstants extends IModuleConstants {
	
	public static final String J2EE_PLUGIN_ID = "org.eclipse.jst.j2ee"; //$NON-NLS-1$
	
	public static final String JAR_EXT = ".jar"; //$NON-NLS-1$
	public static final String WAR_EXT = ".war"; //$NON-NLS-1$
	public static final String RAR_EXT = ".rar"; //$NON-NLS-1$

	public static final String JST_EAR_TEMPLATE = "template.jst.ear"; //$NON-NLS-1$
	public static final String JST_UTILITY_TEMPLATE = "template.jst.utility"; //$NON-NLS-1$
	public static final String JST_APPCLIENT_TEMPLATE = "template.jst.appclient"; //$NON-NLS-1$
	public static final String JST_EJB_TEMPLATE = "template.jst.ejb"; //$NON-NLS-1$
	public static final String JST_JCA_TEMPLATE = "template.jst.connector"; //$NON-NLS-1$
	public static final String JST_WEB_TEMPLATE = "template.jst.web"; //$NON-NLS-1$
	public static final String WST_WEB_TEMPLATE = "template.wst.web"; //$NON-NLS-1$
}
