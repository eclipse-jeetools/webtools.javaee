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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBJarExportOperation;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public final class EJBJarExportDataModel extends J2EEModuleExportDataModel {

	public WTPOperation getDefaultOperation() {
		return new EJBJarExportOperation(this);
	}

	public EJBJarExportDataModel() {
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
				IStatus status2 = validateProjectName(projectName);
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