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
package org.eclipse.jst.j2ee.internal.validation;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.jst.j2ee.J2EEConstants;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.application.EjbModule;
import org.eclipse.jst.j2ee.application.Module;
import org.eclipse.jst.j2ee.application.WebModule;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.common.EjbRef;
import org.eclipse.jst.j2ee.common.MessageDestinationRef;
import org.eclipse.jst.j2ee.common.ResourceEnvRef;
import org.eclipse.jst.j2ee.common.ResourceRef;
import org.eclipse.jst.j2ee.common.SecurityRoleRef;
import org.eclipse.jst.j2ee.commonarchivecore.Archive;
import org.eclipse.jst.j2ee.commonarchivecore.EARFile;
import org.eclipse.jst.j2ee.commonarchivecore.File;
import org.eclipse.jst.j2ee.commonarchivecore.ModuleFile;
import org.eclipse.jst.j2ee.commonarchivecore.ValidateXmlCommand;
import org.eclipse.jst.j2ee.commonarchivecore.helpers.ArchiveConstants;
import org.eclipse.jst.j2ee.commonarchivecore.strategy.LoadStrategy;
import org.eclipse.jst.j2ee.commonarchivecore.util.ArchiveUtil;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EEImportConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.J2EELoadStrategyImpl;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.earcreation.ModuleMapHelper;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.ModuleMapping;
import org.eclipse.jst.j2ee.internal.earcreation.modulemap.UtilityJARMapping;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.model.validation.EarValidator;
import org.eclipse.jst.j2ee.moduleextension.EarModuleManager;
import org.eclipse.jst.j2ee.moduleextension.WebModuleExtension;
import org.eclipse.jst.j2ee.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.webservice.wsclient.ServiceRef;
import org.eclipse.wst.common.emfworkbench.WorkbenchResourceHelper;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.ServerCore;
import org.eclipse.wst.validation.core.IFileDelta;
import org.eclipse.wst.validation.core.IHelper;
import org.eclipse.wst.validation.core.IMessage;
import org.eclipse.wst.validation.core.IReporter;
import org.eclipse.wst.validation.core.Message;
import org.eclipse.wst.validation.core.MessageLimitException;
import org.eclipse.wst.validation.core.SeverityEnum;
import org.eclipse.wst.validation.core.ValidationException;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.WorkbenchURIConverter;


/**
 * Insert the type's description here. Creation date: (9/10/2001 2:11:02 PM)
 * 
 * @author: Administrator
 */
public class UIEarValidator extends EarValidator implements UIEarMessageConstants {
	public static final String VALIDATOR_ID = "org.eclipse.wst.validation.core.ear.workbenchimpl.UIEarValidator"; //$NON-NLS-1$
	public static final String MANIFEST_GROUP_NAME = "WSAD.EAR.MANIFEST"; //$NON-NLS-1$
	protected UIEarHelper earHelper;
	protected EAREditModel earEditModel;
	private EARNatureRuntime earNature;

	/**
	 * UIEarValidator constructor comment.
	 */
	public UIEarValidator() {
		super();
	}

	public Command createValidateXMLCommand() {

		ValidateXmlCommand cmd = (ValidateXmlCommand) super.createValidateXMLCommand();
		cmd.setValidateNested(false);
		return cmd;
	}

	protected String getResourceName() {
		return ((EarHelper) _helper).getProject().getName();
	}

	protected void duplicateProjectMapError(String earProjectName, String moduleUri, String projectName) {

		String[] params = new String[3];
		params[0] = projectName;
		params[1] = earProjectName;
		params[2] = moduleUri;
		addError(getBaseName(), DUPLICATE_MODULE_FOR_PROJECT_NAME_ERROR_, params, appDD);
	}

	public EAREditModel getEAREditModel(IProject earProject) {
		EARNatureRuntime nature = getEarNature(earProject);
		setEarNature(nature);
		if (nature != null)
			return nature.getEarEditModelForRead(this);
		return null;
	}

