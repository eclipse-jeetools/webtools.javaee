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
 * Created on Jan 19, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
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
public class AddServletInitParamOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddServletInitParamOperation(AddServletInitParamDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddServletInitParamDataModel model = (AddServletInitParamDataModel) this.operationDataModel;
		ModifierHelper helper = createServletInitParamHelper(model);
		this.modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createServletInitParamHelper(AddServletInitParamDataModel model) {
		// get data from data model
		Servlet servlet = (Servlet) model.getProperty(AddServletInitParamDataModel.SERVLET);
		String paramName = model.getStringProperty(AddServletInitParamDataModel.PARAMETER_NAME);
		String paramValue = model.getStringProperty(AddServletInitParamDataModel.PARAMETER_VALUE);
		String paramDesc = model.getStringProperty(AddServletInitParamDataModel.DESCRIPTION);
		// set up helper
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(servlet);
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			ParamValue param = CommonFactory.eINSTANCE.createParamValue();
			param.setName(paramName);
			param.setValue(paramValue);
			Description description = CommonFactory.eINSTANCE.createDescription();
			description.setValue(paramDesc);
			param.getDescriptions().add(description);
			param.setDescription(paramDesc);
			helper.setFeature(WebapplicationPackage.eINSTANCE.getServlet_InitParams());
			helper.setValue(param);
		} else {
			InitParam ip = WebapplicationFactory.eINSTANCE.createInitParam();
			ip.setParamName(paramName);
			ip.setParamValue(paramValue);
			ip.setDescription(paramDesc);
			helper.setFeature(WebapplicationPackage.eINSTANCE.getServlet_Params());
			helper.setValue(ip);
		}
		return helper;
	}
}