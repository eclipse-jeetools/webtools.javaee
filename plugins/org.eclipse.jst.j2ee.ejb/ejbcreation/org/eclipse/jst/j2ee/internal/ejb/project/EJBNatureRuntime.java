/***************************************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: IBM Corporation - initial API and implementation
 **************************************************************************************************/
package org.eclipse.jst.j2ee.internal.ejb.project;

import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.jst.j2ee.application.ApplicationPackage;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.commonarchivecore.internal.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.internal.EJBJarFile;
import org.eclipse.jst.j2ee.commonarchivecore.internal.exception.OpenFailureException;
import org.eclipse.jst.j2ee.commonarchivecore.internal.helpers.ArchiveOptions;
import org.eclipse.jst.j2ee.commonarchivecore.internal.util.ArchiveUtil;
import org.eclipse.jst.j2ee.ejb.EJBJar;
import org.eclipse.jst.j2ee.ejb.internal.extensions.EJBExtManager;
import org.eclipse.jst.j2ee.ejb.internal.extensions.EJBExtension;
import org.eclipse.jst.j2ee.ejb.util.EJBAttributeMaintenanceFactoryImpl;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEEditModel;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.ejb.archiveoperations.EJBProjectLoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleNature;
import org.eclipse.jst.j2ee.internal.project.J2EEModuleWorkbenchURIConverterImpl;
import org.eclipse.jst.j2ee.internal.webservices.WebServiceEditModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.ComposedEditModel;
import org.eclipse.wst.common.internal.emfworkbench.integration.EditModel;

import com.ibm.wtp.emf.workbench.ProjectUtilities;

public class EJBNatureRuntime extends J2EEModuleNature implements IEJBNatureConstants {

	private static final String EJB_PROJECT_21_OVERLAY = "2_1_ovr"; //$NON-NLS-1$
	private static final String EJB_PROJECT_20_OVERLAY = "2_0_ovr"; //$NON-NLS-1$
	private static final String EJB_PROJECT_11_OVERLAY = "1_1_ovr"; //$NON-NLS-1$

	public static final String EJB_MAPPING_EDIT_MODEL_ID = "com.ibm.etools.mapping.editModel"; //$NON-NLS-1$
	public static final String EJB_COMPOSED_MAPPING_EDIT_MODEL_ID = "EJBComposedMappingEditingGroup"; //$NON-NLS-1$

	protected static Class workingCopyManagerClass;

	/**
	 * WebNature constructor comment.
	 */
	public EJBNatureRuntime() {
		super();
	}

	protected void addAdapterFactories(ResourceSet aContext) {
		super.addAdapterFactories(aContext);
		EJBExtension wsExtension = EJBExtManager.getEJBWsExt();
		if (wsExtension != null)
			wsExtension.addExtendedAdapterFactory(aContext);
		if (getJ2EEVersion() >= J2EEVersionConstants.J2EE_1_3_ID)
			aContext.getAdapterFactories().add(new EJBAttributeMaintenanceFactoryImpl());
	}

	public Archive asArchive() throws OpenFailureException {
		return asEJBJarFile();
	}

	public Archive asArchive(boolean shouldExportSource) throws OpenFailureException {
		return asEJBJarFile(shouldExportSource);
	}

	public EJBJarFile asEJBJarFile() throws OpenFailureException {
		return asEJBJarFile(true);
	}

	public EJBJarFile asEJBJarFile(boolean shouldExportSource) throws OpenFailureException {

		IProject proj = getProject();
		if (proj == null)
			return null;

		if (isBinaryProject()) {
			String location = ((J2EEModuleWorkbenchURIConverterImpl) getJ2EEWorkbenchURIConverter()).getInputJARLocation().toOSString();
			ArchiveOptions options = new ArchiveOptions();
			options.setIsReadOnly(true);
			return getCommonArchiveFactory().openEJB11JarFile(options, location);
		}
		EJBProjectLoadStrategyImpl loader = new EJBProjectLoadStrategyImpl(proj);
		loader.setExportSource(shouldExportSource);
		loader.setResourceSet(this.getResourceSet());
		return getCommonArchiveFactory().openEJB11JarFile(loader, proj.getName());
	}

