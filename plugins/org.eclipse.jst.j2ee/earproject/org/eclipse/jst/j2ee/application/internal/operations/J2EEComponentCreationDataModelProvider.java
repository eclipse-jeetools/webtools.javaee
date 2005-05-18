package org.eclipse.jst.j2ee.application.internal.operations;

import java.util.ArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.j2ee.componentcore.util.EARArtifactEdit;
import org.eclipse.jst.j2ee.datamodel.properties.IJ2EEComponentCreationDataModelProperties;
import org.eclipse.jst.j2ee.internal.archive.operations.JavaComponentCreationDataModelProvider;
import org.eclipse.jst.j2ee.internal.earcreation.EarComponentCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.UnresolveableURIException;
import org.eclipse.wst.common.componentcore.datamodel.properties.IComponentCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.StructureEdit;
import org.eclipse.wst.common.componentcore.internal.WorkbenchComponent;
import org.eclipse.wst.common.componentcore.internal.impl.ModuleURIUtil;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.resources.ComponentHandle;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.WTPPropertyDescriptor;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.server.core.IProjectProperties;
import org.eclipse.wst.server.core.ServerCore;

public abstract class J2EEComponentCreationDataModelProvider extends JavaComponentCreationDataModelProvider implements IJ2EEComponentCreationDataModelProperties, IAnnotationsDataModel {

	public void init() {
		super.init();
        model.setProperty(COMPONENT_VERSION, getDefaultProperty(COMPONENT_VERSION));
        model.setProperty(NESTED_ADD_COMPONENT_TO_EAR_DM, new AddComponentToEnterpriseApplicationDataModel());
        propertySet(CLASSPATH_SELECTION, null);
        model.setProperty(NESTED_UPDATE_MANIFEST_DM, new UpdateManifestDataModel());
        model.setProperty(USE_ANNOTATIONS, Boolean.FALSE);
	}

