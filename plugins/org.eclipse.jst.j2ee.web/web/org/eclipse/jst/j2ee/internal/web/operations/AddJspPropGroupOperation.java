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
 * Created on Mar 17, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.jsp.JSPConfig;
import org.eclipse.jst.j2ee.jsp.JSPPropertyGroup;
import org.eclipse.jst.j2ee.jsp.JspFactory;
import org.eclipse.jst.j2ee.jsp.JspPackage;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.emfworkbench.integration.ModelModifier;
import org.eclipse.wst.common.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddJspPropGroupOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddJspPropGroupOperation(AddJspPropGroupDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddJspPropGroupDataModel model = (AddJspPropGroupDataModel) this.operationDataModel;
		createJspPropGroupHelper(this.modifier, model);
	}

	private void createJspPropGroupHelper(ModelModifier amodifier, AddJspPropGroupDataModel model) {
		// Get values from data model
		String displayName = model.getStringProperty(AddJspPropGroupDataModel.DISPLAY_NAME);
		String description = model.getStringProperty(AddJspPropGroupDataModel.DESCRIPTION);
		String encoding = model.getStringProperty(AddJspPropGroupDataModel.PAGE_ENCODING);
		Boolean isXML = (Boolean) model.getProperty(AddJspPropGroupDataModel.IS_XML);
		Boolean elIgnored = (Boolean) model.getProperty(AddJspPropGroupDataModel.EL_IGNORED);
		Boolean scriptingValid = (Boolean) model.getProperty(AddJspPropGroupDataModel.SCRIPTING_VALID);
		List urlPatterns = (List) model.getProperty(AddJspPropGroupDataModel.URL_PATTERNS);
		List includePreludes = (List) model.getProperty(AddJspPropGroupDataModel.INCLUDE_PRELUDES);
		List includeCodas = (List) model.getProperty(AddJspPropGroupDataModel.INCLUDE_CODAS);
		// Set up JSPPropertyGroup
		JSPPropertyGroup group = JspFactory.eINSTANCE.createJSPPropertyGroup();
		DisplayName dn = CommonFactory.eINSTANCE.createDisplayName();
		dn.setValue(displayName);
		group.getDisplayNames().add(dn);
		Description desc = CommonFactory.eINSTANCE.createDescription();
		desc.setValue(description);
		group.getDescriptions().add(desc);
		group.setPageEncoding(encoding);
		group.setIsXML(isXML.booleanValue());
		group.setElIgnored(elIgnored.booleanValue());
		group.setScriptingInvalid(scriptingValid.booleanValue());
		if (urlPatterns != null) {
			for (int i = 0; i < urlPatterns.size(); i++) {
				group.getUrlPattern().add(((String[]) urlPatterns.get(i))[0]);
			}
		}
		if (includePreludes != null) {
			for (int i = 0; i < urlPatterns.size(); i++) {
				group.getIncludePreludes().add(((String[]) includePreludes.get(i))[0]);
			}

		}
		if (includeCodas != null) {
			for (int i = 0; i < urlPatterns.size(); i++) {
				group.getIncludeCodas().add(((String[]) includeCodas.get(i))[0]);
			}

		}
		// Set up helper
		ModifierHelper helper = new ModifierHelper();
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		JSPConfig config = webApp.getJspConfig();
		if (config == null) {
			config = JspFactory.eINSTANCE.createJSPConfig();
			config.getPropertyGroups().add(group);
			helper.setOwner(webApp);
			helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_JspConfig());
			helper.setValue(config);
		} else {
			helper.setOwner(config);
			helper.setFeature(JspPackage.eINSTANCE.getJSPConfig_PropertyGroups());
			helper.setValue(group);
		}
		amodifier.addHelper(helper);
	}
}