	/**
	 *  
	 */
	protected void cleanUpSubTaskMessages(EObject ref) {
		String groupName = EJB_REF_GROUP_NAME;
		if (ref instanceof EjbRef)
			ref = (EjbRef) ref;
		else if (ref instanceof ResourceRef) {
			ref = (ResourceRef) ref;
			groupName = RES_REF_GROUP_NAME;
		} else if (ref instanceof ServiceRef) {
			ref = (ServiceRef) ref;
			groupName = SERVICE_REF_GROUP_NAME;
		} else if (ref instanceof ResourceEnvRef) {
			ref = (ResourceEnvRef) ref;
			groupName = RES_ENV_REF_GROUP_NAME;
		} else if (ref instanceof SecurityRoleRef) {
			ref = (SecurityRoleRef) ref;
			groupName = SEC_ROLE_REF_GROUP_NAME;
		} else if (ref instanceof MessageDestinationRef) {
			ref = (MessageDestinationRef) ref;
			groupName = MESSAGE_REF_GROUP_NAME;
		}
		Resource res = ref.eResource();
		if (res != null) {
			IFile file = WorkbenchResourceHelper.getFile(res);
			if (file != null)
				_reporter.removeMessageSubset(this, file, groupName);
		}
	}

	protected void cleanUpAllRefSubTaskMessages(Resource res) {
		if (res != null) {
			IFile file = WorkbenchResourceHelper.getFile(res);
			if (file != null)
				_reporter.removeMessageSubset(this, file, EJB_REF_GROUP_NAME);
			_reporter.removeMessageSubset(this, file, RES_REF_GROUP_NAME);
			_reporter.removeMessageSubset(this, file, SERVICE_REF_GROUP_NAME);
			_reporter.removeMessageSubset(this, file, SEC_ROLE_REF_GROUP_NAME);
			_reporter.removeMessageSubset(this, file, MESSAGE_REF_GROUP_NAME);
			_reporter.removeMessageSubset(this, file, RES_ENV_REF_GROUP_NAME);
		}
	}

	/**
	 * Gets the ear nature.
	 * 
	 * @param IProject
	 *            earProject - The current ear project.
	 * @return EARNatureRuntime
	 */
	public EARNatureRuntime getEarNature(IProject earProject) {
		return EARNatureRuntime.getRuntime(earProject);
	}// EARNatureRuntime

	/**
	 * Insert the method's description here. Creation date: (9/10/2001 2:56:32 PM)
	 * 
	 * @return org.eclipse.wst.validation.core.core.ear.workbenchimpl.UIEarHelper
	 */
	public org.eclipse.jst.j2ee.internal.validation.UIEarHelper getEarHelper() {
		return earHelper;
	}

	protected void invalidClassPathEntryWarning(String entry, Archive anArchive) {
		String[] params = new String[2];
		params[0] = anArchive.getURI();
		params[1] = entry;
		addWarning(getBaseName(), INVALID_MANIFEST_CLASSPATH_ONE_WARN_, params, getManifestFile(anArchive), MANIFEST_GROUP_NAME);
	}

	protected void invalidClassPathEntryWarning(String entry, String resolvedEntry, Archive anArchive) {
		String[] params = new String[3];
		params[0] = anArchive.getURI();
		params[1] = entry;
		params[2] = resolvedEntry;
		addWarning(getBaseName(), INVALID_MANIFEST_CLASSPATH_TWO_WARN_, params, getManifestFile(anArchive), MANIFEST_GROUP_NAME);
	}

	protected void invalidDepedencyWarning(String entry, Archive anArchive, ModuleFile m) {
		String[] params = new String[3];
		params[0] = m.getURI();
		params[1] = entry;
		params[2] = anArchive.getURI();
		addWarning(getBaseName(), INVALID_MANIFEST_CLASSPATH_DEPENDENCY_WARN_, params, getManifestFile(anArchive), MANIFEST_GROUP_NAME);
	}

	/**
	 * Insert the method's description here. Creation date: (9/10/2001 2:56:32 PM)
	 * 
	 * @param newEarHelper
	 *            org.eclipse.wst.validation.core.core.ear.workbenchimpl.UIEarHelper
	 */
	public void setEarHelper(org.eclipse.jst.j2ee.internal.validation.UIEarHelper newEarHelper) {
		earHelper = newEarHelper;
	}

