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
 * Created on Feb 11, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.EnvEntry;
import org.eclipse.jst.j2ee.common.EnvEntryType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddEnvEntryOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddEnvEntryOperation(AddEnvEntryDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddEnvEntryDataModel model = (AddEnvEntryDataModel) this.operationDataModel;
		ModifierHelper helper = createHelper(model);
		this.modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createHelper(AddEnvEntryDataModel model) {
		ModifierHelper helper = new ModifierHelper();
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		helper.setOwner(webApp);
		helper.setFeature(CommonPackage.eINSTANCE.getJNDIEnvRefsGroup_EnvironmentProperties());
		EnvEntry ee = CommonFactory.eINSTANCE.createEnvEntry();
		String name = model.getStringProperty(AddEnvEntryDataModel.ENV_ENTRY_NAME);
		EnvEntryType type = EnvEntryType.get(model.getStringProperty(AddEnvEntryDataModel.ENV_ENTRY_TYPE));
		String value = model.getStringProperty(AddEnvEntryDataModel.ENV_ENTRY_VALUE);
		ee.setName(name);
		ee.setType(type);
		ee.setValue(value);
		helper.setValue(ee);
		return helper;
	}
}