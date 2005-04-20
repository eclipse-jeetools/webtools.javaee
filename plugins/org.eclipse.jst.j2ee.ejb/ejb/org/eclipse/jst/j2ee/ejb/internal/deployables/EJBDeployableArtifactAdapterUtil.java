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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jem.util.logger.proxy.Logger;
import org.eclipse.jem.workbench.utility.JemProjectUtilities;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.server.core.EJBBean;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.server.core.IModule;
import org.eclipse.wst.server.core.IModuleArtifact;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.util.WebResource;

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

	/*
	 * @see IDeployableObjectAdapterDelegate#getDeployableObject(Object)
	 */
	public static IModuleArtifact getModuleObject(Object obj) {
		if (obj == null)
			return null;
		if (obj instanceof EJBJar)
			return getModuleObject((EJBJar) obj);
		if (obj instanceof EnterpriseBean)
			return getModuleObject((EnterpriseBean) obj);
		if (obj instanceof IProject) {
			IProject project = (IProject) obj;
			StructureEdit edit = null;
			try {
				edit = StructureEdit.getStructureEditForWrite(project);
				WorkbenchComponent[] components = edit.findComponentsByType("jst.ejb");
				if (components == null || components.length == 0)
					return null;
				else
					return getModuleObject((IProject) obj);
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				edit.dispose();
			}

		}

		if (obj instanceof IFile)
			return getModuleObject((IFile) obj);
		if (obj instanceof ICompilationUnit) {
			return getModuleObject((ICompilationUnit) obj);
		}
		return null;
	}

	protected static boolean hasEJBComponents(IProject project) {
		StructureEdit edit = null;
		try {
			edit = StructureEdit.getStructureEditForWrite(project);
			WorkbenchComponent[] components = edit.findComponentsByType("jst.ejb");
			if (components == null || components.length == 0)
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
			return getModuleJavaObject((IFile) cu.getCorrespondingResource());
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
		IModule dep = getModule(project);
		return createModuleObject(dep, null, false, false);
	}

	protected static IModuleArtifact getModuleObject(IFile file) {
		if (hasEJBComponents(file.getProject())) {
			String ext = file.getFileExtension();
			if ("java".equals(ext) || "class".equals(ext)) //$NON-NLS-1$ //$NON-NLS-2$
				return getModuleJavaObject(file);
			if (file.getProjectRelativePath().toString().endsWith(J2EEConstants.EJBJAR_DD_URI))
				return createModuleObject(getModule(file.getProject()), null, false, false);
		}
		return null;
	}

	protected static IModule getModule(EObject refObject) {
		IProject proj = ProjectUtilities.getProject(refObject);
		return getModule(proj);
	}

	protected static IModule getModule(IProject project) {
		IModule deployable = null;
		if (deployable != null)
			return deployable;
		Iterator iterator = Arrays.asList(ServerUtil.getModules("j2ee.ejb")).iterator(); //$NON-NLS-1$
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

	protected static EJBNatureRuntime getNature(IProject project) {
		if (project != null)
			return EJBNatureRuntime.getRuntime(project);
		return null;
	}

	protected static IModuleArtifact getModuleJavaObject(IFile file) {
		EJBNatureRuntime nat = getNature(file.getProject());
		if (nat != null) {
			JavaClass javaClass = JemProjectUtilities.getJavaClass(file);
			if (javaClass != null) {
				EJBJar jar = nat.getEJBJar();
				if (jar != null) {
					EnterpriseBean ejb = jar.getEnterpriseBeanWithReference(javaClass);
					return createModuleObject(nat.getModule(), ejb.getName(), isRemote(ejb, javaClass), isLocal(ejb, javaClass));
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
			if (ejbName != null)
				jndiName = ((EJBDeployable) module).getJNDIName(ejbName);
			return new EJBBean(module, jndiName, remote, local);
		}
		return null;
	}



}