	public void validate(IHelper inHelper, IReporter inReporter, IFileDelta[] changedFiles) throws ValidationException {
		inReporter.removeAllMessages(this);
		earHelper = (UIEarHelper) inHelper;
		earEditModel = null;
		try {
			earEditModel = getEAREditModel(earHelper.getProject());
			if (earEditModel != null) {
				super.validate(earHelper, inReporter, changedFiles);
				validateModuleMaps();
				validateManifests();
				validateUtilJarMaps();
				validateUriAlreadyExistsInEar();
				validateDocType();
			}

		} catch (ValidationException ex) {
			throw ex;
		} catch (Exception e) {
			String[] param = new String[1];
			IProject project = earHelper.getProject();
			if (project != null)
				param[0] = project.getName();
			Logger.getLogger().logError(e);
			IMessage errorMsg = new Message(getBaseName(), SeverityEnum.HIGH_SEVERITY, EAR_VALIDATION_INTERNAL_ERROR_UI_, param);
			throw new ValidationException(errorMsg, e);
		} finally {
			if (earEditModel != null)
				earEditModel.releaseAccess(this);
			//Shouldn't this be done in the super class?
			if (earFile != null)
				earFile.close();
		}
	}

	protected IProject getProject(Archive anArchive, EARFile earFileParam) {
		LoadStrategy loader = anArchive.getLoadStrategy();
		if (!(loader instanceof J2EELoadStrategyImpl))
			return ModuleMapHelper.getProject(anArchive.getURI(), earFileParam);

		return ((J2EELoadStrategyImpl) loader).getProject();
	}

	public void validateManifests() throws ValidationException {
		if (earFile == null)
			return;

		List archives = earFile.getArchiveFiles();
		for (int i = 0; i < archives.size(); i++) {
			try {
				Archive anArchive = (Archive) archives.get(i);
				IProject project = getProject(anArchive, earFile);
				if (project != null && project.isAccessible()) {
					IFile target = getManifestFile(anArchive);
					if (target != null)
						_reporter.removeMessageSubset(this, target, MANIFEST_GROUP_NAME);
					validateManifestCase(anArchive);
					validateManifestLines(anArchive);
					validateManifestClasspath(anArchive);
				}
			} catch (MessageLimitException me) {
			}
		}
	}

	public void validateManifestCase(Archive anArchive) {
		String mfuri = ArchiveConstants.MANIFEST_URI;

		//Indicates a manifest file with the valid name exists,
		//nothing left to do
		if (anArchive.containsFile(mfuri))
			return;

		//Otherwise iterate the list of files
		//Ensure the archive is read-only first
		anArchive.getOptions().setIsReadOnly(true);
		List files = anArchive.getFiles();
		String uri = null;
		for (int i = 0; i < files.size(); i++) {
			File aFile = (File) files.get(i);
			uri = aFile.getURI();
			if (mfuri.equalsIgnoreCase(uri) && !mfuri.equals(uri)) {
				String[] params = {uri, anArchive.getURI()};
				IResource target = earHelper.getProject().getFile(J2EEConstants.MANIFEST_URI);
				addError(getBaseName(), INVALID_CASE_FOR_MANIFEST_ERROR_, params, target);
			}
		}

	}

	public void validateManifestClasspath(Archive anArchive) throws ValidationException {

		String[] cp = anArchive.getManifest().getClassPathTokenized();
		for (int i = 0; i < cp.length; i++) {
			String uri = ArchiveUtil.deriveEARRelativeURI(cp[i], anArchive);
			if (uri == null) {
				invalidClassPathEntryWarning(cp[i], anArchive);
				continue;
			}
			File f = null;
			IFile rf = null;
			try {
				if (uri.endsWith(J2EEImportConstants.IMPORTED_JAR_SUFFIX)) {
					J2EENature projectNature = earEditModel.getEARNature().getModuleProject(anArchive.getURI());
					if (projectNature != null) {
						rf = projectNature.getProject().getFile(uri);
						if (rf == null || !rf.exists()) {
							invalidClassPathEntryWarning(cp[i], uri, anArchive);
						}
					}
				} else
					f = earFile.getFile(uri);
			} catch (java.io.FileNotFoundException ex) {
				invalidClassPathEntryWarning(cp[i], earFile.getURI(), anArchive);
				continue;
			}
			if (f != null && f.isArchive() && anArchive.isModuleFile()) {
				Archive archive = (Archive) f;
				ModuleFile m = (ModuleFile) anArchive;
				if (!ArchiveUtil.isValidDependency(archive, m))
					invalidDepedencyWarning(cp[i], archive, m);
			}
		}
	}


