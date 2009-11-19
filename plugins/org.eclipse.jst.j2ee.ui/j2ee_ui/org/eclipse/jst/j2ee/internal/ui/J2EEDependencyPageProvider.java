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
package org.eclipse.jst.j2ee.internal.ui;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.ui.propertypage.IDependencyPageProvider;
import org.eclipse.wst.common.componentcore.ui.propertypage.IModuleDependenciesControl;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class J2EEDependencyPageProvider implements IDependencyPageProvider {

	public boolean canHandle(IFacetedProject project) {
		return isJavaEENotEarWeb(project);
	}

	protected boolean isJavaEENotEarWeb(IFacetedProject fp) {
		return fp.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE)) ||
				fp.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_APPCLIENT_MODULE)) ||
						fp.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_CONNECTOR_MODULE));
	}

	public IModuleDependenciesControl[] createPages(IFacetedProject project,
			ModuleAssemblyRootPage parent) {
		
		return new IModuleDependenciesControl[] { 
				new J2EEModuleDependenciesPropertyPage(project.getProject(), parent) };
		
	}

	public Composite createRootControl(
			IFacetedProject project,
			IModuleDependenciesControl[] pages,
			Composite parent) {
			return pages[0].createContents(parent);
		
	}
	
	protected boolean isWeb(IFacetedProject project) {
		return project.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE));
	}

	public String getPageTitle(IProject project) {
		
		if (JavaEEProjectUtilities.isEJBProject(project))
			return Messages.J2EEDependencyPageProvider_1;
		if (JavaEEProjectUtilities.isApplicationClientProject(project))
			return Messages.J2EEDependencyPageProvider_2;
		if (JavaEEProjectUtilities.isJCAProject(project))
			return Messages.J2EEDependencyPageProvider_3;
		return Messages.J2EEDependencyPageProvider_4;
	}

}
