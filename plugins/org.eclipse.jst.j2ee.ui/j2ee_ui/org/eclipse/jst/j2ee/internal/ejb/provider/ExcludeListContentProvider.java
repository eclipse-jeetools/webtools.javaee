/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.provider;


import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.ExcludeList;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbFactoryImpl;


public class ExcludeListContentProvider extends AbstractMethodsContentProvider {
	private static final EStructuralFeature EXCLUDE_ME_SF = EjbFactoryImpl.getPackage().getExcludeList_MethodElements();
	private static final EStructuralFeature AD_EL_SF = EjbFactoryImpl.getPackage().getAssemblyDescriptor_ExcludeList();
	protected static final EStructuralFeature ECORE_BEAN_NAME = EcorePackage.eINSTANCE.getENamedElement_Name(); //ENAMED_ELEMENT__NAME

	/**
	 * Constructor for ExcludeListContentProvider.
	 * 
	 * @param adapterFactory
	 * @param root
	 * @param viewer
	 */
	public ExcludeListContentProvider(AdapterFactory adapterFactory, boolean root) {
		super(adapterFactory, root);
	}

	/**
	 * Constructor for ExcludesListContentProvider.
	 * 
	 * @param adapterFactory
	 */
	public ExcludeListContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	protected Object[] getEnterpriseBeans(ExcludeList list) {
		List mes = list.getMethodElements();
		return getEnterpriseBeans(list, mes);
	}

	/*
	 * @see AbstractMethodsContentProvider#getMethodElements(EObject, EnterpriseBean)
	 */
	protected List getMethodElements(EObject aRefObject, EnterpriseBean anEJB) {
		if (!(aRefObject instanceof ExcludeList))
			return Collections.EMPTY_LIST;
		ExcludeList parentList = (ExcludeList) aRefObject;
		if (parentList == null)
			return Collections.EMPTY_LIST;
		return parentList.getMethodElements(anEJB);
	}

	/*
	 * @see IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		super.getElements(inputElement);
		ExcludeList list = null;
		if (inputElement instanceof EJBJar) {
			EJBJar jar = (EJBJar) inputElement;
			if (jar.getAssemblyDescriptor() != null)
				list = jar.getAssemblyDescriptor().getExcludeList();
			if (list != null)
				return getEnterpriseBeans(list);
		}
		if (inputElement instanceof AssemblyDescriptor) {
			list = ((AssemblyDescriptor) inputElement).getExcludeList();
			if (list != null)
				return getEnterpriseBeans(list);
		}
		return new Object[0];
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.ui.providers.AbstractMethodsContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ExcludeList)
			return getEnterpriseBeans((ExcludeList) parentElement);
		return super.getChildren(parentElement);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.ui.providers.AbstractMethodsContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof ExcludeList)
			return !((ExcludeList) element).getMethodElements().isEmpty();
		return super.hasChildren(element);
	}

	public void notifyChanged(Notification notifier) {
		if ((notifier.getFeature() == EXCLUDE_ME_SF && notifier.getNotifier() instanceof ExcludeList) || notifier.getFeature() == ECORE_BEAN_NAME) {
			if (notifier.getEventType() == Notification.RESOLVE)
				return;
			if (isRoot)
				refreshTreeViewer();
			else
				refreshTreeViewer(notifier.getNotifier());
		}
		if (notifier.getOldValue() instanceof CMPAttribute && notifier.getNewValue() == null)
			return;
		if (notifier.getFeature() == getMethodElementsReference() || notifier.getFeature() == getMethodElementsContainerReference())
			super.notifyChanged(notifier);
	}

	/*
	 * @see AbstractMethodsContentProvider#getMethodElementsReference()
	 */
	protected EStructuralFeature getMethodElementsReference() {
		return EXCLUDE_ME_SF;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.ui.providers.AbstractMethodsContentProvider#getMethodElementsContainerReference()
	 */
	protected EStructuralFeature getMethodElementsContainerReference() {
		return AD_EL_SF;
	}

}