	protected void validateManifestLines(Archive anArchive) throws ValidationException {
		if (anArchive == null)
			return;
		InputStream is = null;
		try {
			is = anArchive.getInputStream(ArchiveConstants.MANIFEST_URI);
			ManifestLineValidator lineVal = new ManifestLineValidator(is);
			lineVal.validate();
			addErrorsIfNecessary(anArchive, lineVal);
		} catch (FileNotFoundException ex) {
			return;
		} catch (IOException ex) {
			handleManifestException(ex, anArchive);
		} finally {
			if (is != null)
				try {
					is.close();
				} catch (IOException ex) {
					handleManifestException(ex, anArchive);
				}
		}
	}

	protected void addErrorsIfNecessary(Archive anArchive, ManifestLineValidator mfVal) {
		if (!mfVal.hasErrors())
			return;
		IFile target = getManifestFile(anArchive);
		if (!mfVal.endsWithLineBreak())
			addFileEndError(anArchive, mfVal, target);
		int[] lines = mfVal.getLineNumbersExceedingLimit();
		for (int i = 0; i < lines.length; i++) {
			addLineLengthError(anArchive, target, lines[i]);
		}
	}

	protected void addLineLengthError(Archive anArchive, IFile target, int lineNo) {
		String[] args = new String[2];
		args[0] = Integer.toString(lineNo);
		args[1] = anArchive.getURI();
		if (target != null)
			addError(getBaseName(), MANIFEST_LINE_EXCEEDS_LENGTH_ERROR_, args, target, MANIFEST_GROUP_NAME, lineNo);
		else
			addError(getBaseName(), MANIFEST_LINE_EXCEEDS_LENGTH_ERROR_, args, null, MANIFEST_GROUP_NAME);
	}

	protected void addFileEndError(Archive anArchive, ManifestLineValidator mfVal, IFile target) {
		String[] args = new String[]{anArchive.getURI()};
		if (target != null)
			addError(getBaseName(), MANIFEST_LINE_END_ERROR_, args, getManifestFile(anArchive), MANIFEST_GROUP_NAME, mfVal.getLineCount());
		else
			addError(getBaseName(), MANIFEST_LINE_END_ERROR_, args, MANIFEST_GROUP_NAME);
	}

	protected void handleManifestException(IOException ex, Archive anArchive) throws ValidationException {
		Logger.getLogger().logError(ex);
		IMessage message = new Message(getBaseName(), SeverityEnum.HIGH_SEVERITY, ERROR_READING_MANIFEST_ERROR_, new String[]{anArchive.getURI()});
		throw new ValidationException(message, ex);
	}

	/**
	 * Validates utiljar maps
	 */
	public void validateUtilJarMaps() {

		List utilJarModules = earEditModel.getUtilityJARMappings();

		if (!utilJarModules.isEmpty() || !utilJarModules.isEmpty()) {
			for (int i = 0; i < utilJarModules.size(); i++) {

				UtilityJARMapping aUtilJar = (UtilityJARMapping) utilJarModules.get(i);

				if (aUtilJar != null) {
					IProject project = J2EEPlugin.getWorkspace().getRoot().getProject(aUtilJar.getProjectName());
					if (project != null) {
						if (!project.exists()) {
							String[] params = new String[]{project.getName(), aUtilJar.getUri(), earHelper.getProject().getName()};
							addWarning(getBaseName(), PROJECT_DOES_NOT_EXIST_WARN_, params);
						} else {
							validateModuleProjectForValidServerTarget(project);
							if (!project.isOpen()) {
								String[] params = new String[]{project.getName()};
								addWarning(getBaseName(), PROJECT_IS_CLOSED_WARN_, params);
							}

						}// if

					}// if
				}// if
			}// for
		}// if
		validateDuplicateUtilJars(earEditModel);
		validateUtilJarNameCollision(earEditModel);
		validateUtilJarContainsNoSpaces(earEditModel);
	}// validateUtilJarMaps

