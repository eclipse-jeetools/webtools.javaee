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
 * Created on Jun 22, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package org.eclipse.jst.j2ee.internal.common.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.internal.application.ApplicationPackage;
import org.eclipse.jst.j2ee.internal.ejb.impl.EjbFactoryImpl;
import org.eclipse.wst.common.internal.emfworkbench.integration.ModifierHelper;
import org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation;


/**
 * @author jialin
 * 
 * TODO To change the template for this generated type comment go to Window - Preferences - Java -
 * Code Style - Code Templates
 */
public class RemoveSecurityRoleOperation extends ModelModifierOperation {
	//private List securityRoles;

	private int mode = -1;
	private static final int APPLICATION_MODE = 1;
	private static final int EJB_MODE = 2;


	public RemoveSecurityRoleOperation(RemoveSecurityRoleDataModel model) {
		super(model);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.operation.ModelModifierOperation#addHelpers()
	 */
	protected void addHelpers() {
		RemoveSecurityRoleDataModel model = (RemoveSecurityRoleDataModel) operationDataModel;
		EObject root = model.getDeploymentDescriptorRoot();
		if (root instanceof Application) {
			mode = APPLICATION_MODE;
		} else if (root instanceof EJBJar) {
			mode = EJB_MODE;
		} else {
			return;
		}
		List roleList = (List) model.getProperty(RemoveSecurityRoleDataModel.ROLE_LIST);
		int count = roleList.size();
		for (int i = 0; i < count; i++) {
			SecurityRole role = (SecurityRole) roleList.get(i);
			//String securityRoleName = role.getRoleName();
			// remove role
			addRemoveSecurityRoleHelper(role);
		}
		if (mode == EJB_MODE) {
			createMethodPermissionsRoleRemoveHelpers();
		}
	}

	protected void addRemoveSecurityRoleHelper(SecurityRole role) {
		ModifierHelper helper = new ModifierHelper();
		helper.setOwner(role.eContainer());
		if (mode == APPLICATION_MODE) {
			helper.setFeature(ApplicationPackage.eINSTANCE.getApplication_SecurityRoles());
		} else if (mode == EJB_MODE) {
			helper.setFeature(EjbFactoryImpl.getPackage().getAssemblyDescriptor_SecurityRoles());
		}
		helper.setValue(role);
		helper.doUnsetValue();
		modifier.addHelper(helper);
	}

	protected void createMethodPermissionsRoleRemoveHelpers() {
		List roles, mps;
		roles = (List) operationDataModel.getProperty(RemoveSecurityRoleDataModel.ROLE_LIST);
		int size = roles.size();
		SecurityRole role = null;
		ModifierHelper helper;
		for (int i = 0; i < size; i++) {
			role = (SecurityRole) roles.get(i);
			mps = getMethodPermissions(role);
			int s2 = mps.size();
			MethodPermission mp = null;
			for (int j = 0; j < s2; j++) {
				mp = (MethodPermission) mps.get(j);
				helper = new ModifierHelper(mp, EjbFactoryImpl.getPackage().getMethodPermission_Roles(), role);
				helper.doUnsetValue();
				modifier.addHelper(helper);
			}
		}
	}

	protected List getMethodPermissions(SecurityRole aRole) {
		if (aRole == null)
			return Collections.EMPTY_LIST;
		List mps = ((AssemblyDescriptor) aRole.eContainer()).getMethodPermissions();
		if (mps.isEmpty())
			return Collections.EMPTY_LIST;
		int size = mps.size();
		List result = new ArrayList(size);
		MethodPermission mp = null;
		for (int i = 0; i < size; i++) {
			mp = (MethodPermission) mps.get(i);
			if (mp.getRoles().contains(aRole))
				result.add(mp);
		}
		return result;
	}


}