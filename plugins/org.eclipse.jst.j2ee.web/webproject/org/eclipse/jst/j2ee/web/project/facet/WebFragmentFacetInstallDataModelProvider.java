/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.project.facet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.eclipse.core.internal.resources.ResourceStatus;
import org.eclipse.core.internal.utils.Messages;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jem.util.emf.workbench.ProjectUtilities;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.core.JavaFacet;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.internal.web.plugin.WebPlugin;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.J2EEModuleFacetInstallDataModelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties.FacetDataModelMap;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelPropertyDescriptor;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class WebFragmentFacetInstallDataModelProvider extends J2EEModuleFacetInstallDataModelProvider implements IWebFragmentFacetInstallDataModelProperties {

	public WebFragmentFacetInstallDataModelProvider() {
		super();
	}

	
	@Override
	public Set<String> getPropertyNames() {
		Set<String> names = super.getPropertyNames();
		names.add(CONTENT_DIR);
		names.add(ADD_TO_WAR);
		names.add(WAR_PROJECT_NAME);
		names.add(LAST_WAR_NAME);
		return names;
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CONTENT_DIR)) {
			return "";  //$NON-NLS-1$
		} else if (propertyName.equals(FACET_ID)) {
			return WEBFRAGMENT; 
		}else if (propertyName.equals(ADD_TO_EAR)) {
			return false; 
		}else if (propertyName.equals(ADD_TO_WAR)) {
			return true; 
		}else if (propertyName.equals(MODULE_URI)) {
			String projectName = model.getStringProperty(FACET_PROJECT_NAME).replace(' ', '_');
			return projectName + IJ2EEModuleConstants.JAR_EXT;
		}else if (propertyName.equals(ADD_TO_WAR)) {
			return new Boolean( J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.ADD_TO_EAR_BY_DEFAULT) && isWARSupportedByRuntime());
		} else if (propertyName.equals(WAR_PROJECT_NAME)) {
			if (model.isPropertySet(LAST_WAR_NAME)) {
				IProject project = ProjectUtilities.getProject(getStringProperty(LAST_WAR_NAME));
				if (project.exists() && project.isAccessible())
					return project.getName();
			}
			DataModelPropertyDescriptor[] descs = getValidPropertyDescriptors(WAR_PROJECT_NAME);
			if (descs.length > 0) {
				DataModelPropertyDescriptor desc = descs[0];
				String eARName = desc.getPropertyDescription();
				if (eARName != null && !eARName.equals("")) { //$NON-NLS-1$
					return eARName;
				}
				return getDataModel().getStringProperty(FACET_PROJECT_NAME) + "WAR"; //$NON-NLS-1$
			}
			return getDataModel().getStringProperty(FACET_PROJECT_NAME) + "WAR"; //$NON-NLS-1$
		} else if (propertyName.equals( CONFIG_FOLDER )){
			
			// Return the first source folder found in the java facet install config or 
			// the first source folder defined in the classpath
			final IFacetedProjectWorkingCopy localFpjwc 
			= (IFacetedProjectWorkingCopy) getProperty( FACETED_PROJECT_WORKING_COPY );

			if( this.javaFacetInstallConfig != null )
			{
				final List<IPath> sourceFolders = this.javaFacetInstallConfig.getSourceFolders();

				if( ! sourceFolders.isEmpty() )
				{
					return sourceFolders.get( 0 ).toPortableString();
				}
			}
			else
			{
				final IFacetedProject fpj = localFpjwc.getFacetedProject();

				if( fpj.hasProjectFacet( JavaFacet.FACET ) )
				{
					try
					{
						final IJavaProject jpj = JavaCore.create( fpj.getProject() );

						for( IClasspathEntry cpe : jpj.getRawClasspath() )
						{
							if( cpe.getEntryKind() == IClasspathEntry.CPE_SOURCE )
							{
								return cpe.getPath().removeFirstSegments( 1 ).toPortableString();
							}
						}
					}
					catch( CoreException e )
					{
						WebPlugin.logError(e);
					}
				}
			}
		}
		return super.getDefaultProperty(propertyName);
	}
	
	
	@Override
	public IStatus validate(String name) {
		
		if (name.equals(CONTENT_DIR)) {
			String folderName = model.getStringProperty(CONTENT_DIR);
			IStatus status = validateFolderName(folderName);
			if (status.isOK())
			{
				if (folderName.indexOf('#') != -1) { 
					String message = NLS.bind(Messages.resources_invalidCharInName, "#", folderName); //$NON-NLS-1$
					status = new ResourceStatus(IResourceStatus.INVALID_VALUE, null, message);
				}
			}
			return status;
		}
		
		return super.validate(name);
	}
	
	
		@Override
		protected IStatus validateFolderName(String folderName) {
		// the directory is not required, but if the name is entered ensure that it 
		// contains only valid characters.
		if (folderName == null || folderName.length() == 0) {
			return OK_STATUS;
		}
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IPath path = new Path(folderName);
		for (int i = 0, max = path.segmentCount(); i < max; i++) {
			IStatus status = workspace.validateName(path.segment(i), IResource.FOLDER);
			if (! status.isOK())
				return status;
		}

		// all of the potential segments of the folder have been verified
		return OK_STATUS;
	}
	
		private boolean isWARSupportedByRuntime() {
		boolean ret = true;
		IRuntime rt = (IRuntime) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME);
		if (rt != null)
			ret = rt.supports(IJ2EEFacetConstants.DYNAMIC_WEB_FACET);
		return ret;
	}
		
		@Override
		public DataModelPropertyDescriptor[] getValidPropertyDescriptors(String propertyName) {
			if (WAR_PROJECT_NAME.equals(propertyName)) {
				int j2eeVersion = J2EEVersionUtil.convertVersionStringToInt(((IProjectFacetVersion) getProperty(FACET_VERSION)).getVersionString());
				return getWebAppPropertyDescriptors(j2eeVersion);
			}
			return super.getValidPropertyDescriptors(propertyName);
		}

		
		@Override
		public boolean propertySet(String propertyName, Object propertyValue) {
			if (FACET_PROJECT_NAME.equals(propertyName)) {
					if (getBooleanProperty(ADD_TO_WAR)) {
					if (!model.isPropertySet(WAR_PROJECT_NAME)) {
						model.notifyPropertyChange(WAR_PROJECT_NAME, IDataModel.DEFAULT_CHG);
					}
				}
			} else if (FACET_VERSION.equals(propertyName)) {
				model.notifyPropertyChange(WAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
			} else if (propertyName.equals(CONFIG_FOLDER)) {
				IDataModel masterModel = (IDataModel) model.getProperty(MASTER_PROJECT_DM);
				if (masterModel != null) {
					FacetDataModelMap map = (FacetDataModelMap) masterModel.getProperty(IFacetProjectCreationDataModelProperties.FACET_DM_MAP);
					IDataModel javaModel = map.getFacetDataModel(JAVA);
					if (javaModel != null) {
						javaModel.setProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, propertyValue);
					}
				}
			} else if ((WAR_PROJECT_NAME.equals(propertyName) || ADD_TO_WAR.equals(propertyName) || LAST_WAR_NAME.equals(propertyName)) && getBooleanProperty(ADD_TO_WAR)) {
				//ValidateEAR really does general checks for project name. So its applicable here too.
				IStatus status = validateEAR(model.getStringProperty(WAR_PROJECT_NAME));
				if (status.isOK()) {
					IProject project = ProjectUtilities.getProject(getStringProperty(WAR_PROJECT_NAME));
					if (project.exists() && project.isAccessible() && JavaEEProjectUtilities.isDynamicWebProject(project)) {
						try {
							IFacetedProject facetProj = ProjectFacetsManager.create(project, false, new NullProgressMonitor());
							setProperty(FACET_RUNTIME, facetProj.getRuntime());
						} catch (CoreException e) {
							WebPlugin.logError(e);
						}
					}
				}
				model.notifyPropertyChange(FACET_RUNTIME, IDataModel.ENABLE_CHG);
			} else if (propertyName.equals(IFacetProjectCreationDataModelProperties.FACET_RUNTIME)) {
				model.notifyPropertyChange(ADD_TO_WAR, IDataModel.VALID_VALUES_CHG);
				model.notifyPropertyChange(WAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
			} 
			
			if (ADD_TO_WAR.equals(propertyName)) {
				IStatus stat = model.validateProperty(propertyName);
				if (stat != OK_STATUS) {
					return true;
				}
				model.notifyPropertyChange(WAR_PROJECT_NAME, IDataModel.VALID_VALUES_CHG);
			}
	       

			return super.propertySet(propertyName, propertyValue);
		}
		
	private DataModelPropertyDescriptor[] getWebAppPropertyDescriptors(int webAppVersion) {
		ArrayList webAppDescriptorList = new ArrayList();

		IProject[] projs = ProjectUtilities.getAllProjects();

		for (int index = 0; index < projs.length; index++) {
			IProject flexProject = projs[index];
			if (flexProject != null) {
				if (ModuleCoreNature.isFlexibleProject(flexProject)) {
					IVirtualComponent comp = ComponentCore.createComponent(flexProject);
					if (JavaEEProjectUtilities.isDynamicWebProject(comp.getProject())) {
						String sVer = J2EEProjectUtilities.getJ2EEProjectVersion(comp.getProject());
						int ver = J2EEVersionUtil.convertVersionStringToInt(sVer);
						if (webAppVersion <= ver) {
							DataModelPropertyDescriptor desc = new DataModelPropertyDescriptor(comp.getProject().getName());
							webAppDescriptorList.add(desc);
						}
					}
				}
			}
		}
		DataModelPropertyDescriptor[] descriptors = new DataModelPropertyDescriptor[webAppDescriptorList.size()];
		for (int i = 0; i < descriptors.length; i++) {
			DataModelPropertyDescriptor desc = (DataModelPropertyDescriptor) webAppDescriptorList.get(i);
			descriptors[i] = new DataModelPropertyDescriptor(desc.getPropertyDescription(), desc.getPropertyDescription());
		}
		return descriptors;
	}
	
	@Override
	public boolean isPropertyEnabled(String propertyName) {
		if (ADD_TO_WAR.equals(propertyName)) {
			return isWARSupportedByRuntime();
		}
		if (WAR_PROJECT_NAME.equals(propertyName)) {
			return isWARSupportedByRuntime() && getBooleanProperty(ADD_TO_WAR);
		}
		return super.isPropertyEnabled(propertyName);
	}
		
}