	/**
	 * Checks if the util jar contains spaces or not.
	 * 
	 * @param EAREditModel
	 *            earEditModel - The ear editmodel.
	 */
	protected void validateUtilJarContainsNoSpaces(EAREditModel earEditModelParam) {
		List utilJars = earEditModelParam.getUtilityJARMappings();

		if (utilJars == null)
			return;

		for (int i = 0; i < utilJars.size(); i++) {
			UtilityJARMapping utilModule = (UtilityJARMapping) utilJars.get(i);

			if (utilModule != null) {
				if (utilModule.getUri() != null && utilModule.getUri().indexOf(" ") != -1) { //$NON-NLS-1$
					String[] params = new String[1];
					params[0] = utilModule.getUri();
					addError(getBaseName(), URI_CONTAINS_SPACES_ERROR_, params, appDD);
				}// if
			}// if
		}// for

	}// validateUtilJarContainsNoSpaces

	/**
	 * Validates if the a util jar has the same name as another module.
	 * 
	 * @param EAREditModel
	 *            earEditModel - The ear editmodel.
	 */
	protected void validateUtilJarNameCollision(EAREditModel earEditModelParam) {
		List utilJars = earEditModelParam.getUtilityJARMappings();

		if (utilJars == null)
			return;

		for (int i = 0; i < utilJars.size(); i++) {
			UtilityJARMapping utilModule = (UtilityJARMapping) utilJars.get(i);

			if (utilModule != null) {
				if (earEditModelParam.moduleMappingExists(utilModule.getUri())) {

					String[] params = new String[]{utilModule.getUri(), earEditModelParam.getEARNature().getProject().getName()};
					addError(getBaseName(), MESSAGE_UTIL_URI_NAME_COLLISION_ERROR_, params);

				} else if (utilModule.getProjectName() != null || utilModule.getProjectName().length() != 0) {
					if (earEditModelParam.moduleMappingExists(utilModule.getProjectName())) {
						String[] params = new String[]{utilModule.getUri(), utilModule.getProjectName()};
						addError(getBaseName(), MESSAGE_UTIL_PROJECT_NAME_COLLISION_ERROR_, params);
					}// if
				}// if
			}// if
		}// for

	}// validateUtilJarNameCollision


	/**
	 * validate is there are duplicate util jars.
	 * 
	 * @param EAREditModel
	 *            earEditModel - The ear editmodel
	 */
	protected void validateDuplicateUtilJars(EAREditModel earEditModelParam) {
		List utilJars = earEditModelParam.getUtilityJARMappings();
		Set visitedUtilUri = new HashSet();
		if (utilJars == null)
			return;
		for (int i = 0; i < utilJars.size(); i++) {
			UtilityJARMapping utilModule = (UtilityJARMapping) utilJars.get(i);
			if (utilModule != null) {
				if (visitedUtilUri.contains(utilModule.getUri())) {
					String projectName = utilModule.getProjectName();
					duplicateUtilError(earEditModelParam.getEARNature().getProject().getName(), utilModule.getUri(), projectName);
				} else
					visitedUtilUri.add(utilModule.getUri());
			} // if
		} // for
	} // validateModuleMapsDuplicateUtil

	/**
	 * Creates an error for duplicate util jars.
	 * 
	 * @param String
	 *            earProjectName - The ears project name.
	 * @param String
	 *            moduleUri - The modules uri.
	 * @param String
	 *            projectName - The project name.
	 */
	protected void duplicateUtilError(String earProjectName, String moduleUri, String projectName) {
		String[] params = new String[3];
		params[0] = projectName;
		params[1] = earProjectName;
		params[2] = moduleUri;
		addError(getBaseName(), DUPLICATE_UTILJAR_FOR_PROJECT_NAME_ERROR_, params);
	}// duplicateUtilError

