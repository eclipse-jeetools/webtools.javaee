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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.modulecore.IModuleConstants;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.ModuleUtil;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.internal.Trace;
import org.eclipse.wst.server.core.model.ModuleDelegate;
import org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate;

import com.ibm.wtp.common.logger.proxy.Logger;

/**
 * J2EE deployable factory superclass.
 */
public abstract class J2EEDeployableFactory extends ProjectModuleFactoryDelegate {

    protected HashMap projectModules;

    protected ArrayList moduleDelegates = new ArrayList();

    protected static boolean isFlexableProject(IProject project) {
        return ModuleUtil.isFlexableProject(project);
    }

    public J2EEDeployableFactory() {
        super();
    }

    protected void addModuleProject(IProject project) {
        if (!isFlexableProject(project)) {
            super.addModuleProject(project);
            return;
        }
        ModuleCoreNature nature = null;
        try {
            nature = (ModuleCoreNature) project.getNature(IModuleConstants.MODULE_NATURE_ID);
        } catch (CoreException e) {
            Logger.getLogger().write(e);
        }
        List modules = createModules(nature);
        List oldModules = (List) getProjectModules().get(project);
        if (oldModules != null && !oldModules.isEmpty())
            addNewModules(modules, oldModules);
        else
            getProjectModules().put(project, modules);
        if (added == null)
            added = new ArrayList(2);
        added.addAll(modules);
    }

    /**
     * @param newModules
     */
    private void addNewModules(List newModules, List oldModules) {
        for (int i = 0; i < newModules.size(); i++) {
            if (!oldModules.contains(newModules.get(i)))
                oldModules.add(newModules.get(i));
        }

    }

    protected void removeModuleProject(IProject project) {
        if (!isFlexableProject(project)) {
            super.addModuleProject(project);
            return;
        }
        try {
            List modules = (List) getProjectModules().get(project);
            getProjectModules().remove(project);
            //modules.remove(module.getId());
            if (removed == null)
                removed = new ArrayList(2);
            removed.addAll(modules);
        } catch (Exception e) {
            Trace.trace(Trace.SEVERE, "Error removing module project", e);
        }
    }

    private List createModules(IProject project) {
        // TODO Auto-generated method stub
        return null;
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
            // need to create module specific ids - after M3
            /*
             * if (isFlexableProject(project)) return true;
             */
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

    protected abstract List createModules(ModuleCoreNature nature);

    /**
     * Return the nature ID.
     * 
     * @return java.lang.String
     */
    public abstract String getNatureID();

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
        if (!getProjectModules().isEmpty())
            return getALLModules();
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

    /**
     * @return
     */
    private IModule[] getALLModules() {
        Iterator moduleDelegateIterator = getProjectModules().values().iterator();
        List modules = new ArrayList();
        for (Iterator iter = moduleDelegateIterator; iter.hasNext();) {
            List elements = (List) iter.next();
            modules.addAll(elements);
        }
        int i = 0;
        // Iterator modulesIter = modules.iterator();
        Collection nonFlexableProjectModules = Collections.synchronizedCollection(projects.values());
        modules.addAll(nonFlexableProjectModules);
        IModule[] modules_Array = new IModule[modules.size()];
        for (int j = 0; j < modules.size(); j++) {
            IModule element = (IModule) modules.get(i);
            modules_Array[i++] = element;
        }
        return modules_Array;
    }

    public HashMap getProjectModules() {
        if (projectModules == null)
            projectModules = new HashMap();
        return projectModules;
    }

    public void setProjectModules(HashMap projectModules) {
        this.projectModules = projectModules;
    }
}