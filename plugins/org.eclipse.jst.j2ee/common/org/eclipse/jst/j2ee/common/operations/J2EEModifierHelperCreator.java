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
package org.eclipse.jst.j2ee.common.operations;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.common.CommonPackage;
import org.eclipse.jst.j2ee.common.SecurityRole;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.impl.EjbFactoryImpl;
import org.eclipse.wst.common.emf.utilities.EtoolsCopyUtility;
import org.eclipse.wst.common.emfworkbench.integration.ModifierHelper;


/**
 * Insert the type's description here. Creation date: (4/8/2001 10:13:52 PM)
 * 
 * @author: Administrator
 */
public class J2EEModifierHelperCreator {

	public static ModifierHelper createAssemblyDescriptorHelper(EJBJar anEJBJar) {
		if (anEJBJar == null || anEJBJar.getAssemblyDescriptor() != null)
			return null;
		EjbPackage pack = EjbFactoryImpl.getPackage();
		EStructuralFeature sf = pack.getEJBJar_AssemblyDescriptor();
		return new ModifierHelper(anEJBJar, sf, null);
	}

	/**
	 * return modifier helper that will create a method element with method type and param
	 * associated with the methodSig and the methodpermission will be derived from the security
	 * role.
	 */

	public static ModifierHelper createMethodElementAsCopyHelper(MethodElement element, SecurityRole role) {
		if (role == null)
			return null;
		ModifierHelper tempHelper = new ModifierHelper();
		setMethodPermissionOwnerHelper(tempHelper, role);
		tempHelper.setFeature(getEjbPackage().getMethodPermission_MethodElements());
		tempHelper.setValue(EtoolsCopyUtility.createCopy(element));
		return tempHelper;
	}

	/**
	 * return modifier helper that will create a method element with method type and param
	 * associated with the methodSig and the methodpermission will be derived from the security
	 * role.
	 */

	public static ModifierHelper createMethodElementHelper(EnterpriseBean bean, SecurityRole role, String methodType, String methodSig) {
		if (role == null)
			return null;
		ModifierHelper tempHelper = new ModifierHelper();
		setMethodPermissionOwnerHelper(tempHelper, role);
		tempHelper.setFeature(getEjbPackage().getMethodPermission_MethodElements());
		tempHelper.addAttribute(getEjbPackage().getMethodElement_Type(), methodType);
		int index = methodSig.indexOf("(");//$NON-NLS-1$
		String methodName = methodSig;
		String methodParms = null;
		if (index > -1) {
			methodName = methodSig.substring(0, index);
			methodParms = methodSig.substring(index + 1, methodSig.length() - 1);
		}
		if (methodParms != null)
			methodParms = methodParms.replace(',', ' ');
		tempHelper.addAttribute(getEjbPackage().getMethodElement_Name(), methodName);
		if (methodParms != null)
			tempHelper.addAttribute(getEjbPackage().getMethodElement_Parms(), methodParms);
		tempHelper.addAttribute(getEjbPackage().getMethodElement_EnterpriseBean(), bean);
		return tempHelper;
	}

	/**
	 * return modifier helper that will create a method element with method type and param
	 * associated with the methodSig and a new methodtransacton for a transtype.
	 */

	public static ModifierHelper createMethodElementHelper(EnterpriseBean bean, String methodType, String methodSig, String transType) {
		if (bean == null)
			return null;
		ModifierHelper ownerHelper = createMethodTransactionHelper((EJBJar) bean.eContainer(), transType);
		ModifierHelper tempHelper = new ModifierHelper();
		tempHelper.setOwnerHelper(ownerHelper);
		tempHelper.setFeature(getEjbPackage().getMethodTransaction_MethodElements());
		tempHelper.addAttribute(getEjbPackage().getMethodElement_Type(), methodType);
		int index = methodSig.indexOf("(");//$NON-NLS-1$
		String methodName = methodSig;
		String methodParms = null;
		if (index > -1) {
			methodName = methodSig.substring(0, index);
			methodParms = methodSig.substring(index + 1, methodSig.length() - 1);
		}
		if (methodParms != null)
			methodParms = methodParms.replace(',', ' ');
		tempHelper.addAttribute(getEjbPackage().getMethodElement_Name(), methodName);
		if (methodParms != null)
			tempHelper.addAttribute(getEjbPackage().getMethodElement_Parms(), methodParms);
		tempHelper.addAttribute(getEjbPackage().getMethodElement_EnterpriseBean(), bean);
		return tempHelper;
	}

	public static ModifierHelper createMethodPermissionHelper(SecurityRole role) {

		if (role == null)
			return null;

		AssemblyDescriptor assembly = (AssemblyDescriptor) role.eContainer();
		EStructuralFeature sf = EjbPackage.eINSTANCE.getAssemblyDescriptor_MethodPermissions();
		ModifierHelper tempHelper = new ModifierHelper(assembly, sf, null);
		tempHelper.addAttribute(getEjbPackage().getMethodPermission_Roles(), role);
		return tempHelper;
	}

	/**
	 * Create a helper that will create a new MethodTransaction with
	 * 
	 * @aTransType.
	 */
	public static ModifierHelper createMethodTransactionHelper(EJBJar anEJBJar, String aTransType) {
		if (anEJBJar == null)
			return null;
		ModifierHelper helper = new ModifierHelper();
		setAssemblyDescriptorOwnerHelper(helper, anEJBJar);
		helper.setFeature(getEjbPackage().getAssemblyDescriptor_MethodTransactions());
		helper.addAttribute(getEjbPackage().getMethodTransaction_TransactionAttribute(), aTransType);
		return helper;
	}



	protected static CommonPackage getCommonPackage() {
		return CommonPackage.eINSTANCE;
	}

	protected static EjbPackage getEjbPackage() {
		return EjbFactoryImpl.getPackage();
	}

	/**
	 * Set the owner of
	 * 
	 * @helper to be the existing AssemblyDescriptor from
	 * @anEJBJar or set the ownerHelper of
	 * @helper for the creation of a new AssemblyDescriptor when one does not exist.
	 */
	public static void setAssemblyDescriptorOwnerHelper(ModifierHelper helper, EJBJar anEJBJar) {
		ModifierHelper ownerHelper = J2EEModifierHelperCreator.createAssemblyDescriptorHelper(anEJBJar);
		if (ownerHelper == null)
			helper.setOwner(anEJBJar.getAssemblyDescriptor());
		else
			helper.setOwnerHelper(ownerHelper);
	}

	/**
	 * Set the owner of
	 * 
	 * @helper to be the existing AssemblyDescriptor from
	 * @anEJBJar or set the ownerHelper of
	 * @helper for the creation of a new AssemblyDescriptor when one does not exist.
	 */
	public static void setMethodPermissionOwnerHelper(ModifierHelper helper, SecurityRole role) {
		List roleList = new ArrayList(1);
		roleList.add(role);
		MethodPermission permission = ((AssemblyDescriptor) role.eContainer()).getMethodPermission(roleList);
		ModifierHelper ownerHelper = null;
		if (permission == null)
			ownerHelper = J2EEModifierHelperCreator.createMethodPermissionHelper(role);

		if (ownerHelper == null)
			helper.setOwner(permission);
		else
			helper.setOwnerHelper(ownerHelper);
	}



}