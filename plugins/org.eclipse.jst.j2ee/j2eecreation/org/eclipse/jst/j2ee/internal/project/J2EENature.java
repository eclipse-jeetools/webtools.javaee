/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.project;



import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.impl.J2EEResourceFactoryRegistry;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModelNature;
import org.eclipse.wst.server.core.IModule;

import com.ibm.wtp.emf.workbench.EMFWorkbenchContextBase;
import com.ibm.wtp.emf.workbench.ISynchronizerExtender;
import com.ibm.wtp.emf.workbench.ProjectResourceSet;
import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.ResourceSetWorkbenchSynchronizer;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverter;

/**
 * @deprecated
 * Use
 * <p>
 * 		ArtifactEdit
 * </p>
 * 
 */

public abstract class J2EENature extends EditModelNature implements ISynchronizerExtender {

	/** Used to keep documents loaded */
	private EditModel cacheEditModel;

	protected IModule module;
	protected int moduleVersion;
//	protected J2EESettings j2eeSettings;

	private static final String[] J2EE_NATURE_IDS = {IEJBNatureConstants.NATURE_ID, IEARNatureConstants.NATURE_ID, IWebNatureConstants.J2EE_NATURE_ID, IApplicationClientNatureConstants.NATURE_ID, IConnectorNatureConstants.NATURE_ID};

	/**
	 * J2EENature constructor comment.
	 */
	public J2EENature() {
	}

	public abstract String getOverlayIconName();

	/**
	 * Create the folders for the project we have just created.
	 * 
	 * @exception com.ibm.itp.core.api.resources.CoreException
	 *                The exception description.
	 */
	protected abstract void createFolders() throws CoreException;

	protected J2EEWorkbenchURIConverterImpl getJ2EEWorkbenchURIConverter() {
		WorkbenchURIConverter conv = getWorkbenchURIConverter();
		if (conv instanceof J2EEWorkbenchURIConverterImpl)
			return (J2EEWorkbenchURIConverterImpl) conv;
		return null;
	}

	/**
	 * Return the folder where the meta data documents are stored
	 */
	public IFolder getMetaFolder() {
		if (getMetaPath() != null)
			return getProject().getFolder(getMetaPath());
		return null;
	}

	/**
	 * Return the path to the folder in which meta-data is stored; subclasses should override
	 * getMofRoot() and getMetaPathKey()
	 */
	protected IPath getMetaPath() {
		IContainer mofRoot = getEMFRoot();
		if (mofRoot == null)
			return null;
		return mofRoot.getProjectRelativePath().append(getMetaPathKey());
	}

	protected String getMetaPathKey() {
		return ArchiveConstants.META_INF;
	}

	public static J2EENature getRuntime(IProject project, String natureId) {
		if (project == null)
			return null;
		try {
			return (J2EENature) project.getNature(natureId);
		} catch (CoreException e) {
			return null;
		}
	}

	public static J2EENature getRuntime(IProject project, String[] possibleNatureIds) {
		if (project != null && project.isAccessible()) {
			for (int i = 0; i < possibleNatureIds.length; i++) {
				J2EENature nature = getRuntime(project, possibleNatureIds[i]);
				if (nature != null)
					return nature;
			}
		}
		return null;
	}


	/**
	 * Answer the root container for the j2ee module, under which the relative path of all resources
	 * are equivalent to the uris within a j2ee module JAR file; used for the server to run from
	 * 
	 * The default is to return the IProject itself
	 */
	public IContainer getModuleServerRoot() {
		return getProject();
	}

//	protected J2EESettings getJ2EESettings() {
//		if (j2eeSettings == null) {
//			j2eeSettings = new J2EESettings(getProject());
//		}
//		return j2eeSettings;
//	}

	protected J2EEWorkbenchURIConverterImpl initializeWorbenchURIConverter(ProjectResourceSet set) {
		return new J2EEWorkbenchURIConverterImpl(this, set.getSynchronizer());
	}

	/*
	 * @deprecated - Use getJ2EEVersion() with J2EEVersionConstants
	 * @see IJ2EENature#isJ2EEVersionN() Subclasses should override when they have a nature that can
	 *      support this specification level.
	 */
	public boolean isJ2EE1_3() {
		return getJ2EEVersion() >= J2EEVersionConstants.J2EE_1_3_ID;
	}

	/**
	 * Primary Contribute to nature
	 */
	public void primaryContributeToContext(EMFWorkbenchContextBase aNature) {
		if (emfContext == aNature)
			return;
		emfContext = aNature;
		getEmfContext().setDefaultToMOF5Compatibility(true);
		//Overriding superclass to use our own URI converter, which knows about binary projects
		ProjectResourceSet set = aNature.getResourceSet();
		set.setResourceFactoryRegistry(new J2EEResourceFactoryRegistry());
		WorkbenchURIConverter conv = initializeWorbenchURIConverter(set);
		set.setURIConverter(conv);
		initializeCacheEditModel();
		addAdapterFactories(set);
		set.getSynchronizer().addExtender(this); //added so we can be informed of closes to the
		// project.
		new J2EEResourceDependencyRegister(set); //This must be done after the URIConverter is
		// created.
	}

