package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.earcreation.EARComponentCreationDataModel;
import org.eclipse.jst.j2ee.modulecore.util.EARArtifactEdit;
import org.eclipse.wst.common.componentcore.StructureEdit;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.operation.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;

public abstract class J2EEComponentCreationDataModelProvider extends JavaComponentCreationDataModelProvider
	implements IJ2EEComponentCreationDataModelProperties {
	public AddComponentToEnterpriseApplicationDataModel addComponentToEARDataModel;
	protected EARComponentCreationDataModel earComponentCreationDataModel;
	private UpdateManifestDataModel jarDependencyDataModel;
	private ClassPathSelection cachedSelection;
	/**
	 * This  needs to be set up to ensure that other j2ee component is properly added as dependent component of ear 
	 */
	private URI earComponentHandle;

	public void init() {
		super.init();
//		earComponentCreationDataModel = new EARComponentCreationDataModel();
//		if(earComponentCreationDataModel != null)
//			getDataModel().addNestedModel(NESTED_MODEL_EAR_CREATION, earComponentCreationDataModel);
//		addComponentToEARDataModel = createModuleNestedModel();
//		if (addComponentToEARDataModel != null)
//			getDataModel().addNestedModel(NESTED_MODEL_ADD_TO_EAR, addComponentToEARDataModel);
//		jarDependencyDataModel = new UpdateManifestDataModel();
//		getDataModel().addNestedModel(NESTED_MODEL_JAR_DEPENDENCY, jarDependencyDataModel);
	}

 	public String[] getPropertyNames() {
		String[] props = new String[]{EAR_MODULE_NAME, EAR_MODULE_DEPLOY_NAME, ADD_TO_EAR, 
				UI_SHOW_EAR_SECTION, DD_FOLDER, JAVASOURCE_FOLDER, J2EE_VERSION,
				MANIFEST_FOLDER 
//				NESTED_MODEL_VALIDATION_HOOK
		};
		return combineProperties(super.getPropertyNames(), props);
	}

	protected AddComponentToEnterpriseApplicationDataModel createModuleNestedModel() {
		return new AddComponentToEnterpriseApplicationDataModel();
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ADD_TO_EAR)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(UI_SHOW_EAR_SECTION)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(EAR_MODULE_NAME)) {
			return getDataModel().getStringProperty(COMPONENT_NAME)+"EAR";
		} else {
			return super.getDefaultProperty(propertyName);
		}
	}

	protected boolean doSetProperty(String propertyName, Object propertyValue) {
		if (propertyName.equals(EAR_MODULE_NAME)) {
			earComponentHandle = computeEARHandle((String)propertyValue);
		} else if(propertyName.equals(COMPONENT_NAME)){
			if (!getDataModel().isPropertySet(EAR_MODULE_NAME)) 
				getDataModel().notifyPropertyChange(EAR_MODULE_NAME, IDataModel.VALID_VALUES_CHG);
			    setEARDeployNameProperty(getDataModel().getStringProperty(EAR_MODULE_NAME));
		} else if (propertyName.equals(PROJECT_NAME)) {
			WorkbenchComponent workbenchComp = getTargetWorkbenchComponent();
			setEARComponentIfJ2EEModuleCreationOnly(workbenchComp,propertyValue);
		} else if (propertyName.equals(ADD_TO_EAR)) {
			getDataModel().notifyPropertyChange(ADD_TO_EAR, IDataModel.VALID_VALUES_CHG);
		} else if (propertyName.equals(J2EE_VERSION)) {
			Integer modVersion = convertJ2EEVersionToModuleVersion((Integer) propertyValue);
			propertySet(COMPONENT_VERSION, modVersion);
			return false;
		}
		return true;
	}

	private URI computeEARHandle(String earHandle){
		URI uri = URI.createURI(earHandle);
		
		if( uri != null ){
			boolean isValidURI = false;
			try {
				isValidURI = ModuleURIUtil.ensureValidFullyQualifiedModuleURI(uri);
				String deployeName = ModuleURIUtil.getDeployedName(uri);
				setEARDeployNameProperty(deployeName);
			}
			catch (UnresolveableURIException e) {
			}
			if(isValidURI){
				earComponentHandle = uri;
				return earComponentHandle;
			}
		}
		return null;		
	}
	
	private void setEARDeployNameProperty(Object propertyValue) {
	   setProperty(EAR_MODULE_DEPLOY_NAME,propertyValue);
	}

	/**
	 * @param workbenchComp
	 */
	protected void setEARComponentIfJ2EEModuleCreationOnly(WorkbenchComponent workbenchComp, Object propertyValue) {
		getAddModuleToApplicationDataModel().setProperty(AddComponentToEnterpriseApplicationDataModel.ARCHIVE_MODULE, workbenchComp);
		getAddModuleToApplicationDataModel().setProperty(AddComponentToEnterpriseApplicationDataModel.PROJECT_NAME,
				getDataModel().getStringProperty(PROJECT_NAME));
		getAddModuleToApplicationDataModel().setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME,
				getDataModel().getStringProperty(EAR_MODULE_NAME));
		if (!getDataModel().isPropertySet(EAR_MODULE_NAME)) {
			String earModuleName = getDataModel().getStringProperty(EAR_MODULE_NAME);
			getDataModel().notifyPropertyChange(EAR_MODULE_NAME, IDataModel.VALID_VALUES_CHG);

		}
		jarDependencyDataModel.setProperty(UpdateManifestDataModel.PROJECT_NAME, propertyValue);
	}

