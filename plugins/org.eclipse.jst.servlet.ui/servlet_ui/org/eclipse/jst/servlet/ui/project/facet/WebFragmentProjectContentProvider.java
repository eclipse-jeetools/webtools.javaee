/*******************************************************************************

 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.servlet.ui.project.facet;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jst.servlet.ui.internal.plugin.ServletUIPlugin;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.project.facet.core.FacetedProjectFramework;

public class WebFragmentProjectContentProvider implements IStructuredContentProvider {

	public void dispose() {
		// Do nothing
	}

	public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		// Do nothing
	}

	public Object[] getElements(Object arg0) {
		if (arg0 instanceof IWorkspaceRoot) {
			IProject[] projects = ((IWorkspaceRoot) arg0).getProjects();
			if (projects != null && projects.length != 0) {
				List<IProject> theProjects = new ArrayList<IProject>(0);
				for (int i = 0; i < projects.length; i++) {
					IProject project = projects[i];
					try {
						if (FacetedProjectFramework.hasProjectFacet(project, IModuleConstants.JST_WEBFRAGMENT_MODULE)) {
							theProjects.add(project);
						}
					} catch (CoreException e) {
						ServletUIPlugin.log(e);
					}
				}
				return theProjects.toArray();
			}
		}
		return new Object[0];
	}
}