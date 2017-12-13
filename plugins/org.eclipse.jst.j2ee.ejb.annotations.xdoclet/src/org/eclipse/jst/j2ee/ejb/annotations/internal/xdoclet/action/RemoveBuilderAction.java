/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.action;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jface.action.IAction;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;

public class RemoveBuilderAction extends XDocletActionDelegate {

	public void run(IAction action) {

		if (project != null
				&& (J2EEProjectUtilities.isEJBProject(project.getProject()) || J2EEProjectUtilities.isDynamicWebProject(project
						.getProject()))) {
			try {
				ProjectUtilities.removeFromBuildSpec(BUILDERID, project.getProject());
			} catch (CoreException e) {
			}
		}
	}

}