//	protected Boolean basicIsEnabled(String propertyName) {
//		Boolean enabled = super.basicIsEnabled(propertyName);
//		if (enabled.booleanValue()) {
//			if (propertyName.equals(EAR_MODULE_NAME)) {
//				enabled = (Boolean) getProperty(ADD_TO_EAR);
//			} 
//		}
//		return enabled;
//	}

	protected final AddComponentToEnterpriseApplicationDataModel getAddModuleToApplicationDataModel() {
		return addComponentToEARDataModel;
	}

	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(COMPONENT_VERSION)) {
			return getValidComponentVersionDescriptors();
		}
		if (propertyName.equals(EAR_MODULE_NAME)) {
			int j2eeVersion = getJ2EEVersion();
			return getEARPropertyDescriptor(j2eeVersion);			
		}	
	  return super.getValidPropertyDescriptors(propertyName);
	}
	
	
	 private DataModelPropertyDescriptor[] getEARPropertyDescriptor(int j2eeVersion){
	 	
		 StructureEdit mc = null;
		 ArrayList earDescriptorList = new ArrayList();
		 
		 IProject[] projs = ProjectUtilities.getAllProjects();
		 
		 for( int index=0; index< projs.length; index++){
		 	IProject  flexProject = projs[index];
			 try {
				if(flexProject != null) { 
					mc = StructureEdit.getStructureEditForRead(flexProject);
					if( mc != null ){
						WorkbenchComponent[] components = mc.getWorkbenchModules();
		
						int earVersion = 0;
						for (int i = 0; i < components.length; i++) {
							EARArtifactEdit earArtifactEdit = null;
							try {
								WorkbenchComponent wc = (WorkbenchComponent)components[i];
								if(wc.getComponentType() != null && wc.getComponentType().getComponentTypeId().equals(IModuleConstants.JST_EAR_MODULE)) {  
									earArtifactEdit = EARArtifactEdit.getEARArtifactEditForRead(wc);
								    if(j2eeVersion <= earArtifactEdit.getJ2EEVersion()){
								    	WTPPropertyDescriptor desc = new WTPPropertyDescriptor(wc.getHandle().toString(), wc.getName());
								    	earDescriptorList.add(desc);
								    }
								}
							} finally {
									if(earArtifactEdit != null)
										earArtifactEdit.dispose();
							}
							   
						}
					}
				}
			 } finally {
				 if(mc != null)
					 mc.dispose();
			 }
		 }
		DataModelPropertyDescriptor[] descriptors = new DataModelPropertyDescriptor[earDescriptorList.size()];
		for (int i = 0; i < descriptors.length; i++) {
			WTPPropertyDescriptor desc = (WTPPropertyDescriptor)earDescriptorList.get(i);
			descriptors[i] = new DataModelPropertyDescriptor(desc.getPropertyValue(), desc.getPropertyDescription() );
		}
		return descriptors;		 
		 
	  }	 	
	 	
	
		
		
	public IProject getProject() {
		String projName = getDataModel().getStringProperty(IComponentCreationDataModelProperties.PROJECT_NAME );
		if(projName != null && projName.length() > 0)
		  return ProjectUtilities.getProject( projName );
		return null;
	}
	
	public WorkbenchComponent getTargetWorkbenchComponent() {
		StructureEdit core = null;
		try {
			IProject flexProject = getProject();
			if(flexProject != null) {
				core = StructureEdit.getStructureEditForRead(getProject());
				if(core != null) {
					String componentName = getProperty(COMPONENT_NAME) != null ? getProperty(COMPONENT_NAME).toString() : null;
					if(componentName!=null)
						return core.findComponentByName(componentName); 
				}
			}
		} finally {
			if(core != null)
				core.dispose();
		}
		return null;
	}

	protected IStatus validateProperty(String propertyName) {
		if (EAR_MODULE_NAME.equals(propertyName) && getBooleanProperty(ADD_TO_EAR)) {
			return validateEARModuleNameProperty();
		} 
		return super.validate(propertyName);
	}
	
	public EARComponentCreationDataModel getEarComponentCreationDataModel() {
        return earComponentCreationDataModel;
    }

	private IStatus validateEARModuleNameProperty() {
//		IProject earProject = applicationCreationDataModel.getTargetProject();
//		if (null != earProject && earProject.exists()) {
//			if (earProject.isOpen()) {
//				try {
//					EARNatureRuntime earNature = (EARNatureRuntime) earProject.getNature(IEARNatureConstants.NATURE_ID);
//					if (earNature == null) {
//						return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_NOT_EAR, new Object[]{earProject.getName()}));
//					} else if (earNature.getJ2EEVersion() < getJ2EEVersion()) {
//						String earVersion = EnterpriseApplicationCreationDataModel.getVersionString(earNature.getJ2EEVersion());
//						return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.INCOMPATABLE_J2EE_VERSIONS, new Object[]{earProject.getName(), earVersion}));
//					}
//					return OK_STATUS;
//				} catch (CoreException e) {
//					return new Status(IStatus.ERROR, J2EEPlugin.PLUGIN_ID, -1, null, e);
//				}
//			}
//			return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.PROJECT_ClOSED, new Object[]{earProject.getName()}));
//		} else if (null != earProject && null != getTargetProject()) {
//			if (earProject.getName().equals(getTargetProject().getName())) {
//				return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME, new Object[]{earProject.getName()}));
//			} else if (!CoreFileSystemLibrary.isCaseSensitive()) {
//				if (earProject.getName().toLowerCase().equals(getTargetProject().getName().toLowerCase())) {
//					return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME, new Object[]{earProject.getName()}));
//				}
//			}
//		}
//		IStatus status = applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_NAME);
//		if (status.isOK()) {
//			status = applicationCreationDataModel.validateProperty(EnterpriseApplicationCreationDataModel.PROJECT_LOCATION);
//		}
//		return status;
		
		return WTPCommonPlugin.OK_STATUS;
	}

	/**
	 * @return
	 */
	public final UpdateManifestDataModel getUpdateManifestDataModel() {
		return jarDependencyDataModel;
	}



	public final ClassPathSelection getClassPathSelection() {
//		boolean createNew = false;
//		if (null == cachedSelection || !getApplicationCreationDataModel().getTargetProject().getName().equals(cachedSelection.getEARFile().getURI())) {
//			createNew = true;
//		}
//		// close an existing cachedSelection
//		if (createNew && cachedSelection != null) {
//			EARFile earFile = cachedSelection.getEARFile();
//			if (earFile != null)
//				earFile.close();
//		}
//
//		if (createNew && getTargetProject() != null) {
//			cachedSelection = ClasspathSelectionHelper.createClasspathSelection(getTargetProject(), getModuleExtension(), getApplicationCreationDataModel().getTargetProject(), getModuleType());
//		}
		return cachedSelection;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.frameworks.internal.operation.WTPOperationDataModel#dispose()
	 */
//	public void dispose() {
//		if (cachedSelection != null)
//			cachedSelection.getEARFile().close();
//		super.dispose();
//	}

//	public void propertyChanged(WTPOperationDataModelEvent event) {
//		super.propertyChanged(event);
//		if (event.getDataModel() == addComponentToEARDataModel && event.getFlag() == WTPOperationDataModelEvent.PROPERTY_CHG && event.getPropertyName().equals(ArtifactEditOperationDataModel.PROJECT_NAME)) {
//		  earComponentCreationDataModel.setProperty(EARComponentCreationDataModel.COMPONENT_NAME, event.getProperty());
//		}
//	}

	public String getModuleName() {
		return getDataModel().getStringProperty(COMPONENT_NAME);
	}
	
	protected abstract DataModelPropertyDescriptor[] getValidComponentVersionDescriptors();
	
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

	/**
	 * @return Returns the addComponentToEARDataModel.
	 */
	public AddComponentToEnterpriseApplicationDataModel getAddComponentToEARDataModel() {
		return addComponentToEARDataModel;
	}
	

	/**
	 * @param addComponentToEARDataModel The addComponentToEARDataModel to set.
	 */
	public void setAddComponentToEARDataModel(AddComponentToEnterpriseApplicationDataModel addComponentToEARDataModel) {
		this.addComponentToEARDataModel = addComponentToEARDataModel;
	}
	

	public URI getEarComponentHandle() {
		return earComponentHandle;
	}
	public void setEarComponentHandle(URI earComponentHandle) {
		this.earComponentHandle = earComponentHandle;
	}
}