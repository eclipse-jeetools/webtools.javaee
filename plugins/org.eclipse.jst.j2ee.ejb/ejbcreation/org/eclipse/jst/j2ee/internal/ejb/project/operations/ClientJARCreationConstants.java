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
/*
 * Created on Aug 11, 2003
 *
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;




/**
 * @author schacher
 */
public interface ClientJARCreationConstants {
	String DOT_JAR = ".jar"; //$NON-NLS-1$
	String SRC_FOLDER_NAME = "src"; //$NON-NLS-1$
	String _STUB = "_Stub"; //$NON-NLS-1$
	String UNDERSCORE = "_"; //$NON-NLS-1$
	String CREATING_CLIENT_JAR = EJBCreationResourceHandler.getString("CREATING_CLIENT_JAR_UI_"); //$NON-NLS-1$
	String FILES_OUT_OF_SYNC = EJBCreationResourceHandler.getString("FILES_OUT_OF_SYNC_UI_"); //$NON-NLS-1$
	String REMOVING_CLIENT_JAR = EJBCreationResourceHandler.getString("ClientJAR.8"); //$NON-NLS-1$
	String SHOULD_OVERWRITE = EJBCreationResourceHandler.getString("ClientJAR.9"); //$NON-NLS-1$
	String REMOVE_TITLE = EJBCreationResourceHandler.getString("ClientJAR.10"); //$NON-NLS-1$
	String REMOVE_MESSAGE = EJBCreationResourceHandler.getString("ClientJAR.11"); //$NON-NLS-1$
	String NO_CLIENT_JAR_TITLE = EJBCreationResourceHandler.getString("ClientJAR.12"); //$NON-NLS-1$
	String NO_CLIENT_JAR_MSG = EJBCreationResourceHandler.getString("ClientJAR.13"); //$NON-NLS-1$
	String REMOVE_ERROR_TITLE = EJBCreationResourceHandler.getString("ClientJAR.14"); //$NON-NLS-1$
	String BINARY_EJB_PROJECT = EJBCreationResourceHandler.getString("remove.client.jar.binary"); //$NON-NLS-1$
	String BINARY_CLIENT_PROJECT = EJBCreationResourceHandler.getString("remove.client.jar.client.binary"); //$NON-NLS-1$ 
	String ERROR_REMOVING_CLIENT = EJBCreationResourceHandler.getString("ClientJAR.15"); //$NON-NLS-1$
}