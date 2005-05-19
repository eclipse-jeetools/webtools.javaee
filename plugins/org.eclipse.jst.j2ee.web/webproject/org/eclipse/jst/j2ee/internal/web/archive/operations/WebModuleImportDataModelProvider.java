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

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.internal.operations.J2EEModuleImportDataModelProvider;
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
 * @since WTP 1.0
 */
public final class WebModuleImportDataModelProvider extends J2EEModuleImportDataModelProvider implements IWebComponentImportDataModelProperties {

	public String[] getPropertyNames() {
		return combineProperties(super.getPropertyNames(), new String[]{WEB_LIB_COMPONENTS});
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(WEB_LIB_COMPONENTS)) {
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

	protected Archive openArchive(String uri) throws OpenFailureException {
		Archive archive = CommonarchiveFactory.eINSTANCE.openWARFile(getArchiveOptions(), uri);
		return archive;
	}

	public void extractHandled(List newList, boolean addModels) {
		List handledList = new ArrayList();
		WARFile warFile = (WARFile) getArchiveFile();
		List libArchives = warFile.getLibArchives();
		IDataModel tempModel = null;
		for (int i = newList.size() - 1; i > -1; i--) {
			tempModel = (IDataModel) newList.get(i);
			if (libArchives.contains(tempModel.getProperty(FILE))) {
				if (addModels) {
					handledList.add(tempModel);
				}
				newList.remove(tempModel);
			}
		}
		if (addModels) {
			setProperty(WEB_LIB_COMPONENTS, handledList);
		}
	}

	public boolean handlesArchive(Archive archive) {
		List list = (List) getProperty(WEB_LIB_COMPONENTS);
		IDataModel tempModel = null;
		for (int i = 0; i < list.size(); i++) {
			tempModel = (IDataModel) list.get(i);
			if (tempModel.getProperty(FILE) == archive) {
				return true;
			}
		}
		return false;
	}

	protected IDataModel createJ2EEComponentCreationDataModel() {
		return DataModelFactory.createDataModel(new WebComponentCreationDataModelProvider());
	}

	public IDataModelOperation getDefaultOperation() {
		return new WebModuleImportOperationNew(model);
	}


}