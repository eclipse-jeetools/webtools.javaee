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

import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
/**
 * <p>
 * IJavaComponentCreationDataModelProperties provides properties to the 
 * JavaComponentCreationDataModelProvider as well as all extending interfaces extending 
 * IJavaComponentCreationDataModelProperties specifically, but not limited to all J2EE component related
 * creation.
 * @see org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.DataModelFactory
 * @see 
 * 
 * @since 1.0
 */
public interface IEJBClientComponentCreationDataModelProperties extends IJavaComponentCreationDataModelProperties {
    /**
     * Required, type String
     */     
    public static final String EJB_COMPONENT_NAME = "IEJBClientComponentCreationDataModelProperties.EJB_MODULE_NAME"; //$NON-NLS-1$
    
    /**
     * Required, type String
     */     
    public static final String EJB_PROJECT_NAME = "IEJBClientComponentCreationDataModelProperties.EJB_PROJECT_NAME"; //$NON-NLS-1$ 
    
    /**
     * Required, type String
     */     
    public static final String EJB_COMPONENT_DEPLOY_NAME = "IEJBClientComponentCreationDataModelProperties.EJB_COMPONENT_DEPLOY_NAME"; //$NON-NLS-1$
    

    /**
     * Required, type String
     */
    public static final String CLIENT_COMPONENT_URI = "IEJBClientComponentCreationDataModelProperties.CLIENT_COMPONENT_URI"; //$NON-NLS-1$

    /**
     * Optional, type boolean
     */
    public static final String DELETE_WHEN_FINISHED = "IEJBClientComponentCreationDataModelProperties.DELETE_WHEN_FINISHED"; //$NON-NLS-1$
    
    /**
     * type Boolean, default false
     */
    public static final String ADD_TO_EAR = "IEJBClientComponentCreationDataModelProperties.ADD_TO_EAR"; //$NON-NLS-1$

    /**
     * type String
     */
    
    public static final String EAR_MODULE_DEPLOY_NAME = "IEJBClientComponentCreationDataModelProperties.EAR_MODULE_DEPLOY_NAME"; //$NON-NLS-1$
    
    /**
     * type boolean
     */
    
    public static final String CREATE_PROJECT = "IEJBClientComponentCreationDataModelProperties.CREATE_PROJECT"; //$NON-NLS-1$
    /**
     * type URI, This  needs to be set up to ensure that other j2ee component is properly added as dependent component of ear 
     */
    public static final String EAR_COMPONENT_HANDLE = "IEJBClientComponentCreationDataModelProperties.CREATE_PROJECT"; //$NON-NLS-1$

}
