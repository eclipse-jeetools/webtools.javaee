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
 * Created on Mar 2, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.webservice.operation;

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.QName;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webservice.wsclient.Handler;
import org.eclipse.jst.j2ee.webservice.wsclient.Webservice_clientPackage;
import org.eclipse.jst.j2ee.webservice.wscommon.SOAPHeader;
import org.eclipse.jst.j2ee.webservice.wscommon.WscommonFactory;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddHandlerSOAPHeaderOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddHandlerSOAPHeaderOperation(AddHandlerSOAPHeaderDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddHandlerSOAPHeaderDataModel model = (AddHandlerSOAPHeaderDataModel) operationDataModel;
		ModifierHelper helper = createHelper(model);
		modifier.addHelper(helper);
	}

	private ModifierHelper createHelper(AddHandlerSOAPHeaderDataModel model) {
		Handler handler = (Handler) model.getProperty(AddHandlerSOAPHeaderDataModel.HANDLER);
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(handler);
		helper.setFeature(Webservice_clientPackage.eINSTANCE.getHandler_SoapHeaders());

		if (model.getVersionID() >= J2EEVersionConstants.VERSION_1_4) {
			SOAPHeader header = WscommonFactory.eINSTANCE.createSOAPHeader();
			header.setNamespaceURI(model.getStringProperty(AddHandlerSOAPHeaderDataModel.NAMESPACE_URL));
			header.setLocalPart(model.getStringProperty(AddHandlerSOAPHeaderDataModel.LOCAL_PART));
			helper.setValue(header);
		} else {
			QName header = CommonFactory.eINSTANCE.createQName();
			header.setNamespaceURI(model.getStringProperty(AddHandlerSOAPHeaderDataModel.NAMESPACE_URL));
			header.setLocalPart(model.getStringProperty(AddHandlerSOAPHeaderDataModel.LOCAL_PART));
			helper.setValue(header);
		}
		return helper;
	}
}