/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jst.j2ee.internal.archive.operations.EnterpriseApplicationExportOperation;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * This dataModel is used to export Enterprise Applications to EAR files.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class EnterpriseApplicationExportDataModel extends J2EEArtifactExportDataModel {

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
	public static void exportProject(String earProjectName, String earFileName, boolean overwriteExisting, boolean exportSource) {
		EnterpriseApplicationExportDataModel dataModel = new EnterpriseApplicationExportDataModel();
		dataModel.setProperty(PROJECT_NAME, earProjectName);
		dataModel.setBooleanProperty(OVERWRITE_EXISTING, overwriteExisting);
		dataModel.setProperty(ARCHIVE_DESTINATION, earFileName);
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
		return new EnterpriseApplicationExportOperation(this);
	}

	/*
	 * Optional, type boolean
	 */
	public static final String INCLUDE_BUILD_PATH_AND_META_FILES = "J2EEExportDataModel.INCLUDE_BUILD_PATH_AND_META_FILES"; //$NON-NLS-1$

	protected void init() {
		super.init();
	}

	protected void initValidBaseProperties() {
		addValidBaseProperty(INCLUDE_BUILD_PATH_AND_META_FILES);
		super.initValidBaseProperties();
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(INCLUDE_BUILD_PATH_AND_META_FILES))
			return Boolean.FALSE;
		return super.getDefaultProperty(propertyName);
	}

	protected String getModuleExtension() {
		return ".ear"; //$NON-NLS-1$
	}

	protected String getNatureID() {
		return IEARNatureConstants.NATURE_ID;
	}

	protected String getWrongProjectTypeString(String projectName) {
		return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_AN_EAR, new Object[]{projectName});
	}
}