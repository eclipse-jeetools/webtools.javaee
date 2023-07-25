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

import org.eclipse.core.resources.IProject;
import org.eclipse.wst.common.componentcore.resources.IVirtualReference;

/**
 * This is internal. Please don't use it unless you're prepared 
 * for it to change drastically at any random time. 
 * 
 * This interface represents a utility to help discover the heirarchy 
 * of java-related components, and assist in manifest management.
 */
public interface IJavaComponentDiscerner {
	/**
	 * Return a list of parent projects, projects which already *do* have  
	 * a project reference to this child project
	 * @param child
	 * @return
	 */
	public IProject[] findParentProjects(IProject child);
	
	/**
	 * All returned manifest entries should have a runtime path and archiveName 
	 * which, when combined, will be suitable for export to a child's 
	 * manifest file. If a reference is in a folder that does not
	 * require it to be present in the manifest.mf classpath, it 
	 * should *NOT* be returned here. 
	 * 
	 * All references should have a getRuntimePath() + getArchiveName() that
	 * resolves to the value that should be in the manifest.mf classpath section. 
	 * 
	 * Any reference *already* in the manifest should *not* be returned here
	 * 
	 * @param parentProject
	 * @param childProject
	 * @return
	 */
	public IVirtualReference[] findPossibleManifestEntries(IProject parentProject, IProject childProject);
	
	/**
	 * All returned manifest entries should have a runtime path and archiveName 
	 * which, when combined, will be suitable for export to a child's 
	 * manifest file. If a reference is in a folder that does not
	 * require it to be present in the manifest.mf classpath, it 
	 * should *NOT* be returned here. 
	 * 
	 * All references should have a getRuntimePath() + getArchiveName() that
	 * resolves to the value that should be in the manifest.mf classpath section. 
	 * 
	 * Any reference equivilant to the ones in currentEntries should not be returned
	 * 
	 * @param parentProject
	 * @param childProject
	 * @return
	 */
	public IVirtualReference[] findPossibleManifestEntries(IProject parentProject, IProject childProject, IVirtualReference[] currentEntries);
	
	
	/**
	 * All returned manifest entries should have a runtime path and archiveName 
	 * which, when combined, will be suitable for export to a child's 
	 * manifest file. If a reference is in a folder that does not
	 * require it to be present in the manifest.mf classpath, it 
	 * should *NOT* be returned here. 
	 * 
	 * All references should have a getRuntimePath() + getArchiveName() that
	 * resolves to the value that should be in the manifest.mf classpath section. 
	 * 
	 * Any reference *not* in the manifest should *not* be returned here
	 * 
	 * @param parentProject
	 * @param childProject
	 * @return
	 */
	public IVirtualReference[] findCurrentManifestEntries(IProject parentProject, IProject childProject);
}