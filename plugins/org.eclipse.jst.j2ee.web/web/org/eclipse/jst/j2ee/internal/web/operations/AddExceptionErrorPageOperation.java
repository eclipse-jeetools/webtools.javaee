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
 * Created on Feb 12, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.webapplication.ExceptionTypeErrorPage;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel;


/**
 * @author jlanuti
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddExceptionErrorPageOperation extends ModelModifierOperation {

	/**
	 * @param dataModel
	 */
	public AddExceptionErrorPageOperation(ModelModifierOperationDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddExceptionErrorPageDataModel model = (AddExceptionErrorPageDataModel) this.operationDataModel;
		this.modifier.addHelper(createExceptionErrorPageHelper(model));
	}

	private ModifierHelper createExceptionErrorPageHelper(AddExceptionErrorPageDataModel model) {
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_ErrorPages());
		ExceptionTypeErrorPage ecep = WebapplicationFactory.eINSTANCE.createExceptionTypeErrorPage();
		ecep.setExceptionTypeName(model.getStringProperty(AddExceptionErrorPageDataModel.EXCEPTION_ERROR_PAGE_EXCEPTION_TYPE));
		ecep.setLocation(model.getStringProperty(AddExceptionErrorPageDataModel.EXCEPTION_ERROR_PAGE_LOCATION));
		helper.setValue(ecep);
		return helper;
	}
}