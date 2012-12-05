/*******************************************************************************

 * Copyright (c) 2010, 2012 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/

package org.eclipse.jst.j2ee.web.project.facet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetProjectCreationDataModelProvider;
import org.eclipse.osgi.util.NLS;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;
import org.eclipse.wst.common.frameworks.internal.operations.ProjectCreationDataModelProviderNew;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonMessages;
import org.eclipse.wst.common.frameworks.internal.plugin.WTPCommonPlugin;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class WebFragmentFacetProjectCreationDataModelProvider extends J2EEFacetProjectCreationDataModelProvider implements IWebFragmentProjectCreationDataModelProperties{

	public WebFragmentFacetProjectCreationDataModelProvider() {
		super();
	}
	
	public IProjectFacet WEBFRAGMENT_FACET = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEBFRAGMENT_MODULE);

	
	@Override
	public void init() {
		super.init();
        Collection<IProjectFacet> requiredFacets = new ArrayList<IProjectFacet>();
        requiredFacets.add(JavaFacetUtils.JAVA_FACET);
        requiredFacets.add(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEBFRAGMENT_MODULE));
        setProperty(REQUIRED_FACETS_COLLECTION, requiredFacets);
		FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
		IDataModel javaFacet = map.getFacetDataModel(JavaFacetUtils.JAVA_FACET.getId());
		javaFacet.setProperty(IJavaFacetInstallDataModelProperties.DEFAULT_OUTPUT_FOLDER_NAME, "bin"); //$NON-NLS-1$
		
		IDataModel webFragmentFacet = map.getFacetDataModel(WEBFRAGMENT_FACET.getId());
		webFragmentFacet.addListener(new IDataModelListener() {
			public void propertyChanged(DataModelEvent event) {
				if (IWebFragmentFacetInstallDataModelProperties.WAR_PROJECT_NAME.equals(event.getPropertyName())) {
					if (isPropertySet(WAR_PROJECT_NAME))
						setProperty(WAR_PROJECT_NAME, event.getProperty());
					else
						model.notifyPropertyChange(WAR_PROJECT_NAME, IDataModel.DEFAULT_CHG);
				}else if (IWebFragmentFacetInstallDataModelProperties.ADD_TO_WAR.equals(event.getPropertyName())) {
					setProperty(ADD_TO_WAR, event.getProperty());
				}
			}
		});	
	}
	
	@Override
	public IStatus validate(String propertyName) {
		if ( WAR_PROJECT_NAME.equals(propertyName) ) {
				if (model.getBooleanProperty(ADD_TO_WAR)) {
					
					IStatus status = validateWebProjForWebFragment(model.getStringProperty(WAR_PROJECT_NAME));
					if (!status.isOK())
						return status;
					
					if (getStringProperty(IFacetProjectCreationDataModelProperties.FACET_PROJECT_NAME).equals(getStringProperty(WAR_PROJECT_NAME))) {
						String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.SAME_MODULE_AND_EAR_NAME, new Object[]{getStringProperty(WAR_PROJECT_NAME)});
						return WTPCommonPlugin.createErrorStatus(errorMessage);
					}
				}
		}
		return super.validate(propertyName);
	}
	
	
	protected IStatus validateWebProjForWebFragment(String webAppName) {
		if (webAppName.indexOf("#") != -1 || webAppName.indexOf("/") != -1) { //$NON-NLS-1$ //$NON-NLS-2$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_INVALID_CHARS);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		} else if (webAppName.equals("")) { //$NON-NLS-1$
			String errorMessage = WTPCommonPlugin.getResourceString(WTPCommonMessages.ERR_EMPTY_MODULE_NAME);
			return WTPCommonPlugin.createErrorStatus(errorMessage);
		}
		
		IStatus status = ProjectCreationDataModelProviderNew.validateProjectName(webAppName);
		//check for the deleted case, the project is deleted from the workspace but still exists in the
		//file system.
		if( status.isOK()){
			IProject webProj = JavaEEProjectUtilities.getProject(getStringProperty(WAR_PROJECT_NAME));
			if( !webProj.exists() ){
				IPath path = ResourcesPlugin.getWorkspace().getRoot().getLocation();
				path = path.append(webAppName);
				status = ProjectCreationDataModelProviderNew.validateExisting(webAppName, path.toOSString());
			} else{
				IProjectFacetVersion facetVersion = JavaEEProjectUtilities.getProjectFacetVersion(webProj, IJ2EEFacetConstants.DYNAMIC_WEB);
				if(facetVersion != null){
					if(facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_22 ||
					   facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_23 ||
					   facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_24 ||
					   facetVersion == IJ2EEFacetConstants.DYNAMIC_WEB_25){
						String errorMessage = Resources.WEB_FRAGMENT_WEB_INCOMPATIBILITY;
						return WTPCommonPlugin.createErrorStatus(errorMessage);
					}
				}
			}
		}
		return status;
	}
	
	@Override
	public Object getDefaultProperty(String propertyName) {
		if (ADD_TO_WAR.equals(propertyName)) {
			return true;
		}else if (WAR_PROJECT_NAME.equals(propertyName)) {
			IDataModel nestedJ2EEFacetDataModel = getJ2EEFacetModel();
			if (nestedJ2EEFacetDataModel!=null)
				return nestedJ2EEFacetDataModel.getProperty(IWebFragmentFacetInstallDataModelProperties.WAR_PROJECT_NAME);
		}else if (propertyName.equals(ADD_TO_EAR)) {
			return false;
		}
		return super.getDefaultProperty(propertyName);
	}
	
	@Override
	public Set getPropertyNames() {
		Set names = super.getPropertyNames();
		names.add(WAR_PROJECT_NAME);
		names.add(ADD_TO_WAR);
		
		return names;
	}
	
	 private static final class Resources
	    
     extends NLS
     
 {
     public static String WEB_FRAGMENT_WEB_INCOMPATIBILITY;
     static
     {
         initializeMessages( WebFragmentFacetProjectCreationDataModelProvider.class.getName(), 
                             Resources.class );
     }
 }
}
