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
package org.eclipse.jst.j2ee.internal.project.facet;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetConstants;
import org.eclipse.wst.common.componentcore.datamodel.FacetProjectCreationDataModelProvider;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;

public class EARFacetProjectCreationDataModelProvider extends FacetProjectCreationDataModelProvider {

	public EARFacetProjectCreationDataModelProvider() {
		super();
	}

	@Override
	public void init() {
		super.init();

		Collection<IProjectFacet> requiredFacets = new ArrayList<IProjectFacet>();
        requiredFacets.add(IJ2EEFacetConstants.ENTERPRISE_APPLICATION_FACET);
        setProperty(REQUIRED_FACETS_COLLECTION, requiredFacets);
	}
	
}
