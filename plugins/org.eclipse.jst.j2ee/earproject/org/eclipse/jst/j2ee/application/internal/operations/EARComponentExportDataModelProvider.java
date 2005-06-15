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

import org.eclipse.jst.j2ee.internal.archive.operations.EARComponentExportOperation;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class EARComponentExportDataModelProvider extends J2EEArtifactExportDataModelProvider {

    public EARComponentExportDataModelProvider() {
        super();
    }
    
    public IDataModelOperation getDefaultOperation() {
        return new EARComponentExportOperation(model);
    }

    protected String getModuleExtension() {
        return ".ear"; //$NON-NLS-1$
    }

    protected String getWrongComponentTypeString(String projectName) {
        return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_AN_EAR, new Object[]{projectName});
    }

    protected String getComponentID() {
        return IModuleConstants.JST_EAR_MODULE;
    }
    /**
     * Exports the specified Enterprise Appliction project to the specified EAR file.
     * 
     * @param earProjectName
     *            The name of the Enterprise Application project to export.
     * @param earFileName
     *            The fully qualified EAR file location to export the specified Enterprise
     *            Application project.
     * @param overwriteExisting
     *            If this is <code>true</code> then an existing file at the location specified by
     *            <code>earFileName</code> will be overwritten.
     * @param exportSource
     *            If this is <code>true</code> then all source files in the specified Enterprise
     *            Application Project and all its modules will be included in the resulting EAR
     *            file.
     * @since WTP 1.0
     */
//TODO: can this be done in the new datamodel framework?
//    public static void exportProject(String earProjectName, String earFileName, boolean overwriteExisting, boolean exportSource) {
//        EnterpriseApplicationExportDataModel dataModel = new EnterpriseApplicationExportDataModel();
//        dataModel.setProperty(PROJECT_NAME, earProjectName);
//        dataModel.setBooleanProperty(OVERWRITE_EXISTING, overwriteExisting);
//        dataModel.setProperty(ARCHIVE_DESTINATION, earFileName);
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
