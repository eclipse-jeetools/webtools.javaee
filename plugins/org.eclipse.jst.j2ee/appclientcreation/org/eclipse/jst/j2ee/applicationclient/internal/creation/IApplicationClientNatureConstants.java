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
package org.eclipse.jst.j2ee.applicationclient.internal.creation;



/**
 * Insert the type's description here. Creation date: (4/4/2001 10:04:11 AM)
 * @deprecated
 * Use
 * <p>
 * All nature constants interfaces are also deprecated along with nature runtime classes
 * IModuleConstants need to be used.
 * </p>
 */
public interface IApplicationClientNatureConstants {
	String NATURE_ID = IConfigurationConstants.PLUG_IN_ID + ".ApplicationClientNature"; //$NON-NLS-1$
	String NO_NATURE_MESSAGE = AppClientCreationResourceHandler.getString("Not_an_Application_Client_project_ERROR_"); //$NON-NLS-1$
	String DEFAULT_SOURCE_PATH = "appClientModule"; //$NON-NLS-1$
	String[] APPCLIENT_NATURE_IDS = {NATURE_ID};
	String EDIT_MODEL_ID = "org.eclipse.jst.j2ee.applicationClient.editModel"; //$NON-NLS-1$
	String WEB_SERVICE_EDIT_MODEL_ID = "org.eclipse.jst.j2ee.webservice.appClient.editModel"; //$NON-NLS-1$
}