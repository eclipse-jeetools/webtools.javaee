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
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.navigator.internal.J2EEContentProvider;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.jee.ui.internal.navigator.appclient.GroupAppClientProvider;
import org.eclipse.jst.jee.ui.internal.navigator.ear.ModulesNode;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.swt.widgets.Display;

/**
 * Ear 5.0 Content provider is Deployment Descriptor content provider, 
 * used for constructing of the descriptor tree in project explorer. 
 * 
 * @author Dimitar Giormov
 */
public class AppClient5ContentProvider  extends J2EEContentProvider implements IResourceChangeListener, IResourceDeltaVisitor{

	
	private static final String DD_NAME = "application-client.xml"; //$NON-NLS-1$
	private static final Class IPROJECT_CLASS = IProject.class;
	private Viewer viewer;

	public Object[] getChildren(Object aParentElement) {
		IProject project = null;
		List children = new ArrayList();
		if (aParentElement instanceof GroupAppClientProvider) {
			children.addAll(((GroupAppClientProvider) aParentElement).getChildren());
		} else if (aParentElement instanceof IAdaptable) {
			project = (IProject) ((IAdaptable) aParentElement).getAdapter(IPROJECT_CLASS);
			if (project != null && JavaEEProjectUtilities.isApplicationClientProject(project) &&
					J2EEProjectUtilities.isJEEProject(project)) {
				GroupAppClientProvider element = new GroupAppClientProvider(project);
				children.add(element);
			}
		}
		return children.toArray();
	}

	public void inputChanged(Viewer aViewer, Object anOldInput, Object aNewInput) {
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
		viewer = aViewer;
	}

	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
	}

	public void resourceChanged(IResourceChangeEvent event) {
		try {
			event.getDelta().accept(this);
		} catch (CoreException e) {
			String msg = "Error in the JEEContentProvider.resourceChanged()"; //$NON-NLS-1$
			JEEUIPlugin.getDefault().logError(msg, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.core.resources.IResourceDeltaVisitor#visit(org.eclipse.core.resources.IResourceDelta)
	 */
	public boolean visit(IResourceDelta delta) {
		if (delta.getResource().getType() == IResource.FILE) {
			IResource resource = delta.getResource();
			if (DD_NAME.equals(resource.getName())) {
				Runnable refreshThread = new Runnable(){
					public void run(){
						if (viewer != null && ! viewer.getControl().isDisposed()){
							viewer.refresh();
						}
					}
				};
				Display.getDefault().asyncExec(refreshThread);
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof GroupAppClientProvider) {
			return !((GroupAppClientProvider) element).getChildren().isEmpty();
		} else 
			return false;
	}

	public Object getParent(Object object) {
		if (object instanceof ModulesNode){
			return ((ModulesNode) object).getEarProject(); 
		}
		return null;
	}
}
