/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEUtilityJarImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.jst.j2ee.web.datamodel.properties.IWebComponentImportDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

/**
 * This dataModel is used for to import Web Modules (from WAR files) into the workspace.
 * 
 * This class (and all its fields and methods) is likely to change during the WTP 1.0 milestones as
 * the new project structures are adopted. Use at your own risk.
 * 
 * @plannedfor WTP 1.0
 */
public final class WebComponentImportDataModelProvider extends J2EEComponentImportDataModelProvider implements IWebComponentImportDataModelProperties {

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(WEB_LIB_MODELS);
		propertyNames.add(WEB_LIB_ARCHIVES_SELECTED);
		return propertyNames;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(WEB_LIB_MODELS) || propertyName.equals(WEB_LIB_ARCHIVES_SELECTED)) {
			return Collections.EMPTY_LIST;
		}
		return super.getDefaultProperty(propertyName);
	}

	protected int getType() {
		return XMLResource.WEB_APP_TYPE;
	}

	protected IStatus validateModuleType() {
		if (getArchiveFile() instanceof WARFile)
			return OK_STATUS;

		// TODO: STRING MOVE
		return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString("Temp String for none WARFile")); //$NON-NLS-1$);
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(FILE)) {
			Archive archive = (Archive) propertyValue;
			if (null != archive) {
				WARFile war = (WARFile) archive;
				List libs = war.getLibArchives();
				List nestedModels = new ArrayList();
				for (int i = 0; i < libs.size(); i++) {
					IDataModel localModel = DataModelFactory.createDataModel(new J2EEUtilityJarImportDataModelProvider());
					localModel.setProperty(FILE, libs.get(i));
					nestedModels.add(localModel);
				}
				setProperty(WEB_LIB_MODELS, nestedModels);
			}
		}
		return true;
	}

	protected Archive openArchive(String uri) throws OpenFailureException {
		Archive archive = CommonarchiveFactory.eINSTANCE.openWARFile(getArchiveOptions(), uri);
		return archive;
	}

	protected IDataModel createJ2EEComponentCreationDataModel() {
		return DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
	}

	public IDataModelOperation getDefaultOperation() {
		return new WebComponentImportOperation(model);
	}

}