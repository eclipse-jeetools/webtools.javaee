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

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jst.j2ee.internal.earcreation.EarFacetInstallDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetDataModelProperties;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class EARFacetProjectCreationDataModelProvider extends FacetProjectCreationDataModelProvider {

	public EARFacetProjectCreationDataModelProvider() {
		super();
	}

	public void init() {
		super.init();
		FacetDataModelMap map = (FacetDataModelMap) getProperty(FACET_DM_MAP);
		IDataModel earFacet = DataModelFactory.createDataModel(new EarFacetInstallDataModelProvider());
		map.add(earFacet);
		
		Collection requiredFacets = new ArrayList();
		requiredFacets.add(ProjectFacetsManager.getProjectFacet(earFacet.getStringProperty(IFacetDataModelProperties.FACET_ID)));
		setProperty(REQUIRED_FACETS_COLLECTION, requiredFacets);
	}
}