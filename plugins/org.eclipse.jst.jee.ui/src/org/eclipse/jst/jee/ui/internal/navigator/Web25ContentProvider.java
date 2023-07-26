/***********************************************************************
 * Copyright (c) 2008, 2023 by SAP AG, Walldorf. 
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
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.javaee.web.WebApp;
import org.eclipse.jst.jee.ui.internal.navigator.web.WebAppProvider;

/**
 * Web 2.5 Content provider is Deployment Descriptor content provider, 
 * used for constructing of the descriptor tree in project explorer. 
 * 
 * @author Dimitar Giormov
 */
public class Web25ContentProvider extends JEE5ContentProvider {

	@Override
	public Object[] getChildren(Object aParentElement) {

		List<Object> children = new ArrayList<Object>();
		IProject project = null;
		if (IProject.class.isInstance(aParentElement)) {
			project = (IProject) aParentElement;
				AbstractGroupProvider cachedContentProvider = getCachedContentProvider(project);
				if (cachedContentProvider != null && cachedContentProvider.isValid()){
					children.add(cachedContentProvider);
				}
		} else if (AbstractGroupProvider.class.isInstance(aParentElement)){
			AbstractGroupProvider abstractGroupProvider = (AbstractGroupProvider) aParentElement;
			if (abstractGroupProvider.hasChildren()){
				children.addAll(abstractGroupProvider.getChildren());
			}
		}
		return children.toArray();
	}


	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof WebAppProvider) {
			return ((WebAppProvider)element).isValid();
		} else if (element instanceof AbstractGroupProvider) {
			return ((AbstractGroupProvider) element).hasChildren();
		} else
			return false;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}


	@Override
	protected AbstractGroupProvider getNewContentProviderInstance(IProject project) {
		return new WebAppProvider((WebApp) getCachedModelProvider(project).getModelObject(), project);
	}
}
