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
/**
 * <p>
 * IEARComponentImportDataModelProperties provides properties to the IDataModel associated with the 
 * EARComponentImportDataModelProvider.
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
public interface IEARComponentImportDataModelProperties extends IJ2EEComponentImportDataModelProperties {

	/**
     * Optional, type IPath default is ear location
     */
    public static final String NESTED_MODULE_ROOT = "IEnterpriseApplicationImportDataModelProperties.NESTED_MODULE_ROOT"; //$NON-NLS-1$

    /**
     * Optional, A List containing utililty jars;
     */
    public static final String UTILITY_LIST = "IEnterpriseApplicationImportDataModelProperties.UTILITY_LIST"; //$NON-NLS-1$
    /**
     * Optional, A List containing modules to be imported list;
     */
    public static final String SELECTED_MODELS_LIST = "IEnterpriseApplicationImportDataModelProperties.SELECTED_MODELS_LIST"; //$NON-NLS-1$
    /**
     * Optional, A List containing ejb client jars associated with any ejb components which are to be imported.
     */
    public static final String EJB_CLIENT_LIST = "IEnterpriseApplicationImportDataModelProperties.EJB_CLIENT_LIST"; //$NON-NLS-1$

    /**
     * Optional. This is a list of data models. This list must contain all non-utilty projects in
     * the ear to be imported
     */
    public static final String MODULE_MODELS_LIST = "IEnterpriseApplicationImportDataModelProperties.MODULE_MODELS_LIST"; //$NON-NLS-1$

    /**
     * Optional. This is a list of data models. This list must contain all utility jars selected to
     * be imported
     */
    public static final String UTILITY_MODELS_LIST = "IEnterpriseApplicationImportDataModelProperties.UTILITY_MODELS_LIST"; //$NON-NLS-1$
	
	/**
	 * This is an unsettable property which will return all the project DataModels.
	 */
	public static final String ALL_PROJECT_MODELS_LIST = "IEnterpriseApplicationImportDataModelProperties.ALL_PROJECT_MODELS_LIST"; //$NON-NLS-1$

	/**
	 * This is an unsettable property which will return .
	 */
	public static final String UNHANDLED_PROJECT_MODELS_LIST = "IEnterpriseApplicationImportDataModelProperties.UNHANDLED_PROJECT_MODELS_LIST"; //$NON-NLS-1$

	/**
	 * This is an unsettable property which will return .
	 */
	public static final String HANDLED_PROJECT_MODELS_LIST = "IEnterpriseApplicationImportDataModelProperties.HANDLED_PROJECT_MODELS_LIST"; //$NON-NLS-1$
	
	
}
