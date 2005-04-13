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

import org.eclipse.jst.j2ee.application.internal.operations.AddWebModuleToEARDataModel;
import org.eclipse.jst.j2ee.archive.datamodel.properties.IJ2EEComponentCreationDataModelProperties;

public interface IWebComponentCreationDataModelProperties extends IJ2EEComponentCreationDataModelProperties {

    /**
     * Type Integer
     */
    public static final String SERVLET_VERSION = "IWebComponentCreationDataModelProperties.SERVLET_VERSION"; //$NON-NLS-1$
    /**
     * Type Integer
     */
    public static final String JSP_VERSION = "IWebComponentCreationDataModelProperties.JSP_VERSION"; //$NON-NLS-1$
    /**
     * Type String
     */
    public static final String CONTEXT_ROOT = AddWebModuleToEARDataModel.CONTEXT_ROOT;


}
