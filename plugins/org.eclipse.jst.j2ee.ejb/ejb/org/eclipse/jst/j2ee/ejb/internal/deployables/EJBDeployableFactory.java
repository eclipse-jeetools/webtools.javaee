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
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleType;
import org.eclipse.wst.server.core.IProjectProperties;
import org.eclipse.wst.server.core.ServerCore;

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

	public String getFactoryId() {
		return ID;
	}


	public String getNatureID() {
		return IEJBNatureConstants.NATURE_ID;
	}


	protected List createModuleDelegates(EList workBenchModules, IProject project) throws CoreException {
		EJBFlexibleDeployable moduleDelegate = null;
		IModule module = null;
		List moduleList = new ArrayList(workBenchModules.size());
		for (int i = 0; i < workBenchModules.size(); i++) {
			try {
				WorkbenchComponent wbModule = (WorkbenchComponent) workBenchModules.get(i);
				
				if (wbModule.getComponentType()==null || wbModule.getComponentType().getComponentTypeId() == null || !wbModule.getComponentType().getComponentTypeId().equals(EJBFlexibleDeployable.EJB_TYPE))
					continue;
				moduleDelegate = new EJBFlexibleDeployable(project, ID, wbModule);
				module = createModule(wbModule.getName(), wbModule.getName(), moduleDelegate.getType(), moduleDelegate.getVersion(), moduleDelegate.getProject());
				moduleList.add(module);
				moduleDelegate.initialize(module);
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

	public IModule[] getModules() {
		cacheModules(false);
		ArrayList moduleList = new ArrayList();
		for (Iterator iter = projects.values().iterator(); iter.hasNext();) {
			IModule[] element = (IModule[]) iter.next();
			for (int j = 0; j < element.length; j++) {
				moduleList.add(element[j]);
			}

		}
		IModule[] modules = new IModule[moduleList.size()];
		moduleList.toArray(modules);
		return modules;

	}

	protected boolean isValidModule(IProject project) {
		if (isFlexableProject(project)) {
			IProjectProperties properties = ServerCore.getProjectProperties(project);
			if (properties != null || properties.getRuntimeTarget() == null || properties.getRuntimeTarget().getRuntimeType().getModuleTypes() != null) {
				IModuleType[] moduleTypes = properties.getRuntimeTarget().getRuntimeType().getModuleTypes();
				for (int i = 0; i < moduleTypes.length; i++) {
					IModuleType moduleType = moduleTypes[i];
					if (moduleType.getId().equals("j2ee.ejb"))
						return true;
				}

			}

		}
		return false;
	}



}