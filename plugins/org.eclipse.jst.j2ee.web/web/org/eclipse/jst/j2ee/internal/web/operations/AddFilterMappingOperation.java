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

import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddFilterMappingOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddFilterMappingOperation(AddFilterMappingDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddFilterMappingDataModel model = (AddFilterMappingDataModel) this.operationDataModel;
		ModifierHelper helper = createFilterMappingHelper(model);
		this.modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createFilterMappingHelper(AddFilterMappingDataModel model) {
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		String filterName = model.getStringProperty(AddFilterMappingDataModel.FILTER);
		Filter filter = webApp.getFilterNamed(filterName);
		FilterMapping mapping = WebapplicationFactory.eINSTANCE.createFilterMapping();
		mapping.setFilter(filter);
		mapping.setUrlPattern(model.getStringProperty(AddFilterMappingDataModel.URL_PATTERN));
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			List dispatcherTypes = (List) model.getProperty(AddFilterMappingDataModel.DISPATCHER_TYPE);
			if (dispatcherTypes != null && dispatcherTypes.size() > 0)
				mapping.getDispatcherType().addAll(dispatcherTypes);
		}
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_FilterMappings());
		helper.setValue(mapping);
		return helper;
	}
}