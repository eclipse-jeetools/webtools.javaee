/******************************************************************************
 * Copyright (c) 2009 Red Hat, IBM
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 *    Chuck Bridgham - Additional support
 ******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.preferences;

import org.eclipse.core.resources.IProject;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.ui.propertypage.IDependencyPageProvider;
import org.eclipse.wst.common.componentcore.ui.propertypage.IModuleDependenciesControl;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class EarModuleDependencyPageProvider implements IDependencyPageProvider {

	public boolean canHandle(IFacetedProject project) {
		boolean isEAR = project.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EAR_MODULE));
		return isEAR;
	}

	public IModuleDependenciesControl[] createPages(IFacetedProject project,
			ModuleAssemblyRootPage parent) {
		return new IModuleDependenciesControl[] {
				new EarModuleDependenciesPropertyPage(project.getProject(), parent)
		};
	}

	public Composite createRootControl(IFacetedProject project,IModuleDependenciesControl[] pages,
			Composite parent) {
		if( pages.length == 1 && pages[0] != null)
			return pages[0].createContents(parent);
		return null;
	}
	
	public String getPageTitle(IProject project) {
		return Messages.EarModuleDependencyPageProvider_0;
	}



}
