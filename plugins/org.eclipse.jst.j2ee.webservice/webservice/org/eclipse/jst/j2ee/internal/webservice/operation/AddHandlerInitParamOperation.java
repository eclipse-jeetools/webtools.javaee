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
 * Created on Mar 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice.operation;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddHandlerInitParamOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddHandlerInitParamOperation(AddHandlerInitParamDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddHandlerInitParamDataModel model = (AddHandlerInitParamDataModel) operationDataModel;
		ModifierHelper helper = createHelper(model);
		modifier.addHelper(helper);
		Description descript = CommonFactory.eINSTANCE.createDescription();
		descript.setValue(model.getStringProperty(AddHandlerInitParamDataModel.DESCRIPTION));
		param.getDescriptions().add(descript);

	}

	/**
	 * @param model
	 * @return
	 */
	ParamValue param;

	private ModifierHelper createHelper(AddHandlerInitParamDataModel model) {
		Handler handler = (Handler) model.getProperty(AddHandlerInitParamDataModel.HANDLER);
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(handler);
		helper.setFeature(Webservice_clientPackage.eINSTANCE.getHandler_InitParams());
		//if (nature.getJ2EEVersion() >= J2EEVersionConstants.J2EE_1_4_ID) {
		param = CommonFactory.eINSTANCE.createParamValue();
		param.setName(model.getStringProperty(AddHandlerInitParamDataModel.PARAM_NAME));
		param.setValue(model.getStringProperty(AddHandlerInitParamDataModel.PARAM_VALUE));

		helper.setValue(param);

		//		} else {
		//			InitParam param = WscommonFactory.eINSTANCE.createInitParam();
		//			param.setParamName(model.getStringProperty(AddHandlerInitParamDataModel.PARAM_NAME));
		//			param.setParamValue(model.getStringProperty(AddHandlerInitParamDataModel.PARAM_VALUE));
		//			param.setDescription(model.getStringProperty(AddHandlerInitParamDataModel.DESCRIPTION));
		//			helper.setValue(param);
		//		}
		return helper;
	}
}