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
 * IAppClientComponentCreationDataModelProperties provides properties to the 
 * AppClientComponentCreationDataModelProvider as well as all extending interfaces extending 
 * IEjbComponentCreationDataModelProperties 
 * @see org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModelProvider
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
 * @since 1.0
 */


public interface IAppClientComponentCreationDataModelProperties extends IJ2EEComponentCreationDataModelProperties {
    /**
     * Optional, type Boolean. the default value is <code>Boolean.TRUE</code>.If this is true and CREATE_DEFAULT_FILES is true, then a default main
     * class will be generated during component creation.
     */
    public static final String CREATE_DEFAULT_MAIN_CLASS = "IAppClientComponentCreationDataModelProperties.CREATE_DEFAULT_MAIN_CLASS"; //$NON-NLS-1$
    
}