	public void validateModuleMaps() {
		EList modules = appDD.getModules();
		if (!modules.isEmpty()) {
			for (int i = 0; i < modules.size(); i++) {
				Module module = (Module) modules.get(i);
				ModuleMapping modMap = earEditModel.getModuleMapping(module);
				if (modMap == null) {
					String[] params = new String[]{module.getUri(), earHelper.getProject().getName()};
					addWarning(getBaseName(), MISSING_PROJECT_FORMODULE_WARN_, params);
				} else {
					String projectName = modMap.getProjectName();
					if (projectName == null || projectName.length() == 0) {
						String[] params = new String[]{module.getUri(), earHelper.getProject().getName()};
						addWarning(getBaseName(), MISSING_PROJECT_FORMODULE_WARN_, params);
					} else {
						IProject project = J2EEPlugin.getWorkspace().getRoot().getProject(projectName);
						if (!project.exists()) {
							String[] params = new String[]{project.getName(), module.getUri(), earHelper.getProject().getName()};
							addWarning(getBaseName(), PROJECT_DOES_NOT_EXIST_WARN_, params);
						} else if (!project.isOpen()) {
							String[] params = new String[]{project.getName()};
							addWarning(getBaseName(), PROJECT_IS_CLOSED_WARN_, params);
						} else {
							validateModuleProjectsForValidNature(module, project);
							validateModuleProjectForValidServerTarget(project);
						}
					}
				}
				validateModuleURIExtension(module);
			}
		}
		validateEARServerTargetJ2EESpecLevel(earEditModel);
		validateModuleMapsDuplicateProjects(earEditModel);
	}


	/**
	 * @param project
	 */
	private void validateModuleProjectForValidServerTarget(IProject project) {
		IRuntime runtime = ServerCore.getProjectProperties(project).getRuntimeTarget();

		EARNatureRuntime nature = earEditModel.getEARNature();
		IProject earProject = nature.getProject();
		IRuntime earRuntime = ServerCore.getProjectProperties(earProject).getRuntimeTarget();
		if (earRuntime != null) {
			if (runtime != null) {
				if (runtime.getId() != earRuntime.getId()) {
					unmatachedServerTargetOnModuleWarning(project, earProject);
				}
				validateModuleServerTargetJ2EESpecLevel(project, runtime);
			} else {
				missingServerTargetOnModuleWarning(project, earProject);
			}
		} else if (runtime != null) {
			missingServerTargetOnEARWarning(project, earProject);
		}
	}

	private void missingServerTargetOnEARWarning(IProject project, IProject earProject) {
		String[] params = new String[]{earProject.getName(), project.getName()};
		addWarning(getBaseName(), NO_SERVER_TARGET_ON_EAR_WITH_MODULE_SERVER_TARGETS, params);
	}

	private void unmatachedServerTargetOnModuleWarning(IProject project, IProject earProject) {
		String[] params = new String[]{project.getName(), earProject.getName()};
		addWarning(getBaseName(), PROJECT_SERVER_TARGET_DOES_NOT_MATCH_EAR, params);
	}

	private void missingServerTargetOnModuleWarning(IProject project, IProject earProject) {
		String[] params = new String[]{project.getName(), earProject.getName()};
		addWarning(getBaseName(), NO_SERVER_TARGET_MODULE_IN_EAR_WITH_SERVER_TARGET, params);
	}