	/**
	 * Secondary Contribute to nature
	 */
	public void secondaryContributeToContext(EMFWorkbenchContextBase aNature) {
		primaryContributeToContext(aNature);
	}

	/**
	 * Add Adaptor factories to aContext which is now being used for this nature.
	 */
	protected void addAdapterFactories(ResourceSet aSet) {
		// module natures will override
	}

	/**
	 * Gets the module.
	 * 
	 * @return Returns an IModule
	 */
	public IModule getModule() {
		return module;
	}

	/**
	 * Sets the module.
	 * 
	 * @param module
	 *            The module to set
	 */
	public void setModule(IModule module) {
		this.module = module;
	}

	/**
	 * This method is used by migration to set the properties of this nature on the on
	 * <code>anotherNature</code>. TODO - See if this can be deleted
	 */
	public void replaceWith(J2EENature anotherNature) {
		if (anotherNature != null && anotherNature.getDeploymentDescriptorType() == getDeploymentDescriptorType()) {
			//Move EMFNature
			if (emfContext != null) {
				anotherNature.emfContext = emfContext;
				ResourceSetWorkbenchSynchronizer synchronizer = emfContext.getResourceSet().getSynchronizer();
				if (synchronizer != null) {
					synchronizer.removeExtender(this);
					synchronizer.addExtender(anotherNature);
				}

			}
			anotherNature.setCacheEditModel(cacheEditModel);
		}
	}

	public int getJ2EEVersion() {
		return J2EEVersionConstants.J2EE_1_4_ID;
	}

	public String getJ2EEVersionText() {
		switch (getJ2EEVersion()) {
			case J2EEVersionConstants.J2EE_1_2_ID :
				return J2EEVersionConstants.VERSION_1_2_TEXT;
			case J2EEVersionConstants.J2EE_1_3_ID :
				return J2EEVersionConstants.VERSION_1_3_TEXT;
			case J2EEVersionConstants.J2EE_1_4_ID :
			default :
				return J2EEVersionConstants.VERSION_1_4_TEXT;
		}
	}


	/**
	 * @see org.eclipse.jem.internal.java.plugin.AbstractJavaMOFNatureRuntime#primConfigure()
	 */
	protected void primConfigure() throws CoreException {
		super.primConfigure();
		// The two builders below are common to all J2EE project types
		// add Validation Builder to Web Projects' builder list
		ProjectUtilities.addToBuildSpec(J2EEPlugin.VALIDATION_BUILDER_ID, project);
		// add LibCopy Builder to Projects' builder list
		// Note: since this is the last nature added, we are assuming it will
		// be after the Java builder. May need to be more explicit about this.

	}

	/**
	 * @return The uri of the dd resource for the J2EE project
	 */
	public String getDeploymentDescriptorURI() {
		switch (getDeploymentDescriptorType()) {
			case XMLResource.APPLICATION_TYPE :
				return ArchiveConstants.APPLICATION_DD_URI;
			case XMLResource.APP_CLIENT_TYPE :
				return ArchiveConstants.APP_CLIENT_DD_URI;
			case XMLResource.EJB_TYPE :
				return ArchiveConstants.EJBJAR_DD_URI;
			case XMLResource.RAR_TYPE :
				return ArchiveConstants.RAR_DD_URI;
			case XMLResource.WEB_APP_TYPE :
				return ArchiveConstants.WEBAPP_DD_URI;
			default :
				return null;
		}
	}

	/**
	 * Return a EMFNature based on the natures that have been configured.
	 * 
	 * @return EMFNature
	 * @param project
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	public static J2EENature getRegisteredRuntime(IProject project) {
		return getRuntime(project, J2EE_NATURE_IDS);
	}

	/**
	 * Return a EMFFNatureID based on the natures that have been configured.
	 * 
	 * @return String
	 * @param project
	 *            com.ibm.itp.core.api.resources.IProject
	 */
	public static String getRegisteredRuntimeID(IProject project) {
		String natureID = null;
		J2EENature nature = getRegisteredRuntime(project);
		if (nature != null)
			natureID = nature.getNatureID();
		return natureID;
	}

	/**
	 * Return a "virtual" archive on this nature's project; used for export
	 */
	public abstract Archive asArchive() throws OpenFailureException;

	/**
	 * Return a "virtual" archive on this nature's project; used for export
	 */
	public abstract Archive asArchive(boolean shouldExportSource) throws OpenFailureException;

	/**
	 * This will be the type of the deployment descriptor docuemnt. Subclasses should override if
	 * they have a deployment descriptor.
	 * 
	 * @see XMLResource#APP_CLIENT_TYPE
	 * @see XMLResource#APPLICATION_TYPE
	 * @see XMLResource#EJB_TYPE
	 * @see XMLResource#WEB_APP_TYPE
	 * @see XMLResource#RAR_TYPE
	 */
	public abstract int getDeploymentDescriptorType();

	public abstract EObject getDeploymentDescriptorRoot();

