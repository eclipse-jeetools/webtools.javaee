/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.componentcore;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jst.common.jdt.internal.integration.WorkingCopyManager;
import org.eclipse.jst.common.jdt.internal.integration.WorkingCopyManagerFactory;
import org.eclipse.jst.common.jdt.internal.integration.WorkingCopyProvider;
import org.eclipse.wst.common.componentcore.ArtifactEdit;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.internal.ArtifactEditModel;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;

/**
 * <p>
 * EnterpriseArtifactEdit obtains a type-specific J2EE metamodel from the managed
 * {@see org.eclipse.wst.common.modulecore.ArtifactEditModel}. The underlying EditModel maintains
 * {@see org.eclipse.emf.ecore.resource.Resource}s, such as the J2EE deployment descriptor
 * resource. The defined methods extract data or manipulate the contents of the underlying resource.
 * </p>
 * 
 * <p>
 * This class is an abstract class, and clients are intended to subclass and own their
 * implementation.
 * </p>
 */
public abstract class EnterpriseArtifactEdit extends ArtifactEdit implements WorkingCopyProvider {

	private WorkingCopyManager workingCopyManager = null;

	/**
	 * @param aHandle
	 * @param toAccessAsReadOnly
	 * @throws IllegalArgumentException
	 */
	public EnterpriseArtifactEdit(ComponentHandle aHandle, boolean toAccessAsReadOnly) throws IllegalArgumentException {
		super(aHandle, toAccessAsReadOnly);
		// TODO Auto-generated constructor stub
	}

	/**
	 * <p>
	 * Creates an instance facade for the given {@see ArtifactEditModel}.
	 * </p>
	 * <p>
	 * Clients that use this constructor are required to release their access of the EditModel when
	 * finished. Calling {@see ArtifactEdit#dispose()}will not touch the supplied EditModel.
	 * </p>
	 * 
	 * @param anArtifactEditModel
	 *            A valid, properly-accessed EditModel
	 */
	public EnterpriseArtifactEdit(ArtifactEditModel model) {
		super(model);
	}

	/**
	 * <p>
	 * Creates an instance facade for the given {@see WorkbenchComponent}.
	 * </p>
	 * <p>
	 * Instances of EnterpriseArtifactEdit that are returned through this method must be
	 * {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Note: This method is for internal use only. Clients should not call this method.
	 * </p>
	 * 
	 * @param aNature
	 *            A non-null {@see ModuleCoreNature}&nbsp;for an accessible project
	 * @param aModule
	 *            A non-null {@see WorkbenchComponent}&nbsp;pointing to a module from the given
	 *            {@see ModuleCoreNature}
	 */

	public EnterpriseArtifactEdit(ModuleCoreNature aNature, WorkbenchComponent aModule, boolean toAccessAsReadOnly) {
		super(aNature, aModule, toAccessAsReadOnly);
	}

	/**
	 * <p>
	 * Retrieves J2EE version information from deployment descriptor resource.
	 * </p>
	 * 
	 * @return An the J2EE Specification version of the underlying {@see WorkbenchComponent}
	 * 
	 */
	public abstract int getJ2EEVersion();

	/**
	 * <p>
	 * Retrieves a deployment descriptor resource from {@see ArtifactEditModel}using a defined URI.
	 * </p>
	 * 
	 * @return The correct deployment descriptor resource for the underlying
	 *         {@see WorkbenchComponent}
	 * 
	 */
	public abstract Resource getDeploymentDescriptorResource();

	/**
	 * <p>
	 * Obtains the root object from a deployment descriptor resource, the root object contains all
	 * other resource defined objects. Examples of a deployment descriptor root include:
	 * {@see org.eclipse.jst.j2ee.webapplication.WebApp},
	 * {@see org.eclipse.jst.j2ee.application.Application}, and
	 * {@see org.eclipse.jst.j2ee.ejb.EJBJar}
	 * </p>
	 * <p>
	 * Subclasses may extend this method to perform their own deployment descriptor creataion/
	 * retrieval.
	 * </p>
	 * 
	 * @return An EMF metamodel object representing the J2EE deployment descriptor
	 * 
	 */

	public EObject getDeploymentDescriptorRoot() {
		Resource res = getDeploymentDescriptorResource();
		return (EObject) res.getContents().get(0);
	}

	/**
	 * Returns a working copy managet
	 * 
	 * @return
	 */

	public WorkingCopyManager getWorkingCopyManager() {
		if (workingCopyManager == null)
			workingCopyManager = WorkingCopyManagerFactory.newRegisteredInstance();
		return workingCopyManager;
	}

	/**
	 * Returns the working copy remembered for the compilation unit.
	 * 
	 * @param input
	 *            ICompilationUnit
	 * @return the working copy of the compilation unit, or <code>null</code> if there is no
	 *         remembered working copy for this compilation unit
	 */
	public ICompilationUnit getWorkingCopy(ICompilationUnit cu, boolean forNewCU) throws org.eclipse.core.runtime.CoreException {
		if (isReadOnly())
			return null;
		return getWorkingCopyManager().getWorkingCopy(cu, forNewCU);
	}

	/**
	 * Returns the working copy remembered for the compilation unit encoded in the given editor
	 * input. Does not connect the edit model to the working copy.
	 * 
	 * @param input
	 *            ICompilationUnit
	 * @return the working copy of the compilation unit, or <code>null</code> if the input does
	 *         not encode an editor input, or if there is no remembered working copy for this
	 *         compilation unit
	 */
	public ICompilationUnit getExistingWorkingCopy(ICompilationUnit cu) throws org.eclipse.core.runtime.CoreException {
		return getWorkingCopyManager().getExistingWorkingCopy(cu);
	}

	public URI getModuleLocation(String moduleName) {
		WorkbenchComponent component = getWorkBenchComponent(moduleName);
		if (component != null)
			return component.getHandle();
		return null;


	}

	public WorkbenchComponent getWorkBenchComponent(String moduleName) {
		StructureEdit moduleCore = null;
		try {
			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			for (int i = 0; i < projects.length; i++) {
				moduleCore = StructureEdit.getStructureEditForRead(projects[i]);
				WorkbenchComponent wbComponent = moduleCore.findComponentByName(moduleName);
				if (wbComponent == null)
					continue;
				return wbComponent;
			}

		} catch (Exception e) {
			// todo

		} finally {
			if (moduleCore != null)
				moduleCore.dispose();
		}
		return null;

	}



	public IProject getProject(String moduleName) {
		StructureEdit moduleCore = null;
		WorkbenchComponent component = getWorkBenchComponent(moduleName);
		if (component != null)
			return StructureEdit.getContainingProject(component);
		return null;

	}



	/**
	 * This will delete
	 * 
	 * @cu from the workbench and fix the internal references for this working copy manager.
	 */
	public void delete(org.eclipse.jdt.core.ICompilationUnit cu, org.eclipse.core.runtime.IProgressMonitor monitor) {
		getWorkingCopyManager().delete(cu, monitor);
	}

	/**
	 * <p>
	 * Create an deployment descriptor resource if one does not get and return it. Subclasses should
	 * overwrite this method to create their own type of deployment descriptor
	 * </p>
	 * 
	 * @return an EObject
	 */

	public abstract EObject createModelRoot();

	/**
	 * <p>
	 * Create an deployment descriptor resource if one does not get and return it. Subclasses should
	 * overwrite this method to create their own type of deployment descriptor
	 * </p>
	 * 
	 * @param int
	 *            version of the component
	 * @return an EObject
	 */

	public abstract EObject createModelRoot(int version);
}