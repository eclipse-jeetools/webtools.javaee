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
package org.eclipse.jst.j2ee.datamodel.properties;

import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;



/**
 * <p>
 * IJ2EEComponentCreationDataModelProperties provides properties to the 
 * J2EEComponentCreationDataModelProvider as well as all extending interfaces extending 
 * IJ2EEComponentCreationDataModelProperties 
 * @see org.eclipse.jst.j2ee.internal.archive.operations.J2EEComponentCreationDataModelProvider
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.DataModelFactory
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties
 * @see org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties
 * @plannedfor 1.0
 */

/**
 * This has been slated for removal post WTP 1.5. Do not use this class/interface
 * 
 * @deprecated
 * 
 * @see IJ2EEFacetProjectCreationDataModelProperties
 */
public interface IJ2EEComponentCreationDataModelProperties extends IJavaComponentCreationDataModelProperties, DoNotUseMeThisWillBeDeletedPost15{

	/**
	 * Required, type Boolean, the default value is <code>Boolean.FALSE</code>.
	 */
	public static final String ADD_TO_EAR = "IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR"; //$NON-NLS-1$
	
	public static final String MODULE_URI = "IJ2EEComponentCreationDataModelProperties.MODULE_URI"; //$NON-NLS-1$
	/**
	 * type String, this property needs to be set if the value of the property ADD_TO_EAR  is set to <code>Boolean.TRUE</code>
	 */
	public static final String EAR_COMPONENT_NAME = "IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME"; //$NON-NLS-1$
    /**
     * type String, this property needs to be set if the value of the property ADD_TO_EAR  is set to <code>Boolean.TRUE</code>
     */
    public static final String EAR_COMPONENT_DEPLOY_NAME = "IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_DEPLOY_NAME"; //$NON-NLS-1$
    /**
     * type ComponentHandle, this property needs to be set if the value of the property ADD_TO_EAR  is set to <code>Boolean.TRUE</code>.  Represents
     * a handle to the EAR
     */
    public static final String EAR_COMPONENT_PROJECT = "IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_PROJECT"; //$NON-NLS-1$
    
	/**
	 * type Boolean; the default value is <code>Boolean.TRUE</code>, this is UI only property
	 */
	public static final String UI_SHOW_EAR_SECTION = "IJ2EEComponentCreationDataModelProperties.UI_SHOW_EAR_SECTION"; //$NON-NLS-1$
	
	/**
	 * Optional, type String
     * String indicates the name of the the folder where the deployment descriptor exists.
     * Each J2EE componenr provider will default the Deployment descriptor folder.
     * 
	 */
	
	public static final String DD_FOLDER = "IJ2EEComponentCreationDataModelProperties.DD_FOLDER"; //$NON-NLS-1$

    /**
     * Required, type Integer. The user defined version of the component.
     */
    public static final String COMPONENT_VERSION = "IComponentCreationDataModelProperties.COMPONENT_VERSION"; //$NON-NLS-1$

    /**
     * type Integer
     */
    public static final String VALID_COMPONENT_VERSIONS_FOR_PROJECT_RUNTIME = "IComponentCreationDataModelProperties.VALID_COMPONENT_VERSIONS_FOR_PROJECT_RUNTIME"; //$NON-NLS-1$
    /**
     * type AddComponentToEnterpriseApplicationDataModel
     */
    public static final String NESTED_ADD_COMPONENT_TO_EAR_DM = "IComponentCreationDataModelProperties.NESTED_ADD_COMPONENT_TO_EAR_DM"; //$NON-NLS-1$
    /**
     * type EARComponentCreationDataModel
     */
    public static final String NESTED_EAR_COMPONENT_CREATION_DM = "IComponentCreationDataModelProperties.NESTED_EAR_COMPONENT_CREATION_DM"; //$NON-NLS-1$
    /**
     * type UpdateManifestDataModel
     */
    public static final String NESTED_UPDATE_MANIFEST_DM = "IComponentCreationDataModelProperties.NESTED_UPDATE_MANIFEST_DM"; //$NON-NLS-1$
    /**
     * type ClassPathSelection
     */
    public static final String CLASSPATH_SELECTION = "IComponentCreationDataModelProperties.CLASSPATH_SELECTION"; //$NON-NLS-1$

}
