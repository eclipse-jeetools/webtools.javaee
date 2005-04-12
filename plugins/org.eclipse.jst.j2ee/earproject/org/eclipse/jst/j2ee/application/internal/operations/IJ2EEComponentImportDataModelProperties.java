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
package org.eclipse.jst.j2ee.application.internal.operations;

import org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties;

/**
 * This interface is a set of common properties used the IDataModels which import J2EE Components.
 * 
 * This class is likely to change during
 * the WTP 1.0 milestones as the new project structures are adopted. Use at
 * your own risk.
 * 
 * @since WTP 1.0
 */

public interface IJ2EEComponentImportDataModelProperties extends IDataModelProperties {

    /**
     * Required, type String
     */
    public static final String FILE_NAME = "IJ2EEArtifactImportDataModelProperties.FILE_NAME"; //$NON-NLS-1$

    /**
     * Optional, type Archive, used when a file is available as an object rather
     * than a url (i.e. using the FILE_NAME property will not work).
     */
    public static final String FILE = "IJ2EEArtifactImportDataModelProperties.FILE"; //$NON-NLS-1$

    /**
     * Optional, type Boolean defaults to false
     */
    public static final String OVERWRITE_PROJECT = "IJ2EEArtifactImportDataModelProperties.OVERWRITE_PROJECT"; //$NON-NLS-1$

    /**
     * Optional, type Boolean defaults to false
     */
    public static final String DELETE_BEFORE_OVERWRITE_PROJECT = "IJ2EEArtifactImportDataModelProperties.DELETE_BEFORE_OVERWRITE_PROJECT"; //$NON-NLS-1$

    /**
     * Optional, type SaveFilter, default is null
     */
    public static final String SAVE_FILTER = "IJ2EEArtifactImportDataModelProperties.SAVE_FILTER"; //$NON-NLS-1$

    /**
     * Optional, type Boolean, default false this is used only when importing
     * modules in with an ear
     */
    public static final String PRESERVE_PROJECT_METADATA = "IJ2EEArtifactImportDataModelProperties.IS_LIBRARY"; //$NON-NLS-1$

    /**
     * Do not set this property.
     */
    public static final String BINARY = "IJ2EEArtifactImportDataModelProperties.BINARY"; //$NON-NLS-1$

    public static final String OVERWRITE_HANDLER = "IJ2EEArtifactImportDataModelProperties.OVERWRITE_HANDLER"; //$NON-NLS-1$

    public static final String PROJECT_NAME = "IJ2EEArtifactImportDataModelProperties.PROJECT_NAME"; //$NON-NLS-1$

    public static final String COMPONENT_NAME = "IJ2EEArtifactImportDataModelProperties.COMPONENT_NAME"; //$NON-NLS-1$
    
    public static final String NESTED_MODEL_J2EE_PROJECT_CREATION = "IJ2EEArtifactImportDataModelProperties.NESTED_MODEL_J2EE_PROJECT_CREATION"; //$NON-NLS-1$

    /**
     * Optional - Should the archive be closed on dispose?
     * 
     * @type - Boolean, default is True
     */
    public static final String CLOSE_ARCHIVE_ON_DISPOSE = "IJ2EEArtifactImportDataModelProperties.closeArchiveOnDispose"; //$NON-NLS-1$

    /**
     * Optional - type ArchiveFile, default =
     */
    public static final String ARCHIVE_FILE = "IJ2EEArtifactImportDataModelProperties.closeArchiveOnDispose"; //$NON-NLS-1$

    /**
     * Optional - type String, default = getArchiveFile().getURI() or "" if
     * getArchivefile() is null This is used when adding a module to the ear.
     */
    public static final String URI_FOR_MODULE_MAPPING = "IJ2EEArtifactImportDataModelProperties.URI_FOR_MODULE_MAPPING"; //$NON-NLS-1$

    public static final String FILE_SELECTION_HISTORY = "IJ2EEArtifactImportDataModelProperties.FILE_SELECTION_HISTORY"; //$NON-NLS-1$

    public static final String DEFAULT_PROJECT_NAME = "IJ2EEArtifactImportDataModelProperties.DEFAULT_PROJECT_NAME"; //$NON-NLS-1$

    /**
     * Extended attributes
     */
    public static final String SERVER_TARGET_ID = FlexibleProjectCreationDataModel.SERVER_TARGET_ID;
}
