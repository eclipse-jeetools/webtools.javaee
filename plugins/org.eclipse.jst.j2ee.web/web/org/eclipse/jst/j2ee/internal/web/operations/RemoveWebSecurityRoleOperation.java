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
 * Created on Jan 14, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.web.operations;

import java.util.List;

import org.eclipse.jst.j2ee.common.Identity;
import org.eclipse.jst.j2ee.common.RunAsSpecifiedIdentity;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.internal.common.operations.RemoveSecurityRoleDataModel;
import org.eclipse.jst.j2ee.webapplication.AuthConstraint;
import org.eclipse.jst.j2ee.webapplication.SecurityConstraint;
import org.eclipse.jst.j2ee.webapplication.Servlet;
import org.eclipse.jst.j2ee.webapplication.WebApp;
import org.eclipse.jst.j2ee.webapplication.WebapplicationPackage;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author jialin
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class RemoveWebSecurityRoleOperation extends ModelModifierOperation {


	public RemoveWebSecurityRoleOperation(RemoveSecurityRoleDataModel model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		RemoveSecurityRoleDataModel model = (RemoveSecurityRoleDataModel) this.operationDataModel;
		WebApp webApp = (WebApp) model.getDeploymentDescriptorRoot();
		List roleList = (List) model.getProperty(RemoveSecurityRoleDataModel.ROLE_LIST);
		int count = roleList.size();
		for (int i = 0; i < count; i++) {
			SecurityRole role = (SecurityRole) roleList.get(i);
			// remove role
			addRemoveSecurityRoleHelper(webApp, role);
			addRemoveDependentsHelper(webApp, role);
		}
	}

	private void addRemoveSecurityRoleHelper(WebApp webApp, SecurityRole role) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(webApp);
		helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_SecurityRoles());
		helper.setValue(role);
		helper.doUnsetValue();
		this.modifier.addHelper(helper);
	}

	private void addRemoveDependentsHelper(WebApp webApp, SecurityRole role) {
		String securityRoleName = role.getRoleName();
		List servlets = webApp.getServlets();
		for (int j = 0; j < servlets.size(); j++) {
			Servlet servlet = (Servlet) servlets.get(j);
			// remove security role references
			List roleRefs = servlet.getSecurityRoleRefs();
			for (int k = 0; k < roleRefs.size(); k++) {
				SecurityRoleRef roleRef = (SecurityRoleRef) roleRefs.get(k);
				if (securityRoleName.equals(roleRef.getLink())) {
					ModifierHelper helper1 = new ModifierHelper();
					helper1.setOwner(servlet);
					helper1.setFeature(WebapplicationPackage.eINSTANCE.getServlet_SecurityRoleRefs());
					helper1.setValue(roleRef);
					helper1.doUnsetValue();
					this.modifier.addHelper(helper1);
				}
			}
			// remove runas bindings to Servlets related to the role
			RunAsSpecifiedIdentity runAs = servlet.getRunAs();
			if (runAs == null)
				continue;
			Identity identity = runAs.getIdentity();
			if (identity == null)
				continue;
			String roleName = identity.getRoleName();
			if (securityRoleName.equals(roleName)) {
				ModifierHelper helper1 = new ModifierHelper();
				helper1.setOwner(servlet);
				helper1.setFeature(WebapplicationPackage.eINSTANCE.getServlet_RunAs());
				helper1.setValue(runAs);
				helper1.doUnsetValue();
				this.modifier.addHelper(helper1);
			}
		}
		// remove the role from auth constraints
		List constraints = webApp.getConstraints();
		for (int j = 0; j < constraints.size(); j++) {
			SecurityConstraint sc = (SecurityConstraint) constraints.get(j);
			AuthConstraint ac = sc.getAuthConstraint();
			if (ac == null)
				continue;
			List roles = ac.getRoles();
			for (int k = 0; k < roles.size(); k++) {
				String roleName = (String) roles.get(k);
				if (securityRoleName.equals(roleName)) {
					ModifierHelper helper2 = new ModifierHelper();
					helper2.setOwner(ac);
					helper2.setFeature(WebapplicationPackage.eINSTANCE.getAuthConstraint_Roles());
					helper2.setValue(roleName);
					helper2.doUnsetValue();
					this.modifier.addHelper(helper2);
				}
			}
		}
	}
}

