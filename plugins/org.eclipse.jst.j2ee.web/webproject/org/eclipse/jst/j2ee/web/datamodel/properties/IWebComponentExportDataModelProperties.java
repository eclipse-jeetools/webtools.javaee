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

import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentExportDataModelProperties;
/**
 * <p>
 * IWebComponentExportDataModelProperties provides properties to the IDataModel associated with the 
 * WebComponentExportDataModelProvider.
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
public interface IWebComponentExportDataModelProperties extends IJ2EEComponentExportDataModelProperties {
    /**
     * Required, type Boolean flag which indicates whether or not to export compiled JSP files
     * (compiled files exist in /WEB-INF/classes as .class and .java[for debug])
     */
    public static final String EXCLUDE_COMPILE_JSP = "WebExportDataModel.EXCLUDE_COMPILE_JSP"; //$NON-NLS-1$

}
