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

import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.internal.web.providers.WebAppEditResourceHandler;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddServletSecRoleRefOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddServletSecRoleRefOperation(AddServletSecRoleRefDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddServletSecRoleRefDataModel model = (AddServletSecRoleRefDataModel) this.operationDataModel;
		ModifierHelper helper = createServletSecRoleRefHelper(model);
		this.modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createServletSecRoleRefHelper(AddServletSecRoleRefDataModel model) {
		// get data from model
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		Servlet servlet = (Servlet) model.getProperty(AddServletSecRoleRefDataModel.SERVLET);
		String refName = model.getStringProperty(AddServletSecRoleRefDataModel.ROLE_REF_NAME);
		String refDesc = model.getStringProperty(AddServletSecRoleRefDataModel.ROLE_REF_DESC);
		String refLink = model.getStringProperty(AddServletSecRoleRefDataModel.ROLE_LINK);
		// set up values
		SecurityRoleRef srr = CommonFactory.eINSTANCE.createSecurityRoleRef();
		srr.setName(refName);
		srr.setDescription(refDesc);
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			Description description = CommonFactory.eINSTANCE.createDescription();
			description.setValue(refDesc);
			srr.getDescriptions().add(description);

		}
		if (!WebAppEditResourceHandler.getString("None_UI_").equals(refLink.trim())) { //$NON-NLS-1$
			srr.setLink(refLink.trim());
		} else
			srr.setLink(null);
		// set up helper
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(servlet);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getServlet_SecurityRoleRefs());
		helper.setValue(srr);
		return helper;
	}
}