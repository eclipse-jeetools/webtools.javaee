/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.jca.operations;


import org.eclipse.jst.j2ee.application.operations.J2EEModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.jca.archive.operations.RARExportOperation;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class RARExportDataModel extends J2EEModuleExportDataModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new RARExportOperation(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEExportDataModel#getModuleExtension()
	 */
	protected String getModuleExtension() {
		return ".rar"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEExportDataModel#getNatureID()
	 */
	protected String getNatureID() {
		return IConnectorNatureConstants.NATURE_ID;
	}

	protected String getWrongProjectTypeString(String projectName) {
		return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_A_RAR, new Object[]{projectName});
	}

}