/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.provider;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.ejb.provider.GroupedEJBJarItemProvider;


public class J2EEAdapterFactoryContentProvider extends AdapterFactoryContentProvider {
	protected MethodsProviderDelegate methodsProviderDelegate;
	protected static final Class ITreeItemContentProviderClass = ITreeItemContentProvider.class;
	protected List roots = new ArrayList();

	/**
	 * J2EEAdapterFactoryContentProvider constructor comment.
	 * 
	 * @param adapterFactory
	 *            org.eclipse.emf.common.notify.AdapterFactory
	 */
	public J2EEAdapterFactoryContentProvider(org.eclipse.emf.common.notify.AdapterFactory adapterFactory) {
		super(adapterFactory);
		methodsProviderDelegate = new MethodsProviderDelegate(adapterFactory);
	}

	public Object getParent(Object object) {

		if (MethodsProviderDelegate.providesContentFor(object))
			return methodsProviderDelegate.getParent(object);

		//Added so internalExpand() in AbstractTreeViewer knows that EJB's parent is now instance
		// of J2EEItemProvider
		//rather than EJBJar only if bean is root bean
		if (object instanceof EnterpriseBean && GroupedEJBJarItemProvider.isRootBean((EnterpriseBean) object)) {
			J2EEItemProvider provider = GroupedEJBJarItemProvider.getEJBItemProvider((EnterpriseBean) object);
			if (provider != null) {
				return provider;
			}
		}

		Object parent = super.getParent(object);

		//		if (parent == null) {
		//			if (object instanceof EObject)
		//				return J2EERoot.instance().groupFor((EObject) object);
		//		}
		return parent;
	}

	protected boolean isEMFEditObject(Object object) {
		ITreeItemContentProvider treeItemContentProvider = (ITreeItemContentProvider) adapterFactory.adapt(object, ITreeItemContentProviderClass);
		return treeItemContentProvider != null;
	}

	/*
	 * @see ITreeContentProvider#getChildren(Object)
	 */
	public Object[] getChildren(Object parentElement) {

		if (MethodsProviderDelegate.providesContentFor(parentElement))
			return methodsProviderDelegate.getChildren(parentElement);
		else if (isEMFEditObject(parentElement))
			return super.getChildren(parentElement);
		else
			return null;
	}

	/*
	 * @see IStructuredContentProvider#getElements(Object)
	 */
	public Object[] getElements(Object inputElement) {
		return getRoots(inputElement);
		//		else if (MethodsProviderDelegate.providesContentFor(inputElement))
		//			return methodsProviderDelegate.getChildren(inputElement);
		//		return super.getElements(inputElement);
	}

	public Object[] getRoots(Object parent) {
		if (roots.isEmpty()) {
			Object[] j2eeGroups = super.getChildren(parent);
			roots.addAll(Arrays.asList(j2eeGroups));
		}
		return roots.toArray();
	}

	/*
	 * @see ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		if (MethodsProviderDelegate.providesContentFor(element))
			return methodsProviderDelegate.hasChildren(element);
		else if (isEMFEditObject(element))
			return super.hasChildren(element);
		else
			return false;
	}

	/**
	 * @see IContentProvider#dispose()
	 */
	public void dispose() {
		super.dispose();
		if (methodsProviderDelegate != null)
			methodsProviderDelegate.dispose();
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(Viewer, Object, Object)
	 */
	public void inputChanged(Viewer aViewer, Object oldInput, Object newInput) {
		super.inputChanged(aViewer, oldInput, newInput);
		methodsProviderDelegate.inputChanged(aViewer, oldInput, newInput);
	}

}