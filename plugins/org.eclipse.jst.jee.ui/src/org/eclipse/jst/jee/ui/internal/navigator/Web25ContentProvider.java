/***********************************************************************
 * Copyright (c) 2008 by SAP AG, Walldorf. 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     SAP AG - initial API and implementation
 ***********************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.IModelProviderEvent;
import org.eclipse.jst.j2ee.model.IModelProviderListener;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.navigator.internal.J2EEContentProvider;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.swt.widgets.Display;

/**
 * Web 2.5 Content provider is Deployment Descriptor content provider, 
 * used for constructing of the descriptor tree in project explorer. 
 * 
 * @author Dimitar Giormov
 */
public class Web25ContentProvider extends J2EEContentProvider implements IModelProviderListener {

	private static final Class IPROJECT_CLASS = IProject.class;

	private Viewer viewer;

	private static HashMap<IProject, IModelProvider> groupProvidersMap = new HashMap<IProject, IModelProvider>();

	@Override
	public Object[] getChildren(Object aParentElement) {

		List<Object> children = new ArrayList<Object>();
		IProject project = null;
		if (aParentElement instanceof IAdaptable) {
			project = (IProject) ((IAdaptable) aParentElement).getAdapter(IPROJECT_CLASS);
			if (project != null) {
			         IModelProvider provider = groupProvidersMap.get(project);
					if (provider != null){
					    Object mObj = provider.getModelObject();
						children.add(new WebAppProvider((WebApp) mObj,project));
					} else{
						provider = ModelProviderManager.getModelProvider(project);
						provider.addListener(this);
						Object mObj = provider.getModelObject();
						WebAppProvider webProvider = new WebAppProvider((WebApp) mObj,project);
						children.add(webProvider);
						groupProvidersMap.put(project,provider);
					}
			}
		} else if (aParentElement instanceof WebAppProvider){
			children.addAll(((WebAppProvider) aParentElement).getChildren());
		} else if (aParentElement instanceof AbstractGroupProvider){
			children.addAll(((AbstractGroupProvider) aParentElement).getChildren());
		}
		return children.toArray();
	}

	public void inputChanged(Viewer aViewer, Object anOldInput, Object aNewInput) {
		viewer = aViewer;
	}

	@Override
	public void dispose() {
		groupProvidersMap.clear();
		
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof WebAppProvider) {
			return true;
		} else if (element instanceof AbstractGroupProvider) {
			return ((AbstractGroupProvider) element).hasChildren();
		} else
			return false;
	}

	private void j2eeRefreshContent() {
		try {
			Runnable refreshThread = new Runnable() {
				public void run() {
					viewer.refresh();
				}
			};
			Display.getDefault().asyncExec(refreshThread);
		} catch (Exception e) {
			 JEEUIPlugin.logError("Error during refresh", e); //$NON-NLS-1$
		}
	}

	public void projectChanged(final IProject project) {
		// TODO refresh only the Deployment Description tree of the affected 
		// project instead of the DD tree of all projects. 
//		j2eeRefreshContent();

		try
		{
			Runnable refreshThread = new Runnable()
			{
				public void run()
				{
				  if (viewer != null) {
		            ISelection sel = ((TreeViewer) viewer).getSelection();
		            ITreeContentProvider contentProvider = ((ITreeContentProvider) ((TreeViewer) viewer)
		                .getContentProvider());
		            contentProvider.getChildren(project);
		            ((StructuredViewer) viewer).refresh(project);
		            ((TreeViewer) viewer).setSelection(sel);
		          }
		        }
			};
			Display.getDefault().asyncExec(refreshThread);
		} catch (Exception e)
		{
			JEEUIPlugin.logError("Error during refresh", e); //$NON-NLS-1$
		}
	}

  public void modelsChanged(IModelProviderEvent event) {
    projectChanged(event.getProject());
    
  }
}
