/*******************************************************************************
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.earcreation;

import org.eclipse.jst.j2ee.project.facet.IJ2EEFacetInstallDataModelProperties;


public interface IEarFacetInstallDataModelProperties extends IJ2EEFacetInstallDataModelProperties {

	//TODO what is this???
	public static final String CONTENT_DIR = "IEarFacetInstallDataModelProperties.CONTENT_DIR";//$NON-NLS-1$

	public static final String J2EE_PROJECTS_LIST = "IEarFacetInstallDataModelProperties.J2EE_PROJECTS_LIST"; //$NON-NLS-1$
	public static final String JAVA_PROJECT_LIST = "IEarFacetInstallDataModelProperties.JAVA_PROJECT_LIST"; //$NON-NLS-1$
}
