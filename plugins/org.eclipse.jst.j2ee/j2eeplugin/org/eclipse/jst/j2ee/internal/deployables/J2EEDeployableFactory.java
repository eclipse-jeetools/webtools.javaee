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

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.ModuleDelegate;
import org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate;

/**
 * J2EE deployable factory superclass.
 */
public abstract class J2EEDeployableFactory extends ProjectModuleFactoryDelegate {
    
    protected ArrayList moduleDelegates = new ArrayList();

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

   
	protected void handleProjectChange(IProject project, IResourceDelta delta) {
        if (projects == null)
            cacheModules();
        super.handleProjectChange(project, delta);
    }
	
    public ModuleDelegate getModuleDelegate(IModule module) {
        for (Iterator iter = moduleDelegates.iterator(); iter.hasNext();) {
            ModuleDelegate element = (ModuleDelegate) iter.next();
            if (module == element.getModule())
                return element;
        }
        return null;
    }
    
    public IModule[] getModules() {
        if (projects == null)
            cacheModules();
        int i = 0;
        Iterator modules = projects.values().iterator();
        IModule[] modulesArray = new IModule[projects.values().size()];
        while (modules.hasNext()) {
            IModule element = (IModule) modules.next();
            modulesArray[i++] = element;

        }
        // TODO Auto-generated method stub
        return modulesArray;
    }


}