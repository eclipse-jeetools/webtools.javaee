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
 * Created on Nov 24, 2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package org.eclipse.jst.j2ee.internal.ejb.project.operations;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.jst.j2ee.EjbModuleExtensionHelper;
import org.eclipse.jst.j2ee.IEJBModelExtenderManager;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EjbPackage;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodElement;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.util.MethodElementHelper;
import org.eclipse.wst.common.emf.utilities.ExtendedEcoreUtil;


/**
 * TODO: Revisit this, not sure if we need this class. We are not using it, but extensions might be
 * using it.
 * 
 * @author schacher
 * 
 * Helper class used for pushing down method element detail for inherited beans
 */
public class PushDownHelper {

	private static EClass META_ASSEMBLY_DESCRIPTOR = EjbPackage.eINSTANCE.getAssemblyDescriptor();
	public static final PushDownHelper INSTANCE = new PushDownHelper();

	public PushDownHelper() {
		super();
	}

	protected AssemblyDescriptor getAssemblyDescriptor(EnterpriseBean bean) {
		if (bean.getEjbJar() == null)
			return null;

		return bean.getEjbJar().getAssemblyDescriptor();
	}

	/**
	 * Push down the method elements from the
	 * 
	 * @originalSupertype to
	 * @aSubtype. Then obtain the generalizations for the subtype and continue pushing down using
	 *            the
	 * @originalSupertype.
	 */
	public void pushDownAssemblyData(EnterpriseBean originalSupertype, EnterpriseBean aSubtype) {
		AssemblyDescriptor descriptor = getAssemblyDescriptor(originalSupertype);
		if (descriptor == null)
			return;
		pushDownMethodElements(originalSupertype, aSubtype, descriptor);
		List generalizationsForSub = getSubtypes(aSubtype);
		int size = generalizationsForSub.size();
		EnterpriseBean sub;
		for (int i = 0; i < size; i++) {
			sub = (EnterpriseBean) generalizationsForSub.get(i);
			pushDownAssemblyData(originalSupertype, sub);
		}
	}

	protected AssemblyDescriptor getAssemblyDescriptor(MethodElement aMethodElement) {
		EObject container = aMethodElement;
		do {
			container = container.eContainer();
		} while (container != null && container.eClass() != META_ASSEMBLY_DESCRIPTOR);
		return (AssemblyDescriptor) container;
	}

	public boolean pushDownMethodElement(MethodElement aMethodElement) {

		EnterpriseBean bean = aMethodElement.getEnterpriseBean();
		List subtypes = getSubtypes(bean);
		if (subtypes != null && !subtypes.isEmpty()) {
			for (int i = 0; i < subtypes.size(); i++) {
				pushDownMethodElement(aMethodElement, (EnterpriseBean) subtypes.get(i));
			}
			return true;
		}
		return false;
	}

	//TODO: REVISIT THIS
	protected void pushDownMethodElement(MethodElement aMethodElement, EnterpriseBean subtype) {
		AssemblyDescriptor dd = getAssemblyDescriptor(aMethodElement);
		if (dd == null)
			return;
		pushDownMethodElement(aMethodElement, subtype, dd);
		List generalizationsForSub = getSubtypes(subtype);
		int size = generalizationsForSub.size();
		EnterpriseBean gen;
		for (int i = 0; i < size; i++) {
			gen = (EnterpriseBean) generalizationsForSub.get(i);
			pushDownMethodElement(aMethodElement, subtype);
		}
	}

	/**
	 * Remove the pushed down method elements from
	 * 
	 * @aSubtype that were pushed from the
	 * @originalSupertype. Then obtain the generalizations for the subtype and continue removing
	 *                     using the
	 * @originalSupertype.
	 */
	public void removePushedDownAssemblyData(EnterpriseBean originalSupertype, EnterpriseBean aSubtype) {
		AssemblyDescriptor dd = getAssemblyDescriptor(originalSupertype);
		if (dd == null)
			return;
		removePushedDownMethodElements(originalSupertype, aSubtype, dd);
		List generalizationsForSub = getSubtypes(aSubtype);
		int size = generalizationsForSub.size();
		EnterpriseBean gen;
		for (int i = 0; i < size; i++) {
			gen = (EnterpriseBean) generalizationsForSub.get(i);
			removePushedDownAssemblyData(originalSupertype, gen);
		}
	}

	protected void removePushedDownMethodElement(MethodElement aMethodElement) {
		EnterpriseBean bean = aMethodElement.getEnterpriseBean();
		List subtypes = getSubtypes(bean);
		for (int i = 0; i < subtypes.size(); i++) {
			removePushedDownMethodElement(aMethodElement, (EnterpriseBean) subtypes.get(i));
		}

	}

