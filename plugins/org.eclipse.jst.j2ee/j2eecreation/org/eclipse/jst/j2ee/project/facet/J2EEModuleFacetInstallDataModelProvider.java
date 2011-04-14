/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.internal.resources.ResourceStatus;
import org.eclipse.core.internal.utils.Messages;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig.ChangeEvent;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathContainerUtils;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProviderNew;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectEvent;
import org.eclipse.wst.common.project.facet.core.events.IFacetedProjectListener;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.common.project.facet.core.util.IEventListener;

public abstract class J2EEModuleFacetInstallDataModelProvider extends J2EEFacetInstallDataModelProvider implements IJ2EEModuleFacetInstallDataModelProperties {

	/**
	 * An internal Boolean property used to prohibit adding this module to an EAR. This is set on
	 * the nested models when used during EAR creation since EAR creation handles adding to the EAR
	 */
	public static final String PROHIBIT_ADD_TO_EAR = "J2EEModuleFacetInstallDataModelProvider.PROHIBIT_ADD_TO_EAR"; //$NON-NLS-1$

    protected IFacetedProjectWorkingCopy fpjwc = null;
    private IFacetedProjectListener fpjwcListener = null;
    protected JavaFacetInstallConfig javaFacetInstallConfig = null;

    private IEventListener<JavaFacetInstallConfig.ChangeEvent> javaFacetSourceFolderListener 
        = new IEventListener<JavaFacetInstallConfig.ChangeEvent>()
    {
        public void handleEvent( final ChangeEvent event ) 
        {
            handleJavaFacetSourceFoldersChanged( event );
        }
    };

    protected void handleJavaFacetSourceFoldersChanged( final ChangeEvent event )
    {
        final List<IPath> sourceFolders = event.getJavaFacetInstallConfig().getSourceFolders();
        final String sourceFolder = ( sourceFolders.isEmpty() ? null : sourceFolders.get( 0 ).toPortableString() );
        getDataModel().setProperty( CONFIG_FOLDER, sourceFolder );
    }
	
