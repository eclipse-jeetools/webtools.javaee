/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;


import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.util.ProjectModule;
import org.eclipse.wst.server.core.model.ModuleDelegate;
import org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate;

public class LooseArchiveDeployableFactory extends ProjectModuleFactoryDelegate {
	private static final String ID = "com.ibm.wtp.server.looseArchive"; //$NON-NLS-1$

	/*
	 * @see DeployableProjectFactoryDelegate#getFactoryID()
	 */
	public String getFactoryId() {
		return ID;
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
			// Removed the EJB nature - Defect 228981
			return project.hasNature(JavaCore.NATURE_ID) && !project.hasNature(IWebNatureConstants.J2EE_NATURE_ID);
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * Creates the deployable project for the given project.
	 * 
	 * @param project
	 *            org.eclipse.core.resources.IProject
	 * @return com.ibm.etools.server.core.model.IProjectModule
	 */
	

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate#createModule(org.eclipse.core.resources.IProject)
     */
    protected IModule createModule(IProject project) {
        // TODO Auto-generated method stub
        return new LooseArchiveDeployable(project, ID);
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.model.ModuleFactoryDelegate#getModuleDelegate(org.eclipse.wst.server.core.IModule)
     */
    public ModuleDelegate getModuleDelegate(IModule module) {
        // TODO Auto-generated method stub
        return null;
    }

    /* (non-Javadoc)
     * @see org.eclipse.wst.server.core.model.ModuleFactoryDelegate#getModules()
     */
    public IModule[] getModules() {
        // TODO Auto-generated method stub
        return null;
    }
    protected void handleProjectChange(IProject project, IResourceDelta delta) {
        if (projects == null)
            cacheModules();
        super.handleProjectChange(project, delta);
    }

}