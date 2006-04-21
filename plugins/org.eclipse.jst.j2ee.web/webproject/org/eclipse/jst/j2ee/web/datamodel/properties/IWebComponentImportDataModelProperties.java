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
package org.eclipse.jst.j2ee.web.datamodel.properties;

import java.util.List;

import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEModuleImportDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

/**
 * <p>
 * IWebComponentImportDataModelProperties provides properties to the IDataModel associated with the
 * WebComponentImportDataModelProvider. NOTE: The associated Provider and Operations will be created
 * during M5
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
public interface IWebComponentImportDataModelProperties extends IJ2EEModuleImportDataModelProperties {

	/**
	 * <p>
	 * This optional {@link List} property is used to specify which nested web libraries should be
	 * expanded into projects during import. The contents of this {@link List} property should be
	 * {@link Archive}s.
	 * </p>
	 */
	public static final String WEB_LIB_ARCHIVES_SELECTED = "WARImportDataModel.WEB_LIB_ARCHIVES_SELECTED"; //$NON-NLS-1$

	/**
	 * <p>
	 * This {@link List} property should not be set by clients. This list contains the
	 * {@link IDataModel}s for creating the new components to be created for each of the selected
	 * archives. This list will contain one entry for each web lib archive regardless of whether it
	 * is selected for import by the {@link #WEB_LIB_ARCHIVES_SELECTED} property.
	 * </p>
	 */
	public static final String WEB_LIB_MODELS = "WARImportDataModel.WEB_LIB_MODELS"; //$NON-NLS-1$

	public static final String CONTEXT_ROOT = "IAddWebComponentToEnterpriseApplicationDataModelProperties.CONTEXT_ROOT"; //$NON-NLS-1$

}
