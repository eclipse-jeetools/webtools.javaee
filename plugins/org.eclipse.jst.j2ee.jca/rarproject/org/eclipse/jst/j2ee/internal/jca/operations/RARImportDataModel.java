/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;

import org.eclipse.jst.j2ee.application.operations.J2EEModuleCreationDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEModuleImportDataModel;
import org.eclipse.jst.j2ee.application.operations.J2EEProjectCreationDataModel;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.CommonarchiveFactory;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class RARImportDataModel extends J2EEModuleImportDataModel {

	protected void init() {
		super.init();
		setIntProperty(J2EEModuleCreationDataModel.J2EE_VERSION, J2EEVersionConstants.J2EE_1_3_ID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#createJ2EEProjectCreationDataModel()
	 */
	protected J2EEProjectCreationDataModel createJ2EEProjectCreationDataModel() {
		return new ConnectorProjectCreationDataModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#getType()
	 */
	protected int getType() {
		return XMLResource.RAR_TYPE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEImportDataModel#setUpArchiveFile()
	 */
	protected boolean openArchive(String uri) throws OpenFailureException {
		moduleFile = CommonarchiveFactory.eINSTANCE.openRARFile(getArchiveOptions(), uri);
		if (moduleFile == null)
			return false;
		return true;
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(PRESERVE_PROJECT_METADATA) && ((Boolean) propertyValue).booleanValue()) {
			setBooleanProperty(propertyName, false);
			return true;
		}
		return super.doSetProperty(propertyName, propertyValue);
	}

	public WTPOperation getDefaultOperation() {
		return new RARImportOperation(this);
	}



}