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

import org.eclipse.jst.j2ee.common.CommonFactory;
import org.eclipse.jst.j2ee.common.Description;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.WebApp;
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
public class AddAuthConstraintOperation extends ModelModifierOperation {
	/**
	 * @param dataModel
	 */
	public AddAuthConstraintOperation(AddAuthConstraintDataModel dataModel) {
		super(dataModel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		AddAuthConstraintDataModel model = (AddAuthConstraintDataModel) this.operationDataModel;
		createAuthConstraintHelper(this.modifier, model);
	}

	/**
	 * @param model
	 * @return
	 */
	private void createAuthConstraintHelper(ModelModifier amodifier, AddAuthConstraintDataModel model) {
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		SecurityConstraint sc = (SecurityConstraint) model.getProperty(AddAuthConstraintDataModel.SECURITY_CONSTRAINT);
		AuthConstraint ac = sc.getAuthConstraint();
		if (ac == null) {
			ac = WebapplicationFactory.eINSTANCE.createAuthConstraint();
			String acDesc = model.getStringProperty(AddAuthConstraintDataModel.AUTH_DESCRIPTION);
			if (webApp.getJ2EEVersionID() >= J2EEVersionConstants.J2EE_1_4_ID) {
				Description description = CommonFactory.eINSTANCE.createDescription();
				description.setValue(acDesc);
				ac.getDescriptions().add(description);
			} else {
				ac.setDescription(acDesc);
			}
			List checkedRoleNames = (List) model.getProperty(AddAuthConstraintDataModel.AUTH_ROLES);
			if (checkedRoleNames != null && checkedRoleNames.size() > 0) {
				for (int i = 0; i < checkedRoleNames.size(); i++) {
					String roleName = (String) checkedRoleNames.get(i);
					ac.getRoles().add(roleName);
				}
			}
			ModifierHelper helper = new ModifierHelper();
			helper.setOwner(sc);
			helper.setFeature(WebapplicationPackage.eINSTANCE.getSecurityConstraint_AuthConstraint());
			helper.setValue(ac);
			amodifier.addHelper(helper);
		} else {
			List checkedRoleNames = (List) model.getProperty(AddAuthConstraintDataModel.AUTH_ROLES);
			if (checkedRoleNames != null && checkedRoleNames.size() > 0) {
				for (int i = 0; i < checkedRoleNames.size(); i++) {
					String roleName = (String) checkedRoleNames.get(i);
					ModifierHelper helper = new ModifierHelper();
					helper.setOwner(ac);
					helper.setFeature(WebapplicationPackage.eINSTANCE.getAuthConstraint_Roles());
					helper.setValue(roleName);
					amodifier.addHelper(helper);
				}
			}
		}
	}
}