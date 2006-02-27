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

package org.eclipse.jst.j2ee.internal.ejb.archiveoperations;

public interface IEJBClientJarFileMoveDataModelProperties {

    /**
     * Required, type String
     */
    public static final String EJB_PROJECT_NAME = "IEJBClientJarFileMoveDataModelProperties.EJB_PROJECT_NAME"; //$NON-NLS-1$
    /**
     * Required, type String
     */
    public static final String EJB_CLIENTVIEW_PROJECT_NAME = "IEJBClientJarFileMoveDataModelProperties.CLIENT_PROJECT_NAME"; //$NON-NLS-1$
    
    /**
     * Required, type Map
     */    
    public static final String FILES_TO_MOVE_MAP = "IEJBClientJarFileMoveDataModelProperties.FILES_TO_MOVE_MAP"; //$NON-NLS-1$
    
    
}
