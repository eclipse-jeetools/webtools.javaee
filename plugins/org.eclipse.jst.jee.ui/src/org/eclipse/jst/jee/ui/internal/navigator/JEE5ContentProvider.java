/***********************************************************************
 * Copyright (c) 2008, 2025 by SAP AG, Walldorf, and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.j2ee.model.IModelProviderListener;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.navigator.internal.EMFRootObjectProvider.IRefreshHandlerListener;
import org.eclipse.jst.j2ee.navigator.internal.LoadingDDNode;
import org.eclipse.jst.j2ee.navigator.internal.NonConflictingRule;
import org.eclipse.jst.javaee.core.JavaEEObject;
import org.eclipse.jst.javaee.ejb.EntityBean;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;

public abstract class JEE5ContentProvider implements ITreeContentProvider, IRefreshHandlerListener, IModelProviderListener, IResourceChangeListener {

	protected Viewer viewer;
	protected static final Class IPROJECT_CLASS = IProject.class;

	protected static Map<IProject, IModelProvider> groupProvidersMap = new HashMap<IProject, IModelProvider>();

	protected static Map<IProject, AbstractGroupProvider> groupContentProviders = new HashMap<IProject, AbstractGroupProvider>();


	public JEE5ContentProvider() {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this);
	}

	@Override
	public void inputChanged(Viewer aViewer, Object anOldInput, Object aNewInput) {
		viewer = aViewer;
	}


	protected IModelProvider getCachedModelProvider(IProject project) {
		IModelProvider provider = groupProvidersMap.get(project);
		if (provider == null){
			provider = ModelProviderManager.getModelProvider(project);
			provider.addListener(this);
			groupProvidersMap.put(project,provider);
		}
		return provider;
	}

	protected AbstractGroupProvider getCachedContentProvider(IProject project) {
		AbstractGroupProvider provider = groupContentProviders.get(project);
		if (provider == null){
			if (project == null || !project.isAccessible() || !project.isSynchronized(IResource.DEPTH_INFINITE)){
				return null;
			}
			// if the project is no longer a valid flexible project (due to deletion of critical files)
			if (!ModuleCoreNature.isFlexibleProject(project)){
				return null;
			}
			provider = getLoadingNode(project); 
//					getNewContentProviderInstance(project);
//			groupContentProviders.put(project, provider);
		} else {
			try {
				Object modelObject = ModelProviderManager.getModelProvider(project).getModelObject();
				if (provider.getJavaEEObject() != modelObject){
					if (modelObject == null){
						provider.setValid(false);
						return provider;
					}
	
					provider.reinit((JavaEEObject)modelObject);
				}
			} catch(IllegalStateException ise) {
				return null;
			}
		}
		return provider;
	}

	protected  abstract AbstractGroupProvider getNewContentProviderInstance(IProject project); 
	
	private AbstractGroupProvider getLoadingNode(IProject project){
		LoadingDDNode placeHolder = LoadingDDNode.createPlaceHolder(project);
		LoadingGroupProvider provider = new LoadingGroupProvider(placeHolder);
		/*
		 * we need to load the model; possible long running
		 * operation
		 */
		if (LoadingDDNode.canBeginLoading(project))
			new LoadingJeeDDJob(viewer, provider, project, this).schedule();
		
		return provider;
	}


	public void projectChanged(final IProject project) {
		try {
			Runnable refreshThread = new Runnable() {
				@Override
				public void run() {
					if (viewer != null) {
						try{
							
							if( viewer.getControl() != null &&  !viewer.getControl().isDisposed() ){
								viewer.getControl().setRedraw(false);
								ISelection sel = ((TreeViewer) viewer).getSelection();
								ITreeContentProvider contentProvider = ((ITreeContentProvider) ((TreeViewer) viewer)
										.getContentProvider());
								contentProvider.getChildren(project);
								Object[] expandedElements = ((TreeViewer) viewer).getExpandedElements();
								
								((StructuredViewer) viewer).refresh(project);
								viewer.setSelection(sel);
								
								expandElements(expandedElements);
							}
							
						}finally{
							if( viewer.getControl() != null &&  !viewer.getControl().isDisposed() ){
								viewer.getControl().setRedraw(true);
							}
						}
					}
				}

				private void expandElements(Object[] expandedElements) {
					ArrayList<Object> newExpandedElements = new ArrayList<Object>();
					
					ArrayList<Object> allElements = new ArrayList<Object>();
					getViewerElements(allElements, ((TreeViewer) viewer).getControl());
					
					Object[] expandedElementsAfterRefresh = ((TreeViewer) viewer).getExpandedElements();
					newExpandedElements.addAll(Arrays.asList(expandedElementsAfterRefresh));
					for(int i=0;i < expandedElements.length;i++){
						boolean expanded = false;
						for(int j=0;j < expandedElementsAfterRefresh.length; j++){
							if(expandedElements[i].equals(expandedElementsAfterRefresh[j])){
								expanded = true;
								break;
							}
						}
						if(expanded){
							for(Object ob : allElements){
								if(ob instanceof SessionBean
										&& expandedElements[i] instanceof SessionBean){
									SessionBean bean = (SessionBean) ob;
									SessionBean bean2 = (SessionBean) expandedElements[i];
									if(bean.getEjbName().equals(bean2.getEjbName())){
										newExpandedElements.add(ob);
									}
								}
								if(ob instanceof MessageDrivenBean
										&& expandedElements[i] instanceof MessageDrivenBean){
									MessageDrivenBean bean = (MessageDrivenBean) ob;
									MessageDrivenBean bean2 = (MessageDrivenBean) expandedElements[i];
									if(bean.getEjbName().equals(bean2.getEjbName())){
										newExpandedElements.add(ob);
									}
								}
								if(ob instanceof EntityBean
										&& expandedElements[i] instanceof EntityBean){
									EntityBean bean = (EntityBean) ob;
									EntityBean bean2 = (EntityBean) expandedElements[i];
									if(bean.getEjbName().equals(bean2.getEjbName())){
										newExpandedElements.add(ob);
									}
								}
							}
						}
					}
					((TreeViewer) viewer).setExpandedElements(newExpandedElements.toArray());
				}
			};
			Display.getDefault().asyncExec(refreshThread);
		} catch (Exception e)
		{
			JEEUIPlugin.logError("Error during refresh", e); //$NON-NLS-1$
		}
	}

	private void getViewerElements(List result, Widget widget) {
		Item[] items = getChildren(widget);
		for (int i = 0; i < items.length; i++) {
			Item item = items[i];
			result.add(item.getData());
			getViewerElements(result, item);
		}
	}
	
	protected Item[] getChildren(Widget o) {
		if (o instanceof TreeItem) {
			return ((TreeItem) o).getItems();
		}
		if (o instanceof Tree) {
			return ((Tree) o).getItems();
		}
		return null;
	}
	
	@Override
	public void modelsChanged(IModelProviderEvent event) {
		projectChanged(event.getProject());

	}

	@Override
	public void onRefresh(final Object element) {
		if (viewer instanceof AbstractTreeViewer) {
			if (Display.getCurrent() != null) {
				((AbstractTreeViewer) viewer).refresh(element, true);
			} else {
				/* Create and schedule a UI Job to update the Navigator Content Viewer */
				Job job = new UIJob("Update the Navigator Content Viewer Job") { //$NON-NLS-1$
					@Override
					public IStatus runInUIThread(IProgressMonitor monitor) {
						((AbstractTreeViewer) viewer).refresh(element, true);
						return Status.OK_STATUS;
					}
				};
				job.setRule(new NonConflictingRule());
				job.schedule();
			}
		}
	}

	@Override
	public void dispose() {
		groupProvidersMap.clear();
		groupContentProviders.clear();
	}

	@Override
	public void resourceChanged(IResourceChangeEvent event) {
		if (event.getType() == IResourceChangeEvent.PRE_DELETE && event.getResource() != null && event.getResource().getType() == IResource.PROJECT){
			groupContentProviders.remove(event.getResource());	
			groupProvidersMap.remove(event.getResource());		
		}

	}

	protected void registerProvider(IProject project, AbstractGroupProvider provider) {
		groupContentProviders.put(project, provider);
		
	}

}
