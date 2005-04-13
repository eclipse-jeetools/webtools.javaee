/*******************************************************************************
 * Copyright (c) 2003, 2004, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.datamodel.properties;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;

public interface IJ2EEComponentExportDataModelProperties extends IDataModelProperties {

    /**
     * Required, type String
     */
    public static final String PROJECT_NAME = "J2EEExportDataModel.PROJECT_NAME"; //$NON-NLS-1$
    /**
     * Required, type String
     *  
     */
    public static final String ARCHIVE_DESTINATION = "J2EEExportDataModel.ARCHIVE_DESTINATION"; //$NON-NLS-1$
    /*
     * Optional, type boolean
     */
    public static final String EXPORT_SOURCE_FILES = "J2EEExportDataModel.EXPORT_SOURCE_FILES"; //$NON-NLS-1$
    /*
     * Optional, type boolean
     */
    public static final String OVERWRITE_EXISTING = "J2EEExportDataModel.OVERWRITE_EXISTING"; //$NON-NLS-1$

    /*
     * Optional, type boolean, default true
     */
    public static final String RUN_BUILD = "J2EEExportDataModel.RUN_BUILD"; //$NON-NLS-1$
}
