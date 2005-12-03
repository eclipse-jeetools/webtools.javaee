/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.earcreation.EARCreationResourceHandler;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EECreationResourceHandler;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.server.core.IModuleType;
import org.eclipse.wst.server.core.IRuntime;
import org.eclipse.wst.server.core.IRuntimeType;
import org.eclipse.wst.server.core.ServerUtil;

public abstract class J2EEComponentCreationDataModelProvider extends JavaComponentCreationDataModelProvider implements IJ2EEComponentCreationDataModelProperties, IAnnotationsDataModel {

	private IDataModel earCreationDM = null;
	private static String MODULE_NOT_SUPPORTED = "MODULE_NOT_SUPPORTED"; //$NON-NLS-1$
	private static String MODULEVERSION_NOT_SUPPORTED = "VERSION_NOT_SUPPORTED"; //$NON-NLS-1$
	private static String OK = "OK"; //$NON-NLS-1$

	public void init() {
		super.init();
		model.setProperty(COMPONENT_VERSION, getDefaultProperty(COMPONENT_VERSION));

		IDataModel dm = DataModelFactory.createDataModel(createAddComponentToEAR());
		model.setProperty(NESTED_ADD_COMPONENT_TO_EAR_DM, dm);
		propertySet(CLASSPATH_SELECTION, null);
		model.setProperty(NESTED_UPDATE_MANIFEST_DM, DataModelFactory.createDataModel(UpdateManifestDataModelProvider.class));
		model.setProperty(USE_ANNOTATIONS, Boolean.FALSE);
	}

	public AddComponentToEnterpriseApplicationDataModelProvider createAddComponentToEAR() {
		return new AddComponentToEnterpriseApplicationDataModelProvider();
	}

