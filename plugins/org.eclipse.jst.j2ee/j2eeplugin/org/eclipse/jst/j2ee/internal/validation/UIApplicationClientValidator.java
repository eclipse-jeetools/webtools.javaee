/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.validation;


import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.internal.validation.ApplicationClientValidator;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.validation.internal.operations.IWorkbenchContext;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;


/**
 * Creates the application client validator for WSAD
 */
public class UIApplicationClientValidator extends ApplicationClientValidator implements UIApplicationClientMessageConstants {


	/**
	 * Default constructor.
	 */
	public UIApplicationClientValidator() {
		super();
	}// UIApplicationClientValidator


	/**
	 * Does the validation.
	 */

	public void validate(IValidationContext inHelper, IReporter inReporter) throws org.eclipse.wst.validation.internal.core.ValidationException {
		UIApplicationClientHelper helper = (UIApplicationClientHelper) inHelper;
		IProject proj = ((IWorkbenchContext) inHelper).getProject();
		IVirtualComponent virComp = ComponentCore.createComponent(proj);
            if(J2EEProjectUtilities.isApplicationClientProject(proj)) {
				IVirtualFile ddFile = virComp.getRootFolder().getFile(J2EEConstants.APP_CLIENT_DD_URI);
				if( ddFile.exists()) {						
					super.validate(helper, inReporter);
				}
            }
			//validateDocType(helper, editModel);
	}
	

	/**
	 * Checks if the nature is consistent with doc type.
	 */
//	protected void validateDocType(UIApplicationClientHelper helper, AppClientEditModel editModel) {
//		if (editModel.getJ2EENature().getJ2EEVersion() >= J2EEVersionConstants.J2EE_1_3_ID && getAppClientDD().getVersionID() < J2EEVersionConstants.J2EE_1_3_ID) {
//			String[] params = new String[3];
//			params[0] = DOCTYPE_1_2;
//			params[1] = helper.getProject().getName();
//			params[2] = DOCTYPE_1_3;
//			addError(getBaseName(), APPCLIENT_INVALID_DOC_TYPE_ERROR_, params);
//		} else if (editModel.getJ2EENature().getJ2EEVersion() < J2EEVersionConstants.J2EE_1_3_ID && getAppClientDD().getVersionID() >= J2EEVersionConstants.J2EE_1_3_ID) {
//			String[] params = new String[3];
//			params[0] = DOCTYPE_1_3;
//			params[1] = helper.getProject().getName();
//			params[2] = DOCTYPE_1_2;
//			addError(getBaseName(), APPCLIENT_INVALID_DOC_TYPE_ERROR_, params);
//		}// if
//	}// validateDocTypeVsNature


}
