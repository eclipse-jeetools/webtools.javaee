/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.internal.deployables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.componentcore.JavaEEBinaryComponentHelper;
import org.eclipse.jst.j2ee.internal.deployables.J2EEFlexProjDeployable;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.jee.JEEPlugin;
import org.eclipse.jst.jee.util.internal.JavaEEQuickPeek;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.model.ModuleDelegate;
import org.eclipse.wst.server.core.util.ProjectModuleFactoryDelegate;

/**
 * J2EE module factory.
 */
public class JEEDeployableFactory extends ProjectModuleFactoryDelegate {
	protected Map <IModule, ModuleDelegate> moduleDelegates = new HashMap<IModule, ModuleDelegate>(5);

	public static final String ID = "org.eclipse.jst.jee.server"; //$NON-NLS-1$

	public JEEDeployableFactory() {
		super();
	}

	@Override
	protected IModule[] createModules(IProject project) {
		IVirtualComponent comp = ComponentCore.createComponent(project);
		if(comp != null){
			return createModuleDelegates(comp);
		}
		return null;
	}

	/**
	 * Use {@link #createModule(IProject)} instead.
	 * @deprecated
	 * @param nature
	 * @return
	 */
	protected IModule[] createModules(ModuleCoreNature nature) {
		if(nature != null){
			return createModules(nature.getProject());
		}
		return null;
	}

	@Override
	public ModuleDelegate getModuleDelegate(IModule module) {
		if (module == null)
			return null;

		ModuleDelegate md = moduleDelegates.get(module);

		if (md == null) {
			createModules(module.getProject());
			md = moduleDelegates.get(module);
		}

		return md;
	}

	protected IModule[] createModuleDelegates(IVirtualComponent component) {
		if(component == null){
			return null;
		}
		List projectModules = new ArrayList();
		try {
			if (J2EEProjectUtilities.isJEEProject(component.getProject())) {
				IModule module = null;
				String type = J2EEProjectUtilities.getJ2EEProjectType(component.getProject());
				if (type != null && !type.equals("")) { //$NON-NLS-1$
					String version = J2EEProjectUtilities.getJ2EEProjectVersion(component.getProject());
					module = createModule(component.getName(), component.getDeployedName(), type, version, component.getProject());
					JEEFlexProjDeployable moduleDelegate = new JEEFlexProjDeployable(component.getProject(), component);
					moduleDelegates.put(module, moduleDelegate);
					projectModules.add(module);
				}
				// Check to add any binary modules
				if (J2EEProjectUtilities.ENTERPRISE_APPLICATION.equals(type))
					projectModules.addAll(Arrays.asList(createBinaryModules(component)));
			} else {
				return null;
			}
		} catch (Exception e) {
			JEEPlugin.logError(e);
		}
		return (IModule[]) projectModules.toArray(new IModule[projectModules.size()]);
	}

	protected IModule[] createBinaryModules(IVirtualComponent component) {
		List projectModules = new ArrayList();
		IVirtualReference[] references = component.getReferences();
		for (int i = 0; i < references.length; i++) {
			IVirtualComponent moduleComponent = references[i].getReferencedComponent();
			// Is referenced component a J2EE binary module archive or binary
			// utility project?
			if (moduleComponent.isBinary()) {

				JavaEEQuickPeek qp = JavaEEBinaryComponentHelper.getJavaEEQuickPeek(moduleComponent);
				// If it is not a j2ee module and the component project is the
				// ear, it is just an archive
				// and we can ignore as it will be processed by the EAR
				// deployable.members() method
				if (qp.getType() == JavaEEQuickPeek.UNKNOWN) {
					continue;
				}

				String moduleType = null;
				String moduleVersion = null;

				switch (qp.getType()) {
				case JavaEEQuickPeek.APPLICATION_CLIENT_TYPE:
					moduleType = J2EEProjectUtilities.APPLICATION_CLIENT;
					break;
				case JavaEEQuickPeek.WEB_TYPE:
					moduleType = JavaEEProjectUtilities.DYNAMIC_WEB;
					break;
				case JavaEEQuickPeek.EJB_TYPE:
					moduleType = JavaEEProjectUtilities.EJB;
					break;
				case JavaEEQuickPeek.CONNECTOR_TYPE:
					moduleType = JavaEEProjectUtilities.JCA;
					break;
				case JavaEEQuickPeek.APPLICATION_TYPE:
					moduleType = JavaEEProjectUtilities.ENTERPRISE_APPLICATION;
					break;
				default:
					moduleType = JavaEEProjectUtilities.UTILITY;
					moduleVersion = J2EEVersionConstants.VERSION_1_0_TEXT;
				}

				int version = qp.getVersion();
				moduleVersion = J2EEVersionUtil.convertVersionIntToString(version);

				IModule nestedModule = createModule(moduleComponent.getName(), moduleComponent.getDeployedName(), moduleType, moduleVersion, moduleComponent.getProject());
				if (nestedModule != null) {
					J2EEFlexProjDeployable moduleDelegate = new J2EEFlexProjDeployable(moduleComponent.getProject(), moduleComponent);
					moduleDelegates.put(nestedModule, moduleDelegate);
					projectModules.add(nestedModule);
					moduleDelegate.getURI(nestedModule);
				}
			}
		}

		return (IModule[]) projectModules.toArray(new IModule[projectModules.size()]);
	}

	/**
	 * Returns the list of resources that the module should listen to for state
	 * changes. The paths should be project relative paths. Subclasses can
	 * override this method to provide the paths.
	 * 
	 * @return a possibly empty array of paths
	 */
	@Override
	protected IPath[] getListenerPaths() {
		return new IPath[] { new Path(".project"), // nature //$NON-NLS-1$
				new Path(StructureEdit.MODULE_META_FILE_NAME), // component
				new Path(".settings/org.eclipse.wst.common.project.facet.core.xml") // facets  //$NON-NLS-1$
		};
	}
	
	@Override
	protected void clearCache(IProject project) {
		super.clearCache(project);
		List<IModule> modulesToRemove = null;
		for (Iterator<IModule> iterator = moduleDelegates.keySet().iterator(); iterator.hasNext();) {
			IModule module = iterator.next();
			if (module.getProject().equals(project)) {
				if (modulesToRemove == null) {
					modulesToRemove = new ArrayList<IModule>();
				}
				modulesToRemove.add(module);
			}
		}
		if (modulesToRemove != null) {
			for (IModule module : modulesToRemove) {
				moduleDelegates.remove(module);
			}
		}
	}
	
	
}
