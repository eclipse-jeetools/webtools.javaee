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

import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.project.facet.core.IProjectFacet;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class EARFacetUtils {

    public static final IProjectFacet EAR_FACET = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE);
	public static final IProjectFacetVersion EAR_12 = EAR_FACET.getVersion("1.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion EAR_13 = EAR_FACET.getVersion("1.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion EAR_14 = EAR_FACET.getVersion("1.4"); //$NON-NLS-1$

	
}
