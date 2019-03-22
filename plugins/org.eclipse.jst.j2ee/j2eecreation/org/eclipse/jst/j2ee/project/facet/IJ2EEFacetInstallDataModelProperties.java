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
package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetInstallDataModelProperties;
import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;

public interface IJ2EEFacetInstallDataModelProperties extends IFacetInstallDataModelProperties, IJ2EEFacetConstants {

	//TODO this property should probably be deleted
	public static final String RUNTIME_TARGET_ID = "IJ2EEFacetInstallDataModelProperties.RUNTIME_TARGET_ID"; //$NON-NLS-1$
	
	public static final String FACET_RUNTIME = IFacetProjectCreationDataModelProperties.FACET_RUNTIME;

	/**
	 *  boolean property for Java EE 5 projects, to create/not create a deployment descriptor, 
	 *  The default value is false
	 */
	public static final String GENERATE_DD = "IJ2EEFacetInstallDataModelProperties.GENERATE_DD"; //$NON-NLS-1$ 
	
}
