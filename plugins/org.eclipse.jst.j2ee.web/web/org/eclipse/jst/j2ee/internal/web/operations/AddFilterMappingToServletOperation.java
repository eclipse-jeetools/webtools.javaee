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

import java.util.List;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
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
public class AddFilterMappingToServletOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddFilterMappingToServletOperation(AddFilterMappingToServletDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddFilterMappingToServletDataModel model = (AddFilterMappingToServletDataModel) this.operationDataModel;
		ModifierHelper helper = createFilterMappingHelper(model);
		this.modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createFilterMappingHelper(AddFilterMappingToServletDataModel model) {
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		Filter filter = (Filter) model.getProperty(AddFilterMappingToServletDataModel.FILTER);
		FilterMapping mapping = WebapplicationFactory.eINSTANCE.createFilterMapping();
		mapping.setFilter(filter);
		mapping.setServletName(model.getStringProperty(AddFilterMappingToServletDataModel.SERVLET_NAME));
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID)
			mapping.getDispatcherType().addAll((List) model.getProperty(AddFilterMappingToServletDataModel.DISPATCHER_TYPE));
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_FilterMappings());
		helper.setValue(mapping);
		return helper;
	}
}