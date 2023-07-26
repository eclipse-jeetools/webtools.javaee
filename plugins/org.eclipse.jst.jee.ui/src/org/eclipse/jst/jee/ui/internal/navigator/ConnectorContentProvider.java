/*******************************************************************************
 * Copyright (c) 2010, 2023 IBM Corporation and others.
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal.navigator;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.javaee.jca.Connector;
import org.eclipse.jst.jee.ui.internal.navigator.ra.RaGroupContentProvider;

public class ConnectorContentProvider extends JEE5ContentProvider {

	@Override
	public Object[] getChildren(Object aParentElement) {
		List<Object> children = new ArrayList<Object>();
		IProject project = null;
		if (IProject.class.isInstance(aParentElement)) {
			project = (IProject) aParentElement;
				AbstractGroupProvider cachedContentProvider = getCachedContentProvider(project);
				if (cachedContentProvider!= null && cachedContentProvider.isValid()){
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
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		return true;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	@Override
	protected AbstractGroupProvider getNewContentProviderInstance(IProject project) {
		return new RaGroupContentProvider((Connector)getCachedModelProvider(project).getModelObject(), project);
	}

}
