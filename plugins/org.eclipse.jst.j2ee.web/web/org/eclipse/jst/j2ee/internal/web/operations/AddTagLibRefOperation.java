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
 * Created on Mar 9, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.jsp.JSPConfig;
import org.eclipse.jst.j2ee.jsp.JspFactory;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.jsp.TagLibRefType;
import org.eclipse.jst.j2ee.webapplication.TagLibRef;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperationDataModel;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddTagLibRefOperation extends ModelModifierOperation {

	/**
	 * @param dataModel
	 */
	public AddTagLibRefOperation(ModelModifierOperationDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddTagLibRefDataModel model = (AddTagLibRefDataModel) this.operationDataModel;
		this.modifier.addHelper(createHelper(model));
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createHelper(AddTagLibRefDataModel model) {
		ModifierHelper helper = new ModifierHelper();
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			TagLibRefType tlr = JspFactory.eINSTANCE.createTagLibRefType();
			String url = model.getStringProperty(AddTagLibRefDataModel.URL);
			String location = model.getStringProperty(AddTagLibRefDataModel.LOCATION);
			tlr.setTaglibURI(url);
			tlr.setTaglibLocation(location);
			JSPConfig config = webApp.getJspConfig();
			if (config == null) {
				helper.setOwner(webApp);
				config = JspFactory.eINSTANCE.createJSPConfig();
				config.getTagLibs().add(tlr);
				helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_JspConfig());
				helper.setValue(config);
			} else {
				helper.setOwner(config);
				helper.setFeature(JspPackage.eINSTANCE.getJSPConfig_TagLibs());
				helper.setValue(tlr);
			}
		} else {
			TagLibRef tlr = WebapplicationFactory.eINSTANCE.createTagLibRef();
			String url = model.getStringProperty(AddTagLibRefDataModel.URL);
			String location = model.getStringProperty(AddTagLibRefDataModel.LOCATION);
			tlr.setTaglibURI(url);
			tlr.setTaglibLocation(location);
			helper.setOwner(webApp);
			helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_TagLibs());
			helper.setValue(tlr);
		}
		return helper;
	}
}