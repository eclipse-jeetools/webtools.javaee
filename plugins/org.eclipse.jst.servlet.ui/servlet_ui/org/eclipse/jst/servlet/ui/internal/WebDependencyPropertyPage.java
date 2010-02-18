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
package org.eclipse.jst.servlet.ui.internal;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.j2ee.internal.J2EEConstants;
import org.eclipse.jst.j2ee.internal.ui.J2EEModuleDependenciesPropertyPage;
import org.eclipse.jst.j2ee.internal.ui.preferences.Messages;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.DependencyPageExtensionManager;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.DependencyPageExtensionManager.ReferenceExtension;
import org.eclipse.wst.common.componentcore.ui.propertypage.ModuleAssemblyRootPage;

public class WebDependencyPropertyPage extends J2EEModuleDependenciesPropertyPage {

//	protected Button addWebLibRefButton;
//	private boolean addingWebLib = true;
	public WebDependencyPropertyPage(IProject project,
			ModuleAssemblyRootPage page) {
		super(project, page);
	}

	@Override
	protected String getRuntimePath(IVirtualComponent addedComp, String wizardPath) {
//		if (addingWebLib) {
//			String lastSegment = new Path(wizardPath).lastSegment();
//			return new Path(J2EEConstants.WEB_INF_LIB).append(lastSegment).makeAbsolute().toString();
//		} 
		return super.getRuntimePath(addedComp, wizardPath);
	}
	
	@Override
	protected void createPushButtons() {
		
		addMappingButton = createPushButton(getAddFolderLabel());
		addReferenceButton = createPushButton(getAddReferenceLabel());
		//addWebLibRefButton = createPushButton(getAddWebLibRefLabel());
		editReferenceButton = createPushButton(getEditReferenceLabel());
		removeButton = createPushButton(getRemoveSelectedLabel());
		
	}

	protected String getAddWebLibRefLabel() {
		
		return Messages.WebDependencyPropertyPage_0;
	}

//	@Override
//	protected void handleAddReferenceButton() {
//		
//		addingWebLib = false;
//		super.handleAddReferenceButton();
//	}
//	@Override
//	public void handleEvent(Event event) {
//		super.handleEvent(event);
//		if( event.widget == addWebLibRefButton) 
//			handleAddWebLibMappingButton();
//		
//	}
	
	@Override
	protected void addComponents(ArrayList<IVirtualComponent> components) throws CoreException {
		// First add the components
		super.addComponents(components);
		// Now add to MANIFEST
		
	}

//	private void handleAddWebLibMappingButton() {
//		addingWebLib = true;
//		showReferenceWizard(false);
//	}
	
	@Override
	protected String getModuleAssemblyRootPageDescription() {
		
		return Messages.WebDependencyPropertyPage_1;
	}


	@Override
	protected StringBuffer getCompsForManifest(ArrayList<IVirtualComponent> components) {
		StringBuffer newComps = new StringBuffer();
		for (Iterator iterator = components.iterator(); iterator.hasNext();) {
			IVirtualComponent comp = (IVirtualComponent) iterator.next();
			String runtimePath = objectToRuntimePath.get(comp);
			if (runtimePath.indexOf(J2EEConstants.WEB_INF_LIB) == -1) {
				String archiveName = new Path(runtimePath).lastSegment();
				newComps.append(archiveName);
				newComps.append(' ');
			}
		}
		return newComps;
	}

	@Override
	protected ReferenceExtension[] filterReferenceTypes(ReferenceExtension[] defaults) {
		// Replace the default one with our own custom one, in class CustomWebProjectReferenceWizardFragment
		for( int i = 0; i < defaults.length; i++ ) {
			if( defaults[i].getId().equals("org.eclipse.wst.common.componentcore.ui.newProjectReference")) { //$NON-NLS-1$
				defaults[i] = DependencyPageExtensionManager.getManager().findReferenceExtension("org.eclipse.jst.servlet.ui.internal.CustomWebProjectReferenceWizardFragment"); //$NON-NLS-1$
			}
		}
		return defaults;
	}
}