	/**
	 * @param i
	 */
	public void setModuleVersion(int aModuleVersion) throws CoreException {
		if (aModuleVersion == moduleVersion)
			return;
//		To do : Needs rework  for flexibile project ModuleCore.getFirstArtifactEditForRead		
//		J2EESettings settings = getJ2EESettings();
//		settings.setModuleVersion(aModuleVersion);
//		settings.write();
		moduleVersion = aModuleVersion;
	}

	/**
	 * Compare with J2EEVersionConstants
	 */
	public int getModuleVersion() {
//		To do : Needs rework  for flexibile project ModuleCore.getFirstArtifactEditForRead
		
//		if (moduleVersion == 0) {
//			if (getJ2EESettings().getModuleVersion() == 0)
//				try {
//					setModuleVersion(getVersionFromModuleFile());
//				} catch (CoreException e) {
//					Logger.getLogger().logError(e);
//				}
//		}
//		moduleVersion = getJ2EESettings().getModuleVersion();
		moduleVersion =22;
		return moduleVersion;
	}

	/**
	 * Compare with J2EEVersionConstants
	 */
	public String getModuleVersionText() {
		switch (getModuleVersion()) {
			case J2EEVersionConstants.VERSION_1_0 :
				return J2EEVersionConstants.VERSION_1_0_TEXT;
			case J2EEVersionConstants.VERSION_1_1 :
				return J2EEVersionConstants.VERSION_1_1_TEXT;
			case J2EEVersionConstants.VERSION_1_2 :
				return J2EEVersionConstants.VERSION_1_2_TEXT;
			case J2EEVersionConstants.VERSION_1_3 :
				return J2EEVersionConstants.VERSION_1_3_TEXT;
			case J2EEVersionConstants.VERSION_1_4 :
				return J2EEVersionConstants.VERSION_1_4_TEXT;
			case J2EEVersionConstants.VERSION_1_5 :
				return J2EEVersionConstants.VERSION_1_5_TEXT;
			case J2EEVersionConstants.VERSION_2_0 :
				return J2EEVersionConstants.VERSION_2_0_TEXT;
			case J2EEVersionConstants.VERSION_2_1 :
				return J2EEVersionConstants.VERSION_2_1_TEXT;
			case J2EEVersionConstants.VERSION_2_2 :
				return J2EEVersionConstants.VERSION_2_2_TEXT;
			case J2EEVersionConstants.VERSION_2_3 :
				return J2EEVersionConstants.VERSION_2_3_TEXT;
			case J2EEVersionConstants.VERSION_2_4 :
				return J2EEVersionConstants.VERSION_2_4_TEXT;
			case J2EEVersionConstants.VERSION_2_5 :
				return J2EEVersionConstants.VERSION_2_5_TEXT;
			default :
				return "0.0"; //$NON-NLS-1$
		}
	}

	/**
	 * @return the version from he archive
	 */
	protected abstract int getVersionFromModuleFile();

	protected void initializeCacheEditModel() {
		setCacheEditModel(createCacheEditModel());
	}

	/**
	 * Factory method to create a subtype specific edit model; subclasses that wish to keep
	 * resources from being removed except when rolling back changes should override this method.
	 */
	protected abstract EditModel createCacheEditModel();

	/**
	 * Insert the method's description here. Creation date: (9/18/2001 11:54:45 AM)
	 * 
	 * @param newCacheEditModel
	 *            org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel
	 */
	protected void setCacheEditModel(EditModel newCacheEditModel) {
		cacheEditModel = newCacheEditModel;
	}

	/**
	 * Insert the method's description here. Creation date: (9/18/2001 11:54:45 AM)
	 * 
	 * @param newCacheEditModel
	 *            org.eclipse.jst.j2ee.internal.internal.workbench.J2EEEditModel
	 */
	protected EditModel getCacheEditModel() {
		if (emfContext == null)
			// Lazy initialize of the EMF context that will force the cache edit model
			getEmfContext();
		return cacheEditModel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.ISynchronizerExtender#projectChanged(org.eclipse.core.resources.IResourceDelta)
	 */
	public void projectChanged(IResourceDelta delta) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.internal.emfworkbench.ISynchronizerExtender#projectClosed()
	 */
	public void projectClosed() {
		this.emfContext = null;
	}

	/**
	 * Returns a default edit model for the nature
	 * 
	 * @param accessorKey
	 * @return
	 */
	public abstract J2EEEditModel getJ2EEEditModelForRead(Object accessorKey);

	/**
	 * Returns a default edit model for the nature
	 * 
	 * @param accessorKey
	 * @return
	 */
	public abstract J2EEEditModel getJ2EEEditModelForWrite(Object accessorKey);

	/**
	 * Return the default EditModel id for the specific J2EENature.
	 * 
	 * @return
	 */
	public abstract String getEditModelKey();


	public static J2EEEditModel getEditModelForProject(IProject project, Object accessorKey) {
		if (project == null || !project.isAccessible())
			return null;
		J2EEEditModel editModel = null;
		J2EENature nature = J2EENature.getRegisteredRuntime(project);
		if (nature != null) {
			editModel = nature.getJ2EEEditModelForRead(accessorKey);
		}
		return editModel;
	}
}