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
package org.eclipse.jst.j2ee.internal.migration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jem.java.JavaClass;
import org.eclipse.jem.java.JavaParameter;
import org.eclipse.jem.java.JavaURL;
import org.eclipse.jem.java.Method;
import org.eclipse.jst.j2ee.J2EEVersionConstants;
import org.eclipse.jst.j2ee.applicationclient.creation.ApplicationClientNatureRuntime;
import org.eclipse.jst.j2ee.applicationclient.creation.IApplicationClientNatureConstants;
import org.eclipse.jst.j2ee.common.XMLResource;
import org.eclipse.jst.j2ee.ejb.CMPAttribute;
import org.eclipse.jst.j2ee.ejb.CommonRelationship;
import org.eclipse.jst.j2ee.ejb.CommonRelationshipRole;
import org.eclipse.jst.j2ee.ejb.ContainerManagedEntity;
import org.eclipse.jst.j2ee.ejb.EnterpriseBean;
import org.eclipse.jst.j2ee.ejb.extensions.BackendMigration;
import org.eclipse.jst.j2ee.ejb.extensions.BackendMigrationExtensionReader;
import org.eclipse.jst.j2ee.ejb.extensions.DeleteDeployCode;
import org.eclipse.jst.j2ee.ejb.extensions.DeleteDeployCodeExtensionReader;
import org.eclipse.jst.j2ee.ejb.impl.LocalModelledPersistentAttributeFilter;
import org.eclipse.jst.j2ee.internal.earcreation.EAREditModel;
import org.eclipse.jst.j2ee.internal.earcreation.EARNatureRuntime;
import org.eclipse.jst.j2ee.internal.earcreation.IEARNatureConstants;
import org.eclipse.jst.j2ee.internal.ejb.commands.CreatePersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.DeletePersistentAttributeCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.IRootCommand;
import org.eclipse.jst.j2ee.internal.ejb.commands.UpdateContainerManagedEntityCommand;
import org.eclipse.jst.j2ee.internal.ejb.creation.CreateClientViewOperation;
import org.eclipse.jst.j2ee.internal.ejb.creation.DeleteClientViewOperation;
import org.eclipse.jst.j2ee.internal.ejb.operations.EjbProgressOperation;
import org.eclipse.jst.j2ee.internal.ejb.operations.RemoveRelationshipDataModel;
import org.eclipse.jst.j2ee.internal.ejb.operations.RemoveRelationshipDataModelOperation;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBEditModel;
import org.eclipse.jst.j2ee.internal.ejb.project.EJBNatureRuntime;
import org.eclipse.jst.j2ee.internal.ejb.project.operations.ClientViewModel;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreateEJB20RelationshipCommand;
import org.eclipse.jst.j2ee.internal.ejb20.commands.CreatePersistent20RoleCommand;
import org.eclipse.jst.j2ee.internal.jca.operations.ConnectorNatureRuntime;
import org.eclipse.jst.j2ee.internal.migration.actions.IJ2EEMigrationConstants;
import org.eclipse.jst.j2ee.internal.project.IConnectorNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IEJBNatureConstants;
import org.eclipse.jst.j2ee.internal.project.IWebNatureConstants;
import org.eclipse.jst.j2ee.internal.project.J2EENature;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetDataModel;
import org.eclipse.jst.j2ee.internal.servertarget.ServerTargetOperation;
import org.eclipse.jst.j2ee.internal.web.operations.J2EEWebNatureRuntime;
import org.eclipse.jst.j2ee.migration.J2EEMigrationStatus;
import org.eclipse.jst.j2ee.migration.J2EESpecificationMigrationConstants;
import org.eclipse.jst.j2ee.migration.J2EESpecificationMigrator;
import org.eclipse.jst.j2ee.migration.SpecificationMigrator;
import org.eclipse.wst.common.framework.operation.IHeadlessRunnableWithProgress;
import org.eclipse.wst.common.framework.operation.IOperationHandler;
import org.eclipse.wst.common.internal.emfworkbench.operation.EditModelOperationDataModel;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IServer;
import org.eclipse.wst.server.core.IServerWorkingCopy;
import org.eclipse.wst.server.core.ServerUtil;
import org.eclipse.wst.server.core.model.IModule;

import com.ibm.wtp.common.logger.proxy.Logger;
import com.ibm.wtp.emf.workbench.ProjectResourceSet;
import com.ibm.wtp.emf.workbench.ProjectUtilities;
import com.ibm.wtp.emf.workbench.WorkbenchResourceHelperBase;

