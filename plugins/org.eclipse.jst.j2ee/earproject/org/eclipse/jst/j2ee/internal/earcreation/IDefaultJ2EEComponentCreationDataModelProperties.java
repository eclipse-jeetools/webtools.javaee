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
package org.eclipse.jst.j2ee.internal.earcreation;

public interface IDefaultJ2EEComponentCreationDataModelProperties {
    /**
     * Required - This is the name of the project. type String
     */
    public static final String PROJECT_NAME = "DefaultJ2EEComponentCreationDataModel.PROJECT_NAME"; //$NON-NLS-1$
    /**
     * Required - This is the name of the ear. type String
     */
    public static final String EAR_COMPONENT_NAME = "DefaultJ2EEComponentCreationDataModel.EAR_NAME"; //$NON-NLS-1$
    /**
     * Required - This is the name of the app client. type String
     */
    public static final String APPCLIENT_COMPONENT_NAME = "DefaultJ2EEComponentCreationDataModel.APPCLIENT_NAME"; //$NON-NLS-1$
    /**
     * Required - This is the name of the WEB. type String
     */
    public static final String WEB_COMPONENT_NAME = "DefaultJ2EEComponentCreationDataModel.WEB_COMPONENT_NAME"; //$NON-NLS-1$
    /**
     * Required - This is the name of the EJB. type String
     */
    public static final String EJB_COMPONENT_NAME = "DefaultJ2EEComponentCreationDataModel.EJB_COMPONENT_NAME"; //$NON-NLS-1$
    /**
     * Required - This is the name of the connector. type String
     */
    public static final String CONNECTOR_COMPONENT_NAME = "DefaultJ2EEComponentCreationDataModel.CONNECTOR_COMPONENT_NAME"; //$NON-NLS-1$

    public static final String J2EE_VERSION = "DefaultJ2EEComponentCreationDataModel.J2EE_VERSION"; //$NON-NLS-1$
   /**
     * Default is true. type Boolean
     */
    public static final String CREATE_EJB = "DefaultJ2EEComponentCreationDataModel.CREATE_EJB";
    /**
     * Default is true. type Boolean
     */
    public static final String CREATE_WEB = "DefaultJ2EEComponentCreationDataModel.CREATE_WEB";
    /**
     * Default is true. type Boolean
     */
    public static final String CREATE_APPCLIENT = "DefaultJ2EEComponentCreationDataModel.CREATE_CLIENT";
    /**
     * Default is true. type Boolean
     */
    public static final String CREATE_CONNECTOR = "DefaultJ2EEComponentCreationDataModel.CREATE_CONNECTOR";

    /**
     * Used for validation only; validates no collsions between various module names. Do not set
     * this value.
     */
    public static final String MODULE_NAME_COLLISIONS_VALIDATION = "DefaultJ2EEComponentCreationDataModel.MODULE_NAME_COLLISIONS_VALIDATION"; //$NON-NLS-1$

    /**
     * Default is true. type Boolean
     */
    public static final String ENABLED = "DefaultJ2EEComponentCreationDataModel.ENABLED"; //$NON-NLS-1$

    public static final String NESTED_MODEL_EJB = "DefaultJ2EEComponentCreationDataModel.NESTED_MODEL_EJB"; //$NON-NLS-1$
    public static final String NESTED_MODEL_WEB = "DefaultJ2EEComponentCreationDataModel.NESTED_MODEL_WEB"; //$NON-NLS-1$
    public static final String NESTED_MODEL_JCA = "DefaultJ2EEComponentCreationDataModel.NESTED_MODEL_JCA"; //$NON-NLS-1$
    public static final String NESTED_MODEL_CLIENT = "DefaultJ2EEComponentCreationDataModel.NESTED_MODEL_CLIENT"; //$NON-NLS-1$

}
