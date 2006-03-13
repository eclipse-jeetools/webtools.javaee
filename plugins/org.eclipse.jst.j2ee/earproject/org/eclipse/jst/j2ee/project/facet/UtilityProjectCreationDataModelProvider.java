/*******************************************************************************
 * Copyright (c) 2003, 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.jst.common.project.facet.JavaFacetInstallDataModelProvider;
import org.eclipse.jst.j2ee.internal.project.J2EEProjectUtilities;
import org.eclipse.wst.common.frameworks.datamodel.DataModelEvent;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.frameworks.datamodel.IDataModelListener;

public class UtilityProjectCreationDataModelProvider extends J2EEFacetProjectCreationDataModelProvider {

	public UtilityProjectCreationDataModelProvider() {
		super();
	}

	public void init() {
		super.init();
		FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
		IDataModel javaFacet = DataModelFactory.createDataModel(new JavaFacetInstallDataModelProvider());
		map.add(javaFacet);
		IDataModel utilFacet = DataModelFactory.createDataModel(new UtilityFacetInstallDataModelProvider());
		map.add(utilFacet);
		utilFacet.addListener(new IDataModelListener() {
			public void propertyChanged(DataModelEvent event) {
				if (IJ2EEModuleFacetInstallDataModelProperties.EAR_PROJECT_NAME.equals(event.getPropertyName())) {
					setProperty(EAR_PROJECT_NAME, (String)event.getProperty());
				}else if (IJ2EEModuleFacetInstallDataModelProperties.ADD_TO_EAR.equals(event.getPropertyName())) {
					setProperty(ADD_TO_EAR, event.getProperty());
				}
			}
		});			
	}

	public boolean propertySet(String propertyName, Object propertyValue) {
		if( propertyName.equals( MODULE_URI )){
			FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
			IDataModel utilFacet = map.getFacetDataModel( J2EEProjectUtilities.UTILITY );	
			utilFacet.setProperty( IJ2EEModuleFacetInstallDataModelProperties.MODULE_URI, propertyValue );
		}
		return super.propertySet(propertyName, propertyValue);
	}	
}