	public Set getPropertyNames() {
		Set propertyNames = super.getPropertyNames();
		propertyNames.add(EAR_COMPONENT_NAME);
		propertyNames.add(EAR_COMPONENT_DEPLOY_NAME);
		propertyNames.add(ADD_TO_EAR);
		propertyNames.add(UI_SHOW_EAR_SECTION);
		propertyNames.add(DD_FOLDER);
		propertyNames.add(COMPONENT_VERSION);
		propertyNames.add(VALID_COMPONENT_VERSIONS_FOR_PROJECT_RUNTIME);
		propertyNames.add(NESTED_ADD_COMPONENT_TO_EAR_DM);
		propertyNames.add(CLASSPATH_SELECTION);
		propertyNames.add(NESTED_EAR_COMPONENT_CREATION_DM);
		propertyNames.add(NESTED_UPDATE_MANIFEST_DM);
		propertyNames.add(EAR_COMPONENT_PROJECT);
		propertyNames.add(USE_ANNOTATIONS);
		return propertyNames;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ADD_TO_EAR)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(UI_SHOW_EAR_SECTION)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(EAR_COMPONENT_NAME)) {
			return getDataModel().getStringProperty(COMPONENT_NAME) + "EAR"; //$NON-NLS-1$
		} else if (propertyName.equals(COMPONENT_VERSION)) {
			return getDefaultComponentVersion();
		} else if (propertyName.equals(NESTED_EAR_COMPONENT_CREATION_DM))
			return getDefaultEarCreationDM();
		return super.getDefaultProperty(propertyName);
	}

	private Object getDefaultEarCreationDM() {
		if (earCreationDM == null) {
			earCreationDM = DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
			setProperty(NESTED_EAR_COMPONENT_CREATION_DM, earCreationDM);
		}
		return earCreationDM;
	}

	public boolean isPropertyEnabled(String propertyName) {
		if (EAR_COMPONENT_NAME.equals(propertyName)) {
			return getBooleanProperty(ADD_TO_EAR);
		}
		return super.isPropertyEnabled(propertyName);
	}


	public boolean propertySet(String propertyName, Object propertyValue) {
		boolean status = super.propertySet(propertyName, propertyValue);
		if (propertyName.equals(EAR_COMPONENT_NAME)) {
			model.setProperty(EAR_COMPONENT_DEPLOY_NAME, propertyValue);
			IProject earProj = getEARProject();
			IDataModel earDM = (IDataModel) model.getProperty(NESTED_EAR_COMPONENT_CREATION_DM);
			earDM.setProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_PROJECT, earProj);
			model.setProperty(EAR_COMPONENT_PROJECT, earProj);

		} else if (propertyName.equals(COMPONENT_NAME)) {

			if (getBooleanProperty(ADD_TO_EAR)) {
				if (!model.isPropertySet(EAR_COMPONENT_NAME)) {
					model.notifyPropertyChange(EAR_COMPONENT_NAME, IDataModel.VALID_VALUES_CHG);
					model.setProperty(EAR_COMPONENT_DEPLOY_NAME, getProperty(EAR_COMPONENT_NAME));
					IDataModel earDM = (IDataModel) model.getProperty(NESTED_EAR_COMPONENT_CREATION_DM);
					IProject earProject = getEARProject();
					model.setProperty(EAR_COMPONENT_PROJECT, earProject);
					if (earDM != null && earProject != null)
						earDM.setProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_PROJECT, earProject);
				}
			}
		} else if (propertyName.equals(ADD_TO_EAR)) {
			model.notifyPropertyChange(EAR_COMPONENT_NAME, IDataModel.ENABLE_CHG);
			model.notifyPropertyChange(NESTED_EAR_COMPONENT_CREATION_DM, IDataModel.DEFAULT_CHG);
			IProject earProj = getEARProject();
			model.setProperty(EAR_COMPONENT_PROJECT, earProj);
			IDataModel earDM = (IDataModel) model.getProperty(NESTED_EAR_COMPONENT_CREATION_DM);
			if (earDM != null && earProj != null) {
				earDM.setProperty(IJ2EEComponentCreationDataModelProperties.EAR_COMPONENT_PROJECT, earProj);
				earDM.setProperty(COMPONENT_VERSION, new Integer(getJ2EEVersion()));
			}

		} else if (propertyName.equals(COMPONENT_VERSION)) {
			if (getJ2EEVersion() < J2EEVersionConstants.VERSION_1_3)
				setProperty(USE_ANNOTATIONS, Boolean.FALSE);
			model.notifyPropertyChange(USE_ANNOTATIONS, DataModelEvent.ENABLE_CHG);
			model.notifyPropertyChange(EAR_COMPONENT_NAME, DataModelEvent.VALID_VALUES_CHG);
			if (getBooleanProperty(ADD_TO_EAR)) {
				IDataModel earDM = (IDataModel) model.getProperty(NESTED_EAR_COMPONENT_CREATION_DM);
				earDM.setProperty(COMPONENT_VERSION, new Integer(getJ2EEVersion()));
			}
			// this will force to reload all the server types which are valid for this component
			// version
			model.notifyPropertyChange(RUNTIME_TARGET_ID, DataModelEvent.VALID_VALUES_CHG);			
		} else if (RUNTIME_TARGET_ID.equals(propertyName)) {
			setProperty(ADD_TO_EAR, new Boolean(isEARSupported()));

			IDataModel earDM = (IDataModel) model.getProperty(NESTED_EAR_COMPONENT_CREATION_DM);
			earDM.setProperty(RUNTIME_TARGET_ID, propertyValue);
		} 
		return status;
	}

	protected IProject getEARProject() {
		String earProjname = (String) model.getProperty(EAR_COMPONENT_NAME);
		IDataModel earDM = (IDataModel) model.getProperty(NESTED_EAR_COMPONENT_CREATION_DM);
		earDM.setProperty(IComponentCreationDataModelProperties.PROJECT_NAME, earProjname);

		if (earProjname != null && !earProjname.equals("") && validate(EAR_COMPONENT_NAME).isOK()) //$NON-NLS-1$
			return ProjectUtilities.getProject(earProjname);
		return null;
	}


	public DataModelPropertyDescriptor getPropertyDescriptor(String propertyName){
		if (propertyName.equals(RUNTIME_TARGET_ID)) {
			String propertyValue =  (String)getProperty(propertyName);
			if( propertyValue != null ){
				IRuntime runtime = getServerTargetByID(propertyValue);
				if( runtime != null )
					return new DataModelPropertyDescriptor(propertyValue, runtime.getName());
			}
		}
		return super.getPropertyDescriptor(propertyName);
	}

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(COMPONENT_VERSION)) {
			return getValidComponentVersionDescriptors();
		}
		if (propertyName.equals(EAR_COMPONENT_NAME)) {
			int j2eeVersion = getJ2EEVersion();
			return getEARPropertyDescriptor(j2eeVersion);
		} else if (propertyName.equals(RUNTIME_TARGET_ID)) {
			return validJ2EEServerPropertyDescriptors();
		}
		return super.getValidPropertyDescriptors(propertyName);
	}

	protected String isvalidJComponentVersionsSupportedByServer() {
		String serverID = model.getStringProperty(RUNTIME_TARGET_ID);
		IRuntime runtime = getServerTargetByID(serverID);

		if (serverID.equals("") || runtime == null) { //$NON-NLS-1$
			return MODULEVERSION_NOT_SUPPORTED;
		}
		Integer version = (Integer) model.getProperty(COMPONENT_VERSION);
		String j2eeVer = J2EEVersionUtil.convertVersionIntToString(version.intValue());
		return isTypeSupported(runtime.getRuntimeType(), getJ2EEProjectType(), j2eeVer);
	}
	
	protected abstract String getJ2EEProjectType();

	protected DataModelPropertyDescriptor[] validJ2EEServerPropertyDescriptors() {

		Integer version = (Integer) model.getProperty(COMPONENT_VERSION);
		//int j2eeversion = convertModuleVersionToJ2EEVersion(version.intValue());
		String j2eeVersionText = J2EEVersionUtil.convertVersionIntToString(version.intValue());

		ArrayList validServers = new ArrayList();

		IDataModel projectdm = model.getNestedModel(NESTED_PROJECT_CREATION_DM);
		DataModelPropertyDescriptor[] desc = projectdm.getValidPropertyDescriptors(RUNTIME_TARGET_ID);
		for (int i = 0; i < desc.length; i++) {
			DataModelPropertyDescriptor descriptor = desc[i];
			String runtimeid = (String) descriptor.getPropertyValue();
			IRuntime runtime = getServerTargetByID(runtimeid);
			String ok = isTypeSupported(runtime.getRuntimeType(), getJ2EEProjectType(), j2eeVersionText);
			if (ok.equals(OK))
				validServers.add(descriptor);
		}

		if (!validServers.isEmpty()) {
			int serverTargetListSize = validServers.size();
			DataModelPropertyDescriptor[] result = new DataModelPropertyDescriptor[serverTargetListSize];
			for (int i = 0; i < validServers.size(); i++) {
				result[i] = (DataModelPropertyDescriptor) validServers.get(i);
			}
			return result;
		}
		return new DataModelPropertyDescriptor[0];
	}

	protected IRuntime getServerTargetByID(String id) {
		IRuntime[] targets = ServerUtil.getRuntimes("", ""); //$NON-NLS-1$ //$NON-NLS-2$
		for (int i = 0; i < targets.length; i++) {
			IRuntime target = targets[i];
			if (id.equals(target.getId()))
				return target;
		}
		return null;
	}

