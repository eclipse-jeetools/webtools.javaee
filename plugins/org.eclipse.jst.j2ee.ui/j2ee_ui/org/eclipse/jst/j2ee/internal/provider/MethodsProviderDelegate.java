/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;


import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.ejb.ExcludeList;
import org.eclipse.jst.j2ee.ejb.MethodPermission;
import org.eclipse.jst.j2ee.ejb.MethodTransaction;
import org.eclipse.jst.j2ee.ejb.impl.EjbFactoryImpl;
import org.eclipse.jst.j2ee.internal.ejb.provider.AbstractMethodsContentProvider;
import org.eclipse.jst.j2ee.internal.ejb.provider.ExcludeListContentProvider;
import org.eclipse.jst.j2ee.internal.ejb.provider.MethodPermissionsContentProvider;
import org.eclipse.jst.j2ee.internal.ejb.provider.MethodTransactionContentProvider;


public class MethodsProviderDelegate implements ITreeContentProvider {

	protected ExcludeListContentProvider excludeListContentProvider;
	protected MethodPermissionsContentProvider methodPermissionsContentProvider;
	protected MethodTransactionContentProvider methodTransactionContentProvider;

	protected static EObject EL_META = EjbFactoryImpl.getPackage().getExcludeList();
	protected static EObject MP_META = EjbFactoryImpl.getPackage().getMethodPermission();
	protected static EObject MT_META = EjbFactoryImpl.getPackage().getMethodTransaction();

	public static boolean providesContentFor(Object object) {
		return object instanceof AbstractMethodsContentProvider.EJBMethodItem || object instanceof MethodPermission || object instanceof MethodTransaction || object instanceof ExcludeList;
	}

	public MethodsProviderDelegate(AdapterFactory adapterFactory) {
		super();
		excludeListContentProvider = new ExcludeListContentProvider(adapterFactory, false);
		methodPermissionsContentProvider = new MethodPermissionsContentProvider(adapterFactory, false);
		methodTransactionContentProvider = new MethodTransactionContentProvider(adapterFactory, false);
	}

	/**
	 * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
	 */
	public void dispose() {
		if (excludeListContentProvider != null)
			excludeListContentProvider.dispose();
		if (methodPermissionsContentProvider != null)
			methodPermissionsContentProvider.dispose();
		if (methodTransactionContentProvider != null)
			methodTransactionContentProvider.dispose();
	}

	public AbstractMethodsContentProvider getContentProvider(Object object) {
		EObject metaClass = null;
		if (object instanceof AbstractMethodsContentProvider.EJBMethodItem)
			metaClass = ((AbstractMethodsContentProvider.EJBMethodItem) object).refObject.eClass();
		else
			metaClass = ((EObject) object).eClass();

		if (metaClass == EL_META)
			return excludeListContentProvider;
		else if (metaClass == MP_META)
			return methodPermissionsContentProvider;
		else if (metaClass == MT_META)
			return methodTransactionContentProvider;

		return null;
	}


	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(Viewer, Object, Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		excludeListContentProvider.inputChanged(viewer, oldInput, newInput);
		methodPermissionsContentProvider.inputChanged(viewer, oldInput, newInput);
		methodTransactionContentProvider.inputChanged(viewer, oldInput, newInput);
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {
		AbstractMethodsContentProvider prov = getContentProvider(parentElement);
		return prov == null ? null : prov.getChildren(parentElement);
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(Object)
	 */
	public Object getParent(Object element) {
		AbstractMethodsContentProvider prov = getContentProvider(element);
		return prov == null ? null : prov.getParent(element);
	}

	/**
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		AbstractMethodsContentProvider prov = getContentProvider(element);
		return prov != null && prov.hasChildren(element);
	}

	/**
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		AbstractMethodsContentProvider prov = getContentProvider(inputElement);
		return prov == null ? null : prov.getElements(inputElement);
	}
}