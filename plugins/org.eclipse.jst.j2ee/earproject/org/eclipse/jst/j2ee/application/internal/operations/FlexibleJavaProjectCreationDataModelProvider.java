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

import org.eclipse.jst.j2ee.project.datamodel.properties.IFlexibleJavaProjectCreationDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class FlexibleJavaProjectCreationDataModelProvider extends FlexibleProjectCreationDataModelProvider implements IFlexibleJavaProjectCreationDataModelProperties{

    public FlexibleJavaProjectCreationDataModelProvider() {
        super();
    }
    public String[] getPropertyNames() {
        String[] props = new String[]{NESTED_MODEL_SERVER_TARGET, ADD_SERVER_TARGET, SERVER_TARGET_ID};
        return combineProperties(super.getPropertyNames(), props);
    }
    public Object getDefaultProperty(String propertyName) {
        if (propertyName.equals(ADD_SERVER_TARGET)) {
            return Boolean.TRUE;
        }
        return super.getDefaultProperty(propertyName);
    }
    public IDataModelOperation getDefaultOperation() {
        return new FlexibleJavaProjectCreationOperation(model);
    }
}
