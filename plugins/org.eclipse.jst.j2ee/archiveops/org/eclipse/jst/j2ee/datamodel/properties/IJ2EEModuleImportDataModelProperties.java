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
 * IJ2EEModuleImportDataModelProperties provides properties to the IDataModel associated with the 
 * J2EEModuleImportDataModelProvider as well as all extending interfaces extending 
 * IJ2EEModuleImportDataModelProperties specifically all J2EE component specific imports.
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
 * @since 1.0
 */
public interface IJ2EEModuleImportDataModelProperties extends IJ2EEComponentImportDataModelProperties {
    /**
     * Optional, type String.  This property is nested from the J2EEComponentCreationDataModel and set only if user is attempting to add the component to an EAR.
     * Some J2EE components such as Web do not require an EAR.
     */
    public static final String EAR_COMPONENT_NAME = IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_NAME;
    /**
     * Optional, type Boolean.  This property defaults to True, but changes if user wishs to import a component
     * without adding it to an EAR.
     */
    public static final String ADD_TO_EAR = IJ2EEComponentCreationDataModelProperties.ADD_TO_EAR;
	
	public static final String EXTENDED_IMPORT_FACTORY = "IJ2EEModuleImportDataModelProperties.EXTENDED_IMPORT_FACTORY"; //$NON-NLS-1$


}
