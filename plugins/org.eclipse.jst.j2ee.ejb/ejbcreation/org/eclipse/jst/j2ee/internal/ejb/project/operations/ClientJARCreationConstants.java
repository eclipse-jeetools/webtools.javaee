/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
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
	String CREATING_CLIENT_JAR = EJBCreationResourceHandler.CREATING_CLIENT_JAR_UI_; 
	String FILES_OUT_OF_SYNC = EJBCreationResourceHandler.FILES_OUT_OF_SYNC_UI_; 
	String REMOVING_CLIENT_JAR = EJBCreationResourceHandler.ClientJAR_8; 
	String SHOULD_OVERWRITE = EJBCreationResourceHandler.ClientJAR_9; 
	String REMOVE_TITLE = EJBCreationResourceHandler.ClientJAR_10; 
	String REMOVE_MESSAGE = EJBCreationResourceHandler.ClientJAR_11; 
	String NO_CLIENT_JAR_TITLE = EJBCreationResourceHandler.ClientJAR_12; 
	String NO_CLIENT_JAR_MSG = EJBCreationResourceHandler.ClientJAR_13; 
	String REMOVE_ERROR_TITLE = EJBCreationResourceHandler.ClientJAR_14; 
	String BINARY_EJB_PROJECT = EJBCreationResourceHandler.remove_client_jar_binary; 
	String BINARY_CLIENT_PROJECT = EJBCreationResourceHandler.remove_client_jar_client_binary; 
	String ERROR_REMOVING_CLIENT = EJBCreationResourceHandler.ClientJAR_15; 
}
