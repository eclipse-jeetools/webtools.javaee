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
 * Created on Mar 30, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.common.operations.NewJavaClassOperation;
import org.eclipse.jst.j2ee.webapplication.Filter;
import org.eclipse.jst.j2ee.webapplication.FilterMapping;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.emfworkbench.integration.ModelModifier;
import org.eclipse.wst.common.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddFilterOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddFilterOperation(AddFilterDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddFilterDataModel model = (AddFilterDataModel) this.operationDataModel;
		createHelper(this.modifier, model);
	}

	private void createHelper(ModelModifier amodifier, AddFilterDataModel model) {
		String qualifiedClassName = null;
		boolean useExisting = model.getBooleanProperty(AddServletFilterListenerCommonDataModel.USE_EXISTING_CLASS);
		if (!useExisting) {
			NewFilterClassDataModel nestedModel = (NewFilterClassDataModel) model.getNestedModel("NewFilterClassDataModel"); //$NON-NLS-1$
			NewJavaClassOperation op = new NewJavaClassOperation(nestedModel);
			try {
				op.run(null);
			} catch (InvocationTargetException e) {
				Logger.getLogger().log(e);
			} catch (InterruptedException e) {
				Logger.getLogger().log(e);
			}
			qualifiedClassName = nestedModel.getQualifiedClassName();
		} else {
			qualifiedClassName = model.getStringProperty(AddServletFilterListenerCommonDataModel.CLASS_NAME);
		}
		// Get values from data model
		String displayName = model.getStringProperty(AddServletFilterListenerCommonDataModel.DISPLAY_NAME);
		String description = model.getStringProperty(AddServletFilterListenerCommonDataModel.DESCRIPTION);
		// Set up Filter
		Filter filter = WebapplicationFactory.eINSTANCE.createFilter();
		filter.setDisplayName(displayName);
		filter.setName(displayName);
		filter.setDescription(description);
		filter.setFilterClassName(qualifiedClassName);
		// set up InitParam
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		List initParamList = (List) model.getProperty(AddFilterDataModel.INIT_PARAM);
		if (initParamList != null) {
			int nP = initParamList.size();
			if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
				for (int iP = 0; iP < nP; iP++) {
					String[] stringArray = (String[]) initParamList.get(iP);
					ParamValue param = CommonFactory.eINSTANCE.createParamValue();
					param.setName(stringArray[0]);
					param.setValue(stringArray[1]);
					param.setDescription(stringArray[2]);
					filter.getInitParamValues().add(param);
				}
			} else {
				for (int iP = 0; iP < nP; iP++) {
					String[] stringArray = (String[]) initParamList.get(iP);
					InitParam ip = WebapplicationFactory.eINSTANCE.createInitParam();
					ip.setParamName(stringArray[0]);
					ip.setParamValue(stringArray[1]);
					ip.setDescription(stringArray[2]);
					filter.getInitParams().add(ip);
				}
			}
		}
		// Set up helper for filter
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_Filters());
		helper.setValue(filter);
		amodifier.addHelper(helper);

		// set up helper for URL mappings
		List urlMappingList = (List) model.getProperty(AddFilterDataModel.URL_MAPPINGS);
		if (urlMappingList != null) {
			int nM = urlMappingList.size();
			for (int iM = 0; iM < nM; iM++) {
				String[] stringArray = (String[]) urlMappingList.get(iM);
				FilterMapping mapping = WebapplicationFactory.eINSTANCE.createFilterMapping();
				mapping.setFilter(filter);
				mapping.setUrlPattern(stringArray[0]);
				ModifierHelper urlHelper = new ModifierHelper();
				urlHelper.setOwner(webApp);
				urlHelper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_FilterMappings());
				urlHelper.setValue(mapping);
				amodifier.addHelper(urlHelper);
			}
		}
	}
}