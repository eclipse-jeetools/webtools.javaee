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
package org.eclipse.jst.j2ee.web.datamodel.properties;

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEModuleImportDataModelProperties;
/**
 * <p>
 * IWebComponentImportDataModelProperties provides properties to the IDataModel associated with the 
 * WebComponentImportDataModelProvider.
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
public interface IWebComponentImportDataModelProperties extends IJ2EEModuleImportDataModelProperties {
  
    /**
     * Optional, type List.  List consists of Web Library Imports.
     */
    public static final String HANDLED_ARCHIVES = "WARImportDataModel.HANDLED_ARCHIVES"; //$NON-NLS-1$

}
