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
package org.eclipse.jst.j2ee.internal.validation;


import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.command.Command;
import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.applicationclient.creation.AppClientEditModel;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.commonarchivecore.ValidateXmlCommand;
import org.eclipse.jst.j2ee.model.validation.ApplicationClientValidator;
import org.eclipse.wst.validation.core.IFileDelta;
import org.eclipse.wst.validation.core.IHelper;
import org.eclipse.wst.validation.core.IReporter;
import org.eclipse.wst.validation.core.ValidationException;


/**
 * Creates the application client validator for WSAD
 */
public class UIApplicationClientValidator extends ApplicationClientValidator implements UIApplicationClientMessageConstants {
	private ApplicationClientNatureRuntime appNature;

	/**
	 * Default constructor.
	 */
	public UIApplicationClientValidator() {
		super();
	}// UIApplicationClientValidator

	/**
	 * Create the validate XML
	 * 
	 * @return Command
	 */
	public Command createValidateXMLCommand() {
		ValidateXmlCommand cmd = (ValidateXmlCommand) super.createValidateXMLCommand();
		cmd.setValidateNested(false);
		return cmd;
	}// createValidateXMLCommand

	/**
	 * Get the resource name.
	 * 
	 * @return String
	 */
	protected String getResourceName() {
		return ((ApplicationClientHelper) _helper).getProject().getName();
	}// getResourceName

	/**
	 * Get the app client edit model.
	 * 
	 * @param IProject
	 *            appClientProject - Current project to get the editmodel.
	 * @return AppClientEditModel
	 */
	public AppClientEditModel getAppClientEditModel(IProject appClientProject) {
		appNature = ApplicationClientNatureRuntime.getRuntime(appClientProject);
		return appNature.getAppClientEditModelForRead(this);
	}// getAppClientEditModel

	/**
	 * Does the validation.
	 */
	public void validate(IHelper inHelper, IReporter inReporter, IFileDelta[] changedFiles) throws ValidationException {
		inReporter.removeAllMessages(this);
		UIApplicationClientHelper helper = (UIApplicationClientHelper) inHelper;
		AppClientEditModel editModel = getAppClientEditModel(helper.getProject());
		try {
			super.validate(helper, inReporter, changedFiles);
			validateDocType(helper, editModel);
		} finally {
			if (editModel != null) {
				editModel.releaseAccess(this);
			}//if
			if (appClientFile != null)
				appClientFile.close();
		}// try
	}// validate

	/**
	 * Checks if the nature is consistent with doc type.
	 */
	protected void validateDocType(UIApplicationClientHelper helper, AppClientEditModel editModel) {
		if (editModel.getJ2EENature().getJ2EEVersion() >= J2EEVersionConstants.J2EE_1_3_ID && getAppClientDD().getVersionID() < J2EEVersionConstants.J2EE_1_3_ID) {
			String[] params = new String[3];
			params[0] = DOCTYPE_1_2;
			params[1] = helper.getProject().getName();
			params[2] = DOCTYPE_1_3;
			addError(getBaseName(), APPCLIENT_INVALID_DOC_TYPE_ERROR_, params);
		} else if (editModel.getJ2EENature().getJ2EEVersion() < J2EEVersionConstants.J2EE_1_3_ID && getAppClientDD().getVersionID() >= J2EEVersionConstants.J2EE_1_3_ID) {
			String[] params = new String[3];
			params[0] = DOCTYPE_1_3;
			params[1] = helper.getProject().getName();
			params[2] = DOCTYPE_1_2;
			addError(getBaseName(), APPCLIENT_INVALID_DOC_TYPE_ERROR_, params);
		}// if
	}// validateDocTypeVsNature

	protected Object getManifestTarget() {
		if (appNature == null)
			return null;

		IContainer mofRoot = appNature.getEMFRoot();
		if (mofRoot == null)
			return null;

		return mofRoot.getFile(new Path(J2EEConstants.MANIFEST_URI));
	}

}// UIApplicationClientValidator
