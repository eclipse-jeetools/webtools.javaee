/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.ModuleDelegate;
import org.eclipse.wst.server.core.util.ProjectModule;
import org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate;

/**
 * J2EE deployable factory superclass.
 */
public abstract class J2EEDeployableFactory extends ProjectModuleFactoryDelegate {

	public J2EEDeployableFactory() {
		super();
	}

	/**
	 * Returns true if the project represents a deployable project of this type.
	 * 
	 * @param project
	 *            org.eclipse.core.resources.IProject
	 * @return boolean
	 */
	protected boolean isValidModule(IProject project) {
		try {
			return project.hasNature(getNatureID());
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Creates the module project for the given project.
	 * 
	 * @param project
	 *            org.eclipse.core.resources.IProject
	 * @return com.ibm.etools.server.core.model.IProjectModule
	 */
	protected IModule createModule(IProject project) {
		try {
			J2EENature nature = (J2EENature) project.getNature(getNatureID());
			return createModule(nature);
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Return the nature ID.
	 * 
	 * @return java.lang.String
	 */
	public abstract String getNatureID();

	/**
	 * Create a module for the nature.
	 * 
	 * @param nature
	 *            J2EENature
	 * @return com.ibm.etools.server.core.model.IProjectModule
	 */
	public abstract IModule createModule(J2EENature nature);
    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate#handleProjectChange(org.eclipse.core.resources.IProject, org.eclipse.core.resources.IResourceDelta)
     */
    protected void handleProjectChange(IProject project, IResourceDelta delta) {
        if (projects == null)
            cacheModules();
        super.handleProjectChange(project, delta);
    }
    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.model.ModuleFactoryDelegate#getModuleDelegate(org.eclipse.wst.server.core.IModule)
     */

}