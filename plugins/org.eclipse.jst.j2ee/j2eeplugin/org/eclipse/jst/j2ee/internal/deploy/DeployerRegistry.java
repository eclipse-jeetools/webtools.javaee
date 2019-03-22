/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
/*
 * Created on Mar 30, 2004
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.eclipse.jst.j2ee.internal.deploy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.IModelProvider;
import org.eclipse.jst.j2ee.model.ModelProviderManager;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.internal.util.ComponentUtilities;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.server.core.IRuntime;

/**
 * @author cbridgha
 * 
 * To change the template for this generated type comment go to Window - Preferences - Java - Code
 * Generation - Code and Comments
 */
public class DeployerRegistry {
	/**
	 *  
	 */
	private static DeployerRegistry INSTANCE;
	private HashMap deployModuleExtensions = new HashMap();
	private HashMap facetExceptions = new HashMap();

	public DeployerRegistry() {
		super();
	}

	/**
	 * @param deployer
	 * @param facetExclusions 
	 * @param serverTarget
	 * @param natureID
	 */
	public void register(IConfigurationElement deployer, List serverTargets, List natures, List facetExclusions) {
		HashMap targetDeployers;
		for (Iterator iter = natures.iterator(); iter.hasNext();) {
			String natureID = (String) iter.next();
			for (Iterator iterator = serverTargets.iterator(); iterator.hasNext();) {
				String runtimeID = (String) iterator.next();
				targetDeployers = getDeployModuleExtensions(natureID);
				getTargetDeployers(targetDeployers, runtimeID).add(deployer);
			}
		}
		//Now register exclusions if any
		List facets = getFacetExceptions(deployer);
		for (Iterator iterator = facetExclusions.iterator(); iterator.hasNext();) {
			String facetID = (String) iterator.next();
			if (!facets.contains(facetID))
				facets.add(facetID);
		}	
	}
	
	/**
	 * @param deployer
	 * @param serverTarget
	 * @param natureID
	 */
	public void register(IConfigurationElement deployer, List serverTargets, List natures) {
		register(deployer, serverTargets, natures, new ArrayList());
	}

	private List getDeployers(String natureID, String serverTarget) {
		HashMap targetDeployers = getDeployModuleExtensions(natureID);
		return getTargetDeployers(targetDeployers, serverTarget);
	}

	public static DeployerRegistry instance() {
		if (INSTANCE == null) {
			INSTANCE = new DeployerRegistry();
			readRegistry();
		}
		return INSTANCE;
	}

	/**
	 *  
	 */
	private static void readRegistry() {
		DeployerRegistryReader reader = new DeployerRegistryReader();
		reader.readRegistry();
	}

	/**
	 * @return a list of IVirtualComponent's
	 */
	public static List getSelectedModules(Object[] mySelections) {
		List modules = new ArrayList();
		for (int i = 0; i < mySelections.length; i++) {
			Object object = mySelections[i];
			if (object instanceof EObject) {
				object = ProjectUtilities.getProject(object);
			}
			if (object instanceof IProject) {
				IVirtualComponent component = ComponentCore.createComponent((IProject)object);
				IModelProvider modelProvider = null;
					
				modelProvider = ModelProviderManager.getModelProvider(component.getProject());
				if (modelProvider == null)
					continue;
				// we just happen to know it
				EObject root = (EObject) modelProvider.getModelObject();
				
				if (root == null || modules.contains(component))
					continue;
				// Order Ears first...
				if (JavaEEProjectUtilities.isEARProject(component.getProject()))
					modules.add(0, component);
				else
					modules.add(component);
			
			}
		}
		return modules;
	}

	/**
	 * @param targetDeployers
	 * @param serverTarget
	 */
	private List getTargetDeployers(HashMap targetDeployers, String serverTarget) {
		if (targetDeployers.get(serverTarget) == null)
			targetDeployers.put(serverTarget, new ArrayList());
		return (List) targetDeployers.get(serverTarget);
	}

