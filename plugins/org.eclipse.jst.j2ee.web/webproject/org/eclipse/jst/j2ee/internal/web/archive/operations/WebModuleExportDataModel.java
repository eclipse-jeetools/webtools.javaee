/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * This dataModel is used to export Web Modules to WAR files.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class WebModuleExportDataModel extends J2EEModuleExportDataModel {

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
	public static void exportProject(String webProjectName, String warFileName, boolean overwriteExisting, boolean exportSource) {
		WebModuleExportDataModel dataModel = new WebModuleExportDataModel();
		dataModel.setProperty(PROJECT_NAME, webProjectName);
		dataModel.setBooleanProperty(OVERWRITE_EXISTING, overwriteExisting);
		dataModel.setProperty(ARCHIVE_DESTINATION, warFileName);
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
		return new WebModuleExportOperation(this);
	}

	/**
	 * Required, type Boolean flag which indicates whether or not to export compiled JSP files
	 * (compiled files exist in /WEB-INF/classes as .class and .java[for debug])
	 */
	public static final String EXCLUDE_COMPILE_JSP = "WebExportDataModel.EXCLUDE_COMPILE_JSP"; //$NON-NLS-1$

	protected void init() {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(EXCLUDE_COMPILE_JSP);
		super.initValidBaseProperties();
	}

	protected Object getDefaultProperty(String propertyName) {
		if (EXCLUDE_COMPILE_JSP.equals(propertyName)) {
			return Boolean.FALSE;
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEExportDataModel#getModuleExtension()
	 */
	protected String getModuleExtension() {
		return ".war"; //$NON-NLS-1$
	}

	protected String getWrongProjectTypeString(String projectName) {
		return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_A_WAR, new Object[]{projectName});
	}

	protected String getNatureID() {
		return IWebNatureConstants.J2EE_NATURE_ID;
	}

}