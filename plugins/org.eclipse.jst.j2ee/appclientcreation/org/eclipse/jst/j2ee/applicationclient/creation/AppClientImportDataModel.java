/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.applicationclient.creation;

import org.eclipse.jst.j2ee.application.operations.J2EEArtifactCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.archive.operations.AppClientImportOperation;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public final class AppClientImportDataModel extends J2EEModuleImportDataModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#createJ2EEProjectCreationDataModel()
	 */
	protected J2EEArtifactCreationDataModel createJ2EEProjectCreationDataModel() {
		return new AppClientModuleCreationDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#getType()
	 */
	protected int getType() {
		return XMLResource.APP_CLIENT_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#setUpArchiveFile()
	 */
	protected boolean openArchive(String uri) throws OpenFailureException {
		setArchiveFile(CommonarchiveFactory.eINSTANCE.openApplicationClientFile(getArchiveOptions(), uri));
		if (getArchiveFile() == null)
			return false;
		return true;
	}

	public WTPOperation getDefaultOperation() {
		return new AppClientImportOperation(this);
	}
}