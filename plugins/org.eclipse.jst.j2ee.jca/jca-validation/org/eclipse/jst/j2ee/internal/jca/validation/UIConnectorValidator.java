/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Jan 22, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.jca.validation;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorEditModel;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;
import org.eclipse.jst.j2ee.model.validation.ConnectorValidator;
import org.eclipse.wst.validation.core.IHelper;
import org.eclipse.wst.validation.core.IReporter;
import org.eclipse.wst.validation.core.ValidationException;


/**
 * @author vijayb
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class UIConnectorValidator extends ConnectorValidator {
	/**
	 *  
	 */
	public UIConnectorValidator() {
		super();
	}

	/**
	 * Does the validation.
	 */
	public void validate(IHelper inHelper, IReporter inReporter, org.eclipse.wst.validation.core.IFileDelta[] changedFiles) throws ValidationException {
		inReporter.removeAllMessages(this);
		ConnectorHelper helper = (ConnectorHelper) inHelper;
		ConnectorEditModel editModel = getConnectorEditModel(helper.getProject());
		try {
			super.validate(helper, inReporter, changedFiles);
			validateJ2EE14DocType(helper, editModel);

		} finally {
			if (editModel != null) {
				editModel.releaseAccess(this);
			}//if
			if (rarFile != null)
				rarFile.close();
		}// try
	}// validate

	/**
	 * Get the connector edit model.
	 * 
	 * @param IProject
	 *            connectorProject - Current project to get the editmodel.
	 * @return ConnectorEditModel
	 */
	public ConnectorEditModel getConnectorEditModel(IProject appClientProject) {
		ConnectorNatureRuntime connectorNature = ConnectorNatureRuntime.getRuntime(appClientProject);
		return connectorNature.getConnectortEditModelForRead(this);
	}// getAppClientEditModel


	/**
	 * Checks if the nature is consistent with doc type.
	 */
	protected void validateJ2EE14DocType(ConnectorHelper helper, ConnectorEditModel editModel) {

		if (editModel.getConnectorNature().getJ2EEVersion() == J2EEVersionConstants.J2EE_1_4_ID && getConnectorDD().getVersionID() < J2EEVersionConstants.JCA_1_5_ID) {
			String[] params = new String[3];
			params[0] = DOCTYPE_1_4;
			params[1] = helper.getProject().getName();
			params[2] = DOCTYPE_1_3;
			addError(getBaseName(), CONNECTOR_INVALID_DOC_TYPE_ERROR_, params);
		} else if (editModel.getConnectorNature().getJ2EEVersion() == J2EEVersionConstants.J2EE_1_3_ID && getConnectorDD().getVersionID() != J2EEVersionConstants.JCA_1_0_ID) {
			String[] params = new String[3];
			params[0] = DOCTYPE_1_3;
			params[1] = helper.getProject().getName();
			params[2] = DOCTYPE_1_4;
			addError(getBaseName(), CONNECTOR_INVALID_DOC_TYPE_ERROR_, params);
		}// if
	}// validateDocTypeVsNature


}