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
package org.eclipse.jst.j2ee.web.project.facet;

import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;



public interface IWebFacetInstallDataModelProperties extends IJ2EEFacetInstallDataModelProperties {


	public static final String CONTEXT_ROOT = "IWebFacetInstallDataModelProperties.CONTEXT_ROOT";
	
	public static final String CONTENT_DIR ="IWebFacetInstallDataModelProperties.CONTENT_DIR";
	
	public static final String CREATE_WEB_INF_SRC = "IWebFacetInstallDataModelProperties.CREATE_WEB_INF_SRC";

}