	protected EditModel createCacheEditModel() {
		return getEJBEditModelForRead(this);
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature
	 */
	public Module createNewModule() {
		return ((ApplicationPackage) EPackage.Registry.INSTANCE.getEPackage(ApplicationPackage.eNS_URI)).getApplicationFactory().createEjbModule();
	}

	/**
	 * Create a new nature runtime from the project info
	 */
	//	public static EJBNatureRuntime createRuntime(EJBProjectInfo info) throws CoreException {
	//		IProject project = info.getProject();
	//		if (!hasRuntime(project)) {
	//			addNatureToProject(project, info.getNatureId());
	//			EJBNatureRuntime runtime = getRuntime(project);
	//			runtime.initializeFromInfo(info);
	//			return runtime;
	//		}
	//		return getRuntime(project);
	//	}
	/**
	 * Removes this nature from the project.
	 * 
	 * @see IProjectNature#deconfigure
	 */
	public void deconfigure() throws CoreException {
		super.deconfigure();
		// remove Validation Builder to EJB Projects' builder list
		ProjectUtilities.removeFromBuildSpec(J2EEPlugin.VALIDATION_BUILDER_ID, project);
		// remove LibCopy Builder to EJB Projects' builder list
		ProjectUtilities.removeFromBuildSpec(J2EEPlugin.LIBCOPY_BUILDER_ID, project);
	}

	public boolean ejbXmiResourceExists() {
		return fileExists(J2EEConstants.EJBJAR_DD_URI);
	}

	protected String getDefaultSourcePathString() {
		return IEJBNatureConstants.DEFAULT_EJB_MODULE_PATH;
	}

	/**
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getEditModelKey()
	 */
	public String getEditModelKey() {
		return EDIT_MODEL_ID;
	}

	/**
	 * Return an editing model used to read EJB resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public EJBEditModel getEJBEditModelForRead(Object accessorKey) {
		return getEJBEditModelForRead(accessorKey, null);
	}

	/**
	 * Return an editing model used to edit EJB resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public EJBEditModel getEJBEditModelForWrite(Object accessorKey) {
		return getEJBEditModelForWrite(accessorKey, null);
	}

	/**
	 * Return an editing model used to read EJB resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public EJBEditModel getEJBEditModelForRead(Object accessorKey, Map params) {
		return (EJBEditModel) getEditModelForRead(EDIT_MODEL_ID, accessorKey, params);
	}

	/**
	 * Return an editing model used to edit EJB resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public EJBEditModel getEJBEditModelForWrite(Object accessorKey, Map params) {
		return (EJBEditModel) getEditModelForWrite(EDIT_MODEL_ID, accessorKey, params);
	}

	/**
	 * Return an editing model used to read EJB Mapping resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public ComposedEditModel getComposedEJBMappingEditModel(String backendid, Object accessorKey) {
		ComposedEditModel result = null;
		// TODO Will be addressed shortly
		//		if (getEmfContext() != null)
		//			result = getEmfContext().getComposedEditModel(EJB_COMPOSED_MAPPING_EDIT_MODEL_ID + "_" +
		// backendid, accessorKey); //$NON-NLS-1$
		return result;
	}

	/**
	 * Return the root object, the ejb-jar, from the ejb-jar.xml DD.
	 * 
	 * used for Read-Only Purpose
	 */
	public EJBJar getEJBJar() {
		return ((EJBEditModel) getCacheEditModel()).getEJBJar();
	}

	public String getEjbModuleRelative(String fullPath) {
		if (fullPath != null) {
			if (getModuleRoot() != null) {
				String modulePath;
				if (fullPath.indexOf('/') == 0)
					modulePath = getModuleRoot().getFullPath().toOSString();
				else
					modulePath = getModuleRoot().getFullPath().makeRelative().toOSString();
				int indx = fullPath.indexOf(modulePath);
				if (indx != -1)
					return fullPath.substring(modulePath.length() + 1);
			}
		}
		return fullPath;
	}

	/**
	 * Important!!! Calling this method increments the use count of this model. When you are done
	 * accessing the model, call releaseFromRead() casting to a ReferencedResource first!
	 */
	public Resource getEjbXmiResource() {
		return getResource(URI.createURI(J2EEConstants.EJBJAR_DD_URI));
	}

	public IContainer getModuleRoot() {
		return getSourceFolder();
	}

	/**
	 * @see IJ2EENature
	 */
	public IContainer getModuleServerRoot() {
		return ProjectUtilities.getJavaProjectOutputContainer(project);
	}

	/**
	 * Return the nature's ID.
	 */
	public java.lang.String getNatureID() {
		return IEJBNatureConstants.NATURE_ID;
	}

	/**
	 * Return the ID of the plugin that this nature is contained within.
	 */
	protected java.lang.String getPluginID() {
		return J2EEPlugin.PLUGIN_ID; //$NON-NLS-1$
	}

	/**
	 * Return whether or not the project has a runtime created on it.
	 * 
	 * @return boolean
	 * @param project
	 *            org.eclipse.core.resources.IProject
	 */
	public static boolean hasRuntime(IProject project) {
		return hasRuntime(project, IEJBNatureConstants.EJB_NATURE_IDS);
	}

	/**
	 * Get an EJBNatureRuntime that corresponds to the supplied project.
	 * 
	 * @return org.eclipse.jst.j2ee.internal.internal.ejb.ejbproject.EJBNatureRuntime
	 * @param project
	 *            org.eclipse.core.resources.IProject
	 */
	public static EJBNatureRuntime getRuntime(IProject project) {
		return (EJBNatureRuntime) getRuntime(project, IEJBNatureConstants.EJB_NATURE_IDS);
	}

	/**
	 * @deprecated - Use getModuleVersion with J2EEVersionConstants
	 * @return
	 */
	public boolean isEJB2_0() {
		return getModuleVersion() >= J2EEVersionConstants.EJB_2_0_ID;
	}

	/**
	 * @deprecated - use the EJBEditModel#makeEjbXmiResource
	 */
	public Resource makeEjbXmiResource() {
		return createResource(J2EEConstants.EJBJAR_DD_URI_OBJ);
	}

	/*
	 * @see J2EENature#canBeBinary()
	 */
	public boolean canBeBinary() {
		return true;
	}

	public String getOverlayIconName() {
		switch (getModuleVersion()) {
			case J2EEVersionConstants.EJB_1_1_ID :
				return EJB_PROJECT_11_OVERLAY;
			case J2EEVersionConstants.EJB_2_0_ID :
				return EJB_PROJECT_20_OVERLAY;
			case J2EEVersionConstants.EJB_2_1_ID :
			default :
				return EJB_PROJECT_21_OVERLAY;
		}
	}

	public int getDeploymentDescriptorType() {
		return XMLResource.EJB_TYPE;
	}

	/**
	 * Returns the project that represents the EJB Client JAR for this module in an EAR, if a client
	 * JAR is defined.
	 */
	public IProject getDefinedEJBClientJARProject() {
		EJBJar jar = getEJBJar();
		String clientJAR = null;
		if (jar != null)
			clientJAR = jar.getEjbClientJar();
		if (clientJAR == null)
			return null;

		EARNatureRuntime[] ears = getReferencingEARProjects();
		if (ears == null || ears.length == 0)
			return null;

		return findClientProject(clientJAR, ears);
	}

	private IProject findClientProject(String clientJAR, EARNatureRuntime[] ears) {
		IWorkspaceRoot workspaceRoot = getProject().getWorkspace().getRoot();
		EARNatureRuntime runtime = null;
		EAREditModel earModel = null;
		String thisUri = null;
		String earRelativeUri = null;
		for (int i = 0; i < ears.length; i++) {
			runtime = ears[i];
			try {
				earModel = runtime.getEarEditModelForRead(this);
				ModuleMapping mapping = earModel.getModuleMapping(getProject());
				if (mapping == null)
					continue;
				Module aModule = mapping.getModule();
				thisUri = aModule.getUri();
				if (thisUri == null)
					continue;
				earRelativeUri = ArchiveUtil.deriveEARRelativeURI(clientJAR, thisUri);
				if (earRelativeUri == null)
					earRelativeUri = clientJAR;
				UtilityJARMapping jarMapping = earModel.getUtilityJARMapping(earRelativeUri);
				if (jarMapping == null)
					continue;

				return workspaceRoot.getProject(jarMapping.getProjectName());
			} finally {
				if (earModel != null)
					earModel.releaseAccess(this);
			}
		}
		return null;
	}

	/**
	 * Returns the project that represents the EJB Client JAR for this module in an EAR, or if no
	 * client JAR is defined, returns this project.
	 */
	public IProject getEJBClientJARProject() {
		IProject p = getDefinedEJBClientJARProject();
		if (p == null)
			return getProject();

		return p;
	}

	public boolean hasEJBClientJARProject() {
		return getDefinedEJBClientJARProject() != null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getDeploymentDescriptorRoot()
	 */
	public EObject getDeploymentDescriptorRoot() {
		return getEJBJar();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEVersion()
	 */
	public int getJ2EEVersion() {
		// TODO Auto-generated method stub
		int j2eeVersion;
		switch (getModuleVersion()) {
			case J2EEVersionConstants.EJB_1_1_ID :
				j2eeVersion = J2EEVersionConstants.J2EE_1_2_ID;
				break;
			case J2EEVersionConstants.EJB_2_0_ID :
				j2eeVersion = J2EEVersionConstants.J2EE_1_3_ID;
				break;
			default :
				j2eeVersion = J2EEVersionConstants.J2EE_1_4_ID;
		}
		return j2eeVersion;
	} /*
	   * (non-Javadoc)
	   * 
	   * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getVersionFromModuleFile()
	   */

	protected int getVersionFromModuleFile() {
		EJBJar ddRoot = getEJBJar();
		if (ddRoot != null) {
			return ddRoot.getVersionID();
		}
		return J2EEVersionConstants.EJB_2_1_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEEditModelForRead(java.lang.Object)
	 */
	public J2EEEditModel getJ2EEEditModelForRead(Object accessorKey) {
		return getEJBEditModelForRead(accessorKey);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.j2ee.internal.internal.j2eeproject.J2EENature#getJ2EEEditModelForWrite(java.lang.Object)
	 */
	public J2EEEditModel getJ2EEEditModelForWrite(Object accessorKey) {
		return getEJBEditModelForWrite(accessorKey);
	}

	/**
	 * Return an editing model used to read web service resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public WebServiceEditModel getWebServiceEditModelForRead(Object accessorKey, Map params) {
		return (WebServiceEditModel) getEditModelForRead(WEB_SERVICE_EDIT_MODEL_ID, accessorKey, params);
	}

	/**
	 * Return an editing model used to edit web service resources. Important!!! Calling this method
	 * increments the use count of this model. When you are done accessing the model, call
	 * releaseAccess()!
	 */
	public WebServiceEditModel getWebServiceEditModelForWrite(Object accessorKey, Map params) {
		return (WebServiceEditModel) getEditModelForWrite(WEB_SERVICE_EDIT_MODEL_ID, accessorKey, params);
	}
}