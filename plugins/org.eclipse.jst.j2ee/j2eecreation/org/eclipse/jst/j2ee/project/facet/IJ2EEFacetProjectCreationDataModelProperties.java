/*******************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;


import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;

public interface IJ2EEFacetProjectCreationDataModelProperties  
   extends IFacetProjectCreationDataModelProperties{

	public static final String ADD_TO_EAR = "IJ2EEFacetProjectCreationDataModelProperties.ADD_TO_EAR"; //$NON-NLS-1$
	public static final String EAR_PROJECT_NAME = "IJ2EEFacetProjectCreationDataModelProperties.EAR_PROJECT_NAME"; //$NON-NLS-1$
	public static final String MODULE_URI = "IJ2EEFacetProjectCreationDataModelProperties.MODULE_URI"; //$NON-NLS-1$
	
}