/**
 * @author DABERG
 */
public class J2EEProjectMetadataMigrationOperation extends AbstractJ2EEMigrationOperation implements IJ2EEMigrationConstants {
	protected static final String COLLECTION_TYPE = "java.util.Collection"; //$NON-NLS-1$
	protected J2EEMigrationConfig currentConfig;
	protected EARNatureRuntime earNature;
	protected EJBLinkMigrator linkMigrator;
	protected List migrationConfigs;
	protected IProgressMonitor progressMonitor;
	protected String targetVersion;

	public J2EEProjectMetadataMigrationOperation(J2EEMigrationConfig config, String aTargetVersion, IOperationHandler handler) {
		super(config, handler);
		if (config.isComposed()) {
			ComposedMigrationConfig composed = (ComposedMigrationConfig) config;
			migrationConfigs = composed.getAllVersionMigratableConfigs();
			if (composed.isEAR())
				earNature = EARNatureRuntime.getRuntime(composed.getTargetProject());
		} else
			migrationConfigs = Collections.singletonList(config);
		targetVersion = aTargetVersion;
		setCurrentProject(config.getTargetProject());
	}

	/**
	 * Method createDefaultClientViewModel.
	 * 
	 * @return ClientViewModel
	 */
	private ClientViewModel createDefaultClientViewModel() {
		EJBJarMigrationConfig config = getEJBJarConfig();
		ClientViewModel model = new ClientViewModel();
		model.setShouldCreateLocalCommand(true);
		model.setLocalSelected(true);
		model.setLocalSuffix(config.getLocalClientViewSuffix());
		if (config.isDeleteRemoteClientView()) {
			model.setShouldDeleteRemote(true);
			model.setShouldDelete(true); //delete the actual classes
		}
		return model;
	}

	/**
	 * Method createRelationshipCreationCommand.
	 * 
	 * @param config
	 * @param rel
	 */
	private void createRelationshipCreationCommand(EJBJarMigrationConfig config, CommonRelationship rel, CreatePersistent20RoleCommand firstRole, CreatePersistent20RoleCommand secondRole) {
		CreateEJB20RelationshipCommand cmd = new CreateEJB20RelationshipCommand(rel.getName(), firstRole, secondRole);
		config.appendCreateCommand(cmd);
	}

	/**
	 * Method createRelationshipRoleCreationCommand.
	 * 
	 * @param config
	 * @param ejbRelationshipRole
	 */
	private CreatePersistent20RoleCommand createRelationshipRoleCreationCommand(EJBJarMigrationConfig config, CommonRelationshipRole role) {
		ContainerManagedEntity sourceCmp = role.getSourceEntity();
		IRootCommand root = config.getCreateCommand(sourceCmp);
		if (root == null)
			return null; //This should not happen.
		CreatePersistent20RoleCommand cmd = new CreatePersistent20RoleCommand(root, role.getName());
		CommonRelationshipRole op = role.getOppositeAsCommonRole();
		cmd.setIsMany(op.isMany());
		if (role.isNavigable()) {
			cmd.setCmrFieldName(role.getName());
			if (role.isMany())
				cmd.setCmrFieldCollectionTypeName(COLLECTION_TYPE);
		}
		cmd.setForward(role.isForward());
		cmd.setKey(role.isKey());
		return cmd;
	}

	/**
	 * @see org.eclipse.jst.j2ee.operations.HeadlessJ2EEOperation#execute(IProgressMonitor)
	 */
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException {
		progressMonitor = monitor;
		if (migrationConfigs == null && migrationConfigs.isEmpty() && targetVersion == null)
			return;
		monitor.beginTask(MIGRATING_J2EE_VERSION, migrationConfigs.size() * 3);
		linkMigrator = new EJBLinkMigrator(currentConfig);
		try {
			migrateProjectVersions();
		} finally {
			linkMigrator.release();
			monitor.done();
		}
	}

	protected void execute11CMPCodeGenCleanup() throws InvocationTargetException, InterruptedException {
		executeDeleteDeployCode();
		setupCodeGenCommands();
		executeDeleteRelationships();
		execute11CMPDeleteCodeGenCommand();

	}

	/**
	 * Method execute11CodeGenCommands.
	 */
	private void execute11CMPDeleteCodeGenCommand() throws InvocationTargetException, InterruptedException {
		executeCodeGenCommand(getEJBJarConfig().getDeleteCommand());
	}

