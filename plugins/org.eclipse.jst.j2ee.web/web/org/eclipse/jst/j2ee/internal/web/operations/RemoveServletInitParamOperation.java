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

import java.util.List;

import org.eclipse.jst.j2ee.webapplication.InitParam;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class RemoveServletInitParamOperation extends ModelModifierOperation {
	private List objectList;

	public RemoveServletInitParamOperation(AddServletInitParamDataModel dataModel, List objectList) {
		super(dataModel);
		this.objectList = objectList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddServletInitParamDataModel model = (AddServletInitParamDataModel) this.operationDataModel;
		int count = this.objectList.size();
		for (int i = 0; i < count; i++) {
			InitParam obj = (InitParam) this.objectList.get(i);
			ModifierHelper helper = createHelper(model, obj);
			this.modifier.addHelper(helper);
		}
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createHelper(AddServletInitParamDataModel model, InitParam obj) {
		ModifierHelper helper = new ModifierHelper();
		Servlet servlet = (Servlet) model.getProperty(AddServletInitParamDataModel.SERVLET);
		helper.setOwner(servlet);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getServlet_InitParams());
		servlet.getParams().remove(obj);
		helper.setValue(obj);
		helper.doUnsetValue();
		return helper;
	}
}