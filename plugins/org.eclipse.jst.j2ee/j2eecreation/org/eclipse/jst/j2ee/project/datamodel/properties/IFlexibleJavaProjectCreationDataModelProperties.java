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
package org.eclipse.jst.j2ee.project.datamodel.properties;

import org.eclipse.wst.common.componentcore.datamodel.properties.IFlexibleProjectCreationDataModelProperties;
/**
 * <p>
 * IFlexibleJavaProjectCreationDataModelProperties provides properties to the DataModel associated with the 
 * FlexibleJavaProjectCreationDataModelProvider
 * @see org.eclipse.jst.j2ee.application.internal.operations.FlexibleJavaProjectCreationDataModelProvider
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
public interface IFlexibleJavaProjectCreationDataModelProperties extends IFlexibleProjectCreationDataModelProperties {
    /**
     * Required, type IDataModel. The user set IDataModel used to handle the creation of a server target for the project to be
     * created.  Providers which currently exist for this IDataModel include J2EEProjectServerTargetDataModelProvider.
     */
    public static final String NESTED_MODEL_SERVER_TARGET = "IFlexibleProjectCreationDataModelProperties.NESTED_MODEL_SERVER_TARGET"; //$NON-NLS-1$
    
    /**
     * Optional, type Boolean The default value is <code>Boolean.TRUE</code>. If this property is set 
     * to <code>Boolean.TRUE</code> then the server target specified by dataModel property <code>SERVER_TARGET_ID</code> 
     * will be set on the generated artifact.
     * 
     * @see SERVER_TARGET_ID
     */
    public static final String ADD_SERVER_TARGET = "IFlexibleProjectCreationDataModelProperties.ADD_SERVER_TARGET"; //$NON-NLS-1$
    /**
     * Optional, type String. This is used to specify the server target and is required if 
     * the <code>ADD_SERVER_TARGET</code> property is set to <code>Boolean.TRUE</code>.
     */
    public static final String RUNTIME_TARGET_ID = IJ2EEProjectServerTargetDataModelProperties.RUNTIME_TARGET_ID;

}
