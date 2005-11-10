/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.deployables;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.ModuleDelegate;
import org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate;

/**
 * J2EE module factory.
 */
public class J2EEDeployableFactory extends ProjectModuleFactoryDelegate {
	protected Map moduleDelegates = new HashMap(5);

	public J2EEDeployableFactory() {
		super();
	}

	protected IModule createModule(IProject project) {
		try {
			ModuleCoreNature nature = (ModuleCoreNature) project.getNature(IModuleConstants.MODULE_NATURE_ID);
			if (nature != null)
				return createModule(nature);
		} catch (CoreException e) {
			Logger.getLogger().write(e);
		}
		return null;
	}

	protected IModule createModule(ModuleCoreNature nature) {
		IProject project = nature.getProject();
		try {
			IVirtualComponent comp = ComponentCore.createComponent(project);
			return createModuleDelegates(comp);
		} catch (Exception e) {
			Logger.getLogger().write(e);
		}
		return null;
	}

	public ModuleDelegate getModuleDelegate(IModule module) {
		return (ModuleDelegate) moduleDelegates.get(module);
	}

	protected IModule createModuleDelegates(IVirtualComponent component) {
		J2EEFlexProjDeployable moduleDelegate = null;
		IModule module = null;
		try {
			moduleDelegate = new J2EEFlexProjDeployable(component.getProject(), component);
			String type = J2EEProjectUtilities.getJ2EEProjectType(component.getProject());
			if (type != null && !type.equals("")) {
				String version = J2EEProjectUtilities.getJ2EEProjectVersion(component.getProject());
				module = createModule(component.getName(), component.getName(), type, version, moduleDelegate.getProject());
				moduleDelegates.put(module, moduleDelegate);
			} else
				return null;
		} catch (Exception e) {
			Logger.getLogger().write(e);
		}
		return module;
	}

	/**
	 * Returns the list of resources that the module should listen to
	 * for state changes. The paths should be project relative paths.
	 * Subclasses can override this method to provide the paths.
	 *
	 * @return a possibly empty array of paths
	 */
	protected IPath[] getListenerPaths() {
		return new IPath[] {
			new Path(".project"), // nature
			new Path(".settings/.component"), // component
			new Path(".settings/.org.eclipse.wst.common.project.facet.core.xml") // facets
		};
	}

	protected void clearCache() {
		moduleDelegates = new HashMap(5);
	}
}