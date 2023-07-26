/*******************************************************************************
 * Copyright (c) 2003, 2023 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jee.ui.internal.deployables;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.javaee.ejb.EJBJar;
import org.eclipse.jst.javaee.ejb.EntityBean;
import org.eclipse.jst.javaee.ejb.MessageDrivenBean;
import org.eclipse.jst.javaee.ejb.SessionBean;
import org.eclipse.jst.jee.internal.deployables.JEEFlexProjDeployable;
import org.eclipse.jst.jee.ui.plugin.JEEUIPlugin;
import org.eclipse.jst.server.core.EJBBean;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualResource;
import org.eclipse.wst.common.internal.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleArtifact;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.model.ModuleDelegate;
import org.eclipse.wst.server.core.util.NullModuleArtifact;

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
		else if (obj instanceof EJBJar)
			return getModuleObject((EJBJar) obj);
		else if (obj instanceof SessionBean)
			return getModuleObject((SessionBean) obj);
		else if (obj instanceof MessageDrivenBean)
			return getModuleObject((MessageDrivenBean) obj);
		else if (obj instanceof EntityBean)
			return getModuleObject((EntityBean) obj);
		else if (obj instanceof IProject) 
			return  getModuleObject((IProject) obj);
		else if (obj instanceof IJavaProject) 
			return  getModuleObject(((IJavaProject) obj).getProject());
		else if (obj instanceof IFile)
			return getModuleObject((IFile) obj);
		else if (obj instanceof ICompilationUnit)
			return getModuleObject((ICompilationUnit) obj);
		return null;
	}

	protected static boolean hasInterestedComponents(IProject project) {
		return JavaEEProjectUtilities.isEJBProject(project);
	}

	protected static IModuleArtifact getModuleObject(ICompilationUnit cu) {

		try {
			IFile file = (IFile) cu.getCorrespondingResource();
			IProject project = file.getProject();
			if (hasInterestedComponents(project)) {
				return getModuleJavaObject(file);
			}

		} catch (JavaModelException e) {
			JEEUIPlugin.logError(e);
		}
		return null;
	}

	protected static IModuleArtifact getModuleObject(EJBJar ejbJar) {
		IModule dep = getModule((EObject)ejbJar);
		return createModuleObject(dep, null, false, false);
	}

	protected static IModuleArtifact getModuleObject(SessionBean ejb) {
		IModule dep = getModule((EObject)ejb);
		return createModuleObject(dep, ejb.getEjbName(), false, false);
	}
	protected static IModuleArtifact getModuleObject(EntityBean ejb) {
		IModule dep = getModule((EObject)ejb);
		return createModuleObject(dep, ejb.getEjbName(), false, false);
	}
	protected static IModuleArtifact getModuleObject(MessageDrivenBean ejb) {
		IModule dep = getModule((EObject)ejb);
		return createModuleObject(dep, ejb.getEjbName(), false, false);
	}
	protected static IModuleArtifact[] getModuleObjects(SessionBean ejb) {
		List modArtifacts = new ArrayList();
		IModule dep = getModule((EObject)ejb);
		List busLocals = ejb.getBusinessLocals();
		List busRemotes = ejb.getBusinessRemotes();
		for (Iterator iterator = busLocals.iterator(); iterator.hasNext();) {
			String localName = (String) iterator.next();
			modArtifacts.add(createModuleObjectForSessionBean(dep, ejb.getEjbName(), localName, false,true));
			
		}
		for (Iterator iterator = busRemotes.iterator(); iterator.hasNext();) {
			String remoteName = (String) iterator.next();
			modArtifacts.add(createModuleObjectForSessionBean(dep, ejb.getEjbName(), remoteName, true, false));
		}

		// for EJB 3.1 no-interface
		if (ejb.getLocalBean() != null){
			modArtifacts.add(createModuleObjectForSessionBean(dep, ejb.getEjbName(), ejb.getEjbClass(), false,false));
		}

		return (IModuleArtifact[])modArtifacts.toArray(new IModuleArtifact[modArtifacts.size()]);
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
		if (proj == null)
			return null;
		Resource refResource = refObject.eResource();
		IVirtualResource[] resources = null;
		IVirtualComponent component = null;
		try {
			IResource eclipeServResoruce = WorkbenchResourceHelper.getFile(refResource);
			resources = ComponentCore.createResources(eclipeServResoruce);
			if (resources[0] != null)
				component = resources[0].getComponent();
		} catch (Exception e) {
			JEEUIPlugin.logError(e);
		}
		return getModule(proj, component);
	}

	protected static IModule getModule(IProject project, IVirtualComponent component) {
		IModule deployable = null;
		Iterator iterator = Arrays.asList(ServerUtil.getModules(J2EEProjectUtilities.EJB)).iterator();  
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
		return null;
	}

	protected static IModuleArtifact createModuleObject(IModule module, String ejbName, boolean remote, boolean local) {
		if (module != null) {
			String jndiName = null;
			if (ejbName != null) {
				module.loadAdapter(ModuleDelegate.class, new NullProgressMonitor());
				JEEFlexProjDeployable moduleDelegate = (JEEFlexProjDeployable)module.getAdapter(ModuleDelegate.class);
				jndiName = moduleDelegate.getJNDIName(ejbName);
				return new EJBBean(module, jndiName, remote, local,EJBBean.EJB_30);
			}
			return new NullModuleArtifact(module);
		}
		return null;
	}

	protected static IModuleArtifact createModuleObjectForSessionBean(IModule module, String ejbName, String interfaceName, boolean remote, boolean local) {
		if (module != null) {
			String jndiName = null;
			if (ejbName != null) {
				module.loadAdapter(ModuleDelegate.class, new NullProgressMonitor());
				JEEFlexProjDeployable moduleDelegate = (JEEFlexProjDeployable)module.getAdapter(ModuleDelegate.class);
				jndiName = moduleDelegate.getJNDIName(ejbName, interfaceName);
				return new EJBBean(module, jndiName, remote, local,EJBBean.EJB_30, interfaceName);
			}
			return new NullModuleArtifact(module);
		}
		return null;
	}


}
