/*******************************************************************************
 * Copyright (c) 2001, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.model.internal.validation;



import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.emf.common.command.Command;
import org.eclipse.jst.j2ee.client.ApplicationClient;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ApplicationClientFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.ValidateXmlCommand;
import org.eclipse.wst.validation.internal.core.Message;
import org.eclipse.wst.validation.internal.core.ValidationException;
import org.eclipse.wst.validation.internal.provisional.core.IMessage;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;



/**
 * Validates the applicatin-client.xml
 */
public class ApplicationClientValidator extends J2EEValidator implements ApplicationClientMessageConstants {
	protected ApplicationClientFile appClientFile;
	protected ApplicationClient appClientDD;
	
	/**
 	 * ApplicationClientValidator constructor comment.
 	 */
	public ApplicationClientValidator() {
		super();
	}// ApplicationClientValidator
	
	/**
 	 * <p>Answer the id of the resource bundle which is
 	 * used by the receiver.</p>
 	 */
	public String getBaseName() {
		return APLICATIONCLIENT_CATEGORY;
	}// getBaseName
	
	/**
	 *  XML Validator now handles validation of Deployment Descriptor
	 * 
	 * @throws ValidationException
	 */
	public void validate() throws ValidationException {
	  validateMainClassInManifest();
	}// validate

	protected void validateMainClassInManifest() {

		if (appClientFile != null && appClientFile.getManifest() != null) {
			String mainClass = appClientFile.getManifest().getMainClass();
			if (mainClass == null || mainClass.length() == 0) {
				Object target = getManifestTarget();
				addError(getBaseName(), APPCLIENT_MAIN_CLASS_ERROR_, new String[0], target);
			}
		}
	}	
	
	protected Object getManifestTarget() {
		return null;
	}
	/**
	 * Does the validation.
	 * 
	 * @throws ValidationException
	 */
	public void validate(IValidationContext inHelper, IReporter inReporter)
	  throws ValidationException {
		
		validateInJob(inHelper, inReporter);
	}// validate
	/**
	 * Creates the validate xml command.
	 * 
	 * @return Command
	 */	
	public Command createValidateXMLCommand() {
		Command cmd = new ValidateXmlCommand(appClientFile);
		return cmd;
	}// createValidateXMLCommand
	
	/**
	 * Gets the appClientFile.
	 * 
	 * @return ApplicationClientFile
	 */
	public ApplicationClientFile getAppClientFile() {
		return appClientFile;
	}// getAppClientFile

	/**
	 * Sets the appClientFile.
	 * 
	 * @param ApplicatonClientFile appClientFile - The appClientFile to set
	 */
	public void setAppClientFile(ApplicationClientFile appClientFile) {
		this.appClientFile = appClientFile;
	}// setAppClientFile

	/**
	 * Gets the appClientDD.
	 * 
	 * @return ApplicationClient
	 */
	public ApplicationClient getAppClientDD() {
		return appClientDD;
	}// getAppClientDD

	/**
	 * Sets the appClientDD.
	 * 
	 * @param ApplicationClient appClientDD - The appClientDD to set
	 */
	public void setAppClientDD(ApplicationClient appClientDD) {
		this.appClientDD = appClientDD;
	}// setAppClientDD

	public ISchedulingRule getSchedulingRule(IValidationContext helper) {
		return null;
	}
	
	public IStatus validateInJob(IValidationContext inHelper, IReporter inReporter) 
	 		throws ValidationException {

		try {
			super.validateInJob(inHelper, inReporter);
			setAppClientFile( (ApplicationClientFile) inHelper.loadModel(APPCLIENT_MODEL_NAME) );
			if ( appClientFile != null ) {
				setAppClientDD( appClientFile.getDeploymentDescriptor() );
				validate();
			} else {
				IMessage errorMsg = new Message(getBaseName(), IMessage.HIGH_SEVERITY, ERROR_APPCLIENT_INVALID_APPCLIENT_FILE);
				throw new ValidationException(errorMsg);
			}// if
		} catch (ValidationException ex) {
			throw ex;
		} catch (Exception e) {
			IMessage errorMsg = new Message(getBaseName(), IMessage.HIGH_SEVERITY, ERROR_APPCLIENT_VALIDATION_FAILED);
			throw new ValidationException(errorMsg, e);
		}// try 
		return status;
	}
}// ApplicationClientValidator
