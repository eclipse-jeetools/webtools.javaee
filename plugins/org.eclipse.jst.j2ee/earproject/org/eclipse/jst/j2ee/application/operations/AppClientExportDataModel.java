/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.operations;

import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.AppClientExportOperation;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.wst.common.framework.operation.WTPOperation;



public class AppClientExportDataModel extends J2EEModuleExportDataModel {


	public WTPOperation getDefaultOperation() {
		return new AppClientExportOperation(this);
	}

	public AppClientExportDataModel() {
		super();
	}

	protected String getModuleExtension() {
		return ".jar"; //$NON-NLS-1$
	}

	protected String getNatureID() {
		return IApplicationClientNatureConstants.NATURE_ID;
	}

	protected String getWrongProjectTypeString(String projectName) {
		return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_AN_APP_CLIENT, new Object[]{projectName});
	}

}