/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.j2ee.application.operations.J2EEImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.WARFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.impl.WARFileImpl;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;
import org.eclipse.wst.web.internal.operation.WebSettings;
import org.eclispe.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public class WARImportDataModel extends J2EEModuleImportDataModel {
	//do not modify this property constant
	public static final String HANDLED_ARCHIVES = "WARImportDataModel.HANDLED_ARCHIVES"; //$NON-NLS-1$

	protected void initValidBaseProperties() {
		super.initValidBaseProperties();
		addValidBaseProperty(HANDLED_ARCHIVES);
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(BINARY)) {
			return Boolean.FALSE;
		}
		if (propertyName.equals(HANDLED_ARCHIVES)) {
			return Collections.EMPTY_LIST;
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#createJ2EEProjectCreationDataModel()
	 */
	protected J2EEProjectCreationDataModel createJ2EEProjectCreationDataModel() {
		WebProjectCreationDataModel dm = new WebProjectCreationDataModel();
		dm.setBooleanProperty(J2EEProjectCreationDataModel.ADD_SERVER_TARGET, false);
		dm.setBooleanProperty(WebProjectCreationDataModel.MIGRATE_WEB_SETTINGS, false);
		return dm;
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		boolean returnVal = super.doSetProperty(propertyName, propertyValue);
		if (propertyName.equals(PRESERVE_PROJECT_METADATA) || ((propertyName.equals(FILE) || propertyName.equals(FILE_NAME)) && getBooleanProperty(PRESERVE_PROJECT_METADATA))) {
			String webContentName = null;
			if (getBooleanProperty(PRESERVE_PROJECT_METADATA)) {
				WARFileImpl warFile = (WARFileImpl) moduleFile;
				if (null != warFile) {
					if (warFile.containsFile(".websettings")) { //$NON-NLS-1$
						try {
							webContentName = WebSettings.getWebContentDirectory(warFile.getInputStream(".websettings")); //$NON-NLS-1$
						} catch (FileNotFoundException e) {
						} catch (IOException e) {
						}
					}
				}
			}
			setProperty(WebProjectCreationDataModel.WEB_CONTENT, webContentName);
		}
		return returnVal;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#getType()
	 */
	protected int getType() {
		return XMLResource.WEB_APP_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#validateModuleType()
	 */
	protected IStatus validateModuleType() {
		if (moduleFile instanceof WARFile)
			return OK_STATUS;

		//TODO: STRING MOVE
		return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString("Temp String for none WARFile")); //$NON-NLS-1$);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#setUpArchiveFile()
	 */
	protected boolean openArchive(String uri) throws OpenFailureException {
		moduleFile = CommonarchiveFactory.eINSTANCE.openWARFile(getArchiveOptions(), uri);
		if (moduleFile == null)
			return false;
		return true;
	}

	public WTPOperation getDefaultOperation() {
		return new WARImportOperation(this);
	}

	public void extractHandled(List newList, boolean addModels) {
		List handledList = new ArrayList();
		WARFile warFile = (WARFile) getArchiveFile();
		List libArchives = warFile.getLibArchives();
		J2EEImportDataModel model = null;
		for (int i = newList.size() - 1; i > -1; i--) {
			model = (J2EEImportDataModel) newList.get(i);
			if (libArchives.contains(model.getArchiveFile())) {
				if (addModels) {
					handledList.add(model);
				}
				newList.remove(model);
			}
		}
		if (addModels) {
			setProperty(HANDLED_ARCHIVES, handledList);
		}
	}

	public boolean handlesArchive(Archive archive) {
		List list = (List) getProperty(HANDLED_ARCHIVES);
		J2EEImportDataModel model = null;
		for (int i = 0; i < list.size(); i++) {
			model = (J2EEImportDataModel) list.get(i);
			if (model.getArchiveFile() == archive) {
				return true;
			}
		}
		return false;
	}

}