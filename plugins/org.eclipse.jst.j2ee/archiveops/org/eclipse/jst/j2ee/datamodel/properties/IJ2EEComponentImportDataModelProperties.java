/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
	 * Required, type String, name of the Project which the Archive will be imported.
	 */
	public static final String PROJECT_NAME = "IJ2EEComponentImportDataModelProperties.PROJECT_NAME"; //$NON-NLS-1$

	/**
	 * Unsettable property used to retreive the component after the operation has executed.
	 */
	public static final String COMPONENT = "IJ2EEComponentImportDataModelProperties.COMPONENT"; //$NON-NLS-1$
	
	/**
	 * Unsettable property used for archive switch.
	 */
	public static final String ARCHIVE_WRAPPER = "IJ2EEComponentImportDataModelProperties.ARCHIVE_WRAPPER"; //$NON-NLS-1$

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
