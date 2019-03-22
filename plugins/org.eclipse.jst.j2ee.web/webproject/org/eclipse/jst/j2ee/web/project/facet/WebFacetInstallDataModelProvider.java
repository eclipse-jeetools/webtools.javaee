/*******************************************************************************
 * Copyright (c) 2003, 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.project.facet;

import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig.ChangeEvent;
import org.eclipse.jst.j2ee.componentcore.J2EEModuleVirtualComponent;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.J2EEVersionConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.common.classpath.J2EEComponentClasspathContainerUtils;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.j2ee.project.facet.J2EEModuleFacetInstallDataModelProvider;
import org.eclipse.jst.server.core.FacetUtil;
import org.eclipse.wst.common.componentcore.ComponentCore;
import org.eclipse.wst.common.componentcore.ModuleCoreNature;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualFolder;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;
import org.eclipse.wst.project.facet.ProductManager;
import org.eclipse.wst.server.core.IRuntimeType;

import com.ibm.icu.text.UTF16;
import com.ibm.icu.util.StringTokenizer;

public class WebFacetInstallDataModelProvider extends J2EEModuleFacetInstallDataModelProvider implements IWebFacetInstallDataModelProperties {

    @Override
	protected void handleJavaFacetSourceFoldersChanged( final ChangeEvent event )
    {
        final List<IPath> sourceFolders = event.getJavaFacetInstallConfig().getSourceFolders();
        final String sourceFolder = ( sourceFolders.isEmpty() ? null : sourceFolders.get( 0 ).toPortableString() );
        getDataModel().setProperty( SOURCE_FOLDER, sourceFolder );
    }

    @Override
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(CONTEXT_ROOT);
		names.add(SOURCE_FOLDER);
		names.add(INSTALL_WEB_LIBRARY);
		return names;
	}

	@Override
	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CONFIG_FOLDER)) {
			if (model.isPropertySet(FACET_PROJECT_NAME))
			{
				String projectName = model.getStringProperty(FACET_PROJECT_NAME);
				IPath path = new Path(projectName);
				IProject project = (projectName.length() > 0 && path.isValidSegment(projectName)) ? ResourcesPlugin.getWorkspace().getRoot().getProject(projectName) : null;
				if (project != null && project.exists()) {
					if (ModuleCoreNature.isFlexibleProject(project))
					{
						IVirtualComponent c = ComponentCore.createComponent(project, true);
						IVirtualFolder ejbroot = c.getRootFolder();
						IPath configFolderPath = J2EEModuleVirtualComponent.getDefaultDeploymentDescriptorFolder(ejbroot);
						if (configFolderPath != null && project.getFolder(configFolderPath).exists())
						{
							return configFolderPath.toString();
						}
					}
				}
			}
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.WEB_CONTENT_FOLDER);
		} else if (propertyName.equals(SOURCE_FOLDER)) {
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.DYN_WEB_SRC_FOLDER);
		} else if (propertyName.equals(CONTEXT_ROOT)) {
			return getStringProperty(FACET_PROJECT_NAME).replace(' ', '_');
		} else if (propertyName.equals(FACET_ID)) {
			return DYNAMIC_WEB;
		} else if (propertyName.equals(MODULE_URI)) {
			String projectName = model.getStringProperty(FACET_PROJECT_NAME).replace(' ', '_');
			return projectName + IJ2EEModuleConstants.WAR_EXT;
		} else if (propertyName.equals(GENERATE_DD)) {
			String facetVersion = getStringProperty(FACET_VERSION_STR);
			if(J2EEVersionConstants.VERSION_2_5_TEXT.equals(facetVersion)) {
				return Boolean.valueOf(J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.DYNAMIC_WEB_GENERATE_DD));
			}
			else if(J2EEVersionConstants.VERSION_3_0_TEXT.equals(facetVersion)) {
				return Boolean.valueOf(J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.EE6_DYNAMIC_WEB_GENERATE_DD));
			}
			else if(J2EEVersionConstants.VERSION_3_1_TEXT.equals(facetVersion)) {
				return Boolean.valueOf(J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.EE7_DYNAMIC_WEB_GENERATE_DD));
			}
			else if(J2EEVersionConstants.VERSION_4_0_TEXT.equals(facetVersion)) {
				return Boolean.valueOf(J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.EE8_DYNAMIC_WEB_GENERATE_DD));
			}
			return Boolean.TRUE;
		}else if (propertyName.equals(INSTALL_WEB_LIBRARY)){
			return J2EEComponentClasspathContainerUtils.getDefaultUseWebAppLibraries();
		} 
		else if (propertyName.equals(ADD_TO_EAR)) {
			Object result = super.getDefaultProperty(propertyName);
			if (result instanceof Boolean) {
				return ((Boolean) result).booleanValue() && isEARDefaultForRuntime();
			}
		}
		return super.getDefaultProperty(propertyName);
	}
	
	private boolean isEARDefaultForRuntime() {
	boolean result = true;
	IRuntime rt = (IRuntime) model.getProperty(IFacetProjectCreationDataModelProperties.FACET_RUNTIME);
	if (rt != null) {
		IRuntimeType rtType = FacetUtil.getRuntime(rt).getRuntimeType();
		if (rtType.getId() != null) {
			for(String excluded :
					J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.ADD_TO_EAR_RUNTIME_EXCEPTIONS).split(",")) { //$NON-NLS-1$
				
				if (rtType.getId().equals(excluded)) {
					result = false;
					break;
				}
			}
		}
	}
	
	return result;
}

	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		if (ADD_TO_EAR.equals(propertyName)) {
			model.notifyPropertyChange(CONTEXT_ROOT, IDataModel.ENABLE_CHG);
		} else if (FACET_PROJECT_NAME.equals(propertyName)) {
			model.notifyPropertyChange(CONTEXT_ROOT, IDataModel.VALID_VALUES_CHG);
			if (!model.isPropertySet(CONFIG_FOLDER)) {
				model.notifyPropertyChange(CONFIG_FOLDER, IDataModel.DEFAULT_CHG);
			}
		} else if (propertyName.equals(CONFIG_FOLDER)) {
			return true;
		} else if (propertyName.equals(SOURCE_FOLDER)) 
		{
		    if( this.javaFacetInstallConfig != null )
		    {
		        if( propertyValue == null )
		        {
		            this.javaFacetInstallConfig.setSourceFolder( null );
		        }
		        else
		        {
		            final IPath sourceFolder = new Path( (String) propertyValue );
		            
		            if( ! this.javaFacetInstallConfig.getSourceFolders().contains( sourceFolder ) )
		            {
		                this.javaFacetInstallConfig.setSourceFolder( sourceFolder );
		            }
		        }
			}
		}
		
		return super.propertySet(propertyName, propertyValue);
	}

	@Override
	public boolean isPropertyEnabled(String propertyName) {
		return super.isPropertyEnabled(propertyName);
	}

	@Override
	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return J2EEVersionUtil.convertWebVersionStringToJ2EEVersionID(version.getVersionString());
	}

	@Override
	public IStatus validate(String name) {
		if (name.equals(CONTEXT_ROOT)) {
			return validateContextRoot(getStringProperty(CONTEXT_ROOT));
		} else if (name.equals(SOURCE_FOLDER)) {
			IStatus status =  validateFolderName(getStringProperty(SOURCE_FOLDER));
			if( status.isOK() ){
				return validateSourceAndContentFolderUniqueness();
			}
			return status;			
		}else if (name.equals( CONFIG_FOLDER )) {
			IStatus status = super.validate( CONFIG_FOLDER );
			if( status.isOK() ){
				status = validateSourceAndContentFolderUniqueness();
			}
			if( status.isOK() ){
				String configFolder = model.getStringProperty(CONFIG_FOLDER);
				String outFolder = null;
			    if( javaFacetInstallConfig != null ){
			    	outFolder = javaFacetInstallConfig.getDefaultOutputFolder().toString();
				}				
			    if( outFolder != null )
			    	status = validateWebConfigAndOutputFolder(configFolder, outFolder);
			}
			return status;
		}
		// the superclass validates the content directory which is actually a "CONFIG_FOLDER"
		return super.validate(name);
	}

	protected IStatus validateContextRoot(String contextRoot) {
		if (contextRoot == null || contextRoot.length() == 0) {
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Context_Root_cannot_be_empty_2, new Object[]{contextRoot}), null);
		} else if (contextRoot.trim().equals(contextRoot)) {
			StringTokenizer stok = new StringTokenizer(contextRoot, "."); //$NON-NLS-1$
			while (stok.hasMoreTokens()) {
				String token = stok.nextToken();
				int cp;
		        for (int i = 0; i < token.length(); i += UTF16.getCharCount(cp)) {
		            cp = UTF16.charAt(token, i);
					if(token.charAt(i) == ' ')
					{
						return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Names_cannot_contain_whitespace_, new Object[]{contextRoot}), null); 
					}
					else if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-') && !(token.charAt(i) == '/') && Character.isLetterOrDigit(token.charAt(i)) == false) {
						String invalidCharString = null;
						if (UTF16.getCharCount(cp)>1)
						{
							invalidCharString = UTF16.valueOf(cp); 
						}
						else
						{
							invalidCharString = (new Character(token.charAt(i))).toString();
						}
						Object[] invalidChar = new Object[]{invalidCharString};
						String errorStatus = ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.The_character_is_invalid_in_a_context_root, invalidChar);
						return J2EEPlugin.newErrorStatus(errorStatus, null);
					}
				}
			}
		} else
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Names_cannot_contain_whitespace_, new Object[]{contextRoot}), null); 
		return OK_STATUS;
	}
	
	protected IStatus validateSourceAndContentFolderUniqueness(){
		String srcFolder = getStringProperty(SOURCE_FOLDER);
		String contentFolder = getStringProperty( CONFIG_FOLDER );
		if( srcFolder != null && contentFolder != null){
			if (srcFolder.equals( contentFolder )){
				String errorMessage = ProjectSupportResourceHandler.DYNAMIC_WEB_DISTINCT_SRC_WEBCONTENT_ERROR;
				return WTPCommonPlugin.createErrorStatus(errorMessage);
			}
		}
		return OK_STATUS;
	}
	
	public static IStatus validateWebConfigAndOutputFolder(String rawConfigFolder, String outFolder){ 
		if( ProductManager.shouldUseSingleRootStructure()){
			String expectedOutFolder = rawConfigFolder+"/"+J2EEConstants.WEB_INF_CLASSES; //$NON-NLS-1$
			if( !outFolder.equals(expectedOutFolder) ){
				String message = ProjectSupportResourceHandler.getString( ProjectSupportResourceHandler.DYNAMIC_WEB_PERFORMANCE_VALIDATION,
						new Object[]{expectedOutFolder});
				return WTPCommonPlugin.createWarningStatus(message);
			}
		}
		return OK_STATUS;
	}	
}
