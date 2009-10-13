/*******************************************************************************
 * Copyright (c) 2005, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.ejb.ui.internal.actions;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.actions.BaseAction;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientJARCreationConstants;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;



public abstract class AbstractClientJARAction extends BaseAction implements ClientJARCreationConstants {

	
	protected IProject getProject() {
		IProject project = null;
		Object element = selection.getFirstElement();
		
		if (element instanceof EJBJar) 
			project = ProjectUtilities.getProject((EJBJar) element);
		else if (element instanceof IProject)
			project = (IProject) element;
		else if (element instanceof IAdaptable)
			project = (IProject) ((IAdaptable)element).getAdapter(IProject.class);
		
		return project;
	}
	
	protected boolean hasClientJar() {
		IProject project = getProject();

		if (project != null && project.exists() && project.isAccessible()) {
			if (JavaEEProjectUtilities.isEJBProject(project)) {
				IModelProvider provider = ModelProviderManager.getModelProvider(project);
				Object mObj = provider.getModelObject();
				if (mObj != null && provider.getModelObject(new Path(J2EEConstants.EJBJAR_DD_URI)) != null) {
					if (mObj instanceof org.eclipse.jst.javaee.ejb.EJBJar) {
						org.eclipse.jst.javaee.ejb.EJBJar ejbjar = (org.eclipse.jst.javaee.ejb.EJBJar) mObj;
						if (ejbjar.getEjbClientJar() != null)
							return true;
					}
					else if (mObj instanceof org.eclipse.jst.j2ee.ejb.EJBJar) {
						org.eclipse.jst.j2ee.ejb.EJBJar ejbjar = (org.eclipse.jst.j2ee.ejb.EJBJar) mObj;
						if (ejbjar.getEjbClientJar() != null)
							return true;
					}
				}
			}
		}
		return false;
	}
}
