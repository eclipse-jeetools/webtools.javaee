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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IFlexibleProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.ModuleDelegate;
import org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate;

/**
 * J2EE deployable factory superclass.
 */
public abstract class J2EEDeployableFactory extends ProjectModuleFactoryDelegate {

	protected HashMap projectModules;

	protected final List moduleDelegates = Collections.synchronizedList(new ArrayList(1));

	private long cachedStamp = -2;

	protected static boolean isFlexibleProject(IProject project) {
		return ModuleCoreNature.getModuleCoreNature(project) != null;
	}

	public J2EEDeployableFactory() {
		super();
	}



	protected boolean needsUpdating(IProject project) {
		if(!initialized)
			return true;
		IFile wtpmodules = project.getFile(IModuleConstants.WTPMODULE_FILE_PATH);
		if (!wtpmodules.exists())
			return false;
		if(wtpmodules.getModificationStamp() != cachedStamp) {
			cachedStamp = wtpmodules.getModificationStamp();
			return true;
		}
		return false;
	}


	/**
	 * Return the workspace root.
	 * 
	 * @return the workspace root
	 */
	private static IWorkspaceRoot getWorkspaceRoot() {
		return ResourcesPlugin.getWorkspace().getRoot();
	}



	protected IModule[] createModules(IProject project) {

		// List modules = createModules(nature);
		ModuleCoreNature nature = null;
		try {
			nature = (ModuleCoreNature) project.getNature(IModuleConstants.MODULE_NATURE_ID);
		} catch (CoreException e) {
			Logger.getLogger().write(e);
		}
		List modules = createModules(nature);
		if (modules == null)
			return new IModule[0];
		IModule[] moduleArray = new IModule[modules.size()];
		modules.toArray(moduleArray);
		return moduleArray;
	}
	
	protected List createModules(ModuleCoreNature nature) {
		IProject project = nature.getProject();
		List modules = new ArrayList(1); 
		StructureEdit moduleCore = null;
		try {
			IFlexibleProject flexProj = ComponentCore.createFlexibleProject(project);
			IVirtualComponent[] components = flexProj.getComponents();
			modules = createModuleDelegates(components);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(moduleCore != null) 
				moduleCore.dispose();
		}
		return modules;
	}


	protected abstract List createModuleDelegates(IVirtualComponent[] comp) throws CoreException; 

	
	protected boolean isValidModule(IProject project) {
		try {
			return isFlexibleProject(project);
		} catch (Exception e) {
			//Ignore
		}
		return false;
	}

	
/*	protected IModule createModule(IProject project) {
		try {
			J2EENature nature = (J2EENature) project.getNature(getNatureID());
			return createModule(nature);
		} catch (Exception e) {
		}
		return null;
	}*/


	public ModuleDelegate getModuleDelegate(IModule module) {
		if(moduleDelegates.size() == 0)
			return null;
		ModuleDelegate[] delegates = (ModuleDelegate[])
			moduleDelegates.toArray(new ModuleDelegate[moduleDelegates.size()]);
		for (int i=0; i<delegates.length; i++) {			
			if (module == delegates[i].getModule())
				return delegates[i];
		}
		return null;
	}



}