	/**
	 * Method execute20CMPCodeGen.
	 */
	private void execute20CMPCodeGenCommand() throws InvocationTargetException, InterruptedException {
		IRootCommand root = getEJBJarConfig().getCreateCommand();
		if (root != null) {
			root.setShouldPropagateAllChanges(false);
			executeCodeGenCommand(root);
			appendStatus(J2EEMigrationStatus.COMPLETED_OK, format(COMPLETED_CMP20_CODEGEN, getEJBJarConfig().getTargetProject().getName()));
		}
	}

	private void executeCodeGenCommand(IRootCommand root) throws InvocationTargetException, InterruptedException {
		if (root != null) {
			IHeadlessRunnableWithProgress op = new EjbProgressOperation(editModel.getCommandStack(), root);
			executeNestedOperation(progressMonitor, op, 1);
		}
	}

	/**
	 * Method executeDeleteDeployCode.
	 */
	private void executeDeleteDeployCode() {
		List cmps = getEJBJarConfig().get11CMPs();
		deleteDeployCodeForCmps(cmps);
	}

	/**
	 * Method executeDeleteDeployCode.
	 */
	private void executeDelete20DeployCode() {
		List cmps = getEJBJarConfig().get20CMPs();
		deleteDeployCodeForCmps(cmps);
	}

	/**
	 * @param cmps
	 */
	protected void deleteDeployCodeForCmps(List cmps) {
		if (cmps.isEmpty())
			return;
		DeleteDeployCode op = DeleteDeployCodeExtensionReader.getInstance().getDeleteDeployCodeExt();
		if (op != null)
			op.runDeleteDeployCode(getEJBJarConfig().getTargetProject(), cmps);
	}

	/**
	 * Method executeDeleteRelationships.
	 */
	private void executeDeleteRelationships() throws InvocationTargetException, InterruptedException {
		List rels = getEJBJarConfig().get11Relationships();
		if (rels.isEmpty())
			return;
		RemoveRelationshipDataModel model = new RemoveRelationshipDataModel();
		model.setProperty(RemoveRelationshipDataModel.COMMON_RELATIONSHIP_LIST, rels);
		model.setProperty(EditModelOperationDataModel.EDIT_MODEL_ID, IEJBNatureConstants.EDIT_MODEL_ID);
		model.setProperty(EditModelOperationDataModel.PROJECT_NAME, getCurrentProject().getName());
		RemoveRelationshipDataModelOperation op = new RemoveRelationshipDataModelOperation(model);
		op.run(null);
	}

	private void executeLocalClientCreation(ClientViewModel model, EnterpriseBean ejb, boolean reuseRemote, boolean deleteRemote) {
		setupLocalClientViewModel(model, ejb, reuseRemote, deleteRemote);
		CreateClientViewOperation clientViewCreationOp = null;
		EJBEditModel localEditModel = null;
		EJBNatureRuntime natureRuntime = EJBNatureRuntime.getRuntime(currentConfig.getTargetProject());
		try {
			if (deleteRemote) {
				DeleteClientViewOperation deleteViewOp = null;
				try {
					deleteViewOp = new DeleteClientViewOperation(ejb, model, null, operationHandler);
					localEditModel = natureRuntime.getEJBEditModelForWrite(deleteViewOp);
					deleteViewOp.setEditModel(localEditModel);
					if (deleteViewOp != null) {
						//need to delete first since we reuse the name
						model.setShouldCreateLocalCommand(false);
						executeNestedOperation(progressMonitor, deleteViewOp, 1);
					}
				} finally {
					if (localEditModel != null)
						localEditModel.releaseAccess(deleteViewOp);
				}
			}
			clientViewCreationOp = new CreateClientViewOperation(ejb, model, null, operationHandler);
			localEditModel = natureRuntime.getEJBEditModelForWrite(clientViewCreationOp);
			clientViewCreationOp.setEditModel(localEditModel);
			model.setShouldCreateLocalCommand(true);
			model.setShouldCreateRemoteCommand(false);
			executeNestedOperation(progressMonitor, clientViewCreationOp, 1);
			appendStatus(J2EEMigrationStatus.COMPLETED_OK, format(COMPLETED_LOCAL_CLIENT_CREATE, ejb.getName()));
		} catch (InvocationTargetException e) {
			String msg = format(FAILED_LOCAL_CLIENT_CREATE, ejb.getName());
			Logger.getLogger().logWarning(msg);
			Logger.getLogger().logWarning(e);
			appendStatus(J2EEMigrationStatus.WARNING, msg);
		} catch (InterruptedException e) {
		} finally {
			if (editModel != null)
				localEditModel.releaseAccess(clientViewCreationOp);
		}
	}

