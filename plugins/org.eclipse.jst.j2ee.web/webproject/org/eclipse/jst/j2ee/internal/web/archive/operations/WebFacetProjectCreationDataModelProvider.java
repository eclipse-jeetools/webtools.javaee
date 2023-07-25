/*******************************************************************************
 * Copyright (c) 2003, 2019 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.web.archive.operations;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jst.common.project.facet.IJavaFacetInstallDataModelProperties;
import org.eclipse.jst.common.project.facet.JavaFacetUtils;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPlugin;
import org.eclipse.jst.j2ee.internal.plugin.J2EEPreferences.Keys;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.jst.j2ee.project.facet.IJ2EEModuleFacetInstallDataModelProperties;
import org.eclipse.jst.j2ee.project.facet.J2EEFacetProjectCreationDataModelProvider;
import org.eclipse.jst.j2ee.web.project.facet.IWebFacetInstallDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;

public class WebFacetProjectCreationDataModelProvider extends J2EEFacetProjectCreationDataModelProvider {

	public WebFacetProjectCreationDataModelProvider() {
		super();
	}

	@Override
	public void init() {
		super.init();
        
		Collection<IProjectFacet> requiredFacets = new ArrayList<IProjectFacet>();
        requiredFacets.add(JavaFacetUtils.JAVA_FACET);
        requiredFacets.add(IJ2EEFacetConstants.DYNAMIC_WEB_FACET);
        setProperty(REQUIRED_FACETS_COLLECTION, requiredFacets);

        FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
        IDataModel javaFacet = map.getFacetDataModel( JavaFacetUtils.JAVA_FACET.getId() );
		IDataModel webFacet = map.getFacetDataModel( IJ2EEFacetConstants.DYNAMIC_WEB_FACET.getId() );
		//String webRoot = webFacet.getStringProperty(IWebFacetInstallDataModelProperties.CONFIG_FOLDER);
		String webSrc = webFacet.getStringProperty(IWebFacetInstallDataModelProperties.SOURCE_FOLDER);
		javaFacet.setProperty(IJavaFacetInstallDataModelProperties.SOURCE_FOLDER_NAME, webSrc);
		javaFacet.setProperty(IJavaFacetInstallDataModelProperties.DEFAULT_OUTPUT_FOLDER_NAME,
				J2EEPlugin.getDefault().getJ2EEPreferences().getString(Keys.DYN_WEB_OUTPUT_FOLDER) );
		
		webFacet.addListener(new IDataModelListener() {
			@Override
			public void propertyChanged(DataModelEvent event) {
				if (IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME.equals(event.getPropertyName())) {
					if (isPropertySet(EAR_PROJECT_NAME))
						setProperty(EAR_PROJECT_NAME, event.getProperty());
					else
						model.notifyPropertyChange(EAR_PROJECT_NAME, IDataModel.DEFAULT_CHG);
				}else if (IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR.equals(event.getPropertyName())) {
					setProperty(ADD_TO_EAR, event.getProperty());
				}
			}
		});	
	}

	@Override
	public boolean propertySet(String propertyName, Object propertyValue) {
		if( propertyName.equals( MODULE_URI )){
			FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
			IDataModel webFacet = map.getFacetDataModel( J2EEProjectUtilities.DYNAMIC_WEB );	
			webFacet.setProperty( IJ2EEModuleFacetInstallDataModelProperties.MODULE_URI, propertyValue );
		}
		return super.propertySet(propertyName, propertyValue);
	}
}