//public class RemoveWebSecurityRoleOperation extends WTPOperation {
//	private J2EENature nature;
//	private AdapterFactoryEditingDomain editingDomain;
//	private List securityRoles;
//	
//	public RemoveWebSecurityRoleOperation(J2EENature nature, AdapterFactoryEditingDomain
// editingDomain, List securityRoles) {
//		this.nature = nature;
//		this.editingDomain = editingDomain;
//		this.securityRoles = securityRoles;
//	}
//
//	/* (non-Javadoc)
//	 * @see
// org.eclipse.jst.j2ee.internal.internal.operations.HeadlessJ2EEOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
//	 */
//	protected void execute(IProgressMonitor monitor)
//	throws CoreException, InvocationTargetException, InterruptedException {
//		J2EEEditModel editModel = null;
//		ModelModifier modifier = null;
//		try {
//			modifier = new ModelModifier(editingDomain);
//			editModel = (J2EEEditModel) nature.getEditModelForWrite(IWebNatureConstants.EDIT_MODEL_ID,
// modifier);
//			WebApp webApp = (WebApp)editModel.getPrimaryRootObject();
//			int count = securityRoles.size();
//			for (int i = 0; i < count; i++) {
//				// remove the security role
//				ModifierHelper helper = new ModifierHelper();
//				SecurityRole securityRole = (SecurityRole)securityRoles.get(i);
//				String securityRoleName = securityRole.getRoleName();
//				helper.setOwner(webApp);
//				helper.setFeature(WebapplicationPackage.eINSTANCE.getWebApp_SecurityRoles());
//				helper.setValue(securityRole);
//				helper.doUnsetValue();
//				modifier.addHelper(helper);
//				List servlets = webApp.getServlets();
//				for (int j = 0; j < servlets.size(); j++) {
//					Servlet servlet = (Servlet)servlets.get(j);
//					// remove security role references
//					List roleRefs = servlet.getSecurityRoleRefs();
//					for (int k = 0; k < roleRefs.size(); k++) {
//						SecurityRoleRef roleRef = (SecurityRoleRef)roleRefs.get(k);
//						if (securityRoleName.equals(roleRef.getLink())) {
//							ModifierHelper helper1 = new ModifierHelper();
//							helper1.setOwner(servlet);
//							helper1.setFeature(WebapplicationPackage.eINSTANCE.getServlet_SecurityRoleRefs());
//							helper1.setValue(roleRef);
//							helper1.doUnsetValue();
//							modifier.addHelper(helper1);
//						}
//					}
//					// remove runas bindings to Servlets related to the role
//					RunAsSpecifiedIdentity runAs = servlet.getRunAs();
//					if (runAs == null) continue;
//					Identity identity = runAs.getIdentity();
//					if (identity == null) continue;
//					String roleName = identity.getRoleName();
//					if (securityRoleName.equals(roleName)) {
//						ModifierHelper helper1 = new ModifierHelper();
//						helper1.setOwner(servlet);
//						helper1.setFeature(WebapplicationPackage.eINSTANCE.getServlet_RunAs());
//						helper1.setValue(runAs);
//						helper1.doUnsetValue();
//						modifier.addHelper(helper1);
//					}
//				}
//				// remove the role from auth constraints
//				List constraints = webApp.getConstraints();
//				for (int j = 0; j < constraints.size(); j++) {
//					SecurityConstraint sc = (SecurityConstraint)constraints.get(j);
//					AuthConstraint ac = sc.getAuthConstraint();
//					if (ac == null) continue;
//					List roles = ac.getRoles();
//					for (int k = 0; k < roles.size(); k++) {
//						String roleName = (String)roles.get(k);
//						if (securityRoleName.equals(roleName)) {
//							ModifierHelper helper2 = new ModifierHelper();
//							helper2.setOwner(ac);
//							helper2.setFeature(WebapplicationPackage.eINSTANCE.getAuthConstraint_Roles());
//							helper2.setValue(roleName);
//							helper2.doUnsetValue();
//							modifier.addHelper(helper2);
//						}
//					}
//				}
//				
//			}
//			modifier.execute();
//			if (editModel.isDirty()) {
//				editModel.saveIfNecessary(this);
//			}
//		} finally {
//			if (editModel != null) {
//				editModel.releaseAccess(modifier);
//				editModel = null;
//			}
//		}
//	}
//
//}