	/**
	 * Method executeLocalClientGeneration.
	 *  
	 */
	private void executeLocalClientGeneration() {
		List ejbs = getEJBJarConfig().getSelectedLocalClientEJBs();
		if (ejbs.isEmpty())
			return;
		unloadCurrentJavaResources(); //this is necessary to flush the deletes
		// from the cleanup
		ClientViewModel model = createDefaultClientViewModel();
		int size = ejbs.size();
		EnterpriseBean ejb;
		boolean reuseRemote = getEJBJarConfig().isReuseRemoteClientViewName();
		boolean deleteRemote = getEJBJarConfig().isDeleteRemoteClientView();
		for (int i = 0; i < size; i++) {
			ejb = (EnterpriseBean) ejbs.get(i);
			if (ejb.hasLocalClient())
				appendStatus(J2EEMigrationStatus.NOT_NEEDED, format(LOCAL_CLIENT_NOT_NEEDED, ejb.getName()));
			else
				executeLocalClientCreation(model, ejb, reuseRemote, deleteRemote);
		}
	}

	protected J2EEMigrationConfig getConfigForProject(IProject project) {
		int size = migrationConfigs.size();
		J2EEMigrationConfig config = null;
		for (int i = 0; i < size; i++) {
			config = (J2EEMigrationConfig) migrationConfigs.get(i);
			if (config.getTargetProject().getName().equals(project.getName()))
				return config;
		}
		return null;
	}

	protected J2EENature getCurrentProjectNature() {
		if (currentConfig != null) {
			IProject project = currentConfig.getProjectHandle(EditModelOperationDataModel.PROJECT_NAME);
			return J2EENature.getRegisteredRuntime(project);
		}
		return null;
	}

	/**
	 *  
	 */
	protected XMLResource getDeploymentDescriptorResource() {
		J2EENature nature = getCurrentProjectNature();
		if (nature == null)
			return null;
		switch (nature.getDeploymentDescriptorType()) {
			case XMLResource.APP_CLIENT_TYPE :
				return (XMLResource) ((ApplicationClientNatureRuntime) nature).getDeploymentDescriptorRoot().eResource();
			case XMLResource.APPLICATION_TYPE :
				return (XMLResource) ((EARNatureRuntime) nature).getDeploymentDescriptorRoot().eResource();
			case XMLResource.EJB_TYPE :
				return (XMLResource) ((EJBNatureRuntime) nature).getDeploymentDescriptorRoot().eResource();
			case XMLResource.WEB_APP_TYPE :
				return (XMLResource) ((J2EEWebNatureRuntime) nature).getDeploymentDescriptorRoot().eResource();
			case XMLResource.RAR_TYPE :
				return (XMLResource) ((ConnectorNatureRuntime) nature).getDeploymentDescriptorRoot().eResource();
		}
		return null;
	}

	/**
	 *  
	 */
	private EObject getDeploymentDescriptorRoot() {
		J2EENature nature = getCurrentProjectNature();
		if (nature == null)
			return null;
		switch (nature.getDeploymentDescriptorType()) {
			case XMLResource.APP_CLIENT_TYPE :
				return ((ApplicationClientNatureRuntime) nature).getDeploymentDescriptorRoot();
			case XMLResource.APPLICATION_TYPE :
				return ((EARNatureRuntime) nature).getDeploymentDescriptorRoot();
			case XMLResource.EJB_TYPE :
				return ((EJBNatureRuntime) nature).getDeploymentDescriptorRoot();
			case XMLResource.WEB_APP_TYPE :
				return ((J2EEWebNatureRuntime) nature).getDeploymentDescriptorRoot();
		}
		return null;
	}

	/**
	 * @return
	 */
	protected int getDeploymentDescriptorType() {
		J2EENature nature = getCurrentProjectNature();
		if (nature == null)
			return -1;
		return nature.getDeploymentDescriptorType();
	}

	private EJBJarMigrationConfig getEJBJarConfig() {
		return (EJBJarMigrationConfig) currentConfig;
	}

