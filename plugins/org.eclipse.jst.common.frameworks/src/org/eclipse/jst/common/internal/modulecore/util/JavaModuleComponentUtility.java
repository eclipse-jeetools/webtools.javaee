/*******************************************************************************
 * Copyright (c) 2009, 2019 Red Hat and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * Contributors:
 *     Red Hat - Initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.common.internal.modulecore.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jst.common.frameworks.CommonFrameworksPlugin;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * A Utility class to help find current and possible manifest entries
 */
public class JavaModuleComponentUtility {

	private static IJavaComponentDiscerner[] discerners;
	protected static IJavaComponentDiscerner[] getDiscerners() {
		if( discerners == null ) {
			discerners = loadDiscerners();
		}
		return discerners == null ? new IJavaComponentDiscerner[]{} : discerners;
	}
	
	private static IJavaComponentDiscerner[] loadDiscerners() {
		ArrayList<IJavaComponentDiscerner> list = new ArrayList<IJavaComponentDiscerner>();
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        IConfigurationElement[] cf = registry.getConfigurationElementsFor(
        		CommonFrameworksPlugin.PLUGIN_ID, "JavaComponentDiscerner"); //$NON-NLS-1$
        for( int i = 0; i < cf.length; i++ ) {
        	try {
                list.add((IJavaComponentDiscerner)cf[i].createExecutableExtension("class")); //$NON-NLS-1$
        	} catch( CoreException ce ) {
        		// TODO log
        	}
        }
        return list.toArray(new IJavaComponentDiscerner[list.size()]);
	}
	
	public static IProject[] findParentProjects(IProject child) {
		IJavaComponentDiscerner[] extensions = getDiscerners();
		ArrayList<IProject> list = new ArrayList<IProject>();
		for( int i = 0; i < extensions.length; i++ ) {
			list.addAll(Arrays.asList(extensions[i].findParentProjects(child)));
		}
		return list.toArray(new IProject[list.size()]);
	}
	
	public static IVirtualReference[] findPossibleManifestEntries(IProject parentProject, IProject childProject) {
		IJavaComponentDiscerner[] extensions = getDiscerners();
		ArrayList<IVirtualReference> list = new ArrayList<IVirtualReference>();
		for( int i = 0; i < extensions.length; i++ ) {
			list.addAll(Arrays.asList(extensions[i].findPossibleManifestEntries(parentProject, childProject)));
		}
		return list.toArray(new IVirtualReference[list.size()]);
	}

	public static IVirtualReference[] findPossibleManifestEntries(IProject parentProject, IProject childProject, IVirtualReference[] current) {
		IJavaComponentDiscerner[] extensions = getDiscerners();
		ArrayList<IVirtualReference> list = new ArrayList<IVirtualReference>();
		for( int i = 0; i < extensions.length; i++ ) {
			list.addAll(Arrays.asList(extensions[i].findPossibleManifestEntries(parentProject, childProject, current)));
		}
		return list.toArray(new IVirtualReference[list.size()]);
	}

	public static IVirtualReference[] findCurrentManifestEntries(IProject parentProject, IProject childProject) {
		IJavaComponentDiscerner[] extensions = getDiscerners();
		ArrayList<IVirtualReference> list = new ArrayList<IVirtualReference>();
		for( int i = 0; i < extensions.length; i++ ) {
			list.addAll(Arrays.asList(extensions[i].findCurrentManifestEntries(parentProject, childProject)));
		}
		return list.toArray(new IVirtualReference[list.size()]);
	}
}
