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
package org.eclipse.jst.j2ee.ejb.datamodel.properties;

import org.eclipse.jst.j2ee.archive.datamodel.properties.IJ2EEComponentCreationDataModelProperties;

public interface IEjbComponentCreationDataModelProperties extends IJ2EEComponentCreationDataModelProperties {
    public static final String CREATE_CLIENT = "IEjbComponentCreationDataModelProperties.CREATE_CLIENT"; //$NON-NLS-1$
    
    public static final String CREATE_DEFAULT_SESSION_BEAN = "IEjbComponentCreationDataModelProperties.CREATE_DEFAULT_SESSION_BEAN"; //$NON-NLS-1$

    public static final String NESTED_MODEL_EJB_CLIENT_CREATION = "IEjbComponentCreationDataModelProperties.NESTED_MODEL_EJB_CLIENT_CREATION"; //$NON-NLS-1$
}
