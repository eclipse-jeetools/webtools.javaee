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

import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
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
public class AddServletMappingOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddServletMappingOperation(AddServletMappingDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddServletMappingDataModel model = (AddServletMappingDataModel) this.operationDataModel;
		ModifierHelper helper = createServletMappingHelper(model);
		this.modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createServletMappingHelper(AddServletMappingDataModel model) {
		// get data from data model
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		String servletName = model.getStringProperty(AddServletMappingDataModel.SERVLET);
		Servlet servlet = webApp.getServletNamed(servletName);
		String urlPattern = model.getStringProperty(AddServletMappingDataModel.URL_PATTERN);
		// set up values
		ServletMapping mapping = WebapplicationFactory.eINSTANCE.createServletMapping();
		mapping.setServlet(servlet);
		mapping.setUrlPattern(urlPattern);
		// set up helper
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_ServletMappings());
		helper.setValue(mapping);
		return helper;
	}
}