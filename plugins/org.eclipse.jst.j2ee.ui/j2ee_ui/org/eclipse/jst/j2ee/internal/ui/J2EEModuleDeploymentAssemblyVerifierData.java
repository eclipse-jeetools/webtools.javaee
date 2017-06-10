/*******************************************************************************
 * Copyright (c) 2010 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jst.j2ee.internal.ui.J2EEModuleDependenciesPropertyPage.ClasspathEntryProxy;
import org.eclipse.wst.common.componentcore.resources.IVirtualComponent;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;
import org.eclipse.wst.common.componentcore.ui.internal.propertypage.verifier.DeploymentAssemblyVerifierData;
import org.eclipse.wst.common.componentcore.ui.propertypage.AddModuleDependenciesPropertiesPage.ComponentResourceProxy;
import org.eclipse.wst.common.project.facet.core.runtime.IRuntime;

public class J2EEModuleDeploymentAssemblyVerifierData extends DeploymentAssemblyVerifierData{
	
	public J2EEModuleDeploymentAssemblyVerifierData(IVirtualComponent component, IRuntime runtime, ArrayList<IVirtualReference> currentReferences,
			ArrayList<ComponentResourceProxy> resourceMappings, boolean resourceMappingsChanged, List<ClasspathEntryProxy> currentClasspathEntries2) {
		super(component, runtime, currentReferences,resourceMappings, resourceMappingsChanged);
		this.currentClasspathEntries = currentClasspathEntries2;
	}
	private List<ClasspathEntryProxy> currentClasspathEntries;

	public List<ClasspathEntryProxy> getCurrentClasspathEntries() {
		return currentClasspathEntries;
	}
	public void setCurrentClasspathEntries(List<ClasspathEntryProxy> currentClasspathEntries) {
		this.currentClasspathEntries = currentClasspathEntries;
	}
}
