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
package org.eclipse.jst.j2ee.datamodel.properties;

import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;

/**
 * <p>
 * IJavaUtilityJarImportDataModelProperties provides properties to the IDataModel associated with the 
 * JavaUtilityJarImportDataModelProvider.
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
public interface IJavaUtilityJarImportDataModelProperties extends IJ2EEComponentImportDataModelProperties {
    /**
     * Required, type String.  Represents the name of the Project containing the EAR component, which the utility 
     * jar will be added.
     */
    public static final String EAR_PROJECT_NAME = "J2EEUtilityJarImportDataModel.EAR_PROJECT_NAME"; //$NON-NLS-1$
    /**
     * @deprecated use {@link #EAR_PROJECT_NAME}
     * 
     * @see DoNotUseMeThisWillBeDeletedPost15
     */
    public static final String EAR_COMPONENT_NAME = EAR_PROJECT_NAME;

}
