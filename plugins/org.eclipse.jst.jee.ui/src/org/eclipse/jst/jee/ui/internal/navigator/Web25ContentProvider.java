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
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;

/**
 * Web 2.5 Content provider is Deployment Descriptor content provider, 
 * used for constructing of the descriptor tree in project explorer. 
 * 
 * @author Dimitar Giormov
 */
public class Web25ContentProvider extends JEE5ContentProvider {

	public Object[] getChildren(Object aParentElement) {

		List<Object> children = new ArrayList<Object>();
		IProject project = null;
		if (aParentElement instanceof IAdaptable) {
			project = (IProject) ((IAdaptable) aParentElement).getAdapter(IPROJECT_CLASS);
			if (project != null) {
				AbstractGroupProvider cachedContentProvider = getCachedContentProvider(project);
				if (cachedContentProvider.isValid()){
					children.add(cachedContentProvider);
				}
			}
		} else if (aParentElement instanceof WebAppProvider){
			children.addAll(((WebAppProvider) aParentElement).getChildren());
		} else if (aParentElement instanceof AbstractGroupProvider){
			children.addAll(((AbstractGroupProvider) aParentElement).getChildren());
		}
		return children.toArray();
	}


	public boolean hasChildren(Object element) {
		if (element instanceof WebAppProvider) {
			return ((WebAppProvider)element).isValid();
		} else if (element instanceof AbstractGroupProvider) {
			return ((AbstractGroupProvider) element).hasChildren();
		} else
			return false;
	}

	public Object getParent(Object element) {
		return null;
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}


	@Override
	protected AbstractGroupProvider getNewContentProviderInstance(IProject project) {
		return new WebAppProvider((WebApp) getCachedModelProvider(project).getModelObject(), project);
	}
}
