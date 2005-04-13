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
package org.eclipse.jst.j2ee.archive.datamodel.properties;


public interface IAppClientComponentCreationDataModelProperties extends IJ2EEComponentCreationDataModelProperties {
    /**
     * Boolean, default=true. If this is true and CREATE_DEFAULT_FILES is true, then a default main
     * class will be generated during project creation.
     */
    public static final String CREATE_DEFAULT_MAIN_CLASS = "IAppClientComponentCreationDataModelProperties.CREATE_DEFAULT_MAIN_CLASS"; //$NON-NLS-1$
    
}