	@Override
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(ADD_TO_EAR);
		names.add(PROHIBIT_ADD_TO_EAR);
		names.add(CONFIG_FOLDER);
		names.add(EAR_PROJECT_NAME);
		names.add(LAST_EAR_NAME);
		names.add(MODULE_URI);
		// added for jee modules that make deployment descriptors optional
		names.add(IJ2EEFacetInstallDataModelProperties.GENERATE_DD);
		names.add(INSTALL_EAR_LIBRARY);
		return names;
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(PROHIBIT_ADD_TO_EAR)) {
			return Boolean.FALSE;
		} else if (propertyName.equals(ADD_TO_EAR)) {
			return new Boolean( J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.ADD_TO_EAR_BY_DEFAULT) && isEARSupportedByRuntime());
		} else if (propertyName.equals(EAR_PROJECT_NAME)) {
			DataModelPropertyDescriptor[] descs = getValidPropertyDescriptors(EAR_PROJECT_NAME);
			if (model.isPropertySet(LAST_EAR_NAME)) {
				IProject project = ProjectUtilities.getProject(getStringProperty(LAST_EAR_NAME));
				for (int i = 0; i < descs.length; i++) {
					if (project.exists() && project.isAccessible() && project.getName().equals(descs[i].getPropertyDescription())){
						return project.getName();
					}
				}
				return getDataModel().getStringProperty(FACET_PROJECT_NAME) + "EAR"; //$NON-NLS-1$
			}
			if (descs.length > 0) {
				DataModelPropertyDescriptor desc = descs[0];
				String eARName = desc.getPropertyDescription();
				if (eARName != null && !eARName.equals("")) { //$NON-NLS-1$
					return eARName;
				}
				return getDataModel().getStringProperty(FACET_PROJECT_NAME) + "EAR"; //$NON-NLS-1$
			}
			return getDataModel().getStringProperty(FACET_PROJECT_NAME) + "EAR"; //$NON-NLS-1$
		}else if (propertyName.equals(INSTALL_EAR_LIBRARY)){
			return J2EEComponentClasspathContainerUtils.getDefaultUseEARLibraries();
		}
		return super.getDefaultProperty(propertyName);
	}

	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		if (propertyName.equals(PROHIBIT_ADD_TO_EAR)) {
			setBooleanProperty(ADD_TO_EAR, false);
		}
		if (FACET_PROJECT_NAME.equals(propertyName)) {
			if (getBooleanProperty(ADD_TO_EAR)) {
				if (!model.isPropertySet(EAR_PROJECT_NAME)) {
					model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.DEFAULT_CHG);
				}
			}
		} else if (FACET_VERSION.equals(propertyName)) {
			model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
		} else if (propertyName.equals(CONFIG_FOLDER)) {
			IDataModel masterModel = (IDataModel) model.getProperty(MASTER_PROJECT_DM);
			if (masterModel != null) {
				FacetDataModelMap map = (FacetDataModelMap) masterModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
				IDataModel javaModel = map.getFacetDataModel(JAVA);
				if (javaModel != null) {
					javaModel.setProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, propertyValue);
				}
			}
		} else if ((EAR_PROJECT_NAME.equals(propertyName) || ADD_TO_EAR.equals(propertyName) || LAST_EAR_NAME.equals(propertyName)) && getBooleanProperty(ADD_TO_EAR)) {
			IStatus status = validateEAR(model.getStringProperty(EAR_PROJECT_NAME));
			if (status.isOK()) {
				IProject project = ProjectUtilities.getProject(getStringProperty(EAR_PROJECT_NAME));
				if (project.exists() && project.isAccessible() && JavaEEProjectUtilities.isEARProject(project)) {
					try {
						IFacetedProject facetProj = ProjectFacetsManager.create(project, false, new NullProgressMonitor());
						setProperty(FACET_RUNTIME, facetProj.getRuntime());
					} catch (CoreException e) {
						J2EEPlugin.logError(e);
					}
				}
			}
			model.notifyPropertyChange(FACET_RUNTIME, IDataModel.ENABLE_CHG);
		} else if (propertyName.equals(IFacetProjectCreationDataModelProperties.FACET_RUNTIME)) {
			model.notifyPropertyChange(ADD_TO_EAR, IDataModel.VALID_VALUES_CHG);
			model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
		}
        else if( propertyName.equals( FACETED_PROJECT_WORKING_COPY ) )
        {
            this.fpjwc = (IFacetedProjectWorkingCopy) propertyValue;

            this.fpjwcListener = new IFacetedProjectListener()
            {
                public void handleEvent( final IFacetedProjectEvent event ) 
                {
                    handleProjectFacetsChanged();
                }
            };
            
            this.fpjwc.addListener( this.fpjwcListener, IFacetedProjectEvent.Type.PROJECT_FACETS_CHANGED );
        }

		if (ADD_TO_EAR.equals(propertyName)) {
			IStatus stat = model.validateProperty(propertyName);
			if (stat != OK_STATUS) {
				return true;
			}
			model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
		}

		return super.propertySet(propertyName, propertyValue);
	}
	
    private void handleProjectFacetsChanged()
    {
        final IFacetedProject.Action javaInstallAction
            = this.fpjwc.getProjectFacetAction( JavaFacetUtils.JAVA_FACET );
        
        if( javaInstallAction != null )
        {
            final Object config = javaInstallAction.getConfig();
            
            if(config != null)
            {
            	if( config instanceof JavaFacetInstallConfig )
            	{
            		this.javaFacetInstallConfig = (JavaFacetInstallConfig) config;
            	}
            	else
            	{
            		this.javaFacetInstallConfig = (JavaFacetInstallConfig) Platform.getAdapterManager().getAdapter( config, JavaFacetInstallConfig.class );
            	}
            	if (this.javaFacetInstallConfig != null)
            	{
            		this.javaFacetInstallConfig.addListener( this.javaFacetSourceFolderListener, JavaFacetInstallConfig.ChangeEvent.Type.SOURCE_FOLDERS_CHANGED );
            	}
            }
        }
    }

	@Override
	public boolean isPropertyEnabled(String propertyName) {
		if (ADD_TO_EAR.equals(propertyName)) {
			return !getBooleanProperty(PROHIBIT_ADD_TO_EAR) && isEARSupportedByRuntime();
		}
		if (EAR_PROJECT_NAME.equals(propertyName)) {
			return !getBooleanProperty(PROHIBIT_ADD_TO_EAR) && isEARSupportedByRuntime() && getBooleanProperty(ADD_TO_EAR);
		}
		return super.isPropertyEnabled(propertyName);
	}

	@Override
	public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
		if (EAR_PROJECT_NAME.equals(propertyName)) {
			int j2eeVersion = getJ2EEVersion();
			return getEARPropertyDescriptors(j2eeVersion);
		}
		return super.getValidPropertyDescriptors(propertyName);
	}

	protected DataModelPropertyDescriptor[] getEARPropertyDescriptors(int j2eeVersion) {
		ArrayList earDescriptorList = new ArrayList();

		IProject[] projs = ProjectUtilities.getAllProjects();

		for (int index = 0; index < projs.length; index++) {
			IProject flexProject = projs[index];
			if (flexProject != null) {
				if (ModuleCoreNature.isFlexibleProject(flexProject)) {
					IVirtualComponent comp = ComponentCore.createComponent(flexProject);
					if (JavaEEProjectUtilities.isEARProject(comp.getProject())) {
						String sVer = J2EEProjectUtilities.getJ2EEProjectVersion(comp.getProject());
						int ver = J2EEVersionUtil.convertVersionStringToInt(sVer);
						if (j2eeVersion <= ver) {
							DataModelPropertyDescriptor desc = new DataModelPropertyDescriptor(comp.getProject().getName());
							earDescriptorList.add(desc);
						}
					}
				}
			}
		}
		DataModelPropertyDescriptor[] descriptors = new DataModelPropertyDescriptor[earDescriptorList.size()];
		for (int i = 0; i < descriptors.length; i++) {
			DataModelPropertyDescriptor desc = (DataModelPropertyDescriptor) earDescriptorList.get(i);
			descriptors[i] = new DataModelPropertyDescriptor(desc.getPropertyDescription(), desc.getPropertyDescription());
		}
		return descriptors;
	}

	@Override
	public IStatus validate(String name) {
		if (name.equals(EAR_PROJECT_NAME) && getBooleanProperty(ADD_TO_EAR)) {
			IStatus status = validateEAR(getStringProperty(EAR_PROJECT_NAME));
			if (!status.isOK())
				return status;
			final IFacetedProjectWorkingCopy fpjwc = (IFacetedProjectWorkingCopy) getProperty(FACETED_PROJECT_WORKING_COPY);
			String fpjwcName = fpjwc.getProjectName();
			if(fpjwcName != null && fpjwcName.equals(getStringProperty(EAR_PROJECT_NAME))){
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME, new Object [] {getStringProperty(EAR_PROJECT_NAME) });
				return WTPCommonPlugin.createErrorStatus(errorMessage);
			}
		} else if (name.equals(CONFIG_FOLDER)) {
			String folderName = model.getStringProperty(CONFIG_FOLDER);
			if (folderName == null || folderName.length() == 0 || folderName.equals("/") || folderName.equals("\\")) { //$NON-NLS-1$ //$NON-NLS-2$
				// all folders which meet the criteria of "CONFIG_FOLDER" are required
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.SOURCEFOLDER_EMPTY);
				return WTPCommonPlugin.createErrorStatus(errorMessage);
			}
			IStatus status = validateFolderName(folderName);
			if (status.isOK())
			{
				/* bug 223072 test invalid character - URI.FRAGMENT_SEPARATOR */
				if (folderName.indexOf('#') != -1) { 
					String message = NLS.bind(Messages.resources_invalidCharInName, "#", folderName); //$NON-NLS-1$
					status = new ResourceStatus(IResourceStatus.INVALID_VALUE, null, message);
				}
			}
			return status;
		} else if (name.equals(ADD_TO_EAR)) {
			if (!isEARSupportedByRuntime()) {
				String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.MODULE_NOT_SUPPORTED);
				return WTPCommonPlugin.createErrorStatus(errorMessage);
			}
		}
		return super.validate(name);
	}

	protected IStatus validateEAR(String earName) {
		if (earName == null || earName.equals("")) { //$NON-NLS-1$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_EMPTY_MODULE_NAME);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		} else if (earName.indexOf("#") != -1 || earName.indexOf("/") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_INVALID_CHARS);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		}
		return (ProjectCreationDataModelProviderNew.validateProjectName(earName));
	}

	private boolean isEARSupportedByRuntime() {
		boolean ret = true;
		IRuntime rt = (IRuntime) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME);
		if (rt != null)
			ret = rt.supports(IJ2EEFacetConstants.ENTERPRISE_APPLICATION_FACET);
		return ret;
	}

    @Override
    protected int convertFacetVersionToJ2EEVersion( IProjectFacetVersion version )
    {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void dispose()
    {
        if( this.fpjwc != null )
        {
            this.fpjwc.removeListener( this.fpjwcListener );
        }
    }
	
}
