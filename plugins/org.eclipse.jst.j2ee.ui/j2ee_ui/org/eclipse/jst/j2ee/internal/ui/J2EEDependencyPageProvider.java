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
import org.eclipse.jst.common.internal.modulecore.util.JavaModuleComponentUtility;
import org.eclipse.jst.common.ui.internal.assembly.wizard.ManifestModuleDependencyControl;
import org.eclipse.jst.j2ee.project.JavaEEProjectUtilities;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.ui.propertypage.IDependencyPageProvider;
import org.eclipse.wst.common.componentcore.ui.propertypage.IModuleDependenciesControl;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class J2EEDependencyPageProvider implements IDependencyPageProvider {

	private IModuleDependenciesControl[] controls;
	
	public boolean canHandle(IFacetedProject project) {
		return isJavaEENotEarWeb(project);
	}

	protected boolean isJavaEENotEarWeb(IFacetedProject fp) {
		return fp.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_EJB_MODULE)) ||
				fp.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_APPCLIENT_MODULE)) ||
						fp.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_CONNECTOR_MODULE)) ||
						fp.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_UTILITY_MODULE)) ||
						fp.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEBFRAGMENT_MODULE));
	}

	public IModuleDependenciesControl[] createPages(IFacetedProject project,
			ModuleAssemblyRootPage parent) {
		
		if( isStandalone(project.getProject())) 
			return new IModuleDependenciesControl[] {
				new J2EEModuleDependenciesPropertyPage(project.getProject(), parent)};
		
		return new IModuleDependenciesControl[] { 
				new J2EEModuleDependenciesPropertyPage(project.getProject(), parent),
				new ManifestModuleDependencyControl(project.getProject(), parent)};
		
	}
	
	/*
	 * This now allows extenders to say whether this project is standalone or not
	 */
	protected boolean isStandalone(IProject project) {
		return JavaModuleComponentUtility.findParentProjects(project).length == 0;
	}

	public Composite createRootControl(IFacetedProject project,IModuleDependenciesControl[] pages,
			Composite parent) {
		
		if( isStandalone(project.getProject()))
			return pages[0].createContents(parent);
		
		final TabFolder folder = new TabFolder(parent, SWT.LEFT);
		folder.setLayoutData(new GridData(GridData.FILL_BOTH));
		folder.setFont(parent.getFont());

		// Create the two tabs 
		controls = new IModuleDependenciesControl[2];
		controls[0] = pages[0];
		controls[1] = pages[1];
		
		TabItem tab = new TabItem(folder, SWT.NONE);
		tab.setControl(controls[0].createContents(folder));
		tab.setText(Messages.J2EEDependencyPageProvider_4);
		tab = new TabItem(folder, SWT.NONE);
		tab.setControl(controls[1].createContents(folder));
		tab.setText(Messages.J2EEDependencyPageProvider_5); 
	
		folder.setSelection(0);
		return folder;
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
