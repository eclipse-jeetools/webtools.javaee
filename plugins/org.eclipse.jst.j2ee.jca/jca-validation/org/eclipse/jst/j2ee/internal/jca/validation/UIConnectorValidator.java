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
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.model.internal.validation.ConnectorValidator;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFile;
import org.eclipse.wst.validation.internal.operations.IWorkbenchContext;
import org.eclipse.wst.validation.internal.provisional.core.IReporter;
import org.eclipse.wst.validation.internal.provisional.core.IValidationContext;


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
	public void validate(IValidationContext inHelper, IReporter inReporter) throws org.eclipse.wst.validation.internal.core.ValidationException {
		
		ConnectorHelper helper = (ConnectorHelper) inHelper;
		
		IProject proj = ((IWorkbenchContext) inHelper).getProject();
		IVirtualComponent wbModule = ComponentCore.createComponent(proj);
		
            if(wbModule.getComponentTypeId() != null && wbModule.getComponentTypeId().equals(IModuleConstants.JST_CONNECTOR_MODULE)) {
			IVirtualFile rarDD = wbModule.getRootFolder().getFile(J2EEConstants.RAR_DD_URI);
			if( rarDD.exists()) {			
				super.validate(inHelper, inReporter);
			}
			//validateJ2EE14DocType(helper, editModel);
		}
	}


	/**
	 * Checks if the nature is consistent with doc type.
	 */
//	protected void validateJ2EE14DocType(ConnectorHelper helper, ConnectorEditModel editModel) {
//
//		if (editModel.getConnectorNature().getJ2EEVersion() == J2EEVersionConstants.J2EE_1_4_ID && getConnectorDD().getVersionID() < J2EEVersionConstants.JCA_1_5_ID) {
//			String[] params = new String[3];
//			params[0] = DOCTYPE_1_4;
//			params[1] = helper.getProject().getName();
//			params[2] = DOCTYPE_1_3;
//			addError(getBaseName(), CONNECTOR_INVALID_DOC_TYPE_ERROR_, params);
//		} else if (editModel.getConnectorNature().getJ2EEVersion() == J2EEVersionConstants.J2EE_1_3_ID && getConnectorDD().getVersionID() != J2EEVersionConstants.JCA_1_0_ID) {
//			String[] params = new String[3];
//			params[0] = DOCTYPE_1_3;
//			params[1] = helper.getProject().getName();
//			params[2] = DOCTYPE_1_4;
//			addError(getBaseName(), CONNECTOR_INVALID_DOC_TYPE_ERROR_, params);
//		}// if
//	}// validateDocTypeVsNature


}