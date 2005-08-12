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

import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.componentcore.util.EJBArtifactEdit;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.server.core.EJBBean;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleArtifact;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.model.ModuleDelegate;

/**
 * @version 1.0
 * @author
 */
public class EJBDeployableArtifactAdapterUtil {
	/**
	 * Constructor for EJBDeployableObjectAdapter.
	 */
	public EJBDeployableArtifactAdapterUtil() {
		super();
	}

	public static IModuleArtifact getModuleObject(Object obj) {
		if (obj == null)
			return null;
		if (obj instanceof EJBJar)
			return getModuleObject((EJBJar) obj);
		if (obj instanceof EnterpriseBean)
			return getModuleObject((EnterpriseBean) obj);
		if (obj instanceof IProject) {
			IProject project = (IProject) obj;
			return getModuleObject((IProject) obj);
		}
		if (obj instanceof IFile)
			return getModuleObject((IFile) obj);
		if (obj instanceof ICompilationUnit) {
			return getModuleObject((ICompilationUnit) obj);
		}
		return null;
	}

	protected static boolean hasInterestedComponents(IProject project) {
		StructureEdit edit = null;
		if (ModuleCoreNature.getModuleCoreNature(project) == null)
			return false;
		try {
			edit = StructureEdit.getStructureEditForWrite(project);
			WorkbenchComponent[] components = edit.findComponentsByType("jst.ejb");
			WorkbenchComponent[] earComponents = null;// edit.findComponentsByType("jst.ear");
			if (components == null || components.length == 0) //earComponents == null || earComponents.length > 0
				return false;
			else
				return true;
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			edit.dispose();
		}
		return false;
	}

	protected static IModuleArtifact getModuleObject(ICompilationUnit cu) {

		try {
			IFile file = (IFile) cu.getCorrespondingResource();
			IProject project = file.getProject();
			if (hasInterestedComponents(project)) {
				return getModuleJavaObject(file);
			}

		} catch (JavaModelException e) {
			Logger.getLogger().log(e);
		}
		return null;
	}

	protected static IModuleArtifact getModuleObject(EJBJar ejbJar) {
		IModule dep = getModule(ejbJar);
		return createModuleObject(dep, null, false, false);
	}

	protected static IModuleArtifact getModuleObject(EnterpriseBean ejb) {

		IModule dep = getModule(ejb);
		return createModuleObject(dep, ejb.getName(), ejb.hasRemoteClient(), ejb.hasLocalClient());
	}

	protected static IModuleArtifact getModuleObject(IProject project) {
		if (hasInterestedComponents(project)) {
			IModule dep = getModule(project, null);
			return createModuleObject(dep, null, false, false);
		}
		return null;
	}

	protected static IModuleArtifact getModuleObject(IFile file) {
		IVirtualResource[] resources = ComponentCore.createResources(file);
		IVirtualComponent component = null;
		if (resources[0] != null || resources.length <= 0)
			component = resources[0].getComponent();
		if (hasInterestedComponents(file.getProject())) {
			String ext = file.getFileExtension();
			if ("java".equals(ext) || "class".equals(ext)) //$NON-NLS-1$ //$NON-NLS-2$
				return getModuleJavaObject(file);
			if (file.getProjectRelativePath().toString().endsWith(J2EEConstants.EJBJAR_DD_URI))
				return createModuleObject(getModule(file.getProject(), component), null, false, false);
		}
		return null;
	}

	protected static IModule getModule(EObject refObject) {
		IProject proj = ProjectUtilities.getProject(refObject);
		Resource refResource = refObject.eResource();
		IVirtualResource[] resources = null;
		IVirtualComponent component = null;
		try {
			IResource eclipeServResoruce = WorkbenchResourceHelper.getFile(refResource);
			resources = ComponentCore.createResources(eclipeServResoruce);
			if (resources[0] != null)
				component = resources[0].getComponent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getModule(proj, component);
	}

	protected static IModule getModule(IProject project, IVirtualComponent component) {
		IModule deployable = null;
		Iterator iterator = Arrays.asList(ServerUtil.getModules("j2ee.ejb")).iterator();
		String componentName = null;
		if (component != null)
			componentName = component.getName();
		else
			return getModuleProject(project, iterator);
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof IModule) {
				deployable = (IModule) next;
				if (deployable.getName().equals(componentName)) {
					return deployable;
				}
			}
		}
		return null;
	}
	
	protected static IModule getModuleProject(IProject project, Iterator iterator) {
		IModule deployable = null;
		while (iterator.hasNext()) {
			Object next = iterator.next();
			if (next instanceof IModule) {
				deployable = (IModule) next;
				if (deployable.getProject().equals(project))
					return deployable;
			}
		}
		return null;
	}


	protected static IModuleArtifact getModuleJavaObject(IFile file) {

			IVirtualComponent comp = (IVirtualComponent)file.getAdapter(IVirtualComponent.class);
			if (comp != null) {
			JavaClass javaClass = JemProjectUtilities.getJavaClass(file);
			if (javaClass != null) {
				EJBArtifactEdit edit = null;
				try {
				edit = EJBArtifactEdit.getEJBArtifactEditForRead(comp);
				EJBJar jar = edit.getEJBJar();
				if (jar != null) {
					EnterpriseBean ejb = jar.getEnterpriseBeanWithReference(javaClass);
					return createModuleObject(getModule(comp.getProject(), comp), ejb.getName(), isRemote(ejb, javaClass), isLocal(ejb, javaClass));
				}
				} finally {
					if (edit != null)
						edit.dispose();
				}
			}
			}
		return null;
	}

	protected static boolean isRemote(EnterpriseBean ejb, JavaClass javaClass) {
		if (javaClass.equals(ejb.getHomeInterface()) || javaClass.equals(ejb.getRemoteInterface()))
			return true;
		return false;
	}

	protected static boolean isLocal(EnterpriseBean ejb, JavaClass javaClass) {
		if (javaClass.equals(ejb.getLocalHomeInterfaceName()) || javaClass.equals(ejb.getLocalInterface()))
			return true;
		return false;
	}

	protected static IModuleArtifact createModuleObject(IModule module, String ejbName, boolean remote, boolean local) {
		if (module != null) {
			String jndiName = null;
			if (ejbName != null) {
				module.loadAdapter(ModuleDelegate.class, new NullProgressMonitor());
				EJBFlexibleDeployable moduleDelegate = (EJBFlexibleDeployable)module.getAdapter(ModuleDelegate.class);
				jndiName = moduleDelegate.getJNDIName(ejbName);
			}
			return new EJBBean(module, jndiName, remote, local);
		}
		return null;
	}



}