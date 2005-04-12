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
package org.eclipse.jst.j2ee.application.internal.operations;

public interface IJ2EEModuleImportDataModelProperties extends IJ2EEComponentImportDataModelProperties {
    /**
     * nested
     */
    public static final String EAR_NAME = J2EEComponentCreationDataModel.EAR_MODULE_NAME;
    public static final String ADD_TO_EAR = J2EEComponentCreationDataModel.ADD_TO_EAR;

    public static final String EXTENDED_IMPORT_FACTORY = "J2EEModuleImportDataModel.EXTENDED_IMPORT_FACTORY"; //$NON-NLS-1$

}
