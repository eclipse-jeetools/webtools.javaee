/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.applicationclient.creation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.application.internal.operations.FlexibleProjectCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationDataModel;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentCreationOperation;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.applicationclient.internal.creation.AppClientComponentCreationDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.AppClientModuleImportOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

/**
 * This dataModel is used for to import Application Client Modules (from Application Client jar
 * files) into the workspace.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @since WTP 1.0
 */
public final class AppClientModuleImportDataModel extends J2EEModuleImportDataModel {

	/**
	 * Imports the specified Application Client Jar file into the specified Application Client
	 * Module project.
	 * 
	 * @param appClientJarFileName
	 *            The path to the Application Client Jar file.
	 * @param appClientProjectName
	 *            The name of the Application Client project where the Application Client Module
	 *            should be imported.
	 * @param addToEar
	 *            If this is <code>true</code> then Application Client project specified by
	 *            <code>appClientJarFileName</code> will be added to the Enterprise Application
	 *            project specified by <code>earProjectName</code>.
	 * @param earProjectName
	 *            The name of the Enterprise Application project to add the specified Application
	 *            Client project to. This field is only relevant if <code>addToEar</code> is set
	 *            to <code>true</code>. If this is set to <code>null</code> then a default name
	 *            computed from the <code>appClientJarFileName</code> will be used.
	 * @since WTP 1.0
	 */
	public static void importArchive(String appClientJarFileName, String appClientProjectName, boolean addToEar, String earProjectName) {
		AppClientModuleImportDataModel dataModel = new AppClientModuleImportDataModel();
		dataModel.setProperty(FILE_NAME, appClientJarFileName);
		dataModel.setProperty(PROJECT_NAME, appClientProjectName);
		dataModel.setBooleanProperty(ADD_TO_EAR, addToEar);
		if (earProjectName != null) {
			dataModel.setProperty(EAR_NAME, earProjectName);
		}
		try {
			dataModel.getDefaultOperation().run(null);
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
		} catch (InterruptedException e) {
			Logger.getLogger().logError(e);
		}
	}

	protected J2EEComponentCreationDataModel createJ2EEProjectCreationDataModel() {
		return new AppClientComponentCreationDataModel();
	}

	protected int getType() {
		return XMLResource.APP_CLIENT_TYPE;
	}

	protected boolean openArchive(String uri) throws OpenFailureException {
		setArchiveFile(CommonarchiveFactory.eINSTANCE.openApplicationClientFile(getArchiveOptions(), uri));
		if (getArchiveFile() == null)
			return false;
		return true;
	}

	public WTPOperation getDefaultOperation() {
		return new AppClientModuleImportOperation(this);
	}
}