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

import org.eclipse.jst.j2ee.internal.archive.operations.AppClientComponentExportOperation;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;


public class AppClientComponentExportDataModelProvider extends J2EEComponentExportDataModelProvider {

    public AppClientComponentExportDataModelProvider() {
        super();
    }

    public IDataModelOperation getDefaultOperation() {
        return new AppClientComponentExportOperation(model);
    }

    protected String getModuleExtension() {
        return ".jar"; //$NON-NLS-1$
    }

    protected String getWrongComponentTypeString(String projectName) {
        return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_AN_APP_CLIENT, new Object[]{projectName});
    }

    protected String getComponentID() {
        return IModuleConstants.JST_APPCLIENT_MODULE;
    }
    /**
     * Exports the specified Application Client Module project to the specified Application Client
     * Jar file.
     * 
     * @param appClientProjectName
     *            The name of the Application Client Module project to export.
     * @param appClientJarFileName
     *            The fully qualified Application Client Jar file location to export the specified
     *            Application Client Module project.
     * @param overwriteExisting
     *            If this is <code>true</code> then an existing file at the location specified by
     *            <code>earFileName</code> will be overwritten.
     * @param exportSource
     *            If this is <code>true</code> then all source files in the specified Application
     *            Client Module will be included in the resulting Application Client Jar file.
     * @since WTP 1.0
     */
//    public static void exportProject(String appClientProjectName, String appClientJarFileName, boolean overwriteExisting, boolean exportSource) {
//        AppClientModuleExportDataModel dataModel = new AppClientModuleExportDataModel();
//        dataModel.setProperty(PROJECT_NAME, appClientProjectName);
//        dataModel.setBooleanProperty(OVERWRITE_EXISTING, overwriteExisting);
//        dataModel.setProperty(ARCHIVE_DESTINATION, appClientJarFileName);
//        dataModel.setBooleanProperty(EXPORT_SOURCE_FILES, exportSource);
//        try {
//            dataModel.getDefaultOperation().run(null);
//        } catch (InvocationTargetException e) {
//            Logger.getLogger().logError(e);
//        } catch (InterruptedException e) {
//            Logger.getLogger().logError(e);
//        }
//    }
}
