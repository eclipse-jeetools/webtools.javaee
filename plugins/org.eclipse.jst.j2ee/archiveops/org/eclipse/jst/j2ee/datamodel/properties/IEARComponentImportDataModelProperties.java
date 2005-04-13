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

public interface IEARComponentImportDataModelProperties extends IJ2EEComponentImportDataModelProperties {
    /**
     * Optional, type Boolean, default true, This flag is set to allow the EAR project to be
     * imported or not. If it is not imported, it is still possible for the nested projects (moduel &
     * utility projects) to be imported.
     */
    public static final String IMPORT_EAR_PROJECT = "IEnterpriseApplicationImportDataModelProperties.IMPORT_EAR_PROJECT"; //$NON-NLS-1$

    /**
     * Optional, type Boolean, default false, This flag is set to allow nested projects (module
     * projects & utility projects to be overwritten or not.
     */
    public static final String OVERWRITE_NESTED_PROJECTS = "IEnterpriseApplicationImportDataModelProperties.OVERWRITE_NESTED_PROJECTS"; //$NON-NLS-1$

    /*
     * Optional, type IPath default is ear location
     */
    public static final String NESTED_MODULE_ROOT = "IEnterpriseApplicationImportDataModelProperties.NESTED_MODULE_ROOT"; //$NON-NLS-1$

    /*
     * Optional, A List containing utililty jars;
     */
    public static final String UTILITY_LIST = "IEnterpriseApplicationImportDataModelProperties.UTILITY_LIST"; //$NON-NLS-1$

    public static final String SELECTED_MODELS_LIST = "IEnterpriseApplicationImportDataModelProperties.SELECTED_MODELS_LIST"; //$NON-NLS-1$

    public static final String EJB_CLIENT_LIST = "IEnterpriseApplicationImportDataModelProperties.EJB_CLIENT_LIST"; //$NON-NLS-1$

    /**
     * Booleam, default is true. When all the module projects are added to the ear, this controls
     * whether their server targets will be set to be the same as the one set on the ear.
     */
    public static final String SYNC_SERVER_TARGETS_WITH_EAR = "IEnterpriseApplicationImportDataModelProperties.SYNC_SERVER_TARGETS_WITH_EAR"; //$NON-NLS-1$

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
     * This is only to force validation for the nested projects; do not set.
     */
    public static final String NESTED_PROJECTS_VALIDATION = "IEnterpriseApplicationImportDataModelProperties.NESTED_PROJECTS_VALIDATION"; //$NON-NLS-1$

}