//	private List getValidServerTargets() {
//		List validServerTargets = null;
//		validServerTargets = ServerTargetHelper.getServerTargets("", ""); //$NON-NLS-1$  //$NON-NLS-2$
//		if (validServerTargets != null && validServerTargets.isEmpty())
//			validServerTargets = null;
//		if (validServerTargets == null)
//			return Collections.EMPTY_LIST;
//		return validServerTargets;
//	}


	protected String isTypeSupported(IRuntimeType type, String moduleID, String j2eeVersion) {
		IModuleType[] moduleTypes = type.getModuleTypes();

		boolean moduleFound = false;
		boolean moduleVersionFound = false;

		if (moduleTypes != null) {
			int size = moduleTypes.length;

			for (int i = 0; i < size; i++) {
				IModuleType moduleType = moduleTypes[i];

				if (matches(moduleType.getId(), moduleID)) {
					moduleFound = true;
					String version = moduleType.getVersion();
					if (version.equals(j2eeVersion) || version.equals("*")) { //$NON-NLS-1$
						moduleVersionFound = true;
						return OK;
					} 
					if (i < size)
						continue;
				}
			}
		}
		if (!moduleFound)
			return MODULE_NOT_SUPPORTED;
		if (!moduleVersionFound)
			return MODULEVERSION_NOT_SUPPORTED;
		return ""; //$NON-NLS-1$
	}


	protected static String[] getServerVersions(IRuntimeType type, String moduleID) {
		List list = new ArrayList();
		if (type == null)
			return null;
		IModuleType[] moduleTypes = type.getModuleTypes();
		if (moduleTypes != null) {
			int size = moduleTypes.length;
			for (int i = 0; i < size; i++) {
				IModuleType moduleType = moduleTypes[i];
				if (matches(moduleType.getId(), moduleID)) {
					list.add(moduleType.getVersion());
				}

			}
		}
		String[] versions = null;
		if (!list.isEmpty()) {
			versions = new String[list.size()];
			list.toArray(versions);
		}
		return versions;
	}

	protected static boolean matches(String serverTypeID, String j2eeModuleID) {

		if (serverTypeID.equals("jst") || serverTypeID.equals("jst.*")) //$NON-NLS-1$ //$NON-NLS-2$
			return j2eeModuleID.equals(J2EEProjectUtilities.DYNAMIC_WEB) || j2eeModuleID.equals(J2EEProjectUtilities.EJB) || j2eeModuleID.equals(J2EEProjectUtilities.ENTERPRISE_APPLICATION) || j2eeModuleID.equals(J2EEProjectUtilities.APPLICATION_CLIENT) || j2eeModuleID.equals(J2EEProjectUtilities.JCA);
		
		else if (serverTypeID.equals(J2EEProjectUtilities.DYNAMIC_WEB))
			return j2eeModuleID.equals(J2EEProjectUtilities.DYNAMIC_WEB);
		
		else if (serverTypeID.equals(J2EEProjectUtilities.EJB))
			return j2eeModuleID.equals(J2EEProjectUtilities.EJB);
		
		else if (serverTypeID.equals(J2EEProjectUtilities.JCA))
			return j2eeModuleID.equals(J2EEProjectUtilities.JCA);
		
		else if (serverTypeID.equals(J2EEProjectUtilities.APPLICATION_CLIENT))
			return j2eeModuleID.equals(J2EEProjectUtilities.APPLICATION_CLIENT);
		
		else if (serverTypeID.equals(J2EEProjectUtilities.ENTERPRISE_APPLICATION)) 
			return j2eeModuleID.equals(J2EEProjectUtilities.ENTERPRISE_APPLICATION) || j2eeModuleID.equals(J2EEProjectUtilities.APPLICATION_CLIENT) || j2eeModuleID.equals(J2EEProjectUtilities.JCA);
			
		return false;
	}



	private DataModelPropertyDescriptor[] getEARPropertyDescriptor(int j2eeVersion) {
		StructureEdit mc = null;
		ArrayList earDescriptorList = new ArrayList();

		IProject[] projs = ProjectUtilities.getAllProjects();

		for (int index = 0; index < projs.length; index++) {
			IProject flexProject = projs[index];
			try {
				if (flexProject != null) {
					if (ModuleCoreNature.isFlexibleProject(flexProject)) {
						IVirtualComponent comp = ComponentCore.createComponent(flexProject);
						if (J2EEProjectUtilities.isEARProject(comp.getProject())) {
							String sVer = J2EEProjectUtilities.getJ2EEProjectVersion(comp.getProject());
							int ver = J2EEVersionUtil.convertVersionStringToInt(sVer);
							if (j2eeVersion <= ver) {
								DataModelPropertyDescriptor desc = new DataModelPropertyDescriptor(comp.getProject().getName());
								earDescriptorList.add(desc);
							}
						}
					}
				}
			} finally {
				if (mc != null)
					mc.dispose();
			}
		}
		DataModelPropertyDescriptor[] descriptors = new DataModelPropertyDescriptor[earDescriptorList.size()];
		for (int i = 0; i < descriptors.length; i++) {
			DataModelPropertyDescriptor desc = (DataModelPropertyDescriptor) earDescriptorList.get(i);
			descriptors[i] = new DataModelPropertyDescriptor(desc.getPropertyDescription(), desc.getPropertyDescription());
		}
		return descriptors;
	}



	public IProject getProject() {
		String projName = getDataModel().getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
		if (projName != null && projName.length() > 0)
			return ProjectUtilities.getProject(projName);
		return null;
	}

	public WorkbenchComponent getTargetWorkbenchComponent() {
		StructureEdit core = null;
		try {
			IProject flexProject = getProject();
			if (flexProject != null) {
				core = StructureEdit.getStructureEditForRead(getProject());
				if (core != null) {
					String componentName = getProperty(COMPONENT_NAME) != null ? getProperty(COMPONENT_NAME).toString() : null;
					if (componentName != null)
						return core.getComponent();
				}
			}
		} finally {
			if (core != null)
				core.dispose();
		}
		return null;
	}

	protected boolean isEARSupported() {
		if (this instanceof EarComponentCreationDataModelProvider)
			return false;
		String serverID = model.getStringProperty(RUNTIME_TARGET_ID);
		IRuntime runtime = getServerTargetByID(serverID);
		Integer version = (Integer) model.getProperty(COMPONENT_VERSION);
		int nj2eeVer = convertModuleVersionToJ2EEVersion(version.intValue());
		String j2eeVer = J2EEVersionUtil.getJ2EETextVersion(nj2eeVer);

		if (runtime != null) {
			String msg = isTypeSupported(runtime.getRuntimeType(), J2EEProjectUtilities.ENTERPRISE_APPLICATION, j2eeVer);
			if (msg.equals(OK)) {
				return true;
			}
		}
		return false;
	}

	protected boolean validateComponentAlreadyInEar() {
		//IVirtualComponent component = ComponentCore.createComponent(getProject(), getModuleName());

		IProject earProj = (IProject) model.getProperty(EAR_COMPONENT_PROJECT);
		if (earProj != null && earProj.exists()) {
			IVirtualComponent earComp = ComponentCore.createComponent(earProj);
			if (earComp != null && earComp.exists()) {
				IVirtualReference[] refs = earComp.getReferences();
				for (int i = 0; i < refs.length; i++) {
					IVirtualReference ref = refs[i];
					IVirtualComponent referencedComp = ref.getReferencedComponent();
					if (referencedComp.getName().equalsIgnoreCase(getModuleName()))
						return true;
				}
			}
		}
		return false;
	}

	public IStatus validate(String propertyName) {
		if (EAR_COMPONENT_NAME.equals(propertyName) && getBooleanProperty(ADD_TO_EAR)) {
			return validateEARModuleNameProperty();
		} else if (COMPONENT_VERSION.equals(propertyName)) {
			return validateComponentVersionProperty();
		} else if (propertyName.equals(VALID_COMPONENT_VERSIONS_FOR_PROJECT_RUNTIME)) {
			return OK_STATUS;
		} else if (propertyName.equals(ADD_TO_EAR)) {
			boolean val = getBooleanProperty(ADD_TO_EAR);
			if (val) {
				String serverID = model.getStringProperty(RUNTIME_TARGET_ID);
				IRuntime runtime = getServerTargetByID(serverID);
				if (serverID.equals("") || runtime == null) { //$NON-NLS-1$
					String msg = EARCreationResourceHandler.SERVER_TARGET_NOT_SUPPORT_EAR;
					return WTPCommonPlugin.createErrorStatus(msg);
				}
				Integer version = (Integer) model.getProperty(COMPONENT_VERSION);
				int nj2eeVer = convertModuleVersionToJ2EEVersion(version.intValue());
				String j2eeVer = J2EEVersionUtil.getJ2EETextVersion(nj2eeVer);
				String msg = isTypeSupported(runtime.getRuntimeType(), J2EEProjectUtilities.ENTERPRISE_APPLICATION, j2eeVer);
				if (!msg.equals(OK)) {
					msg = EARCreationResourceHandler.SERVER_TARGET_NOT_SUPPORT_EAR;
					return WTPCommonPlugin.createErrorStatus(msg);
				} 
				if (validateComponentAlreadyInEar()) {
						msg = J2EECreationResourceHandler.COMPONENT_ALREADYINEAR; 
						return WTPCommonPlugin.createErrorStatus(msg);
				}
			}
		} else if (propertyName.equals(PROJECT_NAME)) {
			if (!isCreatingEarComponent()) {
				String projName = getDataModel().getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME);
				IDataModel earDM = (IDataModel) model.getProperty(NESTED_EAR_COMPONENT_CREATION_DM);
				IProject earProj = (IProject) earDM.getProperty(EAR_COMPONENT_PROJECT);
				if (earProj != null) {
					String earProjName = earProj.getName();
					if (earProjName.equalsIgnoreCase(projName)) {
						String msg = msg = EARCreationResourceHandler.EAR_PROJECTNAME_SAMEAS_MODULE;
						return WTPCommonPlugin.createErrorStatus(msg);
					}
				}
			}
		}
		return super.validate(propertyName);
	}

	protected boolean isCreatingEarComponent() {
		return false;
	}

	private IStatus validateComponentVersionProperty() {
		int componentVersion = model.getIntProperty(COMPONENT_VERSION);
		String result = isvalidJComponentVersionsSupportedByServer();
		if (result.equals(MODULEVERSION_NOT_SUPPORTED)) {
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SPEC_LEVEL_NOT_FOUND));
		} else if (result.equals(MODULE_NOT_SUPPORTED)) {
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.MODULE_NOT_SUPPORTED));
		}
		if (componentVersion == -1)
			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SPEC_LEVEL_NOT_FOUND));
		return OK_STATUS;
	}

	private IStatus validateEARModuleNameProperty() {
		IStatus status = OK_STATUS;
		String earName = getStringProperty(EAR_COMPONENT_NAME);
		if (status.isOK()) {
			if (earName.indexOf("#") != -1 || earName.indexOf("/") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_INVALID_CHARS); 
				return WTPCommonPlugin.createErrorStatus(errorMessage);
			} else if (earName == null || earName.equals("")) { //$NON-NLS-1$
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_EMPTY_MODULE_NAME);
				return WTPCommonPlugin.createErrorStatus(errorMessage);
			}
		} else
			return status;
		// IProject earProject =
		// applicationCreationDataModel.getTargetProject();
		// if (null != earProject && earProject.exists()) {
		// if (earProject.isOpen()) {
		// try {
		// EARNatureRuntime earNature = (EARNatureRuntime)
		// earProject.getNature(IEARNatureConstants.NATURE_ID);
		// if (earNature == null) {
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NOT_EAR,
		// new Object[]{earProject.getName()}));
		// } else if (earNature.getJ2EEVersion() < getJ2EEVersion()) {
		// String earVersion =
		// EnterpriseApplicationCreationDataModel.getVersionString(earNature.getJ2EEVersion());
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.INCOMPATABLE_J2EE_VERSIONS,
		// new Object[]{earProject.getName(), earVersion}));
		// }
		// return OK_STATUS;
		// } catch (CoreException e) {
		// return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, -1, null, e);
		// }
		// }
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_ClOSED,
		// new Object[]{earProject.getName()}));
		// } else if (null != earProject && null != getTargetProject()) {
		// if (earProject.getName().equals(getTargetProject().getName())) {
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME,
		// new Object[]{earProject.getName()}));
		// } else if (!CoreFileSystemLibrary.isCaseSensitive()) {
		// if
		// (earProject.getName().toLowerCase().equals(getTargetProject().getName().toLowerCase()))
		// {
		// return
		// WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME,
		// new Object[]{earProject.getName()}));
		// }
		// }
		// }
		// IStatus status =
		// applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME);
		// if (status.isOK()) {
		// status =
		// applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_LOCATION);
		// }
		// return status;

		return WTPCommonPlugin.OK_STATUS;
	}

	public final ClassPathSelection getClassPathSelection() {
		// boolean createNew = false;
		// if (null == cachedSelection ||
		// !getApplicationCreationDataModel().getTargetProject().getName().equals(cachedSelection.getEARFile().getURI()))
		// {
		// createNew = true;
		// }
		// // close an existing cachedSelection
		// if (createNew && cachedSelection != null) {
		// EARFile earFile = cachedSelection.getEARFile();
		// if (earFile != null)
		// earFile.close();
		// }
		//
		// if (createNew && getTargetProject() != null) {
		// cachedSelection =
		// ClasspathSelectionHelper.createClasspathSelection(getTargetProject(),
		// getModuleExtension(),
		// getApplicationCreationDataModel().getTargetProject(),
		// getModuleType());
		// }
		// return cachedSelection;
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#dispose()
	 */
	public void dispose() {
		super.dispose();
	}

	// public void propertyChanged(WTPOperationDataModelEvent event) {
	// super.propertyChanged(event);
	// if (event.getDataModel() == addComponentToEARDataModel && event.getFlag()
	// == WTPOperationDataModelEvent.PROPERTY_CHG &&
	// event.getPropertyName().equals(ArtifactEditOperationDataModel.PROJECT_NAME))
	// {
	// earComponentCreationDataModel.setProperty(EARComponentCreationDataModel.COMPONENT_NAME,
	// event.getProperty());
	// }
	// }
	protected final IDataModel getAddComponentToEARDataModel() {
		// return (AddComponentToEnterpriseApplicationDataModel)
		// model.getProperty(NESTED_ADD_COMPONENT_TO_EAR_DM);
		return (IDataModel) model.getProperty(NESTED_ADD_COMPONENT_TO_EAR_DM);
	}

	protected final IDataModel getUpdateManifestDataModel() {
		return (IDataModel) model.getProperty(NESTED_UPDATE_MANIFEST_DM);
	}

	public String getModuleName() {
		return getDataModel().getStringProperty(COMPONENT_NAME);
	}

	public final int getJ2EEVersion() {
		return convertModuleVersionToJ2EEVersion(getIntProperty(COMPONENT_VERSION));
	}

	/**
	 * Subclasses should override to convert the j2eeVersion to a module version id. By default we
	 * return the j2eeVersion which is fine if no conversion is necessary.
	 * 
	 * @param integer
	 * @return
	 */
	protected Integer convertJ2EEVersionToModuleVersion(Integer j2eeVersion) {
		return j2eeVersion;
	}

	protected abstract int convertModuleVersionToJ2EEVersion(int moduleVersion);

	protected abstract DataModelPropertyDescriptor[] getValidComponentVersionDescriptors();


//	private DataModelPropertyDescriptor[] validExistingProjectsDescriptors() {
//
//		IProject[] workspaceProjects = ProjectUtilities.getAllProjects();
//		List items = new ArrayList();
//		for (int i = 0; i < workspaceProjects.length; i++) {
//			IProject project = workspaceProjects[i];
//			try {
//				if (project.hasNature(IModuleConstants.MODULE_NATURE_ID)) {
//					items.add(project.getName());
//				}
//			} catch (CoreException ce) {
//				// Ignore
//			}
//		}
//
//		DataModelPropertyDescriptor[] descriptors = new DataModelPropertyDescriptor[items.size()];
//		for (int i = 0; i < descriptors.length; i++) {
//			descriptors[i] = new DataModelPropertyDescriptor(items.get(i));
//		}
//		return descriptors;
//
//
//		// StructureEdit mc = null;
//		// ArrayList earDescriptorList = new ArrayList();
//		//
//		// IProject[] projs = ProjectUtilities.getAllProjects();
//		//
//		// for (int index = 0; index < projs.length; index++) {
//		// IProject flexProject = projs[index];
//		// try {
//		// if (flexProject != null) {
//		// IFlexibleProject fProject = ComponentCore.createFlexibleProject(flexProject);
//		// if ( fProject.isFlexible()){
//		// IVirtualComponent[] comps = fProject.getComponents();
//		// int earVersion = 0;
//		// for( int i=0; i< comps.length; i++ ){
//		// if( comps[i].getComponentTypeId().equals(IModuleConstants.JST_EAR_MODULE)){
//		// String sVer = comps[i].getVersion();
//		// int ver = J2EEVersionUtil.convertVersionStringToInt(sVer);
//		// if (j2eeVersion <= ver) {
//		// DataModelPropertyDescriptor desc = new
//		// DataModelPropertyDescriptor(comps[i].getComponentHandle(), comps[i].getName());
//		// earDescriptorList.add(desc);
//		// }
//		// }
//		// }
//		// }
//		// }
//		// } finally {
//		// if (mc != null)
//		// mc.dispose();
//		// }
//		// }
//		// DataModelPropertyDescriptor[] descriptors = new
//		// DataModelPropertyDescriptor[earDescriptorList.size()];
//		// for (int i = 0; i < descriptors.length; i++) {
//		// DataModelPropertyDescriptor desc = (DataModelPropertyDescriptor)earDescriptorList.get(i);
//		// descriptors[i] = new DataModelPropertyDescriptor(desc.getPropertyDescription(),
//		// desc.getPropertyDescription());
//		// }
//		// return descriptors;
//
//
//	}
}