	private Set getMigrateableUtilJARProjects() {
		EAREditModel earModel = (EAREditModel) editModel;
		Set utilityJARProjects = earModel.getModuleMappedUtilityJarProjects();
		Set excludeJARs = new HashSet();
		List ears = EARNatureRuntime.getAllEARProjectsInWorkbench();
		for (int i = 0; i < ears.size(); i++) {
			EARNatureRuntime runtime = EARNatureRuntime.getRuntime((IProject) ears.get(i));
			if (runtime == null || runtime == earModel.getEARNature() || runtime.isJ2EE1_3())
				continue;
			excludeJARs.addAll(earModel.getModuleMappedUtilityJarProjects());
		}
		utilityJARProjects.removeAll(excludeJARs);
		return utilityJARProjects;
	}

	public String getProjectName() {
		return currentConfig.getTargetProject().getName();
	}

	private boolean isComplexCMPMigration(J2EEMigrationConfig config) {
		return config.isEJB() && config.isComplex() && ((EJBJarMigrationConfig) config).has11CMPsToMigrate();
	}

	protected boolean isConnector() {
		return getDeploymentDescriptorType() == XMLResource.RAR_TYPE;
	}

	public boolean isTargetVersion1_2() {
		return targetVersion == J2EE_VERSION_1_2;
	}

	public boolean isTargetVersion1_3() {
		return targetVersion == J2EE_VERSION_1_3;
	}

	public boolean isTargetVersion1_4() {
		return targetVersion == J2EE_VERSION_1_4;
	}

	/**
	 * Method migrateDeploymentDescriptor.
	 */
	protected void migrateDeploymentDescriptor() throws CoreException {
		XMLResource resource = getDeploymentDescriptorResource();
		if (resource == null)
			appendStatus(new J2EEMigrationStatus(J2EEMigrationStatus.NOT_NEEDED, resource, format(NOT_NEEDED_DEPLOYMENT_DESC_MIG, getProjectName())));
		J2EESpecificationMigrator migrator = new J2EESpecificationMigrator(resource, targetVersion, currentConfig.isComplex());
		WebServicesClientSpecificationMigrator webServicesClientMigrator = new WebServicesClientSpecificationMigrator(resource, targetVersion, currentConfig.isComplex());
		WebServicesSpecificationMigrator webSerivcesMigrator = new WebServicesSpecificationMigrator(getCurrentProject(), targetVersion, currentConfig.isComplex());

		boolean isTargetVersion13 = migrator.getVersion().equals(J2EESpecificationMigrationConstants.J2EE_VERSION_1_3);
		if (isTargetVersion13)
			migrateDDTo13(migrator);
		else
			migrateDDTo14(migrator, webServicesClientMigrator, webSerivcesMigrator);
		if (getDeploymentDescriptorType() != XMLResource.APPLICATION_TYPE) {
			if (isTargetVersion13 && isConnector())
				return;
			if (earNature != null) {
				linkMigrator.setCurrentConfig(currentConfig);
				linkMigrator.migrate(earNature);
			}
		} else {
			if (earNature != null)
				removeEARFromOldServerEnvironment(earNature.getProject());
		}
	}

	/**
	 * @param migrator
	 * @param webServicesMigrator
	 * @throws CoreException
	 */
	protected void migrateDDTo14(J2EESpecificationMigrator migrator, SpecificationMigrator webServicesClientMigrator, WebServicesSpecificationMigrator webServicesMigrator) throws CoreException {
		J2EEMigrationStatus status14 = migrator.migrateTo14();
		if (status14 != null && status14.isOK())
			setJ2EE14ModuleVersion();
		appendStatus(status14);
		appendStatus(webServicesMigrator.migrateWebServiceResourceTo14IfExists());
		appendStatus(webServicesClientMigrator.migrateTo14());
	}

	/**
	 * @param project
	 */
	private void removeEARFromOldServerEnvironment(IProject project) {
		Iterator iterator = ServerUtil.getModuleProjects(project).iterator();
		while (iterator.hasNext()) {
			IModule module = (IModule) iterator.next();

			IServer[] servers = ServerUtil.getServersByModule(module);
			int size = servers.length;
			for (int i = 0; i < size; i++) {
				if (!ServerUtil.isSupportedModule(servers[i].getServerType().getRuntimeType().getModuleTypes(), module.getType(), module.getVersion())) {
					try {
						IServerWorkingCopy wc = servers[i].getWorkingCopy();
						ServerUtil.modifyModules(wc, null, new IModule[]{module}, new NullProgressMonitor());
						wc.save(new NullProgressMonitor());
					} catch (Exception e) {
						Logger.getLogger().logError(e);
					}
				}
			}
		}
	}

