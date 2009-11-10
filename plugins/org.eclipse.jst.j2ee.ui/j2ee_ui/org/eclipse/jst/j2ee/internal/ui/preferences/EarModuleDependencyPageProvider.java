/******************************************************************************
 * Copyright (c) 2009 Red Hat
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 ******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui.preferences;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;
import org.eclipse.wst.common.componentcore.ui.propertypage.IDependencyPageProvider;
import org.eclipse.wst.common.componentcore.ui.propertypage.IModuleDependenciesControl;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;

public class EarModuleDependencyPageProvider implements IDependencyPageProvider {

	public boolean canHandle(IFacetedProject project) {
		boolean isEAR = project.hasProjectFacet(ProjectFacetsManager.getProjectFacet("jst.ear")); //$NON-NLS-1$
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
	
	public String getPageTitle() {
		return Messages.EarModuleDependencyPageProvider_0;
	}


}
