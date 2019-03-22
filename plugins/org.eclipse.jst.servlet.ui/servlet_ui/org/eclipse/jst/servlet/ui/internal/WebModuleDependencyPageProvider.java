/******************************************************************************
 * Copyright (c) 2010 Red Hat and Others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 *    Chuck Bridgham - Additional support
 *    Konstantin Komissarchik - misc. UI cleanup
 ******************************************************************************/
package org.eclipse.jst.servlet.ui.internal;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.common.internal.modulecore.util.JavaModuleComponentUtility;
import org.eclipse.jst.common.ui.internal.assembly.wizard.ManifestModuleDependencyControl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.ui.propertypage.IDependencyPageProvider;
import org.eclipse.wst.common.componentcore.ui.propertypage.IModuleDependenciesControl;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class WebModuleDependencyPageProvider implements IDependencyPageProvider {

	private IModuleDependenciesControl[] controls;
	
	public boolean canHandle(IFacetedProject project) {
		boolean isWeb = project.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE));
		isWeb |= project.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.WST_WEB_MODULE));
		return isWeb;
	}

	/*
	 * This now allows extenders to say whether this project is standalone or not
	 */
	protected boolean isStandalone(IProject project) {
		return JavaModuleComponentUtility.findParentProjects(project).length == 0;
	}
	
	public IModuleDependenciesControl[] createPages(IFacetedProject project,
			ModuleAssemblyRootPage parent) {
		
		if( isStandalone(project.getProject())) 
			return new IModuleDependenciesControl[] {
				new WebDependencyPropertyPage(project.getProject(), parent)};
			
		return new IModuleDependenciesControl[] {
				new WebDependencyPropertyPage(project.getProject(), parent),
				new ManifestModuleDependencyControl(project.getProject(), parent)
		};
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
		tab.setControl(create(controls[0],folder));
		tab.setText(Messages.DeploymentAssembly);
		tab = new TabItem(folder, SWT.NONE);
		tab.setControl(create(controls[1],folder));
		tab.setText(Messages.ManifestEntries); 
	
		folder.setSelection(0);
		return folder;
	}
	
	private static Control create( final IModuleDependenciesControl control,
			                       final Composite parent )
	{
		final Composite composite = new Composite( parent, SWT.NONE );
		composite.setLayout( new GridLayout() );
		final Composite inner = control.createContents( composite );
		inner.setLayoutData( new GridData( GridData.FILL_BOTH ) );
		
		return composite;
	}
	
	public String getPageTitle(IProject project) {
		return Messages.WebDeploymentAssembly;
	}
}
