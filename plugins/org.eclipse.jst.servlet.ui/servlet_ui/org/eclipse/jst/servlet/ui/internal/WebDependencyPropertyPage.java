/******************************************************************************
 * Copyright (c) 2010 Red Hat and Others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *    Rob Stryker - initial implementation and ongoing maintenance
 *    Konstantin Komissarchik - misc. UI cleanup
 ******************************************************************************/
package org.eclipse.jst.servlet.ui.internal;

import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.ui.J2EEModuleDependenciesPropertyPage;
import org.eclipse.jst.j2ee.internal.ui.preferences.Messages;
import org.eclipse.wst.common.componentcore.internal.impl.TaskModel;
import org.eclipse.wst.common.componentcore.internal.util.IModuleConstants;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.DependencyPageExtensionManager;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.DependencyPageExtensionManager.ReferenceExtension;
import org.eclipse.wst.common.componentcore.ui.propertypage.IReferenceWizardConstants;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;
import org.eclipse.wst.common.project.facet.core.IFacetedProject;
import org.eclipse.wst.common.project.facet.core.ProjectFacetsManager;

public class WebDependencyPropertyPage extends J2EEModuleDependenciesPropertyPage {

	public WebDependencyPropertyPage(IProject project,
			ModuleAssemblyRootPage page) {
		super(project, page);
	}

	@Override
	protected boolean shouldSaveClasspathEntires() {
		try {
			IFacetedProject facetedProject = ProjectFacetsManager.create(project);
			if( facetedProject != null && facetedProject.hasProjectFacet(ProjectFacetsManager.getProjectFacet(IModuleConstants.JST_WEB_MODULE)))
					return true;
		} catch(CoreException ce) {}
		return false;
	}

	@Override
	protected void createPushButtons() {
		super.createPushButtons();
	}

	@Override
	protected void setCustomReferenceWizardProperties(TaskModel model) {
		model.putObject(IReferenceWizardConstants.DEFAULT_LIBRARY_LOCATION, J2EEConstants.WEB_INF_LIB);
	}

	@Override
	protected String getModuleAssemblyRootPageDescription() {
		return Messages.WebDependencyPropertyPage_1;
	}

	@Override
	protected void filterReferenceTypes( final List<ReferenceExtension> extensions ) 
	{
		// Replace the default one with our own custom one, in class CustomWebProjectReferenceWizardFragment
		
		for( int i = 0, n = extensions.size(); i < n; i++ ) 
		{
			final ReferenceExtension ext = extensions.get( i );
			
			if( ext.getId().equals( "org.eclipse.wst.common.componentcore.ui.newProjectReference" ) )  //$NON-NLS-1$
			{
				extensions.set( i, DependencyPageExtensionManager.getManager().findReferenceExtension( "org.eclipse.jst.servlet.ui.internal.CustomWebProjectReferenceWizardFragment" ) ); //$NON-NLS-1$
			}
		}
	}
	
}
