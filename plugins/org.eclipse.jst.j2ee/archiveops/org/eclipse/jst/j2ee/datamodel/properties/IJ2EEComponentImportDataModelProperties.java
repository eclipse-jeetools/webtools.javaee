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

/**
 * <p>
 * IJ2EEComponentImportDataModelProperties provides properties to the IDataModel associated with the
 * J2EEComponentImportDataModelProvider as well as all extending interfaces extending
 * IJ2EEComponentImportDataModelProperties specifically all J2EE component specific imports.
 * 
 * NOTE: The associated Provider and Operations will be created during M5
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.DataModelFactory
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties
 * 
 * @plannedfor 1.0
 */

public interface IJ2EEComponentImportDataModelProperties extends IDataModelProperties {

	/**
	 * Required, type String, name of the file to be imported.
	 */
	public static final String FILE_NAME = "IJ2EEArtifactImportDataModelProperties.FILE_NAME"; //$NON-NLS-1$

	/**
	 * Optional, type Archive, used when a file is available as an object rather than a url (i.e.
	 * using the FILE_NAME property will not work).
	 */
	public static final String FILE = "IJ2EEArtifactImportDataModelProperties.FILE"; //$NON-NLS-1$
	/**
	 * Required, type String, name of the Project which the Archive will be imported.
	 */
	public static final String PROJECT_NAME = "IJ2EEArtifactImportDataModelProperties.PROJECT_NAME"; //$NON-NLS-1$
	
	/**
	 * Required, type String, name of the Component which will be created to house the Archive
	 */
	public static final String COMPONENT_NAME = "IJ2EEArtifactImportDataModelProperties.COMPONENT_NAME"; //$NON-NLS-1$

	/**
	 * Unsettable property used to retreive the component after the operation has executed.
	 */
	public static final String COMPONENT = "IJ2EEArtifactImportDataModelProperties.COMPONENT"; //$NON-NLS-1$
	/**
	 * Optional, type IOverwriteHandler. Defines a IOverwriteHandler for the component in the
	 * workspace if an existing component should be overwritten (OVERWRITE_COMPONENT).
	 */
	public static final String OVERWRITE_HANDLER = "IJ2EEArtifactImportDataModelProperties.OVERWRITE_HANDLER"; //$NON-NLS-1$

	/**
	 * Optional, type SaveFilter, default is null
	 */
	public static final String SAVE_FILTER = "IJ2EEArtifactImportDataModelProperties.SAVE_FILTER"; //$NON-NLS-1$

	/**
	 * Optional, type Boolean - Should the archive be closed on dispose?
	 */
	public static final String CLOSE_ARCHIVE_ON_DISPOSE = "IJ2EEArtifactImportDataModelProperties.closeArchiveOnDispose"; //$NON-NLS-1$
	/**
	 * Optional - type List, populated list of files previously selected. This can be used for GUIs
	 * which wish to show preivous selections.
	 */
	public static final String FILE_SELECTION_HISTORY = "IJ2EEArtifactImportDataModelProperties.FILE_SELECTION_HISTORY"; //$NON-NLS-1$
	/**
	 * Optional, type IDataModel. This IDataModel will have a J2EE specific component creation
	 * provider associated with it.
	 */
	public static final String NESTED_MODEL_J2EE_COMPONENT_CREATION = "IJ2EEArtifactImportDataModelProperties.NESTED_MODEL_J2EE_COMPONENT_CREATION"; //$NON-NLS-1$

}
