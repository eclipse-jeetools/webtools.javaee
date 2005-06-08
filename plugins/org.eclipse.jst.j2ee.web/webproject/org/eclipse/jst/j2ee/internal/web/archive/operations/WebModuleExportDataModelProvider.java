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
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleExportDataModelProvider;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentExportDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class WebModuleExportDataModelProvider extends J2EEModuleExportDataModelProvider implements IWebComponentExportDataModelProperties {

    public WebModuleExportDataModelProvider() {
        super();
    }
    
    public IDataModelOperation getDefaultOperation() {
        return new WebModuleExportOperationNEW(model);
    }
    
    public String[] getPropertyNames() {
        String[] props = new String[]{EXCLUDE_COMPILE_JSP};
        return combineProperties(super.getPropertyNames(), props);
    }
    
    public Object getDefaultProperty(String propertyName) {
        if (EXCLUDE_COMPILE_JSP.equals(propertyName)) {
            return Boolean.FALSE;
        }
        return super.getDefaultProperty(propertyName);
    }
    
    protected String getComponentID() {
        return IModuleConstants.JST_WEB_MODULE;
    }

    protected String getWrongComponentTypeString(String projectName) {
        return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_A_WAR, new Object[]{projectName});
    }

    protected String getModuleExtension() {
        return ".war"; //$NON-NLS-1$
    }
    /**
     * Exports the specified Web Module project to the specified WAR file.
     * 
     * @param webProjectName
     *            The name of the Web Module project to export.
     * @param warFileName
     *            The fully qualified WAR file location to export the specified Web Module project.
     * @param overwriteExisting
     *            If this is <code>true</code> then an existing file at the location specified by
     *            <code>earFileName</code> will be overwritten.
     * @param exportSource
     *            If this is <code>true</code> then all source files in the specified Web Module
     *            will be included in the resulting WAR file.
     * @since WTP 1.0
     */
//TODO: determine plan for new DM Provider
//    public static void exportProject(String webProjectName, String warFileName, boolean overwriteExisting, boolean exportSource) {
//        WebModuleExportDataModel dataModel = new WebModuleExportDataModel();
//        dataModel.setProperty(PROJECT_NAME, webProjectName);
//        dataModel.setBooleanProperty(OVERWRITE_EXISTING, overwriteExisting);
//        dataModel.setProperty(ARCHIVE_DESTINATION, warFileName);
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
