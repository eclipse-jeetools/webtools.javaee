/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.internal.modulecore.util;

import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jst.j2ee.application.Application;
import org.eclipse.jst.j2ee.application.ApplicationResource;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.wst.common.modulecore.ArtifactEditModel;
import org.eclipse.wst.common.modulecore.ModuleCore;
import org.eclipse.wst.common.modulecore.ModuleCoreNature;
import org.eclipse.wst.common.modulecore.UnresolveableURIException;
import org.eclipse.wst.common.modulecore.WorkbenchModule;

/**
 * <p>
 * EARArtifactEdit obtains an {@see org.eclipse.jst.j2ee.application.Application}&nbsp;metamodel.
 * The {@see org.eclipse.jst.j2ee.application.ApplicationResource}&nbsp; which stores the metamodel
 * is retrieved from the {@see org.eclipse.wst.common.modulecore.ArtifactEditModel}&nbsp;using a
 * cached constant (@see
 * org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants#APPLICATION_DD_URI). The
 * defined methods extract data or manipulate the contents of the underlying resource.
 * </p>
 */

public class EARArtifactEdit extends EnterpriseArtifactEdit {

	public static final Class ADAPTER_TYPE = EARArtifactEdit.class;
	/**
	 * <p>
	 * Identifier used to group and query common artifact edits.
	 * </p>
	 */
	public static String TYPE_ID = "jst.ear"; //$NON-NLS-1$
	

	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchModule}. Instances of EARArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an EARArtifactEdit facade for a specific {@see WorkbenchModule}&nbsp;that will not
	 * be used for editing. Invocations of any save*() API on an instance returned from this method
	 * will throw exceptions.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchModule}&nbsp;with a handle that resolves to an accessible
	 *            project in the workspace
	 * @return An instance of EARArtifactEdit that may only be used to read the underlying content
	 *         model
	 * @throws UnresolveableURIException
	 *             could not resolve uri.
	 */
	public static EARArtifactEdit getWebArtifactEditForRead(WorkbenchModule aModule) {
		try {
			if (isValidEARModule(aModule)) {
				IProject project = ModuleCore.getContainingProject(aModule.getHandle());
				ModuleCoreNature nature = ModuleCoreNature.getModuleCoreNature(project);
				return new EARArtifactEdit(nature, aModule, true);
			}
		} catch (UnresolveableURIException uue) {
		}
		return null;
	}


	/**
	 * <p>
	 * Returns an instance facade to manage the underlying edit model for the given
	 * {@see WorkbenchModule}. Instances of WebArtifactEdit that are returned through this method
	 * must be {@see #dispose()}ed of when no longer in use.
	 * </p>
	 * <p>
	 * Use to acquire an EARArtifactEdit facade for a specific {@see WorkbenchModule}&nbsp;that
	 * will be used for editing.
	 * </p>
	 * <p>
	 * <b>This method may return null. </b>
	 * </p>
	 * 
	 * @param aModule
	 *            A valid {@see WorkbenchModule}&nbsp;with a handle that resolves to an accessible
	 *            project in the workspace
	 * @return An instance of EARArtifactEdit that may be used to modify and persist changes to the
	 *         underlying content model
	 */
	public static EARArtifactEdit getWebArtifactEditForWrite(WorkbenchModule aModule) {
		try {
			if (isValidEARModule(aModule)) {
				IProject project = ModuleCore.getContainingProject(aModule.getHandle());
				ModuleCoreNature nature = ModuleCoreNature.getModuleCoreNature(project);
				return new EARArtifactEdit(nature, aModule, false);
			}
		} catch (UnresolveableURIException uue) {
		}
		return null;
	}

	/**
	 * @param module
	 *            A {@see WorkbenchModule}
	 * @return True if the supplied module
	 *         {@see ArtifactEdit#isValidEditableModule(WorkbenchModule)}and the moduleTypeId is a
	 *         JST module
	 */
	public static boolean isValidEARModule(WorkbenchModule aModule) throws UnresolveableURIException {
		if (!isValidEditableModule(aModule))
			return false;
		/* and match the JST_WEB_MODULE type */
		if (!TYPE_ID.equals(aModule.getModuleType().getModuleTypeId()))
			return false;
		return true;
	}

	/**
	 * <p>
	 * Creates an instance facade for the given {@see ArtifactEditModel}.
	 * </p>
	 * 
	 * @param anArtifactEditModel
	 */
	public EARArtifactEdit(ArtifactEditModel model) {
		super(model);
	}	
	
	/**
	 * <p>
	 * Creates an instance facade for the given {@see ArtifactEditModel}
	 * </p>
	 * 
	 * @param aNature
	 *            A non-null {@see ModuleCoreNature}for an accessible project
	 * @param aModule
	 *            A non-null {@see WorkbenchModule}pointing to a module from the given
	 *            {@see ModuleCoreNature}
	 */


	public EARArtifactEdit(ModuleCoreNature aNature, WorkbenchModule aModule, boolean toAccessAsReadOnly) {
		super(aNature, aModule, toAccessAsReadOnly);
	}

	/**
	 * <p>
	 * Retrieves J2EE version information from ApplicationResource.
	 * </p>
	 * 
	 * @return an integer representation of a J2EE Spec version
	 *  
	 */
	public int getJ2EEVersion() {
		return getApplicationXmiResource().getJ2EEVersionID();
	}

	/**
	 * 
	 * @return ApplicationResource from (@link getDeploymentDescriptorResource())
	 *  
	 */

	public ApplicationResource getApplicationXmiResource() {
		return (ApplicationResource) getDeploymentDescriptorResource();
	}

	/**
	 * <p>
	 * Obtains the Application {@see Application}root object from the {@see ApplicationResource},
	 * the root object contains all other resource defined objects.
	 * </p>
	 * 
	 * @return Application
	 *  
	 */

	public Application getApplication() {
		return (Application) getDeploymentDescriptorRoot();
	}

	/**
	 * <p>
	 * Retrieves the resource from the {@see ArtifactEditModel}
	 * </p>
	 * 
	 * @return Resource
	 *  
	 */

	public Resource getDeploymentDescriptorResource() {
		return getArtifactEditModel().getResource(URI.createURI(J2EEConstants.APPLICATION_DD_URI));
	}
}