/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
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
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.ejb.provider.BeanClassProviderHelper;
import org.eclipse.jst.j2ee.internal.ejb.provider.J2EEJavaClassProviderHelper;
import org.eclipse.jst.j2ee.internal.provider.MethodsProviderDelegate;
import org.eclipse.jst.j2ee.navigator.internal.EMFRootObjectProvider.IRefreshHandlerListener;
import org.eclipse.jst.j2ee.navigator.internal.plugin.J2EENavigatorPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.wst.common.internal.emfworkbench.integration.DynamicAdapterFactory;

/**
 * Root content provider for Java EE, delegates events to child providers
 */
public class J2EEContentProvider implements ITreeContentProvider, IRefreshHandlerListener {

	private final EMFRootObjectProvider rootObjectProvider;

	private AdapterFactoryContentProvider delegateContentProvider;
	private MethodsProviderDelegate delegateMethodsProvider;

	private String viewerId = null;
	private AbstractTreeViewer viewer;

	/**
	 *  
	 */
	public J2EEContentProvider() {
		rootObjectProvider = new EMFRootObjectProvider();
		rootObjectProvider.addRefreshHandlerListener(this);
	}

	/**
	 *  
	 */
	public J2EEContentProvider(String aViewerId) {
		rootObjectProvider = new EMFRootObjectProvider();
		updateContentProviders(aViewerId);
		rootObjectProvider.addRefreshHandlerListener(this);
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	@Override
	public Object[] getElements(Object anInputElement) {
		return getChildren(anInputElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#getChildren(java.lang.Object)
	 */
	@Override
	public Object[] getChildren(Object aParentElement) {
		IProject project = null;
		List children = new ArrayList();
		if (aParentElement instanceof IAdaptable && !(aParentElement instanceof EObject) && !(aParentElement instanceof ItemProvider)
				&& !(aParentElement instanceof J2EEJavaClassProviderHelper)) {
			project = ProjectUtilities.getProject(aParentElement);

			if (project != null) {
				// Verify if the model *is being*
				// loaded and just return the place holder
				if (LoadingDDNode.isBeingLoaded(project)) {
					children.add(LoadingDDNode.createPlaceHolder(project));

					// Verify if the model has been loaded, just return it
				} else if (rootObjectProvider.hasLoadedModels(project)) {
					/* model already loaded, just return the model */
					children.addAll(Arrays.asList(rootObjectProvider.getModels(project)));

					// Otherwise return a place holder node and start loading
					// job
				} else {
					LoadingDDNode placeHolder = LoadingDDNode.createPlaceHolder(project);
					/*
					 * we need to load the model; possible long running
					 * operation
					 */
					if (LoadingDDNode.canBeginLoading(project))
						new LoadingDDJob(viewer, placeHolder, (IAdaptable) aParentElement, rootObjectProvider).schedule();
					children.add(placeHolder);
				}

			}
		} else if (MethodsProviderDelegate.providesContentFor(aParentElement))
			return delegateMethodsProvider.getChildren(aParentElement);
		else /* if (isEMFEditObject(aParentElement)) */{
			Object[] siblings = null;
			try {
				siblings = delegateContentProvider.getChildren(aParentElement);
			} catch (Exception e) {
				J2EENavigatorPlugin.logError(0, e.getMessage(), e);			}
			if (siblings != null)
				children.addAll(Arrays.asList(siblings));
		}
		return children.toArray();
	}

	@Override
	public Object getParent(Object object) {
		if (MethodsProviderDelegate.providesContentFor(object))
			return delegateMethodsProvider.getParent(object);
		Object parent = delegateContentProvider.getParent(object);
		if(parent instanceof Resource) {
			parent = ProjectUtilities.getProject((EObject) object);
		} else if (parent == null) {
			if(object instanceof BeanClassProviderHelper)
				parent = ((BeanClassProviderHelper) object).getEjb();
			else if (object instanceof EObject) {
				parent = ProjectUtilities.getProject((EObject) object);	
			}
		} 
		return parent;
	}

	/**
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	@Override
	public void dispose() {
		rootObjectProvider.removeRefreshHandlerListener(this);
		delegateContentProvider.dispose();
		rootObjectProvider.dispose();
		delegateMethodsProvider.dispose();

	}

	/*
	 * @see ITreeContentProvider#hasChildren(Object)
	 */
	@Override
	public boolean hasChildren(Object element) {
		if (MethodsProviderDelegate.providesContentFor(element))
			return delegateMethodsProvider.hasChildren(element);
		/* else if (isEMFEditObject(element)) */
		if(element instanceof ItemProvider)
			return true;
		return delegateContentProvider.hasChildren(element); 

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
	 *      java.lang.Object, java.lang.Object)
	 */
	@Override
	public void inputChanged(Viewer aViewer, Object anOldInput, Object aNewInput) {
		String newViewerId = null;
		viewer = (AbstractTreeViewer) aViewer;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.navigator.internal.EMFRootObjectManager.IRefreshHandlerListener#onRefresh(java.lang.Object)
	 */
	@Override
	public void onRefresh(final Object element) {
		if (Display.getCurrent() != null) {
			(viewer).refresh(element, true);
		} else {
			/* Create and schedule a UI Job to update the Navigator Content Viewer */
			Job job = new UIJob("Update the Navigator Content Viewer Job") { //$NON-NLS-1$
				@Override
				public IStatus runInUIThread(IProgressMonitor monitor) {
					(viewer).refresh(element, true);
					return Status.OK_STATUS;
				}
			};
			ISchedulingRule rule = new ISchedulingRule() {
				@Override
				public boolean contains(ISchedulingRule rule) {
					return rule == this;	
				}
				@Override
				public boolean isConflicting(ISchedulingRule rule) {
					return rule == this;
				}
			};
			job.setRule(rule);
			job.schedule();
		}
	}

}
