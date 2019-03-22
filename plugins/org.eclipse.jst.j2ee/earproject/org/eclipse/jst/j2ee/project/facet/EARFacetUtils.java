/*******************************************************************************
 * Copyright (c) 2003, 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.project.facet;

import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * @deprecated Use IJ2EEFacetConstants instead 
 */
public class EARFacetUtils implements IJ2EEFacetConstants {

    public static final IProjectFacet EAR_FACET = IJ2EEFacetConstants.ENTERPRISE_APPLICATION_FACET;
	public static final IProjectFacetVersion EAR_12 = IJ2EEFacetConstants.ENTERPRISE_APPLICATION_12;
	public static final IProjectFacetVersion EAR_13 = IJ2EEFacetConstants.ENTERPRISE_APPLICATION_13;
	public static final IProjectFacetVersion EAR_14 = IJ2EEFacetConstants.ENTERPRISE_APPLICATION_14;

	
}