	/**
	 * @throws CoreException
	 */
	private void setJ2EE14ModuleVersion() throws CoreException {
		J2EENature nature = getCurrentProjectNature();
		String natureId = nature.getNatureID();
		if (natureId.equals(IEARNatureConstants.NATURE_ID) || natureId.equals(IApplicationClientNatureConstants.NATURE_ID))
			nature.setModuleVersion(J2EEVersionConstants.J2EE_1_4_ID);
		else if (natureId.equals(IEJBNatureConstants.NATURE_ID))
			nature.setModuleVersion(J2EEVersionConstants.EJB_2_1_ID);
		else if (natureId.equals(IWebNatureConstants.J2EE_NATURE_ID))
			nature.setModuleVersion(J2EEVersionConstants.WEB_2_4_ID);
		else if (natureId.endsWith(IConnectorNatureConstants.NATURE_ID))
			nature.setModuleVersion(J2EEVersionConstants.JCA_1_5_ID);
	}

	/**
	 * @param migrator
	 * @throws CoreException
	 */
	protected void migrateDDTo13(J2EESpecificationMigrator migrator) throws CoreException {
		J2EEMigrationStatus status13 = migrator.migrateTo13();
		if (status13.isOK())
			setJ2EE13ModuleVersion();
		appendStatus(status13);
	}

	/**
	 *  
	 */
	private void setJ2EE13ModuleVersion() throws CoreException {
		J2EENature nature = getCurrentProjectNature();
		String natureId = nature.getNatureID();
		if (natureId.equals(IEARNatureConstants.NATURE_ID) || natureId.equals(IApplicationClientNatureConstants.NATURE_ID))
			nature.setModuleVersion(J2EEVersionConstants.J2EE_1_3_ID);
		else if (natureId.equals(IEJBNatureConstants.NATURE_ID))
			nature.setModuleVersion(J2EEVersionConstants.EJB_2_0_ID);
		else if (natureId.equals(IWebNatureConstants.J2EE_NATURE_ID))
			nature.setModuleVersion(J2EEVersionConstants.WEB_2_3_ID);
		else if (natureId.endsWith(IConnectorNatureConstants.NATURE_ID))
			nature.setModuleVersion(J2EEVersionConstants.JCA_1_0_ID);


	}

	protected void migrateProjectClasspath() throws CoreException {
		//TODO Need to remove old classpath entries before setting the Target Runtime using WAS Ext
		// Cleanup
		removeOldClasspathEntries(currentProject);
		IProject project = currentConfig.getProjectHandle(EditModelOperationDataModel.PROJECT_NAME);
		setRuntimeTarget(project);
		if (getDeploymentDescriptorType() == XMLResource.APPLICATION_TYPE)
			migrateUtilityJARClassPaths();
		appendStatus(J2EEMigrationStatus.COMPLETED_OK, format(J2EE_VERSION_SUCCESS, getProjectName()));
	}

	protected void migrateProjectVersion(J2EEMigrationConfig config) {
		IProject project = config.getTargetProject();
		// if project is binary, migration is not possible
		if (ProjectUtilities.isBinaryProject(project)) {
			appendStatus(J2EEMigrationStatus.WARNING, format(BINARY_MIGRATION_FAILED, project.getName()));
			return;
		}
		tagRootAsMigrating();
		boolean isCMP11Migration = isComplexCMPMigration(config);
		try {
			progressMonitor.subTask(project.getName());
			BackendMigration backendMigration = null;
			if (getDeploymentDescriptorType() == XMLResource.EJB_TYPE) {
				backendMigration = BackendMigrationExtensionReader.getInstance().getBackendMigrationExt();
			}
			if (isCMP11Migration) {
				if (backendMigration != null) {
					appendStatus(backendMigration.migrate(project, true));
				}
				execute11CMPCodeGenCleanup();
			} else if (is20CMP(config)) {
				executeDelete20DeployCode();
			}
			migrateProjectClasspath();
			progressMonitor.worked(1);
			migrateDeploymentDescriptor();
			progressMonitor.worked(1);
			if (config.isEJB() && ((EJBJarMigrationConfig) config).hasLocalClientSelected())
				executeLocalClientGeneration();
			if (isCMP11Migration)
				execute20CMPCodeGenCommand();
			if (getDeploymentDescriptorType() == XMLResource.EJB_TYPE && backendMigration != null) {
				appendStatus(backendMigration.migrate(project, false));
			}
			progressMonitor.worked(1);
		} catch (Exception e) {
			Logger.getLogger().logError(e);
			appendStatus(J2EEMigrationStatus.ERROR, e.toString());
			appendStatus(J2EEMigrationStatus.ERROR, format(J2EE_VERSION_FAILED, project.getName()));
		} finally {
			removeRootMigrationTag();
		}
		currentConfig = null;
	}

