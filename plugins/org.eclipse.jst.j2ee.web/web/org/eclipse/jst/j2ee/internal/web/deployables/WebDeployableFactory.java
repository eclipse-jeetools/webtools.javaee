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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jst.j2ee.internal.deployables.J2EEDeployableFactory;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.ModuleStructuralModel;
import org.eclipse.wst.common.modulecore.ProjectComponents;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
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
        return IWebNatureConstants.J2EE_NATURE_ID;
    }

    public IModule createModule(J2EENature nature) {
          return null;
    }

    protected IPath[] getListenerPaths() {
        return PATHS;
    }

    protected List createModules(ModuleCoreNature nature) {
        IProject project = nature.getProject();
        List modules = null;
        ModuleStructuralModel moduleStructureModel = null;
        try {
        	ModuleCoreNature moduleCoreNature = ModuleCoreNature.getModuleCoreNature(project);
        	moduleStructureModel = moduleCoreNature.getModuleStructuralModelForRead(this);
            ProjectComponents components = (ProjectComponents) moduleStructureModel.getPrimaryRootObject();
            EList workBenchModules = components.getComponents();
            modules = createModuleDelegates(workBenchModules, project);

        } catch (Exception e) {
           e.printStackTrace();
        } finally {
            moduleStructureModel.releaseAccess(this);
        }
        return modules;
    }

    private List createModuleDelegates(EList workBenchModules, IProject project) throws CoreException {
        J2EEFlexProjWebDeployable moduleDelegate = null;
        IModule module;
        List moduleList = new ArrayList(workBenchModules.size());
        //  J2EENature nature = (J2EENature)project.getNature(getNatureID());

        for (int i = 0; i < workBenchModules.size(); i++) {
            try {
                WorkbenchComponent wbModule = (WorkbenchComponent) workBenchModules.get(i);
                moduleDelegate = new J2EEFlexProjWebDeployable(project, ID, wbModule);
                module = createModule(wbModule.getName(), wbModule.getName(), moduleDelegate.getType(), moduleDelegate.getVersion(),
                        moduleDelegate.getProject());
                moduleList.add(module);
                moduleDelegate.initialize(module);
                adapt(moduleDelegate, (WorkbenchComponent) workBenchModules.get(i));
            } catch (Exception e) {
                Logger.getLogger().write(e);
            } finally {
                moduleDelegates.add(moduleDelegate);
            }
        }
        return moduleList;

    }

    private void adapt(J2EEFlexProjWebDeployable moduleDelegate, WorkbenchComponent wbModule) {

        ModuleAdapter moduleAdapter = new ModuleAdapter() {
            public void notifyChanged(Notification msg) {
                super.notifyChanged(msg);
            }
        };
        moduleAdapter.setTarget(wbModule);
        moduleAdapter.setModuleDelegate(moduleDelegate);
        wbModule.eAdapters().add(moduleAdapter);
    }
}