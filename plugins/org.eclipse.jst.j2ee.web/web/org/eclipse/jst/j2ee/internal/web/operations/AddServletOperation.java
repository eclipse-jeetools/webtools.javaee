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
 * Created on Mar 29, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.jst.j2ee.application.operations.IAnnotationsDataModel;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.common.ParamValue;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.operations.NewJavaClassDataModel;
import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.JSPType;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.ServletMapping;
import org.eclipse.jst.j2ee.webapplication.ServletType;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModelModifier;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddServletOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddServletOperation(NewServletClassDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		NewServletClassDataModel model = (NewServletClassDataModel) this.operationDataModel;
		createHelper(this.modifier, model);
	}

	private void generateHelpers(ModelModifier amodifier, NewServletClassDataModel model, String qualifiedClassName, boolean isServletType) {
		//Set up servlet
		Servlet servlet = createServlet(qualifiedClassName, isServletType);
		
		// Set up helper for servlet
		setUpServletHelper(amodifier, servlet);

		// set up helpers for InitParam
		List initParamList = (List) model.getProperty(NewServletClassDataModel.INIT_PARAM);
		if (initParamList != null) {
			setUpInitParams(initParamList,servlet);
		}

		// set up helper for URL mappings
		List urlMappingList = (List) model.getProperty(NewServletClassDataModel.URL_MAPPINGS);
		if (urlMappingList != null) {
			setUpURLMappings(urlMappingList, servlet);
		}
	}
	
	private Servlet createServlet(String qualifiedClassName, boolean isServletType) {
		// Get values from data model
		NewServletClassDataModel model = (NewServletClassDataModel) this.operationDataModel;
		String displayName = model.getStringProperty(NewServletClassDataModel.DISPLAY_NAME);
		String description = model.getStringProperty(NewServletClassDataModel.DESCRIPTION);
		
		// Set up Servlet
		Servlet servlet = WebapplicationFactory.eINSTANCE.createServlet();
		servlet.setDisplayName(displayName);
		servlet.setServletName(displayName);
		servlet.setDescription(description);
		if (isServletType) {
			ServletType servletType = WebapplicationFactory.eINSTANCE.createServletType();
			servletType.setClassName(qualifiedClassName);
			servlet.setWebType(servletType);
		} else {
			JSPType jspType = WebapplicationFactory.eINSTANCE.createJSPType();
			jspType.setJspFile(qualifiedClassName);
			servlet.setWebType(jspType);
		}
		return servlet;
	}
	
	private void setUpServletHelper(ModelModifier amodifier, Servlet servlet) {
		NewServletClassDataModel model = (NewServletClassDataModel) this.operationDataModel;
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_Servlets());
		helper.setValue(servlet);
		amodifier.addHelper(helper);
	}
	
	private void setUpInitParams(List initParamList, Servlet servlet) {
		NewServletClassDataModel model = (NewServletClassDataModel) this.operationDataModel;
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		int nP = initParamList.size();
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			for (int iP = 0; iP < nP; iP++) {
				String[] stringArray = (String[]) initParamList.get(iP);
				ParamValue param = CommonFactory.eINSTANCE.createParamValue();
				param.setName(stringArray[0]);
				param.setValue(stringArray[1]);
				Description descriptionObj = CommonFactory.eINSTANCE.createDescription();
				descriptionObj.setValue(stringArray[2]);
				param.getDescriptions().add(descriptionObj);
				param.setDescription(stringArray[2]);
				// servlet.getInitParams().add(param);
				ModifierHelper ipHelper = new ModifierHelper();
				ipHelper.setOwner(servlet);
				ipHelper.setFeature(WebapplicationPackage.eINSTANCE.getServlet_InitParams());
				ipHelper.setValue(param);
				modifier.addHelper(ipHelper);
			}
		} else {
			for (int iP = 0; iP < nP; iP++) {
				String[] stringArray = (String[]) initParamList.get(iP);
				InitParam ip = WebapplicationFactory.eINSTANCE.createInitParam();
				ip.setParamName(stringArray[0]);
				ip.setParamValue(stringArray[1]);
				ip.setDescription(stringArray[2]);
				// servlet.getParams().add(ip);
				ModifierHelper ipHelper = new ModifierHelper();
				ipHelper.setOwner(servlet);
				ipHelper.setFeature(WebapplicationPackage.eINSTANCE.getServlet_Params());
				ipHelper.setValue(ip);
				modifier.addHelper(ipHelper);
			}
		}
	}
	
	private void setUpURLMappings(List urlMappingList, Servlet servlet) {
		NewServletClassDataModel model = (NewServletClassDataModel) this.operationDataModel;
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		int nM = urlMappingList.size();
		for (int iM = 0; iM < nM; iM++) {
			String[] stringArray = (String[]) urlMappingList.get(iM);
			ServletMapping mapping = WebapplicationFactory.eINSTANCE.createServletMapping();
			mapping.setServlet(servlet);
			mapping.setName(servlet.getServletName());
			mapping.setUrlPattern(stringArray[0]);
			ModifierHelper urlHelper = new ModifierHelper();
			urlHelper.setOwner(webApp);
			urlHelper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_ServletMappings());
			urlHelper.setValue(mapping);
			modifier.addHelper(urlHelper);
		}
	}

	private void createHelper(ModelModifier amodifier, NewServletClassDataModel model) {
		boolean useExisting = model.getBooleanProperty(NewServletClassDataModel.USE_EXISTING_CLASS);
		boolean isServletType = model.getBooleanProperty(NewServletClassDataModel.IS_SERVLET_TYPE);
		String qualifiedClassName = model.getStringProperty(NewJavaClassDataModel.CLASS_NAME);
		if (!useExisting && isServletType) {
			// Create servlet java file
			NewServletClassOperation op = new NewServletClassOperation(model);
			try {
				op.setEditModel(this.editModel);
				op.run(null);
			} catch (InvocationTargetException e) {
				Logger.getLogger().log(e);
			} catch (InterruptedException e) {
				Logger.getLogger().log(e);
			}
			qualifiedClassName = model.getQualifiedClassName();
		}

		if (!model.getBooleanProperty(IAnnotationsDataModel.USE_ANNOTATIONS))
			generateHelpers(amodifier, model, qualifiedClassName, isServletType);

	}
}