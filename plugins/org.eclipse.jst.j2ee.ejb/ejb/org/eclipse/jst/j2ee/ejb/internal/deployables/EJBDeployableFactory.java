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
package org.eclipse.jst.j2ee.ejb.internal.deployables;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.deployables.J2EEDeployableFactory;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.WorkbenchComponent;
import org.eclipse.wst.server.core.IModule;

/**
 * @version 1.0
 * @author
 */
public class EJBDeployableFactory extends J2EEDeployableFactory {
	private static final String ID = "com.ibm.wtp.server.ejb"; //$NON-NLS-1$

	/**
	 * Constructor for EJBDeployableFactory.
	 */
	public EJBDeployableFactory() {
		super();
	}

	/*
	 * @see DeployableProjectFactoryDelegate#getFactoryID()
	 */
	public String getFactoryId() {
		return ID;
	}

	/*
	 * @see J2EEDeployableFactory#getNatureID()
	 */
	public String getNatureID() {
		return IEJBNatureConstants.NATURE_ID;
	}


	
    public IModule createModule(J2EENature nature) {
        if (nature == null)
            return null;
        EJBDeployable moduleDelegate = null;
        IModule module = nature.getModule();
        if (module == null) {
            try {
                moduleDelegate = new EJBDeployable(nature, ID);
                module = createModule(moduleDelegate.getId(), moduleDelegate.getName(), moduleDelegate.getType(), moduleDelegate.getVersion(), moduleDelegate.getProject());
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
    
	private List createModuleDelegates(EList workBenchModules, IProject project) throws CoreException {
		EJBFlexibleDeployable moduleDelegate = null;
		IModule module = null;
		List moduleList = new ArrayList(workBenchModules.size());
		//  J2EENature nature = (J2EENature)project.getNature(getNatureID());

		for (int i = 0; i < workBenchModules.size(); i++) {
			try {
				WorkbenchComponent wbModule = (WorkbenchComponent) workBenchModules.get(i);
				moduleDelegate = new EJBFlexibleDeployable(project, ID, wbModule);
				module = createModule(wbModule.getName(), wbModule.getName(), moduleDelegate.getType(), moduleDelegate.getVersion(), moduleDelegate.getProject());
				moduleList.add(module);
				moduleDelegate.initialize(module);
				//adapt(moduleDelegate, (WorkbenchComponent) workBenchModules.get(i));
			} catch (Exception e) {
				Logger.getLogger().write(e);
			} finally {
				if (module != null) {
					if (getModuleDelegate(module) == null)
						moduleDelegates.add(moduleDelegate);
				}
			}
		}
		return moduleList;

	}

    /* (non-Javadoc)
     * @see org.eclipse.jst.j2ee.internal.deployables.J2EEDeployableFactory#createModules(org.eclipse.wst.common.modulecore.ModuleCoreNature)
     */
	protected List createModules(ModuleCoreNature nature) {
		IProject project = nature.getProject();
		List modules = new ArrayList(1); 
		ModuleCore moduleCore = null;
		try {
			
			moduleCore = ModuleCore.getModuleCoreForRead(project);
			EList workBenchModules = moduleCore.getModuleModelRoot().getComponents();						 
			if (workBenchModules.isEmpty())
				return modules;
			modules = createModuleDelegates(workBenchModules, project);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(moduleCore != null) 
				moduleCore.dispose();
		}
		return modules;
	}

	public IModule[] getModules() {
		int i = 0;
		cacheModules();
		ArrayList moduleList = new ArrayList();
		for (Iterator iter = projects.values().iterator(); iter.hasNext();) {
			IModule[] element = (IModule[]) iter.next();
			for (int j = 0; j < element.length; j++) {
				moduleList.add((IModule) element[j]);
			}

		}
		IModule[] modules = new IModule[moduleList.size()];
		moduleList.toArray(modules);
		return modules;

	}
	
	protected boolean isValidModule(IProject project) {

		return false;
	}



}