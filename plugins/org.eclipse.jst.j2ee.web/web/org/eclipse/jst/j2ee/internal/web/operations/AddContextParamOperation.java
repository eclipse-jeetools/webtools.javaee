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
 * Created on Feb 5, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.ContextParam;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddContextParamOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddContextParamOperation(AddContextParamDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddContextParamDataModel model = (AddContextParamDataModel) this.operationDataModel;
		ModifierHelper helper = createContextParamHelper(model);
		this.modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createContextParamHelper(AddContextParamDataModel model) {
		ModifierHelper helper = new ModifierHelper();
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		helper.setOwner(webApp);
		String paramName = model.getStringProperty(AddContextParamDataModel.CONTEXT_PARAM_NAME);
		String paramValue = model.getStringProperty(AddContextParamDataModel.CONTEXT_PARAM_VALUE);
		String paramDesc = model.getStringProperty(AddContextParamDataModel.CONTEXT_PARAM_DESC);
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			ParamValue param = CommonFactory.eINSTANCE.createParamValue();
			param.setName(paramName);
			param.setValue(paramValue);
			param.setDescription(paramDesc);
			Description description = CommonFactory.eINSTANCE.createDescription();
			description.setValue(paramDesc);
			param.getDescriptions().add(description);
			helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_ContextParams());
			helper.setValue(param);
		} else {
			ContextParam cp = WebapplicationFactory.eINSTANCE.createContextParam();
			cp.setParamName(paramName);
			cp.setParamValue(paramValue);
			cp.setDescription(paramDesc);
			helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_Contexts());
			helper.setValue(cp);
		}
		return helper;
	}
}