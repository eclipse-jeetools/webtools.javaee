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
package org.eclipse.jst.j2ee.navigator.internal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.ejb.provider.BeanClassProviderHelper;
import org.eclipse.jst.j2ee.internal.provider.MethodsProviderDelegate;
import org.eclipse.wst.common.internal.emfworkbench.integration.DynamicAdapterFactory;
import org.eclipse.wst.common.navigator.internal.views.CommonViewer;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

/**
 * <p>
 * The following class is experimental until fully documented.
 * </p>
 */
public class J2EEContentProvider implements ITreeContentProvider {

	private static final Class IPROJECT_CLASS = IProject.class;

	private final EMFRootObjectManager rootObjectManager;

	private AdapterFactoryContentProvider delegateContentProvider;
	private MethodsProviderDelegate delegateMethodsProvider;

	private String viewerId = null;

	/**
	 * 
	 */
	public J2EEContentProvider() {
		rootObjectManager = new EMFRootObjectManager();
	}

	/**
	 * 
	 */
	public J2EEContentProvider(String aViewerId) {
		rootObjectManager = new EMFRootObjectManager();
		updateContentProviders(aViewerId);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object anInputElement) {
		return getChildren(anInputElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object aParentElement) {
		IProject project = null;
		List children = new ArrayList();
		if (aParentElement instanceof IProject || aParentElement instanceof IJavaProject) {
			project = (IProject) ((IAdaptable) aParentElement).getAdapter(IPROJECT_CLASS);
			if (project != null) {
				Object rootObject = (rootObjectManager != null) ? rootObjectManager.getRootObject(project) : null;
				if (rootObject != null)
					children.add(rootObject);
			}
		} else if (MethodsProviderDelegate.providesContentFor(aParentElement))
			return delegateMethodsProvider.getChildren(aParentElement);
		else /* if (isEMFEditObject(aParentElement)) */{
			Object[] siblings = delegateContentProvider.getChildren(aParentElement);
			if (siblings != null)
				children.addAll(Arrays.asList(siblings));
		}
		return children.toArray();
	}

	public Object getParent(Object object) {
		if (MethodsProviderDelegate.providesContentFor(object))
			return delegateMethodsProvider.getParent(object);
		Object parent = delegateContentProvider.getParent(object);
		if (parent == null && object instanceof BeanClassProviderHelper)
			parent = ((BeanClassProviderHelper) object).getEjb();
		if (parent == null && object instanceof EObject)
			parent = ProjectUtilities.getProject((EObject) object);
		return parent;
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		delegateContentProvider.dispose();
		rootObjectManager.dispose();
		delegateMethodsProvider.dispose();
	}

	/*
	 * @see ITreeContentProvider#hasChildren(Object)
	 */
	public boolean hasChildren(Object element) {
		if (MethodsProviderDelegate.providesContentFor(element))
			return delegateMethodsProvider.hasChildren(element);
		/* else if (isEMFEditObject(element)) */
		return delegateContentProvider.hasChildren(element);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer aViewer, Object anOldInput, Object aNewInput) {
		String newViewerId = null;
		if (aViewer instanceof CommonViewer)
			newViewerId = ((CommonViewer) aViewer).getNavigatorContentService().getViewerId();

		if (newViewerId != null && (viewerId == null || !viewerId.equals(newViewerId)))
			updateContentProviders(newViewerId);

		delegateContentProvider.inputChanged(aViewer, anOldInput, aNewInput);
		delegateMethodsProvider.inputChanged(aViewer, anOldInput, aNewInput);
	}

	/**
	 * @param viewerId2
	 */
	private void updateContentProviders(String aViewerId) {

		/* Dispose of the existing content providers */
		if (delegateContentProvider != null)
			delegateContentProvider.dispose();
		if (delegateMethodsProvider != null)
			delegateMethodsProvider.dispose();

		/* Create new content providers using the new viewer id */
		DynamicAdapterFactory adapterFactory = new DynamicAdapterFactory(aViewerId);
		delegateContentProvider = new AdapterFactoryContentProvider(adapterFactory);
		delegateMethodsProvider = new MethodsProviderDelegate(adapterFactory);

		/* Remember the viewer id */
		viewerId = aViewerId;

	}

}
