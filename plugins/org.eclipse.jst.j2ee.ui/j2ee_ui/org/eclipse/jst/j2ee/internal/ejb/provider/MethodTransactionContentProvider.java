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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.j2ee.ejb.AssemblyDescriptor;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.internal.impl.EjbFactoryImpl;


public class MethodTransactionContentProvider extends AbstractMethodsContentProvider {
	private static final EStructuralFeature TRANSACTION_MES_SF = EjbFactoryImpl.getPackage().getMethodTransaction_MethodElements();
	private static final EStructuralFeature AD_MT_SF = EjbFactoryImpl.getPackage().getAssemblyDescriptor_MethodTransactions();

	/**
	 * Constructor for MethodTransactionContentProvider.
	 * 
	 * @param adapterFactory
	 * @param root
	 * @param viewer
	 */
	public MethodTransactionContentProvider(AdapterFactory adapterFactory, boolean root) {
		super(adapterFactory, root);
	}

	/**
	 * Constructor for MethodTransationContentProvider.
	 * 
	 * @param adapterFactory
	 */
	public MethodTransactionContentProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/*
	 * @see ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof MethodTransaction)
			return getEnterpriseBeans((MethodTransaction) parentElement);
		return super.getChildren(parentElement);
	}

	protected Object[] getEnterpriseBeans(MethodTransaction mt) {
		List mes = mt.getMethodElements();
		return getEnterpriseBeans(mt, mes);
	}

	/*
	 * @see AbstractMethodsContentProvider#getMethodElements(EObject, EnterpriseBean)
	 */
	protected List getMethodElements(EObject aRefObject, EnterpriseBean anEJB) {
		if (!(aRefObject instanceof MethodTransaction))
			return Collections.EMPTY_LIST;
		MethodTransaction parentMT = (MethodTransaction) aRefObject;
		if (parentMT == null)
			return Collections.EMPTY_LIST;
		return parentMT.getMethodElements(anEJB);
	}

	/*
	 * @see IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		super.getElements(inputElement);
		if (inputElement instanceof EJBJar) {
			EJBJar jar = (EJBJar) inputElement;
			if (jar.getAssemblyDescriptor() != null)
				return jar.getAssemblyDescriptor().getMethodTransactions().toArray();
		}
		if (inputElement instanceof AssemblyDescriptor)
			return ((AssemblyDescriptor) inputElement).getMethodTransactions().toArray();
		return new Object[0];
	}

	/*
	 * @see ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		if (element instanceof MethodTransaction)
			return !((MethodTransaction) element).getMethodElements().isEmpty();
		return super.hasChildren(element);
	}

	/*
	 * @see AbstractMethodsContentProvider#getMethodElementsReference()
	 */
	protected EStructuralFeature getMethodElementsReference() {
		return TRANSACTION_MES_SF;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.ejb.ui.providers.AbstractMethodsContentProvider#getMethodElementsContainerReference()
	 */
	protected EStructuralFeature getMethodElementsContainerReference() {
		return AD_MT_SF;
	}



}
