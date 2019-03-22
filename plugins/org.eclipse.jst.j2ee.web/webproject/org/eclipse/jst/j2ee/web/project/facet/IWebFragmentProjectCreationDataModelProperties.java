/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.web.project.facet;


import org.eclipse.wst.common.componentcore.datamodel.properties.IFacetProjectCreationDataModelProperties;

public interface IWebFragmentProjectCreationDataModelProperties  
   extends IFacetProjectCreationDataModelProperties{

	public static final String ADD_TO_WAR = "IWebFragmentProjectCreationDataModelProperties.ADD_TO_WAR"; //$NON-NLS-1$
	public static final String WAR_PROJECT_NAME = "IWebFragmentProjectCreationDataModelProperties.WAR_PROJECT_NAME"; //$NON-NLS-1$
	
	
}
