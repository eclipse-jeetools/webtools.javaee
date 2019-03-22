/******************************************************************************
 * Copyright (c) 2012 IBM.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Marcos Munoz - Change version facet enablement
 ******************************************************************************/

package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.wst.common.project.facet.core.IDelegate;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * The intention of this class is the enablement of the change facet 
 * version action (Action.Type.VERSION_CHANGE). The real implementation
 * of the tasks associated with the action should be handled by listeners 
 * linked to IFacetedProjectEvent.Type.PRE_VERSION_CHANGE or
 * IFacetedProjectEvent.Type.POST_VERSION_CHANGE events.
 * 
 * Be cautious to filter your actions properly so the code in the listeners 
 * could be executed only when required. 
 * 
 * @author <a href="mailto:jmmunoz@mx1.ibm.com">Marcos Munoz</a>
 *
 */
public class J2EEFacetVersionChangeDelegate implements IDelegate {

	@Override
	public void execute(IProject project, IProjectFacetVersion fv,
			Object config, IProgressMonitor monitor) throws CoreException {
		//do nothing
	}

}
