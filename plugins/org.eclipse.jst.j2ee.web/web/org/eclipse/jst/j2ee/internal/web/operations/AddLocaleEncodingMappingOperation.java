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
 * Created on Mar 8, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.webapplication.LocalEncodingMapping;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddLocaleEncodingMappingOperation extends ModelModifierOperation {

	/**
	 * @param dataModel
	 */
	public AddLocaleEncodingMappingOperation(ModelModifierOperationDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddLocaleEncodingMappingDataModel model = (AddLocaleEncodingMappingDataModel) this.operationDataModel;
		this.modifier.addHelper(createLocalEncodingMappingHelper(model));
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createLocalEncodingMappingHelper(AddLocaleEncodingMappingDataModel model) {
		ModifierHelper helper = new ModifierHelper();
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		if (webApp.getLocalEncodingMappingList() != null)
			helper.setOwner(webApp.getLocalEncodingMappingList());
		else
			helper.setOwnerHelper(createLocalEncodingMappingListHelper(model));
		helper.setFeature(WebapplicationPackage.eINSTANCE.getLocalEncodingMappingList_LocalEncodingMappings());
		LocalEncodingMapping lem = WebapplicationFactory.eINSTANCE.createLocalEncodingMapping();
		String locale = model.getStringProperty(AddLocaleEncodingMappingDataModel.LOCALE_NAME);
		String encoding = model.getStringProperty(AddLocaleEncodingMappingDataModel.ENCODING);
		lem.setLocale(locale);
		lem.setEncoding(encoding);
		helper.setValue(lem);
		return helper;
	}

	private ModifierHelper createLocalEncodingMappingListHelper(AddLocaleEncodingMappingDataModel model) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(model.getDeploymentDescriptorRoot());
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_LocalEncodingMappingList());
		return helper;
	}
}