	protected void removePushedDownMethodElement(MethodElement aMethodElement, EnterpriseBean subtype) {
		AssemblyDescriptor dd = getAssemblyDescriptor(aMethodElement);
		if (dd == null)
			return;
		removePushedDownMethodElement(aMethodElement, subtype, dd);
		List generalizationsForSub = getSubtypes(subtype);
		int size = generalizationsForSub.size();
		EnterpriseBean gen;
		for (int i = 0; i < size; i++) {
			gen = (EnterpriseBean) generalizationsForSub.get(i);
			removePushedDownMethodElement(aMethodElement, gen);
		}
	}


	private List getSubtypes(EnterpriseBean bean) {
		if (bean == null)
			return Collections.EMPTY_LIST;
		EjbModuleExtensionHelper modHelper = IEJBModelExtenderManager.INSTANCE.getEJBModuleExtension(null);
		return modHelper == null ? Collections.EMPTY_LIST : modHelper.getSubtypes(bean);
	}

	protected void pushDownMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		if (aMethodElement == null || subEJB == null)
			return;
		int type = MethodElementHelper.getContainedType(aMethodElement);
		switch (type) {
			case MethodElementHelper.METHOD_PERMISSION :
				pushDownMethodPermissionMethodElement(aMethodElement, subEJB, ad);
			case MethodElementHelper.METHOD_TRANSACTION :
				pushDownMethodTransactionMethodElement(aMethodElement, subEJB, ad);
			case MethodElementHelper.EXCLUDE_LIST :
		//Should we do something here?
		}
	}

	protected void pushDownMethodElements(EnterpriseBean superEJB, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		if (superEJB == null || subEJB == null)
			return;
		pushDownMethodPermissionMethodElements(superEJB, subEJB, ad);
		pushDownMethodTransactionMethodElements(superEJB, subEJB, ad);
	}

	/**
	 * Push down
	 * 
	 * @aMethodElement to a
	 * @subEJB.
	 */
	protected void pushDownMethodPermissionMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		pushDownMethodPermissionMethodElement(aMethodElement, subEJB, ad.getMethodPermissionMethodElements(subEJB));
	}

	protected void pushDownMethodPermissionMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, List allMEs) {
		MethodElement subME;
		boolean containsME = false;
		for (int j = 0; j < allMEs.size(); j++) {
			subME = (MethodElement) allMEs.get(j);
			if (subME.equalSignature(aMethodElement) && ((MethodPermission) subME.eContainer()).isEquivalent((MethodPermission) aMethodElement.eContainer())) {
				containsME = true;
				break;
			}
		}
		if (!containsME)
			createMethodElement(aMethodElement, subEJB, EjbPackage.eINSTANCE.getMethodPermission_MethodElements());
	}

	protected void pushDownMethodPermissionMethodElements(EnterpriseBean superEJB, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		List subMethodElements = ad.getMethodPermissionMethodElements(subEJB);
		List superMethodElements = ad.getMethodPermissionMethodElements(superEJB);
		MethodElement superME;
		for (int i = 0; i < superMethodElements.size(); i++) {
			superME = (MethodElement) superMethodElements.get(i);
			pushDownMethodPermissionMethodElement(superME, subEJB, subMethodElements);
		}
	}

	/**
	 * Push down
	 * 
	 * @aMethodElement to a
	 * @subEJB.
	 */
	protected void pushDownMethodTransactionMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		pushDownMethodTransactionMethodElement(aMethodElement, subEJB, ad.getMethodTransactionMethodElements(subEJB));
	}

	protected void pushDownMethodTransactionMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, List allMEs) {
		MethodElement subME;
		boolean containsME = false;
		for (int j = 0; j < allMEs.size(); j++) {
			subME = (MethodElement) allMEs.get(j);
			if (subME.equalSignature(aMethodElement) && ((MethodTransaction) subME.eContainer()).isEquivalent((MethodTransaction) aMethodElement.eContainer())) {
				containsME = true;
				break;
			}
		}
		if (!containsME)
			createMethodElement(aMethodElement, subEJB, EjbPackage.eINSTANCE.getMethodTransaction_MethodElements());
	}

	protected void pushDownMethodTransactionMethodElements(EnterpriseBean superEJB, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		if (superEJB == null || subEJB == null)
			return;
		List subMethodElements = ad.getMethodTransactionMethodElements(subEJB);
		List superMethodElements = ad.getMethodTransactionMethodElements(superEJB);
		MethodElement superME;
		for (int i = 0; i < superMethodElements.size(); i++) {
			superME = (MethodElement) superMethodElements.get(i);
			pushDownMethodTransactionMethodElement(superME, subEJB, subMethodElements);
		}
	}

	protected void removePushedDownMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, AssemblyDescriptor dd) {
		if (aMethodElement == null || subEJB == null)
			return;
		int type = MethodElementHelper.getContainedType(aMethodElement);
		switch (type) {
			case MethodElementHelper.METHOD_PERMISSION :
				removePushedDownMethodPermissionMethodElement(aMethodElement, subEJB, dd);
			case MethodElementHelper.METHOD_TRANSACTION :
				removePushedDownMethodTransactionMethodElement(aMethodElement, subEJB, dd);
			case MethodElementHelper.EXCLUDE_LIST :
		//Should we do something here?
		}
	}

	protected void removePushedDownMethodElements(EnterpriseBean superEJB, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		removePushedDownMethodPermissionMethodElements(superEJB, subEJB, ad);
		removePushedDownMethodTransactionMethodElements(superEJB, subEJB, ad);
	}

	/**
	 * Remove the pushed down
	 * 
	 * @aMethodElement from a
	 * @subEJB.
	 */
	protected void removePushedDownMethodPermissionMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		removePushedDownMethodPermissionMethodElement(aMethodElement, subEJB, ad.getMethodPermissionMethodElements(subEJB));
	}

	protected void removePushedDownMethodPermissionMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, List allMEs) {
		MethodElement subME;
		for (int j = 0; j < allMEs.size(); j++) {
			subME = (MethodElement) allMEs.get(j);
			if (subME.equalSignature(aMethodElement) && ((MethodPermission) subME.eContainer()).isEquivalent((MethodPermission) aMethodElement.eContainer())) {
				allMEs.remove(j);
				MethodPermission mp = (MethodPermission) subME.eContainer();
				if (mp.getMethodElements().size() > 1)
					mp.getMethodElements().remove(subME);
				else
					mp.getAssemblyDescriptor().getMethodPermissions().remove(mp);
				break;
			}
		}
	}

	protected void removePushedDownMethodPermissionMethodElements(EnterpriseBean superEJB, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		if (superEJB == null || subEJB == null)
			return;
		List subMethodElements = ad.getMethodPermissionMethodElements(subEJB);
		List superMethodElements = ad.getMethodPermissionMethodElements(superEJB);
		MethodElement superME;
		for (int i = 0; i < superMethodElements.size(); i++) {
			superME = (MethodElement) superMethodElements.get(i);
			removePushedDownMethodPermissionMethodElement(superME, subEJB, subMethodElements);
		}
	}

	/**
	 * Remove the pushed down
	 * 
	 * @aMethodElement from a
	 * @subEJB.
	 */
	protected void removePushedDownMethodTransactionMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		removePushedDownMethodTransactionMethodElement(aMethodElement, subEJB, ad.getMethodTransactionMethodElements(subEJB));
	}

	protected void removePushedDownMethodTransactionMethodElement(MethodElement aMethodElement, EnterpriseBean subEJB, List allMEs) {
		MethodElement subME;
		for (int j = 0; j < allMEs.size(); j++) {
			subME = (MethodElement) allMEs.get(j);
			if (subME.equalSignature(aMethodElement) && ((MethodTransaction) subME.eContainer()).isEquivalent((MethodTransaction) aMethodElement.eContainer())) {
				allMEs.remove(j);
				MethodTransaction mt = (MethodTransaction) subME.eContainer();
				if (mt.getMethodElements().size() > 1)
					mt.getMethodElements().remove(subME);
				else
					mt.getAssemblyDescriptor().getMethodTransactions().remove(mt);
				break;
			}
		}
	}

	protected void removePushedDownMethodTransactionMethodElements(EnterpriseBean superEJB, EnterpriseBean subEJB, AssemblyDescriptor ad) {
		List subMethodElements = ad.getMethodTransactionMethodElements(subEJB);
		List superMethodElements = ad.getMethodTransactionMethodElements(superEJB);
		MethodElement superME;
		for (int i = 0; i < superMethodElements.size(); i++) {
			superME = (MethodElement) superMethodElements.get(i);
			removePushedDownMethodTransactionMethodElement(superME, subEJB, subMethodElements);
		}
	}

	/**
	 * Create a new MethodElement that is identical to
	 * 
	 * @originalMethodElement except that it points to
	 * @anEJB.
	 */
	protected MethodElement createMethodElement(MethodElement originalMethodElement, EnterpriseBean anEJB, EReference aContainerReference) {
		if (originalMethodElement == null)
			return null;
		MethodElement newME = EjbPackage.eINSTANCE.getEjbFactory().createMethodElement();
		newME.setName(originalMethodElement.getName());
		newME.setParms(originalMethodElement.getParms());
		newME.setType(originalMethodElement.getType());
		newME.setEnterpriseBean(anEJB);
		ExtendedEcoreUtil.eSetOrAdd(originalMethodElement.eContainer(), aContainerReference, newME);
		return newME;
	}



}