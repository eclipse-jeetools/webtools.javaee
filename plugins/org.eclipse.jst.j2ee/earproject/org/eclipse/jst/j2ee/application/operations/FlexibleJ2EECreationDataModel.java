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
package org.eclipse.jst.j2ee.application.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperationDataModelEvent;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclipse.wst.common.modulecore.internal.operation.ArtifactEditOperationDataModel;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * This dataModel is a common super class used for creation of WTP Modules.
 * 
 * This class (and all its fields and methods) is likely to change during the
 * WTP 1.0 milestones as the new project structures are adopted. Use at your own
 * risk.
 * 
 * @since WTP 1.0
 */
public abstract class FlexibleJ2EECreationDataModel extends WTPOperationDataModel {
    /**
     * Required
     */
    public static final String PROJECT_NAME = "ArtifactEditOperationDataModel.PROJECT_NAME"; //$NON-NLS-1$

    /**
     * Required
     */
    public static final String MODULE_NAME = "ArtifactEditOperationDataModel.MODULE_NAME"; //$NON-NLS-1$

    /**
     * An optional dataModel propertyName for a <code>Boolean</code> type. The
     * default value is <code>Boolean.TRUE</code>. If this property is set to
     * <code>Boolean.TRUE</code> then a default deployment descriptor and
     * supporting bindings files will be generated.
     */
    public static final String CREATE_DEFAULT_FILES = "FlexibleJ2EECreationDataModel.CREATE_DEFAULT_FILES"; //$NON-NLS-1$

    /**
     * An optional dataModel propertyName for a <code>Boolean</code> type. The
     * default value is <code>Boolean.TRUE</code>. If this property is set to
     * <code>Boolean.TRUE</code> then a default deployment descriptor and
     * supporting bindings files will be generated.
     */
    public static final String SHOULD_CREATE_PROJECT = "FlexibleJ2EECreationDataModel.SHOULD_CREATE_PROJECT"; //$NON-NLS-1$

    /**
     * Optional, type String
     */
    public static final String FINAL_PERSPECTIVE = "FlexibleJ2EECreationDataModel.FINAL_PERSPECTIVE"; //$NON-NLS-1$

    protected static final String IS_ENABLED = "FlexibleJ2EECreationDataModel.IS_ENABLED"; //$NON-NLS-1$

    private static final String NESTED_MODEL_J2EE_PROJECT_CREATION = "FlexibleJ2EECreationDataModel.NESTED_MODEL_J2EE_PROJECT_CREATION"; //$NON-NLS-1$


    protected void initValidBaseProperties() {
        addValidBaseProperty(PROJECT_NAME);
        addValidBaseProperty(MODULE_NAME);
        addValidBaseProperty(CREATE_DEFAULT_FILES);
        addValidBaseProperty(IS_ENABLED);
        addValidBaseProperty(FINAL_PERSPECTIVE);
        super.initValidBaseProperties();
    }

    protected Boolean basicIsEnabled(String propertyName) {
        return (Boolean) getProperty(IS_ENABLED);
    }

    public void propertyChanged(WTPOperationDataModelEvent event) {
        if (event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG) {
            WTPOperationDataModel dm = event.getDataModel();
        }
        super.propertyChanged(event);
    }

    protected boolean doSetProperty(String propertyName, Object propertyValue) {
        super.doSetProperty(propertyName, propertyValue);
        if (EditModelOperationDataModel.PROJECT_NAME.equals(propertyName)) {
        } else if (IS_ENABLED.equals(propertyName)) {
            notifyEnablementChange(PROJECT_NAME);
        }
        return true;
    }

    protected IStatus doValidateProperty(String propertyName) {
        if (propertyName.equals(MODULE_NAME)) {
            IStatus status = OK_STATUS;
            String projectName = getStringProperty(PROJECT_NAME);
            if (status.isOK()) {
                if (projectName.indexOf("#") != -1) { //$NON-NLS-1$
                    String errorMessage = J2EECreationResourceHandler.getString("InvalidCharsError"); //$NON-NLS-1$
                    return WTPCommonPlugin.createErrorStatus(errorMessage);
                }
            } else
                return status;

        } else if (propertyName.equals(NESTED_MODEL_VALIDATION_HOOK)) {
            return OK_STATUS;
        }
        return super.doValidateProperty(propertyName);
    }

    protected Object getDefaultProperty(String propertyName) {
        if (propertyName.equals(CREATE_DEFAULT_FILES) || propertyName.equals(IS_ENABLED)) {
            return Boolean.TRUE;
        }
        return super.getDefaultProperty(propertyName);
    }

    protected boolean isResultProperty(String propertyName) {
        if (propertyName.equals(FINAL_PERSPECTIVE))
            return true;
        return super.isResultProperty(propertyName);
    }
}
