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
package org.eclipse.jst.j2ee.project.datamodel.properties;

import org.eclipse.wst.common.frameworks.datamodel.properties.IFlexibleProjectCreationDataModelProperties;

public interface IFlexibleJavaProjectCreationDataModelProperties extends IFlexibleProjectCreationDataModelProperties {
    public static final String NESTED_MODEL_SERVER_TARGET = "IFlexibleProjectCreationDataModelProperties.NESTED_MODEL_SERVER_TARGET"; //$NON-NLS-1$
    
    /**
     * An optional dataModel property for a <code>Boolean</code> type. The default value is
     * <code>Boolean.TRUE</code>. If this property is set to <code>Boolean.TRUE</code> then the
     * server target specified by dataModel property <code>SERVER_TARGET_ID</code> will be set on
     * the generated artifact.
     * 
     * @see SERVER_TARGET_ID
     */
    public static final String ADD_SERVER_TARGET = "IFlexibleProjectCreationDataModelProperties.ADD_SERVER_TARGET"; //$NON-NLS-1$
    /**
     * An optional dataModel property for a <code>java.lang.String</code> type. This is used to
     * specify the server target and is required if the <code>ADD_SERVER_TARGET</code> property is
     * set to <code>Boolean.TRUE</code>.
     * 
     * @see ServerTargetDataModel.RUNTIME_TARGET_ID
     */
    public static final String SERVER_TARGET_ID = IJ2EEProjectServerTargetDataModelProperties.RUNTIME_TARGET_ID;

}