	/**
	 * @return
	 */
	private boolean is20CMP(J2EEMigrationConfig config) {
		return config.isEJB() && config.shouldMigrateJ2EEVersion() && ((EJBJarMigrationConfig) config).has20CMPs();
	}

	protected void migrateProjectVersions() {
		if (currentProject != null) {
			currentConfig = getConfigForProject(currentProject);
			if (currentConfig != null)
				migrateProjectVersion(currentConfig);
		}
	}

	private void migrateUtilityJARClassPath(IProject p) {
		removeOldClasspathEntries(p);
		setRuntimeTarget(p);
	}

	private boolean migrateUtilityJARClassPaths() {
		Set utilityJARProjects = getMigrateableUtilJARProjects();
		if (utilityJARProjects.isEmpty())
			return false;
		Iterator iter = utilityJARProjects.iterator();
		while (iter.hasNext()) {
			IProject p = (IProject) iter.next();
			migrateUtilityJARClassPath(p);
		}
		return true;
	}

	/**
	 *  
	 */
	private void removeOldClasspathEntries(IProject p) {
		//TODO Call WAS Extensions api to clean up old classpath
	}

	/**
	 *  
	 */
	protected void removeRootMigrationTag() {
		EObject root = getDeploymentDescriptorRoot();
		if (root != null) {
			Adapter adapter = EcoreUtil.getExistingAdapter(root, J2EE_PROJ_MIGRATION_ADAPTER);
			if (adapter != null)
				root.eAdapters().remove(adapter);
		}
	}

	private void resolveMethodTypes(ClientViewModel model) {
		if (model.getHomeMethodCollection() != null)
			resolveMethodTypes(model.getHomeMethodCollection());
		if (model.getMethodCollection() != null)
			resolveMethodTypes(model.getMethodCollection());
	}

	private void resolveMethodTypes(List methods) {
		int size = methods.size();
		Method method;
		for (int i = 0; i < size; i++) {
			method = (Method) methods.get(i);
			List params = method.getParameters();
			JavaParameter param;
			for (int j = 0; j < params.size(); j++) {
				param = (JavaParameter) params.get(j);
				param.getJavaType();
			}
		}
	}

