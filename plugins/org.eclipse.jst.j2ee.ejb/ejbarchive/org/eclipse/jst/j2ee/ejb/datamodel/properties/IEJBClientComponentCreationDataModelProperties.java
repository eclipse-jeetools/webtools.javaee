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

import org.eclipse.jst.j2ee.datamodel.properties.IJavaComponentCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.internal.DoNotUseMeThisWillBeDeletedPost15;
/**
 * <p>
 * IEJBClientComponentCreationDataModelProperties provides properties to the 
 * EJBClientComponentCreationDataModelProvider as well as all extending interfaces extending 
 * IEJBClientComponentCreationDataModelProperties
 * 
 * @see org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBClientComponentCreationDataModelProvider
 * </p>
 * <p>
 * This interface is not intended to be implemented by clients.
 * </p>
 * 
 * @see org.eclipse.wst.common.frameworks.datamodel.IDataModelProvider
 * @see org.eclipse.wst.common.frameworks.datamodel.DataModelFactory
 * @see 
 * 
 * @plannedfor 1.0
 */

/**
 * This has been slated for removal post WTP 1.5. Do not use this class/interface
 * 
 * @deprecated
 */
public interface IEJBClientComponentCreationDataModelProperties extends IJavaComponentCreationDataModelProperties, DoNotUseMeThisWillBeDeletedPost15{
    /**
     * Required, type String, the name of the Ejb Component
     */     
    public static final String EJB_COMPONENT_NAME = "IEJBClientComponentCreationDataModelProperties.EJB_COMPONENT_NAME"; //$NON-NLS-1$
    
    /**
     * Required, type String, the project name of the Ejb Component
     */     
    public static final String EJB_PROJECT_NAME = "IEJBClientComponentCreationDataModelProperties.EJB_PROJECT_NAME"; //$NON-NLS-1$ 
    
    /**
     * Required, type String, the deploy name of the Ejb component
     */     
    public static final String EJB_COMPONENT_DEPLOY_NAME = "IEJBClientComponentCreationDataModelProperties.EJB_COMPONENT_DEPLOY_NAME"; //$NON-NLS-1$
    /**
     * Optional, type String, this default value is set by EJBClientComponentCreationDataModelProvider
     */
    public static final String CLIENT_COMPONENT_URI = "IEJBClientComponentCreationDataModelProperties.CLIENT_COMPONENT_URI"; //$NON-NLS-1$
    
    /**
     * Required, type Boolean, the default value is <code>Boolean.TRUE</code>
     */
    public static final String ADD_TO_EAR = "IEJBClientComponentCreationDataModelProperties.ADD_TO_EAR"; //$NON-NLS-1$

    /**
     * Optional, type String, this value needs to be set if the property ADD_TO_EAR is set to <code>Boolean.TRUE</code>
     */
    
    public static final String EAR_COMPONENT_NAME = "IEJBClientComponentCreationDataModelProperties.EAR_COMPONENT_NAME"; //$NON-NLS-1$
    /**
     * Required, type String, the project name of the Ejb Component
     */     
    public static final String EAR_PROJECT_NAME = "IEJBClientComponentCreationDataModelProperties.EAR_PROJECT_NAME"; //$NON-NLS-1$ 
    
    /**
     * Optional, type String, this value needs to be set if the property ADD_TO_EAR is set to <code>Boolean.TRUE</code>
     */
    
    public static final String EAR_COMPONENT_DEPLOY_NAME = "IEJBClientComponentCreationDataModelProperties.EAR_COMPONENT_DEPLOY_NAME"; //$NON-NLS-1$
    
    /**
     * Required, type Boolean, the default value is <code>Boolean.FALSE</code>, if this property is set to <code>Boolean.TRUE</code>
     * the client component will be created in a separate project 
     */
    
    public static final String CREATE_PROJECT = "IEJBClientComponentCreationDataModelProperties.CREATE_PROJECT"; //$NON-NLS-1$

}
