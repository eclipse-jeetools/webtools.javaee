/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
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
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.actions.BaseAction;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientJARCreationConstants;



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
	
	protected boolean  hasClientJar() {
		IProject project = getProject();
		if( project.exists() && project.isAccessible()){
			EJBArtifactEdit edit = null;
			try {
					edit = EJBArtifactEdit.getEJBArtifactEditForRead(project);
					if (edit != null && edit.hasEJBClientJARProject())
						return true;
			} finally {
				if(edit != null)
					edit.dispose();
					  
			}
		}
		return false;
	}	

}
