/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;


import org.eclipse.jst.j2ee.application.operations.J2EEModuleExportDataModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.wst.common.frameworks.internal.operations.WTPOperation;

public class WARExportDataModel extends J2EEModuleExportDataModel {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#getDefaultOperation()
	 */
	public WTPOperation getDefaultOperation() {
		return new WARExportOperation(this);
	}

	/**
	 * Required, type Boolean flag which indicates whether or not to export compiled JSP files
	 * (compiled files exist in /WEB-INF/classes as .class and .java[for debug])
	 */
	public static final String EXCLUDE_COMPILE_JSP = "WebExportDataModel.EXCLUDE_COMPILE_JSP"; //$NON-NLS-1$

	protected void init() {
		super.init();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel#initValidBaseProperties()
	 */
	protected void initValidBaseProperties() {
		addValidBaseProperty(EXCLUDE_COMPILE_JSP);
		super.initValidBaseProperties();
	}

	protected Object getDefaultProperty(String propertyName) {
		if (EXCLUDE_COMPILE_JSP.equals(propertyName)) {
			return Boolean.FALSE;
		}
		return super.getDefaultProperty(propertyName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEExportDataModel#getModuleExtension()
	 */
	protected String getModuleExtension() {
		return ".war"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.application.operations.J2EEExportDataModel#getNatureID()
	 */
	protected String getNatureID() {
		return IWebNatureConstants.J2EE_NATURE_ID;
	}

	protected String getWrongProjectTypeString(String projectName) {
		return EARCreationResourceHandler.getString(EARCreationResourceHandler.NOT_A_WAR, new Object[]{projectName});
	}

}