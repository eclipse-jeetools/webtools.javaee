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
 * Created on Nov 21, 2003
 * 
 * To change the template for this generated file go to Window - Preferences - Java - Code Generation - Code and
 * Comments
 */
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.ejb.provider.BeanClassProviderHelper;
import org.eclipse.jst.j2ee.internal.provider.MethodsProviderDelegate;
import org.eclipse.wst.common.navigator.internal.providers.CommonAdapterFactoryContentProvider;
import org.eclipse.wst.common.navigator.views.INavigatorContentExtension;
import org.eclipse.wst.common.navigator.views.INavigatorContentProvider;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * @author
 */
public class J2EENavigationContentProvider extends CommonAdapterFactoryContentProvider implements INavigatorContentProvider {

	protected MethodsProviderDelegate methodsProviderDelegate;
	protected static final Class ITreeItemContentProviderClass = ITreeItemContentProvider.class;

	/**
	 * J2EEAdapterFactoryContentProvider constructor comment.
	 * 
	 * @param adapterFactory
	 *            org.eclipse.emf.common.notify.AdapterFactory
	 */
	public J2EENavigationContentProvider(AdapterFactory factory, INavigatorContentExtension containingExtension) {
		super(factory, containingExtension);
		methodsProviderDelegate = new MethodsProviderDelegate(adapterFactory);
	}

	public Object getParent(Object object) {

		if (MethodsProviderDelegate.providesContentFor(object))
			return methodsProviderDelegate.getParent(object);

		Object parent = super.getParent(object);

		if (parent == null && object instanceof BeanClassProviderHelper)
			parent = ((BeanClassProviderHelper) object).getEjb();
		if (parent == null && object instanceof EObject)
			parent = ProjectUtilities.getProject((EObject) object);


		return parent;
	}

	protected boolean isEMFEditObject(Object object) {
		ITreeItemContentProvider treeItemContentProvider = (ITreeItemContentProvider) adapterFactory.adapt(object, ITreeItemContentProviderClass);
		return treeItemContentProvider != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.navigator.internal.views.navigator.INavigatorContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {
		List children = new ArrayList();
		if (parentElement instanceof IProject) {
			IProject project = (IProject) parentElement;

			Object rootObject = (getRootObjectManager() != null) ? getRootObjectManager().getRootObject(project) : null;
			if (rootObject != null)
				children.add(rootObject);

		} else if (MethodsProviderDelegate.providesContentFor(parentElement))
			return methodsProviderDelegate.getChildren(parentElement);
		else if (isEMFEditObject(parentElement)) {
			Object[] siblings = super.getChildren(parentElement);
			if (siblings != null)
				children.addAll(Arrays.asList(siblings));
		}
		return children.toArray();
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
		getRootObjectManager().dispose();
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

	/**
	 * @return Returns the rootObjectManager.
	 */
	protected J2EERootObjectManager getRootObjectManager() {
		return ((J2EENavigatorContentExtension) getContainingExtension()).getRootObjectManager();
	}

}
