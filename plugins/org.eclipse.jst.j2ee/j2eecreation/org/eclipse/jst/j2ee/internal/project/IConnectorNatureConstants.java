/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.project;

import org.eclipse.jst.j2ee.internal.plugin.J2EEPluginResourceHandler;


/**
 * Defines constants used with the ConnectorNatureRuntime class
 * @deprecated
 * Use
 * <p>
 * All nature constants interfaces are also deprecated along with nature runtime classes
 * IModuleConstants need to be used.
 * </p>
 */
public interface IConnectorNatureConstants {

	//General
	public static final String CONNECTOR_XML_TEMPLATE_10 = "rartp10.xml"; //$NON-NLS-1$
	public static final String CONNECTOR_XML_TEMPLATE_15 = "rartp15.xml"; //$NON-NLS-1$
	public static final String ENC_NATURE_ID = "META-INF/ra.xml.enc"; //$NON-NLS-1$
	public static final String PLUG_IN_ID = "org.eclipse.jst.j2ee.jca"; //$NON-NLS-1$
	public static final String NATURE_ID = PLUG_IN_ID + ".ConnectorNature"; //$NON-NLS-1$

	public static final String DEFAULT_SOURCE_PATH = "connectorModule"; //$NON-NLS-1$
	public static final String CONNECTOR_NATURE_ID = NATURE_ID; //$NON-NLS-1$
	public static final String EDIT_MODEL_ID = "org.eclipse.jst.j2ee.jca.editModel"; //$NON-NLS-1$
	public static final String CONNECTOR_CLASSPATH_ENTRY_1 = "/lib/j2ee.jar"; //$NON-NLS-1$
	public static final String CONNECTOR_CLASSPATH_ENTRY_2 = "/lib/ivjejb35.jar"; //$NON-NLS-1$	
	public static final String CONNECTOR_DEFAULT_URI = ".rar"; //$NON-NLS-1$		
	public static final String CONNECTOR_FILE_UI_ = J2EEPluginResourceHandler.getString("RAR_File"); //$NON-NLS-1$


	//Errors
	public static final String ARCHIVE_OPERATION_OPENINGARCHIVE = J2EEPluginResourceHandler.getString("Error_opening_archive_for_export_2"); //$NON-NLS-1$
	public static final String PROJECT_NATURE = J2EEPluginResourceHandler.getString("Error_getting_project_nature_runtime_3"); //$NON-NLS-1$
	public static final String ERROR_OCCURED_GETTING_CONN_ERROR_ = J2EEPluginResourceHandler.getString("Error_occurred_loading_ra.xml__4"); //$NON-NLS-1$
	public static final String CREATING_CONN_UI_ = J2EEPluginResourceHandler.getString("Creating_Connector_Project_5"); //$NON-NLS-1$
	public static final String ARCHIVE_OPERATION_PROJECTNATURE = J2EEPluginResourceHandler.getString("Error_getting_project_nature_runtime_6"); //$NON-NLS-1$
	public static final String ARCHIVE_OPERATION_SAVEMANIFEST = J2EEPluginResourceHandler.getString("Error_occurred_saving_the_manifest_7"); //$NON-NLS-1$
}// IConnectorNatureConstants
