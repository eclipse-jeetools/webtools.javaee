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
 * Created on Feb 6, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.DisplayName;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationFactory;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class AddSecurityConstraintOperation extends ModelModifierOperation {
	private SecurityConstraint sc;

	/**
	 * @param dataModel
	 */
	public AddSecurityConstraintOperation(AddSecurityConstraintDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddSecurityConstraintDataModel model = (AddSecurityConstraintDataModel) this.operationDataModel;
		ModifierHelper helper = createSecurityConstraintHelper(model);
		this.modifier.addHelper(helper);
	}

	/**
	 * @param model
	 * @return
	 */
	private ModifierHelper createSecurityConstraintHelper(AddSecurityConstraintDataModel model) {
		ModifierHelper helper = new ModifierHelper();
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_Constraints());
		this.sc = WebapplicationFactory.eINSTANCE.createSecurityConstraint();
		String name = model.getStringProperty(AddSecurityConstraintDataModel.CONSTRAINT_NAME);
		if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
			DisplayName displayName = CommonFactory.eINSTANCE.createDisplayName();
			displayName.setValue(name);
			this.sc.getDisplayNames().add(displayName);
		} else {
			this.sc.setDisplayName(name);
		}
		helper.setValue(this.sc);
		return helper;
	}

	protected void postExecuteCommands(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
		AddWebResourceCollectionDataModel nestedModel = (AddWebResourceCollectionDataModel) this.operationDataModel.getNestedModel(AddWebResourceCollectionDataModel.ID); //$NON-NLS-1$
		nestedModel.setProperty(AddWebResourceCollectionDataModel.SECURITY_CONSTRAINT, this.sc);
		AddWebResourceCollectionOperation op = new AddWebResourceCollectionOperation(nestedModel);
		try {
			op.run(null);
		} catch (InvocationTargetException e) {
			Logger.getLogger().log(e);
		} catch (InterruptedException e) {
			Logger.getLogger().log(e);
		}

	}
}