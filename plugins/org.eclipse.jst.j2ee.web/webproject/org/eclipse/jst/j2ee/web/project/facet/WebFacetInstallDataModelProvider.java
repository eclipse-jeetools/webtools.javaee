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
package org.eclipse.jst.j2ee.web.project.facet;

import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.common.project.facet.core.JavaFacetInstallConfig;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.common.J2EEVersionUtil;
import org.eclipse.jst.j2ee.internal.plugin.IJ2EEModuleConstants;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences;
import org.eclipse.jst.j2ee.internal.project.ProjectSupportResourceHandler;
import org.eclipse.jst.j2ee.project.facet.J2EEModuleFacetInstallDataModelProvider;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.project.facet.ProductManager;

import com.ibm.icu.util.StringTokenizer;

public class WebFacetInstallDataModelProvider extends J2EEModuleFacetInstallDataModelProvider implements IWebFacetInstallDataModelProperties {

	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(CONTEXT_ROOT);
		names.add(SOURCE_FOLDER);
		return names;
	}

	public Object getDefaultProperty(String propertyName) {
		if (propertyName.equals(CONFIG_FOLDER)) {
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
			IProjectFacetVersion facetVersion = (IProjectFacetVersion)getProperty(FACET_VERSION);
			if(facetVersion == WebFacetUtils.WEB_25){
				return Boolean.valueOf(J2EEPlugin.getDefault().getJ2EEPreferences().getBoolean(J2EEPreferences.Keys.DYNAMIC_WEB_GENERATE_DD));
			}
			return Boolean.TRUE;
		}else if (propertyName.equals(OUTPUT_FOLDER)) {
			return J2EEPlugin.getDefault().getJ2EEPreferences().getString(J2EEPreferences.Keys.DYN_WEB_OUTPUT_FOLDER);	
		}
		return super.getDefaultProperty(propertyName);
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		if (ADD_TO_EAR.equals(propertyName)) {
			model.notifyPropertyChange(CONTEXT_ROOT, IDataModel.ENABLE_CHG);
		} else if (FACET_PROJECT_NAME.equals(propertyName)) {
			model.notifyPropertyChange(CONTEXT_ROOT, IDataModel.VALID_VALUES_CHG);
		} else if (propertyName.equals(CONFIG_FOLDER)) {
			return true;
		} else if (propertyName.equals(SOURCE_FOLDER)) 
		{
		    final JavaFacetInstallConfig javaModel = findJavaFacetInstallConfig();
		    
		    if( javaModel != null )
		    {
		        final IPath sourceFolder
		            = propertyValue == null ? null : new Path( (String) propertyValue );
		        
		        javaModel.setSourceFolder( sourceFolder );
			}
		}
		return super.propertySet(propertyName, propertyValue);
	}
	
	private JavaFacetInstallConfig findJavaFacetInstallConfig()
	{
        final IFacetedProjectWorkingCopy fpjwc 
            = (IFacetedProjectWorkingCopy) this.model.getProperty( FACETED_PROJECT_WORKING_COPY );
        
        if( fpjwc != null )
        {
            final IFacetedProject.Action javaInstallAction
                = fpjwc.getProjectFacetAction( JavaFacetUtils.JAVA_FACET );
            
            final Object config = javaInstallAction.getConfig();
            
            if( config instanceof JavaFacetInstallConfig )
            {
                return (JavaFacetInstallConfig) config;
            }
            else
            {
                return (JavaFacetInstallConfig) Platform.getAdapterManager().getAdapter( config, JavaFacetInstallConfig.class );
            }
        }
        
        return null;
	}

	public boolean isPropertyEnabled(String propertyName) {
		return super.isPropertyEnabled(propertyName);
	}

	protected int convertFacetVersionToJ2EEVersion(IProjectFacetVersion version) {
		return J2EEVersionUtil.convertWebVersionStringToJ2EEVersionID(version.getVersionString());
	}

	public IStatus validate(String name) {
		if (name.equals(CONTEXT_ROOT)) {
			return validateContextRoot(getStringProperty(CONTEXT_ROOT));
		} else if (name.equals(SOURCE_FOLDER)) {
			IStatus status =  validateFolderName(getStringProperty(SOURCE_FOLDER));
			if( status.isOK() ){
				status = validateFolderForCharacters(getStringProperty(SOURCE_FOLDER));
			}			
			if( status.isOK() ){
				status = validateSourceAndContentFolderUniqueness();
			}
			if(status.isOK()){
				String outfolderName = model.getStringProperty(OUTPUT_FOLDER);
				String srcfolderName = model.getStringProperty(SOURCE_FOLDER);
				status = validateSourceAndOutputFolderCase(srcfolderName, outfolderName);
			}				
			return status;			
			
		}else if (name.equals( CONFIG_FOLDER )) {
			IStatus status = super.validate( CONFIG_FOLDER );
			if( status.isOK() ){
				status = validateSourceAndContentFolderUniqueness();
			}
			if( status.isOK() ){
				String configFolder = model.getStringProperty(CONFIG_FOLDER);
				String outFolder = model.getStringProperty(OUTPUT_FOLDER);
				status = validateWebConfigAndOutputFolder(configFolder, outFolder);
			}
			return status;
		}else if (name.equals(OUTPUT_FOLDER)) {
			IStatus status = super.validate(OUTPUT_FOLDER);
			if(status.isOK()){
				String configFolder = model.getStringProperty(CONFIG_FOLDER);
				String outFolder = model.getStringProperty(OUTPUT_FOLDER);
				status = validateWebConfigAndOutputFolder(configFolder, outFolder);
			}
			if(status.isOK()){
				String outfolderName = model.getStringProperty(OUTPUT_FOLDER);
				String srcfolderName = model.getStringProperty(SOURCE_FOLDER);
				status = validateSourceAndOutputFolderCase(srcfolderName, outfolderName);
			}				
			return status;
		}
		// the superclass validates the content directory which is actually a "CONFIG_FOLDER"
		return super.validate(name);
	}

	protected IStatus validateContextRoot(String contextRoot) {
		if (contextRoot == null) {
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Context_Root_cannot_be_empty_2, new Object[]{contextRoot}), null);
		} else if (contextRoot.trim().equals(contextRoot)) {
			StringTokenizer stok = new StringTokenizer(contextRoot, "."); //$NON-NLS-1$
			while (stok.hasMoreTokens()) {
				String token = stok.nextToken();
				for (int i = 0; i < token.length(); i++) {
					if (!(token.charAt(i) == '_') && !(token.charAt(i) == '-') && !(token.charAt(i) == '/') && Character.isLetterOrDigit(token.charAt(i)) == false) {
						Object[] invalidChar = new Object[]{(new Character(token.charAt(i))).toString()};
						String errorStatus = ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.The_character_is_invalid_in_a_context_root, invalidChar); //$NON-NLS-1$
						return J2EEPlugin.newErrorStatus(errorStatus, null);
					}
				}
			}
		} else
			return J2EEPlugin.newErrorStatus(ProjectSupportResourceHandler.getString(ProjectSupportResourceHandler.Names_cannot_begin_or_end_with_whitespace_5, new Object[]{contextRoot}), null); //$NON-NLS-1$
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
			String expectedOutFolder = rawConfigFolder+"/"+J2EEConstants.WEB_INF_CLASSES;
			if( !outFolder.equals(expectedOutFolder) ){
				String message = ProjectSupportResourceHandler.getString( ProjectSupportResourceHandler.DYNAMIC_WEB_PERFORMANCE_VALIDATION,
						new Object[]{expectedOutFolder});
				return WTPCommonPlugin.createWarningStatus(message);
			}
		}
		return OK_STATUS;
	}
}
