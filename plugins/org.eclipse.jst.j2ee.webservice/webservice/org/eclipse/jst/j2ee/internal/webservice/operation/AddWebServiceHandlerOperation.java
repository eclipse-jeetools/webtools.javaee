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
 * Created on Feb 27, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice.operation;

import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaRefFactory;
import org.eclipse.jst.j2ee.webservice.internal.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientFactory;
import org.eclipse.wst.common.componentcore.internal.operation.ModelModifierOperation;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddWebServiceHandlerOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddWebServiceHandlerOperation(AddWebServiceHandlerDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddWebServiceHandlerDataModel model = (AddWebServiceHandlerDataModel) operationDataModel;
		ModifierHelper helper = createHelper(model);
		modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createHelper(AddWebServiceHandlerDataModel model) {
		ServiceRef serviceRef = (ServiceRef) model.getProperty(AddWebServiceHandlerDataModel.SERVICE_REF);
		Handler handler = Webservice_clientFactory.eINSTANCE.createHandler();
		handler.setDisplayName(model.getStringProperty(AddWebServiceHandlerDataModel.DISPLAY_NAME));
		handler.setDescription(model.getStringProperty(AddWebServiceHandlerDataModel.DESCRIPTION));
		handler.setHandlerName(model.getStringProperty(AddWebServiceHandlerDataModel.HANDLER_NAME));
		String className = model.getStringProperty(AddWebServiceHandlerDataModel.HANDLER_CLASS_NAME);
		JavaClass handlerClass = JavaRefFactory.eINSTANCE.createClassRef(className);
		handler.setHandlerClass(handlerClass);
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(serviceRef);
		helper.setFeature(Webservice_clientPackage.eINSTANCE.getServiceRef_Handlers());
		helper.setValue(handler);
		return helper;
	}
}