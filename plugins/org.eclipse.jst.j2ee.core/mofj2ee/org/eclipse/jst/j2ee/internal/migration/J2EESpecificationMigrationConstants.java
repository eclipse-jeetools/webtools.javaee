/*******************************************************************************
 * Copyright (c) 2001, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.migration;

import org.eclipse.jst.j2ee.internal.MOFJ2EEResourceHandler;


public interface J2EESpecificationMigrationConstants {
	String J2EE_VERSION_1_2 = "J2EE_1.2";  //$NON-NLS-1$
	String J2EE_VERSION_1_3 = "J2EE_1.3";  //$NON-NLS-1$
	String J2EE_VERSION_1_4 = "J2EE_1.4";  //$NON-NLS-1$

	String DEFAULT_COMPLETED_STATUS_MSG = MOFJ2EEResourceHandler.getString("DEFAULT_COMPLETED_STATUS_MSG"); //$NON-NLS-1$
	String DEFAULT_NOT_NEEDED_STATUS_MSG = MOFJ2EEResourceHandler.getString("DEFAULT_NOT_NEEDED_STATUS_MSG"); //$NON-NLS-1$
	String DEFAULT_NOT_POSSIBLE_STATUS_MSG = MOFJ2EEResourceHandler.getString("DEFAULT_NOT_POSSIBLE_STATUS_MSG"); //$NON-NLS-1$
	String DEFAULT_ERROR_STATUS_MSG = MOFJ2EEResourceHandler.getString("DEFAULT_ERROR_STATUS_MSG"); //$NON-NLS-1$
	
	String REMOVED_LOCAL_CLIENT_MSG = MOFJ2EEResourceHandler.getString("REMOVED_LOCAL_CLIENT_MSG"); //$NON-NLS-1$
	String REMOVED_ACCESS_INTENTS_MSG = MOFJ2EEResourceHandler.getString("REMOVED_ACCESS_INTENTS_MSG"); //$NON-NLS-1$
	String REMOVED_ISOLATION_LEVELS_MSG = MOFJ2EEResourceHandler.getString("REMOVED_ISOLATION_LEVELS_MSG"); //$NON-NLS-1$
	String CONVERTED_FINDER_MSG = MOFJ2EEResourceHandler.getString("CONVERTED_FINDER_MSG"); //$NON-NLS-1$
	String UNNAMED_EJB = MOFJ2EEResourceHandler.getString("UNNAMED_EJB"); //$NON-NLS-1$
	String CONVERTED_QUERY_DESCRIPTION = MOFJ2EEResourceHandler.getString("CONVERTED_QUERY_DESCRIPTION"); //$NON-NLS-1$
	String MIGRATED_DEFAULT_DATASOURCE_JAR_MSG = MOFJ2EEResourceHandler.getString("MIGRATED_DEFAULT_DATASOURCE_JAR_MSG"); //$NON-NLS-1$
	String MIGRATED_DEFAULT_DATASOURCE_MSG 	   = MOFJ2EEResourceHandler.getString("MIGRATED_DEFAULT_DATASOURCE_MSG"); //$NON-NLS-1$
	String ERROR_TEXT = MOFJ2EEResourceHandler.getString("ERROR"); //$NON-NLS-1$
	String WARNING_TEXT = MOFJ2EEResourceHandler.getString("WARNING"); //$NON-NLS-1$
	String INFO_TEXT = MOFJ2EEResourceHandler.getString("INFO"); //$NON-NLS-1$
}