	/**
	 * @param earProject
	 */
	private void validateEARServerTargetJ2EESpecLevel(EAREditModel editModel) {
		EARNatureRuntime nature = earEditModel.getEARNature();
		IProject earProject = nature.getProject();
		IRuntime earRuntime = ServerCore.getProjectProperties(earProject).getRuntimeTarget();
		if (earRuntime != null) {
			try {
				if (earProject.hasNature(IEARNatureConstants.NATURE_ID) && nature.getModuleVersion() == J2EEVersionConstants.J2EE_1_4_ID && !earRuntime.getRuntimeType().getVersion().equals("6.0")) { //$NON-NLS-1$
					String[] params = new String[]{earProject.getName(), earRuntime.getName()};
					addError(getBaseName(), INVALID_EAR_SERVER_TARGET_FOR_14_SPEC_LEVEL, params, earProject);
				}
			} catch (CoreException ce) {
				Logger.getLogger().logError(ce);
			}
		}
	}

	/**
	 * @param project
	 */
	private void validateModuleServerTargetJ2EESpecLevel(IProject project, IRuntime runtime) {
		boolean is14SpecLevelProject = false;
		if (J2EENature.hasRuntime(project, new String[]{IWebNatureConstants.J2EE_NATURE_ID, IEJBNatureConstants.NATURE_ID, IApplicationClientNatureConstants.NATURE_ID})) {
			J2EENature j2eeNature = J2EENature.getRegisteredRuntime(project);
			is14SpecLevelProject = j2eeNature.getJ2EEVersion() == J2EEVersionConstants.J2EE_1_4_ID;
			if (is14SpecLevelProject && !runtime.getRuntimeType().getVersion().equals("6.0")) { //$NON-NLS-1$
				String[] params = new String[]{project.getName(), runtime.getName()};
				addError(getBaseName(), INVALID_MODULE_SERVER_TARGET_FOR_14_SPEC_LEVEL, params);
			}
		}
	}

	protected void validateModuleMapsDuplicateProjects(EAREditModel earEditModelParam) {
		java.util.List moduleMaps = earEditModelParam.getModuleMappings();
		java.util.Set visitedProjectNames = new java.util.HashSet();
		for (int i = 0; i < moduleMaps.size(); i++) {
			ModuleMapping map = (ModuleMapping) moduleMaps.get(i);
			if (map.getModule() != null) {
				String moduleUri = map.getModule().getUri();
				String earProjectName = earEditModelParam.getEARNature().getProject().getName();
				String projectName = map.getProjectName();
				if (visitedProjectNames.contains(projectName))
					duplicateProjectMapError(earProjectName, moduleUri, projectName);
				else
					visitedProjectNames.add(projectName);
			}
		}
	}

	protected void validateModuleURIExtension(Module module) {
		String newUri = module.getUri();
		if (newUri != null && newUri.length() > 0) {
			if (module instanceof EjbModule && !newUri.endsWith(".jar")) { //$NON-NLS-1$
				String[] params = new String[1];
				params[0] = module.getUri();
				IResource target = earHelper.getProject().getFile(ArchiveConstants.APPLICATION_DD_URI);
				addError(getBaseName(), INVALID_URI_FOR_MODULE_ERROR_, params, target);
			} else if (module instanceof WebModule && !newUri.endsWith(".war")) { //$NON-NLS-1$
				String[] params = new String[1];
				params[0] = module.getUri();
				IResource target = earHelper.getProject().getFile(ArchiveConstants.APPLICATION_DD_URI);
				addError(getBaseName(), INVALID_URI_FOR_MODULE_ERROR_, params, target);
			}
		}
	}

	public void validateModuleProjectsForValidNature(Module module, IProject project) {

		try {
			if (module.isWebModule()) {
				if (!project.hasNature(IWebNatureConstants.J2EE_NATURE_ID)) {
					String[] params = new String[2];
					params[0] = project.getName();
					params[1] = earHelper.getProject().getName();
					addWarning(getBaseName(), MISSING_WEBNATURE_FORMODULE_WARN_, params, appDD);
				} else {
					WebModuleExtension webExt = EarModuleManager.getWebModuleExtension();
					if (webExt != null && !(webExt.compareWebContextRoot(module, project))) {
						String[] params = new String[3];
						params[0] = module.getUri();
						params[1] = earHelper.getProject().getName();
						params[2] = project.getName();
						addWarning(getBaseName(), INVALID_CONTEXTROOT_WEBMODULE_WARN_, params, appDD);
					}
				}

			} else if (module.isEjbModule()) {
				if (!project.hasNature(IEJBNatureConstants.NATURE_ID)) {
					String[] params = new String[2];
					params[0] = project.getName();
					params[1] = earHelper.getProject().getName();
					addWarning(getBaseName(), MISSING_EJBNATURE_FORMODULE_WARN_, params);
				}
			} else if (module.isJavaModule()) {
				if (!ApplicationClientNatureRuntime.hasRuntime(project)) {
					String[] params = new String[2];
					params[0] = project.getName();
					params[1] = earHelper.getProject().getName();
					addWarning(getBaseName(), MISSING_CLIENTNATURE_FORMODULE_WARN_, params);
				}
			}
		} catch (org.eclipse.core.runtime.CoreException ce) {
			com.ibm.wtp.common.logger.proxy.Logger.getLogger().logError(ce);
		}

	}

