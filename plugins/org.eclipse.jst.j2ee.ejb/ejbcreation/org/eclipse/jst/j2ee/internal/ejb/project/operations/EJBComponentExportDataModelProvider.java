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
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentExportDataModelProvider;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBComponentExportOperation;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;

public class EJBComponentExportDataModelProvider extends J2EEComponentExportDataModelProvider {

	public EJBComponentExportDataModelProvider() {
		super();
	}

	public IDataModelOperation getDefaultOperation() {
		return new EJBComponentExportOperation(model);
	}

	protected String getProjectType() {
		return J2EEProjectUtilities.EJB;
	}

	protected String getWrongComponentTypeString(String projectName) {
		return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_AN_EJB, new Object[]{projectName});
	}

	protected String getModuleExtension() {
		return ".jar"; //$NON-NLS-1$
	}

	public IStatus validate(String propertyName) {
		IStatus status = super.validate(propertyName);
		// if (status.isOK()) {
		// if (COMPONENT_NAME.equals(propertyName)) {
		// String projectName = (String) model.getProperty(COMPONENT_NAME);
		// IStatus status2 = ProjectCreationDataModel.validateProjectName(projectName);
		// if (!status2.isOK()) {
		// return status2;
		// }
		// IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(projectName);
		// EJBNatureRuntime nature = EJBNatureRuntime.getRuntime(project);
		// if (nature != null && nature.hasEJBClientJARProject()) {
		// return
		// WTPCommonPlugin.createWarningStatus(EJBCreationResourceHandler.getString("EJB_EXPORT_PROJECT_CONTAINS_CLEINT_WARNING"));
		// //$NON-NLS-1$
		// }
		// }
		// }
		return status;
	}
	/**
	 * Exports the specified EJB Module project to the specified EJB JAR file.
	 * 
	 * @param ejbProjectName
	 *            The name of the EJB Module project to export.
	 * @param ejbJarFileName
	 *            The fully qualified EJB JAR file location to export the specified EJB Module
	 *            project.
	 * @param overwriteExisting
	 *            If this is <code>true</code> then an existing file at the location specified by
	 *            <code>earFileName</code> will be overwritten.
	 * @param exportSource
	 *            If this is <code>true</code> then all source files in the specified EJB Module
	 *            will be included in the resulting EJB JAR file.
	 * @plannedfor WTP 1.0
	 */
	// TODO: how should this be handled in new dm providers
	// public static void exportProject(String ejbProjectName, String ejbJarFileName, boolean
	// overwriteExisting, boolean exportSource) {
	// EJBModuleExportDataModel dataModel = new EJBModuleExportDataModel();
	// dataModel.setProperty(PROJECT_NAME, ejbProjectName);
	// dataModel.setBooleanProperty(OVERWRITE_EXISTING, overwriteExisting);
	// dataModel.setProperty(ARCHIVE_DESTINATION, ejbJarFileName);
	// dataModel.setBooleanProperty(EXPORT_SOURCE_FILES, exportSource);
	// try {
	// dataModel.getDefaultOperation().run(null);
	// } catch (InvocationTargetException e) {
	// Logger.getLogger().logError(e);
	// } catch (InterruptedException e) {
	// Logger.getLogger().logError(e);
	// }
	// }
}