 	public String[] getPropertyNames() {
		String[] props = new String[]{EAR_COMPONENT_NAME, EAR_COMPONENT_DEPLOY_NAME, ADD_TO_EAR, 
				UI_SHOW_EAR_SECTION, DD_FOLDER, COMPONENT_VERSION, VALID_COMPONENT_VERSIONS_FOR_PROJECT_RUNTIME,
                NESTED_ADD_COMPONENT_TO_EAR_DM, CLASSPATH_SELECTION, NESTED_EAR_COMPONENT_CREATION_DM,
                NESTED_UPDATE_MANIFEST_DM, EAR_COMPONENT_HANDLE, USE_ANNOTATIONS };
		return combineProperties(super.getPropertyNames(), props);
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(ADD_TO_EAR)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(UI_SHOW_EAR_SECTION)) {
			return Boolean.TRUE;
		} else if (propertyName.equals(EAR_COMPONENT_NAME)) {
			return getDataModel().getStringProperty(COMPONENT_NAME)+"EAR";
		} else if (propertyName.equals(COMPONENT_VERSION)) {
            return getDefaultComponentVersion();
        } else if (propertyName.equals(NESTED_EAR_COMPONENT_CREATION_DM)) {
            return DataModelFactory.createDataModel(new EarComponentCreationDataModelProvider());
        }
		return super.getDefaultProperty(propertyName);
	}
    
	public boolean propertySet(String propertyName, Object propertyValue) {
        boolean status = super.propertySet(propertyName, propertyValue);
        if (PROJECT_NAME.equals(propertyName) && propertyValue !=null && ((String)propertyValue).length()!=0) {
            IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject((String)propertyValue);
            if (project != null) {
                IProjectProperties projProperties = ServerCore.getProjectProperties(project);
                if( projProperties.getRuntimeTarget() != null ){
                    String[] validModuleVersions = getServerVersions(getComponentID(), projProperties.getRuntimeTarget().getRuntimeType());
                    model.setProperty(VALID_COMPONENT_VERSIONS_FOR_PROJECT_RUNTIME, validModuleVersions);
                }
            }         
        }
        else if (propertyName.equals(EAR_COMPONENT_NAME)) {
			model.setProperty(EAR_COMPONENT_HANDLE, computeEARHandle((String)propertyValue));
            model.setProperty(EAR_COMPONENT_DEPLOY_NAME, propertyValue);
            IDataModel earDM = (IDataModel)model.getProperty(NESTED_EAR_COMPONENT_CREATION_DM);
            earDM.setProperty(PROJECT_NAME, propertyValue);
		} else if(propertyName.equals(COMPONENT_NAME)){
			if (!model.isPropertySet(EAR_COMPONENT_NAME)) {
				model.notifyPropertyChange(EAR_COMPONENT_NAME, IDataModel.VALID_VALUES_CHG);
                model.setProperty(EAR_COMPONENT_DEPLOY_NAME, getProperty(EAR_COMPONENT_NAME));
            }
        } else if (propertyName.equals(PROJECT_NAME)) {
			WorkbenchComponent workbenchComp = getTargetWorkbenchComponent();
			setEARComponentIfJ2EEModuleCreationOnly(workbenchComp,propertyValue);
		} else if (propertyName.equals(ADD_TO_EAR)) {
            model.notifyPropertyChange(NESTED_EAR_COMPONENT_CREATION_DM, IDataModel.DEFAULT_CHG);
			model.notifyPropertyChange(ADD_TO_EAR, IDataModel.VALID_VALUES_CHG);
		}
//		else if (propertyName.equals(J2EE_VERSION)) {
//			Integer modVersion = convertJ2EEVersionToModuleVersion((Integer) propertyValue);
//			propertySet(COMPONENT_VERSION, modVersion);
//			return false;
//		}
		return status;
	}

    private URI computeEARHandle(String earHandle){
		URI uri = URI.createURI(earHandle);
		
		if( uri != null ){
			boolean isValidURI = false;
			try {
				isValidURI = ModuleURIUtil.ensureValidFullyQualifiedModuleURI(uri);
				String deployeName = ModuleURIUtil.getDeployedName(uri);
			}
			catch (UnresolveableURIException e) {
			}
			if(isValidURI){
				return uri;
			}
		}
		return null;		
	}
	/**
	 * @param workbenchComp
	 */
	protected void setEARComponentIfJ2EEModuleCreationOnly(WorkbenchComponent workbenchComp, Object propertyValue) {
        getAddComponentToEARDataModel().setProperty(AddComponentToEnterpriseApplicationDataModel.ARCHIVE_MODULE, workbenchComp);
        getAddComponentToEARDataModel().setProperty(AddComponentToEnterpriseApplicationDataModel.PROJECT_NAME, model.getStringProperty(PROJECT_NAME));
        getAddComponentToEARDataModel().setProperty(AddComponentToEnterpriseApplicationDataModel.EAR_MODULE_NAME,model.getStringProperty(EAR_COMPONENT_NAME));
		if (!model.isPropertySet(EAR_COMPONENT_NAME)) {
			String earModuleName = model.getStringProperty(EAR_COMPONENT_NAME);
            model.notifyPropertyChange(EAR_COMPONENT_NAME, IDataModel.VALID_VALUES_CHG);

		}
		((UpdateManifestDataModel)model.getProperty(NESTED_UPDATE_MANIFEST_DM)).setProperty(UpdateManifestDataModel.PROJECT_NAME, propertyValue);
	}
    
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (propertyName.equals(COMPONENT_VERSION)) {
			return getValidComponentVersionDescriptors();
		}
		if (propertyName.equals(EAR_COMPONENT_NAME)) {
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
								WorkbenchComponent wc = components[i];
								if(wc.getComponentType() != null && wc.getComponentType().getComponentTypeId().equals(IModuleConstants.JST_EAR_MODULE)) {  
									ComponentHandle handle = ComponentHandle.create(flexProject,wc.getName());
									earArtifactEdit = EARArtifactEdit.getEARArtifactEditForRead(handle);
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
			descriptors[i] = new DataModelPropertyDescriptor(desc.getPropertyDescription(), desc.getPropertyDescription());
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

	public IStatus validate(String propertyName) {
		if (EAR_COMPONENT_NAME.equals(propertyName) && getBooleanProperty(ADD_TO_EAR)) {
			return validateEARModuleNameProperty();
		} else if (COMPONENT_VERSION.equals(propertyName)) {
            return validateComponentVersionProperty();
        } else if(propertyName.equals(VALID_COMPONENT_VERSIONS_FOR_PROJECT_RUNTIME)){
            return OK_STATUS;
        }
		return super.validate(propertyName);
	}
    private IStatus validateComponentVersionProperty() {
        int componentVersion = model.getIntProperty(COMPONENT_VERSION);
        if (componentVersion == -1)
            return WTPCommonPlugin.createErrorStatus(WTPCommonPlugin.getResourceString(WTPCommonMessages.SPEC_LEVEL_NOT_FOUND));
        return OK_STATUS;
    }

	private IStatus validateEARModuleNameProperty() {
        IStatus status = OK_STATUS;
        String earName = getStringProperty(EAR_COMPONENT_NAME);
        if (status.isOK()) {
            if (earName.indexOf("#") != -1 || earName.indexOf("/") != -1) { //$NON-NLS-1$
                String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_INVALID_CHARS); //$NON-NLS-1$
                return WTPCommonPlugin.createErrorStatus(errorMessage);
            } else if (earName == null || earName.equals("")) { //$NON-NLS-1$
                String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_EMPTY_MODULE_NAME);
                return WTPCommonPlugin.createErrorStatus(errorMessage);
            }
        } else
            return status;
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
		//return cachedSelection;
        return null;
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
    protected final AddComponentToEnterpriseApplicationDataModel getAddComponentToEARDataModel() {
        return (AddComponentToEnterpriseApplicationDataModel) model.getProperty(NESTED_ADD_COMPONENT_TO_EAR_DM);
    }

    protected final UpdateManifestDataModel getUpdateManifestDataModel() {
        return (UpdateManifestDataModel) model.getProperty(NESTED_UPDATE_MANIFEST_DM);
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

}