/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;


import java.lang.reflect.InvocationTargetException;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.jca.archive.operations.ConnectorModuleExportOperation;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

/**
 * This dataModel is used to export Connector Modules to Connector RAR files.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class ConnectorModuleExportDataModel extends J2EEModuleExportDataModel {

	/**
	 * Exports the specified Connector Module project to the specified Connector RAR file.
	 * 
	 * @param connectorProjectName
	 *            The name of the Connector Module project to export.
	 * @param rarFileName
	 *            The fully qualified Connector RAR file location to export the specified Connector
	 *            Module project.
	 * @param overwriteExisting
	 *            If this is <code>true</code> then an existing file at the location specified by
	 *            <code>earFileName</code> will be overwritten.
	 * @param exportSource
	 *            If this is <code>true</code> then all source files in the specified Connector
	 *            Module will be included in the resulting Connector RAR file.
	 * @since WTP 1.0
	 */
	public static void exportProject(String connectorProjectName, String rarFileName, boolean overwriteExisting, boolean exportSource) {
		ConnectorModuleExportDataModel dataModel = new ConnectorModuleExportDataModel();
		dataModel.setProperty(PROJECT_NAME, connectorProjectName);
		dataModel.setBooleanProperty(OVERWRITE_EXISTING, overwriteExisting);
		dataModel.setProperty(ARCHIVE_DESTINATION, rarFileName);
		dataModel.setBooleanProperty(EXPORT_SOURCE_FILES, exportSource);
		try {
			dataModel.getDefaultOperation().run(null);
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
		} catch (InterruptedException e) {
			Logger.getLogger().logError(e);
		}
	}

	public WTPOperation getDefaultOperation() {
		return new ConnectorModuleExportOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEExportDataModel#getModuleExtension()
	 */
	protected String getModuleExtension() {
		return ".rar"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEExportDataModel#getNatureID()
	 */
	protected String getNatureID() {
		return IConnectorNatureConstants.NATURE_ID;
	}

	protected String getWrongProjectTypeString(String projectName) {
		return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_A_RAR, new Object[]{projectName});
	}

}