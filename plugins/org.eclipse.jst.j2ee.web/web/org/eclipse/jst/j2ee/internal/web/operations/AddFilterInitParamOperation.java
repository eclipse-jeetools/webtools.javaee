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
 * Created on Jan 13, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddFilterInitParamOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddFilterInitParamOperation(AddFilterInitParamDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddFilterInitParamDataModel model = (AddFilterInitParamDataModel) this.operationDataModel;
		ModifierHelper helper = createFilterInitParamHelper(model);
		this.modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createFilterInitParamHelper(AddFilterInitParamDataModel model) {
		// get data from data model
		Filter filter = (Filter) model.getProperty(AddFilterInitParamDataModel.FILTER);
		String paramName = model.getStringProperty(AddFilterInitParamDataModel.PARAM_NAME);
		String paramValue = model.getStringProperty(AddFilterInitParamDataModel.PARAM_VALUE);
		String paramDesc = model.getStringProperty(AddFilterInitParamDataModel.DESCRIPTION);
		// set up helper
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(filter);
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			ParamValue param = CommonFactory.eINSTANCE.createParamValue();
			param.setName(paramName);
			param.setValue(paramValue);
			param.setDescription(paramDesc);
			Description description = CommonFactory.eINSTANCE.createDescription();
			description.setValue(paramDesc);
			param.getDescriptions().add(description);
			helper.setFeature(WebapplicationPackage.eINSTANCE.getFilter_InitParamValues());
			helper.setValue(param);
		} else {
			InitParam param = WebapplicationFactory.eINSTANCE.createInitParam();
			param.setParamName(paramName);
			param.setParamValue(paramValue);
			param.setDescription(paramDesc);
			helper.setFeature(WebapplicationPackage.eINSTANCE.getFilter_InitParams());
			helper.setValue(param);
		}

		return helper;
	}
}