/***************************************************************************************************
 * Copyright (c) 2005 Eteration A.S. and others. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Eteration A.S. - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.j2ee.ejb.annotations.internal.xdoclet.facet;

import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class XDocletFacetUtils {

	public static final IProjectFacetVersion WEB_22 = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE).getVersion("2.2"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_23 = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE).getVersion("2.3"); //$NON-NLS-1$
	public static final IProjectFacetVersion WEB_24 = ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE).getVersion("2.4"); //$NON-NLS-1$

}
