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

import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.jst.j2ee.webapplication.WelcomeFile;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddWelcomePageOperation extends ModelModifierOperation {

	/**
	 * @param dataModel
	 */
	public AddWelcomePageOperation(ModelModifierOperationDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddWelcomePageDataModel model = (AddWelcomePageDataModel) this.operationDataModel;
		this.modifier.addHelper(createWelcomePageHelper(model));
	}

	private ModifierHelper createWelcomeFileListHelper(AddWelcomePageDataModel model) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(model.getDeploymentDescriptorRoot());
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_FileList());
		return helper;
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createWelcomePageHelper(AddWelcomePageDataModel model) {
		ModifierHelper helper = new ModifierHelper();
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		if (webApp.getFileList() != null)
			helper.setOwner(webApp.getFileList());
		else
			helper.setOwnerHelper(createWelcomeFileListHelper(model));
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWelcomeFileList_File());
		WelcomeFile wf = WebapplicationFactory.eINSTANCE.createWelcomeFile();
		String paramWelcomeFile = model.getStringProperty(AddWelcomePageDataModel.WELCOME_PAGE_WELCOME_FILE);
		wf.setWelcomeFile(paramWelcomeFile);
		helper.setValue(wf);
		return helper;
	}

}