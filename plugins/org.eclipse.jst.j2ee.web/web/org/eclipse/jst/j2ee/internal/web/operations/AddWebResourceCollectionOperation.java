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
 * Created on Feb 9, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.WebResourceCollection;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModelModifier;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddWebResourceCollectionOperation extends ModelModifierOperation {

	private WebResourceCollection wrc;

	/**
	 * @param dataModel
	 */
	public AddWebResourceCollectionOperation(AddWebResourceCollectionDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddWebResourceCollectionDataModel model = (AddWebResourceCollectionDataModel) this.operationDataModel;
		boolean httpCreated = createHTTPMethodTypeHelper(this.modifier, model);
		boolean urlCreated = createURLPatternsHelper(this.modifier, model);
		if (!httpCreated && !urlCreated) {
			ModifierHelper helper = createWebResourceCollectionHelper(model);
			this.modifier.addHelper(helper);
		}
	}

	private boolean createHTTPMethodTypeHelper(ModelModifier amodifier, AddWebResourceCollectionDataModel model) {
		List checkedHTTPMethods = (List) model.getProperty(AddWebResourceCollectionDataModel.HTTP_METHODS);
		if (checkedHTTPMethods != null && checkedHTTPMethods.size() > 0) {
			for (int i = 0; i < checkedHTTPMethods.size(); i++) {
				String method = (String) checkedHTTPMethods.get(i);
				ModifierHelper helper = new ModifierHelper();
				helper.setOwnerHelper(createWebResourceCollectionHelper(model));
				helper.setFeature(WebapplicationPackage.eINSTANCE.getWebResourceCollection_HttpMethod());
				helper.setValue(method);
				amodifier.addHelper(helper);
			}
			return true;
		}
		return false;
	}

	private boolean createURLPatternsHelper(ModelModifier amodifier, AddWebResourceCollectionDataModel model) {
		List urlPatterList = (List) model.getProperty(AddWebResourceCollectionDataModel.URL_PATTERNS);
		if (urlPatterList != null && urlPatterList.size() > 0) {
			for (int i = 0; i < urlPatterList.size(); i++) {
				String[] urlPatterArray = (String[]) urlPatterList.get(i);
				String urlPattern = urlPatterArray[0];
				ModifierHelper helper = new ModifierHelper();
				helper.setOwnerHelper(createWebResourceCollectionHelper(model));
				helper.setFeature(WebapplicationPackage.eINSTANCE.getWebResourceCollection_UrlPattern());
				helper.setValue(urlPattern);
				amodifier.addHelper(helper);
			}
			return true;
		}
		return false;
	}

	private ModifierHelper createWebResourceCollectionHelper(AddWebResourceCollectionDataModel model) {
		ModifierHelper helper = new ModifierHelper();
		SecurityConstraint sc = (SecurityConstraint) model.getProperty(AddWebResourceCollectionDataModel.SECURITY_CONSTRAINT);
		helper.setOwner(sc);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getSecurityConstraint_WebResourceCollections());
		if (this.wrc == null)
			this.wrc = WebapplicationFactory.eINSTANCE.createWebResourceCollection();
		String wrName = model.getStringProperty(AddWebResourceCollectionDataModel.RESOURCE_NAME);
		String wrDesc = model.getStringProperty(AddWebResourceCollectionDataModel.RESOURCE_DESCRIPTION);
		this.wrc.setWebResourceName(wrName);
		this.wrc.setDescription(wrDesc);
		this.wrc.setSecConstraint(sc);
		helper.setValue(this.wrc);
		return helper;
	}
}