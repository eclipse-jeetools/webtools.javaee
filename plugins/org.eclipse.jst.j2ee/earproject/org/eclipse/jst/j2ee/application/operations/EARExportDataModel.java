/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.application.operations;

import org.eclipse.jst.j2ee.internal.archive.operations.EARExportOperation;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.wst.common.framework.operation.WTPOperation;


public class EARExportDataModel extends J2EEExportDataModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.framework.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new EARExportOperation(this);
	}

	/*
	 * Optional, type boolean
	 */
	public static final String INCLUDE_BUILD_PATH_AND_META_FILES = "J2EEExportDataModel.INCLUDE_BUILD_PATH_AND_META_FILES"; //$NON-NLS-1$

	protected void init() {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(INCLUDE_BUILD_PATH_AND_META_FILES);
		super.initValidBaseProperties();
	}

	protected Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(INCLUDE_BUILD_PATH_AND_META_FILES))
			return Boolean.FALSE;
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEExportDataModel#getModuleExtension()
	 */
	protected String getModuleExtension() {
		return ".ear"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.application.operations.J2EEExportDataModel#getNatureID()
	 */
	protected String getNatureID() {
		return IEARNatureConstants.NATURE_ID;
	}

	protected String getWrongProjectTypeString(String projectName) {
		return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_AN_EAR, new Object[]{projectName});
	}

}