	/*
	 * @see J2EEValidator#cleanup()
	 */
	public void cleanup() {
		if (earHelper != null)
			earHelper.closeEARFile();
	}

	protected IFile getManifestFile(Archive anArchive) {
		URIConverter conv = anArchive.getResourceSet().getURIConverter();
		if (conv instanceof WorkbenchURIConverter) {
			WorkbenchURIConverter wbConv = (WorkbenchURIConverter) conv;
			IContainer input = wbConv.getInputContainer();
			if (input == null)
				return null;
			IProject p = input.getProject();
			if (p == null || ProjectUtilities.isBinaryProject(p))
				return null;
			IFile result = J2EEProjectUtilities.getManifestFile(p);
			if (result != null && result.exists())
				return result;
		}
		return null;
	}

	/**
	 * Checks if the nature is consistent with doc type.
	 */
	protected void validateDocType() {
		//EARNatureRuntime nature = getEarNature(earHelper.getProject());
		EARNatureRuntime nature = getEarNature();
		if (nature == null)
			return;
		if (nature.getJ2EEVersion() >= J2EEVersionConstants.J2EE_1_3_ID && appDD.getVersionID() < J2EEVersionConstants.J2EE_1_3_ID) {
			String[] params = new String[3];
			params[0] = DOCTYPE_1_2;
			params[1] = getResourceName();
			params[2] = DOCTYPE_1_3;
			addError(getBaseName(), EAR_INVALID_DOC_TYPE_ERROR_, params, appDD);
		} else if (nature.getJ2EEVersion() < J2EEVersionConstants.J2EE_1_3_ID && appDD.getVersionID() >= J2EEVersionConstants.J2EE_1_3_ID) {
			String[] params = new String[3];
			params[0] = DOCTYPE_1_3;
			params[1] = getResourceName();
			params[2] = DOCTYPE_1_2;
			addError(getBaseName(), EAR_INVALID_DOC_TYPE_ERROR_, params, appDD);
		}// if
	}// validateDocType

	/**
	 * Validates that conflicting jar does not exist in the ear project.
	 */
	public void validateUriAlreadyExistsInEar() {

		List utilJars = earEditModel.getUtilityJARMappings();

		if (utilJars == null)
			return;

		for (int i = 0; i < utilJars.size(); i++) {
			UtilityJARMapping utilModule = (UtilityJARMapping) utilJars.get(i);

			if (utilModule != null && utilModule.getUri() != null) {
				IProject currentEARProject = earHelper.getProject();

				try {
					IFile exFile = currentEARProject.getFile(utilModule.getUri());
					if (exFile != null && exFile.exists()) {
						String[] params = new String[2];
						params[0] = utilModule.getUri();
						params[1] = currentEARProject.getName();
						addWarning(getBaseName(), URI_ALREADY_EXISTS_IN_EAR_WARN_, params, appDD);
					}// if
				} catch (IllegalArgumentException iae) {
					Logger.getLogger().logError(iae);
				}// try
			}// if
		}// for

	}// validateUriAlreadyExistsInEar

	private EARNatureRuntime getEarNature() {
		return earNature;
	}

	private void setEarNature(EARNatureRuntime runtime) {
		earNature = runtime;
	}

}// UIEarValidator
