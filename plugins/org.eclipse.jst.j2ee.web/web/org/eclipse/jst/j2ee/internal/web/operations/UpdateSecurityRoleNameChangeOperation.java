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
 * Created on May 21, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.Identity;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.frameworks.operations.WTPOperation;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModelModifier;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;


/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Generation - Code and Comments
 */
public class UpdateSecurityRoleNameChangeOperation extends WTPOperation {
	private J2EENature nature;
	private EditingDomain editingDomain;
	private String oldRoleName;
	private String newRoleName;

	public UpdateSecurityRoleNameChangeOperation(J2EENature nature, EditingDomain editingDomain, String oldRoleName, String newRoleName) {
		this.nature = nature;
		this.editingDomain = editingDomain;
		this.oldRoleName = oldRoleName;
		this.newRoleName = newRoleName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.operations.HeadlessJ2EEOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		J2EEEditModel editModel = null;
		ModelModifier modifier = null;
		try {
			modifier = new ModelModifier(this.editingDomain);
			editModel = (J2EEEditModel) this.nature.getEditModelForWrite(IWebNatureConstants.EDIT_MODEL_ID, modifier);
			WebApp webApp = (WebApp) editModel.getPrimaryRootObject();
			List servlets = webApp.getServlets();
			for (int j = 0; j < servlets.size(); j++) {
				Servlet servlet = (Servlet) servlets.get(j);
				// update security role references
				List roleRefs = servlet.getSecurityRoleRefs();
				for (int k = 0; k < roleRefs.size(); k++) {
					SecurityRoleRef roleRef = (SecurityRoleRef) roleRefs.get(k);
					if (this.oldRoleName.equals(roleRef.getLink())) {
						ModifierHelper helper = new ModifierHelper();
						helper.setOwner(roleRef);
						helper.setFeature(CommonPackage.eINSTANCE.getSecurityRoleRef_Link());
						helper.setValue(this.newRoleName);
						modifier.addHelper(helper);
					}
				}
				// update runas bindings to Servlets related to the role
				RunAsSpecifiedIdentity runAs = servlet.getRunAs();
				if (runAs == null)
					continue;
				Identity identity = runAs.getIdentity();
				if (identity == null)
					continue;
				String roleName = identity.getRoleName();
				if (this.oldRoleName.equals(roleName)) {
					ModifierHelper helper1 = new ModifierHelper();
					helper1.setOwner(identity);
					helper1.setFeature(CommonPackage.eINSTANCE.getIdentity_RoleName());
					helper1.setValue(this.newRoleName);
					modifier.addHelper(helper1);
				}
			}
			// update the role from auth constraints
			List constraints = webApp.getConstraints();
			for (int j = 0; j < constraints.size(); j++) {
				SecurityConstraint sc = (SecurityConstraint) constraints.get(j);
				AuthConstraint ac = sc.getAuthConstraint();
				if (ac == null)
					continue;
				List roles = ac.getRoles();
				for (int k = 0; k < roles.size(); k++) {
					String roleName = (String) roles.get(k);
					if (this.oldRoleName.equals(roleName)) {
						// remove the old
						ModifierHelper helper2 = new ModifierHelper();
						helper2.setOwner(ac);
						helper2.setFeature(WebapplicationPackage.eINSTANCE.getAuthConstraint_Roles());
						helper2.setValue(this.oldRoleName);
						helper2.doUnsetValue();
						modifier.addHelper(helper2);
						// add the new
						helper2 = new ModifierHelper();
						helper2.setOwner(ac);
						helper2.setFeature(WebapplicationPackage.eINSTANCE.getAuthConstraint_Roles());
						helper2.setValue(this.newRoleName);
						modifier.addHelper(helper2);
					}
				}
			}

			modifier.execute();
			if (editModel.isDirty()) {
				editModel.saveIfNecessary(this);
			}
		} finally {
			if (editModel != null) {
				editModel.releaseAccess(modifier);
				editModel = null;
			}
		}
	}

}