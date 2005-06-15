/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.applicationclient.internal.creation;

import org.eclipse.jst.j2ee.application.internal.operations.J2EEComponentImportDataModelProvider;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.datamodel.properties.IAppClientComponentImportDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.AppClientComponentImportOperation;
import org.eclipse.jst.j2ee.internal.common.XMLResource;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelOperation;


public final class AppClientComponentImportDataModelProvider extends J2EEComponentImportDataModelProvider implements IAppClientComponentImportDataModelProperties {

	public AppClientComponentImportDataModelProvider() {
		super();
	}

	protected int getType() {
		return XMLResource.APP_CLIENT_TYPE;
	}

	protected Archive openArchive(String uri) throws OpenFailureException {
		return CommonarchiveFactory.eINSTANCE.openApplicationClientFile(getArchiveOptions(), uri);
	}

	public IDataModelOperation getDefaultOperation() {
		return new AppClientComponentImportOperation(model);
	}

	protected IDataModel createJ2EEComponentCreationDataModel() {
		return DataModelFactory.createDataModel(new AppClientComponentCreationDataModelProvider());
	}
}