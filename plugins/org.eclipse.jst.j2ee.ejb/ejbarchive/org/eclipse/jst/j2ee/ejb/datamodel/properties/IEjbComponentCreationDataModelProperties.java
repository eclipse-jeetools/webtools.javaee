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
package org.eclipse.jst.j2ee.ejb.datamodel.properties;
/**
 * <p>
 * IEjbComponentCreationDataModelProperties provides properties to the 
 * EjbComponentCreationDataModelProvider as well as all extending interfaces extending 
 * IEjbComponentCreationDataModelProperties 
 * @see org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EjbComponentCreationDataModelProvider
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.DataModelFactory
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProperties
 * @see org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties
 * @see org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties
 * @plannedfor 1.0
 */

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;

public interface IEjbComponentCreationDataModelProperties extends IJ2EEComponentCreationDataModelProperties {
	
	/**
	 * Required, type Boolean, the default value is <code>Boolean.TRUE</code>
	 */
    public static final String CREATE_CLIENT = "IEjbComponentCreationDataModelProperties.CREATE_CLIENT"; //$NON-NLS-1$
    
    /**
     * Required, type Boolean, the default value is <code>Boolean.FALSE</code>
     */	
    public static final String CREATE_DEFAULT_SESSION_BEAN = "IEjbComponentCreationDataModelProperties.CREATE_DEFAULT_SESSION_BEAN"; //$NON-NLS-1$
	
	/**
	 * Optional, type IDataModelProvider, needs to be set if the property CREATE_CLIENT is <code>Boolean.TRUE</code>
	 */

    public static final String NESTED_MODEL_EJB_CLIENT_CREATION = "IEjbComponentCreationDataModelProperties.NESTED_MODEL_EJB_CLIENT_CREATION"; //$NON-NLS-1$
}