	/**
	 * @param project
	 * @param targetRuntime
	 */
	private void setRuntimeTarget(IProject project) {
		try {
			ServerTargetDataModel stModel = currentConfig.serverTargetDataModel;
			IRuntime runtime = J2EEMigrationHelper.getTargetRuntime(project);
			if (runtime != null && !runtime.getId().equals(stModel.getStringProperty(ServerTargetDataModel.RUNTIME_TARGET_ID))) {
				int ddType = stModel.getIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID);
				if (ddType == -1)
					stModel.setIntProperty(ServerTargetDataModel.DEPLOYMENT_TYPE_ID, XMLResource.APPLICATION_TYPE);
				stModel.setProperty(ServerTargetDataModel.PROJECT_NAME, project.getName());
				ServerTargetOperation operation = new ServerTargetOperation(stModel);
				operation.run(this.progressMonitor);
			}
		} catch (InvocationTargetException e) {
			Logger.getLogger().logError(e);
			appendStatus(J2EEMigrationStatus.ERROR, e.toString());
			appendStatus(J2EEMigrationStatus.ERROR, format(SERVER_TARGET_SETTING_FAILED, project.getName()));
		} catch (InterruptedException e) {
		}
	}

	/**
	 * Method setupAttributeCodeCommands.
	 * 
	 * @param att
	 * @param rootDelete
	 * @param rootCreate
	 */
	private void setupAttributeCodeCommands(CMPAttribute att, IRootCommand rootDelete, IRootCommand rootCreate) {
		new DeletePersistentAttributeCommand(rootDelete, att.getName(), true, false).setSourceMetaType(att);
		CreatePersistentAttributeCommand cmd = new CreatePersistentAttributeCommand(rootCreate, att.getName(), true, false);
		cmd.setSourceMetaType(att);
		cmd.setKey(att.isKey());
		cmd.setGenerateAccessors(true);
	}

	private void setupAttributeCodeGenCommands(ContainerManagedEntity cmp, EJBJarMigrationConfig config) {
		UpdateContainerManagedEntityCommand rootDelete = new UpdateContainerManagedEntityCommand(cmp, (EJBEditModel) editModel);
		UpdateContainerManagedEntityCommand rootCreate = new UpdateContainerManagedEntityCommand(cmp, (EJBEditModel) editModel);
		rootDelete.setIsMigrationCleanup(true);
		rootCreate.setIsMigrationCleanup(true);
		initializeModuleExtensionHelper();
		List attributes = cmp.getFilteredFeatures(LocalModelledPersistentAttributeFilter.singleton());
		int size = attributes.size();
		CMPAttribute att;
		for (int i = 0; i < size; i++) {
			att = (CMPAttribute) attributes.get(i);
			setupAttributeCodeCommands(att, rootDelete, rootCreate);
		}
		config.appendCreateCommand(rootCreate);
		config.appendDeleteCommand(rootDelete);
	}

	/**
	 * Method setupCodeGenCommands.
	 */
	private void setupCodeGenCommands() {
		EJBJarMigrationConfig config = getEJBJarConfig();
		List cmps = config.get11CMPs();
		if (cmps.isEmpty())
			return;
		int size = cmps.size();
		ContainerManagedEntity cmp;
		for (int i = 0; i < size; i++) {
			cmp = (ContainerManagedEntity) cmps.get(i);
			setupAttributeCodeGenCommands(cmp, config);
		}
		setupRelationshipCreationCommands(config);
	}

	private void setupLocalClientViewModel(ClientViewModel model, EnterpriseBean ejb, boolean reuseRemote, boolean deleteRemote) {
		model.resetDerivedAttributes();
		model.setEjbBean(ejb);
		JavaClass remote = ejb.getRemoteInterface();
		JavaClass home = ejb.getHomeInterface();
		model.setRemoteInterfaceExisting(remote);
		model.setRemoteExistingName(remote.getQualifiedName());
		model.setHomeInterfaceExisting(home);
		model.setHomeExistingName(home.getQualifiedName());
		model.setMethodCollection(remote.getMethods());
		model.setHomeMethodCollection(home.getMethods());
		if (reuseRemote) {
			model.setLocalExistingName(model.getRemoteExistingName());
			model.setLocalHomeExistingName(model.getHomeExistingName());
		}
		if (deleteRemote)
			resolveMethodTypes(model); //this is necessary since the JavaClass
		// will be deleted
	}

	/**
	 * Method setupRelationshipCommands.
	 * 
	 * @param config
	 */
	private void setupRelationshipCreationCommands(EJBJarMigrationConfig config) {
		List rels = config.get11Relationships();
		if (rels.isEmpty())
			return;
		int size = rels.size();
		CommonRelationship rel;
		for (int i = 0; i < size; i++) {
			rel = (CommonRelationship) rels.get(i);
			setupRelationshipCreationCommands(config, rel);
		}
	}

	/**
	 * Method setupRelationshipCreationCommands.
	 * 
	 * @param config
	 * @param rel
	 */
	private void setupRelationshipCreationCommands(EJBJarMigrationConfig config, CommonRelationship rel) {
		CreatePersistent20RoleCommand first, second;
		first = createRelationshipRoleCreationCommand(config, rel.getFirstCommonRole());
		second = createRelationshipRoleCreationCommand(config, rel.getSecondCommonRole());
		createRelationshipCreationCommand(config, rel, first, second);
	}

	protected void tagRootAsMigrating() {
		EObject root = getDeploymentDescriptorRoot();
		if (root != null) {
			root.eAdapters().add(new AdapterImpl() {
				public boolean isAdapterForType(Object type) {
					return J2EE_PROJ_MIGRATION_ADAPTER.equals(type);
				}
			});
		}
	}

	private void unloadCurrentJavaResources() {
		if (currentConfig.isEAR())
			return;
		ProjectResourceSet set = (ProjectResourceSet) editModel.getResourceSet();
		List remove = null;
		Iterator it = set.getResources().iterator();
		Resource res;
		while (it.hasNext()) {
			res = (Resource) it.next();
			if (JavaURL.isJavaURL(res.getURI().toString())) {
				if (remove == null)
					remove = new ArrayList();
				remove.add(res);
			}
		}
		if (remove != null)
			WorkbenchResourceHelperBase.removeAndUnloadAll(remove, set);
	}
}