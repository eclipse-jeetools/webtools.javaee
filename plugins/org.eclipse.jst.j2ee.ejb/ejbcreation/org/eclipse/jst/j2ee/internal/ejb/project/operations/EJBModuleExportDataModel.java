/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
/*
 * Created on Dec 15, 2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBModuleExportOperation;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModel;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

import org.eclipse.jem.util.logger.proxy.Logger;

/**
 * This dataModel is used to export EJB Modules to EJB JAR files.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class EJBModuleExportDataModel extends J2EEModuleExportDataModel {

	/**
	 * Exports the specified EJB Module project to the specified EJB JAR file.
	 * 
	 * @param ejbProjectName
	 *            The name of the EJB Module project to export.
	 * @param ejbJarFileName
	 *            The fully qualified EJB JAR file location to export the specified EJB
	 *            Module project.
	 * @param overwriteExisting
	 *            If this is <code>true</code> then an existing file at the location specified by
	 *            <code>earFileName</code> will be overwritten.
	 * @param exportSource
	 *            If this is <code>true</code> then all source files in the specified EJB
	 *            Module will be included in the resulting EJB JAR file.
	 * @since WTP 1.0
	 */
	public static void exportProject(String ejbProjectName, String ejbJarFileName, boolean overwriteExisting, boolean exportSource) {
		EJBModuleExportDataModel dataModel = new EJBModuleExportDataModel();
		dataModel.setProperty(PROJECT_NAME, ejbProjectName);
		dataModel.setBooleanProperty(OVERWRITE_EXISTING, overwriteExisting);
		dataModel.setProperty(ARCHIVE_DESTINATION, ejbJarFileName);
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
		return new EJBModuleExportOperation(this);
	}

	public EJBModuleExportDataModel() {
		super();
	}

	protected String getModuleExtension() {
		return ".jar"; //$NON-NLS-1$
	}

	protected String getNatureID() {
		return IEJBNatureConstants.NATURE_ID;
	}

	protected String getWrongProjectTypeString(String projectName) {
		return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_AN_EJB, new Object[]{projectName});
	}

	protected IStatus doValidateProperty(String propertyName) {
		IStatus status = super.doValidateProperty(propertyName);
		if (status.isOK()) {
			if (PROJECT_NAME.equals(propertyName)) {
				String projectName = (String) getProperty(PROJECT_NAME);
				IStatus status2 = ProjectCreationDataModel.validateProjectName(projectName);
				if (!status2.isOK()) {
					return status2;
				}
				IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
				EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(project);
				if (nature != null && nature.hasEJBClientJARProject()) {
					return WTPCommonPlugin.createWarningStatus(EJBCreationResourceHandler.getString("EJB_EXPORT_PROJECT_CONTAINS_CLEINT_WARNING")); //$NON-NLS-1$
				}
			}
		}
		return status;
	}
}