	/**
	 * @param natureID
	 * @return
	 */
	private HashMap getDeployModuleExtensions(String natureID) {
		if (getDeployModuleExtensions().get(natureID) == null)
			getDeployModuleExtensions().put(natureID, new HashMap());
		return (HashMap) getDeployModuleExtensions().get(natureID);
	}

	/**
	 * @return Returns the deployExtensions.
	 */
	public HashMap getDeployModuleExtensions() {
		return deployModuleExtensions;
	}

	/**
	 * @param deployExtensions
	 *            The deployExtensions to set.
	 */
	public void setDeployModuleExtensions(HashMap deployExtensions) {
		this.deployModuleExtensions = deployExtensions;
	}

	/**
	 * @param module
	 * @param runtime
	 * @return
	 */
	public List getDeployModuleExtensions(EObject module, IRuntime runtime) {
		IVirtualComponent comp = ComponentUtilities.findComponent(module);
		return getDeployModuleExtensions(comp.getProject(), runtime);
	}

	/**
	 * @param module
	 * @param runtime
	 * @return
	 */
	public List getDeployModuleExtensions(IProject project, IRuntime runtime) {
		String typeID = ""; //$NON-NLS-1$
		if (JavaEEProjectUtilities.isEARProject(project))
			typeID = J2EEProjectUtilities.ENTERPRISE_APPLICATION;
		else if (JavaEEProjectUtilities.isApplicationClientProject(project))
			typeID = J2EEProjectUtilities.APPLICATION_CLIENT;
		else if (JavaEEProjectUtilities.isDynamicWebProject(project))
			typeID = J2EEProjectUtilities.DYNAMIC_WEB;
		else if (JavaEEProjectUtilities.isStaticWebProject(project))
			typeID = J2EEProjectUtilities.STATIC_WEB;
		else if (JavaEEProjectUtilities.isEJBProject(project))
			typeID = J2EEProjectUtilities.EJB;
		else if (JavaEEProjectUtilities.isJCAProject(project))
			typeID = J2EEProjectUtilities.JCA;
		else if (JavaEEProjectUtilities.isUtilityProject(project))
			typeID = J2EEProjectUtilities.UTILITY;
		
		String runtimeID = null;
		if (runtime == null)
			runtimeID = "None"; //$NON-NLS-1$
		else
			runtimeID = runtime.getRuntimeType().getId();
		List deployers = getDeployers(typeID, runtimeID);
		return getFilteredDeployers(project,deployers); 
	}

	private List getFilteredDeployers(IProject project, List deployers) {
		IFacetedProject fProj = null;
		try {
			fProj = ProjectFacetsManager.create(project);
		} catch (CoreException e) {
			org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin.logError(e);
		}
		if (fProj == null) return deployers;
		List filteredDeployers = new ArrayList();
		for (Iterator iterator = deployers.iterator(); iterator.hasNext();) {
			boolean excludeDeployer = false;
			IConfigurationElement deployer = (IConfigurationElement) iterator.next();
			List exclusions = getFacetExceptions(deployer);
			if (exclusions.isEmpty())
				filteredDeployers.add(deployer);
			else {
				for (Iterator iterator2 = exclusions.iterator(); iterator2.hasNext();) {
					String exclusion = (String) iterator2.next();
					IProjectFacet facet = null;
					try {
						facet = ProjectFacetsManager.getProjectFacet(exclusion);
					} catch (IllegalArgumentException e) {
						//Facet id not found
						// Don't log as this could be expected.....
					}
					if (facet != null && fProj.hasProjectFacet(facet)) {
						excludeDeployer = true;
					}
				}
				if (!excludeDeployer)
					filteredDeployers.add(deployer);
			}
			
		}
		return filteredDeployers;
	}

	
	private List getFacetExceptions(IConfigurationElement deployer) {
		if (facetExceptions.get(deployer) == null)
			facetExceptions.put(deployer, new ArrayList());
		return (List)facetExceptions.get(deployer);
	}

}
