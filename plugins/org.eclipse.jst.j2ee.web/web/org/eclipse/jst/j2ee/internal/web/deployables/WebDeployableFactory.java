/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.web.deployables;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.internal.deployables.J2EEDeployableFactory;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.ModuleStructuralModel;
import org.eclipse.wst.common.modulecore.ProjectModules;
import org.eclipse.wst.common.modulecore.util.ModuleCore;
import org.eclipse.wst.server.core.IModule;

import com.ibm.wtp.common.logger.proxy.Logger;

public class WebDeployableFactory extends J2EEDeployableFactory {
    private static final String ID = "com.ibm.wtp.web.server"; //$NON-NLS-1$

    protected static final IPath[] PATHS = new IPath[] { new Path(".j2ee") //$NON-NLS-1$
    };

    
    public String getFactoryId() {
        return ID;
    }


    public String getNatureID() {
        //  if (isFlexableProject())
        return IWebNatureConstants.J2EE_NATURE_ID;
    }

    public IModule createModule(J2EENature nature) {
        IProject project = nature.getProject();
        if (isFlexableProject(project))
            return null;
        if (nature == null)
            return null;
        J2EEWebDeployable moduleDelegate = null;
        IModule module = nature.getModule();
        if (module == null) {
            try {
                moduleDelegate = new J2EEWebDeployable(nature, ID);
                module = createModule(moduleDelegate.getId(), moduleDelegate.getName(), moduleDelegate.getType(), moduleDelegate.getVersion(),
                        moduleDelegate.getProject());
                nature.setModule(module);
                moduleDelegate.initialize(module);
            } catch (Exception e) {
                Logger.getLogger().write(e);
            } finally {
                moduleDelegates.add(moduleDelegate);
            }
        }
        return module;
    }


    protected IPath[] getListenerPaths() {
        return PATHS;
    }

 
    protected List createModules(ModuleCoreNature nature) {
        IProject project = nature.getProject();
        List modules = null;
        ModuleStructuralModel moduleStructureModel = null;
        try {
            moduleStructureModel = ModuleCore.getModuleStructuralModelForRead(project, this);
            ProjectModules module = (ProjectModules) moduleStructureModel.getPrimaryRootObject();
            EList workBenchModules = module.getWorkbenchModules();
            modules = createModuleDelegates(workBenchModules, project);

        } catch (Exception e) {
        } finally {
            moduleStructureModel.releaseAccess(this);
        }
        return modules;
    }

    private List createModuleDelegates(EList workBenchModules, IProject project) throws CoreException {
        J2EEWebDeployable moduleDelegate = null;
        IModule module;
        List moduleList = new ArrayList(workBenchModules.size());
        J2EENature nature = (J2EENature)project.getNature(getNatureID());
       
        for (int i = 0; i < workBenchModules.size(); i++) {
            try {
                moduleDelegate = new J2EEWebDeployable(nature, ID);
                module = createModule(moduleDelegate.getId(), moduleDelegate.getName(), moduleDelegate.getType(), moduleDelegate.getVersion(),
                        moduleDelegate.getProject());
                moduleList.add(module);
                moduleDelegate.initialize(module);
            } catch (Exception e) {
                Logger.getLogger().write(e);
            } finally {
                moduleDelegates.add(moduleDelegate);
            }
        }
        return moduleList;

    }

}